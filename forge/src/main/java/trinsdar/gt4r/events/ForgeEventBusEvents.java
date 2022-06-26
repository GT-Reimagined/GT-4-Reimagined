package trinsdar.gt4r.events;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.tool.IAntimatterTool;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import trinsdar.gt4r.data.Attributes;

import java.util.UUID;

import static muramasa.antimatter.Data.DRILL;
import static muramasa.antimatter.Data.PICKAXE;

public class ForgeEventBusEvents {
    private static boolean initialized = false;
    public ForgeEventBusEvents(){
        if (initialized){
            throw new IllegalStateException("Forge event bus utility class can't be initialized!");
        }
        MinecraftForge.EVENT_BUS.addListener(this::onRightlickBlock);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerTick);
        MinecraftForge.EVENT_BUS.addListener(this::playerJoin);
        initialized = true;
    }

    public void onRightlickBlock(PlayerInteractEvent.RightClickBlock event){
        Player player = event.getPlayer();
        InteractionHand hand = event.getHand();
        boolean server = !event.getWorld().isClientSide;
        if (hand == InteractionHand.OFF_HAND && server){
            if (player.getMainHandItem().getItem() instanceof IAntimatterTool && (((IAntimatterTool)player.getMainHandItem().getItem()).getAntimatterToolType() == PICKAXE || ((IAntimatterTool)player.getMainHandItem().getItem()).getAntimatterToolType() == DRILL) && (player.getOffhandItem().getItem() == Items.TORCH || player.getOffhandItem().getItem() == Items.SOUL_TORCH)){
                player.sendMessage(new TranslatableComponent("message.gt4r.pickaxe_torch_right_click"), player.getUUID());
            }
        }
    }

    private int BEAR_INVENTORY_COOL_DOWN = 5;

    public void onPlayerTick(TickEvent.PlayerTickEvent event){
        if (event.phase == TickEvent.Phase.END && event.side.isServer() && event.player.getUUID().equals(new UUID(0x1964e3d1650040e7L, 0x9ff2e6161d41a8c2L))){
            if (event.player.tickCount % 120 == 0) {
                ItemStack tStack;
                int tEmptySlots = 36;
                int tFullSlots = 0;
                for (int i = 0; i < 36; i++) {
                    tStack = event.player.getInventory().getItem(i);

                    if (!tStack.isEmpty()) {
                        tEmptySlots--;
                        tFullSlots++;
                    }
                }

                // This Code is to tell Bear and all the people around him that he should clean up his always cluttered Inventory.
                if (--BEAR_INVENTORY_COOL_DOWN < 0 && tEmptySlots < 4) {
                    InventoryMenu playerContainer = event.player.inventoryMenu;
                    BEAR_INVENTORY_COOL_DOWN = 100;
                    for (int i = 0; i < event.player.level.players().size(); i++) {
                        Player player = event.player.level.players().get(i);
                        if (player == null) continue;
                        if (player == event.player) {
                            if (player.blockPosition().getY() < 30) {
                                player.sendMessage(new TextComponent("Stop making Holes in the Ground, Bear!"), player.getUUID());
                            } else {
                                // Bear does not like being called these names, so lets annoy him. XD
                                switch (tEmptySlots) {
                                    case 0:
                                        player.sendMessage(new TextComponent("Alright Buttercup, your Inventory is full, time to go home."), player.getUUID());
                                        break;
                                    case 1:
                                        player.sendMessage(new TextComponent("Your Inventory is starting to get full, Buttercup"), player.getUUID());
                                        break;
                                    case 2:
                                        player.sendMessage(new TextComponent("Your Inventory is starting to get full, Bean989Sr"), player.getUUID());
                                        break;
                                    case 3:
                                        player.sendMessage(new TextComponent("Your Inventory is starting to get full, Mr. Bear"), player.getUUID());
                                        break;
                                }
                            }
                        } else if (player.getUUID().equals(new UUID(0x06c2928890db44c5L, 0xa642db906b52eb59L))) {
                            ItemStack cookie = new ItemStack(Items.COOKIE);
                            ListTag list = new ListTag();
                            list.add(new CompoundTag());
                            cookie.getOrCreateTag().put("Enchantments", list);
                            cookie.setHoverName(new TextComponent("Jr. Cookie"));
                            if (!player.addItem(cookie)){
                                player.drop(cookie, true);
                            }
                            player.sendMessage(new TextComponent("Have a Jr. Cookie. Please tell Fatass to clean his Inventory, or smack him with it."), player.getUUID());
                        } else if (player.getUUID().equals(new UUID(0xd84f965487ea46a9L, 0x881fc6aa45dd5af8L))) {
                            player.sendMessage(new TextComponent("I'm not trying to tell you what to do, but please don't hurt Bear."), player.getUUID());
                        } else if (player.getUUID().equals(new UUID(0xf6728edb5a16449bL, 0xa8af8ae43bf79d63L))) {
                            player.sendMessage(new TextComponent("Please moo at Bear989 to clean his inventory."), player.getUUID());
                        } else if (player.getUUID().equals(new UUID(0xd8c0b6bd45504970L, 0xb7c00c4f8d8187c6L))) {
                            player.sendMessage(new TextComponent("Could you tell Bear989Sr very gently, that his Inventory is a fucking mess again?"), player.getUUID());
                        } else if (player.getUUID().equals(new UUID(0x91a59513e8ea45a4L, 0xb8afc275085b0451L))) {
                            player.sendMessage(new TextComponent("Here is your special Message to make you tell Bear989Sr to clean his Inventory."), player.getUUID());
                        } else if (player.getUUID().equals(new UUID(0x7c042366854c4582L, 0x8d2c6831646ba5c7L))) {
                            player.sendMessage(new TextComponent("Let the mother fucker know he's full of shit."), player.getUUID());
                        } else {
                            if (player.blockPosition().closerThan(event.player.blockPosition(), 100D)) {
                                player.sendMessage(new TextComponent("There is this fella called Bear-Nine-Eight-Nine, needing be reminded of his Inventory being a major Pine."), player.getUUID());
                            }
                        }
                    }
                }
            }
        }
    }

    public void playerJoin(PlayerEvent.PlayerLoggedInEvent e){
        double base = e.getPlayer().getAttribute(Attributes.ATTACK_REACH).getBaseValue();
        if (base == 4.5){
            e.getPlayer().getAttribute(Attributes.ATTACK_REACH).setBaseValue(5.0);
        }
    }
}
