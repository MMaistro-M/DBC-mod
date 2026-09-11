/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.tobiasmjc.dbcadditions.mixin.late;

import JinRyuu.JRMCore.JRMCoreA;
import JinRyuu.JRMCore.JRMCoreCliTicH;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreHDBC;
import com.llamalad7.mixinextras.sugar.Local;
import com.tobiasmjc.dbcadditions.client.ClientProxy;
import com.tobiasmjc.dbcadditions.common.CommonProxy;
import com.tobiasmjc.dbcadditions.data.DBCAClientData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={JRMCoreCliTicH.class}, remap=false)
public class MixinJRMCoreCliTicH {
    @Shadow
    static int actionSelectID = 0;
    @Shadow
    private boolean actionMenuOpen;

    @Inject(method={"onRenderTick"}, at={@At(value="HEAD")}, cancellable=true)
    private void startRenderKiBar(CallbackInfo ci) {
        ClientProxy.isRenderingKiBar = true;
    }

    @Inject(method={"onRenderTick"}, at={@At(value="TAIL")}, cancellable=true)
    private void endRenderKiBar(CallbackInfo ci) {
        ClientProxy.isRenderingKiBar = false;
    }

    @Inject(method={"onTickInGame"}, at={@At(value="FIELD", target="LJinRyuu/JRMCore/JRMCoreH;data1:[Ljava/lang/String;", ordinal=0, shift=At.Shift.BEFORE)})
    public void setCurrentTickPlayerClientMain(CallbackInfo ci, @Local(name={"plyr"}) EntityPlayer plyr) {
        CommonProxy.CurrentPlayerDBC = plyr;
    }

    @Inject(method={"onTickInGame"}, at={@At(value="INVOKE", target="LJinRyuu/JRMCore/JRMCoreH;data(Ljava/lang/String;ILjava/lang/String;)Ljava/lang/String;", ordinal=0, shift=At.Shift.BEFORE)})
    public void setCurrentTickPlayerClientOthers(CallbackInfo ci, @Local(name={"plyr1"}) EntityPlayer plyr1) {
        CommonProxy.CurrentPlayerDBC = plyr1;
    }

    @Inject(method={"onRenderTick"}, at={@At(value="HEAD")}, cancellable=true)
    private void fixActions(CallbackInfo ci) {
        actionSelectID = -1;
        int id = DBCAClientData.selectedAction;
        if (id != -1 && !this.actionMenuOpen) {
            if (id == 1) {
                EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
                player.openGui((Object)"dbcadditions", 1, player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
            } else {
                JRMCoreA.actions = JRMCoreA.actionsDBC;
                if (JRMCoreA.actions.get(id) != null) {
                    JRMCoreHDBC.action((Integer)JRMCoreA.actions.get(id), true, false);
                }
            }
            DBCAClientData.selectedAction = -1;
        }
        if (!DBCAClientData.actionNBO && this.actionMenuOpen && JRMCoreClient.mc.field_71474_y.field_74312_F.func_151470_d() && id % 9 == 4) {
            actionSelectID = -1;
            DBCAClientData.actionNBO = true;
            DBCAClientData.actionPage = DBCAClientData.actionPage == 0 ? 1 : 0;
        }
    }
}

