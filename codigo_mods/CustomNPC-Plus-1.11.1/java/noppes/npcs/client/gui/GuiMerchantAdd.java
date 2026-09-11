/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.IMerchant
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.C17PacketCustomPayload
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.village.MerchantRecipe
 *  net.minecraft.village.MerchantRecipeList
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.MerchantUpdatePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import noppes.npcs.ServerEventsHandler;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.containers.ContainerMerchantAdd;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiMerchantAdd
extends GuiContainer {
    private static final ResourceLocation merchantGuiTextures = new ResourceLocation("textures/gui/container/villager.png");
    private IMerchant theIMerchant = ServerEventsHandler.Merchant;
    private MerchantButton nextRecipeButtonIndex;
    private MerchantButton previousRecipeButtonIndex;
    private int currentRecipeIndex;
    private String field_94082_v = I18n.func_135052_a((String)"entity.Villager.name", (Object[])new Object[0]);

    public GuiMerchantAdd() {
        super((Container)new ContainerMerchantAdd((EntityPlayer)Minecraft.func_71410_x().field_71439_g, (IMerchant)ServerEventsHandler.Merchant, (World)Minecraft.func_71410_x().field_71441_e));
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        int i = (this.field_146294_l - this.field_146999_f) / 2;
        int j = (this.field_146295_m - this.field_147000_g) / 2;
        this.nextRecipeButtonIndex = new MerchantButton(1, i + 120 + 27, j + 24 - 1, true);
        this.field_146292_n.add(this.nextRecipeButtonIndex);
        this.previousRecipeButtonIndex = new MerchantButton(2, i + 36 - 19, j + 24 - 1, false);
        this.field_146292_n.add(this.previousRecipeButtonIndex);
        this.field_146292_n.add(new GuiNpcButton(4, i + this.field_146999_f, j + 20, 60, 20, "gui.remove"));
        this.field_146292_n.add(new GuiNpcButton(5, i + this.field_146999_f, j + 50, 60, 20, "gui.add"));
        this.nextRecipeButtonIndex.field_146124_l = false;
        this.previousRecipeButtonIndex.field_146124_l = false;
    }

    protected void func_146979_b(int par1, int par2) {
        this.field_146289_q.func_78276_b(this.field_94082_v, this.field_146999_f / 2 - this.field_146289_q.func_78256_a(this.field_94082_v) / 2, 6, CustomNpcResourceListener.DefaultTextColor);
        this.field_146289_q.func_78276_b(I18n.func_135052_a((String)"container.inventory", (Object[])new Object[0]), 8, this.field_147000_g - 96 + 2, CustomNpcResourceListener.DefaultTextColor);
    }

    public void func_73876_c() {
        super.func_73876_c();
        Minecraft mc = Minecraft.func_71410_x();
        MerchantRecipeList merchantrecipelist = this.theIMerchant.func_70934_b((EntityPlayer)mc.field_71439_g);
        if (merchantrecipelist != null) {
            this.nextRecipeButtonIndex.field_146124_l = this.currentRecipeIndex < merchantrecipelist.size() - 1;
            this.previousRecipeButtonIndex.field_146124_l = this.currentRecipeIndex > 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void func_146284_a(GuiButton par1GuiButton) {
        MerchantRecipeList merchantrecipelist;
        boolean flag = false;
        Minecraft mc = Minecraft.func_71410_x();
        if (par1GuiButton == this.nextRecipeButtonIndex) {
            ++this.currentRecipeIndex;
            flag = true;
        } else if (par1GuiButton == this.previousRecipeButtonIndex) {
            --this.currentRecipeIndex;
            flag = true;
        }
        if (par1GuiButton.field_146127_k == 4 && this.currentRecipeIndex < (merchantrecipelist = this.theIMerchant.func_70934_b((EntityPlayer)mc.field_71439_g)).size()) {
            merchantrecipelist.remove(this.currentRecipeIndex);
            if (this.currentRecipeIndex > 0) {
                --this.currentRecipeIndex;
            }
            PacketClient.sendClient(new MerchantUpdatePacket(ServerEventsHandler.Merchant.func_145782_y(), merchantrecipelist));
        }
        if (par1GuiButton.field_146127_k == 5) {
            ItemStack item1 = this.field_147002_h.func_75139_a(0).func_75211_c();
            ItemStack item2 = this.field_147002_h.func_75139_a(1).func_75211_c();
            ItemStack sold = this.field_147002_h.func_75139_a(2).func_75211_c();
            if (item1 == null && item2 != null) {
                item1 = item2;
                item2 = null;
            }
            if (item1 != null && sold != null) {
                item1 = item1.func_77946_l();
                sold = sold.func_77946_l();
                if (item2 != null) {
                    item2 = item2.func_77946_l();
                }
                MerchantRecipe recipe = new MerchantRecipe(item1, item2, sold);
                recipe.func_82783_a(0x7FFFFFF7);
                MerchantRecipeList merchantrecipelist2 = this.theIMerchant.func_70934_b((EntityPlayer)mc.field_71439_g);
                merchantrecipelist2.add((Object)recipe);
                PacketClient.sendClient(new MerchantUpdatePacket(ServerEventsHandler.Merchant.func_145782_y(), merchantrecipelist2));
            }
        }
        if (flag) {
            ((ContainerMerchantAdd)this.field_147002_h).setCurrentRecipeIndex(this.currentRecipeIndex);
            ByteBuf bytebuf = Unpooled.buffer();
            try {
                bytebuf.writeInt(this.currentRecipeIndex);
                this.field_146297_k.func_147114_u().func_147297_a((Packet)new C17PacketCustomPayload("MC|TrSel", bytebuf));
            }
            catch (Exception exception) {
            }
            finally {
                bytebuf.release();
            }
        }
    }

    protected void func_146976_a(float par1, int par2, int par3) {
        int i1;
        MerchantRecipe merchantrecipe;
        Minecraft mc = Minecraft.func_71410_x();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        mc.func_110434_K().func_110577_a(merchantGuiTextures);
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
        MerchantRecipeList merchantrecipelist = this.theIMerchant.func_70934_b((EntityPlayer)mc.field_71439_g);
        if (merchantrecipelist != null && !merchantrecipelist.isEmpty() && (merchantrecipe = (MerchantRecipe)merchantrecipelist.get(i1 = this.currentRecipeIndex)).func_82784_g()) {
            mc.func_110434_K().func_110577_a(merchantGuiTextures);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)2896);
            this.func_73729_b(this.field_147003_i + 83, this.field_147009_r + 21, 212, 0, 28, 21);
            this.func_73729_b(this.field_147003_i + 83, this.field_147009_r + 51, 212, 0, 28, 21);
        }
    }

    public void func_73863_a(int par1, int par2, float par3) {
        super.func_73863_a(par1, par2, par3);
        Minecraft mc = Minecraft.func_71410_x();
        MerchantRecipeList merchantrecipelist = this.theIMerchant.func_70934_b((EntityPlayer)mc.field_71439_g);
        if (merchantrecipelist != null && !merchantrecipelist.isEmpty()) {
            int k = (this.field_146294_l - this.field_146999_f) / 2;
            int l = (this.field_146295_m - this.field_147000_g) / 2;
            int i1 = this.currentRecipeIndex;
            MerchantRecipe merchantrecipe = (MerchantRecipe)merchantrecipelist.get(i1);
            GL11.glPushMatrix();
            ItemStack itemstack = merchantrecipe.func_77394_a();
            ItemStack itemstack1 = merchantrecipe.func_77396_b();
            ItemStack itemstack2 = merchantrecipe.func_77397_d();
            RenderHelper.func_74520_c();
            GL11.glDisable((int)2896);
            GL11.glEnable((int)32826);
            GL11.glEnable((int)2903);
            GL11.glEnable((int)2896);
            GuiMerchantAdd.field_146296_j.field_77023_b = 100.0f;
            field_146296_j.func_82406_b(this.field_146289_q, mc.func_110434_K(), itemstack, k + 36, l + 24);
            field_146296_j.func_77021_b(this.field_146289_q, mc.func_110434_K(), itemstack, k + 36, l + 24);
            if (itemstack1 != null) {
                field_146296_j.func_82406_b(this.field_146289_q, mc.func_110434_K(), itemstack1, k + 62, l + 24);
                field_146296_j.func_77021_b(this.field_146289_q, mc.func_110434_K(), itemstack1, k + 62, l + 24);
            }
            field_146296_j.func_82406_b(this.field_146289_q, mc.func_110434_K(), itemstack2, k + 120, l + 24);
            field_146296_j.func_77021_b(this.field_146289_q, mc.func_110434_K(), itemstack2, k + 120, l + 24);
            GuiMerchantAdd.field_146296_j.field_77023_b = 0.0f;
            GL11.glDisable((int)2896);
            if (this.func_146978_c(36, 24, 16, 16, par1, par2)) {
                this.func_146285_a(itemstack, par1, par2);
            } else if (itemstack1 != null && this.func_146978_c(62, 24, 16, 16, par1, par2)) {
                this.func_146285_a(itemstack1, par1, par2);
            } else if (this.func_146978_c(120, 24, 16, 16, par1, par2)) {
                this.func_146285_a(itemstack2, par1, par2);
            }
            GL11.glPopMatrix();
            GL11.glEnable((int)2896);
            GL11.glEnable((int)2929);
            RenderHelper.func_74519_b();
        }
    }

    public IMerchant getIMerchant() {
        return this.theIMerchant;
    }

    static ResourceLocation func_110417_h() {
        return merchantGuiTextures;
    }

    @SideOnly(value=Side.CLIENT)
    static class MerchantButton
    extends GuiButton {
        private final boolean field_146157_o;

        public MerchantButton(int par1, int par2, int par3, boolean par4) {
            super(par1, par2, par3, 12, 19, "");
            this.field_146157_o = par4;
        }

        public void func_146112_a(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
            if (this.field_146125_m) {
                p_146112_1_.func_110434_K().func_110577_a(merchantGuiTextures);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                boolean flag = p_146112_2_ >= this.field_146128_h && p_146112_3_ >= this.field_146129_i && p_146112_2_ < this.field_146128_h + this.field_146120_f && p_146112_3_ < this.field_146129_i + this.field_146121_g;
                int k = 0;
                int l = 176;
                if (!this.field_146124_l) {
                    l += this.field_146120_f * 2;
                } else if (flag) {
                    l += this.field_146120_f;
                }
                if (!this.field_146157_o) {
                    k += this.field_146121_g;
                }
                this.func_73729_b(this.field_146128_h, this.field_146129_i, l, k, this.field_146120_f, this.field_146121_g);
            }
        }
    }
}

