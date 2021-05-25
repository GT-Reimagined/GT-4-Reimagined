package trinsdar.gt4r.proxy;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.proxy.IProxyHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import trinsdar.gt4r.GT4Reimagined;
import trinsdar.gt4r.block.BlockCasing;
import trinsdar.gt4r.block.BlockMachineMaterial;
import trinsdar.gt4r.client.BakedModels;
import trinsdar.gt4r.client.ClientUtil;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.Machines;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static muramasa.antimatter.machine.Tier.LV;

public class ClientHandler implements IProxyHandler {

    public ClientHandler(){
        if (Minecraft.getInstance() != null){
            BakedModels.init();
            ModelLoaderRegistry.registerLoader(BakedModels.LOADER_SAP_BAG.getLoc(), BakedModels.LOADER_SAP_BAG);
        }

    }

    @SuppressWarnings("RedundantTypeArguments")
    public static void setup(FMLClientSetupEvent e) {
        e.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(GT4RData.RUBBER_SAPLING, RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(GT4RData.RUBBER_LEAVES, RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(GT4RData.SAP_BAG, RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(((BlockItem)Machines.DUSTBIN.getItem(LV)).getBlock(), RenderType.getCutout());
            AntimatterAPI.all(BlockCasing.class, t -> RenderTypeLookup.setRenderLayer(t, RenderType.getCutout()));
            AntimatterAPI.all(BlockMachineMaterial.class, b -> RenderTypeLookup.setRenderLayer(b, RenderType.getCutout()));
        });
        ClientUtil.registerEntityRenders();
        copyProgrammerArtIfMissing();
    }

    private static void copyProgrammerArtIfMissing() {
        File dir = new File(".", "resourcepacks");
        File target = new File(dir, "GT4R-Programmer-Art.zip");


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
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}
