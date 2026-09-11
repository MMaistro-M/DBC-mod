/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api;

import noppes.npcs.api.IPos;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEntity;

public interface IParticle {
    public void spawn(IEntity var1);

    public void spawn(IWorld var1);

    public void spawn(IWorld var1, double var2, double var4, double var6);

    @Deprecated
    public void spawnOnEntity(IEntity var1);

    @Deprecated
    public void spawnInWorld(IWorld var1);

    @Deprecated
    public void spawnInWorld(IWorld var1, double var2, double var4, double var6);

    public void setGlows(boolean var1);

    public boolean getGlows();

    public void setNoClip(boolean var1);

    public boolean getNoClip();

    public void setFacePlayer(boolean var1);

    public boolean getFacePlayer();

    public void setDirectory(String var1);

    public String getDirectory();

    public void setAmount(int var1);

    public int getAmount();

    public void setMaxAge(int var1);

    public int getMaxAge();

    public void setSize(int var1, int var2);

    public int getWidth();

    public int getHeight();

    public void setOffset(int var1, int var2);

    public int getOffsetX();

    public int getOffsetY();

    public void setAnim(int var1, boolean var2, int var3, int var4);

    public int getAnimRate();

    public boolean getAnimLoop();

    public int getAnimStart();

    public int getAnimEnd();

    public void setPosition(double var1, double var3, double var5);

    public double getX();

    public double getY();

    public double getZ();

    public void setPosition(IPos var1);

    public void getPos();

    public void setMotion(double var1, double var3, double var5, float var7);

    public double getMotionX();

    public double getMotionY();

    public double getMotionZ();

    public float getGravity();

    public void setHEXColor(int var1, int var2, float var3, int var4);

    public int getHEXColor1();

    public int getHEXColor2();

    public float getHEXColorRate();

    public int getHEXColorStart();

    public void setAlpha(float var1, float var2, float var3, int var4);

    public float getAlpha1();

    public float getAlpha2();

    public float getAlphaRate();

    public int getAlphaRateStart();

    public void setScale(float var1, float var2, float var3, int var4);

    public void setScaleX(float var1, float var2, float var3, int var4);

    public float getScaleX1();

    public float getScaleX2();

    public float getScaleXRate();

    public int getScaleXRateStart();

    public void setScaleY(float var1, float var2, float var3, int var4);

    public float getScaleY1();

    public float getScaleY2();

    public float getScaleYRate();

    public int getScaleYRateStart();

    public void setRotationX(float var1, float var2, float var3, int var4);

    public float getRotationX1();

    public float getRotationX2();

    public float getRotationXRate();

    public int getRotationXRateStart();

    public void setRotationY(float var1, float var2, float var3, int var4);

    public float getRotationY1();

    public float getRotationY2();

    public float getRotationYRate();

    public int getRotationYRateStart();

    public void setRotationZ(float var1, float var2, float var3, int var4);

    public float getRotationZ1();

    public float getRotationZ2();

    public float getRotationZRate();

    public int getRotationZRateStart();
}

