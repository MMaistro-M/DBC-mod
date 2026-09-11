/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Matrix3f
 *  javax.vecmath.Tuple3d
 *  javax.vecmath.Tuple3f
 *  javax.vecmath.Vector3d
 *  javax.vecmath.Vector3f
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package software.bernie.geckolib3.particles.emitter;

import com.eliotlash.mclib.math.IValue;
import com.eliotlash.mclib.math.Variable;
import com.eliotlash.mclib.utils.Interpolations;
import com.eliotlash.mclib.utils.resources.GifTexture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.vecmath.Matrix3f;
import javax.vecmath.Tuple3d;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import net.geckominecraft.client.renderer.GlStateManager;
import net.geckominecraft.util.math.BlockPos;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import software.bernie.geckolib3.particles.BedrockScheme;
import software.bernie.geckolib3.particles.components.BedrockComponentBase;
import software.bernie.geckolib3.particles.components.GuiModelRenderer;
import software.bernie.geckolib3.particles.components.IComponentEmitterInitialize;
import software.bernie.geckolib3.particles.components.IComponentEmitterUpdate;
import software.bernie.geckolib3.particles.components.IComponentParticleInitialize;
import software.bernie.geckolib3.particles.components.IComponentParticleMorphRender;
import software.bernie.geckolib3.particles.components.IComponentParticleRender;
import software.bernie.geckolib3.particles.components.IComponentParticleUpdate;
import software.bernie.geckolib3.particles.components.IComponentRenderBase;
import software.bernie.geckolib3.particles.components.appearance.BedrockComponentAppearanceBillboard;
import software.bernie.geckolib3.particles.components.appearance.BedrockComponentCollisionAppearance;
import software.bernie.geckolib3.particles.components.appearance.BedrockComponentParticleMorph;
import software.bernie.geckolib3.particles.components.lifetime.BedrockComponentLifetimeLooping;
import software.bernie.geckolib3.particles.components.meta.BedrockComponentInitialization;
import software.bernie.geckolib3.particles.components.motion.BedrockComponentMotionCollision;
import software.bernie.geckolib3.particles.components.rate.BedrockComponentRateSteady;
import software.bernie.geckolib3.particles.emitter.BedrockParticle;

