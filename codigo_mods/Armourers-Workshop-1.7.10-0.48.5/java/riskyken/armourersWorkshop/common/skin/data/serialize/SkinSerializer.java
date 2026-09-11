/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.Charsets
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.skin.data.serialize;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.exception.InvalidCubeTypeException;
import riskyken.armourersWorkshop.common.exception.NewerFileVersionException;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.data.serialize.SkinPartSerializer;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.utils.ModLogger;
import riskyken.armourersWorkshop.utils.StreamUtils;

public final class SkinSerializer {
    private static final String TAG_SKIN_HEADER = "AW-SKIN-START";
    private static final String TAG_SKIN_PROPS_HEADER = "PROPS-START";
    private static final String TAG_SKIN_PROPS_FOOTER = "PROPS-END";
    private static final String TAG_SKIN_TYPE_HEADER = "TYPE-START";
    private static final String TAG_SKIN_TYPE_FOOTER = "TYPE-END";
    private static final String TAG_SKIN_PAINT_HEADER = "PAINT-START";
    private static final String TAG_SKIN_PAINT_FOOTER = "PAINT-END";
    private static final String TAG_SKIN_PART_HEADER = "PART-START";
    private static final String TAG_SKIN_PART_FOOTER = "PART-END";
    private static final String TAG_SKIN_FOOTER = "AW-SKIN-END";
    private static final String KEY_TAGS = "tags";

    private SkinSerializer() {
    }

