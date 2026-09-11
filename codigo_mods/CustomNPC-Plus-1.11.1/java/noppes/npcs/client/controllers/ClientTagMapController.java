/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.controllers;

import java.io.File;
import noppes.npcs.CustomNpcs;
import noppes.npcs.controllers.ServerTagMapController;

public class ClientTagMapController
extends ServerTagMapController {
    public static ClientTagMapController Instance;

    @Override
    public File getDir() {
        File dir = new File(CustomNpcs.Dir, "clones");
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }
}

