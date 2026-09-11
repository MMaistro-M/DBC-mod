/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.tobiasmjc.dbcadditions.mixin.late.npcs;

import JinRyuu.DragonBC.common.Npcs.EntityMasterWhis;
import com.tobiasmjc.dbcadditions.DBCAConfig;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={EntityMasterWhis.class})
public class MixinEntityMasterWhis {
    @Inject(method={"interact"}, at={@At(value="RETURN")})
    private void injectWhisInteract(EntityPlayer player, CallbackInfoReturnable<Boolean> ci) {
        if (!DBCAConfig.WhisTeleport) {
            return;
        }
        EntityMasterWhis instance = (EntityMasterWhis)((Object)this);
        player.openGui((Object)"dbcadditions", 9002, player.field_70170_p, (int)instance.field_70165_t, (int)instance.field_70163_u, (int)instance.field_70161_v);
    }
}

