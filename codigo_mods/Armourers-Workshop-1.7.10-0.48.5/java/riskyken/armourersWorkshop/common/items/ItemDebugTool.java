/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.items.AbstractModItem;

public class ItemDebugTool
extends AbstractModItem {
    public ItemDebugTool() {
        super("debugTool", true);
        this.setSortPriority(-1);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister register) {
        this.field_77791_bV = register.func_94245_a(LibItemResources.DEBUG_TOOL);
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (world.field_72995_K) {
            player.openGui((Object)ArmourersWorkshop.instance, 12, world, 0, 0, 0);
        }
        return stack;
    }

    public static interface IDebug {
        public void getDebugHoverText(World var1, int var2, int var3, int var4, ArrayList<String> var5);
    }
}

