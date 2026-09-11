/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.painting.tool;

import riskyken.armourersWorkshop.common.painting.tool.ToolOptionCheck;
import riskyken.armourersWorkshop.common.painting.tool.ToolOptionIntensity;
import riskyken.armourersWorkshop.common.painting.tool.ToolOptionRadius;

public class ToolOptions {
    private static final String TAG_FULL_BLOCK_MODE = "fullBlockMode";
    private static final String TAG_CHANGE_HUE = "changeHue";
    private static final String TAG_CHANGE_SATURATION = "changeSaturation";
    private static final String TAG_CHANGE_BRIGHTNESS = "changeBrightness";
    private static final String TAG_CHANGE_PAINT_TYPE = "changePaintType";
    private static final String TAG_INTENSITY = "intensity";
    private static final String TAG_RADIUS = "radius";
    private static final String TAG_RADIUS_SAMPLE = "radius.sample";
    private static final String TAG_RADIUS_EFFECT = "radius.effect";
    public static final ToolOptionCheck FULL_BLOCK_MODE = new ToolOptionCheck("fullBlockMode");
    public static final ToolOptionCheck CHANGE_HUE = new ToolOptionCheck("changeHue", false);
    public static final ToolOptionCheck CHANGE_SATURATION = new ToolOptionCheck("changeSaturation", false);
    public static final ToolOptionCheck CHANGE_BRIGHTNESS = new ToolOptionCheck("changeBrightness");
    public static final ToolOptionCheck CHANGE_PAINT_TYPE = new ToolOptionCheck("changePaintType");
    public static final ToolOptionIntensity INTENSITY = new ToolOptionIntensity("intensity");
    public static final ToolOptionRadius RADIUS = new ToolOptionRadius("radius");
    public static final ToolOptionRadius RADIUS_SAMPLE = new ToolOptionRadius("radius.sample");
    public static final ToolOptionRadius RADIUS_EFFECT = new ToolOptionRadius("radius.effect");
}

