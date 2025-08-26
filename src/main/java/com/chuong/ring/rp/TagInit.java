package com.chuong.ring.rp;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class TagInit {

    // The namespace "ring-of-repair" should match the mod's ID in fabric.mod.json
    public static final TagKey<Item> RING_REPAIR_BLACKLIST = TagKey.of(RegistryKeys.ITEM, Identifier.of("ring-of-repair", "ring_repair_blacklist"));

}