public class BedrockEmitter {
    public BedrockScheme scheme;
    public String locator;
    public int disableAfter = -1;
    public double lastTick = 0.0;
    public List<BedrockParticle> particles = new ArrayList<BedrockParticle>();
    public List<BedrockParticle> splitParticles = new ArrayList<BedrockParticle>();
    public Map<String, IValue> variables;
    public Map<String, Double> initialValues = new HashMap<String, Double>();
    public EntityLivingBase target;
    public World world;
    public boolean lit;
    public boolean added;
    public int sanityTicks;
    public boolean running = true;
    private BedrockParticle guiParticle;
    public Vector3d lastGlobal = new Vector3d();
    public Vector3d prevGlobal = new Vector3d();
    public Matrix3f rotation = new Matrix3f(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    public Matrix3f prevRotation = new Matrix3f(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    public Vector3f angularVelocity = new Vector3f();
    public Vector3d translation = new Vector3d();
    public int age;
    public int lifetime;
    public double spawnedParticles;
    public boolean playing = true;
    public float random1 = (float)Math.random();
    public float random2 = (float)Math.random();
    public float random3 = (float)Math.random();
    public float random4 = (float)Math.random();
    private BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
    public double[] scale = new double[]{1.0, 1.0, 1.0};
    public boolean lastLoop = false;
    public int perspective;
    public float cYaw;
    public float cPitch;
    public double cX;
    public double cY;
    public double cZ;
    private Variable varAge;
    private Variable varLifetime;
    private Variable varRandom1;
    private Variable varRandom2;
    private Variable varRandom3;
    private Variable varRandom4;
    private Variable varSpeedABS;
    private Variable varSpeedX;
    private Variable varSpeedY;
    private Variable varSpeedZ;
    private Variable varBounces;
    private Variable varEmitterAge;
    private Variable varEmitterLifetime;
    private Variable varEmitterRandom1;
    private Variable varEmitterRandom2;
    private Variable varEmitterRandom3;
    private Variable varEmitterRandom4;

    public boolean isFinished() {
        return !this.running && this.particles.isEmpty();
    }

    public boolean isLooping() {
        for (BedrockComponentBase componentBase : this.scheme.components) {
            if (!(componentBase instanceof BedrockComponentLifetimeLooping)) continue;
            return true;
        }
        return false;
    }

    public void setLastLoop() {
        this.lastLoop = true;
    }

    public double getDistanceSq() {
        this.setupCameraProperties(0.0f);
        double dx = this.cX - this.lastGlobal.x;
        double dy = this.cY - this.lastGlobal.y;
        double dz = this.cZ - this.lastGlobal.z;
        return dx * dx + dy * dy + dz * dz;
    }

    public double getAge() {
        return this.getAge(0.0f);
    }

    public double getAge(float partialTicks) {
        return (double)((float)this.age + partialTicks) / 20.0;
    }

    public boolean isMorphParticle() {
        BedrockComponentParticleMorph morphComponent = this.scheme.getOrCreate(BedrockComponentParticleMorph.class);
        return morphComponent.enabled;
    }

    public void setTarget(EntityLivingBase target) {
        this.target = target;
        this.world = target == null ? null : target.field_70170_p;
    }

    public void setScheme(BedrockScheme scheme) {
        this.setScheme(scheme, null);
    }

    public void setScheme(BedrockScheme scheme, Map<String, String> variables) {
        this.scheme = scheme;
        if (this.scheme == null) {
            return;
        }
        if (variables != null) {
            this.parseVariables(variables);
        }
        this.lit = true;
        this.stop();
        this.start();
        this.setupVariables();
        this.setEmitterVariables(0.0f);
    }

    public void setupVariables() {
        this.varAge = (Variable)this.scheme.parser.variables.get("variable.particle_age");
        this.varLifetime = (Variable)this.scheme.parser.variables.get("variable.particle_lifetime");
        this.varRandom1 = (Variable)this.scheme.parser.variables.get("variable.particle_random_1");
        this.varRandom2 = (Variable)this.scheme.parser.variables.get("variable.particle_random_2");
        this.varRandom3 = (Variable)this.scheme.parser.variables.get("variable.particle_random_3");
        this.varRandom4 = (Variable)this.scheme.parser.variables.get("variable.particle_random_4");
        this.varSpeedABS = (Variable)this.scheme.parser.variables.get("variable.particle_speed.length");
        this.varSpeedX = (Variable)this.scheme.parser.variables.get("variable.particle_speed.x");
        this.varSpeedY = (Variable)this.scheme.parser.variables.get("variable.particle_speed.y");
        this.varSpeedZ = (Variable)this.scheme.parser.variables.get("variable.particle_speed.z");
        this.varBounces = (Variable)this.scheme.parser.variables.get("variable.particle_bounces");
        this.varEmitterAge = (Variable)this.scheme.parser.variables.get("variable.emitter_age");
        this.varEmitterLifetime = (Variable)this.scheme.parser.variables.get("variable.emitter_lifetime");
        this.varEmitterRandom1 = (Variable)this.scheme.parser.variables.get("variable.emitter_random_1");
        this.varEmitterRandom2 = (Variable)this.scheme.parser.variables.get("variable.emitter_random_2");
        this.varEmitterRandom3 = (Variable)this.scheme.parser.variables.get("variable.emitter_random_3");
        this.varEmitterRandom4 = (Variable)this.scheme.parser.variables.get("variable.emitter_random_4");
    }

    public void setParticleVariables(BedrockParticle particle, float partialTicks) {
        if (this.varAge != null) {
            this.varAge.set(particle.getAge(partialTicks));
        }
        if (this.varLifetime != null) {
            this.varLifetime.set((double)particle.lifetime / 20.0);
        }
        if (this.varRandom1 != null) {
            this.varRandom1.set(particle.random1);
        }
        if (this.varRandom2 != null) {
            this.varRandom2.set(particle.random2);
        }
        if (this.varRandom3 != null) {
            this.varRandom3.set(particle.random3);
        }
        if (this.varRandom4 != null) {
            this.varRandom4.set(particle.random4);
        }
        if (this.varSpeedABS != null) {
            this.varSpeedABS.set(particle.speed.length());
        }
        if (this.varSpeedX != null) {
            this.varSpeedX.set(particle.speed.x);
        }
        if (this.varSpeedY != null) {
            this.varSpeedY.set(particle.speed.y);
        }
        if (this.varSpeedZ != null) {
            this.varSpeedZ.set(particle.speed.z);
        }
        if (this.varBounces != null) {
            this.varBounces.set(particle.bounces);
        }
        this.scheme.updateCurves();
        BedrockComponentInitialization component = this.scheme.get(BedrockComponentInitialization.class);
        if (component != null) {
            component.particleUpdate.get();
        }
    }

    public void setEmitterVariables(float partialTicks) {
        for (Map.Entry<String, Double> entry : this.initialValues.entrySet()) {
            Variable var = (Variable)this.scheme.parser.variables.get(entry.getKey());
            if (var == null) continue;
            var.set(entry.getValue());
        }
        if (this.varEmitterAge != null) {
            this.varEmitterAge.set(this.getAge(partialTicks));
        }
        if (this.varEmitterLifetime != null) {
            this.varEmitterLifetime.set((double)this.lifetime / 20.0);
        }
        if (this.varEmitterRandom1 != null) {
            this.varEmitterRandom1.set(this.random1);
        }
        if (this.varEmitterRandom2 != null) {
            this.varEmitterRandom2.set(this.random2);
        }
        if (this.varEmitterRandom3 != null) {
            this.varEmitterRandom3.set(this.random3);
        }
        if (this.varEmitterRandom4 != null) {
            this.varEmitterRandom4.set(this.random4);
        }
        this.scheme.updateCurves();
    }

    public void parseVariables(Map<String, String> variables) {
        this.variables = new HashMap<String, IValue>();
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            this.parseVariable(entry.getKey(), entry.getValue());
        }
    }

    public void parseVariable(String name, String expression) {
        try {
            this.variables.put(name, this.scheme.parser.parse(expression));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void replaceVariables() {
        if (this.variables == null) {
            return;
        }
        for (Map.Entry<String, IValue> entry : this.variables.entrySet()) {
            Variable var = (Variable)this.scheme.parser.variables.get(entry.getKey());
            if (var == null) continue;
            var.set(entry.getValue().get());
        }
    }

    public void start() {
        if (this.playing) {
            return;
        }
        this.age = 0;
        this.spawnedParticles = 0.0;
        this.playing = true;
        for (IComponentEmitterInitialize component : this.scheme.emitterInitializes) {
            component.apply(this);
        }
    }

    public void stop() {
        if (!this.playing) {
            return;
        }
        this.spawnedParticles = 0.0;
        this.playing = false;
        this.random1 = (float)Math.random();
        this.random2 = (float)Math.random();
        this.random3 = (float)Math.random();
        this.random4 = (float)Math.random();
    }

    public void update() {
        if (this.scheme == null) {
            return;
        }
        this.setEmitterVariables(0.0f);
        for (IComponentEmitterUpdate component : this.scheme.emitterUpdates) {
            component.update(this);
        }
        this.setEmitterVariables(0.0f);
        this.updateParticles();
        ++this.age;
        ++this.sanityTicks;
    }

    private void updateParticles() {
        Iterator<BedrockParticle> it = this.particles.iterator();
        while (it.hasNext()) {
            BedrockParticle particle = it.next();
            this.updateParticle(particle);
            if (!particle.dead) continue;
            it.remove();
        }
        if (!this.splitParticles.isEmpty()) {
            this.particles.addAll(this.splitParticles);
            this.splitParticles.clear();
        }
    }

    private void updateParticlesCollision() {
        BedrockComponentMotionCollision collision = null;
        for (IComponentParticleUpdate component : this.scheme.particleUpdates) {
            if (!(component instanceof BedrockComponentMotionCollision)) continue;
            collision = (BedrockComponentMotionCollision)component;
        }
        if (collision == null) {
            return;
        }
        Iterator<BedrockParticle> it = this.particles.iterator();
        while (it.hasNext()) {
            BedrockParticle particle = it.next();
            collision.update(this, particle);
            if (!particle.dead) continue;
            it.remove();
        }
        if (!this.splitParticles.isEmpty()) {
            this.particles.addAll(this.splitParticles);
            this.splitParticles.clear();
        }
    }

    private void updateParticle(BedrockParticle particle) {
        particle.update(this);
        this.setParticleVariables(particle, 0.0f);
        for (IComponentParticleUpdate component : this.scheme.particleUpdates) {
            component.update(this, particle);
        }
    }

    public void spawnParticle() {
        if (!this.running) {
            return;
        }
        this.particles.add(this.createParticle(false));
    }

    public BedrockParticle createParticle(boolean forceRelative) {
        BedrockParticle particle = new BedrockParticle();
        this.setParticleVariables(particle, 0.0f);
        particle.setupMatrix(this);
        for (IComponentParticleInitialize component : this.scheme.particleInitializes) {
            component.apply(this, particle);
        }
        if (particle.relativePosition && !particle.relativeRotation) {
            Vector3f vec = new Vector3f(particle.position);
            particle.matrix.transform((Tuple3f)vec);
            particle.position.x = vec.x;
            particle.position.y = vec.y;
            particle.position.z = vec.z;
        }
        if (!particle.relativePosition || !particle.relativeRotation) {
            particle.position.add((Tuple3d)this.lastGlobal);
            particle.initialPosition.add((Tuple3d)this.lastGlobal);
        }
        particle.prevPosition.set((Tuple3d)particle.position);
        particle.prevRotation = particle.rotation = particle.initialRotation;
        return particle;
    }

    public void renderOnScreen(int x, int y, float scale) {
        if (this.scheme == null) {
            return;
        }
        BedrockComponentParticleMorph particleMorphComponent = this.scheme.getOrCreate(BedrockComponentParticleMorph.class);
        float partialTicks = Minecraft.func_71410_x().field_71428_T.field_74281_c;
        List<IComponentParticleRender> listParticle = this.scheme.getComponents(IComponentParticleRender.class);
        List<IComponentParticleMorphRender> listMorph = this.scheme.getComponents(IComponentParticleMorphRender.class);
        Matrix3f rotation = this.rotation;
        this.rotation = new Matrix3f();
        if (!(listParticle.isEmpty() || this.isMorphParticle() && !particleMorphComponent.renderTexture)) {
            Minecraft.func_71410_x().field_71446_o.func_110577_a(this.scheme.texture);
            this.scheme.material.beginGL();
            GlStateManager.disableCull();
            if (this.guiParticle == null || this.guiParticle.dead) {
                this.guiParticle = this.createParticle(true);
            }
            this.rotation.setIdentity();
            this.guiParticle.update(this);
            this.setEmitterVariables(partialTicks);
            this.setParticleVariables(this.guiParticle, partialTicks);
            for (IComponentParticleRender iComponentParticleRender : listParticle) {
                iComponentParticleRender.renderOnScreen(this.guiParticle, x, y, scale, partialTicks);
            }
            this.scheme.material.endGL();
            GlStateManager.enableCull();
        }
        if (!listMorph.isEmpty() && this.isMorphParticle()) {
            if (this.guiParticle == null || this.guiParticle.dead) {
                this.guiParticle = this.createParticle(true);
            }
            this.rotation.setIdentity();
            this.guiParticle.update(this);
            this.setEmitterVariables(partialTicks);
            this.setParticleVariables(this.guiParticle, partialTicks);
            for (IComponentParticleMorphRender iComponentParticleMorphRender : listMorph) {
                iComponentParticleMorphRender.renderOnScreen(this.guiParticle, x, y, scale, partialTicks);
            }
        }
        this.rotation = rotation;
    }

    public void render(float partialTicks) {
        if (this.scheme == null) {
            return;
        }
        this.setupCameraProperties(partialTicks);
        BedrockComponentParticleMorph particleMorphComponent = this.scheme.getOrCreate(BedrockComponentParticleMorph.class);
        List<IComponentParticleRender> renders = this.scheme.particleRender;
        List<IComponentParticleMorphRender> morphRenders = this.scheme.particleMorphRender;
        boolean morphRendering = this.isMorphParticle();
        boolean particleRendering = !morphRendering || particleMorphComponent.renderTexture;
        this.updateParticlesCollision();
        if (particleRendering) {
            this.setupOpenGL(partialTicks);
            for (IComponentParticleRender iComponentParticleRender : renders) {
                iComponentParticleRender.preRender(this, partialTicks);
            }
            if (!this.particles.isEmpty()) {
                this.depthSorting();
                this.renderParticles(this.scheme.texture, renders, false, partialTicks);
                BedrockComponentCollisionAppearance collisionAppearance = this.scheme.getOrCreate(BedrockComponentCollisionAppearance.class);
                if (collisionAppearance != null && collisionAppearance.texture != null) {
                    this.renderParticles(collisionAppearance.texture, renders, true, partialTicks);
                }
            }
            for (IComponentParticleRender iComponentParticleRender : renders) {
                iComponentParticleRender.postRender(this, partialTicks);
            }
            this.endOpenGL();
        }
        if (morphRendering) {
            for (IComponentParticleMorphRender iComponentParticleMorphRender : morphRenders) {
                iComponentParticleMorphRender.preRender(this, partialTicks);
            }
            if (!this.particles.isEmpty()) {
                if (!particleRendering) {
                    this.depthSorting();
                }
                this.renderParticles(morphRenders, false, partialTicks);
            }
            for (IComponentParticleMorphRender iComponentParticleMorphRender : morphRenders) {
                if (iComponentParticleMorphRender.getClass() == BedrockComponentRateSteady.class) {
                    if (particleRendering) continue;
                    iComponentParticleMorphRender.postRender(this, partialTicks);
                    continue;
                }
                iComponentParticleMorphRender.postRender(this, partialTicks);
            }
        }
    }

    private void renderParticles(List<? extends IComponentParticleMorphRender> renderComponents, boolean collided, float partialTicks) {
        for (BedrockParticle particle : this.particles) {
            this.setEmitterVariables(partialTicks);
            this.setParticleVariables(particle, partialTicks);
            for (IComponentRenderBase iComponentRenderBase : renderComponents) {
                iComponentRenderBase.render(this, particle, Tessellator.field_78398_a, partialTicks);
            }
        }
    }

    private void renderParticles(ResourceLocation texture, List<? extends IComponentParticleRender> renderComponents, boolean collided, float partialTicks) {
        GifTexture.bindTexture(texture, this.age, partialTicks);
        Tessellator.field_78398_a.func_78382_b();
        for (BedrockParticle particle : this.particles) {
            boolean collisionStuff;
            boolean bl = collisionStuff = particle.isCollisionTexture(this) || particle.isCollisionTinting(this);
            if (collisionStuff != collided) continue;
            this.setEmitterVariables(partialTicks);
            this.setParticleVariables(particle, partialTicks);
            for (IComponentRenderBase iComponentRenderBase : renderComponents) {
                if (collisionStuff && iComponentRenderBase.getClass() == BedrockComponentAppearanceBillboard.class) continue;
                iComponentRenderBase.render(this, particle, Tessellator.field_78398_a, partialTicks);
            }
        }
        Tessellator.field_78398_a.func_78381_a();
    }

    private void setupOpenGL(float partialTicks) {
        this.scheme.material.beginGL();
        if (!GuiModelRenderer.isRendering()) {
            EntityLivingBase camera = Minecraft.func_71410_x().field_71451_h;
            GlStateManager.disableCull();
            GlStateManager.enableTexture2D();
        }
    }

    private void endOpenGL() {
        if (!GuiModelRenderer.isRendering()) {
            Tessellator.field_78398_a.func_78373_b(0.0, 0.0, 0.0);
        }
        this.scheme.material.endGL();
    }

    private void depthSorting() {
        this.particles.sort((a, b) -> {
            double bd;
            double ad = a.getDistanceSq(this);
            if (ad < (bd = b.getDistanceSq(this))) {
                return 1;
            }
            if (ad > bd) {
                return -1;
            }
            return 0;
        });
    }

    public void setupCameraProperties(float partialTicks) {
        if (this.world != null) {
            EntityLivingBase camera = Minecraft.func_71410_x().field_71451_h;
            this.perspective = Minecraft.func_71410_x().field_71474_y.field_74320_O;
            this.cYaw = 180.0f - Interpolations.lerp(camera.field_70126_B, camera.field_70177_z, partialTicks);
            this.cPitch = 180.0f - Interpolations.lerp(camera.field_70127_C, camera.field_70125_A, partialTicks);
            this.cX = Interpolations.lerp(camera.field_70169_q, camera.field_70165_t, (double)partialTicks);
            this.cY = Interpolations.lerp(camera.field_70167_r, camera.field_70163_u, (double)partialTicks) + (double)camera.func_70047_e();
            this.cZ = Interpolations.lerp(camera.field_70166_s, camera.field_70161_v, (double)partialTicks);
        }
    }

    public int getBrightnessForRender(float partialTicks, double x, double y, double z) {
        if (this.lit || this.world == null) {
            return 0xF000F0;
        }
        this.blockPos.set((int)x, (int)y, (int)z);
        return this.world.func_72899_e((int)x, (int)y, (int)z) ? this.world.func_72802_i((int)x, (int)y, (int)z, 0) : 0;
    }
}

