/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.minecraft.client.renderer.Tessellator
 */
package software.bernie.geckolib3.particles.components.rate;

import com.eliotlash.mclib.math.Constant;
import com.eliotlash.molang.MolangException;
import com.eliotlash.molang.MolangParser;
import com.eliotlash.molang.expressions.MolangExpression;
import com.eliotlash.molang.expressions.MolangValue;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.renderer.Tessellator;
import software.bernie.geckolib3.particles.components.BedrockComponentBase;
import software.bernie.geckolib3.particles.components.IComponentParticleMorphRender;
import software.bernie.geckolib3.particles.components.IComponentParticleRender;
import software.bernie.geckolib3.particles.components.rate.BedrockComponentRate;
import software.bernie.geckolib3.particles.emitter.BedrockEmitter;
import software.bernie.geckolib3.particles.emitter.BedrockParticle;

public class BedrockComponentRateSteady
extends BedrockComponentRate
implements IComponentParticleRender,
IComponentParticleMorphRender {
    public static final MolangExpression DEFAULT_PARTICLES = new MolangValue(null, new Constant(50.0));
    public MolangExpression spawnRate = MolangParser.ONE;

    public BedrockComponentRateSteady() {
        this.particles = DEFAULT_PARTICLES;
    }

    @Override
    public BedrockComponentBase fromJson(JsonElement elem, MolangParser parser) throws MolangException {
        if (!elem.isJsonObject()) {
            return super.fromJson(elem, parser);
        }
        JsonObject element = elem.getAsJsonObject();
        if (element.has("spawn_rate")) {
            this.spawnRate = parser.parseJson(element.get("spawn_rate"));
        }
        if (element.has("max_particles")) {
            this.particles = parser.parseJson(element.get("max_particles"));
        }
        return super.fromJson((JsonElement)element, parser);
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        if (!MolangExpression.isOne(this.spawnRate)) {
            object.add("spawn_rate", this.spawnRate.toJson());
        }
        if (!MolangExpression.isConstant(this.particles, 50.0)) {
            object.add("max_particles", this.particles.toJson());
        }
        return object;
    }

    @Override
    public void preRender(BedrockEmitter emitter, float partialTicks) {
    }

    @Override
    public void render(BedrockEmitter emitter, BedrockParticle particle, Tessellator builder, float partialTicks) {
    }

    @Override
    public void renderOnScreen(BedrockParticle particle, int x, int y, float scale, float partialTicks) {
    }

    @Override
    public void postRender(BedrockEmitter emitter, float partialTicks) {
        double particles;
        double diff;
        double spawn;
        if (emitter.playing && (spawn = (double)Math.round(diff = (particles = emitter.getAge(partialTicks) * this.spawnRate.get()) - emitter.spawnedParticles)) > 0.0) {
            emitter.setEmitterVariables(partialTicks);
            double track = spawn;
            int i = 0;
            while ((double)i < spawn) {
                if ((double)emitter.particles.size() < this.particles.get()) {
                    emitter.spawnParticle();
                } else {
                    track -= 1.0;
                }
                ++i;
            }
            emitter.spawnedParticles += track;
        }
    }

    @Override
    public int getSortingIndex() {
        return 10;
    }
}

