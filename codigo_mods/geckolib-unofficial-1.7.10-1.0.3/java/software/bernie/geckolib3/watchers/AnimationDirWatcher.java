/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.watchers;

import java.io.File;
import software.bernie.geckolib3.resource.AnimationLibrary;
import software.bernie.geckolib3.watchers.AbstractDirWatcher;

public class AnimationDirWatcher
extends AbstractDirWatcher {
    public AnimationDirWatcher(File file) {
        super(file.toPath(), true);
    }

    @Override
    public void processCreate(File path) {
        String name = path.getName();
        if (!name.endsWith(".json")) {
            return;
        }
        AnimationLibrary.instance.storeModel(path);
    }

    @Override
    public void processDelete(File path) {
        String name = path.getName();
        if (!name.endsWith(".json")) {
            return;
        }
        AnimationLibrary.instance.remove(path);
    }

    @Override
    public void processModify(File path) {
        String name = path.getName();
        if (!name.endsWith(".json")) {
            return;
        }
        AnimationLibrary.instance.storeModel(path);
    }
}

