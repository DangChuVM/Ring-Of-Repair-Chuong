package com.chuong.ring.rp;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RingOfRepair implements ModInitializer {
    // Changed MOD_ID to match assets
	public static final String MOD_ID = "ring_of_repair";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // Item declaration from original mod
    public static final Item RING_OF_REPAIR = new ItemRingRepair(new Item.Settings().maxCount(1));

	@Override
	public void onInitialize() {
        // Initialize config and creative tab
        ModConfig.setup();
        TabInit.addTab();

        // Register the item
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "ring_of_repair"), RING_OF_REPAIR);
	}
}
