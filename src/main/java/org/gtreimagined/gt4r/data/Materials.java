package org.gtreimagined.gt4r.data;

import org.gtreimagined.gtlib.GTAPI;
import org.gtreimagined.gtlib.material.Material;
import net.minecraft.resources.ResourceLocation;
import org.gtreimagined.gt4r.GT4RRef;

import static org.gtreimagined.gtlib.material.Element.*;
import static org.gtreimagined.gtlib.material.TextureSet.*;

public class Materials {

    public static final ResourceLocation PAHOEHOE_STILL_TEXTURE = new ResourceLocation(GT4RRef.ID, "fluids/pahoehoe_lava");


    /** Elements **/
    public static Material Hydrogen = GTAPI.register(Material.class, new Material(GT4RRef.ID, "hydrogen", 0x0000ff, NONE, H));
    public static Material Helium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "helium", 0xffff00, NONE, He));
    public static Material Lithium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "lithium", 0xe1dcff, DULL, Li));
    public static Material Beryllium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "beryllium", 0x64b464, METALLIC, Be));
    public static Material Carbon = GTAPI.register(Material.class, new Material(GT4RRef.ID, "carbon", 0x141414, DULL, C));
    public static Material Nitrogen = GTAPI.register(Material.class, new Material(GT4RRef.ID, "nitrogen", 0x0096c8, NONE, N));
    public static Material Oxygen = GTAPI.register(Material.class, new Material(GT4RRef.ID, "oxygen", 0x0064c8, NONE, O));
    public static Material Fluorine = GTAPI.register(Material.class, new Material(GT4RRef.ID, "fluorine", 0xffffff, NONE, F));
    public static Material Neon = GTAPI.register(Material.class, new Material(GT4RRef.ID, "neon", 0xFF6464, NONE, Ne));
    public static Material Sodium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "sodium", 0x000096, METALLIC, Na));
    public static Material Magnesium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "magnesium", 0xffc8c8, METALLIC, Mg));
    public static Material Aluminium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "aluminium", 0x80c8f0, DULL, Al));
    public static Material Silicon = GTAPI.register(Material.class, new Material(GT4RRef.ID, "silicon", 0x3c3c50, METALLIC, Si));
    public static Material Phosphor = GTAPI.register(Material.class, new Material(GT4RRef.ID, "phosphor", 0xffff00, DULL, P));
    public static Material Sulfur = GTAPI.register(Material.class, new Material(GT4RRef.ID, "sulfur", 0xc8c800, DULL, S));
    public static Material Chlorine = GTAPI.register(Material.class, new Material(GT4RRef.ID, "chlorine", 0xffffff, NONE, Cr));
    public static Material Argon = GTAPI.register(Material.class, new Material(GT4RRef.ID, "argon", 0xff00f0, NONE, Ar));
    public static Material Potassium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "potassium", 0xfafafa, METALLIC, K));
    public static Material Calcium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "calcium", 0xfff5f5, METALLIC, Ca));
    public static Material Titanium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "titanium", 0xdca0f0, METALLIC, Ti));
    public static Material Chromium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "chromium", 0xffe6e6, SHINY, Cr));
    public static Material Manganese = GTAPI.register(Material.class, new Material(GT4RRef.ID, "manganese", 0xfafafa, DULL, Mn));
    public static Material Nickel = GTAPI.register(Material.class, new Material(GT4RRef.ID, "nickel", 0xc8c8fa, METALLIC, Ni));
    public static Material Zinc = GTAPI.register(Material.class, new Material(GT4RRef.ID, "zinc", 0xfaf0f0, METALLIC, Zn));
    public static Material Technetium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "technetium", 0xC8C8C8, METALLIC, Tc));
    public static Material Silver = GTAPI.register(Material.class, new Material(GT4RRef.ID, "silver", 0xdcdcff, SHINY, Ag));
    public static Material Cadmium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "cadmium", 0x32323c, SHINY, Cd));
    public static Material Tin = GTAPI.register(Material.class, new Material(GT4RRef.ID, "tin", 0xdcdcdc, DULL, Sn));
    public static Material Antimony = GTAPI.register(Material.class, new Material(GT4RRef.ID, "antimony", 0xdcdcf0, SHINY, Sb));
    public static Material Tungsten = GTAPI.register(Material.class, new Material(GT4RRef.ID, "tungsten", 0x323232, METALLIC, W));
    public static Material Osmium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "osmium", 0x3232ff, METALLIC, Os));
    public static Material Iridium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "iridium", 0xf0f0f5, DULL, Ir));
    public static Material Platinum = GTAPI.register(Material.class, new Material(GT4RRef.ID, "platinum", 0xffffc8, SHINY, Pt));
    public static Material Mercury = GTAPI.register(Material.class, new Material(GT4RRef.ID, "mercury", 0xffdcdc, SHINY, Hg));
    public static Material Lead = GTAPI.register(Material.class, new Material(GT4RRef.ID, "lead", 0x8c648c, DULL, Pb));
    public static Material Bismuth = GTAPI.register(Material.class, new Material(GT4RRef.ID, "bismuth", 0x64a0a0, METALLIC, Bi));
    public static Material Thorium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "thorium", 0x001e00, SHINY, Th)).setDisplayNameString("Thorium 232");
    public static Material Plutonium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "plutonium", 0xf03232, METALLIC, Pu));
    public static Material Uranium238 = GTAPI.register(Material.class, new Material(GT4RRef.ID, "uranium", 0x32f032, METALLIC, U)).setDisplayNameString("Uranium 238");


    public static Material Deuterium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "deuterium", 0xffff00, NONE, D));
    public static Material Tritium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "tritium", 0xff0000, METALLIC, T));
    public static Material Helium3 = GTAPI.register(Material.class, new Material(GT4RRef.ID, "helium_3", 0xffffff, NONE, He3));
    public static Material Uranium235 = GTAPI.register(Material.class, new Material(GT4RRef.ID, "uranium_235", 0x46fa46, METALLIC, U235));
    public static Material Plutonium239 = GTAPI.register(Material.class, new Material(GT4RRef.ID, "plutonium_239", 0xf03232, METALLIC, Pu239));

    //TODO: We can be more lenient about what fluids we have in, its not as bad as solids above, and we can stop them from showing in JEI (I think...)

    /** Metals **/
    public static Material BatteryAlloy = GTAPI.register(Material.class, new Material(GT4RRef.ID, "battery_alloy", 0x9c7ca0, DULL));
    public static Material Brass = GTAPI.register(Material.class, new Material(GT4RRef.ID, "brass", 0xffb400, METALLIC));
    public static Material Bronze = GTAPI.register(Material.class, new Material(GT4RRef.ID, "bronze", 0xff8000, METALLIC));
    public static Material Cupronickel = GTAPI.register(Material.class, new Material(GT4RRef.ID, "cupronickel", 0xe39680, METALLIC));
    public static Material Electrum = GTAPI.register(Material.class, new Material(GT4RRef.ID, "electrum", 0xffff64, SHINY));
    public static Material Invar = GTAPI.register(Material.class, new Material(GT4RRef.ID, "invar", 0xb4b478, METALLIC));
    public static Material IronMagnetic = GTAPI.register(Material.class, new Material(GT4RRef.ID, "magnetic_iron", 0xc8c8c8, MAGNETIC));
    public static Material Kanthal = GTAPI.register(Material.class, new Material(GT4RRef.ID, "kanthal", 0xc2d2df, METALLIC));
    public static Material Magnalium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "magnalium", 0xc8beff, DULL));
    public static Material Nichrome = GTAPI.register(Material.class, new Material(GT4RRef.ID, "nichrome", 0xcdcef6, METALLIC));
    public static Material Osmiridium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "osmiridium", 0x6464ff, METALLIC));
    public static Material RedAlloy = GTAPI.register(Material.class, new Material(GT4RRef.ID, "red_alloy", 0xc80000, DULL));
    public static Material SolderingAlloy = GTAPI.register(Material.class, new Material(GT4RRef.ID, "soldering_alloy", 0xdcdce6, DULL));
    public static Material Steel = GTAPI.register(Material.class, new Material(GT4RRef.ID, "steel", 0x808080, METALLIC));
    public static Material SteelMagnetic = GTAPI.register(Material.class, new Material(GT4RRef.ID, "magnetic_steel", 0x808080, MAGNETIC));
    public static Material StainlessSteel = GTAPI.register(Material.class, new Material(GT4RRef.ID, "stainless_steel", 0xc8c8dc, SHINY));
    public static Material TungstenSteel = GTAPI.register(Material.class, new Material(GT4RRef.ID, "tungstensteel", 0x6464a0, METALLIC));
    public static Material TungstenCarbide = GTAPI.register(Material.class, new Material(GT4RRef.ID, "tungsten_carbide", 0x330066, METALLIC));
    public static Material WroughtIron = GTAPI.register(Material.class, new Material(GT4RRef.ID, "wrought_iron", 0xc8b4b4, METALLIC));

    /** TFC Materials **/
    public static Material BismuthBronze = GTAPI.register(Material.class, new Material(GT4RRef.ID, "bismuth_bronze", 0x647d7d, DULL, GT4RRef.MOD_TFC));
    public static Material BlackBronze = GTAPI.register(Material.class, new Material(GT4RRef.ID, "black_bronze", 0x64327d, DULL, GT4RRef.MOD_TFC));
    public static Material BlackSteel = GTAPI.register(Material.class, new Material(GT4RRef.ID, "black_steel", 0x646464, METALLIC, GT4RRef.MOD_TFC));
    public static Material BlueSteel = GTAPI.register(Material.class, new Material(GT4RRef.ID, "blue_steel", 0x64648c, METALLIC, GT4RRef.MOD_TFC));
    public static Material RedSteel = GTAPI.register(Material.class, new Material(GT4RRef.ID, "red_steel", 0x8c6464, METALLIC, GT4RRef.MOD_TFC));
    public static Material RoseGold = GTAPI.register(Material.class, new Material(GT4RRef.ID, "rose_gold", 0xffe61e, SHINY, GT4RRef.MOD_TFC));
    public static Material SterlingSilver = GTAPI.register(Material.class, new Material(GT4RRef.ID, "sterling_silver", 0xfadce1, SHINY, GT4RRef.MOD_TFC));



    /** Gases **/
    public static Material WoodGas = GTAPI.register(Material.class, new Material(GT4RRef.ID, "wood_gas", 0xdecd87, NONE));
    public static Material Methane = GTAPI.register(Material.class, new Material(GT4RRef.ID, "methane", 0xfac8fa, NONE));
    //public static Material Biogas = GTAPI.register(Material.class, new Material(Ref.ID, "biogas", 0xa7984c, NONE).asGas(32);
    public static Material CarbonDioxide = GTAPI.register(Material.class, new Material(GT4RRef.ID, "carbon_dioxide", 0xa9d0f5, NONE));
    //public static Material NobleGases = GTAPI.register(Material.class, new Material(Ref.ID, "noble_gases", 0xc9e3fc, NONE).asGas()/*.setTemp(79, 0)*/.addComposition(of(CarbonDioxide, 21, Helium, 9, Methane, 3, Deuterium, 1));
    public static Material Air = GTAPI.register(Material.class, new Material(GT4RRef.ID, "air", 0xc9e3fc, NONE));
    public static Material NitrogenDioxide = GTAPI.register(Material.class, new Material(GT4RRef.ID, "nitrogen_dioxide", 0x64afff, NONE));
    public static Material NaturalGas = GTAPI.register(Material.class, new Material(GT4RRef.ID, "natural_gas", 0xffffff, NONE));
    public static Material Propane = GTAPI.register(Material.class, new Material(GT4RRef.ID, "propane", 0xfae250, NONE));
    public static Material SulfurDioxide = GTAPI.register(Material.class, new Material(GT4RRef.ID, "sulfur_dioxide", 0xc8c819, NONE));
    public static Material SulfurTrioxide = GTAPI.register(Material.class, new Material(GT4RRef.ID, "sulfur_trioxide", 0xa0a014, NONE));
    public static Material NitricOxide = GTAPI.register(Material.class, new Material(GT4RRef.ID, "nitric_oxide", 0x7dc8f0, NONE));

    /** Fluids **/
    public static Material PahoehoeLava = GTAPI.register(Material.class, new Material(GT4RRef.ID, "pahoehoe_lava", 0xffffff, NONE));
    public static Material Steam = GTAPI.register(Material.class, new Material(GT4RRef.ID, "steam", 0xa0a0a0, NONE));
    public static Material HotCoolant = GTAPI.register(Material.class, new Material(GT4RRef.ID, "hot_coolant", 0xe20000, NONE));
    public static Material ColdCoolant = GTAPI.register(Material.class, new Material(GT4RRef.ID, "cold_coolant", 0x01b2ed, NONE));
    public static Material UUAmplifier = GTAPI.register(Material.class, new Material(GT4RRef.ID, "uu_amplifier", 0x600080, NONE));
    public static Material UUMatter = GTAPI.register(Material.class, new Material(GT4RRef.ID, "uu_matter", 0x8000c4, NONE));
    public static Material Lubricant = GTAPI.register(Material.class, new Material(GT4RRef.ID, "lubricant", 0xffc400, NONE));
    //public static Material WoodTar = GTAPI.register(Material.class, new Material(Ref.ID, "wood_tar", 0x28170b, NONE).asFluid(; TODO: not sure if neede;
    public static Material DistilledWater = GTAPI.register(Material.class, new Material(GT4RRef.ID, "distilled_water", 0x5C5CFF, NONE));
    public static Material Glyceryl = GTAPI.register(Material.class, new Material(GT4RRef.ID, "glyceryl", 0x009696, NONE));
    public static Material SodiumPersulfate = GTAPI.register(Material.class, new Material(GT4RRef.ID, "sodium_persulfate", 0x006646, NONE));
    public static Material NitricAcid = GTAPI.register(Material.class, new Material(GT4RRef.ID, "nitric_acid", 0xe6e2ab, NONE));
    //public static Material HydrochloricAcid = GTAPI.register(Material.class, new Material(Ref.ID, "hydrochloric_acid", 0x6f8a91, NONE).asFluid().mats(of(Hydrogen, 1, Chlorine, 1));
    public static Material SulfuricAcid = GTAPI.register(Material.class, new Material(GT4RRef.ID, "sulfuric_acid", 0xff8000, NONE));
    public static Material NitroCarbon = GTAPI.register(Material.class, new Material(GT4RRef.ID, "nitro_carbon", 0x1f5e5e, NONE));
    public static Material Honey = GTAPI.register(Material.class, new Material(GT4RRef.ID, "honey", 0xfac800, NONE));

    /** Fuels **/
    public static Material Diesel = GTAPI.register(Material.class, new Material(GT4RRef.ID, "diesel", 0xffff00, NONE));
    public static Material Gasoline = GTAPI.register(Material.class, new Material(GT4RRef.ID, "gasoline", 0x84723e, NONE));
    public static Material NitroDiesel = GTAPI.register(Material.class, new Material(GT4RRef.ID, "nitro_diesel", 0xc8ff00, NONE));
    public static Material BioDiesel = GTAPI.register(Material.class, new Material(GT4RRef.ID, "bio_diesel", 0xff8000, NONE));
    public static Material Biomass = GTAPI.register(Material.class, new Material(GT4RRef.ID, "biomass", 0x00ff00, NONE));
    //public static Material Biofuel = GTAPI.register(Material.class, new Material(Ref.ID, "biofuel", 0x99cc00, NONE).asFluid(6);
    public static Material Ethanol = GTAPI.register(Material.class, new Material(GT4RRef.ID, "ethanol", 0xff8000, NONE));
    public static Material Creosote = GTAPI.register(Material.class, new Material(GT4RRef.ID, "creosote", 0x804000, NONE));
    public static Material Naphtha = GTAPI.register(Material.class, new Material(GT4RRef.ID, "naphtha", 0xffff64, NONE));
    public static Material NitroCoalFuel = GTAPI.register(Material.class, new Material(GT4RRef.ID, "nitro-coalfuel", 0x002b2b, NONE));
    public static Material CoalFuel = GTAPI.register(Material.class, new Material(GT4RRef.ID, "coalfuel", 0x0f0f0f, NONE));
    public static Material FishOil = GTAPI.register(Material.class, new Material(GT4RRef.ID, "fish_oil", 0xffc400, NONE));
    public static Material Oil = GTAPI.register(Material.class, new Material(GT4RRef.ID, "oil", 0x0a0a0a, NONE));
    public static Material SeedOil = GTAPI.register(Material.class, new Material(GT4RRef.ID, "seed_oil", 0xc4ff00, NONE));
    //public static Materials SeedOilHemp = new Materials(722, "Hemp Seed Oil", 196, 255, 0, lime, NONE).asSemi(2;
    //public static Materials SeedOilLin = new Materials(723, "Lin Seed Oil", 196, 255, 0, lime, NONE).asSemi(2;
    public static Material Glycerol = GTAPI.register(Material.class, new Material(GT4RRef.ID, "glycerol", 0x87de87, NONE));

    /** Dusts **/
    public static Material SodiumSulfide = GTAPI.register(Material.class, new Material(GT4RRef.ID, "sodium_sulfide", 0xffe680, NONE));
    public static Material PlatinumGroupSludge = GTAPI.register(Material.class, new Material(GT4RRef.ID, "platinum_group_sludge", 0x001e00, NONE));
    //public static Material Oilsands = GTAPI.register(Material.class, new Material(Ref.ID, "oilsands", 0x0a0a0a, NONE).asDust(ORE);
    public static Material RareEarth = GTAPI.register(Material.class, new Material(GT4RRef.ID, "rare_earth", 0x808064, FINE));
    public static Material Almandine = GTAPI.register(Material.class, new Material(GT4RRef.ID, "almandine", 0xff0000, ROUGH));
    public static Material Andradite = GTAPI.register(Material.class, new Material(GT4RRef.ID, "andradite", 0x967800, ROUGH));
    public static Material Ash = GTAPI.register(Material.class, new Material(GT4RRef.ID, "ash", 0x969696, DULL));
    public static Material Calcite = GTAPI.register(Material.class, new Material(GT4RRef.ID, "calcite", 0xfae6dc, DULL));
    public static Material Cassiterite = GTAPI.register(Material.class, new Material(GT4RRef.ID, "cassiterite", 0xdcdcdc, DULL));
    public static Material Chromite = GTAPI.register(Material.class, new Material(GT4RRef.ID, "chromite", 0x23140F, DULL));
    public static Material Clay = GTAPI.register(Material.class, new Material(GT4RRef.ID, "clay", 0xc8c8dc, ROUGH));
    public static Material DarkAsh = GTAPI.register(Material.class, new Material(GT4RRef.ID, "dark_ash", 0x323232, DULL));
    public static Material Energium = GTAPI.register(Material.class, new Material(GT4RRef.ID, "energium", 0xff0000, DIAMOND));
    public static Material Galena = GTAPI.register(Material.class, new Material(GT4RRef.ID, "galena", 0x643c64, DULL));
    public static Material Grossular = GTAPI.register(Material.class, new Material(GT4RRef.ID, "grossular", 0xc86400, ROUGH));
    public static Material Magnesite = GTAPI.register(Material.class, new Material(GT4RRef.ID, "magnesite", 0xfafab4, METALLIC));
    public static Material Obsidian = GTAPI.register(Material.class, new Material(GT4RRef.ID, "obsidian", 0x503264, DULL));
    public static Material Phosphate = GTAPI.register(Material.class, new Material(GT4RRef.ID, "phosphate", 0xffff00, DULL));
    public static Material Pyrite = GTAPI.register(Material.class, new Material(GT4RRef.ID, "pyrite", 0x967828, ROUGH));
    public static Material Pyrope = GTAPI.register(Material.class, new Material(GT4RRef.ID, "pyrope", 0x783264, METALLIC));
    public static Material Saltpeter = GTAPI.register(Material.class, new Material(GT4RRef.ID, "saltpeter", 0xe6e6e6, FINE));
    public static Material SiliconDioxide = GTAPI.register(Material.class, new Material(GT4RRef.ID, "silicon_dioxide", 0xc8c8c8, QUARTZ));
    public static Material SodiumHydroxide = GTAPI.register(Material.class, new Material(GT4RRef.ID, "sodium_hydroxide", 0x003380, DULL));
    public static Material Brick = GTAPI.register(Material.class, new Material(GT4RRef.ID, "brick", 0x9b5643, ROUGH));
    public static Material Fireclay = GTAPI.register(Material.class, new Material(GT4RRef.ID, "fireclay", 0xada09b, ROUGH));
    public static Material Spessartine = GTAPI.register(Material.class, new Material(GT4RRef.ID, "spessartine", 0xff6464, DULL));
    public static Material Sphalerite = GTAPI.register(Material.class, new Material(GT4RRef.ID, "sphalerite", 0xffffff, DULL));
    public static Material Tetrahedrite = GTAPI.register(Material.class, new Material(GT4RRef.ID, "tetrahedrite", 0xc82000, DULL));
    public static Material Tungstate = GTAPI.register(Material.class, new Material(GT4RRef.ID, "tungstate", 0x373223, DULL));
    public static Material Uraninite = GTAPI.register(Material.class, new Material(GT4RRef.ID, "uraninite", 0x232323, DULL));
    public static Material Bauxite = GTAPI.register(Material.class, new Material(GT4RRef.ID, "bauxite", 0xc86400, DULL));
    public static Material PotassiumFeldspar = GTAPI.register(Material.class, new Material(GT4RRef.ID, "potassium_feldspar", 0x782828, FINE));
    public static Material Biotite = GTAPI.register(Material.class, new Material(GT4RRef.ID, "biotite", 0x141e14, METALLIC));
    public static Material Uvarovite = GTAPI.register(Material.class, new Material(GT4RRef.ID, "uvarovite", 0xb4ffb4, DIAMOND));

    /** Gems **/
    //Brittle Gems
    public static Material CoalCoke = GTAPI.register(Material.class, new Material(GT4RRef.ID, "coal_coke", 0x8c8caa, LIGNITE));

    public static Material Lazurite = GTAPI.register(Material.class, new Material(GT4RRef.ID, "lazurite", 0x6478ff, LAPIS));
    public static Material Ruby = GTAPI.register(Material.class, new Material(GT4RRef.ID, "ruby", 0xff6464, RUBY));
    public static Material Sapphire = GTAPI.register(Material.class, new Material(GT4RRef.ID, "sapphire", 0x6464c8, GEM_V));
    public static Material Sodalite = GTAPI.register(Material.class, new Material(GT4RRef.ID, "sodalite", 0x1414ff, LAPIS));
    //public static Material Glass = GTAPI.register(Material.class, new Material(Ref.ID, "glass", 0xfafafa, SHINY)).asDust(PLATE, LENS).mats(of(SiliconDioxide, 1));
    public static Material GreenSapphire = GTAPI.register(Material.class, new Material(GT4RRef.ID, "green_sapphire", 0x64c882, GEM_H));
    public static Material Olivine = GTAPI.register(Material.class, new Material(GT4RRef.ID, "olivine", 0x96ff96, RUBY));
    //public static Material Phosphorus = GTAPI.register(Material.class, new Material(Ref.ID, "phosphorus", 0xffff00, FLINT).asDust().mats(of(Calcium, 3, Phosphate, 2));
    public static Material RedGarnet = GTAPI.register(Material.class, new Material(GT4RRef.ID, "red_garnet", 0xc85050, GARNET));
    public static Material YellowGarnet = GTAPI.register(Material.class, new Material(GT4RRef.ID, "yellow_garnet", 0xc8c850, GARNET));

    public static Material Amethyst = GTAPI.register(Material.class, new Material(GT4RRef.ID, "amethyst", 0xd232d2, RUBY, GT4RRef.MOD_BLUEPOWER));

    /** **/
    public static Material Cinnabar = GTAPI.register(Material.class, new Material(GT4RRef.ID, "cinnabar", 0x960000, REDSTONE));

    /** Solids (Plastic Related Stuff)**/
    public static Material Plastic = GTAPI.register(Material.class, new Material(GT4RRef.ID, "plastic", 0xc8c8c8, DULL));
    public static Material Rubber = GTAPI.register(Material.class, new Material(GT4RRef.ID, "rubber", 0x000000, SHINY));

    /** Stones **/
    public static Material RedGranite = GTAPI.register(Material.class, new Material(GT4RRef.ID, "red_granite", 0xff0080, ROUGH));
    public static Material BlackGranite = GTAPI.register(Material.class, new Material(GT4RRef.ID, "black_granite", 0x0a0a0a, ROUGH));
    public static Material Marble = GTAPI.register(Material.class, new Material(GT4RRef.ID, "marble", 0xc8c8c8, NONE));
    public static Material Komatiite = GTAPI.register(Material.class, new Material(GT4RRef.ID, "komatiite", 0xbebe69, NONE));
    public static Material Limestone = GTAPI.register(Material.class, new Material(GT4RRef.ID, "limestone", 0xe6c882, NONE));
    public static Material GreenSchist = GTAPI.register(Material.class, new Material(GT4RRef.ID, "green_schist", 0x69be69, NONE));
    public static Material BlueSchist = GTAPI.register(Material.class, new Material(GT4RRef.ID, "blue_schist", 0x0569be, NONE));
    public static Material Kimberlite = GTAPI.register(Material.class, new Material(GT4RRef.ID, "kimberlite", 0x64460a, NONE));
    public static Material Quartzite = GTAPI.register(Material.class, new Material(GT4RRef.ID, "quartzite", 0xe6cdcd, QUARTZ));

    public static Material Scoria = GTAPI.register(Material.class, new Material(GT4RRef.ID, "scoria", 0x1e1414, ROUGH, GT4RRef.MOD_CREATE));

    /** Ore Stones **/
    public static Material Salt = GTAPI.register(Material.class, new Material(GT4RRef.ID, "salt", 0xfafafa, FINE));
    public static Material RockSalt = GTAPI.register(Material.class, new Material(GT4RRef.ID, "rock_salt", 0xf0c8c8, FINE));
    //public static Material OilShale = GTAPI.register(Material.class, new Material(Ref.ID, "oil_shale", 0x32323c, NONE)).asDust(ORE_STONE);



    /** Reference Materials **/
    public static Material Superconductor = GTAPI.register(Material.class, new Material(GT4RRef.ID, "superconductor", 0xffffff, NONE));
    public static Material HighPressure = GTAPI.register(Material.class, new Material(GT4RRef.ID, "high_pressure", 0xc80000, NONE));
    public static Material HighCapacity = GTAPI.register(Material.class, new Material(GT4RRef.ID, "high_capacity", 0xb00b69, NONE));
    public static Material PlasmaContainment = GTAPI.register(Material.class, new Material(GT4RRef.ID, "plasma_containment", 0xffff00, NONE));

    public static void init() {
    }

}
