package trinsdar.gt4r.items;

import muramasa.antimatter.item.ItemBasic;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import trinsdar.gt4r.data.GT4RData;

public class ItemIntCircuit extends ItemBasic<ItemIntCircuit> {

    public final int circuitId;

    public ItemIntCircuit(String domain, String id, int circuitId, Properties properties) {
        super(domain, id, properties);
        this.circuitId = circuitId;
    }

    public ItemIntCircuit(String domain, String id,int circuitId) {
        super(domain, id);
        this.circuitId = circuitId;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        int newId = playerIn.isCrouching() ? this.getNewCircuitIdBackward() : this.getNewCircuitIdForward();
        ItemStack stack = playerIn.getHeldItem(handIn);
        ItemStack newStack = new ItemStack(GT4RData.INT_CIRCUITS_ITEMS.get(newId), stack.getCount());
        playerIn.setHeldItem(handIn, newStack);
        return ActionResult.resultConsume(stack);
    }

    private int getNewCircuitIdForward(){
        if (this.circuitId == 24){
            return 0;
        }
        return this.circuitId + 1;
    }
    private int getNewCircuitIdBackward(){
        if (this.circuitId == 0){
            return 24;
        }
        return this.circuitId - 1;
    }
}
