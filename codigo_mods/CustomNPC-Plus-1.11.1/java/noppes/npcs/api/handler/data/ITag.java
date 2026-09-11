/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

public interface ITag {
    public String getUuid();

    public String getName();

    public void setName(String var1);

    public void setColor(int var1);

    public int getId();

    public int getColor();

    public boolean getIsHidden();

    public void setIsHidden(boolean var1);

    public void save();
}

