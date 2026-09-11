/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.StringUtils
 *  org.apache.commons.io.IOUtils
 */
package riskyken.armourersWorkshop.common.library.global;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import net.minecraft.util.StringUtils;
import org.apache.commons.io.IOUtils;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.utils.ModLogger;
import riskyken.armourersWorkshop.utils.SkinIOUtils;

public final class SkinDownloader {
    public static void downloadSkin(CompletionService<Skin> skinCompletion, int serverId) {
        skinCompletion.submit(new DownloadSkinCallable(null, serverId));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Skin downloadSkin(String fileName, int serverId) {
        Skin skin = null;
        long startTime = System.currentTimeMillis();
        long maxRate = 10L;
        InputStream in = null;
        Object data = null;
        ByteArrayInputStream byteIn = null;
        try {
            in = new URL(String.format("http://plushie.moe/armourers_workshop/download-skin.php?skinid=%d&skinFileName=%s", serverId, fileName)).openStream();
            byte[] skinData = IOUtils.toByteArray((InputStream)in);
            byteIn = new ByteArrayInputStream(skinData);
            skin = SkinIOUtils.loadSkinFromStream(byteIn);
            IOUtils.closeQuietly((InputStream)in);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly(in);
        }
        long waitTime = maxRate - (System.currentTimeMillis() - startTime);
        if (waitTime > 0L) {
            try {
                Thread.sleep(waitTime);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (skin != null) {
            skin.serverId = serverId;
        } else {
            ModLogger.log(String.format("Failed to download skin: %s", fileName));
        }
        return skin;
    }

    public static class DownloadSkinCallable
    implements Callable<Skin> {
        private final String name;
        private final int serverId;

        public DownloadSkinCallable(String name, int serverId) {
            this.name = name;
            this.serverId = serverId;
        }

        @Override
        public Skin call() throws Exception {
            Skin skin = this.downloadSkin(this.name, this.serverId);
            return skin;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private Skin downloadSkin(String name, int serverId) throws InterruptedException {
            Skin skin;
            long maxRate;
            long startTime;
            block8: {
                startTime = System.currentTimeMillis();
                maxRate = 5L;
                String downloadUrl = "http://plushie.moe/armourers_workshop/";
                downloadUrl = !StringUtils.func_151246_b((String)name) ? downloadUrl + "skins/" + name : downloadUrl + "get-skin.php?skinid=" + String.valueOf(serverId);
                skin = null;
                InputStream in = null;
                ByteArrayInputStream byteIn = null;
                Object data = null;
                try {
                    in = new URL(downloadUrl).openStream();
                    byte[] skinData = IOUtils.toByteArray((InputStream)in);
                    byteIn = new ByteArrayInputStream(skinData);
                    skin = SkinIOUtils.loadSkinFromStream(byteIn);
                    IOUtils.closeQuietly((InputStream)byteIn);
                }
                catch (IOException e) {
                    e.printStackTrace();
                    break block8;
                }
                finally {
                    IOUtils.closeQuietly(byteIn);
                    IOUtils.closeQuietly((InputStream)in);
                }
                IOUtils.closeQuietly((InputStream)in);
            }
            long waitTime = maxRate - (System.currentTimeMillis() - startTime);
            if (waitTime > 0L) {
                // empty if block
            }
            if (skin != null) {
                skin.serverId = serverId;
            } else {
                ModLogger.log(String.format("Failed to download skin: %d", serverId));
            }
            return skin;
        }
    }
}

