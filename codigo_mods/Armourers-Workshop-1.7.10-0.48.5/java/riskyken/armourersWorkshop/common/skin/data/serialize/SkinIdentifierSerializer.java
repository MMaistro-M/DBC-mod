/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package riskyken.armourersWorkshop.common.skin.data.serialize;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinIdentifier;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.library.LibraryFile;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;

public class SkinIdentifierSerializer {
    private static final String TAG_SKIN_ID_DATA = "identifier";
    private static final String TAG_SKIN_LOCAL_ID = "localId";
    private static final String TAG_SKIN_LIBRARY_FILE = "libraryFile";
    private static final String TAG_SKIN_GLOBAL_ID = "globalId";
    private static final String TAG_SKIN_TYPE = "skinType";
    private static final String TAG_SKIN_OLD_ID = "skinId";

    public static void writeToCompound(ISkinIdentifier skinIdentifier, NBTTagCompound compound) {
        NBTTagCompound idDataCompound = new NBTTagCompound();
        idDataCompound.func_74768_a(TAG_SKIN_LOCAL_ID, skinIdentifier.getSkinLocalId());
        if (skinIdentifier.getSkinLibraryFile() != null) {
            idDataCompound.func_74778_a(TAG_SKIN_LIBRARY_FILE, skinIdentifier.getSkinLibraryFile().getFullName());
        }
        idDataCompound.func_74768_a(TAG_SKIN_GLOBAL_ID, skinIdentifier.getSkinGlobalId());
        if (skinIdentifier.getSkinType() != null) {
            idDataCompound.func_74778_a(TAG_SKIN_TYPE, skinIdentifier.getSkinType().getRegistryName());
        }
        compound.func_74782_a(TAG_SKIN_ID_DATA, (NBTBase)idDataCompound);
    }

    public static SkinIdentifier readFromCompound(NBTTagCompound compound) {
        int localId = 0;
        LibraryFile libraryFile = null;
        int globalId = 0;
        ISkinType skinType = null;
        NBTTagCompound idDataCompound = compound.func_74775_l(TAG_SKIN_ID_DATA);
        localId = idDataCompound.func_74762_e(TAG_SKIN_LOCAL_ID);
        if (idDataCompound.func_150297_b(TAG_SKIN_LIBRARY_FILE, 8)) {
            libraryFile = new LibraryFile(idDataCompound.func_74779_i(TAG_SKIN_LIBRARY_FILE));
        }
        globalId = idDataCompound.func_74762_e(TAG_SKIN_GLOBAL_ID);
        skinType = SkinTypeRegistry.INSTANCE.getSkinTypeFromRegistryName(idDataCompound.func_74779_i(TAG_SKIN_TYPE));
        if (compound.func_150297_b(TAG_SKIN_OLD_ID, 3)) {
            localId = compound.func_74762_e(TAG_SKIN_OLD_ID);
        }
        if (compound.func_150297_b(TAG_SKIN_TYPE, 8)) {
            skinType = SkinTypeRegistry.INSTANCE.getSkinTypeFromRegistryName(compound.func_74779_i(TAG_SKIN_TYPE));
        }
        return new SkinIdentifier(localId, libraryFile, globalId, skinType);
    }

    public static void writeToStream(ISkinIdentifier skinIdentifier, DataOutputStream stream) throws IOException {
        NBTTagCompound compound = new NBTTagCompound();
        SkinIdentifierSerializer.writeToCompound(skinIdentifier, compound);
        CompressedStreamTools.func_74799_a((NBTTagCompound)compound, (OutputStream)stream);
    }

    public static SkinIdentifier readFromStream(DataInputStream stream) throws IOException {
        NBTTagCompound compound = CompressedStreamTools.func_74796_a((InputStream)stream);
        return SkinIdentifierSerializer.readFromCompound(compound);
    }

    public static void writeToByteBuf(ISkinIdentifier skinIdentifier, ByteBuf buf) {
        NBTTagCompound compound = new NBTTagCompound();
        SkinIdentifierSerializer.writeToCompound(skinIdentifier, compound);
        ByteBufUtils.writeTag((ByteBuf)buf, (NBTTagCompound)compound);
    }

    public static SkinIdentifier readFromByteBuf(ByteBuf buf) {
        NBTTagCompound compound = ByteBufUtils.readTag((ByteBuf)buf);
        return SkinIdentifierSerializer.readFromCompound(compound);
    }
}

