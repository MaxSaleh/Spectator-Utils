package net.arsenalnetwork.spectatorutils.utilities;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.List;

public class PlayerUtils
{

    public static boolean isOp(final EntityPlayerMP player)
    {
        return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().canSendCommands(player.getGameProfile());
    }

    public static void sendMessage(final EntityPlayer player, final String message, final boolean hotBar)
    {
        if (!player.world.isRemote)
        {
            player.sendStatusMessage(new TextComponentString(message), hotBar);
        }
    }

    public static boolean isInHand(final EnumHand hand, final EntityLivingBase holder, final Item item)
    {
        final ItemStack heldItem = holder.getHeldItem(hand);
        return heldItem.getItem() == item;
    }

    public static boolean isInMainHand(final EntityLivingBase holder, final Item item)
    {
        return isInHand(EnumHand.MAIN_HAND, holder, item);
    }

    /**
     * Checks if player has item in offhand
     */
    public static boolean isInOffHand(final EntityLivingBase holder, final Item item)
    {
        return isInHand(EnumHand.OFF_HAND, holder, item);
    }

    /**
     * Checks if player has item in either hand
     */
    public static boolean isInEitherHand(final EntityLivingBase holder, final Item item)
    {
        return isInMainHand(holder, item) || isInOffHand(holder, item);
    }

    // MAIN_HAND xor OFF_HAND
    public static boolean isInOneHand(final EntityLivingBase holder, final Item item)
    {
        final boolean mainHand = (isInMainHand(holder, item) && !isInOffHand(holder, item));
        final boolean offHand = (isInOffHand(holder, item) && !isInMainHand(holder, item));
        return mainHand || offHand;
    }

    public static Entity rayTrace(final World world, final EntityPlayer player, final Vec3d startPos, final Vec3d dirVec, final int length)
    {
        final Vec3d startVec = startPos;
        final Vec3d endVec = startVec.add(dirVec.scale(length));

        final RayTraceResult result = world.rayTraceBlocks(startVec, endVec, false, true, false);
        final Vec3d aabbVec1 = startVec.add(new Vec3d(0, -1, 0));
        final Vec3d aabbVec2 = endVec.add(new Vec3d(0, 1, 0));

        Vec3d other = result != null ? result.hitVec.add(new Vec3d(0, 1, 0)) : aabbVec2;

        final AxisAlignedBB bb = new AxisAlignedBB(aabbVec1.x, aabbVec1.y, aabbVec1.z, other.x, other.y, other.z);
        final List<Entity> entities = world.getEntitiesInAABBexcluding(player, bb, RAY_TRACE_ENTITY_PREDICATE);
        final Vec3d endVecEntityCheck = result != null ? result.hitVec : endVec;

        for (final Entity e : entities)
        {
            if (!e.noClip && (e.getEntityBoundingBox() != null))
            {
                final RayTraceResult intercept = e.getEntityBoundingBox().calculateIntercept(startVec, endVecEntityCheck);
                if (intercept != null)
                {
                    return e;
                }
            }
        }

        return null;
    }

    private static final Predicate<? super Entity> RAY_TRACE_ENTITY_PREDICATE = Predicates.and(EntitySelectors.NOT_SPECTATING,
            entity -> (entity != null) && entity.canBeCollidedWith() && (entity instanceof EntityLivingBase));

}