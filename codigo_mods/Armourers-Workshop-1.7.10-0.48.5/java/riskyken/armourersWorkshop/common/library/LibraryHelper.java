/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.FilenameUtils
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.library;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.library.LibraryFile;
import riskyken.armourersWorkshop.utils.ModLogger;
import riskyken.armourersWorkshop.utils.SkinIOUtils;

public final class LibraryHelper {
    private LibraryHelper() {
    }

    public static ArrayList<LibraryFile> getSkinFilesInDirectory(File directory, boolean subDirectories) {
        int i;
        File[] templateFiles;
        ArrayList<LibraryFile> fileList = new ArrayList<LibraryFile>();
        if (!directory.exists() | !directory.isDirectory()) {
            return fileList;
        }
        File libraryDir = SkinIOUtils.getSkinLibraryDirectory();
        try {
            templateFiles = directory.listFiles();
        }
        catch (Exception e) {
            ModLogger.log(Level.ERROR, "Armour file list load failed.");
            e.printStackTrace();
            return fileList;
        }
        for (i = 0; i < templateFiles.length; ++i) {
            String path;
            if (templateFiles[i].getName().endsWith(".armour")) {
                String cleanName = FilenameUtils.removeExtension((String)templateFiles[i].getName());
                path = templateFiles[i].getPath().replace(templateFiles[i].getName(), "");
                path = path.replace(libraryDir.getPath(), "");
                ISkinType skinType = SkinIOUtils.getSkinTypeNameFromFile(templateFiles[i]);
                if (skinType == null) continue;
                fileList.add(new LibraryFile(cleanName, path, skinType));
                continue;
            }
            if (!templateFiles[i].isDirectory()) continue;
            String name = templateFiles[i].getName();
            path = templateFiles[i].getParent() + "/";
            path = path.replace(SkinIOUtils.getSkinLibraryDirectory().getPath(), "");
            path = path.replace("\\", "/");
            if (name.equals("private")) continue;
            fileList.add(new LibraryFile(templateFiles[i].getName(), path, null, true));
        }
        Collections.sort(fileList);
        if (subDirectories) {
            for (i = 0; i < templateFiles.length; ++i) {
                if (!templateFiles[i].isDirectory()) continue;
                fileList.addAll(LibraryHelper.getSkinFilesInDirectory(templateFiles[i], true));
            }
        }
        return fileList;
    }
}

