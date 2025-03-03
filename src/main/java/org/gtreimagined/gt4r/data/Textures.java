package org.gtreimagined.gt4r.data;

import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.texture.IOverlayModeler;
import muramasa.antimatter.texture.IOverlayTexturer;
import muramasa.antimatter.texture.ITextureHandler;
import muramasa.antimatter.texture.Texture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import org.gtreimagined.gt4r.GT4RRef;

import static muramasa.antimatter.machine.Tier.LV;
import static muramasa.antimatter.machine.Tier.MV;

public class Textures {

    public static final Texture ROTOR = new Texture(GT4RRef.ID, "material/rotor");
    public static final Texture MACHINE_BASE = new Texture(GT4RRef.ID, "block/machine/base/machine_base");
    public static final Texture BRONZE_MACHINE_BASE = new Texture(GT4RRef.ID, "block/machine/base/tiers/bronze");
    public static final Texture FUSION_IN = new Texture(GT4RRef.ID, "block/machine/base/fusion_control_computer");
    public static final Texture FUSION_OUT = new Texture(GT4RRef.ID, "block/machine/base/fusion_out");

    public static final ITextureHandler BASE_HANDLER = (m, t, s) -> new Texture[]{
            new Texture(GT4RRef.ID, "block/machine/base/bottom_" + t.getId()),
            new Texture(GT4RRef.ID, "block/machine/base/side_" + t.getId()),
            new Texture(GT4RRef.ID, "block/machine/base/side_" + t.getId()),
            new Texture(GT4RRef.ID, "block/machine/base/side_" + t.getId()),
            new Texture(GT4RRef.ID, "block/machine/base/side_" + t.getId()),
            new Texture(GT4RRef.ID, "block/machine/base/side_" + t.getId()),
    };

    public static final ITextureHandler BATBOX_HANDLER = (m, t, s) -> {
        String side = t == LV || t == MV ? "side" : "energy";
        return new Texture[]{
                new Texture(GT4RRef.ID, "block/machine/base/" + side + "_" + t.getId()),
                new Texture(GT4RRef.ID, "block/machine/base/" + side + "_" + t.getId()),
                new Texture(GT4RRef.ID, "block/machine/base/" + side + "_" + t.getId()),
                new Texture(GT4RRef.ID, "block/machine/base/" + side + "_" + t.getId()),
                new Texture(GT4RRef.ID, "block/machine/base/" + side + "_" + t.getId()),
                new Texture(GT4RRef.ID, "block/machine/base/" + side + "_" + t.getId()),
        };
    };

    public static final ITextureHandler BOILER_HANDLER = (m, t, s) -> new Texture[] {
            new Texture(GT4RRef.ID, "block/machine/base/brick"),
            new Texture(GT4RRef.ID, "block/machine/base/" + t.getId()),
            new Texture(GT4RRef.ID, "block/machine/base/boiler_side_" + t.getId()),
            new Texture(GT4RRef.ID, "block/machine/base/boiler_side_" + t.getId()),
            new Texture(GT4RRef.ID, "block/machine/base/boiler_side_" + t.getId()),
            new Texture(GT4RRef.ID, "block/machine/base/boiler_side_" + t.getId()),
    };

    public static final ITextureHandler BRICKED_HANDLER = (m, t, s) -> new Texture[] {
        new Texture(GT4RRef.ID, "block/machine/base/brick"),
        new Texture(GT4RRef.ID, "block/machine/base/" + t.getId()),
        new Texture(GT4RRef.ID, "block/machine/base/bricked_" + t.getId()),
        new Texture(GT4RRef.ID, "block/machine/base/bricked_" + t.getId()),
        new Texture(GT4RRef.ID, "block/machine/base/bricked_" + t.getId()),
        new Texture(GT4RRef.ID, "block/machine/base/bricked_" + t.getId()),
    };

    public static final ITextureHandler DUSTBIN_HANDLER = (m, t, s) -> new Texture[] {
            new Texture(GT4RRef.ID, "block/machine/base/dust_bin/bottom"),
            new Texture(GT4RRef.ID, "block/machine/base/dust_bin/top"),
            new Texture(GT4RRef.ID, "block/machine/base/dust_bin/side"),
            new Texture(GT4RRef.ID, "block/machine/base/dust_bin/side"),
            new Texture(GT4RRef.ID, "block/machine/base/dust_bin/side"),
            new Texture(GT4RRef.ID, "block/machine/base/dust_bin/side"),
    };

