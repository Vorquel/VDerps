package vorquel.mod.vderps.helper;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import vorquel.mod.vderps.block.BlockVD;
import vorquel.mod.vderps.item.ItemVD;
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
	}
	
	private static void registerBlock(BlockVD block) {
		GameRegistry.registerBlock(block, block.blockName);
	}
	
	public static void recipes() {
		Log.trace("Init.recipes()");
		GameRegistry.addShapedRecipe(new ItemStack(RefObj.toolBucket, 1, 0), "a a", " b ", 'a', Items.water_bucket, 'b', Items.bucket);
	}
}
