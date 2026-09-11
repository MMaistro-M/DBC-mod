/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  io.netty.buffer.ByteBuf
 */
package riskyken.armourersWorkshop.common.library;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import riskyken.armourersWorkshop.api.common.library.ILibraryFile;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;

public class LibraryFile
implements Comparable<LibraryFile>,
ILibraryFile {
    public final String fileName;
    public final String filePath;
    public final ISkinType skinType;
    public final boolean directory;

    public LibraryFile(String fileName, String filePath, ISkinType skinType) {
        this(fileName, filePath, skinType, false);
    }

    public LibraryFile(String fullFilePath) {
        fullFilePath = fullFilePath.replace("\\", "/");
        String[] splitFile = fullFilePath.split("/");
        this.fileName = splitFile[splitFile.length - 1];
        this.filePath = fullFilePath.substring(0, fullFilePath.length() - this.fileName.length());
        this.skinType = null;
        this.directory = false;
    }

    public LibraryFile(String fileName, String filePath, ISkinType skinType, boolean directory) {
        this.fileName = fileName.replace("\\", "/");
        this.filePath = filePath.replace("\\", "/");
        this.skinType = skinType;
        this.directory = directory;
    }

    public void writeToByteBuf(ByteBuf buf) {
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.fileName);
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.filePath);
        buf.writeBoolean(this.directory);
        buf.writeBoolean(this.skinType != null);
        if (this.skinType != null) {
            ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.skinType.getRegistryName());
        }
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.directory ? 1231 : 1237);
        result = 31 * result + (this.fileName == null ? 0 : this.fileName.hashCode());
        result = 31 * result + (this.filePath == null ? 0 : this.filePath.hashCode());
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
        LibraryFile other = (LibraryFile)obj;
        if (this.directory != other.directory) {
            return false;
        }
        if (this.fileName == null ? other.fileName != null : !this.fileName.equals(other.fileName)) {
            return false;
        }
        return !(this.filePath == null ? other.filePath != null : !this.filePath.equals(other.filePath));
    }

    public static LibraryFile readFromByteBuf(ByteBuf buf) {
        String fileName = ByteBufUtils.readUTF8String((ByteBuf)buf);
        String filePath = ByteBufUtils.readUTF8String((ByteBuf)buf);
        boolean directory = buf.readBoolean();
        ISkinType skinType = null;
        if (buf.readBoolean()) {
            String regName = ByteBufUtils.readUTF8String((ByteBuf)buf);
            skinType = SkinTypeRegistry.INSTANCE.getSkinTypeFromRegistryName(regName);
        }
        return new LibraryFile(fileName, filePath, skinType, directory);
    }

    @Override
    public String getFullName() {
        return this.filePath + this.fileName;
    }

    public boolean isDirectory() {
        return this.directory;
    }

    @Override
    public int compareTo(LibraryFile o) {
        if (this.isDirectory() & !o.isDirectory()) {
            return this.getFullName().compareTo(o.getFullName()) - 1000;
        }
        if (!this.isDirectory() & o.isDirectory()) {
            return this.getFullName().compareTo(o.getFullName()) + 1000;
        }
        return this.getFullName().compareTo(o.getFullName());
    }

    public String toString() {
        return "LibraryFile [fileName=" + this.fileName + ", filePath=" + this.filePath + ", skinType=" + this.skinType + ", directory=" + this.directory + "]";
    }
}

