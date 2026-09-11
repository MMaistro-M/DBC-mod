/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.particle.EffectRenderer
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package riskyken.armourersWorkshop.client.particles;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import riskyken.armourersWorkshop.client.render.IRenderBuffer;
import riskyken.armourersWorkshop.client.render.RenderBridge;
import riskyken.armourersWorkshop.utils.UtilRender;

@SideOnly(value=Side.CLIENT)
public class EntityFXPaintSplash
extends EntityFX {
    private static final ResourceLocation paintSplashTextures = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/particles/paintSplash.png");
    private static final ResourceLocation particleTextures = (ResourceLocation)ReflectionHelper.getPrivateValue(EffectRenderer.class, null, (String[])new String[]{"particleTextures", "field_110737_b", "b"});

    public EntityFXPaintSplash(World world, double x, double y, double z, int colour, ForgeDirection dir) {
        super(world, x + (double)dir.offsetX * 0.5, y + (double)dir.offsetY * 0.5, z + (double)dir.offsetZ * 0.5);
        this.field_70544_f = 0.2f + world.field_73012_v.nextFloat() * 0.4f;
        this.field_70547_e = 10;
        Color c = new Color(colour);
        this.field_70552_h = (float)c.getRed() / 255.0f;
        this.field_70553_i = (float)c.getGreen() / 255.0f;
        this.field_70551_j = (float)c.getBlue() / 255.0f;
        float xPos = world.field_73012_v.nextFloat() - 0.5f;
        float yPos = world.field_73012_v.nextFloat() - 0.5f;
        switch (dir) {
            case UP: {
                this.func_70107_b(this.field_70165_t + (double)xPos, this.field_70163_u, this.field_70161_v + (double)yPos);
                break;
            }
            case DOWN: {
                this.func_70107_b(this.field_70165_t + (double)xPos, this.field_70163_u, this.field_70161_v + (double)yPos);
                break;
            }
            case NORTH: {
                this.func_70107_b(this.field_70165_t + (double)xPos, this.field_70163_u + (double)yPos, this.field_70161_v);
                break;
            }
            case SOUTH: {
                this.func_70107_b(this.field_70165_t + (double)xPos, this.field_70163_u + (double)yPos, this.field_70161_v);
                break;
            }
            case EAST: {
                this.func_70107_b(this.field_70165_t, this.field_70163_u + (double)yPos, this.field_70161_v + (double)xPos);
                break;
            }
            case WEST: {
                this.func_70107_b(this.field_70165_t, this.field_70163_u + (double)yPos, this.field_70161_v + (double)xPos);
                break;
            }
        }
        this.field_70159_w = (double)dir.offsetX * 0.08;
        this.field_70181_x = (double)dir.offsetY * 0.08;
        this.field_70179_y = (double)dir.offsetZ * 0.08;
        this.field_70159_w += (double)(world.field_73012_v.nextFloat() - 0.5f) * 0.06;
        this.field_70181_x += (double)(world.field_73012_v.nextFloat() - 0.5f) * 0.06;
        this.field_70179_y += (double)(world.field_73012_v.nextFloat() - 0.5f) * 0.06;
        this.field_94054_b = Math.round(world.field_73012_v.nextFloat()) * 8;
        this.field_94055_c = Math.round(world.field_73012_v.nextFloat()) * 8;
        this.field_70145_X = false;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.field_70181_x -= 0.02;
        this.field_82339_as = 1.0f + -((float)this.field_70546_d / (float)this.field_70547_e);
    }

    public void func_70539_a(Tessellator tessellator, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_) {
        IRenderBuffer renderBuffer = RenderBridge.INSTANCE;
        renderBuffer.draw();
        UtilRender.bindTexture(paintSplashTextures);
        renderBuffer.startDrawingQuads();
        renderBuffer.setBrightness(this.func_70070_b(0.0f));
        float f6 = (float)(this.field_94054_b / 8) * 0.5f;
        float f7 = f6 + 0.5f;
        float f8 = (float)(this.field_94055_c / 8) * 0.5f;
        float f9 = f8 + 0.5f;
        float f10 = 0.1f * this.field_70544_f;
        if (this.field_70550_a != null) {
            f6 = this.field_70550_a.func_94209_e();
            f7 = this.field_70550_a.func_94212_f();
            f8 = this.field_70550_a.func_94206_g();
            f9 = this.field_70550_a.func_94210_h();
        }
        float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)p_70539_2_ - field_70556_an);
        float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)p_70539_2_ - field_70554_ao);
        float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)p_70539_2_ - field_70555_ap);
        renderBuffer.setColourRGBA_F(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as);
        renderBuffer.addVertexWithUV(f11 - p_70539_3_ * f10 - p_70539_6_ * f10, f12 - p_70539_4_ * f10, f13 - p_70539_5_ * f10 - p_70539_7_ * f10, f7, f9);
        renderBuffer.addVertexWithUV(f11 - p_70539_3_ * f10 + p_70539_6_ * f10, f12 + p_70539_4_ * f10, f13 - p_70539_5_ * f10 + p_70539_7_ * f10, f7, f8);
        renderBuffer.addVertexWithUV(f11 + p_70539_3_ * f10 + p_70539_6_ * f10, f12 + p_70539_4_ * f10, f13 + p_70539_5_ * f10 + p_70539_7_ * f10, f6, f8);
        renderBuffer.addVertexWithUV(f11 + p_70539_3_ * f10 - p_70539_6_ * f10, f12 - p_70539_4_ * f10, f13 + p_70539_5_ * f10 - p_70539_7_ * f10, f6, f9);
        renderBuffer.draw();
        UtilRender.bindTexture(particleTextures);
        renderBuffer.startDrawingQuads();
    }
}

