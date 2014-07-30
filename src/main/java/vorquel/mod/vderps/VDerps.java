package vorquel.mod.vderps;

import vorquel.mod.vderps.helper.Config;
import vorquel.mod.vderps.helper.Init;
import vorquel.mod.vderps.helper.Log;
import vorquel.mod.vderps.helper.Ref;
import vorquel.mod.vderps.proxy.IProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Ref.MOD_ID, name = Ref.MOD_NAME, version = Ref.VERSION)
public class VDerps {
	
	@Mod.Instance(Ref.MOD_ID)
	public static VDerps that;
	
	@SidedProxy(clientSide = Ref.CLIENT_PROXY_CLASS, serverSide = Ref.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Log.trace("VDerps.preInit()");
		Config.init(event.getSuggestedConfigurationFile());
		//TODO: make config GUI
		Init.items();
		Init.blocks();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		Log.trace("VDerps.init()");
		Init.recipes();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		Log.trace("VDerps.postInit()");
		Init.eventHandlers();
	}
}
