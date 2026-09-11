/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.Launch
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package makamys.mixingasm;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import net.minecraft.launchwrapper.Launch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigHelper {
    private final String MODID;
    private final Logger LOGGER;

    public ConfigHelper(String modid) {
        this.MODID = modid;
        this.LOGGER = LogManager.getLogger((String)this.MODID);
    }

    public Path getDefaultConfigFilePath(Path relPath) throws IOException {
        String resourceRelPath = Paths.get("assets/" + this.MODID + "/default_config/", new String[0]).resolve(relPath).toString().replace('\\', '/');
        URL resourceURL = new Object(){}.getClass().getEnclosingClass().getClassLoader().getResource(resourceRelPath);
        switch (resourceURL.getProtocol()) {
            case "jar": {
                String urlString = resourceURL.getPath();
                int lastExclamation = urlString.lastIndexOf(33);
                String newURLString = urlString.substring(0, lastExclamation);
                return FileSystems.newFileSystem(new File(URI.create(newURLString)).toPath(), null).getPath(resourceRelPath, new String[0]);
            }
            case "file": {
                return new File(URI.create(resourceURL.toString())).toPath();
            }
        }
        return null;
    }

    private void copyDefaultConfigFile(Path src, Path dest) throws IOException {
        Files.createDirectories(this.getParentSafe(dest), new FileAttribute[0]);
        this.LOGGER.debug("Copying " + src + " -> " + dest);
        Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean createDefaultConfigFileIfMissing(File configFile, boolean overwrite) {
        Path configFolderPath = Paths.get(new File(Launch.minecraftHome, "config").getPath(), new String[0]);
        Path configFilePath = Paths.get(configFile.getPath(), new String[0]);
        Path relPath = configFolderPath.relativize(configFilePath);
        if (configFilePath.startsWith(configFolderPath)) {
            try {
                Path defaultConfigPath = this.getDefaultConfigFilePath(relPath);
                if (Files.isRegularFile(defaultConfigPath, new LinkOption[0])) {
                    if (configFile.exists() && !overwrite) return true;
                    this.copyDefaultConfigFile(defaultConfigPath, configFile.toPath());
                    return true;
                }
                if (!Files.isDirectory(defaultConfigPath, new LinkOption[0])) return true;
                Files.createDirectories(Paths.get(configFile.getPath(), new String[0]), new FileAttribute[0]);
                for (Object po : Files.walk(defaultConfigPath, new FileVisitOption[0]).toArray()) {
                    Path destPath = configFile.toPath().resolve(defaultConfigPath.toAbsolutePath().relativize(((Path)po).toAbsolutePath()).toString());
                    if (!Files.isRegularFile((Path)po, new LinkOption[0]) || Files.exists(destPath, new LinkOption[0]) && !overwrite) continue;
                    this.copyDefaultConfigFile((Path)po, destPath);
                }
                return true;
            }
            catch (IOException e) {
                this.LOGGER.error("Failed to create default config file for " + relPath.toString() + ": " + e.getMessage());
                return false;
            }
        } else {
            this.LOGGER.debug("Invalid argument for creating default config file: " + relPath.toString() + " (file is not in the config directory)");
            return false;
        }
    }

    public Path getParentSafe(Path p) {
        if (p == null || p.getParent() == null) {
            return Paths.get("", new String[0]);
        }
        return p.getParent();
    }
}

