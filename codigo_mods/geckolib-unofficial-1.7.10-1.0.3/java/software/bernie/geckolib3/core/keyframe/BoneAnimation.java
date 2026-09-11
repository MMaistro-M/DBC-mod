/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.core.keyframe;

import com.eliotlash.mclib.math.IValue;
import java.io.Serializable;
import software.bernie.geckolib3.core.keyframe.KeyFrame;
import software.bernie.geckolib3.core.keyframe.VectorKeyFrameList;

public class BoneAnimation
implements Serializable {
    private static final long serialVersionUID = 42L;
    public String boneName;
    public VectorKeyFrameList<KeyFrame<IValue>> rotationKeyFrames;
    public VectorKeyFrameList<KeyFrame<IValue>> positionKeyFrames;
    public VectorKeyFrameList<KeyFrame<IValue>> scaleKeyFrames;
}

