/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package riskyken.armourersWorkshop.common.library;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import riskyken.armourersWorkshop.common.library.ILibraryCallback;
import riskyken.armourersWorkshop.common.library.LibraryFile;
import riskyken.armourersWorkshop.common.library.LibraryFileList;
import riskyken.armourersWorkshop.common.library.LibraryFileType;

public interface ILibraryManager {
    public void reloadLibrary();

    public void reloadLibrary(ILibraryCallback var1);

    public LibraryFileList getClientPublicFileList();

    public LibraryFileList getServerPublicFileList();

    public LibraryFileList getServerPrivateFileList(EntityPlayer var1);

    public void setFileList(ArrayList<LibraryFile> var1, LibraryFileType var2);

    public void addFileToListType(LibraryFile var1, LibraryFileType var2, EntityPlayer var3);

    public void removeFileFromListType(LibraryFile var1, LibraryFileType var2, EntityPlayer var3);

    public void syncLibraryWithPlayer(EntityPlayerMP var1);
}

