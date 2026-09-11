/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.hud;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.client.gui.hud.HudComponent;
import noppes.npcs.config.ConfigClient;
import noppes.npcs.controllers.data.Quest;
import org.lwjgl.opengl.GL11;

public class QuestTrackingComponent
extends HudComponent {
    private final Minecraft mc;
    private ArrayList<String> questTitleLines = new ArrayList();
    private ArrayList<String> questCategoryLines = new ArrayList();
    private final ArrayList<String> objectiveLines = new ArrayList();
    private final ArrayList<String> turnInLines = new ArrayList();

    public QuestTrackingComponent(Minecraft mc) {
        this.mc = mc;
        this.load();
    }

    public void setQuestData(Quest quest, String category, List<String> objectives, String turnIn) {
        this.questTitleLines = this.splitLines(this.convertColorCodes(quest.getName()), this.overlayWidth - 10);
        this.questCategoryLines = this.splitLines(this.convertColorCodes(category), this.overlayWidth - 10);
        this.objectiveLines.clear();
        for (String obj : objectives) {
            this.objectiveLines.add(this.convertColorCodes(obj));
        }
        this.turnInLines.clear();
        if (turnIn != null && !turnIn.isEmpty()) {
            this.turnInLines.add(this.convertColorCodes(turnIn));
        }
    }

    public void setOverlayData(NBTTagCompound compound) {
        Quest quest = new Quest();
        quest.readNBT(compound.func_74775_l("Quest"));
        String category = compound.func_74779_i("CategoryName");
        ArrayList<String> objectives = new ArrayList<String>();
        NBTTagList nbtTagList = compound.func_150295_c("ObjectiveList", 8);
        for (int i = 0; i < nbtTagList.func_74745_c(); ++i) {
            boolean completed;
            String objective;
            block8: {
                objective = nbtTagList.func_150307_f(i);
                String[] split = objective.split(":");
                split = split[split.length - 1].split("/");
                completed = false;
                try {
                    int total;
                    if (split.length < 2) {
                        throw new NumberFormatException("catch");
                    }
                    int killed = Integer.parseInt(split[0].trim());
                    if (killed / (total = Integer.parseInt(split[1].trim())) == 1) {
                        completed = true;
                    }
                }
                catch (NumberFormatException e) {
                    if (!objective.endsWith("(Done)") && !objective.endsWith("(read)") && (!objective.endsWith("Found") || objective.endsWith("Not Found"))) break block8;
                    completed = true;
                }
            }
            objective = completed ? "&a&m" + objective : "&6" + objective;
            objectives.add(objective);
        }
        String turnIn = "";
        boolean instantComplete = compound.func_74767_n("Instant");
        if (instantComplete) {
            turnIn = "Completed automatically";
        } else {
            String npcName = compound.func_74779_i("TurnInNPC");
            if (!npcName.isEmpty()) {
                turnIn = "Complete with " + npcName;
            }
        }
        this.setQuestData(quest, category, objectives, turnIn);
        this.hasData = true;
    }

    @Override
    public void loadData(NBTTagCompound compound) {
        this.setOverlayData(compound);
    }

    @Override
    public void load() {
        this.posX = ConfigClient.QuestOverlayX;
        this.posY = ConfigClient.QuestOverlayY;
        this.scale = ConfigClient.QuestOverlayScale;
        this.textAlign = ConfigClient.QuestOverlayTextAlign;
    }

    @Override
    public void save() {
        ConfigClient.QuestOverlayX = this.posX;
        ConfigClient.QuestOverlayXProperty.set((double)ConfigClient.QuestOverlayX);
        ConfigClient.QuestOverlayY = this.posY;
        ConfigClient.QuestOverlayYProperty.set((double)ConfigClient.QuestOverlayY);
        ConfigClient.QuestOverlayScale = this.scale;
        ConfigClient.QuestOverlayScaleProperty.set(ConfigClient.QuestOverlayScale);
        ConfigClient.QuestOverlayTextAlign = this.textAlign;
        ConfigClient.QuestOverlayTextAlignProperty.set(ConfigClient.QuestOverlayTextAlign);
        if (ConfigClient.config.hasChanged()) {
            ConfigClient.config.save();
        }
    }

    @Override
    public void renderOnScreen(float partialTicks) {
        if (!this.hasData || this.isEditting) {
            return;
        }
        ScaledResolution res = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
        int actualX = (int)(this.posX / 100.0f * (float)res.func_78326_a());
        int actualY = (int)(this.posY / 100.0f * (float)res.func_78328_b());
        float effectiveScale = this.getEffectiveScale(res);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)actualX, (float)actualY, (float)0.0f);
        GL11.glScalef((float)effectiveScale, (float)effectiveScale, (float)effectiveScale);
        this.drawRect(0, 0, this.overlayWidth, this.overlayHeight, 0x40000000);
        int currentY = 5;
        currentY = this.renderTextBlock(this.questTitleLines, currentY, this.textAlign, 0xFFFFFF);
        this.drawDecorativeLine(currentY - 1);
        currentY += 8;
        currentY = this.renderTextBlock(this.questCategoryLines, currentY, this.textAlign, 0xCCCCCC);
        this.drawDecorativeLine(currentY - 1);
        currentY += 8;
        currentY = this.renderTextBlock(this.objectiveLines, currentY, this.textAlign, 0xAAAAAA);
        if (!this.turnInLines.isEmpty()) {
            this.drawDecorativeLine(currentY - 1);
            currentY += 8;
        }
        this.renderTextBlock(this.turnInLines, currentY, this.textAlign, 0xAAAAAA);
        GL11.glPopMatrix();
    }

    @Override
    public void renderEditing() {
        this.isEditting = true;
        ScaledResolution res = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
        int actualX = (int)(this.posX / 100.0f * (float)res.func_78326_a());
        int actualY = (int)(this.posY / 100.0f * (float)res.func_78328_b());
        float effectiveScale = this.getEffectiveScale(res);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)actualX, (float)actualY, (float)0.0f);
        GL11.glScalef((float)effectiveScale, (float)effectiveScale, (float)effectiveScale);
        this.drawRect(0, 0, this.overlayWidth, this.overlayHeight, 0x40FFFFFF);
        this.drawRectOutline(0, 0, this.overlayWidth, this.overlayHeight, -5592406);
        this.drawRect(this.overlayWidth - 10, this.overlayHeight - 10, this.overlayWidth, this.overlayHeight, -3355444);
        int currentY = 5;
        currentY = this.renderDemoTextBlock(new String[]{"Dummy Quest Title"}, currentY, this.textAlign, 0xFFFFFF);
        this.drawDecorativeLine(currentY - 1);
        currentY += 8;
        currentY = this.renderDemoTextBlock(new String[]{"Dummy Category"}, currentY, this.textAlign, 0xCCCCCC);
        this.drawDecorativeLine(currentY - 1);
        currentY += 8;
        currentY = this.renderDemoTextBlock(new String[]{"Objective: Kill 10 mobs", "Objective: Collect 5 items"}, currentY, this.textAlign, 0xAAAAAA);
        this.drawDecorativeLine(currentY - 1);
        this.renderDemoTextBlock(new String[]{"Turn in with NPC"}, currentY += 8, this.textAlign, 0xAAAAAA);
        GL11.glPopMatrix();
    }

    @Override
    public void addEditorButtons(List<GuiButton> buttonList) {
        super.addEditorButtons(buttonList);
        buttonList.add(new GuiButton(1, 100, 0, 120, 20, this.getAlignText()));
        buttonList.add(new GuiButton(3, 100, 0, 120, 20, "Reset to Center"));
    }

    @Override
    public void onEditorButtonPressed(GuiButton button) {
        if (button.field_146127_k == 1) {
            this.textAlign = (this.textAlign + 1) % 3;
            button.field_146126_j = this.getAlignText();
        } else if (button.field_146127_k == 3) {
            ScaledResolution res = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
            float effectiveScale = this.getEffectiveScale(res);
            int compWidth = (int)((float)this.overlayWidth * effectiveScale);
            int compHeight = (int)((float)this.overlayHeight * effectiveScale);
            int centerX = (res.func_78326_a() - compWidth) / 2;
            int centerY = (res.func_78328_b() - compHeight) / 2;
            this.posX = 100.0f * (float)centerX / (float)res.func_78326_a();
            this.posY = 100.0f * (float)centerY / (float)res.func_78328_b();
        } else {
            super.onEditorButtonPressed(button);
        }
    }

    private String getAlignText() {
        switch (this.textAlign) {
            case 0: {
                return "Align: Left";
            }
            case 1: {
                return "Align: Center";
            }
            case 2: {
                return "Align: Right";
            }
        }
        return "Align: Unknown";
    }

    private int renderTextBlock(List<String> lines, int startY, int align, int color) {
        int y = startY;
        FontRenderer font = this.mc.field_71466_p;
        for (String line : lines) {
            int strWidth = font.func_78256_a(line);
            int xOffset = align == 1 ? (this.overlayWidth - strWidth) / 2 : (align == 2 ? this.overlayWidth - strWidth - 5 : 5);
            font.func_78261_a(line, xOffset, y, color);
            y += font.field_78288_b + 4;
        }
        return y;
    }

    private int renderDemoTextBlock(String[] lines, int startY, int align, int color) {
        int y = startY;
        FontRenderer font = this.mc.field_71466_p;
        for (String line : lines) {
            int strWidth = font.func_78256_a(line);
            int xOffset = align == 1 ? (this.overlayWidth - strWidth) / 2 : (align == 2 ? this.overlayWidth - strWidth - 5 : 5);
            font.func_78261_a(line, xOffset, y, color);
            y += font.field_78288_b + 4;
        }
        return y;
    }

    private void drawDecorativeLine(int y) {
        this.drawHorizontalLine(5, this.overlayWidth - 5, y, -8947849);
        this.drawHorizontalLine(5, this.overlayWidth - 5, y + 1, -5723992);
        this.drawHorizontalLine(5, this.overlayWidth - 5, y + 2, -1);
    }

    private void drawRect(int left, int top, int right, int bottom, int color) {
        Gui.func_73734_a((int)left, (int)top, (int)right, (int)bottom, (int)color);
    }

    private void drawHorizontalLine(int left, int right, int y, int color) {
        Gui.func_73734_a((int)left, (int)y, (int)right, (int)(y + 1), (int)color);
    }

    private void drawRectOutline(int left, int top, int right, int bottom, int color) {
        this.drawHorizontalLine(left, right, top, color);
        this.drawHorizontalLine(left, right, bottom - 1, color);
        Gui.func_73734_a((int)left, (int)top, (int)(left + 1), (int)bottom, (int)color);
        Gui.func_73734_a((int)(right - 1), (int)top, (int)right, (int)bottom, (int)color);
    }

    private String convertColorCodes(String text) {
        return text.replaceAll("&([0-9a-fk-or])", "\u00a7$1");
    }

    private ArrayList<String> splitLines(String text, int maxWidth) {
        ArrayList<String> lines = new ArrayList<String>();
        String[] words = text.split(" ");
        StringBuilder current = new StringBuilder();
        FontRenderer font = this.mc.field_71466_p;
        for (String word : words) {
            String test;
            String string = test = current.length() == 0 ? word : current + " " + word;
            if (font.func_78256_a(test) > maxWidth) {
                if (current.length() > 0) {
                    lines.add(current.toString());
                    current = new StringBuilder(word);
                    continue;
                }
                lines.add(word);
                continue;
            }
            if (current.length() > 0) {
                current.append(" ").append(word);
                continue;
            }
            current.append(word);
        }
        if (current.length() > 0) {
            lines.add(current.toString());
        }
        return lines;
    }
}

