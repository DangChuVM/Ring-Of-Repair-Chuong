package com.chuong.ring.rp;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TooltipType;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemRingRepair extends Item {

    public ItemRingRepair(Settings settings) {
        super(settings);
    }

    public static void repairItems(World world, PlayerEntity player) {
        if (!world.isClient()) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            if (serverPlayer.tickCount % ModConfig.getConfig().ringRepairInterval == 0) {
                for (int i = 0; i < serverPlayer.getInventory().size(); ++i) {
                    ItemStack stack2 = serverPlayer.getInventory().getStack(i);

                    // Skip if in blacklist, not a tool, empty, in main hand, or not damaged
                    if (stack2.isIn(TagInit.RING_REPAIR_BLACKLIST) ||
                        !stack2.isIn(ItemTags.TOOLS) ||
                        stack2.isEmpty() ||
                        stack2 == serverPlayer.getMainHandStack() ||
                        !stack2.isDamaged()) {
                        continue;
                    }

                    stack2.setDamage(stack2.getDamage() - 1);
                    break; // Repair one item per interval
                }
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.ring_of_repair.ring_of_repair.tip1").formatted(Formatting.GREEN));
        tooltip.add(Text.translatable("item.ring_of_repair.ring_of_repair.tip2").formatted(Formatting.YELLOW));
    }
}
