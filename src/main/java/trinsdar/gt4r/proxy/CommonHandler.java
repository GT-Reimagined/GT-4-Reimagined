package trinsdar.gt4r.proxy;

import muramasa.antimatter.tool.IAntimatterTool;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.system.CallbackI;
import trinsdar.gt4r.GT4Reimagined;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.events.CommonEvents;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static trinsdar.gt4r.data.GT4RData.*;

public class CommonHandler {

    public static void setup(FMLCommonSetupEvent e){
        MinecraftForge.EVENT_BUS.register(CommonHandler.class);
        copyResourcePack();
    }

    public static void onBreak(PlayerDestroyItemEvent event){
        if (event.getOriginal().getItem() instanceof IAntimatterTool){
            IAntimatterTool tool = (IAntimatterTool)event.getOriginal().getItem();
            if (tool.getType().isPowered()){
                long maxEnergy = tool.getMaxEnergy(event.getOriginal());
                Item motor = maxEnergy <= 100000 ? MotorLV : maxEnergy < 800000 ? MotorMV : MotorHV;
                Item battery;
                if (maxEnergy <= 100000){
                    battery = maxEnergy == 100000 ? BatterySmallLithium : maxEnergy == 75000 ? BatterySmallCadmium : BatterySmallSodium;
                } else if (maxEnergy < 800000){
                    battery = maxEnergy == 400000 ? BatteryMediumLithium : maxEnergy == 300000 ? BatteryMediumCadmium : BatteryMediumSodium;
                } else {
                    battery = maxEnergy == 1600000 ? BatteryMediumLithium : maxEnergy == 1200000 ? BatteryMediumCadmium : maxEnergy == 1000000 ? EnergyCrystal : BatteryMediumSodium;
                }
                ItemStack batteryStack = new ItemStack(battery);
                ItemStack motorStack = new ItemStack(motor);
                if (!event.getPlayer().addItemStackToInventory(batteryStack)){
                    event.getPlayer().dropItem(batteryStack, true);
                }
                if (!event.getPlayer().addItemStackToInventory(motorStack)){
                    event.getPlayer().dropItem(motorStack, true);
                }
            }
        }
    }

    private static void copyResourcePack(){
        File dir = new File(".", "datapacks");
        File target = new File(dir, "GT4R-Vanilla-Overrides.zip");


        if (!target.exists()) {
            try {
                dir.mkdirs();
                InputStream in = GT4Reimagined.class.getResourceAsStream("/data/overrides.zip");
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
    }
}
