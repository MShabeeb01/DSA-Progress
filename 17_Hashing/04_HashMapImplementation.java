import java.util.ArrayList;
import java.util.LinkedList;

public class CustomHashMapImplementation {

    // Generic Custom HashMap
    static class MyHashMap<K, V> {
        // Internal Node representation storing Key-Value pairs
        private class Node {
            K key;
            V value;

            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private int n; // Total number of key-value pairs (size)
        private int N; // Total number of buckets (buckets array length)
        private LinkedList<Node>[] buckets; // Array of LinkedLists (Separate Chaining)

        @SuppressWarnings("unchecked")
        public MyHashMap() {
            this.N = 4; // Initial bucket capacity
            this.buckets = new LinkedList[N];
            for (int i = 0; i < N; i++) {
                this.buckets[i] = new LinkedList<>();
            }
            this.n = 0;
        }

        // Hash Function: Converts Key into a valid bucket index [0 to N-1]
        private int hashFunction(K key) {
            int hc = key.hashCode();
            return Math.abs(hc) % N;
        }

        // Search in LinkedList of a specific bucket
        private int searchInLL(K key, int bi) {
            LinkedList<Node> ll = buckets[bi];
            for (int di = 0; di < ll.size(); di++) {
                Node node = ll.get(di);
                if (node.key.equals(key)) {
                    return di; // Data index found
                }
            }
            return -1; // Key does not exist in this bucket
        }

        // Rehash: Doubles bucket array size and re-maps all nodes when load factor > lambda
        @SuppressWarnings("unchecked")
        private void rehash() {
            LinkedList<Node>[] oldBuckets = buckets;
            this.N = 2 * N;
            this.buckets = new LinkedList[N];
            for (int i = 0; i < N; i++) {
                this.buckets[i] = new LinkedList<>();
            }

            this.n = 0; // Reset size before re-inserting
            for (int i = 0; i < oldBuckets.length; i++) {
                LinkedList<Node> ll = oldBuckets[i];
                for (Node node : ll) {
                    put(node.key, node.value);
                }
            }
        }

        // 1. put(key, value) - O(1) average
        public void put(K key, V value) {
            int bi = hashFunction(key);     // Bucket Index
            int di = searchInLL(key, bi);   // Data Index within bucket

            if (di != -1) {
                // Key exists -> Update value
                Node node = buckets[bi].get(di);
                node.value = value;
            } else {
                // Key does not exist -> Insert new node
                buckets[bi].add(new Node(key, value));
                n++;
            }

            // Check Load Factor: lambda = n / N (threshold typically 2.0)
            double lambda = (double) n / N;
            if (lambda > 2.0) {
                rehash();
            }
        }

        // 2. get(key) - O(1) average
        public V get(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);

            if (di != -1) {
                return buckets[bi].get(di).value;
            }
            return null;
        }

        // 3. containsKey(key) - O(1) average
        public boolean containsKey(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);
            return di != -1;
        }

        // 4. remove(key) - O(1) average
        public V remove(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);

