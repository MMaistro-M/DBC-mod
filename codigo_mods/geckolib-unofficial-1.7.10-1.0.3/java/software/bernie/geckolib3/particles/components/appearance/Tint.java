/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonPrimitive
 */
package software.bernie.geckolib3.particles.components.appearance;

import com.eliotlash.mclib.math.Constant;
import com.eliotlash.mclib.utils.Interpolations;
import com.eliotlash.mclib.utils.MathUtils;
import com.eliotlash.molang.MolangException;
import com.eliotlash.molang.MolangParser;
import com.eliotlash.molang.expressions.MolangExpression;
import com.eliotlash.molang.expressions.MolangValue;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import software.bernie.geckolib3.particles.BedrockSchemeJsonAdapter;
import software.bernie.geckolib3.particles.emitter.BedrockParticle;

public abstract class Tint {
    public static Solid parseColor(JsonElement element, MolangParser parser) throws MolangException {
        JsonArray array;
        MolangExpression r = MolangParser.ONE;
        MolangExpression g = MolangParser.ONE;
        MolangExpression b = MolangParser.ONE;
        MolangExpression a = MolangParser.ONE;
        if (element.isJsonPrimitive()) {
            String hex = element.getAsString();
            if (hex.startsWith("#") && (hex.length() == 7 || hex.length() == 9)) {
                boolean hasAlpha = hex.length() == 9;
                try {
                    int color = Integer.parseInt(hex.substring(hasAlpha ? 3 : 1), 16);
                    float hr = (float)(color >> 16 & 0xFF) / 255.0f;
                    float hg = (float)(color >> 8 & 0xFF) / 255.0f;
                    float hb = (float)(color & 0xFF) / 255.0f;
                    float ha = hasAlpha ? (float)Integer.parseInt(hex.substring(1, 3), 16) / 255.0f : 1.0f;
                    r = new MolangValue(parser, new Constant(hr));
                    g = new MolangValue(parser, new Constant(hg));
                    b = new MolangValue(parser, new Constant(hb));
                    a = new MolangValue(parser, new Constant(ha));
                }
                catch (Exception exception) {}
            }
        } else if (element.isJsonArray() && ((array = element.getAsJsonArray()).size() == 3 || array.size() == 4)) {
            r = parser.parseJson(array.get(0));
            g = parser.parseJson(array.get(1));
            b = parser.parseJson(array.get(2));
            if (array.size() == 4) {
                a = parser.parseJson(array.get(3));
            }
        }
        return new Solid(r, g, b, a);
    }

    public static Tint parseGradient(JsonObject color, MolangParser parser) throws MolangException {
        JsonElement gradient = color.get("gradient");
        MolangExpression expression = MolangParser.ZERO;
        ArrayList<Gradient.ColorStop> colorStops = new ArrayList<Gradient.ColorStop>();
        boolean equal = true;
        if (gradient.isJsonObject()) {
            for (Map.Entry entry : gradient.getAsJsonObject().entrySet()) {
                Solid stopColor = Tint.parseColor((JsonElement)entry.getValue(), parser);
                colorStops.add(new Gradient.ColorStop(Float.parseFloat((String)entry.getKey()), stopColor));
            }
            colorStops.sort((a, b) -> Float.compare(a.stop, b.stop));
            equal = false;
        } else if (gradient.isJsonArray()) {
            JsonArray colors = gradient.getAsJsonArray();
            int i = 0;
            for (JsonElement stop : colors) {
                colorStops.add(new Gradient.ColorStop((float)i / (float)(colors.size() - 1), Tint.parseColor(stop, parser)));
                ++i;
            }
        }
        float range = ((Gradient.ColorStop)colorStops.get((int)(colorStops.size() - 1))).stop;
        for (Gradient.ColorStop stop : colorStops) {
            stop.stop /= range;
        }
        if (color.has("interpolant")) {
            expression = parser.parseJson(color.get("interpolant"));
        }
        return new Gradient(colorStops, range, expression, equal);
    }

    public abstract void compute(BedrockParticle var1);

    public abstract JsonElement toJson();

