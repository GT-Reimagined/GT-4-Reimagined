package trinsdar.gt4r.tile;

import muramasa.antimatter.AntimatterAPI;
import net.minecraft.tileentity.TileEntityType;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.tile.single.TileEntitySapBag;

import java.util.Collections;

public class TileEntityTypes {
    public static final TileEntityType<?> SAP_BAG_TYPE = new TileEntityType<>(TileEntitySapBag::new, Collections.singleton(GT4RData.SAP_BAG), null).setRegistryName(Ref.ID, "sap_bag");

    public static void init(){
        AntimatterAPI.register(TileEntityType.class, "sap_bag", SAP_BAG_TYPE);
    }
}
