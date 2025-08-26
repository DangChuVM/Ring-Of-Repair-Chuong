package com.chuong.ringofrepair.util;

import com.chuong.ringofrepair.RingOfRepair;
import com.chuong.ringofrepair.init.ItemList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = RingOfRepair.MOD_ID, bus = EventBusSubscriber.Bus.FORGE )
public class RepairTickHandler
{
	public static int repairTickRate = 120;
	
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		PlayerEntity player = event.player;
		PlayerInventory inv = event.player.inventory;
		EnderChestInventory end_inv = player.getInventoryEnderChest();

		for (int slot = 0; slot < inv.getSizeInventory(); slot++)
		{
			ItemStack stack = inv.getStackInSlot(slot);
			if (stack.getItem() == ItemList.ring_of_repair)
			{	
				if (player.ticksExisted % repairTickRate == 0)
        		{
					repair(player, inv);
       			 	} 
			}
		}
		
		for (int slot = 0; slot < end_inv.getSizeInventory(); slot++)
		{
			ItemStack stack = end_inv.getStackInSlot(slot);
			if (stack.getItem() == ItemList.ring_of_repair)
			{	
				if (player.ticksExisted % repairTickRate == 0)
        		{
					repair(player, inv);
       			 	} 
			}
		}
		
		if (SupportMods.CURIOS.isLoaded())
	    {
			if (CuriosUtil.findItem(ItemList.ring_of_repair, player) != ItemStack.EMPTY)
			{
				if (player.ticksExisted % repairTickRate == 0)
	    		{
					repair(player, inv);
	   			 	}  
		    }
	    }  
	}
	
	private static void repair(PlayerEntity player, PlayerInventory inv)
	{		
		for(int slot = 0; slot < inv.getSizeInventory(); slot++)
		{
			ItemStack target = inv.getStackInSlot(slot);
			if (!target.isEmpty())
			{
				if (!(target == player.getHeldItemMainhand()))
				{
					if (target.isDamaged() && target.getItem().isRepairable(target))
					{
						target.setDamage(target.getDamage() - 1);
						return; 
					}
				}
			}
		}
	}
}
