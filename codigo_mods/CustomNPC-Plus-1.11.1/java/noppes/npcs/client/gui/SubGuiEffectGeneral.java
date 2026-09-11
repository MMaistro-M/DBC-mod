/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui;

import java.util.ArrayList;
import java.util.List;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.effects.EffectSavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.gui.SubGuiTagSelect;
import noppes.npcs.client.gui.player.inventory.GuiCNPCInventory;
import noppes.npcs.client.gui.script.GuiScriptInterface;
import noppes.npcs.client.gui.util.GuiMenuTopButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcButtonYesNo;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.GuiScrollWindow;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.client.renderer.AnimationHelper;
import noppes.npcs.client.renderer.ImageData;
import noppes.npcs.controllers.data.CustomEffect;
import noppes.npcs.controllers.data.EffectScript;
import org.lwjgl.opengl.GL11;

public class SubGuiEffectGeneral
extends SubGuiInterface
implements ITextfieldListener {
    private final GuiScreen parent;
    public CustomEffect effect;
    private final String originalName;
    private final List<GuiMenuTopButton> topButtons = new ArrayList<GuiMenuTopButton>();

    public SubGuiEffectGeneral(GuiScreen parent, CustomEffect effect) {
        this.effect = effect;
        this.parent = parent;
        this.originalName = effect.name;
        this.closeOnEsc = true;
        this.setBackground("menubg.png");
        this.xSize = 360;
        this.ySize = 216;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        GuiMenuTopButton close = new GuiMenuTopButton(-5, this.guiLeft + this.xSize - 22, this.guiTop - 10, "X");
        GuiMenuTopButton general = new GuiMenuTopButton(-1, this.guiLeft + 4, this.guiTop - 10, "menu.general");
        GuiMenuTopButton scripts = new GuiMenuTopButton(-2, general.field_146128_h + general.getWidth(), this.guiTop - 10, "script.scripts");
        close.active = false;
        general.active = true;
        scripts.active = false;
        this.addTopButton(close);
        this.addTopButton(general);
        this.addTopButton(scripts);
        this.guiTop += 7;
        int y = this.guiTop + 7;
        int x = this.guiLeft + 4 + 4;
        this.addTextField(new GuiNpcTextField(1, this, this.field_146289_q, x + 36, y, 210, 20, this.effect.name));
        this.getTextField(1).func_146203_f(40);
        this.addLabel(new GuiNpcLabel(1, "gui.name", x, y + 5));
        this.addLabel(new GuiNpcLabel(-1, "ID", x + 250, y + 1));
        this.addLabel(new GuiNpcLabel(-2, this.effect.id + "", x + 250, y + 11));
        this.addButton(new GuiNpcButton(40, this.getTextField((int)1).field_146209_f + this.getTextField((int)1).field_146218_h + 30, y, 70, 20, "gui.tags"));
        this.addTextField(new GuiNpcTextField(2, this, x + 70, y += 23, 246, 20, this.effect.menuName.replaceAll("\u00a7", "&")));
        this.getTextField(2).func_146203_f(40);
        this.addLabel(new GuiNpcLabel(2, "general.menuName", x, y + 5));
        this.addTextField(this.setIntegerOnly(new GuiNpcTextField(3, this, x + 70, y += 23, 83, 20, "" + this.effect.everyXTick), 10, 1200, this.effect.everyXTick));
        this.getTextField(3).func_146203_f(6);
        this.getTextField((int)3).integersOnly = true;
        this.addLabel(new GuiNpcLabel(3, "effect.editor.runsEveryX", x, y + 5));
        int oldX = x;
        int xEnd = this.guiLeft + this.xSize - 10;
        x = this.getTextField((int)3).field_146209_f + this.getTextField((int)3).field_146218_h;
        this.addButton(new GuiNpcButtonYesNo(10, (x + xEnd - 83) / 2, y + 23, 83, 20, this.effect.lossOnDeath));
        GuiNpcLabel label = new GuiNpcLabel(10, "effect.editor.lossOnDeath", x, y + 5);
        label.x = (x + xEnd - this.field_146289_q.func_78256_a(label.label)) / 2;
        this.addLabel(label);
        x = oldX;
        this.addTextField(this.setIntegerOnly(new GuiNpcTextField(4, this, x + 70, y += 23, 83, 20, "" + this.effect.length), -100, 86400, this.effect.length));
        this.addLabel(new GuiNpcLabel(4, "effect.editor.defaultLength", x, y + 5));
        x = oldX - 4;
        int scrollClipHeight = this.ySize - 10 - ((y += 23) - this.guiTop);
        GuiScrollWindow scrollWindow = new GuiScrollWindow(this, x + 5, y, this.xSize - 20, scrollClipHeight, 0){

            @Override
            public void drawComponents(int mouseX, int mouseY, float partialTicks) {
                super.drawComponents(mouseX, mouseY, partialTicks);
                int iconRenderSize = 96;
                int x = 10;
                int y = (this.clipHeight - 96) / 2;
                TextureManager textureManager = this.field_146297_k.func_110434_K();
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ImageData data = ClientCacheHandler.getImageData(SubGuiEffectGeneral.this.effect.icon);
                if (data.imageLoaded()) {
                    data.bindTexture();
                    int iconX = SubGuiEffectGeneral.this.effect.iconX;
                    int iconY = SubGuiEffectGeneral.this.effect.iconY;
                    int iconWidth = SubGuiEffectGeneral.this.effect.getWidth();
                    int iconHeight = SubGuiEffectGeneral.this.effect.getHeight();
                    int width = data.getTotalWidth();
                    int height = data.getTotalHeight();
                    if (data.isAnimated()) {
                        iconY += (int)(data.getCurrentFrameVOffset() * (float)height);
                    } else if (SubGuiEffectGeneral.this.effect.animated && SubGuiEffectGeneral.this.effect.frameCount > 1) {
                        iconY += (int)(AnimationHelper.getFrameVOffset(height, SubGuiEffectGeneral.this.effect.frameCount, SubGuiEffectGeneral.this.effect.frametime) * (float)height);
                    }
                    1.func_152125_a((int)x, (int)y, (float)iconX, (float)iconY, (int)iconWidth, (int)iconHeight, (int)iconRenderSize, (int)iconRenderSize, (float)width, (float)height);
                } else {
                    textureManager.func_110577_a(new ResourceLocation("customnpcs", "textures/marks/question.png"));
                    1.func_152125_a((int)x, (int)y, (float)0.0f, (float)0.0f, (int)1, (int)1, (int)iconRenderSize, (int)iconRenderSize, (float)1.0f, (float)1.0f);
                }
                GL11.glDisable((int)2929);
                textureManager.func_110577_a(GuiCNPCInventory.specialIcons);
                1.func_152125_a((int)x, (int)y, (float)0.0f, (float)224.0f, (int)16, (int)16, (int)iconRenderSize, (int)iconRenderSize, (float)256.0f, (float)256.0f);
                GL11.glEnable((int)2929);
            }
        };
        this.addScrollableGui(0, scrollWindow);
        y = (scrollWindow.clipHeight - 96) / 2 + 5;
        x = 116;
        scrollWindow.addLabel(new GuiNpcLabel(5, "display.texture", x, y, 0xFFFFFF));
        scrollWindow.addTextField(new GuiNpcTextField(5, this, x, y += 12, scrollWindow.clipWidth - x - 10, 20, this.effect.icon));
        scrollWindow.getTextField(5).func_146203_f(100);
        scrollWindow.addLabel(new GuiNpcLabel(6, "effect.editor.xPos", x, (y += 25) + 6, 0xFFFFFF));
        scrollWindow.addTextField(this.setIntegerOnly(new GuiNpcTextField(6, this, x + 43, y, 60, 20, "" + this.effect.iconX), 0, 10240, this.effect.iconX));
        scrollWindow.addTextField(this.setIntegerOnly(new GuiNpcTextField(7, this, scrollWindow.clipWidth - 60 - 10, y, 60, 20, "" + this.effect.iconY), 0, 10240, this.effect.iconY));
        scrollWindow.addLabel(new GuiNpcLabel(7, "effect.editor.yPos", scrollWindow.getTextField((int)7).field_146209_f - 43, y + 6, 0xFFFFFF));
        scrollWindow.addLabel(new GuiNpcLabel(8, "effect.editor.width", x, (y += 23) + 6, 0xFFFFFF));
        scrollWindow.addTextField(this.setIntegerOnly(new GuiNpcTextField(8, this, x + 43, y, 60, 20, "" + this.effect.width), 0, 10240, this.effect.width));
        scrollWindow.addTextField(this.setIntegerOnly(new GuiNpcTextField(9, this, scrollWindow.clipWidth - 60 - 10, y, 60, 20, "" + this.effect.height), 0, 10240, this.effect.height));
        scrollWindow.addLabel(new GuiNpcLabel(9, "effect.editor.height", scrollWindow.getTextField((int)9).field_146209_f - 43, y + 6, 0xFFFFFF));
        scrollWindow.addLabel(new GuiNpcLabel(11, "gui.animated", x, (y += 25) + 5, 0xFFFFFF));
        GuiNpcButtonYesNo animBtn = new GuiNpcButtonYesNo(11, x + 70, y, 50, 20, this.effect.animated);
        animBtn.setHoverText("gui.animated.hover");
        scrollWindow.addButton(animBtn);
        if (this.effect.animated) {
            scrollWindow.addLabel(new GuiNpcLabel(12, "gui.frameCount", x, (y += 23) + 6, 0xFFFFFF));
            scrollWindow.addTextField(this.setIntegerOnly(new GuiNpcTextField(12, this, x + 80, y, 60, 20, "" + this.effect.frameCount), 1, 256, this.effect.frameCount));
            scrollWindow.addLabel(new GuiNpcLabel(13, "gui.frameTime", x, (y += 23) + 6, 0xFFFFFF));
            scrollWindow.addTextField(this.setIntegerOnly(new GuiNpcTextField(13, this, x + 80, y, 60, 20, "" + this.effect.frametime), 1, 100, this.effect.frametime));
        }
        int contentBottom = y + 20 + 5;
        scrollWindow.maxScrollY = Math.max(0, contentBottom - scrollClipHeight);
    }

    private GuiNpcTextField setIntegerOnly(GuiNpcTextField field, int min, int max, int def) {
        field.integersOnly = true;
        field.setMinMaxDefault(min, max, def);
        field.func_146203_f(6);
        return field;
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButtonYesNo button;
        int id = guibutton.field_146127_k;
        if (id == -5) {
            this.close();
            return;
        }
        if (id == -2) {
            PacketClient.sendClient(new EffectSavePacket(this.effect.writeToNBT(false), this.originalName));
            GuiScriptInterface.open(this.parent, new EffectScript(this.effect.id));
        }
        if (id == 40) {
            this.setSubGui(new SubGuiTagSelect(this.effect.tagUUIDs));
            return;
        }
        if (id == 10) {
            button = (GuiNpcButtonYesNo)guibutton;
            this.effect.lossOnDeath = button.getBoolean();
        }
        if (id == 11) {
            button = (GuiNpcButtonYesNo)guibutton;
            this.effect.animated = button.getBoolean();
            this.func_73866_w_();
        }
    }

    @Override
    public void unFocused(GuiNpcTextField guiNpcTextField) {
        int id = guiNpcTextField.id;
        switch (id) {
            case 1: {
                this.effect.setName(guiNpcTextField.func_146179_b());
                break;
            }
            case 2: {
                this.effect.setMenuName(guiNpcTextField.func_146179_b());
                break;
            }
            case 3: {
                this.effect.setEveryXTick(guiNpcTextField.getInteger());
                guiNpcTextField.func_146180_a(this.effect.everyXTick + "");
                break;
            }
            case 4: {
                int length = guiNpcTextField.getInteger();
                if (length < 0) {
                    length = -100;
                }
                guiNpcTextField.func_146180_a(length + "");
                this.effect.length = length;
                break;
            }
            case 5: {
                this.effect.icon = guiNpcTextField.func_146179_b();
                break;
            }
            case 6: {
                this.effect.iconX = guiNpcTextField.getInteger();
                break;
            }
            case 7: {
                this.effect.iconY = guiNpcTextField.getInteger();
                break;
            }
            case 8: {
                this.effect.width = guiNpcTextField.getInteger();
                break;
            }
            case 9: {
                this.effect.height = guiNpcTextField.getInteger();
                break;
            }
            case 12: {
                this.effect.frameCount = Math.max(1, guiNpcTextField.getInteger());
                break;
            }
            case 13: {
                this.effect.frametime = Math.max(1, guiNpcTextField.getInteger());
            }
        }
    }

    @Override
    public void close() {
        super.close();
        PacketClient.sendClient(new EffectSavePacket(this.effect.writeToNBT(false), this.originalName));
    }
}

