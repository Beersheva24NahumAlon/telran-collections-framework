package telran.util;

public abstract class AbstractMap<K, V> implements Map<K, V> {
    protected Set<Entry<K, V>> set;

    protected abstract Set<K> getEmptyKeySet();

    @SuppressWarnings("unchecked")
    private Entry<K, V> getEntry(Object key) {
        Entry<K, V> pattern = new Entry<>((K) key, null);
        Entry<K, V> entry = set.get(pattern); 
        return entry; 
    }

    public V get(Object key) {
        Entry<K, V> entry = getEntry(key);
        return entry == null ? null : entry.getValue();
    }

    @Override
    public V put(K key, V value) {
        Entry<K, V> entry = getEntry(key);
        V res = null;
        if (entry != null) {
            res = entry.getValue();
            entry.setValue(value);
        } else {
            set.add(new Entry<>(key, value));
        }
        return res;
    }

    @Override
    public boolean containsKey(Object key) {
        Entry<K, V> entry = getEntry(key);
        return entry != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return set.stream().anyMatch(o -> java.util.Objects.equals(o.getValue(), value));
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = getEmptyKeySet();
        set.stream().forEach(entry -> keySet.add(entry.getKey()));
        return keySet;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return set;
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> collection = new ArrayList<>();
        set.stream().forEach(entry -> collection.add(entry.getValue()));
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

    @Override
    public V remove(K key) {
        Entry<K, V> entry = getEntry(key);
        V res = null;
        if (entry != null) {
            set.remove(entry);
            res = entry.getValue();
        }
        return res;
    }
}