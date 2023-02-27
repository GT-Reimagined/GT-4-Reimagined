package trinsdar.gt4r.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.material.MaterialType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fluids.FluidAttributes;
import trinsdar.gt4r.Ref;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.fluid.AntimatterFluid.LIQUID_FLOW_TEXTURE;
import static muramasa.antimatter.fluid.AntimatterFluid.LIQUID_STILL_TEXTURE;
import static muramasa.antimatter.fluid.AntimatterFluid.OVERLAY_TEXTURE;
import static muramasa.antimatter.material.Element.*;
import static muramasa.antimatter.material.TextureSet.*;

public class Materials {

    public static final ResourceLocation PAHOEHOE_STILL_TEXTURE = new ResourceLocation(Ref.ID, "fluids/pahoehoe_lava");


    /** Elements **/
    public static Material Aluminium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "aluminium", 0x80c8f0, DULL, Al));
    public static Material Beryllium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "beryllium", 0x64b464, METALLIC, Be));
    public static Material Bismuth = AntimatterAPI.register(Material.class, new Material(Ref.ID, "bismuth", 0x64a0a0, METALLIC, Bi, Ref.MOD_TFC));
    public static Material Carbon = AntimatterAPI.register(Material.class, new Material(Ref.ID, "carbon", 0x141414, DULL, C));
    public static Material Chrome = AntimatterAPI.register(Material.class, new Material(Ref.ID, "chrome", 0xffe6e6, SHINY, Cr));
    public static Material Iridium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "iridium", 0xf0f0f5, DULL, Ir));
    public static Material Lead = AntimatterAPI.register(Material.class, new Material(Ref.ID, "lead", 0x8c648c, DULL, Pb));
    public static Material Manganese = AntimatterAPI.register(Material.class, new Material(Ref.ID, "manganese", 0xfafafa, DULL, Mn));
    public static Material Nickel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "nickel", 0xc8c8fa, METALLIC, Ni));
    public static Material Osmium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "osmium", 0x3232ff, METALLIC, Os));
    public static Material Platinum = AntimatterAPI.register(Material.class, new Material(Ref.ID, "platinum", 0xffffc8, SHINY, Pt));
    public static Material Plutonium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "plutonium_244", 0xf03232, METALLIC, Pu));
    public static Material Plutonium239 = AntimatterAPI.register(Material.class, new Material(Ref.ID, "plutonium_239", 0xf03232, METALLIC, Pu239));
    public static Material Silver = AntimatterAPI.register(Material.class, new Material(Ref.ID, "silver", 0xdcdcff, SHINY, Ag));
    public static Material Thorium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "thorium", 0x001e00, SHINY, Th));
    public static Material Titanium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "titanium", 0xdca0f0, METALLIC, Ti));
    public static Material Tungsten = AntimatterAPI.register(Material.class, new Material(Ref.ID, "tungsten", 0x323232, METALLIC, W));
    public static Material Uranium238 = AntimatterAPI.register(Material.class, new Material(Ref.ID, "uranium_238", 0x32f032, METALLIC, U));
    public static Material Uranium235 = AntimatterAPI.register(Material.class, new Material(Ref.ID, "uranium_235", 0x46fa46, METALLIC, U235));
    public static Material Antimony = AntimatterAPI.register(Material.class, new Material(Ref.ID, "antimony", 0xdcdcf0, SHINY, Sb));
    public static Material Argon = AntimatterAPI.register(Material.class, new Material(Ref.ID, "argon", 0xff00f0, NONE, Ar));
    public static Material Calcium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "calcium", 0xfff5f5, METALLIC, Ca));
    public static Material Cadmium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "cadmium", 0x32323c, SHINY, Cd));
    public static Material Chlorine = AntimatterAPI.register(Material.class, new Material(Ref.ID, "chlorine", 0xffffff, NONE, Cr));
    public static Material Deuterium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "deuterium", 0xffff00, NONE, D));
    public static Material Fluorine = AntimatterAPI.register(Material.class, new Material(Ref.ID, "fluorine", 0xffffff, NONE, F));
    public static Material Hydrogen = AntimatterAPI.register(Material.class, new Material(Ref.ID, "hydrogen", 0x0000ff, NONE, H));
    public static Material Helium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "helium", 0xffff00, NONE, He));
    public static Material Helium3 = AntimatterAPI.register(Material.class, new Material(Ref.ID, "helium_3", 0xffffff, NONE, He3));
    public static Material Lithium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "lithium", 0xe1dcff, DULL, Li));
    public static Material Magnesium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "magnesium", 0xffc8c8, METALLIC, Mg));
    public static Material Mercury = AntimatterAPI.register(Material.class, new Material(Ref.ID, "mercury", 0xffdcdc, SHINY, Hg));
    public static Material Nitrogen = AntimatterAPI.register(Material.class, new Material(Ref.ID, "nitrogen", 0x0096c8, NONE, N));
    public static Material Oxygen = AntimatterAPI.register(Material.class, new Material(Ref.ID, "oxygen", 0x0064c8, NONE, O));
    public static Material Phosphor = AntimatterAPI.register(Material.class, new Material(Ref.ID, "phosphor", 0xffff00, DULL, P));
    public static Material Potassium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "potassium", 0xfafafa, METALLIC, K));
    public static Material Silicon = AntimatterAPI.register(Material.class, new Material(Ref.ID, "silicon", 0x3c3c50, METALLIC, Si));
    public static Material Sodium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sodium", 0x000096, METALLIC, Na));
    public static Material Sulfur = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sulfur", 0xc8c800, DULL, S));
    public static Material Tin = AntimatterAPI.register(Material.class, new Material(Ref.ID, "tin", 0xdcdcdc, DULL, Sn));
    public static Material Tritium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "tritium", 0xff0000, METALLIC, T));
    public static Material Zinc = AntimatterAPI.register(Material.class, new Material(Ref.ID, "zinc", 0xfaf0f0, METALLIC, Zn));
    public static Material Technetium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "technetium", 0xC8C8C8, METALLIC, Tc));
    public static Material Neon = AntimatterAPI.register(Material.class, new Material(Ref.ID, "neon", 0xFF6464, NONE, Ne));

    //TODO: We can be more lenient about what fluids we have in, its not as bad as solids above, and we can stop them from showing in JEI (I think...)

    /** Gases **/
    public static Material WoodGas = AntimatterAPI.register(Material.class, new Material(Ref.ID, "wood_gas", 0xdecd87, NONE));
    public static Material Methane = AntimatterAPI.register(Material.class, new Material(Ref.ID, "methane", 0xfac8fa, NONE));
    //public static Material Biogas = AntimatterAPI.register(Material.class, new Material(Ref.ID, "biogas", 0xa7984c, NONE).asGas(32);
    public static Material CarbonDioxide = AntimatterAPI.register(Material.class, new Material(Ref.ID, "carbon_dioxide", 0xa9d0f5, NONE));
    //public static Material NobleGases = AntimatterAPI.register(Material.class, new Material(Ref.ID, "noble_gases", 0xc9e3fc, NONE).asGas()/*.setTemp(79, 0)*/.addComposition(of(CarbonDioxide, 21, Helium, 9, Methane, 3, Deuterium, 1));
    public static Material Air = AntimatterAPI.register(Material.class, new Material(Ref.ID, "air", 0xc9e3fc, NONE));
    public static Material NitrogenDioxide = AntimatterAPI.register(Material.class, new Material(Ref.ID, "nitrogen_dioxide", 0x64afff, NONE));
    public static Material NaturalGas = AntimatterAPI.register(Material.class, new Material(Ref.ID, "natural_gas", 0xffffff, NONE));
    public static Material Propane = AntimatterAPI.register(Material.class, new Material(Ref.ID, "propane", 0xfae250, NONE));
    public static Material SulfurDioxide = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sulfur_dioxide", 0xc8c819, NONE));
    public static Material SulfurTrioxide = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sulfur_trioxide", 0xa0a014, NONE));
    public static Material NitricOxide = AntimatterAPI.register(Material.class, new Material(Ref.ID, "nitric_oxide", 0x7dc8f0, NONE));

    /** Fluids **/
    public static Material PahoehoeLava = AntimatterAPI.register(Material.class, new Material(Ref.ID, "pahoehoe_lava", 0xffffff, NONE));
    public static Material Steam = AntimatterAPI.register(Material.class, new Material(Ref.ID, "steam", 0xa0a0a0, NONE));
    public static Material HotCoolant = AntimatterAPI.register(Material.class, new Material(Ref.ID, "hot_coolant", 0xe20000, NONE));
    public static Material ColdCoolant = AntimatterAPI.register(Material.class, new Material(Ref.ID, "cold_coolant", 0x01b2ed, NONE));
    public static Material UUAmplifier = AntimatterAPI.register(Material.class, new Material(Ref.ID, "uu_amplifier", 0x600080, NONE));
    public static Material UUMatter = AntimatterAPI.register(Material.class, new Material(Ref.ID, "uu_matter", 0x8000c4, NONE));
    public static Material Lubricant = AntimatterAPI.register(Material.class, new Material(Ref.ID, "lubricant", 0xffc400, NONE));
    //public static Material WoodTar = AntimatterAPI.register(Material.class, new Material(Ref.ID, "wood_tar", 0x28170b, NONE).asFluid(; TODO: not sure if neede;
    public static Material DistilledWater = AntimatterAPI.register(Material.class, new Material(Ref.ID, "distilled_water", 0x5C5CFF, NONE));
    public static Material Glyceryl = AntimatterAPI.register(Material.class, new Material(Ref.ID, "glyceryl", 0x009696, NONE));
    public static Material SodiumPersulfate = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sodium_persulfate", 0x006646, NONE));
    public static Material NitricAcid = AntimatterAPI.register(Material.class, new Material(Ref.ID, "nitric_acid", 0xe6e2ab, NONE));
    //public static Material HydrochloricAcid = AntimatterAPI.register(Material.class, new Material(Ref.ID, "hydrochloric_acid", 0x6f8a91, NONE).asFluid().mats(of(Hydrogen, 1, Chlorine, 1));
    public static Material SulfuricAcid = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sulfuric_acid", 0xff8000, NONE));
    public static Material NitroCarbon = AntimatterAPI.register(Material.class, new Material(Ref.ID, "nitro_carbon", 0x1f5e5e, NONE));
    public static Material Honey = AntimatterAPI.register(Material.class, new Material(Ref.ID, "honey", 0xfac800, NONE));

    /** Fuels **/
    public static Material Diesel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "diesel", 0xffff00, NONE));
    public static Material Gasoline = AntimatterAPI.register(Material.class, new Material(Ref.ID, "gasoline", 0x84723e, NONE));
    public static Material NitroDiesel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "nitro_diesel", 0xc8ff00, NONE));
    public static Material BioDiesel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "bio_diesel", 0xff8000, NONE));
    public static Material Biomass = AntimatterAPI.register(Material.class, new Material(Ref.ID, "biomass", 0x00ff00, NONE));
    //public static Material Biofuel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "biofuel", 0x99cc00, NONE).asFluid(6);
    public static Material Ethanol = AntimatterAPI.register(Material.class, new Material(Ref.ID, "ethanol", 0xff8000, NONE));
    public static Material Creosote = AntimatterAPI.register(Material.class, new Material(Ref.ID, "creosote", 0x804000, NONE));
    public static Material Naphtha = AntimatterAPI.register(Material.class, new Material(Ref.ID, "naphtha", 0xffff64, NONE));
    public static Material NitroCoalFuel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "nitro-coalfuel", 0x002b2b, NONE));
    public static Material CoalFuel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "coalfuel", 0x0f0f0f, NONE));
    public static Material FishOil = AntimatterAPI.register(Material.class, new Material(Ref.ID, "fish_oil", 0xffc400, NONE));
    public static Material Oil = AntimatterAPI.register(Material.class, new Material(Ref.ID, "oil", 0x0a0a0a, NONE));
    public static Material SeedOil = AntimatterAPI.register(Material.class, new Material(Ref.ID, "seed_oil", 0xc4ff00, NONE));
    //public static Materials SeedOilHemp = new Materials(722, "Hemp Seed Oil", 196, 255, 0, lime, NONE).asSemi(2;
    //public static Materials SeedOilLin = new Materials(723, "Lin Seed Oil", 196, 255, 0, lime, NONE).asSemi(2;
    public static Material Glycerol = AntimatterAPI.register(Material.class, new Material(Ref.ID, "glycerol", 0x87de87, NONE));

    /** Dusts **/
    public static Material SodiumSulfide = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sodium_sulfide", 0xffe680, NONE));
    public static Material PlatinumGroupSludge = AntimatterAPI.register(Material.class, new Material(Ref.ID, "platinum_group_sludge", 0x001e00, NONE));
    //public static Material Oilsands = AntimatterAPI.register(Material.class, new Material(Ref.ID, "oilsands", 0x0a0a0a, NONE).asDust(ORE);
    public static Material RareEarth = AntimatterAPI.register(Material.class, new Material(Ref.ID, "rare_earth", 0x808064, FINE));
    public static Material Almandine = AntimatterAPI.register(Material.class, new Material(Ref.ID, "almandine", 0xff0000, ROUGH));
    public static Material Andradite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "andradite", 0x967800, ROUGH));
    public static Material Ash = AntimatterAPI.register(Material.class, new Material(Ref.ID, "ash", 0x969696, DULL));
    public static Material Calcite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "calcite", 0xfae6dc, DULL));
    public static Material Cassiterite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "cassiterite", 0xdcdcdc, DULL));
    public static Material Chromite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "chromite", 0x23140F, DULL));
    public static Material Clay = AntimatterAPI.register(Material.class, new Material(Ref.ID, "clay", 0xc8c8dc, ROUGH));
    public static Material DarkAsh = AntimatterAPI.register(Material.class, new Material(Ref.ID, "dark_ash", 0x323232, DULL));
    public static Material Energium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "energium", 0xff0000, DIAMOND));
    public static Material Galena = AntimatterAPI.register(Material.class, new Material(Ref.ID, "galena", 0x643c64, DULL));
    public static Material Grossular = AntimatterAPI.register(Material.class, new Material(Ref.ID, "grossular", 0xc86400, ROUGH));
    public static Material Magnesite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "magnesite", 0xfafab4, METALLIC));
    public static Material Obsidian = AntimatterAPI.register(Material.class, new Material(Ref.ID, "obsidian", 0x503264, DULL));
    public static Material Phosphate = AntimatterAPI.register(Material.class, new Material(Ref.ID, "phosphate", 0xffff00, DULL));
    public static Material Pyrite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "pyrite", 0x967828, ROUGH));
    public static Material Pyrope = AntimatterAPI.register(Material.class, new Material(Ref.ID, "pyrope", 0x783264, METALLIC));
    public static Material Saltpeter = AntimatterAPI.register(Material.class, new Material(Ref.ID, "saltpeter", 0xe6e6e6, FINE));
    public static Material SiliconDioxide = AntimatterAPI.register(Material.class, new Material(Ref.ID, "silicon_dioxide", 0xc8c8c8, QUARTZ));
    public static Material SodiumHydroxide = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sodium_hydroxide", 0x003380, DULL));
    public static Material Brick = AntimatterAPI.register(Material.class, new Material(Ref.ID, "brick", 0x9b5643, ROUGH));
    public static Material Fireclay = AntimatterAPI.register(Material.class, new Material(Ref.ID, "fireclay", 0xada09b, ROUGH));
    public static Material Spessartine = AntimatterAPI.register(Material.class, new Material(Ref.ID, "spessartine", 0xff6464, DULL));
    public static Material Sphalerite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sphalerite", 0xffffff, DULL));
    public static Material Tetrahedrite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "tetrahedrite", 0xc82000, DULL));
    public static Material Tungstate = AntimatterAPI.register(Material.class, new Material(Ref.ID, "tungstate", 0x373223, DULL));
    public static Material Uraninite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "uraninite", 0x232323, DULL));
    public static Material Bauxite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "bauxite", 0xc86400, DULL));
    public static Material PotassiumFeldspar = AntimatterAPI.register(Material.class, new Material(Ref.ID, "potassium_feldspar", 0x782828, FINE));
    public static Material Biotite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "biotite", 0x141e14, METALLIC));
    public static Material Uvarovite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "uvarovite", 0xb4ffb4, DIAMOND));

    /** Gems **/
    //Brittle Gems
    public static Material CoalCoke = AntimatterAPI.register(Material.class, new Material(Ref.ID, "coal_coke", 0x8c8caa, LIGNITE));

    public static Material Lazurite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "lazurite", 0x6478ff, LAPIS));
    public static Material Ruby = AntimatterAPI.register(Material.class, new Material(Ref.ID, "ruby", 0xff6464, RUBY));
    public static Material Sapphire = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sapphire", 0x6464c8, GEM_V));
    public static Material Sodalite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sodalite", 0x1414ff, LAPIS));
    //public static Material Glass = AntimatterAPI.register(Material.class, new Material(Ref.ID, "glass", 0xfafafa, SHINY)).asDust(PLATE, LENS).mats(of(SiliconDioxide, 1));
    public static Material Olivine = AntimatterAPI.register(Material.class, new Material(Ref.ID, "olivine", 0x96ff96, RUBY));
    //public static Material Phosphorus = AntimatterAPI.register(Material.class, new Material(Ref.ID, "phosphorus", 0xffff00, FLINT).asDust().mats(of(Calcium, 3, Phosphate, 2));
    public static Material RedGarnet = AntimatterAPI.register(Material.class, new Material(Ref.ID, "red_garnet", 0xc85050, GARNET));
    public static Material YellowGarnet = AntimatterAPI.register(Material.class, new Material(Ref.ID, "yellow_garnet", 0xc8c850, GARNET));

    public static Material Amethyst = AntimatterAPI.register(Material.class, new Material(Ref.ID, "amethyst", 0xd232d2, RUBY, Ref.MOD_BLUEPOWER));

    /** **/
    public static Material Cinnabar = AntimatterAPI.register(Material.class, new Material(Ref.ID, "cinnabar", 0x960000, REDSTONE));

    /** Metals **/
    public static Material BatteryAlloy = AntimatterAPI.register(Material.class, new Material(Ref.ID, "battery_alloy", 0x9c7ca0, DULL));
    public static Material Brass = AntimatterAPI.register(Material.class, new Material(Ref.ID, "brass", 0xffb400, METALLIC));
    public static Material Bronze = AntimatterAPI.register(Material.class, new Material(Ref.ID, "bronze", 0xff8000, METALLIC));
    public static Material Cupronickel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "cupronickel", 0xe39680, METALLIC));
    public static Material Electrum = AntimatterAPI.register(Material.class, new Material(Ref.ID, "electrum", 0xffff64, SHINY));
    public static Material Invar = AntimatterAPI.register(Material.class, new Material(Ref.ID, "invar", 0xb4b478, METALLIC));
    public static Material Kanthal = AntimatterAPI.register(Material.class, new Material(Ref.ID, "kanthal", 0xc2d2df, METALLIC));
    public static Material Magnalium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "magnalium", 0xc8beff, DULL));
    public static Material Nichrome = AntimatterAPI.register(Material.class, new Material(Ref.ID, "nichrome", 0xcdcef6, METALLIC));
    public static Material SolderingAlloy = AntimatterAPI.register(Material.class, new Material(Ref.ID, "soldering_alloy", 0xdcdce6, DULL));
    public static Material Steel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "steel", 0x808080, METALLIC));
    public static Material StainlessSteel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "stainless_steel", 0xc8c8dc, SHINY));
    public static Material WroughtIron = AntimatterAPI.register(Material.class, new Material(Ref.ID, "wrought_iron", 0xc8b4b4, METALLIC));
    public static Material TungstenSteel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "tungstensteel", 0x6464a0, METALLIC));
    public static Material TungstenCarbide = AntimatterAPI.register(Material.class, new Material(Ref.ID, "tungsten_carbide", 0x330066, METALLIC));
    public static Material RedAlloy = AntimatterAPI.register(Material.class, new Material(Ref.ID, "red_alloy", 0xc80000, DULL));
    public static Material Osmiridium = AntimatterAPI.register(Material.class, new Material(Ref.ID, "osmiridium", 0x6464ff, METALLIC));
    public static Material IronMagnetic = AntimatterAPI.register(Material.class, new Material(Ref.ID, "magnetic_iron", 0xc8c8c8, MAGNETIC));
    public static Material SteelMagnetic = AntimatterAPI.register(Material.class, new Material(Ref.ID, "magnetic_steel", 0x808080, MAGNETIC));

    /** TFC Materials **/
    public static Material SterlingSilver = AntimatterAPI.register(Material.class, new Material(Ref.ID, "sterling_silver", 0xfadce1, SHINY, Ref.MOD_TFC));
    public static Material RoseGold = AntimatterAPI.register(Material.class, new Material(Ref.ID, "rose_gold", 0xffe61e, SHINY, Ref.MOD_TFC));
    public static Material BlackBronze = AntimatterAPI.register(Material.class, new Material(Ref.ID, "black_bronze", 0x64327d, DULL, Ref.MOD_TFC));
    public static Material BismuthBronze = AntimatterAPI.register(Material.class, new Material(Ref.ID, "bismuth_bronze", 0x647d7d, DULL, Ref.MOD_TFC));
    public static Material BlackSteel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "black_steel", 0x646464, METALLIC, Ref.MOD_TFC));
    public static Material RedSteel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "red_steel", 0x8c6464, METALLIC, Ref.MOD_TFC));
    public static Material BlueSteel = AntimatterAPI.register(Material.class, new Material(Ref.ID, "blue_steel", 0x64648c, METALLIC, Ref.MOD_TFC));

    /** Solids (Plastic Related Stuff)**/
    public static Material Plastic = AntimatterAPI.register(Material.class, new Material(Ref.ID, "plastic", 0xc8c8c8, DULL));
    public static Material Rubber = AntimatterAPI.register(Material.class, new Material(Ref.ID, "rubber", 0x000000, SHINY));

    /** Stones **/
    public static Material RedGranite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "red_granite", 0xff0080, ROUGH));
    public static Material BlackGranite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "black_granite", 0x0a0a0a, ROUGH));
    public static Material Marble = AntimatterAPI.register(Material.class, new Material(Ref.ID, "marble", 0xc8c8c8, NONE));
    public static Material Komatiite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "komatiite", 0xbebe69, NONE));
    public static Material Limestone = AntimatterAPI.register(Material.class, new Material(Ref.ID, "limestone", 0xe6c882, NONE));
    public static Material GreenSchist = AntimatterAPI.register(Material.class, new Material(Ref.ID, "green_schist", 0x69be69, NONE));
    public static Material BlueSchist = AntimatterAPI.register(Material.class, new Material(Ref.ID, "blue_schist", 0x0569be, NONE));
    public static Material Kimberlite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "kimberlite", 0x64460a, NONE));
    public static Material Quartzite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "quartzite", 0xe6cdcd, QUARTZ));

    public static Material Scoria = AntimatterAPI.register(Material.class, new Material(Ref.ID, "scoria", 0x1e1414, ROUGH, Ref.MOD_CREATE));

    /** Ore Stones **/
    public static Material Salt = AntimatterAPI.register(Material.class, new Material(Ref.ID, "salt", 0xfafafa, FINE));
    public static Material RockSalt = AntimatterAPI.register(Material.class, new Material(Ref.ID, "rock_salt", 0xf0c8c8, FINE));
    //public static Material OilShale = AntimatterAPI.register(Material.class, new Material(Ref.ID, "oil_shale", 0x32323c, NONE)).asDust(ORE_STONE);



    /** Reference Materials **/
    public static Material Superconductor = AntimatterAPI.register(Material.class, new Material(Ref.ID, "superconductor", 0xffffff, NONE));
    public static Material HighPressure = AntimatterAPI.register(Material.class, new Material(Ref.ID, "high_pressure", 0xc80000, NONE));
    public static Material HighCapacity = AntimatterAPI.register(Material.class, new Material(Ref.ID, "high_capacity", 0xb00b69, NONE));
    public static Material PlasmaContainment = AntimatterAPI.register(Material.class, new Material(Ref.ID, "plasma_containment", 0xffff00, NONE));

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
                .viscosity(1000).density(1000).temperature(MaterialTags.LIQUID_TEMPERATURE.getInt(material));
    }

    private static Block.Properties prepareProperties(Material m, MaterialType<?> type) {
        return Block.Properties.of(net.minecraft.world.level.material.Material.WATER).strength(100.0F).noDrops().lightLevel(s -> type == AntimatterMaterialTypes.PLASMA ? 15 : m == PahoehoeLava ? 9: 0);
    }

}
