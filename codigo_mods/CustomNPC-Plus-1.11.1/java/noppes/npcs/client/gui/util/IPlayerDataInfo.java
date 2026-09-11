/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util;

import java.util.Map;
import noppes.npcs.controllers.data.MagicData;

public interface IPlayerDataInfo {
    public void setQuestData(Map<String, Integer> var1, Map<String, Integer> var2, Map<String, Integer> var3);

    public void setDialogData(Map<String, Integer> var1, Map<String, Integer> var2);

    public void setTransportData(Map<String, Integer> var1, Map<String, Integer> var2);

    public void setBankData(Map<String, Integer> var1);

    public void setFactionData(Map<String, Integer> var1);

    public void setMagicData(MagicData var1);
}

