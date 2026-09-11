/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.constants.EnumRoleType;
import noppes.npcs.controllers.ServerCloneController;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleCompanion;
import noppes.npcs.roles.RoleFollower;

public class ItemSoulstoneEmpty
extends Item {
    public ItemSoulstoneEmpty() {
        this.func_77625_d(64);
    }

    public Item func_77655_b(String name) {
        super.func_77655_b(name);
        GameRegistry.registerItem((Item)this, (String)name);
        return this;
    }

    public boolean store(EntityLivingBase entity, ItemStack stack, EntityPlayer player) {
        if (!this.canSoulStone(entity, player) || entity instanceof EntityPlayer) {
            return false;
        }
        ItemStack stone = new ItemStack(CustomItems.soulstoneFull);
        NBTTagCompound compound = new NBTTagCompound();
        if (!entity.func_70039_c(compound)) {
            return false;
        }
        ServerCloneController.Instance.cleanTags(compound);
        if (stone.field_77990_d == null) {
            stone.field_77990_d = new NBTTagCompound();
        }
        stone.field_77990_d.func_74782_a("Entity", (NBTBase)compound);
        String name = EntityList.func_75621_b((Entity)entity);
        if (name == null) {
            name = "generic";
        }
        stone.field_77990_d.func_74778_a("Name", "entity." + name + ".name");
        if (entity instanceof EntityNPCInterface) {
            EntityNPCInterface npc = (EntityNPCInterface)entity;
            stone.field_77990_d.func_74778_a("DisplayName", entity.func_70005_c_());
            if (npc.advanced.role == EnumRoleType.Companion) {
                RoleCompanion role = (RoleCompanion)npc.roleInterface;
                stone.field_77990_d.func_74778_a("ExtraText", "companion.stage,: ," + role.stage.name);
            }
        } else if (entity instanceof EntityLiving && ((EntityLiving)entity).func_94056_bM()) {
            stone.field_77990_d.func_74778_a("DisplayName", ((EntityLiving)entity).func_94057_bL());
        }
        NoppesUtilServer.GivePlayerItem((Entity)player, player, stone);
        if (!player.field_71075_bZ.field_75098_d) {
            stack.func_77979_a(1);
            if (stack.field_77994_a <= 0) {
                player.func_71028_bD();
            }
        }
        entity.field_70128_L = true;
        return true;
    }

    public boolean canSoulStone(EntityLivingBase entity, EntityPlayer player) {
        if (NoppesUtilServer.isOp(player) && player.field_71075_bZ.field_75098_d) {
            return true;
        }
        if (CustomNpcsPermissions.enabled() && CustomNpcsPermissions.hasPermission(player, CustomNpcsPermissions.SOULSTONE_ALL)) {
            return true;
        }
        if (entity instanceof EntityNPCInterface) {
            RoleCompanion role;
            EntityNPCInterface npc = (EntityNPCInterface)entity;
            if (npc.advanced.refuseSoulStone) {
                return false;
            }
            if (ConfigMain.SoulStoneFriendlyNPCs && npc.getFaction() != null) {
                int p = npc.advanced.minFactionPointsToSoulStone;
                if (p == -1 && npc.getFaction().isFriendlyToPlayer(player)) {
                    return true;
                }
                if (p != -1) {
                    PlayerData data = PlayerData.get(player);
                    return data.factionData.getFactionPoints(npc.getFaction().getId()) >= p;
                }
            }
            if (npc.advanced.role == EnumRoleType.Companion && (role = (RoleCompanion)npc.roleInterface).getOwner() == player) {
                return true;
            }
            if (npc.advanced.role == EnumRoleType.Follower && (role = (RoleFollower)npc.roleInterface).getOwner() == player) {
                return !role.refuseSoulStone;
            }
            return ConfigMain.SoulStoneNPCs;
        }
        if (entity instanceof EntityAnimal) {
            return ConfigMain.SoulStoneAnimals;
        }
        if (entity instanceof EntityVillager) {
            return ConfigMain.SoulStoneVillagers;
        }
        return false;
    }
}

