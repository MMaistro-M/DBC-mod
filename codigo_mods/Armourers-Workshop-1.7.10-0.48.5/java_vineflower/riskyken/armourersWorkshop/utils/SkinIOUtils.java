package riskyken.armourersWorkshop.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StringUtils;
import net.minecraftforge.common.DimensionManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.exception.InvalidCubeTypeException;
import riskyken.armourersWorkshop.common.exception.NewerFileVersionException;
import riskyken.armourersWorkshop.common.library.LibraryFile;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.serialize.SkinSerializer;

public final class SkinIOUtils {
   public static final String SKIN_FILE_EXTENSION = ".armour";

   public static boolean saveSkinFromFileName(String filePath, String fileName, Skin skin) {
      filePath = makeFilePathValid(filePath);
      fileName = makeFileNameValid(fileName);
      File file = new File(getSkinLibraryDirectory(), filePath + fileName);
      return saveSkinToFile(file, skin);
   }

   public static String makeFileNameValid(String fileName) {
      fileName = fileName.replace("\\", "/");
      fileName = fileName.replace("/", "_");
      return fileName.replace(":", "_");
   }

   public static String makeFilePathValid(String filePath) {
      filePath = filePath.replace("\\", "/");
      filePath = filePath.replace("../", "_");
      return filePath.replace(":", "_");
   }

