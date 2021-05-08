package trinsdar.gt4r.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Data;
import muramasa.antimatter.fluid.AntimatterMaterialFluid;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialItem;
import muramasa.antimatter.material.MaterialTag;
import muramasa.antimatter.material.MaterialType;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.antimatter.util.Utils;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.Items;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.items.ItemTurbineRotor;
import trinsdar.gt4r.items.MaterialSpear;

import java.util.Properties;
import java.util.function.Supplier;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.material.Element.*;
import static muramasa.antimatter.material.MaterialTag.ELEC;
import static muramasa.antimatter.material.MaterialTag.RUBBERTOOLS;
import static muramasa.antimatter.material.TextureSet.NONE;
import static net.minecraft.item.ItemTier.IRON;
import static net.minecraft.item.ItemTier.NETHERITE;
import static trinsdar.gt4r.data.Textures.*;

public class Materials {

    public static MaterialTag ELEC30 = new MaterialTag("elec30");
    public static MaterialTag ELEC60 = new MaterialTag("elec60");
    public static MaterialTag ELEC90 = new MaterialTag("elec90");
    public static MaterialTag ELEC120 = new MaterialTag("elec120");

    public static MaterialTypeItem<?> HULL = new MaterialTypeItem<>("hull", 2, true, muramasa.antimatter.Ref.U * 8);
    public static MaterialTypeItem<?> TURBINE_BLADE = new MaterialTypeItem<>("turbine_blade", 1, true, muramasa.antimatter.Ref.U * 3);//.unSplitName();
    public static MaterialTypeItem<?> TURBINE_ROTOR = new MaterialTypeItem<>("turbine_rotor", 1, true, muramasa.antimatter.Ref.U * 17, new MaterialTypeItem.ItemSupplier() {
        @Override
        public MaterialItem supply(String domain, MaterialType<?> type, Material material) {
            return new ItemTurbineRotor(domain, type, material, new Item.Properties().defaultMaxDamage(getMaxDamage(material)).group(muramasa.antimatter.Ref.TAB_MATERIALS));
        }

        private int getMaxDamage(Material material){
            int d = 10000;
            if (material == Bronze){
                d = 15000;
            }
            if (material == TungstenSteel){
                d = 30000;
            }
            if (material == Carbon){
                d = 2500;
            }
            if (material == Osmium){
                d = 60000;
            }
            if (material == Osmiridium){
                d = 120000;
            }
            return d;
        }
    });
    public static MaterialTag FLINT_TAG = new MaterialTag("flint");
    public static MaterialTag SEMIFLUID = new MaterialTag("semifluid");
    public static AntimatterToolType SPEAR = new AntimatterToolType(Ref.ID, "spear", 2, 1, 10, 3.0F, -2.9F){
        @Override
        public IAntimatterTool instantiateTools(String domain) {
            return new MaterialSpear(domain, this, prepareInstantiation(domain));
        }

        @Override
        public IAntimatterTool instantiateTools(String domain, Supplier<Item.Properties> properties) {
            return new MaterialSpear(domain, this, properties.get());
        }

        private Item.Properties prepareInstantiation(String domain) {
            if (domain.isEmpty()) Utils.onInvalidData("An AntimatterToolType was instantiated with an empty domain name!");
            Item.Properties properties = new Item.Properties().group(getItemGroup());
            if (!getRepairability()) properties.setNoRepair();
            return properties;
        }
    };


