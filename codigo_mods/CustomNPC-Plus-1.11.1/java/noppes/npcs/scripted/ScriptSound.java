/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagByte
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagDouble
 *  net.minecraft.nbt.NBTTagFloat
 *  net.minecraft.nbt.NBTTagInt
 *  net.minecraft.nbt.NBTTagString
 */
package noppes.npcs.scripted;

import java.lang.reflect.Field;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import noppes.npcs.api.IPos;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.handler.data.ISound;
import noppes.npcs.scripted.NpcAPI;

public class ScriptSound
implements ISound {
    public IEntity sourceEntity;
    public String directory;
    public float volume = 1.0f;
    public float pitch = 1.0f;
    public float xPosF;
    public float yPosF;
    public float zPosF;
    public boolean repeat = false;
    public int repeatDelay = 0;

    public ScriptSound(String location) {
        this.directory = location;
    }

    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        ScriptSound newSound = new ScriptSound("");
        for (Field field : ScriptSound.class.getDeclaredFields()) {
            try {
                if (field.getName().equals("sourceEntity") && this.sourceEntity != null) {
                    compound.func_74768_a("EntityID", this.sourceEntity.getEntityId());
                    continue;
                }
                if (field.get(this).equals(field.get(newSound))) continue;
                compound = this.writeNBTTag(compound, field.getType(), field.getName(), field.get(this));
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return compound;
    }

    public NBTTagCompound writeNBTTag(NBTTagCompound compound, Class<?> c, String key, Object value) {
        if (c == Boolean.TYPE) {
            compound.func_74757_a(key, ((Boolean)value).booleanValue());
        }
        if (c == Integer.TYPE) {
            compound.func_74768_a(key, ((Integer)value).intValue());
        }
        if (c == Double.TYPE) {
            compound.func_74780_a(key, ((Double)value).doubleValue());
        }
        if (c == Float.TYPE) {
            compound.func_74776_a(key, ((Float)value).floatValue());
        }
        if (c == String.class) {
            compound.func_74778_a(key, (String)value);
        }
        return compound;
    }

    private static Object readNBTTag(NBTTagCompound compound, String key) {
        if (compound.func_74781_a(key) instanceof NBTTagByte) {
            return compound.func_74767_n(key);
        }
        if (compound.func_74781_a(key) instanceof NBTTagInt) {
            return compound.func_74762_e(key);
        }
        if (compound.func_74781_a(key) instanceof NBTTagDouble) {
            return compound.func_74769_h(key);
        }
        if (compound.func_74781_a(key) instanceof NBTTagFloat) {
            return Float.valueOf(compound.func_74760_g(key));
        }
        if (compound.func_74781_a(key) instanceof NBTTagString) {
            return compound.func_74779_i(key);
        }
        return null;
    }

    public static ScriptSound fromNBT(NBTTagCompound compound) {
        ScriptSound particle = new ScriptSound(compound.func_74779_i("directory"));
        for (Field field : ScriptSound.class.getDeclaredFields()) {
            try {
                Object val;
                if (!compound.func_74764_b(field.getName()) || field.getName().equals("sourceEntity") || (val = ScriptSound.readNBTTag(compound, field.getName())) == null) continue;
                field.set(particle, val);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return particle;
    }

    @Override
    public void setEntity(IEntity entity) {
        this.sourceEntity = entity;
    }

    @Override
    public IEntity getEntity() {
        return this.sourceEntity;
    }

    @Override
    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    @Override
    public boolean repeats() {
        return this.repeat;
    }

    @Override
    public void setRepeatDelay(int delay) {
        this.repeatDelay = delay;
    }

    @Override
    public int getRepeatDelay() {
        return this.repeatDelay;
    }

    @Override
    public void setVolume(float volume) {
        this.volume = volume;
    }

    @Override
    public float getVolume() {
        return this.volume;
    }

    @Override
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    @Override
    public float getPitch() {
        return this.pitch;
    }

    @Override
    public void setPosition(IPos pos) {
        this.xPosF = (float)pos.getXD();
        this.yPosF = (float)pos.getYD();
        this.zPosF = (float)pos.getZD();
    }

    @Override
    public void setPosition(float x, float y, float z) {
        this.xPosF = x;
        this.yPosF = y;
        this.zPosF = z;
    }

    public IPos getPos() {
        return NpcAPI.Instance().getIPos(this.xPosF, this.yPosF, this.zPosF);
    }

    @Override
    public float getX() {
        return this.xPosF;
    }

    @Override
    public float getY() {
        return this.yPosF;
    }

    @Override
    public float getZ() {
        return this.zPosF;
    }
}

