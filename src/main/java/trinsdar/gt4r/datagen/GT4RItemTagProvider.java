package trinsdar.gt4r.datagen;

import muramasa.antimatter.datagen.ExistingFileHelperOverride;
import muramasa.antimatter.datagen.providers.AntimatterItemTagProvider;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;
import trinsdar.gt4r.data.CustomTags;

import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.GEM;
import static trinsdar.gt4r.data.CustomTags.*;
import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.Materials.*;

public class GT4RItemTagProvider extends AntimatterItemTagProvider {
    public GT4RItemTagProvider(String providerDomain, String providerName, boolean replace, DataGenerator gen, BlockTagsProvider p, ExistingFileHelperOverride fh) {
        super(providerDomain, providerName, replace, gen, p, fh);
    }

    @Override
    public void registerTags() {
        super.registerTags();
        this.getOrCreateBuilder(PLATES_STEELS).addTag(getTag("plates/steel")).addTag(getTag("plates/stainless_steel"));
        this.getOrCreateBuilder(PLATES_INVAR_ALUMINIUM).addTag(getTag("plates/invar")).addTag(getTag("plates/aluminium"));
        this.getOrCreateBuilder(PLATES_IRON_ALUMINIUM).addTag(getTag("plates/iron")).addTag(getTag("plates/aluminium"));
        this.getOrCreateBuilder(PLATES_WROUGHT_ALUMINIUM).addTag(getTag("plates/wrought_iron")).addTag(getTag("plates/aluminium"));
        this.getOrCreateBuilder(PLATES_TITAN_TUNGSTEEL).addTag(getTag("plates/titanium")).addTag(getTag("plates/tungstensteel"));
        this.getOrCreateBuilder(CIRCUITS_BASIC).add(CircuitBasic);
        this.getOrCreateBuilder(CIRCUITS_ADVANCED).add(CircuitAdv);
        this.getOrCreateBuilder(CIRCUITS_ELITE).add(CircuitDataControl);
        this.getOrCreateBuilder(CIRCUITS_MASTER).add(CircuitEnergyFlow);
        this.getOrCreateBuilder(CIRCUITS_DATA).add(CircuitDataStorage);
        this.getOrCreateBuilder(CIRCUITS_ULTIMATE).add(CircuitDataOrb);
        this.getOrCreateBuilder(MACHINE_HULLS_CHEAP).add(HULL.get(Bronze), HULL.get(Brass), HULL.get(WroughtIron), HULL.get(Aluminium));
        this.getOrCreateBuilder(MACHINE_HULLS_SEMI_CHEAP).add(HULL.get(WroughtIron), HULL.get(Aluminium));
        this.getOrCreateBuilder(MACHINE_HULLS_BASIC).add(HULL.get(Steel), HULL.get(StainlessSteel), HULL.get(Aluminium));
        this.getOrCreateBuilder(MACHINE_HULLS_ADVANCED).add(HULL.get(TungstenSteel), HULL.get(Titanium));
        this.getOrCreateBuilder(MACHINE_HULLS_VERY_ADVANCED).add(HULL.get(TungstenSteel), HULL.get(Titanium));
        this.getOrCreateBuilder(GRINDING_HEAD).add(DiamondGrindHead, TungstenGrindHead);
        this.getOrCreateBuilder(PISTONS).add(Items.PISTON, Items.STICKY_PISTON);
        this.getOrCreateBuilder(GEARS_TITAN_TUNGSTEEL).addTag(getTag("gears/titanium")).addTag(getTag("gears/tungstensteel"));
        this.getOrCreateBuilder(GEARS_STEELS).addTag(getTag("gears/steel")).addTag(getTag("gears/stainless_steel"));
        this.getOrCreateBuilder(DUSTS_LAPIS_LAZ).addTag(getTag("dusts/lapis")).addTag(getTag("dusts/lazurite"));
        this.getOrCreateBuilder(DUSTS_COALS).addTag(getTag("dusts/coal")).addTag(getTag("dusts/charcoal"));
        this.getOrCreateBuilder(GEM.getMaterialTag(Flint)).add(Items.FLINT);
        this.getOrCreateBuilder(DUST.getMaterialTag(Blaze)).add(Items.BLAZE_POWDER);
        this.getOrCreateBuilder(BATTERIES_SMALL).add(BatterySmallSodium, BatterySmallCadmium, BatterySmallLithium);
        this.getOrCreateBuilder(BATTERIES_MEDIUM).add(BatteryMediumSodium, BatteryMediumCadmium, BatteryMediumLithium);
        this.getOrCreateBuilder(BATTERIES_LARGE).add(BatteryLargeSodium, BatteryLargeCadmium, BatteryLargeLithium, EnergyCrystal);
        this.getOrCreateBuilder(TagUtils.getForgeItemTag("stone_ores/iron")).add(Items.IRON_ORE);
        this.getOrCreateBuilder(TagUtils.getForgeItemTag("stone_ores/gold")).add(Items.GOLD_ORE);
        this.getOrCreateBuilder(TagUtils.getForgeItemTag("stone_ores/coal")).add(Items.COAL_ORE);
        this.getOrCreateBuilder(TagUtils.getForgeItemTag("stone_ores/lapis")).add(Items.LAPIS_ORE);
        this.getOrCreateBuilder(TagUtils.getForgeItemTag("stone_ores/diamond")).add(Items.DIAMOND_ORE);
        this.getOrCreateBuilder(TagUtils.getForgeItemTag("stone_ores/redstone")).add(Items.REDSTONE_ORE);
        this.getOrCreateBuilder(TagUtils.getForgeItemTag("stone_ores/emerald")).add(Items.EMERALD_ORE);
        this.getOrCreateBuilder(INGOTS_MIXED_METAL).add(MixedMetal);

    }

}
