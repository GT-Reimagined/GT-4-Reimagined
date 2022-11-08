package trinsdar.gt4r.proxy;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.client.ModelUtils;
import muramasa.antimatter.proxy.IProxyHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.Level;
import trinsdar.gt4r.GT4Reimagined;
import trinsdar.gt4r.block.BlockCasing;
import trinsdar.gt4r.block.BlockMachineMaterial;
import trinsdar.gt4r.block.BlockMaterialChest;
import trinsdar.gt4r.block.BlockRedstoneMachine;
import trinsdar.gt4r.client.MaterialChestRenderer;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.Machines;
import trinsdar.gt4r.data.client.ScreenFactories;

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
            ModelUtils.setRenderLayer(GT4RData.SAP_BAG, RenderType.cutout());
            ModelUtils.setRenderLayer(((BlockItem)Machines.DUSTBIN.getItem(LV)).getBlock(), RenderType.cutout());
            AntimatterAPI.all(BlockCasing.class, t -> ModelUtils.setRenderLayer(t, RenderType.cutout()));
            AntimatterAPI.all(BlockMachineMaterial.class, b -> ModelUtils.setRenderLayer(b, RenderType.cutout()));
            AntimatterAPI.all(BlockMaterialChest.class, b -> ModelUtils.setRenderLayer(b, RenderType.cutout()));
            AntimatterAPI.all(BlockRedstoneMachine.class, b -> ModelUtils.setRenderLayer(b, RenderType.cutout()));
        });
        AntimatterAPI.all(BlockMaterialChest.class, b -> muramasa.antimatter.proxy.ClientHandler.registerBlockEntityRenderer(b.getType().getTileType(), MaterialChestRenderer::new));
        copyProgrammerArtIfMissing();
    }

    private static void copyProgrammerArtIfMissing() {
        File dir = new File(".", "resourcepacks");
        File target = new File(dir, "GT4R-Programmer-Art.zip");
        File target2 = new File(dir, "GT4R-Classic-Tools.zip");


        //if(!target.exists())
            try {
                dir.mkdirs();
                InputStream in = GT4Reimagined.class.getResourceAsStream("/assets/gt4r/programmer_art.zip");
                FileOutputStream out = new FileOutputStream(target);

                byte[] buf = new byte[16384];
                int len = 0;
                while((len = in.read(buf)) > 0)
                    out.write(buf, 0, len);

                in.close();
                out.close();
                in = GT4Reimagined.class.getResourceAsStream("/assets/gt4r/classic_tools.zip");
                out = new FileOutputStream(target2);
                buf = new byte[16384];
                len = 0;
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
