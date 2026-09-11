/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcButtonYesNo;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.GuiScrollWindow;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.controllers.data.SpawnData;

public class SubGuiSpawningOptions
extends SubGuiInterface
implements ITextfieldListener {
    private final SpawnData data;

    public SubGuiSpawningOptions(SpawnData data) {
        this.data = data;
        this.setBackground("menubg.png");
        this.xSize = 256;
        this.ySize = 216;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int scrollX = this.guiLeft + 4;
        int scrollY = this.guiTop + 4;
        int scrollW = this.xSize - 8;
        int scrollH = this.ySize - 34;
        GuiScrollWindow sw = new GuiScrollWindow(this, scrollX, scrollY, scrollW, scrollH, 0);
        sw.drawDefaultBackground = false;
        this.addScrollableGui(0, sw);
        int labelX = 4;
        int controlX = 132;
        int y = 6;
        sw.addButton(new GuiNpcButtonYesNo(10, controlX, y, this.data.animalSpawning));
        sw.addLabel(new GuiNpcLabel(10, "spawning.animalSpawning", labelX, y + 5));
        sw.getButton(10).setHoverText("tooltip.naturalspawns.options.animal");
        sw.addButton(new GuiNpcButtonYesNo(11, controlX, y += 22, this.data.monsterSpawning));
        sw.addLabel(new GuiNpcLabel(11, "spawning.monsterSpawning", labelX, y + 5));
        sw.getButton(11).setHoverText("tooltip.naturalspawns.options.monster");
        sw.addButton(new GuiNpcButtonYesNo(12, controlX, y += 22, this.data.liquidSpawning));
        sw.addLabel(new GuiNpcLabel(12, "spawning.liquidSpawning", labelX, y + 5));
        sw.getButton(12).setHoverText("tooltip.naturalspawns.options.liquid");
        sw.addButton(new GuiNpcButtonYesNo(13, controlX, y += 22, this.data.airSpawning));
        sw.addLabel(new GuiNpcLabel(13, "spawning.airSpawning", labelX, y + 5));
        sw.getButton(13).setHoverText("tooltip.naturalspawns.options.air");
        sw.addTextField(new GuiNpcTextField(14, this, controlX, y += 22, 60, 20, "" + this.data.spawnHeightMin));
        sw.addLabel(new GuiNpcLabel(14, "spawning.minHeight", labelX, y + 5));
        sw.getTextField((int)14).integersOnly = true;
        sw.getTextField(14).setMinMaxDefault(-2147483647, Integer.MAX_VALUE, 0);
        sw.getTextField(14).setHoverText("tooltip.naturalspawns.options.minHeight");
        sw.addTextField(new GuiNpcTextField(15, this, controlX, y += 22, 60, 20, "" + this.data.spawnHeightMax));
        sw.addLabel(new GuiNpcLabel(15, "spawning.maxHeight", labelX, y + 5));
        sw.getTextField((int)15).integersOnly = true;
        sw.getTextField(15).setMinMaxDefault(-2147483647, Integer.MAX_VALUE, 0);
        sw.getTextField(15).setHoverText("tooltip.naturalspawns.options.maxHeight");
        sw.addTextField(new GuiNpcTextField(16, this, controlX, y += 22, 60, 20, "" + this.data.maxAlive));
        sw.addLabel(new GuiNpcLabel(16, "spawning.maxAlive", labelX, y + 5));
        sw.getTextField((int)16).integersOnly = true;
        sw.getTextField(16).setMinMaxDefault(0, Integer.MAX_VALUE, 0);
        sw.getTextField(16).setHoverText("tooltip.naturalspawns.options.maxAlive");
        sw.addTextField(new GuiNpcTextField(17, this, controlX, y += 22, 60, 20, "" + this.data.cooldownTicks));
        sw.addLabel(new GuiNpcLabel(17, "spawning.cooldownTicks", labelX, y + 5));
        sw.getTextField((int)17).integersOnly = true;
        sw.getTextField(17).setMinMaxDefault(0, Integer.MAX_VALUE, 0);
        sw.getTextField(17).setHoverText("tooltip.naturalspawns.options.cooldownTicks");
        sw.addTextField(new GuiNpcTextField(18, this, controlX, y += 22, 60, 20, "" + this.data.attemptsPerCycle));
        sw.addLabel(new GuiNpcLabel(18, "spawning.attemptsPerCycle", labelX, y + 5));
        sw.getTextField((int)18).integersOnly = true;
        sw.getTextField(18).setMinMaxDefault(1, Integer.MAX_VALUE, 1);
        sw.getTextField(18).setHoverText("tooltip.naturalspawns.options.attemptsPerCycle");
        sw.addTextField(new GuiNpcTextField(19, this, controlX, y += 22, 60, 20, "" + this.data.playerMinDistance));
        sw.addLabel(new GuiNpcLabel(19, "spawning.playerMinDistance", labelX, y + 5));
        sw.getTextField((int)19).integersOnly = true;
        sw.getTextField(19).setMinMaxDefault(0, Integer.MAX_VALUE, 24);
        sw.getTextField(19).setHoverText("tooltip.naturalspawns.options.playerMinDistance");
        sw.addButton(new GuiNpcButton(20, controlX, y += 22, 100, 20, new String[]{"spawning.despawn.forceNatural", "spawning.despawn.preserve", "spawning.despawn.forcePersistent"}, this.data.despawnMode));
        sw.addLabel(new GuiNpcLabel(20, "spawning.despawnMode", labelX, y + 5));
        sw.getButton(20).setHoverText("tooltip.naturalspawns.options.despawnMode");
        sw.maxScrollY = Math.max(0, (y += 22) - sw.clipHeight + 8);
        this.addButton(new GuiNpcButton(100, this.guiLeft + this.xSize - 64, this.guiTop + this.ySize - 24, 60, 20, "gui.done"));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 10) {
            boolean bl = this.data.animalSpawning = button.getValue() == 1;
        }
        if (button.field_146127_k == 11) {
            boolean bl = this.data.monsterSpawning = button.getValue() == 1;
        }
        if (button.field_146127_k == 12) {
            boolean bl = this.data.liquidSpawning = button.getValue() == 1;
        }
        if (button.field_146127_k == 13) {
            boolean bl = this.data.airSpawning = button.getValue() == 1;
        }
        if (button.field_146127_k == 20) {
            this.data.setDespawnMode(button.getValue());
        }
        if (button.field_146127_k == 100) {
            this.close();
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == 14) {
            this.data.spawnHeightMin = textfield.getInteger();
        }
        if (textfield.id == 15) {
            this.data.spawnHeightMax = textfield.getInteger();
        }
        if (textfield.id == 16) {
            this.data.setMaxAlive(textfield.getInteger());
        }
        if (textfield.id == 17) {
            this.data.setCooldownTicks(textfield.getInteger());
        }
        if (textfield.id == 18) {
            this.data.setAttemptsPerCycle(textfield.getInteger());
        }
        if (textfield.id == 19) {
            this.data.setPlayerMinDistance(textfield.getInteger());
        }
    }
}

