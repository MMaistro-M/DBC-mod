/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package riskyken.armourersWorkshop.common.library.global;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import riskyken.armourersWorkshop.common.library.global.MultipartForm;
import riskyken.armourersWorkshop.utils.ModLogger;

public final class SkinUploader {
    private static final Executor SKIN_UPLOAD_EXECUTOR = Executors.newFixedThreadPool(1);
    private static final String UPLOAD_URL = "https://plushie.moe/armourers_workshop/user-skin-upload.php";

    public static FutureTask<JsonObject> uploadSkin(byte[] file, String name, String userId, String description, String accessToken) {
        FutureTask<JsonObject> futureTask = new FutureTask<JsonObject>(new SkinUploadCallable(file, name, userId, description, accessToken));
        SKIN_UPLOAD_EXECUTOR.execute(futureTask);
        return futureTask;
    }

    private static String doSkinUpload(byte[] file, String name, String userId, String description, String accessToken) throws IOException {
        MultipartForm multipartForm = new MultipartForm(UPLOAD_URL);
        multipartForm.addText("name", name);
        multipartForm.addText("userId", userId);
        multipartForm.addText("description", description);
        multipartForm.addText("accessToken", accessToken);
        multipartForm.addFile("fileToUpload", name, file);
        return multipartForm.upload();
    }

    public static class SkinUploadCallable
    implements Callable<JsonObject> {
        private byte[] file;
        private String name;
        private String userId;
        private String description;
        private String accessToken;

        public SkinUploadCallable(byte[] file, String name, String userId, String description, String accessToken) {
            this.file = file;
            this.name = name;
            this.userId = userId;
            this.description = description;
            this.accessToken = accessToken;
        }

        @Override
        public JsonObject call() throws Exception {
            String result = SkinUploader.doSkinUpload(this.file, this.name, this.userId, this.description, this.accessToken);
            ModLogger.log(result);
            JsonObject json = null;
            try {
                json = (JsonObject)new JsonParser().parse(result);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return json;
        }
    }
}

