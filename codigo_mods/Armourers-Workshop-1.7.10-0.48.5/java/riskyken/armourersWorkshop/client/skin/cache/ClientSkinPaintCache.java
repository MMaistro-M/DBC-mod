/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 */
package riskyken.armourersWorkshop.client.skin.cache;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashSet;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.client.skin.SkinModelTexture;
import riskyken.armourersWorkshop.client.skin.SkinTextureKey;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;
import riskyken.armourersWorkshop.common.data.ExpiringHashMap;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinTexture;

@SideOnly(value=Side.CLIENT)
public class ClientSkinPaintCache
implements ExpiringHashMap.IExpiringMapCallback,
Runnable {
    public static ClientSkinPaintCache INSTANCE = new ClientSkinPaintCache();
    private final ExpiringHashMap<SkinTextureKey, SkinModelTexture> textureMap = new ExpiringHashMap(1000 * ConfigHandlerClient.clientTextureCacheTime, this);
    private final HashSet<TextureGenInfo> requestSet = new HashSet();
    private final ArrayList<TextureGenInfo> requestList = new ArrayList();
    private volatile Thread textureGenThread = new Thread((Runnable)this, "Texture Gen Thread");

    public ClientSkinPaintCache() {
        this.textureGenThread.start();
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    public SkinModelTexture getTextureForSkin(Skin skin, ISkinDye skinDye, byte[] extraColours) {
        if (extraColours == null) {
            extraColours = new byte[]{127, 127, 127, 127, 127, 127};
        }
        SkinTextureKey cmk = new SkinTextureKey(skin.lightHash(), skinDye, extraColours);
        return this.getTextureForSkin(skin, cmk);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public SkinModelTexture getTextureForSkin(Skin skin, SkinTextureKey cmk) {
        SkinModelTexture st = this.textureMap.get(cmk);
        if (st != null) {
            return st;
        }
        TextureGenInfo tgi = new TextureGenInfo(skin, cmk);
        HashSet<TextureGenInfo> hashSet = this.requestSet;
        synchronized (hashSet) {
            if (!this.requestSet.contains(tgi)) {
                this.requestSet.add(tgi);
                ArrayList<TextureGenInfo> arrayList = this.requestList;
                synchronized (arrayList) {
                    this.requestList.add(tgi);
                }
            }
        }
        return skin.skinModelTexture;
    }

    public int size() {
        return this.textureMap.size();
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            this.textureMap.cleanupCheck();
        }
    }

    public void itemExpired(Object mapItem) {
        if (mapItem != null && mapItem instanceof SkinTexture) {
            ((SkinModelTexture)((Object)mapItem)).func_147631_c();
        }
    }

    protected void finalize() throws Throwable {
        this.textureGenThread = null;
        super.finalize();
    }

    @Override
    public void run() {
        Thread thisThread = Thread.currentThread();
        while (this.textureGenThread == thisThread) {
            try {
                Thread.sleep(100L);
                this.genTextures();
            }
            catch (InterruptedException interruptedException) {}
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void genTextures() {
        SkinModelTexture smt = null;
        TextureGenInfo tgi = null;
        HashSet<TextureGenInfo> hashSet = this.requestList;
        synchronized (hashSet) {
            if (this.requestList.size() > 0) {
                tgi = this.requestList.get(this.requestList.size() - 1);
                this.requestList.remove(this.requestList.size() - 1);
                smt = new SkinModelTexture();
                smt.createTextureForColours(tgi.skin, tgi.cmk);
            }
        }
        if (smt != null && tgi != null) {
            hashSet = this.textureMap;
            synchronized (hashSet) {
                this.textureMap.put(tgi.cmk, smt);
            }
            hashSet = this.requestSet;
            synchronized (hashSet) {
                this.requestSet.remove(tgi);
            }
        }
    }

    protected class TextureGenInfo {
        public Skin skin;
        public SkinTextureKey cmk;

        public TextureGenInfo(Skin skin, SkinTextureKey cmk) {
            this.skin = skin;
            this.cmk = cmk;
        }

        public int hashCode() {
            int prime = 31;
            int result = 1;
            result = 31 * result + (this.cmk == null ? 0 : this.cmk.hashCode());
            result = 31 * result + (this.skin == null ? 0 : this.skin.hashCode());
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
            TextureGenInfo other = (TextureGenInfo)obj;
            if (this.cmk == null ? other.cmk != null : !this.cmk.equals(other.cmk)) {
                return false;
            }
            return !(this.skin == null ? other.skin != null : !this.skin.equals(other.skin));
        }
    }
}

