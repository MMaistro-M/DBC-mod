/*
 * Decompiled with CFR 0.152.
 */
package kamkeel.npcs.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.AbilityVariant;
import kamkeel.npcs.controllers.data.ability.conditions.AbilityCondition;
import kamkeel.npcs.controllers.data.ability.data.effect.IEffectAction;
import noppes.npcs.LogWriter;
import noppes.npcs.controllers.AnimationController;
import noppes.npcs.controllers.data.Animation;

public class Register<T> {
    public static final Map<String, List<String>> REGISTERED_NAMESPACES = new LinkedHashMap<String, List<String>>();
    public static final Map<String, String> NAMESPACE_DISPLAY_NAMES = new LinkedHashMap<String, String>();
    protected final String registryKey;
    protected final String namespace;
    protected final Map<String, Supplier<T>> entries = new LinkedHashMap<String, Supplier<T>>();

    private Register(String registryKey, String namespace) {
        this.registryKey = registryKey;
        this.namespace = namespace;
    }

    public T register(String factoryName, Supplier<T> factory) {
        this.entries.put(this.registryKey + "." + this.namespace + "." + factoryName, factory);
        return factory.get();
    }

    public static boolean isEmpty(String registryKey) {
        if (REGISTERED_NAMESPACES.isEmpty()) {
            return true;
        }
        List<String> list = REGISTERED_NAMESPACES.get(registryKey);
        return list == null || list.isEmpty();
    }

    public static class Conditions
    extends Register<AbilityCondition> {
        private Conditions(String namespace) {
            super("condition", namespace);
        }

        @Override
        public AbilityCondition register(String name, Supplier<AbilityCondition> factory) {
            this.entries.put(this.registryKey + "." + this.namespace + "." + name, factory);
            return factory.get();
        }

        public void register() {
            for (Map.Entry entry : this.entries.entrySet()) {
                AbilityController.Instance.registerCondition((Supplier)entry.getValue());
            }
        }

        public static Conditions create(String namespace, String displayName) {
            if (!REGISTERED_NAMESPACES.containsKey("condition")) {
                REGISTERED_NAMESPACES.put("condition", new ArrayList());
            }
            if (((List)REGISTERED_NAMESPACES.get("condition")).contains(namespace)) {
                LogWriter.error("REGISTER CONDITIONS: Namespace " + namespace + " already registered!");
            }
            ((List)REGISTERED_NAMESPACES.get("condition")).add(namespace);
            NAMESPACE_DISPLAY_NAMES.put(namespace, displayName);
            return new Conditions(namespace);
        }
    }

    public static class EffectActions
    extends Register<IEffectAction> {
        private EffectActions(String namespace) {
            super("effect_action", namespace);
        }

        public IEffectAction register(String name, IEffectAction action) {
            String key = this.namespace + ":" + name.trim().toLowerCase().replaceAll(" ", "_");
            this.entries.put(key, () -> action);
            return action;
        }

        public void register() {
            for (Map.Entry entry : this.entries.entrySet()) {
                AbilityController.Instance.registerEffectAction((IEffectAction)((Supplier)entry.getValue()).get());
            }
        }

        public static EffectActions create(String namespace, String displayName) {
            if (!REGISTERED_NAMESPACES.containsKey("effect_action")) {
                REGISTERED_NAMESPACES.put("effect_action", new ArrayList());
            }
            if (((List)REGISTERED_NAMESPACES.get("effect_action")).contains(namespace)) {
                LogWriter.error("REGISTER EFFECT ACTIONS: Namespace " + namespace + " already registered!");
            }
            ((List)REGISTERED_NAMESPACES.get("effect_action")).add(namespace);
            NAMESPACE_DISPLAY_NAMES.put(namespace, displayName);
            return new EffectActions(namespace);
        }
    }

