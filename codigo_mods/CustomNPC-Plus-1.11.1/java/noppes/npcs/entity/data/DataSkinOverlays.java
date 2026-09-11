/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.entity.data;

import java.util.HashMap;
import java.util.Map;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.packets.data.PlayerUpdateSkinOverlaysPacket;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.api.ISkinOverlay;
import noppes.npcs.api.handler.IOverlayHandler;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.SkinOverlay;
import noppes.npcs.entity.EntityNPCInterface;

public class DataSkinOverlays
implements IOverlayHandler {
    public final Object parent;
    public HashMap<Integer, ISkinOverlay> overlayList = new HashMap();

    public DataSkinOverlays(Object parent) {
        this.parent = parent;
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        HashMap<Integer, SkinOverlay> skinOverlays = new HashMap<Integer, SkinOverlay>();
        NBTTagList skinOverlayList = nbtTagCompound.func_150295_c("SkinOverlayData", 10);
        for (int i = 0; i < skinOverlayList.func_74745_c(); ++i) {
            int tagID = skinOverlayList.func_150305_b(i).func_74762_e("SkinOverlayID");
            SkinOverlay skinOverlay = (SkinOverlay)SkinOverlay.overlayFromNBT(skinOverlayList.func_150305_b(i));
            skinOverlay.parent = this;
            skinOverlays.put(tagID, skinOverlay);
        }
        this.overlayList = skinOverlays;
        this.updateClient();
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
        NBTTagList overlayList = new NBTTagList();
        if (!this.overlayList.isEmpty()) {
            for (Map.Entry<Integer, ISkinOverlay> overlayData : this.overlayList.entrySet()) {
                NBTTagCompound compound = new NBTTagCompound();
                compound.func_74768_a("SkinOverlayID", overlayData.getKey().intValue());
                compound = ((SkinOverlay)overlayData.getValue()).writeToNBT(compound);
                overlayList.func_74742_a((NBTBase)compound);
            }
        }
        nbttagcompound.func_74782_a("SkinOverlayData", (NBTBase)overlayList);
        return nbttagcompound;
    }

    public void save() {
        if (this.parent instanceof PlayerData) {
            ((PlayerData)this.parent).save();
        }
    }

    public void updateClient() {
        if (this.parent != null) {
            if (this.parent instanceof PlayerData && ((PlayerData)this.parent).player != null) {
                NBTTagCompound compound = this.writeToNBT(new NBTTagCompound());
                ((PlayerData)this.parent).player.getEntityData().func_74782_a("SkinOverlayData", (NBTBase)compound.func_150295_c("SkinOverlayData", 10));
                PacketHandler.Instance.sendToAll(new PlayerUpdateSkinOverlaysPacket(((PlayerData)this.parent).player.func_70005_c_(), compound));
            } else if (this.parent instanceof EntityNPCInterface) {
                ((EntityNPCInterface)((Object)this.parent)).updateClient = true;
            }
        }
    }

    @Override
    public void add(int id, ISkinOverlay data) {
        if (this.overlayList.size() >= ConfigMain.SkinOverlayLimit) {
            return;
        }
        ((SkinOverlay)data).parent = this;
        this.overlayList.put(id, data);
        this.updateClient();
        this.save();
    }

    @Override
    public ISkinOverlay get(int id) {
        return this.overlayList.get(id);
    }

    @Override
    public boolean has(int id) {
        return this.overlayList.containsKey(id);
    }

    @Override
    public boolean remove(int id) {
        boolean removed = this.overlayList.remove(id) != null;
        this.updateClient();
        this.save();
        return removed;
    }

    @Override
    public int size() {
        return this.overlayList.size();
    }

    @Override
    public void clear() {
        this.overlayList.clear();
        this.updateClient();
        this.save();
    }
}

