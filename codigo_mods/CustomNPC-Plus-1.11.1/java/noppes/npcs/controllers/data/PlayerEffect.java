/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package noppes.npcs.controllers.data;

import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.IPlayerEffect;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.controllers.data.CustomEffect;

public class PlayerEffect
implements IPlayerEffect {
    public int id;
    public int duration;
    public byte level;
    public int index;

    public PlayerEffect(int id, int duration, byte level, int index) {
        this.id = id;
        this.duration = duration;
        this.level = level;
        this.index = index;
    }

    @Override
    public void kill() {
        this.duration = 0;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getDuration() {
        return this.duration;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public byte getLevel() {
        return this.level;
    }

    @Override
    public void setLevel(byte level) {
        this.level = level;
    }

    @Override
    public String getName() {
        CustomEffect effect = CustomEffectController.getInstance().get(this.id, this.index);
        if (effect != null) {
            return effect.getName();
        }
        return "UNKNOWN";
    }

    @Override
    public void performEffect(IPlayer player) {
        CustomEffect effect;
        if (player != null && player.getMCEntity() != null && player.getMCEntity() instanceof EntityPlayer && (effect = CustomEffectController.getInstance().get(this.id, this.index)) != null) {
            effect.onTick((EntityPlayer)player.getMCEntity(), this);
        }
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }
}

