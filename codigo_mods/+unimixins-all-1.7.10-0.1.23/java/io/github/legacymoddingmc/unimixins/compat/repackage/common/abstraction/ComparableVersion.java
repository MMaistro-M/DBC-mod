/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.versioning.ComparableVersion
 *  net.minecraftforge.fml.common.versioning.ComparableVersion
 */
package io.github.legacymoddingmc.unimixins.compat.repackage.common.abstraction;

public class ComparableVersion {
    private final IComparableVersionImpl impl;

    public ComparableVersion(String version) {
        this.impl = ComparableVersion.class.getResource("/cpw/mods/fml/common/versioning/ComparableVersion.class") != null ? new ComparableVersionOld(version) : new ComparableVersionNew(version);
    }

    public int compareTo(ComparableVersion other) {
        return this.impl.compareTo(other.impl);
    }

    public static class ComparableVersionNew
    implements IComparableVersionImpl {
        private final net.minecraftforge.fml.common.versioning.ComparableVersion internal;

        public ComparableVersionNew(String version) {
            this.internal = new net.minecraftforge.fml.common.versioning.ComparableVersion(version);
        }

        @Override
        public int compareTo(IComparableVersionImpl other) {
            if (other instanceof ComparableVersionNew) {
                return this.internal.compareTo(((ComparableVersionNew)other).internal);
            }
            throw new IllegalArgumentException();
        }
    }

    public static class ComparableVersionOld
    implements IComparableVersionImpl {
        private final cpw.mods.fml.common.versioning.ComparableVersion internal;

        public ComparableVersionOld(String version) {
            this.internal = new cpw.mods.fml.common.versioning.ComparableVersion(version);
        }

        @Override
        public int compareTo(IComparableVersionImpl other) {
            if (other instanceof ComparableVersionOld) {
                return this.internal.compareTo(((ComparableVersionOld)other).internal);
            }
            throw new IllegalArgumentException();
        }
    }

    public static interface IComparableVersionImpl {
        public int compareTo(IComparableVersionImpl var1);
    }
}

