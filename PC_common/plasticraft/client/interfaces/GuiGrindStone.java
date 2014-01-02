package plasticraft.client.interfaces;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import plasticraft.PlastiCraft;
import plasticraft.lib.References;
import plasticraft.tileentities.TeGrindStone;

public class GuiGrindStone extends GuiContainer {

	private ResourceLocation texture = new ResourceLocation(References.MOD_ID.toLowerCase(), "textures/gui/grindstone.png");
	
	private TeGrindStone teGrindStone;
	private GuiButton button = new GuiButton(0, this.guiLeft + 61, this.guiTop + 37, 54, 20, "Grind");
	private int progressBar;
	private EntityPlayer player;
	
	public GuiGrindStone(InventoryPlayer player, TeGrindStone grindstone){
		super(new ContainerGrindStone(player, grindstone));
		this.teGrindStone = grindstone;
		this.player = player.player;
		xSize = 176;
		ySize = 154;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j){
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		button.xPosition = guiLeft + 49;
		button.yPosition = guiTop + 37;
		
		boolean enableButton = false;
		
		int newProgressBar = this.teGrindStone.getDamageScaled(45);

		if (newProgressBar > progressBar)
		{
			++progressBar;
		}
		else if (newProgressBar < progressBar)
		{
			--progressBar;
		}
		
		this.drawTexturedModalRect(guiLeft + 117, guiTop + 57 - progressBar, 176, 44 - progressBar, 13, progressBar);
		
		if (teGrindStone.getStackInSlot(0) != null)
		{
			if (teGrindStone.getStackInSlot(0).getItemDamage() != 0)
			{
				if (player.capabilities.isCreativeMode)
				{
					enableButton = true;
				}
				else
				{
					enableButton = teGrindStone.getStackInSlot(0).getItemDamage() > 0 && teGrindStone.getStackInSlot(1) != null;
				}
			}
		}
		
		button.enabled = enableButton;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRenderer.drawString(this.teGrindStone.getInvName(), 43, 6, 4210752);
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		this.buttonList.clear();
		this.buttonList.add(button);
	}
	
	protected void actionPerformed(GuiButton button)
	{
		if (button.id == 0)
		{
			this.teGrindStone.Activate(this.player);
		}
	}
}
