package trinsdar.gt4r.data;

import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.material.TextureSet;
import muramasa.antimatter.texture.IOverlayTexturer;
import muramasa.antimatter.texture.ITextureHandler;
import muramasa.antimatter.texture.Texture;
import trinsdar.gt4r.Ref;

public class Textures {

    public static TextureSet DULL = new TextureSet(Ref.ID, "dull");
    public static TextureSet METALLIC = new TextureSet(Ref.ID, "metallic");
    public static TextureSet SHINY = new TextureSet(Ref.ID, "shiny");
    public static TextureSet ROUGH = new TextureSet(Ref.ID, "rough");
    public static TextureSet MAGNETIC = new TextureSet(Ref.ID, "magnetic");
    public static TextureSet DIAMOND = new TextureSet(Ref.ID, "diamond");
    public static TextureSet RUBY = new TextureSet(Ref.ID, "ruby");
    public static TextureSet LAPIS = new TextureSet(Ref.ID, "lapis");
    public static TextureSet GEM_V = new TextureSet(Ref.ID, "gem_v");
    public static TextureSet GARNET = new TextureSet(Ref.ID, "garnet");
    public static TextureSet QUARTZ = new TextureSet(Ref.ID, "quartz");
    public static TextureSet FINE = new TextureSet(Ref.ID, "fine");
    public static TextureSet FLINT = new TextureSet(Ref.ID, "flint");
    public static TextureSet LIGNITE = new TextureSet(Ref.ID, "lignite");
    public static TextureSet WOOD = new TextureSet(Ref.ID, "wood");

    public static final Texture ROTOR = new Texture(Ref.ID, "material/rotor");
    public static final Texture MACHINE_BASE = new Texture(Ref.ID, "block/machine/base/machine_base");
    public static final Texture BRONZE_MACHINE_BASE = new Texture(Ref.ID, "block/machine/base/tiers/bronze");
    public static final Texture FUSION_IN = new Texture(Ref.ID, "block/machine/base/fusion_control_computer");
    public static final Texture FUSION_OUT = new Texture(Ref.ID, "block/machine/base/fusion_out");

    public static final ITextureHandler BOILER_HANDLER = (m, t) -> new Texture[] {
        new Texture(Ref.ID, "block/machine/base/brick"),
        new Texture(Ref.ID, "block/machine/base/" + t.getId()),
        new Texture(Ref.ID, "block/machine/base/bricked_" + t.getId()),
        new Texture(Ref.ID, "block/machine/base/bricked_" + t.getId()),
        new Texture(Ref.ID, "block/machine/base/bricked_" + t.getId()),
        new Texture(Ref.ID, "block/machine/base/bricked_" + t.getId()),
    };

    public static final ITextureHandler DUSTBIN_HANDLER = (m, t) -> new Texture[] {
            new Texture(Ref.ID, "block/machine/base/dust_bin/bottom"),
            new Texture(Ref.ID, "block/machine/base/dust_bin/top"),
            new Texture(Ref.ID, "block/machine/base/dust_bin/side"),
            new Texture(Ref.ID, "block/machine/base/dust_bin/side"),
            new Texture(Ref.ID, "block/machine/base/dust_bin/side"),
            new Texture(Ref.ID, "block/machine/base/dust_bin/side"),
    };

    public static final IOverlayTexturer LEFT_RIGHT_HANDLER = (type, state) -> {
        if (state != MachineState.ACTIVE && state != MachineState.INVALID_STRUCTURE) state = MachineState.IDLE;
        String stateDir = state == MachineState.IDLE ? "" : state.getId() + "/";

        return new Texture[] {
                new Texture(Ref.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "bottom"),
                new Texture(Ref.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "top"),
                new Texture(Ref.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "front"),
                new Texture(Ref.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "back"),
                new Texture(Ref.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "right"),
                new Texture(Ref.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "left"),
        };
    };

    public static final ITextureHandler DRUM_HANDLER = (m, t) -> new Texture[] {
            new Texture(Ref.ID, "block/machine/base/drum/bottom"),
            new Texture(Ref.ID, "block/machine/base/drum/top"),
            new Texture(Ref.ID, "block/machine/base/drum/side"),
            new Texture(Ref.ID, "block/machine/base/drum/side"),
            new Texture(Ref.ID, "block/machine/base/drum/side"),
            new Texture(Ref.ID, "block/machine/base/drum/side"),
    };

    public static final IOverlayTexturer DRUM_OVERLAY_HANDLER = (type, state) -> new Texture[] {
            new Texture(Ref.ID, "block/machine/overlay/drum/bottom"),
            new Texture(Ref.ID, "block/machine/overlay/drum/top"),
            new Texture(Ref.ID, "block/machine/overlay/drum/side"),
            new Texture(Ref.ID, "block/machine/overlay/drum/side"),
            new Texture(Ref.ID, "block/machine/overlay/drum/side"),
            new Texture(Ref.ID, "block/machine/overlay/drum/side"),
    };

    public static final ITextureHandler CABINET_HANDLER = (m, t) -> new Texture[] {
            new Texture(Ref.ID, "block/machine/base/cabinet/bottom"),
            new Texture(Ref.ID, "block/machine/base/cabinet/top"),
            new Texture(Ref.ID, "block/machine/base/cabinet/front"),
            new Texture(Ref.ID, "block/machine/base/cabinet/back"),
            new Texture(Ref.ID, "block/machine/base/cabinet/side"),
            new Texture(Ref.ID, "block/machine/base/cabinet/side"),
    };

