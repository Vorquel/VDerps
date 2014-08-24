package vorquel.mod.vderps.item

import cpw.mods.fml.relauncher.{SideOnly, Side}
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.item.{Item, ItemStack}
import vorquel.mod.vderps.helper.Ref

class ItemVD(name: String) extends Item {
  val itemName: String = name
  setCreativeTab(Ref.VD_TAB)

  override def getUnlocalizedName: String = String.format("item.%s", getShortName)

  override def getUnlocalizedName(itemStack: ItemStack): String = getUnlocalizedName

  @SideOnly(Side.CLIENT)
  override def registerIcons(iconRegister: IIconRegister) {
    itemIcon = iconRegister.registerIcon(getShortName)
  }

  def getShortName: String = String.format("%s:%s", Ref.MOD_ID.toLowerCase, itemName)
}
