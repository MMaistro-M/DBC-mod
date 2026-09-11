/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonSyntaxException
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.shader.ShaderGroup
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.GuiOpenEvent
 */
package noppes.npcs.client.gui.player.modern;

import com.google.gson.JsonSyntaxException;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import noppes.npcs.client.gui.player.modern.GuiModernDialogInteract;
import noppes.npcs.client.gui.player.modern.GuiModernQuestDialog;

public class BlurEventHandler {
    @SubscribeEvent
    public void onGuiChange(GuiOpenEvent event) {
        if (Minecraft.func_71410_x().field_71441_e != null) {
            EntityRenderer er = Minecraft.func_71410_x().field_71460_t;
            if (er.func_147706_e() == null && event.gui instanceof GuiModernDialogInteract || event.gui instanceof GuiModernQuestDialog) {
                BlurEventHandler.loadEffect(er, new ResourceLocation("customnpcs", "shaders/post/blur.json"));
            } else if (er.func_147706_e() != null && !(event.gui instanceof GuiModernDialogInteract)) {
                er.func_147703_b();
            }
        }
    }

    public static void loadEffect(EntityRenderer er, ResourceLocation p_175069_1_) {
        if (er.field_147707_d != null) {
            er.field_147707_d.func_148021_a();
        }
        Minecraft mc = Minecraft.func_71410_x();
        try {
            er.field_147707_d = new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), p_175069_1_);
            er.field_147707_d.func_148026_a(mc.field_71443_c, mc.field_71440_d);
        }
        catch (JsonSyntaxException | IOException throwable) {
            // empty catch block
        }
    }
}

