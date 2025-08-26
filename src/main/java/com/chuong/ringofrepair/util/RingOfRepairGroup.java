package com.chuong.ringofrepair.util;

import com.chuong.ringofrepair.init.ItemInit;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class RingOfRepairGroup extends ItemGroup
{
	public RingOfRepairGroup() 
	{
		super("ring_of_repair");
	}

	@Override
	public ItemStack createIcon() 
	{
		return new ItemStack(ItemInit.ring_of_repair);
	}	
}
