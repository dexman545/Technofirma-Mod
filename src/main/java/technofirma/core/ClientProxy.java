package technofirma.core;

import technofirma.client.handlers.GuiHandler;
import technofirma.client.renderer.RenderDarkOre;
import technofirma.client.renderer.RenderLantern;
import technofirma.client.renderer.RenderMagicAnvil;
import technofirma.client.renderer.tesr.TESRMagicAnvil;
import technofirma.events.ThaumcraftTreeFell;
import technofirma.handlers.NEIHandler;
import technofirma.tileentities.TEMagicAnvil;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void RegisterRenderers()
	{
		// Our Magic Anvil ID
		RenderingRegistry.registerBlockHandler(TechnofirmaCore.OilLanternRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderLantern());
	
		if (TechnofirmaCore.FoundThaumcraft)
		{
			// Our Dark Ore render ID
			RenderingRegistry.registerBlockHandler(TechnofirmaCore.DarkOreRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderDarkOre());
		
			// Our Magic Anvil ID
			RenderingRegistry.registerBlockHandler(TechnofirmaCore.DarkMagicAnvilRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderMagicAnvil());
		}
	}
	
	@Override
	public void RegisterMagicTileEntities()
	{
		ClientRegistry.registerTileEntity(TEMagicAnvil.class, "MagicAnvil", new TESRMagicAnvil());
	}
	
	@Override
	public void RegisterGuiHandler()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(TechnofirmaCore.instance, new GuiHandler());
	}
	
	@Override
	public void RegisterEvents()
	{
		// TreeCap Handler
		MinecraftForge.EVENT_BUS.register(new ThaumcraftTreeFell());
		
		// NEI Handler
		MinecraftForge.EVENT_BUS.register(new NEIHandler());
		
		// GUI Handler
		MinecraftForge.EVENT_BUS.register(new GuiHandler());
	}
}
