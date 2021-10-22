package trinsdar.gt4r.machine;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Data;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import trinsdar.gt4r.block.BlockNonSolidMachine;

import static muramasa.antimatter.machine.MachineFlag.BASIC;
import static muramasa.antimatter.machine.MachineFlag.ENERGY;

public class NonSolidMachine extends Machine<NonSolidMachine> {
    public NonSolidMachine(String domain, String id) {
        super(domain, id);
        addFlags(BASIC, ENERGY);
        setTile(() -> new TileEntityMachine(this));
        setGUI(Data.BASIC_MENU_HANDLER);
    }

    @Override
    protected Block getBlock(Machine<NonSolidMachine> type, Tier tier) {
        return new BlockNonSolidMachine(type, tier);
    }

    public Item getItem(Tier tier) {
        return BlockItem.BLOCK_TO_ITEM.get(AntimatterAPI.get(BlockNonSolidMachine.class,this.getId() + "_" + tier.getId(), getDomain()));
    }
}
