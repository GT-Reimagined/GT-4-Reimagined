package org.gtreimagined.gt4r.data;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class Attributes {

    public static final Attribute ATTACK_REACH = new RangedAttribute(
            "attribute.name.generic.gt4r.attackReach",
            5.0D,
            0.0D,
            1024.0D)
            .setSyncable(true);

    public static void init(){
    }
}
