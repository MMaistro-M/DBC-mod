/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.api.common.skin.data;

public interface ISkinProperties {
    public void removeProperty(String var1);

    public void setProperty(String var1, Object var2);

    public String getPropertyString(String var1, String var2);

    public int getPropertyInt(String var1, int var2);

    public double getPropertyDouble(String var1, double var2);

    public Boolean getPropertyBoolean(String var1, Boolean var2);

    public Object getProperty(String var1, Object var2);

    public boolean haveProperty(String var1);
}

