/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

public interface ICustomEffect {
    public int getID();

    public String getName();

    public void setMenuName(String var1);

    public String getMenuName();

    public void setName(String var1);

    public String getIcon();

    public void setIcon(String var1);

    public int getEveryXTick();

    public void setEveryXTick(int var1);

    public int getIconX();

    public void setIconX(int var1);

    public int getIconY();

    public void setIconY(int var1);

    public int getWidth();

    public void setWidth(int var1);

    public int getHeight();

    public void setHeight(int var1);

    public boolean isLossOnDeath();

    public void setLossOnDeath(boolean var1);

    public ICustomEffect save();

    public void setID(int var1);

    public int getIndex();

    public boolean isAnimated();

    public void setAnimated(boolean var1);

    public int getFrameCount();

    public void setFrameCount(int var1);

    public int getFrameTime();

    public void setFrameTime(int var1);
}

