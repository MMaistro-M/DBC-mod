/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import noppes.npcs.api.entity.IEntityLivingBase;

public interface ILine {
    public ILine formatTarget(IEntityLivingBase var1);

    public String getText();

    public void setText(String var1);

    public String getSound();

    public void setSound(String var1);

    public void hideText(boolean var1);

    public boolean hideText();
}