    public static class Animations
    extends Register<Animation> {
        private final Class<?> modClass;
        private final String animationsPath;

        private Animations(Class<?> modClass, String animationsPath, String namespace) {
            super("animation", namespace);
            this.animationsPath = animationsPath;
            this.modClass = modClass;
        }

        @Override
        public Animation register(String factoryName, Supplier<Animation> factory) {
            return super.register(factoryName, factory);
        }

        public Animation[] registerBundle(String factoryName, Supplier<Animation> factory, String ... appendixes) {
            ArrayList<Animation> animations = new ArrayList<Animation>();
            for (String appendix : appendixes) {
                this.entries.put(this.registryKey + "." + this.namespace + "." + factoryName + "_" + appendix, factory);
                animations.add(factory.get());
            }
            return animations.toArray(new Animation[0]);
        }

        public void register() {
            try {
                String path = "/assets/" + this.namespace + "/" + this.animationsPath;
                for (Map.Entry entry : this.entries.entrySet()) {
                    String prefix = this.registryKey + "." + this.namespace + ".";
                    String key = (String)entry.getKey();
                    String name = key.substring(prefix.length());
                    AnimationController.Instance.loadBuiltInAnimation(this.modClass, path, name);
                }
            }
            catch (Exception e) {
                LogWriter.error("Error scanning built-in animations folder", e);
            }
        }

        public static Animations create(Class<?> modClass, String animationsPath, String namespace) {
            if (!REGISTERED_NAMESPACES.containsKey("animation")) {
                REGISTERED_NAMESPACES.put("animation", new ArrayList());
            }
            if (((List)REGISTERED_NAMESPACES.get("animation")).contains(namespace)) {
                LogWriter.error("REGISTER ANIMATIONS: Namespace " + namespace + " already registered!");
            }
            ((List)REGISTERED_NAMESPACES.get("animation")).add(namespace);
            return new Animations(modClass, animationsPath, namespace);
        }
    }

    public static class Abilities
    extends Register<Ability> {
        protected final Map<String, Map<String, Supplier<AbilityVariant>>> variantEntries = new LinkedHashMap<String, Map<String, Supplier<AbilityVariant>>>();
        protected final Map<String, String> uniqueNames = new LinkedHashMap<String, String>();
        protected final Set<String> typeOnly = new HashSet<String>();

        private Abilities(String namespace) {
            super("ability", namespace);
        }

        @Override
        public Ability register(String factoryName, Supplier<Ability> factory) {
            String name = this.registryKey + "." + this.namespace + "." + factoryName.trim().toLowerCase().replaceAll(" ", "_");
            this.entries.put(name, factory);
            this.uniqueNames.put(name, factoryName);
            return factory.get();
        }

        public Ability registerType(String factoryName, Supplier<Ability> factory) {
            String name = this.registryKey + "." + this.namespace + "." + factoryName.trim().toLowerCase().replaceAll(" ", "_");
            this.entries.put(name, factory);
            this.uniqueNames.put(name, factoryName);
            this.typeOnly.add(name);
            return factory.get();
        }

        public AbilityVariant registerVariant(String typeId, String variantName, String group, Consumer<Ability> configurator) {
            return this.registerVariant(typeId, variantName, () -> new AbilityVariant(variantName, group, configurator));
        }

        public AbilityVariant registerVariant(String typeId, String factoryName, Supplier<AbilityVariant> factory) {
            if (!this.variantEntries.containsKey(typeId)) {
                this.variantEntries.put(typeId, new HashMap());
            }
            Map<String, Supplier<AbilityVariant>> type = this.variantEntries.get(typeId);
            String name = this.registryKey + "." + this.namespace + "." + factoryName.trim().toLowerCase().replaceAll(" ", "_");
            type.put(name, factory);
            return factory.get();
        }

        public void register() {
            for (Map.Entry entry : this.entries.entrySet()) {
                AbilityController.Instance.registerType((Supplier)entry.getValue());
                if (this.typeOnly.contains(entry.getKey())) continue;
                AbilityController.Instance.registerAbility(this.uniqueNames.get(entry.getKey()), (Ability)((Supplier)entry.getValue()).get());
            }
            for (Map.Entry<Object, Object> entry : this.variantEntries.entrySet()) {
                String typeId = (String)entry.getKey();
                Map map = (Map)entry.getValue();
                for (Map.Entry innerEntry : map.entrySet()) {
                    AbilityController.Instance.registerVariant(typeId, (AbilityVariant)((Supplier)innerEntry.getValue()).get());
                }
            }
        }

        public static Abilities create(String namespace, String displayName) {
            if (!REGISTERED_NAMESPACES.containsKey("ability")) {
                REGISTERED_NAMESPACES.put("ability", new ArrayList());
            }
            if (((List)REGISTERED_NAMESPACES.get("ability")).contains(namespace)) {
                LogWriter.error("REGISTER ABILITIES: Namespace " + namespace + " already registered!");
            }
            ((List)REGISTERED_NAMESPACES.get("ability")).add(namespace);
            NAMESPACE_DISPLAY_NAMES.put(namespace, displayName);
            return new Abilities(namespace);
        }
    }
}

