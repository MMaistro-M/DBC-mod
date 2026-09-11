/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui.mainmenu;

import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.mainmenu.MainmenuAIGetPacket;
import kamkeel.npcs.network.packets.request.mainmenu.MainmenuAISavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.DataAI;
import noppes.npcs.client.gui.SubGuiNpcMovement;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.constants.EnumCombatPolicy;
import noppes.npcs.constants.EnumNavType;
import noppes.npcs.constants.EnumRoleType;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleMount;

public class GuiNpcAI
extends GuiNPCInterface2
implements ITextfieldListener,
IGuiData {
    private DataAI ai;

    public GuiNpcAI(EntityNPCInterface npc) {
        super(npc, 6);
        this.ai = npc.ais;
        PacketClient.sendClient(new MainmenuAIGetPacket());
    }

    @Override
    public void func_73866_w_() {
        String label;
        int returnValue;
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(0, "ai.enemyresponse", this.guiLeft + 5, this.guiTop + 17));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 86, this.guiTop + 10, 60, 20, new String[]{"gui.retaliate", "gui.panic", "gui.retreat", "gui.nothing"}, this.npc.ais.onAttack));
        this.addLabel(new GuiNpcLabel(1, "ai.door", this.guiLeft + 5, this.guiTop + 40));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 86, this.guiTop + 35, 60, 20, new String[]{"gui.break", "gui.open", "gui.disabled"}, this.npc.ais.doorInteract));
        this.addLabel(new GuiNpcLabel(12, "ai.swim", this.guiLeft + 5, this.guiTop + 65));
        this.addButton(new GuiNpcButton(7, this.guiLeft + 86, this.guiTop + 60, 60, 20, new String[]{"gui.no", "gui.yes"}, this.npc.ais.canSwim ? 1 : 0));
        this.addLabel(new GuiNpcLabel(13, "ai.shelter", this.guiLeft + 5, this.guiTop + 90));
        this.addButton(new GuiNpcButton(9, this.guiLeft + 86, this.guiTop + 85, 60, 20, new String[]{"gui.darkness", "gui.sunlight", "gui.disabled"}, this.npc.ais.findShelter));
        this.addLabel(new GuiNpcLabel(14, "ai.clearlos", this.guiLeft + 5, this.guiTop + 115));
        this.addButton(new GuiNpcButton(10, this.guiLeft + 86, this.guiTop + 110, 60, 20, new String[]{"gui.no", "gui.yes"}, this.npc.ais.directLOS ? 1 : 0));
        this.addLabel(new GuiNpcLabel(18, "ai.sprint", this.guiLeft + 5, this.guiTop + 140));
        this.addButton(new GuiNpcButton(16, this.guiLeft + 86, this.guiTop + 135, 60, 20, new String[]{"gui.no", "gui.yes"}, this.npc.ais.canSprint ? 1 : 0));
        this.addLabel(new GuiNpcLabel(10, "ai.avoidwater", this.guiLeft + 150, this.guiTop + 17));
        this.addButton(new GuiNpcButton(5, this.guiLeft + 230, this.guiTop + 10, 60, 20, new String[]{"gui.no", "gui.yes"}, this.npc.ais.avoidsWater ? 1 : 0));
        this.addLabel(new GuiNpcLabel(11, "ai.return", this.guiLeft + 150, this.guiTop + 40));
        int n = returnValue = this.npc.ais.returnToStart ? 1 : 0;
        if (this.npc.advanced.role == EnumRoleType.Mount && this.npc.roleInterface instanceof RoleMount) {
            returnValue = ((RoleMount)this.npc.roleInterface).getReturnToStartPreference() ? 1 : 0;
        }
        this.addButton(new GuiNpcButton(6, this.guiLeft + 230, this.guiTop + 35, 60, 20, new String[]{"gui.no", "gui.yes"}, returnValue));
        this.getButton(6).setEnabled(this.npc.advanced.role != EnumRoleType.Mount);
        this.addLabel(new GuiNpcLabel(17, "ai.leapattarget", this.guiLeft + 150, this.guiTop + 65));
        this.addButton(new GuiNpcButton(15, this.guiLeft + 230, this.guiTop + 60, 60, 20, new String[]{"gui.no", "ai.jump", "ai.pounce"}, this.npc.ais.leapType));
        this.addLabel(new GuiNpcLabel(15, "ai.indirect", this.guiLeft + 150, this.guiTop + 90));
        this.addButton(new GuiNpcButton(13, this.guiLeft + 230, this.guiTop + 85, 60, 20, new String[]{"gui.no", "gui.whendistant", "gui.whenhidden"}, this.ai.canFireIndirect));
        this.addLabel(new GuiNpcLabel(16, "ai.rangemelee", this.guiLeft + 150, this.guiTop + 115));
        this.addButton(new GuiNpcButton(14, this.guiLeft + 230, this.guiTop + 110, 60, 20, new String[]{this.npc.inventory.getProjectile() == null ? "gui.no" : "gui.always", "gui.untilclose", "gui.whenavailable"}, this.ai.useRangeMelee));
        if (this.ai.useRangeMelee >= 1) {
            this.addLabel(new GuiNpcLabel(20, "gui.minrange", this.guiLeft + 300, this.guiTop + 115));
            this.addTextField(new GuiNpcTextField(6, this, this.field_146289_q, this.guiLeft + 380, this.guiTop + 110, 30, 20, this.ai.distanceToMelee + ""));
            this.getTextField((int)6).integersOnly = true;
            this.getTextField(6).setMinMaxDefault(1, this.npc.stats.aggroRange, 5);
        }
        this.addLabel(new GuiNpcLabel(19, "ai.tacticalvariant", this.guiLeft + 150, this.guiTop + 140));
        this.addButton(new GuiNpcButton(17, this.guiLeft + 230, this.guiTop + 135, 60, 20, EnumNavType.names(), this.ai.tacticalVariant.ordinal()));
        if (this.ai.tacticalVariant != EnumNavType.Default && this.ai.tacticalVariant != EnumNavType.None) {
            label = "";
            switch (this.ai.tacticalVariant) {
                case Surround: {
                    label = "gui.orbitdistance";
                    break;
                }
                case HitNRun: {
                    label = "gui.fightifthisclose";
                    break;
                }
                case Ambush: {
                    label = "gui.ambushdistance";
                    break;
                }
                case Stalk: {
                    label = "gui.ambushdistance";
                    break;
                }
                default: {
                    label = "gui.engagedistance";
                }
            }
            this.addLabel(new GuiNpcLabel(21, label, this.guiLeft + 300, this.guiTop + 140));
            this.addTextField(new GuiNpcTextField(3, this, this.field_146289_q, this.guiLeft + 380, this.guiTop + 135, 30, 20, this.ai.tacticalRadius + ""));
            this.getTextField((int)3).integersOnly = true;
            this.getTextField(3).setMinMaxDefault(1, this.npc.stats.aggroRange, 5);
        }
        this.addLabel(new GuiNpcLabel(25, "ai.combatpolicy", this.guiLeft + 150, this.guiTop + 165));
        this.addButton(new GuiNpcButton(25, this.guiLeft + 230, this.guiTop + 160, 60, 20, EnumCombatPolicy.names(), this.ai.combatPolicy.ordinal()));
        if (this.ai.combatPolicy == EnumCombatPolicy.Stubborn) {
            label = "";
            label = "gui.combatchance";
            this.addLabel(new GuiNpcLabel(21, label, this.guiLeft + 300, this.guiTop + 165));
            this.addTextField(new GuiNpcTextField(4, this, this.field_146289_q, this.guiLeft + 380, this.guiTop + 160, 30, 20, this.ai.tacticalChance + ""));
            this.getTextField((int)4).integersOnly = true;
            this.getTextField(4).setMinMaxDefault(1, 100, 5);
        } else if (this.ai.combatPolicy == EnumCombatPolicy.Tactical) {
            this.addButton(new GuiNpcButton(40, this.guiLeft + 295, this.guiTop + 160, 60, 20, new String[]{"stats.normal", "stats.reverse"}, this.npc.ais.tacticalChance > 50 ? 1 : 0));
        }
        this.getButton(17).setEnabled(this.ai.onAttack == 0);
        this.getButton(15).setEnabled(this.ai.onAttack == 0);
        this.getButton(25).setEnabled(this.ai.onAttack == 0);
        this.getButton(13).setEnabled(this.npc.inventory.getProjectile() != null);
        this.getButton(14).setEnabled(this.npc.inventory.getProjectile() != null);
        this.getButton(10).setEnabled(this.ai.tacticalVariant != EnumNavType.Stalk && this.ai.tacticalVariant != EnumNavType.None);
        this.addLabel(new GuiNpcLabel(2, "ai.movement", this.guiLeft + 4, this.guiTop + 165));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 86, this.guiTop + 160, 60, 20, "selectServer.edit"));
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == 3) {
            this.ai.tacticalRadius = textfield.getInteger();
        }
        if (textfield.id == 4) {
            this.ai.tacticalChance = textfield.getInteger();
        }
        if (textfield.id == 6) {
            this.ai.distanceToMelee = textfield.getInteger();
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 0) {
            this.ai.onAttack = button.getValue();
            this.func_73866_w_();
        } else if (button.field_146127_k == 1) {
            this.ai.doorInteract = button.getValue();
        } else if (button.field_146127_k == 2) {
            this.setSubGui(new SubGuiNpcMovement(this.ai));
        } else if (button.field_146127_k == 5) {
            this.npc.setAvoidWater(button.getValue() == 1);
        } else if (button.field_146127_k == 6) {
            if (this.npc.advanced.role == EnumRoleType.Mount && this.npc.roleInterface instanceof RoleMount) {
                ((RoleMount)this.npc.roleInterface).setReturnToStartPreference(button.getValue() == 1);
                this.ai.returnToStart = false;
                this.func_73866_w_();
            } else {
                this.ai.returnToStart = button.getValue() == 1;
            }
        } else if (button.field_146127_k == 7) {
            this.ai.canSwim = button.getValue() == 1;
        } else if (button.field_146127_k == 9) {
            this.ai.findShelter = button.getValue();
        } else if (button.field_146127_k == 10) {
            this.ai.directLOS = button.getValue() == 1;
        } else if (button.field_146127_k == 13) {
            this.ai.canFireIndirect = button.getValue();
        } else if (button.field_146127_k == 14) {
            this.ai.useRangeMelee = button.getValue();
            this.func_73866_w_();
        } else if (button.field_146127_k == 15) {
            this.ai.leapType = (byte)button.getValue();
        } else if (button.field_146127_k == 16) {
            this.ai.canSprint = button.getValue() == 1;
        } else if (button.field_146127_k == 17) {
            this.ai.tacticalVariant = EnumNavType.values()[button.getValue()];
            this.ai.directLOS = EnumNavType.values()[button.getValue()] != EnumNavType.Stalk && this.ai.directLOS;
            this.func_73866_w_();
        } else if (button.field_146127_k == 25) {
            this.ai.combatPolicy = EnumCombatPolicy.values()[button.getValue()];
            this.func_73866_w_();
        } else if (button.field_146127_k == 40) {
            int val = button.getValue();
            this.ai.tacticalChance = val == 0 ? 0 : 100;
            this.func_73866_w_();
        }
    }

    @Override
    public void save() {
        PacketClient.sendClient(new MainmenuAISavePacket(this.ai.writeToNBT(new NBTTagCompound())));
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        this.ai.readToNBT(compound);
        this.func_73866_w_();
    }
}

