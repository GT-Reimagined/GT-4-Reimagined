package trinsdar.gt4r.gui;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.gui.MenuHandlerMachine;
import muramasa.antimatter.gui.SlotData;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.core.NonNullList;
import net.minecraft.world.level.Level;
import trinsdar.gt4r.gui.slots.SlotWorkTableResult;
import trinsdar.gt4r.tile.single.TileEntityMaterial;

import java.util.Collections;
import java.util.Optional;

public class ContainerWorkbench<T extends TileEntityMaterial<T>> extends ContainerMachine<T> {
    private CraftingContainer craftingGrid;
    private ResultContainer craftResult;
    public ContainerWorkbench(T tile, Inventory playerInv, MenuHandlerMachine<T, ContainerMachine<T>> menuHandler, int windowId) {
        super(tile, playerInv, menuHandler, windowId);
        this.slotsChanged(this.craftingGrid);
    }

    @Override
    protected void addSlots(TileEntityMachine<?> tile) {
        craftResult =  new ResultContainer();
        craftingGrid = new InventoryWorkbench(this, (MachineItemHandler<?>) tile.itemHandler.map(m -> m).orElse(null), 3, 3);
        addSlot(new SlotWorkTableResult((MachineItemHandler<?>) tile.itemHandler.map(m -> m).orElse(null), playerInv.player, craftingGrid, craftResult, 0, 136, 46));
        Object2IntMap<String> slotIndexMap = new Object2IntOpenHashMap<>();
        for (SlotData slot : tile.getMachineType().getSlots(tile.getMachineTier())) {
            slotIndexMap.computeIntIfAbsent(slot.getType().getId(), k -> 0);
            Slot supplier;
            if (slot.getType().getId().equals("crafting")){
                supplier = new Slot(craftingGrid, slotIndexMap.getInt(slot.getType().getId()), slot.getX(), slot.getY());
            } else {
                supplier = slot.getType().getSlotSupplier().get((SlotType) slot.getType(), tile, tile.itemHandler.map(t -> t.getAll()).orElse(Collections.emptyMap()), slotIndexMap.getInt(slot.getType().getId()),(SlotData) slot);
            }
            addSlot(supplier);
            slotIndexMap.computeInt(slot.getType().getId(),(a,b) -> {
                if (b == null) return 0;
                return b + 1;
            });
        }
    }

    @Override
    public void clicked(int slotId, int dragType, ClickType clickTypeIn, Player player) {

        boolean clickTypeCrafting = slotId == 0 && slots.get(slotId).hasItem() &&
                (clickTypeIn.equals(ClickType.PICKUP) || clickTypeIn.equals(ClickType.QUICK_MOVE));

        //Save the Matrix State before Crafting
        NonNullList<ItemStack> beforeAction = NonNullList.withSize(9, ItemStack.EMPTY);
        if(clickTypeCrafting){
            for (int i = 17; i < 26; ++i) {
                Slot matrixSlot = slots.get(i);
                ItemStack matrixStack = matrixSlot.getItem();
                beforeAction.set(i - 17, matrixStack);
            }
        }

        super.clicked(slotId, dragType, clickTypeIn, player);

        //Try to pull from the Project Table Inventory if the last of an item for a recipe.
        if(clickTypeCrafting){
            for (int i = 17; i < 26; ++i) {
                ItemStack beforeStack = beforeAction.get(i - 17);
                Slot matrixSlot = slots.get(i);
                ItemStack matrixStack = matrixSlot.getItem();

                if (matrixStack.getCount() == 0 && beforeStack.getCount() != 0) {
                    for (int ptSlot = 1; ptSlot < 17; ++ptSlot) {
                        Slot inventorySlot = slots.get(ptSlot);
                        ItemStack ptStack = inventorySlot.getItem();
                        if (ptStack.getItem() == beforeStack.getItem() && ptStack.getTag() == beforeStack.getTag()) {
                            ptStack.setCount(ptStack.getCount() - 1);
                            inventorySlot.set(ptStack);
                            beforeStack.setCount(1);
                            matrixSlot.set(beforeStack);
                            break;
                        }
                    }
                }
            }
        }
    }

    protected static void updateCrafting(int id, Level world, Player playerEntity, CraftingContainer craftingInventory, ResultContainer craftResultInventory) {
        if (!world.isClientSide) {
            ServerPlayer serverplayerentity = (ServerPlayer)playerEntity;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<CraftingRecipe> optional = world.getServer().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, craftingInventory, world);
            if (optional.isPresent()) {
                CraftingRecipe icraftingrecipe = optional.get();
                if (craftResultInventory.setRecipeUsed(world, serverplayerentity, icraftingrecipe)) {
                    itemstack = icraftingrecipe.assemble(craftingInventory);
                }
            }

            craftResultInventory.setItem(0, itemstack);
            serverplayerentity.connection.send(new ClientboundContainerSetSlotPacket(id, 0, itemstack));
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void slotsChanged(Container inventoryIn) {
        updateCrafting(this.containerId, this.playerInv.player.getCommandSenderWorld(), this.playerInv.player, this.craftingGrid, this.craftResult);
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return true;
    }

    public void clearCraftingGrid() {
        for (int i = 17; i < 26; i++) {
            Slot slot = (Slot) slots.get(i);
            if (slot.hasItem()) {
                moveItemStackTo(slot.getItem(), 1, 17, false);
                if (slot.getItem().getCount() <= 0)
                    slot.set(ItemStack.EMPTY);
            }
        }
    }

    public void clearCraftingGridToPlayer() {
        for (int i = 17; i < 26; i++) {
            Slot slot = (Slot) slots.get(i);
            if (slot.hasItem()) {
                moveItemStackTo(slot.getItem(), 32, 68, false);
                if (slot.getItem().getCount() <= 0)
                    slot.set(ItemStack.EMPTY);
            }
        }
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slotIn) {
        return slotIn.container != this.craftResult && super.canTakeItemForPickAll(stack, slotIn);
    }

    public CraftingContainer getCraftingGrid() {
        return craftingGrid;
    }

    /*
     * 0 result, 17-25 matrix,  1 - 16 inventory, 32 - 67 player inv.
     */
    @Override
    public ItemStack quickMoveStack(Player player, int slotId) {
        ItemStack itemstack;
        Slot slot = slots.get(slotId);
        if (slot.hasItem() && slotId == 0) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (!moveItemStackTo(itemstack1, 32, 68, false))
                return ItemStack.EMPTY;
            if (itemstack1.getCount() == 0) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
            if (itemstack1.getCount() != itemstack.getCount()) {
                slot.onQuickCraft(itemstack, itemstack1);
            } else {

                this.slotsChanged(this.craftingGrid);
                return ItemStack.EMPTY;
            }
            ItemStack itemstack2 = slot.onTake(player, itemstack1);

            player.drop(itemstack2, false);
            this.slotsChanged(this.craftingGrid);
            return itemstack;
        } else {
           return super.quickMoveStack(player, slotId);
        }
    }
}
