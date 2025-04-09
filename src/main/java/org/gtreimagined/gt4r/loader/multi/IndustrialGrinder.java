package org.gtreimagined.gt4r.loader.multi;

import org.gtreimagined.gtlib.GTAPI;
import org.gtreimagined.gtlib.data.GTMaterialTypes;
import org.gtreimagined.gtlib.data.GTLibMaterials;
import org.gtreimagined.gtlib.material.Material;
import org.gtreimagined.gtlib.material.MaterialTags;
import org.gtreimagined.gtlib.material.MaterialTypeItem;
import org.gtreimagined.gtlib.util.RegistryUtils;
import org.gtreimagined.gtlib.util.TagUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.gtreimagined.gtcore.data.GTCoreBlocks;

import static org.gtreimagined.gtlib.data.GTMaterialTypes.*;
import static org.gtreimagined.gtlib.data.GTLibMaterials.*;
import static org.gtreimagined.gtlib.recipe.ingredient.RecipeIngredient.of;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gt4r.data.RecipeMaps.INDUSTRIAL_GRINDING;

public class IndustrialGrinder {
    public static void init(){
        GTMaterialTypes.CRUSHED_PURIFIED.all().forEach(m -> {
            if (m == GTLibMaterials.Diamond || m == GTLibMaterials.Emerald || m == Olivine || m == GTLibMaterials.Lapis || m == GTLibMaterials.Redstone || m == Iridium || m == Platinum){
                return;
            }
            if (m == Bauxite){
                addGrinderRecipe(m, GTLibMaterials.Water, GTMaterialTypes.CRUSHED_PURIFIED.get(m, 2 * MaterialTags.ORE_MULTI.getInt(m)), GTMaterialTypes.DUST.get(Grossular, 1), GTMaterialTypes.DUST.get(Aluminium, 1));
                return;
            }
            int multiplier = (m == Ruby || m == Sapphire) ? 2 : 1;
            MaterialTypeItem<?> dustByproduct = GTMaterialTypes.DUST_SMALL;
            if (m == Sodalite){
                dustByproduct = GTMaterialTypes.DUST;
            }
            Material oreByProduct1 = m.getByProducts().size() >= 1 ? m.getByProducts().get(0) : MaterialTags.MACERATE_INTO.getMapping(m);
            Material oreByProduct2 = m.getByProducts().size() >= 2 ? m.getByProducts().get(1) : oreByProduct1;

            addGrinderRecipe(m, GTLibMaterials.Water, GTMaterialTypes.CRUSHED_PURIFIED.get(m, 2 * MaterialTags.ORE_MULTI.getInt(m)), dustByproduct.get(oreByProduct1, multiplier), dustByproduct.get(oreByProduct2, multiplier));
        });
        addGrinderRecipe(Platinum, GTLibMaterials.Water, GTMaterialTypes.CRUSHED_PURIFIED.get(Platinum, 2), GTMaterialTypes.DUST.get(Nickel, 1), GTMaterialTypes.DUST_TINY.get(Iridium, 2));
        addGrinderRecipe(GTLibMaterials.Diamond, GTLibMaterials.Water, GTMaterialTypes.CRUSHED_PURIFIED.get(GTLibMaterials.Diamond, 2), GTMaterialTypes.DUST_SMALL.get(GTLibMaterials.Diamond, 2), GTMaterialTypes.DUST.get(GTLibMaterials.Coal, 1));
        addGrinderRecipe(Olivine, GTLibMaterials.Water, GTMaterialTypes.CRUSHED_PURIFIED.get(Olivine, 2), GTMaterialTypes.DUST_SMALL.get(Olivine, 2), GTMaterialTypes.DUST_SMALL.get(Pyrope, 2));
        addGrinderRecipe(GTLibMaterials.Emerald, GTLibMaterials.Water, GTMaterialTypes.CRUSHED_PURIFIED.get(GTLibMaterials.Emerald, 2), GTMaterialTypes.DUST_SMALL.get(GTLibMaterials.Emerald, 2), GTMaterialTypes.DUST_SMALL.get(Aluminium, 2));
        addGrinderRecipe(GTLibMaterials.Lapis, GTLibMaterials.Water, GTMaterialTypes.CRUSHED_PURIFIED.get(GTLibMaterials.Lapis, 12), GTMaterialTypes.DUST.get(Lazurite, 3));
        addGrinderRecipe(GTLibMaterials.Redstone, GTLibMaterials.Water, GTMaterialTypes.CRUSHED_PURIFIED.get(GTLibMaterials.Redstone, 10), GTMaterialTypes.DUST_SMALL.get(Cinnabar, 1), GTMaterialTypes.DUST_SMALL.get(GTLibMaterials.Glowstone, 1));
        addGrinderRecipe(Iridium, GTLibMaterials.Water, GTMaterialTypes.CRUSHED_PURIFIED.get(Iridium, 2), GTMaterialTypes.DUST_SMALL.get(Iridium, 2), GTMaterialTypes.DUST_SMALL.get(Platinum, 2));
        addGrinderRecipe(Iridium, Mercury, GTMaterialTypes.CRUSHED_PURIFIED.get(Iridium, 2), GTMaterialTypes.DUST.get(Platinum, 1));
        addGrinderRecipe(Platinum, Mercury, GTMaterialTypes.CRUSHED_PURIFIED.get(Platinum, 3), GTMaterialTypes.DUST.get(Nickel, 1), GTMaterialTypes.DUST_TINY.get(Iridium, 2));
        addGrinderRecipe(Galena, Mercury, GTMaterialTypes.CRUSHED_PURIFIED.get(Galena, 2), GTMaterialTypes.DUST_SMALL.get(Sulfur, 1), GTMaterialTypes.DUST.get(Silver, 1));
        addGrinderRecipe(GTLibMaterials.Gold, Mercury, GTMaterialTypes.CRUSHED_PURIFIED.get(GTLibMaterials.Gold, 3), GTMaterialTypes.DUST_SMALL.get(GTLibMaterials.Copper, 1), GTMaterialTypes.DUST_SMALL.get(Nickel, 1));
        addGrinderRecipe(GTLibMaterials.Copper, Mercury, GTMaterialTypes.CRUSHED_PURIFIED.get(GTLibMaterials.Copper, 2), GTMaterialTypes.DUST_SMALL.get(Nickel, 1), GTMaterialTypes.DUST.get(GTLibMaterials.Gold, 1));
        addGrinderRecipe(Uraninite, Mercury, GTMaterialTypes.CRUSHED_PURIFIED.get(Uraninite, 2), GTMaterialTypes.DUST_SMALL.get(Uranium235, 1), GTMaterialTypes.DUST.get(Lead, 1));
        addGrinderRecipe(GTLibMaterials.Iron, SodiumPersulfate, GTMaterialTypes.CRUSHED_PURIFIED.get(GTLibMaterials.Iron, 2), GTMaterialTypes.DUST.get(Nickel, 1), GTMaterialTypes.DUST_SMALL.get(Tin, 1));
        addGrinderRecipe(Sphalerite, SodiumPersulfate, GTMaterialTypes.CRUSHED_PURIFIED.get(Sphalerite, 2), GTMaterialTypes.DUST.get(Zinc, 1), GTMaterialTypes.DUST_SMALL.get(YellowGarnet, 1));
        addGrinderRecipe(Tetrahedrite, SodiumPersulfate, GTMaterialTypes.CRUSHED_PURIFIED.get(Tetrahedrite, 3), GTMaterialTypes.DUST_SMALL.get(Zinc, 1), GTMaterialTypes.DUST_SMALL.get(Antimony, 1));
        addGrinderRecipe(Tin, SodiumPersulfate, GTMaterialTypes.CRUSHED_PURIFIED.get(Tin, 2), GTMaterialTypes.DUST.get(Zinc, 1), GTMaterialTypes.DUST_SMALL.get(GTLibMaterials.Iron, 1));
        addGrinderRecipe(GTLibMaterials.Copper, SodiumPersulfate, GTMaterialTypes.CRUSHED_PURIFIED.get(GTLibMaterials.Copper, 3), GTMaterialTypes.DUST_SMALL.get(Nickel, 1), GTMaterialTypes.DUST_SMALL.get(GTLibMaterials.Gold, 1));
        addGrinderRecipe(GTLibMaterials.Gold, SodiumPersulfate, GTMaterialTypes.CRUSHED_PURIFIED.get(GTLibMaterials.Gold, 2), GTMaterialTypes.DUST.get(GTLibMaterials.Copper, 1), GTMaterialTypes.DUST_SMALL.get(Nickel, 1));
        addGrinderRecipe(Uraninite, SodiumPersulfate, GTMaterialTypes.CRUSHED_PURIFIED.get(Uraninite, 3), GTMaterialTypes.DUST_SMALL.get(Uranium235, 1), GTMaterialTypes.DUST_SMALL.get(Lead, 1));
        addGrinderRecipe(Bauxite, SulfuricAcid, GTMaterialTypes.CRUSHED_PURIFIED.get(Bauxite, 4), GTMaterialTypes.DUST.get(Aluminium, 1), GTMaterialTypes.DUST_SMALL.get(Titanium, 1));
        addGrinderRecipe(Ruby, SulfuricAcid, GTMaterialTypes.GEM.get(Ruby, 2), GTMaterialTypes.DUST_SMALL.get(RedGarnet, 1));
        addGrinderRecipe(Sapphire, SulfuricAcid, GTMaterialTypes.GEM.get(Sapphire, 2), GTMaterialTypes.DUST_SMALL.get(Aluminium, 1));
        addGrinderRecipe(GTLibMaterials.Diamond, SulfuricAcid, new ItemStack(Items.DIAMOND, 2));
        addGrinderRecipe(GTLibMaterials.Emerald, SulfuricAcid, new ItemStack(Items.EMERALD, 2), GTMaterialTypes.DUST_SMALL.get(Aluminium, 1));
        addGrinderRecipe(Olivine, SulfuricAcid, GTMaterialTypes.GEM.get(Olivine, 2), GTMaterialTypes.DUST_SMALL.get(Pyrope, 2));

        INDUSTRIAL_GRINDING.RB().ii(of(new ItemStack(GTCoreBlocks.BLACK_GRANITE.getState().getBlock(), 16))).fi(Water.getLiquid(1000)).io(DUST.get(BlackGranite, 16), DUST_SMALL.get(Thorium, 1)).add("black_granite",1600, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(new ItemStack(GTCoreBlocks.RED_GRANITE.getState().getBlock(), 16))).fi(Water.getLiquid(1000)).io(DUST.get(RedGranite, 16), DUST_SMALL.get(Uranium238, 1)).add("red_granite",1600, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(new ItemStack(Items.END_STONE, 1))).fi(Water.getLiquid(1000)).io(DUST.get(Endstone, 16), DUST_TINY.get(Tungsten, 1)).add("end_stone",1600, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(TagUtils.getForgelikeItemTag("sandless_ores/" + Coal.getId()), 1)).fi(Water.getLiquid(1000)).io(new ItemStack(Items.COAL), DUST.get(Coal, 1), DUST_SMALL.get(Thorium, 1)).add("coal",100, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(Items.NETHER_QUARTZ_ORE, 1)).fi(Water.getLiquid(1000)).io(new ItemStack(Items.QUARTZ, 4), DUST_SMALL.get(Netherrack, 2)).add("nether_quartz",100, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(Items.TERRACOTTA, 2)).fi(Water.getLiquid(1000)).io(DUST.get(Clay, 2)).add("terracotta",100, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(Items.NETHERRACK, 16)).fi(Water.getLiquid(1000)).io(DUST.get(Netherrack, 16), DUST_TINY.get(Gold, 1)).add("netherrack",1600, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(Items.NETHERRACK, 8)).fi(Mercury.getLiquid(1000)).io(DUST.get(Netherrack, 8), DUST_TINY.get(Gold, 5)).add("netherrack_1",1600, 120);
        if (GTAPI.isModLoaded("cinderscapes")){
            INDUSTRIAL_GRINDING.RB().ii(of(RegistryUtils.getItemFromID(new ResourceLocation("cinderscapes", "rose_quartz_ore")), 1)).fi(Water.getLiquid(1000)).io(new ItemStack(RegistryUtils.getItemFromID(new ResourceLocation("cinderscapes", "rose_quartz")), 4), DUST_SMALL.get(Netherrack, 2)).add("rose_quartz",100, 120);
            INDUSTRIAL_GRINDING.RB().ii(of(RegistryUtils.getItemFromID(new ResourceLocation("cinderscapes", "smoky_quartz_ore")), 1)).fi(Water.getLiquid(1000)).io(new ItemStack(RegistryUtils.getItemFromID(new ResourceLocation("cinderscapes", "smoky_quartz")), 4), DUST_SMALL.get(Netherrack, 2)).add("smoky_quartz",100, 120);
            INDUSTRIAL_GRINDING.RB().ii(of(RegistryUtils.getItemFromID(new ResourceLocation("cinderscapes", "sulfur_quartz_ore")), 1)).fi(Water.getLiquid(1000)).io(new ItemStack(RegistryUtils.getItemFromID(new ResourceLocation("cinderscapes", "sulfur_quartz")), 4), DUST_SMALL.get(Netherrack, 2)).add("sulfur_quartz",100, 120);
        }
    }

    private static void addGrinderRecipe(Material ore, Material fluid, ItemStack... outputs){
        INDUSTRIAL_GRINDING.RB().ii(of(TagUtils.getForgelikeItemTag("sandless_ores/" + ore.getId()), 1)).fi(fluid.getLiquid(1000)).io(outputs).add(ore.getId() + "_in_" + fluid.getId(),100, 120);
    }
}
