/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.particles.components;

import software.bernie.geckolib3.particles.components.IComponentBase;
import software.bernie.geckolib3.particles.emitter.BedrockEmitter;
import software.bernie.geckolib3.particles.emitter.BedrockParticle;

public interface IComponentParticleUpdate
extends IComponentBase {
    public void update(BedrockEmitter var1, BedrockParticle var2);
}

