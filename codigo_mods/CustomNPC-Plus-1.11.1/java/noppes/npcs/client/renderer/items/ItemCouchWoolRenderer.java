/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer.items;

import kamkeel.npcs.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import noppes.npcs.client.renderer.blocks.BlockCouchWoolRenderer;
import noppes.npcs.client.renderer.blocks.BlockRendererInterface;
import noppes.npcs.client.renderer.blocks.BlockTallLampRenderer;
import noppes.npcs.config.ConfigClient;
import org.lwjgl.opengl.GL11;

public class ItemCouchWoolRenderer
implements IItemRenderer {
    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        int meta = item.func_77960_j();
        int colorValue = ColorUtil.colorTableInts[15 - meta];
        if (item.func_77942_o() && item.func_77978_p().func_74764_b("BrushColor")) {
            colorValue = item.func_77978_p().func_74762_e("BrushColor");
        }
        float[] color = ColorUtil.hexToRGB(colorValue);
        Minecraft mc = Minecraft.func_71410_x();
        GL11.glPushAttrib((int)1048575);
        GL11.glEnable((int)3008);
        GL11.glPushMatrix();
        if (type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        }
        GL11.glTranslatef((float)0.0f, (float)0.9f, (float)0.1f);
        GL11.glScalef((float)0.9f, (float)0.9f, (float)0.9f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        if (ConfigClient.LegacyCouch) {
            this.setWoodTexture(meta);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            BlockCouchWoolRenderer.modelLegacyCouchMiddle.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            mc.func_110434_K().func_110577_a(BlockTallLampRenderer.resourceTop);
            GL11.glColor3f((float)color[0], (float)color[1], (float)color[2]);
            BlockCouchWoolRenderer.modelLegacyCouchMiddleWool.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else {
            this.setCouchWood(meta);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            BlockCouchWoolRenderer.modelCouch.CouchBack.func_78785_a(0.0625f);
            mc.func_110434_K().func_110577_a(BlockCouchWoolRenderer.wool);
            GL11.glColor3f((float)color[0], (float)color[1], (float)color[2]);
            BlockCouchWoolRenderer.modelCouch.Cussion.func_78785_a(0.0625f);
        }
        GL11.glDisable((int)3008);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    private void setCouchWood(int meta) {
        TextureManager manager = Minecraft.func_71410_x().func_110434_K();
        if (meta == 1) {
            manager.func_110577_a(BlockCouchWoolRenderer.spruce);
        } else if (meta == 2) {
            manager.func_110577_a(BlockCouchWoolRenderer.birch);
        } else if (meta == 3) {
            manager.func_110577_a(BlockCouchWoolRenderer.jungle);
        } else if (meta == 4) {
            manager.func_110577_a(BlockCouchWoolRenderer.acacia);
        } else if (meta == 5) {
            manager.func_110577_a(BlockCouchWoolRenderer.dark_oak);
        } else {
            manager.func_110577_a(BlockCouchWoolRenderer.oak);
        }
    }

    private void setWoodTexture(int meta) {
        BlockRendererInterface.setWoodTexture(meta);
    }
}

