/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.attribute.requirement.types;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.UUID;
import kamkeel.npcs.controllers.ProfileController;
import kamkeel.npcs.controllers.data.attribute.requirement.IRequirementChecker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class SoulbindRequirement
implements IRequirementChecker {
    public static HashMap<String, String> uuidToUsername = new HashMap();

    @Override
    public String getKey() {
        return "cnpc_soulbind";
    }

    @Override
    public String getTranslation() {
        return "requirement.soulbind";
    }

    @Override
    public String getTooltipValue(NBTTagCompound nbt) {
        String uuid;
        if (nbt.func_74764_b(this.getKey()) && (uuid = nbt.func_74779_i(this.getKey())) != null && !uuid.isEmpty()) {
            return this.getUsernameFromUUID(uuid);
        }
        return "null";
    }

    private String getUsernameFromUUID(String uuid) {
        if (uuidToUsername.containsKey(uuid) && !uuidToUsername.get(uuid).equals("null")) {
            return uuidToUsername.get(uuid);
        }
        try {
            String strippedUUID = uuid.replace("-", "");
            String urlStr = "https://sessionserver.mojang.com/session/minecraft/profile/" + strippedUUID;
            HttpURLConnection connection = (HttpURLConnection)new URL(urlStr).openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream stream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            JsonElement element = new JsonParser().parse((Reader)reader);
            JsonObject jsonObj = element.getAsJsonObject();
            if (jsonObj.has("name")) {
                String name = jsonObj.get("name").getAsString();
                uuidToUsername.put(uuid, name);
                return name;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "null";
    }

    @Override
    public Object getValue(NBTTagCompound nbt) {
        if (nbt.func_74764_b(this.getKey())) {
            return nbt.func_74779_i(this.getKey());
        }
        return null;
    }

    @Override
    public void apply(NBTTagCompound nbt, Object value) {
        if (value instanceof String) {
            String entry = (String)value;
            if (entry.isEmpty()) {
                return;
            }
            UUID uuid = null;
            try {
                uuid = UUID.fromString(entry);
            }
            catch (Exception exception) {
                // empty catch block
            }
            if (uuid == null) {
                uuid = ProfileController.Instance.getUUIDFromUsername(entry);
            }
            if (uuid != null) {
                nbt.func_74778_a(this.getKey(), uuid.toString());
            }
        }
    }

    @Override
    public boolean check(EntityPlayer player, NBTTagCompound nbt) {
        if (nbt.func_74764_b(this.getKey())) {
            String uuidString = nbt.func_74779_i(this.getKey());
            if (uuidString != null && !uuidString.isEmpty()) {
                UUID convert = null;
                try {
                    convert = UUID.fromString(uuidString);
                }
                catch (Exception exception) {
                    // empty catch block
                }
                if (convert != null) {
                    return player.func_110124_au().equals(convert);
                }
            }
            return false;
        }
        return true;
    }
}

