/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  org.apache.commons.io.Charsets
 *  org.apache.commons.io.IOUtils
 */
package riskyken.armourersWorkshop.client.skin.cache;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinIdentifier;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;
import riskyken.armourersWorkshop.common.network.ByteBufHelper;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.utils.SerializeHelper;

public class FastCache {
    public static final FastCache INSTANCE = new FastCache();
    private static final String VERTEX_EXTENSION = ".vertex";
    private static final String DATA_FILE_NAME = "cache.json";
    private static final Object IO_LOCK = new Object();
    private static final Object FILE_MAP_LOCK = new Object();
    private final LinkedHashMap<String, CacheFile> fileMap = new LinkedHashMap();

    public FastCache() {
        if (!this.getCacheDirectory().exists()) {
            this.getCacheDirectory().mkdir();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void removeOldFiles() {
        Object object = FILE_MAP_LOCK;
        synchronized (object) {
            int removeCount = this.fileMap.size() - ConfigHandlerClient.fastCacheSize;
            if (removeCount > 0) {
                Object[] cacheFiles = this.fileMap.values().toArray(new CacheFile[this.fileMap.size()]);
                Arrays.sort(cacheFiles);
                for (int i = 0; i < removeCount; ++i) {
                    Object cacheFile = cacheFiles[i];
                    File file = new File(this.getCacheDirectory(), ((CacheFile)cacheFile).fileName + VERTEX_EXTENSION);
                    if (!file.exists()) continue;
                    try {
                        file.delete();
                        this.fileMap.remove(((CacheFile)cacheFile).fileName);
                        continue;
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void saveCacheData() {
        Object object = FILE_MAP_LOCK;
        synchronized (object) {
            File dataFile = this.getDataFile();
            JsonArray json = new JsonArray();
            for (CacheFile cacheFile : this.fileMap.values()) {
                json.add(CacheFile.Storage.serialize(cacheFile));
            }
            SerializeHelper.writeJsonFile(dataFile, Charsets.UTF_8, (JsonElement)json);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void loadCacheData() {
        Object object = FILE_MAP_LOCK;
        synchronized (object) {
            this.fileMap.clear();
            File dataFile = this.getDataFile();
            if (dataFile.exists()) {
                try {
                    JsonArray json = SerializeHelper.readJsonFile(dataFile, Charsets.UTF_8).getAsJsonArray();
                    for (int i = 0; i < json.size(); ++i) {
                        CacheFile cacheFile = CacheFile.Storage.deserialize((JsonElement)json.get(i).getAsJsonObject());
                        if (cacheFile == null) continue;
                        this.fileMap.put(cacheFile.fileName, cacheFile);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private File getCacheDirectory() {
        return new File(ArmourersWorkshop.getProxy().getModDirectory(), "fast-cache");
    }

    private File getDataFile() {
        return new File(this.getCacheDirectory(), DATA_FILE_NAME);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean containsFile(ISkinIdentifier identifier) {
        boolean exists;
        Object object = IO_LOCK;
        synchronized (object) {
            exists = new File(this.getCacheDirectory(), identifier.hashCode() + VERTEX_EXTENSION).exists();
        }
        return exists;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void saveSkin(Skin skin) {
        if (skin.requestId == null) {
            return;
        }
        if (skin.requestId.hasLibraryFile()) {
            return;
        }
        File file = new File(this.getCacheDirectory(), skin.requestId.hashCode() + VERTEX_EXTENSION);
        Object object = IO_LOCK;
        synchronized (object) {
            block14: {
                if (!file.exists()) {
                    byte[] data = ByteBufHelper.convertSkinToByteArray(skin);
                    data = ByteBufHelper.compressedByteArray(data);
                    FileOutputStream fos = null;
                    BufferedOutputStream bus = null;
                    try {
                        fos = new FileOutputStream(file);
                        bus = new BufferedOutputStream(fos);
                        ArrayUtils.reverse(data);
                        bus.write(data);
                        bus.flush();
                        Object object2 = FILE_MAP_LOCK;
                        synchronized (object2) {
                            CacheFile cacheFile = new CacheFile(String.valueOf(skin.requestId.hashCode()));
                            this.fileMap.put(cacheFile.fileName, cacheFile);
                        }
                        this.removeOldFiles();
                        this.saveCacheData();
                        IOUtils.closeQuietly((OutputStream)bus);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        break block14;
                    }
                    finally {
                        IOUtils.closeQuietly(bus);
                        IOUtils.closeQuietly((OutputStream)fos);
                    }
                    IOUtils.closeQuietly((OutputStream)fos);
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Skin loadSkin(ISkinIdentifier identifier) {
        if (ConfigHandlerClient.fastCacheSize < 1) {
            return null;
        }
        File file = new File(this.getCacheDirectory(), identifier.hashCode() + VERTEX_EXTENSION);
        Skin skin = null;
        Object object = IO_LOCK;
        synchronized (object) {
            block13: {
                if (file.exists()) {
                    FileInputStream fis = null;
                    BufferedInputStream bis = null;
                    try {
                        fis = new FileInputStream(file);
                        bis = new BufferedInputStream(fis);
                        byte[] data = IOUtils.toByteArray((InputStream)bis);
                        ArrayUtils.reverse(data);
                        data = ByteBufHelper.decompressByteArray(data);
                        skin = ByteBufHelper.convertByteArrayToSkin(data);
                        Object object2 = FILE_MAP_LOCK;
                        synchronized (object2) {
                            CacheFile cacheFile = new CacheFile(String.valueOf(identifier.hashCode()));
                            this.fileMap.put(cacheFile.fileName, cacheFile);
                        }
                        this.saveCacheData();
                        IOUtils.closeQuietly((InputStream)bis);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        break block13;
                    }
                    finally {
                        IOUtils.closeQuietly(bis);
                        IOUtils.closeQuietly((InputStream)fis);
                    }
                    IOUtils.closeQuietly((InputStream)fis);
                }
            }
        }
        return skin;
    }

    private static class CacheFile
    implements Comparable<CacheFile> {
        private final String fileName;
        private Date lastAccess;

        public CacheFile(String fileName) {
            this.fileName = fileName;
            this.updateLastAccess();
        }

        public CacheFile(String fileName, Date lastAccess) {
            this.fileName = fileName;
            this.lastAccess = lastAccess;
        }

        public void setLastAccess(Date lastAccess) {
            this.lastAccess = lastAccess;
        }

        public void updateLastAccess() {
            this.lastAccess = Calendar.getInstance().getTime();
        }

        public int hashCode() {
            return this.fileName.hashCode();
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
            CacheFile other = (CacheFile)obj;
            return !(this.fileName == null ? other.fileName != null : !this.fileName.equals(other.fileName));
        }

        @Override
        public int compareTo(CacheFile o) {
            return this.lastAccess.compareTo(o.lastAccess);
        }

        private static class Storage {
            private static final String TAG_FILENAME = "filename";
            private static final String TAG_LAST_ACCESS = "lastAccess";
            private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss", Locale.ENGLISH);

            private Storage() {
            }

            public static CacheFile deserialize(JsonElement json) throws JsonParseException {
                String fileName = null;
                Date lastAccess = null;
                fileName = json.getAsJsonObject().get(TAG_FILENAME).getAsString();
                try {
                    lastAccess = SDF.parse(json.getAsJsonObject().get(TAG_LAST_ACCESS).getAsString());
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                return new CacheFile(fileName, lastAccess);
            }

            public static JsonElement serialize(CacheFile src) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(TAG_FILENAME, src.fileName);
                jsonObject.addProperty(TAG_LAST_ACCESS, SDF.format(src.lastAccess));
                return jsonObject;
            }
        }
    }
}

