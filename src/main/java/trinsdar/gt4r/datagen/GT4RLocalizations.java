package trinsdar.gt4r.datagen;


import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterLanguageProvider;
import muramasa.antimatter.machine.BlockMachine;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.block.BlockCasing;
import trinsdar.gt4r.block.BlockCasingMachine;
import trinsdar.gt4r.block.BlockTurbineCasing;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.items.ItemIntCircuit;
import net.minecraft.data.DataGenerator;

import static muramasa.antimatter.util.Utils.lowerUnderscoreToUpperSpaced;

public class GT4RLocalizations {

    public static class en_US extends AntimatterLanguageProvider {

        public en_US(DataGenerator gen) {
            super(Ref.ID, Ref.NAME + " en_us Localization", "en_us", gen);
        }

        @Override
        protected void addTranslations() {
            super.addTranslations();
            add(Ref.ID + ".advancements.greg", "GregTech Intergalactical");
            add(Ref.ID + ".advancements.greg.desc", "Getting familiar with your surroundings");
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
            AntimatterAPI.all(ItemIntCircuit.class, domain).forEach(i -> add(i, "Integrated Circuit (" + i.circuitId + ")"));
            add(GT4RData.MixedMetal, lowerUnderscoreToUpperSpaced(GT4RData.MixedMetal.getId()));
            add(GT4RData.SAP_BAG, lowerUnderscoreToUpperSpaced(GT4RData.SAP_BAG.getId()));
        }
    }

}
