/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
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
import kamkeel.npcs.network.packets.request.linked.LinkedGetPacket;
import kamkeel.npcs.network.packets.request.linked.LinkedItemBuildPacket;
import kamkeel.npcs.network.packets.request.linked.LinkedItemClonePacket;
import kamkeel.npcs.network.packets.request.linked.LinkedItemRemovePacket;
import kamkeel.npcs.network.packets.request.linked.LinkedItemSavePacket;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.gui.global.GuiNPCManageLinked;
import noppes.npcs.client.gui.item.SubGuiLinkedItem;
import noppes.npcs.client.gui.util.GuiDirectoryCategorized;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.client.renderer.ImageData;
import noppes.npcs.controllers.data.Category;
import noppes.npcs.controllers.data.LinkedItem;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public class GuiLinkedItemDirectory
extends GuiDirectoryCategorized {
    public LinkedItem linkedItem = null;
    public EntityNPCInterface npc;

    public GuiLinkedItemDirectory(EntityNPCInterface npc) {
        this.npc = npc;
    }

    @Override
    protected String getTitle() {
        return "Linked Items";
    }

    @Override
    protected void requestCategoryList() {
        PacketClient.sendClient(new CategoryListRequestPacket(3));
    }

    @Override
    protected void requestItemsInCategory(int catId) {
        PacketClient.sendClient(new CategoryItemsRequestPacket(3, catId));
    }

    @Override
    protected void requestItemData(int itemId) {
        LinkedGetPacket.GetItem(itemId);
    }

    @Override
    protected void onSaveCategory(Category cat) {
        PacketClient.sendClient(new CategorySavePacket(3, cat.writeNBT(new NBTTagCompound())));
    }

    @Override
    protected void onRemoveCategory(int catId) {
        PacketClient.sendClient(new CategoryRemovePacket(3, catId));
    }

    @Override
    protected void onAddItem(int catId) {
        String name = "New";
        while (this.itemData.containsKey(name)) {
            name = name + "_";
        }
        LinkedItem newItem = new LinkedItem(name);
        PacketClient.sendClient(new LinkedItemSavePacket(newItem.writeToNBT(false), ""));
    }

    @Override
    protected void onRemoveItem(int itemId) {
        PacketClient.sendClient(new LinkedItemRemovePacket(itemId));
        this.linkedItem = null;
    }

    @Override
    protected void onEditItem() {
        if (this.linkedItem != null && this.linkedItem.id >= 0) {
            this.setSubGui(new SubGuiLinkedItem(this, this.linkedItem));
        }
    }

    @Override
    protected void onCloneItem() {
        if (this.linkedItem != null && this.linkedItem.id >= 0) {
            PacketClient.sendClient(new LinkedItemClonePacket(this.linkedItem.id));
        }
    }

    @Override
    protected void onItemReceived(NBTTagCompound compound) {
        this.linkedItem = new LinkedItem();
        this.linkedItem.readFromNBT(compound);
        this.setPrevItemName(this.linkedItem.name);
    }

    @Override
    protected boolean hasSelectedItem() {
        return this.linkedItem != null && this.linkedItem.id >= 0;
    }

    @Override
    protected int getSelectedItemId() {
        return this.linkedItem != null ? this.linkedItem.id : -1;
    }

    @Override
    protected void sendMovePacket(int itemId, int destCatId) {
        PacketClient.sendClient(new CategoryMoveItemPacket(3, itemId, destCatId));
    }

    @Override
    protected GuiScreen getWindowedVariant() {
        return new GuiNPCManageLinked(this.npc);
    }

    @Override
    protected void saveCurrentItem() {
        if (this.linkedItem != null && this.linkedItem.id >= 0 && this.prevItemName != null && !this.prevItemName.isEmpty()) {
            PacketClient.sendClient(new LinkedItemSavePacket(this.linkedItem.writeToNBT(false), this.prevItemName));
            this.prevItemName = this.linkedItem.name;
        }
    }

    @Override
    protected void onSubGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiLinkedItem && this.linkedItem != null && this.linkedItem.id >= 0) {
            this.setPrevItemName(this.linkedItem.name);
            if (this.selectedCatId >= 0) {
                this.requestItemsInCategory(this.selectedCatId);
            }
        }
    }

    @Override
    protected void initRightPanel(int startY) {
        int bottomH = (this.btnH + this.gap) * 2 + 14;
        this.previewX = this.rightX;
        this.previewY = this.contentY;
        this.previewW = this.rightPanelW;
        this.previewH = this.contentH - bottomH - this.gap;
        int halfW = (this.rightPanelW - this.gap) / 2;
        int row1Y = this.contentY + this.contentH - this.btnH * 2 - this.gap;
        GuiNpcButton editBtn = new GuiNpcButton(51, this.rightX, row1Y, halfW, this.btnH, "gui.edit");
        editBtn.field_146124_l = this.hasSelectedItem() && this.movePhase == 0;
        this.addButton(editBtn);
        GuiNpcButton buildBtn = new GuiNpcButton(56, this.rightX + halfW + this.gap, row1Y, halfW, this.btnH, "gui.build");
        buildBtn.field_146124_l = this.hasSelectedItem() && this.movePhase == 0;
        this.addButton(buildBtn);
        int row2Y = row1Y + this.btnH + this.gap;
        GuiNpcButton cloneBtn = new GuiNpcButton(52, this.rightX, row2Y, halfW, this.btnH, "gui.copy");
        cloneBtn.field_146124_l = this.hasSelectedItem() && this.movePhase == 0;
        this.addButton(cloneBtn);
        GuiNpcButton removeBtn = new GuiNpcButton(53, this.rightX + halfW + this.gap, row2Y, halfW, this.btnH, "gui.remove");
        removeBtn.field_146124_l = this.hasSelectedItem() && this.movePhase == 0;
        removeBtn.setTextColor(0xFF5555);
        this.addButton(removeBtn);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton.field_146127_k == 56 && this.linkedItem != null && this.linkedItem.id >= 0) {
            PacketClient.sendClient(new LinkedItemBuildPacket(this.linkedItem.getId()));
            return;
        }
        super.func_146284_a(guibutton);
    }

    @Override
    protected void drawItemPreview(int centerX, int centerY, int mouseX, int mouseY, float partialTicks) {
        if (this.linkedItem == null || this.linkedItem.id < 0) {
            return;
        }
        int iconRenderSize = Math.min(this.previewW, this.previewH) / 3;
        iconRenderSize = Math.max(32, Math.min(iconRenderSize, 128));
        int x = centerX - iconRenderSize / 2;
        int y = centerY - iconRenderSize;
        TextureManager textureManager = this.field_146297_k.func_110434_K();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ImageData imageData = ClientCacheHandler.getImageData(this.linkedItem.display.texture);
        if (imageData.imageLoaded()) {
            float[] colors = ColorUtil.hexToRGB(this.linkedItem.display.itemColor);
            GL11.glColor3f((float)colors[0], (float)colors[1], (float)colors[2]);
            imageData.bindTexture();
            int iconWidth = imageData.getTotalWidth();
            int iconHeight = imageData.getTotalHeight();
            GuiLinkedItemDirectory.func_152125_a((int)x, (int)y, (float)0.0f, (float)0.0f, (int)iconWidth, (int)iconHeight, (int)iconRenderSize, (int)iconRenderSize, (float)iconWidth, (float)iconHeight);
        } else {
            textureManager.func_110577_a(new ResourceLocation("customnpcs", "textures/marks/question.png"));
            GuiLinkedItemDirectory.func_152125_a((int)x, (int)y, (float)0.0f, (float)0.0f, (int)1, (int)1, (int)iconRenderSize, (int)iconRenderSize, (float)1.0f, (float)1.0f);
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
    }

    @Override
    protected void drawItemDetails(int x, int y, int w) {
        int useActionIndex;
        if (this.linkedItem == null || this.linkedItem.id < 0) {
            return;
        }
        this.field_146289_q.func_85187_a(this.linkedItem.name, x, y, 0xFFFFFF, true);
        int labelColor = 16756237;
        this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"display.version") + ": " + this.linkedItem.version, x, y += 14, labelColor, false);
        labelColor = 16733972;
        this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"display.maxStack") + ": " + this.linkedItem.stackSize, x, y += 12, labelColor, false);
        labelColor = 16239144;
        this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"display.digSpeed") + ": " + this.linkedItem.digSpeed, x, y += 12, labelColor, false);
        y += 12;
        labelColor = 2741945;
        String[] useActions = new String[]{StatCollector.func_74838_a((String)"use_action.none"), StatCollector.func_74838_a((String)"use_action.block"), StatCollector.func_74838_a((String)"use_action.eat"), StatCollector.func_74838_a((String)"use_action.drink"), StatCollector.func_74838_a((String)"use_action.bow")};
        switch (this.linkedItem.itemUseAction) {
            case 0: {
                useActionIndex = 0;
                break;
            }
            case 1: {
                useActionIndex = 1;
                break;
            }
            case 2: {
                useActionIndex = 4;
                break;
            }
            case 3: {
                useActionIndex = 2;
                break;
            }
            case 4: {
                useActionIndex = 3;
                break;
            }
            default: {
                useActionIndex = 0;
            }
        }
        this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"display.useAction") + ": " + useActions[useActionIndex], x, y, labelColor, false);
        String[] armorOptions = new String[]{StatCollector.func_74838_a((String)"armor_type.none"), StatCollector.func_74838_a((String)"armor_type.all"), StatCollector.func_74838_a((String)"armor_type.head"), StatCollector.func_74838_a((String)"armor_type.chestplate"), StatCollector.func_74838_a((String)"armor_type.leggings"), StatCollector.func_74838_a((String)"armor_type.boots")};
        int armorIndex = this.linkedItem.armorType == -2 ? 0 : (this.linkedItem.armorType == -1 ? 1 : this.linkedItem.armorType + 2);
        this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"display.armor") + ": " + armorOptions[armorIndex], x, y += 12, labelColor, false);
        labelColor = 8191828;
        this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"display.isTool") + ": " + ("" + this.linkedItem.isTool).toUpperCase(), x, y += 12, labelColor, false);
        this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"display.isNormalItem") + ": " + ("" + this.linkedItem.isNormalItem).toUpperCase(), x, y += 12, labelColor, false);
        labelColor = 13530618;
        this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"model.scale") + ": " + this.linkedItem.display.scaleX + ", " + this.linkedItem.display.scaleY + ", " + this.linkedItem.display.scaleZ, x, y += 12, labelColor, false);
        this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"model.rotate") + ": " + this.linkedItem.display.rotationX + ", " + this.linkedItem.display.rotationY + ", " + this.linkedItem.display.rotationZ, x, y += 12, labelColor, false);
        this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"model.translate") + ": " + this.linkedItem.display.translateX + ", " + this.linkedItem.display.translateY + ", " + this.linkedItem.display.translateZ, x, y += 12, labelColor, false);
    }
}

