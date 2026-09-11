/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.player.modern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.player.CheckPlayerValue;
import kamkeel.npcs.network.packets.player.DialogSelectPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.CustomNpcs;
import noppes.npcs.api.handler.data.IDialogImage;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.TextBlockClient;
import noppes.npcs.client.controllers.MusicController;
import noppes.npcs.client.gui.player.GuiDialogImage;
import noppes.npcs.client.gui.player.modern.GuiModernQuestDialog;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.IGuiClose;
import noppes.npcs.constants.EnumOptionType;
import noppes.npcs.controllers.data.Dialog;
import noppes.npcs.controllers.data.DialogImage;
import noppes.npcs.controllers.data.DialogOption;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public class GuiModernDialogInteract
extends GuiNPCInterface
implements IGuiClose {
    private Dialog dialog;
    private int selected = 0;
    private List<TextBlockClient> lineBlocks = new ArrayList<TextBlockClient>();
    private List<Integer> options = new ArrayList<Integer>();
    private int totalRows = 0;
    private ScaledResolution scaledResolution;
    private int optionStart = 0;
    private static int textSpeed = 10;
    private static boolean textSoundEnabled = true;
    private int scrollY;
    private final ResourceLocation decomposed = new ResourceLocation("customnpcs", "textures/gui/dialog_menu_decomposed.png");
    private boolean isGrabbed = false;
    private final HashMap<Integer, GuiDialogImage> dialogImages = new HashMap();
    private boolean sentClosePacket = false;
    private String dialogSound = null;

    public GuiModernDialogInteract(EntityNPCInterface npc, Dialog dialog) {
        super(npc);
        this.dialog = dialog;
        this.appendDialog(dialog);
        this.ySize = 238;
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

    public void drawLine(int x, int y, int width) {
        GuiModernDialogInteract.func_73734_a((int)x, (int)y, (int)width, (int)(y + 1), (int)(-16777216 + this.dialog.colorData.getLineColor1()));
        GuiModernDialogInteract.func_73734_a((int)x, (int)(y + 1), (int)width, (int)(y + 2), (int)(-16777216 + this.dialog.colorData.getLineColor2()));
        GuiModernDialogInteract.func_73734_a((int)x, (int)(y + 2), (int)width, (int)(y + 3), (int)(-16777216 + this.dialog.colorData.getLineColor3()));
    }

    @Override
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        GuiDialogImage image;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.func_73733_a(0, 0, this.field_146294_l, this.field_146295_m, 0x66000000, 0x66000000);
        super.func_73863_a(mouseX, mouseY, partialTicks);
        this.setOptionOffset();
        GL11.glEnable((int)3042);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glEnable((int)3008);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.5f, (float)100.065f);
        for (IDialogImage dialogImage : this.dialog.dialogImages.values()) {
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
        if (!this.dialog.hideNPC) {
            float scaleHeight = (float)this.field_146295_m / 509.0f;
            float scaleWidth = (float)this.field_146294_l / 960.0f;
            this.drawNpc(this.npc, -210 + this.dialog.npcOffsetX + (int)(300.0f * (1.0f - scaleWidth)), 350 + this.dialog.npcOffsetY - (int)(100.0f * (1.0f - scaleHeight)), 9.5f * this.dialog.npcScale * scaleHeight, -20);
        }
        int textBlockWidth = 700;
        int lineCount = this.getLineCount(this.dialog.text, textBlockWidth);
        int gap = Math.max(16, Math.min((int)(2.6f * (float)lineCount), 32));
        int textPartHeight = 26 + lineCount * ClientProxy.Font.height() + 2 * gap;
        GL11.glPushMatrix();
        GL11.glTranslated((double)0.0, (double)0.5, (double)200.065f);
        this.func_73733_a(0, this.field_146295_m - textPartHeight, this.field_146294_l, this.field_146295_m, -1728053248, -1728053248);
        this.drawLine(23, this.field_146295_m - textPartHeight + 23, this.field_146294_l - 23);
        GL11.glScalef((float)1.5f, (float)1.5f, (float)1.0f);
        this.func_73731_b(this.field_146289_q, this.npc.func_70005_c_(), 31, (int)((double)(this.field_146295_m - textPartHeight + 5) / 1.5), this.dialog.titleColor);
        GL11.glScalef((float)0.6666667f, (float)0.6666667f, (float)1.0f);
        this.drawTextBlock(this.dialog.text, (this.field_146294_l - textBlockWidth) / 2, this.field_146295_m - textPartHeight + 23 + 3 + gap, textBlockWidth);
        this.selected = -1;
        for (int i = 0; i < this.options.size(); ++i) {
            int optionHeight = this.field_146295_m / 2 - 30 + i * 19;
            int optionNum = this.options.get(i);
            DialogOption option = this.dialog.options.get(optionNum);
            if (mouseX >= this.field_146294_l - 237 && mouseX <= this.field_146294_l - 14 && mouseY >= optionHeight && mouseY <= optionHeight + 13) {
                this.selected = i;
            }
            GL11.glEnable((int)3042);
            Minecraft.func_71410_x().func_110434_K().func_110577_a(this.decomposed);
            this.func_73729_b(this.field_146294_l - 233, optionHeight, 0, i == this.selected ? 13 : 0, 223, 13);
            GL11.glDisable((int)3042);
            if (this.getQuestByOptionId(optionNum) != null) {
                this.func_73731_b(this.field_146289_q, "!", this.field_146294_l - 229, optionHeight + 3, 7792731);
            } else {
                this.func_73731_b(this.field_146289_q, ">", this.field_146294_l - 229, optionHeight + 3, -1);
            }
            this.func_73731_b(this.field_146289_q, option.title, this.field_146294_l - 221, optionHeight + 3, option.optionColor);
        }
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public Quest getQuestByOptionId(int id) {
        DialogOption option = this.dialog.options.get(id);
        if (option != null && option.getDialog() != null && option.getDialog().hasQuest()) {
            return option.getDialog().getQuest();
        }
        return null;
    }

    public void drawNpc(EntityNPCInterface entity, int x, int y, float zoomed, int rotation) {
        EntityNPCInterface npc = entity;
        float f2 = entity.field_70761_aq;
        float f3 = entity.field_70177_z;
        float f4 = entity.field_70125_A;
        float f5 = entity.field_70758_at;
        float f6 = entity.field_70759_as;
        float scale = 1.0f;
        if ((double)entity.field_70131_O > 2.4) {
            scale = 2.0f / entity.field_70131_O;
        }
        float f7 = this.guiLeft + x;
        entity.field_70761_aq = 0.0f;
        entity.field_70177_z = (float)Math.atan(f7 / 80.0f) * 40.0f + (float)rotation;
        entity.field_70125_A = 0.0f;
        entity.field_70759_as = 0.0f;
        entity.field_70758_at = 0.0f;
        int orientation = 0;
        orientation = npc.ais.orientation;
        npc.ais.orientation = rotation;
        int visibleName = npc.display.showName;
        npc.display.showName = 1;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)this.guiLeft + (float)x), (float)(this.guiTop + y), (float)1050.0f);
        GL11.glScalef((float)1.0f, (float)1.0f, (float)-1.0f);
        GL11.glTranslated((double)0.0, (double)0.0, (double)1000.0);
        GL11.glScalef((float)(30.0f * scale * zoomed), (float)(30.0f * scale * zoomed), (float)(30.0f * scale * zoomed));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(-((float)rotation)), (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74519_b();
        RenderManager.field_78727_a.field_78735_i = 180.0f;
        GL11.glEnable((int)2929);
        RenderManager.field_78727_a.func_147940_a((Entity)npc, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        GL11.glPopMatrix();
        entity.field_70761_aq = f2;
        entity.field_70177_z = f3;
        entity.field_70125_A = f4;
        entity.field_70758_at = f5;
        entity.field_70759_as = f6;
        npc.ais.orientation = orientation;
        npc.display.showName = visibleName;
        RenderHelper.func_74518_a();
        GL11.glDisable((int)32826);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77476_b);
        GL11.glDisable((int)3553);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77478_a);
    }

    public void func_146270_b(int p_238651_2_) {
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
        if ((i == this.field_146297_k.field_71474_y.field_74351_w.func_151463_i() || i == 203) && --textSpeed < 1) {
            textSpeed = 1;
        }
        if (i == this.field_146297_k.field_71474_y.field_74368_y.func_151463_i() || i == 205) {
            ++textSpeed;
        }
        if (i == this.field_146297_k.field_71474_y.field_74314_A.func_151463_i() || i == 57) {
            boolean bl = textSoundEnabled = !textSoundEnabled;
        }
        if (i == this.field_146297_k.field_71474_y.field_74368_y.func_151463_i() || i == 201 && this.scrollY < (this.totalRows - 2) * ClientProxy.Font.height()) {
            this.scrollY += ClientProxy.Font.height() * 2;
        }
        if (i == this.field_146297_k.field_71474_y.field_74368_y.func_151463_i() || i == 209 && this.scrollY > 0) {
            this.scrollY -= ClientProxy.Font.height() * 2;
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
        if (this.getQuestByOptionId(optionId) == null) {
            PacketClient.sendClient(new DialogSelectPacket(this.dialog.id, optionId));
        } else {
            CustomNpcs.proxy.openGui((EntityPlayer)this.player, new GuiModernQuestDialog(this.npc, this.getQuestByOptionId(optionId), this.dialog, optionId));
        }
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
        this.calculateRowHeight();
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
    public void drawTextBlock(String text, int x, int y, int width) {
        TextBlockClient block = new TextBlockClient("", text, width, -1, new Object[]{this.player, this.npc});
        int count = 0;
        for (IChatComponent line : block.lines) {
            int height = y + count * ClientProxy.Font.height();
            this.func_73732_a(this.field_146289_q, line.func_150254_d(), x + width / 2, height, this.dialog.color);
            ++count;
        }
    }

    public void func_73732_a(FontRenderer p_73732_1_, String p_73732_2_, int p_73732_3_, int p_73732_4_, int p_73732_5_) {
        ClientProxy.Font.drawString(p_73732_2_, p_73732_3_ - ClientProxy.Font.width(p_73732_2_) / 2, p_73732_4_, p_73732_5_);
    }

    public int getLineCount(String text, int width) {
        TextBlockClient block = new TextBlockClient("", text, width, -1, new Object[]{this.player, this.npc});
        return block.lines.size();
    }

    @Override
    public void setClose(int i, NBTTagCompound data) {
        this.grabMouse(false);
    }

    @Override
    public void func_146281_b() {
        if (!this.sentClosePacket) {
            if (this.dialog != null) {
                PacketClient.sendClient(new DialogSelectPacket(this.dialog.id, -1));
            }
            this.closed();
        }
        if (this.dialogSound != null) {
            MusicController.Instance.stopSound(this.dialogSound);
            this.dialogSound = null;
        }
        super.func_146281_b();
    }
}

