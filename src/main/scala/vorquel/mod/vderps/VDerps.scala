package vorquel.mod.vderps

import cpw.mods.fml.common.{Mod, SidedProxy}
import cpw.mods.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import vorquel.mod.vderps.helper.{Config, Init, Log}
import vorquel.mod.vderps.proxy.IProxy

@Mod(modid = "VDerps", name = "Vorquel's Derps", version = "indev", modLanguage = "scala")
object VDerps {

  @SidedProxy(clientSide = "vorquel.mod.vderps.proxy.ClientProxy", serverSide = "vorquel.mod.vderps.proxy.ServerProxy")
  var proxy: IProxy = null

  @Mod.EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit = {
    Log.trace("VDerps.preInit()")
    Config.init(event.getSuggestedConfigurationFile)
    Init.items()
    Init.blocks()
  }

  @Mod.EventHandler
  def init(event: FMLInitializationEvent): Unit = {
    Log.trace("VDerps.init()")
    Init.recipes()
  }

  @Mod.EventHandler
  def postInit(event: FMLPostInitializationEvent): Unit = {
    Log.trace("VDerps.postInit()")
    Init.eventHandlers()
  }
}