    /** Elements **/
    public static Material Aluminium = new Material(Ref.ID, "aluminium", 0x80c8f0, DULL, Al).asMetal(933, 1000, PLATE, ROD, GEAR, HULL);
    public static Material Beryllium = new Material(Ref.ID, "beryllium", 0x64b464, METALLIC, Be).asMetal(1560, 0, PLATE);
    public static Material Carbon = new Material(Ref.ID, "carbon", 0x141414, DULL, C).asDust(TURBINE_BLADE, PLATE, TURBINE_ROTOR);
    public static Material Chrome = new Material(Ref.ID, "chrome", 0xffe6e6, SHINY, Cr).asMetal(2180, 1700, PLATE);
    public static Material Gold = new Material(Ref.ID, "gold", 0xffe650, SHINY, Au).asMetal(1337, 0, ROD, GEAR, ORE,  PLATE, ROD).harvestLevel(2);
    public static Material Iridium = new Material(Ref.ID, "iridium", 0xf0f0f5, DULL, Ir).asMetal(2719, 3000, PLATE, ORE).harvestLevel(3);
    public static Material Iron = new Material(Ref.ID, "iron", 0xc8c8c8, METALLIC, Fe).asMetal(1811, 500, ORE, PLATE, ROD).asPlasma().addTools(IRON.getAttackDamage(), IRON.getEfficiency(), IRON.getMaxUses(), IRON.getHarvestLevel(),  of(Enchantments.SHARPNESS, 1));
    public static Material Lead = new Material(Ref.ID, "lead", 0x8c648c, DULL, Pb).asMetal(600, 0, PLATE, PLATE_DENSE, ROD).harvestLevel(2);
    public static Material Manganese = new Material(Ref.ID, "manganese", 0xfafafa, DULL, Mn).asMetal(1519, 0);
    public static Material Nickel = new Material(Ref.ID, "nickel", 0xc8c8fa, METALLIC, Ni).asMetal(1728, 0, PLATE).asPlasma();
    public static Material Osmium = new Material(Ref.ID, "osmium", 0x3232ff, METALLIC, Os).asMetal(3306, 3306, PLATE, ROD, TURBINE_BLADE, TURBINE_ROTOR);
    public static Material Platinum = new Material(Ref.ID, "platinum", 0xffffc8, SHINY, Pt).asMetal(2041, 0, PLATE, ROD, ORE).harvestLevel(2);
    public static Material Plutonium = new Material(Ref.ID, "plutonium_244", 0xf03232, METALLIC, Pu).asMetal(912, 0);
    public static Material Silver = new Material(Ref.ID, "silver", 0xdcdcff, SHINY, Ag).asMetal(1234, 0, PLATE).harvestLevel(2);
    public static Material Thorium = new Material(Ref.ID, "thorium", 0x001e00, SHINY, Th).asMetal(2115, 0);
    public static Material Titanium = new Material(Ref.ID, "titanium", 0xdca0f0, METALLIC, Ti).asMetal(1941, 1500,  PLATE, ROD, GEAR, HULL).addArmor(new int[]{1, 1, 2, 1}, 1.0F, 0.0F, 15).addTools(3.5F, 8.0F, 1600, 3);
    public static Material Tungsten = new Material(Ref.ID, "tungsten", 0x323232, METALLIC, W).asMetal(3695, 2500, PLATE, ROD).addTools(3.5F, 8.0F, 2560, 3);
    public static Material Uranium238 = new Material(Ref.ID, "uranium_238", 0x32f032, METALLIC, U).asMetal(1405, 0);
    public static Material Uranium235 = new Material(Ref.ID, "uranium_235", 0x46fa46, METALLIC, U235).asMetal(1405, 0);
    public static Material Antimony = new Material(Ref.ID, "antimony", 0xdcdcf0, SHINY, Sb).asMetal(1449, 0);
    public static Material Argon = new Material(Ref.ID, "argon", 0xff00f0, NONE, Ar).asGas();
    public static Material Calcium = new Material(Ref.ID, "calcium", 0xfff5f5, METALLIC, Ca).asDust(1115);
    public static Material Cadmium = new Material(Ref.ID, "cadmium", 0x32323c, SHINY, Cd).asDust(594);
    public static Material Chlorine = new Material(Ref.ID, "chlorine", 0xffffff, NONE, Cr).asGas();
    public static Material Copper = new Material(Ref.ID, "copper", 0xff6400, SHINY, Cu).asMetal(1357, 0, PLATE, ROD, GEAR, ORE);
    public static Material Deuterium = new Material(Ref.ID, "deuterium", 0xffff00, NONE, D).asGas();
    public static Material Fluorine = new Material(Ref.ID, "fluorine", 0xffffff, NONE, F).asGas();
    public static Material Hydrogen = new Material(Ref.ID, "hydrogen", 0x0000ff, NONE, H).asGas(15);
    public static Material Helium = new Material(Ref.ID, "helium", 0xffff00, NONE, He).asPlasma();
    public static Material Helium3 = new Material(Ref.ID, "helium_3", 0xffffff, NONE, He_3).asGas();
    public static Material Lithium = new Material(Ref.ID, "lithium", 0xe1dcff, DULL, Li).asSolid(454, 0);
    public static Material Magnesium = new Material(Ref.ID, "magnesium", 0xffc8c8, METALLIC, Mg).asMetal(923, 0);
    public static Material Mercury = new Material(Ref.ID, "mercury", 0xffdcdc, SHINY, Hg).asFluid();
    public static Material Nitrogen = new Material(Ref.ID, "nitrogen", 0x0096c8, NONE, N).asPlasma();
    public static Material Oxygen = new Material(Ref.ID, "oxygen", 0x0064c8, NONE, O).asPlasma();
    public static Material Phosphor = new Material(Ref.ID, "phosphor", 0xffff00, DULL, P).asDust(317);
    public static Material Potassium = new Material(Ref.ID, "potassium", 0xfafafa, METALLIC, K).asSolid(336, 0);
    public static Material Silicon = new Material(Ref.ID, "silicon", 0x3c3c50, METALLIC, Si).asMetal(1687, 1000, PLATE);
    public static Material Sodium = new Material(Ref.ID, "sodium", 0x000096, METALLIC, Na).asDust(370);
    public static Material Sulfur = new Material(Ref.ID, "sulfur", 0xc8c800, DULL, S).asDust(388).asPlasma();
    public static Material Tin = new Material(Ref.ID, "tin", 0xdcdcdc, DULL, Sn).asMetal(505, 505, PLATE, ROD, GEAR, ORE);
    public static Material Tritium = new Material(Ref.ID, "tritium", 0xff0000, METALLIC, T).asGas();
    public static Material Zinc = new Material(Ref.ID, "zinc", 0xfaf0f0, METALLIC, Zn).asMetal(692, 0, PLATE);
    public static Material Technetium = new Material(Ref.ID, "technetium", 0xC8C8C8, METALLIC, Tc).asMetal(PLATE);
    public static Material Neon = new Material(Ref.ID, "neon", 0xFF6464, NONE, Ne).asGas();

    //TODO: We can be more lenient about what fluids we have in, its not as bad as solids above, and we can stop them from showing in JEI (I think...)

    /** Gases **/
    public static Material WoodGas = new Material(Ref.ID, "wood_gas", 0xdecd87, NONE).asGas(25);
    public static Material Methane = new Material(Ref.ID, "methane", 0xffffff, NONE).asGas(48).mats(of(Carbon, 1, Hydrogen, 4));
    public static Material CarbonDioxide = new Material(Ref.ID, "carbon_dioxide", 0xa9d0f5, NONE).asGas().mats(of(Carbon, 1, Oxygen, 2));
    //public static Material NobleGases = new Material(Ref.ID, "noble_gases", 0xc9e3fc, NONE).asGas()/*.setTemp(79, 0)*/.addComposition(of(CarbonDioxide, 21, Helium, 9, Methane, 3, Deuterium, 1));
    public static Material Air = new Material(Ref.ID, "air", 0xc9e3fc, NONE).asGas().mats(of(Nitrogen, 40, Oxygen, 11, Argon, 1/*, NobleGases, 1*/));
    public static Material NitrogenDioxide = new Material(Ref.ID, "nitrogen_dioxide", 0x64afff, NONE).asGas().mats(of(Nitrogen, 1, Oxygen, 2));
    public static Material NaturalGas = new Material(Ref.ID, "natural_gas", 0xffffff, NONE).asGas(15);
    public static Material Propane = new Material(Ref.ID, "propane", 0xfae250, NONE).asGas(48).mats(of(Carbon, 2, Hydrogen, 6));
    public static Material SulfurDioxide = new Material(Ref.ID, "sulfur_dioxide", 0xc8c819, NONE).asGas().mats(of(Sulfur, 1, Oxygen, 2));
    public static Material SulfurTrioxide = new Material(Ref.ID, "sulfur_trioxide", 0xa0a014, NONE).asGas()/*.setTemp(344, 1)*/.mats(of(Sulfur, 1, Oxygen, 3));
    public static Material NitricOxide = new Material(Ref.ID, "nitric_oxide", 0x7dc8f0, NONE).asGas().mats(of(Nitrogen, 1, Oxygen, 1));

