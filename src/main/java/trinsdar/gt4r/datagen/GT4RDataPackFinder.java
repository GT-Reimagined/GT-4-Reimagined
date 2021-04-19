package trinsdar.gt4r.datagen;

import muramasa.antimatter.Antimatter;
import net.minecraft.resources.FilePack;
import net.minecraft.resources.IPackFinder;
import net.minecraft.resources.IPackNameDecorator;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import trinsdar.gt4r.GT4Reimagined;
import trinsdar.gt4r.Ref;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;

@ParametersAreNonnullByDefault
@Mod.EventBusSubscriber(modid = Ref.ID)
public class GT4RDataPackFinder implements IPackFinder {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void addPackFinder(FMLServerAboutToStartEvent e) {
        Antimatter.LOGGER.info("Adding GT4R's Vanilla Override Datapack to the server...");
        e.getServer().getResourcePacks().addPackFinder(Ref.SERVER_PACK_FINDER);
    }

    @Override
    public void findPacks(Consumer<ResourcePackInfo> infoConsumer, ResourcePackInfo.IFactory infoFactory) {
        File dir = new File(".", "datapacks");
        File target = new File(dir, "GT4R-Vanilla-Overrides.zip");
        String s = "file/" + target.getName();
        ResourcePackInfo resourcepackinfo = ResourcePackInfo.createResourcePack(s, true, () -> new FilePack(target), infoFactory, ResourcePackInfo.Priority.TOP, IPackNameDecorator.BUILTIN);
        if (resourcepackinfo != null) {
            infoConsumer.accept(resourcepackinfo);
        }
    }
}
