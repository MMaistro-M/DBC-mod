/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.roles;

import kamkeel.npcs.network.packets.request.jobs.JobSavePacket;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobHealer;

public class GuiNpcHealer
extends GuiNPCInterface2 {
    private JobHealer job;

    public GuiNpcHealer(EntityNPCInterface npc) {
        super(npc);
        this.job = (JobHealer)npc.jobInterface;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(1, "Healing Speed:", this.guiLeft + 60, this.guiTop + 110));
        this.addTextField(new GuiNpcTextField(1, this, this.field_146289_q, this.guiLeft + 130, this.guiTop + 105, 40, 20, this.job.speed + ""));
        this.getTextField((int)1).integersOnly = true;
        this.getTextField(1).setMinMaxDefault(1, Integer.MAX_VALUE, 8);
        this.addLabel(new GuiNpcLabel(2, "Range:", this.guiLeft + 60, this.guiTop + 133));
        this.addTextField(new GuiNpcTextField(2, this, this.field_146289_q, this.guiLeft + 130, this.guiTop + 128, 40, 20, this.job.range + ""));
        this.getTextField((int)2).integersOnly = true;
        this.getTextField(2).setMinMaxDefault(2, Integer.MAX_VALUE, 5);
    }

    @Override
    public void elementClicked() {
    }

    @Override
    public void save() {
        this.job.speed = this.getTextField(1).getInteger();
        this.job.range = this.getTextField(2).getInteger();
        JobSavePacket.saveJob(this.job);
    }
}

