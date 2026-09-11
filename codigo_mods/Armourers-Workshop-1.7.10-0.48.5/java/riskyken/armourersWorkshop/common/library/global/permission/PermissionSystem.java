/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.MathHelper
 */
package riskyken.armourersWorkshop.common.library.global.permission;

import java.util.EnumSet;
import net.minecraft.util.MathHelper;

public final class PermissionSystem {
    public final PermissionGroup[] permissionGroups = new PermissionGroup[256];
    private final PermissionGroup groupNoLogin;
    private final PermissionGroup groupUser;
    private final PermissionGroup groupUserUploadBan;
    private final PermissionGroup groupMod;
    private final PermissionGroup groupAdmin;

    public PermissionSystem() {
        EnumSet<PlushieAction> actions = EnumSet.noneOf(PlushieAction.class);
        actions.add(PlushieAction.SKIN_DOWNLOAD);
        actions.add(PlushieAction.GET_RECENTLY_UPLOADED);
        actions.add(PlushieAction.GET_MOST_DOWNLOADED);
        actions.add(PlushieAction.GET_MOST_LIKED);
        actions.add(PlushieAction.USER_INFO);
        actions.add(PlushieAction.SKIN_SEARCH);
        actions.add(PlushieAction.SKIN_LIST_USER);
        actions.add(PlushieAction.BETA_JOIN);
        actions.add(PlushieAction.BETA_CHECK);
        actions.add(PlushieAction.SERVER_VIEW_STATS);
        this.groupNoLogin = new PermissionGroup("no login", (EnumSet<PlushieAction>)actions.clone());
        actions.add(PlushieAction.SKIN_RATE);
        actions.add(PlushieAction.SKIN_REPORT);
        actions.add(PlushieAction.SKIN_OWNER_DELETE);
        actions.add(PlushieAction.SKIN_OWNER_EDIT);
        actions.add(PlushieAction.SKIN_COMMENT_CREATE);
        actions.add(PlushieAction.SKIN_COMMENT_OWNER_DELETE);
        actions.add(PlushieAction.SKIN_COMMENT_OWNER_EDIT);
        this.groupUserUploadBan = new PermissionGroup("user_upload_ban", (EnumSet<PlushieAction>)actions.clone());
        actions.add(PlushieAction.SKIN_UPLOAD);
        this.groupUser = new PermissionGroup("user", (EnumSet<PlushieAction>)actions.clone());
        actions.add(PlushieAction.SKIN_MOD_EDIT);
        actions.add(PlushieAction.SKIN_MOD_DELETE);
        actions.add(PlushieAction.SKIN_COMMENT_MOD_DELETE);
        actions.add(PlushieAction.SKIN_COMMENT_MOD_EDIT);
        actions.add(PlushieAction.FLAG_GET_LIST);
        actions.add(PlushieAction.FLAG_DELETE);
        actions.add(PlushieAction.USER_BAN_TEMP);
        actions.add(PlushieAction.USER_BAN_PERM);
        this.groupMod = new PermissionGroup("mod", (EnumSet<PlushieAction>)actions.clone());
        this.groupAdmin = new PermissionGroup("admin", EnumSet.allOf(PlushieAction.class));
        for (int i = 0; i < this.permissionGroups.length; ++i) {
            this.permissionGroups[i] = this.groupNoLogin;
        }
        this.permissionGroups[0] = this.groupUser;
        this.permissionGroups[1] = this.groupMod;
        this.permissionGroups[2] = this.groupUserUploadBan;
        this.permissionGroups[255] = this.groupAdmin;
    }

    public PermissionGroup getNoLogin() {
        return this.groupNoLogin;
    }

    public PermissionGroup getPermissionGroup(int id) {
        if (id < 0 | id > 255) {
            return this.getNoLogin();
        }
        id = MathHelper.func_76125_a((int)id, (int)0, (int)255);
        return this.permissionGroups[id];
    }

    public static class PermissionGroup {
        private final String name;
        private final EnumSet<PlushieAction> actions;

        public PermissionGroup(String name, EnumSet<PlushieAction> actions) {
            this.name = name;
            this.actions = EnumSet.noneOf(PlushieAction.class);
            this.actions.addAll(actions);
        }

        public String getName() {
            return this.name;
        }

        public boolean havePermission(PlushieAction action) {
            return this.actions.contains((Object)action);
        }
    }

    public static enum PlushieAction {
        GET_RECENTLY_UPLOADED,
        GET_MOST_LIKED,
        GET_MOST_DOWNLOADED,
        BETA_JOIN,
        BETA_CHECK,
        SKIN_SEARCH,
        SKIN_LIST_USER,
        SKIN_DOWNLOAD,
        SKIN_UPLOAD,
        SKIN_RATE,
        SKIN_GET_RATED,
        SKIN_REPORT,
        SKIN_OWNER_DELETE,
        SKIN_MOD_DELETE,
        SKIN_OWNER_EDIT,
        SKIN_MOD_EDIT,
        SKIN_COMMENT_CREATE,
        SKIN_COMMENT_OWNER_DELETE,
        SKIN_COMMENT_MOD_DELETE,
        SKIN_COMMENT_OWNER_EDIT,
        SKIN_COMMENT_MOD_EDIT,
        FLAG_GET_LIST,
        FLAG_DELETE,
        USER_INFO,
        USER_BAN_TEMP,
        USER_BAN_PERM,
        USER_GROUP_CHANGE,
        SERVER_VIEW_STATS;

    }
}

