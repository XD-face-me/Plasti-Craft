package plasticraft.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import plasticraft.items.Items;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;

public class Knife extends Item{

	public Knife(int par1) {
		super(par1);
		setCreativeTab(PlastiCraft.tabsPC);
		setTextureName(References.MOD_ID.toLowerCase() + ":knife");
		setMaxStackSize(1);
		setNoRepair();
		setMaxDamage(64);
		isDamageable();
	}
	
	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack)
	{
		return false;
	}
	
	@Override
	public boolean hasContainerItem()
	{
		return true;
	}
	
	@Override
	public ItemStack getContainerItemStack(ItemStack itemStack)
    {
		itemStack.setItemDamage(itemStack.getItemDamage() + 1);
        return itemStack;
    }
}
