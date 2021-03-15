package trinsdar.gt4r.datagen;

import muramasa.antimatter.datagen.ExistingFileHelperOverride;
import muramasa.antimatter.datagen.providers.AntimatterItemTagProvider;
import muramasa.antimatter.util.Utils;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraft.item.Item;

import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.Materials.*;

public class GT4RItemTagProvider extends AntimatterItemTagProvider {
    public GT4RItemTagProvider(String providerDomain, String providerName, boolean replace, DataGenerator gen, BlockTagsProvider p, ExistingFileHelperOverride fh) {
        super(providerDomain, providerName, replace, gen, p, fh);
    }

    @Override
    public void registerTags() {
        super.registerTags();
        this.getOrCreateBuilder(getTag("plates/steels")).addTag(getTag("plates/steel")).addTag(getTag("plates/stainless_steel"));
        this.getOrCreateBuilder(getTag("plates/invaraluminium")).addTag(getTag("plates/invar")).addTag(getTag("plates/aluminium"));
        this.getOrCreateBuilder(getTag("circuits/basic")).add(CircuitBasic);
        this.getOrCreateBuilder(getTag("circuits/advanced")).add(CircuitAdv);
        this.getOrCreateBuilder(getTag("circuits/elite")).add(CircuitDataControl);
        this.getOrCreateBuilder(getTag("circuits/master")).add(CircuitEnergyFlow);
        this.getOrCreateBuilder(getTag("circuits/data")).add(CircuitDataStorage);
        this.getOrCreateBuilder(getTag("circuits/ultimate")).add(CircuitDataOrb);
        this.getOrCreateBuilder(getTag("machine_hull/cheap")).add(HULL.get(Bronze), HULL.get(Brass), HULL.get(WroughtIron), HULL.get(Aluminium));
        this.getOrCreateBuilder(getTag("machine_hull/semi_cheap")).add(HULL.get(WroughtIron), HULL.get(Aluminium));
        this.getOrCreateBuilder(getTag("machine_hull/basic")).add(HULL.get(Steel), HULL.get(StainlessSteel), HULL.get(Aluminium));
        this.getOrCreateBuilder(getTag("machine_hull/advanced")).add(HULL.get(TungstenSteel), HULL.get(Titanium));
        this.getOrCreateBuilder(getTag("machine_hull/very_advanced")).add(HULL.get(TungstenSteel), HULL.get(Titanium));
        this.getOrCreateBuilder(getTag("grinding_head")).add(DiamondGrindHead, TungstenGrindHead);
        this.getOrCreateBuilder(getTag("pistons")).add(Items.PISTON, Items.STICKY_PISTON);
        this.getOrCreateBuilder(getTag("gears/titantungsteel")).addTag(getTag("gears/titanium")).addTag(getTag("gears/tungstensteel"));
        this.getOrCreateBuilder(getTag("gears/steels")).addTag(getTag("gears/steel")).addTag(getTag("gears/stainless_steel"));

    }

    public ITag.INamedTag<Item> getTag(String id){
        return Utils.getForgeItemTag(id);
    }
}
