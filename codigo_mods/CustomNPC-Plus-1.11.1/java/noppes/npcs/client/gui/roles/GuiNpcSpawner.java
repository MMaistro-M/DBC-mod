/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client.gui.roles;

import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.jobs.JobSavePacket;
import kamkeel.npcs.network.packets.request.jobs.JobSpawnerAddPacket;
import kamkeel.npcs.network.packets.request.jobs.JobSpawnerRemovePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.GuiNpcMobSpawnerSelector;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobSpawner;

public class GuiNpcSpawner
extends GuiNPCInterface2
implements ITextfieldListener,
IGuiData {
    private JobSpawner job;
    private int slot = -1;
    public String title1 = "gui.selectnpc";
    public String title2 = "gui.selectnpc";
    public String title3 = "gui.selectnpc";
    public String title4 = "gui.selectnpc";
    public String title5 = "gui.selectnpc";
    public String title6 = "gui.selectnpc";
    public String title7 = "gui.selectnpc";
    public String title8 = "gui.selectnpc";

    public GuiNpcSpawner(EntityNPCInterface npc) {
        super(npc);
        this.job = (JobSpawner)npc.jobInterface;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 6;
        this.addButton(new GuiNpcButton(20, this.guiLeft + 20, y, 20, 20, "X"));
        this.addLabel(new GuiNpcLabel(0, "1:", this.guiLeft + 4, y + 5));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 45, y, 140, 20, this.title1));
        this.addLabel(new GuiNpcLabel(6, "spawner.diesafter", this.guiLeft + 4 + 190, y + 5));
        this.addButton(new GuiNpcButton(26, this.guiLeft + 370, y, 40, 20, new String[]{"gui.yes", "gui.no"}, this.job.doesntDie ? 1 : 0));
        this.addButton(new GuiNpcButton(21, this.guiLeft + 20, y += 23, 20, 20, "X"));
        this.addLabel(new GuiNpcLabel(1, "2:", this.guiLeft + 4, y + 5));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 45, y, 140, 20, this.title2));
        this.addLabel(new GuiNpcLabel(11, "spawner.despawn", this.guiLeft + 4 + 190, y + 5));
        this.addButton(new GuiNpcButton(11, this.guiLeft + 370, y, 40, 20, new String[]{"gui.no", "gui.yes"}, this.job.despawnOnTargetLost ? 1 : 0));
        this.addButton(new GuiNpcButton(22, this.guiLeft + 20, y += 23, 20, 20, "X"));
        this.addLabel(new GuiNpcLabel(2, "3:", this.guiLeft + 4, y + 5));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 45, y, 140, 20, this.title3));
        this.addLabel(new GuiNpcLabel(27, "spawner.despawnsummon", this.guiLeft + 4 + 190, y + 5));
        this.addButton(new GuiNpcButton(27, this.guiLeft + 370, y, 40, 20, new String[]{"gui.no", "gui.yes"}, this.job.despawnOnSummonerDeath ? 0 : 1));
        this.addButton(new GuiNpcButton(23, this.guiLeft + 20, y += 23, 20, 20, "X"));
        this.addLabel(new GuiNpcLabel(3, "4:", this.guiLeft + 4, y + 5));
        this.addButton(new GuiNpcButton(3, this.guiLeft + 45, y, 140, 20, this.title4));
        this.addButton(new GuiNpcButton(24, this.guiLeft + 20, y += 23, 20, 20, "X"));
        this.addLabel(new GuiNpcLabel(4, "5:", this.guiLeft + 4, y + 5));
        this.addButton(new GuiNpcButton(4, this.guiLeft + 45, y, 140, 20, this.title5));
        this.addButton(new GuiNpcButton(25, this.guiLeft + 20, y += 23, 20, 20, "X"));
        this.addLabel(new GuiNpcLabel(5, "6:", this.guiLeft + 4, y + 5));
        this.addButton(new GuiNpcButton(5, this.guiLeft + 45, y, 140, 20, this.title6));
        y += 23;
        this.addLabel(new GuiNpcLabel(7, StatCollector.func_74838_a((String)"spawner.posoffset") + " X:", this.guiLeft + 4 + 190, (y += 23) + 5));
        this.addTextField(new GuiNpcTextField(7, this, this.field_146289_q, this.guiLeft + 99 + 190, y, 24, 20, this.job.xOffset + ""));
        this.getTextField((int)7).integersOnly = true;
        this.getTextField(7).setMinMaxDefault(-9, 9, 0);
        this.addLabel(new GuiNpcLabel(8, "Y:", this.guiLeft + 125 + 190, y + 5));
        this.addTextField(new GuiNpcTextField(8, this, this.field_146289_q, this.guiLeft + 135 + 190, y, 24, 20, this.job.yOffset + ""));
        this.getTextField((int)8).integersOnly = true;
        this.getTextField(8).setMinMaxDefault(-9, 9, 0);
        this.addLabel(new GuiNpcLabel(9, "Z:", this.guiLeft + 161 + 190, y + 5));
        this.addTextField(new GuiNpcTextField(9, this, this.field_146289_q, this.guiLeft + 171 + 190, y, 24, 20, this.job.zOffset + ""));
        this.getTextField((int)9).integersOnly = true;
        this.getTextField(9).setMinMaxDefault(-9, 9, 0);
        this.addLabel(new GuiNpcLabel(10, "spawner.type", this.guiLeft + 4 + 190, (y += 23) + 5));
        this.addButton(new GuiNpcButton(10, this.guiLeft + 80 + 190, y, 100, 20, new String[]{"spawner.one", "spawner.all", "spawner.random", "spawner.summoner"}, this.job.spawnType));
        if (this.job.despawnOnSummonerDeath) {
            this.job.doesntDie = true;
            this.getButton(26).setEnabled(false);
            this.getButton(26).setDisplay(1);
        }
        if (this.job.spawnType == 3) {
            this.job.doesntDie = true;
            this.getButton(26).setEnabled(false);
            this.getButton(26).setDisplay(1);
            this.job.despawnOnTargetLost = false;
            this.getButton(11).setEnabled(false);
            this.getButton(11).setDisplay(0);
            this.job.despawnOnSummonerDeath = false;
            this.getButton(27).setEnabled(false);
            this.getButton(27).setDisplay(0);
        }
    }

    @Override
    public void elementClicked() {
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k >= 0 && button.field_146127_k < 6) {
            this.slot = button.field_146127_k + 1;
            this.setSubGui(new GuiNpcMobSpawnerSelector());
        }
        if (button.field_146127_k >= 20 && button.field_146127_k < 26) {
            int removeID = button.field_146127_k - 19;
            this.job.setJobCompound(removeID, null);
            PacketClient.sendClient(new JobSpawnerRemovePacket(button.field_146127_k - 19));
        }
        if (button.field_146127_k == 26) {
            boolean bl = this.job.doesntDie = button.getValue() == 1;
        }
        if (button.field_146127_k == 27) {
            boolean bl = this.job.despawnOnSummonerDeath = button.getValue() == 1;
            if (this.job.despawnOnSummonerDeath) {
                this.job.doesntDie = true;
                this.getButton(26).setEnabled(false);
                this.getButton(26).setDisplay(1);
            } else if (this.job.spawnType != 3) {
                this.getButton(26).setEnabled(true);
            }
        }
        if (button.field_146127_k == 10) {
            this.job.spawnType = button.getValue();
            if (this.job.spawnType == 3) {
                this.job.doesntDie = true;
                this.getButton(26).setEnabled(false);
                this.getButton(26).setDisplay(1);
                this.job.despawnOnTargetLost = false;
                this.getButton(11).setEnabled(false);
                this.getButton(11).setDisplay(0);
                this.job.despawnOnSummonerDeath = false;
                this.getButton(27).setEnabled(false);
                this.getButton(27).setDisplay(0);
            } else {
                if (!this.job.despawnOnSummonerDeath) {
                    this.getButton(26).setEnabled(true);
                }
                this.getButton(11).setEnabled(true);
                this.getButton(27).setEnabled(true);
            }
        }
        if (button.field_146127_k == 11) {
            this.job.despawnOnTargetLost = button.getValue() == 1;
        }
    }

    @Override
    public void closeSubGui(SubGuiInterface gui) {
        super.closeSubGui(gui);
        GuiNpcMobSpawnerSelector selector = (GuiNpcMobSpawnerSelector)gui;
        if (selector.isServer) {
            String selected = selector.getSelected();
            if (selected != null) {
                PacketClient.sendClient(new JobSpawnerAddPacket(selector.isServer, selected, selector.activeTab, this.slot));
            }
        } else {
            NBTTagCompound compound = selector.getCompound();
            if (compound != null) {
                this.job.setJobCompound(this.slot, compound);
                PacketClient.sendClient(new JobSpawnerAddPacket(selector.isServer, this.slot, compound));
            }
        }
        this.func_73866_w_();
    }

    @Override
    public void save() {
        NBTTagCompound compound = this.job.writeToNBT(new NBTTagCompound());
        this.job.cleanCompound(compound);
        PacketClient.sendClient(new JobSavePacket(compound));
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == 7) {
            this.job.xOffset = textfield.getInteger();
        }
        if (textfield.id == 8) {
            this.job.yOffset = textfield.getInteger();
        }
        if (textfield.id == 9) {
            this.job.zOffset = textfield.getInteger();
        }
        this.save();
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        this.title1 = compound.func_74779_i("Title1");
        this.title2 = compound.func_74779_i("Title2");
        this.title3 = compound.func_74779_i("Title3");
        this.title4 = compound.func_74779_i("Title4");
        this.title5 = compound.func_74779_i("Title5");
        this.title6 = compound.func_74779_i("Title6");
        this.func_73866_w_();
    }
}

