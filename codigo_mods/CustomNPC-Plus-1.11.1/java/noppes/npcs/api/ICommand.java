/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api;

public interface ICommand {
    public String getCommandName();

    public String getCommandUsage();

    public int getPermissionLevel();

    public void setCommandName(String var1);

    public void setCommandUsage(String var1);

    public void setPermissionLevel(int var1);

    public String[] getAliases();

    public void addAliases(String ... var1);

    public boolean hasAlias(String var1);

    public void removeAlias(String var1);
}

