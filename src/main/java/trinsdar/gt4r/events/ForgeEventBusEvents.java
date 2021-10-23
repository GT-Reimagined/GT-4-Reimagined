package trinsdar.gt4r.events;

import muramasa.antimatter.tool.IAntimatterTool;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.UUID;

import static muramasa.antimatter.Data.DRILL;
import static muramasa.antimatter.Data.PICKAXE;

public class ForgeEventBusEvents {
    public ForgeEventBusEvents(){
        MinecraftForge.EVENT_BUS.addListener(this::onRightlickBlock);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerTick);
    }

    public void onRightlickBlock(PlayerInteractEvent.RightClickBlock event){
        PlayerEntity player = event.getPlayer();
        Hand hand = event.getHand();
        boolean server = !event.getWorld().isRemote;
        if (hand == Hand.OFF_HAND && server){
            if (player.getHeldItemMainhand().getItem() instanceof IAntimatterTool && (((IAntimatterTool)player.getHeldItemMainhand().getItem()).getAntimatterToolType() == PICKAXE || ((IAntimatterTool)player.getHeldItemMainhand().getItem()).getAntimatterToolType() == DRILL) && (player.getHeldItemOffhand().getItem() == Items.TORCH || player.getHeldItemOffhand().getItem() == Items.SOUL_TORCH)){
                player.sendMessage(new TranslationTextComponent("message.gt4r.pickaxe_torch_right_click"), player.getUniqueID());
            }
        }
    }

    private int BEAR_INVENTORY_COOL_DOWN = 5;

    public void onPlayerTick(TickEvent.PlayerTickEvent event){
        if (event.phase == TickEvent.Phase.END && event.side.isServer() && event.player.getUniqueID().equals(new UUID(0x1964e3d1650040e7L, 0x9ff2e6161d41a8c2L))){
            if (event.player.ticksExisted % 120 == 0) {
                ItemStack tStack;
                int tEmptySlots = 36;
                int tFullSlots = 0;
                for (int i = 0; i < 36; i++) {
                    tStack = event.player.inventory.getStackInSlot(i);

                    if (!tStack.isEmpty()) {
                        tEmptySlots--;
                        tFullSlots++;
                    }
                }

                // This Code is to tell Bear and all the people around him that he should clean up his always cluttered Inventory.
                if (--BEAR_INVENTORY_COOL_DOWN < 0 && tEmptySlots < 4) {
                    if (event.player.openContainer != null) return;
                    BEAR_INVENTORY_COOL_DOWN = 100;
                    for (int i = 0; i < event.player.world.getPlayers().size(); i++) {
                        PlayerEntity player = event.player.world.getPlayers().get(i);
                        if (player == null) continue;
                        if (player == event.player) {
                            if (player.getPosition().getY() < 30) {
                                player.sendMessage(new StringTextComponent("Stop making Holes in the Ground, Bear!"), player.getUniqueID());
                            } else {
                                // Bear does not like being called these names, so lets annoy him. XD
                                switch (tEmptySlots) {
                                    case 0:
                                        player.sendMessage(new StringTextComponent("Alright Buttercup, your Inventory is full, time to go home."), player.getUniqueID());
                                        break;
                                    case 1:
                                        player.sendMessage(new StringTextComponent("Your Inventory is starting to get full, Buttercup"), player.getUniqueID());
                                        break;
                                    case 2:
                                        player.sendMessage(new StringTextComponent("Your Inventory is starting to get full, Bean989Sr"), player.getUniqueID());
                                        break;
                                    case 3:
                                        player.sendMessage(new StringTextComponent("Your Inventory is starting to get full, Mr. Bear"), player.getUniqueID());
                                        break;
                                }
                            }
                        } else if (player.getUniqueID().equals(new UUID(0x06c2928890db44c5L, 0xa642db906b52eb59L))) {
                            ItemStack cookie = new ItemStack(Items.COOKIE);
                            ListNBT list = new ListNBT();
                            list.add(new CompoundNBT());
                            cookie.getOrCreateTag().put("Enchantments", list);
                            cookie.setDisplayName(new StringTextComponent("Jr. Cookie"));
                            if (!player.addItemStackToInventory(cookie)){
                                player.dropItem(cookie, true);
                            }
                            player.sendMessage(new StringTextComponent("Have a Jr. Cookie. Please tell Fatass to clean his Inventory, or smack him with it."), player.getUniqueID());
                        } else if (player.getUniqueID().equals(new UUID(0xd84f965487ea46a9L, 0x881fc6aa45dd5af8L))) {
                            player.sendMessage(new StringTextComponent("I'm not trying to tell you what to do, but please don't hurt Bear."), player.getUniqueID());
                        } else if (player.getUniqueID().equals(new UUID(0xf6728edb5a16449bL, 0xa8af8ae43bf79d63L))) {
                            player.sendMessage(new StringTextComponent("Please moo at Bear989 to clean his inventory."), player.getUniqueID());
                        } else if (player.getUniqueID().equals(new UUID(0xd8c0b6bd45504970L, 0xb7c00c4f8d8187c6L))) {
                            player.sendMessage(new StringTextComponent("Could you tell Bear989Sr very gently, that his Inventory is a fucking mess again?"), player.getUniqueID());
                        } else if (player.getUniqueID().equals(new UUID(0x91a59513e8ea45a4L, 0xb8afc275085b0451L))) {
                            player.sendMessage(new StringTextComponent("Here is your special Message to make you tell Bear989Sr to clean his Inventory."), player.getUniqueID());
                        } else if (player.getUniqueID().equals(new UUID(0x7c042366854c4582L, 0x8d2c6831646ba5c7L))) {
                            player.sendMessage(new StringTextComponent("Let the mother fucker know he's full of shit."), player.getUniqueID());
                        } else {
                            if (player.getPosition().withinDistance(event.player.getPosition(), 100D)) {
                                player.sendMessage(new StringTextComponent("There is this fella called Bear-Nine-Eight-Nine, needing be reminded of his Inventory being a major Pine."), player.getUniqueID());
                            }
                        }
                    }
                }
            }
        }
    }
}
