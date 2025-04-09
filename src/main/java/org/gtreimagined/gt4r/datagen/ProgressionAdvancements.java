package org.gtreimagined.gt4r.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.resources.ResourceLocation;
import org.gtreimagined.gt4r.GT4RRef;

import java.util.function.Consumer;

import static org.gtreimagined.gtlib.data.GTMaterialTypes.GEM;
import static org.gtreimagined.gtlib.data.GTLibMaterials.Flint;
import static org.gtreimagined.gtlib.datagen.providers.GTAdvancementProvider.buildRootAdvancement;
import static org.gtreimagined.gtlib.datagen.providers.GTAdvancementProvider.getLoc;
import static org.gtreimagined.gtlib.util.Utils.hasItem;

public class ProgressionAdvancements implements Consumer<Consumer<Advancement>> {

    public static Advancement progressionRoot;

    @Override
    public void accept(Consumer<Advancement> consumer) {
        progressionRoot = buildRootAdvancement(GEM.get(Flint), new ResourceLocation("textures/gui/advancements/backgrounds/stone.png"),
                        GT4RRef.ID + ".advancements.gt4r", GT4RRef.ID + ".advancements.gt4r.desc", FrameType.TASK, true, true, false)
                        .addCriterion("has_rocks", hasItem(GEM.get(Flint))).save(consumer, getLoc(GT4RRef.ID, "progression/root"));
    }

}
