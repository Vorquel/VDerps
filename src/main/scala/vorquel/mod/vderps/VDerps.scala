package vorquel.mod.vderps

import cpw.mods.fml.common.{Mod, SidedProxy}
import cpw.mods.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import vorquel.mod.vderps.helper.{Config, Init, Log, Ref}
import vorquel.mod.vderps.proxy.IProxy

@Mod(modid = Ref.MOD_ID, name = Ref.MOD_NAME, version = Ref.VERSION, modLanguage = "scala")
object VDerps {

  @SidedProxy(clientSide = Ref.CLIENT_PROXY_CLASS, serverSide = Ref.SERVER_PROXY_CLASS)
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

