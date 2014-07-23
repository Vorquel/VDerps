package vorquel.mod.vderps.helper;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Init {
	
	public static void items() {
		Log.trace("Init.items()");
		GameRegistry.registerItem(RefObj.bootsBucket, "bootsBucket");
		GameRegistry.registerItem(RefObj.bucketWaterBottomless, "bucketWaterBottomless");
	}
	
	public static void blocks() {
		Log.trace("Init.blocks()");
		GameRegistry.registerBlock(RefObj.furnaceMulti, "furnaceMulti");
	}
	
	public static void recipes() {
		Log.trace("Init.recipes()");
		GameRegistry.addShapedRecipe(new ItemStack(RefObj.bucketWaterBottomless), "a a", " b ", 'a', Items.water_bucket, 'b', Items.bucket);
	}
}
