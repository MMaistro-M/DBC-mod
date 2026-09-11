/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui.roles;

import java.util.Locale;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.role.RoleSavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleMount;

public class GuiNpcMount
extends GuiNPCInterface2
implements ITextfieldListener {
    private final RoleMount role;

    public GuiNpcMount(EntityNPCInterface npc) {
        super(npc);
        this.role = npc.roleInterface instanceof RoleMount ? (RoleMount)npc.roleInterface : null;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        if (this.role == null) {
            this.close();
            return;
        }
        int leftLabelX = this.guiLeft + 4;
        int leftFieldX = this.guiLeft + 116;
        int leftY = this.guiTop + 6;
        leftY = this.addOffsetRow(0, 10, "role.mount.offsetx", this.role.getOffsetX(), leftLabelX, leftFieldX, leftY);
        leftY = this.addOffsetRow(1, 11, "role.mount.offsety", this.role.getOffsetY(), leftLabelX, leftFieldX, leftY);
        leftY = this.addOffsetRow(2, 12, "role.mount.offsetz", this.role.getOffsetZ(), leftLabelX, leftFieldX, leftY);
        leftY = this.addJumpRow(3, 13, "role.mount.jumpheight", this.role.getJumpStrength(), leftLabelX, leftFieldX, leftY);
        this.addLabel(new GuiNpcLabel(4, "role.mount.sprint", leftLabelX, leftY + 5));
        this.addButton(new GuiNpcButton(101, leftFieldX, leftY, 60, 20, new String[]{"gui.no", "gui.yes"}, this.role.isSprintAllowed() ? 1 : 0));
        leftY += 24;
        int rightLabelX = this.guiLeft + 214;
        int rightFieldX = this.guiLeft + 320;
        int rightY = this.guiTop + 6;
        this.addLabel(new GuiNpcLabel(20, "role.mount.fly", rightLabelX, rightY + 5));
        this.addButton(new GuiNpcButton(102, rightFieldX, rightY, 60, 20, new String[]{"gui.no", "gui.yes"}, this.role.isFlyingMountEnabled() ? 1 : 0));
        rightY += 24;
        if (this.role.isFlyingMountEnabled()) {
            this.addLabel(new GuiNpcLabel(21, "role.mount.hover", rightLabelX, rightY + 5));
            this.addButton(new GuiNpcButton(103, rightFieldX, rightY, 60, 20, new String[]{"gui.no", "gui.yes"}, this.role.isHoverModeEnabled() ? 1 : 0));
            rightY += 24;
            rightY = this.addSpeedRow(22, 14, "role.mount.ascendspeed", this.role.getFlyingAscendSpeed(), rightLabelX, rightFieldX, rightY, 0.1f, 3.0f, 0.6f);
            rightY = this.addSpeedRow(23, 15, "role.mount.descendspeed", this.role.getFlyingDescendSpeed(), rightLabelX, rightFieldX, rightY, 0.05f, 3.0f, 0.35f);
        }
    }

    private int addOffsetRow(int labelId, int fieldId, String translationKey, float value, int labelX, int fieldX, int y) {
        this.addLabel(new GuiNpcLabel(labelId, translationKey, labelX, y + 5));
        GuiNpcTextField field = new GuiNpcTextField(fieldId, this, fieldX, y, 80, 20, this.format(value));
        field.floatsOnly = true;
        field.setMinMaxDefaultFloat(-5.0f, 5.0f, 0.0f);
        this.addTextField(field);
        return y + 24;
    }

    private int addJumpRow(int labelId, int fieldId, String translationKey, float value, int labelX, int fieldX, int y) {
        this.addLabel(new GuiNpcLabel(labelId, translationKey, labelX, y + 5));
        GuiNpcTextField field = new GuiNpcTextField(fieldId, this, fieldX, y, 80, 20, this.format(value));
        field.floatsOnly = true;
        field.setMinMaxDefaultFloat(0.1f, 15.0f, 1.0f);
        this.addTextField(field);
        return y + 24;
    }

    private int addSpeedRow(int labelId, int fieldId, String translationKey, float value, int labelX, int fieldX, int y, float min, float max, float defaultValue) {
        this.addLabel(new GuiNpcLabel(labelId, translationKey, labelX, y + 5));
        GuiNpcTextField field = new GuiNpcTextField(fieldId, this, fieldX, y, 80, 20, this.format(value));
        field.floatsOnly = true;
        field.setMinMaxDefaultFloat(min, max, defaultValue);
        this.addTextField(field);
        return y + 24;
    }

    private String format(float value) {
        return String.format(Locale.ROOT, "%.2f", Float.valueOf(value));
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        if (this.role == null) {
            return;
        }
        if (guibutton instanceof GuiNpcButton) {
            GuiNpcButton button = (GuiNpcButton)guibutton;
            if (guibutton.field_146127_k == 101) {
                this.role.setSprintAllowed(button.getValue() == 1);
            } else if (guibutton.field_146127_k == 102) {
                this.role.setFlyingMountEnabled(button.getValue() == 1);
                this.func_73866_w_();
            } else if (guibutton.field_146127_k == 103) {
                this.role.setHoverModeEnabled(button.getValue() == 1);
            }
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (this.role == null) {
            return;
        }
        float value = textfield.isFloat() ? textfield.getFloat() : 0.0f;
        switch (textfield.id) {
            case 10: {
                this.role.setOffsetX(value);
                break;
            }
            case 11: {
                this.role.setOffsetY(value);
                break;
            }
            case 12: {
                this.role.setOffsetZ(value);
                break;
            }
            case 13: {
                this.role.setJumpStrength(value);
                break;
            }
            case 14: {
                this.role.setFlyingAscendSpeed(value);
                break;
            }
            case 15: {
                this.role.setFlyingDescendSpeed(value);
            }
        }
        textfield.func_146180_a(this.format(this.valueForField(textfield.id)));
    }

    private float valueForField(int id) {
        switch (id) {
            case 10: {
                return this.role.getOffsetX();
            }
            case 11: {
                return this.role.getOffsetY();
            }
            case 12: {
                return this.role.getOffsetZ();
            }
            case 13: {
                return this.role.getJumpStrength();
            }
            case 14: {
                return this.role.getFlyingAscendSpeed();
            }
            case 15: {
                return this.role.getFlyingDescendSpeed();
            }
        }
        return 0.0f;
    }

    @Override
    public void save() {
        if (this.role != null) {
            PacketClient.sendClient(new RoleSavePacket(this.role.writeToNBT(new NBTTagCompound())));
        }
    }
}

