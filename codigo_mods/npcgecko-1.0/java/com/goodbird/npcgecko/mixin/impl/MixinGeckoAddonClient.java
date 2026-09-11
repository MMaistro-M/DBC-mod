/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kamkeel.npcs.addon.client.GeckoAddonClient
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.goodbird.npcgecko.mixin.impl;

import com.goodbird.npcgecko.client.gui.GuiStringSelection;
import com.goodbird.npcgecko.client.gui.SubGuiModelAnimation;
import com.goodbird.npcgecko.entity.EntityCustomModel;
import com.goodbird.npcgecko.mixin.IDataDisplay;
import java.util.Vector;
import kamkeel.npcs.addon.client.GeckoAddonClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.gui.model.GuiCreationScreen;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.model.ModelMPM;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.resource.GeckoLibCache;

@Mixin(value={GeckoAddonClient.class})
public class MixinGeckoAddonClient {
    @Shadow(remap=false)
    public boolean supportEnabled;

    @Overwrite(remap=false)
    public void showGeckoButtons(GuiCreationScreen creationScreen, EntityLivingBase entity) {
        if (!this.supportEnabled) {
            return;
        }
        if (entity instanceof EntityCustomModel) {
            creationScreen.addButton(new GuiNpcButton(202, creationScreen.guiLeft - 60, creationScreen.guiTop + 40, 180, 20, ((IDataDisplay)((Object)creationScreen.npc.display)).getCustomModelData().getModel()));
        }
    }

    @Overwrite(remap=false)
    public void geckoGuiCreationScreenActionPerformed(GuiCreationScreen creationScreen, GuiNpcButton button) {
        if (!this.supportEnabled) {
            return;
        }
        if (button.field_146127_k == 202) {
            Vector<String> list = new Vector<String>();
            for (ResourceLocation resLoc : GeckoLibCache.getInstance().getGeoModels().keySet()) {
                list.add(resLoc.toString());
            }
            creationScreen.setSubGui(new GuiStringSelection(creationScreen, "Selecting GeckoLib Model:", list, name -> {
                ((IDataDisplay)((Object)creationScreen.npc.display)).getCustomModelData().setModel((String)name);
                creationScreen.getButton(202).setDisplayText((String)name);
            }));
        }
    }

    @Overwrite(remap=false)
    public void geckoNpcDisplayInitGui(GuiNPCInterface2 gui) {
        if (!this.supportEnabled) {
            return;
        }
        int y = gui.guiTop + 188;
        gui.addLabel(new GuiNpcLabel(212, "Model Animation", gui.guiLeft + 185, y + 5));
        gui.addButton(new GuiNpcButton(212, gui.guiLeft + 300, y, 100, 20, "selectServer.edit"));
        if (!((IDataDisplay)((Object)gui.npc.display)).hasCustomModel()) {
            gui.getLabel((int)212).enabled = false;
            gui.getButton(212).setVisible(false);
            gui.getButton(212).setEnabled(false);
        }
    }

    @Overwrite(remap=false)
    public void geckoNpcDisplayActionPerformed(GuiNPCInterface2 gui, GuiNpcButton btn) {
        if (!this.supportEnabled) {
            return;
        }
        if (btn.field_146127_k == 212) {
            gui.setSubGui(new SubGuiModelAnimation(gui.npc));
        }
    }

    @Overwrite(remap=false)
    public boolean isGeckoModel(ModelBase mainModel) {
        if (!this.supportEnabled) {
            return false;
        }
        return mainModel instanceof ModelMPM && ((ModelMPM)mainModel).entity instanceof IAnimatable;
    }

    @Overwrite(remap=false)
    public void geckoRenderModel(ModelMPM mainModel, EntityNPCInterface npc, float rot, float partial) {
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glTranslated((double)0.0, (double)-1.5, (double)0.0);
        mainModel.entity.field_70760_ar = 0.0f;
        mainModel.entity.field_70761_aq = 0.0f;
        if (!npc.func_82150_aj()) {
            if (mainModel.entity instanceof EntityCustomModel) {
                ((EntityCustomModel)mainModel.entity).isSemiVisible = false;
            }
            RenderManager.field_78727_a.func_147940_a((Entity)mainModel.entity, 0.0, 0.0, 0.0, rot, partial);
        } else if (!npc.func_98034_c((EntityPlayer)Minecraft.func_71410_x().field_71439_g)) {
            if (mainModel.entity instanceof EntityCustomModel) {
                ((EntityCustomModel)mainModel.entity).isSemiVisible = true;
                RenderManager.field_78727_a.func_147940_a((Entity)mainModel.entity, 0.0, 0.0, 0.0, rot, partial);
            } else {
                GL11.glPushMatrix();
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.15f);
                GL11.glDepthMask((boolean)false);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glAlphaFunc((int)516, (float)0.003921569f);
                RenderManager.field_78727_a.func_147940_a((Entity)mainModel.entity, 0.0, 0.0, 0.0, rot, partial);
                GL11.glDisable((int)3042);
                GL11.glAlphaFunc((int)516, (float)0.1f);
                GL11.glPopMatrix();
                GL11.glDepthMask((boolean)true);
            }
        }
    }
}
