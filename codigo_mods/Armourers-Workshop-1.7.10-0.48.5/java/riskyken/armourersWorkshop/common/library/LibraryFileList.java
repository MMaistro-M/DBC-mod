/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedOutEvent
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package riskyken.armourersWorkshop.common.library;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.library.LibraryFile;
import riskyken.armourersWorkshop.common.library.LibraryFileType;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.server.MessageServerLibraryFileList;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.utils.ModLogger;

public class LibraryFileList {
    private final ArrayList<LibraryFile> fileList = new ArrayList();
    private final HashMap<String, ArrayList<LibraryFile>> typeListsMap = new HashMap();
    private final LibraryFileType listType;
    private final HashSet<UUID> syncedClients;

    public LibraryFileList(LibraryFileType listType) {
        this.listType = listType;
        this.syncedClients = new HashSet();
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @SubscribeEvent
    public void onClientDisconnected(PlayerEvent.PlayerLoggedOutEvent event) {
        HashSet<UUID> hashSet = this.syncedClients;
        synchronized (hashSet) {
            this.syncedClients.remove(event.player.func_110124_au());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void markDirty() {
        HashSet<UUID> hashSet = this.syncedClients;
        synchronized (hashSet) {
            this.syncedClients.clear();
        }
        this.updateTypeLists();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<LibraryFile> getFileList() {
        ArrayList<LibraryFile> returnList = new ArrayList<LibraryFile>();
        ArrayList<LibraryFile> arrayList = this.fileList;
        synchronized (arrayList) {
            returnList.addAll(this.fileList);
        }
        return returnList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setFileList(ArrayList<LibraryFile> fileList) {
        ArrayList<LibraryFile> arrayList = this.fileList;
        synchronized (arrayList) {
            this.fileList.clear();
            this.fileList.addAll(fileList);
        }
        this.markDirty();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addFileToList(LibraryFile file) {
        this.removeFileFromList(file);
        ArrayList<LibraryFile> arrayList = this.fileList;
        synchronized (arrayList) {
            this.fileList.add(file);
            Collections.sort(this.fileList);
        }
        this.markDirty();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void updateTypeLists() {
        ArrayList<ISkinType> skinTypes = SkinTypeRegistry.INSTANCE.getRegisteredSkinTypes();
        HashMap<String, ArrayList<LibraryFile>> hashMap = this.typeListsMap;
        synchronized (hashMap) {
            this.typeListsMap.clear();
            for (int i = 0; i < skinTypes.size(); ++i) {
                ISkinType skinType = skinTypes.get(i);
                ArrayList<LibraryFile> typeList = this.getFileListForSkinType(skinType);
                this.typeListsMap.put(skinType.getRegistryName(), typeList);
            }
            ModLogger.log(String.format("Created %d type lists for file list type %s.", this.typeListsMap.size(), this.listType.toString()));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private ArrayList<LibraryFile> getFileListForSkinType(ISkinType skinType) {
        ArrayList<LibraryFile> typeList = new ArrayList<LibraryFile>();
        ArrayList<LibraryFile> arrayList = this.fileList;
        synchronized (arrayList) {
            for (int i = 0; i < this.fileList.size(); ++i) {
                LibraryFile libraryFile = this.fileList.get(i);
                if (libraryFile.skinType != skinType) continue;
                typeList.add(libraryFile);
            }
        }
        return typeList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<LibraryFile> getCachedFileListForSkinType(ISkinType skinType) {
        HashMap<String, ArrayList<LibraryFile>> hashMap = this.typeListsMap;
        synchronized (hashMap) {
            return this.typeListsMap.get(skinType.getRegistryName());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeFileFromList(LibraryFile file) {
        ArrayList<LibraryFile> arrayList = this.fileList;
        synchronized (arrayList) {
            for (int i = 0; i < this.fileList.size(); ++i) {
                if (!this.fileList.get(i).getFullName().equals(file.getFullName())) continue;
                this.fileList.remove(i);
                this.markDirty();
                break;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int getFileCount() {
        int size = 0;
        ArrayList<LibraryFile> arrayList = this.fileList;
        synchronized (arrayList) {
            size = this.fileList.size();
        }
        return size;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void clearList() {
        ArrayList<LibraryFile> arrayList = this.fileList;
        synchronized (arrayList) {
            this.fileList.clear();
        }
        this.markDirty();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void syncFileListWithPlayer(EntityPlayerMP player) {
        HashSet<UUID> hashSet = this.syncedClients;
        synchronized (hashSet) {
            ArrayList<LibraryFile> fileList = this.getFileList();
            if (!this.syncedClients.contains(player.func_110124_au())) {
                this.syncedClients.add(player.func_110124_au());
                ModLogger.log(String.format("Sending file list type %s to %s", this.listType.toString(), player.getDisplayName()));
                MessageServerLibraryFileList message = new MessageServerLibraryFileList(fileList, this.listType);
                PacketHandler.networkWrapper.sendTo((IMessage)message, player);
            }
        }
    }
}

