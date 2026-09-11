/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.script;

import java.util.HashMap;
import kamkeel.npcs.network.packets.request.script.ScriptInfoPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.gui.script.GuiScriptInterface;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.controllers.data.ForgeDataScript;
import noppes.npcs.controllers.data.GlobalNPCDataScript;
import noppes.npcs.controllers.data.PlayerDataScript;
import org.lwjgl.opengl.GL11;

public class GuiScriptGlobal
extends GuiNPCInterface
implements IGuiData {
    private final ResourceLocation resource = new ResourceLocation("customnpcs", "textures/gui/menubg.png");
    private HashMap<Integer, Class<?>> scriptGuiClasses = new HashMap();

    public GuiScriptGlobal() {
        this.xSize = 176;
        this.ySize = 222;
        this.drawDefaultBackground = false;
        this.title = "";
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        GuiNpcButton playerButton = new GuiNpcButton(0, this.guiLeft + 38, this.guiTop + 20, 100, 20, "Players");
        playerButton.setEnabled(false);
        this.addButton(playerButton);
        GuiNpcButton forgeButton = new GuiNpcButton(1, this.guiLeft + 38, this.guiTop + 50, 100, 20, "Forge");
        forgeButton.setEnabled(false);
        this.addButton(forgeButton);
        GuiNpcButton npcButton = new GuiNpcButton(2, this.guiLeft + 38, this.guiTop + 80, 100, 20, "All NPCs");
        npcButton.setEnabled(false);
        this.addButton(npcButton);
        ScriptInfoPacket.Get();
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        this.getButton(0).setEnabled(compound.func_74767_n("PlayerScriptsEnabled") && compound.func_74767_n("ScriptsEnabled"));
        this.getButton(1).setEnabled(compound.func_74767_n("ForgeScriptsEnabled") && compound.func_74767_n("ScriptsEnabled"));
        this.getButton(2).setEnabled(compound.func_74767_n("GlobalNPCScriptsEnabled") && compound.func_74767_n("ScriptsEnabled"));
    }

    public void addScriptGui(Class<?> guiClass, String buttonText, boolean enabled) {
        int buttonId = this.buttons.size();
        this.scriptGuiClasses.put(buttonId, guiClass);
        GuiNpcButton npcButton = new GuiNpcButton(buttonId, this.guiLeft + 38, this.guiTop + 80 + (buttonId - 2) * 30, 100, 20, buttonText);
        npcButton.setEnabled(enabled);
        this.addButton(npcButton);
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        this.func_146276_q_();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(this.resource);
        this.func_73729_b(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        super.func_73863_a(i, j, f);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton.field_146127_k == 0) {
            GuiScriptInterface.open(this, new PlayerDataScript(null));
        }
        if (guibutton.field_146127_k == 1) {
            GuiScriptInterface.open(this, new ForgeDataScript());
        }
        if (guibutton.field_146127_k == 2) {
            GuiScriptInterface.open(this, new GlobalNPCDataScript(null));
        }
        if (guibutton.field_146127_k > 2) {
            try {
                Class<?> guiClass = this.scriptGuiClasses.get(guibutton.field_146127_k);
                this.displayGuiScreen((GuiScreen)guiClass.newInstance());
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    @Override
    public void func_73869_a(char c, int i) {
        if (i == 1 || this.isInventoryKey(i)) {
            this.close();
        }
    }

    @Override
    public void save() {
    }
}

