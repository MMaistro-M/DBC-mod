/*
 * Decompiled with CFR 0.152.
 */
package com.tobiasmjc.dbcadditions.mixin.late.gui;

import JinRyuu.JRMCore.JRMCoreGui;
import com.tobiasmjc.dbcadditions.client.ClientProxy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={JRMCoreGui.class}, remap=false)
public class MixinJRMCoreGui {
    @Inject(method={"renderActionMenu"}, at={@At(value="HEAD")}, cancellable=true)
    private static void modifyActionMenu(CallbackInfo ci) {
        ci.cancel();
        ClientProxy.ACTION_MENU.renderActionMenu();
    }
}

