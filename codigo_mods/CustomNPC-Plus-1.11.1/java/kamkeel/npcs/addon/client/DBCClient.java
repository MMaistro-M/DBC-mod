/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 */
package kamkeel.npcs.addon.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.gui.mainmenu.GuiNpcStats;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.entity.EntityNPCInterface;

public class DBCClient {
    public static DBCClient Instance;
    public boolean supportEnabled = true;

    public DBCClient() {
        Instance = this;
    }

    public void showDBCStatButtons(GuiNpcStats stats, EntityLivingBase entity) {
    }

    public void showDBCStatActionPerformed(GuiNpcStats stats, GuiNpcButton btn) {
    }

    public void renderDBCAuras(EntityNPCInterface npcInterface) {
    }

    public GuiNPCInterface2 manageCustomForms(EntityNPCInterface npcInterface) {
        return null;
    }

    public GuiNPCInterface2 manageCustomAuras(EntityNPCInterface npcInterface) {
        return null;
    }

    public boolean applyRenderModel(ModelRenderer renderer) {
        return false;
    }

    public boolean firstPersonAnimation(float partialRenderTick, EntityPlayer player, ModelBiped model, RenderBlocks renderBlocksIr, ResourceLocation resItemGlint) {
        return false;
    }
}

