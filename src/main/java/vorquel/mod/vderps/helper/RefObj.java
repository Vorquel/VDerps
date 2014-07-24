package vorquel.mod.vderps.helper;

import vorquel.mod.vderps.block.BlockFurnaceMulti;
import vorquel.mod.vderps.block.BlockVD;
import vorquel.mod.vderps.item.ItemBootsBucket;
import vorquel.mod.vderps.item.ItemToolBucket;
import vorquel.mod.vderps.item.ItemVD;
import cpw.mods.fml.common.registry.GameRegistry;

//This class is separate from Ref for the sole reason that FML's @GameRegistry.ObjectHolder doesn't play well with others.

@GameRegistry.ObjectHolder(Ref.MOD_ID)
public class RefObj {
	
	//////
	//  Items
	//////
	public static ItemVD bootsBucket = new ItemBootsBucket();
	public static ItemVD toolBucket = new ItemToolBucket();
	
	//////
	//  Blocks
	//////
	public static BlockVD furnaceMulti = new BlockFurnaceMulti();
	
}
