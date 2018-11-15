//package edu.miracosta.cs113;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Hashtable;
import java.util.Map;

/**
 * HashTableChain : Tester class for a hash table implementation which utilizes chaining.
 *
 * @author King
 * @version 1.0
 */
public class HashTableChainTest {

    /** Map implementation using integer values with String keys. */
    private Map<String, Integer> hashTable;

    /** Helper method using Map's put() to place a given number of unique key-value pairs into this Map. */
    private void populateMap(int numElements) {
        for (int i = 0; i < numElements; i++) {
            hashTable.put(Integer.toString(i), i);
        }
    }

    @Before
    public void setup() {
        hashTable = new Hashtable<String, Integer>();
    }

    // region Map tests
    // The following tests assess the functionality of your Map implementation,
    // with exception to the putAll() and values() methods.

    @Test
    public void testClear() {
        // Removes all of the mapping from this map
        populateMap(12);
        hashTable.clear();

        // Validate with isEmpty
        assertTrue(hashTable.isEmpty());
    }

    @Test
    public void testContainsKey() {
        // Returns true if this map contains a mapping for the specified key.
        populateMap(5);

        assertTrue(hashTable.containsKey("3"));
    }

    @Test
    public void testContainsValue() {
        // Returns true if this map maps one or more keys to the specified value.
        populateMap(12);

        assertTrue(hashTable.containsValue(3));
    }

    @Test
    public void testEntrySet() {
        // Returns a Set view of the mappings contained in this map.
    }

    @Test
    public void testEquals() {
        // Compares the specified object with this map for equality.
    }

    @Test
    public void testGet() {
        // Returns the value to which the specified key is mapped, or null...
    }

    @Test
    public void testHashCode() {
        // Returns the hash code value for this map.
    }

    @Test
    public void testIsEmpty() {
        // Returns true if this map contains no key-value mappings.
    }

    @Test
    public void testKeySet() {
        // Returns a Set view of the keys contained in this map.
    }

    @Test
    public void testPut() {
        // Associates the specified value with the specified key in this map.
    }

    // Test omitted for putAll

    @Test
    public void testRemove() {
        // Removes the mapping for a key from this map if it is present.
    }

    @Test
    public void testSize() {
        // Returns the number of key-value mappings in this map.
    }

    // Test omitted for values

    // endregion Map tests
    // region Iterator tests

    @Test
    public void testIteratorHasNext() {
        // Returns true if the iterator has more elements.
    }

    @Test
    public void testIteratorNext() {
        // Returns the next element in the iteration.
    }

    @Test
    public void testIteratorRemove() {
        // Removes the underlying collection the last element returned...
    }

    // endregion Iterator tests

} // End of class HashTableChainTest