package vorquel.mod.vderps.helper;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public class Ref {
	
	//////
	//  Constants
	//////
	public static final String MOD_ID = "VDerps";
	public static final String MOD_NAME = "Vorquel's Derps";
	public static final String VERSION = "1.7.10-0.0.0";
	public static final String CLIENT_PROXY_CLASS = "vorquel.mod.vderps.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "vorquel.mod.vderps.proxy.ServerProxy";
	
	//////
	//  Creative Tabs
	//////
	public static final CreativeTabs VD_TAB = new CreativeTabs(MOD_ID.toLowerCase()) {
		
		@Override
		public Item getTabIconItem() {
			return RefObj.bootsBucket;
		}
	};
}
