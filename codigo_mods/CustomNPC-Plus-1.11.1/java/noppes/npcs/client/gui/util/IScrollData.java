/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util;

import java.util.HashMap;
import java.util.Vector;
import noppes.npcs.constants.EnumScrollData;

public interface IScrollData {
    public void setData(Vector<String> var1, HashMap<String, Integer> var2, EnumScrollData var3);

    public void setSelected(String var1);
}

