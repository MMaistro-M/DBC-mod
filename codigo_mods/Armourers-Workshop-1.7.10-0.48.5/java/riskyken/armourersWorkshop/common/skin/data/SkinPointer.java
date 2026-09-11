/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package riskyken.armourersWorkshop.common.skin.data;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinDye;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.common.skin.data.serialize.SkinIdentifierSerializer;

public class SkinPointer
implements ISkinPointer {
    public static final String TAG_SKIN_DATA = "armourersWorkshop";
    private static final String TAG_SKIN_LOCK = "lock";
    private SkinIdentifier identifier;
    public boolean lockSkin;
    public SkinDye skinDye;

    public SkinPointer() {
        this.skinDye = new SkinDye();
        this.identifier = new SkinIdentifier(0, null, 0, null);
    }

    public SkinPointer(Skin skin) {
        this(new SkinIdentifier(skin.lightHash(), null, 0, skin.getSkinType()));
    }

    public SkinPointer(ISkinPointer skinPointer) {
        this.identifier = new SkinIdentifier(skinPointer.getIdentifier());
        this.lockSkin = false;
        this.skinDye = new SkinDye(skinPointer.getSkinDye());
    }

    public SkinPointer(SkinIdentifier identifier) {
        this.identifier = identifier;
        this.lockSkin = false;
        this.skinDye = new SkinDye();
    }

    public SkinPointer(SkinIdentifier identifier, SkinDye skinDye) {
        this.identifier = identifier;
        this.lockSkin = false;
        this.skinDye = skinDye;
    }

    public SkinPointer(SkinIdentifier identifier, boolean lockSkin) {
        this.identifier = identifier;
        this.lockSkin = lockSkin;
        this.skinDye = new SkinDye();
    }

    public SkinPointer(SkinIdentifier identifier, ISkinDye skinDye, boolean lockSkin) {
        this.identifier = identifier;
        this.lockSkin = lockSkin;
        this.skinDye = new SkinDye(skinDye);
    }

    @Override
    public SkinIdentifier getIdentifier() {
        return this.identifier;
    }

    @Override
    @Deprecated
    public int getSkinId() {
        return this.identifier.getSkinLocalId();
    }

    @Override
    @Deprecated
    public ISkinType getSkinType() {
        return this.identifier.getSkinType();
    }

    @Override
    public ISkinDye getSkinDye() {
        return this.skinDye;
    }

    public void readFromCompound(NBTTagCompound compound) {
        this.readFromCompound(compound, TAG_SKIN_DATA);
    }

    public void readFromCompound(NBTTagCompound compound, String tag) {
        NBTTagCompound skinDataCompound = compound.func_74775_l(tag);
        this.identifier = SkinIdentifierSerializer.readFromCompound(skinDataCompound);
        this.lockSkin = skinDataCompound.func_74767_n(TAG_SKIN_LOCK);
        this.skinDye.readFromCompound(skinDataCompound);
    }

    public void writeToCompound(NBTTagCompound compound) {
        this.writeToCompound(compound, TAG_SKIN_DATA);
    }

    public void writeToCompound(NBTTagCompound compound, String tag) {
        NBTTagCompound skinDataCompound = new NBTTagCompound();
        SkinIdentifierSerializer.writeToCompound(this.identifier, skinDataCompound);
        skinDataCompound.func_74757_a(TAG_SKIN_LOCK, this.lockSkin);
        this.skinDye.writeToCompound(skinDataCompound);
        compound.func_74782_a(tag, (NBTBase)skinDataCompound);
    }
}

