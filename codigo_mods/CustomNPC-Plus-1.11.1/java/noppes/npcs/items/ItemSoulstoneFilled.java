/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package noppes.npcs.items;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import noppes.npcs.LogWriter;
import noppes.npcs.config.ConfigDebug;
import noppes.npcs.constants.EnumRoleType;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleCompanion;
import noppes.npcs.roles.RoleFollower;

public class ItemSoulstoneFilled
extends Item {
    public ItemSoulstoneFilled() {
        this.func_77625_d(1);
    }

    public Item func_77655_b(String name) {
        super.func_77655_b(name);
        GameRegistry.registerItem((Item)this, (String)name);
        return this;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean bo) {
        if (stack.field_77990_d == null || !stack.field_77990_d.func_150297_b("Entity", 10)) {
            list.add(EnumChatFormatting.RED + "Error");
            return;
        }
        String name = StatCollector.func_74838_a((String)stack.field_77990_d.func_74779_i("Name"));
        if (stack.field_77990_d.func_74764_b("DisplayName")) {
            name = stack.field_77990_d.func_74779_i("DisplayName") + " (" + name + ")";
        }
        list.add(EnumChatFormatting.BLUE + name);
        if (stack.field_77990_d.func_74764_b("ExtraText")) {
            String[] split;
            String text = "";
            for (String s : split = stack.field_77990_d.func_74779_i("ExtraText").split(",")) {
                text = text + StatCollector.func_74838_a((String)s);
            }
            list.add(text);
        }
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (!this.spawn(player, stack, world, x, y, z)) {
            return false;
        }
        if (!player.field_71075_bZ.field_75098_d) {
            stack.func_77979_a(1);
        }
        return true;
    }

    public boolean spawn(EntityPlayer player, ItemStack stack, World world, int x, int y, int z) {
        if (world.field_72995_K) {
            return true;
        }
        if (stack.field_77990_d == null || !stack.field_77990_d.func_150297_b("Entity", 10)) {
            return false;
        }
        NBTTagCompound compound = stack.field_77990_d.func_74775_l("Entity");
        Entity entity = EntityList.func_75615_a((NBTTagCompound)compound, (World)world);
        if (entity == null) {
            return false;
        }
        entity.func_70107_b((double)x + 0.5, (double)((float)(y + 1) + 0.2f), (double)z + 0.5);
        entity.field_71093_bK = world.field_73011_w.field_76574_g;
        String sourceName = "DISPENSER";
        if (player != null) {
            sourceName = player.func_70005_c_();
        }
        if (ConfigDebug.PlayerLogging && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
            LogWriter.script(String.format("[%s] %s PLACED ENTITY %s", "SOULSTONE", sourceName, entity));
        }
        if (entity instanceof EntityNPCInterface) {
            EntityNPCInterface npc = (EntityNPCInterface)entity;
            npc.ais.startPos = new int[]{x, y, z};
            npc.func_70606_j(npc.func_110138_aP());
            npc.func_70107_b((float)x + 0.5f, npc.getStartYPos(), (float)z + 0.5f);
            npc.advanced.soulStonePlayerName = sourceName;
            npc.advanced.soulStoneInit = true;
            if (npc.advanced.role == EnumRoleType.Companion && player != null) {
                PlayerData data = PlayerData.get(player);
                if (data.hasCompanion()) {
                    return false;
                }
                ((RoleCompanion)npc.roleInterface).setOwner(player);
                data.setCompanion(npc);
            }
            if (npc.advanced.role == EnumRoleType.Follower && player != null) {
                ((RoleFollower)npc.roleInterface).setOwner(player);
            }
        }
        world.func_72838_d(entity);
        return true;
    }
}

