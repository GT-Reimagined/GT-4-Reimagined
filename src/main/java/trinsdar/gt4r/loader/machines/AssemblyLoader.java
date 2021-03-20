package trinsdar.gt4r.loader.machines;

import com.google.common.collect.ImmutableSet;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import muramasa.antimatter.recipe.ingredient.TagIngredient;
import muramasa.antimatter.util.Utils;
import net.minecraft.tags.ITag;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;
import trinsdar.gt4r.data.CustomTags;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.recipe.ingredient.AntimatterIngredient.of;
import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.Machines.PUMP;
import static trinsdar.gt4r.data.Machines.WATERMILL;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.ASSEMBLING;
import static trinsdar.gt4r.data.Tiers.LV;
import static trinsdar.gt4r.data.Tiers.ULV;

public class AssemblyLoader {
    public static void init() {
        AntimatterAPI.all(Wire.class,t -> {
            Cable<?> cable = AntimatterAPI.get(Cable.class, "cable" + "_" + t.getMaterial().getId());
            if (cable == null) return;
            ImmutableSet<PipeSize> sizes = t.getSizes();
            sizes.forEach(size -> {
                Item wireItem = t.getBlockItem(size);
                Item cableItem = cable.getBlockItem(size);
                ASSEMBLING.RB().ii(of(wireItem,1), of(INGOT.get(Rubber, size.getCableThickness()))).io(new ItemStack(cableItem,1)).add(size.getCableThickness()* 20L,8);
            });
        });
        HULL.all().forEach(m -> {
            ASSEMBLING.RB().ii(of(MachineParts, 1), of(PLATE.getMaterialTag(m), 6)).io(HULL.get(m, 1)).add(400, 8);
        });
        ASSEMBLING.RB().ii(of(getTag("forge", "rods/wooden"), 1), of(getTag("minecraft", "coals"), 1)).io(new ItemStack(Items.TORCH, 4)).add(400, 1);
        ASSEMBLING.RB().ii(of(getTag("forge", "string"), 1), of(getTag("forge", "slimeballs"), 1)).io(new ItemStack(Items.LEAD, 2)).add(200, 2);
        ASSEMBLING.RB().ii(of(new ItemStack(CompressedCoalBall, 8)), of(Items.BRICKS, 1)).io(new ItemStack(CoalChunk)).add(400, 4);
        ASSEMBLING.RB().ii(of(CircuitBoardAdv, 1), of(AdvCircuitParts, 2)).io(new ItemStack(CircuitAdv, 1)).add(1600, 2);
        ASSEMBLING.RB().ii(of(CircuitBoardProcessor, 1), of(CircuitDataStorage, 1)).io(new ItemStack(CircuitDataControl, 2)).add(3200, 4);
        ASSEMBLING.RB().ii(of(CircuitBoardProcessor, 1), of(LapotronCrystal, 1)).io(new ItemStack(CircuitEnergyFlow, 1)).add(3200, 4);
        ASSEMBLING.RB().ii(of(CircuitDataControl, 1), of(CircuitDataStorage, 8)).io(new ItemStack(CircuitDataOrb, 1)).add(12800, 16);
        ASSEMBLING.RB().ii(of(ConveyorModule, 1), of(PumpModule, 1)).io(new ItemStack(ItemTransportValve)).add(3200, 4);
        ASSEMBLING.RB().ii(of(CarbonFibre, 2), INT_CIRCUITS.get(2)).io(new ItemStack(CarbonMesh)).add(800, 2);
        ASSEMBLING.RB().ii(of(CarbonMesh, 16), INT_CIRCUITS.get(16)).io(new ItemStack(LavaFilter)).add(1600, 8);
        ASSEMBLING.RB().ii(of(CustomTags.PLATES_IRON_ALUMINIUM, 1), of(PUMP.getItem(LV), 1)).io(new ItemStack(PumpModule)).add(800, 16);
        ASSEMBLING.RB().ii(of(CustomTags.PLATES_IRON_ALUMINIUM, 2), of(Items.IRON_BARS, 2)).io(new ItemStack(Drain)).add(800, 16);
        ASSEMBLING.RB().ii(of(CustomTags.PLATES_IRON_ALUMINIUM, 1), of(Items.LEVER, 1)).io(new ItemStack(RedstoneMachineController)).add(800, 16);
        ASSEMBLING.RB().ii(of(CustomTags.PLATES_IRON_ALUMINIUM, 1), of(Items.CRAFTING_TABLE, 1)).io(new ItemStack(CraftingModule)).add(800, 16);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Aluminium, 2), of(getTag("forge", "circuits/basic"), 1)).io(new ItemStack(MachineParts, 3)).add(800, 16);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Aluminium, 4), of(getTag("forge", "machine_hull/basic"), 1), of(BatteryRE, 1)).io(new ItemStack(WATERMILL.getItem(ULV), 2)).add(6400, 8);
        ASSEMBLING.RB().ii(of(CustomTags.PLATES_IRON_ALUMINIUM, 1), PLATE.getMaterialIngredient(RedAlloy, 2)).io(new ItemStack(CircuitBoardBasic)).add(800, 1);
        ASSEMBLING.RB().ii(of(CustomTags.PLATES_IRON_ALUMINIUM, 1), PLATE.getMaterialIngredient(Electrum, 2)).io(new ItemStack(CircuitBoardBasic, 2)).add(800, 1);
        ASSEMBLING.RB().ii(of(CustomTags.PLATES_IRON_ALUMINIUM, 1), of(Items.IRON_TRAPDOOR, 1)).io(new ItemStack(Shutter, 1)).add(400, 16);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Iron, 5), of(Tags.Items.CHESTS, 1)).io(new ItemStack(Items.HOPPER, 1)).add(800, 2);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(WroughtIron, 2), of(getTag("forge", "circuits/basic"), 1)).io(new ItemStack(MachineParts, 4)).add(800, 16);




    }

    public static ITag.INamedTag<Item> getTag(String domain, String path){
        return Utils.getItemTag(new ResourceLocation(domain, path));
    }
}
