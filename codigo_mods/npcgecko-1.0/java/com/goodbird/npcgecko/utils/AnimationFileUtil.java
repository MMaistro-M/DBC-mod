/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package com.goodbird.npcgecko.utils;

import java.util.List;
import java.util.Vector;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.builder.Animation;
import software.bernie.geckolib3.file.AnimationFile;
import software.bernie.geckolib3.resource.GeckoLibCache;

public class AnimationFileUtil {
    public static List<String> getAnimationList(String animFileName) {
        Vector<String> list = new Vector<String>();
        AnimationFile file = GeckoLibCache.getInstance().getAnimations().get(new ResourceLocation(animFileName));
        if (file != null) {
            for (Animation anim : file.getAllAnimations()) {
                list.add(anim.animationName);
            }
        }
        return list;
    }

    public static List<String> getAnimationFileList() {
        Vector<String> list = new Vector<String>();
        for (ResourceLocation resLoc : GeckoLibCache.getInstance().getAnimations().keySet()) {
            list.add(resLoc.toString());
        }
        return list;
    }
}

