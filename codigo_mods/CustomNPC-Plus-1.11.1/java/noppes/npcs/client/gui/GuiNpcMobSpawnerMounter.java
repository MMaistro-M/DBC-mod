/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagDouble
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.world.World
 */
package noppes.npcs.client.gui;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.MountPacket;
import kamkeel.npcs.network.packets.request.clone.CloneListPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import noppes.npcs.client.controllers.ClientCloneController;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiMenuSideButton;
import noppes.npcs.client.gui.util.GuiMenuTopButton;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.IGuiData;

public class GuiNpcMobSpawnerMounter
extends GuiNPCInterface
implements IGuiData {
    private GuiCustomScroll scroll;
    private int posX;
    private int posY;
    private int posZ;
    private List<String> list;
    private static int showingClones = 0;
    private static String search = "";
    private int activeTab = 1;

    public GuiNpcMobSpawnerMounter(int i, int j, int k) {
        this.xSize = 256;
        this.posX = i;
        this.posY = j;
        this.posZ = k;
        this.closeOnEsc = true;
        this.setBackground("menubg.png");
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.guiTop += 10;
        if (this.scroll == null) {
            this.scroll = new GuiCustomScroll(this, 0);
            this.scroll.setSize(165, 188);
        } else {
            this.scroll.clear();
        }
        this.scroll.guiLeft = this.guiLeft + 4;
        this.scroll.guiTop = this.guiTop + 26;
        this.addScroll(this.scroll);
        this.addTextField(new GuiNpcTextField(1, this, this.field_146289_q, this.guiLeft + 4, this.guiTop + 4, 165, 20, search));
        GuiMenuTopButton button = new GuiMenuTopButton(3, this.guiLeft + 4, this.guiTop - 17, "spawner.clones");
        this.addTopButton(button);
        button.active = showingClones == 0;
        button = new GuiMenuTopButton(4, button, "spawner.entities");
        this.addTopButton(button);
        button.active = showingClones == 1;
        button = new GuiMenuTopButton(5, button, "gui.server");
        this.addTopButton(button);
        button.active = showingClones == 2;
        this.addButton(new GuiNpcButton(1, this.guiLeft + 170, this.guiTop + 6, 82, 20, "spawner.mount"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 170, this.guiTop + 50, 82, 20, "spawner.mountplayer"));
        if (showingClones == 0 || showingClones == 2) {
            this.addSideButton(new GuiMenuSideButton(21, this.guiLeft - 90, this.guiTop + 2, 90, 22, "1"));
            this.addSideButton(new GuiMenuSideButton(22, this.guiLeft - 90, this.guiTop + 23, 90, 22, "2"));
            this.addSideButton(new GuiMenuSideButton(23, this.guiLeft - 90, this.guiTop + 44, 90, 22, "3"));
            this.addSideButton(new GuiMenuSideButton(24, this.guiLeft - 90, this.guiTop + 65, 90, 22, "4"));
            this.addSideButton(new GuiMenuSideButton(25, this.guiLeft - 90, this.guiTop + 86, 90, 22, "5"));
            this.addSideButton(new GuiMenuSideButton(26, this.guiLeft - 45, this.guiTop + 107, 45, 22, "6"));
            this.addSideButton(new GuiMenuSideButton(27, this.guiLeft - 90, this.guiTop + 107, 45, 22, "7"));
            this.addSideButton(new GuiMenuSideButton(28, this.guiLeft - 45, this.guiTop + 128, 45, 22, "8"));
            this.addSideButton(new GuiMenuSideButton(29, this.guiLeft - 90, this.guiTop + 128, 45, 22, "9"));
            this.addSideButton(new GuiMenuSideButton(30, this.guiLeft - 45, this.guiTop + 149, 45, 22, "10"));
            this.addSideButton(new GuiMenuSideButton(31, this.guiLeft - 90, this.guiTop + 149, 45, 22, "11"));
            this.addSideButton(new GuiMenuSideButton(32, this.guiLeft - 45, this.guiTop + 170, 45, 22, "12"));
            this.addSideButton(new GuiMenuSideButton(33, this.guiLeft - 90, this.guiTop + 170, 45, 22, "13"));
            this.addSideButton(new GuiMenuSideButton(34, this.guiLeft - 45, this.guiTop + 191, 45, 22, "14"));
            this.addSideButton(new GuiMenuSideButton(35, this.guiLeft - 90, this.guiTop + 191, 45, 22, "15"));
            this.getSideButton((int)(20 + this.activeTab)).active = true;
            this.showClones();
        } else {
            this.showEntities();
        }
    }

    private void showEntities() {
        Map data = EntityList.field_75625_b;
        ArrayList<String> list = new ArrayList<String>();
        for (Object name : data.keySet()) {
            Class c = (Class)data.get(name);
            try {
                if (!EntityLiving.class.isAssignableFrom(c) || c.getConstructor(World.class) == null || Modifier.isAbstract(c.getModifiers())) continue;
                list.add(name.toString());
            }
            catch (SecurityException e) {
                e.printStackTrace();
            }
            catch (NoSuchMethodException noSuchMethodException) {}
        }
        this.list = list;
        this.scroll.setList(this.getSearchList());
    }

    private void showClones() {
        if (showingClones == 2) {
            PacketClient.sendClient(new CloneListPacket(this.activeTab));
            return;
        }
        ArrayList list = new ArrayList();
        this.list = ClientCloneController.Instance.getClones(this.activeTab);
        this.scroll.setList(this.getSearchList());
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (search.equals(this.getTextField(1).func_146179_b())) {
            return;
        }
        search = this.getTextField(1).func_146179_b().toLowerCase();
        this.scroll.setList(this.getSearchList());
    }

    private List<String> getSearchList() {
        if (search.isEmpty()) {
            return new ArrayList<String>(this.list);
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : this.list) {
            if (!name.toLowerCase().contains(search)) continue;
            list.add(name);
        }
        return list;
    }

    private NBTTagCompound getCompound() {
        String sel = this.scroll.getSelected();
        if (sel == null) {
            return null;
        }
        if (showingClones == 0) {
            return ClientCloneController.Instance.getCloneData((ICommandSender)this.player, sel, this.activeTab);
        }
        Entity entity = EntityList.func_75620_a((String)sel, (World)Minecraft.func_71410_x().field_71441_e);
        if (entity == null) {
            return null;
        }
        NBTTagCompound compound = new NBTTagCompound();
        entity.func_70039_c(compound);
        return compound;
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        NBTTagCompound compound;
        int id = guibutton.field_146127_k;
        if (id == 0) {
            this.close();
        }
        if (id == 1 && (compound = this.getCompound()) != null) {
            compound.func_74782_a("Pos", (NBTBase)this.newDoubleNBTList((double)this.posX + 0.5, this.posY + 1, (double)this.posZ + 0.5));
            MountPacket.Spawn(compound);
            this.close();
        }
        if (id == 2) {
            MountPacket.Player();
            this.close();
        }
        if (id == 3) {
            showingClones = 0;
            this.func_73866_w_();
        }
        if (id == 4) {
            showingClones = 1;
            this.func_73866_w_();
        }
        if (id == 5) {
            showingClones = 2;
            this.func_73866_w_();
        }
        if (id > 20) {
            this.activeTab = id - 20;
            this.func_73866_w_();
        }
    }

    protected NBTTagList newDoubleNBTList(double ... par1ArrayOfDouble) {
        NBTTagList nbttaglist = new NBTTagList();
        double[] adouble = par1ArrayOfDouble;
        int i = par1ArrayOfDouble.length;
        for (int j = 0; j < i; ++j) {
            double d1 = adouble[j];
            nbttaglist.func_74742_a((NBTBase)new NBTTagDouble(d1));
        }
        return nbttaglist;
    }

    @Override
    public void save() {
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        NBTTagList nbtlist = compound.func_150295_c("List", 8);
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < nbtlist.func_74745_c(); ++i) {
            list.add(nbtlist.func_150307_f(i));
        }
        this.list = list;
        this.scroll.setList(this.getSearchList());
    }
}

