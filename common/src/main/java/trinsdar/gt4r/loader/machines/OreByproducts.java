package trinsdar.gt4r.loader.machines;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;
import trinsdar.gt4r.data.Machines;
import trinsdar.gt4r.data.Materials;

import java.util.List;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.machine.Tier.LV;
import static muramasa.antimatter.machine.Tier.MV;
import static trinsdar.gt4r.data.RecipeMaps.ORE_BYPRODUCTS;

public class OreByproducts {
    public static void init() {
        CRUSHED.all().forEach(m -> {
            if (!m.has(ORE)) return;
            RecipeIngredient ore = ORE.getMaterialIngredient(m ,1);
            RecipeIngredient crushed = CRUSHED.getIngredient(m, 1);
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
                if (m.has(ORE)) ores.add(ore);
                if (m.has(CRUSHED)) {
                    ores.add(CRUSHED.getIngredient(m, 2 * m.getOreMulti()));
                    ores.add(crushed);
                    ores.add(CRUSHED_PURIFIED.getIngredient(m, 1));
                    ores.add(DUST_PURE.getIngredient(m, 1));
                    ores.add(DUST_IMPURE.getIngredient(m, 1));
                    ores.add(CRUSHED_CENTRIFUGED.getIngredient(m, 1));
                    fluids.add(new FluidStack(Fluids.WATER, 1000));
                }

                Material oreByProduct1 = m.getByProducts().size() > 0 ? m.getByProducts().get(0) : m;
                Material oreByProduct2 = m.getByProducts().size() > 1 ? m.getByProducts().get(1) : oreByProduct1;
                Material oreByProduct3 = m.getByProducts().size() > 2 ? m.getByProducts().get(2) : oreByProduct2;

                List<ItemStack> dusts = new ObjectArrayList<>();
                if (m.getSmeltInto().has(INGOT) && !m.needsBlastFurnace()){
                    dusts.add(INGOT.get(m.getSmeltInto(), m.getSmeltingMulti()));
                } else {
                    dusts.add(new ItemStack(Items.BARRIER));
                }
                dusts.add(DUST_TINY.get(oreByProduct1, 1));
                dusts.add(DUST.get(oreByProduct2, 1));
                dusts.add(DUST.get(oreByProduct1, 1));
                dusts.add(DUST.get(oreByProduct1, 1));
                dusts.add(DUST.get(m, 1));
                dusts.add(DUST_TINY.get(oreByProduct1, 1));
                dusts.add(DUST.get(m, 1));
                dusts.add(DUST.get(oreByProduct3, 1));
                dusts.add(DUST_TINY.get(oreByProduct2, 1));
                dusts.add(DUST.get(m, 1));
                dusts.add(DUST_TINY.get(oreByProduct2, 1));
                dusts.add(CRUSHED_PURIFIED.get(m, 1));
                dusts.add(DUST_TINY.get(oreByProduct1, 1));
                if (m.has(Materials.BATHING_MERCURY) || m.has(Materials.BATHING_PERSULFATE)){
                    if (m.has(Materials.BATHING_PERSULFATE)){
                        ores.add(CRUSHED_PURIFIED.getIngredient(m, 1));
                        dusts.add(DUST.get(Materials.BATHING_MAP_PERSULFATE.get(m), 1));
                        fluids.add(Materials.SodiumPersulfate.getLiquid(100));
                        ORE_BYPRODUCTS.RB().ii(ores).fi(fluids).io(dusts.toArray(new ItemStack[0])).chances(100, 100, 10, 10, 10, 100, 100, 100, 10, 100, 100, 100, 100, 50, 70).add();
                        ores.remove(ores.size() - 1);
                        dusts.remove(dusts.size() - 1);
                        fluids.remove(1);
                    }
                    if (m.has(Materials.BATHING_MERCURY)){
                        ores.add(CRUSHED_PURIFIED.getIngredient(m, 1));
                        dusts.add(DUST.get(Materials.BATHING_MAP_MERCURY.get(m), 1));
                        fluids.add(Materials.Mercury.getLiquid(100));
                        ORE_BYPRODUCTS.RB().ii(ores).fi(fluids).io(dusts.toArray(new ItemStack[0])).chances(100, 100, 10, 10, 10, 100, 100, 100, 10, 100, 100, 100, 100, 50, 70).add();
                    }
                } else {
                    ORE_BYPRODUCTS.RB().ii(ores).fi(fluids).io(dusts.toArray(new ItemStack[0])).chances(100, 100, 10, 10, 10, 100, 100, 100, 10, 100, 100, 100, 100, 50).add();
                }
            }
        });
    }
}
