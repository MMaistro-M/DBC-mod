/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.player;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.gui.player.GuiAnvilRecipes;
import noppes.npcs.client.gui.util.GuiContainerNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.containers.ContainerAnvilRepair;
import noppes.npcs.controllers.RecipeController;
import org.lwjgl.opengl.GL11;

public class GuiNpcAnvil
extends GuiContainerNPCInterface {
    private final ResourceLocation resource = new ResourceLocation("customnpcs", "textures/gui/anvil.png");
    private final ContainerAnvilRepair container;
    private GuiNpcButton button;

    public GuiNpcAnvil(ContainerAnvilRepair container) {
        super(null, container);
        this.container = container;
        this.title = "";
        this.field_146291_p = false;
        this.closeOnEsc = true;
        this.field_147000_g = 180;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.button = new GuiNpcButton(0, this.field_147003_i + 158, this.field_147009_r + 4, 12, 20, "...");
        this.addButton(this.button);
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        this.displayGuiScreen(new GuiAnvilRecipes());
    }

    @Override
    protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
        this.button.field_146124_l = RecipeController.Instance != null && !RecipeController.Instance.getAnvilList().isEmpty();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(this.resource);
        int l = (this.field_146294_l - this.field_146999_f) / 2;
        int i1 = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(l, i1, 0, 0, this.field_146999_f, this.field_147000_g);
        super.func_146976_a(partialTicks, mouseX, mouseY);
        if (!this.container.canPickupResult()) {
            this.drawRedSlotOutline(this.field_147003_i + 133, this.field_147009_r + 47);
        }
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"tile.anvil.name"), this.field_147003_i + 4, this.field_147009_r + 4, CustomNpcResourceListener.DefaultTextColor);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"container.inventory"), this.field_147003_i + 4, this.field_147009_r + 87, CustomNpcResourceListener.DefaultTextColor);
        String status = "";
        if (this.container.repairCost > 0) {
            status = this.container.repairCost > this.field_146297_k.field_71439_g.field_71067_cb ? "Repair cost: " + this.container.repairCost + " XP" : "Repair cost: " + this.container.repairCost + " XP";
        } else if (this.container.anvilMatrix.func_70463_b(0, 0) != null && this.container.anvilMatrix.func_70463_b(0, 0).func_77984_f() && this.container.anvilMatrix.func_70463_b(0, 0).func_77960_j() <= 0) {
            status = "Item is already fully repaired";
        }
        int textColor = CustomNpcResourceListener.DefaultTextColor;
        if (this.container.repairCost > this.field_146297_k.field_71439_g.field_71067_cb) {
            textColor = 0xFF0000;
        }
        this.field_146289_q.func_78276_b(status, this.field_147003_i + 5, this.field_147009_r + 75, textColor);
    }

    private void drawRedSlotOutline(int x, int y) {
        int c = -65536;
        GuiNpcAnvil.func_73734_a((int)(x - 1), (int)(y - 1), (int)(x + 17), (int)y, (int)c);
        GuiNpcAnvil.func_73734_a((int)(x - 1), (int)(y + 16), (int)(x + 17), (int)(y + 17), (int)c);
        GuiNpcAnvil.func_73734_a((int)(x - 1), (int)y, (int)x, (int)(y + 16), (int)c);
        GuiNpcAnvil.func_73734_a((int)(x + 16), (int)y, (int)(x + 17), (int)(y + 16), (int)c);
    }

    @Override
    public void save() {
    }
}

