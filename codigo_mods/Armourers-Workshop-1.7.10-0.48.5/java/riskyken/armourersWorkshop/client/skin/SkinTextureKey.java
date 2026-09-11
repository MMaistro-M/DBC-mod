/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 */
package riskyken.armourersWorkshop.client.skin;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;

@SideOnly(value=Side.CLIENT)
public class SkinTextureKey {
    private final int skinId;
    private final ISkinDye skinDye;
    private final byte[] extraColours;

    public SkinTextureKey(int skinId, ISkinDye skinDye, byte[] extraColours) {
        this.skinId = skinId;
        this.skinDye = skinDye;
        this.extraColours = extraColours;
    }

    public ISkinDye getSkinDye() {
        return this.skinDye;
    }

    public byte[] getExtraColours() {
        return this.extraColours;
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + Arrays.hashCode(this.extraColours);
        result = 31 * result + (this.skinDye == null ? 0 : this.skinDye.hashCode());
        result = 31 * result + this.skinId;
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        SkinTextureKey other = (SkinTextureKey)obj;
        if (!Arrays.equals(this.extraColours, other.extraColours)) {
            return false;
        }
        if (this.skinDye == null ? other.skinDye != null : !this.skinDye.equals(other.skinDye)) {
            return false;
        }
        return this.skinId == other.skinId;
    }
}

