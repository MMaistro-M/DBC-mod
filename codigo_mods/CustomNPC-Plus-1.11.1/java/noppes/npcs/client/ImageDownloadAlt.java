/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.IImageBuffer
 *  net.minecraft.client.renderer.texture.SimpleTexture
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.util.ResourceLocation
 *  org.apache.commons.io.FileUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package noppes.npcs.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.config.ConfigClient;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SideOnly(value=Side.CLIENT)
public class ImageDownloadAlt
extends SimpleTexture {
    private static final Logger logger = LogManager.getLogger();
    private static final AtomicInteger threadDownloadCounter = new AtomicInteger(0);
    private final File cacheFile;
    private final String imageUrl;
    private final IImageBuffer imageBuffer;
    private BufferedImage bufferedImage;
    private Thread imageThread;
    private boolean textureUploaded;

    public ImageDownloadAlt(File file, String url, ResourceLocation resource, IImageBuffer buffer) {
        super(resource);
        this.cacheFile = file;
        this.imageUrl = url;
        this.imageBuffer = buffer;
    }

    private void checkTextureUploaded() {
        if (!this.textureUploaded && this.bufferedImage != null) {
            if (this.field_110568_b != null) {
                this.func_147631_c();
            }
            TextureUtil.func_110987_a((int)super.func_110552_b(), (BufferedImage)this.bufferedImage);
            this.textureUploaded = true;
        }
    }

    public int func_110552_b() {
        this.checkTextureUploaded();
        return super.func_110552_b();
    }

    public void setBufferedImage(BufferedImage p_147641_1_) {
        this.bufferedImage = p_147641_1_;
        if (this.imageBuffer != null) {
            this.imageBuffer.func_152634_a();
        }
    }

    public BufferedImage getBufferedImage() {
        return this.bufferedImage;
    }

    public void func_110551_a(IResourceManager resourceManager) throws IOException {
        if (this.bufferedImage == null && this.field_110568_b != null) {
            super.func_110551_a(resourceManager);
        }
        if (this.imageThread == null) {
            if (this.cacheFile != null && this.cacheFile.isFile()) {
                logger.debug("Loading http texture from local cache ({})", new Object[]{this.cacheFile});
                try {
                    this.bufferedImage = ImageIO.read(this.cacheFile);
                    if (this.imageBuffer != null) {
                        this.setBufferedImage(this.imageBuffer.func_78432_a(this.bufferedImage));
                    }
                }
                catch (IOException ioexception) {
                    logger.error("Couldn't load skin " + this.cacheFile, (Throwable)ioexception);
                    this.loadTextureFromServer();
                }
            } else {
                this.loadTextureFromServer();
            }
        }
    }

    protected void loadTextureFromServer() {
        this.imageThread = new Thread("Texture Downloader #" + threadDownloadCounter.incrementAndGet()){

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    BufferedImage bufferedimage;
                    URL url = new URL(ImageDownloadAlt.this.imageUrl);
                    connection = ConfigClient.ImprovedImageDownloadConnection ? ImageDownloadAlt.this.setupConnectionImproved(url) : ImageDownloadAlt.this.setupConnectionOld(url);
                    connection.connect();
                    if (connection.getResponseCode() / 100 != 2) {
                        return;
                    }
                    if (ImageDownloadAlt.this.cacheFile != null) {
                        try (InputStream in = connection.getInputStream();){
                            FileUtils.copyInputStreamToFile((InputStream)in, (File)ImageDownloadAlt.this.cacheFile);
                        }
                        bufferedimage = ImageIO.read(ImageDownloadAlt.this.cacheFile);
                    } else {
                        try (InputStream in = connection.getInputStream();){
                            bufferedimage = ImageIO.read(in);
                        }
                    }
                    if (ImageDownloadAlt.this.imageBuffer != null) {
                        bufferedimage = ImageDownloadAlt.this.imageBuffer.func_78432_a(bufferedimage);
                    }
                    ImageDownloadAlt.this.setBufferedImage(bufferedimage);
                }
                catch (MalformedURLException url) {
                }
                catch (Exception exception) {
                    logger.error("Couldn't download http texture", (Throwable)exception);
                }
                finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        };
        this.imageThread.setDaemon(true);
        this.imageThread.start();
    }

    private HttpURLConnection setupConnectionOld(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection)url.openConnection(Minecraft.func_71410_x().func_110437_J());
        connection.setDoInput(true);
        connection.setDoOutput(false);
        connection.setRequestProperty("Content-Type", "image/png");
        connection.setRequestProperty("Expect", "100-continue");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        if (ImageDownloadAlt.isImgurLink(url)) {
            connection.setRequestProperty("Accept", "*/*");
        }
        return connection;
    }

    private HttpURLConnection setupConnectionImproved(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection)url.openConnection(Minecraft.func_71410_x().func_110437_J());
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setDoOutput(false);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:147.0) Gecko/20100101 Firefox/147.0");
        connection.setRequestProperty("Accept", "image/png,image/*");
        return connection;
    }

    private static boolean isImgurLink(URL url) {
        return url.getHost().endsWith("imgur.com");
    }
}

