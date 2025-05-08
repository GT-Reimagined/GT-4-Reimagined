package org.gtreimagined.gt4r.loader.machines;

import com.google.common.collect.ImmutableSet;
import org.gtreimagined.gtcore.machine.ChestMachine;
import org.gtreimagined.gtlib.GTAPI;
import org.gtreimagined.gtlib.pipe.PipeSize;
import org.gtreimagined.gtlib.pipe.types.Cable;
import org.gtreimagined.gtlib.pipe.types.Wire;
import org.gtreimagined.gtlib.util.TagUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import org.gtreimagined.gtcore.data.GTCoreBlocks;
import org.gtreimagined.gt4r.data.GT4RBlocks;
import org.gtreimagined.gt4r.data.GT4RMaterialTags;
import org.gtreimagined.gt4r.data.RecipeMaps;
import org.gtreimagined.gtcore.machine.HopperMachine;

import static org.gtreimagined.gtlib.data.GTMaterialTypes.*;
import static org.gtreimagined.gtlib.data.GTLibMaterials.*;
import static org.gtreimagined.gtlib.machine.Tier.*;
import static org.gtreimagined.gtlib.recipe.ingredient.RecipeIngredient.of;
import static org.gtreimagined.gtlib.util.TagUtils.getForgelikeItemTag;
import static org.gtreimagined.gt4r.data.CustomTags.*;
import static org.gtreimagined.gt4r.data.GT4RItems.*;
import static org.gtreimagined.gt4r.data.Machines.*;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gtcore.data.GTCoreItems.*;
import static org.gtreimagined.gtcore.data.GTCoreTags.*;

