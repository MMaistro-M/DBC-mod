/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.core.json;

import com.relocated.fasterxml.jackson.core.Version;
import com.relocated.fasterxml.jackson.core.Versioned;
import com.relocated.fasterxml.jackson.core.util.VersionUtil;

public final class PackageVersion
implements Versioned {
    public static final Version VERSION = VersionUtil.parseVersion("2.9.0", "com.relocated.fasterxml.jackson.core", "jackson-core");

    @Override
    public Version version() {
        return VERSION;
    }
}

