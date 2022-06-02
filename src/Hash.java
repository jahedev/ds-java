public class Hash<K, V> /* implements HashI<K,V> */ {
    class HashElement<K, V> implements Comparable<HashElement<K,V>> {
       K key;
       V value;

       public HashElement(K key, V value) {
           this.key = key;
           this.value = value;
       }

        @Override
        public int compareTo(HashElement<K, V> o) {
            return ((Comparable<K>) o.key).compareTo(this.key);
        }
    }

    int numElements, tableSize;
    double maxLoadFactor;

    LinkedList<HashElement<K, V>>[] hArr;

    public Hash(int tableSize) {
        this.tableSize = tableSize;
        hArr = (LinkedList<HashElement<K,V>>[]) new LinkedList[tableSize];

        for (int i = 0; i < tableSize; i++) {;
            hArr[i] = new LinkedList<HashElement<K,V>>();
        }

        maxLoadFactor = .75;
        numElements = 0;
    }

    public void put(K key, V value) {
        if (loadFactor() > maxLoadFactor) {
            resize(tableSize * 2);
        }

        HashElement<K,V> element = new HashElement(key, value);
        int hashval = key.hashCode();
        hashval = hashval & 0x7fffffff;
        hashval = hashval % tableSize;
        hArr[hashval].addFirst(element);
        numElements++;
    }

    public V get(K key) {
        int hashval = (key.hashCode() & 0x7fffffff) % tableSize;

        for (HashElement<K,V> hashEl : hArr[hashval]) {
            if (((Comparable<K>) key).compareTo(hashEl.key) == 0) {
                return hashEl.value;
            }
        }
        return null;
    }

    public double loadFactor() {
        return numElements / tableSize;
    }

    public void resize(double newMaxLoadFactor) {
        this.maxLoadFactor = newMaxLoadFactor;
        // add code to resize
    }
}
