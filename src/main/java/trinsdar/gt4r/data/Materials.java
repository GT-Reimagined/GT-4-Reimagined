package trinsdar.gt4r.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Data;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialItem;
import muramasa.antimatter.material.MaterialTag;
import muramasa.antimatter.material.MaterialType;
import muramasa.antimatter.material.MaterialTypeItem;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.items.ItemTurbineRotor;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.fluid.AntimatterFluid.LIQUID_FLOW_TEXTURE;
import static muramasa.antimatter.fluid.AntimatterFluid.LIQUID_STILL_TEXTURE;
import static muramasa.antimatter.fluid.AntimatterFluid.OVERLAY_TEXTURE;
import static muramasa.antimatter.material.Element.*;
import static muramasa.antimatter.material.MaterialTag.ELEC;
import static muramasa.antimatter.material.MaterialTag.RUBBERTOOLS;
import static muramasa.antimatter.material.TextureSet.NONE;
import static net.minecraft.item.ItemTier.IRON;
import static net.minecraft.item.ItemTier.NETHERITE;
import static trinsdar.gt4r.data.Textures.*;

public class Materials {

    public static final ResourceLocation PAHOEHOE_STILL_TEXTURE = new ResourceLocation(Ref.ID, "fluids/pahoehoe_lava");

    public static MaterialTag ELEC30 = new MaterialTag("elec30");
    public static MaterialTag ELEC60 = new MaterialTag("elec60");
    public static MaterialTag ELEC90 = new MaterialTag("elec90");
    public static MaterialTag ELEC120 = new MaterialTag("elec120");
    public static MaterialTag ROCK_CUTTER = new MaterialTag("rock_cutter");

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

    public static MaterialTypeItem<?> BROKEN_TURBINE_ROTOR = new MaterialTypeItem<>("broken_turbine_rotor", 1, true, muramasa.antimatter.Ref.U * 17);
    public static MaterialTypeItem<?> PICKAXE_HEAD = new MaterialTypeItem<>("pickaxe_head", 2, true, muramasa.antimatter.Ref.U * 3);
    public static MaterialTypeItem<?> AXE_HEAD = new MaterialTypeItem<>("axe_head", 2, true, muramasa.antimatter.Ref.U * 3);
    public static MaterialTypeItem<?> SWORD_HEAD = new MaterialTypeItem<>("sword_head", 2, true, muramasa.antimatter.Ref.U * 2);
    public static MaterialTypeItem<?> SHOVEL_HEAD = new MaterialTypeItem<>("shovel_head", 2, true, muramasa.antimatter.Ref.U);
    public static MaterialTypeItem<?> HOE_HEAD = new MaterialTypeItem<>("hoe_head", 2, true, muramasa.antimatter.Ref.U * 2);
    public static MaterialTypeItem<?> HAMMER_HEAD = new MaterialTypeItem<>("hammer_head", 2, true, muramasa.antimatter.Ref.U * 6);
    public static MaterialTypeItem<?> FILE_HEAD = new MaterialTypeItem<>("file_head", 2, true, muramasa.antimatter.Ref.U * 2);
    public static MaterialTypeItem<?> SAW_HEAD = new MaterialTypeItem<>("saw_head", 2, true, muramasa.antimatter.Ref.U * 2);
    public static MaterialTag FLINT_TAG = new MaterialTag("flint");
    public static MaterialTag SEMIFLUID = new MaterialTag("semifluid");
    public static MaterialTag CABINET = new MaterialTag("cabinet");
    public static MaterialTag WORKBENCH = new MaterialTag("workbench");
    public static MaterialTag CHARGING_WORKBENCH = new MaterialTag("charging_workbench");
    public static MaterialTag LOCKER = new MaterialTag("locker");
    public static MaterialTag CHARGING_LOCKER = new MaterialTag("charging_locker");


