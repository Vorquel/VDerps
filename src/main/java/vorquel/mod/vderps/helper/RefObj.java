package vorquel.mod.vderps.helper;

import vorquel.mod.vderps.block.BlockFurnaceMulti;
import vorquel.mod.vderps.block.BlockVD;
import vorquel.mod.vderps.item.ItemBootsBucket;
import vorquel.mod.vderps.item.ItemVD;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Ref.MOD_ID)
public class RefObj {
	
	//////
	//  Items
	//////
	public static ItemVD bootsBucket = new ItemBootsBucket();
	
	//////
	//  Blocks
	//////
	public static BlockVD furnaceMulti = new BlockFurnaceMulti();
	
}