    /** Fluids **/
    public static Material Lava = new Material(Ref.ID, "lava", 0xff4000, NONE).asFluid(/*0, 400*/);
    public static Material Water = new Material(Ref.ID, "water", 0x0000ff, NONE).asFluid().mats(of(Hydrogen, 2, Oxygen, 1));
    public static Material Steam = new Material(Ref.ID, "steam", 0xa0a0a0, NONE).asGas(0, 373);
    public static Material UUAmplifier = new Material(Ref.ID, "uu_amplifier", 0x600080, NONE).asFluid();
    public static Material UUMatter = new Material(Ref.ID, "uu_matter", 0x8000c4, NONE).asFluid();
    public static Material Lubricant = new Material(Ref.ID, "lubricant", 0xffc400, NONE).asFluid();
    //public static Material WoodTar = new Material(Ref.ID, "wood_tar", 0x28170b, NONE).asFluid(); TODO: not sure if needed
    public static Material DistilledWater = new Material(Ref.ID, "distilled_water", 0x5C5CFF, NONE).asFluid().mats(of(Hydrogen, 2, Oxygen, 1));
    public static Material Glyceryl = new Material(Ref.ID, "glyceryl", 0x009696, NONE).asFluid().mats(of(Carbon, 3, Hydrogen, 5, Nitrogen, 3, Oxygen, 9));
    public static Material SodiumPersulfate = new Material(Ref.ID, "sodium_persulfate", 0x006646, NONE).asFluid().mats(of(Sodium, 1, Sulfur, 1, Oxygen, 4));
    public static Material NitricAcid = new Material(Ref.ID, "nitric_acid", 0xe6e2ab, NONE).asFluid().mats(of(Hydrogen, 1, Nitrogen, 1, Oxygen, 3));
    //public static Material HydrochloricAcid = new Material(Ref.ID, "hydrochloric_acid", 0x6f8a91, NONE).asFluid().mats(of(Hydrogen, 1, Chlorine, 1));
    public static Material SulfuricAcid = new Material(Ref.ID, "sulfuric_acid", 0xff8000, NONE).asFluid().mats(of(Hydrogen, 2, Sulfur, 1, Oxygen, 4));
    public static Material NitroCarbon = new Material(Ref.ID, "nitro_carbon", 0x1f5e5e, NONE).asFluid().mats(of(Nitrogen, 1, Carbon, 1));

