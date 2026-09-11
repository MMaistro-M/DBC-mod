/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.apache.logging.log4j.Logger
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksConfig;
import invtweaks.InvTweaksConst;
import invtweaks.InvTweaksHandlerAutoRefill;
import invtweaks.InvTweaksHandlerShortcuts;
import invtweaks.InvTweaksItemTreeLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.apache.logging.log4j.Logger;

public class InvTweaksConfigManager {
    private static final Logger log = InvTweaks.log;
    private Minecraft mc;
    private InvTweaksConfig config = null;
    private long storedConfigLastModified = 0L;
    private InvTweaksHandlerAutoRefill autoRefillHandler = null;
    private InvTweaksHandlerShortcuts shortcutsHandler = null;

    public InvTweaksConfigManager(Minecraft mc) {
        this.mc = mc;
    }

    public boolean makeSureConfigurationIsLoaded() {
        try {
            if (this.config != null && this.config.refreshProperties()) {
                this.shortcutsHandler = new InvTweaksHandlerShortcuts(this.mc, this.config);
                InvTweaks.logInGameStatic("invtweaks.propsfile.loaded");
            }
        }
        catch (IOException e) {
            InvTweaks.logInGameErrorStatic("invtweaks.loadconfig.refresh.error", e);
        }
        long configLastModified = this.computeConfigLastModified();
        if (this.config != null) {
            if (this.storedConfigLastModified != configLastModified) {
                return this.loadConfig();
            }
            return true;
        }
        this.storedConfigLastModified = configLastModified;
        return this.loadConfig();
    }

    public InvTweaksConfig getConfig() {
        return this.config;
    }

    public InvTweaksHandlerAutoRefill getAutoRefillHandler() {
        return this.autoRefillHandler;
    }

    public InvTweaksHandlerShortcuts getShortcutsHandler() {
        return this.shortcutsHandler;
    }

    private long computeConfigLastModified() {
        return InvTweaksConst.CONFIG_RULES_FILE.lastModified() + InvTweaksConst.CONFIG_TREE_FILE.lastModified();
    }

