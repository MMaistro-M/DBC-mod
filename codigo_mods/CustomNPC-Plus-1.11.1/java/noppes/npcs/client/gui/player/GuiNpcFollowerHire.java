/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.player;

import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.player.FollowerPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.gui.util.GuiContainerNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.containers.ContainerNPCFollowerHire;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleFollower;
import org.lwjgl.opengl.GL11;

public class GuiNpcFollowerHire
extends GuiContainerNPCInterface {
    private final ResourceLocation resource = new ResourceLocation("customnpcs", "textures/gui/followerhire.png");
    private EntityNPCInterface npc;
    private ContainerNPCFollowerHire container;
    private RoleFollower role;

    public GuiNpcFollowerHire(EntityNPCInterface npc, ContainerNPCFollowerHire container) {
        super(npc, container);
        this.container = container;
        this.npc = npc;
        this.role = (RoleFollower)npc.roleInterface;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addButton(new GuiNpcButton(5, this.field_147003_i + 26, this.field_147009_r + 60, 50, 20, StatCollector.func_74838_a((String)"follower.hire")));
    }

    @Override
    public void func_146284_a(GuiButton guibutton) {
        super.func_146284_a(guibutton);
        if (guibutton.field_146127_k == 5) {
            PacketClient.sendClient(new FollowerPacket(FollowerPacket.Action.Hire));
            this.close();
        }
    }

    @Override
    protected void func_146979_b(int par1, int par2) {
    }

    @Override
    protected void func_146976_a(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(this.resource);
        int l = (this.field_146294_l - this.field_146999_f) / 2;
        int i1 = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(l, i1, 0, 0, this.field_146999_f, this.field_147000_g);
        int index = 0;
        for (int id : this.role.inventory.items.keySet()) {
            ItemStack itemstack = this.role.inventory.items.get(id);
            if (itemstack == null) continue;
            int days = 1;
            if (this.role.rates.containsKey(id)) {
                days = this.role.rates.get(id);
            }
            int yOffset = index * 26;
            int x = this.field_147003_i + 78;
            int y = this.field_147009_r + yOffset + 10;
            GL11.glEnable((int)32826);
            RenderHelper.func_74520_c();
            field_146296_j.func_77015_a(this.field_146289_q, this.field_146297_k.field_71446_o, itemstack, x + 11, y);
            field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.field_71446_o, itemstack, x + 11, y);
            RenderHelper.func_74518_a();
            GL11.glDisable((int)32826);
            String daysS = days + " " + (days == 1 ? StatCollector.func_74838_a((String)"follower.day") : StatCollector.func_74838_a((String)"follower.days"));
            this.field_146289_q.func_78276_b(" = " + daysS, x + 27, y + 4, CustomNpcResourceListener.DefaultTextColor);
            if (this.func_146978_c(x - this.field_147003_i + 11, y - this.field_147009_r, 16, 16, this.mouseX, this.mouseY)) {
                this.func_146285_a(itemstack, this.mouseX, this.mouseY);
            }
            ++index;
        }
    }

    @Override
    public void save() {
    }
}

