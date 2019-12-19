package net.arsenalnetwork.spectatorutils.client;

import net.arsenalnetwork.spectatorutils.client.gui.SpectatorGUI;
import net.arsenalnetwork.spectatorutils.common.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(new SpectatorGUI());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public void Init(FMLInitializationEvent event) {
        super.Init(event);
    }


}
