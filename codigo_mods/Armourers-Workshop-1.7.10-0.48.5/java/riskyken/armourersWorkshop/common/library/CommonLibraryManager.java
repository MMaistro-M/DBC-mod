/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.library;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.common.library.ILibraryCallback;
import riskyken.armourersWorkshop.common.library.ILibraryManager;
import riskyken.armourersWorkshop.common.library.LibraryFile;
import riskyken.armourersWorkshop.common.library.LibraryFileList;
import riskyken.armourersWorkshop.common.library.LibraryFileType;
import riskyken.armourersWorkshop.common.library.LibraryHelper;
import riskyken.armourersWorkshop.utils.ModLogger;
import riskyken.armourersWorkshop.utils.SkinIOUtils;

public class CommonLibraryManager
implements ILibraryManager {
    private final LibraryFileList serverPublicFiles = new LibraryFileList(LibraryFileType.SERVER_PUBLIC);
    private final HashMap<UUID, LibraryFileList> serverPrivateFiles = new HashMap();
    private boolean loadingLibaray = false;

    @Override
    public void reloadLibrary() {
        this.reloadLibrary(null);
    }

    @Override
    public void reloadLibrary(ILibraryCallback callback) {
        if (!this.loadingLibaray) {
            this.loadingLibaray = true;
            new Thread((Runnable)new LibraryLoader(this, callback), "Armourer's Workshop library thread.").start();
        } else {
            ModLogger.log("Library is already loading.");
        }
    }

    private void finishedLoading() {
        this.loadingLibaray = false;
    }

    private int loadPublicFiles() {
        File directory = SkinIOUtils.getSkinLibraryDirectory();
        ArrayList<LibraryFile> fileList = LibraryHelper.getSkinFilesInDirectory(directory, true);
        this.setFileList(fileList, LibraryFileType.SERVER_PUBLIC);
        return fileList.size();
    }

    private int loadPrivateFiles() {
        int count = 0;
        File directory = SkinIOUtils.getSkinLibraryDirectory();
        if (!(directory = new File(directory, "private")).exists()) {
            directory.mkdirs();
        }
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; ++i) {
            File file = files[i];
            if (!file.isDirectory()) continue;
            try {
                UUID playerId = UUID.fromString(file.getName());
                LibraryFileList fileList = new LibraryFileList(LibraryFileType.SERVER_PRIVATE);
                ArrayList<LibraryFile> privateFileList = LibraryHelper.getSkinFilesInDirectory(file, true);
                fileList.setFileList(privateFileList);
                this.serverPrivateFiles.put(playerId, fileList);
                count += fileList.getFileCount();
                continue;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                // empty catch block
            }
        }
        return count;
    }

    @Override
    public LibraryFileList getClientPublicFileList() {
        ModLogger.log(Level.WARN, "Tried to get client file list from the server library manager.");
        return null;
    }

    @Override
    public LibraryFileList getServerPublicFileList() {
        return this.serverPublicFiles;
    }

    @Override
    public LibraryFileList getServerPrivateFileList(EntityPlayer player) {
        return this.serverPrivateFiles.get(player.func_110124_au());
    }

    @Override
    public void setFileList(ArrayList<LibraryFile> fileList, LibraryFileType listType) {
        switch (listType) {
            case LOCAL: {
                ModLogger.log(Level.WARN, "Tried to set the file list in the server library manager.");
                break;
            }
            case SERVER_PUBLIC: {
                this.serverPublicFiles.setFileList(fileList);
                break;
            }
        }
    }

    @Override
    public void addFileToListType(LibraryFile file, LibraryFileType listType, EntityPlayer player) {
        switch (listType) {
            case LOCAL: {
                ModLogger.log(Level.WARN, "Tried to add a file in the server library manager.");
                break;
            }
            case SERVER_PUBLIC: {
                this.serverPublicFiles.addFileToList(file);
                break;
            }
            case SERVER_PRIVATE: {
                LibraryFileList fileList = this.serverPrivateFiles.get(player.func_110124_au());
                if (fileList != null) {
                    fileList.addFileToList(file);
                    break;
                }
                fileList = new LibraryFileList(LibraryFileType.SERVER_PRIVATE);
                fileList.addFileToList(file);
                this.serverPrivateFiles.put(player.func_110124_au(), fileList);
            }
        }
    }

    @Override
    public void removeFileFromListType(LibraryFile file, LibraryFileType listType, EntityPlayer player) {
        switch (listType) {
            case LOCAL: {
                ModLogger.log(Level.WARN, "Tried to remove a file in the server library manager.");
                break;
            }
            case SERVER_PUBLIC: {
                this.serverPublicFiles.removeFileFromList(file);
                break;
            }
            case SERVER_PRIVATE: {
                LibraryFileList fileList = this.serverPrivateFiles.get(player.func_110124_au());
                if (fileList != null) {
                    fileList.removeFileFromList(file);
                    break;
                }
                fileList = new LibraryFileList(LibraryFileType.SERVER_PRIVATE);
                fileList.removeFileFromList(file);
                this.serverPrivateFiles.put(player.func_110124_au(), fileList);
            }
        }
    }

    @Override
    public void syncLibraryWithPlayer(EntityPlayerMP player) {
        this.serverPublicFiles.syncFileListWithPlayer(player);
        LibraryFileList privateList = this.serverPrivateFiles.get(player.func_110124_au());
        if (privateList != null) {
            privateList.syncFileListWithPlayer(player);
        }
    }

    private static class LibraryLoader
    implements Runnable {
        private CommonLibraryManager libraryManager;
        private ILibraryCallback callback;

        public LibraryLoader(CommonLibraryManager libraryManager, ILibraryCallback callback) {
            this.libraryManager = libraryManager;
            this.callback = callback;
        }

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            ModLogger.log("Loading public library skins");
            int publicFileCount = this.libraryManager.loadPublicFiles();
            int endTime = (int)(System.currentTimeMillis() - startTime);
            ModLogger.log(String.format("Finished loading %d server public library skins in %d ms", publicFileCount, endTime));
            ModLogger.log("Loading private library skins");
            startTime = System.currentTimeMillis();
            int privateFileCount = this.libraryManager.loadPrivateFiles();
            endTime = (int)(System.currentTimeMillis() - startTime);
            ModLogger.log(String.format("Finished loading %d server private library skins in %d ms", privateFileCount, endTime));
            this.libraryManager.finishedLoading();
            if (this.callback != null) {
                this.callback.libraryReloaded(this.libraryManager);
            }
        }
    }
}

