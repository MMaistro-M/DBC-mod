/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.Tessellator
 */
package software.bernie.geckolib3.particles.components;

import net.minecraft.client.renderer.Tessellator;
import software.bernie.geckolib3.particles.components.IComponentBase;
import software.bernie.geckolib3.particles.emitter.BedrockEmitter;
import software.bernie.geckolib3.particles.emitter.BedrockParticle;

public interface IComponentRenderBase
extends IComponentBase {
    public void render(BedrockEmitter var1, BedrockParticle var2, Tessellator var3, float var4);

    public void renderOnScreen(BedrockParticle var1, int var2, int var3, float var4, float var5);

    public void preRender(BedrockEmitter var1, float var2);

    public void postRender(BedrockEmitter var1, float var2);
}

