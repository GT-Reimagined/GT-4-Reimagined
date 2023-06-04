package trinsdar.gt4r.machine;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fluids.FluidStack;
import trinsdar.gt4r.block.BlockMachineMaterial;
import trinsdar.gt4r.tile.single.TileEntityMaterial;

import static muramasa.antimatter.Data.WRENCH_MATERIAL;
import static muramasa.antimatter.machine.MachineFlag.COVERABLE;

public class MaterialMachine extends Machine<MaterialMachine> {
    Material material;
    public MaterialMachine(String domain, String id, Material material) {
        super(domain, id);
        this.material = material;
        setItemBlockClass(() -> BlockMachineMaterial.class);
        setBlock((type, tier) -> {
            if (type.getId().contains("_drum")) return new BlockMachineMaterial(type, tier, BlockBehaviour.Properties.of(WRENCH_MATERIAL).strength(1.0f, 10.0f));
            return new BlockMachineMaterial(type, tier);
        });
        if (id.contains("drum")){
            setTooltipInfo((stack, world, tooltip, flag) -> {
                CompoundTag nbt = stack.getTag();
                if (nbt != null && (nbt.contains("Fluid") || nbt.contains("Outputs"))){
                    FluidStack fluid = nbt.contains("Fluid") ? FluidStack.loadFluidStackFromNBT(nbt.getCompound("Fluid")) : FluidStack.EMPTY;
                    if (fluid != null && !fluid.isEmpty()){
                        tooltip.add(new TranslatableComponent("machine.drum.fluid", fluid.getAmount(), fluid.getDisplayName()));
                    }
                    if (nbt.contains("Outputs")){
                        tooltip.add(new TranslatableComponent("machine.drum.output"));
                    }
                }
            });
        }
        setTile(TileEntityMaterial::new);
        addFlags(COVERABLE);
        noCovers();
        frontCovers();
    }

    public Material getMaterial() {
        return material;
    }
}
