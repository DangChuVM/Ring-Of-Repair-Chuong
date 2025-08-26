/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1792$class_9635
 *  net.minecraft.class_1799
 *  net.minecraft.class_1836
 *  net.minecraft.class_1937
 *  net.minecraft.class_2561
 *  net.minecraft.class_3222
 *  net.minecraft.class_3489
 */
package com.kwpugh.ring_of_repair;

import com.kwpugh.ring_of_repair.ModConfig;
import com.kwpugh.ring_of_repair.TagInit;
import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1937;
import net.minecraft.class_2561;
import net.minecraft.class_3222;
import net.minecraft.class_3489;

public class ItemRingRepair
extends class_1792 {
    public ItemRingRepair(class_1792.class_1793 settings) {
        super(settings);
    }

    public static void repairItems(class_1937 world, class_1657 player) {
        if (!world.field_9236) {
            class_3222 serverPlayer = (class_3222)player;
            if (serverPlayer.field_6012 % ModConfig.getConfig().ringRepairInterval == 0) {
                for (int i = 0; i < serverPlayer.method_31548().method_5439(); ++i) {
                    class_1799 stack2 = serverPlayer.method_31548().method_5438(i);
                    if (stack2.method_31573(TagInit.RING_REPAIR_BLACKLIST) || !stack2.method_31573(class_3489.field_48310) || stack2.method_7960() || stack2 == serverPlayer.method_6047() || !stack2.method_7986()) continue;
                    stack2.method_7974(stack2.method_7919() - 1);
                    break;
                }
            }
        }
    }

    public void method_7851(class_1799 stack, class_1792.class_9635 context, List<class_2561> tooltip, class_1836 type) {
        tooltip.add((class_2561)class_2561.method_43471((String)"item.ring_of_repair.ring_of_repair.tip1").method_27692(class_124.field_1060));
        tooltip.add((class_2561)class_2561.method_43471((String)"item.ring_of_repair.ring_of_repair.tip2").method_27692(class_124.field_1054));
    }
}

