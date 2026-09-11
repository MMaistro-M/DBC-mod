/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package hedaox.ninjinentities.event;

import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import hedaox.ninjinentities.entities.EntityDBCNinjin;
import java.util.Arrays;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EventAttackManager {
    private final int slowClient = 0;

    @SubscribeEvent
    public void checkHurtEvent(LivingHurtEvent event) {
        try {
            if (!event.entity.field_70170_p.field_72995_K && event.entity instanceof EntityPlayer) {
                EntityPlayer loadedPlayer = (EntityPlayer)event.entity;
                EntityLivingBase entityKiller = event.entityLiving.func_94060_bK();
                if (entityKiller instanceof EntityDBCNinjin && JRMCoreH.getInt(loadedPlayer, "jrmcBdy") < 10 && ((EntityDBCNinjin)entityKiller).isTrainer) {
                    JRMCoreH.setInt(10, loadedPlayer, "jrmcBdy");
                    NBTTagCompound nbt = JRMCoreH.nbt((Entity)loadedPlayer, "pres");
                    byte st = nbt.func_74771_c("jrmcState");
                    byte st2 = nbt.func_74771_c("jrmcState2");
                    byte rc = nbt.func_74771_c("jrmcRace");
                    byte rls = JRMCoreH.getByte(loadedPlayer, "jrmcRelease");
                    int curStam = JRMCoreH.getInt(loadedPlayer, "jrmcStamina");
                    String StE = nbt.func_74779_i("jrmcStatusEff");
                    nbt.func_74768_a("jrmcHar4va", 5);
                    JRMCoreH.setByte(rc == 4 ? (byte)4 : (st < 4 ? (byte)st : (byte)0), loadedPlayer, "jrmcState");
                    JRMCoreH.setByte(0, loadedPlayer, "jrmcState2");
                    JRMCoreH.setByte(0, loadedPlayer, "jrmcRelease");
                    JRMCoreH.setInt(0, loadedPlayer, "jrmcStamina");
                    StE = JRMCoreH.StusEfcts(19, StE, nbt, false);
                    JRMCoreH.StusEfcts(13, StE, nbt, false);
                    for (Object entity : entityKiller.field_70170_p.func_72872_a(EntityDBCNinjin.class, AxisAlignedBB.func_72330_a((double)(entityKiller.field_70165_t - 100.0), (double)(entityKiller.field_70163_u - 100.0), (double)(entityKiller.field_70161_v - 100.0), (double)(entityKiller.field_70165_t + 100.0), (double)(entityKiller.field_70163_u + 100.0), (double)(entityKiller.field_70161_v + 100.0)))) {
                        ((EntityDBCNinjin)((Object)entity)).func_70106_y();
                    }
                }
            }
        }
        catch (NullPointerException e) {
            System.out.println("exception occured : " + e + " at " + Arrays.toString(e.getStackTrace()));
        }
    }
}

