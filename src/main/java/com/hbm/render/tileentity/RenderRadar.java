package com.hbm.render.tileentity;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.TileEntityMachineRadar;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class RenderRadar extends TileEntitySpecialRenderer<TileEntityMachineRadar> {

	@Override
	public boolean isGlobalRenderer(TileEntityMachineRadar te) {
		return true;
	}
	
	@Override
	public void render(TileEntityMachineRadar te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GlStateManager.enableLighting();
        GlStateManager.disableCull();
		GL11.glRotatef(180, 0F, 1F, 0F);

        bindTexture(ResourceManager.radar_body_tex);
        
        ResourceManager.radar_body.renderAll();

        GL11.glPopMatrix();
        
        renderTileEntityAt2(te, x, y, z, partialTicks);
        GlStateManager.enableCull();
	}
	
	public void renderTileEntityAt2(TileEntity tileEntity, double x, double y, double z, float f)
    {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GlStateManager.enableLighting();
        GlStateManager.disableCull();
		GL11.glRotatef(180, 0F, 1F, 0F);
		
		TileEntityMachineRadar radar = (TileEntityMachineRadar)tileEntity;
		
		if(radar.power > 0)
			GL11.glRotatef((System.currentTimeMillis() / 10) % 360, 0F, 1F, 0F);

        bindTexture(ResourceManager.radar_head_tex);
        ResourceManager.radar_head.renderAll();

        GL11.glPopMatrix();
    }
}
