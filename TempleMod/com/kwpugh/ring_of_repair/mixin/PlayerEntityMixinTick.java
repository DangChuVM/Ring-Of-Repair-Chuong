/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1937
 *  net.minecraft.class_3218
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.kwpugh.ring_of_repair.mixin;

import com.kwpugh.ring_of_repair.ItemRingRepair;
import com.kwpugh.ring_of_repair.PlayerEquipUtil;
import com.kwpugh.ring_of_repair.RingOfRepair;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_3218;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_1657.class})
public abstract class PlayerEntityMixinTick
extends class_1309 {
    protected PlayerEntityMixinTick(class_1299<? extends class_1309> type, class_1937 world) {
        super(type, world);
    }

    @Inject(method={"tick"}, at={@At(value="HEAD")})
    public void repairTick(CallbackInfo ci) {
        class_1937 class_19372 = this.method_37908();
        if (class_19372 instanceof class_3218) {
            class_3218 server = (class_3218)class_19372;
            class_1657 player = (class_1657)this;
            if (PlayerEquipUtil.hasItemInInventory(player, RingOfRepair.RING_OF_REPAIR) || PlayerEquipUtil.hasItemInEnderchest(player, RingOfRepair.RING_OF_REPAIR)) {
                ItemRingRepair.repairItems(this.method_37908(), player);
            }
        }
    }
}

