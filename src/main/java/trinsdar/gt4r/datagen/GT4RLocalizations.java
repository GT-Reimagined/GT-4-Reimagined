package trinsdar.gt4r.datagen;


import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Data;
import muramasa.antimatter.block.BlockStone;
import muramasa.antimatter.datagen.providers.AntimatterLanguageProvider;
import muramasa.antimatter.item.ItemFluidCell;
import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialItem;
import muramasa.antimatter.material.MaterialTag;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.block.BlockCasing;
import trinsdar.gt4r.block.BlockCasingMachine;
import trinsdar.gt4r.block.BlockTurbineCasing;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.Machines;
import trinsdar.gt4r.items.ItemIntCircuit;
import net.minecraft.data.DataGenerator;
import trinsdar.gt4r.items.ItemMatch;
import trinsdar.gt4r.items.ItemPowerUnit;

import static muramasa.antimatter.machine.Tier.MV;
import static muramasa.antimatter.util.Utils.lowerUnderscoreToUpperSpaced;
import static muramasa.antimatter.util.Utils.lowerUnderscoreToUpperSpacedRotated;
import static muramasa.antimatter.util.Utils.validateNBT;

public class GT4RLocalizations {

    public static class en_US extends AntimatterLanguageProvider {

        public en_US(DataGenerator gen) {
            super(Ref.ID, Ref.NAME + " en_us Localization", "en_us", gen);
        }

        @Override
        protected void addTranslations() {
            super.addTranslations();
            add(Ref.ID + ".advancements.gt4r", "GT4 Renewed");
            add(Ref.ID + ".advancements.gt4r.desc", "Getting familiar with your surroundings");
            add(Ref.ID + ".tooltip.occurrence", "Indicates occurrence of ");
            add("block.gt4r.rubber_leaves", "Rubber Leaves");
            add("block.gt4r.rubber_log", "Rubber Log");
            add("block.gt4r.rubber_sapling", "Rubber Sapling");
            add("attribute.name.generic.gt4r.attackReach", "Attack Reach");;
        }

        @Override
        protected void processTranslations(String domain, String locale) {
            super.processTranslations(domain, locale);
            AntimatterAPI.all(BlockCasing.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpaced(i.getId())));
            AntimatterAPI.all(ItemMatch.class, domain).forEach(i -> {
                String value = lowerUnderscoreToUpperSpaced(i.getId());
                if (i == GT4RData.Lighter) value = value.concat(" (Full)");
                add(i, value);
            });
            AntimatterAPI.all(ItemPowerUnit.class, domain).forEach(i -> {
                String value = lowerUnderscoreToUpperSpaced(i.getId());
                if (i.getId().startsWith("power_unit")) value = lowerUnderscoreToUpperSpacedRotated(i.getId());
                add(i, value);
            });
            AntimatterAPI.all(ItemIntCircuit.class, domain).forEach(i -> add(i, "Integrated Circuit (" + i.circuitId + ")"));
            add(GT4RData.MixedMetal, lowerUnderscoreToUpperSpaced(GT4RData.MixedMetal.getId()));
            add(GT4RData.SAP_BAG, lowerUnderscoreToUpperSpaced(GT4RData.SAP_BAG.getId()));
            add(GT4RData.RockCutter, lowerUnderscoreToUpperSpaced(GT4RData.RockCutter.getId()));
            add(GT4RData.CraftingModule, lowerUnderscoreToUpperSpaced(GT4RData.CraftingModule.getId()));
        }

        @Override
        public void add(Item key, String name) {
            if (key == GT4RData.LighterEmpty){
                super.add(key, name.replace("Empty", "(Empty)"));
                return;
            }
            if (key instanceof ItemFluidCell){
                add(key.getTranslationKey(), lowerUnderscoreToUpperSpacedRotated(((ItemFluidCell)key).getId()));
                return;
            }
            if (key instanceof MaterialItem && name.contains("Gem")){
                super.add(key, name.replace(" Gem", ""));
                return;
            }
            super.add(key, name);
        }

        @Override
        public void add(Block key, String name) {
            if (key instanceof BlockStone){
                super.add(key, name.replace("Stone ", ""));
                return;
            }
            super.add(key, name);
        }

        @Override
        public void add(final String key, final String value) {
            if (key.contains("machine")){
                String id = key.contains("macerator.mv") ? "universal_macerator" : key.contains("electrolyzer") ? key.contains("lv") ? "basic_electrolyzer" : "industrial_electrolyzer" : "";
                if (key.contains("battery_buffer")){
                    String tier = value.substring(0, 3);
                    String number = value.contains("One") ? "1x " : value.contains("Four") ? "4x " : "8x ";
                    String afterTier = value.substring(3).replace(" One", "").replace(" Four", "").replace(" Eight", "");
                    super.add(key, tier + number + afterTier);
                    return;
                }
                super.add(key,  value.contains("Infinite") || value.contains("Transformer") || value.contains("Battery") ? value : (id.isEmpty() ? value.replace("Mv ", "").replace("Lv ", "").replace("Ulv ", "").replace("Hv ", "").replace("Ev ", "") : lowerUnderscoreToUpperSpaced(id)));
                return;
            }
            if (key.startsWith("item.gt4r.") && key.contains("bucket")){
                String id;
                if (value.startsWith("Liquid") || value.startsWith("Plasma")){
                    id = value.substring(7).replace(" Bucket", "");
                    if (value.startsWith("Plasma")){
                        id = id + " Plasma";
                    }
                } else {
                    id = value.substring(4).replace(" Bucket", "");
                }
                if (key.startsWith("item.gt4r.liquid_")){
                    String molten = key.replace("item.gt4r.liquid_", "").replace("_bucket", "");
                    if (Material.get(molten) != Data.NULL && Material.get(molten).has(MaterialTag.METAL)){
                        id = value.replace("Liquid", "Molten").replace(" Bucket", "");
                    }
                }
                super.add(key, id + " Bucket");
                return;
            }
            if (key.startsWith("block.gt4r.liquid")){
                String id;
                if (value.startsWith("Liquid") || value.startsWith("Plasma")){
                    id = value.substring(7);
                    if (value.startsWith("Plasma")){
                        id = id + " Plasma";
                    }
                } else {
                    id = value.substring(4);
                }
                if (key.startsWith("block.gt4r.liquid.liquid_")){
                    String molten = key.replace("block.gt4r.liquid.liquid_", "");
                    if (Material.get(molten) != Data.NULL && Material.get(molten).has(MaterialTag.METAL)){
                        id = value.replace("Liquid", "Molten");
                    }
                }

                super.add(key, id);
                return;
            }
            super.add(key, value);
        }
    }

}
