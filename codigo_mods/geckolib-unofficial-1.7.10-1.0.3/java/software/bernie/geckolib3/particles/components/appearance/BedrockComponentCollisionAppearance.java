/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  javax.vecmath.Tuple4f
 *  javax.vecmath.Vector3d
 *  javax.vecmath.Vector3f
 *  javax.vecmath.Vector4f
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.ResourceLocation
 */
package software.bernie.geckolib3.particles.components.appearance;

import com.eliotlash.mclib.utils.Interpolations;
import com.eliotlash.mclib.utils.resources.RLUtils;
import com.eliotlash.molang.MolangException;
import com.eliotlash.molang.MolangParser;
import com.eliotlash.molang.expressions.MolangExpression;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Map;
import java.util.Set;
import javax.vecmath.Tuple4f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.particles.BedrockMaterial;
import software.bernie.geckolib3.particles.BedrockScheme;
import software.bernie.geckolib3.particles.components.BedrockComponentBase;
import software.bernie.geckolib3.particles.components.IComponentParticleRender;
import software.bernie.geckolib3.particles.components.appearance.BedrockComponentAppearanceBillboard;
import software.bernie.geckolib3.particles.emitter.BedrockEmitter;
import software.bernie.geckolib3.particles.emitter.BedrockParticle;

