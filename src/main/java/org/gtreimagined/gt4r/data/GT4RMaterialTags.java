package org.gtreimagined.gt4r.data;


import org.gtreimagined.gtlib.GTAPI;
import org.gtreimagined.gtlib.Ref;
import org.gtreimagined.gtlib.material.Material;
import org.gtreimagined.gtlib.material.MaterialTag;
import org.gtreimagined.gtlib.material.MaterialType;
import org.gtreimagined.gtlib.material.MaterialTypeItem;
import org.gtreimagined.gtlib.material.tags.DoubleMaterialTag;
import org.gtreimagined.gtlib.material.tags.NumberMaterialTag;
import net.minecraft.world.item.Item;
import org.gtreimagined.gt4r.items.ItemTurbineRotor;

public class GT4RMaterialTags {
    public static final DoubleMaterialTag BATHING_PERSULFATE = new DoubleMaterialTag("bathing_nas");
    public static final DoubleMaterialTag BATHING_MERCURY = new DoubleMaterialTag("bathing_mercury");
    public static final MaterialTag ELEC30 = GTAPI.register(MaterialTag.class, new MaterialTag("elec30", true));
    public static final MaterialTag ELEC60 = GTAPI.register(MaterialTag.class, new MaterialTag("elec60", true));
    public static final MaterialTag ELEC90 = GTAPI.register(MaterialTag.class, new MaterialTag("elec90", true));
    public static final MaterialTag ELEC120 = GTAPI.register(MaterialTag.class, new MaterialTag("elec120", true));
    public static final MaterialTag ELEC = GTAPI.register(MaterialTag.class, new MaterialTag("elec", true));

    public static final MaterialTag NEEDS_BLAST_FURNACE = GTAPI.register(MaterialTag.class, new MaterialTag("needs_blast_furnace", true));
    public static final NumberMaterialTag BLAST_FURNACE_TEMP = (NumberMaterialTag) GTAPI.register(MaterialTag.class, new NumberMaterialTag("blast_furnace_temp", true));
    public static final MaterialTag ROCK_CUTTER = new MaterialTag("rock_cutter");
    public static final MaterialTypeItem<?> HULL = GTAPI.register(MaterialTypeItem.class, new MaterialTypeItem<>("hull", 2, true, Ref.U * 8));
    public static final MaterialTypeItem<?> TURBINE_BLADE = GTAPI.register(MaterialTypeItem.class, new MaterialTypeItem<>("turbine_blade", 1, true, (Ref.U * 3) + (Ref.U8 * 2)));//.unSplitName();
    public static final MaterialTypeItem<?> TURBINE_ROTOR = GTAPI.register(MaterialTypeItem.class, new MaterialTypeItem<>("turbine_rotor", 1, true, Ref.U * 17, new MaterialTypeItem.ItemSupplier() {
        @Override
        public void createItems(String domain, MaterialType<?> type, Material material) {
            new ItemTurbineRotor(domain, type, material, new Item.Properties().defaultDurability(getMaxDamage(material)).tab(Ref.TAB_MATERIALS));
        }

        private int getMaxDamage(Material material){
            int d = 10000;
            if (material == Materials.Bronze){
                d = 15000;
            }
            if (material == Materials.TungstenSteel){
                d = 30000;
            }
            if (material == Materials.Carbon){
                d = 2500;
            }
            if (material == Materials.Osmium){
                d = 60000;
            }
            if (material == Materials.Osmiridium){
                d = 120000;
            }
            return d;
        }
    }));
    public static final MaterialTypeItem<?> BROKEN_TURBINE_ROTOR = new MaterialTypeItem<>("broken_turbine_rotor", 1, true, Ref.U * 17);
    public static final MaterialTag SEMIFLUID = new MaterialTag("semifluid");

    public static void init(){
        TURBINE_BLADE.unSplitName().setIgnoreTextureSets();
        TURBINE_ROTOR.unSplitName().setIgnoreTextureSets();
        BROKEN_TURBINE_ROTOR.unSplitName().setIgnoreTextureSets();
        HULL.setIgnoreTextureSets();
    }
}
