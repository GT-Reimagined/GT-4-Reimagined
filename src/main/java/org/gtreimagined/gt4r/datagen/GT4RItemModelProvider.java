package org.gtreimagined.gt4r.datagen;


import org.gtreimagined.gtlib.client.GTLibModelManager;
import org.gtreimagined.gtlib.datagen.builder.GTItemModelBuilder;
import org.gtreimagined.gtlib.datagen.providers.GTItemModelProvider;
import org.gtreimagined.gtlib.machine.MachineState;
import org.gtreimagined.gtlib.texture.Texture;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gtlib.Ref;

import static org.gtreimagined.gtlib.machine.Tier.LV;
import static org.gtreimagined.gt4r.data.Machines.DUSTBIN;

public class GT4RItemModelProvider extends GTItemModelProvider {
    public GT4RItemModelProvider(String providerDomain, String providerName) {
        super(providerDomain, providerName);
    }

    @Override
    public void processItemModels(String domain) {
        GTLibModelManager.put(DUSTBIN.getItem(LV), (item, prov) -> {
            GTItemModelBuilder b = prov.getBuilder(item).parent(prov.existing(GT4RRef.ID, "block/layered_dustbin")).texture("base", DUSTBIN.getBaseTexture(LV, MachineState.ACTIVE)[0]);
            Texture[] base = DUSTBIN.getBaseTexture(LV, MachineState.ACTIVE);
            if (base.length >= 6){
                for (int s = 0; s < 6; s++){
                    b.texture("base" +  Ref.DIRS[s].getSerializedName(), base[s]);
                }
            }
            Texture[] overlays = DUSTBIN.getOverlayTextures(MachineState.ACTIVE, LV, 0);
            for (int s = 0; s < 6; s++) {
                b.texture("overlay" + Ref.DIRS[s].getSerializedName(), overlays[s]);
            }
        });
        super.processItemModels(domain);
    }
}
