/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.EntityPortalFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.fx;

import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.data.ModelPartData;
import org.lwjgl.opengl.GL11;

public class EntityEnderFX
extends EntityPortalFX {
    private final float portalParticleScale;
    private final int particleNumber;
    private final EntityCustomNpc npc;
    private static final ResourceLocation resource = new ResourceLocation("textures/particle/particles.png");
    private final ResourceLocation location;
    private boolean move = true;
    private float startX = 0.0f;
    private float startY = 0.0f;
    private float startZ = 0.0f;

    public EntityEnderFX(EntityCustomNpc npc, double par2, double par4, double par6, double par8, double par10, double par12, ModelPartData data) {
        super(npc.field_70170_p, par2, par4, par6, par8, par10, par12);
        this.npc = npc;
        this.particleNumber = npc.func_70681_au().nextInt(2);
        this.portalParticleScale = this.field_70544_f = this.field_70146_Z.nextFloat() * 0.2f + 0.5f;
        this.field_70552_h = (float)(data.color >> 16 & 0xFF) / 255.0f;
        this.field_70553_i = (float)(data.color >> 8 & 0xFF) / 255.0f;
        this.field_70551_j = (float)(data.color & 0xFF) / 255.0f;
        if (npc.func_70681_au().nextInt(3) == 1) {
            this.move = false;
            this.startX = (float)npc.field_70165_t;
            this.startY = (float)npc.field_70163_u;
            this.startZ = (float)npc.field_70161_v;
        }
        this.location = data.playerTexture ? npc.textureLocation : new ResourceLocation(data.texture);
    }

    public void func_70539_a(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
        if (this.move) {
            this.startX = (float)(this.npc.field_70169_q + (this.npc.field_70165_t - this.npc.field_70169_q) * (double)par2);
            this.startY = (float)(this.npc.field_70167_r + (this.npc.field_70163_u - this.npc.field_70167_r) * (double)par2);
            this.startZ = (float)(this.npc.field_70166_s + (this.npc.field_70161_v - this.npc.field_70166_s) * (double)par2);
        }
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78381_a();
        float scale = ((float)this.field_70546_d + par2) / (float)this.field_70547_e;
        scale = 1.0f - scale;
        scale *= scale;
        scale = 1.0f - scale;
        this.field_70544_f = this.portalParticleScale * scale;
        ClientProxy.bindTexture(this.location);
        float f = 0.875f;
        float f1 = f + 0.125f;
        float f2 = 0.75f - (float)this.particleNumber * 0.25f;
        float f3 = f2 + 0.25f;
        float f4 = 0.1f * this.field_70544_f;
        float f5 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)par2 - field_70556_an + (double)this.startX);
        float f6 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)par2 - field_70554_ao + (double)this.startY);
        float f7 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)par2 - field_70555_ap + (double)this.startZ);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        tessellator.func_78382_b();
        tessellator.func_78380_c(240);
        par1Tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        par1Tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 1.0f);
        par1Tessellator.func_78374_a((double)(f5 - par3 * f4 - par6 * f4), (double)(f6 - par4 * f4), (double)(f7 - par5 * f4 - par7 * f4), (double)f1, (double)f3);
        par1Tessellator.func_78374_a((double)(f5 - par3 * f4 + par6 * f4), (double)(f6 + par4 * f4), (double)(f7 - par5 * f4 + par7 * f4), (double)f1, (double)f2);
        par1Tessellator.func_78374_a((double)(f5 + par3 * f4 + par6 * f4), (double)(f6 + par4 * f4), (double)(f7 + par5 * f4 + par7 * f4), (double)f, (double)f2);
        par1Tessellator.func_78374_a((double)(f5 + par3 * f4 - par6 * f4), (double)(f6 - par4 * f4), (double)(f7 + par5 * f4 - par7 * f4), (double)f, (double)f3);
        tessellator.func_78381_a();
        ClientProxy.bindTexture(resource);
        tessellator.func_78382_b();
    }

    public int func_70537_b() {
        return 0;
    }
}

