/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.core.builder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.keyframe.BoneAnimation;
import software.bernie.geckolib3.core.keyframe.EventKeyFrame;
import software.bernie.geckolib3.core.keyframe.ParticleEventKeyFrame;

public class Animation
implements Serializable {
    private static final long serialVersionUID = 42L;
    public String animationName;
    public Double animationLength;
    public ILoopType loop = ILoopType.EDefaultLoopTypes.LOOP;
    public List<BoneAnimation> boneAnimations;
    public List<EventKeyFrame<String>> soundKeyFrames = new ArrayList<EventKeyFrame<String>>();
    public List<ParticleEventKeyFrame> particleKeyFrames = new ArrayList<ParticleEventKeyFrame>();
    public List<EventKeyFrame<String>> customInstructionKeyframes = new ArrayList<EventKeyFrame<String>>();
}

