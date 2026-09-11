/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.recipe.RecipeGetPacket;
import kamkeel.npcs.network.packets.request.recipe.RecipeRemovePacket;
import kamkeel.npcs.network.packets.request.recipe.RecipeSavePacket;
import kamkeel.npcs.network.packets.request.recipe.RecipesGetPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.SubGuiNpcAvailability;
import noppes.npcs.client.gui.script.GuiScriptInterface;
import noppes.npcs.client.gui.util.GuiButtonBiDirectional;
import noppes.npcs.client.gui.util.GuiContainerNPCInterface2;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcButtonYesNo;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.containers.ContainerManageRecipes;
import noppes.npcs.controllers.data.RecipeAnvil;
import noppes.npcs.controllers.data.RecipeCarpentry;
import noppes.npcs.controllers.data.RecipeScript;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public class GuiNpcManageRecipes
extends GuiContainerNPCInterface2
implements IScrollData,
IGuiData,
ICustomScrollListener,
ITextfieldListener,
ISubGuiListener {
    public static int tab = 1;
    public GuiCustomScroll scroll;
    public HashMap<String, Integer> data = new HashMap();
    private ContainerManageRecipes container;
    private String selected = null;
    private ResourceLocation slot;
    private String search = "";

    public GuiNpcManageRecipes(EntityNPCInterface npc, ContainerManageRecipes container) {
        super(npc, container);
        this.container = container;
        this.drawDefaultBackground = false;
        PacketClient.sendClient(new RecipesGetPacket(container.width));
        this.setBackground("inventorymenu.png");
        this.slot = this.getResource("slot.png");
        this.field_147000_g = 200;
        tab = container.width == 1 ? 2 : (container.width == 3 ? 0 : 1);
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        if (this.scroll == null) {
            this.scroll = new GuiCustomScroll((GuiScreen)this, 0, 0);
        }
        this.scroll.setSize(130, 150);
        this.scroll.guiLeft = this.field_147003_i + 280;
        this.scroll.guiTop = this.field_147009_r + 38;
        this.addScroll(this.scroll);
        int y = this.field_147009_r + 10;
        this.addButton(new GuiButtonBiDirectional(0, this.field_147003_i + 280, y, 130, 20, new String[]{"menu.global", "tile.npcCarpentyBench.name", "tile.anvil.name"}, tab));
        y += 106;
        this.addButton(new GuiNpcButton(3, this.field_147003_i + 172, y += 44, 50, 20, "gui.add"));
        this.addButton(new GuiNpcButton(4, this.field_147003_i + 226, y, 50, 20, "gui.remove"));
        this.addButton(new GuiNpcButton(10, this.field_147003_i + 226, y += 30, 50, 20, "gui.copy"));
        if (this.container.width != 1) {
            int buttonPos = this.field_147009_r + 72;
            this.addLabel(new GuiNpcLabel(0, "gui.ignoreDamage", this.field_147003_i + 131, buttonPos));
            this.addButton(new GuiNpcButtonYesNo(5, this.field_147003_i + 235, buttonPos - 5, 40, 20, this.container.recipe.ignoreDamage));
            this.addLabel(new GuiNpcLabel(1, "gui.ignoreNBT", this.field_147003_i + 131, buttonPos += 22));
            this.addButton(new GuiNpcButtonYesNo(6, this.field_147003_i + 235, buttonPos - 5, 40, 20, this.container.recipe.ignoreNBT));
            this.addButton(new GuiNpcButton(15, this.field_147003_i + 172, (buttonPos += 22) - 5, 103, 20, "availability.options"));
            this.addButton(new GuiNpcButton(16, this.field_147003_i + 172, buttonPos + 17, 103, 20, "script.scripts"));
            this.getButton(5).setEnabled(false);
            this.getButton(6).setEnabled(false);
            this.getButton(15).setEnabled(false);
            this.getButton(16).setEnabled(false);
            this.addTextField(new GuiNpcTextField(0, (GuiScreen)this, this.field_146289_q, this.field_147003_i + 8, this.field_147009_r + 8, 160, 20, this.container.recipe.name));
            this.getTextField((int)0).enabled = false;
        } else {
            int buttonPos = this.field_147009_r + 50;
            this.addLabel(new GuiNpcLabel(0, "gui.ignoreMatDamage", this.field_147003_i + 131, buttonPos));
            this.addButton(new GuiNpcButtonYesNo(7, this.field_147003_i + 235, buttonPos - 5, 40, 20, this.container.recipeAnvil.ignoreRepairMaterialDamage));
            this.addLabel(new GuiNpcLabel(1, "gui.ignoreMatNBT", this.field_147003_i + 131, buttonPos += 22));
            this.addButton(new GuiNpcButtonYesNo(8, this.field_147003_i + 235, buttonPos - 5, 40, 20, this.container.recipeAnvil.ignoreRepairMaterialNBT));
            this.addLabel(new GuiNpcLabel(11, "gui.repairPercent", this.field_147003_i + 8, buttonPos));
            this.addTextField(new GuiNpcTextField(1, (GuiScreen)this, this.field_146289_q, this.field_147003_i + 90, buttonPos - 5, 35, 20, this.container.recipeAnvil.getRepairPercentage() + ""));
            this.getTextField((int)1).floatsOnly = true;
            this.getTextField(1).setMinMaxDefaultFloat(1.0f, 100.0f, this.container.recipeAnvil.getRepairPercentage());
            this.getTextField((int)1).enabled = false;
            this.addLabel(new GuiNpcLabel(2, "gui.ignoreItemNBT", this.field_147003_i + 131, buttonPos += 22));
            this.addButton(new GuiNpcButtonYesNo(9, this.field_147003_i + 235, buttonPos - 5, 40, 20, this.container.recipeAnvil.ignoreRepairItemNBT));
            this.addLabel(new GuiNpcLabel(12, "gui.xpCost", this.field_147003_i + 8, buttonPos));
            this.addTextField(new GuiNpcTextField(2, (GuiScreen)this, this.field_146289_q, this.field_147003_i + 70, buttonPos - 5, 55, 20, this.container.recipeAnvil.getXpCost() + ""));
            this.getTextField((int)2).integersOnly = true;
            this.getTextField(2).setMinMaxDefault(0, Integer.MAX_VALUE, this.container.recipeAnvil.getXpCost());
            this.getTextField((int)2).enabled = false;
            this.addButton(new GuiNpcButton(15, this.field_147003_i + 172, (buttonPos += 22) - 5, 103, 20, "availability.options"));
            this.addButton(new GuiNpcButton(16, this.field_147003_i + 172, buttonPos + 17, 103, 20, "script.scripts"));
            this.getButton(7).setEnabled(false);
            this.getButton(8).setEnabled(false);
            this.getButton(9).setEnabled(false);
            this.getButton(15).setEnabled(false);
            this.getButton(16).setEnabled(false);
            this.addTextField(new GuiNpcTextField(0, (GuiScreen)this, this.field_146289_q, this.field_147003_i + 8, this.field_147009_r + 8, 160, 20, this.container.recipeAnvil.name));
            this.getTextField((int)0).enabled = false;
        }
        this.addTextField(new GuiNpcTextField(55, (GuiScreen)this, this.field_146289_q, this.field_147003_i + 280, this.field_147009_r + 8 + 3 + 180, 130, 20, this.search));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 0) {
            GuiButtonBiDirectional buttonBiDirectional = (GuiButtonBiDirectional)button;
            tab = buttonBiDirectional.getValue();
            if (tab == 0) {
                this.getTextField(55).func_146180_a("");
                this.search = "";
                this.scroll.clear();
                this.save();
                NoppesUtil.requestOpenGUI(EnumGuiType.ManageRecipes, 3, 0, 0);
            } else if (tab == 1) {
                this.getTextField(55).func_146180_a("");
                this.search = "";
                this.scroll.clear();
                this.save();
                NoppesUtil.requestOpenGUI(EnumGuiType.ManageRecipes, 4, 0, 0);
            } else {
                this.getTextField(55).func_146180_a("");
                this.search = "";
                this.scroll.clear();
                this.save();
                NoppesUtil.requestOpenGUI(EnumGuiType.ManageRecipes, 1, 0, 0);
            }
        }
        if (button.field_146127_k == 3) {
            this.save();
            this.scroll.clear();
            String name = "New";
            while (this.data.containsKey(name)) {
                name = name + "_";
            }
            if (this.container.width == 1) {
                RecipeAnvil recipe = new RecipeAnvil();
                recipe.name = name;
                PacketClient.sendClient(new RecipeSavePacket(recipe.writeNBT()));
            } else {
                RecipeCarpentry recipe = new RecipeCarpentry(name);
                recipe.isGlobal = this.container.width == 3;
                PacketClient.sendClient(new RecipeSavePacket(recipe.writeNBT()));
            }
        }
        if (button.field_146127_k == 4 && this.data.containsKey(this.scroll.getSelected())) {
            PacketClient.sendClient(new RecipeRemovePacket(this.data.get(this.scroll.getSelected()), this.container.width == 1));
            this.scroll.clear();
        }
        if (button.field_146127_k == 5) {
            boolean bl = this.container.recipe.ignoreDamage = button.getValue() == 1;
        }
        if (button.field_146127_k == 6) {
            boolean bl = this.container.recipe.ignoreNBT = button.getValue() == 1;
        }
        if (button.field_146127_k == 7) {
            boolean bl = this.container.recipeAnvil.ignoreRepairMaterialDamage = button.getValue() == 1;
        }
        if (button.field_146127_k == 8) {
            boolean bl = this.container.recipeAnvil.ignoreRepairMaterialNBT = button.getValue() == 1;
        }
        if (button.field_146127_k == 9) {
            boolean bl = this.container.recipeAnvil.ignoreRepairItemNBT = button.getValue() == 1;
        }
        if (button.field_146127_k == 15) {
            this.save();
            if (this.container.width == 1) {
                this.setSubGui(new SubGuiNpcAvailability(this.container.recipeAnvil.availability));
            } else {
                this.setSubGui(new SubGuiNpcAvailability(this.container.recipe.availability));
            }
        }
        if (button.field_146127_k == 16) {
            this.save();
            if (this.container.width == 1) {
                GuiScriptInterface.open((GuiScreen)this, new RecipeScript(this.container.recipeAnvil.id, true));
            } else if (!this.container.recipe.isGlobal) {
                GuiScriptInterface.open((GuiScreen)this, new RecipeScript(this.container.recipe.id, false));
            }
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
            this.scroll.resetScroll();
            this.scroll.setList(this.getSearchList());
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

    @Override
    public void setGuiData(NBTTagCompound compound) {
        if (compound.func_74764_b("IsAnvil")) {
            RecipeAnvil recipe = new RecipeAnvil();
            recipe.readNBT(compound);
            this.container.setRecipe(recipe);
            this.container.width = 1;
            tab = 2;
            this.fixButtons();
        } else {
            RecipeCarpentry recipe = RecipeCarpentry.create(compound);
            recipe.readNBT(compound);
            this.container.setRecipe(recipe);
            this.fixButtons();
        }
    }

    private void fixButtons() {
        if (tab == 2) {
            GuiNpcButton script;
            GuiNpcButton avail;
            this.getTextField(0).func_146180_a(this.container.recipeAnvil.name);
            this.getTextField(1).func_146180_a(this.container.recipeAnvil.getRepairPercentage() + "");
            this.getTextField(2).func_146180_a(this.container.recipeAnvil.getXpCost() + "");
            this.setSelected(this.container.recipeAnvil.name);
            this.getTextField((int)0).enabled = true;
            this.getTextField((int)1).enabled = true;
            this.getTextField((int)2).enabled = true;
            GuiNpcButtonYesNo btnMatDamage = (GuiNpcButtonYesNo)this.getButton(7);
            GuiNpcButtonYesNo btnMatNBT = (GuiNpcButtonYesNo)this.getButton(8);
            GuiNpcButtonYesNo btnItemNBT = (GuiNpcButtonYesNo)this.getButton(9);
            if (btnMatDamage != null) {
                btnMatDamage.setDisplay(this.container.recipeAnvil.ignoreRepairMaterialDamage ? 1 : 0);
                btnMatDamage.setEnabled(true);
            }
            if (btnMatNBT != null) {
                btnMatNBT.setDisplay(this.container.recipeAnvil.ignoreRepairMaterialNBT ? 1 : 0);
                btnMatNBT.setEnabled(true);
            }
            if (btnItemNBT != null) {
                btnItemNBT.setDisplay(this.container.recipeAnvil.ignoreRepairItemNBT ? 1 : 0);
                btnItemNBT.setEnabled(true);
            }
            if ((avail = this.getButton(15)) != null) {
                avail.setEnabled(true);
            }
            if ((script = this.getButton(16)) != null) {
                script.setEnabled(true);
            }
        } else {
            this.getTextField(0).func_146180_a(this.container.recipe.name);
            this.getTextField((int)0).enabled = true;
            this.getButton(5).setEnabled(true);
            this.getButton(5).setDisplay(this.container.recipe.ignoreDamage ? 1 : 0);
            this.getButton(6).setEnabled(true);
            this.getButton(6).setDisplay(this.container.recipe.ignoreNBT ? 1 : 0);
            this.setSelected(this.container.recipe.name);
            if (!this.container.recipe.isGlobal) {
                GuiNpcButton script;
                GuiNpcButton avail = this.getButton(15);
                if (avail != null) {
                    avail.setEnabled(true);
                }
                if ((script = this.getButton(16)) != null) {
                    script.setEnabled(true);
                }
            }
        }
    }

    @Override
    protected void func_146976_a(float f, int x, int y) {
        super.func_146976_a(f, x, y);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(this.slot);
        for (int i = 0; i < this.container.width; ++i) {
            for (int j = 0; j < this.container.width; ++j) {
                this.func_73729_b(this.field_147003_i + i * 18 + 7, this.field_147009_r + j * 18 + 34, 0, 0, 18, 18);
            }
        }
        if (this.container.width == 1) {
            this.func_73729_b(this.field_147003_i + 101, this.field_147009_r + 34, 0, 0, 18, 18);
            this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"gui.material"), this.field_147003_i + 28, this.field_147009_r + 38, CustomNpcResourceListener.DefaultTextColor);
        } else {
            this.func_73729_b(this.field_147003_i + 86, this.field_147009_r + 60, 0, 0, 18, 18);
        }
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        String name = this.scroll.getSelected();
        this.data = data;
        this.scroll.setList(this.getSearchList());
        boolean bl = this.getTextField((int)0).enabled = name != null;
        if (this.container.width == 1) {
            this.getButton(7).setEnabled(name != null);
            this.getButton(8).setEnabled(name != null);
            this.getButton(9).setEnabled(name != null);
        } else {
            this.getButton(5).setEnabled(name != null);
            this.getButton(6).setEnabled(name != null);
        }
        if (name != null) {
            this.scroll.setSelected(name);
        }
    }

    @Override
    public void setSelected(String selected) {
        this.selected = selected;
        this.scroll.setSelected(selected);
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        this.save();
        this.selected = this.scroll.getSelected();
        if (this.container.width == 1) {
            PacketClient.sendClient(new RecipeGetPacket(this.data.get(this.selected), true));
        } else {
            PacketClient.sendClient(new RecipeGetPacket(this.data.get(this.selected), false));
        }
    }

    @Override
    public void save() {
        GuiNpcTextField.unfocus();
        if (this.selected != null && this.data.containsKey(this.selected)) {
            this.container.saveRecipe();
            if (this.container.width == 1) {
                PacketClient.sendClient(new RecipeSavePacket(this.container.recipeAnvil.writeNBT()));
            } else {
                PacketClient.sendClient(new RecipeSavePacket(this.container.recipe.writeNBT()));
            }
        }
    }

    @Override
    public void unFocused(GuiNpcTextField guiNpcTextField) {
        String name;
        if (guiNpcTextField.id == 0 && !(name = guiNpcTextField.func_146179_b()).isEmpty() && !this.data.containsKey(name)) {
            if (this.container.width == 1) {
                String old = this.container.recipeAnvil.name;
                this.data.remove(this.container.recipeAnvil.name);
                this.container.recipeAnvil.name = name;
                this.data.put(this.container.recipeAnvil.name, this.container.recipeAnvil.id);
                this.selected = name;
                this.scroll.replace(old, this.container.recipeAnvil.name);
            } else {
                String old = this.container.recipe.name;
                this.data.remove(this.container.recipe.name);
                this.container.recipe.name = name;
                this.data.put(this.container.recipe.name, this.container.recipe.id);
                this.selected = name;
                this.scroll.replace(old, this.container.recipe.name);
            }
        }
        if (guiNpcTextField.id == 1) {
            float percent = guiNpcTextField.getFloat();
            if (this.container.width == 1) {
                this.container.recipeAnvil.repairPercentage = percent;
            }
        }
        if (guiNpcTextField.id == 2) {
            int xpCost = guiNpcTextField.getInteger();
            if (this.container.width == 1) {
                this.container.recipeAnvil.xpCost = xpCost;
            }
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiNpcAvailability) {
            this.fixButtons();
        }
    }
}

