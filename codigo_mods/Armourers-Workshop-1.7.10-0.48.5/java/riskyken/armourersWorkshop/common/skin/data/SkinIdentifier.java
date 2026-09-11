/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.skin.data;

import riskyken.armourersWorkshop.api.common.library.ILibraryFile;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinIdentifier;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.skin.data.Skin;

public class SkinIdentifier
implements ISkinIdentifier {
    private static final String TAG_SKIN_ID_DATA = "identifier";
    private static final String TAG_SKIN_LOCAL_ID = "localId";
    private static final String TAG_SKIN_LIBRARY_FILE = "libraryFile";
    private static final String TAG_SKIN_GLOBAL_ID = "globalId";
    private static final String TAG_SKIN_TYPE = "skinType";
    private static final String TAG_SKIN_OLD_ID = "skinId";
    private int localId;
    private ILibraryFile libraryFile;
    private int globalId;
    private ISkinType skinType;

    public SkinIdentifier() {
        this.localId = 0;
        this.libraryFile = null;
        this.globalId = 0;
        this.skinType = null;
    }

    public SkinIdentifier(int localId, ILibraryFile libraryFile, int globalId, ISkinType skinType) {
        this.localId = localId;
        this.libraryFile = libraryFile;
        this.globalId = globalId;
        this.skinType = skinType;
    }

    public SkinIdentifier(Skin skin) {
        this(skin.lightHash(), null, 0, skin.getSkinType());
    }

    public SkinIdentifier(ISkinIdentifier identifier) {
        this(identifier.getSkinLocalId(), identifier.getSkinLibraryFile(), identifier.getSkinGlobalId(), identifier.getSkinType());
    }

    @Override
    public boolean hasLocalId() {
        return this.localId != 0;
    }

    @Override
    public boolean hasLibraryFile() {
        return this.libraryFile != null;
    }

    @Override
    public boolean hasGlobalId() {
        return this.globalId != 0;
    }

    @Override
    public int getSkinLocalId() {
        return this.localId;
    }

    @Override
    public ILibraryFile getSkinLibraryFile() {
        return this.libraryFile;
    }

    @Override
    public int getSkinGlobalId() {
        return this.globalId;
    }

    @Override
    public ISkinType getSkinType() {
        return this.skinType;
    }

    public String toString() {
        return "SkinIdentifier [localId=" + this.localId + ", libraryFile=" + this.libraryFile + ", globalId=" + this.globalId + "]";
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + this.globalId;
        result = 31 * result + (this.libraryFile == null ? 0 : this.libraryFile.hashCode());
        result = 31 * result + this.localId;
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
        SkinIdentifier other = (SkinIdentifier)obj;
        if (this.globalId != other.globalId) {
            return false;
        }
        if (this.libraryFile == null ? other.libraryFile != null : !this.libraryFile.equals(other.libraryFile)) {
            return false;
        }
        return this.localId == other.localId;
    }

    public static enum SkinIdentifierType {
        LOCAL_DATABASE,
        LOCAL_FILE,
        GLOBAL_DATABASE;

    }
}

