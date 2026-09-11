/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.DragonBC.common.Gui;

import JinRyuu.DragonBC.common.Gui.DBCGuiButtons01;
import JinRyuu.DragonBC.common.Render.ArtGravDevContainer;
import JinRyuu.DragonBC.common.Render.ArtGravDevTileEntity;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.p.DBC.DBCPwish;
import JinRyuu.JRMCore.p.PD;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class DBCGuiArtGravDev
extends GuiContainer {
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation(JRMCoreH.tjdbcAssts, "gui/ArtGravDev.png");
    private ArtGravDevTileEntity tileFurnace;
    private String name = "";
    private int text = 0;
    protected GuiTextField inputField;
    private String defaultInputFieldText = "";
    private boolean updateTimerStopper = false;

    public DBCGuiArtGravDev(InventoryPlayer p_i1091_1_, ArtGravDevTileEntity p_i1091_2_) {
        super((Container)new ArtGravDevContainer(p_i1091_1_, p_i1091_2_));
        this.tileFurnace = p_i1091_2_;
    }

    public void func_146284_a(GuiButton button) {
        if ((this.name.matches("[0-9].+") || this.name.matches("[0-9]+")) && this.name.length() > 0) {
            boolean success = true;
            try {
                this.name = (Float.parseFloat(this.name) > 1000.0f ? 1000.0f : (Float.parseFloat(this.name) < 1.0f ? 1.0f : Float.parseFloat(this.name))) + "";
            }
            catch (Exception e) {
                success = false;
            }
            if (!success) {
                this.name = "1";
            }
            if (button.field_146127_k == 210) {
                PD.sendToServer(new DBCPwish(4, this.tileFurnace.field_145851_c + ";" + this.tileFurnace.field_145848_d + ";" + this.tileFurnace.field_145849_e + ";" + this.name));
                this.field_146297_k.field_71439_g.func_71053_j();
            }
        }
    }

    private void name(FontRenderer var8, int i, int j) {
        this.inputField = new GuiTextField(var8, i + 100, j + 15 + 0, 40, 12);
        this.inputField.func_146203_f(5);
        this.inputField.func_146185_a(true);
        this.inputField.func_146195_b(true);
        this.inputField.func_146180_a(this.defaultInputFieldText);
        this.inputField.func_146205_d(true);
    }

    public void func_73876_c() {
        if (this.inputField != null) {
            this.inputField.func_146178_a();
        }
    }

    protected void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.inputField != null) {
            this.inputField.func_146201_a(c, i);
        }
    }

    protected void func_73864_a(int i, int j, int k) {
        super.func_73864_a(i, j, k);
        if (this.inputField != null) {
            this.inputField.func_146192_a(i, j, k);
        }
    }

    /*
     * Unable to fully structure code
     */
    protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
        block3: {
            block2: {
                s = this.tileFurnace.func_145818_k_() != false ? this.tileFurnace.func_145825_b() : I18n.func_135052_a((String)this.tileFurnace.func_145825_b(), (Object[])new Object[0]);
                this.field_146289_q.func_78276_b(s, this.field_146999_f / 2 - this.field_146289_q.func_78256_a(s) / 2, 6, 0x404040);
                this.field_146289_q.func_78276_b(I18n.func_135052_a((String)"container.inventory", (Object[])new Object[0]), 8, this.field_147000_g - 96 + 2, 0x404040);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                guiLeft = (this.field_146294_l - this.field_146999_f) / 2;
                guiTop = (this.field_146295_m - this.field_147000_g) / 2;
                line = 1;
                ++line;
                v0 = new StringBuilder().append(JRMCoreH.trl("dbc", "Gravity")).append(": ");
                if (!this.tileFurnace.isBurning() || !(this.tileFurnace.getGravity() > 1.0f)) break block2;
                v1 = Float.valueOf(this.tileFurnace.getGravity());
                break block3;
            }
            v2 = new StringBuilder().append("1");
            if (this.tileFurnace.isBurning()) ** GOTO lbl-1000
            if (this.tileFurnace.func_70301_a(0) != null) lbl-1000:
            // 2 sources

            {
                v3 = "";
            } else {
                v3 = " (" + JRMCoreH.trl("dbc", "OutOfFuel") + ")";
            }
            v1 = v2.append(v3).toString();
        }
        n = v0.append(v1).toString();
        nw = this.field_146289_q.func_78256_a(n) + 8;
        this.field_146289_q.func_78276_b(n, 25, 10 + line * 11, 0);
        ++line;
    }

    protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(furnaceGuiTextures);
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
        int guiLeft = (this.field_146294_l - this.field_146999_f) / 2;
        int guiTop = (this.field_146295_m - this.field_147000_g) / 2;
        if (this.tileFurnace.isBurning()) {
            int i1 = this.tileFurnace.getBurnTimeRemainingScaled(13);
            this.func_73729_b(k + 5, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
        }
        int line = 1;
        String n = JRMCoreH.trl("dbc", "SetGravity") + ":";
        int nw = this.field_146289_q.func_78256_a(n) + 8;
        ++this.text;
        if (this.text == 1) {
            this.name(this.field_146289_q, guiLeft + 25 - 100 + nw, guiTop + 10 - 18 + line * 11);
            this.inputField.func_146180_a("1");
        } else {
            this.text = 2;
        }
        if (this.inputField != null) {
            this.inputField.func_146194_f();
            this.name = this.inputField.func_146179_b();
            this.field_146289_q.func_78276_b(n, guiLeft + 25, guiTop + 10 + line * 11, 0);
        }
        ++line;
    }

    public void func_73863_a(int x, int y, float f) {
        ScaledResolution var5 = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
        int var6 = var5.func_78326_a();
        int var7 = var5.func_78328_b();
        FontRenderer var8 = this.field_146297_k.field_71466_p;
        this.field_146292_n.clear();
        int posX = this.field_146294_l / 2;
        int posY = this.field_146295_m / 2;
        int line = 1;
        String n = JRMCoreH.trl("jrmc", "Update");
        int nw = this.field_146289_q.func_78256_a(n) + 8;
        this.field_146292_n.add(new DBCGuiButtons01(210, this.field_147003_i + 165 - nw, this.field_147009_r + 35 + line * 11, nw, 20, n));
        ++line;
        super.func_73863_a(x, y, f);
    }
}

