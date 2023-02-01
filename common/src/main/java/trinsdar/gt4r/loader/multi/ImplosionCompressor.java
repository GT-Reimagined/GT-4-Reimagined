package trinsdar.gt4r.loader.multi;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import trinsdar.gt4r.data.GT4RData;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterials.Diamond;
import static muramasa.antimatter.data.AntimatterMaterials.Emerald;
import static muramasa.antimatter.data.AntimatterMaterialTypes.GEM;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.IMPLOSION_COMPRESSING;

public class ImplosionCompressor {
    public static void init(){
        //TODO: ITNT instead of tnt
        IMPLOSION_COMPRESSING.RB().ii(of(GT4RData.IridiumAlloyIngot, 1), of(Items.TNT, 8)).io(new ItemStack(GT4RData.IridiumReinforcedPlate), DUST.get(DarkAsh, 4)).add("iridium_reinforced_plate",20, 30);
        IMPLOSION_COMPRESSING.RB().ii(of(GT4RData.CoalChunk, 1), of(Items.TNT, 8)).io(GEM.get(Diamond, 1), DUST.get(DarkAsh, 4)).add("industrial_diamond",20, 30);
        IMPLOSION_COMPRESSING.RB().ii(DUST.getMaterialIngredient(Emerald, 1), of(Items.TNT, 24)).io(new ItemStack(Items.EMERALD)).add("emerald",20, 30);
        IMPLOSION_COMPRESSING.RB().ii(DUST.getMaterialIngredient(YellowGarnet, 1), of(Items.TNT, 16)).io(GEM.get(YellowGarnet, 1)).add("yellow_garnet",20, 30);
        IMPLOSION_COMPRESSING.RB().ii(DUST.getMaterialIngredient(RedGarnet, 1), of(Items.TNT, 16)).io(GEM.get(RedGarnet, 1)).add("red_garnet",20, 30);
        IMPLOSION_COMPRESSING.RB().ii(DUST.getMaterialIngredient(Sapphire, 1), of(Items.TNT, 24)).io(GEM.get(Sapphire, 1)).add("sapphire",20, 30);
        IMPLOSION_COMPRESSING.RB().ii(DUST.getMaterialIngredient(Diamond, 1), of(Items.TNT, 32)).io(GEM.get(Diamond, 1)).add("diamond",20, 30);
        IMPLOSION_COMPRESSING.RB().ii(DUST.getMaterialIngredient(Ruby, 1), of(Items.TNT, 24)).io(GEM.get(Ruby, 1)).add("ruby",20, 30);
        IMPLOSION_COMPRESSING.RB().ii(DUST.getMaterialIngredient(Olivine, 1), of(Items.TNT, 24)).io(GEM.get(Olivine, 1)).add("olivine",20, 30);
    }
}
