package trinsdar.gt4r.datagen;

import muramasa.antimatter.datagen.providers.AntimatterItemTagProvider;
import muramasa.antimatter.util.Utils;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.Tag;
import net.minecraft.util.WeightedRandom;
import net.minecraft.item.Item;
import trinsdar.gt4r.data.GT4RData;

import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.Materials.*;

public class GT4RItemTagProvider extends AntimatterItemTagProvider {
    public GT4RItemTagProvider(String providerDomain, String providerName, boolean replace, DataGenerator gen) {
        super(providerDomain, providerName, replace, gen);
    }

    @Override
    public void registerTags() {
        super.registerTags();
        this.getBuilder(getTag("plates/steels")).add(getTag("plates/steel")).add(getTag("plates/stainless_steel"));
        this.getBuilder(getTag("circuits/basic")).add(CircuitBasic);
        this.getBuilder(getTag("circuits/advanced")).add(CircuitAdv);
        this.getBuilder(getTag("circuits/elite")).add(CircuitDataControl);
        this.getBuilder(getTag("circuits/master")).add(CircuitEnergyFlow);
        this.getBuilder(getTag("circuits/data")).add(CircuitDataStorage);
        this.getBuilder(getTag("circuits/ultimate")).add(CircuitDataOrb);
        this.getBuilder(getTag("machine_hull/cheap")).add(HULL.get(Bronze), HULL.get(Brass), HULL.get(WroughtIron));
        this.getBuilder(getTag("machine_hull/basic")).add(HULL.get(Steel), HULL.get(StainlessSteel), HULL.get(Aluminium));
        this.getBuilder(getTag("machine_hull/advanced")).add(HULL.get(Tungsten), HULL.get(Titanium));
    }

    public Tag<Item> getTag(String id){
        return Utils.getForgeItemTag(id);
    }
}
