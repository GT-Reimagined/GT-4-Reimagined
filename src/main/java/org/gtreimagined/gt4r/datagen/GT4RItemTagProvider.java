package org.gtreimagined.gt4r.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.data.AntimatterStoneTypes;
import muramasa.antimatter.datagen.builder.AntimatterTagBuilder;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemTagProvider;
import muramasa.antimatter.ore.BlockOre;
import muramasa.antimatter.util.RegistryUtils;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.gtreimagined.gt4r.data.CustomTags;
import org.gtreimagined.gt4r.data.GT4RItems;
import org.gtreimagined.gtcore.data.GTCoreItems;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.data.GT4RMaterialTags;

import static muramasa.antimatter.data.AntimatterMaterialTypes.GEM;
import static muramasa.antimatter.data.AntimatterMaterials.Diamond;
import static muramasa.antimatter.util.Utils.getConventionalMaterialType;
import static muramasa.antimatter.util.Utils.getConventionalStoneType;
import static org.gtreimagined.gt4r.data.CustomTags.*;
import static org.gtreimagined.gt4r.data.GT4RItems.*;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gtcore.data.GTCoreTags.*;

public class GT4RItemTagProvider extends AntimatterItemTagProvider {
    public GT4RItemTagProvider(String providerDomain, String providerName, boolean replace, AntimatterBlockTagProvider p) {
        super(providerDomain, providerName, replace, p);
    }

