package net.arsenalnetwork.spectatorutils.client.gui;

import net.arsenalnetwork.spectatorutils.SpectatorUtils;
import net.arsenalnetwork.spectatorutils.utilities.RenderHelperM;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@Mod.EventBusSubscriber(modid = SpectatorUtils.MOD_ID)
public class SpectatorGUI {

    public static int guiWidth = 192;
    public static int guiHeight = 256;

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void drawHUD(RenderGameOverlayEvent.Post event)
    {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL)
        {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayer player = (EntityPlayer) mc.getRenderViewEntity();
            if (player.isSpectator() && !(mc.currentScreen instanceof GuiGameOver))
            {
                GL11.glPushMatrix();

                //HEALTH
                RenderHelperM.renderRectWithOutline(guiWidth - 185,  guiHeight - 250, 100, 10, 1426063360, 587202559, 1);
                RenderHelperM.renderRect(guiWidth - 185,  guiHeight - 250, Math.round(calculatePercentage(player.getHealth(), player.getMaxHealth())), 10, 1006063360);
                RenderHelperM.renderText((int) calculatePercentage(player.getHealth(), player.getMaxHealth()) + " Health",  guiWidth - 185 + 2,  guiHeight - 249, 587202559);

                //HUNGER
                RenderHelperM.renderRectWithOutline( guiWidth - 185,  guiHeight - 235, 100, 10, 1426063360, 587202559, 1);
                RenderHelperM.renderRect( guiWidth - 185,  guiHeight - 235, Math.round(calculatePercentage(player.getFoodStats().getFoodLevel(), 20.0f)), 10, 1001549360);
                RenderHelperM.renderText((int) calculatePercentage(player.getFoodStats().getFoodLevel(), 20.0f) + " Food",  guiWidth - 185 + 2,  guiHeight - 234, 587202559);

                GlStateManager.disableRescaleNormal();
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.enableBlend();

                GlStateManager.disableBlend();

                GL11.glPopMatrix();

            }
        }
    }

    /**
     * Cancels the vanilla Health/Food/Experience/Armor GUI Bars.
     */
    @SubscribeEvent
    public static void onRenderGuiPre(RenderGameOverlayEvent.Pre event)
    {
        /**
        if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH || event.getType() == RenderGameOverlayEvent.ElementType.FOOD || event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE || event.getType() == RenderGameOverlayEvent.ElementType.ARMOR)
        {
            event.setCanceled(true);
        }**/
    }

    public static float calculatePercentage(final float input, final float max)
    {
        return input * 100.0f / max;
    }

    public static float calculateper(final float input)
    {
        final int max = 20;
        return input * 100.0f / max;
    }

}
