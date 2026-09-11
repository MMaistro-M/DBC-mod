/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import noppes.npcs.api.handler.data.IQuest;
import noppes.npcs.api.item.IItemStack;

public interface IPlayerMail {
    public void setPageText(String[] var1);

    public String[] getPageText();

    public int getPageCount();

    public void setSender(String var1);

    public String getSender();

    public void setSubject(String var1);

    public String getSubject();

    public long getTimePast();

    public long getTimeSent();

    public boolean hasQuest();

    public IQuest getQuest();

    public IItemStack[] getItems();

    public void setItems(IItemStack[] var1);
}

