/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.ModContainer
 *  cpw.mods.fml.common.discovery.ModCandidate
 *  cpw.mods.fml.common.discovery.ModDiscoverer
 */
package com.falsepattern.gasstation.mixins.mixin.dev;

import com.falsepattern.gasstation.mixins.IModDiscovererMixin;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.discovery.ModCandidate;
import cpw.mods.fml.common.discovery.ModDiscoverer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={Loader.class}, remap=false)
public abstract class LoaderMixin {
    @Redirect(method={"identifyMods"}, at=@At(value="INVOKE", target="Lcpw/mods/fml/common/discovery/ModDiscoverer;identifyMods()Ljava/util/List;"), require=1)
    private List<ModContainer> removeDuplicateFiles(ModDiscoverer instance) {
        List<ModCandidate> candidates = ((IModDiscovererMixin)instance).getCandidates();
        ArrayList<ModCandidate> uniques = new ArrayList<ModCandidate>();
        ArrayList<ModCandidate> dupes = new ArrayList<ModCandidate>();
        for (ModCandidate candidate : candidates) {
            File file = candidate.getModContainer().getAbsoluteFile().toPath().normalize().toFile();
            boolean isUnique = true;
            for (ModCandidate uniqueCandidate : uniques) {
                File uniqueFile = uniqueCandidate.getModContainer().getAbsoluteFile().toPath().normalize().toFile();
                if (!file.equals(uniqueFile)) continue;
                isUnique = false;
                break;
            }
            if (isUnique) {
                uniques.add(candidate);
                continue;
            }
            dupes.add(candidate);
        }
        candidates.removeAll(dupes);
        return instance.identifyMods();
    }
}

