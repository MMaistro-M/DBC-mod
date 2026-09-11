/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.player.CheckPlayerValue;
import kamkeel.npcs.network.packets.player.DialogSelectPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.NoppesStringUtils;
import noppes.npcs.api.handler.data.IDialogImage;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.TextBlockClient;
import noppes.npcs.client.controllers.MusicController;
import noppes.npcs.client.gui.player.GuiDialogImage;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.IGuiClose;
import noppes.npcs.config.ConfigClient;
import noppes.npcs.constants.EnumOptionType;
import noppes.npcs.controllers.data.Dialog;
import noppes.npcs.controllers.data.DialogImage;
import noppes.npcs.controllers.data.DialogOption;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiDialogInteract
extends GuiNPCInterface
implements IGuiClose {
    private GuiScreen parent;
    private Dialog dialog;
    private int selected = 0;
    private List<TextBlockClient> lineBlocks = new ArrayList<TextBlockClient>();
    private List<Integer> options = new ArrayList<Integer>();
    private int totalRows = 0;
    private ScaledResolution scaledResolution;
    private String gradualText = "";
    private int currentBlock = 0;
    private int currentLine = 0;
    private int gradualTextTime = 0;
    private int optionStart = 0;
    private int instantBlockPos = 0;
    private int instantLinePos = 0;
    private int prevPausePos = -1;
    private int scrollY;
    private ResourceLocation wheel;
    private ResourceLocation[] wheelparts;
    private ResourceLocation indicator;
    private boolean isGrabbed = false;
    private int textSoundTime = 0;
    private int textPauseTime = 0;
    private HashMap<Integer, GuiDialogImage> dialogImages = new HashMap();
    private boolean sentClosePacket = false;
    private String dialogSound = null;
    private int selectedX = 0;
    private int selectedY = 0;

    public GuiDialogInteract(EntityNPCInterface npc, Dialog dialog) {
        super(npc);
        this.dialog = dialog;
        this.appendDialog(dialog);
        this.ySize = 238;
        this.wheel = this.getResource("wheel.png");
        this.indicator = this.getResource("indicator.png");
        this.wheelparts = new ResourceLocation[]{this.getResource("wheel1.png"), this.getResource("wheel2.png"), this.getResource("wheel3.png"), this.getResource("wheel4.png"), this.getResource("wheel5.png"), this.getResource("wheel6.png")};
    }

    public GuiDialogInteract(GuiScreen parent, EntityNPCInterface npc, Dialog dialog) {
        this(npc, dialog);
        this.parent = parent;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.isGrabbed = false;
        this.grabMouse(this.dialog.showWheel);
        this.guiTop = this.field_146295_m - this.ySize;
        this.calculateRowHeight();
        this.scaledResolution = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
        this.drawDefaultBackground = this.dialog.darkenScreen;
        this.setOptionOffset();
    }

    public void setOptionOffset() {
        this.optionStart = this.scaledResolution.func_78328_b() - this.options.size() * (ClientProxy.Font.height() + this.dialog.optionSpaceY) - 20;
    }

    public void grabMouse(boolean grab) {
        if (grab && !this.isGrabbed) {
            Minecraft.func_71410_x().field_71417_B.func_74372_a();
            this.isGrabbed = true;
        } else if (!grab && this.isGrabbed) {
            Minecraft.func_71410_x().field_71417_B.func_74373_b();
            this.isGrabbed = false;
        }
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        block46: {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            if (!this.dialog.hideNPC) {
                this.npc.isDrawn = true;
                float l = this.guiLeft - 70;
                float i1 = this.guiTop + this.ySize;
                GL11.glEnable((int)2903);
                GL11.glPushMatrix();
                GL11.glTranslatef((float)(l + (float)this.dialog.npcOffsetX), (float)(i1 + (float)this.dialog.npcOffsetY), (float)50.0f);
                float zoomed = this.npc.field_70131_O;
                if (this.npc.field_70130_N * 2.0f > zoomed) {
                    zoomed = this.npc.field_70130_N * 2.0f;
                }
                zoomed = 2.0f / zoomed * 40.0f;
                GL11.glScalef((float)(-zoomed), (float)zoomed, (float)zoomed);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                float f2 = this.npc.field_70761_aq;
                float f3 = this.npc.field_70177_z;
                float f4 = this.npc.field_70125_A;
                float f7 = this.npc.field_70759_as;
                float f5 = l - (float)i;
                float f6 = i1 - 50.0f - (float)j;
                int rotation = this.npc.ais.orientation;
                this.npc.ais.orientation = 0;
                GL11.glRotatef((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                RenderHelper.func_74519_b();
                GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-((float)Math.atan(f6 / 80.0f)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
                this.npc.field_70761_aq = 0.0f;
                this.npc.field_70177_z = (float)Math.atan(f5 / 80.0f) * 40.0f;
                this.npc.field_70125_A = -((float)Math.atan(f6 / 80.0f)) * 20.0f;
                this.npc.field_70758_at = this.npc.field_70759_as = this.npc.field_70177_z;
                GL11.glTranslatef((float)0.0f, (float)this.npc.field_70129_M, (float)0.0f);
                RenderManager.field_78727_a.field_78735_i = 180.0f;
                try {
                    GL11.glScalef((float)this.dialog.npcScale, (float)this.dialog.npcScale, (float)this.dialog.npcScale);
                    RenderManager.field_78727_a.func_147940_a((Entity)this.npc, 0.0, 0.0, 0.0, 0.0f, 1.0f);
                }
                catch (Exception exception) {
                    // empty catch block
                }
                this.npc.ais.orientation = rotation;
                this.npc.field_70761_aq = f2;
                this.npc.field_70177_z = f3;
                this.npc.field_70125_A = f4;
                this.npc.field_70758_at = this.npc.field_70759_as = f7;
                this.npc.isDrawn = false;
                GL11.glPopMatrix();
                RenderHelper.func_74518_a();
                GL11.glDisable((int)32826);
                OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77476_b);
                GL11.glDisable((int)3553);
                OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77478_a);
            }
            super.func_73863_a(i, j, f);
            this.setOptionOffset();
            GL11.glEnable((int)3042);
            OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
            GL11.glEnable((int)3008);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.0f, (float)0.5f, (float)100.065f);
            for (IDialogImage dialogImage : this.dialog.dialogImages.values()) {
                GuiDialogImage image;
                if (dialogImage.getImageType() != 0) continue;
                if (this.dialogImages.containsKey(dialogImage.getId())) {
                    image = this.dialogImages.get(dialogImage.getId());
                } else {
                    image = new GuiDialogImage((DialogImage)dialogImage);
                    this.dialogImages.put(dialogImage.getId(), image);
                }
                GL11.glEnable((int)3042);
                OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
                GL11.glDisable((int)3008);
                GL11.glTranslatef((float)image.x, (float)image.y, (float)0.0f);
                GL11.glTranslatef((float)((float)(image.alignment % 3) * ((float)this.scaledResolution.func_78326_a() / 2.0f)), (float)((float)(Math.floor(image.alignment / 3) * (double)((float)this.scaledResolution.func_78328_b() / 2.0f))), (float)0.0f);
                image.onRender(this.field_146297_k);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glDisable((int)2896);
                GL11.glEnable((int)3008);
            }
            GL11.glPushMatrix();
            for (IDialogImage dialogImage : this.dialog.dialogImages.values()) {
                GuiDialogImage image;
                if (dialogImage.getImageType() != 1) continue;
                if (this.dialogImages.containsKey(dialogImage.getId())) {
                    image = this.dialogImages.get(dialogImage.getId());
                } else {
                    image = new GuiDialogImage((DialogImage)dialogImage);
                    this.dialogImages.put(dialogImage.getId(), image);
                }
                GL11.glEnable((int)3042);
                OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
                GL11.glDisable((int)3008);
                GL11.glTranslatef((float)image.x, (float)image.y, (float)0.0f);
                GL11.glTranslatef((float)(this.guiLeft + this.dialog.textOffsetX), (float)((float)(this.optionStart + this.dialog.textOffsetY) - (float)image.height * image.scale), (float)0.0f);
                image.onRender(this.field_146297_k);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glDisable((int)2896);
                GL11.glEnable((int)3008);
            }
            GL11.glPopMatrix();
            if (!this.dialog.renderGradual) {
                int count = 0;
                for (int b = 0; b < this.lineBlocks.size(); ++b) {
                    TextBlockClient block = this.lineBlocks.get(b);
                    int size = ClientProxy.Font.width(block.getName() + " ");
                    this.drawDialogString(block.getName() + " ", -4 - size, count, false, block);
                    for (int l = 0; l < block.lines.size(); ++l) {
                        IChatComponent line = (IChatComponent)block.lines.get(l);
                        String drawText = line.func_150254_d();
                        if (b >= this.instantBlockPos && l >= this.instantLinePos) {
                            if (drawText.matches("(.*)(\\{(\\d+)})(.*)")) {
                                if (this.textPauseTime > 0) {
                                    drawText = drawText.substring(0, this.prevPausePos);
                                    this.drawDialogString(drawText, 0, count, true, block);
                                    --this.textPauseTime;
                                    break block46;
                                }
                                String strInt = "";
                                for (int c = 0; c < drawText.length(); ++c) {
                                    if (drawText.substring(c).matches("^(\\{(\\d+)})(.*)") && c > this.prevPausePos) {
                                        this.prevPausePos = c++;
                                        strInt = strInt + drawText.charAt(c);
                                        continue;
                                    }
                                    if (c <= this.prevPausePos || strInt.isEmpty()) continue;
                                    if (drawText.charAt(c) == '}') break;
                                    strInt = strInt + drawText.charAt(c);
                                }
                                if (!strInt.isEmpty()) {
                                    drawText = drawText.substring(0, this.prevPausePos);
                                    this.drawDialogString(drawText, 0, count, true, block);
                                    this.instantBlockPos = b;
                                    this.instantLinePos = l;
                                    this.textPauseTime = Integer.parseInt(strInt);
                                    break block46;
                                }
                            } else {
                                this.prevPausePos = -1;
                            }
                        }
                        this.drawDialogString(drawText, 0, count, true, block);
                        ++count;
                    }
                    ++count;
                }
            } else {
                int count = 0;
                for (int pastBlock = 0; pastBlock < this.currentBlock; ++pastBlock) {
                    TextBlockClient block = this.lineBlocks.get(pastBlock);
                    int size = ClientProxy.Font.width(block.getName() + " ");
                    this.drawDialogString(block.getName() + " ", -4 - size, count, false, block);
                    for (IChatComponent line : block.lines) {
                        this.drawDialogString(line.func_150254_d(), 0, count, true, block);
                        ++count;
                    }
                    ++count;
                }
                if (this.currentBlock < this.lineBlocks.size()) {
                    TextBlockClient block = this.lineBlocks.get(this.currentBlock);
                    int size = ClientProxy.Font.width(block.getName() + " ");
                    this.drawDialogString(block.getName() + " ", -4 - size, count, false, block);
                    for (int pastLine = 0; pastLine < this.currentLine; ++pastLine) {
                        IChatComponent line = (IChatComponent)block.lines.get(pastLine);
                        this.drawDialogString(line.func_150254_d(), 0, count, true, block);
                        ++count;
                    }
                    if (this.currentLine < block.lines.size()) {
                        IChatComponent line = (IChatComponent)block.lines.get(this.currentLine);
                        try {
                            if (this.textPauseTime > 0) {
                                --this.textPauseTime;
                            } else if (ConfigClient.DialogSpeed > 10 || this.gradualTextTime % (11 - ConfigClient.DialogSpeed) == 0) {
                                int addChar = ConfigClient.DialogSpeed > 10 ? ConfigClient.DialogSpeed - 9 : 1;
                                String addText = line.func_150254_d().substring(this.gradualText.length(), this.gradualText.length() + addChar);
                                if (addText.matches("^(\\{(\\d+)})(.*)")) {
                                    int numLength;
                                    StringBuilder numStr = new StringBuilder();
                                    for (numLength = 1; numLength < addText.length() && addText.charAt(numLength) != '}'; ++numLength) {
                                        numStr.append(addText.charAt(numLength));
                                    }
                                    this.textPauseTime = Integer.parseInt(numStr.toString());
                                    addText = line.func_150254_d().substring(this.gradualText.length(), this.gradualText.length() + numLength + 1);
                                } else if (addText.matches("(.+)\\{(\\d+)(.*)")) {
                                    StringBuilder str = new StringBuilder();
                                    for (char c : addText.toCharArray()) {
                                        if (c == '{') break;
                                        str.append(c);
                                    }
                                    addText = str.toString();
                                } else if (addText.matches("(\\{(\\d+))$") || addText.equals("{")) {
                                    while (addText.length() != this.gradualText.length() + addText.length() + 1 && (addText = addText + line.func_150254_d().substring(this.gradualText.length() + addText.length(), this.gradualText.length() + addText.length() + 1)).matches("(.*)(\\{(\\d+))$")) {
                                    }
                                    this.textPauseTime = Integer.parseInt(addText.replace("{", "").replace("}", ""));
                                }
                                this.gradualText = this.gradualText + addText;
                                if (ConfigClient.DialogSound) {
                                    if (this.textSoundTime % 5 == 0) {
                                        Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a((ResourceLocation)new ResourceLocation(this.dialog.textSound), (float)this.dialog.textPitch));
                                    }
                                    ++this.textSoundTime;
                                }
                            }
                        }
                        catch (IndexOutOfBoundsException exception) {
                            this.gradualText = line.func_150254_d();
                        }
                        this.drawDialogString(this.gradualText, 0, count, true, block);
                        if (this.gradualText.length() == line.func_150254_d().length()) {
                            this.gradualText = "";
                            ++this.currentLine;
                            if (this.currentLine >= this.lineBlocks.get((int)this.currentBlock).lines.size()) {
                                ++this.currentBlock;
                            }
                        }
                        ++this.gradualTextTime;
                    }
                }
            }
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        if (!this.options.isEmpty()) {
            if (!this.dialog.showWheel) {
                GL11.glTranslatef((float)this.dialog.optionOffsetX, (float)this.dialog.optionOffsetY, (float)0.0f);
                this.drawLinedOptions(j);
            } else {
                this.drawWheel();
            }
        }
        GL11.glPopMatrix();
    }

    private void drawWheel() {
        int yoffset = this.optionStart + this.dialog.optionOffsetY;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(this.wheel);
        this.func_73729_b(this.field_146294_l / 2 - 31 + this.dialog.optionOffsetX, yoffset, 0, 0, 63, 40);
        this.selectedX += Mouse.getDX();
        this.selectedY += Mouse.getDY();
        int limit = 80;
        if (this.selectedX > limit) {
            this.selectedX = limit;
        }
        if (this.selectedX < -limit) {
            this.selectedX = -limit;
        }
        if (this.selectedY > limit) {
            this.selectedY = limit;
        }
        if (this.selectedY < -limit) {
            this.selectedY = -limit;
        }
        this.selected = 1;
        if (this.selectedY < -20) {
            ++this.selected;
        }
        if (this.selectedY > 54) {
            --this.selected;
        }
        if (this.selectedX < 0) {
            this.selected += 3;
        }
        this.field_146297_k.field_71446_o.func_110577_a(this.wheelparts[this.selected]);
        this.func_73729_b(this.field_146294_l / 2 - 31 + this.dialog.optionOffsetX, yoffset, 0, 0, 85, 55);
        for (int slot : this.dialog.options.keySet()) {
            DialogOption option = this.dialog.options.get(slot);
            if (option == null || option.optionType == EnumOptionType.Disabled) continue;
            int color = option.optionColor;
            if (slot == this.selected) {
                color = 8622040;
            }
            if (slot == 0) {
                this.func_73731_b(this.field_146289_q, option.title, this.field_146294_l / 2 + 13 + this.dialog.optionOffsetX, yoffset - 6, color);
            }
            if (slot == 1) {
                this.func_73731_b(this.field_146289_q, option.title, this.field_146294_l / 2 + 33 + this.dialog.optionOffsetX, yoffset + 12, color);
            }
            if (slot == 2) {
                this.func_73731_b(this.field_146289_q, option.title, this.field_146294_l / 2 + 27 + this.dialog.optionOffsetX, yoffset + 32, color);
            }
            if (slot == 3) {
                this.func_73731_b(this.field_146289_q, option.title, this.field_146294_l / 2 - 13 + this.dialog.optionOffsetX - ClientProxy.Font.width(option.title), yoffset - 6, color);
            }
            if (slot == 4) {
                this.func_73731_b(this.field_146289_q, option.title, this.field_146294_l / 2 - 33 + this.dialog.optionOffsetX - ClientProxy.Font.width(option.title), yoffset + 12, color);
            }
            if (slot != 5) continue;
            this.func_73731_b(this.field_146289_q, option.title, this.field_146294_l / 2 - 27 + this.dialog.optionOffsetX - ClientProxy.Font.width(option.title), yoffset + 32, color);
        }
        this.field_146297_k.field_71446_o.func_110577_a(this.indicator);
        this.func_73729_b(this.field_146294_l / 2 + this.selectedX / 4 - 2 + this.dialog.optionOffsetX, yoffset + 16 - this.selectedY / 6, 0, 0, 8, 8);
    }

    private void drawLinedOptions(int j) {
        int selected;
        int offset = this.optionStart;
        if (j >= offset && (selected = (j - offset - ClientProxy.Font.height() - this.dialog.optionOffsetY) / (ClientProxy.Font.height() + this.dialog.optionSpaceY)) < this.options.size()) {
            this.selected = selected;
        }
        if (this.selected >= this.options.size()) {
            this.selected = this.options.size() - 1;
        }
        if (this.selected < 0) {
            this.selected = 0;
        }
        if (this.dialog.showOptionLine) {
            this.func_73730_a(this.guiLeft - 60, this.guiLeft + this.xSize + 120, offset, -1);
        }
        for (int k = 0; k < this.options.size(); ++k) {
            GL11.glPushMatrix();
            int id = this.options.get(k);
            DialogOption option = this.dialog.options.get(id);
            int y = offset + (k + 1) * ClientProxy.Font.height();
            offset += this.dialog.optionSpaceY;
            if (this.selected == k) {
                this.func_73731_b(this.field_146289_q, ">", this.guiLeft - 60, y, 0xE0E0E0);
            }
            GL11.glPushMatrix();
            for (IDialogImage dialogImage : this.dialog.dialogImages.values()) {
                GuiDialogImage image;
                if (dialogImage.getImageType() != 2) continue;
                if (this.dialogImages.containsKey(dialogImage.getId())) {
                    image = this.dialogImages.get(dialogImage.getId());
                } else {
                    image = new GuiDialogImage((DialogImage)dialogImage);
                    this.dialogImages.put(dialogImage.getId(), image);
                }
                GL11.glEnable((int)3042);
                OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
                GL11.glDisable((int)3008);
                GL11.glTranslatef((float)(this.guiLeft - 30 + this.dialog.optionSpaceX * k), (float)y, (float)0.0f);
                image.color = this.selected == k ? image.selectedColor : image.color;
                GL11.glTranslatef((float)image.x, (float)image.y, (float)0.0f);
                image.onRender(this.field_146297_k);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glDisable((int)2896);
                GL11.glEnable((int)3008);
            }
            GL11.glPopMatrix();
            this.func_73731_b(this.field_146289_q, NoppesStringUtils.formatText(option.title, new Object[]{this.player, this.npc}), this.guiLeft - 30 + this.dialog.optionSpaceX * k, y, option.optionColor);
            GL11.glPopMatrix();
        }
    }

    private void drawDialogString(String text, int left, int count, boolean mainDialogText, TextBlockClient block) {
        int color;
        int offsetY;
        int offsetX;
        int lineOffset = this.dialog.renderGradual ? (this.currentBlock < this.lineBlocks.size() ? this.lineBlocks.get((int)this.currentBlock).lines.size() : this.lineBlocks.get((int)(this.lineBlocks.size() - 1)).lines.size()) - this.currentLine : 0;
        int height = count - this.totalRows + lineOffset;
        int screenPos = this.optionStart;
        int y = height * ClientProxy.Font.height() + screenPos + this.scrollY;
        if (this.dialog.alignment == 1) {
            int i = this.totalRows - this.lineBlocks.get((int)(this.lineBlocks.size() - 1)).lines.size() - 1;
            height = count - this.totalRows + lineOffset - i + 1;
            screenPos = ClientProxy.Font.height() * (this.totalRows - lineOffset);
            y = height * ClientProxy.Font.height() + screenPos + this.scrollY;
        }
        if ((block.titlePos == 0 || mainDialogText) && (this.dialog.alignment == 1 ? (float)y > (float)this.optionStart - (float)ClientProxy.Font.height() / 2.0f : y < screenPos - this.dialog.textHeight || y > screenPos - ClientProxy.Font.height())) {
            return;
        }
        if (mainDialogText) {
            offsetX = this.dialog.textOffsetX;
            offsetY = this.dialog.textOffsetY;
            color = block.color;
        } else {
            offsetX = this.dialog.titleOffsetX;
            offsetY = this.dialog.titleOffsetY;
            color = block.titleColor;
            if (block.titlePos == 1 && block.equals(this.lineBlocks.get(this.currentBlock < this.lineBlocks.size() ? this.currentBlock : this.currentBlock - 1))) {
                y = screenPos - ClientProxy.Font.height() - 5;
            }
        }
        text = text.replaceAll("\\{(\\d+)}", "");
        if (!mainDialogText && block.titlePos == 2 && block.equals(this.lineBlocks.get(this.currentBlock < this.lineBlocks.size() ? this.currentBlock : this.currentBlock - 1))) {
            this.func_73731_b(this.field_146289_q, text, offsetX, offsetY, color);
        } else {
            this.func_73731_b(this.field_146289_q, text, this.guiLeft + left + offsetX, y + offsetY, color);
        }
    }

    public void func_73731_b(FontRenderer fontRendererIn, String text, int x, int y, int color) {
        ClientProxy.Font.drawString(text, x, y, color);
    }

    @Override
    public void func_73869_a(char c, int i) {
        if (i == this.field_146297_k.field_71474_y.field_74351_w.func_151463_i() || i == 200) {
            this.selected = this.dialog.showWheel ? --this.selected : ++this.selected;
        }
        if (i == this.field_146297_k.field_71474_y.field_74368_y.func_151463_i() || i == 208) {
            this.selected = this.dialog.showWheel ? ++this.selected : --this.selected;
        }
        if (this.dialog.alignment == 1 && this.totalRows * ClientProxy.Font.height() > this.dialog.textHeight) {
            if ((i == this.field_146297_k.field_71474_y.field_74368_y.func_151463_i() || i == 201) && this.scrollY < this.totalRows * ClientProxy.Font.height()) {
                this.scrollY += ClientProxy.Font.height() * 2;
            }
            int latestBlockSize = this.lineBlocks.get((int)(this.lineBlocks.size() - 1)).lines.size();
            if ((i == this.field_146297_k.field_71474_y.field_74368_y.func_151463_i() || i == 209) && this.scrollY > -(latestBlockSize - 2) * ClientProxy.Font.height()) {
                this.scrollY -= ClientProxy.Font.height() * 2;
            }
        } else {
            if ((i == this.field_146297_k.field_71474_y.field_74368_y.func_151463_i() || i == 201) && this.scrollY < (this.totalRows - 2) * ClientProxy.Font.height()) {
                this.scrollY += ClientProxy.Font.height() * 2;
            }
            if ((i == this.field_146297_k.field_71474_y.field_74368_y.func_151463_i() || i == 209) && this.scrollY > 0) {
                this.scrollY -= ClientProxy.Font.height() * 2;
            }
        }
        if (i == 28) {
            this.handleDialogSelection();
        }
        if (this.closeOnEsc && (i == 1 || this.isInventoryKey(i))) {
            PacketClient.sendClient(new DialogSelectPacket(this.dialog.id, -1));
            this.closed();
            this.close();
        }
        super.func_73869_a(c, i);
    }

    @Override
    public void func_73864_a(int i, int j, int k) {
        if (this.selected == -1 && this.options.isEmpty() || this.selected >= 0) {
            this.scrollY = 0;
            this.handleDialogSelection();
        }
    }

    private void handleDialogSelection() {
        int optionId = -1;
        if (this.dialog.showWheel) {
            optionId = this.selected;
        } else if (!this.options.isEmpty()) {
            optionId = this.options.get(this.selected);
        }
        PacketClient.sendClient(new DialogSelectPacket(this.dialog.id, optionId));
        if (this.dialog == null || !this.dialog.hasOtherOptions() || this.options.isEmpty()) {
            this.closed();
            this.close();
            return;
        }
        DialogOption option = this.dialog.options.get(optionId);
        if (option == null || option.optionType != EnumOptionType.DialogOption) {
            this.closed();
            this.close();
            return;
        }
        if (!this.dialog.showPreviousBlocks) {
            this.lineBlocks.clear();
        }
        this.lineBlocks.add(new TextBlockClient(this.player.getDisplayName(), option.title, this.dialog.textWidth, option.optionColor, new Object[]{this.player, this.npc}));
        this.gradualText = "";
        this.currentBlock = this.lineBlocks.size() - 1;
        this.currentLine = 0;
        this.gradualTextTime = 0;
        this.instantBlockPos = this.lineBlocks.size() - 1;
        this.instantLinePos = 0;
        this.calculateRowHeight();
        this.textPauseTime = 0;
        NoppesUtil.clickSound();
    }

    private void closed() {
        this.sentClosePacket = true;
        this.grabMouse(false);
        PacketClient.sendClient(new CheckPlayerValue(CheckPlayerValue.Type.CheckQuestCompletion));
    }

    @Override
    public void save() {
    }

    public void appendDialog(Dialog dialog) {
        this.closeOnEsc = !dialog.disableEsc;
        this.sentClosePacket = false;
        this.dialogImages.clear();
        this.dialog = dialog;
        this.options = new ArrayList<Integer>();
        if (dialog.sound != null && !dialog.sound.isEmpty()) {
            if (this.dialogSound != null) {
                MusicController.Instance.stopSound(this.dialogSound);
            }
            MusicController.Instance.stopMusic();
            MusicController.Instance.playSound(dialog.sound, (float)this.npc.field_70165_t, (float)this.npc.field_70163_u, (float)this.npc.field_70161_v);
            this.dialogSound = dialog.sound;
        }
        if (!dialog.showPreviousBlocks) {
            this.lineBlocks.clear();
        }
        this.lineBlocks.add(new TextBlockClient(this.npc, dialog, new Object[]{this.player, this.npc}));
        this.gradualText = "";
        this.currentBlock = this.lineBlocks.size() - 1;
        this.currentLine = 0;
        this.gradualTextTime = 0;
        for (int slot : dialog.options.keySet()) {
            DialogOption option = dialog.options.get(slot);
            if (option == null || option.optionType == EnumOptionType.Disabled) continue;
            this.options.add(slot);
        }
        this.calculateRowHeight();
        this.grabMouse(dialog.showWheel);
    }

    private void calculateRowHeight() {
        this.totalRows = 0;
        for (TextBlockClient block : this.lineBlocks) {
            this.totalRows += block.lines.size() + 1;
        }
    }

    @Override
    public void close() {
        super.close();
        if (this.parent != null) {
            NoppesUtil.openGUI((EntityPlayer)this.player, this.parent);
        }
        if (this.dialogSound != null) {
            MusicController.Instance.stopSound(this.dialogSound);
            this.dialogSound = null;
        }
    }

    @Override
    public void func_146281_b() {
        if (!this.sentClosePacket) {
            if (this.dialog != null) {
                PacketClient.sendClient(new DialogSelectPacket(this.dialog.id, -1));
            }
            this.closed();
        }
        super.func_146281_b();
    }

    @Override
    public void setClose(int i, NBTTagCompound data) {
        this.grabMouse(false);
    }
}

