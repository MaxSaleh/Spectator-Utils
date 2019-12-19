package net.arsenalnetwork.spectatorutils.utilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class RenderHelperM
{

    public static void renderText(String text, int posX, int posY, int color)
    {
        Minecraft mc = Minecraft.getMinecraft();
        mc.fontRenderer.drawString(text, posX, posY, color);
    }

    public static void renderRectWithOutline(int givenPosX, int givenPosY, int givenWidth, int givenHeight, int givenColor, int givenOutlineColor, int outlineThickness)
    {
        GL11.glPushMatrix();
        renderRect(givenPosX - outlineThickness, givenPosY - outlineThickness, givenWidth + outlineThickness * 2, givenHeight + outlineThickness * 2, givenOutlineColor);
        renderRect(givenPosX, givenPosY, givenWidth, givenHeight, givenColor);
        GL11.glPopMatrix();
    }

    public static void renderRect(int givenPosX, int givenPosY, int givenWidth, int givenHeight, int givenColor)
    {
        GL11.glPushMatrix();

        givenWidth = givenPosX + givenWidth;
        givenHeight = givenPosY + givenHeight;
        if (givenPosX < givenWidth)
        {
            int i = givenPosX;
            givenPosX = givenWidth;
            givenWidth = i;
        }
        if (givenPosY < givenHeight)
        {
            int j = givenPosY;
            givenPosY = givenHeight;
            givenHeight = j;
        }
        float f = (givenColor >> 16 & 0xFF) / 255.0F;
        float f1 = (givenColor >> 8 & 0xFF) / 255.0F;
        float f2 = (givenColor & 0xFF) / 255.0F;
        float f3 = (givenColor >> 24 & 0xFF) / 255.0F;

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(f, f1, f2, f3);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferbuilder.pos(givenPosX, givenHeight, 0.0D).endVertex();
        bufferbuilder.pos(givenWidth, givenHeight, 0.0D).endVertex();
        bufferbuilder.pos(givenWidth, givenPosY, 0.0D).endVertex();
        bufferbuilder.pos(givenPosX, givenPosY, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();

        GL11.glPopMatrix();
    }

}
