package trinsdar.gt4r.loader.machines;

import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import com.google.common.collect.ImmutableSet;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.resources.ResourceLocation;
import muramasa.antimatter.data.ForgeCTags;
import trinsdar.gt4r.data.GT4RMaterialTags;

import static muramasa.antimatter.machine.Tier.LV;
import static muramasa.antimatter.machine.Tier.ULV;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.antimatter.util.TagUtils.getForgelikeItemTag;
import static trinsdar.gt4r.data.CustomTags.*;
import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.Machines.PUMP;
import static trinsdar.gt4r.data.Machines.WINDMILL;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.ASSEMBLING;

public class AssemblyLoader {
    public static void init() {
        AntimatterAPI.all(Wire.class,t -> {
            Cable<?> cable = AntimatterAPI.get(Cable.class, "cable" + "_" + t.getMaterial().getId());
            if (cable == null) return;
            ImmutableSet<PipeSize> sizes = t.getSizes();
            sizes.forEach(size -> {
                Item wireItem = t.getBlockItem(size);
                Item cableItem = cable.getBlockItem(size);
                ASSEMBLING.RB().ii(of(wireItem,1), PLATE.getMaterialIngredient(Rubber, getRubberAmount(size))).io(new ItemStack(cableItem,1)).add(t.getMaterial().getId() + "_cable_" + size.getId(),size.getCableThickness()* 20L,8);
            });
        });
        GT4RMaterialTags.HULL.all().forEach(m -> {
            ASSEMBLING.RB().ii(of(MachineParts, 1), of(PLATE.getMaterialTag(m), 6)).io(GT4RMaterialTags.HULL.get(m, 1)).add(m.getId() + "_hull",400, 8);
        });
        ASSEMBLING.RB().ii(of(getTag("forge", "rods/wooden"), 1), of(getTag("minecraft", "coals"), 1)).io(new ItemStack(Items.TORCH, 4)).add("torch",400, 1);
        ASSEMBLING.RB().ii(of(getTag("forge", "string"), 1), of(getTag("forge", "slimeballs"), 1)).io(new ItemStack(Items.LEAD, 2)).add("lead", 200, 2);
        ASSEMBLING.RB().ii(of(new ItemStack(CompressedCoalBall, 8)), of(Items.BRICKS, 1)).io(new ItemStack(CoalChunk)).add("coal_chunk", 400, 4);
        ASSEMBLING.RB().ii(of(CircuitBoardAdv, 1), of(AdvCircuitParts, 2)).io(new ItemStack(CircuitAdv, 1)).add("circuit_advanced", 1600, 2);
        ASSEMBLING.RB().ii(of(CircuitBoardProcessor, 1), of(CircuitDataStorage, 1)).io(new ItemStack(CircuitDataControl, 2)).add("circuit_data_control", 3200, 4);
        ASSEMBLING.RB().ii(of(CircuitBoardProcessor, 1), of(LapotronCrystal, 1)).io(new ItemStack(CircuitEnergyFlow, 1)).add("circuit_energy_flow", 3200, 4);
        ASSEMBLING.RB().ii(of(CircuitDataControl, 1), of(CircuitDataStorage, 8)).io(new ItemStack(CircuitDataOrb, 1)).add("data_orb",12800, 16);
        ASSEMBLING.RB().ii(of(CarbonFibre, 2), INT_CIRCUITS.get(2)).io(new ItemStack(CarbonMesh)).add("carbon_mesh",800, 2);
        ASSEMBLING.RB().ii(of(CarbonMesh, 16), INT_CIRCUITS.get(16)).io(new ItemStack(LavaFilter)).add("lava_filter",1600, 8);
        ASSEMBLING.RB().ii(of(PLATES_IRON_ALUMINIUM, 1), of(PUMP.getItem(LV), 1)).io(new ItemStack(PumpModule)).add("pump_module",800, 16);
        ASSEMBLING.RB().ii(of(PLATES_IRON_ALUMINIUM, 2), of(Items.IRON_BARS, 2)).io(new ItemStack(Drain)).add("drain",800, 16);
        ASSEMBLING.RB().ii(of(PLATES_IRON_ALUMINIUM, 1), of(Items.LEVER, 1)).io(new ItemStack(RedstoneMachineController)).add("redstone_machine_controller",800, 16);
        ASSEMBLING.RB().ii(of(PLATES_IRON_ALUMINIUM, 1), of(Items.CRAFTING_TABLE, 1)).io(new ItemStack(CraftingModule)).add("crafting_module",800, 16);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Aluminium, 2), of(CIRCUITS_BASIC, 1)).io(new ItemStack(MachineParts, 3)).add("machine_parts",800, 16);
        //ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Aluminium, 4), of(MACHINE_HULLS_BASIC, 1), of(BatteryRE, 1)).io(new ItemStack(WATERMILL.getItem(ULV), 2)).add(6400, 8);
        ASSEMBLING.RB().ii(of(PLATES_IRON_ALUMINIUM, 1), PLATE.getMaterialIngredient(RedAlloy, 2)).io(new ItemStack(CircuitBoardBasic)).add("circuit_board_basic",800, 1);
        ASSEMBLING.RB().ii(of(PLATES_IRON_ALUMINIUM, 1), PLATE.getMaterialIngredient(Electrum, 2)).io(new ItemStack(CircuitBoardBasic, 2)).add("circuit_board_basic_1",800, 1);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Plastic, 1), PLATE.getMaterialIngredient(RedAlloy, 2)).io(new ItemStack(CircuitBoardBasic)).add("circuit_board_basic_2",800, 1);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Plastic, 1), PLATE.getMaterialIngredient(Electrum, 2)).io(new ItemStack(CircuitBoardBasic, 2)).add("circuit_board_basic_3",800, 1);
        ASSEMBLING.RB().ii(of(PLATES_IRON_ALUMINIUM, 1), of(Items.IRON_TRAPDOOR, 1)).io(new ItemStack(Shutter, 1)).add("shutter",400, 16);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Iron, 5), of(ForgeCTags.CHESTS, 1)).io(new ItemStack(Items.HOPPER, 1)).add("hopper",800, 2);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(WroughtIron, 2), of(CIRCUITS_BASIC, 1)).io(new ItemStack(MachineParts, 4)).add("machine_parts",800, 16);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Plastic, 2), of(CIRCUITS_BASIC, 1)).io(new ItemStack(MachineParts, 2)).add("machine_parts_1",800, 16);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Electrum, 2), of(CIRCUITS_BASIC, 1)).io(new ItemStack(CircuitBoardAdv)).add("circuit_board_advanced",1600,2);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Electrum, 4), PLATE.getMaterialIngredient(Silicon, 1)).io(new ItemStack(CircuitBoardAdv, 2)).add("circuit_board_advanced_1",1600,2);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Platinum, 1), of(CIRCUITS_ADVANCED, 1)).io(new ItemStack(CircuitBoardProcessor)).add("circuit_board_processor",1600,2);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Magnalium, 2), of(MACHINE_HULLS_BASIC, 1), of(BatteryRE, 1).setIgnoreNbt()).io(new ItemStack(WINDMILL.getItem(ULV), 1)).add("windmill",6400, 8);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(TungstenSteel, 1), of(REINFORCED_STONE, 1)).io(new ItemStack(TUNGSTENSTEEL_REINFORCED_STONE)).add("tungstensteel_reinforced_stone",400, 4);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(TungstenSteel, 1), of(IRIDIUM_REINFORCED_STONE, 1)).io(new ItemStack(IRIDIUM_TUNGSTENSTEEL_REINFORCED_STONE)).add("iridium_tungstensteel_reinforced_stone",400, 4);
        ASSEMBLING.RB().ii(of(IridiumReinforcedPlate, 1), of(REINFORCED_STONE, 1)).io(new ItemStack(IRIDIUM_REINFORCED_STONE)).add("iridium_reinforced_stone",400, 4);
        ASSEMBLING.RB().ii(of(IridiumReinforcedPlate, 1), of(TUNGSTENSTEEL_REINFORCED_STONE, 1)).io(new ItemStack(IRIDIUM_TUNGSTENSTEEL_REINFORCED_STONE)).add("iridium_tungstensteel_reinforced_stone_1",400, 4);
        ASSEMBLING.RB().ii(GEM.getMaterialIngredient(Emerald, 8), of(CIRCUITS_ADVANCED, 1)).io(new ItemStack(CircuitDataStorage, 4)).add("circuit_data_storage",6400, 8);
        ASSEMBLING.RB().ii(GEM.getMaterialIngredient(Olivine, 8), of(CIRCUITS_ADVANCED, 1)).io(new ItemStack(CircuitDataStorage, 4)).add("circuit_data_storage_1",6400, 8);
        ASSEMBLING.RB().ii(of(Items.ENDER_PEARL, 1), DUST.getMaterialIngredient(Blaze, 1)).io(new ItemStack(Items.ENDER_EYE)).add("ender_eye",400, 2);
        ASSEMBLING.RB().ii(of(Items.ENDER_PEARL, 5), ROD.getMaterialIngredient(Blaze, 1)).io(new ItemStack(Items.ENDER_EYE, 5)).add("ender_eye_1",2500, 2);
        //Todo when gt4r makes it to 1.19
        //ASSEMBLING.RB().ii(DUST.getMaterialIngredient(Flint, 5), of(Items.TNT, 1)).io(new ItemStack(ITNT, 5)).add(800, 2);

        ASSEMBLING.RB().ii(DUST.getMaterialIngredient(Phosphor, 1), of(getTag("forge", "rods/wooden"), 1)).io(new ItemStack(Match, 4)).add("match",400, 1);
        ASSEMBLING.RB().ii(of(Match, 64), of(Items.PAPER, 2)).io(new ItemStack(MatchBook)).add("match_book",400, 1);
        ASSEMBLING.RB().ii(of(Items.GUNPOWDER, 4), of(ForgeCTags.SAND, 1)).io(new ItemStack(Items.TNT, 1)).add("tnt",400, 1);
        ASSEMBLING.RB().ii(DUST.getMaterialIngredient(Glowstone, 1), of(DUSTS_LAPIS_LAZ, 1)).io(new ItemStack(AdvCircuitParts, 2)).add("adv_circuit_parts",800,2);
        ASSEMBLING.RB().ii(DUST.getMaterialIngredient(Redstone, 4), DUST.getMaterialIngredient(Glowstone, 4)).io(new ItemStack(Items.REDSTONE_LAMP)).add("redstone_lamp",400, 1);
        ASSEMBLING.RB().ii(of(getTag("forge", "rods/wooden"), 1), DUST.getMaterialIngredient(Redstone, 1)).io(new ItemStack(Items.REDSTONE_TORCH)).add("redstone_torch",400, 1);
        ASSEMBLING.RB().ii(INGOT.getMaterialIngredient(Iron, 4), DUST.getMaterialIngredient(Redstone, 1)).io(new ItemStack(Items.COMPASS)).add("compass",400, 4);
        ASSEMBLING.RB().ii(INGOT.getMaterialIngredient(Gold, 4), DUST.getMaterialIngredient(Redstone, 1)).io(new ItemStack(Items.CLOCK)).add("clock",400, 4);
        ASSEMBLING.RB().ii(of(getTag("forge", "rods/wooden"), 1), of(GTRubberData.StickyResin, 1)).io(new ItemStack(Items.TORCH, 6)).add("torch_1",400, 1);
        ASSEMBLING.RB().ii(of(Items.COAL, 8), GEM.getMaterialIngredient(Flint, 1)).io(new ItemStack(CompressedCoalBall)).add("compressed_coal_ball",400, 4);
        ASSEMBLING.RB().ii(of(getTag("forge", "rods/wooden"), 1), of(ForgeCTags.COBBLESTONE, 1)).io(new ItemStack(Items.LEVER, 1)).add("lever",400, 1);
        ASSEMBLING.RB().ii(of(CircuitBoardBasic, 1), of(CABLE_COPPER.getBlockItem(PipeSize.VTINY), 3)).io(new ItemStack(CircuitBasic)).add("circuit_basic",800, 1);
        ASSEMBLING.RB().ii(of(CompressedCoalBall, 8), of(BLOCK.getMaterialTag(Iron), 1)).io(new ItemStack(CoalChunk)).add("coal_chunk",400, 4);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Carbon, 4), of(MACHINE_HULLS_BASIC, 1), of(BatteryRE, 1).setIgnoreNbt()).io(new ItemStack(WINDMILL.getItem(ULV), 1)).add("windmill_1",6400, 8);
        //missing
        ASSEMBLING.RB().ii(of(AdvancedAlloy, 1), of(getForgelikeItemTag("stone"), 8)).io(new ItemStack(REINFORCED_STONE, 8)).add("reinforced_stone",400, 4);
        ASSEMBLING.RB().ii(of(PLATE.getMaterialTag(Wood), 8), DUST.getMaterialIngredient(Redstone, 1)).io(new ItemStack(Items.NOTE_BLOCK)).add("note_block",800, 1);
        ASSEMBLING.RB().ii(of(PLATE.getMaterialTag(Wood), 8), GEM.getMaterialIngredient(Diamond, 1)).io(new ItemStack(Items.JUKEBOX)).add("jukebox",1600, 1);
        ASSEMBLING.RB().ii(of(AdvancedAlloy, 2), of(ForgeCTags.GLASS, 7)).io(new ItemStack(REINFORCED_GLASS, 7)).add("reinforced_glass",400, 4);
        ASSEMBLING.RB().ii(of(getTag("minecraft", "planks"), 8), DUST.getMaterialIngredient(Redstone, 1)).io(new ItemStack(Items.NOTE_BLOCK)).add("note_block_1",800, 1);
        ASSEMBLING.RB().ii(of(getTag("minecraft", "planks"), 8), GEM.getMaterialIngredient(Diamond, 1)).io(new ItemStack(Items.JUKEBOX)).add("jukebox_1",1600, 1);
        ASSEMBLING.RB().ii(of(CompressedCoalBall, 8), of(getForgelikeItemTag("obsidian"), 1)).io(new ItemStack(CoalChunk)).add("coal_chunk",400, 4);
        ASSEMBLING.RB().ii(of(CABLE_TIN.getBlockItem(PipeSize.VTINY), 1), PLATE.getMaterialIngredient(BatteryAlloy, 1)).io(new ItemStack(BatteryHullSmall)).add("battery_hull_small",800, 1);
        ASSEMBLING.RB().ii(of(CABLE_COPPER.getBlockItem(PipeSize.VTINY), 2), PLATE.getMaterialIngredient(BatteryAlloy, 3)).io(new ItemStack(BatteryHullMedium)).add("battery_hull_medium",1600, 2);
        ASSEMBLING.RB().ii(of(CABLE_GOLD.getBlockItem(PipeSize.VTINY), 4), PLATE.getMaterialIngredient(BatteryAlloy, 9)).io(new ItemStack(BatteryHullLarge)).add("battery_hull_large",6400, 4);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Invar, 2), GEM.getMaterialIngredient(Flint, 1)).io(new ItemStack(LighterEmpty)).add("lighter_empty",256, 16);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Zinc, 4), of(CarbonMesh, 4)).io(new ItemStack(ItemFilter)).add("item_filter",1600, 32);
    }

    public static int getRubberAmount(PipeSize size){
        switch (size){
            case SMALL: return  2;
            case NORMAL: return 3;
            case LARGE: return 4;
            case HUGE: return 5;
            default: return 1;
        }
    }

    public static TagKey<Item> getTag(String domain, String path){
        return TagUtils.getItemTag(new ResourceLocation(domain, path));
    }
}
