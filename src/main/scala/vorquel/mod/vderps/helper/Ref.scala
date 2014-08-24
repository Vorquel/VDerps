package vorquel.mod.vderps.helper

import net.minecraft.creativetab.CreativeTabs
import vorquel.mod.vderps.item.ItemBootsBucket

object Ref {

  // Constants
  val MOD_ID = "VDerps"
  val MOD_NAME = "Vorquel's Derps"
  val VERSION = "1.7.10-0.0.0"
  val CLIENT_PROXY_CLASS = "vorquel.mod.vderps.proxy.ClientProxy"
  val SERVER_PROXY_CLASS = "vorquel.mod.vderps.proxy.ServerProxy"

  // Creative Tab
  val VD_TAB = new CreativeTabs((MOD_ID.toLowerCase)) {
    override def getTabIconItem = ItemBootsBucket
  }
}
