package com.chuong.ringofrepair;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chuong.ringofrepair.init.ItemInit;
import com.chuong.ringofrepair.util.RingOfRepairGroup;

import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.curios.api.CuriosAPI;
import top.theillusivec4.curios.api.imc.CurioIMCMessage;

@Mod(RingOfRepair.MOD_ID)
public class RingOfRepair 
{
	public static RingOfRepair instance;
		
	public static final String MOD_ID = "ring_of_repair";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);	
	public static final ItemGroup RING_OF_REPAIR_GROUP = new RingOfRepairGroup();
    
	public RingOfRepair() 
	{
		instance = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::modSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
    
	private void modSetup(final FMLCommonSetupEvent event)
	{
		LOGGER.info("Ring of Repair setup completed");
	}
	
	private void clientSetup(final FMLClientSetupEvent event)
	{
		LOGGER.info("Ring of Repair client setup completed");
	}
	
    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        InterModComms.sendTo("curios", CuriosAPI.IMC.REGISTER_TYPE, () -> new CurioIMCMessage("ring").setSize(1));
    }
}
