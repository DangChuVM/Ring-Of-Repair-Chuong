/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1730
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_3222
 */
package com.kwpugh.ring_of_repair;

import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1730;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_3222;

public final class PlayerEquipUtil {
    public static boolean hasItemInOffHand(class_1657 player, class_1792 item) {
        class_1799 offHand = player.method_6079();
        return offHand.method_7909() == item;
    }

    public static boolean hasItemInMainHand(class_1657 player, class_1792 item) {
        class_1799 offHand = player.method_6047();
        return offHand.method_7909() == item;
    }

    public static boolean hasItemInInventory(class_1657 player, class_1792 item) {
        class_1661 inv = player.method_31548();
        int size = inv.method_5439();
        for (int slot = 0; slot < size; ++slot) {
            class_1799 stack = inv.method_5438(slot);
            if (stack.method_7909() != item) continue;
            return true;
        }
        return false;
    }

    public static class_1799 getItemInInventory(class_1657 player, class_1792 item) {
        class_1661 inv = player.method_31548();
        int size = inv.method_5439();
        for (int slot = 0; slot < size; ++slot) {
            class_1799 stack = inv.method_5438(slot);
            if (stack.method_7909() != item) continue;
            return stack;
        }
        return class_1799.field_8037;
    }

    public static boolean hasItemInEnderchest(class_1657 player, class_1792 item) {
        if (!(player instanceof class_3222)) {
            return false;
        }
        class_3222 serverPlayer = (class_3222)player;
        class_1730 inv = serverPlayer.method_7274();
        int size = inv.method_5439();
        for (int slot = 0; slot < size; ++slot) {
            class_1799 stack = inv.method_5438(slot);
            if (stack.method_7909() != item) continue;
            return true;
        }
        return false;
    }

    public static class_1799 getItemStackInEnderchest(class_1657 player, class_1792 item) {
        if (!(player instanceof class_3222)) {
            return class_1799.field_8037;
        }
        class_3222 serverPlayer = (class_3222)player;
        class_1730 inv = serverPlayer.method_7274();
        int size = inv.method_5439();
        for (int slot = 0; slot < size; ++slot) {
            class_1799 stack = inv.method_5438(slot);
            if (stack.method_7909() != item) continue;
            return stack;
        }
        return class_1799.field_8037;
    }
}

