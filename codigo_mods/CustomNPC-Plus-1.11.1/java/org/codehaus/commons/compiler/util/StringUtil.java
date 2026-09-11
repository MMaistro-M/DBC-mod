/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.commons.compiler.util;

import java.io.File;
import java.util.ArrayList;
import org.codehaus.commons.nullanalysis.Nullable;

public final class StringUtil {
    private StringUtil() {
    }

    public static File[] parsePath(String s) {
        int from = 0;
        ArrayList<File> l = new ArrayList<File>();
        while (true) {
            int to;
            if ((to = s.indexOf(File.pathSeparatorChar, from)) == -1) {
                if (from == s.length()) break;
                l.add(new File(s.substring(from)));
                break;
            }
            if (to != from) {
                l.add(new File(s.substring(from, to)));
            }
            from = to + 1;
        }
        return l.toArray(new File[l.size()]);
    }

    @Nullable
    public static File[] parseOptionalPath(@Nullable String s) {
        return s == null ? null : StringUtil.parsePath(s);
    }
}

