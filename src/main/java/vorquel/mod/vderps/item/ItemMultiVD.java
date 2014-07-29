package vorquel.mod.vderps.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMultiVD extends ItemVD {
	
	private String[] subname;
	@SideOnly(Side.CLIENT)
	protected IIcon[] itemIcons;
	
	public ItemMultiVD(String name, String... params) {
		super(name);
		this.subname = params;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int index = itemStack.getItemDamage();
		String sub = null;
		if(index < subname.length)
			sub = subname[index];
		return String.format("%s.%s", getUnlocalizedName(), sub);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcons = new IIcon[subname.length];
		for(int i=0; i<subname.length; ++i) {
			itemIcons[i] = iconRegister.registerIcon(String.format("%s.%s", getShortName(), subname[i]));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for(ItemStack stack : getSubItemStacks())
			list.add(stack);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int damage) {
		return itemIcons[damage];
	}
	
	public ItemStack[] getSubItemStacks(int stackSize) {
		ItemStack[] stacks = new ItemStack[subname.length];
		for(int i=0; i<subname.length; ++i)
			stacks[i] = new ItemStack(this, stackSize, i);
		return stacks;
	}
	
	public ItemStack[] getSubItemStacks() {
		return getSubItemStacks(1);
	}
}
