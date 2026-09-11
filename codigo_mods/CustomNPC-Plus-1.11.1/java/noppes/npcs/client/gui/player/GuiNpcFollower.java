/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.player;

import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.player.FollowerPacket;
import kamkeel.npcs.network.packets.player.GetNPCRole;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.gui.util.GuiContainerNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.containers.ContainerNPCFollower;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleFollower;
import org.lwjgl.opengl.GL11;

public class GuiNpcFollower
extends GuiContainerNPCInterface
implements IGuiData {
    private final ResourceLocation resource = new ResourceLocation("customnpcs", "textures/gui/follower.png");
    private EntityNPCInterface npc;
    private RoleFollower role;

    public GuiNpcFollower(EntityNPCInterface npc, ContainerNPCFollower container) {
        super(npc, container);
        this.npc = npc;
        this.role = (RoleFollower)npc.roleInterface;
        this.closeOnEsc = true;
        PacketClient.sendClient(new GetNPCRole());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.field_146292_n.clear();
        this.addButton(new GuiNpcButton(4, this.field_147003_i + 100, this.field_147009_r + 110, 50, 20, new String[]{StatCollector.func_74838_a((String)"follower.waiting"), StatCollector.func_74838_a((String)"follower.following")}, this.role.isFollowing ? 1 : 0));
        if (!this.role.infiniteDays) {
            this.addButton(new GuiNpcButton(5, this.field_147003_i + 8, this.field_147009_r + 30, 50, 20, StatCollector.func_74838_a((String)"follower.hire")));
        }
    }

    @Override
    public void func_146284_a(GuiButton guibutton) {
        super.func_146284_a(guibutton);
        int id = guibutton.field_146127_k;
        if (id == 4) {
            PacketClient.sendClient(new FollowerPacket(FollowerPacket.Action.State));
        }
        if (id == 5) {
            PacketClient.sendClient(new FollowerPacket(FollowerPacket.Action.Extend));
        }
    }

    @Override
    protected void func_146979_b(int par1, int par2) {
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"follower.health") + ": " + this.npc.func_110143_aJ() + "/" + this.npc.func_110138_aP(), 62, 70, CustomNpcResourceListener.DefaultTextColor);
        if (!this.role.infiniteDays) {
            if (this.role.getDaysLeft() <= 1) {
                this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"follower.daysleft") + ": " + StatCollector.func_74838_a((String)"follower.lastday"), 62, 94, CustomNpcResourceListener.DefaultTextColor);
            } else {
                this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"follower.daysleft") + ": " + (this.role.getDaysLeft() - 1), 62, 94, CustomNpcResourceListener.DefaultTextColor);
            }
        }
    }

    @Override
    protected void func_146976_a(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(this.resource);
        int l = this.field_147003_i;
        int i1 = this.field_147009_r;
        this.func_73729_b(l, i1, 0, 0, this.field_146999_f, this.field_147000_g);
        int index = 0;
        if (!this.role.infiniteDays) {
            for (int id : this.role.inventory.items.keySet()) {
                ItemStack itemstack = this.role.inventory.items.get(id);
                if (itemstack == null) continue;
                int days = 1;
                if (this.role.rates.containsKey(id)) {
                    days = this.role.rates.get(id);
                }
                int yOffset = index * 20;
                int x = this.field_147003_i + 68;
                int y = this.field_147009_r + yOffset + 4;
                RenderHelper.func_74520_c();
                GL11.glEnable((int)32826);
                GL11.glEnable((int)2903);
                GL11.glEnable((int)2896);
                field_146296_j.func_77015_a(this.field_146289_q, this.field_146297_k.field_71446_o, itemstack, x + 11, y);
                field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.field_71446_o, itemstack, x + 11, y);
                RenderHelper.func_74518_a();
                GL11.glDisable((int)32826);
                String daysS = days + " " + (days == 1 ? StatCollector.func_74838_a((String)"follower.day") : StatCollector.func_74838_a((String)"follower.days"));
                this.field_146289_q.func_78276_b(" = " + daysS, x + 27, y + 4, CustomNpcResourceListener.DefaultTextColor);
                ++index;
            }
        }
        this.drawNpc(33, 131);
    }

    @Override
    public void save() {
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        this.npc.roleInterface.readFromNBT(compound);
        this.func_73866_w_();
    }
}

