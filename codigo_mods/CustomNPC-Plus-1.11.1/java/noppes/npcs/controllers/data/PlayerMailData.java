/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers.data;

import java.util.ArrayList;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.api.handler.IPlayerMailData;
import noppes.npcs.api.handler.data.IPlayerMail;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerMail;

public class PlayerMailData
implements IPlayerMailData {
    private final PlayerData parent;
    public ArrayList<PlayerMail> playermail = new ArrayList();

    public PlayerMailData() {
        this.parent = null;
    }

    public PlayerMailData(PlayerData parent) {
        this.parent = parent;
    }

    public void loadNBTData(NBTTagCompound compound) {
        ArrayList<PlayerMail> newmail = new ArrayList<PlayerMail>();
        NBTTagList list = compound.func_150295_c("MailData", 10);
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.func_74745_c(); ++i) {
            PlayerMail mail = new PlayerMail();
            mail.readNBT(list.func_150305_b(i));
            newmail.add(mail);
        }
        this.playermail = newmail;
    }

    public NBTTagCompound saveNBTData(NBTTagCompound compound) {
        NBTTagList list = new NBTTagList();
        for (PlayerMail mail : this.playermail) {
            list.func_74742_a((NBTBase)mail.writeNBT());
        }
        compound.func_74782_a("MailData", (NBTBase)list);
        return compound;
    }

    @Override
    public boolean hasMail() {
        for (PlayerMail mail : this.playermail) {
            if (mail.beenRead) continue;
            return true;
        }
        return false;
    }

    @Override
    public void addMail(IPlayerMail mail) {
        this.playermail.add((PlayerMail)mail);
    }

    @Override
    public void removeMail(IPlayerMail mail) {
        this.playermail.remove((PlayerMail)mail);
    }

    @Override
    public boolean hasMail(IPlayerMail mail) {
        return this.playermail.contains((PlayerMail)mail);
    }

    @Override
    public IPlayerMail[] getAllMail() {
        return this.playermail.toArray(new IPlayerMail[0]);
    }

    @Override
    public IPlayerMail[] getUnreadMail() {
        ArrayList<PlayerMail> mails = new ArrayList<PlayerMail>();
        for (PlayerMail mail : this.playermail) {
            if (mail.beenRead) continue;
            mails.add(mail);
        }
        return mails.toArray(new IPlayerMail[0]);
    }

    @Override
    public IPlayerMail[] getReadMail() {
        ArrayList<PlayerMail> mails = new ArrayList<PlayerMail>();
        for (PlayerMail mail : this.playermail) {
            if (!mail.beenRead) continue;
            mails.add(mail);
        }
        return mails.toArray(new IPlayerMail[0]);
    }

    @Override
    public IPlayerMail[] getMailFrom(String sender) {
        ArrayList<PlayerMail> mails = new ArrayList<PlayerMail>();
        for (PlayerMail mail : this.playermail) {
            if (!mail.sender.equals(sender)) continue;
            mails.add(mail);
        }
        return mails.toArray(new IPlayerMail[0]);
    }
}