    /** Elements **/
    public static Material Aluminium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "aluminium", 0x80c8f0, DULL, Al)).asMetal(933, 1000, PLATE, ROD, GEAR, BOLT, SCREW);
    public static Material Beryllium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "beryllium", 0x64b464, METALLIC, Be)).asMetal(1560, 0, PLATE);
    public static Material Bismuth = AntimatterAPI.register(Material.class, new Material(Ref.ID, "bismuth", 0x64a0a0, METALLIC, Bi, Ref.MOD_TFC)).asMetal(544, 0, PLATE).asOre();
    public static Material Carbon = AntimatterAPI.register(Material.class, new Material(Ref.ID, "carbon", 0x141414, DULL, C)).asMetal(3800, 1000, PLATE);
    public static Material Chrome = AntimatterAPI.register(Material.class, new Material(Ref.ID, "chrome", 0xffe6e6, SHINY, Cr)).asMetal(2180, 1700, PLATE);
    public static Material Gold = AntimatterAPI.register(Material.class, new Material(Ref.ID, "gold", 0xffe650, SHINY, Au)).asMetal(1337, 0, ROD, GEAR, PLATE, ROD).asOre(1, 5, true).harvestLevel(2);
    public static Material Iridium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "iridium", 0xf0f0f5, DULL, Ir)).asMetal(2719, 3000, PLATE).asOre(3, 7, true).harvestLevel(3);
    public static Material Iron = AntimatterAPI.register(Material.class, new Material(Ref.ID, "iron", 0xc8c8c8, METALLIC, Fe)).asMetal(1811, 500, PLATE, ROD).asOre(1, 5, true).asPlasma().addTools(IRON.getAttackDamage(), IRON.getEfficiency(), 256, IRON.getHarvestLevel(),  of(Enchantments.SHARPNESS, 1));
    public static Material Lead = AntimatterAPI.register(Material.class, new Material(Ref.ID, "lead", 0x8c648c, DULL, Pb)).asMetal(600, 0, PLATE, PLATE_DENSE, ROD).harvestLevel(2);
    public static Material Manganese = AntimatterAPI.register(Material.class, new Material(Ref.ID, "manganese", 0xfafafa, DULL, Mn)).asMetal(1519, 0);
    public static Material Nickel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "nickel", 0xc8c8fa, METALLIC, Ni)).asMetal(1728, 0, PLATE).asPlasma();
    public static Material Osmium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "osmium", 0x3232ff, METALLIC, Os)).asMetal(3306, 3306, PLATE, ROD);
    public static Material Platinum = AntimatterAPI.register(Material.class, new Material(Ref.ID, "platinum", 0xffffc8, SHINY, Pt)).asMetal(2041, 0, PLATE, ROD).asOre(3, 7, true).harvestLevel(2);
    public static Material Plutonium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "plutonium_244", 0xf03232, METALLIC, Pu)).asMetal(912, 0);
    public static Material Silver = AntimatterAPI.register(Material.class, new Material(Ref.ID, "silver", 0xdcdcff, SHINY, Ag)).asMetal(1234, 0, PLATE).harvestLevel(2);
    public static Material Thorium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "thorium", 0x001e00, SHINY, Th)).asMetal(2115, 0);
    public static Material Titanium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "titanium", 0xdca0f0, METALLIC, Ti)).asMetal(1941, 1500,  PLATE, ROD, GEAR).addArmor(new int[]{1, 1, 2, 1}, 1.0F, 0.0F, 15).addTools(3.5F, 8.0F, 1600, 3);
    public static Material Tungsten = AntimatterAPI.register(Material.class, new Material(Ref.ID, "tungsten", 0x323232, METALLIC, W)).asMetal(3695, 2500, PLATE, ROD).addTools(3.5F, 8.0F, 2560, 3);
    public static Material Uranium238 = AntimatterAPI.register(Material.class, new Material(Ref.ID, "uranium_238", 0x32f032, METALLIC, U)).asMetal(1405, 0);
    public static Material Uranium235 = AntimatterAPI.register(Material.class, new Material(Ref.ID, "uranium_235", 0x46fa46, METALLIC, U235)).asMetal(1405, 0);
    public static Material Antimony = AntimatterAPI.register(Material.class, new Material(Ref.ID, "antimony", 0xdcdcf0, SHINY, Sb)).asMetal(1449, 0);
    public static Material Argon = AntimatterAPI.register(Material.class, new Material(Ref.ID, "argon", 0xff00f0, NONE, Ar)).asGas();
    public static Material Calcium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "calcium", 0xfff5f5, METALLIC, Ca)).asDust(1115);
    public static Material Cadmium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "cadmium", 0x32323c, SHINY, Cd)).asDust(594);
    public static Material Chlorine = AntimatterAPI.register(Material.class, new Material(Ref.ID, "chlorine", 0xffffff, NONE, Cr)).asGas();
    public static Material Copper = AntimatterAPI.register(Material.class, new Material(Ref.ID, "copper", 0xff6400, SHINY, Cu)).asMetal(1357, 0, PLATE, ROD, GEAR).asOre(1, 5, true);
    public static Material Deuterium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "deuterium", 0xffff00, NONE, D)).asGas();
    public static Material Fluorine = AntimatterAPI.register(Material.class, new Material(Ref.ID, "fluorine", 0xffffff, NONE, F)).asGas();
    public static Material Hydrogen = AntimatterAPI.register(Material.class, new Material(Ref.ID, "hydrogen", 0x0000ff, NONE, H)).asGas(15);
    public static Material Helium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "helium", 0xffff00, NONE, He)).asPlasma();
    public static Material Helium3 = AntimatterAPI.register(Material.class, new Material(Ref.ID, "helium_3", 0xffffff, NONE, He_3)).asGas();
    public static Material Lithium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "lithium", 0xe1dcff, DULL, Li)).asMetal(454, 0);
    public static Material Magnesium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "magnesium", 0xffc8c8, METALLIC, Mg)).asMetal(923, 0);
    public static Material Mercury = AntimatterAPI.register(Material.class, new Material(Ref.ID, "mercury", 0xffdcdc, SHINY, Hg)).asFluid();
    public static Material Nitrogen = AntimatterAPI.register(Material.class, new Material(Ref.ID, "nitrogen", 0x0096c8, NONE, N)).asPlasma();
    public static Material Oxygen = AntimatterAPI.register(Material.class, new Material(Ref.ID, "oxygen", 0x0064c8, NONE, O)).asPlasma();
    public static Material Phosphor = AntimatterAPI.register(Material.class, new Material(Ref.ID, "phosphor", 0xffff00, DULL, P)).asDust(317);
    public static Material Potassium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "potassium", 0xfafafa, METALLIC, K)).asMetal(336, 0);
    public static Material Silicon = AntimatterAPI.register(Material.class, new Material(Ref.ID, "silicon", 0x3c3c50, METALLIC, Si)).asMetal(1687, 1000, PLATE);
    public static Material Sodium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sodium", 0x000096, METALLIC, Na)).asDust(370);
    public static Material Sulfur = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sulfur", 0xc8c800, DULL, S)).asDust(388).asPlasma();
    public static Material Tin = AntimatterAPI.register(Material.class, new Material(Ref.ID, "tin", 0xdcdcdc, DULL, Sn)).asMetal(505, 505, PLATE, ROD, GEAR).asOre(1, 5, true);
    public static Material Tritium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "tritium", 0xff0000, METALLIC, T)).asGas();
    public static Material Zinc = AntimatterAPI.register(Material.class, new Material(Ref.ID, "zinc", 0xfaf0f0, METALLIC, Zn)).asMetal(692, 0, PLATE);
    public static Material Technetium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "technetium", 0xC8C8C8, METALLIC, Tc)).asMetal(PLATE);
    public static Material Neon = AntimatterAPI.register(Material.class, new Material(Ref.ID, "neon", 0xFF6464, NONE, Ne)).asGas();

    //TODO: We can be more lenient about what fluids we have in, its not as bad as solids above, and we can stop them from showing in JEI (I think...)

    /** Gases **/
    public static Material WoodGas = AntimatterAPI.register(Material.class, new Material(Ref.ID, "wood_gas", 0xdecd87, NONE)).asGas(25);
    public static Material Methane = AntimatterAPI.register(Material.class, new Material(Ref.ID, "methane", 0xfac8fa, NONE)).asGas(128).mats(of(Carbon, 1, Hydrogen, 4));
    //public static Material Biogas = AntimatterAPI.register(Material.class, new Material(Ref.ID, "biogas", 0xa7984c, NONE).asGas(32);
    public static Material CarbonDioxide = AntimatterAPI.register(Material.class, new Material(Ref.ID, "carbon_dioxide", 0xa9d0f5, NONE)).asGas().mats(of(Carbon, 1, Oxygen, 2));
    //public static Material NobleGases = AntimatterAPI.register(Material.class, new Material(Ref.ID, "noble_gases", 0xc9e3fc, NONE).asGas()/*.setTemp(79, 0)*/.addComposition(of(CarbonDioxide, 21, Helium, 9, Methane, 3, Deuterium, 1));
    public static Material Air = AntimatterAPI.register(Material.class, new Material(Ref.ID, "air", 0xc9e3fc, NONE)).asGas().mats(of(Nitrogen, 40, Oxygen, 11, Argon, 1/*, NobleGases, 1*/));
    public static Material NitrogenDioxide = AntimatterAPI.register(Material.class, new Material(Ref.ID, "nitrogen_dioxide", 0x64afff, NONE)).asGas().mats(of(Nitrogen, 1, Oxygen, 2));
    public static Material NaturalGas = AntimatterAPI.register(Material.class, new Material(Ref.ID, "natural_gas", 0xffffff, NONE)).asGas(15);
    public static Material Propane = AntimatterAPI.register(Material.class, new Material(Ref.ID, "propane", 0xfae250, NONE)).asGas(48).mats(of(Carbon, 2, Hydrogen, 6));
    public static Material SulfurDioxide = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sulfur_dioxide", 0xc8c819, NONE)).asGas().mats(of(Sulfur, 1, Oxygen, 2));
    public static Material SulfurTrioxide = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sulfur_trioxide", 0xa0a014, NONE)).asGas()/*.setTemp(344, 1)*/.mats(of(Sulfur, 1, Oxygen, 3));
    public static Material NitricOxide = AntimatterAPI.register(Material.class, new Material(Ref.ID, "nitric_oxide", 0x7dc8f0, NONE)).asGas().mats(of(Nitrogen, 1, Oxygen, 1));

    /** Fluids **/
    public static Material Lava = AntimatterAPI.register(Material.class, new Material(Ref.ID, "lava", 0xff4000, NONE)).asFluid(0, 1300);
    public static Material PahoehoeLava = AntimatterAPI.register(Material.class, new Material(Ref.ID, "pahoehoe_lava", 0xffffff, NONE)).asFluid(0, 1200);
    public static Material Water = AntimatterAPI.register(Material.class, new Material(Ref.ID, "water", 0x0000ff, NONE)).asFluid().mats(of(Hydrogen, 2, Oxygen, 1));
    public static Material Steam = AntimatterAPI.register(Material.class, new Material(Ref.ID, "steam", 0xa0a0a0, NONE)).asGas(0, 373);
    public static Material HotCoolant = AntimatterAPI.register(Material.class, new Material(Ref.ID, "hot_coolant", 0xe20000, NONE)).asFluid(0, 1200);
    public static Material ColdCoolant = AntimatterAPI.register(Material.class, new Material(Ref.ID, "cold_coolant", 0x01b2ed, NONE)).asFluid(0, 300);
    public static Material UUAmplifier = AntimatterAPI.register(Material.class, new Material(Ref.ID, "uu_amplifier", 0x600080, NONE)).asFluid();
    public static Material UUMatter = AntimatterAPI.register(Material.class, new Material(Ref.ID, "uu_matter", 0x8000c4, NONE)).asFluid();
    public static Material Lubricant = AntimatterAPI.register(Material.class, new Material(Ref.ID, "lubricant", 0xffc400, NONE)).asFluid();
    //public static Material WoodTar = AntimatterAPI.register(Material.class, new Material(Ref.ID, "wood_tar", 0x28170b, NONE).asFluid(; TODO: not sure if neede;
    public static Material DistilledWater = AntimatterAPI.register(Material.class, new Material(Ref.ID, "distilled_water", 0x5C5CFF, NONE)).asFluid().mats(of(Hydrogen, 2, Oxygen, 1));
    public static Material Glyceryl = AntimatterAPI.register(Material.class, new Material(Ref.ID, "glyceryl", 0x009696, NONE)).asFluid().mats(of(Carbon, 3, Hydrogen, 5, Nitrogen, 3, Oxygen, 9));
    public static Material SodiumPersulfate = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sodium_persulfate", 0x006646, NONE)).asFluid().mats(of(Sodium, 1, Sulfur, 1, Oxygen, 4));
    public static Material NitricAcid = AntimatterAPI.register(Material.class, new Material(Ref.ID, "nitric_acid", 0xe6e2ab, NONE)).asFluid().mats(of(Hydrogen, 1, Nitrogen, 1, Oxygen, 3));
    //public static Material HydrochloricAcid = AntimatterAPI.register(Material.class, new Material(Ref.ID, "hydrochloric_acid", 0x6f8a91, NONE).asFluid().mats(of(Hydrogen, 1, Chlorine, 1));
    public static Material SulfuricAcid = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sulfuric_acid", 0xff8000, NONE)).asFluid().mats(of(Hydrogen, 2, Sulfur, 1, Oxygen, 4));
    public static Material NitroCarbon = AntimatterAPI.register(Material.class, new Material(Ref.ID, "nitro_carbon", 0x1f5e5e, NONE)).asFluid().mats(of(Nitrogen, 1, Carbon, 1));
    public static Material Honey = AntimatterAPI.register(Material.class, new Material(Ref.ID, "honey", 0xfac800, NONE)).asFluid();

    /** Fuels **/
    public static Material Diesel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "diesel", 0xffff00, NONE)).asFluid(128);
    public static Material Gasoline = AntimatterAPI.register(Material.class, new Material(Ref.ID, "gasoline", 0x84723e, NONE)).asFluid(128);
    public static Material NitroDiesel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "nitro_diesel", 0xc8ff00, NONE)).asFluid(384);
    public static Material BioDiesel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "bio_diesel", 0xff8000, NONE)).asFluid(192);
    public static Material Biomass = AntimatterAPI.register(Material.class, new Material(Ref.ID, "biomass", 0x00ff00, NONE)).asFluid(8);
    //public static Material Biofuel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "biofuel", 0x99cc00, NONE).asFluid(6);
    public static Material Ethanol = AntimatterAPI.register(Material.class, new Material(Ref.ID, "ethanol", 0xff8000, NONE)).asFluid(128).mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 1));
    public static Material Creosote = AntimatterAPI.register(Material.class, new Material(Ref.ID, "creosote", 0x804000, NONE)).asFluid(8);
    public static Material Naphtha = AntimatterAPI.register(Material.class, new Material(Ref.ID, "naphtha", 0xffff64, NONE)).asFluid(128);
    public static Material NitroCoalFuel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "nitro-coalfuel", 0x002b2b, NONE)).asFluid(48);
    public static Material CoalFuel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "coalfuel", 0x0f0f0f, NONE)).asFluid(16);
    public static Material FishOil = AntimatterAPI.register(Material.class, new Material(Ref.ID, "fish_oil", 0xffc400, NONE)).asFluid(6);
    public static Material Oil = AntimatterAPI.register(Material.class, new Material(Ref.ID, "oil", 0x0a0a0a, NONE)).asFluid(15);
    public static Material SeedOil = AntimatterAPI.register(Material.class, new Material(Ref.ID, "seed_oil", 0xc4ff00, NONE)).asFluid(6);
    //public static Materials SeedOilHemp = new Materials(722, "Hemp Seed Oil", 196, 255, 0, lime, NONE).asSemi(2;
    //public static Materials SeedOilLin = new Materials(723, "Lin Seed Oil", 196, 255, 0, lime, NONE).asSemi(2;
    public static Material Glycerol = AntimatterAPI.register(Material.class, new Material(Ref.ID, "glycerol", 0x87de87, NONE)).asFluid(160).mats(of(Carbon, 3, Hydrogen, 8, Oxygen, 3));

    /** Dusts **/
    public static Material Sugar = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sugar", 0xfafafa, DULL)).asDust();
    public static Material SodiumSulfide = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sodium_sulfide", 0xffe680, NONE)).asDust().mats(of(Sodium, 2, Sulfur, 1));
    public static Material PlatinumGroupSludge = AntimatterAPI.register(Material.class, new Material(Ref.ID, "platinum_group_sludge", 0x001e00, NONE)).asDust();
    public static Material Glowstone = AntimatterAPI.register(Material.class, new Material(Ref.ID, "glowstone", 0xffff00, SHINY)).asDust();
    //public static Material Oilsands = AntimatterAPI.register(Material.class, new Material(Ref.ID, "oilsands", 0x0a0a0a, NONE).asDust(ORE);
    public static Material RareEarth = AntimatterAPI.register(Material.class, new Material(Ref.ID, "rare_earth", 0x808064, FINE)).asDust();
    public static Material Almandine = AntimatterAPI.register(Material.class, new Material(Ref.ID, "almandine", 0xff0000, ROUGH)).asDust().mats(of(Aluminium, 2, Iron, 3, Silicon, 3, Oxygen, 12));
    public static Material Andradite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "andradite", 0x967800, ROUGH)).asDust().mats(of(Calcium, 3, Iron, 2, Silicon, 3, Oxygen, 12));
    public static Material Ash = AntimatterAPI.register(Material.class, new Material(Ref.ID, "ash", 0x969696, DULL)).asDust();
    public static Material Calcite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "calcite", 0xfae6dc, DULL)).asDust().mats(of(Calcium, 1, Carbon, 1, Oxygen, 3));
    public static Material Cassiterite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "cassiterite", 0xdcdcdc, METALLIC)).asOre(1, 5, true).mats(of(Tin, 1, Oxygen, 2)).setOreMulti(2);
    public static Material Chromite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "chromite", 0x23140F, DULL)).asOre(1, 5, true).mats(of(Iron, 1, Chrome, 2, Oxygen, 4)).harvestLevel(3);
    public static Material Clay = AntimatterAPI.register(Material.class, new Material(Ref.ID, "clay", 0xc8c8dc, ROUGH)).asDust().mats(of(Sodium, 2, Lithium, 1, Aluminium, 2, Silicon, 2));
    public static Material DarkAsh = AntimatterAPI.register(Material.class, new Material(Ref.ID, "dark_ash", 0x323232, DULL)).asDust().mats(of(Carbon, 2));
    public static Material Energium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "energium", 0xff0000, DIAMOND)).asDust();
    public static Material Galena = AntimatterAPI.register(Material.class, new Material(Ref.ID, "galena", 0x643c64, DULL)).asOre(1, 5, true).mats(of(Lead, 3, Silver, 3, Sulfur, 2)).harvestLevel(2);
    public static Material Grossular = AntimatterAPI.register(Material.class, new Material(Ref.ID, "grossular", 0xc86400, ROUGH)).asDust().mats(of(Calcium, 3, Aluminium, 2, Silicon, 3, Oxygen, 12));
    public static Material Magnesite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "magnesite", 0xfafab4, METALLIC)).asDust().mats(of(Magnesium, 1, Carbon, 1, Oxygen, 3));
    public static Material Obsidian = AntimatterAPI.register(Material.class, new Material(Ref.ID, "obsidian", 0x503264, DULL)).asDust().mats(of(Magnesium, 1, Iron, 1, Silicon, 2, Oxygen, 8));
    public static Material Phosphate = AntimatterAPI.register(Material.class, new Material(Ref.ID, "phosphate", 0xffff00, DULL)).asDust().mats(of(Phosphor, 1, Oxygen, 4));
    public static Material Pyrite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "pyrite", 0x967828, ROUGH)).asOre(1, 5,true).mats(of(Iron, 1, Sulfur, 2)).setOreMulti(2);
    public static Material Pyrope = AntimatterAPI.register(Material.class, new Material(Ref.ID, "pyrope", 0x783264, METALLIC)).asDust().mats(of(Aluminium, 2, Magnesium, 3, Silicon, 3, Oxygen, 12));
    public static Material Saltpeter = AntimatterAPI.register(Material.class, new Material(Ref.ID, "saltpeter", 0xe6e6e6, FINE)).asDust().mats(of(Potassium, 1, Nitrogen, 1, Oxygen, 3));
    public static Material SiliconDioxide = AntimatterAPI.register(Material.class, new Material(Ref.ID, "silicon_dioxide", 0xc8c8c8, QUARTZ)).asDust().mats(of(Silicon, 1, Oxygen, 2));
    public static Material SodiumHydroxide = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sodium_hydroxide", 0x003380, DULL)).asDust().mats(of(Sodium, 1, Oxygen, 1, Hydrogen, 1));
    public static Material Brick = AntimatterAPI.register(Material.class, new Material(Ref.ID, "brick", 0x9b5643, ROUGH)).asDust().mats(of(Aluminium, 4, Silicon, 3, Oxygen, 12));
    public static Material Fireclay = AntimatterAPI.register(Material.class, new Material(Ref.ID, "fireclay", 0xada09b, ROUGH)).asDust().mats(of(Brick, 1));
    public static Material Spessartine = AntimatterAPI.register(Material.class, new Material(Ref.ID, "spessartine", 0xff6464, DULL)).asDust().mats(of(Aluminium, 2, Manganese, 3, Silicon, 3, Oxygen, 12));
    public static Material Sphalerite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sphalerite", 0xffffff, DULL)).asOre(1, 5,true).mats(of(Zinc, 1, Sulfur, 1));
    public static Material Tetrahedrite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "tetrahedrite", 0xc82000, DULL)).asOre(1, 5, true).mats(of(Copper, 3, Antimony, 1, Sulfur, 3, Iron, 1));
    public static Material Tungstate = AntimatterAPI.register(Material.class, new Material(Ref.ID, "tungstate", 0x373223, DULL)).asOre(1, 5, true).mats(of(Tungsten, 1, Lithium, 2, Oxygen, 4)).setOreMulti(2).harvestLevel(2);
    public static Material Uraninite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "uraninite", 0x232323, DULL)).asOre(1, 5, true).mats(of(Uranium238, 1, Oxygen, 2)).harvestLevel(2);
    public static Material Bauxite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "bauxite", 0xc86400, DULL)).asOre(1, 5, true).mats(of(Aluminium, 16, Hydrogen, 10, Oxygen, 11, Titanium, 1)).setOreMulti(2);
    public static Material Wood = AntimatterAPI.register(Material.class, new Material(Ref.ID, "wood", 0x643200, WOOD)).asDust(PLATE).addTools(ItemTier.WOOD.getAttackDamage(), ItemTier.WOOD.getEfficiency(), 16, ItemTier.WOOD.getHarvestLevel(), of(), SOFT_HAMMER).addHandleStat(12, 0.0F).mats(of(Carbon, 1, Oxygen, 1, Hydrogen, 1));
    public static Material Blaze = AntimatterAPI.register(Material.class, new Material(Ref.ID, "blaze", 0xffc800, NONE)).asDust().mats(of(Sulfur, 1, DarkAsh, 1/*, Magic, 1*/)).addHandleStat(-10, -0.5F, of(Enchantments.FIRE_ASPECT, 1));
    public static Material Flint = AntimatterAPI.register(Material.class, new Material(Ref.ID, "flint", 0x002040, FLINT)).asDust(GEM).addTools(1.25F, 2.5F, 128, 1, of(Enchantments.FIRE_ASPECT, 1), PICKAXE, AXE, SHOVEL, SWORD, HOE, MORTAR, KNIFE, ToolTypes.SPEAR).mats(of(SiliconDioxide, 1));
    public static Material PotassiumFeldspar = AntimatterAPI.register(Material.class, new Material(Ref.ID, "potassium_feldspar", 0x782828, FINE)).asDust().mats(of(Potassium, 1, Aluminium, 1, Silicon, 3, Oxygen, 8));
    public static Material Biotite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "biotite", 0x141e14, METALLIC)).asDust().mats(b -> b.put(Potassium, 1).put(Magnesium, 3).put(Aluminium, 3).put(Fluorine, 2).put(Silicon, 3).put(Oxygen, 10));
    public static Material Uvarovite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "uvarovite", 0xb4ffb4, DIAMOND)).asDust().mats(of(Calcium, 3, Chrome, 2, Silicon, 3, Oxygen, 12));

    /** Gems **/
    //Brittle Gems
    public static Material CoalCoke = AntimatterAPI.register(Material.class, new Material(Ref.ID, "coal_coke", 0x8c8caa, LIGNITE)).asGemBasic(false);
    public static Material Charcoal = AntimatterAPI.register(Material.class, new Material(Ref.ID, "charcoal", 0x644646, LIGNITE)).asDust(BLOCK).mats(of(Carbon, 1));
    public static Material Coal = AntimatterAPI.register(Material.class, new Material(Ref.ID, "coal", 0x464646, LIGNITE)).asGemBasic(false).asOre(0, 2, true, RAW_ORE).mats(of(Carbon, 2));

    public static Material Diamond = AntimatterAPI.register(Material.class, new Material(Ref.ID, "diamond", /*0x3de0e5*/0xc8ffff, DIAMOND)).asGemBasic(false).asOre(3, 7,true, RAW_ORE).mats(of(Carbon, 128)).addTools(ItemTier.DIAMOND.getAttackDamage(), ItemTier.DIAMOND.getEfficiency(), ItemTier.DIAMOND.getMaxUses(), ItemTier.DIAMOND.getHarvestLevel());
    public static Material Emerald = AntimatterAPI.register(Material.class, new Material(Ref.ID, "emerald", 0x50ff50, GEM_V)).asGemBasic(false).asOre(3, 7,true, RAW_ORE).mats(of(Beryllium, 3, Aluminium, 2, Silicon, 3, Oxygen, 18));
    public static Material Lazurite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "lazurite", 0x6478ff, LAPIS)).asDust().mats(of(Aluminium, 3, Silicon, 3, Calcium, 4, Sodium, 4));
    public static Material Ruby = AntimatterAPI.register(Material.class, new Material(Ref.ID, "ruby", 0xff6464, RUBY)).asGemBasic(false).asOre(3, 7,true, RAW_ORE).addTools(3.0F, 7.0F, 1024, 3).addArmor(new int[]{1, 1, 2, 1}, 1.0F, 0.0F, 30).mats(of(Chrome, 1, Aluminium, 2, Oxygen, 3));
    public static Material Sapphire = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sapphire", 0x6464c8, GEM_V)).asGemBasic(false).asOre(3, 7,true, RAW_ORE).addTools(3.0F, 7.0F, 1024, 3).addArmor(new int[]{1, 1, 2, 1}, 1.0F, 0.0F, 30).mats(of(Aluminium, 2, Oxygen, 3));
    public static Material Sodalite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sodalite", 0x1414ff, LAPIS)).asGemBasic(false).asOre(1, 5,true, RAW_ORE).mats(of(Aluminium, 3, Silicon, 3, Sodium, 4, Chlorine, 1)).setOreMulti(6).harvestLevel(2);
    //public static Material Glass = AntimatterAPI.register(Material.class, new Material(Ref.ID, "glass", 0xfafafa, SHINY)).asDust(PLATE, LENS).mats(of(SiliconDioxide, 1));
    public static Material Olivine = AntimatterAPI.register(Material.class, new Material(Ref.ID, "olivine", 0x96ff96, RUBY)).asGemBasic(false).asOre(3, 7,true, RAW_ORE).mats(of(Magnesium, 2, Iron, 1, Silicon, 1, Oxygen, 4)).harvestLevel(3);
    public static Material EnderPearl = AntimatterAPI.register(Material.class, new Material(Ref.ID, "enderpearl", 0x6cdcc8, SHINY)).asGemBasic(false).mats(of(Beryllium, 1, Potassium, 4, Nitrogen, 5, Chlorine, 6));
    public static Material EnderEye = AntimatterAPI.register(Material.class, new Material(Ref.ID, "endereye", 0xa0fae6, SHINY)).asGemBasic(false).mats(of(EnderPearl, 1, Blaze, 1));
    public static Material Lapis = AntimatterAPI.register(Material.class, new Material(Ref.ID, "lapis", 0x4646dc, LAPIS)).asGemBasic(false).asOre(2, 5,true, RAW_ORE).mats(of(Lazurite, 12, Sodalite, 2, Pyrite, 1, Calcite, 1)).setOreMulti(6);
    //public static Material Phosphorus = AntimatterAPI.register(Material.class, new Material(Ref.ID, "phosphorus", 0xffff00, FLINT).asDust().mats(of(Calcium, 3, Phosphate, 2));
    public static Material RedGarnet = AntimatterAPI.register(Material.class, new Material(Ref.ID, "red_garnet", 0xc85050, GARNET)).asGemBasic(false).mats(of(Pyrope, 3, Almandine, 5, Spessartine, 8));
    public static Material YellowGarnet = AntimatterAPI.register(Material.class, new Material(Ref.ID, "yellow_garnet", 0xc8c850, GARNET)).asGemBasic(false).mats(of(Uvarovite, 3, Andradite, 5, Grossular, 8));

    public static Material Amethyst = AntimatterAPI.register(Material.class, new Material(Ref.ID, "amethyst", 0xd232d2, RUBY, Ref.MOD_BLUEPOWER)).asGemBasic(false).asOre(3, 7,true, RAW_ORE).addTools(3.0F, 7.0F, 1024, 3).addArmor(new int[]{1, 1, 2, 1}, 1.0F, 0.0F, 30).mats(of(SiliconDioxide, 4, Iron, 1));

    /** **/
    public static Material Redstone = AntimatterAPI.register(Material.class, new Material(Ref.ID, "redstone", 0xc80000, REDSTONE)).asDust().asOre(1, 5,true).mats(of(Silicon, 1, Pyrite, 5, Ruby, 1, Mercury, 3)).setOreMulti(4);
    public static Material Cinnabar = AntimatterAPI.register(Material.class, new Material(Ref.ID, "cinnabar", 0x960000, REDSTONE)).asDust().asOre(1, 5,true).mats(of(Mercury, 1, Sulfur, 1)).setOreMulti(2).harvestLevel(2);

    /** Metals **/
    public static Material BatteryAlloy = AntimatterAPI.register(Material.class, new Material(Ref.ID, "battery_alloy", 0x9c7ca0, DULL)).asMetal(295, 0, PLATE).mats(of(Lead, 4, Antimony, 1));
    public static Material Brass = AntimatterAPI.register(Material.class, new Material(Ref.ID, "brass", 0xffb400, METALLIC)).asMetal(1170, 0,  PLATE, ROD).mats(of(Zinc, 1, Copper, 3));
    public static Material Bronze = AntimatterAPI.register(Material.class, new Material(Ref.ID, "bronze", 0xff8000, METALLIC)).asMetal(1125, 0,  PLATE, ROD, GEAR).mats(of(Tin, 1, Copper, 3)).addTools(2.0F, 6.0F, 192, 2,  of(Enchantments.SHARPNESS, 1)).addArmor(new int[]{0, 0, 0, 0}, 0.0F, 0.0F, 12);
    public static Material Cupronickel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "cupronickel", 0xe39680, METALLIC)).asMetal(1728, 0, PLATE).mats(of(Copper, 1, Nickel, 1));
    public static Material Electrum = AntimatterAPI.register(Material.class, new Material(Ref.ID, "electrum", 0xffff64, SHINY)).asMetal(1330, 0, PLATE, ROD).mats(of(Silver, 1, Gold, 1));
    public static Material Invar = AntimatterAPI.register(Material.class, new Material(Ref.ID, "invar", 0xb4b478, METALLIC)).asMetal(1700, 0,  PLATE, ROD).mats(of(Iron, 2, Nickel, 1)).addTools(Iron, of(Enchantments.BANE_OF_ARTHROPODS, 3)).addArmor(new int[]{0, 0, 0,0}, 0.0F, 0.0F, 15, of(Enchantments.FIRE_PROTECTION, 1));
    public static Material Kanthal = AntimatterAPI.register(Material.class, new Material(Ref.ID, "kanthal", 0xc2d2df, METALLIC)).asMetal(1800, 2200,  PLATE, ROD).mats(of(Iron, 1, Aluminium, 1, Chrome, 1));
    public static Material Magnalium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "magnalium", 0xc8beff, DULL)).asMetal(870, 0,  PLATE, ROD).mats(of(Magnesium, 1, Aluminium, 2));
    public static Material Nichrome = AntimatterAPI.register(Material.class, new Material(Ref.ID, "nichrome", 0xcdcef6, METALLIC)).asMetal(2700, 2500).mats(of(Nickel, 4, Chrome, 1));
    public static Material SolderingAlloy = AntimatterAPI.register(Material.class, new Material(Ref.ID, "soldering_alloy", 0xdcdce6, DULL)).asMetal(400, 400, PLATE, ROD).mats(of(Tin, 9, Antimony, 1));
    public static Material Steel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "steel", 0x808080, METALLIC)).asMetal(1811, 1500, PLATE, ROD, GEAR, RING).addTools(2.5F, 6.0F, 512, 2,  of(Enchantments.SHARPNESS, 2)).addArmor(new int[]{0, 1, 1, 0}, 1.0F, 0.0F, 21, of(Enchantments.PROTECTION, 1)).mats(of(Iron, 50, Carbon, 1));
    public static Material StainlessSteel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "stainless_steel", 0xc8c8dc, SHINY)).asMetal(1700, 1700, PLATE, ROD, GEAR).mats(of(Iron, 6, Chrome, 1, Manganese, 1, Nickel, 1)).addTools(2.5F, 7.0F, 480, 2, of(Enchantments.SHARPNESS, 3)).addArmor(new int[]{0, 1, 1, 0}, 2.0F, 0.0F, 20, of(Enchantments.PROTECTION, 2));
    public static Material WroughtIron = AntimatterAPI.register(Material.class, new Material(Ref.ID, "wrought_iron", 0xc8b4b4, METALLIC)).asMetal(1811, 0, PLATE, ROD, GEAR).mats(of(Iron, 1)).addTools(IRON.getAttackDamage(), IRON.getEfficiency(), (int)(256 * 1.5F), IRON.getHarvestLevel(),  of(Enchantments.SHARPNESS, 2)).addArmor(new int[]{0, 0, 0, 0}, 1.0F, 0.0F, 17, of(Enchantments.PROTECTION, 1));
    public static Material TungstenSteel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "tungstensteel", 0x6464a0, METALLIC)).asMetal(3000, 3000, PLATE, ROD, GEAR).addTools(6.0F, 10.0F, 5120, 4).mats(of(Steel, 1, Tungsten, 1)).addArmor(new int[]{1, 2, 3, 1}, 3.0F, 0.0F, 18, of(Enchantments.PROTECTION, 3));
    public static Material TungstenCarbide = AntimatterAPI.register(Material.class, new Material(Ref.ID, "tungsten_carbide", 0x330066, METALLIC)).asMetal(2460, 2460).addTools(5.0F, 14.0F, 2560, 4, of(Enchantments.SHARPNESS, 5)).mats(of(Tungsten, 1, Carbon, 1));
    public static Material RedAlloy = AntimatterAPI.register(Material.class, new Material(Ref.ID, "red_alloy", 0xc80000, DULL)).asMetal(295, 0, PLATE, ROD).mats(of(Copper, 1, Redstone, 4));
    public static Material Osmiridium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "osmiridium", 0x6464ff, METALLIC)).asMetal(3333, 3300,  PLATE, ROD).mats(of(Iridium, 1, Osmium, 1));
    public static Material IronMagnetic = AntimatterAPI.register(Material.class, new Material(Ref.ID, "magnetic_iron", 0xc8c8c8, MAGNETIC)).asMetal(1811, 0,ROD).addTools(Iron).mats(of(Iron, 1));
    public static Material SteelMagnetic = AntimatterAPI.register(Material.class, new Material(Ref.ID, "magnetic_steel", 0x808080, MAGNETIC)).asMetal(1000, 1000,ROD).addTools(Steel).mats(of(Steel, 1));

    /** TFC Materials **/
    public static Material SterlingSilver = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sterling_silver", 0xfadce1, SHINY, Ref.MOD_TFC)).asMetal(1700, 1700).mats(of(Copper, 1, Silver, 4));
    public static Material RoseGold = AntimatterAPI.register(Material.class, new Material(Ref.ID, "rose_gold", 0xffe61e, SHINY, Ref.MOD_TFC)).asMetal(1600, 1600).mats(of(Copper, 1, Gold, 4));
    public static Material BlackBronze = AntimatterAPI.register(Material.class, new Material(Ref.ID, "black_bronze", 0x64327d, DULL, Ref.MOD_TFC)).asMetal(2000, 2000).addTools(Bronze, of(Enchantments.SWEEPING, 1)).mats(of(Gold, 1, Silver, 1, Copper, 3));
    public static Material BismuthBronze = AntimatterAPI.register(Material.class, new Material(Ref.ID, "bismuth_bronze", 0x647d7d, DULL, Ref.MOD_TFC)).asMetal(1100, 900, PLATE).addTools(2.5F, Bronze.getToolSpeed() + 2.0F, 350, 2, of(Enchantments.BANE_OF_ARTHROPODS, 4)).mats(of(Bismuth, 1, Zinc, 1, Copper, 3));
    public static Material BlackSteel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "black_steel", 0x646464, METALLIC, Ref.MOD_TFC)).asMetal(1200, 1200, FRAME, PLATE).addTools(3.5F, 6.5F, 768, 2).mats(of(Nickel, 1, BlackBronze, 1, Steel, 3));
    public static Material RedSteel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "red_steel", 0x8c6464, METALLIC, Ref.MOD_TFC)).asMetal(1300, 1300).addTools(3.5F, 7.0F, 896, 2).mats(of(SterlingSilver, 1, BismuthBronze, 1, Steel, 2, BlackSteel, 4));
    public static Material BlueSteel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "blue_steel", 0x64648c, METALLIC, Ref.MOD_TFC)).asMetal(1400, 1400, FRAME).addTools(3.5F, 7.5F, 1024, 2).mats(of(RoseGold, 1, Brass, 1, Steel, 2, BlackSteel, 4));

    /** Solids (Plastic Related Stuff)**/
    public static Material Bone = AntimatterAPI.register(Material.class, new Material(Ref.ID, "bone", 0xb3b3b3, DULL)).addHandleStat(12, 0.0F);
    public static Material Plastic = AntimatterAPI.register(Material.class, new Material(Ref.ID, "plastic", 0xc8c8c8, DULL)).asSolid(295, 0, PLATE, RUBBERTOOLS).addTools(0.0F, 0.0F, 64, 0, of(), SOFT_HAMMER).addHandleStat(66, 0.5F).mats(of(Carbon, 1, Hydrogen, 2));
    public static Material Rubber = AntimatterAPI.register(Material.class, new Material(Ref.ID, "rubber", 0x000000, SHINY)).asSolid(295, 0, PLATE, RUBBERTOOLS).addTools(0.0F, 0.0F, 64, 0, of(), SOFT_HAMMER).addHandleStat(11, 0.4F).mats(of(Carbon, 5, Hydrogen, 8));

    /** Stones **/
    public static Material RedGranite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "red_granite", 0xff0080, ROUGH)).asDust(ROCK).addHandleStat(74, 1.0F, of(Enchantments.UNBREAKING, 1)).mats(of(Aluminium, 2, PotassiumFeldspar, 1, Oxygen, 3));
    public static Material BlackGranite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "black_granite", 0x0a0a0a, ROUGH)).asDust(ROCK).addHandleStat(74, 1.0F, of(Enchantments.UNBREAKING, 1)).mats(of(SiliconDioxide, 4, Biotite, 1));
    public static Material Marble = AntimatterAPI.register(Material.class, new Material(Ref.ID, "marble", 0xc8c8c8, NONE)).asDust(ROCK).mats(of(Magnesium, 1, Calcite, 7));
    public static Material Basalt = AntimatterAPI.register(Material.class, new Material(Ref.ID, "basalt", 0x1e1414, ROUGH)).asDust(ROCK).mats(of(Olivine, 1, Calcite, 3, Flint, 8, DarkAsh, 4));
    public static Material Komatiite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "komatiite", 0xbebe69, NONE)).asDust(ROCK).mats(of(Olivine, 1, Magnesite, 2, Flint, 6, DarkAsh, 3));
    public static Material Limestone = AntimatterAPI.register(Material.class, new Material(Ref.ID, "limestone", 0xe6c882, NONE)).asDust(ROCK).mats(of(Calcite, 1));
    public static Material GreenSchist = AntimatterAPI.register(Material.class, new Material(Ref.ID, "green_schist", 0x69be69, NONE)).asDust(ROCK);
    public static Material BlueSchist = AntimatterAPI.register(Material.class, new Material(Ref.ID, "blue_schist", 0x0569be, NONE)).asDust(ROCK);
    public static Material Kimberlite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "kimberlite", 0x64460a, NONE)).asDust(ROCK);
    public static Material Quartzite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "quartzite", 0xe6cdcd, QUARTZ)).asGemBasic(false, ROCK).mats(of(Silicon, 1, Oxygen, 2));
    public static Material Prismarine = AntimatterAPI.register(Material.class, new Material(Ref.ID, "prismarine", 0x6eb2a5, NONE)).asDust().mats(of(Potassium, 2, Oxygen, 8, Manganese, 1, Silicon, 5));
    public static Material DarkPrismarine = AntimatterAPI.register(Material.class, new Material(Ref.ID, "dark_prismarine", 0x587d6c, NONE)).asDust();


    public static Material Scoria = AntimatterAPI.register(Material.class, new Material(Ref.ID, "scoria", 0x1e1414, ROUGH, Ref.MOD_CREATE)).asDust().mats(of(SiliconDioxide, 6, Calcium, 1, Carbon, 1, Iron, 1));

    /** Ore Stones **/
    public static Material Salt = AntimatterAPI.register(Material.class, new Material(Ref.ID, "salt", 0xfafafa, FINE)).asDust(ORE_STONE).setExpRange(1, 5).mats(of(Sodium, 1, Chlorine, 1));
    public static Material RockSalt = AntimatterAPI.register(Material.class, new Material(Ref.ID, "rock_salt", 0xf0c8c8, FINE)).asDust(ORE_STONE).setExpRange(1, 5).mats(of(Potassium, 1, Chlorine, 1));
    //public static Material OilShale = AntimatterAPI.register(Material.class, new Material(Ref.ID, "oil_shale", 0x32323c, NONE)).asDust(ORE_STONE);



    /** Reference Materials **/
    public static Material Superconductor = AntimatterAPI.register(Material.class, new Material(Ref.ID, "superconductor", 0xffffff, NONE));
    public static Material HighPressure = AntimatterAPI.register(Material.class, new Material(Ref.ID, "high_pressure", 0xc80000, NONE));
    public static Material HighCapacity = AntimatterAPI.register(Material.class, new Material(Ref.ID, "high_capacity", 0xb00b69, NONE));
    public static Material PlasmaContainment = AntimatterAPI.register(Material.class, new Material(Ref.ID, "plasma_containment", 0xffff00, NONE));
    public static Material Quartz = AntimatterAPI.register(Material.class, new Material(Ref.ID, "quartz", 0xffffff, NONE)).asDust();

    /** VANILLA **/
    public static Material Netherite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "netherite", 0x504650, DULL)).asMetal(2246,1300, PLATE, ROD).addTools(3.0F, 10, 500, NETHERITE.getHarvestLevel(), of(Enchantments.FIRE_ASPECT, 3)).addArmor(new int[]{0, 1, 1, 0}, 0.5F, 0.1F, 20);
    public static Material NetherizedDiamond = AntimatterAPI.register(Material.class, new Material(Ref.ID, "netherized_diamond", 0x5a505a, DIAMOND)).asGemBasic(false).addTools(4.0F, 12, NETHERITE.getMaxUses(), NETHERITE.getHarvestLevel(), of(Enchantments.FIRE_ASPECT, 3, Enchantments.SHARPNESS, 4)).addArmor(new int[]{1, 1, 2, 1}, 3.0F, 0.1F, 37, of(Enchantments.PROTECTION, 4));
    public static Material NetheriteScrap = AntimatterAPI.register(Material.class, new Material(Ref.ID, "netherite_scrap", 0x6e505a, ROUGH)).asDust(CRUSHED, CRUSHED_PURIFIED, CRUSHED_CENTRIFUGED, DUST_IMPURE, DUST_PURE);


    static {
        /*ELEC.add(Methane, CarbonDioxide, NitrogenDioxide, Toluene, VinylChloride, SulfurDioxide, SulfurTrioxide, Dimethylamine, DinitrogenTetroxide, NitricOxide, Ammonia, Chloromethane, Tetrafluoroethylene, CarbonMonoxide, Ethylene, Propane, Ethenone, Ethanol, Glyceryl, SodiumPersulfate, Dichlorobenzene, Styrene, Isoprene, Tetranitromethane, Epichlorohydrin, NitricAcid, Dimethylhydrazine, Chloramine, Dimethyldichlorosilane, HydrofluoricAcid, Chloroform, BisphenolA, AceticAcid, Acetone, Methanol, VinylAcetate, MethylAcetate, AllylChloride, HypochlorousAcid, Cumene, PhosphoricAcid, SulfuricAcid, Benzene, Phenol, Glycerol, SodiumSulfide, Almandine, Andradite, BandedIron, Calcite, Cassiterite, Chalcopyrite, Cobaltite, Galena, Garnierite, Grossular, Bauxite, Magnesite, Magnetite, Molybdenite, Obsidian, Phosphate, Polydimethylsiloxane, Pyrite, Pyrolusite, Pyrope, RockSalt, Saltpeter, SiliconDioxide, Massicot, ArsenicTrioxide, CobaltOxide, Magnesia, Quicklime, Potash, SodaAsh, PhosphorousPentoxide, SodiumHydroxide, Spessartine, Sphalerite, Uvarovite, PotassiumFeldspar, Biotite, RedGranite, Bastnasite, Pentlandite, Spodumene, Glauconite, Bentonite, Malachite, Barite, Talc, AntimonyTrioxide, CupricOxide, Ferrosilite, Quartzite, Charcoal, Coal, Lignite, Diamond, Emerald, Ruby, Sapphire, Tanzanite, Topaz, Olivine, Opal, Amethyst, EnderPearl, StainlessSteel, Steel, Ultimet, IronMagnetic, SteelMagnetic, NeodymiumMagnetic, Osmiridium);
        CENT.add(*//*NobleGases, *//*Air, BrownLimonite, Cinnabar, Clay, Cooperite*//*, Powellite*//*, Stibnite, Tetrahedrite, Uraninite, Wulfenite, YellowLimonite, Blaze, Flint, Marble, BlackGranite, VanadiumMagnetite, Pitchblende, Glass, Lapis, EnderEye, Phosphorus, Redstone, Basalt, AnnealedCopper, BatteryAlloy, Brass, Bronze, Cupronickel, Electrum, Invar, Kanthal, Magnalium, Nichrome, NiobiumTitanium, SolderingAlloy, VanadiumGallium, WroughtIron, SterlingSilver, RoseGold, BismuthBronze, TungstenSteel, RedAlloy, CobaltBrass, TungstenCarbide, VanadiumSteel, HSSG, HSSE, HSSS, GalliumArsenide*//*, IndiumGalliumPhosphide, BorosilicateGlass*//*);
        RUBBERTOOLS.add(Rubber, StyreneButadieneRubber, Plastic, PolyvinylChloride, Polystyrene, Silicone);
        SOLDER.add(Lead, Tin, SolderingAlloy);
        //TODO Mercury.add(METALL, SMELTG);
*/

        /*if (!AntimatterAPI.isModLoaded("gtsp")){
            Flint.getToolTypes().add(ToolTypes.SPEAR);
        }*/

        ELEC30.add(Charcoal, Prismarine, Salt, RockSalt, Quartzite);
        ELEC60.add(Cassiterite, SodiumSulfide, Sapphire, SiliconDioxide, Methane, Pyrite, Sphalerite, NitrogenDioxide, Phosphate, Magnesite);
        ELEC90.add(Calcite, EnderPearl, SulfuricAcid, RedGranite, Saltpeter, Chromite, SodiumPersulfate, Glyceryl, Ruby, Olivine, Galena, Tungstate/* CalciumCarbonate*/);
        ELEC120.add(Emerald, Grossular, Clay, StainlessSteel, Sodalite, Bauxite, Obsidian, Pyrope, Uvarovite, Almandine, Andradite, Lazurite, Spessartine, PotassiumFeldspar, Biotite);
        ROCK_CUTTER.add(Diamond, Ruby, Sapphire, NetherizedDiamond, Amethyst);

        CABINET.add(Iron, Aluminium, WroughtIron, Brass, Cupronickel, Electrum, Gold, Silver, Magnalium, Platinum, Osmium);
        WORKBENCH.add(Bronze, Iron, Aluminium);
        CHARGING_WORKBENCH.add(Iron, Aluminium);
        LOCKER.add(Iron, Aluminium);
        CHARGING_LOCKER.add(Iron, Aluminium);
        Material[] turbineStuff = new Material[]{Carbon, Osmium, Bronze, Magnalium, Steel, TungstenSteel, Osmiridium};
        for (Material material : turbineStuff) {
            material.flags(TURBINE_ROTOR, TURBINE_BLADE, BROKEN_TURBINE_ROTOR);
        }
        Material[] hulls = new Material[]{Aluminium, Iron, Titanium, Brass, Bronze, Steel, StainlessSteel, WroughtIron, TungstenSteel};
        for (Material hull : hulls) {
            hull.flags(HULL);
        }
        SEMIFLUID.add(Biomass, Creosote, FishOil, Oil, SeedOil);
        FLINT_TAG.add(Flint);

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
        DUST.forceOverride(Sugar, Items.SUGAR);
        GEM.forceOverride(Flint, Items.FLINT);
        GEM.forceOverride(Diamond, Items.DIAMOND);
        GEM.forceOverride(Emerald, Items.EMERALD);
        GEM.forceOverride(Lapis, Items.LAPIS_LAZULI);
        GEM.forceOverride(Coal, Items.COAL);
        GEM.forceOverride(Charcoal, Items.CHARCOAL);
        GEM.forceOverride(EnderEye, Items.ENDER_EYE);
        GEM.forceOverride(EnderPearl, Items.ENDER_PEARL);


        BLOCK.forceOverride(Iron, Items.IRON_BLOCK);
        BLOCK.forceOverride(Gold, Items.GOLD_BLOCK);
        BLOCK.forceOverride(Diamond, Items.DIAMOND_BLOCK);
        BLOCK.forceOverride(Emerald, Items.EMERALD_BLOCK);
        BLOCK.forceOverride(Lapis, Items.LAPIS_BLOCK);
        BLOCK.forceOverride(Netherite, Items.NETHERITE_BLOCK);

        ROD.forceOverride(Blaze, Items.BLAZE_ROD);
        ROD.forceOverride(Bone, Items.BONE);
        ROD.forceOverride(Wood, Items.STICK);

        Lava.mats(of(Copper, 1, Tin, 1, Gold, 1, Silver, 1, Tungsten, 1));
        Granite.mats(of(Aluminium, 2, Flint, 1, Clay, 1));
        Glowstone.mats(of(Redstone, 8, Gold, 8, Helium, 1));
        Diorite.mats(of(Nickel, 1));

        //Bronze.remove(BOLT, SCREW);
        //Iron.remove(BOLT, SCREW);
        //IronMagnetic.remove(BOLT, SCREW);
        //SteelMagnetic.remove(BOLT, SCREW);
        //Steel.remove(BOLT, SCREW);
        //TungstenSteel.remove(BOLT, SCREW);
        //Titanium.remove(BOLT, SCREW);
        //Tungsten.remove(BOLT, SCREW);
        //Netherite.remove(BOLT, SCREW);
        //Rubber.remove(BOLT, SCREW);
        //Plastic.remove(BOLT, SCREW);
        //NetherizedDiamond.remove(BOLT, SCREW);
        //Invar.remove(BOLT, SCREW);
        //WroughtIron.remove(BOLT, SCREW);
        //TungstenCarbide.remove(BOLT, SCREW);
        //Carbon.remove(INGOT, NUGGET, BLOCK);
        //Lithium.remove(INGOT, NUGGET, BLOCK);

        Coal.remove(CRUSHED, CRUSHED_PURIFIED, CRUSHED_CENTRIFUGED, DUST_IMPURE, DUST_PURE);

        TOOLS.all().forEach(m -> {
            if (m != Flint && m != NULL && !m.has(RUBBERTOOLS) && m != Wood){
                m.flags(PICKAXE_HEAD, AXE_HEAD, SHOVEL_HEAD, SWORD_HEAD, HOE_HEAD, HAMMER_HEAD, FILE_HEAD, SAW_HEAD);
            }
        });

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
        /*LIQUID.all().stream().filter(l -> !l.equals(Water) || !l.equals(Lava)).forEach(m -> {
            if (m == HotCoolant || m == PahoehoeLava) {
                new AntimatterMaterialFluid(Ref.ID, m, LIQUID, prepareAttributes(Ref.ID, m, LIQUID), prepareProperties(m, LIQUID));
                return;
            }
            new AntimatterMaterialFluid(Ref.ID, m, LIQUID);
        });
        GAS.all().forEach(m -> new AntimatterMaterialFluid(Ref.ID, m, GAS));
        PLASMA.all().forEach(m -> new AntimatterMaterialFluid(Ref.ID, m, PLASMA));

        AntimatterAPI.all(Material.class, Material::setChemicalFormula);*/
    }

    private static FluidAttributes.Builder prepareAttributes(String domain, Material material, MaterialType<?> type) {
        FluidAttributes.Builder builder = FluidAttributes.builder(LIQUID_STILL_TEXTURE, LIQUID_FLOW_TEXTURE);
        if (material == PahoehoeLava) builder = FluidAttributes.builder(PAHOEHOE_STILL_TEXTURE, PAHOEHOE_STILL_TEXTURE);
        return builder.overlay(OVERLAY_TEXTURE).color((155 << 24) | (material.getRGB() & 0x00ffffff))
                .translationKey(String.join("", "block.", domain, type.getId(), ".", material.getId()))
                .viscosity(1000).density(1000).temperature(material.getLiquidTemperature());
    }

    private static Block.Properties prepareProperties(Material m, MaterialType<?> type) {
        return Block.Properties.create(net.minecraft.block.material.Material.WATER).hardnessAndResistance(100.0F).noDrops().setLightLevel(s -> type == Data.PLASMA ? 15 : m == PahoehoeLava ? 9: 0);
    }
}