    public static final IOverlayTexturer CABINET_OVERLAY_HANDLER = (type, state) -> new Texture[] {
            new Texture(Ref.ID, "block/machine/overlay/cabinet/bottom"),
            new Texture(Ref.ID, "block/machine/overlay/cabinet/top"),
            new Texture(Ref.ID, "block/machine/overlay/cabinet/front"),
            new Texture(Ref.ID, "block/machine/overlay/cabinet/back"),
            new Texture(Ref.ID, "block/machine/overlay/cabinet/side"),
            new Texture(Ref.ID, "block/machine/overlay/cabinet/side"),
    };

    public static final ITextureHandler WORKBENCH_HANDLER = (m, t) -> new Texture[] {
            new Texture(Ref.ID, "block/machine/base/workbench/bottom"),
            new Texture(Ref.ID, "block/machine/base/workbench/top"),
            new Texture(Ref.ID, "block/machine/base/workbench/front"),
            new Texture(Ref.ID, "block/machine/base/workbench/back"),
            new Texture(Ref.ID, "block/machine/base/workbench/side"),
            new Texture(Ref.ID, "block/machine/base/workbench/side"),
    };

    public static final IOverlayTexturer WORKBENCH_OVERLAY_HANDLER = (type, state) -> new Texture[] {
            new Texture(Ref.ID, "block/machine/overlay/workbench/bottom"),
            new Texture(Ref.ID, "block/machine/overlay/workbench/top"),
            new Texture(Ref.ID, "block/machine/overlay/workbench/front"),
            new Texture(Ref.ID, "block/machine/overlay/workbench/back"),
            new Texture(Ref.ID, "block/machine/overlay/workbench/side"),
            new Texture(Ref.ID, "block/machine/overlay/workbench/side"),
    };

    public static final IOverlayTexturer CHARGING_WORKBENCH_OVERLAY_HANDLER = (type, state) -> new Texture[] {
            new Texture(Ref.ID, "block/machine/overlay/charging_workbench/bottom"),
            new Texture(Ref.ID, "block/machine/overlay/charging_workbench/top"),
            new Texture(Ref.ID, "block/machine/overlay/charging_workbench/front"),
            new Texture(Ref.ID, "block/machine/overlay/charging_workbench/back"),
            new Texture(Ref.ID, "block/machine/overlay/charging_workbench/side"),
            new Texture(Ref.ID, "block/machine/overlay/charging_workbench/side"),
    };

    public static final ITextureHandler LOCKER_HANDLER = (m, t) -> new Texture[] {
            new Texture(Ref.ID, "block/machine/base/locker/bottom"),
            new Texture(Ref.ID, "block/machine/base/locker/top"),
            new Texture(Ref.ID, "block/machine/base/locker/front"),
            new Texture(Ref.ID, "block/machine/base/locker/back"),
            new Texture(Ref.ID, "block/machine/base/locker/side"),
            new Texture(Ref.ID, "block/machine/base/locker/side"),
    };

    public static final IOverlayTexturer LOCKER_OVERLAY_HANDLER = (type, state) -> new Texture[] {
            new Texture(Ref.ID, "block/machine/overlay/locker/bottom"),
            new Texture(Ref.ID, "block/machine/overlay/locker/top"),
            new Texture(Ref.ID, "block/machine/overlay/locker/front"),
            new Texture(Ref.ID, "block/machine/overlay/locker/back"),
            new Texture(Ref.ID, "block/machine/overlay/locker/side"),
            new Texture(Ref.ID, "block/machine/overlay/locker/side"),
    };

    public static final IOverlayTexturer CHARGING_LOCKER_OVERLAY_HANDLER = (type, state) -> new Texture[] {
            new Texture(Ref.ID, "block/machine/overlay/charging_locker/bottom"),
            new Texture(Ref.ID, "block/machine/overlay/charging_locker/top"),
            new Texture(Ref.ID, "block/machine/overlay/charging_locker/front"),
            new Texture(Ref.ID, "block/machine/overlay/charging_locker/back"),
            new Texture(Ref.ID, "block/machine/overlay/charging_locker/side"),
            new Texture(Ref.ID, "block/machine/overlay/charging_locker/side"),
    };

    public static final ITextureHandler MULTI_HANDLER = (m, t) -> m.getTiers().size() > 1 ? new Texture[]{new Texture(Ref.ID, "block/machine/base/" + m.getId() + "_" + t.getId())} : new Texture[]{new Texture(Ref.ID, "block/machine/base/" + m.getId())};

    public static final Texture[] LARGE_TURBINE = new Texture[] {
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_0"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_1"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_2"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_3"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_4"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_5"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_6"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_7"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_8")
    };

    public static final Texture[] LARGE_TURBINE_ACTIVE = new Texture[] {
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_active_0"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_active_1"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_active_2"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_active_3"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_active_4"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_active_5"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_active_6"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_active_7"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_active_8")
    };

    public static final Texture[] FUSION_1_CT = new Texture[] {
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_0"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_1"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_2"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_3"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_4"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_5"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_6"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_7"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_8"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_9"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_10"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_11"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_12")
    };

    public static final Texture[] FUSION_2_CT = new Texture[] {
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_0"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_1"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_2"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_3"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_4"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_5"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_6"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_7"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_8"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_9"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_10"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_11"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_12")
    };

    public static final Texture[] FUSION_3_CT = new Texture[] {
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_0"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_1"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_2"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_3"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_4"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_5"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_6"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_7"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_8"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_9"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_10"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_11"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_12")
    };
}
