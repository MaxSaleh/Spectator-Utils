package net.arsenalnetwork.spectatorutils.utilities.event;

import net.minecraftforge.common.MinecraftForge;

public class ForgeEvent
{

    /**
     * Event registration helper class
     */
    public ForgeEvent()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

}