    private boolean loadConfig() {
        File configDir = InvTweaksConst.MINECRAFT_CONFIG_DIR;
        if (!configDir.exists()) {
            configDir.mkdir();
        }
        try {
            if (!InvTweaksItemTreeLoader.isValidVersion(InvTweaksConst.CONFIG_TREE_FILE)) {
                this.backupFile(InvTweaksConst.CONFIG_TREE_FILE);
            }
        }
        catch (Exception e) {
            log.warn("Failed to check item tree version: " + e.getMessage());
        }
        if (InvTweaksConst.OLD_CONFIG_TREE_FILE.exists()) {
            if (InvTweaksConst.CONFIG_RULES_FILE.exists()) {
                this.backupFile(InvTweaksConst.CONFIG_TREE_FILE);
            }
            InvTweaksConst.OLD_CONFIG_TREE_FILE.renameTo(InvTweaksConst.CONFIG_TREE_FILE);
        } else if (InvTweaksConst.OLDER_CONFIG_RULES_FILE.exists()) {
            if (InvTweaksConst.CONFIG_RULES_FILE.exists()) {
                this.backupFile(InvTweaksConst.CONFIG_RULES_FILE);
            }
            InvTweaksConst.OLDER_CONFIG_RULES_FILE.renameTo(InvTweaksConst.CONFIG_RULES_FILE);
        }
        if (!InvTweaksConst.CONFIG_RULES_FILE.exists() && this.extractFile(InvTweaksConst.DEFAULT_CONFIG_FILE, InvTweaksConst.CONFIG_RULES_FILE)) {
            InvTweaks.logInGameStatic(InvTweaksConst.CONFIG_RULES_FILE + " " + StatCollector.func_74838_a((String)"invtweaks.loadconfig.filemissing"));
        }
        if (!InvTweaksConst.CONFIG_TREE_FILE.exists() && this.extractFile(InvTweaksConst.DEFAULT_CONFIG_TREE_FILE, InvTweaksConst.CONFIG_TREE_FILE)) {
            InvTweaks.logInGameStatic(InvTweaksConst.CONFIG_TREE_FILE + " " + StatCollector.func_74838_a((String)"invtweaks.loadconfig.filemissing"));
        }
        this.storedConfigLastModified = this.computeConfigLastModified();
        String error = null;
        Exception errorException = null;
        try {
            if (this.config == null) {
                this.config = new InvTweaksConfig(InvTweaksConst.CONFIG_RULES_FILE, InvTweaksConst.CONFIG_TREE_FILE);
                this.autoRefillHandler = new InvTweaksHandlerAutoRefill(this.mc, this.config);
                this.shortcutsHandler = new InvTweaksHandlerShortcuts(this.mc, this.config);
            }
            this.config.load();
            this.shortcutsHandler.loadShortcuts();
            InvTweaks.logInGameStatic("invtweaks.loadconfig.done");
            this.showConfigErrors(this.config);
        }
        catch (FileNotFoundException e) {
            error = "Config file not found";
            errorException = e;
        }
        catch (Exception e) {
            error = "Error while loading config";
            errorException = e;
        }
        if (error != null) {
            log.error(error);
            InvTweaks.logInGameErrorStatic(error, errorException);
            try {
                this.backupFile(InvTweaksConst.CONFIG_TREE_FILE);
                this.backupFile(InvTweaksConst.CONFIG_RULES_FILE);
                this.backupFile(InvTweaksConst.CONFIG_PROPS_FILE);
                this.extractFile(InvTweaksConst.DEFAULT_CONFIG_FILE, InvTweaksConst.CONFIG_RULES_FILE);
                this.extractFile(InvTweaksConst.DEFAULT_CONFIG_TREE_FILE, InvTweaksConst.CONFIG_TREE_FILE);
                this.config = new InvTweaksConfig(InvTweaksConst.CONFIG_RULES_FILE, InvTweaksConst.CONFIG_TREE_FILE);
                this.autoRefillHandler = new InvTweaksHandlerAutoRefill(this.mc, this.config);
                this.shortcutsHandler = new InvTweaksHandlerShortcuts(this.mc, this.config);
                this.config.load();
                this.shortcutsHandler.loadShortcuts();
            }
            catch (Exception e) {
                this.config = null;
                this.autoRefillHandler = null;
                this.shortcutsHandler = null;
                if (e.getCause() == null) {
                    e.initCause(errorException);
                }
                throw new Error("InvTweaks config load failed", e);
            }
            return false;
        }
        return true;
    }

    private void backupFile(File file) {
        File newFile = new File(file.getName() + ".bak");
        if (newFile.exists()) {
            newFile.delete();
        }
        file.renameTo(newFile);
    }

    private void backupFile(File file, String name) {
        File newFile = new File(name + ".bak");
        if (newFile.exists()) {
            newFile.delete();
        }
        file.renameTo(newFile);
    }

    private boolean extractFile(ResourceLocation resource, File destination) {
        try {
            InputStream input = this.mc.func_110442_L().func_110536_a(resource).func_110527_b();
            byte[] contents = new byte[input.available()];
            input.read(contents);
            input.close();
            try {
                FileOutputStream f = new FileOutputStream(destination);
                f.write(contents);
                f.close();
                return true;
            }
            catch (IOException e) {
                InvTweaks.logInGameStatic("[16] The mod won't work, because " + destination + " creation failed!");
                log.error("Cannot create " + destination + " file: " + e.getMessage());
                return false;
            }
        }
        catch (IOException e) {
            InvTweaks.logInGameStatic("[15] The mod won't work, because " + resource + " extraction failed!");
            log.error("Cannot extract " + resource + " file: " + e.getMessage());
            return false;
        }
    }

    private void showConfigErrors(InvTweaksConfig config) {
        Vector<String> invalid = config.getInvalidKeywords();
        if (invalid.size() > 0) {
            String error = StatCollector.func_74838_a((String)"invtweaks.loadconfig.invalidkeywords") + ": ";
            for (String keyword : config.getInvalidKeywords()) {
                error = error + keyword + " ";
            }
            InvTweaks.logInGameStatic(error);
        }
    }
}

