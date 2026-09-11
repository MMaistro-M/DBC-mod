/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.scripted;

import kamkeel.npcs.controllers.TelegraphController;
import kamkeel.npcs.controllers.data.telegraph.TelegraphInstance;
import noppes.npcs.api.ITelegraphInstance;
import noppes.npcs.api.entity.IEntity;

public class ScriptTelegraphInstance
implements ITelegraphInstance {
    private final TelegraphInstance instance;

    public ScriptTelegraphInstance(TelegraphInstance instance) {
        this.instance = instance;
    }

    public TelegraphInstance getMCInstance() {
        return this.instance;
    }

    @Override
    public String getInstanceId() {
        return this.instance.getInstanceId();
    }

    @Override
    public double getX() {
        return this.instance.getX();
    }

    @Override
    public double getY() {
        return this.instance.getY();
    }

    @Override
    public double getZ() {
        return this.instance.getZ();
    }

    @Override
    public float getYaw() {
        return this.instance.getYaw();
    }

    @Override
    public void setPosition(double x, double y, double z) {
        this.instance.setX(x);
        this.instance.setY(y);
        this.instance.setZ(z);
    }

    @Override
    public void followEntity(IEntity entity) {
        if (entity == null || entity.getMCEntity() == null) {
            return;
        }
        Object mcEntity = entity.getMCEntity();
        this.instance.setEntityIdToFollow(mcEntity.func_145782_y());
    }

    @Override
    public void stopFollowing() {
        this.instance.lockPosition();
    }

    @Override
    public boolean isFollowing() {
        return this.instance.getEntityIdToFollow() >= 0;
    }

    @Override
    public int getRemainingTicks() {
        return this.instance.getRemainingTicks();
    }

    @Override
    public int getTotalTicks() {
        return this.instance.getTotalTicks();
    }

    @Override
    public float getProgress() {
        return this.instance.getProgress();
    }

    @Override
    public boolean isWarning() {
        return this.instance.isWarning();
    }

    @Override
    public void remove() {
        TelegraphController.Instance.remove(this.instance);
    }

    @Override
    public void lockPosition() {
        this.instance.lockPosition();
    }
}

