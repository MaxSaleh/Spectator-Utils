package net.arsenalnetwork.spectatorutils;

import net.arsenalnetwork.spectatorutils.common.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = SpectatorUtils.MOD_ID, name = SpectatorUtils.MOD_NAME, version = SpectatorUtils.VERSION)
public class SpectatorUtils
{

    public static final String MOD_ID = "spectatorutils";
    public static final String MOD_NAME = "Spectator Utils";
    public static final String VERSION = "2019.3-1.3.2";

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static SpectatorUtils INSTANCE;

    /**
     * Proxies
     */
    @SidedProxy(clientSide = "net.arsenalnetwork.spectatorutils.client.ClientProxy", serverSide = "net.arsenalnetwork.spectatorutils.common.CommonProxy")
    public static CommonProxy PROXY;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
        PROXY.preInit(event);
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        PROXY.Init(event);
    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {
        PROXY.postInit(event);
    }

}
