/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  org.apache.commons.io.Charsets
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.library.global;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.common.library.global.DownloadUtils;
import riskyken.armourersWorkshop.common.library.global.MultipartForm;
import riskyken.armourersWorkshop.common.library.global.PlushieUser;
import riskyken.armourersWorkshop.utils.ModLogger;

public final class GlobalSkinLibraryUtils {
    private static final String BASE_URL = "http://plushie.moe/armourers_workshop/";
    private static final String USER_INFO_URL = "http://plushie.moe/armourers_workshop/user-info.php";
    private static final String USER_SKINS_URL = "http://plushie.moe/armourers_workshop/user-skins.php";
    private static final String USER_SKIN_EDIT_URL = "http://plushie.moe/armourers_workshop/user-skin-edit.php";
    private static final String USER_SKIN_DELETE_URL = "http://plushie.moe/armourers_workshop/user-skin-delete.php";
    private static final Executor JSON_DOWNLOAD_EXECUTOR = Executors.newFixedThreadPool(1);
    private static final HashMap<Integer, PlushieUser> USERS = new HashMap();
    private static final HashSet<Integer> DOWNLOADED_USERS = new HashSet();

    private GlobalSkinLibraryUtils() {
    }

    public static FutureTask<JsonArray> getUserSkinsList(Executor executor, int userId) {
        Validate.notNull(executor);
        Validate.notNull(userId);
        String searchUrl = "http://plushie.moe/armourers_workshop/user-skins.php?userId=" + String.valueOf(userId) + "&maxFileVersion=" + String.valueOf(13);
        FutureTask<JsonArray> futureTask = new FutureTask<JsonArray>(new DownloadUtils.DownloadJsonCallable(searchUrl));
        executor.execute(futureTask);
        return futureTask;
    }

    public static FutureTask<JsonObject> deleteSkin(Executor executor, int userId, String accessToken, int skinId) {
        Validate.notNull(executor);
        Validate.notNull(userId);
        Validate.notNull(accessToken);
        String searchUrl = "http://plushie.moe/armourers_workshop/user-skin-delete.php?userId=" + String.valueOf(userId) + "&accessToken=" + accessToken + "&skinId=" + String.valueOf(skinId);
        FutureTask<JsonObject> futureTask = new FutureTask<JsonObject>(new DownloadUtils.DownloadJsonObjectCallable(searchUrl));
        executor.execute(futureTask);
        return futureTask;
    }

    public static FutureTask<JsonObject> editSkin(Executor executor, int userId, String accessToken, int skinId, String name, String description) {
        Validate.notNull(executor);
        Validate.notNull(userId);
        Validate.notNull(accessToken);
        String searchUrl = "http://plushie.moe/armourers_workshop/user-skin-edit.php?userId=" + String.valueOf(userId) + "&accessToken=" + accessToken + "&skinId=" + String.valueOf(skinId);
        MultipartForm multipartForm = new MultipartForm(searchUrl);
        multipartForm.addText("name", name);
        multipartForm.addText("description", description);
        FutureTask<JsonObject> futureTask = new FutureTask<JsonObject>(new PostMultipartFormCallable(multipartForm));
        executor.execute(futureTask);
        return futureTask;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static PlushieUser getUserInfo(int userId) {
        HashMap<Integer, PlushieUser> hashMap = USERS;
        synchronized (hashMap) {
            if (USERS.containsKey(userId)) {
                return USERS.get(userId);
            }
        }
        if (!DOWNLOADED_USERS.contains(userId)) {
            DOWNLOADED_USERS.add(userId);
            JSON_DOWNLOAD_EXECUTOR.execute(new DownloadUserCallable(userId));
        }
        return null;
    }

    public static int[] getJavaVersion() {
        int[] version = new int[]{6, 0};
        try {
            int javaVersion;
            String java = System.getProperty("java.version");
            String[] javaSplit = java.split("_");
            version[1] = javaVersion = Integer.valueOf(javaSplit[1]).intValue();
            javaSplit = javaSplit[0].split("\\.");
            version[0] = Integer.valueOf(javaSplit[1]);
        }
        catch (Exception exception) {
            // empty catch block
        }
        return version;
    }

    public static boolean isValidJavaVersion(int[] javaVersion) {
        if (javaVersion[0] < 8) {
            return false;
        }
        return javaVersion[1] >= 101;
    }

    public static boolean isValidJavaVersion() {
        int[] javaVersion = GlobalSkinLibraryUtils.getJavaVersion();
        return !(javaVersion[0] < 8 & javaVersion[1] < 101);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String performPostRequest(URL url, String post, String contentType) throws IOException {
        Validate.notNull(url);
        Validate.notNull(post);
        Validate.notNull(contentType);
        HttpURLConnection connection = GlobalSkinLibraryUtils.createUrlConnection(url);
        byte[] postAsBytes = post.getBytes(Charsets.UTF_8);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", contentType + "; charset=utf-8");
        connection.setRequestProperty("Content-Length", "" + postAsBytes.length);
        connection.setDoOutput(true);
        OutputStream outputStream = null;
        try {
            outputStream = connection.getOutputStream();
            IOUtils.write((byte[])postAsBytes, (OutputStream)outputStream);
        }
        finally {
            IOUtils.closeQuietly((OutputStream)outputStream);
        }
        InputStream inputStream = null;
        try {
            String result;
            inputStream = connection.getInputStream();
            String string = result = IOUtils.toString((InputStream)inputStream, (Charset)Charsets.UTF_8);
            return string;
        }
        catch (IOException e) {
            IOUtils.closeQuietly((InputStream)inputStream);
            inputStream = connection.getErrorStream();
            if (inputStream != null) {
                String result;
                String string = result = IOUtils.toString((InputStream)inputStream, (Charset)Charsets.UTF_8);
                return string;
            }
            throw e;
        }
        finally {
            IOUtils.closeQuietly((InputStream)inputStream);
        }
    }

    private static HttpURLConnection createUrlConnection(URL url) throws IOException {
        Validate.notNull(url);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(15000);
        connection.setUseCaches(false);
        return connection;
    }

    public static class DownloadUserCallable
    implements Runnable {
        private final int userId;

        public DownloadUserCallable(int userId) {
            this.userId = userId;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void run() {
            JsonObject json = DownloadUtils.downloadJsonObject("http://plushie.moe/armourers_workshop/user-info.php?userId=" + this.userId);
            PlushieUser plushieUser = PlushieUser.readPlushieUser(json);
            if (plushieUser != null) {
                HashMap hashMap = USERS;
                synchronized (hashMap) {
                    USERS.put(this.userId, plushieUser);
                }
            } else {
                ModLogger.log(Level.ERROR, "Failed downloading info for user id: " + this.userId);
            }
        }
    }

    public static class PostMultipartFormCallable
    implements Callable<JsonObject> {
        private final MultipartForm multipartForm;

        public PostMultipartFormCallable(MultipartForm multipartForm) {
            this.multipartForm = multipartForm;
        }

        @Override
        public JsonObject call() throws Exception {
            JsonObject result = null;
            try {
                String downloadData = this.multipartForm.upload();
                ModLogger.log(downloadData);
                result = (JsonObject)new JsonParser().parse(downloadData);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}

