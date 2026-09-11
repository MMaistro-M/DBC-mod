/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.hash;

import java.nio.charset.Charset;
import org.spongepowered.libraries.com.google.common.annotations.Beta;
import org.spongepowered.libraries.com.google.common.hash.Funnel;
import org.spongepowered.libraries.com.google.common.hash.HashCode;
import org.spongepowered.libraries.com.google.common.hash.Hasher;

@Beta
public interface HashFunction {
    public Hasher newHasher();

    public Hasher newHasher(int var1);

    public HashCode hashInt(int var1);

    public HashCode hashLong(long var1);

    public HashCode hashBytes(byte[] var1);

    public HashCode hashBytes(byte[] var1, int var2, int var3);

    public HashCode hashUnencodedChars(CharSequence var1);

    public HashCode hashString(CharSequence var1, Charset var2);

    public <T> HashCode hashObject(T var1, Funnel<? super T> var2);

    public int bits();
}

