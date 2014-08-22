package vorquel.mod.vderps.helper

import net.minecraft.creativetab.CreativeTabs

object Ref {

  // Constants
  final def MOD_ID = "VDerps"
  final def MOD_NAME = "Vorquel's Derps"
  final def VERSION = "1.7.10-0.0.0"
  final def CLIENT_PROXY_CLASS = "vorquel.mod.vderps.proxy.ClientProxy"
  final def SERVER_PROXY_CLASS = "vorquel.mod.vderps.proxy.ServerProxy"

  // Creative Tab
  final val VD_TAB_ = new CreativeTabs((MOD_ID.toLowerCase)) {
    override def getTabIconItem = RefObj.bootsBucket
  }
  final def VD_TAB = VD_TAB_
}
