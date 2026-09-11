/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.scripted.entity;

import kamkeel.npcs.entity.EntityEnergyPanel;
import noppes.npcs.api.entity.IEnergyPanel;
import noppes.npcs.scripted.entity.ScriptEnergyBarrier;

public class ScriptEnergyPanel<T extends EntityEnergyPanel>
extends ScriptEnergyBarrier<T>
implements IEnergyPanel {
    public ScriptEnergyPanel(T entity) {
        super(entity);
    }

    @Override
    public int getType() {
        return 21;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 21 || super.typeOf(type);
    }

    @Override
    public int getBarrierType() {
        return 1;
    }

    @Override
    public float getPanelWidth() {
        return ((EntityEnergyPanel)this.entity).getPanelData().panelWidth;
    }

    @Override
    public void setPanelWidth(float width) {
        ((EntityEnergyPanel)this.entity).getPanelData().setPanelWidth(width);
    }

    @Override
    public float getPanelHeight() {
        return ((EntityEnergyPanel)this.entity).getPanelData().panelHeight;
    }

    @Override
    public void setPanelHeight(float height) {
        ((EntityEnergyPanel)this.entity).getPanelData().setPanelHeight(height);
    }

    @Override
    public float getPanelYaw() {
        return ((EntityEnergyPanel)this.entity).getPanelYaw();
    }

    @Override
    public int getPanelMode() {
        return ((EntityEnergyPanel)this.entity).getMode().ordinal();
    }

    @Override
    public boolean isLaunched() {
        return ((EntityEnergyPanel)this.entity).getMode() == EntityEnergyPanel.PanelMode.LAUNCHED;
    }

    @Override
    public void setPanelYaw(float yaw) {
        ((EntityEnergyPanel)this.entity).setPanelYaw(yaw);
    }

    @Override
    public void setPanelMode(int mode) {
        EntityEnergyPanel.PanelMode[] values = EntityEnergyPanel.PanelMode.values();
        if (mode >= 0 && mode < values.length) {
            ((EntityEnergyPanel)this.entity).setMode(values[mode]);
        }
    }

    @Override
    public void spawn() {
        if (!((EntityEnergyPanel)this.entity).field_70175_ag && ((EntityEnergyPanel)this.entity).field_70170_p != null) {
            ((EntityEnergyPanel)this.entity).field_70170_p.func_72838_d(this.entity);
        }
    }
}

