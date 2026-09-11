/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

public interface IMagic {
    public int getId();

    public String getName();

    public void setName(String var1);

    public void setColor(int var1);

    public int getColor();

    public String getDisplayName();

    public void setDisplayName(String var1);

    public void save();

    public boolean hasInteraction(int var1);

    public void setInteraction(int var1, float var2);

    public float getInteraction(int var1, float var2);
}

