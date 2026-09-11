/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiYesNo
 *  net.minecraft.client.gui.GuiYesNoCallback
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.item;

import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.linked.LinkedItemSavePacket;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.gui.SubGuiColorSelector;
import noppes.npcs.client.gui.SubGuiTagSelect;
import noppes.npcs.client.gui.script.GuiScriptInterface;
import noppes.npcs.client.gui.util.GuiButtonBiDirectional;
import noppes.npcs.client.gui.util.GuiMenuTopButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcButtonYesNo;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.GuiScrollWindow;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.client.renderer.AnimationHelper;
import noppes.npcs.client.renderer.ImageData;
import noppes.npcs.controllers.data.LinkedItem;
import noppes.npcs.controllers.data.LinkedItemScript;
import org.lwjgl.opengl.GL11;

public class SubGuiLinkedItem
extends SubGuiInterface
implements ITextfieldListener,
GuiYesNoCallback,
ISubGuiListener {
    public LinkedItem linkedItem;
    private final String originalName;
    private int tab = -1;
    private int colorPicked = 0;

    public SubGuiLinkedItem(GuiScreen parent, LinkedItem linkedItem) {
        this.linkedItem = linkedItem;
        this.parent = parent;
        this.originalName = linkedItem.name;
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
        GuiMenuTopButton advanced = new GuiMenuTopButton(-2, general.field_146128_h + general.getWidth(), this.guiTop - 10, "menu.advanced");
        GuiMenuTopButton scripts = new GuiMenuTopButton(-3, advanced.field_146128_h + advanced.getWidth(), this.guiTop - 10, "script.scripts");
        general.active = this.tab == -1 || this.tab == 0;
        advanced.active = this.tab == -2;
        scripts.active = this.tab == -3;
        close.active = this.tab == -5;
        this.addTopButton(close);
        this.addTopButton(advanced);
        this.addTopButton(general);
        this.addTopButton(scripts);
        this.guiTop += 7;
        if (general.active) {
            this.addGeneralComponents();
        } else if (advanced.active) {
            this.addAdvancedComponents();
        }
    }

    private void addGeneralComponents() {
        int x = this.guiLeft + 10;
        int y = this.guiTop + 8;
        int spacing = 25;
        this.addLabel(new GuiNpcLabel(1, "gui.name", x, y + 5, CustomNpcResourceListener.DefaultTextColor));
        this.addTextField(new GuiNpcTextField(1, this, x + 60, y, 180, 20, this.linkedItem.name));
        this.addLabel(new GuiNpcLabel(100, "gui.id", x + 250, y + 1, CustomNpcResourceListener.DefaultTextColor));
        this.addLabel(new GuiNpcLabel(101, this.linkedItem.id + "", x + 250, y + 11, CustomNpcResourceListener.DefaultTextColor));
        this.addLabel(new GuiNpcLabel(2, "display.version", x, (y += spacing) + 5, CustomNpcResourceListener.DefaultTextColor));
        GuiNpcButton versionButton = new GuiNpcButton(20, x + 100, y, 80, 20, "gui.update");
        versionButton.setHoverText("display.versionInfo");
        this.addButton(versionButton);
        this.addLabel(new GuiNpcLabel(200, "display.version", x + 190, y + 1, CustomNpcResourceListener.DefaultTextColor));
        this.addLabel(new GuiNpcLabel(201, this.linkedItem.version + "", x + 190, y + 11, CustomNpcResourceListener.DefaultTextColor));
        this.addLabel(new GuiNpcLabel(4, "display.maxStack", x, (y += spacing) + 5, CustomNpcResourceListener.DefaultTextColor));
        GuiNpcTextField stackField = new GuiNpcTextField(3, this, x + 110, y, 50, 20, "" + this.linkedItem.stackSize);
        stackField.setIntegersOnly().setMinMaxDefault(1, 64, 64);
        this.addTextField(stackField);
        this.addLabel(new GuiNpcLabel(9, "display.enchantability", x, (y += spacing) + 5, CustomNpcResourceListener.DefaultTextColor));
        GuiNpcTextField enchantField = new GuiNpcTextField(10, this, x + 110, y, 50, 20, "" + this.linkedItem.enchantability);
        enchantField.setIntegersOnly().setMinMaxDefault(0, 30, 0);
        this.addTextField(enchantField);
        this.addLabel(new GuiNpcLabel(11, "Dig & Attack Speed", x, (y += spacing) + 5, CustomNpcResourceListener.DefaultTextColor));
        GuiNpcTextField digSpeedField = new GuiNpcTextField(12, this, x + 110, y, 50, 20, "" + this.linkedItem.digSpeed);
        digSpeedField.setIntegersOnly().setMinMaxDefault(0, 20, 1);
        this.addTextField(digSpeedField);
        GuiNpcTextField attackSpeedField = new GuiNpcTextField(14, this, x + 165, y, 50, 20, "" + this.linkedItem.attackSpeed);
        attackSpeedField.setIntegersOnly().setMinMaxDefault(0, 999999999, 20);
        this.addTextField(attackSpeedField);
        this.addLabel(new GuiNpcLabel(3, "display.texture", x, (y += spacing) + 5, CustomNpcResourceListener.DefaultTextColor));
        this.addTextField(new GuiNpcTextField(2, this, x + 60, y, 200, 20, this.linkedItem.display.texture));
        this.addLabel(new GuiNpcLabel(5, "display.useAction", x, (y += spacing) + 5, CustomNpcResourceListener.DefaultTextColor));
        String[] useActions = new String[]{"use_action.none", "use_action.block", "use_action.eat", "use_action.drink", "use_action.bow"};
        int useActionIndex = 0;
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
        GuiButtonBiDirectional useActionButton = new GuiButtonBiDirectional(6, x + 120, y, 100, 20, useActions, useActionIndex);
        this.addButton(useActionButton);
        this.addButton(new GuiNpcButton(40, x + 230, y, 100, 20, "gui.tags"));
        this.addLabel(new GuiNpcLabel(7, "display.armor", x, (y += spacing) + 5, CustomNpcResourceListener.DefaultTextColor));
        String[] armorOptions = new String[]{"armor_type.none", "armor_type.all", "armor_type.head", "armor_type.chestplate", "armor_type.leggings", "armor_type.boots"};
        int armorIndex = this.linkedItem.armorType == -2 ? 0 : (this.linkedItem.armorType == -1 ? 1 : this.linkedItem.armorType + 2);
        GuiButtonBiDirectional armorButton = new GuiButtonBiDirectional(8, x + 120, y, 100, 20, armorOptions, armorIndex);
        this.addButton(armorButton);
        y += spacing;
    }

    private void addAdvancedComponents() {
        int x = 5;
        int y = 5;
        int clipWidth = this.xSize - 20;
        int clipHeight = this.ySize - 20;
        GuiScrollWindow scrollWindow = new GuiScrollWindow(this, this.guiLeft + 10, this.guiTop + 10, clipWidth, clipHeight, 0);
        this.addScrollableGui(0, scrollWindow);
        int localY = 5;
        scrollWindow.addLabel(new GuiNpcLabel(20, "gui.color", x, localY + 5, 0xFFFFFF));
        String colorHex = Integer.toHexString(this.linkedItem.display.itemColor);
        while (colorHex.length() < 6) {
            colorHex = "0" + colorHex;
        }
        GuiNpcButton colorPickerButton = new GuiNpcButton(24, x + 80, localY, 80, 20, colorHex);
        colorPickerButton.packedFGColour = this.linkedItem.display.itemColor;
        scrollWindow.addButton(colorPickerButton);
        GuiNpcButton clearColorButton = new GuiNpcButton(25, x + 170, localY, 60, 20, "gui.clear");
        scrollWindow.addButton(clearColorButton);
        scrollWindow.addLabel(new GuiNpcLabel(27, "display.isTool", x, (localY += 30) + 5, 0xFFFFFF));
        GuiNpcButtonYesNo toolButton = new GuiNpcButtonYesNo(27, x + 120, localY, this.linkedItem.isTool);
        toolButton.setHoverText("display.isToolInfo");
        scrollWindow.addButton(toolButton);
        scrollWindow.addLabel(new GuiNpcLabel(28, "display.isNormalItem", x, (localY += 25) + 5, 0xFFFFFF));
        GuiNpcButtonYesNo normalButton = new GuiNpcButtonYesNo(28, x + 120, localY, this.linkedItem.isNormalItem);
        normalButton.setHoverText("display.isNormalItemInfo");
        scrollWindow.addButton(normalButton);
        scrollWindow.addLabel(new GuiNpcLabel(31, "model.scale", x, (localY += 25) + 5, 0xFFFFFF));
        scrollWindow.addLabel(new GuiNpcLabel(32, "X", x + 80, localY + 5, 0xFFFFFF));
        GuiNpcTextField scaleXField = new GuiNpcTextField(31, this, x + 90, localY, 40, 20, this.linkedItem.display.scaleX + "");
        scaleXField.setFloatsOnly().setMinMaxDefaultFloat(0.0f, 30.0f, 1.0f);
        scrollWindow.addTextField(scaleXField);
        scrollWindow.addLabel(new GuiNpcLabel(33, "Y", x + 140, localY + 5, 0xFFFFFF));
        GuiNpcTextField scaleYField = new GuiNpcTextField(32, this, x + 150, localY, 40, 20, this.linkedItem.display.scaleY + "");
        scaleYField.setFloatsOnly().setMinMaxDefaultFloat(0.0f, 30.0f, 1.0f);
        scrollWindow.addTextField(scaleYField);
        scrollWindow.addLabel(new GuiNpcLabel(34, "Z", x + 200, localY + 5, 0xFFFFFF));
        GuiNpcTextField scaleZField = new GuiNpcTextField(33, this, x + 210, localY, 40, 20, this.linkedItem.display.scaleZ + "");
        scaleZField.setFloatsOnly().setMinMaxDefaultFloat(0.0f, 30.0f, 1.0f);
        scrollWindow.addTextField(scaleZField);
        scrollWindow.addLabel(new GuiNpcLabel(41, "model.rotate", x, (localY += 30) + 5, 0xFFFFFF));
        scrollWindow.addLabel(new GuiNpcLabel(42, "X", x + 80, localY + 5, 0xFFFFFF));
        GuiNpcTextField rotXField = new GuiNpcTextField(41, this, x + 90, localY, 40, 20, this.linkedItem.display.rotationX + "");
        rotXField.setFloatsOnly().setMinMaxDefaultFloat(-360.0f, 360.0f, 0.0f);
        scrollWindow.addTextField(rotXField);
        scrollWindow.addLabel(new GuiNpcLabel(43, "Y", x + 140, localY + 5, 0xFFFFFF));
        GuiNpcTextField rotYField = new GuiNpcTextField(42, this, x + 150, localY, 40, 20, this.linkedItem.display.rotationY + "");
        rotYField.setFloatsOnly().setMinMaxDefaultFloat(-360.0f, 360.0f, 0.0f);
        scrollWindow.addTextField(rotYField);
        scrollWindow.addLabel(new GuiNpcLabel(44, "Z", x + 200, localY + 5, 0xFFFFFF));
        GuiNpcTextField rotZField = new GuiNpcTextField(43, this, x + 210, localY, 40, 20, this.linkedItem.display.rotationZ + "");
        rotZField.setFloatsOnly().setMinMaxDefaultFloat(-360.0f, 360.0f, 0.0f);
        scrollWindow.addTextField(rotZField);
        scrollWindow.addLabel(new GuiNpcLabel(51, "model.rotationRate", x, (localY += 30) + 5, 0xFFFFFF));
        scrollWindow.addLabel(new GuiNpcLabel(52, "X", x + 80, localY + 5, 0xFFFFFF));
        GuiNpcTextField rotXRateField = new GuiNpcTextField(51, this, x + 90, localY, 40, 20, this.linkedItem.display.rotationXRate + "");
        rotXRateField.setFloatsOnly().setMinMaxDefaultFloat(0.0f, 100.0f, 0.0f);
        scrollWindow.addTextField(rotXRateField);
        scrollWindow.addLabel(new GuiNpcLabel(53, "Y", x + 140, localY + 5, 0xFFFFFF));
        GuiNpcTextField rotYRateField = new GuiNpcTextField(52, this, x + 150, localY, 40, 20, this.linkedItem.display.rotationYRate + "");
        rotYRateField.setFloatsOnly().setMinMaxDefaultFloat(0.0f, 100.0f, 0.0f);
        scrollWindow.addTextField(rotYRateField);
        scrollWindow.addLabel(new GuiNpcLabel(54, "Z", x + 200, localY + 5, 0xFFFFFF));
        GuiNpcTextField rotZRateField = new GuiNpcTextField(53, this, x + 210, localY, 40, 20, this.linkedItem.display.rotationZRate + "");
        rotZRateField.setFloatsOnly().setMinMaxDefaultFloat(0.0f, 100.0f, 0.0f);
        scrollWindow.addTextField(rotZRateField);
        scrollWindow.addLabel(new GuiNpcLabel(61, "model.translate", x, (localY += 30) + 5, 0xFFFFFF));
        scrollWindow.addLabel(new GuiNpcLabel(62, "X", x + 80, localY + 5, 0xFFFFFF));
        GuiNpcTextField transXField = new GuiNpcTextField(61, this, x + 90, localY, 40, 20, this.linkedItem.display.translateX + "");
        transXField.setFloatsOnly().setMinMaxDefaultFloat(-10.0f, 10.0f, 0.0f);
        scrollWindow.addTextField(transXField);
        scrollWindow.addLabel(new GuiNpcLabel(63, "Y", x + 140, localY + 5, 0xFFFFFF));
        GuiNpcTextField transYField = new GuiNpcTextField(62, this, x + 150, localY, 40, 20, this.linkedItem.display.translateY + "");
        transYField.setFloatsOnly().setMinMaxDefaultFloat(-10.0f, 10.0f, 0.0f);
        scrollWindow.addTextField(transYField);
        scrollWindow.addLabel(new GuiNpcLabel(64, "Z", x + 200, localY + 5, 0xFFFFFF));
        GuiNpcTextField transZField = new GuiNpcTextField(63, this, x + 210, localY, 40, 20, this.linkedItem.display.translateZ + "");
        transZField.setFloatsOnly().setMinMaxDefaultFloat(-10.0f, 10.0f, 0.0f);
        scrollWindow.addTextField(transZField);
        scrollWindow.addLabel(new GuiNpcLabel(70, "display.durabilityShow", x, (localY += 30) + 5, 0xFFFFFF));
        GuiNpcButtonYesNo durabilityButton = new GuiNpcButtonYesNo(26, x + 120, localY, this.linkedItem.display.durabilityShow);
        scrollWindow.addButton(durabilityButton);
        scrollWindow.addLabel(new GuiNpcLabel(80, "gui.animated", x, (localY += 30) + 5, 0xFFFFFF));
        boolean isAnim = this.linkedItem.display.animated != null && this.linkedItem.display.animated != false;
        GuiNpcButtonYesNo animBtn = new GuiNpcButtonYesNo(29, x + 120, localY, isAnim);
        animBtn.setHoverText("gui.animated.hover");
        scrollWindow.addButton(animBtn);
        localY += 25;
        if (isAnim) {
            scrollWindow.addLabel(new GuiNpcLabel(81, "gui.frameCount", x, localY + 5, 0xFFFFFF));
            GuiNpcTextField fcField = new GuiNpcTextField(71, this, x + 120, localY, 60, 20, "" + (this.linkedItem.display.frameCount != null ? this.linkedItem.display.frameCount : 1));
            fcField.setIntegersOnly().setMinMaxDefault(1, 256, 1);
            scrollWindow.addTextField(fcField);
            scrollWindow.addLabel(new GuiNpcLabel(82, "gui.frameTime", x, (localY += 25) + 5, 0xFFFFFF));
            GuiNpcTextField ftField = new GuiNpcTextField(72, this, x + 120, localY, 60, 20, "" + (this.linkedItem.display.frametime != null ? this.linkedItem.display.frametime : 2));
            ftField.setIntegersOnly().setMinMaxDefault(1, 100, 2);
            scrollWindow.addTextField(ftField);
            localY += 25;
        }
        scrollWindow.scrollY = 0.0f;
        scrollWindow.maxScrollY = Math.max(localY - scrollWindow.clipHeight, 0);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == -5) {
            this.close();
            return;
        }
        if (id == -1 && this.tab != -1) {
            this.tab = -1;
            this.func_73866_w_();
            return;
        }
        if (id == -2 && this.tab != -2) {
            this.tab = -2;
            this.func_73866_w_();
            return;
        }
        if (id == -3) {
            PacketClient.sendClient(new LinkedItemSavePacket(this.linkedItem.writeToNBT(false), this.originalName));
            GuiScriptInterface.open(this.parent, new LinkedItemScript(this.linkedItem.id));
            return;
        }
        if (id == 40) {
            this.setSubGui(new SubGuiTagSelect(this.linkedItem.tagUUIDs));
            return;
        }
        if (id == 20) {
            GuiYesNo guiyesno = new GuiYesNo((GuiYesNoCallback)this, "Confirm", "Bump version and update all linked items?", 20);
            this.displayGuiScreen((GuiScreen)guiyesno);
            return;
        }
        if (id == 24) {
            this.setSubGui(new SubGuiColorSelector(this.linkedItem.display.itemColor));
            this.colorPicked = 1;
            return;
        }
        if (id == 25) {
            GuiNpcButton npcButton;
            this.linkedItem.display.itemColor = 0xFFFFFF;
            GuiScrollWindow scrollWindow = this.getScrollableGui(0);
            if (scrollWindow != null && (npcButton = scrollWindow.getButton(24)) != null) {
                npcButton.field_146126_j = "ffffff";
                npcButton.packedFGColour = 0xFFFFFF;
            }
        } else if (id == 6) {
            int index = ((GuiButtonBiDirectional)guibutton).getValue();
            switch (index) {
                case 0: {
                    this.linkedItem.itemUseAction = 0;
                    break;
                }
                case 1: {
                    this.linkedItem.itemUseAction = 1;
                    break;
                }
                case 2: {
                    this.linkedItem.itemUseAction = 3;
                    break;
                }
                case 3: {
                    this.linkedItem.itemUseAction = 4;
                    break;
                }
                case 4: {
                    this.linkedItem.itemUseAction = 2;
                }
            }
        } else if (id == 8) {
            int index = ((GuiButtonBiDirectional)guibutton).getValue();
            switch (index) {
                case 0: {
                    this.linkedItem.armorType = -2;
                    break;
                }
                case 1: {
                    this.linkedItem.armorType = -1;
                    break;
                }
                case 2: {
                    this.linkedItem.armorType = 0;
                    break;
                }
                case 3: {
                    this.linkedItem.armorType = 1;
                    break;
                }
                case 4: {
                    this.linkedItem.armorType = 2;
                    break;
                }
                case 5: {
                    this.linkedItem.armorType = 3;
                }
            }
        } else if (id == 27 && guibutton instanceof GuiNpcButtonYesNo) {
            this.linkedItem.isTool = ((GuiNpcButtonYesNo)guibutton).getBoolean();
        } else if (id == 28 && guibutton instanceof GuiNpcButtonYesNo) {
            this.linkedItem.isNormalItem = ((GuiNpcButtonYesNo)guibutton).getBoolean();
        } else if (id == 26 && guibutton instanceof GuiNpcButtonYesNo) {
            this.linkedItem.display.durabilityShow = ((GuiNpcButtonYesNo)guibutton).getBoolean();
        } else if (id == 29 && guibutton instanceof GuiNpcButtonYesNo) {
            this.linkedItem.display.animated = ((GuiNpcButtonYesNo)guibutton).getBoolean();
            this.func_73866_w_();
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textField) {
        int id = textField.id;
        if (id == 1) {
            this.linkedItem.name = textField.func_146179_b();
        } else if (id == 2) {
            this.linkedItem.display.texture = textField.func_146179_b();
        } else if (id == 3) {
            this.linkedItem.stackSize = textField.getInteger();
        } else if (id == 10) {
            this.linkedItem.enchantability = textField.getInteger();
        } else if (id == 12) {
            this.linkedItem.digSpeed = textField.getInteger();
        } else if (id == 14) {
            this.linkedItem.attackSpeed = textField.getInteger();
        } else if (id == 31) {
            this.linkedItem.display.scaleX = Float.valueOf(textField.getFloat());
        } else if (id == 32) {
            this.linkedItem.display.scaleY = Float.valueOf(textField.getFloat());
        } else if (id == 33) {
            this.linkedItem.display.scaleZ = Float.valueOf(textField.getFloat());
        } else if (id == 41) {
            this.linkedItem.display.rotationX = Float.valueOf(textField.getFloat());
        } else if (id == 42) {
            this.linkedItem.display.rotationY = Float.valueOf(textField.getFloat());
        } else if (id == 43) {
            this.linkedItem.display.rotationZ = Float.valueOf(textField.getFloat());
        } else if (id == 51) {
            this.linkedItem.display.rotationXRate = Float.valueOf(textField.getFloat());
        } else if (id == 52) {
            this.linkedItem.display.rotationYRate = Float.valueOf(textField.getFloat());
        } else if (id == 53) {
            this.linkedItem.display.rotationZRate = Float.valueOf(textField.getFloat());
        } else if (id == 61) {
            this.linkedItem.display.translateX = Float.valueOf(textField.getFloat());
        } else if (id == 62) {
            this.linkedItem.display.translateY = Float.valueOf(textField.getFloat());
        } else if (id == 63) {
            this.linkedItem.display.translateZ = Float.valueOf(textField.getFloat());
        } else if (id == 71) {
            this.linkedItem.display.frameCount = Math.max(1, textField.getInteger());
        } else if (id == 72) {
            this.linkedItem.display.frametime = Math.max(1, textField.getInteger());
        }
    }

    public void func_73878_a(boolean flag, int id) {
        if (id == 20 && flag) {
            ++this.linkedItem.version;
            this.func_73866_w_();
        }
        this.field_146297_k.field_71462_r = this.parent;
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        super.func_73863_a(i, j, f);
    }

    @Override
    public void drawBackground() {
        super.drawBackground();
        this.renderScreen();
    }

    private void renderScreen() {
        if (this.tab != -1) {
            return;
        }
        int y = this.guiTop + 36;
        int x = this.guiLeft + 250;
        int iconRenderSize = 86;
        GL11.glPushAttrib((int)1048575);
        TextureManager textureManager = this.field_146297_k.func_110434_K();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ImageData data = ClientCacheHandler.getImageData(this.linkedItem.display.texture);
        if (data.imageLoaded()) {
            data.bindTexture();
            boolean iconX = false;
            int iconY = 0;
            int iconWidth = data.getTotalWidth();
            int iconHeight = data.getTotalWidth();
            int width = data.getTotalWidth();
            int height = data.getTotalHeight();
            if (data.isAnimated()) {
                iconY += (int)(data.getCurrentFrameVOffset() * (float)height);
                iconHeight = data.getFrameHeight();
            } else if (this.linkedItem.display.animated != null && this.linkedItem.display.animated.booleanValue() && this.linkedItem.display.frameCount != null && this.linkedItem.display.frameCount > 1) {
                iconY += (int)(AnimationHelper.getFrameVOffset(height, this.linkedItem.display.frameCount, this.linkedItem.display.frametime) * (float)height);
                iconHeight = height / this.linkedItem.display.frameCount;
            } else {
                iconHeight = data.getTotalWidth();
                height = data.getTotalWidth();
            }
            float[] colors = ColorUtil.hexToRGB(this.linkedItem.display.itemColor);
            GL11.glColor3f((float)colors[0], (float)colors[1], (float)colors[2]);
            SubGuiLinkedItem.func_152125_a((int)x, (int)y, (float)((float)iconX), (float)iconY, (int)iconWidth, (int)iconHeight, (int)iconRenderSize, (int)iconRenderSize, (float)width, (float)height);
        } else {
            textureManager.func_110577_a(new ResourceLocation("customnpcs", "textures/marks/question.png"));
            SubGuiLinkedItem.func_152125_a((int)x, (int)y, (float)0.0f, (float)0.0f, (int)1, (int)1, (int)iconRenderSize, (int)iconRenderSize, (float)1.0f, (float)1.0f);
        }
        GL11.glPopAttrib();
    }

    @Override
    public void close() {
        super.close();
        PacketClient.sendClient(new LinkedItemSavePacket(this.linkedItem.writeToNBT(false), this.originalName));
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiColorSelector) {
            if (this.colorPicked == 1) {
                this.linkedItem.display.itemColor = ((SubGuiColorSelector)subgui).color;
            }
            if (this.colorPicked == 2) {
                this.linkedItem.display.durabilityColor = ((SubGuiColorSelector)subgui).color;
            }
            this.func_73866_w_();
        }
    }
}

