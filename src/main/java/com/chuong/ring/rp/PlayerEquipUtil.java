package com.chuong.ring.rp;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public final class PlayerEquipUtil {

    public static boolean hasItemInOffHand(PlayerEntity player, Item item) {
        ItemStack offHand = player.getOffHandStack();
        return offHand.getItem() == item;
    }

    public static boolean hasItemInMainHand(PlayerEntity player, Item item) {
        ItemStack mainHand = player.getMainHandStack();
        return mainHand.getItem() == item;
    }

    public static boolean hasItemInInventory(PlayerEntity player, Item item) {
        PlayerInventory inv = player.getInventory();
        int size = inv.size();
        for (int slot = 0; slot < size; ++slot) {
            ItemStack stack = inv.getStack(slot);
            if (stack.getItem() == item) {
                return true;
            }
        }
        return false;
    }

    public static ItemStack getItemInInventory(PlayerEntity player, Item item) {
        PlayerInventory inv = player.getInventory();
        int size = inv.size();
        for (int slot = 0; slot < size; ++slot) {
            ItemStack stack = inv.getStack(slot);
            if (stack.getItem() == item) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    public static boolean hasItemInEnderchest(PlayerEntity player, Item item) {
        if (!(player instanceof ServerPlayerEntity serverPlayer)) {
            return false;
        }
        
        EnderChestInventory inv = serverPlayer.getEnderChestInventory();
        int size = inv.size();
        for (int slot = 0; slot < size; ++slot) {
            ItemStack stack = inv.getStack(slot);
            if (stack.getItem() == item) {
                return true;
            }
        }
        return false;
    }

    public static ItemStack getItemStackInEnderchest(PlayerEntity player, Item item) {
        if (!(player instanceof ServerPlayerEntity serverPlayer)) {
            return ItemStack.EMPTY;
        }

        EnderChestInventory inv = serverPlayer.getEnderChestInventory();
        int size = inv.size();
        for (int slot = 0; slot < size; ++slot) {
            ItemStack stack = inv.getStack(slot);
            if (stack.getItem() == item) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }
}
