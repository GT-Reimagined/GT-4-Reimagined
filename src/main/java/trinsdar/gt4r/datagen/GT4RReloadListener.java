package trinsdar.gt4r.datagen;

import muramasa.antimatter.Antimatter;
import net.minecraft.resources.DataPackRegistries;
import net.minecraft.resources.IResourceManager;
import net.minecraft.resources.IResourceManagerReloadListener;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.storage.FolderName;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;
import trinsdar.gt4r.GT4RConfig;
import trinsdar.gt4r.GT4Reimagined;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Predicate;

public class GT4RReloadListener implements ISelectiveResourceReloadListener {
    private final DataPackRegistries registries;
    public static MinecraftServer server;

    public GT4RReloadListener(DataPackRegistries registries){
        this.registries = registries;
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {
        Antimatter.LOGGER.info("resources reloading");
        if(server != null) {
            if (GT4RConfig.GAMEPLAY.HARDER_VANILLA_RECIPES) {
                createDataPacks(server);
            }
        }
    }

    public static boolean createDataPacks(MinecraftServer server){
        String path = server.func_240776_a_(FolderName.DATAPACKS).toString();
        File dir = new File(path);
        File target = new File(dir, "GT4R-Vanilla-Overrides.zip");


        if (!target.exists()) {
            try {
                InputStream in = GT4Reimagined.class.getResourceAsStream("/data/overrides.zip");
                FileOutputStream out = new FileOutputStream(target);

                byte[] buf = new byte[16384];
                int len = 0;
                while((len = in.read(buf)) > 0)
                    out.write(buf, 0, len);

                in.close();
                out.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
