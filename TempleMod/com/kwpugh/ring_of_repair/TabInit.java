/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
 *  net.minecraft.class_1761
 *  net.minecraft.class_1799
 *  net.minecraft.class_1935
 *  net.minecraft.class_2378
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_5321
 *  net.minecraft.class_7923
 *  net.minecraft.class_7924
 */
package com.kwpugh.ring_of_repair;

import com.kwpugh.ring_of_repair.RingOfRepair;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.class_1761;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_2378;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_7923;
import net.minecraft.class_7924;

public class TabInit {
    public static final class_5321<class_1761> RING_REPAIR_GROUP = class_5321.method_29179((class_5321)class_7924.field_44688, (class_2960)class_2960.method_60655((String)"ring_of_repair", (String)"ring_repair_group"));

    public static void addTab() {
        class_2378.method_39197((class_2378)class_7923.field_44687, RING_REPAIR_GROUP, (Object)FabricItemGroup.builder().method_47320(() -> new class_1799((class_1935)RingOfRepair.RING_OF_REPAIR)).method_47321((class_2561)class_2561.method_43471((String)"itemGroup.ring_of_repair.ring_repair_group")).method_47317((context, entries) -> entries.method_45421((class_1935)RingOfRepair.RING_OF_REPAIR)).method_47324());
    }
}

