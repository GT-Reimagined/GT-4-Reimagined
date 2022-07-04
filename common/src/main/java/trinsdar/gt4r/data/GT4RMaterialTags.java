package trinsdar.gt4r.data;

import muramasa.antimatter.material.DoubleMaterialTag;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTag;
import muramasa.antimatter.material.MaterialType;
import muramasa.antimatter.material.MaterialTypeItem;
import net.minecraft.world.item.Item;
import trinsdar.gt4r.items.ItemTurbineRotor;

public class GT4RMaterialTags {
    public static final DoubleMaterialTag BATHING_PERSULFATE = new DoubleMaterialTag("bathing_nas");
    public static final DoubleMaterialTag BATHING_MERCURY = new DoubleMaterialTag("bathing_mercury");
    public static MaterialTag ELEC30 = new MaterialTag("elec30");
    public static MaterialTag ELEC60 = new MaterialTag("elec60");
    public static MaterialTag ELEC90 = new MaterialTag("elec90");
    public static MaterialTag ELEC120 = new MaterialTag("elec120");
    public static MaterialTag ROCK_CUTTER = new MaterialTag("rock_cutter");
    public static MaterialTypeItem<?> HULL = new MaterialTypeItem<>("hull", 2, true, muramasa.antimatter.Ref.U * 8);
    public static MaterialTypeItem<?> TURBINE_BLADE = new MaterialTypeItem<>("turbine_blade", 1, true, muramasa.antimatter.Ref.U * 3);//.unSplitName();
    public static MaterialTypeItem<?> TURBINE_ROTOR = new MaterialTypeItem<>("turbine_rotor", 1, true, muramasa.antimatter.Ref.U * 17, new MaterialTypeItem.ItemSupplier() {
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
    public static MaterialTypeItem<?> BROKEN_TURBINE_ROTOR = new MaterialTypeItem<>("broken_turbine_rotor", 1, true, muramasa.antimatter.Ref.U * 17);
    public static MaterialTypeItem<?> PICKAXE_HEAD = new MaterialTypeItem<>("pickaxe_head", 2, true, muramasa.antimatter.Ref.U * 3);
    public static MaterialTypeItem<?> AXE_HEAD = new MaterialTypeItem<>("axe_head", 2, true, muramasa.antimatter.Ref.U * 3);
    public static MaterialTypeItem<?> SWORD_HEAD = new MaterialTypeItem<>("sword_head", 2, true, muramasa.antimatter.Ref.U * 2);
    public static MaterialTypeItem<?> SHOVEL_HEAD = new MaterialTypeItem<>("shovel_head", 2, true, muramasa.antimatter.Ref.U);
    public static MaterialTypeItem<?> HOE_HEAD = new MaterialTypeItem<>("hoe_head", 2, true, muramasa.antimatter.Ref.U * 2);
    public static MaterialTypeItem<?> HAMMER_HEAD = new MaterialTypeItem<>("hammer_head", 2, true, muramasa.antimatter.Ref.U * 6);
    public static MaterialTypeItem<?> FILE_HEAD = new MaterialTypeItem<>("file_head", 2, true, muramasa.antimatter.Ref.U * 2);
    public static MaterialTypeItem<?> SAW_HEAD = new MaterialTypeItem<>("saw_head", 2, true, muramasa.antimatter.Ref.U * 2);
    public static MaterialTag SEMIFLUID = new MaterialTag("semifluid");
    public static MaterialTag CABINET = new MaterialTag("cabinet");
    public static MaterialTag WORKBENCH = new MaterialTag("workbench");
    public static MaterialTag CHARGING_WORKBENCH = new MaterialTag("charging_workbench");
    public static MaterialTag LOCKER = new MaterialTag("locker");
    public static MaterialTag CHARGING_LOCKER = new MaterialTag("charging_locker");

    public static void init(){}
}
