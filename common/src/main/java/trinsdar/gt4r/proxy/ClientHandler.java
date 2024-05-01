package trinsdar.gt4r.proxy;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.client.ModelUtils;
import muramasa.antimatter.proxy.IProxyHandler;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.server.packs.AbstractPackResources;
import net.minecraft.server.packs.FilePackResources;
import net.minecraft.server.packs.PackResources;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.Level;
import org.apache.commons.io.FileUtils;
import trinsdar.gt4r.GT4Reimagined;
import trinsdar.gt4r.block.BlockBatBox;
import trinsdar.gt4r.block.BlockCasing;
import trinsdar.gt4r.block.BlockRedstoneMachine;
import trinsdar.gt4r.data.Machines;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static muramasa.antimatter.machine.Tier.LV;

public class ClientHandler implements IProxyHandler {

    public ClientHandler(){
    }

    @SuppressWarnings("RedundantTypeArguments")
    public static void setup() {
        AntimatterAPI.runLaterClient(() -> {
            ModelUtils.setRenderLayer(((BlockItem)Machines.DUSTBIN.getItem(LV)).getBlock(), RenderType.cutout());
            AntimatterAPI.all(BlockCasing.class, t -> ModelUtils.setRenderLayer(t, RenderType.cutout()));
            AntimatterAPI.all(BlockRedstoneMachine.class, b -> ModelUtils.setRenderLayer(b, RenderType.cutout()));
            AntimatterAPI.all(BlockBatBox.class, b -> ModelUtils.setRenderLayer(b, RenderType.cutout()));
        });
        copyProgrammerArtIfMissing();
    }

    private static void copyProgrammerArtIfMissing() {
        File dir = new File(AntimatterPlatformUtils.getConfigDir().getParent().toFile(), "resourcepacks");
        File target = new File(dir, "GT4R-Programmer-Art.zip");
        File target2 = new File(dir, "GT4R-Classic-Tools.zip");
        File target3 = new File(dir, "GT4R-Machine-Alt.zip");


        //if(!target.exists())
            try {
                dir.mkdirs();
                InputStream in = GT4Reimagined.class.getResourceAsStream("/assets/gt4r/gt4r-machine-alt.zip");
                FileOutputStream out = new FileOutputStream(target3);
                byte[] buf = new byte[16384];
                int len = 0;
                while((len = in.read(buf)) > 0)
                    out.write(buf, 0, len);

                in.close();
                out.close();

                in = GT4Reimagined.class.getResourceAsStream("/assets/gt4r/programmer_art.zip");
                out = new FileOutputStream(target);
                buf = new byte[16384];
                while((len = in.read(buf)) > 0)
                    out.write(buf, 0, len);

                in.close();
                out.close();
                in = GT4Reimagined.class.getResourceAsStream("/assets/gt4r/classic_tools.zip");
                out = new FileOutputStream(target2);
                buf = new byte[16384];
                while((len = in.read(buf)) > 0)
                    out.write(buf, 0, len);

                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Level getClientWorld() {
        return Minecraft.getInstance().level;
    }

    @Override
    public Player getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}
