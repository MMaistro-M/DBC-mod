/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers;

import kamkeel.npcs.util.VaultUtil;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.config.ConfigMarket;

public class AuctionConfigSync {
    public static NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74757_a("AuctionEnabled", ConfigMarket.AuctionEnabled);
        compound.func_74772_a("ListingFee", ConfigMarket.ListingFee);
        compound.func_74778_a("CurrencyName", AuctionConfigSync.getEffectiveCurrencyName());
        compound.func_74768_a("AuctionDurationHours", ConfigMarket.AuctionDurationHours);
        compound.func_74780_a("MinBidIncrement", ConfigMarket.MinBidIncrementPercent);
        compound.func_74768_a("MaxActiveListings", ConfigMarket.DefaultMaxTrades);
        compound.func_74768_a("ClaimExpirationDays", ConfigMarket.ClaimExpirationDays);
        return compound;
    }

    public static String getEffectiveCurrencyName() {
        String vaultName;
        if (ConfigMarket.UseVault && VaultUtil.isEnabled() && (vaultName = VaultUtil.getCurrencyNamePlural()) != null && !vaultName.isEmpty()) {
            return vaultName;
        }
        return ConfigMarket.CurrencyName;
    }
}

