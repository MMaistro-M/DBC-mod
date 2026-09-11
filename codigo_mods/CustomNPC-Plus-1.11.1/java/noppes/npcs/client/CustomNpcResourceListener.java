/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.FolderResourcePack
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.resources.IResourceManagerReloadListener
 *  net.minecraft.client.resources.IResourcePack
 *  net.minecraft.client.resources.SimpleReloadableResourceManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.FolderResourcePack;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.CustomNpcs;
import noppes.npcs.LogWriter;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeRegistry;
import noppes.npcs.client.gui.util.script.interpreter.token.ScriptColorScheme;

public class CustomNpcResourceListener
implements IResourceManagerReloadListener {
    public static int DefaultTextColor = 0x404040;

    public void func_110549_a(IResourceManager var1) {
        if (var1 instanceof SimpleReloadableResourceManager) {
            this.createTextureCache(new File(CustomNpcs.Dir, "assets/customnpcs/textures/cache"));
            SimpleReloadableResourceManager simplemanager = (SimpleReloadableResourceManager)var1;
            FolderResourcePack pack = new FolderResourcePack(CustomNpcs.Dir);
            LogWriter.info("[ResourceListener] Adding FolderResourcePack (as fallback): " + CustomNpcs.Dir.getAbsolutePath());
            simplemanager.func_110545_a((IResourcePack)pack);
            ScriptColorScheme.reloadColorScheme(var1);
            JSTypeRegistry registry = JSTypeRegistry.getInstance();
            registry.clear();
            registry.initializeFromResources();
            try {
                DefaultTextColor = Integer.parseInt(StatCollector.func_74838_a((String)"customnpcs.defaultTextColor"), 16);
            }
            catch (NumberFormatException e) {
                DefaultTextColor = 0x404040;
            }
        }
    }

    private void createTextureCache(File dir) {
        if (dir == null) {
            return;
        }
        this.enlargeTexture("planks_oak", dir);
        this.enlargeTexture("planks_big_oak", dir);
        this.enlargeTexture("planks_birch", dir);
        this.enlargeTexture("planks_jungle", dir);
        this.enlargeTexture("planks_spruce", dir);
        this.enlargeTexture("planks_acacia", dir);
        this.enlargeTexture("iron_block", dir);
        this.enlargeTexture("diamond_block", dir);
        this.enlargeTexture("stone", dir);
        this.enlargeTexture("gold_block", dir);
        this.enlargeTexture("wool_colored_white", dir);
    }

    private void enlargeTexture(String texture, File dir) {
        try {
            IResourceManager manager = Minecraft.func_71410_x().func_110442_L();
            ResourceLocation location = new ResourceLocation("textures/blocks/" + texture + ".png");
            try (InputStream stream = manager.func_110536_a(location).func_110527_b();){
                BufferedImage bufferedimage = ImageIO.read(stream);
                int i = bufferedimage.getWidth();
                int j = bufferedimage.getHeight();
                BufferedImage image = new BufferedImage(i * 4, j * 2, 1);
                Graphics g = image.getGraphics();
                g.drawImage(bufferedimage, 0, 0, null);
                g.drawImage(bufferedimage, i, 0, null);
                g.drawImage(bufferedimage, i * 2, 0, null);
                g.drawImage(bufferedimage, i * 3, 0, null);
                g.drawImage(bufferedimage, 0, i, null);
                g.drawImage(bufferedimage, i, j, null);
                g.drawImage(bufferedimage, i * 2, j, null);
                g.drawImage(bufferedimage, i * 3, j, null);
                ImageIO.write((RenderedImage)image, "png", new File(dir, texture + ".png"));
            }
        }
        catch (Exception e) {
            LogWriter.error("Failed caching texture: " + texture, e);
        }
    }
}

