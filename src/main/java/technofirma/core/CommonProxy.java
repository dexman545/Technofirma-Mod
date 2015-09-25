package technofirma.core;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import com.bioxx.tfc.Items.ItemMeltedMetal;
import com.bioxx.tfc.api.Metal;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Crafting.AnvilRecipe;
import com.bioxx.tfc.api.Crafting.AnvilReq;
import com.bioxx.tfc.api.Crafting.PlanRecipe;
import com.bioxx.tfc.api.Enums.RuleEnum;

import technofirma.blocks.BlockTFOilLamp;
import technofirma.crafting.MagicAnvilManager;
import technofirma.crafting.MagicAnvilRecipe;
import technofirma.crafting.MagicPlanRecipe;
import technofirma.events.ThaumcraftTreeFell;
import technofirma.foci.ItemFocusProspecting;
import technofirma.handlers.GuiHandler;
import technofirma.items.ItemTFOilLamp;
import technofirma.items.ItemTechnofirmaAxe;
import technofirma.items.ItemTechnofirmaHoe;
import technofirma.items.ItemTechnofirmaIngot;
import technofirma.items.ItemTechnofirmaPickaxe;
import technofirma.items.ItemTechnofirmaShovel;
import technofirma.items.ItemTechnofirmaSword;
import technofirma.items.ItemTechnofirmaToolHead;
import technofirma.lamp.LampFluid;
import technofirma.tileentities.TEMagicAnvil;
import technofirma.tileentities.TETFIngotPile;
import technofirma.tileentities.TETFOilLamp;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy 
{
	public void RegisterRenderers() { }
	
	public void RegisterGuiHandler() 
	{ 
		NetworkRegistry.INSTANCE.registerGuiHandler(TechnofirmaCore.instance, new GuiHandler());
	}
	
	public void SetupLampFuels()
	{		
		LampFluid.CreateFluid("oliveoil", 1);
		LampFluid.CreateFluid("oil", 2);
		LampFluid.CreateFluid("fuel", 3);
	}
		
	public void SetupBlocks()
	{
		TechnofirmaCore.OilLamp = new BlockTFOilLamp().setHardness(1F).setBlockName("TFOilLamp");
	}
	
	public void RegisterBlocks()
	{
		GameRegistry.registerBlock(TechnofirmaCore.OilLamp, ItemTFOilLamp.class, "TFOilLamp");
	}
	
	public void RegisterTE()
	{
		GameRegistry.registerTileEntity(TETFOilLamp.class, "TF Oil Lamp");
	}
	
	public void SetupMagicItems()
	{
		// Setup magic ingots
		TechnofirmaCore.ThaumicSteelIngot = new ItemTechnofirmaIngot().SetIconTexture("thaumcraft:thaumiumingot").setUnlocalizedName("Thaumic Steel Ingot");
		
		// Setup Magic Melted Metal
		TechnofirmaCore.ThaumicSteelUnshaped = new ItemMeltedMetal().setUnlocalizedName("Thaumic Steel Unshaped");
		
		// Setup Magic Metal
		TechnofirmaCore.ThaumicSteel = new Metal("Thaumic Steel", TechnofirmaCore.ThaumicSteelUnshaped, TechnofirmaCore.ThaumicSteelIngot);
	
		// Setup magic tools
		TechnofirmaCore.ThaumicSteelAxe = new ItemTechnofirmaAxe(TechnofirmaCore.ThaumicSteelMaterial, 250).SetIconTexture("thaumcraft:thaumiumaxe").setUnlocalizedName("Thaumic Steel Axe").setMaxDamage(TFCItems.steelUses + 250);
		TechnofirmaCore.ThaumicSteelShovel = new ItemTechnofirmaShovel(TechnofirmaCore.ThaumicSteelMaterial).SetIconTexture("thaumcraft:thaumiumshovel").setUnlocalizedName("Thaumic Steel Shovel").setMaxDamage(TFCItems.steelUses + 250);
		TechnofirmaCore.ThaumicSteelPickaxe = new ItemTechnofirmaPickaxe(TechnofirmaCore.ThaumicSteelMaterial).SetIconTexture("thaumcraft:thaumiumpick").setUnlocalizedName("Thaumic Steel Pickaxe").setMaxDamage(TFCItems.steelUses + 250);
		TechnofirmaCore.ThaumicSteelHoe = new ItemTechnofirmaHoe(TechnofirmaCore.ThaumicSteelMaterial).SetIconTexture("thaumcraft:thaumiumhoe").setUnlocalizedName("Thaumic Steel Hoe").setMaxDamage(TFCItems.steelUses + 250);
		TechnofirmaCore.ThaumicSteelSword = new ItemTechnofirmaSword(TechnofirmaCore.ThaumicSteelMaterial, 300).SetIconTexture("thaumcraft:thaumiumsword").setUnlocalizedName("Thaumic Steel Sword").setMaxDamage(TFCItems.steelUses + 250);
		
		// Setup magic tool heads
		TechnofirmaCore.ThaumicSteelAxeHead = new ItemTechnofirmaToolHead().SetIconTexture("technofirma:Thaumic Steel Axe Head").setUnlocalizedName("Thaumic Steel Axe Head");
		TechnofirmaCore.ThaumicSteelShovelHead = new ItemTechnofirmaToolHead().SetIconTexture("technofirma:Thaumic Steel Shovel Head").setUnlocalizedName("Thaumic Steel Shovel Head");
		TechnofirmaCore.ThaumicSteelPickaxeHead = new ItemTechnofirmaToolHead().SetIconTexture("technofirma:Thaumic Steel Pickaxe Head").setUnlocalizedName("Thaumic Steel Pickaxe Head");
		TechnofirmaCore.ThaumicSteelHoeHead = new ItemTechnofirmaToolHead().SetIconTexture("technofirma:Thaumic Steel Hoe Head").setUnlocalizedName("Thaumic Steel Hoe Head");
		TechnofirmaCore.ThaumicSteelSwordHead = new ItemTechnofirmaToolHead().SetIconTexture("technofirma:Thaumic Steel Sword Head").setUnlocalizedName("Thaumic Steel Sword Head");
	
		// Create our items
		TechnofirmaCore.ProspectingFocus = new ItemFocusProspecting().setUnlocalizedName("prospecting_focus");
	}
	
	public void RegisterRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(TechnofirmaCore.ThaumicSteelAxe, 1), new Object[] { "#", "%", Character.valueOf('#'), new ItemStack(TechnofirmaCore.ThaumicSteelAxeHead, 1), Character.valueOf('%'), new ItemStack(TFCItems.stick, 1)});
		GameRegistry.addRecipe(new ItemStack(TechnofirmaCore.ThaumicSteelShovel, 1), new Object[] { "#", "%", Character.valueOf('#'), new ItemStack(TechnofirmaCore.ThaumicSteelShovelHead, 1), Character.valueOf('%'), new ItemStack(TFCItems.stick, 1)});
		GameRegistry.addRecipe(new ItemStack(TechnofirmaCore.ThaumicSteelPickaxe, 1), new Object[] { "#", "%", Character.valueOf('#'), new ItemStack(TechnofirmaCore.ThaumicSteelPickaxeHead, 1), Character.valueOf('%'), new ItemStack(TFCItems.stick, 1)});
		GameRegistry.addRecipe(new ItemStack(TechnofirmaCore.ThaumicSteelHoe, 1), new Object[] { "#", "%", Character.valueOf('#'), new ItemStack(TechnofirmaCore.ThaumicSteelHoeHead, 1), Character.valueOf('%'), new ItemStack(TFCItems.stick, 1)});
		GameRegistry.addRecipe(new ItemStack(TechnofirmaCore.ThaumicSteelSword, 1), new Object[] { "#", "%", Character.valueOf('#'), new ItemStack(TechnofirmaCore.ThaumicSteelSwordHead, 1), Character.valueOf('%'), new ItemStack(TFCItems.stick, 1)});
	
		GameRegistry.addShapelessRecipe(new ItemStack(TechnofirmaCore.ThaumicSteelIngot, 1), new Object[] { ItemApi.getItem("itemResource", 2), new ItemStack(TFCItems.steelIngot, 1) });

		GameRegistry.addRecipe(new ItemStack(TechnofirmaCore.ThaumicAnvil, 1), new Object[] { "   ", "   ", "###", Character.valueOf('#'), ItemApi.getBlock("blockCosmeticSolid", 4)});
	}
	
	public void RegisterEvents() 
	{
		MinecraftForge.EVENT_BUS.register(new ThaumcraftTreeFell());
	}
	
	public void RegisterMagicTileEntities()
	{
		GameRegistry.registerTileEntity(TEMagicAnvil.class, "MagicAnvil");
		GameRegistry.registerTileEntity(TETFIngotPile.class, "IngotPile");
	}
	
	public void RegisterWailaMessages()
	{
		// For our dark ore to show up properly in waila
		if (TechnofirmaCore.FoundThaumcraft)
		{
			FMLInterModComms.sendMessage("Waila", "register", "technofirma.WAILA.WDarkOre.callbackRegister");
			FMLInterModComms.sendMessage("Waila", "register", "technofirma.WAILA.WTFIngotPile.callbackRegister");
		}
	}
	
	public void RegisterMagicItems()
	{
		// Register magic tools
		GameRegistry.registerItem(TechnofirmaCore.ThaumicSteelAxe, "ThaumicSteelAxe");
		GameRegistry.registerItem(TechnofirmaCore.ThaumicSteelShovel, "ThaumicSteelShovel");
		GameRegistry.registerItem(TechnofirmaCore.ThaumicSteelPickaxe, "ThaumicSteelPickage");
		GameRegistry.registerItem(TechnofirmaCore.ThaumicSteelHoe, "ThaumicSteelHoe");
		GameRegistry.registerItem(TechnofirmaCore.ThaumicSteelSword, "ThaumicSteelSword");
		
		// Register magic tool heads
		GameRegistry.registerItem(TechnofirmaCore.ThaumicSteelAxeHead, "ThaumicSteelAxeHead");
		GameRegistry.registerItem(TechnofirmaCore.ThaumicSteelShovelHead, "ThaumicSteelShovelHead");
		GameRegistry.registerItem(TechnofirmaCore.ThaumicSteelPickaxeHead, "ThaumicSteelPickaxeHead");
		GameRegistry.registerItem(TechnofirmaCore.ThaumicSteelHoeHead, "ThaumicSteelHoeHead");
		GameRegistry.registerItem(TechnofirmaCore.ThaumicSteelSwordHead, "ThaumicSteelSwordHead");
		
		// Register magic ingots
		GameRegistry.registerItem(TechnofirmaCore.ThaumicSteelIngot, "ThaumicSteelIngot");
		
		// Register magic unshaped metals
		GameRegistry.registerItem(TechnofirmaCore.ThaumicSteelUnshaped, "ThaumicSteelUnshaped");
		
		// Register our magic foci
		GameRegistry.registerItem(TechnofirmaCore.ProspectingFocus, "Prospecting Focus");
	}
	
	public void RegisterMagicToolsClasses()
	{
		TechnofirmaCore.ThaumicSteelAxe.setHarvestLevel("axe", 3);
		TechnofirmaCore.ThaumicSteelShovel.setHarvestLevel("shovel", 3);
		TechnofirmaCore.ThaumicSteelPickaxe.setHarvestLevel("pickaxe", 3);
	}
	
	
	public void RegisterAnvilPlans()
	{
		// Get the anvil manager
		AnvilManager anvilmgr = AnvilManager.getInstance();
		
		// Prospecting focus
		anvilmgr.addPlan("pro_focus", new PlanRecipe(new RuleEnum[]{RuleEnum.PUNCHLAST, RuleEnum.DRAWNOTLAST, RuleEnum.BENDNOTLAST}));
		
		// Iron Wand cap
		anvilmgr.addPlan("iron_wand_cap", new PlanRecipe(new RuleEnum[]{RuleEnum.PUNCHLAST, RuleEnum.PUNCHSECONDFROMLAST, RuleEnum.BENDTHIRDFROMLAST}));
	}
	
	public void RegisterAnvilRecipes()
	{
		// Get the anvil manager
		AnvilManager anvilmgr = AnvilManager.getInstance();
		
		// Prospecting Focus
		anvilmgr.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelIngot2x), null, "pro_focus", 70, false, AnvilReq.STEEL.ordinal(), new ItemStack(TechnofirmaCore.ProspectingFocus, 1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		
		// Iron Wand Cap
		anvilmgr.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), null, "iron_wand_cap", 70, false, AnvilReq.WROUGHTIRON.ordinal(), ItemApi.getItem("itemWandCap", 0)).addRecipeSkill(Global.SKILL_TOOLSMITH));
	}
		
	public void RegisterMagicAnvilPlans()
	{
		// Get the anvil manager
		MagicAnvilManager anvilmgr = MagicAnvilManager.getInstance();
		
		// Register our plans
		AspectList alist = new AspectList();
		alist.add(Aspect.ORDER, 2);
		alist.add(Aspect.FIRE, 2);
		alist.add(Aspect.AIR, 2);

		anvilmgr.addPlan("copper_wand_cap", new MagicPlanRecipe("CAP_copper", alist, new RuleEnum[]{RuleEnum.PUNCHLAST, RuleEnum.PUNCHSECONDFROMLAST, RuleEnum.BENDTHIRDFROMLAST}));
		
		alist = new AspectList();
		alist.add(Aspect.ORDER, 4);
		alist.add(Aspect.FIRE, 4);
		alist.add(Aspect.AIR, 4);
		
		anvilmgr.addPlan("silver_wand_cap", new MagicPlanRecipe("CAP_silver", alist, new RuleEnum[]{RuleEnum.PUNCHLAST, RuleEnum.PUNCHSECONDFROMLAST, RuleEnum.BENDTHIRDFROMLAST}));
		
		alist = new AspectList();
		alist.add(Aspect.ORDER, 3);
		alist.add(Aspect.FIRE, 3);
		alist.add(Aspect.AIR, 3);
		
		anvilmgr.addPlan("gold_wand_cap", new MagicPlanRecipe("CAP_gold", alist, new RuleEnum[]{RuleEnum.PUNCHLAST, RuleEnum.PUNCHSECONDFROMLAST, RuleEnum.BENDTHIRDFROMLAST}));
		
		alist = new AspectList();	
		anvilmgr.addPlan("thaumiumaxe", new MagicPlanRecipe("aspect.", alist, new RuleEnum[]{RuleEnum.PUNCHLAST, RuleEnum.HITSECONDFROMLAST, RuleEnum.UPSETTHIRDFROMLAST}));
		anvilmgr.addPlan("thaumiumshovel", new MagicPlanRecipe("aspect.", alist, new RuleEnum[]{RuleEnum.PUNCHLAST, RuleEnum.HITNOTLAST, RuleEnum.ANY}));
		anvilmgr.addPlan("thaumiumpickaxe", new MagicPlanRecipe("aspect.", alist, new RuleEnum[]{RuleEnum.PUNCHLAST, RuleEnum.BENDNOTLAST, RuleEnum.DRAWNOTLAST}));
		anvilmgr.addPlan("thaumiumhoe", new MagicPlanRecipe("aspect.", alist, new RuleEnum[]{RuleEnum.PUNCHLAST, RuleEnum.HITNOTLAST, RuleEnum.BENDNOTLAST}));
		anvilmgr.addPlan("thaumiumsword", new MagicPlanRecipe("aspect.", alist, new RuleEnum[]{RuleEnum.HITLAST, RuleEnum.BENDSECONDFROMLAST, RuleEnum.BENDTHIRDFROMLAST}));
	}
	
	public void RegisterMagicAnvilRecipes()
	{
		// Get the anvil manager
		MagicAnvilManager anvilmgr = MagicAnvilManager.getInstance();
						
		// Register our anvil recipes
		anvilmgr.addRecipe(new MagicAnvilRecipe(new ItemStack(TFCItems.copperIngot), null, "copper_wand_cap", ItemApi.getItem("itemWandCap", 3)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilmgr.addRecipe(new MagicAnvilRecipe(new ItemStack(TFCItems.silverIngot), null, "silver_wand_cap", ItemApi.getItem("itemWandCap", 5)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilmgr.addRecipe(new MagicAnvilRecipe(new ItemStack(TFCItems.goldIngot), null, "gold_wand_cap", ItemApi.getItem("itemWandCap", 1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
	
		anvilmgr.addRecipe(new MagicAnvilRecipe(new ItemStack(TechnofirmaCore.ThaumicSteelIngot), null, "thaumiumaxe", new ItemStack(TechnofirmaCore.ThaumicSteelAxeHead, 1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilmgr.addRecipe(new MagicAnvilRecipe(new ItemStack(TechnofirmaCore.ThaumicSteelIngot), null, "thaumiumshovel", new ItemStack(TechnofirmaCore.ThaumicSteelShovelHead, 1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilmgr.addRecipe(new MagicAnvilRecipe(new ItemStack(TechnofirmaCore.ThaumicSteelIngot), null, "thaumiumpickaxe", new ItemStack(TechnofirmaCore.ThaumicSteelPickaxeHead, 1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilmgr.addRecipe(new MagicAnvilRecipe(new ItemStack(TechnofirmaCore.ThaumicSteelIngot), null, "thaumiumhoe", new ItemStack(TechnofirmaCore.ThaumicSteelHoeHead, 1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilmgr.addRecipe(new MagicAnvilRecipe(new ItemStack(TechnofirmaCore.ThaumicSteelIngot), null, "thaumiumsword", new ItemStack(TechnofirmaCore.ThaumicSteelSwordHead, 1)).addRecipeSkill(Global.SKILL_WEAPONSMITH));
	}
}
