package edu.miracosta.cs113;

import java.util.*;

public class HashTableChain<K, V> implements Map<K, V>  {

    private LinkedList<Entry<K, V>>[] table ;
    private  int numKeys ;
    private static final int CAPACITY = 10 ;
    private static final double LOAD_THRESHOLD = 3.0 ;

    ///////////// ENTRY CLASS ///////////////////////////////////////

    /**
     * Contains key-value pairs for HashTable
     * @param <K> the key
     * @param <V> the value
     */
    private static class Entry<K, V> implements Map.Entry<K, V>{
        private K key ;
        private V value ;

        /**
         * Creates a new key-value pair
         * @param key the key
         * @param value the value
         */
        public Entry(K key, V value) {
            this.key = key ;
            this.value = value ;
        }

        /**
         * Returns the key
         * @return the key
         */
        public K getKey() {
            return  key;
        }

        /**
         * Returns the value
         * @return the value
         */
        public V getValue() {
            return value ;
        }

        /**
         * Sets the value
         * @param val the new value
         * @return the old value
         */
        public V setValue(V val) {
            V oldVal = value;
            value = val ;
            return oldVal ;
        }
        @Override
        public String toString() {
            return " " + key + "=" + value  ;
        }



    }

    ////////////// end Entry Class /////////////////////////////////

    ////////////// EntrySet Class //////////////////////////////////

    private class EntrySet<K, V> extends AbstractSet<Map.Entry<K, V>> {


        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new SetIterator();
        }

        @Override
        public int size() {
            return numKeys ;
        }
    }

    ////////////// end EntrySet Class //////////////////////////////

    //////////////   SetIterator Class ////////////////////////////

    /**
     * Class that iterates over the table. Index is key
     * and lastItemReturned is value
     */
    private class SetIterator implements Iterator {

        private int index  ;
        private  int lastItemReturned ;

        public  SetIterator() {
            index = 0 ;
            lastItemReturned = -1 ;

        }

        @Override
        public boolean hasNext() {
           // if (Map.Entry. != null )
            if (numKeys > index) {
                return true ;
            } else {
                return false ;
            }

        }

        @Override
        public Map.Entry<K, V> next() {
            index = 0 ;
            // find index we are at
            for (int i = 0 ; i < table.length ; i++) {
                if (table[i] == null) {
                    i++ ;
                } else {
                    for (Entry<K, V> nextItem : table[i]) {
                        if (index == lastItemReturned + 1 && hasNext()) {
                            lastItemReturned++ ;
                            System.out.println("next Item is: " + nextItem);
                            return  nextItem;
                        }
                        else {
                            if (hasNext())
                            index++ ;
                        }
                    }
                }
            }
            return null ;
        }
    }

    ////////////// end SetIterator Class ////////////////////////////

    public HashTableChain() {
        table = new LinkedList[CAPACITY] ;
    }


    @Override
    public int size() {
        return numKeys;
    }

    @Override
    public boolean isEmpty() {
        if (numKeys > 0) {
            return  false ;
        }
        else {
            return true ;
        }
    }

    @Override
    public boolean containsKey(Object key) {
        int index = key.hashCode() % table.length ;
        if (index < 0) {
            index += table.length ;
        }
        if (table[index] == null) {
            return false ;
        }
        for (Entry<K, V> nextItem : table[index]) {
            if (nextItem.key.equals(key)) {
                return true ;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0 ; i < CAPACITY ; i++) {
            if (table[i] == null) {
                i++ ;
            }
            else {
                for (Entry<K, V> nextItem : table[i]) {
                    if (nextItem.value.equals(value)) {
                        System.out.println("value is true, containsValue " + value.toString());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int index = key.hashCode() % table.length ;
        if (index < 0) {
            index += table.length ;
        }
        if (table[index] == null) {
            return null ;
        }
        for (Entry<K, V> nextItem : table[index]) {
            if (nextItem.key.equals(key)) {
                return nextItem.value ;
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % table.length ;
        if (index < 0) {
            index += table.length ;
        }
        if (table[index] == null) {
            table[index] = new LinkedList<Entry<K,V>>() ;
        }
        for (Entry<K, V> nextItem : table[index]) {
            if (nextItem.key.equals(key)) {
                V oldVal = nextItem.value ;
                nextItem.setValue(value) ;
                return oldVal ;
            }
        }
        table[index].addFirst(new Entry<K, V>(key, value));
        numKeys++ ;
        if (numKeys > (LOAD_THRESHOLD * table.length)) {
            rehash() ;
        }
        return null;
    }

    private void rehash() {
    }

    @Override
    public V remove(Object key) {
       int index  = key.hashCode() % table.length ;
       if (index < 0) {
           index += table.length ;
       }
       if (table[index] == null) {
           table[index] = new LinkedList<Entry<K,V>>() ;
       }
        for (Entry<K, V> nextItem : table[index]) {
            if (nextItem.key.equals(key)) {
                table[index].removeFirstOccurrence(nextItem) ;
                numKeys-- ;
                V oldVal = nextItem.value ;
                return oldVal ;
            }
        }
        if (table[index].isEmpty()) {
            table[index] = null ;
        }
        return  null ;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        // stub out
    }

    @Override
    public void clear() {
        table = new LinkedList[CAPACITY] ;
        numKeys = 0 ;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        // stub out
        return null;
    }


    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> temp = new EntrySet<>();

        return temp ;
    }

    public boolean equals(Object o) {
        if (o == null ) {
            return false ;
        }
        if (!(o instanceof Map)) {
            return false ;
        }

        Map anotherMap = (Map) o ;
        if (size() != anotherMap.size()) {
            return false ;  }


        return true ;

    }

    @Override
    public int hashCode() {
        int sum = 0 ; // is this the set view traversal ???
        for (int i = 0 ; i < table.length; i++) {
            if (table[i] != null) {
                for (Entry<K, V> nextItem : table[i]) {
                    sum += nextItem.key.hashCode() + nextItem.value.hashCode();
                }
            }
        }
        return sum ;

    }
}
