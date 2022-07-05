package trinsdar.gt4r.tile;

import muramasa.antimatter.AntimatterAPI;
import net.minecraft.world.level.block.entity.BlockEntityType;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.tile.single.TileEntitySapBag;

import java.util.Collections;

public class TileEntityTypes {
    public static final BlockEntityType<?> SAP_BAG_TYPE = new BlockEntityType<>(TileEntitySapBag::new, Collections.singleton(GT4RData.SAP_BAG), null);

    public static void init(){
        AntimatterAPI.register(BlockEntityType.class, "sap_bag", Ref.ID, SAP_BAG_TYPE);
    }
}
