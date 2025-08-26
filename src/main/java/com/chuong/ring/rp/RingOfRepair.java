package com.chuong.ring.rp;

import com.chuong.ring.rp.config.RingOfRepairConfig;
import com.chuong.ring.rp.item.RingOfRepairItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RingOfRepair implements ModInitializer {
    public static final String MOD_ID = "ring-of-repair";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static RingOfRepairConfig CONFIG;

    public static final Item RING_OF_REPAIR = new RingOfRepairItem(new Item.Settings());

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Ring of Repair");

        CONFIG = RingOfRepairConfig.load();

        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "ring_of_repair"), RING_OF_REPAIR);
    }
}
