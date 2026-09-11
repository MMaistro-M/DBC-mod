/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.Tessellator
 *  org.lwjgl.opengl.GL11
 */
package kamkeel.npcs.client.renderer.lightning;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import kamkeel.npcs.client.renderer.lightning.Vec3d;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public class AttachedLightningRenderer {
    private static final Random rand = new Random();

    private static LightningArc createArc(float radius, int outerColor, int innerColor, int maxAge) {
        double theta = rand.nextDouble() * Math.PI * 2.0;
        double phi = Math.acos(2.0 * rand.nextDouble() - 1.0);
        double r = (double)radius * (0.5 + rand.nextDouble() * 0.5);
        double endX = r * Math.sin(phi) * Math.cos(theta);
        double endY = r * Math.sin(phi) * Math.sin(theta);
        double endZ = r * Math.cos(phi);
        int segments = 4 + rand.nextInt(3);
        float displacement = radius * 0.25f;
        List<double[]> points = AttachedLightningRenderer.generateLightningPath(0.0, 0.0, 0.0, endX, endY, endZ, segments, displacement);
        return new LightningArc(points, maxAge, outerColor, innerColor);
    }

    private static List<double[]> generateLightningPath(double x1, double y1, double z1, double x2, double y2, double z2, int segments, float displacement) {
        ArrayList<double[]> points = new ArrayList<double[]>();
        points.add(new double[]{x1, y1, z1});
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dz = z2 - z1;
        Vec3d dir = new Vec3d(dx, dy, dz);
        Vec3d perp1 = dir.copy().perpendicular().normalize();
        Vec3d perp2 = dir.copy().crossProduct(perp1).normalize();
        for (int i = 1; i < segments; ++i) {
            double t = (double)i / (double)segments;
            double px = x1 + dx * t;
            double py = y1 + dy * t;
            double pz = z1 + dz * t;
            float strength = (float)(1.0 - Math.abs(t - 0.5) * 2.0) * displacement;
            double offset1 = (rand.nextDouble() - 0.5) * 2.0 * (double)strength;
            double offset2 = (rand.nextDouble() - 0.5) * 2.0 * (double)strength;
            points.add(new double[]{px += perp1.x * offset1 + perp2.x * offset2, py += perp1.y * offset1 + perp2.y * offset2, pz += perp1.z * offset1 + perp2.z * offset2});
        }
        points.add(new double[]{x2, y2, z2});
        return points;
    }

    private static void renderBoltPath(Tessellator tess, List<double[]> points, int color, float width, float alpha) {
        if (alpha <= 0.01f) {
            return;
        }
        int r = color >> 16 & 0xFF;
        int g = color >> 8 & 0xFF;
        int b = color & 0xFF;
        int a = Math.min(255, Math.max(0, (int)(alpha * 255.0f)));
        for (int i = 0; i < points.size() - 1; ++i) {
            double[] p1 = points.get(i);
            double[] p2 = points.get(i + 1);
            double segDx = p2[0] - p1[0];
            double segDy = p2[1] - p1[1];
            double segDz = p2[2] - p1[2];
            Vec3d segDir = new Vec3d(segDx, segDy, segDz);
            Vec3d perp = segDir.copy().perpendicular().normalize().multiply(width);
            tess.func_78382_b();
            tess.func_78380_c(0xF000F0);
            tess.func_78370_a(r, g, b, a);
            tess.func_78377_a(p1[0] - perp.x, p1[1] - perp.y, p1[2] - perp.z);
            tess.func_78377_a(p1[0] + perp.x, p1[1] + perp.y, p1[2] + perp.z);
            tess.func_78377_a(p2[0] + perp.x, p2[1] + perp.y, p2[2] + perp.z);
            tess.func_78377_a(p2[0] - perp.x, p2[1] - perp.y, p2[2] - perp.z);
            tess.func_78381_a();
            Vec3d perp2 = segDir.copy().crossProduct(perp).normalize().multiply(width);
            tess.func_78382_b();
            tess.func_78380_c(0xF000F0);
            tess.func_78370_a(r, g, b, a);
            tess.func_78377_a(p1[0] - perp2.x, p1[1] - perp2.y, p1[2] - perp2.z);
            tess.func_78377_a(p1[0] + perp2.x, p1[1] + perp2.y, p1[2] + perp2.z);
            tess.func_78377_a(p2[0] + perp2.x, p2[1] + perp2.y, p2[2] + perp2.z);
            tess.func_78377_a(p2[0] - perp2.x, p2[1] - perp2.y, p2[2] - perp2.z);
            tess.func_78381_a();
        }
    }

    public static LightningArc createArcBetween(double x1, double y1, double z1, double x2, double y2, double z2, float displacement, int outerColor, int innerColor, int maxAge) {
        int segments = 4 + rand.nextInt(3);
        List<double[]> points = AttachedLightningRenderer.generateLightningPath(x1, y1, z1, x2, y2, z2, segments, displacement);
        return new LightningArc(points, maxAge, outerColor, innerColor);
    }

    public static void renderInstantArc(float radius, int outerColor, int innerColor) {
        double theta = rand.nextDouble() * Math.PI * 2.0;
        double phi = Math.acos(2.0 * rand.nextDouble() - 1.0);
        double r = (double)radius * (0.5 + rand.nextDouble() * 0.5);
        double endX = r * Math.sin(phi) * Math.cos(theta);
        double endY = r * Math.sin(phi) * Math.sin(theta);
        double endZ = r * Math.cos(phi);
        int segments = 4 + rand.nextInt(3);
        float displacement = radius * 0.25f;
        List<double[]> points = AttachedLightningRenderer.generateLightningPath(0.0, 0.0, 0.0, endX, endY, endZ, segments, displacement);
        GL11.glDepthMask((boolean)false);
        Tessellator tess = Tessellator.field_78398_a;
        AttachedLightningRenderer.renderBoltPath(tess, points, outerColor, 0.025f, 0.35f);
        AttachedLightningRenderer.renderBoltPath(tess, points, innerColor, 0.012f, 0.7f);
        GL11.glDepthMask((boolean)true);
    }

    public static void renderCracklingLightning(int count, float radius, int outerColor, int innerColor) {
        for (int i = 0; i < count; ++i) {
            AttachedLightningRenderer.renderInstantArc(radius, outerColor, innerColor);
        }
    }

    public static class LightningState {
        private final List<LightningArc> arcs = new ArrayList<LightningArc>();
        private float spawnAccumulator = 0.0f;

        public void update(float density, float radius, int outerColor, int innerColor, int maxAge) {
            Iterator<LightningArc> iter = this.arcs.iterator();
            while (iter.hasNext()) {
                LightningArc arc = iter.next();
                arc.tick();
                if (!arc.isDead()) continue;
                iter.remove();
            }
            this.spawnAccumulator += density;
            while (this.spawnAccumulator >= 1.0f) {
                this.spawnAccumulator -= 1.0f;
                this.arcs.add(AttachedLightningRenderer.createArc(radius, outerColor, innerColor, maxAge));
            }
        }

        public void tick() {
            Iterator<LightningArc> iter = this.arcs.iterator();
            while (iter.hasNext()) {
                LightningArc arc = iter.next();
                arc.tick();
                if (!arc.isDead()) continue;
                iter.remove();
            }
        }

        public void addArc(LightningArc arc) {
            this.arcs.add(arc);
        }

        public void render() {
            if (this.arcs.isEmpty()) {
                return;
            }
            GL11.glDepthMask((boolean)false);
            Tessellator tess = Tessellator.field_78398_a;
            for (LightningArc arc : this.arcs) {
                float alphaMult = arc.getAlphaMultiplier();
                AttachedLightningRenderer.renderBoltPath(tess, arc.points, arc.outerColor, 0.025f, 0.35f * alphaMult);
                AttachedLightningRenderer.renderBoltPath(tess, arc.points, arc.innerColor, 0.012f, 0.7f * alphaMult);
            }
            GL11.glDepthMask((boolean)true);
        }

        public int getArcCount() {
            return this.arcs.size();
        }
    }

    public static class LightningArc {
        public List<double[]> points;
        public int age;
        public int maxAge;
        public int outerColor;
        public int innerColor;

        public LightningArc(List<double[]> points, int maxAge, int outerColor, int innerColor) {
            this.points = points;
            this.age = 0;
            this.maxAge = maxAge;
            this.outerColor = outerColor;
            this.innerColor = innerColor;
        }

        public float getAlphaMultiplier() {
            if (this.maxAge <= 0) {
                return 0.0f;
            }
            return 1.0f - (float)this.age / (float)this.maxAge;
        }

        public boolean isDead() {
            return this.age >= this.maxAge;
        }

        public void tick() {
            ++this.age;
        }
    }
}