    public static void writeToStream(Skin skin, DataOutputStream stream) throws IOException {
        int i;
        stream.writeInt(13);
        StreamUtils.writeString(stream, Charsets.US_ASCII, TAG_SKIN_HEADER);
        StreamUtils.writeString(stream, Charsets.US_ASCII, TAG_SKIN_PROPS_HEADER);
        skin.getProperties().writeToStream(stream);
        StreamUtils.writeString(stream, Charsets.US_ASCII, TAG_SKIN_PROPS_FOOTER);
        StreamUtils.writeString(stream, Charsets.US_ASCII, TAG_SKIN_TYPE_HEADER);
        stream.writeUTF(skin.getSkinType().getRegistryName());
        StreamUtils.writeString(stream, Charsets.US_ASCII, TAG_SKIN_TYPE_FOOTER);
        StreamUtils.writeString(stream, Charsets.US_ASCII, TAG_SKIN_PAINT_HEADER);
        if (skin.hasPaintData()) {
            stream.writeBoolean(true);
            for (i = 0; i < 2048; ++i) {
                stream.writeInt(skin.getPaintData()[i]);
            }
        } else {
            stream.writeBoolean(false);
        }
        StreamUtils.writeString(stream, Charsets.US_ASCII, TAG_SKIN_PAINT_FOOTER);
        stream.writeByte(skin.getParts().size());
        for (i = 0; i < skin.getParts().size(); ++i) {
            SkinPart skinPart = skin.getParts().get(i);
            StreamUtils.writeString(stream, Charsets.US_ASCII, TAG_SKIN_PART_HEADER);
            SkinPartSerializer.saveSkinPart(skinPart, stream);
            StreamUtils.writeString(stream, Charsets.US_ASCII, TAG_SKIN_PART_FOOTER);
        }
        StreamUtils.writeString(stream, Charsets.US_ASCII, TAG_SKIN_FOOTER);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Skin readSkinFromStream(DataInputStream stream) throws IOException, NewerFileVersionException, InvalidCubeTypeException {
        String footer;
        String typeFooter;
        boolean hasPaintData;
        String typeFooter2;
        int fileVersion = stream.readInt();
        if (fileVersion > 13) {
            throw new NewerFileVersionException();
        }
        if (fileVersion > 12) {
            String propsHeader;
            String header = StreamUtils.readString(stream, Charsets.US_ASCII);
            if (!header.equals(TAG_SKIN_HEADER)) {
                ModLogger.log(Level.ERROR, "Error loading skin header.");
            }
            if (!(propsHeader = StreamUtils.readString(stream, Charsets.US_ASCII)).equals(TAG_SKIN_PROPS_HEADER)) {
                ModLogger.log(Level.ERROR, "Error loading skin props header.");
            }
        }
        SkinProperties properties = new SkinProperties();
        boolean loadedProps = true;
        IOException e = null;
        if (fileVersion < 12) {
            String authorName = stream.readUTF();
            String customName = stream.readUTF();
            String tags = "";
            tags = fileVersion >= 4 ? stream.readUTF() : "";
            properties.setProperty("authorName", authorName);
            properties.setProperty("customName", customName);
            if (tags != null && !tags.equalsIgnoreCase("")) {
                properties.setProperty(KEY_TAGS, tags);
            }
        } else {
            try {
                properties.readFromStream(stream, fileVersion);
            }
            catch (IOException propE) {
                ModLogger.log(Level.ERROR, "prop load failed");
                e = propE;
                loadedProps = false;
            }
        }
        if (fileVersion > 12) {
            String typeHeader;
            String propsFooter = StreamUtils.readString(stream, Charsets.US_ASCII);
            if (!propsFooter.equals(TAG_SKIN_PROPS_FOOTER)) {
                ModLogger.log(Level.ERROR, "Error loading skin props footer.");
            }
            if (!(typeHeader = StreamUtils.readString(stream, Charsets.US_ASCII)).equals(TAG_SKIN_TYPE_HEADER)) {
                ModLogger.log(Level.ERROR, "Error loading skin type header.");
            }
        }
        ISkinType equipmentSkinType = null;
        if (fileVersion < 5) {
            if (!loadedProps) throw e;
            equipmentSkinType = SkinTypeRegistry.INSTANCE.getSkinTypeFromLegacyId(stream.readByte() - 1);
        } else if (loadedProps) {
            String regName = stream.readUTF();
            if (regName.equals(SkinTypeRegistry.oldSkinSkirt.getRegistryName())) {
                regName = SkinTypeRegistry.skinLegs.getRegistryName();
            }
            equipmentSkinType = SkinTypeRegistry.INSTANCE.getSkinTypeFromRegistryName(regName);
        } else {
            StringBuilder sb = new StringBuilder();
            do {
                sb.append(new String(new byte[]{stream.readByte()}, "UTF-8"));
            } while (!sb.toString().endsWith("armourers:"));
            ModLogger.log("Got armourers");
            sb = new StringBuilder();
            sb.append("armourers:");
            while (SkinTypeRegistry.INSTANCE.getSkinTypeFromRegistryName(sb.toString()) == null) {
                sb.append(new String(new byte[]{stream.readByte()}, "UTF-8"));
            }
            ModLogger.log(sb.toString());
            equipmentSkinType = SkinTypeRegistry.INSTANCE.getSkinTypeFromRegistryName(sb.toString());
            ModLogger.log("got failed type " + equipmentSkinType);
        }
        if (fileVersion > 12 && !(typeFooter2 = StreamUtils.readString(stream, Charsets.US_ASCII)).equals(TAG_SKIN_TYPE_FOOTER)) {
            ModLogger.log(Level.ERROR, "Error loading skin type footer.");
        }
        if (equipmentSkinType == null) {
            throw new InvalidCubeTypeException();
        }
        if (fileVersion > 12 && !(typeFooter2 = StreamUtils.readString(stream, Charsets.US_ASCII)).equals(TAG_SKIN_PAINT_HEADER)) {
            ModLogger.log(Level.ERROR, "Error loading skin paint header.");
        }
        int[] paintData = null;
        if (fileVersion > 7 && (hasPaintData = stream.readBoolean())) {
            paintData = new int[2048];
            for (int i = 0; i < 2048; ++i) {
                paintData[i] = stream.readInt();
            }
        }
        if (fileVersion > 12 && !(typeFooter = StreamUtils.readString(stream, Charsets.US_ASCII)).equals(TAG_SKIN_PAINT_FOOTER)) {
            ModLogger.log(Level.ERROR, "Error loading skin paint footer.");
        }
        int size = stream.readByte();
        ArrayList<SkinPart> parts = new ArrayList<SkinPart>();
        for (int i = 0; i < size; ++i) {
            String partFooter;
            String partHeader;
            if (fileVersion > 12 && !(partHeader = StreamUtils.readString(stream, Charsets.US_ASCII)).equals(TAG_SKIN_PART_HEADER)) {
                ModLogger.log(Level.ERROR, "Error loading skin part header.");
            }
            SkinPart part = SkinPartSerializer.loadSkinPart(stream, fileVersion);
            if (fileVersion > 12 && !(partFooter = StreamUtils.readString(stream, Charsets.US_ASCII)).equals(TAG_SKIN_PART_FOOTER)) {
                ModLogger.log(Level.ERROR, "Error loading skin part footer.");
            }
            parts.add(part);
        }
        if (fileVersion > 12 && !(footer = StreamUtils.readString(stream, Charsets.US_ASCII)).equals(TAG_SKIN_FOOTER)) {
            ModLogger.log(Level.ERROR, "Error loading skin footer.");
        }
        if (SkinProperties.PROP_MODEL_OVERRIDE.getValue(properties).booleanValue()) {
            if (equipmentSkinType == SkinTypeRegistry.skinHead) {
                SkinProperties.PROP_MODEL_OVERRIDE_HEAD.setValue(properties, true);
            }
            if (equipmentSkinType == SkinTypeRegistry.skinChest) {
                SkinProperties.PROP_MODEL_OVERRIDE_CHEST.setValue(properties, true);
                SkinProperties.PROP_MODEL_OVERRIDE_ARM_LEFT.setValue(properties, true);
                SkinProperties.PROP_MODEL_OVERRIDE_ARM_RIGHT.setValue(properties, true);
            }
            if (equipmentSkinType == SkinTypeRegistry.skinLegs) {
                SkinProperties.PROP_MODEL_OVERRIDE_LEG_LEFT.setValue(properties, true);
                SkinProperties.PROP_MODEL_OVERRIDE_LEG_RIGHT.setValue(properties, true);
            }
            if (equipmentSkinType == SkinTypeRegistry.skinFeet) {
                SkinProperties.PROP_MODEL_OVERRIDE_LEG_LEFT.setValue(properties, true);
                SkinProperties.PROP_MODEL_OVERRIDE_LEG_RIGHT.setValue(properties, true);
            }
            SkinProperties.PROP_MODEL_OVERRIDE.clearValue(properties);
        }
        if (!SkinProperties.PROP_MODEL_HIDE_OVERLAY.getValue(properties).booleanValue()) return new Skin(properties, equipmentSkinType, paintData, parts);
        if (equipmentSkinType == SkinTypeRegistry.skinHead) {
            SkinProperties.PROP_MODEL_HIDE_OVERLAY_HEAD.setValue(properties, true);
        }
        if (equipmentSkinType == SkinTypeRegistry.skinChest) {
            SkinProperties.PROP_MODEL_HIDE_OVERLAY_CHEST.setValue(properties, true);
            SkinProperties.PROP_MODEL_HIDE_OVERLAY_ARM_LEFT.setValue(properties, true);
            SkinProperties.PROP_MODEL_HIDE_OVERLAY_ARM_RIGHT.setValue(properties, true);
        }
        if (equipmentSkinType == SkinTypeRegistry.skinLegs) {
            SkinProperties.PROP_MODEL_HIDE_OVERLAY_LEG_LEFT.setValue(properties, true);
            SkinProperties.PROP_MODEL_HIDE_OVERLAY_LEG_RIGHT.setValue(properties, true);
        }
        if (equipmentSkinType == SkinTypeRegistry.skinFeet) {
            SkinProperties.PROP_MODEL_HIDE_OVERLAY_LEG_LEFT.setValue(properties, true);
            SkinProperties.PROP_MODEL_HIDE_OVERLAY_LEG_RIGHT.setValue(properties, true);
        }
        SkinProperties.PROP_MODEL_HIDE_OVERLAY.clearValue(properties);
        return new Skin(properties, equipmentSkinType, paintData, parts);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static ISkinType readSkinTypeNameFromStream(DataInputStream stream) throws IOException, NewerFileVersionException {
        int fileVersion = stream.readInt();
        if (fileVersion > 13) {
            throw new NewerFileVersionException();
        }
        if (fileVersion > 12) {
            String propsHeader;
            String header = StreamUtils.readString(stream, Charsets.US_ASCII);
            if (!header.equals(TAG_SKIN_HEADER)) {
                ModLogger.log(Level.ERROR, "Error loading skin header.");
            }
            if (!(propsHeader = StreamUtils.readString(stream, Charsets.US_ASCII)).equals(TAG_SKIN_PROPS_HEADER)) {
                ModLogger.log(Level.ERROR, "Error loading skin props header.");
            }
        }
        SkinProperties properties = new SkinProperties();
        boolean loadedProps = true;
        IOException e = null;
        if (fileVersion < 12) {
            String authorName = stream.readUTF();
            String customName = stream.readUTF();
            String tags = "";
            tags = fileVersion >= 4 ? stream.readUTF() : "";
            properties.setProperty("authorName", authorName);
            properties.setProperty("customName", customName);
            if (tags != null && !tags.equalsIgnoreCase("")) {
                properties.setProperty(KEY_TAGS, tags);
            }
        } else {
            try {
                properties.readFromStream(stream, fileVersion);
            }
            catch (IOException propE) {
                ModLogger.log(Level.ERROR, "prop load failed");
                e = propE;
                loadedProps = false;
            }
        }
        if (fileVersion > 12) {
            String typeHeader;
            String propsFooter = StreamUtils.readString(stream, Charsets.US_ASCII);
            if (!propsFooter.equals(TAG_SKIN_PROPS_FOOTER)) {
                ModLogger.log(Level.ERROR, "Error loading skin props footer.");
            }
            if (!(typeHeader = StreamUtils.readString(stream, Charsets.US_ASCII)).equals(TAG_SKIN_TYPE_HEADER)) {
                ModLogger.log(Level.ERROR, "Error loading skin type header.");
            }
        }
        ISkinType equipmentSkinType = null;
        if (fileVersion < 5) {
            if (!loadedProps) throw e;
            return SkinTypeRegistry.INSTANCE.getSkinTypeFromLegacyId(stream.readByte() - 1);
        }
        if (loadedProps) {
            String regName = stream.readUTF();
            if (!regName.equals(SkinTypeRegistry.oldSkinSkirt.getRegistryName())) return SkinTypeRegistry.INSTANCE.getSkinTypeFromRegistryName(regName);
            regName = SkinTypeRegistry.skinLegs.getRegistryName();
            return SkinTypeRegistry.INSTANCE.getSkinTypeFromRegistryName(regName);
        }
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(new String(new byte[]{stream.readByte()}, "UTF-8"));
        } while (!sb.toString().endsWith("armourers:"));
        ModLogger.log("Got armourers");
        sb = new StringBuilder();
        sb.append("armourers:");
        while (SkinTypeRegistry.INSTANCE.getSkinTypeFromRegistryName(sb.toString()) == null) {
            sb.append(new String(new byte[]{stream.readByte()}, "UTF-8"));
        }
        ModLogger.log(sb.toString());
        equipmentSkinType = SkinTypeRegistry.INSTANCE.getSkinTypeFromRegistryName(sb.toString());
        ModLogger.log("got failed type " + equipmentSkinType);
        return equipmentSkinType;
    }
}

