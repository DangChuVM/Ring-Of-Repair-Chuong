/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.ModInitializer
 *  net.minecraft.class_1792
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_2378
 *  net.minecraft.class_2960
 *  net.minecraft.class_7923
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package com.kwpugh.ring_of_repair;

import com.kwpugh.ring_of_repair.ItemRingRepair;
import com.kwpugh.ring_of_repair.ModConfig;
import com.kwpugh.ring_of_repair.TabInit;
import net.fabricmc.api.ModInitializer;
import net.minecraft.class_1792;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RingOfRepair
implements ModInitializer {
    public static final String MOD_ID = "ring_of_repair";
    public static final Logger LOGGER = LogManager.getLogger(RingOfRepair.class);
    public static class_1792 RING_OF_REPAIR = new ItemRingRepair(new class_1792.class_1793().method_7889(1));

    public void onInitialize() {
        ModConfig.setup();
        class_2378.method_10230((class_2378)class_7923.field_41178, (class_2960)class_2960.method_60655((String)MOD_ID, (String)MOD_ID), (Object)RING_OF_REPAIR);
        TabInit.addTab();
    }
}

