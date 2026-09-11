/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.DragonBC.common.Gui;

import JinRyuu.DragonBC.common.Render.SpacePod01TileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class DBCGui
extends GuiContainer {
    public DBCGui(InventoryPlayer inventory, SpacePod01TileEntity tile_entity) {
        super(null);
    }

    protected void func_146979_b(int i, int j) {
        this.field_146289_q.func_78276_b("Tutorial Gui", 6, 6, 0xFFFFFF);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"container.inventory"), 6, this.field_147000_g - 96, 0xFFFFFF);
    }

    protected void func_146976_a(float f, int i, int j) {
        String picture = "/terrain.png";
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ResourceLocation tx = new ResourceLocation(picture);
        this.field_146297_k.field_71446_o.func_110577_a(tx);
        int x = (this.field_146294_l - this.field_146999_f) / 2;
        int y = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(x, y, 0, 0, this.field_146999_f, this.field_147000_g);
    }
}

