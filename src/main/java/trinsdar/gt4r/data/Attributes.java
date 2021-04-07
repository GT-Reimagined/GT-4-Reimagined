package trinsdar.gt4r.data;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import trinsdar.gt4r.Ref;

public class Attributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, Ref.ID);

    public static final RegistryObject<Attribute> ATTACK_REACH = ATTRIBUTES.register("attack_reach", () -> new RangedAttribute(
            "attribute.name.generic.gt4r.attackReach",
            4.5D,
            0.0D,
            1024.0D)
            .setShouldWatch(true));

    public static void init(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ATTRIBUTES.register(modEventBus);
    }
}
