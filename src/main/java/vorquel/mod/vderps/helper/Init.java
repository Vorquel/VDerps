package vorquel.mod.vderps.helper;

import cpw.mods.fml.common.registry.GameRegistry;

public class Init {
	
	public static void items() {
		Log.trace("Init.items()");
		GameRegistry.registerItem(RefObj.bootsBucket, "bootsBucket");
	}
	
	public static void blocks() {
		Log.trace("Init.blocks()");
		GameRegistry.registerBlock(RefObj.furnaceMulti, "furnaceMulti");
	}
	
	public static void recipes() {
		Log.trace("Init.recipes()");
		
	}
}
