/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.skin.exporter;

import java.io.File;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.exporter.ISkinExporter;
import riskyken.armourersWorkshop.common.skin.exporter.SkinExporterPolygon;
import riskyken.armourersWorkshop.utils.ModLogger;

public final class SkinExportManager {
    private SkinExportManager() {
    }

    public static ISkinExporter getSkinExporter(String fileExtension) {
        if (fileExtension == null) {
            return null;
        }
        if (fileExtension.equalsIgnoreCase("obj")) {
            // empty if block
        }
        if (fileExtension.equalsIgnoreCase("ply")) {
            return new SkinExporterPolygon();
        }
        return null;
    }

    public static void exportSkin(Skin skin, String fileExtension, File file, float scale) {
        ISkinExporter skinExporter = SkinExportManager.getSkinExporter(fileExtension);
        if (skinExporter != null) {
            SkinExportManager.exportSkin(skin, skinExporter, file, scale);
        } else {
            ModLogger.log(Level.ERROR, String.format("Could not export to %s format.", skinExporter));
        }
    }

    public static void exportSkin(Skin skin, ISkinExporter skinExporter, File file, float scale) {
        skinExporter.exportSkin(skin, file, scale);
    }
}

