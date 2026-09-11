/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StringUtils
 *  net.minecraftforge.common.DimensionManager
 *  org.apache.commons.io.FileUtils
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.utils;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StringUtils;
import net.minecraftforge.common.DimensionManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.library.LibraryFile;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.serialize.SkinSerializer;
import riskyken.armourersWorkshop.utils.ModLogger;

public final class SkinIOUtils {
    public static final String SKIN_FILE_EXTENSION = ".armour";

    public static boolean saveSkinFromFileName(String filePath, String fileName, Skin skin) {
        filePath = SkinIOUtils.makeFilePathValid(filePath);
        fileName = SkinIOUtils.makeFileNameValid(fileName);
        File file = new File(SkinIOUtils.getSkinLibraryDirectory(), filePath + fileName);
        return SkinIOUtils.saveSkinToFile(file, skin);
    }

    public static String makeFileNameValid(String fileName) {
        fileName = fileName.replace("\\", "/");
        fileName = fileName.replace("/", "_");
        fileName = fileName.replace(":", "_");
        return fileName;
    }

    public static String makeFilePathValid(String filePath) {
        filePath = filePath.replace("\\", "/");
        filePath = filePath.replace("../", "_");
        filePath = filePath.replace(":", "_");
        return filePath;
    }

    public static boolean isInLibraryDir(File file) {
        return SkinIOUtils.isInSubDirectory(file, SkinIOUtils.getSkinLibraryDirectory());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     */
    public static boolean saveSkinToFile(File file, Skin skin) {
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        DataOutputStream stream = null;
        try {
            stream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            SkinSerializer.writeToStream(skin, stream);
            stream.flush();
        }
        catch (FileNotFoundException e) {
            ModLogger.log(Level.WARN, "Skin file not found.");
            e.printStackTrace();
            boolean bl = false;
            IOUtils.closeQuietly((OutputStream)stream);
            return bl;
        }
        catch (IOException e2) {
            ModLogger.log(Level.ERROR, "Skin file save failed.");
            e2.printStackTrace();
            boolean bl = false;
            {
                catch (Throwable throwable) {
                    IOUtils.closeQuietly(stream);
                    throw throwable;
                }
            }
            IOUtils.closeQuietly((OutputStream)stream);
            return bl;
        }
        IOUtils.closeQuietly((OutputStream)stream);
        return true;
    }

    public static Skin loadSkinFromLibraryFile(LibraryFile libraryFile) {
        return SkinIOUtils.loadSkinFromFileName(libraryFile.getFullName() + SKIN_FILE_EXTENSION);
    }

    public static Skin loadSkinFromFileName(String fileName) {
        File file = new File(SkinIOUtils.getSkinLibraryDirectory(), fileName);
        if (!SkinIOUtils.isInSubDirectory(SkinIOUtils.getSkinLibraryDirectory(), file)) {
            ModLogger.log(Level.WARN, "Player tried to load a file in a invalid location.");
            ModLogger.log(Level.WARN, String.format("The file was: %s", file.getAbsolutePath().replace("%", "")));
            return null;
        }
        return SkinIOUtils.loadSkinFromFile(file);
    }

    /*
     * Exception decompiling
     */
    public static Skin loadSkinFromFile(File file) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Started 4 blocks at once
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    /*
     * Exception decompiling
     */
    public static Skin loadSkinFromStream(InputStream inputStream) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Started 3 blocks at once
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean saveSkinToStream(OutputStream outputStream, Skin skin) {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            SkinSerializer.writeToStream(skin, new DataOutputStream(bufferedOutputStream));
            bufferedOutputStream.flush();
        }
        catch (IOException e) {
            boolean bl;
            try {
                ModLogger.log(Level.ERROR, "Skin file load failed.");
                e.printStackTrace();
                bl = false;
            }
            catch (Throwable throwable) {
                IOUtils.closeQuietly(bufferedOutputStream);
                throw throwable;
            }
            IOUtils.closeQuietly((OutputStream)bufferedOutputStream);
            return bl;
        }
        IOUtils.closeQuietly((OutputStream)bufferedOutputStream);
        return true;
    }

