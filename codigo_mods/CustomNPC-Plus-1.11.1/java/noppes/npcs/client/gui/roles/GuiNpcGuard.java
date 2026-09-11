/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 */
package noppes.npcs.client.gui.roles;

import java.util.ArrayList;
import kamkeel.npcs.network.packets.request.jobs.JobSavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobGuard;

public class GuiNpcGuard
extends GuiNPCInterface2 {
    private JobGuard role;
    private GuiCustomScroll scroll1;
    private GuiCustomScroll scroll2;

    public GuiNpcGuard(EntityNPCInterface npc) {
        super(npc);
        this.role = (JobGuard)npc.jobInterface;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(0, "guard.animals", this.guiLeft + 10, this.guiTop + 9));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 85, this.guiTop + 4, 50, 20, new String[]{"gui.no", "gui.yes"}, this.role.attacksAnimals ? 1 : 0));
        this.addLabel(new GuiNpcLabel(1, "guard.mobs", this.guiLeft + 140, this.guiTop + 9));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 222, this.guiTop + 4, 50, 20, new String[]{"gui.no", "gui.yes"}, this.role.attackHostileMobs ? 1 : 0));
        this.addLabel(new GuiNpcLabel(2, "guard.creepers", this.guiLeft + 275, this.guiTop + 9));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 360, this.guiTop + 4, 50, 20, new String[]{"gui.no", "gui.yes"}, this.role.attackCreepers ? 1 : 0));
        this.getButton((int)2).field_146124_l = this.role.attackHostileMobs;
        this.addLabel(new GuiNpcLabel(3, "guard.specifictargets", this.guiLeft + 10, this.guiTop + 31));
        this.addButton(new GuiNpcButton(3, this.guiLeft + 85, this.guiTop + 26, 50, 20, new String[]{"gui.no", "gui.yes"}, this.role.specific ? 1 : 0));
        if (this.role.specific) {
            if (this.scroll1 == null) {
                this.scroll1 = new GuiCustomScroll(this, 0);
                this.scroll1.setSize(175, 154);
            }
            this.scroll1.guiLeft = this.guiLeft + 4;
            this.scroll1.guiTop = this.guiTop + 58;
            this.addScroll(this.scroll1);
            this.addLabel(new GuiNpcLabel(11, "guard.availableTargets", this.guiLeft + 4, this.guiTop + 48));
            if (this.scroll2 == null) {
                this.scroll2 = new GuiCustomScroll(this, 1);
                this.scroll2.setSize(175, 154);
            }
            this.scroll2.guiLeft = this.guiLeft + 235;
            this.scroll2.guiTop = this.guiTop + 58;
            this.addScroll(this.scroll2);
            this.addLabel(new GuiNpcLabel(12, "guard.currentTargets", this.guiLeft + 235, this.guiTop + 48));
            ArrayList<String> all = new ArrayList<String>();
            for (Object entity : EntityList.field_75625_b.keySet()) {
                String name = "entity." + entity + ".name";
                Class cl = (Class)EntityList.field_75625_b.get(entity);
                if (this.role.targets.contains(name) || EntityNPCInterface.class.isAssignableFrom(cl) || !EntityLivingBase.class.isAssignableFrom(cl)) continue;
                all.add(name);
            }
            this.scroll1.setList(all);
            this.scroll2.setList(this.role.targets);
            this.addButton(new GuiNpcButton(11, this.guiLeft + 180, this.guiTop + 80, 55, 20, ">"));
            this.addButton(new GuiNpcButton(12, this.guiLeft + 180, this.guiTop + 102, 55, 20, "<"));
            this.addButton(new GuiNpcButton(13, this.guiLeft + 180, this.guiTop + 130, 55, 20, ">>"));
            this.addButton(new GuiNpcButton(14, this.guiLeft + 180, this.guiTop + 152, 55, 20, "<<"));
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 0) {
            boolean bl = this.role.attacksAnimals = button.getValue() == 1;
        }
        if (button.field_146127_k == 1) {
            this.role.attackHostileMobs = button.getValue() == 1;
            this.func_73866_w_();
        }
        if (button.field_146127_k == 2) {
            boolean bl = this.role.attackCreepers = button.getValue() == 1;
        }
        if (button.field_146127_k == 3) {
            this.role.specific = button.getValue() == 1;
            this.func_73866_w_();
        }
        if (button.field_146127_k == 11 && this.scroll1.hasSelected()) {
            this.role.targets.add(this.scroll1.getSelected());
            this.scroll1.selected = -1;
            this.scroll1.selected = -1;
            this.func_73866_w_();
        }
        if (button.field_146127_k == 12 && this.scroll2.hasSelected()) {
            this.role.targets.remove(this.scroll2.getSelected());
            this.scroll2.selected = -1;
            this.func_73866_w_();
        }
        if (button.field_146127_k == 13) {
            this.role.targets.clear();
            ArrayList<String> all = new ArrayList<String>();
            for (Object entity : EntityList.field_75625_b.keySet()) {
                String name = "entity." + entity + ".name";
                Class cl = (Class)EntityList.field_75625_b.get(entity);
                if (!EntityLivingBase.class.isAssignableFrom(cl)) continue;
                all.add(name);
            }
            this.role.targets = all;
            this.scroll1.selected = -1;
            this.scroll2.selected = -1;
            this.func_73866_w_();
        }
        if (button.field_146127_k == 14) {
            this.role.targets.clear();
            this.scroll1.selected = -1;
            this.scroll2.selected = -1;
            this.func_73866_w_();
        }
    }

    @Override
    public void save() {
        JobSavePacket.saveJob(this.role);
    }
}

