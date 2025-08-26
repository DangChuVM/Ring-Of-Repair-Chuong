package com.chuong.ringofrepair.init;

import com.chuong.ringofrepair.RingOfRepair;
import com.chuong.ringofrepair.items.ItemCustomRingRepair;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ItemInit
{
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event)
		{
			event.getRegistry().registerAll
			(
				ItemList.ring_of_repair = new ItemCustomRingRepair(new Item.Properties().maxStackSize(1).group(RingOfRepair.RING_OF_REPAIR_GROUP)).setRegistryName(location("ring_of_repair"))
			);
			RingOfRepair.LOGGER.info("Ring of Repair Items registered.");
		}
		
		private static ResourceLocation location(String name)
		{
			return new ResourceLocation(RingOfRepair.MOD_ID, name);
		}
	}
}
