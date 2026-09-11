/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.core.easing;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.DoubleStream;
import software.bernie.geckolib3.core.easing.EasingType;
import software.bernie.geckolib3.core.util.Memoizer;

public class EasingManager {
    static Function<Double, Double> quart = EasingManager.poly(4.0);
    static Function<Double, Double> quint = EasingManager.poly(5.0);
    static Function<EasingFunctionArgs, Function<Double, Double>> getEasingFunction = Memoizer.memoize(EasingManager::getEasingFuncImpl);

    public static double ease(double number, EasingType easingType, List<Double> easingArgs) {
        Double firstArg = easingArgs == null || easingArgs.size() < 1 ? null : easingArgs.get(0);
        return getEasingFunction.apply(new EasingFunctionArgs(easingType, firstArg)).apply(number);
    }

    static Function<Double, Double> getEasingFuncImpl(EasingFunctionArgs args) {
        switch (args.easingType) {
            default: {
                return EasingManager.in(EasingManager::linear);
            }
            case Step: {
                return EasingManager.in(EasingManager.step(args.arg0));
            }
            case EaseInSine: {
                return EasingManager.in(EasingManager::sin);
            }
            case EaseOutSine: {
                return EasingManager.out(EasingManager::sin);
            }
            case EaseInOutSine: {
                return EasingManager.inOut(EasingManager::sin);
            }
            case EaseInQuad: {
                return EasingManager.in(EasingManager::quad);
            }
            case EaseOutQuad: {
                return EasingManager.out(EasingManager::quad);
            }
            case EaseInOutQuad: {
                return EasingManager.inOut(EasingManager::quad);
            }
            case EaseInCubic: {
                return EasingManager.in(EasingManager::cubic);
            }
            case EaseOutCubic: {
                return EasingManager.out(EasingManager::cubic);
            }
            case EaseInOutCubic: {
                return EasingManager.inOut(EasingManager::cubic);
            }
            case EaseInExpo: {
                return EasingManager.in(EasingManager::exp);
            }
            case EaseOutExpo: {
                return EasingManager.out(EasingManager::exp);
            }
            case EaseInOutExpo: {
                return EasingManager.inOut(EasingManager::exp);
            }
            case EaseInCirc: {
                return EasingManager.in(EasingManager::circle);
            }
            case EaseOutCirc: {
                return EasingManager.out(EasingManager::circle);
            }
            case EaseInOutCirc: {
                return EasingManager.inOut(EasingManager::circle);
            }
            case EaseInQuart: {
                return EasingManager.in(quart);
            }
            case EaseOutQuart: {
                return EasingManager.out(quart);
            }
            case EaseInOutQuart: {
                return EasingManager.inOut(quart);
            }
            case EaseInQuint: {
                return EasingManager.in(quint);
            }
            case EaseOutQuint: {
                return EasingManager.out(quint);
            }
            case EaseInOutQuint: {
                return EasingManager.inOut(quint);
            }
            case EaseInBack: {
                return EasingManager.in(EasingManager.back(args.arg0));
            }
            case EaseOutBack: {
                return EasingManager.out(EasingManager.back(args.arg0));
            }
            case EaseInOutBack: {
                return EasingManager.inOut(EasingManager.back(args.arg0));
            }
            case EaseInElastic: {
                return EasingManager.in(EasingManager.elastic(args.arg0));
            }
            case EaseOutElastic: {
                return EasingManager.out(EasingManager.elastic(args.arg0));
            }
            case EaseInOutElastic: {
                return EasingManager.inOut(EasingManager.elastic(args.arg0));
            }
            case EaseInBounce: {
                return EasingManager.in(EasingManager.bounce(args.arg0));
            }
            case EaseOutBounce: {
                return EasingManager.out(EasingManager.bounce(args.arg0));
            }
            case EaseInOutBounce: {
                return EasingManager.inOut(EasingManager.bounce(args.arg0));
            }
            case Catmullrom: 
        }
        return EasingManager.in(EasingManager::catmullRom);
    }

    static Function<Double, Double> in(Function<Double, Double> easing) {
        return easing;
    }

    static Function<Double, Double> out(Function<Double, Double> easing) {
        return t -> 1.0 - (Double)easing.apply(1.0 - t);
    }

    static Function<Double, Double> inOut(Function<Double, Double> easing) {
        return t -> {
            if (t < 0.5) {
                return (Double)easing.apply(t * 2.0) / 2.0;
            }
            return 1.0 - (Double)easing.apply((1.0 - t) * 2.0) / 2.0;
        };
    }

