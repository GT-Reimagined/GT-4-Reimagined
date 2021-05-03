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

    public static final ITextureHandler TIER_HANDLER = (m, t) -> new Texture[]{
            new Texture(Ref.ID, "block/machine/base/tiers/" + t.getId()),
    };

    public static final ITextureHandler BOILER_HANDLER = (m, t) -> new Texture[] {
        new Texture(Ref.ID, "block/machine/base/brick"),
        new Texture(Ref.ID, "block/machine/base/tiers/" + t.getId()),
        new Texture(Ref.ID, "block/machine/base/tiers/bricked_" + t.getId()),
        new Texture(Ref.ID, "block/machine/base/tiers/bricked_" + t.getId()),
        new Texture(Ref.ID, "block/machine/base/tiers/bricked_" + t.getId()),
        new Texture(Ref.ID, "block/machine/base/tiers/bricked_" + t.getId()),
    };

    public static final IOverlayTexturer MACERATOR_HANDLER = (type, state) -> {
        if (state != MachineState.ACTIVE && state != MachineState.INVALID_STRUCTURE) state = MachineState.IDLE;
        String stateDir = state == MachineState.IDLE ? "" : state.getId() + "/";

        return new Texture[] {
                new Texture(Ref.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "bottom"),
                new Texture(Ref.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "top"),
                new Texture(Ref.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "front"),
                new Texture(Ref.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "back"),
                new Texture(Ref.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "side"),
                new Texture(Ref.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "side"),
        };
    };

    public static final ITextureHandler DRUM_HANDLER = (m, t) -> new Texture[] {
            new Texture(Ref.ID, "block/machine/base/drum_bottom"),
            new Texture(Ref.ID, "block/machine/base/drum_top"),
            new Texture(Ref.ID, "block/machine/base/drum_side"),
            new Texture(Ref.ID, "block/machine/base/drum_side"),
            new Texture(Ref.ID, "block/machine/base/drum_side"),
            new Texture(Ref.ID, "block/machine/base/drum_side"),
    };

    public static final IOverlayTexturer DRUM_OVERLAY_HANDLER = (type, state) -> new Texture[] {
            new Texture(Ref.ID, "block/machine/overlay/drum/bottom"),
            new Texture(Ref.ID, "block/machine/overlay/drum/top"),
            new Texture(Ref.ID, "block/machine/overlay/drum/side"),
            new Texture(Ref.ID, "block/machine/overlay/drum/side"),
            new Texture(Ref.ID, "block/machine/overlay/drum/side"),
            new Texture(Ref.ID, "block/machine/overlay/drum/side"),
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