            if (di != -1) {
                Node node = buckets[bi].remove(di);
                n--;
                return node.value;
            }
            return null;
        }

        // 5. keySet() - O(n)
        public ArrayList<K> keySet() {
            ArrayList<K> keys = new ArrayList<>();
            for (int i = 0; i < buckets.length; i++) {
                for (Node node : buckets[i]) {
                    keys.add(node.key);
                }
            }
            return keys;
        }

        // 6. size() & isEmpty() - O(1)
        public int size() {
            return n;
        }

        public boolean isEmpty() {
            return n == 0;
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        // Test insertion
        map.put("India", 100);
        map.put("China", 150);
        map.put("US", 50);
        map.put("Nepal", 5);

        System.out.println("All Keys: " + map.keySet());
        System.out.println("Size: " + map.size()); // 4

        // Test get & containsKey
        System.out.println("Get India: " + map.get("India"));       // 100
        System.out.println("Contains US? " + map.containsKey("US")); // true

        // Test update
        map.put("India", 140);
        System.out.println("Updated India: " + map.get("India"));   // 140

        // Test remove
        System.out.println("Removed US: " + map.remove("US"));      // 50
        System.out.println("Contains US after remove? " + map.containsKey("US")); // false
        System.out.println("Remaining Size: " + map.size());        // 3
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 38 — Internal Implementation of HashMap (Separate Chaining)[cite: 28]

Underlying Architecture:
A HashMap is built upon an Array of LinkedLists:
- Array: Referred to as the "Bucket Array" of size `N`.
- LinkedList at each index: Serves as the collision-resolution container (Separate Chaining).
- Node: Encapsulates `(K key, V value)` pairs[cite: 28].

Two-Tier Index Lookup Strategy:
1. `bi` (Bucket Index):
   - Identifies which bucket array slot the key belongs to:
     `bi = Math.abs(key.hashCode()) % N;`
2. `di` (Data Index):
   - Scans the LinkedList at `buckets[bi]` to locate the matching node via `.equals()`.

The Load Factor (λ) & Rehashing Mechanism:
- Formula: $\lambda = \frac{n}{N}$ (Ratio of total elements $n$ to total buckets $N$).
- Threshold: Once $\lambda > K$ (typically $2.0$ for this design, or $0.75$ in standard JCF):
  1. The existing bucket array becomes saturated, increasing search chain lengths.
  2. Rehashing allocates a new array of size $2 \times N$.
  3. All entries from the old buckets are re-hashed and inserted into the new array.
- Rehashing ensures individual bucket chains remain very short ($O(1)$ length on average).

-------------------------------------------------

Separate Chaining Bucket Array Visual

Keys Inserted: ("India", 100), ("China", 150), ("US", 50), ("Nepal", 5)[cite: 28]

Bucket Array (`buckets[]`, N = 4):
+---------+-------------------------------------------------------+
| Index   | LinkedList of Nodes                                   |
+---------+-------------------------------------------------------+
| [ 0 ]   | -> Node("China", 150) -> null                         |
| [ 1 ]   | -> null                                               |
| [ 2 ]   | -> Node("India", 100) -> Node("Nepal", 5) -> null    | (Collision resolved)
| [ 3 ]   | -> Node("US", 50) -> null                             |
+---------+-------------------------------------------------------+

Operations Flow:
- put("Nepal", 5):
  - hashFunction("Nepal") -> bi = 2.
  - Traverse bucket[2]: "India" != "Nepal" -> append Node("Nepal", 5).
- get("India"):
  - hashFunction("India") -> bi = 2.
  - Traverse bucket[2]: matches at index 0 -> return 100.

-------------------------------------------------

Operations Execution & Complexity Reference[cite: 28]

---------------------------------------------------------------------------------------------------------
Operation         | Method Call            | Average Time[cite: 28] | Worst Case | Amortized Reason
---------------------------------------------------------------------------------------------------------
Insert / Update   | put(key, value)[cite: 28] | O(1)[cite: 28]         | O(n)       | Controlled by load factor λ <= constant
Retrieve Value    | get(key)[cite: 28]    | O(1)[cite: 28]         | O(n)       | Average chain length is bounded
Membership Test   | containsKey(key)[cite: 28]| O(1)[cite: 28]     | O(n)       | Quick hash lookup + short scan
Delete Pair       | remove(key)[cite: 28] | O(1)[cite: 28]         | O(n)       | Unlinking from short bucket list
Get Element Count | size()[cite: 28]      | O(1)[cite: 28]         | O(1)       | Tracked by integer counter `n`
Rehash Process    | rehash()               | O(n)                    | O(n)       | Occurs rarely; amortized O(1) cost
---------------------------------------------------------------------------------------------------------

Space Complexity:
- O(n + N): Stores $n$ elements across an array of $N$ buckets.
=================================================
*/
