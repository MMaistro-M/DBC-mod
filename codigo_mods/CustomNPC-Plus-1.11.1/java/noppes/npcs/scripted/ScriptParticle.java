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
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.IParticle;
import noppes.npcs.api.IPos;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.scripted.NpcAPI;

public class ScriptParticle
implements IParticle {
    public String directory;
    public int HEXColor = 0xFFFFFF;
    public int HEXColor2 = 0xFFFFFF;
    public float HEXColorRate = 0.0f;
    public int HEXColorStart = 0;
    public int amount = 1;
    public int maxAge = 20;
    public double x = 0.0;
    public double y = 0.0;
    public double z = 0.0;
    public double motionX = 0.0;
    public double motionY = 0.0;
    public double motionZ = 0.0;
    public float gravity = 0.0f;
    public float scaleX1 = 20.0f;
    public float scaleX2 = 20.0f;
    public float scaleXRate = 0.0f;
    public float scaleY1 = 20.0f;
    public float scaleY2 = 20.0f;
    public float scaleYRate = 0.0f;
    public float alpha1 = 1.0f;
    public float alpha2 = 1.0f;
    public float alphaRate = 0.0f;
    public int scaleXRateStart = 0;
    public int scaleYRateStart;
    public int alphaRateStart = 0;
    public float rotationX1 = 0.0f;
    public float rotationX2 = 0.0f;
    public float rotationXRate = 0.0f;
    public int rotationXRateStart = 0;
    public float rotationY1 = 0.0f;
    public float rotationY2 = 0.0f;
    public float rotationYRate = 0.0f;
    public int rotationYRateStart = 0;
    public float rotationZ1 = 0.0f;
    public float rotationZ2 = 0.0f;
    public float rotationZRate = 0.0f;
    public int rotationZRateStart = 0;
    public int width = -1;
    public int height = -1;
    public int offsetX = 0;
    public int offsetY = 0;
    public int animRate = 0;
    public boolean animLoop = true;
    public int animStart = 0;
    public int animEnd = -1;
    public boolean facePlayer = true;
    public boolean glows = true;
    public boolean noClip = true;

    public ScriptParticle(String directory) {
        this.directory = directory;
    }

    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        ScriptParticle newParticle = new ScriptParticle("");
        for (Field field : ScriptParticle.class.getDeclaredFields()) {
            try {
                if (field.get(this).equals(field.get(newParticle))) continue;
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

    public static ScriptParticle fromNBT(NBTTagCompound compound) {
        ScriptParticle particle = new ScriptParticle(compound.func_74779_i("directory"));
        for (Field field : ScriptParticle.class.getDeclaredFields()) {
            try {
                Object val;
                if (!compound.func_74764_b(field.getName()) || (val = ScriptParticle.readNBTTag(compound, field.getName())) == null) continue;
                field.set(particle, val);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return particle;
    }

    @Override
    public void spawn(IEntity entity) {
        int entityID = entity.getMCEntity().func_145782_y();
        NBTTagCompound compound = this.writeToNBT();
        compound.func_74768_a("EntityID", entityID);
        NoppesUtilServer.spawnScriptedParticle(compound, entity.getWorld().getDimensionID());
    }

    @Override
    public void spawn(IWorld world) {
        NBTTagCompound compound = this.writeToNBT();
        NoppesUtilServer.spawnScriptedParticle(compound, world.getDimensionID());
    }

    @Override
    public void spawn(IWorld world, double x, double y, double z) {
        double prevX = this.x;
        double prevY = this.y;
        double prevZ = this.z;
        this.x = x;
        this.y = y;
        this.z = z;
        NBTTagCompound compound = this.writeToNBT();
        NoppesUtilServer.spawnScriptedParticle(compound, world.getDimensionID());
        this.x = prevX;
        this.y = prevY;
        this.z = prevZ;
    }

    @Override
    public void spawnOnEntity(IEntity entity) {
        this.spawn(entity);
    }

    @Override
    public void spawnInWorld(IWorld world) {
        this.spawn(world);
    }

    @Override
    public void spawnInWorld(IWorld world, double x, double y, double z) {
        this.spawn(world, x, y, z);
    }

    @Override
    public void setGlows(boolean glows) {
        this.glows = glows;
    }

    @Override
    public boolean getGlows() {
        return this.glows;
    }

    @Override
    public void setNoClip(boolean noClip) {
        this.noClip = noClip;
    }

    @Override
    public boolean getNoClip() {
        return this.noClip;
    }

    @Override
    public void setFacePlayer(boolean facePlayer) {
        this.facePlayer = facePlayer;
    }

    @Override
    public boolean getFacePlayer() {
        return this.facePlayer;
    }

    @Override
    public String getDirectory() {
        return this.directory;
    }

    @Override
    public void setDirectory(String directory) {
        this.directory = directory;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return this.amount;
    }

    @Override
    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    @Override
    public int getMaxAge() {
        return this.maxAge;
    }

    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void setOffset(int offsetX, int offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    @Override
    public int getOffsetX() {
        return this.offsetX;
    }

    @Override
    public int getOffsetY() {
        return this.offsetY;
    }

    @Override
    public void setAnim(int animRate, boolean animLoop, int animStart, int animEnd) {
        this.animRate = animRate;
        this.animLoop = animLoop;
        this.animStart = animStart;
        this.animEnd = animEnd;
    }

    @Override
    public int getAnimRate() {
        return this.animRate;
    }

    @Override
    public boolean getAnimLoop() {
        return this.animLoop;
    }

    @Override
    public int getAnimStart() {
        return this.animStart;
    }

    @Override
    public int getAnimEnd() {
        return this.animEnd;
    }

    @Override
    public void setPosition(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getZ() {
        return this.z;
    }

    @Override
    public void setPosition(IPos pos) {
        this.setPosition(pos.getXD(), pos.getYD(), pos.getZD());
    }

    @Override
    public void getPos() {
        NpcAPI.Instance().getIPos(this.x, this.y, this.z);
    }

    @Override
    public void setMotion(double motionX, double motionY, double motionZ, float gravity) {
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
        this.gravity = gravity;
    }

    @Override
    public double getMotionX() {
        return this.motionX;
    }

    @Override
    public double getMotionY() {
        return this.motionY;
    }

    @Override
    public double getMotionZ() {
        return this.motionZ;
    }

    @Override
    public float getGravity() {
        return this.gravity;
    }

    @Override
    public void setHEXColor(int HEXColor, int HEXColor2, float HEXColorRate, int HEXColorStart) {
        this.HEXColor = HEXColor;
        this.HEXColor2 = HEXColor2;
        this.HEXColorRate = HEXColorRate;
        this.HEXColorStart = HEXColorStart;
    }

    @Override
    public int getHEXColor1() {
        return this.HEXColor;
    }

    @Override
    public int getHEXColor2() {
        return this.HEXColor2;
    }

    @Override
    public float getHEXColorRate() {
        return this.HEXColorRate;
    }

    @Override
    public int getHEXColorStart() {
        return this.HEXColorStart;
    }

    @Override
    public void setAlpha(float alpha1, float alpha2, float alphaRate, int alphaRateStart) {
        this.alpha1 = alpha1;
        this.alpha2 = alpha2;
        this.alphaRate = alphaRate;
        this.alphaRateStart = alphaRateStart;
    }

    @Override
    public float getAlpha1() {
        return this.alpha1;
    }

    @Override
    public float getAlpha2() {
        return this.alpha2;
    }

    @Override
    public float getAlphaRate() {
        return this.alphaRate;
    }

    @Override
    public int getAlphaRateStart() {
        return this.alphaRateStart;
    }

    @Override
    public void setScale(float scale1, float scale2, float scaleRate, int scaleRateStart) {
        this.scaleX1 = this.scaleY1 = scale1;
        this.scaleX2 = this.scaleY2 = scale2;
        this.scaleXRate = this.scaleYRate = scaleRate;
        this.scaleXRateStart = this.scaleYRateStart = scaleRateStart;
    }

    @Override
    public void setScaleX(float scale1, float scale2, float scaleRate, int scaleRateStart) {
        this.scaleX1 = scale1;
        this.scaleX2 = scale2;
        this.scaleXRate = scaleRate;
        this.scaleXRateStart = scaleRateStart;
    }

    @Override
    public float getScaleX1() {
        return this.scaleX1;
    }

    @Override
    public float getScaleX2() {
        return this.scaleX2;
    }

    @Override
    public float getScaleXRate() {
        return this.scaleXRate;
    }

    @Override
    public int getScaleXRateStart() {
        return this.scaleXRateStart;
    }

    @Override
    public void setScaleY(float scale1, float scale2, float scaleRate, int scaleRateStart) {
        this.scaleY1 = scale1;
        this.scaleY2 = scale2;
        this.scaleYRate = scaleRate;
        this.scaleYRateStart = scaleRateStart;
    }

    @Override
    public float getScaleY1() {
        return this.scaleY1;
    }

    @Override
    public float getScaleY2() {
        return this.scaleY2;
    }

    @Override
    public float getScaleYRate() {
        return this.scaleYRate;
    }

    @Override
    public int getScaleYRateStart() {
        return this.scaleYRateStart;
    }

    @Override
    public void setRotationX(float rotationX1, float rotationX2, float rotationXRate, int rotationXRateStart) {
        this.rotationX1 = rotationX1;
        this.rotationX2 = rotationX2;
        this.rotationXRate = rotationXRate;
        this.rotationXRateStart = rotationXRateStart;
    }

    @Override
    public float getRotationX1() {
        return this.rotationX1;
    }

    @Override
    public float getRotationX2() {
        return this.rotationX2;
    }

    @Override
    public float getRotationXRate() {
        return this.rotationXRate;
    }

    @Override
    public int getRotationXRateStart() {
        return this.rotationXRateStart;
    }

    @Override
    public void setRotationY(float rotationY1, float rotationY2, float rotationYRate, int rotationYRateStart) {
        this.rotationY1 = rotationY1;
        this.rotationY2 = rotationY2;
        this.rotationYRate = rotationYRate;
        this.rotationYRateStart = rotationYRateStart;
    }

    @Override
    public float getRotationY1() {
        return this.rotationY1;
    }

    @Override
    public float getRotationY2() {
        return this.rotationY2;
    }

    @Override
    public float getRotationYRate() {
        return this.rotationYRate;
    }

    @Override
    public int getRotationYRateStart() {
        return this.rotationYRateStart;
    }

    @Override
    public void setRotationZ(float rotationZ1, float rotationZ2, float rotationZRate, int rotationZRateStart) {
        this.rotationZ1 = rotationZ1;
        this.rotationZ2 = rotationZ2;
        this.rotationZRate = rotationZRate;
        this.rotationZRateStart = rotationZRateStart;
    }

    @Override
    public float getRotationZ1() {
        return this.rotationZ1;
    }

    @Override
    public float getRotationZ2() {
        return this.rotationZ2;
    }

    @Override
    public float getRotationZRate() {
        return this.rotationZRate;
    }

    @Override
    public int getRotationZRateStart() {
        return this.rotationZRateStart;
    }

    @Deprecated
    public void setHEXColor(int HEXColor) {
        this.HEXColor = HEXColor;
    }

    @Deprecated
    public int getHEXColor() {
        return this.HEXColor;
    }
}

