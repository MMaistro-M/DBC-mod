/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 */
package JinRyuu.DragonBC.common.Gui;

import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.entity.EntityEnergyAtt;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

public class ComDbcSpawnKi
extends CommandBase {
    final String line = "Use '/dbcspawnki (Type) (Speed) (Damage) (Effect) (Color) (Density) (Sound: Move) (Charge Percentage) [User Position X] [User Position Y] [User Position Z]' to spawn a Ki Attack.";

    public String func_71517_b() {
        return "dbcspawnki";
    }

    public String func_71518_a(ICommandSender icommandsender) {
        return "Use '/dbcspawnki (Type) (Speed) (Damage) (Effect) (Color) (Density) (Sound: Move) (Charge Percentage) [User Position X] [User Position Y] [User Position Z]' to spawn a Ki Attack.";
    }

    public int func_82362_a() {
        return 2;
    }

    public boolean func_71519_b(ICommandSender par1ICommandSender) {
        return true;
    }

    private NBTTagCompound nbt(EntityPlayer p, String s) {
        return JRMCoreH.nbt((Entity)p, s);
    }

    public void func_71515_b(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        byte type;
        Object entityplayermp;
        if (par2ArrayOfStr.length < 10) {
            throw new WrongUsageException("Use '/dbcspawnki (Type) (Speed) (Damage) (Effect) (Color) (Density) (Sound: Move) (Charge Percentage) [User Position X] [User Position Y] [User Position Z]' to spawn a Ki Attack.", new Object[0]);
        }
        boolean spawn = true;
        boolean found = false;
        if (par2ArrayOfStr.length == 11) {
            double minx = Double.parseDouble(par2ArrayOfStr[8]) - 1.0;
            double miny = Double.parseDouble(par2ArrayOfStr[9]) - 1.0;
            double minz = Double.parseDouble(par2ArrayOfStr[10]) - 1.0;
            double maxx = minx + 2.0;
            double maxy = miny + 2.0;
            double maxz = minz + 2.0;
            List var6 = par1ICommandSender.func_130014_f_().func_72839_b(null, AxisAlignedBB.func_72330_a((double)minx, (double)miny, (double)minz, (double)maxx, (double)maxy, (double)maxz));
            Entity var10 = null;
            for (int var9 = 0; var9 < var6.size(); ++var9) {
                var10 = (Entity)var6.get(var9);
                if (!(var10 instanceof EntityLivingBase) || !var10.func_70067_L()) continue;
                found = true;
                break;
            }
            entityplayermp = found ? (EntityLivingBase)var10 : null;
        } else if (par2ArrayOfStr.length == 12) {
            entityplayermp = ComDbcSpawnKi.func_82359_c((ICommandSender)par1ICommandSender, (String)par2ArrayOfStr[11]);
            spawn = true;
            found = true;
        } else {
            return;
        }
        EntityEnergyAtt mr = null;
        boolean spawned = false;
        if (spawn && found && entityplayermp != null && JRMCoreConfig.dat5695[type = Byte.parseByte(par2ArrayOfStr[0])]) {
            byte chrg;
            byte sndMv;
            byte density;
            byte color;
            byte effect;
            int dam1;
            byte speed;
            if (type < 0) {
                type = 0;
            }
            if (type > 8) {
                type = 8;
            }
            if ((speed = Byte.parseByte(par2ArrayOfStr[1])) < 0) {
                speed = 0;
            }
            if ((dam1 = Integer.parseInt(par2ArrayOfStr[2])) < 0) {
                dam1 = 0;
            }
            if ((effect = Byte.parseByte(par2ArrayOfStr[3])) < 0) {
                effect = 0;
            }
            if (effect > 1) {
                effect = 1;
            }
            if ((color = Byte.parseByte(par2ArrayOfStr[4])) < 0) {
                color = 0;
            }
            if (color > JRMCoreH.techCol.length - 1) {
                color = (byte)(JRMCoreH.techCol.length - 1);
            }
            if ((density = Byte.parseByte(par2ArrayOfStr[5])) < 0) {
                density = 0;
            }
            if ((sndMv = Byte.parseByte(par2ArrayOfStr[6])) < 0) {
                sndMv = 0;
            }
            if (sndMv > 1) {
                sndMv = 1;
            }
            if ((chrg = Byte.parseByte(par2ArrayOfStr[7])) < 0) {
                chrg = 0;
            }
            if (chrg > 100) {
                chrg = 100;
            }
            byte[] sts = JRMCoreH.techDBCstatsDefault;
            entityplayermp.field_70170_p.func_72956_a((Entity)entityplayermp, "jinryuudragonbc:DBC2.basicbeam_fire", 0.5f, 1.0f);
            mr = new EntityEnergyAtt((EntityLivingBase)entityplayermp, type, speed, 50, effect, color, density, 0, 0, sndMv, chrg, dam1, 0, sts, 0);
            entityplayermp.field_70170_p.func_72838_d((Entity)mr);
            spawned = true;
        }
    }
}

