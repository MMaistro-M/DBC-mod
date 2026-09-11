/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.data.BipedRotations;
import riskyken.armourersWorkshop.common.items.AbstractModItem;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMannequin;
import riskyken.armourersWorkshop.utils.NBTHelper;
import riskyken.armourersWorkshop.utils.TranslateUtils;

public class ItemMannequinTool
extends AbstractModItem {
    private static final String TAG_ROTATION_DATA = "rotationData";

    public ItemMannequinTool() {
        super("mannequinTool");
        this.setSortPriority(10);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister register) {
        this.field_77791_bV = register.func_94245_a(LibItemResources.MANNEQUIN_TOOL);
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        int meta;
        TileEntity te;
        Block block = world.func_147439_a(x, y, z);
        if (block != null && block == ModBlocks.mannequin | block == ModBlocks.doll && (te = (meta = world.func_72805_g(x, y, z)) == 0 ? world.func_147438_o(x, y, z) : world.func_147438_o(x, y - 1, z)) != null && te instanceof TileEntityMannequin) {
            TileEntityMannequin teMan = (TileEntityMannequin)te;
            if (player.func_70093_af()) {
                this.setRotationDataOnStack(stack, teMan.getBipedRotations());
            } else {
                BipedRotations bipedRotations = this.getRotationDataFromStack(stack);
                if (bipedRotations != null) {
                    teMan.setBipedRotations(bipedRotations);
                }
            }
            return true;
        }
        return false;
    }

    private BipedRotations getRotationDataFromStack(ItemStack stack) {
        if (!stack.func_77942_o()) {
            return null;
        }
        NBTTagCompound compound = stack.func_77978_p();
        if (!compound.func_74764_b(TAG_ROTATION_DATA)) {
            return null;
        }
        NBTTagCompound rotationCompound = compound.func_74775_l(TAG_ROTATION_DATA);
        BipedRotations bipedRotations = new BipedRotations();
        bipedRotations.loadNBTData(rotationCompound);
        return bipedRotations;
    }

    private void setRotationDataOnStack(ItemStack stack, BipedRotations bipedRotations) {
        NBTTagCompound compound = NBTHelper.getNBTForStack(stack);
        NBTTagCompound rotationCompound = new NBTTagCompound();
        bipedRotations.saveNBTData(rotationCompound);
        compound.func_74782_a(TAG_ROTATION_DATA, (NBTBase)rotationCompound);
        stack.func_77982_d(compound);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        super.func_77624_a(stack, player, list, p_77624_4_);
        if (stack.func_77942_o()) {
            String settingsSaved = TranslateUtils.translate("item.armourersworkshop:rollover.settingsSaved");
            list.add(settingsSaved);
        } else {
            String noSettingsSaved = TranslateUtils.translate("item.armourersworkshop:rollover.noSettingsSaved");
            list.add(noSettingsSaved);
        }
    }
}