    /*
     * Exception decompiling
     */
    public static ISkinType getSkinTypeNameFromFile(File file) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Started 3 blocks at once
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    public static void makeDatabaseDirectory() {
        File directory = SkinIOUtils.getSkinDatabaseDirectory();
        ModLogger.log("Loading skin database at: " + directory.getAbsolutePath());
        SkinIOUtils.copyGlobalDatabase();
        if (!directory.exists() && directory.mkdir()) {
            SkinIOUtils.copyOldDatabase();
        }
    }

    public static void makeLibraryDirectory() {
        File directory = SkinIOUtils.getSkinLibraryDirectory();
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public static void copyOldDatabase() {
        ModLogger.log("Moving skin database to a new location.");
        File dirNewDatabase = SkinIOUtils.getSkinDatabaseDirectory();
        File dirOldDatabase = SkinIOUtils.getOldSkinDatabaseDirectory();
        if (!dirOldDatabase.exists()) {
            ModLogger.log("Old database not found.");
            return;
        }
        File[] oldFiles = dirOldDatabase.listFiles();
        for (int i = 0; i < oldFiles.length; ++i) {
            File oldFile = oldFiles[i];
            ModLogger.log("Copying file: " + oldFile.getName());
            File newFile = new File(dirNewDatabase, oldFile.getName());
            try {
                FileUtils.copyFile((File)oldFile, (File)newFile);
                continue;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyGlobalDatabase() {
        File dirGlobalDatabase = SkinIOUtils.getGlobalSkinDatabaseDirectory();
        if (dirGlobalDatabase.exists()) {
            File dirWorldDatabase = SkinIOUtils.getSkinDatabaseDirectory();
            File[] globalFiles = dirGlobalDatabase.listFiles();
            for (int i = 0; i < globalFiles.length; ++i) {
                File globalFile = globalFiles[i];
                File worldFile = new File(dirWorldDatabase, globalFile.getName());
                if (!(!globalFile.getName().equals("readme.txt") & !worldFile.exists())) continue;
                try {
                    FileUtils.copyFile((File)globalFile, (File)worldFile);
                    continue;
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        SkinIOUtils.createGlobalDatabaseReadme();
    }

    private static void createGlobalDatabaseReadme() {
        File globalDatabaseReadme = new File(SkinIOUtils.getGlobalSkinDatabaseDirectory(), "readme.txt");
        if (!SkinIOUtils.getGlobalSkinDatabaseDirectory().exists()) {
            SkinIOUtils.getGlobalSkinDatabaseDirectory().mkdirs();
        }
        if (!globalDatabaseReadme.exists()) {
            DataOutputStream outputStream = null;
            try {
                String crlf = "\r\n";
                outputStream = new DataOutputStream(new FileOutputStream(globalDatabaseReadme));
                outputStream.writeBytes("Any files placed in this directory will be copied into the skin-database folder of any worlds that are loaded." + crlf);
                outputStream.writeBytes("Please read Info for Map & Mod Pack Makers on the main forum post if you want to know how to use this." + crlf);
                outputStream.writeBytes("http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/wip-mods/2309193-wip-alpha-armourers-workshop-weapon-armour-skins");
                outputStream.flush();
                IOUtils.closeQuietly((OutputStream)outputStream);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                IOUtils.closeQuietly(outputStream);
            }
        }
    }

    public static File getSkinDatabaseDirectory() {
        return new File(DimensionManager.getCurrentSaveRootDirectory(), "skin-database");
    }

    public static File getOldSkinDatabaseDirectory() {
        return new File(System.getProperty("user.dir"), "equipment-database");
    }

    public static File getGlobalSkinDatabaseDirectory() {
        return new File(System.getProperty("user.dir"), "global-skin-database");
    }

    public static File getSkinLibraryDirectory() {
        return new File(System.getProperty("user.dir"), "armourersWorkshop");
    }

    public static File getFastCaheDirectory() {
        return new File(System.getProperty("user.dir"), "fast-cache");
    }

    public static boolean createDirectory(File file) {
        if (!file.exists()) {
            return file.mkdirs();
        }
        return true;
    }

    public static void recoverSkins(EntityPlayer player) {
        player.func_146105_b((IChatComponent)new ChatComponentText("Starting skin recovery."));
        File skinDir = SkinIOUtils.getSkinDatabaseDirectory();
        if (skinDir.exists() & skinDir.isDirectory()) {
            File recoverDir = new File(System.getProperty("user.dir"), "recovered-skins");
            if (!recoverDir.exists()) {
                recoverDir.mkdirs();
            }
            File[] skinFiles = skinDir.listFiles();
            player.func_146105_b((IChatComponent)new ChatComponentText(String.format("Found %d skins to be recovered.", skinFiles.length)));
            player.func_146105_b((IChatComponent)new ChatComponentText("Working..."));
            int unnamedSkinCount = 0;
            int successCount = 0;
            int failCount = 0;
            for (int i = 0; i < skinFiles.length; ++i) {
                File skinFile = skinFiles[i];
                Skin skin = SkinIOUtils.loadSkinFromFile(skinFile);
                if (skin != null) {
                    int nameCount;
                    File newSkinFile;
                    String fileName = skin.getProperties().getPropertyString("fileName", null);
                    String customName = skin.getProperties().getPropertyString("customName", null);
                    if (!StringUtils.func_151246_b((String)fileName)) {
                        newSkinFile = new File(recoverDir, (fileName = SkinIOUtils.makeFileNameValid(fileName)) + SKIN_FILE_EXTENSION);
                        if (newSkinFile.exists()) {
                            nameCount = 0;
                            while ((newSkinFile = new File(recoverDir, fileName + "-" + ++nameCount + SKIN_FILE_EXTENSION)).exists()) {
                            }
                        }
                        SkinIOUtils.saveSkinToFile(newSkinFile, skin);
                        ++successCount;
                        continue;
                    }
                    if (!StringUtils.func_151246_b((String)customName)) {
                        newSkinFile = new File(recoverDir, (customName = SkinIOUtils.makeFileNameValid(customName)) + SKIN_FILE_EXTENSION);
                        if (newSkinFile.exists()) {
                            nameCount = 0;
                            while ((newSkinFile = new File(recoverDir, customName + "-" + ++nameCount + SKIN_FILE_EXTENSION)).exists()) {
                            }
                        }
                        SkinIOUtils.saveSkinToFile(newSkinFile, skin);
                        ++successCount;
                        continue;
                    }
                    SkinIOUtils.saveSkinToFile(new File(recoverDir, "unnamed-skin-" + ++unnamedSkinCount + SKIN_FILE_EXTENSION), skin);
                    ++successCount;
                    continue;
                }
                ++failCount;
            }
            player.func_146105_b((IChatComponent)new ChatComponentText("Finished skin recovery."));
            player.func_146105_b((IChatComponent)new ChatComponentText(String.format("%d skins were recovered and %d fail recovery.", successCount, failCount)));
        } else {
            player.func_146105_b((IChatComponent)new ChatComponentText("No skins found to recover."));
        }
    }

    public static void updateSkins(EntityPlayer player) {
        File updateDir = new File(System.getProperty("user.dir"), "skin-update");
        if (!updateDir.exists() & updateDir.isDirectory()) {
            player.func_146105_b((IChatComponent)new ChatComponentText("Directory skin-update not found."));
            return;
        }
        File outputDir = new File(updateDir, "updated");
        if (!outputDir.exists()) {
            outputDir.mkdir();
        }
        File[] skinFiles = updateDir.listFiles();
        player.func_146105_b((IChatComponent)new ChatComponentText(String.format("Found %d skins to be updated.", skinFiles.length)));
        player.func_146105_b((IChatComponent)new ChatComponentText("Working..."));
        int successCount = 0;
        int failCount = 0;
        for (int i = 0; i < skinFiles.length; ++i) {
            File skinFile = skinFiles[i];
            if (!skinFile.isFile()) continue;
            Skin skin = SkinIOUtils.loadSkinFromFile(skinFile);
            if (skin != null) {
                if (SkinIOUtils.saveSkinToFile(new File(outputDir, skinFile.getName()), skin)) {
                    ++successCount;
                    continue;
                }
                ModLogger.log(Level.ERROR, "Failed to update skin " + skinFile.getName());
                ++failCount;
                continue;
            }
            ModLogger.log(Level.ERROR, "Failed to update skin " + skinFile.getName());
            ++failCount;
        }
        player.func_146105_b((IChatComponent)new ChatComponentText("Finished skin update."));
        player.func_146105_b((IChatComponent)new ChatComponentText(String.format("%d skins were updated and %d failed.", successCount, failCount)));
    }

    public static boolean isInSubDirectory(File dir, File file) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            // empty if block
        }
        if (file.getParentFile().equals(dir)) {
            return true;
        }
        return SkinIOUtils.isInSubDirectory(dir, file.getParentFile());
    }
}

