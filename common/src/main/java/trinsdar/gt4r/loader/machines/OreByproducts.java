package trinsdar.gt4r.loader.machines;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;
import trinsdar.gt4r.data.GT4RMaterialTags;
import trinsdar.gt4r.data.Machines;
import trinsdar.gt4r.data.Materials;

import java.util.List;

import static muramasa.antimatter.machine.Tier.LV;
import static muramasa.antimatter.machine.Tier.MV;
import static trinsdar.gt4r.data.RecipeMaps.ORE_BYPRODUCTS;

public class OreByproducts {
    public static void init() {
        AntimatterMaterialTypes.CRUSHED.all().forEach(m -> {
            if (!m.has(AntimatterMaterialTypes.ORE)) return;
            RecipeIngredient ore = AntimatterMaterialTypes.ORE.getMaterialIngredient(m ,1);
            RecipeIngredient crushed = AntimatterMaterialTypes.CRUSHED.getIngredient(m, 1);
            if (m.hasByProducts()) {
                List<Material> byProducts = m.getByProducts();
                int byProductsCount = byProducts.size();

                List<Ingredient> ores = new ObjectArrayList<>();
                ores.add(RecipeIngredient.of(1, new ItemStack(Blocks.FURNACE), new ItemStack(Blocks.BLAST_FURNACE)));
                RecipeIngredient macerator = RecipeIngredient.of(Machines.MACERATOR.getItem(LV), 1);
                ores.add(macerator);
                ores.add(RecipeIngredient.of(Machines.ORE_WASHER.getItem(LV), 1));
                ores.add(RecipeIngredient.of(Machines.BATH.getItem(LV), 1));
                ores.add(macerator);
                ores.add(macerator);
                ores.add(RecipeIngredient.of(Machines.CENTRIFUGE.getItem(LV), 1));
                ores.add(macerator);
                ores.add(RecipeIngredient.of(Machines.THERMAL_CENTRIFUGE.getItem(MV), 1));
                ores.add(RecipeIngredient.of(Machines.CENTRIFUGE.getItem(LV), 1));
                ores.add(RecipeIngredient.of(1, new ItemStack(Machines.ORE_WASHER.getItem(LV)), new ItemStack(Blocks.CAULDRON)));
                List<FluidStack> fluids = new ObjectArrayList<>();
                if (m.has(AntimatterMaterialTypes.ORE)) ores.add(ore);
                if (m.has(AntimatterMaterialTypes.CRUSHED)) {
                    ores.add(AntimatterMaterialTypes.CRUSHED.getIngredient(m, 2 * MaterialTags.ORE_MULTI.getInt(m)));
                    ores.add(crushed);
                    ores.add(AntimatterMaterialTypes.CRUSHED_PURIFIED.getIngredient(m, 1));
                    ores.add(AntimatterMaterialTypes.DUST_PURE.getIngredient(m, 1));
                    ores.add(AntimatterMaterialTypes.DUST_IMPURE.getIngredient(m, 1));
                    ores.add(AntimatterMaterialTypes.CRUSHED_REFINED.getIngredient(m, 1));
                    fluids.add(new FluidStack(Fluids.WATER, 1000));
                }

                Material oreByProduct1 = m.getByProducts().size() > 0 ? m.getByProducts().get(0) : m;
                Material oreByProduct2 = m.getByProducts().size() > 1 ? m.getByProducts().get(1) : oreByProduct1;
                Material oreByProduct3 = m.getByProducts().size() > 2 ? m.getByProducts().get(2) : oreByProduct2;

                List<ItemStack> dusts = new ObjectArrayList<>();
                if (MaterialTags.SMELT_INTO.getMapping(m).has(AntimatterMaterialTypes.INGOT) && !m.has(GT4RMaterialTags.NEEDS_BLAST_FURNACE)){
                    dusts.add(AntimatterMaterialTypes.INGOT.get(MaterialTags.SMELT_INTO.getMapping(m), MaterialTags.SMELTING_MULTI.getInt(m)));
                } else {
                    dusts.add(new ItemStack(Items.BARRIER));
                }
                dusts.add(AntimatterMaterialTypes.DUST_TINY.get(oreByProduct1, 1));
                dusts.add(AntimatterMaterialTypes.DUST.get(oreByProduct2, 1));
                dusts.add(AntimatterMaterialTypes.DUST.get(oreByProduct1, 1));
                dusts.add(AntimatterMaterialTypes.DUST.get(oreByProduct1, 1));
                dusts.add(AntimatterMaterialTypes.DUST.get(m, 1));
                dusts.add(AntimatterMaterialTypes.DUST_TINY.get(oreByProduct1, 1));
                dusts.add(AntimatterMaterialTypes.DUST.get(m, 1));
                dusts.add(AntimatterMaterialTypes.DUST.get(oreByProduct3, 1));
                dusts.add(AntimatterMaterialTypes.DUST_TINY.get(oreByProduct2, 1));
                dusts.add(AntimatterMaterialTypes.DUST.get(m, 1));
                dusts.add(AntimatterMaterialTypes.DUST_TINY.get(oreByProduct2, 1));
                dusts.add(AntimatterMaterialTypes.CRUSHED_PURIFIED.get(m, 1));
                dusts.add(AntimatterMaterialTypes.DUST_TINY.get(oreByProduct1, 1));
                if (m.has(GT4RMaterialTags.BATHING_MERCURY) || m.has(GT4RMaterialTags.BATHING_PERSULFATE)){
                    if (m.has(GT4RMaterialTags.BATHING_PERSULFATE)){
                        ores.add(AntimatterMaterialTypes.CRUSHED_PURIFIED.getIngredient(m, 1));
                        dusts.add(AntimatterMaterialTypes.DUST.get(GT4RMaterialTags.BATHING_PERSULFATE.getMapping(m), 1));
                        fluids.add(Materials.SodiumPersulfate.getLiquid(100));
                        ORE_BYPRODUCTS.RB().ii(ores).fi(fluids).io(dusts.toArray(new ItemStack[0])).chances(1.0, 1.0, 0.1, 0.1, 0.1, 1.0, 1.0, 1.0, 0.1, 1.0, 1.0, 1.0, 1.0, 0.5, 0.7).add(m.getId() + "_byproducts");
                        ores.remove(ores.size() - 1);
                        dusts.remove(dusts.size() - 1);
                        fluids.remove(1);
                    }
                    if (m.has(GT4RMaterialTags.BATHING_MERCURY)){
                        ores.add(AntimatterMaterialTypes.CRUSHED_PURIFIED.getIngredient(m, 1));
                        dusts.add(AntimatterMaterialTypes.DUST.get(GT4RMaterialTags.BATHING_MERCURY.getMapping(m), 1));
                        fluids.add(Materials.Mercury.getLiquid(100));
                        ORE_BYPRODUCTS.RB().ii(ores).fi(fluids).io(dusts.toArray(new ItemStack[0])).chances(1.0, 1.0, 0.1, 0.1, 0.1, 1.0, 1.0, 1.0, 0.1, 1.0, 1.0, 1.0, 1.0, 0.5, 0.7).add(m.getId() + "_byproducts_1");
                    }
                } else {
                    ORE_BYPRODUCTS.RB().ii(ores).fi(fluids).io(dusts.toArray(new ItemStack[0])).chances(1.0, 1.0, 0.1, 0.1, 0.1, 1.0, 1.0, 1.0, 0.1, 1.0, 1.0, 1.0, 1.0, 0.5).add(m.getId() + "_byproducts_2");
                }
            }
        });
    }
}
