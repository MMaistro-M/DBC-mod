/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.network.FMLNetworkEvent$ClientDisconnectionFromServerEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package riskyken.armourersWorkshop.client.library;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.File;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import riskyken.armourersWorkshop.common.library.ILibraryCallback;
import riskyken.armourersWorkshop.common.library.ILibraryManager;
import riskyken.armourersWorkshop.common.library.LibraryFile;
import riskyken.armourersWorkshop.common.library.LibraryFileList;
import riskyken.armourersWorkshop.common.library.LibraryFileType;
import riskyken.armourersWorkshop.common.library.LibraryHelper;
import riskyken.armourersWorkshop.utils.ModLogger;
import riskyken.armourersWorkshop.utils.SkinIOUtils;

@SideOnly(value=Side.CLIENT)
public class ClientLibraryManager
implements ILibraryManager {
    private final LibraryFileList serverPublicFiles = new LibraryFileList(LibraryFileType.SERVER_PUBLIC);
    private final LibraryFileList serverPrivateFiles = new LibraryFileList(LibraryFileType.SERVER_PRIVATE);
    private final LibraryFileList clientFiles = new LibraryFileList(LibraryFileType.LOCAL);
    private boolean loadingLibaray = false;

    public ClientLibraryManager() {
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    @SubscribeEvent
    public void onClientDisconnected(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        this.serverPublicFiles.clearList();
        this.serverPrivateFiles.clearList();
    }

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
            ModLogger.log("Library is already loading client.");
        }
    }

    private void finishedLoading() {
        this.loadingLibaray = false;
    }

    @Override
    public LibraryFileList getClientPublicFileList() {
        return this.clientFiles;
    }

    @Override
    public LibraryFileList getServerPublicFileList() {
        return this.serverPublicFiles;
    }

    @Override
    public LibraryFileList getServerPrivateFileList(EntityPlayer player) {
        return this.serverPrivateFiles;
    }

    @Override
    public void setFileList(ArrayList<LibraryFile> fileList, LibraryFileType listType) {
        switch (listType) {
            case LOCAL: {
                this.clientFiles.setFileList(fileList);
                break;
            }
            case SERVER_PUBLIC: {
                this.serverPublicFiles.setFileList(fileList);
                break;
            }
            case SERVER_PRIVATE: {
                this.serverPrivateFiles.setFileList(fileList);
            }
        }
    }

    @Override
    public void addFileToListType(LibraryFile file, LibraryFileType listType, EntityPlayer player) {
        switch (listType) {
            case LOCAL: {
                this.clientFiles.addFileToList(file);
                break;
            }
            case SERVER_PUBLIC: {
                this.serverPublicFiles.addFileToList(file);
                break;
            }
            case SERVER_PRIVATE: {
                this.serverPrivateFiles.addFileToList(file);
            }
        }
    }

    @Override
    public void removeFileFromListType(LibraryFile file, LibraryFileType listType, EntityPlayer player) {
        switch (listType) {
            case LOCAL: {
                this.clientFiles.removeFileFromList(file);
                break;
            }
            case SERVER_PUBLIC: {
                this.serverPublicFiles.removeFileFromList(file);
                break;
            }
            case SERVER_PRIVATE: {
                this.serverPrivateFiles.removeFileFromList(file);
            }
        }
    }

    @Override
    public void syncLibraryWithPlayer(EntityPlayerMP player) {
    }

    private static class LibraryLoader
    implements Runnable {
        private ClientLibraryManager libraryManager;
        private ILibraryCallback callback;

        public LibraryLoader(ClientLibraryManager libraryManager, ILibraryCallback callback) {
            this.libraryManager = libraryManager;
            this.callback = callback;
        }

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            ModLogger.log("Loading library skins");
            File directory = SkinIOUtils.getSkinLibraryDirectory();
            ArrayList<LibraryFile> fileList = LibraryHelper.getSkinFilesInDirectory(directory, true);
            this.libraryManager.setFileList(fileList, LibraryFileType.LOCAL);
            ModLogger.log(String.format("Finished loading %d client library skins in %d ms", this.libraryManager.clientFiles.getFileCount(), System.currentTimeMillis() - startTime));
            this.libraryManager.finishedLoading();
            if (this.callback != null) {
                this.callback.libraryReloaded(this.libraryManager);
            }
        }
    }
}

