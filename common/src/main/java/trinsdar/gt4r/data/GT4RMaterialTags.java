package trinsdar.gt4r.data;


import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTag;
import muramasa.antimatter.material.MaterialType;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.material.tags.DoubleMaterialTag;
import muramasa.antimatter.material.tags.NumberMaterialTag;
import net.minecraft.world.item.Item;
import trinsdar.gt4r.items.ItemTurbineRotor;

public class GT4RMaterialTags {
    public static final DoubleMaterialTag BATHING_PERSULFATE = new DoubleMaterialTag("bathing_nas");
    public static final DoubleMaterialTag BATHING_MERCURY = new DoubleMaterialTag("bathing_mercury");
    public static final MaterialTag ELEC30 = AntimatterAPI.register(MaterialTag.class, new MaterialTag("elec30", true));
    public static final MaterialTag ELEC60 = AntimatterAPI.register(MaterialTag.class, new MaterialTag("elec60", true));
    public static final MaterialTag ELEC90 = AntimatterAPI.register(MaterialTag.class, new MaterialTag("elec90", true));
    public static final MaterialTag ELEC120 = AntimatterAPI.register(MaterialTag.class, new MaterialTag("elec120", true));
    public static final MaterialTag ELEC = AntimatterAPI.register(MaterialTag.class, new MaterialTag("elec", true));

    public static final MaterialTag NEEDS_BLAST_FURNACE = AntimatterAPI.register(MaterialTag.class, new MaterialTag("needs_blast_furnace", true));
    public static final NumberMaterialTag BLAST_FURNACE_TEMP = (NumberMaterialTag) AntimatterAPI.register(MaterialTag.class, new NumberMaterialTag("blast_furnace_temp", true));
    public static final MaterialTag ROCK_CUTTER = new MaterialTag("rock_cutter");
    public static final MaterialTypeItem<?> HULL = new MaterialTypeItem<>("hull", 2, true, muramasa.antimatter.Ref.U * 8);
    public static final MaterialTypeItem<?> TURBINE_BLADE = new MaterialTypeItem<>("turbine_blade", 1, true, muramasa.antimatter.Ref.U * 3);//.unSplitName();
    public static final MaterialTypeItem<?> TURBINE_ROTOR = new MaterialTypeItem<>("turbine_rotor", 1, true, muramasa.antimatter.Ref.U * 17, new MaterialTypeItem.ItemSupplier() {
        @Override
        public void createItems(String domain, MaterialType<?> type, Material material) {
            new ItemTurbineRotor(domain, type, material, new Item.Properties().defaultDurability(getMaxDamage(material)).tab(muramasa.antimatter.Ref.TAB_MATERIALS));
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
    });
    public static final MaterialTypeItem<?> BROKEN_TURBINE_ROTOR = new MaterialTypeItem<>("broken_turbine_rotor", 1, true, muramasa.antimatter.Ref.U * 17);
    public static final MaterialTag SEMIFLUID = new MaterialTag("semifluid");
    public static final MaterialTag CABINET = new MaterialTag("cabinet");
    public static final MaterialTag WORKBENCH = new MaterialTag("workbench");
    public static final MaterialTag CHARGING_WORKBENCH = new MaterialTag("charging_workbench");
    public static final MaterialTag LOCKER = new MaterialTag("locker");
    public static final MaterialTag CHARGING_LOCKER = new MaterialTag("charging_locker");
    public static final MaterialTag DRUM = new MaterialTag("drum");

    public static void init(){
        TURBINE_BLADE.unSplitName().setIgnoreTextureSets();
        TURBINE_ROTOR.unSplitName().setIgnoreTextureSets();
        BROKEN_TURBINE_ROTOR.unSplitName().setIgnoreTextureSets();
        HULL.setIgnoreTextureSets();
    }
}
