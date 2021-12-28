package trinsdar.gt4r.tile.single;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.minecart.MinecartEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TileEntityTeleporter extends TileEntityMachine<TileEntityTeleporter> {

    @Nullable
    BlockPos destination = null;
    boolean redstoneTicked = false;

    public TileEntityTeleporter(Machine<?> type) {
        super(type);
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        super.load(state, tag);
        this.redstoneTicked = tag.getBoolean("redstoneTicked");
        if (tag.contains("destination")){
            CompoundNBT pos = tag.getCompound("destination");
            destination = new BlockPos(pos.getInt("X"), pos.getInt("Y"), pos.getInt("Z"));
        }
    }


    @Override
    public CompoundNBT save(CompoundNBT tag) {
        super.save(tag);
        tag.putBoolean("redstoneTicked", redstoneTicked);
        if (destination == null) return tag;
        CompoundNBT pos = new CompoundNBT();
        pos.putInt("X", destination.getX());
        pos.putInt("Y", destination.getY());
        pos.putInt("Z", destination.getZ());
        tag.put("destination", pos);
        return tag;
    }

    @Override
    public void onBlockUpdate(BlockPos neighbor) {
        super.onBlockUpdate(neighbor);
        boolean flag = this.getLevel().hasNeighborSignal(this.getBlockPos());
        if (flag && !redstoneTicked){
            redstoneTicked = true;
            findEntityToTeleport();
        } else if (!flag && redstoneTicked){
            redstoneTicked = false;
        }

    }

    public void findEntityToTeleport(){
        List<Entity> entites = this.getLevel().getEntities(null, new AxisAlignedBB(this.getBlockPos().offset(-2, -3, -2), this.getBlockPos().offset(2, 3, 2)));
        if (!entites.isEmpty()){
            if (destination == null) return;
            TileEntity teleporter = level.getBlockEntity(destination);
            if (teleporter instanceof TileEntityTeleporter){
                setMachineState(MachineState.ACTIVE);
                final double[] minimumDistance = {Double.MAX_VALUE};
                BlockPos offset = this.getBlockPos().relative(this.getFacing());
                Entity[] entityToTeleport = new Entity[]{null};
                entites.forEach(e -> {
                    if (e.getVehicle() == null && e.isAlive()){
                        double distanceSq = e.distanceToSqr(offset.getX(), offset.getY(), offset.getZ());
                        if (distanceSq >= minimumDistance[0]){
                            return;
                        }
                        minimumDistance[0] = distanceSq;
                        entityToTeleport[0] = e;
                    }
                });
                if (entityToTeleport[0] != null){
                    teleportEntity(entityToTeleport[0], (TileEntityTeleporter) teleporter);
                }
            } else {
                destination = null;
            }
        }

    }

    public void teleportEntity(Entity entity, TileEntityTeleporter teleporter){
        int baseWeight = entityWeight(entity);
        if (baseWeight == 0) return;
        Direction facing = teleporter.getFacing();
        BlockPos teleportTo = teleporter.getBlockPos().relative(facing);
        double distance = Math.sqrt(this.getBlockPos().distSqr(destination));
        int energyCost = (int)(baseWeight * Math.pow(distance + 10.0, 0.7) * 5.0);
        if (energyHandler.map(e -> e.getEnergy() >= energyCost).orElse(false)){
            energyHandler.ifPresent(e -> e.extractEnergy((int) (energyCost * AntimatterConfig.GAMEPLAY.EU_TO_FE_RATIO), false));
            entity.teleportTo(teleportTo.getX(), teleportTo.getY(), teleportTo.getZ());
        }
    }

    public int entityWeight(Entity entity){
        int totalWeight = 0;
        if (entity instanceof ItemEntity){
            ItemStack stack = ((ItemEntity)entity).getItem();
            totalWeight += (stack.getCount() / stack.getMaxStackSize()) * 100;
        }
        else if (entity instanceof AnimalEntity || entity instanceof MinecartEntity || entity instanceof BoatEntity){
            totalWeight += 100;
        }
        else if (entity instanceof PlayerEntity){
            totalWeight += 1000;
            PlayerInventory inventory = ((PlayerEntity)entity).inventory;
            for (ItemStack stack : inventory.items) {
                if (stack.isEmpty()) continue;
                totalWeight += (stack.getCount() / stack.getMaxStackSize()) * 100;
            }
            for (ItemStack stack : inventory.armor) {
                if (stack.isEmpty()) continue;
                totalWeight += (stack.getCount() / stack.getMaxStackSize()) * 100;
            }
            ItemStack stack = inventory.player.getOffhandItem();
            if (!stack.isEmpty()){
                totalWeight += (stack.getCount() / stack.getMaxStackSize()) * 100;
            }
        }
        else if (entity instanceof GhastEntity){
            totalWeight += 2500;
        }
        else if (entity instanceof CreatureEntity){
            totalWeight += 500;
        }
        if (entity instanceof LivingEntity && !(entity instanceof PlayerEntity)){
            for (EquipmentSlotType slot : EquipmentSlotType.values()) {
                ItemStack stack = ((LivingEntity)entity).getItemBySlot(slot);
                if (!stack.isEmpty()) totalWeight += (stack.getCount() / stack.getMaxStackSize()) * 100;
            }
        }
        for (Entity rider : entity.getPassengers()) {
            totalWeight += entityWeight(rider);
        }
        return totalWeight;
    }
}
