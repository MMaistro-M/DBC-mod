/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import noppes.npcs.api.handler.data.IPlayerMail;

public interface IPlayerMailData {
    public boolean hasMail();

    public void addMail(IPlayerMail var1);

    public void removeMail(IPlayerMail var1);

    public boolean hasMail(IPlayerMail var1);

    public IPlayerMail[] getAllMail();

    public IPlayerMail[] getUnreadMail();

    public IPlayerMail[] getReadMail();

    public IPlayerMail[] getMailFrom(String var1);
}

