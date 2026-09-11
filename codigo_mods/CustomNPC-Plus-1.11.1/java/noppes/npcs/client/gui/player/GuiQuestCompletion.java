/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.player;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.TextBlockClient;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.ITopButtonListener;
import noppes.npcs.controllers.data.Quest;
import org.lwjgl.opengl.GL11;

public class GuiQuestCompletion
extends GuiNPCInterface
implements ITopButtonListener {
    private Quest quest;
    private final ResourceLocation resource = new ResourceLocation("customnpcs", "textures/gui/menubg.png");

    public GuiQuestCompletion(Quest quest) {
        this.xSize = 176;
        this.ySize = 222;
        this.quest = quest;
        this.drawDefaultBackground = false;
        this.title = "";
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        String questTitle = this.quest.title;
        int left = (this.xSize - this.field_146289_q.func_78256_a(questTitle)) / 2;
        this.addLabel(new GuiNpcLabel(0, questTitle, this.guiLeft + left, this.guiTop + 4));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 38, this.guiTop + this.ySize - 24, 100, 20, StatCollector.func_74838_a((String)"quest.complete")));
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        this.func_146276_q_();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(this.resource);
        this.func_73729_b(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        this.func_73730_a(this.guiLeft + 4, this.guiLeft + 170, this.guiTop + 13, -16777216 + CustomNpcResourceListener.DefaultTextColor);
        this.drawQuestText();
        super.func_73863_a(i, j, f);
    }

    private void drawQuestText() {
        int xoffset = this.guiLeft + 4;
        TextBlockClient block = new TextBlockClient(this.quest.completeText, 172, true, this.player);
        int yoffset = this.guiTop + 20;
        for (int i = 0; i < block.lines.size(); ++i) {
            String text = ((IChatComponent)block.lines.get(i)).func_150254_d();
            this.field_146289_q.func_78276_b(text, this.guiLeft + 4, this.guiTop + 16 + i * this.field_146289_q.field_78288_b, CustomNpcResourceListener.DefaultTextColor);
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton.field_146127_k == 0) {
            this.close();
        }
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
    }

    @Override
    public void func_73869_a(char c, int i) {
        if (i == 1 || this.isInventoryKey(i)) {
            this.close();
        }
    }

    @Override
    public void save() {
    }
}

