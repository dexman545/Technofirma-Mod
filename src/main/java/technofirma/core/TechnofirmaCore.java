package technofirma.core;

import technofirma.blocks.BlockDarkOre;
import technofirma.blocks.BlockTFIngotPile;
import technofirma.devices.BlockMagicAnvil;
import technofirma.events.AntiTFCEvents;
import thaumcraft.api.ItemApi;

import com.bioxx.tfc.Core.Recipes;
import com.bioxx.tfc.Core.Metal.Alloy;
import com.bioxx.tfc.Core.Metal.MetalRegistry;
import com.bioxx.tfc.api.HeatIndex;
import com.bioxx.tfc.api.HeatRaw;
import com.bioxx.tfc.api.HeatRegistry;
import com.bioxx.tfc.api.Metal;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Constant.Global;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid=TechnofirmaCore.MODID, name=TechnofirmaCore.MODName, version=TechnofirmaCore.VERSION, dependencies = "after:thaumcraft")
public class TechnofirmaCore
{
	@Instance("technofirma")
	public static TechnofirmaCore instance;
	
    public static final String MODID = "technofirma";
    public static final String MODName = "Technofirma";
    public static final String VERSION = "0.2.0";

	@SidedProxy(clientSide = "technofirma.core.ClientProxy", serverSide = "technofirma.core.CommonProxy")
	public static CommonProxy proxy;
    
	public static final String[] DARKORENAMES = {
		"Air Infused Stone", "Fire Infused Stone", "Water Infused Stone", "Earth Infused Stone", "Order Infused Stone", "Entropy Infused Stone"
	};
	
	public static final int[] DARKORECOLORS = {
		12303194, 12265472, 25787, 25600, 10658491, 4079191
	};
    
	public static Block TFIngotPile;
	
	public static Block DarkOre;
	public static Block ThaumicAnvil;
	public static Item ProspectingFocus;
	
	public static int DarkOreRenderID;
	public static int DarkMagicAnvilRenderID;
		
	public static boolean FoundThaumcraft;
	
	public static ToolMaterial ThaumicSteelMaterial;
	public static Item ThaumicSteelIngot;
	public static Item ThaumicSteelUnshaped;
	
	public static Item ThaumicSteelAxe;
	public static Item ThaumicSteelAxeHead;
	
	public static Item ThaumicSteelShovel;
	public static Item ThaumicSteelShovelHead;
	
	public static Item ThaumicSteelHoe;
	public static Item ThaumicSteelHoeHead;
	
	public static Item ThaumicSteelPickaxe;
	public static Item ThaumicSteelPickaxeHead;
	
	public static Item ThaumicSteelSword;
	public static Item ThaumicSteelSwordHead;
	
	public static Block OilLamp;
	public static int OilLanternRenderID;
	
	public static Metal ThaumicSteel;
	
	public TechnofirmaCore()
	{		
		// Assign our singleton
		instance = this;

		// Create our ore block
		DarkOre = new BlockDarkOre(Material.rock).setHardness(10F).setResistance(10F).setBlockName("Ore");
		
		// Register our ore block
		GameRegistry.registerBlock(DarkOre, "DarkOre");
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{				
		FMLCommonHandler.instance().bus().register(new AntiTFCEvents());
	}
	
	@EventHandler
	public void preload(FMLPreInitializationEvent event)
	{		
		FoundThaumcraft = ItemApi.getItem("itemThaumonomicon", 0) != null;
			
		proxy.RegisterGuiHandler();
		
		proxy.SetupBlocks();
		proxy.RegisterBlocks();
		proxy.RegisterTE();
						
		if (FoundThaumcraft)
		{
			proxy.RegisterMagicTileEntities();
			
			// Temp handling for metal
			HeatRaw ThaumicSteelRaw = new HeatRaw(0.35, 1540);
			
			// Create our ThaumicSteel material
			ThaumicSteelMaterial = EnumHelper.addToolMaterial("ThaumicSteel", 3, TFCItems.steelUses + 500, TFCItems.steelEff, 200, 10);
			
			proxy.SetupMagicItems();
			
			proxy.RegisterMagicItems();
			
			// Heat registry
			HeatRegistry.getInstance().addIndex(new HeatIndex(new ItemStack(ThaumicSteelIngot,1), ThaumicSteelRaw, new ItemStack(ThaumicSteelUnshaped, 1)));
			
			MetalRegistry.instance.addMetal(ThaumicSteel, Alloy.EnumTier.TierIV);
				
			// Setup our blocks
			ThaumicAnvil = new BlockMagicAnvil().setBlockName("Magic Anvil").setHardness(3).setResistance(100F);
			
			// Register our blocks
			GameRegistry.registerBlock(ThaumicAnvil, "Magic Anvil");
			
			TFIngotPile = new BlockTFIngotPile().setBlockName("ingotpile").setHardness(3);
			
			GameRegistry.registerBlock(TFIngotPile, "IngotPile");
		}
	}
			
	@EventHandler
	public void load(FMLInitializationEvent event)
	{					
		if (FoundThaumcraft)
		{
			proxy.RegisterAnvilPlans();
			proxy.RegisterAnvilRecipes();
			
			proxy.RegisterMagicAnvilPlans();
			proxy.RegisterMagicAnvilRecipes();

			proxy.RegisterMagicToolsClasses();
			
			proxy.RegisterRecipes();
		}
		
		proxy.SetupLampFuels();
				
		proxy.RegisterRenderers();
		
		proxy.RegisterWailaMessages();
		
		proxy.RegisterEvents();
	}	
	
	@EventHandler
	public void postload(FMLPostInitializationEvent  event)
	{				
		
		if (FoundThaumcraft)
		{
			Item[] newaxes = new Item[] { ThaumicSteelAxe };
			System.arraycopy(newaxes, 0, Recipes.axes, Recipes.axes.length - 1, newaxes.length);
			
			TechnofirmaCore.GetThaumcraftBlock("blockMagicalLog").setHardness(60);
		}
	}

	
	public static Block GetThaumcraftBlock(String itemString) {
		Block block = null;

		try {
			String itemClass = "thaumcraft.common.config.ConfigBlocks";
			Object obj = Class.forName(itemClass).getField(itemString).get(null);
			if (obj instanceof Block) {
				block = (Block)obj;
			}
		} catch (Exception ex) {
			FMLLog.warning("[Thaumcraft] Could not retrieve block identified by: " + itemString);
		}

		return block;
	}
}
