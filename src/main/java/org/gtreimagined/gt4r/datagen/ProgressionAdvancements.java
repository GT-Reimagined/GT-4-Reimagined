package org.gtreimagined.gt4r.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.resources.ResourceLocation;
import org.gtreimagined.gt4r.GT4RRef;

import java.util.function.Consumer;

import static muramasa.antimatter.data.AntimatterMaterialTypes.GEM;
import static muramasa.antimatter.data.AntimatterMaterials.Flint;
import static muramasa.antimatter.datagen.providers.AntimatterAdvancementProvider.buildRootAdvancement;
import static muramasa.antimatter.datagen.providers.AntimatterAdvancementProvider.getLoc;
import static muramasa.antimatter.util.Utils.hasItem;

public class ProgressionAdvancements implements Consumer<Consumer<Advancement>> {

    public static Advancement progressionRoot;

    @Override
    public void accept(Consumer<Advancement> consumer) {
        progressionRoot = buildRootAdvancement(GEM.get(Flint), new ResourceLocation("textures/gui/advancements/backgrounds/stone.png"),
                        GT4RRef.ID + ".advancements.gt4r", GT4RRef.ID + ".advancements.gt4r.desc", FrameType.TASK, true, true, false)
                        .addCriterion("has_rocks", hasItem(GEM.get(Flint))).save(consumer, getLoc(GT4RRef.ID, "progression/root"));
    }

}
