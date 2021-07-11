package trinsdar.gt4r.proxy;

import muramasa.antimatter.tool.IAntimatterTool;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.datagen.GT4RRandomDropBonus;

import static trinsdar.gt4r.data.GT4RData.*;

public class CommonHandler {

    public static void setup(FMLCommonSetupEvent e){
        e.enqueueWork(() -> {
            Registry.register(Registry.LOOT_FUNCTION_TYPE, new ResourceLocation(Ref.ID, "random_drop_bonus"), GT4RRandomDropBonus.RANDOM_DROP_BONUS);
        });
    }
}
