/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers.data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kamkeel.npcs.util.VaultUtil;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.api.handler.IPlayerTradeData;
import noppes.npcs.api.handler.data.IAuctionClaim;
import noppes.npcs.config.ConfigMarket;
import noppes.npcs.controllers.data.AuctionClaim;
import noppes.npcs.controllers.data.PlayerData;

public class PlayerTradeData
implements IPlayerTradeData {
    public static final String NBT_KEY = "TradeData";
    private final PlayerData playerData;
    private long balance;
    private long lifetimeEarned;
    private long lifetimeSpent;
    private final CopyOnWriteArrayList<AuctionClaim> claims = new CopyOnWriteArrayList();

    public PlayerTradeData(PlayerData playerData) {
        this.playerData = playerData;
        this.balance = ConfigMarket.StartingBalance;
        this.lifetimeEarned = 0L;
        this.lifetimeSpent = 0L;
    }

    private boolean shouldUseVault() {
        return ConfigMarket.UseVault && VaultUtil.isEnabled();
    }

    private String getPlayerName() {
        if (this.playerData != null && this.playerData.player != null) {
            return this.playerData.player.func_70005_c_();
        }
        return this.playerData != null ? this.playerData.playername : null;
    }

    @Override
    public long getBalance() {
        String playerName;
        if (this.shouldUseVault() && (playerName = this.getPlayerName()) != null) {
            return (long)VaultUtil.getBalance(playerName);
        }
        return this.balance;
    }

    @Override
    public void setBalance(long balance) {
        String playerName;
        if (balance < 0L) {
            balance = 0L;
        }
        if (balance > ConfigMarket.MaxBalance) {
            balance = ConfigMarket.MaxBalance;
        }
        if (this.shouldUseVault() && (playerName = this.getPlayerName()) != null) {
            VaultUtil.setBalance(playerName, (double)balance);
            return;
        }
        this.balance = balance;
    }

    @Override
    public boolean deposit(long amount) {
        String playerName;
        if (amount <= 0L) {
            return false;
        }
        if (this.shouldUseVault() && (playerName = this.getPlayerName()) != null) {
            boolean success = VaultUtil.addMoney(playerName, (double)amount);
            if (success) {
                this.lifetimeEarned += amount;
            }
            return success;
        }
        long newBalance = this.balance + amount;
        if (newBalance < this.balance) {
            newBalance = ConfigMarket.MaxBalance;
        }
        if (newBalance > ConfigMarket.MaxBalance) {
            newBalance = ConfigMarket.MaxBalance;
        }
        this.balance = newBalance;
        this.lifetimeEarned += amount;
        return true;
    }

    @Override
    public boolean withdraw(long amount) {
        String playerName;
        if (amount <= 0L) {
            return false;
        }
        if (this.shouldUseVault() && (playerName = this.getPlayerName()) != null) {
            boolean success = VaultUtil.withdrawMoney(playerName, (double)amount);
            if (success) {
                this.lifetimeSpent += amount;
            }
            return success;
        }
        if (this.balance < amount) {
            return false;
        }
        this.balance -= amount;
        this.lifetimeSpent += amount;
        return true;
    }

    @Override
    public boolean canAfford(long amount) {
        String playerName;
        if (this.shouldUseVault() && (playerName = this.getPlayerName()) != null) {
            return VaultUtil.has(playerName, (double)amount);
        }
        return this.balance >= amount;
    }

    @Override
    public long getLifetimeEarned() {
        return this.lifetimeEarned;
    }

    @Override
    public long getLifetimeSpent() {
        return this.lifetimeSpent;
    }

    @Override
    public String formatBalance() {
        if (this.shouldUseVault()) {
            return VaultUtil.format(this.getBalance());
        }
        return PlayerTradeData.formatAmount(this.balance);
    }

    public static String formatAmount(long amount) {
        return String.format("%,d %s", amount, ConfigMarket.CurrencyName);
    }

    @Override
    public boolean isUsingVault() {
        return this.shouldUseVault();
    }

    public boolean isVaultConfigured() {
        return ConfigMarket.UseVault;
    }

    public synchronized void addClaim(AuctionClaim claim) {
        if (claim != null && !claim.claimed) {
            for (AuctionClaim existing : this.claims) {
                if (!existing.id.equals(claim.id)) continue;
                return;
            }
            this.claims.add(claim);
        }
    }

    public List<AuctionClaim> getClaimsList() {
        ArrayList<AuctionClaim> result = new ArrayList<AuctionClaim>();
        for (AuctionClaim claim : this.claims) {
            if (claim.claimed) continue;
            result.add(claim);
        }
        return result;
    }

    @Override
    public IAuctionClaim[] getClaims() {
        List<AuctionClaim> result = this.getClaimsList();
        return result.toArray(new IAuctionClaim[0]);
    }

    public synchronized AuctionClaim getClaimInternal(String claimId) {
        for (AuctionClaim claim : this.claims) {
            if (!claim.id.equals(claimId) || claim.claimed) continue;
            return claim;
        }
        return null;
    }

    @Override
    public IAuctionClaim getClaim(String claimId) {
        return this.getClaimInternal(claimId);
    }

    public synchronized boolean claimAndRemove(String claimId) {
        for (AuctionClaim claim : this.claims) {
            if (!claim.id.equals(claimId) || claim.claimed) continue;
            claim.claimed = true;
            this.claims.remove(claim);
            return true;
        }
        return false;
    }

    @Override
    public int getClaimCount() {
        int count = 0;
        for (AuctionClaim claim : this.claims) {
            if (claim.claimed) continue;
            ++count;
        }
        return count;
    }

    @Override
    public boolean hasClaims() {
        for (AuctionClaim claim : this.claims) {
            if (claim.claimed) continue;
            return true;
        }
        return false;
    }

    public int processExpiredClaims() {
        int expirationDays = ConfigMarket.ClaimExpirationDays;
        ArrayList<AuctionClaim> expired = new ArrayList<AuctionClaim>();
        for (AuctionClaim claim : this.claims) {
            if (claim.claimed || !claim.isExpired(expirationDays)) continue;
            expired.add(claim);
        }
        if (!expired.isEmpty()) {
            this.claims.removeAll(expired);
        }
        return expired.size();
    }

    public void readFromNBT(NBTTagCompound compound) {
        NBTTagCompound tradeData = compound.func_74775_l(NBT_KEY);
        if (tradeData == null || tradeData.func_82582_d()) {
            this.balance = ConfigMarket.StartingBalance;
            this.lifetimeEarned = 0L;
            this.lifetimeSpent = 0L;
            this.claims.clear();
            return;
        }
        this.balance = tradeData.func_74764_b("CurrencyBalance") ? tradeData.func_74763_f("CurrencyBalance") : ConfigMarket.StartingBalance;
        this.lifetimeEarned = tradeData.func_74763_f("CurrencyLifetimeEarned");
        this.lifetimeSpent = tradeData.func_74763_f("CurrencyLifetimeSpent");
        this.claims.clear();
        if (tradeData.func_74764_b("AuctionClaims")) {
            NBTTagList claimsList = tradeData.func_150295_c("AuctionClaims", 10);
            for (int i = 0; i < claimsList.func_74745_c(); ++i) {
                AuctionClaim claim = AuctionClaim.fromNBT(claimsList.func_150305_b(i));
                if (claim.claimed) continue;
                this.claims.add(claim);
            }
        }
    }

    public void writeToNBT(NBTTagCompound compound) {
        NBTTagCompound tradeData = new NBTTagCompound();
        tradeData.func_74772_a("CurrencyBalance", this.balance);
        tradeData.func_74772_a("CurrencyLifetimeEarned", this.lifetimeEarned);
        tradeData.func_74772_a("CurrencyLifetimeSpent", this.lifetimeSpent);
        NBTTagList claimsList = new NBTTagList();
        for (AuctionClaim claim : this.claims) {
            if (claim.claimed) continue;
            claimsList.func_74742_a((NBTBase)claim.writeToNBT(new NBTTagCompound()));
        }
        tradeData.func_74782_a("AuctionClaims", (NBTBase)claimsList);
        compound.func_74782_a(NBT_KEY, (NBTBase)tradeData);
    }
}

