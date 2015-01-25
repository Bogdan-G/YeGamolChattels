/*
 *  Copyright (c) 2014, Lukas Tenbrink.
 *  * http://lukas.axxim.net
 */

package ivorius.yegamolchattels.blocks;

import ivorius.ivtoolkit.blocks.IvMultiBlockHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockLootChest extends Block
{
    public BlockLootChest()
    {
        super(Material.wood);

        setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.8f, 1.0f);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
        TileEntityLootChest tileEntityLootChest = (TileEntityLootChest) world.getTileEntity(x, y, z);
        tileEntityLootChest.dropAllItems();

        super.breakBlock(world, x, y, z, block, metadata);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        TileEntityLootChest lootChest = (TileEntityLootChest) world.getTileEntity(x, y, z);

        if (!world.isRemote)
        {
            if (!lootChest.opened)
            {
                lootChest.open();
            }
            else if (lootChest.itemAccessible())
            {
                if (lootChest.firstItem() == null)
                {
                    ItemStack currentItem = player.getCurrentEquippedItem();
                    if (currentItem != null)
                    {
                        lootChest.addLoot(currentItem.copy());
                        currentItem.stackSize = 0;
                    }
                    lootChest.close();
                }
                else
                {
                    lootChest.pickUpItem(player);
                }
            }
        }

        return true;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack)
    {
        super.onBlockPlacedBy(world, x, y, z, entityLivingBase, itemStack);

        TileEntityLootChest entity = (TileEntityLootChest) world.getTileEntity(x, y, z);
        entity.direction = IvMultiBlockHelper.getRotation(entityLivingBase);
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World var1, int var2)
    {
        return new TileEntityLootChest();
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public IIcon getIcon(int var1, int var2)
    {
        return Blocks.planks.getIcon(0, 1);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {

    }
}
