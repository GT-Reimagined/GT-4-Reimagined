package trinsdar.gt4r.tile;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.AntimatterAPI;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.tile.single.TileEntitySapBag;
import trinsdar.gt4r.tile.single.TileEntitySlaveController;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

public class TileEntityTypes {
    public static final TileEntityType<?> SAP_BAG_TYPE = new TileEntityType<>(TileEntitySapBag::new, Collections.singleton(GT4RData.SAP_BAG), null).setRegistryName(Ref.ID, "sap_bag");
    public static final TileEntityType<?> SLAVE_CONTROLLER_TYPE = new TileEntityType<>(TileEntitySlaveController::new, new HashSet<>(Arrays.asList(GT4RData.FIRE_BRICKS)), null).setRegistryName(Ref.ID, "slave_controller");

    public static void init(){
        AntimatterAPI.register(TileEntityType.class, "sap_bag", SAP_BAG_TYPE);
        AntimatterAPI.register(TileEntityType.class, "slave_controller", SLAVE_CONTROLLER_TYPE);
    }
}
