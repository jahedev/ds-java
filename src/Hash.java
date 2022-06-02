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
}