    /** Fuels **/
    public static Material Diesel = new Material(Ref.ID, "diesel", 0xffff00, NONE).asFluid(128);
    public static Material Gasoline = new Material(Ref.ID, "gasoline", 0x84723e, NONE).asFluid(128);
    public static Material NitroDiesel = new Material(Ref.ID, "nitro_diesel", 0xc8ff00, NONE).asFluid(384);
    public static Material BioDiesel = new Material(Ref.ID, "bio_diesel", 0xff8000, NONE).asFluid(192);
    public static Material Biomass = new Material(Ref.ID, "biomass", 0x00ff00, NONE).asFluid(8).flags(SEMIFLUID);
    public static Material Biofuel = new Material(Ref.ID, "biofuel", 0x99cc00, NONE).asFluid(6);
    public static Material Ethanol = new Material(Ref.ID, "ethanol", 0xff8000, NONE).asFluid(128).mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 1));
    public static Material Creosote = new Material(Ref.ID, "creosote", 0x804000, NONE).asFluid(8).flags(SEMIFLUID);
    public static Material Naphtha = new Material(Ref.ID, "naphtha", 0xffff64, NONE).asFluid(128);
    public static Material NitroCoalFuel = new Material(Ref.ID, "nitro-coalfuel", 0x002b2b, NONE).asFluid(48);
    public static Material CoalFuel = new Material(Ref.ID, "coalfuel", 0x0f0f0f, NONE).asFluid(16);

    public static Material Oil = new Material(Ref.ID, "oil", 0x0a0a0a, NONE).asFluid(15).flags(SEMIFLUID);
    public static Material SeedOil = new Material(Ref.ID, "seed_oil", 0xc4ff00, NONE).asFluid(6).flags(SEMIFLUID);
    //public static Materials SeedOilHemp = new Materials(722, "Hemp Seed Oil", 196, 255, 0, lime, NONE).asSemi(2);
    //public static Materials SeedOilLin = new Materials(723, "Lin Seed Oil", 196, 255, 0, lime, NONE).asSemi(2);

    /** Dusts **/
    public static Material SodiumSulfide = new Material(Ref.ID, "sodium_sulfide", 0xffe680, NONE).asDust().mats(of(Sodium, 2, Sulfur, 1));
    public static Material PlatinumGroupSludge = new Material(Ref.ID, "platinum_group_sludge", 0x001e00, NONE).asDust();
    public static Material Glowstone = new Material(Ref.ID, "glowstone", 0xffff00, SHINY).asDust();
    //public static Material Oilsands = new Material(Ref.ID, "oilsands", 0x0a0a0a, NONE).asDust(ORE);
    public static Material RareEarth = new Material(Ref.ID, "rare_earth", 0x808064, FINE).asDust();
    public static Material Almandine = new Material(Ref.ID, "almandine", 0xff0000, ROUGH).asDust().mats(of(Aluminium, 2, Iron, 3, Silicon, 3, Oxygen, 12));
    public static Material Andradite = new Material(Ref.ID, "andradite", 0x967800, ROUGH).asDust().mats(of(Calcium, 3, Iron, 2, Silicon, 3, Oxygen, 12));
    public static Material Ash = new Material(Ref.ID, "ash", 0x969696, DULL).asDust();
    public static Material Calcite = new Material(Ref.ID, "calcite", 0xfae6dc, DULL).asDust().mats(of(Calcium, 1, Carbon, 1, Oxygen, 3));
    public static Material Cassiterite = new Material(Ref.ID, "cassiterite", 0xdcdcdc, METALLIC).asDust(ORE).mats(of(Tin, 1, Oxygen, 2)).setOreMulti(2);
    public static Material Chromite = new Material(Ref.ID, "chromite", 0x23140F, DULL).asDust(ORE).mats(of(Iron, 1, Chrome, 2, Oxygen, 4)).harvestLevel(3);
    public static Material Clay = new Material(Ref.ID, "clay", 0xc8c8dc, ROUGH).asDust().mats(of(Sodium, 2, Lithium, 1, Aluminium, 2, Silicon, 2));
    public static Material DarkAsh = new Material(Ref.ID, "dark_ash", 0x323232, DULL).asDust().mats(of(Carbon, 2));
    public static Material Energium = new Material(Ref.ID, "energium", 0xff0000, DIAMOND).asDust();
    public static Material Galena = new Material(Ref.ID, "galena", 0x643c64, DULL).asDust(ORE).mats(of(Lead, 3, Silver, 3, Sulfur, 2)).harvestLevel(2);
    public static Material Grossular = new Material(Ref.ID, "grossular", 0xc86400, ROUGH).asDust().mats(of(Calcium, 3, Aluminium, 2, Silicon, 3, Oxygen, 12));
    public static Material Magnesite = new Material(Ref.ID, "magnesite", 0xfafab4, METALLIC).asDust().mats(of(Magnesium, 1, Carbon, 1, Oxygen, 3));
    public static Material Obsidian = new Material(Ref.ID, "obsidian", 0x503264, DULL).asDust().mats(of(Magnesium, 1, Iron, 1, Silicon, 2, Oxygen, 8));
    public static Material Phosphate = new Material(Ref.ID, "phosphate", 0xffff00, DULL).asDust().mats(of(Phosphor, 1, Oxygen, 4));
    public static Material Pyrite = new Material(Ref.ID, "pyrite", 0x967828, ROUGH).asDust(ORE).mats(of(Iron, 1, Sulfur, 2)).setOreMulti(2);
    public static Material Pyrope = new Material(Ref.ID, "pyrope", 0x783264, METALLIC).asDust().mats(of(Aluminium, 2, Magnesium, 3, Silicon, 3, Oxygen, 12));
    public static Material Saltpeter = new Material(Ref.ID, "saltpeter", 0xe6e6e6, FINE).asDust().mats(of(Potassium, 1, Nitrogen, 1, Oxygen, 3));
    public static Material SiliconDioxide = new Material(Ref.ID, "silicon_dioxide", 0xc8c8c8, QUARTZ).asDust().mats(of(Silicon, 1, Oxygen, 2));
    public static Material Brick = new Material(Ref.ID, "brick", 0x9b5643, ROUGH).asDust().mats(of(Aluminium, 4, Silicon, 3, Oxygen, 12));
    public static Material Fireclay = new Material(Ref.ID, "fireclay", 0xada09b, ROUGH).asDust().mats(of(Brick, 1));
    public static Material Spessartine = new Material(Ref.ID, "spessartine", 0xff6464, DULL).asDust().mats(of(Aluminium, 2, Manganese, 3, Silicon, 3, Oxygen, 12));
    public static Material Sphalerite = new Material(Ref.ID, "sphalerite", 0xffffff, DULL).asDust(ORE).mats(of(Zinc, 1, Sulfur, 1));
    public static Material Tetrahedrite = new Material(Ref.ID, "tetrahedrite", 0xc82000, DULL).asDust(ORE).mats(of(Copper, 3, Antimony, 1, Sulfur, 3, Iron, 1));
    public static Material Tungstate = new Material(Ref.ID, "tungstate", 0x373223, DULL).asDust(ORE).mats(of(Tungsten, 1, Lithium, 2, Oxygen, 4)).setOreMulti(2).harvestLevel(2);
    public static Material Uraninite = new Material(Ref.ID, "uraninite", 0x232323, DULL).asDust(ORE).mats(of(Uranium238, 1, Oxygen, 2)).harvestLevel(2);
    public static Material Bauxite = new Material(Ref.ID, "bauxite", 0xc86400, DULL).asDust(ORE).mats(of(Aluminium, 16, Hydrogen, 10, Oxygen, 11, Titanium, 1)).setOreMulti(2);
    public static Material Wood = new Material(Ref.ID, "wood", 0x643200, WOOD).asDust(PLATE).addHandleStat(12, 0.0F).mats(of(Carbon, 1, Oxygen, 1, Hydrogen, 1));
    public static Material Blaze = new Material(Ref.ID, "blaze", 0xffc800, NONE).asDust().mats(of(Sulfur, 1, DarkAsh, 1/*, Magic, 1*/)).addHandleStat(-10, -0.5F, of(Enchantments.FIRE_ASPECT, 1));
    public static Material Flint = new Material(Ref.ID, "flint", 0x002040, FLINT).asDust(GEM, FLINT_TAG).addTools(1.25F, 2.5F, 128, 1, of(Enchantments.FIRE_ASPECT, 1), PICKAXE, AXE, SHOVEL, SWORD, HOE, MORTAR, KNIFE, SPEAR).mats(of(SiliconDioxide, 1));
    public static Material PotassiumFeldspar = new Material(Ref.ID, "potassium_feldspar", 0x782828, FINE).asDust().mats(of(Potassium, 1, Aluminium, 1, Silicon, 3, Oxygen, 8));
    public static Material Biotite = new Material(Ref.ID, "biotite", 0x141e14, METALLIC).asDust().mats(b -> b.put(Potassium, 1).put(Magnesium, 3).put(Aluminium, 3).put(Fluorine, 2).put(Silicon, 3).put(Oxygen, 10));
    public static Material Uvarovite = new Material(Ref.ID, "uvarovite", 0xb4ffb4, DIAMOND).asDust().mats(of(Calcium, 3, Chrome, 2, Silicon, 3, Oxygen, 12));

    /** Gems **/
    //Brittle Gems
    public static Material CoalCoke = new Material(Ref.ID, "coal_coke", 0x8c8caa, LIGNITE).asGemBasic(false);
    public static Material Charcoal = new Material(Ref.ID, "charcoal", 0x644646, LIGNITE).asDust().mats(of(Carbon, 1));
    public static Material Coal = new Material(Ref.ID, "coal", 0x464646, LIGNITE).asDust(ORE).mats(of(Carbon, 2));

    public static Material Diamond = new Material(Ref.ID, "diamond", /*0x3de0e5*/0xc8ffff, DIAMOND).asGemBasic(false, ORE).mats(of(Carbon, 128)).addTools(ItemTier.DIAMOND.getAttackDamage(), ItemTier.DIAMOND.getEfficiency(), ItemTier.DIAMOND.getMaxUses(), ItemTier.DIAMOND.getHarvestLevel());
    public static Material Emerald = new Material(Ref.ID, "emerald", 0x50ff50, GEM_V).asGemBasic(false, ORE).mats(of(Beryllium, 3, Aluminium, 2, Silicon, 3, Oxygen, 18));
    public static Material Lazurite = new Material(Ref.ID, "lazurite", 0x6478ff, LAPIS).asDust().mats(of(Aluminium, 3, Silicon, 3, Calcium, 4, Sodium, 4));
    public static Material Ruby = new Material(Ref.ID, "ruby", 0xff6464, RUBY).asGemBasic(false, ORE).addTools(3.0F, 7.0F, 1024, 3).addArmor(new int[]{1, 1, 2, 1}, 1.0F, 0.0F, 30).mats(of(Chrome, 1, Aluminium, 2, Oxygen, 3));
    public static Material Sapphire = new Material(Ref.ID, "sapphire", 0x6464c8, GEM_V).asGemBasic(false, ORE).addTools(3.0F, 7.0F, 1024, 3).addArmor(new int[]{1, 1, 2, 1}, 1.0F, 0.0F, 30).mats(of(Aluminium, 2, Oxygen, 3));
    public static Material Sodalite = new Material(Ref.ID, "sodalite", 0x1414ff, LAPIS).asDust(ORE).mats(of(Aluminium, 3, Silicon, 3, Sodium, 4, Chlorine, 1)).setOreMulti(6).harvestLevel(2);
    //public static Material Glass = new Material(Ref.ID, "glass", 0xfafafa, SHINY).asDust(PLATE, LENS).mats(of(SiliconDioxide, 1));
    public static Material Olivine = new Material(Ref.ID, "olivine", 0x96ff96, RUBY).asGemBasic(false, ORE).mats(of(Magnesium, 2, Iron, 1, Silicon, 1, Oxygen, 4)).harvestLevel(3);
    public static Material EnderPearl = new Material(Ref.ID, "enderpearl", 0x6cdcc8, SHINY).asDust().mats(of(Beryllium, 1, Potassium, 4, Nitrogen, 5, Chlorine, 6));
    public static Material EnderEye = new Material(Ref.ID, "endereye", 0xa0fae6, SHINY).asDust().mats(of(EnderPearl, 1, Blaze, 1));
    public static Material Lapis = new Material(Ref.ID, "lapis", 0x4646dc, LAPIS).asDust(ORE).mats(of(Lazurite, 12, Sodalite, 2, Pyrite, 1, Calcite, 1)).setOreMulti(6);
    //public static Material Phosphorus = new Material(Ref.ID, "phosphorus", 0xffff00, FLINT).asDust().mats(of(Calcium, 3, Phosphate, 2));
    public static Material RedGarnet = new Material(Ref.ID, "red_garnet", 0xc85050, GARNET).asGemBasic(false).mats(of(Pyrope, 3, Almandine, 5, Spessartine, 8));
    public static Material YellowGarnet = new Material(Ref.ID, "yellow_garnet", 0xc8c850, GARNET).asGemBasic(false).mats(of(Uvarovite, 3, Andradite, 5, Grossular, 8));

    /** **/
    public static Material Redstone = new Material(Ref.ID, "redstone", 0xc80000, ROUGH).asDust(ORE).mats(of(Silicon, 1, Pyrite, 5, Ruby, 1, Mercury, 3)).setOreMulti(4);
    public static Material Cinnabar = new Material(Ref.ID, "cinnabar", 0x960000, ROUGH).asDust(ORE).mats(of(Mercury, 1, Sulfur, 1)).setOreMulti(2).harvestLevel(2);

    /** Metals **/
    public static Material BatteryAlloy = new Material(Ref.ID, "battery_alloy", 0x9c7ca0, DULL).asMetal(295, 0, PLATE).mats(of(Lead, 4, Antimony, 1));
    public static Material Brass = new Material(Ref.ID, "brass", 0xffb400, METALLIC).asMetal(1170, 0,  PLATE, ROD, HULL).mats(of(Zinc, 1, Copper, 3));
    public static Material Bronze = new Material(Ref.ID, "bronze", 0xff8000, METALLIC).asMetal(1125, 0,  PLATE, ROD, GEAR, HULL, TURBINE_BLADE, TURBINE_ROTOR).mats(of(Tin, 1, Copper, 3)).addTools(2.0F, 6.0F, 350, 2,  of(Enchantments.SHARPNESS, 1)).addArmor(new int[]{1, 1, 2, 1}, 0.0F, 0.0F, 15);
    public static Material Cupronickel = new Material(Ref.ID, "cupronickel", 0xe39680, METALLIC).asMetal(1728, 0, PLATE).mats(of(Copper, 1, Nickel, 1));
    public static Material Electrum = new Material(Ref.ID, "electrum", 0xffff64, SHINY).asMetal(1330, 0, PLATE, ROD).mats(of(Silver, 1, Gold, 1));
    public static Material Invar = new Material(Ref.ID, "invar", 0xb4b478, METALLIC).asMetal(1700, 0,  PLATE, ROD).mats(of(Iron, 2, Nickel, 1)).addTools(Iron, of(Enchantments.BANE_OF_ARTHROPODS, 3)).addArmor(new int[]{0, 0, 0,0}, 0.0F, 0.0F, 15, of(Enchantments.PROJECTILE_PROTECTION, 1));
    public static Material Kanthal = new Material(Ref.ID, "kanthal", 0xc2d2df, METALLIC).asMetal(1800, 2200,  PLATE, ROD).mats(of(Iron, 1, Aluminium, 1, Chrome, 1));
    public static Material Magnalium = new Material(Ref.ID, "magnalium", 0xc8beff, DULL).asMetal(870, 0,  PLATE, ROD, TURBINE_BLADE, TURBINE_ROTOR).mats(of(Magnesium, 1, Aluminium, 2));
    public static Material Nichrome = new Material(Ref.ID, "nichrome", 0xcdcef6, METALLIC).asMetal(2700, 2500).mats(of(Nickel, 4, Chrome, 1));
    public static Material SolderingAlloy = new Material(Ref.ID, "soldering_alloy", 0xdcdce6, DULL).asMetal(400, 400, PLATE, ROD).mats(of(Tin, 9, Antimony, 1));
    public static Material Steel = new Material(Ref.ID, "steel", 0x808080, METALLIC).asMetal(1811, 1500, PLATE, ROD, GEAR, HULL, TURBINE_BLADE, TURBINE_ROTOR, RING).addTools(2.5F, 6.0F, 512, 2,  of(Enchantments.SHARPNESS, 2)).addArmor(new int[]{0, 1, 1, 0}, 1.0F, 0.0F, 21, of(Enchantments.PROTECTION, 1)).mats(of(Iron, 50, Carbon, 1));
    public static Material StainlessSteel = new Material(Ref.ID, "stainless_steel", 0xc8c8dc, SHINY).asMetal(1700, 1700, PLATE, ROD, GEAR, HULL).mats(of(Iron, 6, Chrome, 1, Manganese, 1, Nickel, 1)).addTools(2.5F, 7.0F, 480, 2, of(Enchantments.SHARPNESS, 3)).addArmor(new int[]{0, 1, 1, 0}, 2.0F, 0.0F, 20, of(Enchantments.PROTECTION, 2));
    public static Material WroughtIron = new Material(Ref.ID, "wrought_iron", 0xc8b4b4, METALLIC).asMetal(1811, 0, PLATE, ROD, GEAR, HULL).mats(of(Iron, 1)).addTools(IRON.getAttackDamage(), IRON.getEfficiency(), (int)(IRON.getMaxUses() * 1.5F), IRON.getHarvestLevel(),  of(Enchantments.SHARPNESS, 2)).addArmor(new int[]{0, 0, 0, 0}, 1.0F, 0.0F, 17, of(Enchantments.PROTECTION, 1));
    public static Material TungstenSteel = new Material(Ref.ID, "tungstensteel", 0x6464a0, METALLIC).asMetal(3000, 3000, PLATE, ROD, GEAR, HULL, TURBINE_BLADE, TURBINE_ROTOR).addTools(6.0F, 10.0F, 5120, 4).mats(of(Steel, 1, Tungsten, 1)).addArmor(new int[]{1, 2, 3, 1}, 3.0F, 0.0F, 18, of(Enchantments.PROTECTION, 3));
    public static Material TungstenCarbide = new Material(Ref.ID, "tungsten_carbide", 0x330066, METALLIC).asMetal(2460, 2460).addTools(5.0F, 14.0F, 2560, 4, of(Enchantments.SHARPNESS, 5)).mats(of(Tungsten, 1, Carbon, 1));
    public static Material RedAlloy = new Material(Ref.ID, "red_alloy", 0xc80000, DULL).asMetal(295, 0, PLATE, ROD).mats(of(Copper, 1, Redstone, 4));
    public static Material Osmiridium = new Material(Ref.ID, "osmiridium", 0x6464ff, METALLIC).asMetal(3333, 3300,  PLATE, ROD, TURBINE_BLADE, TURBINE_ROTOR).mats(of(Iridium, 1, Osmium, 1));

    /** Solids (Plastic Related Stuff)**/
    public static Material Plastic = new Material(Ref.ID, "plastic", 0xc8c8c8, DULL).asSolid(295, 0, PLATE, RUBBERTOOLS).addHandleStat(66, 0.5F).mats(of(Carbon, 1, Hydrogen, 2));
    public static Material Rubber = new Material(Ref.ID, "rubber", 0x000000, SHINY).asSolid(295, 0, PLATE, RUBBERTOOLS).addHandleStat(11, 0.4F).mats(of(Carbon, 5, Hydrogen, 8));

    /** Stones **/
    public static Material RedGranite = new Material(Ref.ID, "red_granite", 0xff0080, ROUGH).asDust(ROCK).addHandleStat(74, 1.0F, of(Enchantments.UNBREAKING, 1)).mats(of(Aluminium, 2, PotassiumFeldspar, 1, Oxygen, 3));
    public static Material BlackGranite = new Material(Ref.ID, "black_granite", 0x0a0a0a, ROUGH).asDust(ROCK).addHandleStat(74, 1.0F, of(Enchantments.UNBREAKING, 1)).mats(of(SiliconDioxide, 4, Biotite, 1));
    public static Material Marble = new Material(Ref.ID, "marble", 0xc8c8c8, NONE).asDust(ROCK).mats(of(Magnesium, 1, Calcite, 7));
    public static Material Basalt = new Material(Ref.ID, "basalt", 0x1e1414, ROUGH).asDust(ROCK).mats(of(Olivine, 1, Calcite, 3, Flint, 8, DarkAsh, 4));
    public static Material Komatiite = new Material(Ref.ID, "komatiite", 0xbebe69, NONE).asDust(ROCK).mats(of(Olivine, 1, Magnesite, 2, Flint, 6, DarkAsh, 3));
    public static Material Limestone = new Material(Ref.ID, "limestone", 0xe6c882, NONE).asDust(ROCK).mats(of(Calcite, 1));
    public static Material GreenSchist = new Material(Ref.ID, "green_schist", 0x69be69, NONE).asDust(ROCK);
    public static Material BlueSchist = new Material(Ref.ID, "blue_schist", 0x0569be, NONE).asDust(ROCK);
    public static Material Kimberlite = new Material(Ref.ID, "kimberlite", 0x64460a, NONE).asDust(ROCK);
    public static Material Quartzite = new Material(Ref.ID, "quartzite", 0xe6cdcd, QUARTZ).asGemBasic(false, ROCK).mats(of(Silicon, 1, Oxygen, 2));
    public static Material Prismarine = new Material(Ref.ID, "prismarine", 0x6eb2a5, NONE).asDust().mats(of(Potassium, 2, Oxygen, 8, Manganese, 1, Silicon, 5));
    public static Material DarkPrismarine = new Material(Ref.ID, "dark_prismarine", 0x587d6c, NONE).asDust();

    /** Ore Stones **/
    public static Material Salt = new Material(Ref.ID, "salt", 0xfafafa, FINE).asDust().mats(of(Sodium, 1, Chlorine, 1));
    public static Material RockSalt = new Material(Ref.ID, "rock_salt", 0xf0c8c8, FINE).asDust().mats(of(Potassium, 1, Chlorine, 1));
    //public static Material OilShale = new Material(Ref.ID, "oil_shale", 0x32323c, NONE).asDust(ORE_STONE);



    /** Reference Materials **/
    public static Material Superconductor = new Material(Ref.ID, "superconductor", 0xffffff, NONE);
    public static Material HighPressure = new Material(Ref.ID, "high_pressure", 0xc80000, NONE);
    public static Material HighCapacity = new Material(Ref.ID, "high_capacity", 0xb00b69, NONE);
    public static Material PlasmaContainment = new Material(Ref.ID, "plasma_containment", 0xffff00, NONE);
    public static Material Quartz = new Material(Ref.ID, "quartz", 0xffffff, NONE).asDust();

    /** VANILLA **/
    public static Material Netherite = new Material(Ref.ID, "netherite", 0x504650, DULL).asMetal(2246,1300, PLATE, ROD).addTools(3.0F, 10, 500, NETHERITE.getHarvestLevel(), of(Enchantments.FIRE_ASPECT, 3)).addArmor(new int[]{0, 1, 1, 0}, 0.5F, 0.1F, 20);
    public static Material NetherizedDiamond = new Material(Ref.ID, "netherized_diamond", 0x5a505a, DIAMOND).asGemBasic(false).addTools(4.0F, 12, NETHERITE.getMaxUses(), NETHERITE.getHarvestLevel(), of(Enchantments.FIRE_ASPECT, 3, Enchantments.SHARPNESS, 4)).addArmor(new int[]{1, 1, 2, 1}, 3.0F, 0.1F, 37, of(Enchantments.PROTECTION, 4));
    public static Material NetheriteScrap = new Material(Ref.ID, "netherite_scrap", 0x6e505a, ROUGH).asDust(CRUSHED, CRUSHED_PURIFIED, CRUSHED_CENTRIFUGED, DUST_IMPURE, DUST_PURE);


    static {
        /*ELEC.add(Methane, CarbonDioxide, NitrogenDioxide, Toluene, VinylChloride, SulfurDioxide, SulfurTrioxide, Dimethylamine, DinitrogenTetroxide, NitricOxide, Ammonia, Chloromethane, Tetrafluoroethylene, CarbonMonoxide, Ethylene, Propane, Ethenone, Ethanol, Glyceryl, SodiumPersulfate, Dichlorobenzene, Styrene, Isoprene, Tetranitromethane, Epichlorohydrin, NitricAcid, Dimethylhydrazine, Chloramine, Dimethyldichlorosilane, HydrofluoricAcid, Chloroform, BisphenolA, AceticAcid, Acetone, Methanol, VinylAcetate, MethylAcetate, AllylChloride, HypochlorousAcid, Cumene, PhosphoricAcid, SulfuricAcid, Benzene, Phenol, Glycerol, SodiumSulfide, Almandine, Andradite, BandedIron, Calcite, Cassiterite, Chalcopyrite, Cobaltite, Galena, Garnierite, Grossular, Bauxite, Magnesite, Magnetite, Molybdenite, Obsidian, Phosphate, Polydimethylsiloxane, Pyrite, Pyrolusite, Pyrope, RockSalt, Saltpeter, SiliconDioxide, Massicot, ArsenicTrioxide, CobaltOxide, Magnesia, Quicklime, Potash, SodaAsh, PhosphorousPentoxide, SodiumHydroxide, Spessartine, Sphalerite, Uvarovite, PotassiumFeldspar, Biotite, RedGranite, Bastnasite, Pentlandite, Spodumene, Glauconite, Bentonite, Malachite, Barite, Talc, AntimonyTrioxide, CupricOxide, Ferrosilite, Quartzite, Charcoal, Coal, Lignite, Diamond, Emerald, Ruby, Sapphire, Tanzanite, Topaz, Olivine, Opal, Amethyst, EnderPearl, StainlessSteel, Steel, Ultimet, IronMagnetic, SteelMagnetic, NeodymiumMagnetic, Osmiridium);
        CENT.add(*//*NobleGases, *//*Air, BrownLimonite, Cinnabar, Clay, Cooperite*//*, Powellite*//*, Stibnite, Tetrahedrite, Uraninite, Wulfenite, YellowLimonite, Blaze, Flint, Marble, BlackGranite, VanadiumMagnetite, Pitchblende, Glass, Lapis, EnderEye, Phosphorus, Redstone, Basalt, AnnealedCopper, BatteryAlloy, Brass, Bronze, Cupronickel, Electrum, Invar, Kanthal, Magnalium, Nichrome, NiobiumTitanium, SolderingAlloy, VanadiumGallium, WroughtIron, SterlingSilver, RoseGold, BismuthBronze, TungstenSteel, RedAlloy, CobaltBrass, TungstenCarbide, VanadiumSteel, HSSG, HSSE, HSSS, GalliumArsenide*//*, IndiumGalliumPhosphide, BorosilicateGlass*//*);
        RUBBERTOOLS.add(Rubber, StyreneButadieneRubber, Plastic, PolyvinylChloride, Polystyrene, Silicone);
        SOLDER.add(Lead, Tin, SolderingAlloy);
        //TODO Mercury.add(METALL, SMELTG);
*/
        ELEC30.add(Charcoal, Prismarine, Salt, RockSalt, Quartzite);
        ELEC60.add(Cassiterite, SodiumSulfide, Sapphire, SiliconDioxide, Methane, Pyrite, Sphalerite, NitrogenDioxide, Phosphate, Magnesite);
        ELEC90.add(Calcite, EnderPearl, SulfuricAcid, RedGranite, Saltpeter, Chromite, SodiumPersulfate, Glyceryl, Ruby, Olivine, Galena, Tungstate/* CalciumCarbonate*/);
        ELEC120.add(Emerald, Grossular, Clay, StainlessSteel, Sodalite, Bauxite, Obsidian, Pyrope, Uvarovite, Almandine, Andradite, Lazurite, Spessartine, PotassiumFeldspar, Biotite);

        ELEC.add(ELEC30.all().toArray(new Material[0]));
        ELEC.add(ELEC60.all().toArray(new Material[0]));
        ELEC.add(ELEC90.all().toArray(new Material[0]));
        ELEC.add(ELEC120.all().toArray(new Material[0]));

        NUGGET.forceOverride(Iron, Items.IRON_NUGGET);
        NUGGET.forceOverride(Gold, Items.GOLD_NUGGET);
        INGOT.forceOverride(Iron, Items.IRON_INGOT);
        INGOT.forceOverride(Gold, Items.GOLD_INGOT);
        INGOT.forceOverride(Netherite, Items.NETHERITE_INGOT);
        DUST.forceOverride(Redstone, Items.REDSTONE);
        DUST.forceOverride(Glowstone, Items.GLOWSTONE_DUST);
        DUST.forceOverride(Blaze, Items.BLAZE_POWDER);
        GEM.forceOverride(Flint, Items.FLINT);
        GEM.forceOverride(Diamond, Items.DIAMOND);
        GEM.forceOverride(Emerald, Items.EMERALD);

        BLOCK.forceOverride(Iron, Items.IRON_BLOCK);
        BLOCK.forceOverride(Gold, Items.GOLD_BLOCK);

        ROD.forceOverride(Blaze, Items.BLAZE_ROD);
        ROD.forceOverride(Wood, Items.STICK);

        Lava.mats(of(Electrum, 1, Copper, 4, Tungsten, 1, Basalt, 1));
        Granite.mats(of(Aluminium, 2, Flint, 1, Clay, 1));
        Glowstone.mats(of(Redstone, 8, Gold, 8, Helium, 1));
        Diorite.mats(of(Nickel, 1));

        Bronze.remove(BOLT, SCREW);
        Iron.remove(BOLT, SCREW);
        //Steel.remove(BOLT, SCREW);
        //TungstenSteel.remove(BOLT, SCREW);
        //Titanium.remove(BOLT, SCREW);
        Tungsten.remove(BOLT, SCREW);
        Netherite.remove(BOLT, SCREW);
        NetherizedDiamond.remove(BOLT, SCREW);
        Invar.remove(BOLT, SCREW);
        WroughtIron.remove(BOLT, SCREW);
        TungstenCarbide.remove(BOLT, SCREW);

        Gold.remove(ORE_SMALL, ROCK);
        Iron.remove(ORE_SMALL, ROCK);
        Emerald.remove(ORE_SMALL, ROCK);
        Iridium.remove(ORE_SMALL, ROCK);
        Uraninite.remove(ORE_SMALL, ROCK);
        Copper.remove(ORE_SMALL, ROCK);
        Tin.remove(ORE_SMALL, ROCK);
        Cassiterite.remove(ORE_SMALL, ROCK);
        Chromite.remove(ORE_SMALL, ROCK);
        Galena.remove(ORE_SMALL, ROCK);
        Bauxite.remove(ORE_SMALL, ROCK);
        Pyrite.remove(ORE_SMALL, ROCK);
        Sphalerite.remove(ORE_SMALL, ROCK);
        Tetrahedrite.remove(ORE_SMALL, ROCK);
        Tungstate.remove(ORE_SMALL, ROCK);
        Sodalite.remove(ORE_SMALL, ROCK);
        Cinnabar.remove(ORE_SMALL, ROCK);
        Ruby.remove(ORE_SMALL, ROCK);
        Sapphire.remove(ORE_SMALL, ROCK);
        Olivine.remove(ORE_SMALL, ROCK);
        Platinum.remove(ORE_SMALL, ROCK);
        Diamond.remove(ORE_SMALL, ROCK);
        Coal.remove(CRUSHED, CRUSHED_PURIFIED, CRUSHED_CENTRIFUGED, DUST_IMPURE, DUST_PURE, ORE_SMALL, ROCK);
        Redstone.remove(ORE_SMALL, ROCK);
        Lapis.remove(ORE_SMALL, ROCK);

        //WroughtIron.setSmeltInto(Iron).setMacerateInto(Iron);

        //Cinnabar.setDirectSmeltInto(Mercury);
        Tetrahedrite.setDirectSmeltInto(Copper).setSmeltInto(Copper);
        Cassiterite.setDirectSmeltInto(Tin).setSmeltInto(Tin);
        Cassiterite.setOreMulti(2).setSmeltingMulti(2);
        Redstone.setOreMulti(5).setSmeltingMulti(5);
        // ore byproducts
        // eligible: cinnabar, uranium, copper, cassiterite, pyrite, sodalite-special:4x byproduct, sphalerite, tetra, bauxite 4x bypoduct, lead, tin, galena, iron, gold, platinum aka sheldonite, nickel, tungstate
        // gems & iridium will be separate redstone, lapis
        Gold.addByProduct(Copper, Nickel);
        Iron.addByProduct(Nickel, Tin);
        Iridium.addByProduct(Platinum, Osmium);
        Uraninite.addByProduct(Lead, Uranium235, Thorium);
        Copper.addByProduct(Gold, Nickel);
        Tin.addByProduct(Iron, Zinc);
        Cassiterite.addByProduct(Tin);
        Chromite.addByProduct(Iron, Magnesium, Chrome);
        Galena.addByProduct(Sulfur, Silver, Lead);
        Pyrite.addByProduct(Sulfur, Phosphor, Iron);
        Sphalerite.addByProduct(Zinc, YellowGarnet, Cadmium);
        Tetrahedrite.addByProduct(Antimony, Zinc);
        Tungstate.addByProduct(Manganese, Silver, Lithium);
        Ruby.addByProduct(Chrome, RedGarnet);
        Sapphire.addByProduct(Aluminium, Sapphire);
        Sodalite.addByProduct(Lazurite, Lapis);
        Olivine.addByProduct(Pyrope, Magnesium);
        Cinnabar.addByProduct(Redstone, Sulfur, Glowstone);
        Bauxite.addByProduct(Grossular, Titanium);
        Emerald.addByProduct(Beryllium, Aluminium);
        Redstone.addByProduct(Cinnabar, RareEarth, Glowstone);
        Lapis.addByProduct(Lazurite, Sodalite, Pyrite);
        Diamond.addByProduct(Carbon/*Graphite*/);
        Platinum.addByProduct(Nickel, Iridium);
        NetheriteScrap.addByProduct(Quartz);

        //ore byproducts from non vanilla/gt4r ores
        Nickel.addByProduct(Iron, Platinum);
        Silver.addByProduct(Lead, Sulfur);
        Lead.addByProduct(Silver, Sulfur);
        Zinc.addByProduct(Tin);
        RedGarnet.addByProduct(Spessartine, Pyrope, Almandine);
        YellowGarnet.addByProduct(Andradite, Grossular);
        Tungsten.addByProduct(Manganese);
        Thorium.addByProduct(Uranium238, Lead);

        //other byproducts
        Andradite.addByProduct(YellowGarnet, Iron);
        Glowstone.addByProduct(Redstone, Gold);
        Antimony.addByProduct(Zinc, Iron);
        Plutonium.addByProduct(Uranium238, Lead);
        Electrum.addByProduct(Gold, Silver);
        Bronze.addByProduct(Copper, Tin);
        Brass.addByProduct(Copper, Zinc);
        Manganese.addByProduct(Chrome, Iron);
        Chrome.addByProduct(Iron, Magnesium);
        Basalt.addByProduct(Olivine, DarkAsh);
        Pyrope.addByProduct(RedGarnet, Magnesium);
        Almandine.addByProduct(RedGarnet, Aluminium);
        Spessartine.addByProduct(RedGarnet, Manganese);
        Grossular.addByProduct(YellowGarnet, Calcium);
        Calcite.addByProduct(Andradite);
        Beryllium.addByProduct(Emerald);
        Steel.addByProduct(Iron);
        Netherrack.addByProduct(Sulfur);
        Flint.addByProduct(Obsidian);
        Sulfur.addByProduct(Sulfur);
        Saltpeter.addByProduct(Saltpeter);
        Endstone.addByProduct(Helium3);
        Osmium.addByProduct(Iridium);
        Magnesium.addByProduct(Olivine);
        Aluminium.addByProduct(Bauxite);
        Titanium.addByProduct(Almandine);
        Obsidian.addByProduct(Olivine);
        Ash.addByProduct(Carbon);
        DarkAsh.addByProduct(Carbon);
        Marble.addByProduct(Calcite);
        Clay.addByProduct(Clay);
        Phosphate.addByProduct(Phosphor);
        Phosphor.addByProduct(Phosphate);
        Lithium.addByProduct(Lithium);
        Silicon.addByProduct(SiliconDioxide);
    }

    //TODO go through the GT_Loader_Item_Block_And_Fluid and make sure all explicitly added fluids have the LIQUID tag
    public static void init() {
        //TODO assign correct handle materials
//                for (Material material : generated) {
//            if (material == Blaze) {
//                material.handleMaterial = "blaze";
//            } /*else if (aMaterial.contains(SubTag.MAGICAL) && aMaterial.contains(SubTag.CRYSTAL) && Utils.isModLoaded(MOD_ID_TC)) {
//                    aMaterial.mHandleMaterial = Thaumium;
//                }*/ else if (material.getMass() > Element.Tc.getMass() * 2) {
//                material.handleMaterial = Tungstensteel.;
//            } else if (material.getMass() > Element.Tc.getMass()) {
//                material.handleMaterial = Steel;
//            } else {
//                material.handleMaterial = Wood;
//            }
//        }

        //TODO move to antimatter
        LIQUID.all().stream().filter(l -> !l.equals(Water) || !l.equals(Lava)).forEach(m -> new AntimatterMaterialFluid(Ref.ID, m, LIQUID));
        GAS.all().forEach(m -> new AntimatterMaterialFluid(Ref.ID, m, GAS));
        PLASMA.all().forEach(m -> new AntimatterMaterialFluid(Ref.ID, m, PLASMA));

        AntimatterAPI.all(Material.class, Material::setChemicalFormula);
    }
}
