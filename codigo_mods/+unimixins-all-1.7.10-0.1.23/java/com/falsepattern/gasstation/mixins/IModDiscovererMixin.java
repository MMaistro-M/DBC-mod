/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.discovery.ModCandidate
 */
package com.falsepattern.gasstation.mixins;

import cpw.mods.fml.common.discovery.ModCandidate;
import java.util.List;

@Deprecated
public interface IModDiscovererMixin {
    public List<ModCandidate> getCandidates();
}