public class BedrockComponentCollisionAppearance
extends BedrockComponentAppearanceBillboard
implements IComponentParticleRender {
    public BedrockMaterial material = BedrockMaterial.OPAQUE;
    public ResourceLocation texture = BedrockScheme.DEFAULT_TEXTURE;
    public MolangExpression enabled = MolangParser.ZERO;
    public boolean lit;

    @Override
    public BedrockComponentBase fromJson(JsonElement elem, MolangParser parser) throws MolangException {
        String texture;
        if (!elem.isJsonObject()) {
            return super.fromJson(elem, parser);
        }
        JsonObject element = elem.getAsJsonObject();
        if (element.has("enabled")) {
            this.enabled = parser.parseJson(element.get("enabled"));
        }
        if (element.has("lit")) {
            this.lit = element.get("lit").getAsBoolean();
        }
        if (element.has("material")) {
            this.material = BedrockMaterial.fromString(element.get("material").getAsString());
        }
        if (element.has("texture") && !(texture = element.get("texture").getAsString()).equals("textures/particle/particles")) {
            this.texture = RLUtils.create(texture);
        }
        return super.fromJson((JsonElement)element, parser);
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        object.add("enabled", this.enabled.toJson());
        object.addProperty("lit", Boolean.valueOf(this.lit));
        object.addProperty("material", this.material.id);
        if (this.texture != null && !this.texture.equals((Object)BedrockScheme.DEFAULT_TEXTURE)) {
            object.addProperty("texture", this.texture.toString());
        }
        JsonObject superJson = (JsonObject)super.toJson();
        Set entries = superJson.entrySet();
        for (Map.Entry entry : entries) {
            object.add((String)entry.getKey(), (JsonElement)entry.getValue());
        }
        return object;
    }

    @Override
    public void preRender(BedrockEmitter emitter, float partialTicks) {
    }

    @Override
    public void render(BedrockEmitter emitter, BedrockParticle particle, Tessellator builder, float partialTicks) {
        boolean tmpLit = false;
        if (!particle.isCollisionTexture(emitter)) {
            if (particle.isCollisionTinting(emitter)) {
                tmpLit = emitter.lit;
                emitter.lit = this.lit;
                emitter.scheme.get(BedrockComponentAppearanceBillboard.class).render(emitter, particle, builder, partialTicks);
                emitter.lit = tmpLit;
            }
            return;
        }
        if (!particle.isCollisionTinting(emitter)) {
            tmpLit = this.lit;
            this.lit = emitter.lit;
        }
        this.calculateUVs(particle, partialTicks);
        double px = Interpolations.lerp(particle.prevPosition.x, particle.position.x, (double)partialTicks);
        double py = Interpolations.lerp(particle.prevPosition.y, particle.position.y, (double)partialTicks);
        double pz = Interpolations.lerp(particle.prevPosition.z, particle.position.z, (double)partialTicks);
        float angle = Interpolations.lerp(particle.prevRotation, particle.rotation, partialTicks);
        Vector3d pos = this.calculatePosition(emitter, particle, px, py, pz);
        px = pos.x;
        py = pos.y;
        pz = pos.z;
        int light = this.lit ? 0xF000F0 : emitter.getBrightnessForRender(partialTicks, px, py, pz);
        int lightX = light >> 16 & 0xFFFF;
        int lightY = light & 0xFFFF;
        this.calculateFacing(emitter, particle, px, py, pz);
        this.rotation.rotZ(angle / 180.0f * (float)Math.PI);
        this.transform.mul(this.rotation);
        this.transform.setTranslation(new Vector3f((float)px, (float)py, (float)pz));
        for (Vector4f vertex : this.vertices) {
            this.transform.transform((Tuple4f)vertex);
        }
        float u1 = this.u1 / (float)this.textureWidth;
        float u2 = this.u2 / (float)this.textureWidth;
        float v1 = this.v1 / (float)this.textureHeight;
        float v2 = this.v2 / (float)this.textureHeight;
        Tessellator t = Tessellator.field_78398_a;
        t.func_78385_a((double)u1, (double)v1);
        t.func_78380_c(light);
        t.func_78369_a(particle.r, particle.g, particle.b, particle.a);
        t.func_78377_a((double)this.vertices[0].x, (double)this.vertices[0].y, (double)this.vertices[0].z);
        t.func_78385_a((double)u2, (double)v1);
        t.func_78380_c(light);
        t.func_78369_a(particle.r, particle.g, particle.b, particle.a);
        t.func_78377_a((double)this.vertices[1].x, (double)this.vertices[1].y, (double)this.vertices[1].z);
        t.func_78385_a((double)u2, (double)v2);
        t.func_78380_c(light);
        t.func_78369_a(particle.r, particle.g, particle.b, particle.a);
        t.func_78377_a((double)this.vertices[2].x, (double)this.vertices[2].y, (double)this.vertices[2].z);
        t.func_78385_a((double)u1, (double)v2);
        t.func_78380_c(light);
        t.func_78369_a(particle.r, particle.g, particle.b, particle.a);
        t.func_78377_a((double)this.vertices[3].x, (double)this.vertices[3].y, (double)this.vertices[3].z);
        if (!particle.isCollisionTinting(emitter)) {
            this.lit = tmpLit;
        }
    }

    @Override
    public void renderOnScreen(BedrockParticle particle, int x, int y, float scale, float partialTicks) {
    }

    @Override
    public void calculateUVs(BedrockParticle particle, float partialTicks) {
        this.w = (float)this.sizeW.get() * 2.25f;
        this.h = (float)this.sizeH.get() * 2.25f;
        float u = (float)this.uvX.get();
        float v = (float)this.uvY.get();
        float w = (float)this.uvW.get();
        float h = (float)this.uvH.get();
        if (this.flipbook) {
            int index = (int)(particle.getAge(partialTicks) * (double)this.fps);
            int max = (int)this.maxFrame.get();
            if (this.stretchFPS) {
                float lifetime;
                float f = lifetime = particle.lifetime <= 0 ? 0.0f : ((float)particle.age + partialTicks) / (float)(particle.lifetime - particle.firstIntersection);
                if (particle.getExpireAge() != -1) {
                    lifetime = particle.lifetime <= 0 ? 0.0f : ((float)particle.age + partialTicks) / (float)particle.getExpirationDelay();
                }
                index = (int)(lifetime * (float)max);
            }
            if (this.loop && max != 0) {
                index %= max;
            }
            if (index > max) {
                index = max;
            }
            u += this.stepX * (float)index;
            v += this.stepY * (float)index;
        }
        this.u1 = u;
        this.v1 = v;
        this.u2 = u + w;
        this.v2 = v + h;
    }

    @Override
    public void postRender(BedrockEmitter emitter, float partialTicks) {
    }

    @Override
    public int getSortingIndex() {
        return 200;
    }
}

