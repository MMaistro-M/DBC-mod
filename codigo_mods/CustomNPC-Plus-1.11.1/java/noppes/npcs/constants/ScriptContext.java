/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import noppes.npcs.api.event.IAbilityEvent;
import noppes.npcs.api.event.IAnimationEvent;
import noppes.npcs.api.event.IAuctionEvent;
import noppes.npcs.api.event.IBlockEvent;
import noppes.npcs.api.event.IChainEvent;
import noppes.npcs.api.event.ICustomGuiEvent;
import noppes.npcs.api.event.IDialogEvent;
import noppes.npcs.api.event.IEnergyBarrierEvent;
import noppes.npcs.api.event.IEnergyProjectileEvent;
import noppes.npcs.api.event.IFactionEvent;
import noppes.npcs.api.event.IForgeEvent;
import noppes.npcs.api.event.IItemEvent;
import noppes.npcs.api.event.ILinkedItemEvent;
import noppes.npcs.api.event.INpcEvent;
import noppes.npcs.api.event.IPartyEvent;
import noppes.npcs.api.event.IPlayerEvent;
import noppes.npcs.api.event.IProjectileEvent;
import noppes.npcs.api.event.IQuestEvent;
import noppes.npcs.api.event.IRecipeEvent;

public class ScriptContext {
    private static final Map<String, ScriptContext> REGISTRY = new ConcurrentHashMap<String, ScriptContext>();
    public static final ScriptContext NPC = ScriptContext.register("NPC", "npc", INpcEvent.class, IProjectileEvent.class, IEnergyProjectileEvent.class, IEnergyBarrierEvent.class, IAbilityEvent.class, IChainEvent.class, IAnimationEvent.class);
    public static final ScriptContext PLAYER = ScriptContext.register("PLAYER", "player", IPlayerEvent.class, IAnimationEvent.class, IPartyEvent.class, IDialogEvent.class, IQuestEvent.class, IFactionEvent.class, ICustomGuiEvent.class, IEnergyProjectileEvent.class, IEnergyBarrierEvent.class, IAbilityEvent.class, IChainEvent.class, IAuctionEvent.class);
    public static final ScriptContext BLOCK = ScriptContext.register("BLOCK", "block", IBlockEvent.class);
    public static final ScriptContext ITEM = ScriptContext.register("ITEM", "item", IItemEvent.class);
    public static final ScriptContext FORGE = ScriptContext.register("FORGE", "forge", IForgeEvent.class);
    public static final ScriptContext LINKED_ITEM = ScriptContext.register("LINKED_ITEM", "linked_item", ILinkedItemEvent.class, IItemEvent.class);
    public static final ScriptContext RECIPE = ScriptContext.register("RECIPE", "recipe", IRecipeEvent.class);
    public static final ScriptContext EFFECT = ScriptContext.register("EFFECT", "effect", IPlayerEvent.class);
    public static final ScriptContext ABILITY = ScriptContext.register("ABILITY", "ability", IAbilityEvent.class);
    public static final ScriptContext CHAINED_ABILITY = ScriptContext.register("CHAINED_ABILITY", "chained_ability", IChainEvent.class);
    public static final ScriptContext GLOBAL = ScriptContext.register("GLOBAL", "", "Global");
    public final String id;
    public final String hookContext;
    private final List<String> namespaces;
    private final List<String> namespaceFQNs = new ArrayList<String>();

    private ScriptContext(String id, String hookContext, String ... namespaces) {
        this.id = id;
        this.hookContext = hookContext != null ? hookContext : "";
        this.namespaces = new ArrayList<String>(Arrays.asList(namespaces));
    }

    public List<String> getNamespaces() {
        return Collections.unmodifiableList(this.namespaces);
    }

    public List<String> getNamespaceFQNs() {
        return Collections.unmodifiableList(this.namespaceFQNs);
    }

    public boolean hasNamespace(String namespace) {
        return this.namespaces.contains(namespace);
    }

    public void addNamespace(String namespace) {
        if (!this.namespaces.contains(namespace)) {
            this.namespaces.add(namespace);
        }
    }

    public String getPrimaryNamespace() {
        return this.namespaces.isEmpty() ? "Global" : this.namespaces.get(0);
    }

    public static ScriptContext register(String id, String hookContext, String ... namespaces) {
        ScriptContext context = new ScriptContext(id, hookContext, namespaces);
        REGISTRY.put(id, context);
        return context;
    }

    public static ScriptContext register(String id, String hookContext, Class<?> ... eventClasses) {
        String[] namespaces = new String[eventClasses.length];
        for (int i = 0; i < eventClasses.length; ++i) {
            namespaces[i] = eventClasses[i].getSimpleName();
        }
        ScriptContext context = ScriptContext.register(id, hookContext, namespaces);
        for (Class<?> clazz : eventClasses) {
            context.namespaceFQNs.add(clazz.getName().replace('$', '.'));
        }
        return context;
    }

    public static ScriptContext byId(String id) {
        return REGISTRY.getOrDefault(id, GLOBAL);
    }

    public static ScriptContext byNamespace(String namespace) {
        if (namespace == null) {
            return GLOBAL;
        }
        for (ScriptContext ctx : REGISTRY.values()) {
            if (!ctx.hasNamespace(namespace)) continue;
            return ctx;
        }
        return GLOBAL;
    }

    public static ScriptContext byHookContext(String hookContext) {
        if (hookContext == null || hookContext.isEmpty()) {
            return GLOBAL;
        }
        for (ScriptContext ctx : REGISTRY.values()) {
            if (!hookContext.equals(ctx.hookContext)) continue;
            return ctx;
        }
        return GLOBAL;
    }

    public static Collection<ScriptContext> values() {
        return Collections.unmodifiableCollection(REGISTRY.values());
    }

    public static boolean exists(String id) {
        return REGISTRY.containsKey(id);
    }

    public String toString() {
        return this.id + " -> " + this.namespaces;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScriptContext)) {
            return false;
        }
        ScriptContext other = (ScriptContext)obj;
        return this.id.equals(other.id);
    }

    public int hashCode() {
        return this.id.hashCode();
    }
}

