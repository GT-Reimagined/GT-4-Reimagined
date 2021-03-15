package trinsdar.gt4r.loader.crafting;

import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.GT4RData;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.BLOCK;
import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.GEM;
import static muramasa.antimatter.Data.PLATE;
import static muramasa.antimatter.machine.Tier.LV;
import static muramasa.antimatter.util.Utils.getForgeItemTag;
import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.Machines.PUMP;
import static trinsdar.gt4r.data.Machines.VACUUM_FREEZER;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.loader.crafting.CraftingHelper.of2;

public class Parts {

    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.addStackRecipe(output, Ref.ID, "circuit_energy_flow", "parts", "has_iridium_plate", provider.hasSafeItem(IridiumReinforcedPlate),
                new ItemStack(CircuitEnergyFlow, 4), of('C', getForgeItemTag("circuits/advanced"), 'T', PLATE.getMaterialTag(Tungsten), 'L', LapotronCrystal, 'P', IridiumReinforcedPlate), "CTC", "LPL", "CTC");
        provider.addStackRecipe(output, Ref.ID, "circuit_data_control", "parts", "has_iridium_plate", provider.hasSafeItem(IridiumReinforcedPlate),
                new ItemStack(CircuitDataControl, 4), of('C', getForgeItemTag("circuits/advanced"), 'c', getForgeItemTag("circuits/data"), 'P', IridiumReinforcedPlate), "CcC", "cPc", "CcC");
        provider.addStackRecipe(output, Ref.ID, "comp_monitor", "parts", "has_glass_pane", provider.hasSafeItem(Items.GLASS_PANE),
                new ItemStack(ComputerMonitor, 1), of2('A', PLATE.getMaterialTag(Aluminium), 'G', Items.GLASS_PANE, 'g', Tags.Items.DYES_GREEN, 'R', Tags.Items.DYES_RED, 'B', Tags.Items.DYES_BLUE, 'D', DUST.getMaterialTag(Glowstone)), "AgA", "RGB", "ADA");
        provider.addStackRecipe(output, Ref.ID, "conv_module", "parts", "has_battery", provider.hasSafeItem(Items.GLASS_PANE),
                new ItemStack(ConveyorModule, 1), of('A', getForgeItemTag("plates/ironaluminium"), 'G', Tags.Items.GLASS, 'B', BatteryRE, 'C', getForgeItemTag("circuits/basic")), "GGG", "AAA", "CBC");
        provider.addStackRecipe(output, Ref.ID, "sawblade", "parts", "has_diamond_dust", provider.hasSafeItem(DUST.getMaterialTag(Diamond)),
                new ItemStack(DiamondSawBlade, 4), of('A', getForgeItemTag("plates/stainless_steel"), 'D', DUST.getMaterialTag(Diamond)), "DAD", "A A", "DAD");
        provider.addStackRecipe(output, Ref.ID, "d_grindhead", "parts", "has_diamond", provider.hasSafeItem(GEM.getMaterialTag(Diamond)),
                new ItemStack(DiamondGrindHead, 4), of('A', getForgeItemTag("plates/steels"), 'D', DUST.getMaterialTag(Diamond), 'G', GEM.getMaterialTag(Diamond)), "DAD", "AGA", "DAD");
        provider.addStackRecipe(output, Ref.ID, "w_grindhead", "parts", "has_steel_block", provider.hasSafeItem(BLOCK.getMaterialTag(Steel)),
                new ItemStack(TungstenGrindHead, 4), of('S', getForgeItemTag("plates/steels"), 'T', PLATE.getMaterialTag(Tungsten), 'B', BLOCK.getMaterialTag(Steel)), "TST", "SBS", "TST");
    }
}
