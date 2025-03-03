package org.gtreimagined.gt4r.data;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.Wire;
import net.minecraft.world.item.Item;
import org.gtreimagined.gtcore.data.GTCoreItems;

import static org.gtreimagined.gt4r.data.Materials.*;

public class TierMaps {
    public static final ImmutableMap<Tier, Material> TIER_MATERIALS;
    public static ImmutableMap<Tier, Wire<?>> TIER_WIRES;
    public static ImmutableMap<Tier, Cable<?>> TIER_CABLES;
    public static ImmutableMap<Tier, Item> TIER_BATTERIES;

    static {
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
            builder.put(Tier.ULV, GT4RBlocks.WIRE_SOLDERING_ALLOY);
            builder.put(Tier.LV, GT4RBlocks.WIRE_TIN);
            builder.put(Tier.MV, GT4RBlocks.WIRE_COPPER);
            builder.put(Tier.HV, GT4RBlocks.WIRE_GOLD);
            builder.put(Tier.EV, GT4RBlocks.WIRE_ALUMINIUM);
            builder.put(Tier.IV, GT4RBlocks.WIRE_TUNGSTEN);
            builder.put(Tier.LUV, GT4RBlocks.WIRE_SUPERCONDUCTOR);
            TIER_WIRES = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Cable<?>> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, GT4RBlocks.CABLE_SOLDERING_ALLOY);
            builder.put(Tier.LV, GT4RBlocks.CABLE_TIN);
            builder.put(Tier.MV, GT4RBlocks.CABLE_COPPER);
            builder.put(Tier.HV, GT4RBlocks.CABLE_GOLD);
            builder.put(Tier.EV, GT4RBlocks.CABLE_ALUMINIUM);
            builder.put(Tier.IV, GT4RBlocks.CABLE_TUNGSTEN);
            builder.put(Tier.LUV, GT4RBlocks.WIRE_SUPERCONDUCTOR);
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
