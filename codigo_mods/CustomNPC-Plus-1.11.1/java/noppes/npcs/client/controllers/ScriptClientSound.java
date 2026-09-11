/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound$AttenuationType
 *  net.minecraft.client.audio.ITickableSound
 *  net.minecraft.client.audio.MovingSound
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package noppes.npcs.client.controllers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ITickableSound;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import noppes.npcs.scripted.ScriptSound;

public class ScriptClientSound
extends MovingSound
implements ITickableSound {
    public final String sound;
    private Entity entity;
    public boolean paused;

    public ScriptClientSound(String sound) {
        super(new ResourceLocation(sound));
        this.sound = sound;
    }

    public static ScriptClientSound fromScriptSound(NBTTagCompound compound, World world) {
        ScriptSound sound = ScriptSound.fromNBT(compound);
        ScriptClientSound clientSound = new ScriptClientSound(sound.directory);
        clientSound.field_147660_d = sound.xPosF;
        clientSound.field_147661_e = sound.yPosF;
        clientSound.field_147658_f = sound.zPosF;
        clientSound.field_147662_b = sound.volume;
        clientSound.field_147663_c = sound.pitch;
        clientSound.field_147659_g = sound.repeat;
        clientSound.field_147665_h = sound.repeatDelay;
        if (compound.func_74764_b("EntityID")) {
            clientSound.setEntity(world.func_73045_a(compound.func_74762_e("EntityID")));
            clientSound.field_147666_i = ISound.AttenuationType.NONE;
        }
        return clientSound;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
        if (entity != null) {
            this.field_147660_d = (float)this.entity.field_70165_t;
            this.field_147661_e = (float)this.entity.field_70163_u;
            this.field_147658_f = (float)this.entity.field_70161_v;
        }
    }

    public Entity getEntity() {
        return this.entity;
    }

    public void stopSound() {
        this.field_147668_j = true;
        this.field_147659_g = false;
        this.field_147662_b = 0.0f;
    }

    public void func_73660_a() {
        if (this.entity != null) {
            if (this.entity.field_70128_L) {
                this.field_147668_j = true;
            } else {
                this.field_147660_d = (float)this.entity.field_70165_t;
                this.field_147661_e = (float)this.entity.field_70163_u;
                this.field_147658_f = (float)this.entity.field_70161_v;
            }
        }
    }

    public void setPos(float x, float y, float z) {
        this.field_147660_d = x;
        this.field_147661_e = y;
        this.field_147658_f = z;
    }

    public double getDistance() {
        return Minecraft.func_71410_x().field_71439_g.func_70011_f((double)this.field_147660_d, (double)this.field_147661_e, (double)this.field_147658_f);
    }

    public void setVolume(float volume) {
        this.field_147662_b = volume;
    }

    public void setAttenuationType(ISound.AttenuationType attenuationType) {
        this.field_147666_i = attenuationType;
    }

    public void setRepeat(boolean repeat) {
        this.field_147659_g = repeat;
    }
}

