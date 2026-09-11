/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.input.Keyboard
 */
package invtweaks;

import invtweaks.InvTweaks;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

public class InvTweaksShortcutMapping {
    private static final Logger log = InvTweaks.log;
    private List<Integer> keysToHold = new LinkedList<Integer>();

    public InvTweaksShortcutMapping(int keyCode) {
        this.keysToHold.add(keyCode);
    }

    public InvTweaksShortcutMapping(int ... keyCodes) {
        for (int keyCode : keyCodes) {
            this.keysToHold.add(keyCode);
        }
    }

    public InvTweaksShortcutMapping(String keyName) {
        this(new String[]{keyName});
    }

    public InvTweaksShortcutMapping(String ... keyNames) {
        for (String keyName : keyNames) {
            keyName = keyName.trim().replace("KEY_", "").replace("ALT", "MENU");
            this.keysToHold.add(Keyboard.getKeyIndex((String)keyName));
        }
    }

    public boolean isTriggered(Map<Integer, Boolean> pressedKeys) {
        for (Integer keyToHold : this.keysToHold) {
            if (!(keyToHold != 29 ? pressedKeys.get(keyToHold) == false : pressedKeys.get(keyToHold) == false || Keyboard.isKeyDown((int)184))) continue;
            return false;
        }
        return true;
    }

    public List<Integer> getKeyCodes() {
        return this.keysToHold;
    }
}

