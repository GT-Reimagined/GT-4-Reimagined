package trinsdar.gt4r.block;

import muramasa.antimatter.client.AntimatterModelManager;
import muramasa.antimatter.datagen.builder.AntimatterBlockModelBuilder;
import muramasa.antimatter.datagen.builder.VariantBlockStateBuilder;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.NotNull;

import static muramasa.antimatter.machine.Tier.*;

public class BlockBatBox extends BlockMachine {
    public static EnumProperty<BatBoxTiers> TIER = EnumProperty.create("tier", BatBoxTiers.class);

    public BlockBatBox(Machine<?> type, Tier tier) {
        super(type, tier);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(TIER);
    }

    @Override
    public void onBlockModelBuild(Block block, AntimatterBlockStateProvider prov) {
        Tier original = this.tier;
        for (BatBoxTiers tier : BatBoxTiers.values()) {
            this.tier = fromBatBoxTier(tier);
            AntimatterBlockModelBuilder builder = prov.models().getBuilder(getId() + "_" + tier.getSerializedName());
            buildModelsForState(builder, MachineState.IDLE);
            buildModelsForState(builder, MachineState.ACTIVE);
            builder.loader(AntimatterModelManager.LOADER_MACHINE);
            builder.property("particle", getType().getBaseTexture(this.tier, MachineState.IDLE)[0].toString());
        }
        this.tier = original;
        prov.getVariantBuilder(block).forAllStatesExcept(b ->
                new VariantBlockStateBuilder.VariantBuilder().modelFile(prov.models().getBuilder(getId() + "_" + b.getValue(TIER).getSerializedName())),
                BlockStateProperties.HORIZONTAL_FACING);
    }

    @Override
    public Component getDisplayName(ItemStack stack) {
        Tier tier1 = getTier();
        if (stack.getTag() != null && stack.getTag().contains("tier")){
            byte tier = stack.getTag().getByte("tier");
            tier1 = fromBatBoxTier(BatBoxTiers.values()[tier]);
        }
        return getType().getDisplayName(tier1);
    }

    private static Tier fromBatBoxTier(BatBoxTiers tier){
        return switch (tier) {
            case LV -> LV;
            case MV -> MV;
            case HV -> HV;
            case EV -> IV;
            case IV -> EV;
        };
    }

    public enum BatBoxTiers implements StringRepresentable {
        LV, MV, HV, EV, IV;

        @Override
        public @NotNull String getSerializedName() {
            return this.name().toLowerCase();
        }
    }
}
