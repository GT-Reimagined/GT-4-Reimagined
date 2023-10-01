package trinsdar.gt4r.datagen;


import muramasa.antimatter.client.AntimatterModelManager;
import muramasa.antimatter.datagen.builder.AntimatterItemModelBuilder;
import muramasa.antimatter.datagen.providers.AntimatterItemModelProvider;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.texture.Texture;
import trinsdar.gt4r.GT4RRef;

import static muramasa.antimatter.machine.Tier.LV;
import static trinsdar.gt4r.data.Machines.DUSTBIN;

public class GT4RItemModelProvider extends AntimatterItemModelProvider {
    public GT4RItemModelProvider(String providerDomain, String providerName) {
        super(providerDomain, providerName);
    }

    @Override
    public void processItemModels(String domain) {
        AntimatterModelManager.put(DUSTBIN.getItem(LV), (item, prov) -> {
            AntimatterItemModelBuilder b = prov.getBuilder(item).parent(prov.existing(GT4RRef.ID, "block/layered_dustbin")).texture("base", DUSTBIN.getBaseTexture(LV)[0]);
            Texture[] base = DUSTBIN.getBaseTexture(LV);
            if (base.length >= 6){
                for (int s = 0; s < 6; s++){
                    b.texture("base" +  muramasa.antimatter.Ref.DIRS[s].getSerializedName(), base[s]);
                }
            }
            Texture[] overlays = DUSTBIN.getOverlayTextures(MachineState.ACTIVE, LV);
            for (int s = 0; s < 6; s++) {
                b.texture("overlay" + muramasa.antimatter.Ref.DIRS[s].getSerializedName(), overlays[s]);
            }
        });
        super.processItemModels(domain);
    }
}
