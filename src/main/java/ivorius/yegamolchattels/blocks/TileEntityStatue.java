/***************************************************************************************************
 * Copyright (c) 2014, Lukas Tenbrink.
 * http://lukas.axxim.net
 **************************************************************************************************/

package ivorius.yegamolchattels.blocks;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import ivorius.ivtoolkit.blocks.IvTileEntityMultiBlock;
import ivorius.ivtoolkit.network.IvNetworkHelperServer;
import ivorius.ivtoolkit.network.PartialUpdateHandler;
import ivorius.ivtoolkit.tools.IvDateHelper;
import ivorius.yegamolchattels.YGCConfig;
import ivorius.yegamolchattels.YeGamolChattels;
import ivorius.yegamolchattels.achievements.YGCAchievementList;
import ivorius.yegamolchattels.client.rendering.TextureAllocationHandler;
import ivorius.yegamolchattels.entities.EntityFakePlayer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TileEntityStatue extends IvTileEntityMultiBlock implements PartialUpdateHandler
{
    private NBTTagCompound storedStatueTag;
    private Statue statue;

    public Statue getStatue()
    {
        return statue;
    }

    public void setStatue(Statue statue)
    {
        this.statue = statue;
    }

    public void statueDataChanged()
    {
        IvNetworkHelperServer.sendTileEntityUpdatePacket(this, "statueData", YeGamolChattels.network);
        markDirty();
    }

    public void setStatueRotationYaw(float rotation)
    {
        Entity statueEntity = statue.getEntity();
        statueEntity.rotationYaw = rotation;
        statueEntity.prevRotationYaw = rotation;

        if (statueEntity instanceof EntityLivingBase)
            ((EntityLivingBase) statueEntity).renderYawOffset = rotation;
    }

    @Override
    public void setWorldObj(World world)
    {
        super.setWorldObj(world);

        if (storedStatueTag != null)
        {
            createStatueFromNBT(storedStatueTag);
            storedStatueTag = null;
        }
    }

    @Override
    public boolean canUpdate()
    {
        return false;
    }

    public boolean letStatueComeAlive()
    {
        Entity statueEntity = statue.getEntity();
        if (statueEntity != null && YGCConfig.areLifeStatuesAllowed && !(statueEntity instanceof EntityFakePlayer))
        {
            if (YGCConfig.mayEntityStatueComeAlive(statueEntity))
            {
                if (!worldObj.isRemote)
                {
                    double[] center = getActiveCenterCoords();
                    statueEntity.setPosition(center[0], center[1] - centerCoordsSize[1], center[2]);
                    statue.updateEntityRotations();

                    // Rotation doesn't get transmitted ;_;
//                    if (statueEntity instanceof EntityLivingBase)
//                    {
//                        ((EntityLivingBase) statueEntity).rotationYawHead = statueEntity.rotationYaw;
//                        ((EntityLivingBase) statueEntity).prevRotationYawHead = statueEntity.rotationYaw;
//                        ((EntityLivingBase) statueEntity).renderYawOffset = statueEntity.rotationYaw;
//                        ((EntityLivingBase) statueEntity).prevRenderYawOffset = statueEntity.rotationYaw;
//                    }

                    Statue.BlockFragment statueBlock = statue.getMaterial();
                    int indefiniteTime = 9999999;
                    if (statueBlock.getBlock().getMaterial() == Material.rock)
                    {
                        ((EntityLiving) statueEntity).addPotionEffect(new PotionEffect(Potion.resistance.id, indefiniteTime, 0, true));
                        ((EntityLiving) statueEntity).addPotionEffect(new PotionEffect(Potion.fireResistance.id, indefiniteTime, 0, true));
                    }
                    else if (statueBlock.getBlock().getMaterial() == Material.iron)
                    {
                        ((EntityLiving) statueEntity).addPotionEffect(new PotionEffect(Potion.fireResistance.id, indefiniteTime, 0, true));
                    }

                    if ("Zombie".equals(EntityList.getEntityString(statueEntity)))
                    {
                        EntityPlayer player = worldObj.getClosestPlayer(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, 10.0);
                        if (player != null)
                            player.triggerAchievement(YGCAchievementList.zombieStatueReanimated);
                    }

                    worldObj.spawnEntityInWorld(statueEntity);
                }

                return true;
            }
        }

        return false;
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);

        writeStatueDataToNBT(par1NBTTagCompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);

        readStatueDataFromNBT(par1NBTTagCompound);
    }

    public void writeStatueDataToNBT(NBTTagCompound compound)
    {
        if (statue != null)
        {
            compound.setTag("statue", statue.createTagCompound());
            compound.setFloat("statueRotationYaw", statue.getEntity().rotationYaw); // Entity doesn't save this
        }
    }

    public void readStatueDataFromNBT(NBTTagCompound compound)
    {
        if (worldObj != null)
        {
            createStatueFromNBT(compound);
        }
        else
        {
            storedStatueTag = compound;
        }
    }

    private void createStatueFromNBT(NBTTagCompound compound)
    {
        if (compound.hasKey("statue"))
            statue = new Statue(compound.getCompoundTag("statue"), worldObj);
        else if (isParent())
            statue = new Statue(new EntityPig(worldObj), new Statue.BlockFragment(Blocks.stone, 0), 0.0f, 0.0f, 0.0f, 2.4f);
        else
            statue = null;

        if (statue != null)
        {
            setStatueRotationYaw(compound.getFloat("statueRotationYaw"));
        }
    }

    public boolean isEntityEquippable()
    {
        return statue != null && YGCConfig.isEntityEquippable(statue.getEntity());
    }

    public boolean tryEquipping(ItemStack item)
    {
        if (item != null && statue != null)
        {
            if (isEntityEquippable())
            {
                Entity statueEntity = statue.getEntity();
                int slot = EntityLiving.getArmorPosition(item);

                if (slot >= 0 && statueEntity.getLastActiveItems()[slot] == null)
                {
                    if (!worldObj.isRemote)
                    {
                        statueEntity.setCurrentItemOrArmor(slot, item.copy());
                        item.stackSize = 0;

                        statueDataChanged();
                    }

                    return true;
                }
            }

//            if (statueEntity instanceof EntityHorse)
//            {
//                EntityHorse horse = (EntityHorse) statueEntity;
//
//                if (item.getItem() == Items.saddle)
//                {
//                    horse.horseChest
//                }
//                else if (horse.func_110259_cr() /* canHaveArmor */ && EntityHorse.func_146085_a(item.getItem()) /* isArmor */)
//                {
//
//                }
//            }
        }

        return false;
    }

    public void addEquipmentToInventory(EntityPlayer player)
    {
        if (statue != null)
        {
            Entity statueEntity = statue.getEntity();
            for (int i = 0; i < statueEntity.getLastActiveItems().length; i++)
            {
                if (statueEntity.getLastActiveItems()[i] != null)
                {
                    if (player.inventory.addItemStackToInventory(statueEntity.getLastActiveItems()[i]))
                        statueEntity.getLastActiveItems()[i] = null;
                }
            }

            if (!worldObj.isRemote)
            {
                statueDataChanged();
            }
        }
    }

    public void dropEquipment()
    {
        if (!worldObj.isRemote && statue != null)
        {
            Entity statueEntity = statue.getEntity();

            for (int i = 0; i < statueEntity.getLastActiveItems().length; i++)
            {
                if (statueEntity.getLastActiveItems()[i] != null)
                {
                    float var7 = 0.7F;
                    double var8 = worldObj.rand.nextFloat() * var7 + (1.0F - var7) * 0.5D;
                    double var10 = worldObj.rand.nextFloat() * var7 + (1.0F - var7) * 0.2D + 0.6D;
                    double var12 = worldObj.rand.nextFloat() * var7 + (1.0F - var7) * 0.5D;
                    EntityItem var14 = new EntityItem(worldObj, xCoord + var8, yCoord + var10, zCoord + var12, statueEntity.getLastActiveItems()[i]);
                    var14.delayBeforeCanPickup = 10;
                    worldObj.spawnEntityInWorld(var14);

                    statueEntity.getLastActiveItems()[i] = null;
                }
            }

            statueDataChanged();
        }
    }

    @Override
    public double getMaxRenderDistanceSquared()
    {
        if (statue != null)
        {
            Entity statueEntity = statue.getEntity();
            double var3 = statueEntity.boundingBox.getAverageEdgeLength();
            var3 *= 64.0D * statueEntity.renderDistanceWeight;

            return var3 * var3;
        }

        return 0.0;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        if (statue != null)
        {
            Entity statueEntity = statue.getEntity();
            if (statueEntity.ignoreFrustumCheck)
                return INFINITE_EXTENT_AABB;

            return getBoxAroundCenter(statueEntity.width / 2, statueEntity.height / 2, statueEntity.width / 2);
        }

        return super.getRenderBoundingBox();
    }

    @Override
    public void writeUpdateData(ByteBuf buffer, String context, Object... params)
    {
        if ("statueData".equals(context))
        {
            NBTTagCompound compound = new NBTTagCompound();
            writeStatueDataToNBT(compound);
            ByteBufUtils.writeTag(buffer, compound);
        }
    }

    @Override
    public void readUpdateData(ByteBuf buffer, String context)
    {
        if ("statueData".equals(context))
        {
            readStatueDataFromNBT(ByteBufUtils.readTag(buffer));
        }
    }

    @Override
    public void invalidate()
    {
        super.invalidate();

        if (worldObj.isRemote)
        {
            releaseTexture();
        }
    }

    // Client

    @SideOnly(Side.CLIENT)
    private ResourceLocation statueTexture;

    @SideOnly(Side.CLIENT)
    private ResourceLocation usedEntityTexture;

    @SideOnly(Side.CLIENT)
    public void releaseTexture()
    {
        if (statueTexture != null)
        {
            TextureAllocationHandler.releaseTexture(statueTexture);
            statueTexture = null;
        }
    }

    @SideOnly(Side.CLIENT)
    public ResourceLocation getStatueTexture()
    {
        return statueTexture;
    }

    @SideOnly(Side.CLIENT)
    public void setStatueTexture(ResourceLocation statueTexture)
    {
        this.statueTexture = statueTexture;
    }

    public ResourceLocation getUsedEntityTexture()
    {
        return usedEntityTexture;
    }

    public void setUsedEntityTexture(ResourceLocation usedEntityTexture)
    {
        this.usedEntityTexture = usedEntityTexture;
    }

    @Override
    public boolean shouldRenderInPass(int pass)
    {
        if (statue != null)
            return statue.getMaterial().getBlock().canRenderInPass(pass);
        return false;
    }
}
