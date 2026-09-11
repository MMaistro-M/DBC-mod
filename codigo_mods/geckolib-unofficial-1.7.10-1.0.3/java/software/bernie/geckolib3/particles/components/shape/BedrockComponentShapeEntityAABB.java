/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.particles.components.shape;

import software.bernie.geckolib3.particles.components.shape.BedrockComponentShapeBase;
import software.bernie.geckolib3.particles.emitter.BedrockEmitter;
import software.bernie.geckolib3.particles.emitter.BedrockParticle;

public class BedrockComponentShapeEntityAABB
extends BedrockComponentShapeBase {
    @Override
    public void apply(BedrockEmitter emitter, BedrockParticle particle) {
        float centerX = (float)this.offset[0].get();
        float centerY = (float)this.offset[1].get();
        float centerZ = (float)this.offset[2].get();
        float w = 0.0f;
        float h = 0.0f;
        float d = 0.0f;
        if (emitter.target != null) {
            w = emitter.target.field_70130_N;
            h = emitter.target.field_70131_O;
            d = emitter.target.field_70130_N;
        }
        particle.position.x = centerX + ((float)Math.random() - 0.5f) * w;
        particle.position.y = centerY + ((float)Math.random() - 0.5f) * h;
        particle.position.z = centerZ + ((float)Math.random() - 0.5f) * d;
        if (this.surface) {
            int roll = (int)(Math.random() * 6.0 * 100.0) % 6;
            if (roll == 0) {
                particle.position.x = centerX + w / 2.0f;
            } else if (roll == 1) {
                particle.position.x = centerX - w / 2.0f;
            } else if (roll == 2) {
                particle.position.y = centerY + h / 2.0f;
            } else if (roll == 3) {
                particle.position.y = centerY - h / 2.0f;
            } else if (roll == 4) {
                particle.position.z = centerZ + d / 2.0f;
            } else if (roll == 5) {
                particle.position.z = centerZ - d / 2.0f;
            }
        }
        this.direction.applyDirection(particle, centerX, centerY, centerZ);
    }
}

