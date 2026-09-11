/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.roles;

import java.util.ArrayList;
import java.util.List;
import kamkeel.npcs.network.packets.request.jobs.JobSavePacket;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobFollower;

public class GuiNpcFollowerJob
extends GuiNPCInterface2
implements ICustomScrollListener {
    private JobFollower job;
    private GuiCustomScroll scroll;

    public GuiNpcFollowerJob(EntityNPCInterface npc) {
        super(npc);
        this.job = (JobFollower)npc.jobInterface;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(1, "gui.name", this.guiLeft + 6, this.guiTop + 110));
        this.addTextField(new GuiNpcTextField(1, this, this.field_146289_q, this.guiLeft + 50, this.guiTop + 105, 200, 20, this.job.name));
        this.scroll = new GuiCustomScroll(this, 0);
        this.scroll.setSize(143, 208);
        this.scroll.guiLeft = this.guiLeft + 268;
        this.scroll.guiTop = this.guiTop + 4;
        this.addScroll(this.scroll);
        ArrayList<String> names = new ArrayList<String>();
        List list = this.npc.field_70170_p.func_72872_a(EntityNPCInterface.class, this.npc.field_70121_D.func_72314_b(40.0, 40.0, 40.0));
        for (EntityNPCInterface npc : list) {
            if (npc == this.npc || names.contains(npc.display.name)) continue;
            names.add(npc.display.name);
        }
        this.scroll.setList(names);
    }

    @Override
    public void save() {
        this.job.name = this.getTextField(1).func_146179_b();
        JobSavePacket.saveJob(this.job);
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        this.getTextField(1).func_146180_a(guiCustomScroll.getSelected());
    }
}

