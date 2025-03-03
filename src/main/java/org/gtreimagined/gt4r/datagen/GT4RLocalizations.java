package org.gtreimagined.gt4r.datagen;


import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterLanguageProvider;
import muramasa.antimatter.item.ItemFluidCell;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tool.IAntimatterTool;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.block.BlockCasing;
import org.gtreimagined.gt4r.block.BlockColoredWall;
import org.gtreimagined.gt4r.block.BlockFakeCasing;
import org.gtreimagined.gt4r.data.GT4RItems;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static muramasa.antimatter.machine.Tier.LV;
import static muramasa.antimatter.util.Utils.lowerUnderscoreToUpperSpaced;
import static muramasa.antimatter.util.Utils.lowerUnderscoreToUpperSpacedRotated;
import static org.gtreimagined.gt4r.data.GT4RMaterialTags.BROKEN_TURBINE_ROTOR;
import static org.gtreimagined.gt4r.data.GT4RMaterialTags.TURBINE_ROTOR;
import static org.gtreimagined.gt4r.data.Machines.ELECTROLYZER;
import static org.gtreimagined.gt4r.data.Machines.MACERATOR;

public class GT4RLocalizations {

    public static class en_US extends AntimatterLanguageProvider {

        public en_US() {
            this("en_us");
        }

        public en_US(String locale) {
            super(GT4RRef.ID, GT4RRef.NAME + " " + locale + " Localization", locale);
        }

        @Override
        protected void addTranslations() {
            super.addTranslations();
            add(GT4RRef.ID + ".advancements.gt4r", "GT4 Reimagined");
            add(GT4RRef.ID + ".advancements.gt4r.desc", "Getting familiar with your surroundings");
            add(GT4RRef.ID + ".tooltip.occurrence", "Indicates occurrence of ");
            add("machine.transformer.voltage_info", "%s -> %s (Use Soft Hammer to invert)");
            add("block.gt4r.rubber_leaves", "Rubber Leaves");
            add("block.gt4r.rubber_log", "Rubber Log");
            add("block.gt4r.rubber_sapling", "Rubber Sapling");
            add("attribute.name.generic.gt4r.attackReach", "Attack Reach");;
            add("message.gt4r.pickaxe_torch_right_click", "Fyi there is no need to put a torch in your offhand, just right click with a pickaxe");
            add("message.gt4r.digital_chest_inventory", "Inventory of the Digital chest must be empty before uploading!!!");
            add("message.gt4r.digital_tank_inventory", "Not enough room or tank has another fluid.");
            add("tooltip.gt4r.upload_chest", "Upload to Chest");
            add("tooltip.gt4r.upload_tank", "Upload to Tank");
            add("tooltip.gt4r.download_orb", "Download to Data Orb");
            add("tooltip.gt4r.sort_inventory", "Sort Inventory");
            add("tooltip.gt4r.possible_upgrades", "Possible Upgrades: %s");
            add("tooltip.gt4r.overclocker_upgrades", "%s Overclocker Upgrades");
            add("tooltip.gt4r.transformer_upgrades", "%s Transformer Upgrades");
            add("tooltip.gt4r.muffler_upgrades", "Has Muffler Upgrades");
            add("tooltip.gt4r.steam_upgrades", "Has Steam Upgrade");
            add("machine.drum.fluid", "Contains %s mb of %s");
            add("machine.drum.output", "Currently set to auto output");
            add("message.gt4r.player_detector.all", "Detects all Players");
            add("message.gt4r.player_detector.other", "Detects all Players except %s");
            add("message.gt4r.player_detector.only", "Detects only %s");
        }

        @Override
        protected void english(String domain, String locale) {
            super.english(domain, locale);
            AntimatterAPI.all(BlockCasing.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpaced(i.getId())));
            AntimatterAPI.all(BlockFakeCasing.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpaced(i.getId())));
            AntimatterAPI.all(BlockColoredWall.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpaced(i.getId())));
            override(AntimatterAPI.get(IAntimatterTool.class, "rock_cutter_lv", GT4RRef.ID).getItem().getDescriptionId(), "Rock Cutter");
            override(GT4RItems.StorageDataOrb.getDescriptionId(), "Data Orb");
            AntimatterAPI.all(Machine.class, domain).forEach(i -> {
                Collection<Tier> tiers =  i.getTiers();
                String value = i.getLang(locale).concat(" (%s)");
                if (i.getId().contains("battery_buffer")){
                    override("machine." + i.getId(), value.replace(" x", "x"));
                    return;
                }
                if (i == MACERATOR || i == ELECTROLYZER){
                    if (i == MACERATOR){
                        override("machine." + i.getId() + ".mv", lowerUnderscoreToUpperSpaced("universal_macerator").concat(" (%s)"));
                    } else {
                        tiers.forEach(t -> {
                            String id = t == LV ? "basic_electrolyzer" : "industrial_electrolyzer";
                            override("machine." + i.getId() + "." + t.getId(), lowerUnderscoreToUpperSpaced(id).concat(" (%s)"));
                        });
                    }
                }
            });
            AntimatterAPI.all(ItemFluidCell.class, domain).forEach(i -> override(i.getDescriptionId(), lowerUnderscoreToUpperSpacedRotated(i.getId())));
        }

        @Override
        protected void overrides() {
            BROKEN_TURBINE_ROTOR.all().forEach(m -> override(GT4RRef.ANTIMATTER, BROKEN_TURBINE_ROTOR.get(m).getDescriptionId(), lowerUnderscoreToUpperSpaced("broken_" + m.getId() + "_" + TURBINE_ROTOR.getId())));
        }
    }

    public static class en_GB extends en_US{
        private final Map<String, String> usToGB = new HashMap<>();
        public en_GB() {
            super("en_gb");
            usToGB.put("Gasoline", "Petrol");
        }

        @Override
        public void add(String key, String value) {
            boolean superCall = false;
            for (String us : usToGB.keySet()){
                if (value.contains(us)){
                    value = value.replace(us, usToGB.get(us));
                    superCall = true;
                }
            }
            if (superCall) {
                super.add(key, value);
            }
        }
    }

}
