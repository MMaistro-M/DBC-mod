/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  scala.NotImplementedError
 */
package riskyken.armourersWorkshop.common.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import scala.NotImplementedError;

public class BidirectionalHashMap<K, V>
implements Map<K, V> {
    private final HashMap<K, V> mapForward = new HashMap();
    private final HashMap<V, K> mapBackward = new HashMap();

    public HashMap<V, K> getMapBackward() {
        return this.mapBackward;
    }

    public HashMap<K, V> getMapForward() {
        return this.mapForward;
    }

    @Override
    public void clear() {
        this.mapForward.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.mapForward.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.mapForward.containsValue(value);
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return this.mapForward.entrySet();
    }

    @Override
    public V get(Object key) {
        return this.mapForward.get(key);
    }

    public K getBackward(V key) {
        return this.mapBackward.get(key);
    }

    @Override
    public boolean isEmpty() {
        return this.mapForward.isEmpty();
    }

    @Override
    public Set<K> keySet() {
        return this.mapForward.keySet();
    }

    @Override
    public V put(K key, V value) {
        this.mapBackward.put(value, key);
        return this.mapForward.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new NotImplementedError();
    }

    @Override
    public V remove(Object key) {
        V value = this.mapForward.get(key);
        if (value != null) {
            this.mapBackward.remove(value);
        }
        return this.mapForward.remove(key);
    }

    @Override
    public int size() {
        return this.mapForward.size();
    }

    @Override
    public Collection<V> values() {
        return this.mapForward.values();
    }
}

