package com.chuong.ring.rp;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class TabInit {

    public static final RegistryKey<ItemGroup> RING_REPAIR_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of("ring_of_repair", "ring_repair_group"));

    public static void addTab() {
        Registry.register(Registries.ITEM_GROUP, RING_REPAIR_GROUP, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.ring_of_repair.ring_repair_group"))
                .icon(() -> new ItemStack(RingOfRepair.RING_OF_REPAIR))
                .entries((displayContext, entries) -> {
                    entries.add(RingOfRepair.RING_OF_REPAIR);
                })
                .build());
    }
}
