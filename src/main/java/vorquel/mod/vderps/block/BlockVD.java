package vorquel.mod.vderps.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import vorquel.mod.vderps.helper.Ref;

public class BlockVD extends Block {
	
	public final String blockName;
	
	public BlockVD(String name, Material material) {
		super(material);
		blockName = name;
		setCreativeTab(Ref.VD_TAB());
	}
	
	public BlockVD(String name) {
		this(name, Material.rock);
	}
	
	@Override
	public String getUnlocalizedName() {
		return String.format("tile.%s", getShortName());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(getShortName());
	}
	
	public String getShortName() {
		return String.format("%s:%s", Ref.MOD_ID().toLowerCase(), blockName);
	}
}
