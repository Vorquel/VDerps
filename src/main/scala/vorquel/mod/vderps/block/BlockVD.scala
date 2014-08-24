package vorquel.mod.vderps.block

import cpw.mods.fml.relauncher.{SideOnly, Side}
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IIconRegister
import vorquel.mod.vderps.helper.Ref

class BlockVD(name: String, material: Material) extends Block(material) {
  val blockName: String = name
  setCreativeTab(Ref.VD_TAB)

  def this(name: String) {
    this(name, Material.rock)
  }

  override def getUnlocalizedName: String = String.format("tile.%s", getShortName)

  @SideOnly(Side.CLIENT) override def registerBlockIcons(iconRegister: IIconRegister) {
    blockIcon = iconRegister.registerIcon(getShortName)
  }

  def getShortName: String = String.format("%s:%s", Ref.MOD_ID.toLowerCase, blockName)
}
