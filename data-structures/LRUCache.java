import java.util.LinkedHashMap;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.Map;
import java.util.ArrayList;
import java.util.Set;

public class LRUCache<K, V> implements Map<K, V> {
    private static final float hashTableLoadFactor = 0.75f;

    private LinkedHashMap<K, V> map;
    private int cacheSize;

    public LRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
        int hashTableCapacity = 
            (int)Math.ceil(cacheSize / hashTableLoadFactor) + 1;

        map = new LinkedHashMap<K, V>(hashTableCapacity, 
                hashTableLoadFactor, true) {
            private static final long serialVersionUID = 1;

            @Override
            protected boolean removeEldestEntry(Entry<K, V> eldest) {
                return size() > LRUCache.this.cacheSize;
            }
        };
    }

    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    public V get(Object key) {
        return map.get(key);
    }

    public V put(K key, V value) {
        return map.put(key, value);
    }

    public V remove(Object key) {
        return map.remove(key);
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    public Set<K> keySet() {
        return map.keySet();
    }

    public void clear() {
        map.clear();
    }

    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    public int size() {
        return map.size();
    }

    public boolean equals(Object o) {
        return map.equals(o);
    }

    public int hashCode() {
        return map.hashCode();
    }
    
    public boolean isEmpty() {
        return map.size() == 0;
    }

    public Collection<Entry<K, V>> getAll() {
        return new ArrayList<Entry<K, V>>(map.entrySet());
    }

    public Collection<V> values() {
        return map.values();
    }

    public static void main(String[] args) {
        LRUCache<String, String> c = new LRUCache<String, String>(3);

        c.put("1", "one");
        c.put("2", "two");
        c.put("3", "three");
        c.put("4", "four");
        if (c.get("2") == null)
            throw new Error();

        c.put("5", "five");
        c.put("4", "second four");

        if (c.size() != 3)
            throw new Error();

        if (!c.get("4").equals("second four"))
            throw new Error();

        if (!c.get("5").equals("five"))
            throw new Error();

        if (!c.get("2").equals("two"))
            throw new Error();

        for (Entry<String, String> e : c.getAll())
            System.out.println(e.getKey() + " : " + e.getValue());

        for (String v : c.values())
            System.out.println(v);

        System.out.println(c.hashCode());

    }
}
