/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui.mainmenu;

import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.jobs.JobGetPacket;
import kamkeel.npcs.network.packets.request.mainmenu.MainmenuAdvancedGetPacket;
import kamkeel.npcs.network.packets.request.mainmenu.MainmenuAdvancedSavePacket;
import kamkeel.npcs.network.packets.request.role.RoleGetPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.NoppesStringUtils;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.advanced.GuiNPCAbilities;
import noppes.npcs.client.gui.advanced.GuiNPCAdvancedLinkedNpc;
import noppes.npcs.client.gui.advanced.GuiNPCDialogNpcOptions;
import noppes.npcs.client.gui.advanced.GuiNPCFactionSetup;
import noppes.npcs.client.gui.advanced.GuiNPCLinesMenu;
import noppes.npcs.client.gui.advanced.GuiNPCMagic;
import noppes.npcs.client.gui.advanced.GuiNPCMarks;
import noppes.npcs.client.gui.advanced.GuiNPCNightSetup;
import noppes.npcs.client.gui.advanced.GuiNPCSoundsMenu;
import noppes.npcs.client.gui.advanced.GuiNPCTagSetup;
import noppes.npcs.client.gui.roles.GuiNpcBard;
import noppes.npcs.client.gui.roles.GuiNpcCompanion;
import noppes.npcs.client.gui.roles.GuiNpcConversation;
import noppes.npcs.client.gui.roles.GuiNpcFollowerJob;
import noppes.npcs.client.gui.roles.GuiNpcGuard;
import noppes.npcs.client.gui.roles.GuiNpcHealer;
import noppes.npcs.client.gui.roles.GuiNpcMount;
import noppes.npcs.client.gui.roles.GuiNpcSpawner;
import noppes.npcs.client.gui.roles.GuiNpcTransporter;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.constants.EnumJobType;
import noppes.npcs.constants.EnumRoleType;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNpcAdvanced
extends GuiNPCInterface2
implements IGuiData {
    private boolean hasChanges = false;

    public GuiNpcAdvanced(EntityNPCInterface npc) {
        super(npc, 4);
        PacketClient.sendClient(new MainmenuAdvancedGetPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 10;
        this.addButton(new GuiNpcButton(3, this.guiLeft + 85 + 160, y, 52, 20, "selectServer.edit"));
        this.addButton(new GuiNpcButton(8, this.guiLeft + 85, y, 155, 20, new String[]{"role.none", "role.trader", "role.follower", "role.bank", "role.transporter", "role.mailman", NoppesStringUtils.translate("role.companion", "(WIP)"), "role.mount", "role.auctioneer"}, this.npc.advanced.role.ordinal()));
        this.getButton(3).setEnabled(this.npc.advanced.role != EnumRoleType.None && this.npc.advanced.role != EnumRoleType.Postman && this.npc.advanced.role != EnumRoleType.Auctioneer);
        this.addButton(new GuiNpcButton(4, this.guiLeft + 85 + 160, y += 22, 52, 20, "selectServer.edit"));
        this.addButton(new GuiNpcButton(5, this.guiLeft + 85, y, 155, 20, new String[]{"job.none", "job.bard", "job.healer", "job.guard", "job.itemgiver", "role.follower", "job.spawner", "job.conversation", "job.chunkloader"}, this.npc.advanced.job.ordinal()));
        this.getButton(4).setEnabled(this.npc.advanced.job != EnumJobType.None && this.npc.advanced.job != EnumJobType.ChunkLoader);
        this.addButton(new GuiNpcButton(7, this.guiLeft + 15, y += 22, 190, 20, "advanced.lines"));
        this.addButton(new GuiNpcButton(9, this.guiLeft + 208, y, 190, 20, "menu.factions"));
        this.addButton(new GuiNpcButton(10, this.guiLeft + 15, y += 22, 190, 20, "dialog.dialogs"));
        this.addButton(new GuiNpcButton(11, this.guiLeft + 208, y, 190, 20, "advanced.sounds"));
        this.addButton(new GuiNpcButton(12, this.guiLeft + 15, y += 22, 190, 20, "advanced.night"));
        this.addButton(new GuiNpcButton(13, this.guiLeft + 208, y, 190, 20, "global.linked"));
        this.addButton(new GuiNpcButton(14, this.guiLeft + 15, y += 22, 190, 20, "menu.tags"));
        this.addButton(new GuiNpcButton(15, this.guiLeft + 208, y, 190, 20, "advanced.marks"));
        this.addButton(new GuiNpcButton(16, this.guiLeft + 15, y += 22, 190, 20, "menu.magics"));
        this.addButton(new GuiNpcButton(17, this.guiLeft + 208, y, 190, 20, "menu.abilities"));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 3) {
            this.save();
            PacketClient.sendClient(new RoleGetPacket());
        }
        if (button.field_146127_k == 8) {
            this.hasChanges = true;
            this.npc.advanced.setRole(button.getValue());
            this.getButton(3).setEnabled(this.npc.advanced.role != EnumRoleType.None && this.npc.advanced.role != EnumRoleType.Postman && this.npc.advanced.role != EnumRoleType.Auctioneer);
        }
        if (button.field_146127_k == 4) {
            this.save();
            PacketClient.sendClient(new JobGetPacket());
        }
        if (button.field_146127_k == 5) {
            this.hasChanges = true;
            this.npc.advanced.setJob(button.getValue());
            this.getButton(4).setEnabled(this.npc.advanced.job != EnumJobType.None && this.npc.advanced.job != EnumJobType.ChunkLoader);
        }
        if (button.field_146127_k == 9) {
            this.save();
            NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNPCFactionSetup(this.npc));
        }
        if (button.field_146127_k == 10) {
            this.save();
            NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNPCDialogNpcOptions(this.npc, this));
        }
        if (button.field_146127_k == 11) {
            this.save();
            NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNPCSoundsMenu(this.npc));
        }
        if (button.field_146127_k == 7) {
            this.save();
            NoppesUtil.openGUI((EntityPlayer)this.player, (Object)new GuiNPCLinesMenu(this.npc));
        }
        if (button.field_146127_k == 12) {
            this.save();
            NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNPCNightSetup(this.npc));
        }
        if (button.field_146127_k == 13) {
            this.save();
            NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNPCAdvancedLinkedNpc(this.npc));
        }
        if (button.field_146127_k == 14) {
            this.save();
            NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNPCTagSetup(this.npc));
        }
        if (button.field_146127_k == 15) {
            this.save();
            NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNPCMarks(this.npc));
        }
        if (button.field_146127_k == 16) {
            this.save();
            NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNPCMagic(this.npc));
        }
        if (button.field_146127_k == 17) {
            this.save();
            NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNPCAbilities(this.npc));
        }
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        if (compound.func_74764_b("RoleData")) {
            this.npc.advanced.setRole(compound.func_74762_e("RoleOrdinal"));
            if (this.npc.roleInterface != null) {
                this.npc.roleInterface.readFromNBT(compound);
            }
            if (this.npc.advanced.role == EnumRoleType.Trader) {
                NoppesUtil.requestOpenGUI(EnumGuiType.SetupTrader);
            } else if (this.npc.advanced.role == EnumRoleType.Follower) {
                NoppesUtil.requestOpenGUI(EnumGuiType.SetupFollower);
            } else if (this.npc.advanced.role == EnumRoleType.Bank) {
                NoppesUtil.requestOpenGUI(EnumGuiType.SetupBank);
            } else if (this.npc.advanced.role == EnumRoleType.Transporter) {
                this.displayGuiScreen(new GuiNpcTransporter(this.npc));
            } else if (this.npc.advanced.role == EnumRoleType.Companion) {
                this.displayGuiScreen(new GuiNpcCompanion(this.npc));
            } else if (this.npc.advanced.role == EnumRoleType.Mount) {
                this.displayGuiScreen(new GuiNpcMount(this.npc));
            }
        } else if (compound.func_74764_b("JobData")) {
            if (this.npc.jobInterface != null) {
                this.npc.jobInterface.readFromNBT(compound);
            }
            if (this.npc.advanced.job == EnumJobType.Bard) {
                NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNpcBard(this.npc));
            } else if (this.npc.advanced.job == EnumJobType.Healer) {
                NoppesUtil.openGUI((EntityPlayer)this.player, (Object)new GuiNpcHealer(this.npc));
            } else if (this.npc.advanced.job == EnumJobType.Guard) {
                NoppesUtil.openGUI((EntityPlayer)this.player, (Object)new GuiNpcGuard(this.npc));
            } else if (this.npc.advanced.job == EnumJobType.ItemGiver) {
                NoppesUtil.requestOpenGUI(EnumGuiType.SetupItemGiver);
            } else if (this.npc.advanced.job == EnumJobType.Follower) {
                NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNpcFollowerJob(this.npc));
            } else if (this.npc.advanced.job == EnumJobType.Spawner) {
                NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNpcSpawner(this.npc));
            } else if (this.npc.advanced.job == EnumJobType.Conversation) {
                NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNpcConversation(this.npc));
            }
        } else {
            this.npc.advanced.readToNBT(compound);
            this.func_73866_w_();
        }
    }

    @Override
    public void save() {
        if (this.hasChanges) {
            PacketClient.sendClient(new MainmenuAdvancedSavePacket(this.npc.advanced.writeToNBT(new NBTTagCompound())));
            this.hasChanges = false;
        }
    }
}

