/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
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
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.CustomNpcs;
import noppes.npcs.api.handler.data.IDialogImage;
import noppes.npcs.api.handler.data.IQuestObjective;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.TextBlockClient;
import noppes.npcs.client.gui.player.GuiDialogImage;
import noppes.npcs.client.gui.player.GuiDialogInteract;
import noppes.npcs.client.gui.player.modern.GuiModernDialogInteract;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiTexturedButton;
import noppes.npcs.client.gui.util.IGuiClose;
import noppes.npcs.config.ConfigExperimental;
import noppes.npcs.controllers.FactionController;
import noppes.npcs.controllers.data.Dialog;
import noppes.npcs.controllers.data.DialogImage;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.controllers.data.QuestData;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public class GuiModernQuestDialog
extends GuiNPCInterface
implements IGuiClose {
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
    private boolean sentClosePacket = false;
    private final HashMap<Integer, GuiDialogImage> dialogImages = new HashMap();
    private Dialog prevDialog;
    private int optionId;
    private Quest quest;

    public GuiModernQuestDialog(EntityNPCInterface npc, Quest quest, Dialog prevDialog, int optionId) {
        super(npc);
        this.ySize = 238;
        this.prevDialog = prevDialog;
        this.quest = quest;
        this.optionId = optionId;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.isGrabbed = false;
        this.sentClosePacket = false;
        this.guiTop = this.field_146295_m - this.ySize;
        this.calculateRowHeight();
        this.scaledResolution = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
        this.drawDefaultBackground = this.prevDialog.darkenScreen;
        this.setOptionOffset();
        this.addButton(new GuiTexturedButton(0, "questgui.reject", 720, 326, 156, 40, this.decomposed.toString(), 72, 15));
        this.addButton(new GuiTexturedButton(1, "questgui.accept", 812, 326, 156, 40, this.decomposed.toString(), 72, 15));
    }

    public void setOptionOffset() {
        this.optionStart = this.scaledResolution.func_78328_b() - this.options.size() * (ClientProxy.Font.height() + this.prevDialog.optionSpaceY) - 20;
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
        GuiModernQuestDialog.func_73734_a((int)x, (int)y, (int)width, (int)(y + 1), (int)(-16777216 + this.prevDialog.colorData.getLineColor1()));
        GuiModernQuestDialog.func_73734_a((int)x, (int)(y + 1), (int)width, (int)(y + 2), (int)(-16777216 + this.prevDialog.colorData.getLineColor2()));
        GuiModernQuestDialog.func_73734_a((int)x, (int)(y + 2), (int)width, (int)(y + 3), (int)(-16777216 + this.prevDialog.colorData.getLineColor3()));
    }

    @Override
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        int fac2ID;
        int fac1ID;
        GuiDialogImage image;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.func_73733_a(0, 0, this.field_146294_l, this.field_146295_m, 0x66000000, 0x66000000);
        if (!this.prevDialog.hideNPC) {
            float scaleHeight = (float)this.field_146295_m / 509.0f;
            float scaleWidth = (float)this.field_146294_l / 960.0f;
            this.drawNpc(this.npc, -210 + this.prevDialog.npcOffsetX + (int)(300.0f * (1.0f - scaleWidth)), 350 + this.prevDialog.npcOffsetY - (int)(100.0f * (1.0f - scaleHeight)), 9.5f * this.prevDialog.npcScale * scaleHeight, -20);
        }
        this.setOptionOffset();
        GL11.glEnable((int)3042);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glEnable((int)3008);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.5f, (float)100.065f);
        for (IDialogImage dialogImage : this.prevDialog.dialogImages.values()) {
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
            GL11.glTranslatef((float)((float)(image.alignment % 3) * ((float)this.scaledResolution.func_78326_a() / 2.0f)), (float)((float)(Math.floor(image.alignment / 3) * (double)((float)this.scaledResolution.func_78328_b() / 2.0f))), (float)0.0f);
            image.onRender(this.field_146297_k);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)2896);
            GL11.glEnable((int)3008);
        }
        GL11.glPushMatrix();
        for (IDialogImage dialogImage : this.prevDialog.dialogImages.values()) {
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
            GL11.glTranslatef((float)(this.guiLeft + this.prevDialog.textOffsetX), (float)((float)(this.optionStart + this.prevDialog.textOffsetY) - (float)image.height * image.scale), (float)0.0f);
            image.onRender(this.field_146297_k);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)2896);
            GL11.glEnable((int)3008);
        }
        GL11.glPopMatrix();
        int textBlockWidth = 700;
        String takeQuestString = this.translate("questgui.doyouaccept");
        int lineCount = this.getLineCount(takeQuestString, textBlockWidth);
        int gap = Math.max(16, Math.min((int)(2.6f * (float)lineCount), 32));
        int textPartHeight = 26 + lineCount * ClientProxy.Font.height() + 2 * gap;
        GL11.glPushMatrix();
        GL11.glTranslated((double)0.0, (double)0.5, (double)200.065f);
        this.func_73733_a(0, this.field_146295_m - textPartHeight, this.field_146294_l, this.field_146295_m, -1728053248, -1728053248);
        this.drawLine(23, this.field_146295_m - textPartHeight + 23, this.field_146294_l - 23);
        GL11.glScalef((float)1.5f, (float)1.5f, (float)1.0f);
        this.func_73731_b(this.field_146289_q, this.npc.func_70005_c_(), 31, (int)((double)(this.field_146295_m - textPartHeight + 5) / 1.5), this.prevDialog.titleColor);
        GL11.glScalef((float)0.6666667f, (float)0.6666667f, (float)1.0f);
        this.drawTextBlock(takeQuestString, (this.field_146294_l - textBlockWidth) / 2, this.field_146295_m - textPartHeight + 23 + 3 + gap, textBlockWidth, -1);
        HashMap<Integer, QuestData> activeQuests = PlayerData.get((EntityPlayer)this.player).questData.activeQuests;
        boolean hadQuest = activeQuests.containsKey(this.quest.id);
        activeQuests.put(this.quest.id, new QuestData(this.quest));
        StringBuilder objectiveString = new StringBuilder();
        String[] questType = new String[]{this.translate("questgui.bringitems"), this.translate("questgui.readdialog"), this.translate("questgui.killmobs"), this.translate("questgui.findlocation"), this.translate("questgui.defeat")};
        for (IQuestObjective objective : this.quest.questInterface.getObjectives((EntityPlayer)this.player)) {
            if (objective == null) continue;
            objectiveString.append("- " + questType[this.quest.getType()] + ": ").append(objective.getText()).append("\n");
        }
        if (!hadQuest) {
            activeQuests.remove(this.quest.id);
        }
        int questLineCount = this.getLineCount(this.quest.logText, 180);
        int objectivesLineCount = this.getLineCount(objectiveString.toString(), 180);
        int topToTextBottom = (int)((double)this.field_146295_m * 0.08) + 38 + questLineCount * ClientProxy.Font.height() + 20;
        int topToObjectivesBottom = topToTextBottom + 19 + objectivesLineCount * ClientProxy.Font.height() + 14;
        int rewardCount = 0;
        ArrayList<Integer> facIDs = new ArrayList<Integer>();
        for (Integer facID : new Integer[]{this.quest.factionOptions.factionId, this.quest.factionOptions.faction2Id}) {
            if (facID == -1) continue;
            facIDs.add(facID);
        }
        for (IItemStack reward : this.quest.getRewards().getItems()) {
            if (reward == null || reward.getMCItemStack() == null) continue;
            ++rewardCount;
        }
        int topToRewardsBottom = topToObjectivesBottom + (rewardCount == 0 ? 0 : 49);
        int topToExpBottom = topToRewardsBottom + (this.quest.rewardExp == 0 ? 0 : 12);
        int topToFactionBottom = topToExpBottom + facIDs.size() * 15;
        int questBlockHeight = topToFactionBottom + 28;
        this.func_73733_a(this.field_146294_l - 285, (int)((double)this.field_146295_m * 0.08), this.field_146294_l - 285 + 260, questBlockHeight, -1728053248, -1728053248);
        GL11.glScalef((float)1.5f, (float)1.5f, (float)1.0f);
        this.func_73731_b(this.field_146289_q, this.quest.getName(), (int)((double)(this.field_146294_l - 268) / 1.5), (int)((double)this.field_146295_m * 0.095 / 1.5), -1);
        GL11.glScalef((float)0.6666667f, (float)0.6666667f, (float)1.0f);
        this.drawLine(this.field_146294_l - 274, (int)((double)this.field_146295_m * 0.13), this.field_146294_l - 285 + 260 - 11);
        this.drawTextBlock(this.quest.logText, this.field_146294_l - 245, (int)((double)this.field_146295_m * 0.157), 180, 0xB8B8B8);
        this.func_73731_b(this.field_146289_q, this.translate("questgui.objectives"), this.field_146294_l - 270, topToTextBottom, -1);
        this.drawLeftAllignedTextBlock(objectiveString.toString(), this.field_146294_l - 255, topToTextBottom + 12, 180, 0xB8B8B8);
        if (rewardCount != 0) {
            this.func_73731_b(this.field_146289_q, this.translate("questgui.rewards"), this.field_146294_l - 270, topToObjectivesBottom, -1);
        }
        for (int i = 0; i < this.quest.rewardItems.func_70302_i_(); ++i) {
            ItemStack rewardStack = this.quest.rewardItems.func_70301_a(i);
            if (rewardStack == null) continue;
            Minecraft.func_71410_x().func_110434_K().func_110577_a(this.decomposed);
            GL11.glDisable((int)2896);
            int color = this.prevDialog.colorData.getSlotColor();
            GL11.glColor4f((float)((float)(color >> 16 & 0xFF) / 255.0f), (float)((float)(color >> 8 & 0xFF) / 255.0f), (float)((float)(color & 0xFF) / 255.0f), (float)1.0f);
            this.func_73729_b(this.field_146294_l - 270 + 26 * i, topToObjectivesBottom + 16, 0, 27, 24, 24);
            field_146296_j.func_82406_b(this.field_146289_q, Minecraft.func_71410_x().field_71446_o, rewardStack, this.field_146294_l - 266 + 26 * i, topToObjectivesBottom + 20);
            field_146296_j.func_94148_a(this.field_146289_q, Minecraft.func_71410_x().field_71446_o, rewardStack, this.field_146294_l - 266 + 26 * i, topToObjectivesBottom + 20, "" + rewardStack.field_77994_a);
        }
        GL11.glDisable((int)2896);
        if (this.quest.rewardExp != 0) {
            this.func_73731_b(this.field_146289_q, this.translate("questgui.experience"), this.field_146294_l - 270, topToRewardsBottom, 0xB8B8B8);
            int expPosX = this.field_146294_l - 270 + ClientProxy.Font.width(this.translate("questgui.experience"));
            this.func_73731_b(this.field_146289_q, "" + this.quest.rewardExp, expPosX, topToRewardsBottom, -1);
            int expSymbolPosX = expPosX + ClientProxy.Font.width("" + this.quest.rewardExp + "  ");
            Minecraft.func_71410_x().func_110434_K().func_110577_a(this.decomposed);
            this.func_73729_b(expSymbolPosX, topToRewardsBottom, 26, 27, 8, 8);
        }
        if ((fac1ID = this.quest.factionOptions.factionId) != -1) {
            String fac1Name = FactionController.getInstance().getFaction(fac1ID).getName();
            String fac1Color = this.quest.factionOptions.decreaseFactionPoints ? "\u00a7c-" : "\u00a7a+";
            int fac1Point = this.quest.factionOptions.factionPoints;
            int facIDIndex = facIDs.indexOf(fac1ID);
            this.func_73731_b(this.field_146289_q, fac1Name + " " + fac1Color + fac1Point, this.field_146294_l - 270, topToExpBottom + facIDIndex * 12, 0xB8B8B8);
        }
        if ((fac2ID = this.quest.factionOptions.faction2Id) != -1) {
            String fac2Name = FactionController.getInstance().getFaction(fac2ID).getName();
            String fac2Color = this.quest.factionOptions.decreaseFaction2Points ? "\u00a7c-" : "\u00a7a+";
            int fac2Point = this.quest.factionOptions.faction2Points;
            int facIDIndex = facIDs.indexOf(fac2ID);
            this.func_73731_b(this.field_146289_q, fac2Name + " " + fac2Color + fac2Point, this.field_146294_l - 270, topToExpBottom + facIDIndex * 12, 0xB8B8B8);
        }
        ((GuiNpcButton)((Object)this.buttons.get((Object)Integer.valueOf((int)0)))).field_146128_h = this.field_146294_l - 240;
        ((GuiNpcButton)((Object)this.buttons.get((Object)Integer.valueOf((int)1)))).field_146128_h = this.field_146294_l - 148;
        ((GuiNpcButton)((Object)this.buttons.get((Object)Integer.valueOf((int)0)))).field_146129_i = topToFactionBottom;
        ((GuiNpcButton)((Object)this.buttons.get((Object)Integer.valueOf((int)1)))).field_146129_i = topToFactionBottom;
        ((GuiTexturedButton)((Object)this.buttons.get((Object)Integer.valueOf((int)0)))).scale = 0.5f;
        ((GuiTexturedButton)((Object)this.buttons.get((Object)Integer.valueOf((int)1)))).scale = 0.5f;
        ((GuiTexturedButton)((Object)this.buttons.get((Object)Integer.valueOf((int)0)))).color = this.prevDialog.colorData.getButtonRejectColor();
        ((GuiTexturedButton)((Object)this.buttons.get((Object)Integer.valueOf((int)1)))).color = this.prevDialog.colorData.getButtonAcceptColor();
        super.func_73863_a(mouseX, mouseY, partialTicks);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public String translate(String key) {
        return StatCollector.func_74838_a((String)key);
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
        float f8 = (float)(this.guiTop + y) - 50.0f * scale * zoomed;
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
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 0) {
            PacketClient.sendClient(new DialogSelectPacket(this.prevDialog.id, -1));
            this.closed();
            this.close();
        } else if (button.field_146127_k == 1) {
            if (this.optionId != -2) {
                PacketClient.sendClient(new DialogSelectPacket(this.prevDialog.id, this.optionId));
            } else if (ConfigExperimental.ModernGuiSystem) {
                CustomNpcs.proxy.openGui((EntityPlayer)this.player, new GuiModernDialogInteract(this.npc, this.prevDialog));
            } else {
                CustomNpcs.proxy.openGui((EntityPlayer)this.player, new GuiDialogInteract(this.npc, this.prevDialog));
            }
        }
    }

    private void closed() {
        this.sentClosePacket = true;
        this.grabMouse(false);
        PacketClient.sendClient(new CheckPlayerValue(CheckPlayerValue.Type.CheckQuestCompletion));
    }

    @Override
    public void save() {
    }

    private void calculateRowHeight() {
        this.totalRows = 0;
        for (TextBlockClient block : this.lineBlocks) {
            this.totalRows += block.lines.size() + 1;
        }
    }

    public void drawTextBlock(String text, int x, int y, int width, int color) {
        TextBlockClient block = new TextBlockClient("", text, width, -1, new Object[]{this.player, this.npc});
        int count = 0;
        for (IChatComponent line : block.lines) {
            int height = y + count * ClientProxy.Font.height();
            this.func_73732_a(this.field_146289_q, line.func_150254_d(), x + width / 2, height, color);
            ++count;
        }
    }

    public void drawLeftAllignedTextBlock(String text, int x, int y, int width, int color) {
        TextBlockClient block = new TextBlockClient("", text, width, -1, new Object[]{this.player, this.npc});
        int count = 0;
        for (IChatComponent line : block.lines) {
            int height = y + count * ClientProxy.Font.height();
            this.func_73731_b(this.field_146289_q, line.func_150254_d(), x, height, color);
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
            if (this.prevDialog != null) {
                PacketClient.sendClient(new DialogSelectPacket(this.prevDialog.id, -1));
            }
            this.closed();
        }
        super.func_146281_b();
    }
}

