package trinsdar.gt4r.loader.multi;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import trinsdar.gt4r.data.GT4RData;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.INDUSTRIAL_GRINDING;

public class IndustrialGrinder {
    public static void init(){
        CRUSHED_PURIFIED.all().forEach(m -> {
            if (m == AntimatterMaterials.Diamond || m == AntimatterMaterials.Emerald || m == Olivine || m == AntimatterMaterials.Lapis || m == AntimatterMaterials.Redstone || m == Iridium || m == Platinum){
                return;
            }
            if (m == Bauxite){
                addGrinderRecipe(m, AntimatterMaterials.Water, CRUSHED_PURIFIED.get(m, 2 * MaterialTags.ORE_MULTI.getInt(m)), DUST.get(Grossular, 1), DUST.get(Aluminium, 1));
                return;
            }
            int multiplier = (m == Ruby || m == Sapphire) ? 2 : 1;
            MaterialTypeItem<?> dustByproduct = DUST_SMALL;
            if (m == Sodalite){
                dustByproduct = DUST;
            }
            Material oreByProduct1 = m.getByProducts().size() >= 1 ? m.getByProducts().get(0) : MaterialTags.MACERATE_INTO.getMapping(m);
            Material oreByProduct2 = m.getByProducts().size() >= 2 ? m.getByProducts().get(1) : oreByProduct1;

            addGrinderRecipe(m, AntimatterMaterials.Water, CRUSHED_PURIFIED.get(m, 2 * MaterialTags.ORE_MULTI.getInt(m)), dustByproduct.get(oreByProduct1, multiplier), dustByproduct.get(oreByProduct2, multiplier));
        });
        addGrinderRecipe(Platinum, AntimatterMaterials.Water, CRUSHED_PURIFIED.get(Platinum, 2), DUST.get(Nickel, 1), DUST_TINY.get(Iridium, 2));
        addGrinderRecipe(AntimatterMaterials.Diamond, AntimatterMaterials.Water, CRUSHED_PURIFIED.get(AntimatterMaterials.Diamond, 2), DUST_SMALL.get(AntimatterMaterials.Diamond, 2), DUST.get(AntimatterMaterials.Coal, 1));
        addGrinderRecipe(Olivine, AntimatterMaterials.Water, CRUSHED_PURIFIED.get(Olivine, 2), DUST_SMALL.get(Olivine, 2), DUST_SMALL.get(Pyrope, 2));
        addGrinderRecipe(AntimatterMaterials.Emerald, AntimatterMaterials.Water, CRUSHED_PURIFIED.get(AntimatterMaterials.Emerald, 2), DUST_SMALL.get(AntimatterMaterials.Emerald, 2), DUST_SMALL.get(Aluminium, 2));
        addGrinderRecipe(AntimatterMaterials.Lapis, AntimatterMaterials.Water, CRUSHED_PURIFIED.get(AntimatterMaterials.Lapis, 12), DUST.get(Lazurite, 3));
        addGrinderRecipe(AntimatterMaterials.Redstone, AntimatterMaterials.Water, CRUSHED_PURIFIED.get(AntimatterMaterials.Redstone, 10), DUST_SMALL.get(Cinnabar, 1), DUST_SMALL.get(AntimatterMaterials.Glowstone, 1));
        addGrinderRecipe(Iridium, AntimatterMaterials.Water, CRUSHED_PURIFIED.get(Iridium, 2), DUST_SMALL.get(Iridium, 2), DUST_SMALL.get(Platinum, 2));
        addGrinderRecipe(Iridium, Mercury, CRUSHED_PURIFIED.get(Iridium, 2), DUST.get(Platinum, 1));
        addGrinderRecipe(Platinum, Mercury, CRUSHED_PURIFIED.get(Platinum, 3), DUST.get(Nickel, 1), DUST_TINY.get(Iridium, 2));
        addGrinderRecipe(Galena, Mercury, CRUSHED_PURIFIED.get(Galena, 2), DUST_SMALL.get(Sulfur, 1), DUST.get(Silver, 1));
        addGrinderRecipe(AntimatterMaterials.Gold, Mercury, CRUSHED_PURIFIED.get(AntimatterMaterials.Gold, 3), DUST_SMALL.get(AntimatterMaterials.Copper, 1), DUST_SMALL.get(Nickel, 1));
        addGrinderRecipe(AntimatterMaterials.Copper, Mercury, CRUSHED_PURIFIED.get(AntimatterMaterials.Copper, 2), DUST_SMALL.get(Nickel, 1), DUST.get(AntimatterMaterials.Gold, 1));
        addGrinderRecipe(Uraninite, Mercury, CRUSHED_PURIFIED.get(Uraninite, 2), DUST_SMALL.get(Uranium235, 1), DUST.get(Lead, 1));
        addGrinderRecipe(AntimatterMaterials.Iron, SodiumPersulfate, CRUSHED_PURIFIED.get(AntimatterMaterials.Iron, 2), DUST.get(Nickel, 1), DUST_SMALL.get(Tin, 1));
        addGrinderRecipe(Sphalerite, SodiumPersulfate, CRUSHED_PURIFIED.get(Sphalerite, 2), DUST.get(Zinc, 1), DUST_SMALL.get(YellowGarnet, 1));
        addGrinderRecipe(Tetrahedrite, SodiumPersulfate, CRUSHED_PURIFIED.get(Tetrahedrite, 3), DUST_SMALL.get(Zinc, 1), DUST_SMALL.get(Antimony, 1));
        addGrinderRecipe(Tin, SodiumPersulfate, CRUSHED_PURIFIED.get(Tin, 2), DUST.get(Zinc, 1), DUST_SMALL.get(AntimatterMaterials.Iron, 1));
        addGrinderRecipe(AntimatterMaterials.Copper, SodiumPersulfate, CRUSHED_PURIFIED.get(AntimatterMaterials.Copper, 3), DUST_SMALL.get(Nickel, 1), DUST_SMALL.get(AntimatterMaterials.Gold, 1));
        addGrinderRecipe(AntimatterMaterials.Gold, SodiumPersulfate, CRUSHED_PURIFIED.get(AntimatterMaterials.Gold, 2), DUST.get(AntimatterMaterials.Copper, 1), DUST_SMALL.get(Nickel, 1));
        addGrinderRecipe(Uraninite, SodiumPersulfate, CRUSHED_PURIFIED.get(Uraninite, 3), DUST_SMALL.get(Uranium235, 1), DUST_SMALL.get(Lead, 1));
        addGrinderRecipe(Bauxite, SulfuricAcid, CRUSHED_PURIFIED.get(Bauxite, 4), DUST.get(Aluminium, 1), DUST_SMALL.get(Titanium, 1));
        addGrinderRecipe(Ruby, SulfuricAcid, GEM.get(Ruby, 2), DUST_SMALL.get(RedGarnet, 1));
        addGrinderRecipe(Sapphire, SulfuricAcid, GEM.get(Sapphire, 2), DUST_SMALL.get(Aluminium, 1));
        addGrinderRecipe(AntimatterMaterials.Diamond, SulfuricAcid, new ItemStack(Items.DIAMOND, 2));
        addGrinderRecipe(AntimatterMaterials.Emerald, SulfuricAcid, new ItemStack(Items.EMERALD, 2), DUST_SMALL.get(Aluminium, 1));
        addGrinderRecipe(Olivine, SulfuricAcid, GEM.get(Olivine, 2), DUST_SMALL.get(Pyrope, 2));

        INDUSTRIAL_GRINDING.RB().ii(of(new ItemStack(GT4RData.GRANITE_BLACK.getState().getBlock(), 16))).fi(AntimatterMaterials.Water.getLiquid(1000)).io(DUST.get(BlackGranite, 16), DUST_SMALL.get(Thorium, 1)).add(1600, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(new ItemStack(GT4RData.GRANITE_RED.getState().getBlock(), 16))).fi(AntimatterMaterials.Water.getLiquid(1000)).io(DUST.get(RedGranite, 16), DUST_SMALL.get(Uranium238, 1)).add(1600, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(new ItemStack(Items.END_STONE, 1))).fi(AntimatterMaterials.Water.getLiquid(1000)).io(DUST.get(AntimatterMaterials.Endstone, 16), DUST_TINY.get(Tungsten, 1)).add(1600, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(TagUtils.getForgelikeItemTag("sandless_ores/" + AntimatterMaterials.Coal.getId()), 1)).fi(AntimatterMaterials.Water.getLiquid(1000)).io(new ItemStack(Items.COAL), DUST.get(AntimatterMaterials.Coal, 1), DUST_SMALL.get(Thorium, 1)).add(100, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(Items.NETHER_QUARTZ_ORE, 1)).fi(AntimatterMaterials.Water.getLiquid(1000)).io(new ItemStack(Items.QUARTZ, 4), DUST_SMALL.get(AntimatterMaterials.Netherrack, 2)).add(100, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(Items.TERRACOTTA, 2)).fi(AntimatterMaterials.Water.getLiquid(1000)).io(DUST.get(Clay, 2)).add(100, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(Items.NETHERRACK, 16)).fi(AntimatterMaterials.Water.getLiquid(1000)).io(DUST.get(AntimatterMaterials.Netherrack, 16), DUST_TINY.get(AntimatterMaterials.Gold, 1)).add(1600, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(Items.NETHERRACK, 8)).fi(Mercury.getLiquid(1000)).io(DUST.get(AntimatterMaterials.Netherrack, 8), DUST_TINY.get(AntimatterMaterials.Gold, 5)).add(1600, 120);
        if (AntimatterAPI.isModLoaded("cinderscapes")){
            INDUSTRIAL_GRINDING.RB().ii(of(AntimatterPlatformUtils.getItemFromID(new ResourceLocation("cinderscapes", "rose_quartz_ore")), 1)).fi(AntimatterMaterials.Water.getLiquid(1000)).io(new ItemStack(AntimatterPlatformUtils.getItemFromID(new ResourceLocation("cinderscapes", "rose_quartz")), 4), DUST_SMALL.get(AntimatterMaterials.Netherrack, 2)).add(100, 120);
            INDUSTRIAL_GRINDING.RB().ii(of(AntimatterPlatformUtils.getItemFromID(new ResourceLocation("cinderscapes", "smoky_quartz_ore")), 1)).fi(AntimatterMaterials.Water.getLiquid(1000)).io(new ItemStack(AntimatterPlatformUtils.getItemFromID(new ResourceLocation("cinderscapes", "smoky_quartz")), 4), DUST_SMALL.get(AntimatterMaterials.Netherrack, 2)).add(100, 120);
            INDUSTRIAL_GRINDING.RB().ii(of(AntimatterPlatformUtils.getItemFromID(new ResourceLocation("cinderscapes", "sulfur_quartz_ore")), 1)).fi(AntimatterMaterials.Water.getLiquid(1000)).io(new ItemStack(AntimatterPlatformUtils.getItemFromID(new ResourceLocation("cinderscapes", "sulfur_quartz")), 4), DUST_SMALL.get(AntimatterMaterials.Netherrack, 2)).add(100, 120);
        }
    }

    private static void addGrinderRecipe(Material ore, Material fluid, ItemStack... outputs){
        INDUSTRIAL_GRINDING.RB().ii(of(TagUtils.getForgelikeItemTag("sandless_ores/" + ore.getId()), 1)).fi(fluid.getLiquid(1000)).io(outputs).add(100, 120);
    }
}
