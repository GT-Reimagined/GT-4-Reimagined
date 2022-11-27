package trinsdar.gt4r.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemTagProvider;
import muramasa.antimatter.ore.BlockOre;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.GT4RMaterialTags;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.util.Utils.getConventionalMaterialType;
import static muramasa.antimatter.util.Utils.getConventionalStoneType;
import static trinsdar.gt4r.data.CustomTags.*;
import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.Materials.*;

public class GT4RItemTagProvider extends AntimatterItemTagProvider {
    public GT4RItemTagProvider(String providerDomain, String providerName, boolean replace, AntimatterBlockTagProvider p) {
        super(providerDomain, providerName, replace, p);
    }

    @Override
    public void processTags(String domain) {
        super.processTags(domain);
        this.tag(PLATES_STEELS).addTag(getTag("plates/steel")).addTag(getTag("plates/stainless_steel"));
        this.tag(PLATES_INVAR_ALUMINIUM).addTag(getTag("plates/invar")).addTag(getTag("plates/aluminium"));
        this.tag(PLATES_IRON_ALUMINIUM).addTag(getTag("plates/iron")).addTag(getTag("plates/aluminium"));
        this.tag(PLATES_WROUGHT_ALUMINIUM).addTag(getTag("plates/wrought_iron")).addTag(getTag("plates/aluminium"));
        this.tag(PLATES_TITAN_TUNGSTEEL).addTag(getTag("plates/titanium")).addTag(getTag("plates/tungstensteel"));
        this.tag(CIRCUITS_BASIC).add(CircuitBasic);
        this.tag(CIRCUITS_ADVANCED).add(CircuitAdv);
        this.tag(CIRCUITS_ELITE).add(CircuitDataControl);
        this.tag(CIRCUITS_MASTER).add(CircuitEnergyFlow);
        this.tag(CIRCUITS_DATA).add(CircuitDataStorage);
        this.tag(CIRCUITS_ULTIMATE).add(CircuitDataOrb);
        this.tag(MACHINE_HULLS_CHEAP).add(GT4RMaterialTags.HULL.get(Bronze), GT4RMaterialTags.HULL.get(Brass), GT4RMaterialTags.HULL.get(WroughtIron), GT4RMaterialTags.HULL.get(AntimatterMaterials.Iron), GT4RMaterialTags.HULL.get(Aluminium));
        this.tag(MACHINE_HULLS_SEMI_CHEAP).add(GT4RMaterialTags.HULL.get(WroughtIron), GT4RMaterialTags.HULL.get(Aluminium));
        this.tag(MACHINE_HULLS_BASIC).add(GT4RMaterialTags.HULL.get(Steel), GT4RMaterialTags.HULL.get(StainlessSteel), GT4RMaterialTags.HULL.get(Aluminium));
        this.tag(MACHINE_HULLS_ADVANCED).add(GT4RMaterialTags.HULL.get(Aluminium), GT4RMaterialTags.HULL.get(StainlessSteel));
        this.tag(MACHINE_HULLS_STABILIZED).add(GT4RMaterialTags.HULL.get(StainlessSteel), GT4RMaterialTags.HULL.get(TungstenSteel), GT4RMaterialTags.HULL.get(Titanium));
        this.tag(MACHINE_HULLS_VERY_ADVANCED).add(GT4RMaterialTags.HULL.get(TungstenSteel), GT4RMaterialTags.HULL.get(Titanium));
        this.tag(GRINDING_HEAD).add(DiamondGrindHead, TungstenGrindHead);
        this.tag(PISTONS).add(Items.PISTON, Items.STICKY_PISTON);
        this.tag(GEARS_TITAN_TUNGSTEEL).addTag(getTag("gears/titanium")).addTag(getTag("gears/tungstensteel"));
        this.tag(GEARS_STEELS).addTag(getTag("gears/steel")).addTag(getTag("gears/stainless_steel"));
        this.tag(DUSTS_LAPIS_LAZ).addTag(getTag("dusts/lapis")).addTag(getTag("dusts/lazurite"));
        this.tag(DUSTS_COALS).addTag(getTag("dusts/coal")).addTag(getTag("dusts/charcoal"));

        this.tag(BATTERIES_RE).add(BatteryRE);
        this.tag(BATTERIES_SMALL).add(BatterySmallSodium, BatterySmallCadmium, BatterySmallLithium);
        this.tag(BATTERIES_MEDIUM).add(BatteryMediumSodium, BatteryMediumCadmium, BatteryMediumLithium);
        this.tag(BATTERIES_LARGE).add(BatteryLargeSodium, BatteryLargeCadmium, BatteryLargeLithium, EnergyCrystal);
        this.tag(POWER_UNIT_LV).add(PowerUnitLV);
        this.tag(POWER_UNIT_MV).add(PowerUnitMV);
        this.tag(POWER_UNIT_HV).add(PowerUnitHV);
        this.tag(POWER_UNIT_SMALL).add(SmallPowerUnit);
        this.tag(POWER_UNIT_ROCK_CUTTER).add(RockCutterPowerUnit);
        this.tag(TagUtils.getForgelikeItemTag("stone_ores/iron")).add(Items.IRON_ORE);
        this.tag(TagUtils.getForgelikeItemTag("stone_ores/gold")).add(Items.GOLD_ORE);
        this.tag(TagUtils.getForgelikeItemTag("stone_ores/coal")).add(Items.COAL_ORE);
        this.tag(TagUtils.getForgelikeItemTag("stone_ores/lapis")).add(Items.LAPIS_ORE);
        this.tag(TagUtils.getForgelikeItemTag("stone_ores/diamond")).add(Items.DIAMOND_ORE);
        this.tag(TagUtils.getForgelikeItemTag("stone_ores/redstone")).add(Items.REDSTONE_ORE);
        this.tag(TagUtils.getForgelikeItemTag("stone_ores/emerald")).add(Items.EMERALD_ORE);
        if (AntimatterAPI.isModLoaded(Ref.MOD_BLUEPOWER)){
            this.tag(TagUtils.getForgelikeItemTag("stone_ores/amethyst")).add(AntimatterPlatformUtils.getItemFromID(new ResourceLocation(Ref.MOD_BLUEPOWER, "amethyst_ore")));
        }

        this.tag(INGOTS_MIXED_METAL).add(MixedMetal);
        this.tag(TagUtils.getForgelikeItemTag("plates/constantan")).addTag(PLATE.getMaterialTag(Cupronickel));
        this.tag(TagUtils.getForgelikeItemTag("ingots/constantan")).addTag(INGOT.getMaterialTag(Cupronickel));

        this.tag(RODS_STEELS).addTag(getTag("rods/steel")).addTag(getTag("rods/stainless_steel"));
        this.tag(RODS_MAGNETIC).addTag(getTag("rods/magnetic_steel")).addTag(getTag("rods/magnetic_iron"));
        TagsProvider.TagAppender<Item> add = this.tag(CORALS);
        String[] corals = {"tube", "brain", "bubble", "fire", "horn"};
        for (String coral : corals){
            add.add(AntimatterPlatformUtils.getItemFromID(new ResourceLocation(coral + "_coral")), AntimatterPlatformUtils.getItemFromID(new ResourceLocation("dead_" + coral + "_coral")), AntimatterPlatformUtils.getItemFromID(new ResourceLocation(coral + "_coral_fan")), AntimatterPlatformUtils.getItemFromID(new ResourceLocation("dead_" + coral + "_coral_fan"))).replace(false);
        }
        this.tag(VINES).add(Items.VINE, Items.TWISTING_VINES, Items.WEEPING_VINES);
        AntimatterAPI.all(BlockOre.class, o -> {
            if (o.getStoneType() != SAND && o.getStoneType() != SAND_RED && o.getStoneType() != GRAVEL){
                this.tag(TagUtils.getForgelikeItemTag("sandless_" + getConventionalMaterialType(o.getOreType()) + "/" +  o.getMaterial().getId())).addTag(TagUtils.getForgelikeItemTag(String.join("", getConventionalStoneType(o.getStoneType()), "_", getConventionalMaterialType(o.getOreType()), "/", o.getMaterial().getId()))).replace(false);
            }
        });
        RAW_ORE.all().forEach(m -> {
            this.tag(TagUtils.getForgelikeItemTag("sandless_ores/"+ m.getId())).add(RAW_ORE.get(m));
        });
        this.tag(TagUtils.getForgelikeItemTag("sandless_ores/"+ AntimatterMaterials.NetheriteScrap.getId())).add(Items.ANCIENT_DEBRIS);
        this.tag(TagUtils.getForgelikeItemTag("sandless_ores/coal")).add(ORE_STONE.get().get(AntimatterMaterials.Coal).asItem());
        this.tag(TagUtils.getForgelikeItemTag("dyes/black")).add(DUST.get(DarkAsh));
        this.tag(TagUtils.getForgelikeItemTag("dyes/gray")).add(DUST.get(Ash));

    }

}
