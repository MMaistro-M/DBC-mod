/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.Launch
 */
package makamys.mixingasm.api;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.launchwrapper.Launch;

public class TransformerInclusions {
    private static final String INCLUSION_LIST_BLACKBOARD_KEY = "mixingasm.transformerInclusionList";

    public static List<String> getTransformerInclusionList() {
        ArrayList list = (ArrayList)Launch.blackboard.get(INCLUSION_LIST_BLACKBOARD_KEY);
        if (list == null) {
            list = new ArrayList();
            Launch.blackboard.put(INCLUSION_LIST_BLACKBOARD_KEY, list);
        }
        return list;
    }
}

