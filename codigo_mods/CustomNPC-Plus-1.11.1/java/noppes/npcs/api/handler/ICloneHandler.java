/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import noppes.npcs.api.IPos;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEntity;

public interface ICloneHandler {
    public IEntity spawn(double var1, double var3, double var5, int var7, String var8, IWorld var9, boolean var10);

    public IEntity spawn(IPos var1, int var2, String var3, IWorld var4, boolean var5);

    public IEntity spawn(double var1, double var3, double var5, int var7, String var8, IWorld var9);

    public IEntity spawn(IPos var1, int var2, String var3, IWorld var4);

    public IEntity[] getTab(int var1, IWorld var2);

    public IEntity get(int var1, String var2, IWorld var3);

    public boolean has(int var1, String var2);

    public void set(int var1, String var2, IEntity var3);

    public void remove(int var1, String var2);

    public String[] getFolders();

    public boolean hasFolder(String var1);

    public IEntity spawn(double var1, double var3, double var5, String var7, String var8, IWorld var9, boolean var10);

    public IEntity spawn(IPos var1, String var2, String var3, IWorld var4, boolean var5);

    public IEntity spawn(double var1, double var3, double var5, String var7, String var8, IWorld var9);

    public IEntity spawn(IPos var1, String var2, String var3, IWorld var4);

    public IEntity[] getFolder(String var1, IWorld var2);

    public IEntity get(String var1, String var2, IWorld var3);

    public boolean has(String var1, String var2);

    public void set(String var1, String var2, IEntity var3);

    public void remove(String var1, String var2);
}