    @Override
    public void processTags(String domain) {
        super.processTags(domain);
        this.tag(PLATES_STEELS).addTag(CustomTags.getTag("plates/steel")).addTag(CustomTags.getTag("plates/stainless_steel"));
        this.tag(PLATES_INVAR_ALUMINIUM).addTag(CustomTags.getTag("plates/invar")).addTag(CustomTags.getTag("plates/aluminium"));
        this.tag(PLATES_IRON_ALUMINIUM).addTag(CustomTags.getTag("plates/iron")).addTag(CustomTags.getTag("plates/aluminium"));
        this.tag(PLATES_WROUGHT_ALUMINIUM).addTag(CustomTags.getTag("plates/wrought_iron")).addTag(CustomTags.getTag("plates/aluminium"));
        this.tag(PLATES_TITAN_TUNGSTEEL).addTag(CustomTags.getTag("plates/titanium")).addTag(CustomTags.getTag("plates/tungstensteel"));
        this.tag(OVERCLOCKER_UPGRADES).add(OverclockerUpgrade);
        this.tag(TRANSFORMER_UPGRADES).add(TransformerUpgrade);
        this.tag(HV_TRANSFORMER_UPGRADES).add(HVTransformerUpgrade);
        this.tag(MUFFLER_UPGRADES).add(MufflerUpgrade);
        this.tag(STEAM_UPGRADES).add(SteamUpgrade);;
        this.tag(MACHINE_HULLS_CHEAP).add(GT4RMaterialTags.HULL.get(Bronze), GT4RMaterialTags.HULL.get(Brass), GT4RMaterialTags.HULL.get(WroughtIron), GT4RMaterialTags.HULL.get(Aluminium));
        this.tag(MACHINE_HULLS_BASIC).add(GT4RMaterialTags.HULL.get(WroughtIron), GT4RMaterialTags.HULL.get(Aluminium));
        this.tag(MACHINE_HULLS_ADVANCED).add(GT4RMaterialTags.HULL.get(Steel), GT4RMaterialTags.HULL.get(StainlessSteel));
        this.tag(MACHINE_HULLS_STABILIZED).add(GT4RMaterialTags.HULL.get(StainlessSteel), GT4RMaterialTags.HULL.get(TungstenSteel), GT4RMaterialTags.HULL.get(Titanium));
        this.tag(MACHINE_HULLS_VERY_ADVANCED).add(GT4RMaterialTags.HULL.get(TungstenSteel), GT4RMaterialTags.HULL.get(Titanium));
        this.tag(GRINDING_HEAD).add(GTCoreItems.DiamondGrindHead, GTCoreItems.TungstenGrindHead);
        this.tag(PISTONS).add(Items.PISTON, Items.STICKY_PISTON);
        this.tag(GEARS_TITAN_TUNGSTEEL).addTag(CustomTags.getTag("gears/titanium")).addTag(CustomTags.getTag("gears/tungstensteel"));
        this.tag(GEARS_STEELS).addTag(CustomTags.getTag("gears/steel")).addTag(CustomTags.getTag("gears/stainless_steel"));
        this.tag(DUSTS_LAPIS_LAZ).addTag(CustomTags.getTag("dusts/lapis")).addTag(CustomTags.getTag("dusts/lazurite"));
        this.tag(DUSTS_COALS).addTag(CustomTags.getTag("dusts/coal")).addTag(CustomTags.getTag("dusts/charcoal"));
        this.tag(GEMS_DIAMOND_RUBY).addTag(GEM.getMaterialTag(Diamond)).addTag(GEM.getMaterialTag(Ruby));

        this.tag(POWER_UNIT_ROCK_CUTTER).add(RockCutterPowerUnit);
        this.tag(DRILL).add(Drill);
        this.tag(BATTERIES_LV).add(REBattery);
        this.tag(BATTERIES_MV).add(LithiumBattery, EnergyCrystal);
        this.tag(BATTERIES_HV).add(LapotronCrystal);
        this.tag(TagUtils.getForgelikeItemTag("stone_ores/iron")).add(Items.IRON_ORE);
        this.tag(TagUtils.getForgelikeItemTag("stone_ores/gold")).add(Items.GOLD_ORE);
        this.tag(TagUtils.getForgelikeItemTag("stone_ores/coal")).add(Items.COAL_ORE);
        this.tag(TagUtils.getForgelikeItemTag("stone_ores/lapis")).add(Items.LAPIS_ORE);
        this.tag(TagUtils.getForgelikeItemTag("stone_ores/diamond")).add(Items.DIAMOND_ORE);
        this.tag(TagUtils.getForgelikeItemTag("stone_ores/redstone")).add(Items.REDSTONE_ORE);
        this.tag(TagUtils.getForgelikeItemTag("stone_ores/emerald")).add(Items.EMERALD_ORE);
        if (AntimatterAPI.isModLoaded(GT4RRef.MOD_BLUEPOWER)){
            this.tag(TagUtils.getForgelikeItemTag("stone_ores/amethyst")).add(RegistryUtils.getItemFromID(new ResourceLocation(GT4RRef.MOD_BLUEPOWER, "amethyst_ore")));
        }

        this.tag(TagUtils.getForgelikeItemTag("plates/constantan")).addTag(AntimatterMaterialTypes.PLATE.getMaterialTag(Cupronickel));
        this.tag(TagUtils.getForgelikeItemTag("ingots/constantan")).addTag(AntimatterMaterialTypes.INGOT.getMaterialTag(Cupronickel));

        this.tag(RODS_STEELS).addTag(CustomTags.getTag("rods/steel")).addTag(CustomTags.getTag("rods/stainless_steel"));
        this.tag(RODS_MAGNETIC).addTag(CustomTags.getTag("rods/magnetic_steel")).addTag(CustomTags.getTag("rods/magnetic_iron"));
        AntimatterTagBuilder<Item> add = this.tag(CORALS);
        String[] corals = {"tube", "brain", "bubble", "fire", "horn"};
        for (String coral : corals){
            add.add(RegistryUtils.getItemFromID(new ResourceLocation(coral + "_coral")), RegistryUtils.getItemFromID(new ResourceLocation("dead_" + coral + "_coral")), RegistryUtils.getItemFromID(new ResourceLocation(coral + "_coral_fan")), RegistryUtils.getItemFromID(new ResourceLocation("dead_" + coral + "_coral_fan"))).replace(false);
        }
        this.tag(VINES).add(Items.VINE, Items.TWISTING_VINES, Items.WEEPING_VINES);
        AntimatterAPI.all(BlockOre.class, o -> {
            if (o.getStoneType() != AntimatterStoneTypes.SAND && o.getStoneType() != AntimatterStoneTypes.SAND_RED && o.getStoneType() != AntimatterStoneTypes.GRAVEL){
                this.tag(TagUtils.getForgelikeItemTag("sandless_" + getConventionalMaterialType(o.getOreType()) + "/" +  o.getMaterial().getId())).addTag(TagUtils.getForgelikeItemTag(String.join("", getConventionalStoneType(o.getStoneType()), "_", getConventionalMaterialType(o.getOreType()), "/", o.getMaterial().getId()))).replace(false);
            }
        });
        AntimatterMaterialTypes.RAW_ORE.all().forEach(m -> {
            this.tag(TagUtils.getForgelikeItemTag("sandless_ores/"+ m.getId())).add(AntimatterMaterialTypes.RAW_ORE.get(m));
        });
        this.tag(TagUtils.getForgelikeItemTag("sandless_ores/"+ AntimatterMaterials.NetheriteScrap.getId())).add(Items.ANCIENT_DEBRIS);
        this.tag(TagUtils.getForgelikeItemTag("sandless_ores/coal")).add(AntimatterMaterialTypes.ORE_STONE.get().get(AntimatterMaterials.Coal).asItem());
        this.tag(TagUtils.getForgelikeItemTag("dyes/black")).add(AntimatterMaterialTypes.DUST.get(DarkAsh));
        this.tag(TagUtils.getForgelikeItemTag("dyes/gray")).add(AntimatterMaterialTypes.DUST.get(Ash));

    }

}