    public static final IOverlayTexturer LEFT_RIGHT_HANDLER = (type, state, tier, index) -> {
        if (state != MachineState.ACTIVE && state != MachineState.INVALID_STRUCTURE) state = MachineState.IDLE;
        String stateDir = state == MachineState.IDLE ? "" : state.getId() + "/";

        return new Texture[] {
                new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "bottom"),
                new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "top"),
                new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "back"),
                new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "front"),
                new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "left"),
                new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "right"),
        };
    };

    public static final ITextureHandler DRUM_HANDLER = (m, t, s) -> new Texture[] {
            new Texture(GT4RRef.ID, "block/machine/base/drum/bottom"),
            new Texture(GT4RRef.ID, "block/machine/base/drum/top"),
            new Texture(GT4RRef.ID, "block/machine/base/drum/side"),
            new Texture(GT4RRef.ID, "block/machine/base/drum/side"),
            new Texture(GT4RRef.ID, "block/machine/base/drum/side"),
            new Texture(GT4RRef.ID, "block/machine/base/drum/side"),
    };

    public static final IOverlayTexturer DRUM_OVERLAY_HANDLER = (type, state, tier, index) -> new Texture[] {
            new Texture(GT4RRef.ID, "block/machine/overlay/drum/bottom"),
            new Texture(GT4RRef.ID, "block/machine/overlay/drum/top"),
            new Texture(GT4RRef.ID, "block/machine/overlay/drum/side"),
            new Texture(GT4RRef.ID, "block/machine/overlay/drum/side"),
            new Texture(GT4RRef.ID, "block/machine/overlay/drum/side"),
            new Texture(GT4RRef.ID, "block/machine/overlay/drum/side"),
    };

    public static final ITextureHandler CABINET_HANDLER = (m, t, s) -> new Texture[] {
            new Texture(GT4RRef.ID, "block/machine/base/cabinet/bottom"),
            new Texture(GT4RRef.ID, "block/machine/base/cabinet/top"),
            new Texture(GT4RRef.ID, "block/machine/base/cabinet/back"),
            new Texture(GT4RRef.ID, "block/machine/base/cabinet/front"),
            new Texture(GT4RRef.ID, "block/machine/base/cabinet/side"),
            new Texture(GT4RRef.ID, "block/machine/base/cabinet/side"),
    };

    public static final ITextureHandler CHEST_HANDLER = (m, t, s) -> new Texture[] {
            new Texture(GT4RRef.ID, "model/material_chest_particle"),
            new Texture(GT4RRef.ID, "model/material_chest_particle"),
            new Texture(GT4RRef.ID, "model/material_chest_particle"),
            new Texture(GT4RRef.ID, "model/material_chest_particle"),
            new Texture(GT4RRef.ID, "model/material_chest_particle"),
            new Texture(GT4RRef.ID, "model/material_chest_particle"),
    };

    public static final IOverlayTexturer CABINET_OVERLAY_HANDLER = (type, state, tier, index) -> new Texture[] {
            new Texture(GT4RRef.ID, "block/machine/overlay/cabinet/bottom"),
            new Texture(GT4RRef.ID, "block/machine/overlay/cabinet/top"),
            new Texture(GT4RRef.ID, "block/machine/overlay/cabinet/back"),
            new Texture(GT4RRef.ID, "block/machine/overlay/cabinet/front"),
            new Texture(GT4RRef.ID, "block/machine/overlay/cabinet/side"),
            new Texture(GT4RRef.ID, "block/machine/overlay/cabinet/side"),
    };

    public static final IOverlayTexturer CHEST_OVERLAY_HANDLER = (type, state, tier, index) -> new Texture[] {
            new Texture(GT4RRef.ID, "model/material_chest_overlay_inventory"),
            new Texture(GT4RRef.ID, "model/material_chest_overlay_inventory"),
            new Texture(GT4RRef.ID, "model/material_chest_overlay_inventory"),
            new Texture(GT4RRef.ID, "model/material_chest_overlay_inventory"),
            new Texture(GT4RRef.ID, "model/material_chest_overlay_inventory"),
            new Texture(GT4RRef.ID, "model/material_chest_overlay_inventory"),
    };

    public static final ITextureHandler WORKBENCH_HANDLER = (m, t, s) -> new Texture[] {
            new Texture(GT4RRef.ID, "block/machine/base/workbench/bottom"),
            new Texture(GT4RRef.ID, "block/machine/base/workbench/top"),
            new Texture(GT4RRef.ID, "block/machine/base/workbench/back"),
            new Texture(GT4RRef.ID, "block/machine/base/workbench/front"),
            new Texture(GT4RRef.ID, "block/machine/base/workbench/side"),
            new Texture(GT4RRef.ID, "block/machine/base/workbench/side"),
    };

    public static final IOverlayTexturer WORKBENCH_OVERLAY_HANDLER = (type, state, tier, index) -> new Texture[] {
            new Texture(GT4RRef.ID, "block/machine/overlay/workbench/bottom"),
            new Texture(GT4RRef.ID, "block/machine/overlay/workbench/top"),
            new Texture(GT4RRef.ID, "block/machine/overlay/workbench/back"),
            new Texture(GT4RRef.ID, "block/machine/overlay/workbench/front"),
            new Texture(GT4RRef.ID, "block/machine/overlay/workbench/side"),
            new Texture(GT4RRef.ID, "block/machine/overlay/workbench/side"),
    };

    public static final IOverlayTexturer CHARGING_WORKBENCH_OVERLAY_HANDLER = (type, state, tier, index) -> new Texture[] {
            new Texture(GT4RRef.ID, "block/machine/overlay/charging_workbench/bottom"),
            new Texture(GT4RRef.ID, "block/machine/overlay/charging_workbench/top"),
            new Texture(GT4RRef.ID, "block/machine/overlay/charging_workbench/back"),
            new Texture(GT4RRef.ID, "block/machine/overlay/charging_workbench/front"),
            new Texture(GT4RRef.ID, "block/machine/overlay/charging_workbench/side"),
            new Texture(GT4RRef.ID, "block/machine/overlay/charging_workbench/side"),
    };

    public static final ITextureHandler LOCKER_HANDLER = (m, t, s) -> new Texture[] {
            new Texture(GT4RRef.ID, "block/machine/base/locker/bottom"),
            new Texture(GT4RRef.ID, "block/machine/base/locker/top"),
            new Texture(GT4RRef.ID, "block/machine/base/locker/back"),
            new Texture(GT4RRef.ID, "block/machine/base/locker/front"),
            new Texture(GT4RRef.ID, "block/machine/base/locker/side"),
            new Texture(GT4RRef.ID, "block/machine/base/locker/side"),
    };

    public static final IOverlayTexturer LOCKER_OVERLAY_HANDLER = (type, state, tier, index) -> new Texture[] {
            new Texture(GT4RRef.ID, "block/machine/overlay/locker/bottom"),
            new Texture(GT4RRef.ID, "block/machine/overlay/locker/top"),
            new Texture(GT4RRef.ID, "block/machine/overlay/locker/back"),
            new Texture(GT4RRef.ID, "block/machine/overlay/locker/front"),
            new Texture(GT4RRef.ID, "block/machine/overlay/locker/side"),
            new Texture(GT4RRef.ID, "block/machine/overlay/locker/side"),
    };

    public static final IOverlayTexturer CHARGING_LOCKER_OVERLAY_HANDLER = (type, state, tier, index) -> new Texture[] {
            new Texture(GT4RRef.ID, "block/machine/overlay/charging_locker/bottom"),
            new Texture(GT4RRef.ID, "block/machine/overlay/charging_locker/top"),
            new Texture(GT4RRef.ID, "block/machine/overlay/charging_locker/back"),
            new Texture(GT4RRef.ID, "block/machine/overlay/charging_locker/front"),
            new Texture(GT4RRef.ID, "block/machine/overlay/charging_locker/side"),
            new Texture(GT4RRef.ID, "block/machine/overlay/charging_locker/side"),
    };

    public static final IOverlayTexturer TIER_SPECIFIC_OVERLAY_HANDLER = (type, state, tier, index) -> {
        if (state != MachineState.ACTIVE && state != MachineState.INVALID_STRUCTURE) state = MachineState.IDLE;
        String stateDir = state == MachineState.IDLE ? "" : state.getId() + "/";

        return new Texture[] {
                new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + tier.getId() + "/" + stateDir + "bottom"),
                new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + tier.getId() + "/" + stateDir + "top"),
                new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + tier.getId() + "/" + stateDir + "back"),
                new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + tier.getId() + "/" + stateDir + "front"),
                new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + tier.getId() + "/" + stateDir + "side"),
                new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + tier.getId() + "/" + stateDir + "side"),
        };
    };

    public static IOverlayTexturer SIMPLE_SIDED = (type, state, tier, index) -> new Texture[]{
            new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + "side"),
            new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + "side"),
            new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + "side"),
            new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + "side"),
            new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + "side"),
            new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + "side")
    };

    public static IOverlayTexturer SIMPLE_ACTIVE_SIDED = (type, state, tier, index) -> {
        if (state != MachineState.ACTIVE && state != MachineState.INVALID_STRUCTURE) state = MachineState.IDLE;
        String stateDir = state == MachineState.IDLE ? "" : state.getId() + "/";
        return new Texture[]{
                new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "side"),
                new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "side"),
                new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "side"),
                new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "side"),
                new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "side"),
                new Texture(GT4RRef.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "side")
        };
    };

    public static IOverlayModeler TURBINE = (type, state, side) -> {
        String suffix = "";
        if (side == Direction.SOUTH && state != MachineState.INVALID_STRUCTURE){
            suffix = "_" + state.getId();
        }
        return new ResourceLocation(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + side.getSerializedName() + suffix);
    };
}
