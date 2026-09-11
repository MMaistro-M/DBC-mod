/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package riskyken.armourersWorkshop.common.library.global;

import com.google.gson.JsonObject;
import java.util.UUID;

public class PlushieUser {
    private int id;
    private UUID uuid;
    private String username;
    private int permissionGroupId;

    public static PlushieUser readPlushieUser(JsonObject json) {
        if (json != null && json.has("valid") && json.get("valid").getAsBoolean() && json.has("id") & json.has("uuid") & json.has("username") & json.has("permission_group_id")) {
            int id = json.get("id").getAsInt();
            UUID uuid = null;
            try {
                uuid = UUID.fromString(json.get("uuid").getAsString());
            }
            catch (IllegalArgumentException e) {
                return null;
            }
            String username = json.get("username").getAsString();
            int permission_group_id = json.get("permission_group_id").getAsInt();
            return new PlushieUser(id, uuid, username, permission_group_id);
        }
        return null;
    }

    private PlushieUser(int id, UUID uuid, String username, int permissionGroupId) {
        this.id = id;
        this.uuid = uuid;
        this.username = username;
        this.permissionGroupId = permissionGroupId;
    }

    public int getId() {
        return this.id;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public String getUsername() {
        return this.username;
    }

    public int getPermissionGroupId() {
        return this.permissionGroupId;
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + this.id;
        return result;
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
        PlushieUser other = (PlushieUser)obj;
        return this.id == other.id;
    }
}

