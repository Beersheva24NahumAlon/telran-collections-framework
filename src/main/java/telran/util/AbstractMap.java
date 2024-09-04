package telran.util;

import java.util.Iterator;

public abstract class AbstractMap<K, V> implements Map<K, V> {
    protected Set<Entry<K, V>> set;

    protected abstract Set<K> getEmptyKeySet();

    @SuppressWarnings("unchecked")
    @Override
    public V get(Object key) {
        Entry<K, V> pattern = new Entry<>((K) key, null);
        Entry<K, V> entry = set.get(pattern);
        return entry == null ? null : entry.getValue();
    }

    @Override
    public V put(K key, V value) {
        Entry<K, V> pattern = new Entry<>((K) key, null);
        Entry<K, V> entry = set.get(pattern);
        V res = null;
        if (entry != null) {
            res = entry.getValue();
            entry.setValue(value);
        } else {
            set.add(new Entry<>(key, value));
        }
        return res;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean containsKey(Object key) {
        Entry<K, V> pattern = new Entry<>((K) key, null);
        Entry<K, V> entry = set.get(pattern);
        return entry != null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean containsValue(Object value) {
        return values().contains((V) value);
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = getEmptyKeySet();
        Iterator<Entry<K, V>> it = set.iterator();
        while (it.hasNext()) {
            keySet.add(it.next().getKey());
        }
        return keySet;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return set;
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> collection = new ArrayList<>();
        Iterator<Entry<K, V>> it = set.iterator();
        while (it.hasNext()) {
            collection.add(it.next().getValue());
        }
        return collection;
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }
}