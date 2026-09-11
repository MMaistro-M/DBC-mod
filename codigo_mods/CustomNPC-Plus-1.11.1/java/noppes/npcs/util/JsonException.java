/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.util;

import noppes.npcs.util.NBTJsonUtil;

public class JsonException
extends Exception {
    public JsonException(String message, NBTJsonUtil.JsonFile json) {
        super(message + ": " + json.getCurrentPos());
    }
}

