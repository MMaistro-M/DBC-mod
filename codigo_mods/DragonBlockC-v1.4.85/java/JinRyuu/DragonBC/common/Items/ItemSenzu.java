/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.ArrowLooseEvent
 *  net.minecraftforge.event.entity.player.ArrowNockEvent
 */
package JinRyuu.DragonBC.common.Items;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.server.JGPlayerMP;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemSenzu
extends Item {
    private boolean alwaysEdible;

    public ItemSenzu(int par2, float par3, boolean par4) {
        this.func_77627_a(true);
        this.field_77777_bU = 5;
        this.func_77656_e(1);
        this.func_77637_a(mod_DragonBC.DragonBlockC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add(StatCollector.func_74838_a((String)"dbc.itemSenzu.line1"));
    }

    public String getTextureFile() {
        return "jinryuudragonbc:";
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("jinryuudragonbc:" + this.func_77658_a());
    }

    public void func_77615_a(ItemStack itemStack, World world, EntityPlayer entityPlayer, int par4) {
        int j = this.func_77626_a(itemStack) - par4;
        ArrowLooseEvent event = new ArrowLooseEvent(entityPlayer, itemStack, j);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.isCanceled()) {
            return;
        }
        j = event.charge;
        if (!world.field_72995_K) {
            EntityPlayer player = entityPlayer;
            int currentTime = (int)(System.currentTimeMillis() / 1000L);
            int senzuCool = JRMCoreH.getInt(player, "jrmcSenzuCC");
            if (senzuCool > currentTime) {
                player.func_145747_a(new ChatComponentText("Senzu can be used again after " + (senzuCool - currentTime) + " seconds!").func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.GOLD)));
                return;
            }
            JGPlayerMP jgPlayer = new JGPlayerMP(player);
            jgPlayer.connectBaseNBT();
            if (!JRMCoreConfig.CanUseSenzuWhileKOd && jgPlayer.getNBT().func_74762_e("jrmcHar4va") > 0) {
                return;
            }
            int[] playerAttributes = jgPlayer.getAttributes();
            byte powerType = jgPlayer.getPowerType();
            byte race = jgPlayer.getRace();
            byte classID = jgPlayer.getClassID();
            int maxBody = jgPlayer.getHealthMax(race, classID, powerType, playerAttributes);
            int maxEnergy = jgPlayer.getEnergyMax(race, classID, powerType, playerAttributes, JRMCoreH.SklLvl_KiBs(player, (int)powerType));
            int maxStam = jgPlayer.getStaminaMax(race, classID, powerType, playerAttributes);
            JRMCoreH.setInt(maxBody, player, "jrmcBdy");
            JRMCoreH.setInt(maxEnergy, player, "jrmcEnrgy");
            JRMCoreH.setInt(maxStam, player, "jrmcStamina");
            JRMCoreH.setInt(currentTime + DBCConfig.senzuCool, player, "jrmcSenzuCC");
            entityPlayer.func_70606_j(entityPlayer.func_110138_aP());
            world.func_72956_a((Entity)entityPlayer, "jinryuudragonbc:DBC2.sensu", 0.5f, world.field_73012_v.nextFloat() * 0.1f + 0.9f);
            entityPlayer.func_71024_bL().func_75122_a(20, 0.9f);
            if (!entityPlayer.field_71075_bZ.field_75098_d) {
                entityPlayer.field_71071_by.func_146026_a((Item)this);
            }
        }
    }

    public ItemStack func_77654_b(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        return par1ItemStack;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.isCanceled()) {
            return event.result;
        }
        if (par3EntityPlayer.field_71075_bZ.field_75098_d || par3EntityPlayer.field_71071_by.func_146028_b((Item)this)) {
            par3EntityPlayer.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        }
        return par1ItemStack;
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.block;
    }
}

