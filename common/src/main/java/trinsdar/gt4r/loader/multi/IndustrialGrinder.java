package trinsdar.gt4r.loader.multi;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
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

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.INDUSTRIAL_GRINDING;

public class IndustrialGrinder {
    public static void init(){
        AntimatterMaterialTypes.CRUSHED_PURIFIED.all().forEach(m -> {
            if (m == AntimatterMaterials.Diamond || m == AntimatterMaterials.Emerald || m == Olivine || m == AntimatterMaterials.Lapis || m == AntimatterMaterials.Redstone || m == Iridium || m == Platinum){
                return;
            }
            if (m == Bauxite){
                addGrinderRecipe(m, AntimatterMaterials.Water, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(m, 2 * MaterialTags.ORE_MULTI.getInt(m)), AntimatterMaterialTypes.DUST.get(Grossular, 1), AntimatterMaterialTypes.DUST.get(Aluminium, 1));
                return;
            }
            int multiplier = (m == Ruby || m == Sapphire) ? 2 : 1;
            MaterialTypeItem<?> dustByproduct = AntimatterMaterialTypes.DUST_SMALL;
            if (m == Sodalite){
                dustByproduct = AntimatterMaterialTypes.DUST;
            }
            Material oreByProduct1 = m.getByProducts().size() >= 1 ? m.getByProducts().get(0) : MaterialTags.MACERATE_INTO.getMapping(m);
            Material oreByProduct2 = m.getByProducts().size() >= 2 ? m.getByProducts().get(1) : oreByProduct1;

            addGrinderRecipe(m, AntimatterMaterials.Water, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(m, 2 * MaterialTags.ORE_MULTI.getInt(m)), dustByproduct.get(oreByProduct1, multiplier), dustByproduct.get(oreByProduct2, multiplier));
        });
        addGrinderRecipe(Platinum, AntimatterMaterials.Water, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(Platinum, 2), AntimatterMaterialTypes.DUST.get(Nickel, 1), AntimatterMaterialTypes.DUST_TINY.get(Iridium, 2));
        addGrinderRecipe(AntimatterMaterials.Diamond, AntimatterMaterials.Water, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(AntimatterMaterials.Diamond, 2), AntimatterMaterialTypes.DUST_SMALL.get(AntimatterMaterials.Diamond, 2), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Coal, 1));
        addGrinderRecipe(Olivine, AntimatterMaterials.Water, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(Olivine, 2), AntimatterMaterialTypes.DUST_SMALL.get(Olivine, 2), AntimatterMaterialTypes.DUST_SMALL.get(Pyrope, 2));
        addGrinderRecipe(AntimatterMaterials.Emerald, AntimatterMaterials.Water, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(AntimatterMaterials.Emerald, 2), AntimatterMaterialTypes.DUST_SMALL.get(AntimatterMaterials.Emerald, 2), AntimatterMaterialTypes.DUST_SMALL.get(Aluminium, 2));
        addGrinderRecipe(AntimatterMaterials.Lapis, AntimatterMaterials.Water, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(AntimatterMaterials.Lapis, 12), AntimatterMaterialTypes.DUST.get(Lazurite, 3));
        addGrinderRecipe(AntimatterMaterials.Redstone, AntimatterMaterials.Water, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(AntimatterMaterials.Redstone, 10), AntimatterMaterialTypes.DUST_SMALL.get(Cinnabar, 1), AntimatterMaterialTypes.DUST_SMALL.get(AntimatterMaterials.Glowstone, 1));
        addGrinderRecipe(Iridium, AntimatterMaterials.Water, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(Iridium, 2), AntimatterMaterialTypes.DUST_SMALL.get(Iridium, 2), AntimatterMaterialTypes.DUST_SMALL.get(Platinum, 2));
        addGrinderRecipe(Iridium, Mercury, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(Iridium, 2), AntimatterMaterialTypes.DUST.get(Platinum, 1));
        addGrinderRecipe(Platinum, Mercury, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(Platinum, 3), AntimatterMaterialTypes.DUST.get(Nickel, 1), AntimatterMaterialTypes.DUST_TINY.get(Iridium, 2));
        addGrinderRecipe(Galena, Mercury, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(Galena, 2), AntimatterMaterialTypes.DUST_SMALL.get(Sulfur, 1), AntimatterMaterialTypes.DUST.get(Silver, 1));
        addGrinderRecipe(AntimatterMaterials.Gold, Mercury, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(AntimatterMaterials.Gold, 3), AntimatterMaterialTypes.DUST_SMALL.get(AntimatterMaterials.Copper, 1), AntimatterMaterialTypes.DUST_SMALL.get(Nickel, 1));
        addGrinderRecipe(AntimatterMaterials.Copper, Mercury, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(AntimatterMaterials.Copper, 2), AntimatterMaterialTypes.DUST_SMALL.get(Nickel, 1), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Gold, 1));
        addGrinderRecipe(Uraninite, Mercury, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(Uraninite, 2), AntimatterMaterialTypes.DUST_SMALL.get(Uranium235, 1), AntimatterMaterialTypes.DUST.get(Lead, 1));
        addGrinderRecipe(AntimatterMaterials.Iron, SodiumPersulfate, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(AntimatterMaterials.Iron, 2), AntimatterMaterialTypes.DUST.get(Nickel, 1), AntimatterMaterialTypes.DUST_SMALL.get(Tin, 1));
        addGrinderRecipe(Sphalerite, SodiumPersulfate, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(Sphalerite, 2), AntimatterMaterialTypes.DUST.get(Zinc, 1), AntimatterMaterialTypes.DUST_SMALL.get(YellowGarnet, 1));
        addGrinderRecipe(Tetrahedrite, SodiumPersulfate, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(Tetrahedrite, 3), AntimatterMaterialTypes.DUST_SMALL.get(Zinc, 1), AntimatterMaterialTypes.DUST_SMALL.get(Antimony, 1));
        addGrinderRecipe(Tin, SodiumPersulfate, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(Tin, 2), AntimatterMaterialTypes.DUST.get(Zinc, 1), AntimatterMaterialTypes.DUST_SMALL.get(AntimatterMaterials.Iron, 1));
        addGrinderRecipe(AntimatterMaterials.Copper, SodiumPersulfate, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(AntimatterMaterials.Copper, 3), AntimatterMaterialTypes.DUST_SMALL.get(Nickel, 1), AntimatterMaterialTypes.DUST_SMALL.get(AntimatterMaterials.Gold, 1));
        addGrinderRecipe(AntimatterMaterials.Gold, SodiumPersulfate, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(AntimatterMaterials.Gold, 2), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Copper, 1), AntimatterMaterialTypes.DUST_SMALL.get(Nickel, 1));
        addGrinderRecipe(Uraninite, SodiumPersulfate, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(Uraninite, 3), AntimatterMaterialTypes.DUST_SMALL.get(Uranium235, 1), AntimatterMaterialTypes.DUST_SMALL.get(Lead, 1));
        addGrinderRecipe(Bauxite, SulfuricAcid, AntimatterMaterialTypes.CRUSHED_PURIFIED.get(Bauxite, 4), AntimatterMaterialTypes.DUST.get(Aluminium, 1), AntimatterMaterialTypes.DUST_SMALL.get(Titanium, 1));
        addGrinderRecipe(Ruby, SulfuricAcid, AntimatterMaterialTypes.GEM.get(Ruby, 2), AntimatterMaterialTypes.DUST_SMALL.get(RedGarnet, 1));
        addGrinderRecipe(Sapphire, SulfuricAcid, AntimatterMaterialTypes.GEM.get(Sapphire, 2), AntimatterMaterialTypes.DUST_SMALL.get(Aluminium, 1));
        addGrinderRecipe(AntimatterMaterials.Diamond, SulfuricAcid, new ItemStack(Items.DIAMOND, 2));
        addGrinderRecipe(AntimatterMaterials.Emerald, SulfuricAcid, new ItemStack(Items.EMERALD, 2), AntimatterMaterialTypes.DUST_SMALL.get(Aluminium, 1));
        addGrinderRecipe(Olivine, SulfuricAcid, AntimatterMaterialTypes.GEM.get(Olivine, 2), AntimatterMaterialTypes.DUST_SMALL.get(Pyrope, 2));

        INDUSTRIAL_GRINDING.RB().ii(of(new ItemStack(GT4RData.GRANITE_BLACK.getState().getBlock(), 16))).fi(Water.getLiquid(1000)).io(DUST.get(BlackGranite, 16), DUST_SMALL.get(Thorium, 1)).add("black_granite",1600, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(new ItemStack(GT4RData.GRANITE_RED.getState().getBlock(), 16))).fi(Water.getLiquid(1000)).io(DUST.get(RedGranite, 16), DUST_SMALL.get(Uranium238, 1)).add("red_granite",1600, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(new ItemStack(Items.END_STONE, 1))).fi(Water.getLiquid(1000)).io(DUST.get(Endstone, 16), DUST_TINY.get(Tungsten, 1)).add("end_stone",1600, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(TagUtils.getForgelikeItemTag("sandless_ores/" + Coal.getId()), 1)).fi(Water.getLiquid(1000)).io(new ItemStack(Items.COAL), DUST.get(Coal, 1), DUST_SMALL.get(Thorium, 1)).add("coal",100, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(Items.NETHER_QUARTZ_ORE, 1)).fi(Water.getLiquid(1000)).io(new ItemStack(Items.QUARTZ, 4), DUST_SMALL.get(Netherrack, 2)).add("nether_quartz",100, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(Items.TERRACOTTA, 2)).fi(Water.getLiquid(1000)).io(DUST.get(Clay, 2)).add("terracotta",100, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(Items.NETHERRACK, 16)).fi(Water.getLiquid(1000)).io(DUST.get(Netherrack, 16), DUST_TINY.get(Gold, 1)).add("netherrack",1600, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(Items.NETHERRACK, 8)).fi(Mercury.getLiquid(1000)).io(DUST.get(Netherrack, 8), DUST_TINY.get(Gold, 5)).add("netherrack_1",1600, 120);
        if (AntimatterAPI.isModLoaded("cinderscapes")){
            INDUSTRIAL_GRINDING.RB().ii(of(AntimatterPlatformUtils.INSTANCE.getItemFromID(new ResourceLocation("cinderscapes", "rose_quartz_ore")), 1)).fi(Water.getLiquid(1000)).io(new ItemStack(AntimatterPlatformUtils.INSTANCE.getItemFromID(new ResourceLocation("cinderscapes", "rose_quartz")), 4), DUST_SMALL.get(Netherrack, 2)).add("rose_quartz",100, 120);
            INDUSTRIAL_GRINDING.RB().ii(of(AntimatterPlatformUtils.INSTANCE.getItemFromID(new ResourceLocation("cinderscapes", "smoky_quartz_ore")), 1)).fi(Water.getLiquid(1000)).io(new ItemStack(AntimatterPlatformUtils.INSTANCE.getItemFromID(new ResourceLocation("cinderscapes", "smoky_quartz")), 4), DUST_SMALL.get(Netherrack, 2)).add("smoky_quartz",100, 120);
            INDUSTRIAL_GRINDING.RB().ii(of(AntimatterPlatformUtils.INSTANCE.getItemFromID(new ResourceLocation("cinderscapes", "sulfur_quartz_ore")), 1)).fi(Water.getLiquid(1000)).io(new ItemStack(AntimatterPlatformUtils.INSTANCE.getItemFromID(new ResourceLocation("cinderscapes", "sulfur_quartz")), 4), DUST_SMALL.get(Netherrack, 2)).add("sulfur_quartz",100, 120);
        }
    }

    private static void addGrinderRecipe(Material ore, Material fluid, ItemStack... outputs){
        INDUSTRIAL_GRINDING.RB().ii(of(TagUtils.getForgelikeItemTag("sandless_ores/" + ore.getId()), 1)).fi(fluid.getLiquid(1000)).io(outputs).add(ore.getId() + "_in_" + fluid.getId(),100, 120);
    }
}
