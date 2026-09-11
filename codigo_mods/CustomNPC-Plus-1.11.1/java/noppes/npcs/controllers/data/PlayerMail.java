/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 */
package noppes.npcs.controllers.data;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.handler.data.IPlayerMail;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.controllers.QuestController;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.scripted.NpcAPI;

public class PlayerMail
implements IInventory,
IPlayerMail {
    public String subject = "";
    public String sender = "";
    public NBTTagCompound message = new NBTTagCompound();
    public long time = 0L;
    public boolean beenRead = false;
    public int questId = -1;
    public String questTitle = "";
    public ItemStack[] items = new ItemStack[4];
    public long timePast;

    public void readNBT(NBTTagCompound compound) {
        this.subject = compound.func_74779_i("Subject");
        this.sender = compound.func_74779_i("Sender");
        this.time = compound.func_74763_f("Time");
        this.beenRead = compound.func_74767_n("BeenRead");
        this.message = compound.func_74775_l("Message");
        this.timePast = compound.func_74763_f("TimePast");
        if (compound.func_74764_b("MailQuest")) {
            this.questId = compound.func_74762_e("MailQuest");
        }
        this.questTitle = compound.func_74779_i("MailQuestTitle");
        this.items = new ItemStack[this.func_70302_i_()];
        NBTTagList nbttaglist = compound.func_150295_c("MailItems", 10);
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            int j = nbttagcompound1.func_74771_c("Slot") & 0xFF;
            if (j < 0 || j >= this.items.length) continue;
            this.items[j] = NoppesUtilServer.readItem(nbttagcompound1);
        }
    }

    public NBTTagCompound writeNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74778_a("Subject", this.subject);
        compound.func_74778_a("Sender", this.sender);
        compound.func_74772_a("Time", this.time);
        compound.func_74757_a("BeenRead", this.beenRead);
        compound.func_74782_a("Message", (NBTBase)this.message);
        compound.func_74772_a("TimePast", System.currentTimeMillis() - this.time);
        compound.func_74768_a("MailQuest", this.questId);
        if (this.hasQuest()) {
            compound.func_74778_a("MailQuestTitle", this.getQuest().title);
        }
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.func_74774_a("Slot", (byte)i);
            NoppesUtilServer.writeItem(this.items[i], nbttagcompound1);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        }
        compound.func_74782_a("MailItems", (NBTBase)nbttaglist);
        return compound;
    }

    public boolean isValid() {
        return !this.subject.isEmpty() && !this.message.func_82582_d() && !this.sender.isEmpty();
    }

    @Override
    public boolean hasQuest() {
        return this.getQuest() != null;
    }

    @Override
    public Quest getQuest() {
        return QuestController.Instance != null ? QuestController.Instance.quests.get(this.questId) : null;
    }

    public int func_70302_i_() {
        return 4;
    }

    public int func_70297_j_() {
        return 64;
    }

    public ItemStack func_70301_a(int var1) {
        return this.items[var1];
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.items[par1] != null) {
            if (this.items[par1].field_77994_a <= par2) {
                ItemStack itemstack = this.items[par1];
                this.items[par1] = null;
                this.func_70296_d();
                return itemstack;
            }
            ItemStack itemstack = this.items[par1].func_77979_a(par2);
            if (this.items[par1].field_77994_a == 0) {
                this.items[par1] = null;
            }
            this.func_70296_d();
            return itemstack;
        }
        return null;
    }

    public ItemStack func_70304_b(int var1) {
        if (this.items[var1] != null) {
            ItemStack itemstack = this.items[var1];
            this.items[var1] = null;
            return itemstack;
        }
        return null;
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        this.items[par1] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.field_77994_a > this.func_70297_j_()) {
            par2ItemStack.field_77994_a = this.func_70297_j_();
        }
        this.func_70296_d();
    }

    public String func_145825_b() {
        return null;
    }

    public boolean func_145818_k_() {
        return false;
    }

    public void func_70296_d() {
    }

    public boolean func_70300_a(EntityPlayer var1) {
        return true;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int var1, ItemStack var2) {
        return true;
    }

    public PlayerMail copy() {
        PlayerMail mail = new PlayerMail();
        mail.readNBT(this.writeNBT());
        return mail;
    }

    @Override
    public void setPageText(String[] pages) {
        NBTTagList bookPages = new NBTTagList();
        for (String text : pages) {
            bookPages.func_74742_a((NBTBase)new NBTTagString(text));
        }
        this.message.func_74782_a("pages", (NBTBase)bookPages);
    }

    @Override
    public String[] getPageText() {
        NBTTagList bookPages = new NBTTagList();
        if (this.message.func_74764_b("pages")) {
            bookPages = this.message.func_150295_c("pages", 8);
        }
        if (bookPages != null) {
            bookPages = (NBTTagList)bookPages.func_74737_b();
            ArrayList<String> pageStrings = new ArrayList<String>();
            for (int i = 0; i < bookPages.func_74745_c(); ++i) {
                pageStrings.add(bookPages.func_150307_f(i));
            }
            return pageStrings.toArray(new String[0]);
        }
        return new String[]{""};
    }

    @Override
    public int getPageCount() {
        NBTTagList bookPages = new NBTTagList();
        if (this.message.func_74764_b("pages")) {
            bookPages = this.message.func_150295_c("pages", 8);
        }
        int bookTotalPages = 0;
        if (bookPages != null) {
            bookTotalPages = (bookPages = (NBTTagList)bookPages.func_74737_b()).func_74745_c();
            if (bookTotalPages < 1) {
                bookTotalPages = 1;
            }
            return bookTotalPages;
        }
        return 0;
    }

    @Override
    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String getSender() {
        return this.sender;
    }

    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String getSubject() {
        return this.subject;
    }

    @Override
    public long getTimePast() {
        return this.timePast;
    }

    @Override
    public long getTimeSent() {
        return this.time;
    }

    @Override
    public IItemStack[] getItems() {
        ArrayList<IItemStack> list = new ArrayList<IItemStack>();
        for (ItemStack itemStack : this.items) {
            list.add(NpcAPI.Instance().getIItemStack(itemStack));
        }
        return list.toArray(new IItemStack[0]);
    }

    @Override
    public void setItems(IItemStack[] items) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        for (IItemStack itemStack : items) {
            list.add(itemStack.getMCItemStack());
        }
        this.items = list.toArray(new ItemStack[0]);
    }
}

