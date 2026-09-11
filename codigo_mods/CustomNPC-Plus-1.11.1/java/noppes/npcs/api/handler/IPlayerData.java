/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import noppes.npcs.api.ability.IPlayerAbilityData;
import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.handler.IPlayerBankData;
import noppes.npcs.api.handler.IPlayerDialogData;
import noppes.npcs.api.handler.IPlayerFactionData;
import noppes.npcs.api.handler.IPlayerItemGiverData;
import noppes.npcs.api.handler.IPlayerMailData;
import noppes.npcs.api.handler.IPlayerQuestData;
import noppes.npcs.api.handler.IPlayerTradeData;
import noppes.npcs.api.handler.IPlayerTransportData;

public interface IPlayerData {
    public void setCompanion(ICustomNpc var1);

    public ICustomNpc getCompanion();

    public boolean hasCompanion();

    public int getCompanionID();

    public IPlayerDialogData getDialogData();

    public IPlayerBankData getBankData();

    public IPlayerQuestData getQuestData();

    public IPlayerTransportData getTransportData();

    public IPlayerFactionData getFactionData();

    public IPlayerItemGiverData getItemGiverData();

    public IPlayerMailData getMailData();

    public IPlayerTradeData getTradeData();

    public IPlayerAbilityData getAbilityData();

    public void save();
}

