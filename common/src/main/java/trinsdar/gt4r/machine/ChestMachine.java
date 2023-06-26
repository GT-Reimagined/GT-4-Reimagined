package trinsdar.gt4r.machine;

import io.github.gregtechintergalactical.gtutility.machine.MaterialMachine;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import trinsdar.gt4r.block.BlockMaterialChest;

import static muramasa.antimatter.machine.MachineFlag.COVERABLE;

public class ChestMachine extends MaterialMachine {
    public ChestMachine(String domain, String id, Material material) {
        super(domain, id, material);
        setBlock(BlockMaterialChest::new);
        setItemBlockClass(() -> BlockMaterialChest.class);
        COVERABLE.remove(this);
    }
}
