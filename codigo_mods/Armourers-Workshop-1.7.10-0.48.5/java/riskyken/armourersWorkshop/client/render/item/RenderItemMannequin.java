/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render.item;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.handler.ModClientFMLEventHandler;
import riskyken.armourersWorkshop.client.helper.MannequinTextureHelper;
import riskyken.armourersWorkshop.client.model.ModelMannequin;
import riskyken.armourersWorkshop.client.texture.PlayerTexture;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;

public class RenderItemMannequin
implements IItemRenderer {
    private final ModelMannequin modelSteve;
    private final ModelMannequin modelAlex;

    public RenderItemMannequin(ModelMannequin modelSteve, ModelMannequin modelAlex) {
        this.modelSteve = modelSteve;
        this.modelAlex = modelAlex;
    }

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        GL11.glPushMatrix();
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        GL11.glTranslatef((float)0.0f, (float)-0.5f, (float)0.0f);
        float headPitch = 0.0f;
        float headTilt = 0.0f;
        float limbWobble = 0.0f;
        switch (type) {
            case EQUIPPED_FIRST_PERSON: {
                GL11.glTranslatef((float)-0.6f, (float)-0.5f, (float)0.6f);
                GL11.glRotatef((float)-60.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                headPitch = -40.0f;
                headTilt = -10.0f;
                if (data.length < 2 || !(data[1] instanceof AbstractClientPlayer & data[0] instanceof RenderBlocks)) break;
                RenderBlocks renderBlocks = (RenderBlocks)data[0];
                AbstractClientPlayer player = (AbstractClientPlayer)data[1];
                World world = player.field_70170_p;
                float partialTickTime = ModClientFMLEventHandler.renderTickTime;
                float pitchTime = (float)(world.func_82737_E() % 10L) + partialTickTime;
                float tiltTime = (float)(world.func_82737_E() % 8L) + partialTickTime;
                float limbTime = (float)(world.func_82737_E() % 6L) + partialTickTime;
                pitchTime = pitchTime / 5.0f - 1.0f;
                tiltTime = tiltTime / 4.0f - 1.0f;
                limbTime = limbTime / 3.0f - 1.0f;
                float lastDistance = player.field_70140_Q - player.field_70141_P;
                headTilt = (float)((double)headTilt + Math.sin((double)tiltTime * Math.PI) * 80.0 * (double)lastDistance);
                headPitch = (float)((double)headPitch + Math.sin((double)pitchTime * Math.PI) * 40.0 * (double)lastDistance);
                limbWobble = (float)((double)limbWobble + Math.sin((double)limbTime * Math.PI) * (double)lastDistance / 10.0);
                break;
            }
            case ENTITY: {
                GL11.glScalef((float)1.4f, (float)1.4f, (float)1.4f);
                GL11.glTranslatef((float)0.0f, (float)-0.8f, (float)0.0f);
                break;
            }
            case EQUIPPED: {
                GL11.glScalef((float)1.2f, (float)1.2f, (float)1.2f);
                GL11.glTranslatef((float)-0.6f, (float)-0.5f, (float)0.6f);
                GL11.glRotatef((float)-60.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case INVENTORY: {
                GL11.glTranslatef((float)0.0f, (float)0.1f, (float)0.0f);
                GL11.glScalef((float)0.9f, (float)0.9f, (float)0.9f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
        }
        PlayerTexture playerTexture = MannequinTextureHelper.getMannequinTexture(item);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(playerTexture.getResourceLocation());
        if (item.func_77973_b() == Item.func_150898_a((Block)ModBlocks.doll)) {
            float dollScale = 0.5f;
            GL11.glScalef((float)dollScale, (float)dollScale, (float)dollScale);
        }
        float scale = 0.0625f;
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        if (playerTexture.isSlimModel()) {
            this.modelAlex.render(null, 0.0f, limbWobble, 0.0f, headPitch, headTilt, scale, true);
        } else {
            this.modelSteve.render(null, 0.0f, limbWobble, 0.0f, headPitch, headTilt, scale, true);
        }
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }
}

