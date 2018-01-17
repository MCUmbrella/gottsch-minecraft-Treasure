/**
 * 
 */
package com.someguyssoftware.treasure2.client.gui.inventory;

import java.awt.Color;

import com.someguyssoftware.treasure2.Treasure;
import com.someguyssoftware.treasure2.inventory.TreasureChestContainer;
import com.someguyssoftware.treasure2.tileentity.TreasureChestTileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

/**
 * @author Mark Gottschling on Jan 16, 2018
 *
 */
public class TreasureChestGui extends GuiContainer {

	// This is the resource location for the background image for the GUI
	private static final ResourceLocation texture = new ResourceLocation(Treasure.MODID, "textures/gui/container/treasure_chest.png");
	private TreasureChestTileEntity tileEntity;

	/**
	 * NOTE can pass anything into the ChestGui (GuiContainer) as long as the player's inventory and the container's inventory is present.
	 * NOTE both can be IInventory - doesn't have to be TileEntity
	 * @param invPlayer
	 * @param tileEntity
	 */
	public TreasureChestGui(InventoryPlayer invPlayer, TreasureChestTileEntity tileEntity) {
		super(new TreasureChestContainer(invPlayer, (IInventory) tileEntity.getInventoryProxy())); // <---- this is where we can pass in a Proxy instead of TE.
		this.tileEntity = tileEntity;
		
		// Set the width and height of the gui.  Should match the size of the texture!
		xSize = 176;
		ySize = 167;
	}	
	
	/* (non-Javadoc)
	 * @see net.minecraft.client.gui.inventory.GuiContainer#drawGuiContainerBackgroundLayer(float, int, int)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		// Bind the image texture of our custom container
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		// Draw the image
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	// draw the foreground for the GUI - rendered after the slots, but before the dragged items and tooltips
	// renders relative to the top left corner of the background
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		final int LABEL_XPOS = 5;
		final int LABEL_YPOS = 5;
		fontRenderer.drawString(tileEntity.getDisplayName().getUnformattedText(), LABEL_XPOS, LABEL_YPOS, Color.darkGray.getRGB());
	}
}
