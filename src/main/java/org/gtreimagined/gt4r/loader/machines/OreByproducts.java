package org.gtreimagined.gt4r.loader.machines;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.gtreimagined.gtlib.data.GTMaterialTypes;
import org.gtreimagined.gtlib.material.Material;
import org.gtreimagined.gtlib.material.MaterialTags;
import org.gtreimagined.gtlib.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fluids.FluidStack;
import org.gtreimagined.gt4r.data.GT4RMaterialTags;
import org.gtreimagined.gt4r.data.Machines;
import org.gtreimagined.gt4r.data.Materials;

import java.util.List;

import static org.gtreimagined.gtlib.data.GTLibMaterials.Water;
import static org.gtreimagined.gtlib.machine.Tier.LV;
import static org.gtreimagined.gt4r.data.RecipeMaps.ORE_BYPRODUCTS;

public class OreByproducts {
    public static void init() {
        GTMaterialTypes.CRUSHED.all().forEach(m -> {
            if (!m.has(GTMaterialTypes.ORE)) return;
            RecipeIngredient ore = GTMaterialTypes.ORE.getMaterialIngredient(m ,1);
            RecipeIngredient crushed = GTMaterialTypes.CRUSHED.getIngredient(m, 1);
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
                ores.add(RecipeIngredient.of(Machines.CENTRIFUGE.getItem(LV), 1));
                ores.add(RecipeIngredient.of(Machines.CENTRIFUGE.getItem(LV), 1));
                ores.add(RecipeIngredient.of(1, new ItemStack(Machines.ORE_WASHER.getItem(LV)), new ItemStack(Blocks.CAULDRON)));
                List<FluidStack> fluids = new ObjectArrayList<>();
                if (m.has(GTMaterialTypes.ORE)) ores.add(ore);
                if (m.has(GTMaterialTypes.CRUSHED)) {
                    ores.add(GTMaterialTypes.CRUSHED.getIngredient(m, 2 * MaterialTags.ORE_MULTI.getInt(m)));
                    ores.add(crushed);
                    ores.add(GTMaterialTypes.CRUSHED_PURIFIED.getIngredient(m, 1));
                    ores.add(GTMaterialTypes.DUST_PURE.getIngredient(m, 1));
                    ores.add(GTMaterialTypes.DUST_IMPURE.getIngredient(m, 1));
                    ores.add(GTMaterialTypes.CRUSHED_REFINED.getIngredient(m, 1));
                    fluids.add(Water.getLiquid(1000));
                }

                Material oreByProduct1 = m.getByProducts().size() > 0 ? m.getByProducts().get(0) : m;
                Material oreByProduct2 = m.getByProducts().size() > 1 ? m.getByProducts().get(1) : oreByProduct1;
                Material oreByProduct3 = m.getByProducts().size() > 2 ? m.getByProducts().get(2) : oreByProduct2;

                List<ItemStack> dusts = new ObjectArrayList<>();
                if (MaterialTags.SMELT_INTO.getMapping(m).has(GTMaterialTypes.INGOT) && !m.has(GT4RMaterialTags.NEEDS_BLAST_FURNACE)){
                    dusts.add(GTMaterialTypes.INGOT.get(MaterialTags.SMELT_INTO.getMapping(m), MaterialTags.SMELTING_MULTI.getInt(m)));
                } else {
                    dusts.add(new ItemStack(Items.BARRIER));
                }
                dusts.add(GTMaterialTypes.DUST_TINY.get(oreByProduct1, 1));
                dusts.add(GTMaterialTypes.DUST.get(oreByProduct2, 1));
                dusts.add(GTMaterialTypes.DUST.get(oreByProduct1, 1));
                dusts.add(GTMaterialTypes.DUST.get(oreByProduct1, 1));
                dusts.add(GTMaterialTypes.DUST.get(m, 1));
                dusts.add(GTMaterialTypes.DUST_TINY.get(oreByProduct1, 1));
                dusts.add(GTMaterialTypes.DUST.get(m, 1));
                dusts.add(GTMaterialTypes.DUST.get(oreByProduct3, 1));
                dusts.add(GTMaterialTypes.DUST_TINY.get(oreByProduct2, 1));
                dusts.add(GTMaterialTypes.DUST.get(m, 1));
                dusts.add(GTMaterialTypes.DUST_TINY.get(oreByProduct2, 1));
                dusts.add(GTMaterialTypes.CRUSHED_PURIFIED.get(m, 1));
                dusts.add(GTMaterialTypes.DUST_TINY.get(oreByProduct1, 1));
                if (m.has(GT4RMaterialTags.BATHING_MERCURY) || m.has(GT4RMaterialTags.BATHING_PERSULFATE)){
                    if (m.has(GT4RMaterialTags.BATHING_PERSULFATE)){
                        ores.add(GTMaterialTypes.CRUSHED_PURIFIED.getIngredient(m, 1));
                        dusts.add(GTMaterialTypes.DUST.get(GT4RMaterialTags.BATHING_PERSULFATE.getMapping(m), 1));
                        fluids.add(Materials.SodiumPersulfate.getLiquid(100));
                        ORE_BYPRODUCTS.RB().ii(ores).fi(fluids).io(dusts.toArray(new ItemStack[0])).outputChances(1.0, 1.0, 0.1, 0.1, 0.1, 1.0, 1.0, 1.0, 0.1, 1.0, 1.0, 1.0, 1.0, 0.5, 0.7).add(m.getId() + "_byproducts");
                        ores.remove(ores.size() - 1);
                        dusts.remove(dusts.size() - 1);
                        fluids.remove(1);
                    }
                    if (m.has(GT4RMaterialTags.BATHING_MERCURY)){
                        ores.add(GTMaterialTypes.CRUSHED_PURIFIED.getIngredient(m, 1));
                        dusts.add(GTMaterialTypes.DUST.get(GT4RMaterialTags.BATHING_MERCURY.getMapping(m), 1));
                        fluids.add(Materials.Mercury.getLiquid(100));
                        ORE_BYPRODUCTS.RB().ii(ores).fi(fluids).io(dusts.toArray(new ItemStack[0])).outputChances(1.0, 1.0, 0.1, 0.1, 0.1, 1.0, 1.0, 1.0, 0.1, 1.0, 1.0, 1.0, 1.0, 0.5, 0.7).add(m.getId() + "_byproducts_1");
                    }
                } else {
                    ORE_BYPRODUCTS.RB().ii(ores).fi(fluids).io(dusts.toArray(new ItemStack[0])).outputChances(1.0, 1.0, 0.1, 0.1, 0.1, 1.0, 1.0, 1.0, 0.1, 1.0, 1.0, 1.0, 1.0, 0.5).add(m.getId() + "_byproducts_2");
                }
            }
        });
    }
}
