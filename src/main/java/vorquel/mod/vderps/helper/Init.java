package vorquel.mod.vderps.helper;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeInternalHandler;
import net.minecraftforge.common.MinecraftForge;
import vorquel.mod.vderps.block.BlockVD;
import vorquel.mod.vderps.event.CraftingFixer;
import vorquel.mod.vderps.event.LandingPadHandler;
import vorquel.mod.vderps.item.ItemVD;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class Init {
	
	public static void items() {
		Log.trace("Init.items()");
		registerItem(RefObj.bootsBucket);
		registerItem(RefObj.toolBucket);
	}
	
	private static void registerItem(ItemVD item) {
		GameRegistry.registerItem(item, item.itemName);
	}
	
	public static void blocks() {
		Log.trace("Init.blocks()");
		registerBlock(RefObj.furnaceMulti);
		registerBlock(RefObj.landingPad);
	}
	
	private static void registerBlock(BlockVD block) {
		GameRegistry.registerBlock(block, block.blockName);
	}
	
	public static void recipes() {
		Log.trace("Init.recipes()");
		GameRegistry.addShapedRecipe(new ItemStack(RefObj.bootsBucket), "a", "b", "c", 'a', Items.leather_boots, 'b', Items.golden_boots, 'c', Items.bucket);
		GameRegistry.addShapedRecipe(new ItemStack(RefObj.toolBucket, 1, 0), "a", "b", 'a', Blocks.dirt, 'b', Items.bucket);
		GameRegistry.addShapedRecipe(new ItemStack(RefObj.toolBucket, 1, 0), "a", "b", 'a', Blocks.sand, 'b', Items.bucket);
		GameRegistry.addShapedRecipe(new ItemStack(RefObj.toolBucket, 1, 1), "a a", " b ", 'a', Items.water_bucket, 'b', Items.bucket);
		GameRegistry.addShapedRecipe(new ItemStack(RefObj.toolBucket, 1, 2), "a b", " c ", 'a', Items.water_bucket, 'b', Items.lava_bucket, 'c', Blocks.cobblestone);
		GameRegistry.addShapedRecipe(new ItemStack(RefObj.toolBucket, 1, 3), "a b", " c ", 'a', new ItemStack(RefObj.toolBucket, 1, 1), 'b', Items.lava_bucket, 'c', Blocks.stone);
	}
	
	public static void eventHandlers() {
		Log.trace("Init.eventHandlers()");
		FMLCommonHandler.instance().bus().register(CraftingFixer.that);
		MinecraftForge.EVENT_BUS.register(new LandingPadHandler());
	}
}
