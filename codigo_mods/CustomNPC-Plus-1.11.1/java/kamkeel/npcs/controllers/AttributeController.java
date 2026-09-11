/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package kamkeel.npcs.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import kamkeel.npcs.CustomAttributes;
import kamkeel.npcs.controllers.data.attribute.AttributeDefinition;
import kamkeel.npcs.controllers.data.attribute.AttributeValueType;
import kamkeel.npcs.controllers.data.attribute.tracker.PlayerAttributeTracker;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.IAttributeHandler;
import noppes.npcs.api.handler.data.IAttributeDefinition;
import noppes.npcs.api.handler.data.IPlayerAttributes;

public class AttributeController
implements IAttributeHandler {
    public static AttributeController Instance;
    public CustomAttributes attributes;
    private static final Map<UUID, PlayerAttributeTracker> trackers;
    private static final Map<String, AttributeDefinition> definitions;

    public AttributeController() {
        Instance = this;
        definitions.clear();
        trackers.clear();
        this.attributes = new CustomAttributes();
    }

    public static AttributeDefinition registerAttribute(String key, String displayName, char colorCode, AttributeValueType valueType, AttributeDefinition.AttributeSection section) {
        if (definitions.containsKey(key)) {
            throw new IllegalArgumentException("Attribute already registered with key: " + key);
        }
        AttributeDefinition def = new AttributeDefinition(key, displayName, colorCode, valueType, section);
        definitions.put(key, def);
        return def;
    }

    public static AttributeDefinition registerAttribute(AttributeDefinition definition) {
        if (definitions.containsKey(definition.getKey())) {
            throw new IllegalArgumentException("Attribute already registered with key: " + definition.getKey());
        }
        definitions.put(definition.getKey(), definition);
        return definition;
    }

    public static AttributeDefinition getAttribute(String key) {
        return definitions.get(key);
    }

    public static Collection<AttributeDefinition> getAllAttributes() {
        return definitions.values();
    }

    public static PlayerAttributeTracker getTracker(EntityPlayer player) {
        return trackers.computeIfAbsent(player.func_110124_au(), id -> new PlayerAttributeTracker((UUID)id));
    }

    public static void removeTracker(UUID playerId) {
        trackers.remove(playerId);
    }

    public static void updateAllTrackers(Iterable<EntityPlayer> players) {
        for (EntityPlayer player : players) {
            PlayerAttributeTracker tracker = AttributeController.getTracker(player);
            tracker.updateIfChanged(player);
        }
    }

    @Override
    public IPlayerAttributes getPlayerAttributes(IPlayer player) {
        if (player == null || player.getMCEntity() == null) {
            return null;
        }
        EntityPlayer entityPlayer = (EntityPlayer)player.getMCEntity();
        return trackers.computeIfAbsent(entityPlayer.func_110124_au(), id -> new PlayerAttributeTracker((UUID)id));
    }

    @Override
    public IAttributeDefinition getAttributeDefinition(String key) {
        return definitions.get(key);
    }

    @Override
    public IAttributeDefinition[] getAllAttributesArray() {
        return definitions.values().toArray(new AttributeDefinition[definitions.size()]);
    }

    static {
        trackers = new HashMap<UUID, PlayerAttributeTracker>();
        definitions = new HashMap<String, AttributeDefinition>();
    }
}