   public static boolean isInLibraryDir(File file) {
      return isInSubDirectory(file, getSkinLibraryDirectory());
   }

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
      } catch (FileNotFoundException e) {
         ModLogger.log(Level.WARN, "Skin file not found.");
         e.printStackTrace();
         return false;
      } catch (IOException e) {
         ModLogger.log(Level.ERROR, "Skin file save failed.");
         e.printStackTrace();
         return false;
      } finally {
         IOUtils.closeQuietly(stream);
      }

      return true;
   }

   public static Skin loadSkinFromLibraryFile(LibraryFile libraryFile) {
      return loadSkinFromFileName(libraryFile.getFullName() + ".armour");
   }

   public static Skin loadSkinFromFileName(String fileName) {
      File file = new File(getSkinLibraryDirectory(), fileName);
      if (!isInSubDirectory(getSkinLibraryDirectory(), file)) {
         ModLogger.log(Level.WARN, "Player tried to load a file in a invalid location.");
         ModLogger.log(Level.WARN, String.format("The file was: %s", file.getAbsolutePath().replace("%", "")));
         return null;
      } else {
         return loadSkinFromFile(file);
      }
   }

   public static Skin loadSkinFromFile(File file) {
      DataInputStream stream = null;
      Skin skin = null;

      try {
         stream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
         skin = SkinSerializer.readSkinFromStream(stream);
      } catch (FileNotFoundException e) {
         ModLogger.log(Level.WARN, "Skin file not found.");
         ModLogger.log(Level.WARN, file);
      } catch (IOException e) {
         ModLogger.log(Level.ERROR, "Skin file load failed.");
         e.printStackTrace();
      } catch (NewerFileVersionException e) {
         ModLogger.log(Level.ERROR, "Can not load skin file it was saved in newer version.");
         e.printStackTrace();
      } catch (InvalidCubeTypeException e) {
         ModLogger.log(Level.ERROR, "Unable to load skin. Unknown cube types found.");
         e.printStackTrace();
      } catch (Exception e) {
         ModLogger.log(Level.ERROR, "Unable to load skin. Unknown error.");
         e.printStackTrace();
      } finally {
         IOUtils.closeQuietly(stream);
      }

      return skin;
   }

   public static Skin loadSkinFromStream(InputStream inputStream) {
      DataInputStream stream = null;
      Skin skin = null;

      try {
         stream = new DataInputStream(new BufferedInputStream(inputStream));
         skin = SkinSerializer.readSkinFromStream(stream);
      } catch (FileNotFoundException e) {
         ModLogger.log(Level.WARN, "Skin file not found.");
         e.printStackTrace();
      } catch (IOException e) {
         ModLogger.log(Level.ERROR, "Skin file load failed.");
         e.printStackTrace();
      } catch (NewerFileVersionException e) {
         ModLogger.log(Level.ERROR, "Can not load skin file it was saved in newer version.");
         e.printStackTrace();
      } catch (InvalidCubeTypeException e) {
         ModLogger.log(Level.ERROR, "Unable to load skin. Unknown cube types found.");
         e.printStackTrace();
      } finally {
         IOUtils.closeQuietly(stream);
         IOUtils.closeQuietly(inputStream);
      }

      return skin;
   }

   public static boolean saveSkinToStream(OutputStream outputStream, Skin skin) {
      BufferedOutputStream bufferedOutputStream = null;

      try {
         bufferedOutputStream = new BufferedOutputStream(outputStream);
         SkinSerializer.writeToStream(skin, new DataOutputStream(bufferedOutputStream));
         bufferedOutputStream.flush();
      } catch (IOException e) {
         ModLogger.log(Level.ERROR, "Skin file load failed.");
         e.printStackTrace();
         return false;
      } finally {
         IOUtils.closeQuietly(bufferedOutputStream);
      }

      return true;
   }

   public static ISkinType getSkinTypeNameFromFile(File file) {
      DataInputStream stream = null;
      ISkinType skinType = null;

      try {
         stream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
         skinType = SkinSerializer.readSkinTypeNameFromStream(stream);
      } catch (FileNotFoundException e) {
         e.printStackTrace();
         ModLogger.log(Level.ERROR, "File name: " + file.getName());
      } catch (IOException e) {
         e.printStackTrace();
         ModLogger.log(Level.ERROR, "File name: " + file.getName());
      } catch (NewerFileVersionException e) {
         e.printStackTrace();
         ModLogger.log(Level.ERROR, "File name: " + file.getName());
      } catch (Exception e) {
         ModLogger.log(Level.ERROR, "Unable to load skin name. Unknown error.");
         e.printStackTrace();
      } finally {
         IOUtils.closeQuietly(stream);
      }

      return skinType;
   }

   public static void makeDatabaseDirectory() {
      File directory = getSkinDatabaseDirectory();
      ModLogger.log("Loading skin database at: " + directory.getAbsolutePath());
      copyGlobalDatabase();
      if (!directory.exists() && directory.mkdir()) {
         copyOldDatabase();
      }
   }

   public static void makeLibraryDirectory() {
      File directory = getSkinLibraryDirectory();
      if (!directory.exists()) {
         directory.mkdir();
      }
   }

   public static void copyOldDatabase() {
      ModLogger.log("Moving skin database to a new location.");
      File dirNewDatabase = getSkinDatabaseDirectory();
      File dirOldDatabase = getOldSkinDatabaseDirectory();
      if (!dirOldDatabase.exists()) {
         ModLogger.log("Old database not found.");
      } else {
         File[] oldFiles = dirOldDatabase.listFiles();

         for (int i = 0; i < oldFiles.length; i++) {
            File oldFile = oldFiles[i];
            ModLogger.log("Copying file: " + oldFile.getName());
            File newFile = new File(dirNewDatabase, oldFile.getName());

            try {
               FileUtils.copyFile(oldFile, newFile);
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }
   }

   public static void copyGlobalDatabase() {
      File dirGlobalDatabase = getGlobalSkinDatabaseDirectory();
      if (dirGlobalDatabase.exists()) {
         File dirWorldDatabase = getSkinDatabaseDirectory();
         File[] globalFiles = dirGlobalDatabase.listFiles();

         for (int i = 0; i < globalFiles.length; i++) {
            File globalFile = globalFiles[i];
            File worldFile = new File(dirWorldDatabase, globalFile.getName());
            if (!globalFile.getName().equals("readme.txt") & !worldFile.exists()) {
               try {
                  FileUtils.copyFile(globalFile, worldFile);
               } catch (IOException e) {
                  e.printStackTrace();
               }
            }
         }
      }

      createGlobalDatabaseReadme();
   }

   private static void createGlobalDatabaseReadme() {
      File globalDatabaseReadme = new File(getGlobalSkinDatabaseDirectory(), "readme.txt");
      if (!getGlobalSkinDatabaseDirectory().exists()) {
         getGlobalSkinDatabaseDirectory().mkdirs();
      }

      if (!globalDatabaseReadme.exists()) {
         DataOutputStream outputStream = null;

         try {
            String crlf = "\r\n";
            outputStream = new DataOutputStream(new FileOutputStream(globalDatabaseReadme));
            outputStream.writeBytes("Any files placed in this directory will be copied into the skin-database folder of any worlds that are loaded." + crlf);
            outputStream.writeBytes("Please read Info for Map & Mod Pack Makers on the main forum post if you want to know how to use this." + crlf);
            outputStream.writeBytes(
               "http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/wip-mods/2309193-wip-alpha-armourers-workshop-weapon-armour-skins"
            );
            outputStream.flush();
         } catch (IOException e) {
            e.printStackTrace();
         } finally {
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
      return !file.exists() ? file.mkdirs() : true;
   }

   public static void recoverSkins(EntityPlayer player) {
      player.func_146105_b(new ChatComponentText("Starting skin recovery."));
      File skinDir = getSkinDatabaseDirectory();
      if (skinDir.exists() & skinDir.isDirectory()) {
         File recoverDir = new File(System.getProperty("user.dir"), "recovered-skins");
         if (!recoverDir.exists()) {
            recoverDir.mkdirs();
         }

         File[] skinFiles = skinDir.listFiles();
         player.func_146105_b(new ChatComponentText(String.format("Found %d skins to be recovered.", skinFiles.length)));
         player.func_146105_b(new ChatComponentText("Working..."));
         int unnamedSkinCount = 0;
         int successCount = 0;
         int failCount = 0;

         for (int i = 0; i < skinFiles.length; i++) {
            File skinFile = skinFiles[i];
            Skin skin = loadSkinFromFile(skinFile);
            if (skin == null) {
               failCount++;
            } else {
               String fileName = skin.getProperties().getPropertyString("fileName", null);
               String customName = skin.getProperties().getPropertyString("customName", null);
               if (!StringUtils.func_151246_b(fileName)) {
                  fileName = makeFileNameValid(fileName);
                  File newSkinFile = new File(recoverDir, fileName + ".armour");
                  if (newSkinFile.exists()) {
                     int nameCount = 0;

                     do {
                        newSkinFile = new File(recoverDir, fileName + "-" + ++nameCount + ".armour");
                     } while (newSkinFile.exists());
                  }

                  saveSkinToFile(newSkinFile, skin);
                  successCount++;
               } else if (StringUtils.func_151246_b(customName)) {
                  saveSkinToFile(new File(recoverDir, "unnamed-skin-" + ++unnamedSkinCount + ".armour"), skin);
                  successCount++;
               } else {
                  customName = makeFileNameValid(customName);
                  File newSkinFile = new File(recoverDir, customName + ".armour");
                  if (newSkinFile.exists()) {
                     int nameCount = 0;

                     do {
                        newSkinFile = new File(recoverDir, customName + "-" + ++nameCount + ".armour");
                     } while (newSkinFile.exists());
                  }

                  saveSkinToFile(newSkinFile, skin);
                  successCount++;
               }
            }
         }

         player.func_146105_b(new ChatComponentText("Finished skin recovery."));
         player.func_146105_b(new ChatComponentText(String.format("%d skins were recovered and %d fail recovery.", successCount, failCount)));
      } else {
         player.func_146105_b(new ChatComponentText("No skins found to recover."));
      }
   }

   public static void updateSkins(EntityPlayer player) {
      File updateDir = new File(System.getProperty("user.dir"), "skin-update");
      if (!updateDir.exists() & updateDir.isDirectory()) {
         player.func_146105_b(new ChatComponentText("Directory skin-update not found."));
      } else {
         File outputDir = new File(updateDir, "updated");
         if (!outputDir.exists()) {
            outputDir.mkdir();
         }

         File[] skinFiles = updateDir.listFiles();
         player.func_146105_b(new ChatComponentText(String.format("Found %d skins to be updated.", skinFiles.length)));
         player.func_146105_b(new ChatComponentText("Working..."));
         int successCount = 0;
         int failCount = 0;

         for (int i = 0; i < skinFiles.length; i++) {
            File skinFile = skinFiles[i];
            if (skinFile.isFile()) {
               Skin skin = loadSkinFromFile(skinFile);
               if (skin != null) {
                  if (saveSkinToFile(new File(outputDir, skinFile.getName()), skin)) {
                     successCount++;
                  } else {
                     ModLogger.log(Level.ERROR, "Failed to update skin " + skinFile.getName());
                     failCount++;
                  }
               } else {
                  ModLogger.log(Level.ERROR, "Failed to update skin " + skinFile.getName());
                  failCount++;
               }
            }
         }

         player.func_146105_b(new ChatComponentText("Finished skin update."));
         player.func_146105_b(new ChatComponentText(String.format("%d skins were updated and %d failed.", successCount, failCount)));
      }
   }

   public static boolean isInSubDirectory(File dir, File file) {
      if (file == null) {
         return false;
      }

      if (file.isDirectory()) {
      }

      return file.getParentFile().equals(dir) ? true : isInSubDirectory(dir, file.getParentFile());
   }
}
