package vorquel.mod.vderps.item

import java.util

import cpw.mods.fml.relauncher.{SideOnly, Side}
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util.IIcon

class ItemMultiVD(name: String, params: String*) extends ItemVD(name) {
  private val subname: Seq[String] = params
  @SideOnly(Side.CLIENT)
  protected val itemIcons: Array[IIcon] = new Array(subname.length)

  override def getUnlocalizedName(itemStack: ItemStack): String = {
    val index: Int = itemStack.getItemDamage
    var sub: String = null
    if(index < subname.length) sub = subname(index)
    s"${getUnlocalizedName()}.$sub"
  }

  @SideOnly(Side.CLIENT) override def registerIcons(iconRegister: IIconRegister) {
    for(i <- 0 until subname.length)
      itemIcons(i) = iconRegister.registerIcon(s"$getShortName.${subname(i)}")
  }

  @SideOnly(Side.CLIENT)
  override def getSubItems(item: Item, tab: CreativeTabs, list: util.List[_]) {
    val temp = list.asInstanceOf[util.ArrayList[Object]]
    for(i <- 0 until subname.length)
      temp.add(new ItemStack(this, 1, i))
  }

  @SideOnly(Side.CLIENT)
  override def getIconFromDamage(damage: Int): IIcon = itemIcons(damage)
}