    static Function<Double, Double> step0() {
        return n -> n > 0.0 ? 1.0 : 0.0;
    }

    static Function<Double, Double> step1() {
        return n -> n >= 1.0 ? 1.0 : 0.0;
    }

    static double linear(double t) {
        return t;
    }

    static double catmullRom(double n) {
        return n;
    }

    public float q(float t) {
        return 0.5f * (4.0f + t - t * t);
    }

    static double quad(double t) {
        return t * t;
    }

    static double cubic(double t) {
        return t * t * t;
    }

    static Function<Double, Double> poly(double n) {
        return t -> Math.pow(t, n);
    }

    static double sin(double t) {
        return 1.0 - Math.cos((float)(t * Math.PI / 2.0));
    }

    static double circle(double t) {
        return 1.0 - Math.sqrt(1.0 - t * t);
    }

    static double exp(double t) {
        return Math.pow(2.0, 10.0 * (t - 1.0));
    }

    static Function<Double, Double> elastic(Double bounciness) {
        double p = (bounciness == null ? 1.0 : bounciness) * Math.PI;
        return t -> 1.0 - Math.pow(Math.cos((float)(t * Math.PI / 2.0)), 3.0) * Math.cos((float)(t * p));
    }

    static Function<Double, Double> back(Double s) {
        double p = s == null ? 1.70158 : s * 1.70158;
        return t -> t * t * ((p + 1.0) * t - p);
    }

    public static Function<Double, Double> bounce(Double s) {
        double k = s == null ? 0.5 : s;
        Function<Double, Double> q = x -> 7.5625 * x * x;
        Function<Double, Double> w = x -> 30.25 * k * Math.pow(x - 0.5454545454545454, 2.0) + 1.0 - k;
        Function<Double, Double> r = x -> 121.0 * k * k * Math.pow(x - 0.8181818181818182, 2.0) + 1.0 - k * k;
        Function<Double, Double> t = x -> 484.0 * k * k * k * Math.pow(x - 0.9545454545454546, 2.0) + 1.0 - k * k * k;
        return x -> EasingManager.min((Double)q.apply((Double)x), (Double)w.apply((Double)x), (Double)r.apply((Double)x), (Double)t.apply((Double)x));
    }

    static Function<Double, Double> step(Double stepArg) {
        int steps = stepArg != null ? stepArg.intValue() : 2;
        double[] intervals = EasingManager.stepRange(steps);
        return t -> intervals[EasingManager.findIntervalBorderIndex(t, intervals, false)];
    }

    static double min(double a, double b, double c, double d) {
        return Math.min(Math.min(a, b), Math.min(c, d));
    }

    static int findIntervalBorderIndex(double point, double[] intervals, boolean useRightBorder) {
        if (point < intervals[0]) {
            return 0;
        }
        if (point > intervals[intervals.length - 1]) {
            return intervals.length - 1;
        }
        int indexOfNumberToCompare = 0;
        int leftBorderIndex = 0;
        int rightBorderIndex = intervals.length - 1;
        while (rightBorderIndex - leftBorderIndex != 1) {
            indexOfNumberToCompare = leftBorderIndex + (rightBorderIndex - leftBorderIndex) / 2;
            if (point >= intervals[indexOfNumberToCompare]) {
                leftBorderIndex = indexOfNumberToCompare;
                continue;
            }
            rightBorderIndex = indexOfNumberToCompare;
        }
        return useRightBorder ? rightBorderIndex : leftBorderIndex;
    }

    static double[] stepRange(int steps) {
        double stop = 1.0;
        if (steps < 2) {
            throw new IllegalArgumentException("steps must be > 2, got:" + steps);
        }
        double stepLength = 1.0 / (double)steps;
        AtomicInteger i = new AtomicInteger();
        return DoubleStream.generate(() -> (double)i.getAndIncrement() * stepLength).limit(steps).toArray();
    }

    static class EasingFunctionArgs {
        public final EasingType easingType;
        public final Double arg0;

        public EasingFunctionArgs(EasingType easingType, Double arg0) {
            this.easingType = easingType;
            this.arg0 = arg0;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            EasingFunctionArgs that = (EasingFunctionArgs)o;
            return this.easingType == that.easingType && Objects.equals(this.arg0, that.arg0);
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.easingType, this.arg0});
        }
    }
}

