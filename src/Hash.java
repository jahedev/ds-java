import java.util.Iterator;

public class Hash<K, V> implements Iterable<K> /* implements HashI<K,V> */ {

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

    class IteratorHelper<T> implements Iterator<T> {

        T[] keys;
        int postion;

        public IteratorHelper() {
            keys = (T[]) new Object[numElements];
            int p = 0;

            for (int i = 0; i < tableSize; i++) {;
                LinkedList<HashElement<K,V>> list = hArr[i];

                for (HashElement<K,V> hashEl : list) {
                    keys[p++] = (T) hashEl.key;
                }
            }

            postion = 0;
        }

        @Override
        public boolean hasNext() {
            return postion < keys.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }

            return keys[postion++];
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new IteratorHelper();
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

        maxLoadFactor = .5;
        numElements = 0;
    }

    public void put(K key, V value) {
        if (loadFactor() > maxLoadFactor) {
            resize(tableSize * 2);
        }

        HashElement<K,V> element = new HashElement<>(key, value);
        int hashval = hashMod(key.hashCode(), tableSize);
//        System.out.printf("hashval: %d\n", hashval);
        hArr[hashval].addFirst(element);
        numElements++;
    }

    public boolean remove(K key) {
        V value = get(key);
        if (value == null) return false;

        int hashval = hashMod(key.hashCode(), tableSize);
        hArr[hashval].remove(getHashElement(key));
        return true;
    }

    public V get(K key) {
        HashElement<K,V> hashEl = getHashElement(key);
        return hashEl != null ? hashEl.value : null;
    }

    private HashElement<K, V> getHashElement(K key) {
        int hashval = hashMod(key.hashCode(), tableSize);

        for (HashElement<K,V> hashEl : hArr[hashval]) {
            if (((Comparable<K>) key).compareTo(hashEl.key) == 0) {
                return hashEl;
            }
        }
        return null;
    }

    public boolean hasKey(K key) {
        return get(key) != null;
    }

    public double loadFactor() {
//        System.out.println("<->LoadFactor: " + (double) numElements / tableSize);
//        System.out.println("<->MaxLoadFactor: " + maxLoadFactor);
        return numElements / tableSize;
    }

    public void resize(int newSize) {
        System.out.printf("<-> resizing: %d\n", newSize);
        LinkedList<HashElement<K,V>>[] newHashArr =
                (LinkedList<HashElement<K,V>>[]) new LinkedList[newSize];

        for (int i = 0; i < newSize; i++) {;
            newHashArr[i] = new LinkedList<>();
        }

        for (K key : this) {
            V val = get(key);
            HashElement<K,V> hashEl = new HashElement<K,V>(key,val);
            int hashval = hashMod(key.hashCode(), newSize);
            newHashArr[hashval].addFirst(hashEl);

        }
        hArr = newHashArr;
        tableSize = newSize;
    }

    public int hashMod(int hashCode, int arrSize) {
        return (hashCode & 0x7fffffff) % arrSize;
    }
}
