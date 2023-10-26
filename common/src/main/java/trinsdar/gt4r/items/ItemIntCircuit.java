package trinsdar.gt4r.items;

import muramasa.antimatter.item.ItemBasic;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import trinsdar.gt4r.data.TierMaps;

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
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        int newId = playerIn.isCrouching() ? this.getNewCircuitIdBackward() : this.getNewCircuitIdForward();
        ItemStack stack = playerIn.getItemInHand(handIn);
        ItemStack newStack = new ItemStack(TierMaps.INT_CIRCUITS_ITEMS.get(newId), stack.getCount());
        playerIn.setItemInHand(handIn, newStack);
        return InteractionResultHolder.consume(stack);
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
