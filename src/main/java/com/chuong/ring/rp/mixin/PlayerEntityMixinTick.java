package com.chuong.ring.rp.mixin;

import com.chuong.ring.rp.ItemRingRepair;
import com.chuong.ring.rp.PlayerEquipUtil;
import com.chuong.ring.rp.RingOfRepair;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixinTick extends LivingEntity {

    protected PlayerEntityMixinTick(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        // The ItemRingRepair.repairItems method already checks if it's the server side
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (PlayerEquipUtil.hasItemInInventory(player, RingOfRepair.RING_OF_REPAIR) || PlayerEquipUtil.hasItemInEnderchest(player, RingOfRepair.RING_OF_REPAIR)) {
            ItemRingRepair.repairItems(player.getWorld(), player);
        }
    }
}
