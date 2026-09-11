/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client.gui.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.naturalspawns.NaturalSpawnGetAllPacket;
import kamkeel.npcs.network.packets.request.naturalspawns.NaturalSpawnGetPacket;
import kamkeel.npcs.network.packets.request.naturalspawns.NaturalSpawnRemovePacket;
import kamkeel.npcs.network.packets.request.naturalspawns.NaturalSpawnSavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.GuiNpcMobSpawnerSelector;
import noppes.npcs.client.gui.SubGuiNpcBiomes;
import noppes.npcs.client.gui.SubGuiNpcDimensions;
import noppes.npcs.client.gui.SubGuiSpawningOptions;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiHoverText;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcSlider;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.ISliderListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.data.SpawnData;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNpcNaturalSpawns
extends GuiNPCInterface2
implements IGuiData,
IScrollData,
ITextfieldListener,
ICustomScrollListener,
ISliderListener {
    private GuiCustomScroll scrollNaturalSpawns;
    private final GuiCustomScroll spawnEntryScroll = new GuiCustomScroll((GuiScreen)this, 20, false);
    private HashMap<String, Integer> data = new HashMap();
    private String search = "";
    private SpawnData spawn = new SpawnData();

    public GuiNpcNaturalSpawns(EntityNPCInterface npc) {
        super(npc);
        PacketClient.sendClient(new NaturalSpawnGetAllPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        if (this.scrollNaturalSpawns == null) {
            this.scrollNaturalSpawns = new GuiCustomScroll((GuiScreen)this, 0, 0);
            this.scrollNaturalSpawns.setSize(143, 185);
        }
        this.scrollNaturalSpawns.guiLeft = this.guiLeft + 214;
        this.scrollNaturalSpawns.guiTop = this.guiTop + 4;
        this.addScroll(this.scrollNaturalSpawns);
        this.addTextField(new GuiNpcTextField(55, this, this.field_146289_q, this.guiLeft + 214, this.guiTop + 4 + 3 + 185, 143, 20, this.search));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 358, this.guiTop + 38, 58, 20, "gui.add"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 358, this.guiTop + 61, 58, 20, "gui.remove"));
        this.getButton(1).setHoverText("tooltip.naturalspawns.main.addProfile");
        this.getButton(2).setHoverText("tooltip.naturalspawns.main.removeProfile");
        if (this.getTextField(55) != null) {
            this.getTextField(55).setHoverText("tooltip.naturalspawns.main.search");
        }
        if (this.spawn.id >= 0) {
            this.showSpawn();
        }
    }

    private void showSpawn() {
        this.addLabel(new GuiNpcLabel(1, "gui.title", this.guiLeft + 4, this.guiTop + 8));
        this.addTextField(new GuiNpcTextField(1, this, this.field_146289_q, this.guiLeft + 60, this.guiTop + 3, 140, 20, this.spawn.name));
        int y = this.guiTop + 30;
        this.addLabel(new GuiNpcLabel(3, "spawning.biomes", this.guiLeft + 4, y));
        this.addButton(new GuiNpcButton(3, this.guiLeft + 40, y - 5, 50, 20, "selectServer.edit"));
        this.getButton(3).setHoverText("tooltip.naturalspawns.main.biomes");
        this.addLabel(new GuiNpcLabel(11, StatCollector.func_74838_a((String)"Dimensions"), this.guiLeft + 95, y));
        this.addButton(new GuiNpcButton(11, this.guiLeft + 150, y - 5, 50, 20, "selectServer.edit"));
        this.getButton(11).setHoverText("tooltip.naturalspawns.main.dimensions");
        this.addLabel(new GuiNpcLabel(4, "spawning.options", this.guiLeft + 4, y += 22));
        this.addButton(new GuiNpcButton(4, this.guiLeft + 90, y - 5, 50, 20, "selectServer.edit"));
        this.getButton(4).setHoverText("tooltip.naturalspawns.main.options");
        this.addSlider(new GuiNpcSlider(this, 5, this.guiLeft + 4, y += 17, 180, 20, (float)this.spawn.field_76292_a / 100.0f));
        this.addExtra(new GuiHoverText(1, "spawning.naturalInfoPerformance", this.guiLeft + 188, y + 5));
        this.spawnEntryScroll.guiLeft = this.guiLeft + 4;
        this.spawnEntryScroll.guiTop = y + 40;
        this.spawnEntryScroll.setSize(50, 102);
        ArrayList<String> list = new ArrayList<String>();
        Set<Integer> keySet = this.spawn.spawnCompounds.keySet();
        for (int i : keySet) {
            list.add("" + i);
        }
        this.spawnEntryScroll.setList(list);
        this.addScroll(this.spawnEntryScroll);
        this.addButton(new GuiNpcButton(21, this.guiLeft + 6, y += 20, 20, 20, "+"));
        this.getButton(21).setHoverText("tooltip.naturalspawns.main.addEntry");
        if (this.spawnEntryScroll.hasSelected()) {
            int selected = Integer.parseInt(this.spawnEntryScroll.getSelected());
            this.addButton(new GuiNpcButton(22, this.guiLeft + 32, y, 20, 20, "-"));
            this.getButton(22).setHoverText("tooltip.naturalspawns.main.removeEntry");
            GuiNpcTextField num = new GuiNpcTextField(25, this, this.guiLeft + 60, y += 10, 30, 20, "" + selected);
            num.integersOnly = true;
            this.addTextField(num);
            this.getTextField(25).setHoverText("tooltip.naturalspawns.main.slot");
            this.addButton(new GuiNpcButton(26, this.guiLeft + 92, y, 100, 20, this.getTitle(this.spawn.spawnCompounds.get(selected))));
            this.getButton(26).setHoverText("tooltip.naturalspawns.main.selectEntry");
        }
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(55) != null && this.getTextField(55).func_146206_l()) {
            if (this.search.equals(this.getTextField(55).func_146179_b())) {
                return;
            }
            this.search = this.getTextField(55).func_146179_b().toLowerCase();
            this.scrollNaturalSpawns.resetScroll();
            this.scrollNaturalSpawns.setList(this.getSearchList());
        }
    }

    private List<String> getSearchList() {
        if (this.search.isEmpty()) {
            return new ArrayList<String>(this.data.keySet());
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : this.data.keySet()) {
            if (!name.toLowerCase().contains(this.search)) continue;
            list.add(name);
        }
        return list;
    }

    private String getTitle(NBTTagCompound compound) {
        if (compound != null && compound.func_74764_b("ClonedName")) {
            return compound.func_74779_i("ClonedName");
        }
        return "gui.selectnpc";
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 1) {
            this.save();
            String name = "New";
            while (this.data.containsKey(name)) {
                name = name + "_";
            }
            SpawnData spawn = new SpawnData();
            spawn.name = name;
            PacketClient.sendClient(new NaturalSpawnSavePacket(spawn.writeNBT(new NBTTagCompound())));
            this.spawnEntryScroll.selected = -1;
        }
        if (id == 2 && this.data.containsKey(this.scrollNaturalSpawns.getSelected())) {
            PacketClient.sendClient(new NaturalSpawnRemovePacket(this.spawn.id));
            this.spawn = new SpawnData();
            this.scrollNaturalSpawns.clear();
            this.spawnEntryScroll.selected = -1;
        }
        if (id == 3) {
            this.setSubGui(new SubGuiNpcBiomes(this.spawn));
        }
        if (id == 11) {
            this.setSubGui(new SubGuiNpcDimensions(this.spawn));
        }
        if (id == 4) {
            this.setSubGui(new SubGuiSpawningOptions(this.spawn));
        }
        if (id == 21) {
            int addId = 0;
            if (this.spawnEntryScroll.hasSelected()) {
                int selected = Integer.parseInt(this.spawnEntryScroll.getSelected());
                ArrayList<Integer> keys = new ArrayList<Integer>(this.spawn.spawnCompounds.keySet());
                int keyIndex = keys.indexOf(selected);
                do {
                    addId = keys.get(keyIndex) + 1;
                    ++keyIndex;
                } while (this.spawn.spawnCompounds.containsKey(addId));
            } else if (this.spawn.spawnCompounds.size() > 0) {
                addId = (Integer)this.spawn.spawnCompounds.keySet().toArray()[this.spawn.spawnCompounds.size() - 1] + 1;
            }
            this.spawn.spawnCompounds.put(addId, new NBTTagCompound());
            this.func_73866_w_();
        }
        if (id == 22 && this.spawnEntryScroll.hasSelected()) {
            int selected = Integer.parseInt(this.spawnEntryScroll.getSelected());
            this.spawn.spawnCompounds.remove(selected);
            this.spawnEntryScroll.selected = -1;
            this.func_73866_w_();
        }
        if (id == 26) {
            this.setSubGui(new GuiNpcMobSpawnerSelector());
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == 1) {
            String name = textfield.func_146179_b();
            if (name.isEmpty() || this.data.containsKey(name)) {
                textfield.func_146180_a(this.spawn.name);
            } else {
                String old = this.spawn.name;
                this.data.remove(old);
                this.spawn.name = name;
                this.data.put(this.spawn.name, this.spawn.id);
                this.scrollNaturalSpawns.replace(old, this.spawn.name);
            }
        }
        if (textfield.id == 25 && this.spawnEntryScroll.hasSelected()) {
            int selected = Integer.parseInt(this.spawnEntryScroll.getSelected());
            if (this.spawnEntryScroll.getList().contains("" + textfield.getInteger())) {
                textfield.func_146180_a("" + selected);
                return;
            }
            NBTTagCompound compound = this.spawn.spawnCompounds.get(selected);
            this.spawn.spawnCompounds.remove(selected);
            this.spawn.spawnCompounds.put(textfield.getInteger(), compound);
            this.spawnEntryScroll.selected = -1;
            this.func_73866_w_();
        }
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        String name = this.scrollNaturalSpawns.getSelected();
        this.data = data;
        this.scrollNaturalSpawns.setList(this.getSearchList());
        if (name != null) {
            this.scrollNaturalSpawns.setSelected(name);
        }
        this.func_73866_w_();
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 0) {
            this.save();
            String selected = this.scrollNaturalSpawns.getSelected();
            this.spawn = new SpawnData();
            PacketClient.sendClient(new NaturalSpawnGetPacket(this.data.get(selected)));
        }
        if (guiCustomScroll.id == 20) {
            this.func_73866_w_();
        }
    }

    @Override
    public void save() {
        GuiNpcTextField.unfocus();
        if (this.spawn.id >= 0) {
            PacketClient.sendClient(new NaturalSpawnSavePacket(this.spawn.writeNBT(new NBTTagCompound())));
        }
    }

    @Override
    public void setSelected(String selected) {
    }

    @Override
    public void closeSubGui(SubGuiInterface gui) {
        super.closeSubGui(gui);
        if (gui instanceof GuiNpcMobSpawnerSelector && this.spawnEntryScroll.hasSelected()) {
            GuiNpcMobSpawnerSelector selector = (GuiNpcMobSpawnerSelector)gui;
            int selected = Integer.parseInt(this.spawnEntryScroll.getSelected());
            this.spawn.spawnCompounds.put(selected, selector.getCompound());
            this.func_73866_w_();
        }
        if (gui instanceof SubGuiNpcBiomes || gui instanceof SubGuiNpcDimensions || gui instanceof SubGuiSpawningOptions) {
            this.save();
        }
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        this.spawn.readNBT(compound);
        this.setSelected(this.spawn.name);
        this.func_73866_w_();
    }

    @Override
    public void mouseDragged(GuiNpcSlider guiNpcSlider) {
        guiNpcSlider.field_146126_j = StatCollector.func_74838_a((String)"spawning.weightedChance") + ": " + (int)(guiNpcSlider.sliderValue * 100.0f);
    }

    @Override
    public void mousePressed(GuiNpcSlider guiNpcSlider) {
    }

    @Override
    public void mouseReleased(GuiNpcSlider guiNpcSlider) {
        this.spawn.field_76292_a = (int)(guiNpcSlider.sliderValue * 100.0f);
    }
}

