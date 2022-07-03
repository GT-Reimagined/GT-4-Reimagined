package trinsdar.gt4r.events;

import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.event.events.common.TickEvent;
import muramasa.antimatter.tool.IAntimatterTool;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import trinsdar.gt4r.data.Attributes;

import java.util.UUID;

import static muramasa.antimatter.Data.DRILL;
import static muramasa.antimatter.Data.PICKAXE;

public class CommonEvents {
    @SuppressWarnings("NoTranslation")
    public static void onRightlickBlock(Player player, InteractionHand hand, boolean server){
        if (hand == InteractionHand.OFF_HAND && server){
            if (player.getMainHandItem().getItem() instanceof IAntimatterTool && (((IAntimatterTool)player.getMainHandItem().getItem()).getAntimatterToolType() == PICKAXE || ((IAntimatterTool)player.getMainHandItem().getItem()).getAntimatterToolType() == DRILL) && (player.getOffhandItem().getItem() == Items.TORCH || player.getOffhandItem().getItem() == Items.SOUL_TORCH)){
                player.sendMessage(new TranslatableComponent("message.gt4r.pickaxe_torch_right_click"), player.getUUID());
            }
        }
    }

    private static int BEAR_INVENTORY_COOL_DOWN = 5;

    public static void onPlayerTick(Player player, boolean isServer){
        if (isServer && player.getUUID().equals(new UUID(0x1964e3d1650040e7L, 0x9ff2e6161d41a8c2L))){
            if (player.tickCount % 120 == 0) {
                ItemStack tStack;
                int tEmptySlots = 36;
                int tFullSlots = 0;
                for (int i = 0; i < 36; i++) {
                    tStack = player.getInventory().getItem(i);

                    if (!tStack.isEmpty()) {
                        tEmptySlots--;
                        tFullSlots++;
                    }
                }

                // This Code is to tell Bear and all the people around him that he should clean up his always cluttered Inventory.
                if (--BEAR_INVENTORY_COOL_DOWN < 0 && tEmptySlots < 4) {
                    InventoryMenu playerContainer = player.inventoryMenu;
                    BEAR_INVENTORY_COOL_DOWN = 100;
                    for (int i = 0; i < player.level.players().size(); i++) {
                        Player player2 = player.level.players().get(i);
                        if (player2 == null) continue;
                        if (player2 == player) {
                            if (player2.blockPosition().getY() < 30) {
                                player2.sendMessage(new TextComponent("Stop making Holes in the Ground, Bear!"), player2.getUUID());
                            } else {
                                // Bear does not like being called these names, so lets annoy him. XD
                                switch (tEmptySlots) {
                                    case 0:
                                        player2.sendMessage(new TextComponent("Alright Buttercup, your Inventory is full, time to go home."), player2.getUUID());
                                        break;
                                    case 1:
                                        player2.sendMessage(new TextComponent("Your Inventory is starting to get full, Buttercup"), player2.getUUID());
                                        break;
                                    case 2:
                                        player2.sendMessage(new TextComponent("Your Inventory is starting to get full, Bean989Sr"), player2.getUUID());
                                        break;
                                    case 3:
                                        player2.sendMessage(new TextComponent("Your Inventory is starting to get full, Mr. Bear"), player2.getUUID());
                                        break;
                                }
                            }
                        } else if (player2.getUUID().equals(new UUID(0x06c2928890db44c5L, 0xa642db906b52eb59L))) {
                            ItemStack cookie = new ItemStack(Items.COOKIE);
                            ListTag list = new ListTag();
                            list.add(new CompoundTag());
                            cookie.getOrCreateTag().put("Enchantments", list);
                            cookie.setHoverName(new TextComponent("Jr. Cookie"));
                            if (!player2.addItem(cookie)){
                                player2.drop(cookie, true);
                            }
                            player2.sendMessage(new TextComponent("Have a Jr. Cookie. Please tell Fatass to clean his Inventory, or smack him with it."), player2.getUUID());
                        } else if (player2.getUUID().equals(new UUID(0xd84f965487ea46a9L, 0x881fc6aa45dd5af8L))) {
                            player2.sendMessage(new TextComponent("I'm not trying to tell you what to do, but please don't hurt Bear."), player2.getUUID());
                        } else if (player2.getUUID().equals(new UUID(0xf6728edb5a16449bL, 0xa8af8ae43bf79d63L))) {
                            player2.sendMessage(new TextComponent("Please moo at Bear989 to clean his inventory."), player2.getUUID());
                        } else if (player2.getUUID().equals(new UUID(0xd8c0b6bd45504970L, 0xb7c00c4f8d8187c6L))) {
                            player2.sendMessage(new TextComponent("Could you tell Bear989Sr very gently, that his Inventory is a fucking mess again?"), player2.getUUID());
                        } else if (player2.getUUID().equals(new UUID(0x91a59513e8ea45a4L, 0xb8afc275085b0451L))) {
                            player2.sendMessage(new TextComponent("Here is your special Message to make you tell Bear989Sr to clean his Inventory."), player2.getUUID());
                        } else if (player2.getUUID().equals(new UUID(0x7c042366854c4582L, 0x8d2c6831646ba5c7L))) {
                            player2.sendMessage(new TextComponent("Let the mother fucker know he's full of shit."), player2.getUUID());
                        } else {
                            if (player2.blockPosition().closerThan(player.blockPosition(), 100D)) {
                                player2.sendMessage(new TextComponent("There is this fella called Bear-Nine-Eight-Nine, needing be reminded of his Inventory being a major Pine."), player2.getUUID());
                            }
                        }
                    }
                }
            }
        }
    }

    public static void playerJoin(Player player){
        double base = player.getAttribute(Attributes.ATTACK_REACH).getBaseValue();
        if (base == 4.5){
            player.getAttribute(Attributes.ATTACK_REACH).setBaseValue(5.0);
        }
    }
}
