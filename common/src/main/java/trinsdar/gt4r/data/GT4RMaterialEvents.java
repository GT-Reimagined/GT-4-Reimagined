package trinsdar.gt4r.data;

import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.event.MaterialEvent;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import net.minecraft.world.item.enchantment.Enchantments;
import trinsdar.gt4r.material.GT4RMaterialEvent;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.data.AntimatterMaterialTypes.FOIL;
import static muramasa.antimatter.data.AntimatterMaterialTypes.PLATE;
import static muramasa.antimatter.material.MaterialTags.NOSMASH;
import static muramasa.antimatter.material.MaterialTags.RUBBERTOOLS;
import static net.minecraft.world.item.Tiers.IRON;
import static trinsdar.gt4r.data.GT4RMaterialTags.ELEC;
import static trinsdar.gt4r.data.Materials.*;

public class GT4RMaterialEvents {
    public static void onMaterialEvent(GT4RMaterialEvent event){
        flags(event);
        antimatterMaterials(event);
	    byProducts(event);
	    Material[] turbineStuff = new Material[]{Carbon, Osmium, Bronze, Magnalium, Steel, TungstenSteel, Osmiridium};
        for (Material material : turbineStuff) {
            event.setMaterial(material).flags(GT4RMaterialTags.TURBINE_ROTOR, GT4RMaterialTags.TURBINE_BLADE, GT4RMaterialTags.BROKEN_TURBINE_ROTOR);
        }
        GT4RMaterialTags.HULL.add(Aluminium, AntimatterMaterials.Iron, Titanium, Brass, Bronze, Steel, StainlessSteel, WroughtIron, TungstenSteel);
        event.setMaterial(Aluminium).asMetal(933, 1000, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.GEAR, AntimatterMaterialTypes.BOLT, AntimatterMaterialTypes.SCREW);
        event.setMaterial(Beryllium).asMetal(1560, 0, AntimatterMaterialTypes.PLATE);
        event.setMaterial(Bismuth).asMetal(544, 0, AntimatterMaterialTypes.PLATE).asOre(false);
        event.setMaterial(Carbon).asMetal(3800, 1000, AntimatterMaterialTypes.PLATE);
        event.setMaterial(Chromium).asMetal(2180, 1700, AntimatterMaterialTypes.PLATE);
        event.setMaterial(Iridium).asMetal(2719, 3000, AntimatterMaterialTypes.PLATE).asOre(3, 7, false).harvestLevel(3);
        event.setMaterial(Lead).asMetal(600, 0, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.PLATE_DENSE, AntimatterMaterialTypes.ROD).harvestLevel(2);
        event.setMaterial(Manganese).asMetal(1519, 0);
        event.setMaterial(Nickel).asMetal(1728, 0, AntimatterMaterialTypes.PLATE).asOre(false);
        event.setMaterial(Osmium).asMetal(3306, 3306, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD);
        event.setMaterial(Platinum).asMetal(2041, 0, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD).asOre(3, 7, false).harvestLevel(2);
        event.setMaterial(Plutonium).asMetal(912, 0);
        event.setMaterial(Plutonium239).asMetal(912, 0);
        event.setMaterial(Silver).asMetal(1234, 0, AntimatterMaterialTypes.PLATE).harvestLevel(2);
        event.setMaterial(Thorium).asMetal(2115, 0);
        event.setMaterial(Titanium).asMetal(1941, 1500,  AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.GEAR).addArmor(new int[]{3, 7, 7, 3}, 1.0F, 0.0F, 15).addTools(3.5F, 8.0F, 1600, 3);
        event.setMaterial(Tungsten).asMetal(3695, 2500, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD, FOIL).addTools(3.5F, 8.0F, 2560, 3);
        event.setMaterial(Uranium238).asMetal(1405, 0);
        event.setMaterial(Uranium235).asMetal(1405, 0);
        event.setMaterial(Antimony).asMetal(1449, 0);
        event.setMaterial(Argon).asGas();
        event.setMaterial(Calcium).asDust(1115);
        event.setMaterial(Cadmium).asDust(594);
        event.setMaterial(Chlorine).asGas();
        event.setMaterial(Deuterium).asGas();
        event.setMaterial(Fluorine).asGas();
        event.setMaterial(Hydrogen).asGas(15);
        event.setMaterial(Helium).asGas();
        event.setMaterial(Helium3).asGas();
        event.setMaterial(Lithium).asMetal(454, 0);
        event.setMaterial(Magnesium).asMetal(923, 0);
        event.setMaterial(Mercury).asFluid();
        event.setMaterial(Nitrogen).asGas();
        event.setMaterial(Oxygen).asGas();
        event.setMaterial(Phosphor).asDust(317);
        event.setMaterial(Potassium).asMetal(336, 0);
        event.setMaterial(Silicon).asMetal(1687, 1000, AntimatterMaterialTypes.PLATE);
        event.setMaterial(Sodium).asDust(370);
        event.setMaterial(Sulfur).asDust(388);
        event.setMaterial(Tin).asMetal(505, 505, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.GEAR).asOre(1, 5, false);
        event.setMaterial(Tritium).asGas();
        event.setMaterial(Zinc).asMetal(692, 0, AntimatterMaterialTypes.PLATE);
        event.setMaterial(Technetium).asMetal(AntimatterMaterialTypes.PLATE);
        event.setMaterial(Neon).asGas();

        //TODO: We can be more lenient about what fluids we have in, its not as bad as solids above, and we can stop them from showing in JEI (I think...)

        /** Gases **/
        event.setMaterial(WoodGas).asGas(25);
        event.setMaterial(Methane).asGas(128).mats(of(Carbon, 1, Hydrogen, 4));
        //event.setMaterial(Biogas).asGas(32);
        event.setMaterial(CarbonDioxide).asGas().mats(of(Carbon, 1, Oxygen, 2));
        //event.setMaterial(NobleGases).asGas()/*.setTemp(79, 0)*/.addComposition(of(CarbonDioxide, 21, Helium, 9, Methane, 3, Deuterium, 1));
        event.setMaterial(Air).asGas().mats(of(Nitrogen, 40, Oxygen, 11, Argon, 1/*, NobleGases, 1*/));
        event.setMaterial(NitrogenDioxide).asGas().mats(of(Nitrogen, 1, Oxygen, 2));
        event.setMaterial(NaturalGas).asGas(15);
        event.setMaterial(Propane).asGas(48).mats(of(Carbon, 2, Hydrogen, 6));
        event.setMaterial(SulfurDioxide).asGas().mats(of(Sulfur, 1, Oxygen, 2));
        event.setMaterial(SulfurTrioxide).asGas()/*.setTemp(344, 1)*/.mats(of(Sulfur, 1, Oxygen, 3));
        event.setMaterial(NitricOxide).asGas().mats(of(Nitrogen, 1, Oxygen, 1));

        /** Fluids **/
        event.setMaterial(Steam).asGas(0, 373);
        event.setMaterial(HotCoolant).asFluid(0, 1200);
        event.setMaterial(ColdCoolant).asFluid(0, 300);
        event.setMaterial(UUAmplifier).asFluid();
        event.setMaterial(UUMatter).asFluid();
        event.setMaterial(Lubricant).asFluid();
        //WoodTar = AntimatterAPI.register(Material.class, new Material(Ref.ID, "wood_tar", 0x28170b, NONE).asFluid(; TODO: not sure if needed
        event.setMaterial(DistilledWater).asFluid().mats(of(Hydrogen, 2, Oxygen, 1));
        event.setMaterial(Glyceryl).asFluid().mats(of(Carbon, 3, Hydrogen, 5, Nitrogen, 3, Oxygen, 9));
        event.setMaterial(SodiumPersulfate).asFluid().mats(of(Sodium, 1, Sulfur, 1, Oxygen, 4));
        event.setMaterial(NitricAcid).asFluid().mats(of(Hydrogen, 1, Nitrogen, 1, Oxygen, 3));
        //HydrochloricAcid = AntimatterAPI.register(Material.class, new Material(Ref.ID, "hydrochloric_acid", 0x6f8a91, NONE).asFluid().mats(of(Hydrogen, 1, Chlorine, 1));
        event.setMaterial(SulfuricAcid).asFluid().mats(of(Hydrogen, 2, Sulfur, 1, Oxygen, 4));
        event.setMaterial(NitroCarbon).asFluid().mats(of(Nitrogen, 1, Carbon, 1));
        event.setMaterial(Honey).asFluid();

        /** Fuels **/
        event.setMaterial(Diesel).asFluid(128);
        event.setMaterial(Gasoline).asFluid(128);
        event.setMaterial(NitroDiesel).asFluid(384);
        event.setMaterial(BioDiesel).asFluid(192);
        event.setMaterial(Biomass).asFluid(8);
        //Biofuel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "biofuel", 0x99cc00, NONE).asFluid(6);
        event.setMaterial(Ethanol).asFluid(128).mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 1));
        event.setMaterial(Creosote).asFluid(8);
        event.setMaterial(Naphtha).asFluid(128);
        event.setMaterial(NitroCoalFuel).asFluid(48);
        event.setMaterial(CoalFuel).asFluid(16);
        event.setMaterial(FishOil).asFluid(6);
        event.setMaterial(Oil).asFluid(15);
        event.setMaterial(SeedOil).asFluid(6);
        //public static Materials SeedOilHemp = new Materials(722, "Hemp Seed Oil", 196, 255, 0, lime, NONE).asSemi(2;
        //public static Materials SeedOilLin = new Materials(723, "Lin Seed Oil", 196, 255, 0, lime, NONE).asSemi(2;
        event.setMaterial(Glycerol).asFluid(160).mats(of(Carbon, 3, Hydrogen, 8, Oxygen, 3));

        /** Dusts **/
        event.setMaterial(SodiumSulfide).asDust().mats(of(Sodium, 2, Sulfur, 1));
        event.setMaterial(PlatinumGroupSludge).asDust();
        //Oilsands = AntimatterAPI.register(Material.class, new Material(Ref.ID, "oilsands", 0x0a0a0a, NONE).asDust(ORE);
        event.setMaterial(RareEarth).asDust();
        event.setMaterial(Almandine).asDust().mats(of(Aluminium, 2, AntimatterMaterials.Iron, 3, Silicon, 3, Oxygen, 12));
        event.setMaterial(Andradite).asDust().mats(of(Calcium, 3, AntimatterMaterials.Iron, 2, Silicon, 3, Oxygen, 12));
        event.setMaterial(Ash).asDust();
        event.setMaterial(Calcite).asDust().mats(of(Calcium, 1, Carbon, 1, Oxygen, 3));
        event.setMaterial(Cassiterite).asOre(1, 5, false).mats(of(Tin, 1, Oxygen, 2))
                .setDirectSmeltInto(Tin).setSmeltInto(Tin).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Chromite).asOre(1, 5, false).mats(of(AntimatterMaterials.Iron, 1, Chromium, 2, Oxygen, 4)).harvestLevel(3);
        event.setMaterial(Clay).asDust().mats(of(Sodium, 2, Lithium, 1, Aluminium, 2, Silicon, 2));
        event.setMaterial(DarkAsh).asDust().mats(of(Carbon, 2));
        event.setMaterial(Energium).asDust();
        event.setMaterial(Galena).asOre(1, 5, false).mats(of(Lead, 3, Silver, 3, Sulfur, 2)).harvestLevel(2);
        event.setMaterial(Grossular).asDust().mats(of(Calcium, 3, Aluminium, 2, Silicon, 3, Oxygen, 12));
        event.setMaterial(Magnesite).asDust().mats(of(Magnesium, 1, Carbon, 1, Oxygen, 3));
        event.setMaterial(Obsidian).asDust(PLATE).mats(of(Magnesium, 1, AntimatterMaterials.Iron, 1, Silicon, 2, Oxygen, 8));
        event.setMaterial(Phosphate).asDust().mats(of(Phosphor, 1, Oxygen, 4));
        event.setMaterial(Pyrite).asOre(1, 5,false).mats(of(AntimatterMaterials.Iron, 1, Sulfur, 2)).setOreMulti(2);
        event.setMaterial(Pyrope).asDust().mats(of(Aluminium, 2, Magnesium, 3, Silicon, 3, Oxygen, 12));
        event.setMaterial(Saltpeter).asDust().mats(of(Potassium, 1, Nitrogen, 1, Oxygen, 3));
        event.setMaterial(SiliconDioxide).asDust().mats(of(Silicon, 1, Oxygen, 2));
        event.setMaterial(SodiumHydroxide).asDust().mats(of(Sodium, 1, Oxygen, 1, Hydrogen, 1));
        event.setMaterial(Brick).asDust().mats(of(Aluminium, 4, Silicon, 3, Oxygen, 12));
        event.setMaterial(Fireclay).asDust().mats(of(Brick, 1));
        event.setMaterial(Spessartine).asDust().mats(of(Aluminium, 2, Manganese, 3, Silicon, 3, Oxygen, 12));
        event.setMaterial(Sphalerite).asOre(1, 5,false).mats(of(Zinc, 1, Sulfur, 1));
        event.setMaterial(Tetrahedrite).asOre(1, 5, false, MaterialTags.HAS_CUSTOM_SMELTING).mats(of(AntimatterMaterials.Copper, 3, Antimony, 1, Sulfur, 3, AntimatterMaterials.Iron, 1))
                .setDirectSmeltInto(AntimatterMaterials.Copper).setSmeltInto(AntimatterMaterials.Copper);
        event.setMaterial(Tungstate).asOre(1, 5, false).mats(of(Tungsten, 1, Lithium, 2, Oxygen, 4)).setOreMulti(2).harvestLevel(2);
        event.setMaterial(Uraninite).asOre(1, 5, false).mats(of(Uranium238, 1, Oxygen, 2)).harvestLevel(2);
        event.setMaterial(Bauxite).asOre(1, 5, false).mats(of(Aluminium, 16, Hydrogen, 10, Oxygen, 11, Titanium, 1)).setOreMulti(2);
        event.setMaterial(PotassiumFeldspar).asDust().mats(of(Potassium, 1, Aluminium, 1, Silicon, 3, Oxygen, 8));
        event.setMaterial(Biotite).asDust().mats(b -> b.put(Potassium, 1).put(Magnesium, 3).put(Aluminium, 3).put(Fluorine, 2).put(Silicon, 3).put(Oxygen, 10));
        event.setMaterial(Uvarovite).asDust().mats(of(Calcium, 3, Chromium, 2, Silicon, 3, Oxygen, 12));

        /** Gems **/
        //Brittle Gems
        event.setMaterial(CoalCoke).asGemBasic(false);

        event.setMaterial(Lazurite).asDust().mats(of(Aluminium, 3, Silicon, 3, Calcium, 4, Sodium, 4));
        event.setMaterial(Ruby).asGemBasic(false).asOre(3, 7,true).addTools(3.0F, 7.0F, 1024, 3).addArmor(new int[]{3, 7, 7, 3}, 1.0F, 0.0F, 30).mats(of(Chromium, 1, Aluminium, 2, Oxygen, 3));
        event.setMaterial(Sapphire).asGemBasic(false).asOre(3, 7,true).addTools(3.0F, 7.0F, 1024, 3).addArmor(new int[]{3, 7, 7, 3}, 1.0F, 0.0F, 30).mats(of(Aluminium, 2, Oxygen, 3));
        event.setMaterial(Sodalite).asGemBasic(false).asOre(1, 5,false).mats(of(Aluminium, 3, Silicon, 3, Sodium, 4, Chlorine, 1)).setOreMulti(6).harvestLevel(2);
        //Glass = AntimatterAPI.register(Material.class, new Material(Ref.ID, "glass", 0xfafafa, SHINY)).asDust(PLATE, LENS).mats(of(SiliconDioxide, 1));
        event.setMaterial(Olivine).asGemBasic(false).asOre(3, 7,true).mats(of(Magnesium, 2, AntimatterMaterials.Iron, 1, Silicon, 1, Oxygen, 4)).harvestLevel(3);
        //Phosphorus = AntimatterAPI.register(Material.class, new Material(Ref.ID, "phosphorus", 0xffff00, FLINT).asDust().mats(of(Calcium, 3, Phosphate, 2));
        event.setMaterial(RedGarnet).asGemBasic(false).mats(of(Pyrope, 3, Almandine, 5, Spessartine, 8));
        event.setMaterial(YellowGarnet).asGemBasic(false).mats(of(Uvarovite, 3, Andradite, 5, Grossular, 8));

        event.setMaterial(Amethyst).asGemBasic(false).asOre(3, 7,true).addTools(3.0F, 7.0F, 1024, 3).addArmor(new int[]{3, 7, 7, 3}, 1.0F, 0.0F, 30).mats(of(SiliconDioxide, 4, AntimatterMaterials.Iron, 1));

        /** **/
        event.setMaterial(Cinnabar).asDust().asOre(1, 5,false).mats(of(Mercury, 1, Sulfur, 1)).setOreMulti(2).harvestLevel(2);

        /** Metals **/
        event.setMaterial(BatteryAlloy).asMetal(295, 0, AntimatterMaterialTypes.PLATE).mats(of(Lead, 4, Antimony, 1));
        event.setMaterial(Brass).asMetal(1170, 0,  AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD).mats(of(Zinc, 1, AntimatterMaterials.Copper, 3));
        event.setMaterial(Bronze).asMetal(1125, 0,  AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.GEAR).mats(of(Tin, 1, AntimatterMaterials.Copper, 3)).addTools(2.0F, 6.0F, 192, 2,  of(Enchantments.SHARPNESS, 1)).addArmor(new int[]{2, 6, 5, 2}, 0.0F, 0.0F, 12);
        event.setMaterial(Cupronickel).asMetal(1728, 0, AntimatterMaterialTypes.PLATE).mats(of(AntimatterMaterials.Copper, 1, Nickel, 1));
        event.setMaterial(Electrum).asMetal(1330, 0, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD).mats(of(Silver, 1, AntimatterMaterials.Gold, 1));
        event.setMaterial(Invar).asMetal(1700, 0,  AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD).mats(of(AntimatterMaterials.Iron, 2, Nickel, 1)).addTools(AntimatterMaterials.Iron, of(Enchantments.BANE_OF_ARTHROPODS, 3)).addArmor(new int[]{2, 6, 5, 2}, 0.0F, 0.0F, 15, of(Enchantments.FIRE_PROTECTION, 1));
        event.setMaterial(Kanthal).asMetal(1800, 2200,  AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD).mats(of(AntimatterMaterials.Iron, 1, Aluminium, 1, Chromium, 1));
        event.setMaterial(Magnalium).asMetal(870, 0,  AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD).mats(of(Magnesium, 1, Aluminium, 2));
        event.setMaterial(Nichrome).asMetal(2700, 2500).mats(of(Nickel, 4, Chromium, 1));
        event.setMaterial(SolderingAlloy).asMetal(400, 400, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD).mats(of(Tin, 9, Antimony, 1));
        event.setMaterial(Steel).asMetal(1811, 1500, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.GEAR, AntimatterMaterialTypes.RING).addTools(2.5F, 6.0F, 512, 2,  of(Enchantments.SHARPNESS, 2)).addArmor(new int[]{2, 7, 6, 2}, 1.0F, 0.0F, 21, of(Enchantments.ALL_DAMAGE_PROTECTION, 1)).mats(of(AntimatterMaterials.Iron, 50, Carbon, 1));
        event.setMaterial(StainlessSteel).asMetal(1700, 1700, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.GEAR).mats(of(AntimatterMaterials.Iron, 6, Chromium, 1, Manganese, 1, Nickel, 1)).addTools(2.5F, 7.0F, 480, 2, of(Enchantments.SHARPNESS, 3)).addArmor(new int[]{2, 7, 6, 2}, 2.0F, 0.0F, 20, of(Enchantments.ALL_DAMAGE_PROTECTION, 2));
        event.setMaterial(WroughtIron).asMetal(1811, 0, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.GEAR).mats(of(AntimatterMaterials.Iron, 1)).addTools(IRON.getAttackDamageBonus(), IRON.getSpeed(), (int)(256 * 1.5F), IRON.getLevel(),  of(Enchantments.SHARPNESS, 2)).addArmor(new int[]{2, 6, 5, 2}, 1.0F, 0.0F, 17, of(Enchantments.ALL_DAMAGE_PROTECTION, 1));
        event.setMaterial(TungstenSteel).asMetal(3000, 3000, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.GEAR).addTools(6.0F, 10.0F, 5120, 4).mats(of(Steel, 1, Tungsten, 1)).addArmor(new int[]{3, 8, 8, 3}, 3.0F, 0.0F, 18, of(Enchantments.ALL_DAMAGE_PROTECTION, 3));
        event.setMaterial(TungstenCarbide).asMetal(2460, 2460).addTools(5.0F, 14.0F, 2560, 4, of(Enchantments.SHARPNESS, 5)).mats(of(Tungsten, 1, Carbon, 1));
        event.setMaterial(RedAlloy).asMetal(295, 0, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD).mats(of(AntimatterMaterials.Copper, 1, AntimatterMaterials.Redstone, 4));
        event.setMaterial(Osmiridium).asMetal(3333, 3300,  AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD).mats(of(Iridium, 1, Osmium, 1));
        event.setMaterial(IronMagnetic).asMetal(1811, 0, AntimatterMaterialTypes.ROD).addTools(AntimatterMaterials.Iron).mats(of(AntimatterMaterials.Iron, 1));
        event.setMaterial(SteelMagnetic).asMetal(1000, 1000, AntimatterMaterialTypes.ROD).addTools(Steel).mats(of(Steel, 1));

        /** TFC Materials **/
        event.setMaterial(SterlingSilver).asMetal(1700, 1700).mats(of(AntimatterMaterials.Copper, 1, Silver, 4));
        event.setMaterial(RoseGold).asMetal(1600, 1600).mats(of(AntimatterMaterials.Copper, 1, AntimatterMaterials.Gold, 4));
        event.setMaterial(BlackBronze).asMetal(2000, 2000).addTools(Bronze, of(Enchantments.SWEEPING_EDGE, 1)).mats(of(AntimatterMaterials.Gold, 1, Silver, 1, AntimatterMaterials.Copper, 3));
        event.setMaterial(BismuthBronze).asMetal(1100, 900, AntimatterMaterialTypes.PLATE).addTools(2.5F, MaterialTags.TOOLS.get(Bronze).toolSpeed() + 2.0F, 350, 2, of(Enchantments.BANE_OF_ARTHROPODS, 4)).mats(of(Bismuth, 1, Zinc, 1, AntimatterMaterials.Copper, 3));
        event.setMaterial(BlackSteel).asMetal(1200, 1200, AntimatterMaterialTypes.FRAME, AntimatterMaterialTypes.PLATE).addTools(3.5F, 6.5F, 768, 2).mats(of(Nickel, 1, BlackBronze, 1, Steel, 3));
        event.setMaterial(RedSteel).asMetal(1300, 1300).addTools(3.5F, 7.0F, 896, 2).mats(of(SterlingSilver, 1, BismuthBronze, 1, Steel, 2, BlackSteel, 4));
        event.setMaterial(BlueSteel).asMetal(1400, 1400, AntimatterMaterialTypes.FRAME).addTools(3.5F, 7.5F, 1024, 2).mats(of(RoseGold, 1, Brass, 1, Steel, 2, BlackSteel, 4));

        /** Solids (Plastic Related Stuff)**/
        event.setMaterial(Plastic).asSolid(295, 0, AntimatterMaterialTypes.PLATE, RUBBERTOOLS, NOSMASH, FOIL).addTools(0.0F, 0.0F, 64, 0, of(), AntimatterDefaultTools.SOFT_HAMMER).addHandleStat(66, 0.5F).mats(of(Carbon, 1, Hydrogen, 2));
        event.setMaterial(Rubber).asSolid(295, 0, AntimatterMaterialTypes.PLATE, RUBBERTOOLS, NOSMASH).addTools(0.0F, 0.0F, 64, 0, of(), AntimatterDefaultTools.SOFT_HAMMER).addHandleStat(11, 0.4F).mats(of(Carbon, 5, Hydrogen, 8));

        /** Stones **/
        event.setMaterial(RedGranite).asDust(AntimatterMaterialTypes.ROCK).addHandleStat(74, 1.0F, of(Enchantments.UNBREAKING, 1)).mats(of(Aluminium, 2, PotassiumFeldspar, 1, Oxygen, 3));
        event.setMaterial(BlackGranite).asDust(AntimatterMaterialTypes.ROCK).addHandleStat(74, 1.0F, of(Enchantments.UNBREAKING, 1)).mats(of(SiliconDioxide, 4, Biotite, 1));
        event.setMaterial(Marble).asDust(AntimatterMaterialTypes.ROCK).mats(of(Magnesium, 1, Calcite, 7));
        event.setMaterial(Komatiite).asDust(AntimatterMaterialTypes.ROCK).mats(of(Olivine, 1, Magnesite, 2, AntimatterMaterials.Flint, 6, DarkAsh, 3));
        event.setMaterial(Limestone).asDust(AntimatterMaterialTypes.ROCK).mats(of(Calcite, 1));
        event.setMaterial(GreenSchist).asDust(AntimatterMaterialTypes.ROCK);
        event.setMaterial(BlueSchist).asDust(AntimatterMaterialTypes.ROCK);
        event.setMaterial(Kimberlite).asDust(AntimatterMaterialTypes.ROCK);
        event.setMaterial(Quartzite).mats(of(Silicon, 1, Oxygen, 2));

        event.setMaterial(Scoria).asDust().mats(of(SiliconDioxide, 6, Calcium, 1, Carbon, 1, AntimatterMaterials.Iron, 1));

        /** Ore Stones **/
        event.setMaterial(Salt).asDust(AntimatterMaterialTypes.ORE_STONE).setExpRange(1, 5).mats(of(Sodium, 1, Chlorine, 1));
        event.setMaterial(RockSalt).asDust(AntimatterMaterialTypes.ORE_STONE).setExpRange(1, 5).mats(of(Potassium, 1, Chlorine, 1));


        event.setMaterial(AntimatterMaterials.Coal);

        //WroughtIron.setSmeltInto(Iron).setMacerateInto(Iron);

        //Cinnabar.setDirectSmeltInto(Mercury);

        HotCoolant.remove(MaterialTags.MOLTEN);
    }

    private static void byProducts(GT4RMaterialEvent event){
        // ore byproducts
        // eligible: cinnabar, uranium, copper, cassiterite, pyrite, sodalite-special:4x byproduct, sphalerite, tetra, bauxite 4x bypoduct, lead, tin, galena, iron, gold, platinum aka sheldonite, nickel, tungstate
        // gems & iridium will be separate redstone, lapis
        event.setMaterial(Iridium).addByProduct(Platinum, Osmium);
        event.setMaterial(Uraninite).addByProduct(Lead, Uranium238, Thorium);
        event.setMaterial(Tin).addByProduct(AntimatterMaterials.Iron, Zinc);
        event.setMaterial(Cassiterite).addByProduct(Tin);
        event.setMaterial(Chromite).addByProduct(AntimatterMaterials.Iron, Magnesium, Chromium);
        event.setMaterial(Galena).addByProduct(Sulfur, Silver, Lead);
        event.setMaterial(Pyrite).addByProduct(Sulfur, Phosphor, AntimatterMaterials.Iron);
        event.setMaterial(Sphalerite).addByProduct(Zinc, YellowGarnet, Cadmium);
        event.setMaterial(Tetrahedrite).addByProduct(Antimony, Zinc);
        event.setMaterial(Tungstate).addByProduct(Manganese, Silver, Lithium);
        event.setMaterial(Ruby).addByProduct(Chromium, RedGarnet);
        event.setMaterial(Sapphire).addByProduct(Aluminium, Sapphire);
        event.setMaterial(Sodalite).addByProduct(Lazurite, AntimatterMaterials.Lapis);
        event.setMaterial(Olivine).addByProduct(Pyrope, Magnesium);
        event.setMaterial(Cinnabar).addByProduct(AntimatterMaterials.Redstone, Sulfur, AntimatterMaterials.Glowstone);
        event.setMaterial(Bauxite).addByProduct(Grossular, Titanium);
        event.setMaterial(Platinum).addByProduct(Nickel, Iridium);


        //ore byproducts from non vanilla/gt4r ores
        event.setMaterial(Nickel).addByProduct(AntimatterMaterials.Iron, Platinum);
        event.setMaterial(Silver).addByProduct(Lead, Sulfur);
        event.setMaterial(Lead).addByProduct(Silver, Sulfur);
        event.setMaterial(Zinc).addByProduct(Tin);
        event.setMaterial(RedGarnet).addByProduct(Spessartine, Pyrope, Almandine);
        event.setMaterial(YellowGarnet).addByProduct(Andradite, Grossular);
        event.setMaterial(Tungsten).addByProduct(Manganese);
        event.setMaterial(Thorium).addByProduct(Uranium238, Lead);

        //other byproducts
        event.setMaterial(Andradite).addByProduct(YellowGarnet, AntimatterMaterials.Iron);
        event.setMaterial(AntimatterMaterials.Glowstone).addByProduct(AntimatterMaterials.Redstone, AntimatterMaterials.Gold);
        event.setMaterial(Antimony).addByProduct(Zinc, AntimatterMaterials.Iron);
        event.setMaterial(Plutonium).addByProduct(Uranium238, Lead);
        event.setMaterial(Electrum).addByProduct(AntimatterMaterials.Gold, Silver);
        event.setMaterial(Bronze).addByProduct(AntimatterMaterials.Copper, Tin);
        event.setMaterial(Brass).addByProduct(AntimatterMaterials.Copper, Zinc);
        event.setMaterial(Manganese).addByProduct(Chromium, AntimatterMaterials.Iron);
        event.setMaterial(Chromium).addByProduct(AntimatterMaterials.Iron, Magnesium);
        event.setMaterial(Pyrope).addByProduct(RedGarnet, Magnesium);
        event.setMaterial(Almandine).addByProduct(RedGarnet, Aluminium);
        event.setMaterial(Spessartine).addByProduct(RedGarnet, Manganese);
        event.setMaterial(Grossular).addByProduct(YellowGarnet, Calcium);
        event.setMaterial(Calcite).addByProduct(Andradite);
        event.setMaterial(Beryllium).addByProduct(AntimatterMaterials.Emerald);
        event.setMaterial(Steel).addByProduct(AntimatterMaterials.Iron);
        event.setMaterial(Sulfur).addByProduct(Sulfur);
        event.setMaterial(Saltpeter).addByProduct(Saltpeter);
        event.setMaterial(Osmium).addByProduct(Iridium);
        event.setMaterial(Magnesium).addByProduct(Olivine);
        event.setMaterial(Aluminium).addByProduct(Bauxite);
        event.setMaterial(Titanium).addByProduct(Almandine);
        event.setMaterial(Obsidian).addByProduct(Olivine);
        event.setMaterial(Ash).addByProduct(Carbon);
        event.setMaterial(DarkAsh).addByProduct(Carbon);
        event.setMaterial(Marble).addByProduct(Calcite);
        event.setMaterial(Clay).addByProduct(Clay);
        event.setMaterial(Phosphate).addByProduct(Phosphor);
        event.setMaterial(Phosphor).addByProduct(Phosphate);
        event.setMaterial(Lithium).addByProduct(Lithium);
        event.setMaterial(Silicon).addByProduct(SiliconDioxide);
    }

    private static void flags(MaterialEvent event){
        GT4RMaterialTags.BATHING_PERSULFATE.add(AntimatterMaterials.Copper, AntimatterMaterials.Copper);
        GT4RMaterialTags.BATHING_PERSULFATE.add(AntimatterMaterials.Gold, AntimatterMaterials.Copper);
        GT4RMaterialTags.BATHING_PERSULFATE.add(AntimatterMaterials.Iron, Nickel);
        GT4RMaterialTags.BATHING_PERSULFATE.add(Sphalerite, Zinc);
        GT4RMaterialTags.BATHING_PERSULFATE.add(Tetrahedrite, Tetrahedrite);
        GT4RMaterialTags.BATHING_PERSULFATE.add(Tin, Zinc);
        GT4RMaterialTags.BATHING_PERSULFATE.add(Platinum, Nickel);
        GT4RMaterialTags.BATHING_MERCURY.add(Galena, Silver);
        GT4RMaterialTags.BATHING_MERCURY.add(Tungstate, Silver);
        GT4RMaterialTags.BATHING_MERCURY.add(AntimatterMaterials.Gold, AntimatterMaterials.Gold);
        GT4RMaterialTags.BATHING_MERCURY.add(Iridium, Platinum);
        GT4RMaterialTags.BATHING_MERCURY.add(AntimatterMaterials.Copper, AntimatterMaterials.Gold);
        GT4RMaterialTags.BATHING_MERCURY.add(Platinum, Platinum);
        GT4RMaterialTags.ELEC30.add(AntimatterMaterials.Charcoal, AntimatterMaterials.Prismarine, Salt, RockSalt, Quartzite);
        GT4RMaterialTags.ELEC60.add(Cassiterite, SodiumSulfide, Sapphire, SiliconDioxide, Methane, Pyrite, Sphalerite, NitrogenDioxide, Phosphate, Magnesite);
        GT4RMaterialTags.ELEC90.add(Calcite, AntimatterMaterials.EnderPearl, SulfuricAcid, RedGranite, Saltpeter, Chromite, SodiumPersulfate, Glyceryl, Ruby, Olivine, Galena, Tungstate/* CalciumCarbonate*/);
        GT4RMaterialTags.ELEC120.add(AntimatterMaterials.Emerald, Grossular, Clay, StainlessSteel, Sodalite, Bauxite, Obsidian, Pyrope, Uvarovite, Almandine, Andradite, Lazurite, Spessartine, PotassiumFeldspar, Biotite);
        GT4RMaterialTags.ROCK_CUTTER.add(AntimatterMaterials.Diamond, Ruby, Sapphire, AntimatterMaterials.NetherizedDiamond, Amethyst);

        GT4RMaterialTags.CABINET.add(AntimatterMaterials.Iron, Aluminium, WroughtIron, Brass);
        GT4RMaterialTags.CABINET.all().forEach(m -> event.setMaterial(m).flags(AntimatterMaterialTypes.ROD));
        GT4RMaterialTags.DRUM.add(Bronze, Steel, StainlessSteel, Invar, Tungsten, TungstenSteel, AntimatterMaterials.Netherite);
        GT4RMaterialTags.DRUM.all().forEach(m -> event.setMaterial(m).flags(AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.PLATE));
        GT4RMaterialTags.WORKBENCH.add(Bronze, AntimatterMaterials.Iron, Aluminium);
        GT4RMaterialTags.CHARGING_WORKBENCH.add(AntimatterMaterials.Iron, Aluminium);
        GT4RMaterialTags.LOCKER.add(AntimatterMaterials.Iron, Aluminium);
        GT4RMaterialTags.CHARGING_LOCKER.add(AntimatterMaterials.Iron, Aluminium);
        GT4RMaterialTags.SEMIFLUID.add(Biomass, Creosote, FishOil, Oil, SeedOil);
        MaterialTags.FLINT.add(AntimatterMaterials.Flint);

        ELEC.add(GT4RMaterialTags.ELEC30.all().toArray(new Material[0]));
        ELEC.add(GT4RMaterialTags.ELEC60.all().toArray(new Material[0]));
        ELEC.add(GT4RMaterialTags.ELEC90.all().toArray(new Material[0]));
        ELEC.add(GT4RMaterialTags.ELEC120.all().toArray(new Material[0]));
    }

    private static void antimatterMaterials(GT4RMaterialEvent event){
        event.setMaterial(AntimatterMaterials.Redstone).mats(of(Silicon, 1, Pyrite, 5, Ruby, 1, Mercury, 3)).setOreMulti(5).setSmeltingMulti(5)
                .addByProduct(Cinnabar, RareEarth, AntimatterMaterials.Glowstone);
        event.setMaterial(AntimatterMaterials.Prismarine).mats(of(Potassium, 2, Oxygen, 8, Manganese, 1, Silicon, 5));
        event.setMaterial(AntimatterMaterials.Basalt).mats(of(Olivine, 1, Calcite, 3, AntimatterMaterials.Flint, 8, DarkAsh, 4));
        event.setMaterial(AntimatterMaterials.Lapis).mats(of(Lazurite, 12, Sodalite, 2, Pyrite, 1, Calcite, 1)).setOreMulti(6)
                .addByProduct(Lazurite, Sodalite, Pyrite);
        event.setMaterial(AntimatterMaterials.EnderEye).mats(of(AntimatterMaterials.EnderPearl, 1, AntimatterMaterials.Blaze, 1));
        event.setMaterial(AntimatterMaterials.EnderPearl).mats(of(Beryllium, 1, Potassium, 4, Nitrogen, 5, Chlorine, 6));
        event.setMaterial(AntimatterMaterials.Diamond).mats(of(Carbon, 128)).addByProduct(Carbon/*Graphite*/);
        event.setMaterial(AntimatterMaterials.Emerald).mats(of(Beryllium, 3, Aluminium, 2, Silicon, 3, Oxygen, 18)).addByProduct(Beryllium, Aluminium);
        event.setMaterial(AntimatterMaterials.Coal).mats(of(Carbon, 2));
        event.setMaterial(AntimatterMaterials.Iron).flags(AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD).addByProduct(Nickel, Tin);
        event.setMaterial(AntimatterMaterials.Gold).flags(AntimatterMaterialTypes.GEAR).addByProduct(AntimatterMaterials.Copper, Nickel);
        event.setMaterial(AntimatterMaterials.Copper).flags(AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.GEAR).addByProduct(AntimatterMaterials.Gold, Nickel);
        event.setMaterial(AntimatterMaterials.Water).mats(of(Hydrogen, 2, Oxygen, 1));
        event.setMaterial(AntimatterMaterials.Flint).setAllowedTypes(AntimatterDefaultTools.PICKAXE, AntimatterDefaultTools.AXE, AntimatterDefaultTools.SHOVEL, AntimatterDefaultTools.SWORD, AntimatterDefaultTools.HOE, AntimatterDefaultTools.MORTAR, AntimatterDefaultTools.KNIFE).mats(of(SiliconDioxide, 1));
        event.setMaterial(AntimatterMaterials.Wood).mats(of(Carbon, 1, Oxygen, 1, Hydrogen, 1));
        event.setMaterial(AntimatterMaterials.Blaze).mats(of(Sulfur, 1, DarkAsh, 1/*, Magic, 1*/));
        event.setMaterial(AntimatterMaterials.Charcoal).mats(of(Carbon, 1));
        event.setMaterial(AntimatterMaterials.Lava).mats(of(AntimatterMaterials.Copper, 1, Tin, 1, AntimatterMaterials.Gold, 1, Silver, 1, Tungsten, 1));
        event.setMaterial(AntimatterMaterials.Granite).mats(of(Aluminium, 2, AntimatterMaterials.Flint, 1, Clay, 1));
        event.setMaterial(AntimatterMaterials.Glowstone).mats(of(AntimatterMaterials.Redstone, 8, AntimatterMaterials.Gold, 8, Helium, 1));
        event.setMaterial(AntimatterMaterials.Diorite).mats(of(Nickel, 1));
        event.setMaterial(AntimatterMaterials.Netherite).asMetal(2246, 1300);
        event.setMaterial(AntimatterMaterials.NetheriteScrap).addByProduct(AntimatterMaterials.Quartz);
        event.setMaterial(AntimatterMaterials.Basalt).addByProduct(Olivine, DarkAsh);
        event.setMaterial(AntimatterMaterials.Netherrack).addByProduct(Sulfur);
        event.setMaterial(AntimatterMaterials.Flint).addByProduct(Obsidian);
        event.setMaterial(AntimatterMaterials.Endstone).addByProduct(Helium3);
    }
}
