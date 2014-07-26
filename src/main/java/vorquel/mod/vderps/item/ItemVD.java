package vorquel.mod.vderps.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vorquel.mod.vderps.helper.Ref;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemVD extends Item {
	
	public final String itemName;
	
	public ItemVD(String name) {
		super();
		itemName = name;
		setCreativeTab(Ref.VD_TAB);
	}
	
	@Override
	public String getUnlocalizedName() {
		return String.format("item.%s", getShortName());
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return getUnlocalizedName();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(getShortName());
	}
	
	public String getShortName() {
		return String.format("%s:%s", Ref.MOD_ID.toLowerCase(), itemName);
	}
}
