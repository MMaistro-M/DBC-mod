/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import noppes.npcs.api.handler.data.IAuctionClaim;

public interface IPlayerTradeData {
    public long getBalance();

    public void setBalance(long var1);

    public boolean deposit(long var1);

    public boolean withdraw(long var1);

    public boolean canAfford(long var1);

    public long getLifetimeEarned();

    public long getLifetimeSpent();

    public String formatBalance();

    public boolean isUsingVault();

    public int getClaimCount();

    public boolean hasClaims();

    public IAuctionClaim[] getClaims();

    public IAuctionClaim getClaim(String var1);
}

