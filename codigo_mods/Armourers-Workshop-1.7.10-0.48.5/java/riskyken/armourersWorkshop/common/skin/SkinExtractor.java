/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.skin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.common.config.ConfigHandler;
import riskyken.armourersWorkshop.utils.ModLogger;
import riskyken.armourersWorkshop.utils.SkinIOUtils;

public final class SkinExtractor {
    private static final String SKINS_ASSETS_LOCATION = "assets/armourersworkshop/skins/";
    private static final String[] SKIN_FILES = new String[]{"Angel Wings", "Arbalest", "Arrow", "Barrel", "Bat Wings", "Butterfly Wings", "Chessboard", "Dress Shoes", "Dress Skirt", "Dress Top", "Evil Wings", "Fez", "Fox Ears", "Glass Chair", "Glass Table", "Halo", "Head Bow Left", "Head Bow Right", "Lightsaber (Dual)", "Lightsaber", "Madoka's Head Bows", "Madoka's Shoes", "Madoka's Skirt", "Madoka's Top", "Pika Hood", "Pika Pants", "Pika Paws", "Pika T", "Robot Key", "Sakura's Cat Boots", "Sakura's Cat Chest", "Sakura's Cat Ears", "Sakura's Cat Skirt", "Sakura's Chest", "Sakura's Shoes", "Sakura's Skirt", "Scythe", "Viking Helmet (Blood)", "Viking Helmet", "Witch's Boots", "Witch's Hat", "Witch's Robes", "Witch's Skirt"};

    private SkinExtractor() {
    }

    public static void extractSkins() {
        if (!ConfigHandler.extractOfficialSkins) {
            return;
        }
        for (int i = 0; i < SKIN_FILES.length; ++i) {
            SkinExtractor.extractSkin(SKIN_FILES[i]);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void extractSkin(String fileName) {
        block11: {
            File outputFile;
            InputStream input = null;
            FileOutputStream output = null;
            File outputDir = new File(SkinIOUtils.getSkinLibraryDirectory(), "official");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }
            if ((outputFile = new File(outputDir, fileName + ".armour")).exists()) {
                if (SkinExtractor.getFileSize(outputFile) > 0L) {
                    return;
                }
                ModLogger.log("Deleting corrupted skin file " + fileName);
                outputFile.delete();
            }
            try {
                ModLogger.log("Extracting file " + fileName);
                input = SkinExtractor.class.getClassLoader().getResourceAsStream(SKINS_ASSETS_LOCATION + fileName + ".armour");
                if (input != null) {
                    output = new FileOutputStream(outputFile);
                    while (input.available() > 0) {
                        output.write(input.read());
                    }
                    output.flush();
                } else {
                    ModLogger.log(Level.ERROR, "Error extracting skin " + fileName);
                }
                IOUtils.closeQuietly((InputStream)input);
            }
            catch (IOException e) {
                e.printStackTrace();
                break block11;
            }
            finally {
                IOUtils.closeQuietly(input);
                IOUtils.closeQuietly(output);
            }
            IOUtils.closeQuietly((OutputStream)output);
        }
    }

    private static long getFileSize(File file) {
        return file.length();
    }
}

