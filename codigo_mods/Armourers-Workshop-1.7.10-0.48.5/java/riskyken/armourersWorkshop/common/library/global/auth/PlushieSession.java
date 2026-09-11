/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package riskyken.armourersWorkshop.common.library.global.auth;

import com.google.gson.JsonObject;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.common.library.global.permission.PermissionSystem;
import riskyken.armourersWorkshop.utils.ModLogger;

public class PlushieSession {
    private PermissionSystem.PermissionGroup permissionGroup;
    private boolean isAuth;
    private int server_id;
    private String mc_id;
    private String mc_name;
    private String accessToken;
    private long accessTokenReceivedTime;
    private int accessTokenExpiryTime;
    private int permission_group_id = -1;

    public PlushieSession() {
        this.updatePermissionGroup();
        this.server_id = 0;
    }

    public boolean authenticate(JsonObject jsonObject) {
        if (jsonObject != null && jsonObject.has("valid") && jsonObject.get("valid").getAsBoolean()) {
            this.server_id = jsonObject.get("server_id").getAsInt();
            this.mc_id = jsonObject.get("mc_id").getAsString();
            this.mc_name = jsonObject.get("mc_name").getAsString();
            this.setPermission_group_id(jsonObject.get("permission_group_id").getAsInt());
            this.updateToken(jsonObject);
            return true;
        }
        return false;
    }

    public void updateToken(JsonObject json) {
        if (json != null && json.has("valid") && json.has("valid") && json.has("accessToken") & json.has("expiryTime")) {
            this.setAccessToken(json.get("accessToken").getAsString(), json.get("expiryTime").getAsInt());
            this.isAuth = true;
            return;
        }
        this.isAuth = false;
    }

    public int getServerId() {
        return this.server_id;
    }

    public boolean isOwner(int userId) {
        return this.server_id == userId;
    }

    public void setPermission_group_id(int permission_group_id) {
        this.permission_group_id = permission_group_id;
        this.updatePermissionGroup();
    }

    public void setServerId(int serverId) {
        this.server_id = serverId;
    }

    public boolean hasServerId() {
        return this.server_id > 0;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public boolean isAuthenticated() {
        return this.isAuth && this.accessTokenReceivedTime + (long)(this.accessTokenExpiryTime * 1000) > System.currentTimeMillis();
    }

    public int getTokenExpiryTime() {
        return this.accessTokenExpiryTime * 1000 - this.getTimeSinceTokenUpdate();
    }

    public int getTimeSinceTokenUpdate() {
        return (int)(System.currentTimeMillis() - this.accessTokenReceivedTime);
    }

    public void setAccessToken(String accessToken, int expiryTime) {
        this.accessToken = accessToken;
        this.accessTokenExpiryTime = expiryTime;
        this.accessTokenReceivedTime = System.currentTimeMillis();
    }

    private void updatePermissionGroup() {
        PermissionSystem ps = ArmourersWorkshop.getProxy().getPermissionSystem();
        if (!this.isAuthenticated()) {
            this.permissionGroup = ps.getNoLogin();
        }
        this.permissionGroup = ps.getPermissionGroup(this.permission_group_id);
        ModLogger.log("Permission group set to " + this.permissionGroup.getName());
    }

    public boolean hasPermission(PermissionSystem.PlushieAction action) {
        return this.permissionGroup.havePermission(action);
    }
}