    public static class Solid
    extends Tint {
        public MolangExpression r;
        public MolangExpression g;
        public MolangExpression b;
        public MolangExpression a;

        public Solid(MolangExpression r, MolangExpression g, MolangExpression b, MolangExpression a) {
            this.r = r;
            this.g = g;
            this.b = b;
            this.a = a;
        }

        public Solid() {
            this.r = MolangParser.ONE;
            this.g = MolangParser.ONE;
            this.b = MolangParser.ONE;
            this.a = MolangParser.ONE;
        }

        public boolean isConstant() {
            return MolangExpression.isExpressionConstant(this.r) && MolangExpression.isExpressionConstant(this.g) && MolangExpression.isExpressionConstant(this.b) && MolangExpression.isExpressionConstant(this.a);
        }

        @Override
        public void compute(BedrockParticle particle) {
            particle.r = (float)this.r.get();
            particle.g = (float)this.g.get();
            particle.b = (float)this.b.get();
            particle.a = (float)this.a.get();
        }

        @Override
        public JsonElement toJson() {
            JsonArray array = new JsonArray();
            if (MolangExpression.isOne(this.r) && MolangExpression.isOne(this.g) && MolangExpression.isOne(this.b) && MolangExpression.isOne(this.a)) {
                return array;
            }
            array.add(this.r.toJson());
            array.add(this.g.toJson());
            array.add(this.b.toJson());
            array.add(this.a.toJson());
            return array;
        }

        public JsonElement toHexJson() {
            int r = (int)(this.r.get() * 255.0) & 0xFF;
            int g = (int)(this.g.get() * 255.0) & 0xFF;
            int b = (int)(this.b.get() * 255.0) & 0xFF;
            int a = (int)(this.a.get() * 255.0) & 0xFF;
            String hex = "#";
            if (a < 255) {
                hex = hex + StringUtils.leftPad(Integer.toHexString(a), 2, "0").toUpperCase();
            }
            hex = hex + StringUtils.leftPad(Integer.toHexString(r), 2, "0").toUpperCase();
            hex = hex + StringUtils.leftPad(Integer.toHexString(g), 2, "0").toUpperCase();
            hex = hex + StringUtils.leftPad(Integer.toHexString(b), 2, "0").toUpperCase();
            return new JsonPrimitive(hex);
        }

        public void lerp(BedrockParticle particle, float factor) {
            particle.r = Interpolations.lerp(particle.r, (float)this.r.get(), factor);
            particle.g = Interpolations.lerp(particle.g, (float)this.g.get(), factor);
            particle.b = Interpolations.lerp(particle.b, (float)this.b.get(), factor);
            particle.a = Interpolations.lerp(particle.a, (float)this.a.get(), factor);
        }
    }

    public static class Gradient
    extends Tint {
        public List<ColorStop> stops;
        public MolangExpression interpolant;
        public float range = 1.0f;
        public boolean equal;

        public Gradient(List<ColorStop> stops, float range, MolangExpression interpolant, boolean equal) {
            this.stops = stops;
            this.range = range;
            this.interpolant = interpolant;
            this.equal = equal;
        }

        public Gradient() {
            this.stops = new ArrayList<ColorStop>();
            this.stops.add(new ColorStop(0.0f, new Solid(new MolangValue(null, new Constant(1.0)), new MolangValue(null, new Constant(1.0)), new MolangValue(null, new Constant(1.0)), new MolangValue(null, new Constant(1.0)))));
            this.stops.add(new ColorStop(1.0f, new Solid(new MolangValue(null, new Constant(0.0)), new MolangValue(null, new Constant(0.0)), new MolangValue(null, new Constant(0.0)), new MolangValue(null, new Constant(1.0)))));
            this.interpolant = MolangParser.ZERO;
            this.equal = false;
        }

        public void sort() {
            this.stops.sort((a, b) -> Float.compare(a.stop, b.stop));
        }

        @Override
        public void compute(BedrockParticle particle) {
            ColorStop prev;
            int length = this.stops.size();
            if (length == 0) {
                particle.a = 1.0f;
                particle.b = 1.0f;
                particle.g = 1.0f;
                particle.r = 1.0f;
                return;
            }
            if (length == 1) {
                this.stops.get((int)0).color.compute(particle);
                return;
            }
            double factor = this.interpolant.get();
            if ((factor = MathUtils.clamp(factor, 0.0, 1.0)) < (double)(prev = this.stops.get(0)).getStop(this.range)) {
                prev.color.compute(particle);
                return;
            }
            for (int i = 1; i < length; ++i) {
                ColorStop stop = this.stops.get(i);
                if ((double)stop.getStop(this.range) > factor) {
                    prev.color.compute(particle);
                    stop.color.lerp(particle, (float)(factor - (double)prev.getStop(this.range)) / (stop.getStop(this.range) - prev.getStop(this.range)));
                    return;
                }
                prev = stop;
            }
            prev.color.compute(particle);
        }

        @Override
        public JsonElement toJson() {
            JsonArray color;
            JsonObject object = new JsonObject();
            if (this.equal) {
                JsonArray gradient = new JsonArray();
                for (ColorStop stop : this.stops) {
                    gradient.add(stop.color.toHexJson());
                }
                color = gradient;
            } else {
                JsonObject gradient = new JsonObject();
                for (ColorStop stop : this.stops) {
                    gradient.add(String.valueOf(stop.getStop(this.range)), stop.color.toHexJson());
                }
                color = gradient;
            }
            if (!BedrockSchemeJsonAdapter.isEmpty((JsonElement)color)) {
                object.add("gradient", (JsonElement)color);
            }
            if (!MolangExpression.isZero(this.interpolant)) {
                object.add("interpolant", this.interpolant.toJson());
            }
            return object;
        }

        public static class ColorStop {
            public float stop;
            public Solid color;

            public ColorStop(float stop, Solid color) {
                this.stop = stop;
                this.color = color;
            }

            public float getStop(float range) {
                return this.stop * range;
            }
        }
    }
}

