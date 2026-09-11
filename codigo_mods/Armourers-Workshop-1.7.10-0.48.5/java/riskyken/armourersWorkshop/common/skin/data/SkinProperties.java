/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  org.apache.commons.io.IOUtils
 */
package riskyken.armourersWorkshop.common.skin.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.commons.io.IOUtils;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinProperties;
import riskyken.armourersWorkshop.common.skin.data.SkinProperty;
import riskyken.armourersWorkshop.common.skin.type.wings.SkinWings;
import riskyken.armourersWorkshop.utils.StreamUtils;

public class SkinProperties
implements ISkinProperties {
    public static final SkinProperty<String> PROP_ALL_CUSTOM_NAME = new SkinProperty<String>("customName", "");
    public static final SkinProperty<String> PROP_ALL_FLAVOUR_TEXT = new SkinProperty<String>("flavour", "");
    public static final SkinProperty<String> PROP_ALL_AUTHOR_NAME = new SkinProperty<String>("authorName", "");
    public static final SkinProperty<String> PROP_ALL_AUTHOR_UUID = new SkinProperty<String>("authorUUID", "");
    @Deprecated
    public static final SkinProperty<Boolean> PROP_MODEL_OVERRIDE = new SkinProperty<Boolean>("armourOverride", false);
    public static final SkinProperty<Boolean> PROP_MODEL_OVERRIDE_HEAD = new SkinProperty<Boolean>("overrideModelHead", false);
    public static final SkinProperty<Boolean> PROP_MODEL_OVERRIDE_CHEST = new SkinProperty<Boolean>("overrideModelChest", false);
    public static final SkinProperty<Boolean> PROP_MODEL_OVERRIDE_ARM_LEFT = new SkinProperty<Boolean>("overrideModelArmLeft", false);
    public static final SkinProperty<Boolean> PROP_MODEL_OVERRIDE_ARM_RIGHT = new SkinProperty<Boolean>("overrideModelArmRight", false);
    public static final SkinProperty<Boolean> PROP_MODEL_OVERRIDE_LEG_LEFT = new SkinProperty<Boolean>("overrideModelLegLeft", false);
    public static final SkinProperty<Boolean> PROP_MODEL_OVERRIDE_LEG_RIGHT = new SkinProperty<Boolean>("overrideModelLegRight", false);
    @Deprecated
    public static final SkinProperty<Boolean> PROP_MODEL_HIDE_OVERLAY = new SkinProperty<Boolean>("armourHideOverlay", false);
    public static final SkinProperty<Boolean> PROP_MODEL_HIDE_OVERLAY_HEAD = new SkinProperty<Boolean>("hideOverlayHead", false);
    public static final SkinProperty<Boolean> PROP_MODEL_HIDE_OVERLAY_CHEST = new SkinProperty<Boolean>("hideOverlayChest", false);
    public static final SkinProperty<Boolean> PROP_MODEL_HIDE_OVERLAY_ARM_LEFT = new SkinProperty<Boolean>("hideOverlayArmLeft", false);
    public static final SkinProperty<Boolean> PROP_MODEL_HIDE_OVERLAY_ARM_RIGHT = new SkinProperty<Boolean>("hideOverlayArmRight", false);
    public static final SkinProperty<Boolean> PROP_MODEL_HIDE_OVERLAY_LEG_LEFT = new SkinProperty<Boolean>("hideOverlayLegLeft", false);
    public static final SkinProperty<Boolean> PROP_MODEL_HIDE_OVERLAY_LEG_RIGHT = new SkinProperty<Boolean>("hideOverlayLegRight", false);
    public static final SkinProperty<Boolean> PROP_MODEL_LEGS_LIMIT_LIMBS = new SkinProperty<Boolean>("limitLimbs", false);
    public static final SkinProperty<String> PROP_OUTFIT_PART_INDEXS = new SkinProperty<String>("partIndexs", "");
    public static final SkinProperty<Boolean> PROP_BLOCK_GLOWING = new SkinProperty<Boolean>("blockGlowing", false);
    public static final SkinProperty<Boolean> PROP_BLOCK_LADDER = new SkinProperty<Boolean>("blockLadder", false);
    public static final SkinProperty<Boolean> PROP_BLOCK_NO_COLLISION = new SkinProperty<Boolean>("blockNoCollision", false);
    public static final SkinProperty<Boolean> PROP_BLOCK_SEAT = new SkinProperty<Boolean>("blockSeat", false);
    public static final SkinProperty<Boolean> PROP_BLOCK_MULTIBLOCK = new SkinProperty<Boolean>("blockMultiblock", false);
    public static final SkinProperty<Boolean> PROP_BLOCK_BED = new SkinProperty<Boolean>("blockBed", false);
    public static final SkinProperty<Boolean> PROP_BLOCK_INVENTORY = new SkinProperty<Boolean>("blockInventory", false);
    public static final SkinProperty<Boolean> PROP_BLOCK_ENDER_INVENTORY = new SkinProperty<Boolean>("blockEnderInventory", false);
    public static final SkinProperty<Integer> PROP_BLOCK_INVENTORY_WIDTH = new SkinProperty<Integer>("blockInventoryWidth", 9);
    public static final SkinProperty<Integer> PROP_BLOCK_INVENTORY_HEIGHT = new SkinProperty<Integer>("blockInventoryHeight", 4);
    public static final SkinProperty<Double> PROP_WINGS_MAX_ANGLE = new SkinProperty<Double>("wingsMaxAngle", 75.0);
    public static final SkinProperty<Double> PROP_WINGS_MIN_ANGLE = new SkinProperty<Double>("wingsMinAngle", 0.0);
    public static final SkinProperty<Double> PROP_WINGS_IDLE_SPEED = new SkinProperty<Double>("wingsIdleSpeed", 6000.0);
    public static final SkinProperty<Double> PROP_WINGS_FLYING_SPEED = new SkinProperty<Double>("wingsFlyingSpeed", 350.0);
    public static final SkinProperty<String> PROP_WINGS_MOVMENT_TYPE = new SkinProperty<String>("wingsMovmentType", SkinWings.MovementType.EASE.toString());
    private static final String TAG_SKIN_PROPS = "skinProps";
    private final LinkedHashMap<String, Object> properties;

    public SkinProperties() {
        this.properties = new LinkedHashMap();
    }

    public SkinProperties(SkinProperties skinProps) {
        this.properties = (LinkedHashMap)skinProps.properties.clone();
    }

    public ArrayList<String> getPropertiesList() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < this.properties.size(); ++i) {
            String key = (String)this.properties.keySet().toArray()[i];
            list.add(key + ":" + this.properties.get(key));
        }
        return list;
    }

    public void writeToStream(DataOutputStream stream) throws IOException {
        stream.writeInt(this.properties.size());
        for (int i = 0; i < this.properties.size(); ++i) {
            String key = (String)this.properties.keySet().toArray()[i];
            Object value = this.properties.get(key);
            StreamUtils.writeStringUtf8(stream, key);
            if (value instanceof String) {
                stream.writeByte(DataTypes.STRING.ordinal());
                StreamUtils.writeStringUtf8(stream, (String)value);
            }
            if (value instanceof Integer) {
                stream.writeByte(DataTypes.INT.ordinal());
                stream.writeInt((Integer)value);
            }
            if (value instanceof Double) {
                stream.writeByte(DataTypes.DOUBLE.ordinal());
                stream.writeDouble((Double)value);
            }
            if (!(value instanceof Boolean)) continue;
            stream.writeByte(DataTypes.BOOLEAN.ordinal());
            stream.writeBoolean((Boolean)value);
        }
    }

    public void readFromStream(DataInputStream stream, int fileVersion) throws IOException {
        int count = stream.readInt();
        for (int i = 0; i < count; ++i) {
            String key = null;
            key = fileVersion > 12 ? StreamUtils.readStringUtf8(stream) : stream.readUTF();
            byte byteType = stream.readByte();
            DataTypes type = DataTypes.STRING;
            if (!(byteType >= 0 & byteType < DataTypes.values().length)) {
                throw new IOException("Error loading skin properties " + byteType);
            }
            type = DataTypes.values()[byteType];
            Object value = null;
            switch (type) {
                case STRING: {
                    if (fileVersion > 12) {
                        value = StreamUtils.readStringUtf8(stream);
                        break;
                    }
                    value = stream.readUTF();
                    break;
                }
                case INT: {
                    value = stream.readInt();
                    break;
                }
                case DOUBLE: {
                    value = stream.readDouble();
                    break;
                }
                case BOOLEAN: {
                    value = stream.readBoolean();
                }
            }
            this.properties.put(key, value);
        }
    }

    @Override
    public void removeProperty(String key) {
        this.properties.remove(key);
    }

    @Override
    public void setProperty(String key, Object value) {
        this.properties.put(key, value);
    }

    @Override
    public String getPropertyString(String key, String defaultValue) {
        Object value = this.properties.get(key);
        if (value != null && value instanceof String) {
            return (String)value;
        }
        return defaultValue;
    }

    @Override
    public int getPropertyInt(String key, int defaultValue) {
        Object value = this.properties.get(key);
        if (value != null && value instanceof Integer) {
            return (Integer)value;
        }
        return defaultValue;
    }

    @Override
    public double getPropertyDouble(String key, double defaultValue) {
        Object value = this.properties.get(key);
        if (value != null && value instanceof Double) {
            return (Double)value;
        }
        return defaultValue;
    }

    @Override
    public Boolean getPropertyBoolean(String key, Boolean defaultValue) {
        Object value = this.properties.get(key);
        if (value != null && value instanceof Boolean) {
            return (Boolean)value;
        }
        return defaultValue;
    }

    @Override
    public Object getProperty(String key, Object defaultValue) {
        Object value = this.properties.get(key);
        if (value != null) {
            return value;
        }
        return defaultValue;
    }

    @Override
    public boolean haveProperty(String key) {
        return this.properties.containsKey(key);
    }

    public int hashCode() {
        return this.toString().hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        SkinProperties other = (SkinProperties)obj;
        return !(this.properties == null ? other.properties != null : !this.properties.equals(other.properties));
    }

    public String toString() {
        return "SkinProperties [properties=" + this.properties + "]";
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void readFromNBT(NBTTagCompound compound) {
        if (!compound.func_74764_b(TAG_SKIN_PROPS)) {
            return;
        }
        byte[] data = compound.func_74770_j(TAG_SKIN_PROPS);
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream dataInputStream = new DataInputStream(bais);
        try {
            this.readFromStream(dataInputStream, 13);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly((InputStream)dataInputStream);
            IOUtils.closeQuietly((InputStream)bais);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void writeToNBT(NBTTagCompound compound) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(baos);
        try {
            this.writeToStream(dataOutputStream);
            dataOutputStream.flush();
            byte[] data = baos.toByteArray();
            compound.func_74773_a(TAG_SKIN_PROPS, data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly((OutputStream)dataOutputStream);
            IOUtils.closeQuietly((OutputStream)baos);
        }
    }

    private static enum DataTypes {
        STRING,
        INT,
        DOUBLE,
        BOOLEAN;

    }
}

