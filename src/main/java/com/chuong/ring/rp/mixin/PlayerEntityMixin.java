package com.chuong.ring.rp.mixin;

import com.chuong.ring.rp.RingOfRepair;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    private int ticksSinceLastRepair = 0;

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (!player.getWorld().isClient()) {
            int repairIntervalTicks = (int) (RingOfRepair.CONFIG.repairIntervalSeconds * 20);
            if (repairIntervalTicks <= 0) return; // Avoid division by zero or negative intervals

            ticksSinceLastRepair++;
            if (ticksSinceLastRepair >= repairIntervalTicks) {
                ticksSinceLastRepair = 0;

                if (hasRing(player)) {
                    repairFirstDamagedItem(player);
                }
            }
        }
    }

    private boolean hasRing(PlayerEntity player) {
        // Check main inventory, armor, and off-hand
        return player.getInventory().contains(new ItemStack(RingOfRepair.RING_OF_REPAIR));
    }

    private void repairFirstDamagedItem(PlayerEntity player) {
        Inventory inventory = player.getInventory();
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.isDamaged()) {
                stack.setDamage(stack.getDamage() - 1);
                // Repair only one item per interval to keep it balanced.
                break;
            }
        }
    }
}