public class AssemblyLoader {
    public static void init() {
        GTAPI.all(Wire.class, t -> {
            Cable<?> cable = GTAPI.get(Cable.class, "cable" + "_" + t.getMaterial().getId());
            if (cable == null) return;
            ImmutableSet<PipeSize> sizes = t.getSizes();
            sizes.forEach(size -> {
                Item wireItem = t.getBlockItem(size);
                Item cableItem = cable.getBlockItem(size);
                RecipeMaps.ASSEMBLER.RB().ii(of(wireItem,1), PLATE.getMaterialIngredient(Rubber, getRubberAmount(size))).io(new ItemStack(cableItem,1)).add(t.getMaterial().getId() + "_cable_" + size.getId(),size.getCableThickness()* 20L,8);
            });
        });
        GT4RMaterialTags.HULL.all().forEach(m -> {
            RecipeMaps.ASSEMBLER.RB().ii(of(MachineParts, 1), of(PLATE.getMaterialTag(m), 6)).io(GT4RMaterialTags.HULL.get(m, 1)).add(m.getId() + "_hull",400, 8);
        });
        RecipeMaps.ASSEMBLER.RB().ii(of(getTag("forge", "rods/wooden"), 1), of(getTag("minecraft", "coals"), 1)).io(new ItemStack(Items.TORCH, 4)).add("torch",400, 1);
        RecipeMaps.ASSEMBLER.RB().ii(of(getTag("forge", "string"), 1), of(getTag("forge", "slimeballs"), 1)).io(new ItemStack(Items.LEAD, 2)).add("lead", 200, 2);
        RecipeMaps.ASSEMBLER.RB().ii(of(new ItemStack(CompressedCoalBall, 8)), of(Items.BRICKS, 1)).io(new ItemStack(CoalChunk)).add("coal_chunk", 400, 4);
        RecipeMaps.ASSEMBLER.RB().ii(of(CircuitBoardAdvanced, 1), of(AdvCircuitParts, 2)).io(new ItemStack(CircuitAdv, 1)).add("circuit_advanced", 1600, 2);
        RecipeMaps.ASSEMBLER.RB().ii(of(CircuitBoardProcessor, 1), of(CircuitDataStorage, 1)).io(new ItemStack(CircuitDataControl, 2)).add("circuit_data_control", 3200, 4);
        RecipeMaps.ASSEMBLER.RB().ii(of(CircuitBoardProcessor, 1), of(LapotronCrystal, 1)).io(new ItemStack(CircuitEnergyFlow, 1)).add("circuit_energy_flow", 3200, 4);
        RecipeMaps.ASSEMBLER.RB().ii(of(CircuitDataControl, 1), of(CircuitDataStorage, 8)).io(new ItemStack(DataOrb, 1)).add("data_orb",12800, 16);
        RecipeMaps.ASSEMBLER.RB().ii(of(CarbonFibre, 2), SELECTOR_TAG_INGREDIENTS.get(2)).io(new ItemStack(CarbonMesh)).add("carbon_mesh",800, 2);
        RecipeMaps.ASSEMBLER.RB().ii(of(CarbonMesh, 16), SELECTOR_TAG_INGREDIENTS.get(16)).io(new ItemStack(LavaFilter)).add("lava_filter",1600, 8);
        RecipeMaps.ASSEMBLER.RB().ii(of(PLATES_IRON_ALUMINIUM, 1), of(PUMP.getItem(LV), 1)).io(new ItemStack(PumpModule)).add("pump_module",800, 16);
        RecipeMaps.ASSEMBLER.RB().ii(of(PLATES_IRON_ALUMINIUM, 2), of(Items.IRON_BARS, 2)).io(new ItemStack(Drain)).add("drain",800, 16);
        RecipeMaps.ASSEMBLER.RB().ii(of(PLATES_IRON_ALUMINIUM, 1), of(Items.LEVER, 1)).io(new ItemStack(RedstoneMachineController)).add("redstone_machine_controller",800, 16);
        RecipeMaps.ASSEMBLER.RB().ii(of(PLATES_IRON_ALUMINIUM, 1), of(Items.CRAFTING_TABLE, 1)).io(new ItemStack(CraftingModule)).add("crafting_module",800, 16);
        RecipeMaps.ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Aluminium, 2), of(CIRCUITS_BASIC, 1)).io(new ItemStack(MachineParts, 3)).add("machine_parts",800, 16);
        //ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Aluminium, 4), of(MACHINE_HULLS_BASIC, 1), of(BatteryRE, 1)).io(new ItemStack(WATERMILL.getItem(ULV), 2)).add(6400, 8);
        RecipeMaps.ASSEMBLER.RB().ii(of(PLATES_IRON_ALUMINIUM, 1), PLATE.getMaterialIngredient(RedAlloy, 2)).io(new ItemStack(CircuitBoardBasic)).add("circuit_board_basic",800, 1);
        RecipeMaps.ASSEMBLER.RB().ii(of(PLATES_IRON_ALUMINIUM, 1), PLATE.getMaterialIngredient(Electrum, 2)).io(new ItemStack(CircuitBoardBasic, 2)).add("circuit_board_basic_1",800, 1);
        RecipeMaps.ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Plastic, 1), PLATE.getMaterialIngredient(RedAlloy, 2)).io(new ItemStack(CircuitBoardBasic)).add("circuit_board_basic_2",800, 1);
        RecipeMaps.ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Plastic, 1), PLATE.getMaterialIngredient(Electrum, 2)).io(new ItemStack(CircuitBoardBasic, 2)).add("circuit_board_basic_3",800, 1);
        RecipeMaps.ASSEMBLER.RB().ii(of(PLATES_IRON_ALUMINIUM, 1), of(Items.IRON_TRAPDOOR, 1)).io(new ItemStack(Shutter, 1)).add("shutter",400, 16);
        RecipeMaps.ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Iron, 5), of(Tags.Items.CHESTS_WOODEN, 1)).io(new ItemStack(Items.HOPPER, 1)).add("hopper",800, 2);
        RecipeMaps.ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(WroughtIron, 2), of(CIRCUITS_BASIC, 1)).io(new ItemStack(MachineParts, 4)).add("machine_parts_2",800, 16);
        RecipeMaps.ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Plastic, 2), of(CIRCUITS_BASIC, 1)).io(new ItemStack(MachineParts, 2)).add("machine_parts_1",800, 16);
        RecipeMaps.ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Electrum, 2), of(CIRCUITS_BASIC, 1)).io(new ItemStack(CircuitBoardAdvanced)).add("circuit_board_advanced",1600,2);
        RecipeMaps.ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Electrum, 4), PLATE.getMaterialIngredient(Silicon, 1)).io(new ItemStack(CircuitBoardAdvanced, 2)).add("circuit_board_advanced_1",1600,2);
        RecipeMaps.ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Platinum, 1), of(CIRCUITS_ADVANCED, 1)).io(new ItemStack(CircuitBoardProcessor)).add("circuit_board_processor",1600,2);
        RecipeMaps.ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Magnalium, 2), of(MACHINE_HULLS_BASIC, 1), of(REBattery, 1).setIgnoreNbt()).io(new ItemStack(WINDMILL.getItem(ULV), 1)).add("windmill",6400, 8);
        RecipeMaps.ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(TungstenSteel, 1), of(GTCoreBlocks.REINFORCED_STONE, 1)).io(new ItemStack(GT4RBlocks.TUNGSTENSTEEL_REINFORCED_STONE)).add("tungstensteel_reinforced_stone",400, 4);
        RecipeMaps.ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(TungstenSteel, 1), of(GT4RBlocks.IRIDIUM_REINFORCED_STONE, 1)).io(new ItemStack(GT4RBlocks.IRIDIUM_TUNGSTENSTEEL_REINFORCED_STONE)).add("iridium_tungstensteel_reinforced_stone",400, 4);
        RecipeMaps.ASSEMBLER.RB().ii(of(IridiumReinforcedPlate, 1), of(GTCoreBlocks.REINFORCED_STONE, 1)).io(new ItemStack(GT4RBlocks.IRIDIUM_REINFORCED_STONE)).add("iridium_reinforced_stone",400, 4);
        RecipeMaps.ASSEMBLER.RB().ii(of(IridiumReinforcedPlate, 1), of(GT4RBlocks.TUNGSTENSTEEL_REINFORCED_STONE, 1)).io(new ItemStack(GT4RBlocks.IRIDIUM_TUNGSTENSTEEL_REINFORCED_STONE)).add("iridium_tungstensteel_reinforced_stone_1",400, 4);
        RecipeMaps.ASSEMBLER.RB().ii(GEM.getMaterialIngredient(Emerald, 8), of(CIRCUITS_ADVANCED, 1)).io(new ItemStack(CircuitDataStorage, 4)).add("circuit_data_storage",6400, 8);
        RecipeMaps.ASSEMBLER.RB().ii(GEM.getMaterialIngredient(Olivine, 8), of(CIRCUITS_ADVANCED, 1)).io(new ItemStack(CircuitDataStorage, 4)).add("circuit_data_storage_1",6400, 8);
        RecipeMaps.ASSEMBLER.RB().ii(of(Items.ENDER_PEARL, 1), DUST.getMaterialIngredient(Blaze, 1)).io(new ItemStack(Items.ENDER_EYE)).add("ender_eye",400, 2);
        RecipeMaps.ASSEMBLER.RB().ii(of(Items.ENDER_PEARL, 5), ROD.getMaterialIngredient(Blaze, 1)).io(new ItemStack(Items.ENDER_EYE, 5)).add("ender_eye_1",2500, 2);
        //Todo when gt4r makes it to 1.19
        //ASSEMBLING.RB().ii(DUST.getMaterialIngredient(Flint, 5), of(Items.TNT, 1)).io(new ItemStack(ITNT, 5)).add(800, 2);

        RecipeMaps.ASSEMBLER.RB().ii(DUST.getMaterialIngredient(Phosphor, 1), of(getTag("forge", "rods/wooden"), 1)).io(new ItemStack(Match, 4)).add("match",400, 1);
        RecipeMaps.ASSEMBLER.RB().ii(of(Match, 64), of(Items.PAPER, 2)).io(new ItemStack(MatchBook)).add("match_book",400, 1);
        RecipeMaps.ASSEMBLER.RB().ii(of(Items.GUNPOWDER, 4), of(Tags.Items.SAND, 1)).io(new ItemStack(Items.TNT, 1)).add("tnt",400, 1);
        RecipeMaps.ASSEMBLER.RB().ii(DUST.getMaterialIngredient(Glowstone, 1), of(DUSTS_LAPIS_LAZ, 1)).io(new ItemStack(AdvCircuitParts, 2)).add("adv_circuit_parts",800,2);
        RecipeMaps.ASSEMBLER.RB().ii(DUST.getMaterialIngredient(Redstone, 4), DUST.getMaterialIngredient(Glowstone, 4)).io(new ItemStack(Items.REDSTONE_LAMP)).add("redstone_lamp",400, 1);
        RecipeMaps.ASSEMBLER.RB().ii(of(getTag("forge", "rods/wooden"), 1), DUST.getMaterialIngredient(Redstone, 1)).io(new ItemStack(Items.REDSTONE_TORCH)).add("redstone_torch",400, 1);
        RecipeMaps.ASSEMBLER.RB().ii(INGOT.getMaterialIngredient(Iron, 4), DUST.getMaterialIngredient(Redstone, 1)).io(new ItemStack(Items.COMPASS)).add("compass",400, 4);
        RecipeMaps.ASSEMBLER.RB().ii(INGOT.getMaterialIngredient(Gold, 4), DUST.getMaterialIngredient(Redstone, 1)).io(new ItemStack(Items.CLOCK)).add("clock",400, 4);
        RecipeMaps.ASSEMBLER.RB().ii(of(getTag("forge", "rods/wooden"), 1), of(StickyResin, 1)).io(new ItemStack(Items.TORCH, 6)).add("torch_1",400, 1);
        RecipeMaps.ASSEMBLER.RB().ii(of(Items.COAL, 8), GEM.getMaterialIngredient(Flint, 1)).io(new ItemStack(CompressedCoalBall)).add("compressed_coal_ball",400, 4);
        RecipeMaps.ASSEMBLER.RB().ii(of(getTag("forge", "rods/wooden"), 1), of(Tags.Items.COBBLESTONE, 1)).io(new ItemStack(Items.LEVER, 1)).add("lever",400, 1);
        RecipeMaps.ASSEMBLER.RB().ii(of(CircuitBoardBasic, 1), of(GT4RBlocks.CABLE_COPPER.getBlockItem(PipeSize.VTINY), 3)).io(new ItemStack(CircuitBasic)).add("circuit_basic",800, 1);
        RecipeMaps.ASSEMBLER.RB().ii(of(CompressedCoalBall, 8), of(BLOCK.getMaterialTag(Iron), 1)).io(new ItemStack(CoalChunk)).add("coal_chunk_2",400, 4);
        RecipeMaps.ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Carbon, 4), of(MACHINE_HULLS_BASIC, 1), of(REBattery, 1).setIgnoreNbt()).io(new ItemStack(WINDMILL.getItem(ULV), 1)).add("windmill_1",6400, 8);
        //missing
        RecipeMaps.ASSEMBLER.RB().ii(of(AdvancedAlloy, 1), of(getForgelikeItemTag("stone"), 8)).io(new ItemStack(GTCoreBlocks.REINFORCED_STONE, 8)).add("reinforced_stone",400, 4);
        RecipeMaps.ASSEMBLER.RB().ii(of(PLATE.getMaterialTag(Wood), 8), DUST.getMaterialIngredient(Redstone, 1)).io(new ItemStack(Items.NOTE_BLOCK)).add("note_block",800, 1);
        RecipeMaps.ASSEMBLER.RB().ii(of(PLATE.getMaterialTag(Wood), 8), GEM.getMaterialIngredient(Diamond, 1)).io(new ItemStack(Items.JUKEBOX)).add("jukebox",1600, 1);
        RecipeMaps.ASSEMBLER.RB().ii(of(AdvancedAlloy, 2), of(Tags.Items.GLASS, 7)).io(new ItemStack(GTCoreBlocks.REINFORCED_GLASS, 7)).add("reinforced_glass",400, 4);
        RecipeMaps.ASSEMBLER.RB().ii(of(getTag("minecraft", "planks"), 8), DUST.getMaterialIngredient(Redstone, 1)).io(new ItemStack(Items.NOTE_BLOCK)).add("note_block_1",800, 1);
        RecipeMaps.ASSEMBLER.RB().ii(of(getTag("minecraft", "planks"), 8), GEM.getMaterialIngredient(Diamond, 1)).io(new ItemStack(Items.JUKEBOX)).add("jukebox_1",1600, 1);
        RecipeMaps.ASSEMBLER.RB().ii(of(CompressedCoalBall, 8), of(getForgelikeItemTag("obsidian"), 1)).io(new ItemStack(CoalChunk)).add("coal_chunk_3",400, 4);
        RecipeMaps.ASSEMBLER.RB().ii(of(GT4RBlocks.CABLE_TIN.getBlockItem(PipeSize.VTINY), 1), PLATE.getMaterialIngredient(BatteryAlloy, 1)).io(new ItemStack(BatteryHull)).add("battery_hull_small",800, 1);
        RecipeMaps.ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Invar, 2), GEM.getMaterialIngredient(Flint, 1)).io(new ItemStack(LighterEmpty)).add("lighter_empty",256, 16);
        RecipeMaps.ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Zinc, 4), of(CarbonMesh, 4)).io(new ItemStack(ItemFilter)).add("item_filter",1600, 32);
        RecipeMaps.ASSEMBLER.RB().ii(TRANSFORMER.getItem(HV), TransformerUpgrade).io(HVTransformerUpgrade).add("hv_transformer_upgrade", 3200, 4);
        RecipeMaps.ASSEMBLER.RB().ii(of(PLATES_STEELS, 2), of(STEAM_TURBINE.getItem(LV))).io(SteamUpgrade).add("steam_upgrade",1600, 32);
        RecipeMaps.ASSEMBLER.RB().ii(of(PLATES_IRON_ALUMINIUM, 1), of(2, DUST.getMaterialTag(Plastic), DUST.getMaterialTag(Wood))).io(MufflerUpgrade).add("muffler_upgrade",1600, 2);
        GTAPI.all(HopperMachine.class).forEach(hopper -> {
            if (!hopper.getMaterial().has(PLATE)) return;
            RecipeMaps.ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(hopper.getMaterial(), 5), of(Tags.Items.CHESTS_WOODEN), SELECTOR_TAG_INGREDIENTS.get(5)).io(hopper.getItem(NONE)).add(hopper.getId(), 800, 2);
        });
        GTAPI.all(ChestMachine.class).forEach(chest -> {
            if (!chest.getMaterial().has(PLATE) || !chest.getMaterial().has(ROD)) return;
            RecipeMaps.ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(chest.getMaterial(), 4), ROD.getMaterialIngredient(chest.getMaterial(), 2), of(Tags.Items.CHESTS_WOODEN)).io(chest.getItem(NONE)).add(chest.getId(), 800, 2);
        });
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
