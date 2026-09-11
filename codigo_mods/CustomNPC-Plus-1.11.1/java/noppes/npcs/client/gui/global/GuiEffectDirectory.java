/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.global;

import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.category.CategoryItemsRequestPacket;
import kamkeel.npcs.network.packets.request.category.CategoryListRequestPacket;
import kamkeel.npcs.network.packets.request.category.CategoryMoveItemPacket;
import kamkeel.npcs.network.packets.request.category.CategoryRemovePacket;
import kamkeel.npcs.network.packets.request.category.CategorySavePacket;
import kamkeel.npcs.network.packets.request.effects.EffectClonePacket;
import kamkeel.npcs.network.packets.request.effects.EffectGetPacket;
import kamkeel.npcs.network.packets.request.effects.EffectRemovePacket;
import kamkeel.npcs.network.packets.request.effects.EffectSavePacket;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.gui.SubGuiEffectGeneral;
import noppes.npcs.client.gui.global.GuiNPCManageEffects;
import noppes.npcs.client.gui.player.inventory.GuiCNPCInventory;
import noppes.npcs.client.gui.util.GuiDirectoryCategorized;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.client.renderer.ImageData;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.controllers.data.Category;
import noppes.npcs.controllers.data.CustomEffect;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public class GuiEffectDirectory
extends GuiDirectoryCategorized {
    public CustomEffect effect = new CustomEffect();
    public EntityNPCInterface npc;

    public GuiEffectDirectory(EntityNPCInterface npc) {
        this.npc = npc;
    }

    @Override
    protected String getTitle() {
        return "Effects";
    }

    @Override
    protected void requestCategoryList() {
        PacketClient.sendClient(new CategoryListRequestPacket(1));
    }

    @Override
    protected void requestItemsInCategory(int catId) {
        PacketClient.sendClient(new CategoryItemsRequestPacket(1, catId));
    }

    @Override
    protected void requestItemData(int itemId) {
        PacketClient.sendClient(new EffectGetPacket(itemId));
    }

    @Override
    protected void onSaveCategory(Category cat) {
        PacketClient.sendClient(new CategorySavePacket(1, cat.writeNBT(new NBTTagCompound())));
    }

    @Override
    protected void onRemoveCategory(int catId) {
        PacketClient.sendClient(new CategoryRemovePacket(1, catId));
    }

    @Override
    protected void onAddItem(int catId) {
        String name = "New";
        while (this.itemData.containsKey(name)) {
            name = name + "_";
        }
        CustomEffect newEffect = new CustomEffect(-1, name);
        PacketClient.sendClient(new EffectSavePacket(newEffect.writeToNBT(false), ""));
    }

    @Override
    protected void onRemoveItem(int itemId) {
        PacketClient.sendClient(new EffectRemovePacket(itemId));
        this.effect = new CustomEffect();
    }

    @Override
    protected void onEditItem() {
        if (this.effect != null && this.effect.id >= 0) {
            this.setSubGui(new SubGuiEffectGeneral(this, this.effect));
        }
    }

    @Override
    protected void onCloneItem() {
        if (this.effect != null && this.effect.id >= 0) {
            PacketClient.sendClient(new EffectClonePacket(this.effect.id));
        }
    }

    @Override
    protected void onItemReceived(NBTTagCompound compound) {
        this.effect = new CustomEffect();
        this.effect.readFromNBT(compound);
        this.setPrevItemName(this.effect.name);
        if (this.effect.id != -1) {
            CustomEffectController.getInstance().getCustomEffects().replace(this.effect.id, this.effect);
        }
    }

    @Override
    protected boolean hasSelectedItem() {
        return this.effect != null && this.effect.id >= 0;
    }

    @Override
    protected int getSelectedItemId() {
        return this.effect != null ? this.effect.id : -1;
    }

    @Override
    protected void sendMovePacket(int itemId, int destCatId) {
        PacketClient.sendClient(new CategoryMoveItemPacket(1, itemId, destCatId));
    }

    @Override
    protected GuiScreen getWindowedVariant() {
        return new GuiNPCManageEffects(this.npc);
    }

    @Override
    protected void saveCurrentItem() {
        if (this.effect != null && this.effect.id >= 0 && this.prevItemName != null && !this.prevItemName.isEmpty()) {
            PacketClient.sendClient(new EffectSavePacket(this.effect.writeToNBT(false), this.prevItemName));
            this.prevItemName = this.effect.name;
        }
    }

    @Override
    protected void onSubGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiEffectGeneral && this.effect != null && this.effect.id >= 0) {
            this.setPrevItemName(this.effect.name);
            if (this.selectedCatId >= 0) {
                this.requestItemsInCategory(this.selectedCatId);
            }
        }
    }

    @Override
    protected void drawItemPreview(int centerX, int centerY, int mouseX, int mouseY, float partialTicks) {
        if (this.effect == null || this.effect.id == -1) {
            return;
        }
        int iconRenderSize = Math.min(this.previewW, this.previewH) / 3;
        iconRenderSize = Math.max(32, Math.min(iconRenderSize, 128));
        int x = centerX - iconRenderSize / 2;
        int y = centerY - iconRenderSize;
        TextureManager textureManager = this.field_146297_k.func_110434_K();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ImageData data = ClientCacheHandler.getImageData(this.effect.icon);
        if (data.imageLoaded()) {
            data.bindTexture();
            int iconX = this.effect.iconX;
            int iconY = this.effect.iconY;
            int iconWidth = this.effect.getWidth();
            int iconHeight = this.effect.getHeight();
            int width = data.getTotalWidth();
            int height = data.getTotalHeight();
            GuiEffectDirectory.func_152125_a((int)x, (int)y, (float)iconX, (float)iconY, (int)iconWidth, (int)iconHeight, (int)iconRenderSize, (int)iconRenderSize, (float)width, (float)height);
        } else {
            textureManager.func_110577_a(new ResourceLocation("customnpcs", "textures/marks/question.png"));
            GuiEffectDirectory.func_152125_a((int)x, (int)y, (float)0.0f, (float)0.0f, (int)1, (int)1, (int)iconRenderSize, (int)iconRenderSize, (float)1.0f, (float)1.0f);
        }
        GL11.glDisable((int)2929);
        textureManager.func_110577_a(GuiCNPCInventory.specialIcons);
        GuiEffectDirectory.func_152125_a((int)x, (int)y, (float)0.0f, (float)224.0f, (int)16, (int)16, (int)iconRenderSize, (int)iconRenderSize, (float)256.0f, (float)256.0f);
        GL11.glEnable((int)2929);
    }

    @Override
    protected void drawItemDetails(int x, int y, int w) {
        if (this.effect == null || this.effect.id == -1) {
            return;
        }
        String drawString = this.effect.getMenuName();
        this.field_146289_q.func_85187_a(drawString, x, y, 0xFFFFFF, true);
        this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"gui.name") + ": " + this.effect.name, x, y += 14, 0xFFFFFF, false);
        this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"effect.runsEveryX") + ": " + this.effect.everyXTick + "t", x, y += 12, 0xB5B5B5, false);
        this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"effect.defaultLength") + ": " + this.effect.length + "s", x, y += 12, 0xB5B5B5, false);
    }
}

