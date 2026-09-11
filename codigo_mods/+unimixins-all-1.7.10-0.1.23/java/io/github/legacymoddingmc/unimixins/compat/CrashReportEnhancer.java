/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.crash.CrashReport
 *  net.minecraft.crash.CrashReportCategory
 */
package io.github.legacymoddingmc.unimixins.compat;

import io.github.legacymoddingmc.unimixins.compat.CompatCore;
import io.github.legacymoddingmc.unimixins.compat.MixinErrorHandler;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.ClassInfo;

public class CrashReportEnhancer {
    public static void addMixinsToCrashReport(CrashReport crashReport, CrashReportCategory category) {
        try {
            Collection<String> classesInStackTrace = CrashReportEnhancer.getClassesInStackTrace(crashReport);
            CrashReportEnhancer.addMixinErrorsToCrashReport(category, classesInStackTrace);
            CrashReportEnhancer.addMixinsToCrashReport(category, classesInStackTrace);
        }
        catch (Throwable t) {
            CompatCore.LOGGER.error("Encountered error while enhancing crash report");
            t.printStackTrace();
        }
    }

    private static void addMixinsToCrashReport(CrashReportCategory category, Collection<String> classesInStackTrace) {
        String msg = "";
        for (String s : classesInStackTrace) {
            Collection<IMixinInfo> mixins = CrashReportEnhancer.getMixinsForClass(s);
            if (mixins.isEmpty()) continue;
            msg = msg + "\n\t\t" + s + ":";
            for (IMixinInfo mi : mixins) {
                msg = msg + "\n\t\t\t" + mi;
            }
        }
        if (!msg.isEmpty()) {
            category.func_71507_a("Mixins in Stacktrace", (Object)msg);
        }
    }

    private static Collection<IMixinInfo> getMixinsForClass(String s) {
        try {
            Field f;
            ClassInfo ci = ClassInfo.fromCache(s);
            if (ci != null && (f = ClassInfo.class.getDeclaredField("mixins")) != null) {
                f.setAccessible(true);
                return (Set)f.get(ci);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return Collections.emptySet();
    }

    private static void addMixinErrorsToCrashReport(CrashReportCategory category, Collection<String> classesInStackTrace) {
        HashMap<String, List<String>> errors = new HashMap<String, List<String>>();
        for (String s : classesInStackTrace) {
            List<String> list = MixinErrorHandler.getErrorsForClass(s);
            if (list.isEmpty()) continue;
            errors.put(s, list);
        }
        if (!errors.isEmpty()) {
            String msg = "";
            for (Map.Entry entry : errors.entrySet()) {
                String cls = (String)entry.getKey();
                List clsErrors = (List)entry.getValue();
                msg = msg + "\n\t\t" + cls + ":";
                for (String clsError : (List)entry.getValue()) {
                    msg = msg + "\n\t\t\t" + clsError;
                }
            }
            category.func_71507_a(String.format("Mixin Errors in Stacktrace", new Object[0]), (Object)msg);
        }
    }

    private static Collection<String> getClassesInStackTrace(CrashReport crashReport) {
        HashSet<String> classes = new HashSet<String>();
        for (Throwable th = crashReport.func_71505_b(); th != null; th = th.getCause()) {
            String msg;
            if (th instanceof ClassNotFoundException && (msg = th.getMessage()) != null && !msg.isEmpty()) {
                classes.add(msg);
            }
            for (StackTraceElement elem : th.getStackTrace()) {
                classes.add(elem.getClassName());
            }
        }
        return classes;
    }
}

