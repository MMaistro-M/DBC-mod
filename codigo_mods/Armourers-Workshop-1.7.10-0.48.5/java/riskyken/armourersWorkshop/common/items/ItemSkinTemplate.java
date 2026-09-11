/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTUtil
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.items;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.items.AbstractModItem;
import riskyken.armourersWorkshop.common.skin.ISkinHolder;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class ItemSkinTemplate
extends AbstractModItem
implements ISkinHolder {
    private static final String TAG_OWNER = "owner";
    @SideOnly(value=Side.CLIENT)
    IIcon giftIcon;

    public ItemSkinTemplate() {
        super("equipmentSkinTemplate");
        this.func_77625_d(64);
        this.func_77627_a(true);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister register) {
        this.field_77791_bV = register.func_94245_a(LibItemResources.TEMPLATE_BLANK);
        this.giftIcon = register.func_94245_a(LibItemResources.GIFT_SACK);
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (!world.field_72995_K && stack.func_77960_j() == 1000) {
            ItemStack giftStack = new ItemStack(ModBlocks.doll, 1);
            NBTTagCompound profileTag = new NBTTagCompound();
            NBTUtil.func_152460_a((NBTTagCompound)profileTag, (GameProfile)player.func_146103_bH());
            giftStack.func_77982_d(new NBTTagCompound());
            giftStack.func_77978_p().func_74782_a(TAG_OWNER, (NBTBase)profileTag);
            if (player.field_71071_by.func_70441_a(giftStack)) {
                --stack.field_77994_a;
            } else {
                player.func_146105_b((IChatComponent)new ChatComponentText(StatCollector.func_74838_a((String)"chat.armourersworkshop:inventoryFull")));
            }
        }
        return super.func_77659_a(stack, world, player);
    }

    public IIcon func_77617_a(int damage) {
        if (damage == 1000) {
            return this.giftIcon;
        }
        return super.func_77617_a(damage);
    }

    @Override
    public ItemStack makeStackForEquipment(Skin armourItemData) {
        return SkinNBTHelper.makeEquipmentSkinStack(armourItemData);
    }
}

