/*
 * Decompiled with CFR 0.152.
 */
package kamkeel.npcs.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class FileNameHelper {
    private static final Set<String> WINDOWS_RESERVED_NAMES = new HashSet<String>(Arrays.asList("CON", "PRN", "AUX", "NUL", "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "LPT1", "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9"));

    private FileNameHelper() {
    }

    public static String sanitizeName(String input, String fallback) {
        String sanitized = FileNameHelper.sanitizeInternal(input);
        if (sanitized.isEmpty()) {
            sanitized = FileNameHelper.sanitizeInternal(fallback);
        }
        if (sanitized.isEmpty()) {
            sanitized = "Ability";
        }
        if (FileNameHelper.isReservedWindowsName(sanitized)) {
            sanitized = "_" + sanitized;
        }
        return sanitized;
    }

    public static String sanitizeTextInput(String input) {
        return FileNameHelper.sanitizeInternal(input);
    }

    public static boolean isFileNameCompatible(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        if (FileNameHelper.isReservedWindowsName(value)) {
            return false;
        }
        for (int i = 0; i < value.length(); ++i) {
            char c = value.charAt(i);
            if (Character.isLetterOrDigit(c) || c == '_' || c == '-') continue;
            return false;
        }
        return true;
    }

    public static String toDisplayName(String value) {
        if (value == null || value.isEmpty()) {
            return "";
        }
        return value.replace('_', ' ');
    }

    private static String sanitizeInternal(String raw) {
        if (raw == null) {
            return "";
        }
        String trimmed = raw.trim();
        if (trimmed.isEmpty()) {
            return "";
        }
        StringBuilder out = new StringBuilder(trimmed.length());
        for (int i = 0; i < trimmed.length(); ++i) {
            char last;
            char c = trimmed.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                out.append(c);
                continue;
            }
            if (c == '_' || c == '-') {
                out.append(c);
                continue;
            }
            if (!Character.isWhitespace(c) || out.length() == 0 || (last = out.charAt(out.length() - 1)) == '_') continue;
            out.append('_');
        }
        return out.toString();
    }

    private static boolean isReservedWindowsName(String value) {
        return WINDOWS_RESERVED_NAMES.contains(value.toUpperCase());
    }
}

