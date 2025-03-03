package org.gtreimagined.gt4r.data;

import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.texture.Texture;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.cover.CoverConveyor;
import org.gtreimagined.gt4r.cover.CoverCrafting;
import org.gtreimagined.gt4r.cover.CoverDrain;
import org.gtreimagined.gt4r.cover.CoverDynamoOld;
import org.gtreimagined.gt4r.cover.CoverFusionInput;
import org.gtreimagined.gt4r.cover.CoverFusionOutput;
import org.gtreimagined.gt4r.cover.CoverPump;
import org.gtreimagined.gt4r.cover.CoverSteamVent;
import org.gtreimagined.gt4r.cover.redstone.CoverRedstoneMachineController;
import org.gtreimagined.gt4r.items.ItemCraftingModule;

public class GT4RCovers {
    public static final CoverFactory COVER_CONVEYOR = CoverFactory.builder(CoverConveyor::new).gui().item((a, b) ->
            new ItemCover(GT4RRef.ID, "conveyor_module").tip("Can be placed on machines as a cover")).addTextures(new Texture(GT4RRef.ID, "block/cover/conveyor_module")).build(GT4RRef.ID, "conveyor_module");
    public static final CoverFactory COVER_PUMP = CoverFactory.builder(CoverPump::new).gui().item((a, b) ->
            new ItemCover(GT4RRef.ID, "pump_module").tip("Can be placed on machines as a cover")).addTextures(new Texture(GT4RRef.ID, "block/cover/pump_module")).build(GT4RRef.ID, "pump_module");
    public static final CoverFactory COVER_FUSION_OUTPUT = CoverFactory.builder(CoverFusionOutput::new)
            .addTextures(new Texture(GT4RRef.ID, "block/cover/fusion_output")).build(GT4RRef.ID, "fusion_output");
    public static final CoverFactory COVER_FUSION_INPUT = CoverFactory.builder(CoverFusionInput::new)
            .addTextures(new Texture(GT4RRef.ID, "block/cover/fusion_input")).build(GT4RRef.ID, "fusion_input");
    public static final CoverFactory COVER_DYNAMO_OLD = CoverFactory.builder(CoverDynamoOld::new)
            .addTextures(new Texture(GT4RRef.ID, "block/cover/dynamo")).build(GT4RRef.ID, "dynamo_old");
    public static final CoverFactory COVER_DRAIN = CoverFactory.builder(CoverDrain::new).item((a, b) ->
            new ItemCover(GT4RRef.ID, "drain").tip("Can be placed on machines as a cover")).addTextures(new Texture(GT4RRef.ID, "block/cover/drain")).build(GT4RRef.ID, "drain");
    public static final CoverFactory COVER_STEAM_VENT = CoverFactory.builder(CoverSteamVent::new)
            .addTextures(new Texture(GT4RRef.ID, "block/cover/output")).build(GT4RRef.ID, "steam_vent");
    public static final CoverFactory COVER_CRAFTING = CoverFactory.builder(CoverCrafting::new).gui().item((a, b) ->
            new ItemCraftingModule().tip("Can be placed on machines as a cover")).setMenuHandler(MenuHandlers.COVER_CRAFTING_HANDLER).addTextures(new Texture(GT4RRef.ID, "block/cover/crafting_module")).build(GT4RRef.ID, "crafting_module");
    public static final CoverFactory COVER_REDSTONE_MACHINE_CONTROLLER = CoverFactory.builder(CoverRedstoneMachineController::new).gui().item((a, b) ->
            new ItemCover(GT4RRef.ID, "redstone_machine_controller").tip("Can be placed on machines as a cover")).addTextures(new Texture(GT4RRef.ID, "block/cover/redstone_machine_controller")).build(GT4RRef.ID, "redstone_machine_controller");
    public static void init(){

    }
}
