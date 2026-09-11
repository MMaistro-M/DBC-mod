/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.attribute.requirement;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import kamkeel.npcs.controllers.data.attribute.requirement.IRequirementChecker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class RequirementCheckerRegistry {
    private static final Map<String, IRequirementChecker> checkers = new HashMap<String, IRequirementChecker>();

    public static void registerChecker(IRequirementChecker checker) {
        if (!checkers.containsKey(checker.getKey())) {
            checkers.put(checker.getKey(), checker);
        }
    }

    public static boolean checkRequirements(EntityPlayer player, NBTTagCompound nbt) {
        for (Map.Entry<String, IRequirementChecker> entry : checkers.entrySet()) {
            String reqKey = entry.getKey();
            if (!nbt.func_74764_b(reqKey) || entry.getValue().check(player, nbt)) continue;
            return false;
        }
        return true;
    }

    public static Set<String> getAllKeys() {
        return checkers.keySet();
    }

    public static IRequirementChecker getChecker(String key) {
        return checkers.get(key);
    }
}

