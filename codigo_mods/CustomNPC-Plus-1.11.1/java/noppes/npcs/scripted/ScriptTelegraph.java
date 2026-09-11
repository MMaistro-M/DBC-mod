/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package noppes.npcs.scripted;

import kamkeel.npcs.controllers.TelegraphController;
import kamkeel.npcs.controllers.data.telegraph.Telegraph;
import kamkeel.npcs.controllers.data.telegraph.TelegraphInstance;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import noppes.npcs.api.ITelegraph;
import noppes.npcs.api.ITelegraphInstance;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.scripted.ScriptTelegraphInstance;

public class ScriptTelegraph
implements ITelegraph {
    private final Telegraph telegraph;

    public ScriptTelegraph(Telegraph telegraph) {
        this.telegraph = telegraph;
    }

    public Telegraph getMCTelegraph() {
        return this.telegraph;
    }

    @Override
    public String getType() {
        return this.telegraph.getType().name();
    }

    @Override
    public void setType(String type) {
        if (type == null) {
            return;
        }
        try {
            this.telegraph.setType(TelegraphType.valueOf(type.toUpperCase()));
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
    }

    @Override
    public float getRadius() {
        return this.telegraph.getRadius();
    }

    @Override
    public void setRadius(float radius) {
        this.telegraph.setRadius(radius);
    }

    @Override
    public float getInnerRadius() {
        return this.telegraph.getInnerRadius();
    }

    @Override
    public void setInnerRadius(float innerRadius) {
        this.telegraph.setInnerRadius(innerRadius);
    }

    @Override
    public float getLength() {
        return this.telegraph.getLength();
    }

    @Override
    public void setLength(float length) {
        this.telegraph.setLength(length);
    }

    @Override
    public float getWidth() {
        return this.telegraph.getWidth();
    }

    @Override
    public void setWidth(float width) {
        this.telegraph.setWidth(width);
    }

    @Override
    public float getAngle() {
        return this.telegraph.getAngle();
    }

    @Override
    public void setAngle(float angle) {
        this.telegraph.setAngle(angle);
    }

    @Override
    public int getDuration() {
        return this.telegraph.getDurationTicks();
    }

    @Override
    public void setDuration(int ticks) {
        this.telegraph.setDurationTicks(ticks);
    }

    @Override
    public int getColor() {
        return this.telegraph.getColor();
    }

    @Override
    public void setColor(int argb) {
        this.telegraph.setColor(argb);
    }

    @Override
    public int getWarningColor() {
        return this.telegraph.getWarningColor();
    }

    @Override
    public void setWarningColor(int argb) {
        this.telegraph.setWarningColor(argb);
    }

    @Override
    public int getWarningStartTick() {
        return this.telegraph.getWarningStartTick();
    }

    @Override
    public void setWarningStartTick(int tick) {
        this.telegraph.setWarningStartTick(tick);
    }

    @Override
    public boolean isAnimated() {
        return this.telegraph.isAnimated();
    }

    @Override
    public void setAnimated(boolean animated) {
        this.telegraph.setAnimated(animated);
    }

    @Override
    public float getHeightOffset() {
        return this.telegraph.getHeightOffset();
    }

    @Override
    public void setHeightOffset(float offset) {
        this.telegraph.setHeightOffset(offset);
    }

    @Override
    public ITelegraphInstance spawn(IWorld world, double x, double y, double z) {
        return this.spawn(world, x, y, z, 0.0f);
    }

    @Override
    public ITelegraphInstance spawn(IWorld world, double x, double y, double z, float yaw) {
        if (world == null || world.getMCWorld() == null) {
            return null;
        }
        WorldServer mcWorld = world.getMCWorld();
        TelegraphInstance instance = TelegraphController.Instance.spawn(this.telegraph, (World)mcWorld, x, y, z, yaw);
        return new ScriptTelegraphInstance(instance);
    }

    @Override
    public ITelegraphInstance spawn(IEntity entity) {
        return this.spawn(entity, 0.0f);
    }

    @Override
    public ITelegraphInstance spawn(IEntity entity, float yaw) {
        if (entity == null || entity.getMCEntity() == null) {
            return null;
        }
        Object mcEntity = entity.getMCEntity();
        TelegraphInstance instance = TelegraphController.Instance.spawn(this.telegraph, (Entity)mcEntity, yaw);
        return new ScriptTelegraphInstance(instance);
    }
}

