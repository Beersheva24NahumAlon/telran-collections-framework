package telran.util;

public interface Map<K, V> {
    public static class Entry<K, V> implements Comparable<Entry<K, V>> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        public K getKey() {
            return key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @SuppressWarnings("unchecked")
        @Override
        public int compareTo(Entry<K, V> entry) {
            return ((Comparable<K>) key).compareTo(entry.getKey());
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object obj) {
            Entry<K, V> entry = (Entry<K, V>) obj;
            return key.equals(entry.key);
        }
    }

    V get(Object key);

    default V getOrDefault(Object key, V defaultValue) {
        V res = get(key);
        return res == null ? defaultValue : res;
    }

    V put(K key, V value);

    default V putIfAbsent(K key, V value) {
        V res = get(key);
        if (res == null) {
            put(key, value);
        }
        return res;
    }

    boolean containsKey(Object key);

    boolean containsValue(Object value);

    Set<K> keySet();

    Set<Entry<K, V>> entrySet();

    Collection<V> values();

    int size();

    boolean isEmpty();
}