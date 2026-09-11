/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderPlayerEvent$Post
 *  net.minecraftforge.client.event.RenderPlayerEvent$Pre
 *  net.minecraftforge.client.event.RenderPlayerEvent$Specials$Post
 *  org.lwjgl.opengl.GL11
 */
package com.tobiasmjc.dbcadditions.event;

import JinRyuu.JBRA.ModelBipedDBC;
import JinRyuu.JBRA.RenderPlayerJBRA;
import JinRyuu.JRMCore.JRMCoreH;
import com.tobiasmjc.dbcadditions.client.models.ModelPotara;
import com.tobiasmjc.dbcadditions.data.ability.DBCAAbilities;
import com.tobiasmjc.dbcadditions.items.ItemPotara;
import com.tobiasmjc.dbcadditions.utils.DataUtils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;

public class DBCAClientHandler {
    public static EntityPlayer currentRenderPlayer;

    @SubscribeEvent
    public void onRenderPlayer(RenderPlayerEvent.Pre event) {
        currentRenderPlayer = event.entityPlayer;
        if (currentRenderPlayer != null && DBCAAbilities.isAbsorbing(currentRenderPlayer)) {
            DBCAClientHandler.currentRenderPlayer.field_70737_aN = -1;
        }
    }

    @SubscribeEvent
    public void potaraRender(RenderPlayerEvent.Specials.Post event) {
        EntityPlayer otherPlayer;
        if (!(event.renderer instanceof RenderPlayerJBRA)) {
            return;
        }
        RenderPlayerJBRA r = (RenderPlayerJBRA)event.renderer;
        this.renderPlayerPotara(event.entityPlayer, r);
        if (DataUtils.isPotaraFusion(event.entityPlayer) && (otherPlayer = DataUtils.getFusionPartner(event.entityPlayer)) != null) {
            this.renderPlayerPotara(otherPlayer, r);
        }
    }

    private void renderPlayerPotara(EntityPlayer pl, RenderPlayerJBRA r) {
        ModelPotara modelPotara;
        ModelBipedDBC mdl = r.modelMain;
        ItemStack right = null;
        ItemStack left = null;
        for (int i = 0; i < JRMCoreH.plyrs.length; ++i) {
            String[] data;
            if (!JRMCoreH.plyrs[i].equals(pl.func_70005_c_()) || (data = JRMCoreH.dat19[i].split(";")) == null) continue;
            int rightID = Integer.parseInt(data[3]);
            int leftID = Integer.parseInt(data[4]);
            if (rightID != -1) {
                right = new ItemStack(Item.func_150899_d((int)rightID));
            }
            if (leftID == -1) continue;
            left = new ItemStack(Item.func_150899_d((int)leftID));
        }
        String color = "yellow";
        GL11.glColor3f((float)235.0f, (float)235.0f, (float)235.0f);
        if (right != null) {
            color = ((ItemPotara)right.func_77973_b()).color;
            modelPotara = ModelPotara.RIGHT_EAR;
            modelPotara.field_78095_p = r.field_77109_a.field_78095_p;
            modelPotara.field_78093_q = r.field_77109_a.field_78093_q;
            modelPotara.field_78091_s = r.field_77109_a.field_78091_s;
            Minecraft.func_71410_x().func_110434_K().func_110577_a(new ResourceLocation("dbcadditions:textures/armor/potara_" + color + ".png"));
            modelPotara.func_78088_a((Entity)pl, mdl.rot1, mdl.rot2, mdl.rot3, mdl.rot4, mdl.rot5, mdl.rot6);
        }
        if (left != null) {
            color = ((ItemPotara)left.func_77973_b()).color;
            modelPotara = ModelPotara.LEFT_EAR;
            modelPotara.field_78095_p = r.field_77109_a.field_78095_p;
            modelPotara.field_78093_q = r.field_77109_a.field_78093_q;
            modelPotara.field_78091_s = r.field_77109_a.field_78091_s;
            Minecraft.func_71410_x().func_110434_K().func_110577_a(new ResourceLocation("dbcadditions:textures/armor/potara_" + color + ".png"));
            modelPotara.func_78088_a((Entity)pl, mdl.rot1, mdl.rot2, mdl.rot3, mdl.rot4, mdl.rot5, mdl.rot6);
        }
    }

    @SubscribeEvent
    public void onRenderPlayer(RenderPlayerEvent.Post event) {
        currentRenderPlayer = null;
    }
}

