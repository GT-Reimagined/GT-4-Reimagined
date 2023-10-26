package trinsdar.gt4r.data;

import com.google.common.collect.ImmutableMap;
import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.Item;
import trinsdar.gt4r.GT4RRef;
import trinsdar.gt4r.items.ItemIntCircuit;

import static trinsdar.gt4r.data.Materials.*;

public class TierMaps {
    public static final ImmutableMap<Integer, RecipeIngredient> INT_CIRCUITS;
    public static final ImmutableMap<Integer, Item> INT_CIRCUITS_ITEMS;
    public static final ImmutableMap<Tier, Material> TIER_MATERIALS;
    public static ImmutableMap<Tier, Wire<?>> TIER_WIRES;
    public static ImmutableMap<Tier, Cable<?>> TIER_CABLES;
    public static ImmutableMap<Tier, Item> TIER_ROTORS;
    public static ImmutableMap<Tier, Item> TIER_BATTERIES;
    public static ImmutableMap<Tier, FluidPipe> TIER_PIPES;

    static {
        {
            ImmutableMap.Builder<Integer, RecipeIngredient> builder = ImmutableMap.builder();
            ImmutableMap.Builder<Integer, Item> builderItems = ImmutableMap.builder();
            for (int i = 0; i <= 24; i++) {
                Item circuit = new ItemIntCircuit(GT4RRef.ID, "int_circuit_"+i, i).tip("ID: " + i);
                builder.put(i, RecipeIngredient.of(circuit, 1).setNoConsume());
                builderItems.put(i, circuit);
            }
            INT_CIRCUITS = builder.build();
            INT_CIRCUITS_ITEMS = builderItems.build();
        }
        {
            ImmutableMap.Builder<Tier, Material> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, WroughtIron);
            builder.put(Tier.LV, Steel);
            builder.put(Tier.MV, Aluminium);
            builder.put(Tier.HV, StainlessSteel);
            builder.put(Tier.EV, Titanium);
            builder.put(Tier.IV, TungstenSteel);
            TIER_MATERIALS = builder.build();
        }
    }

    public static void buildTierMaps() {
        {
            ImmutableMap.Builder<Tier, Wire<?>> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, GT4RData.WIRE_LEAD);
            builder.put(Tier.LV, GT4RData.WIRE_TIN);
            builder.put(Tier.MV, GT4RData.WIRE_COPPER);
            builder.put(Tier.HV, GT4RData.WIRE_GOLD);
            builder.put(Tier.EV, GT4RData.WIRE_ALUMINIUM);
            builder.put(Tier.IV, GT4RData.WIRE_TUNGSTEN);
            builder.put(Tier.LUV, GT4RData.WIRE_SUPERCONDUCTOR);
            TIER_WIRES = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Cable<?>> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, GT4RData.CABLE_LEAD);
            builder.put(Tier.LV, GT4RData.CABLE_TIN);
            builder.put(Tier.MV, GT4RData.CABLE_COPPER);
            builder.put(Tier.HV, GT4RData.CABLE_GOLD);
            builder.put(Tier.EV, GT4RData.CABLE_ALUMINIUM);
            builder.put(Tier.IV, GT4RData.CABLE_TUNGSTEN);
            builder.put(Tier.LUV, GT4RData.WIRE_SUPERCONDUCTOR);
            TIER_CABLES = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Item> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, GTCoreItems.BatteryRE);
            builder.put(Tier.LV, GTCoreItems.BatterySmallLithium);
            builder.put(Tier.MV, GTCoreItems.BatteryMediumLithium);
            builder.put(Tier.HV, GTCoreItems.BatteryLargeLithium);
            builder.put(Tier.EV, GTCoreItems.LapotronCrystal);
            builder.put(Tier.IV, GTCoreItems.BatteryEnergyOrb);
            TIER_BATTERIES = builder.build();
        }

    }
}
