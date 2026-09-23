public class TrieIntroduction {

    // Definition of a Trie Node (k-ary / 26-ary tree node)
    static class Node {
        Node[] children = new Node[26]; // 26 pointers for lowercase English letters 'a' through 'z'
        boolean eow = false;            // End of Word marker (true if a valid word ends at this node)

        public Node() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }

    // Root of the Trie (always empty, represents the prefix "")
    public static Node root = new Node();

    // 1. Insertion into Trie - O(L) where L is the length of the word
    public static void insert(String word) {
        Node curr = root;

        for (int level = 0; level < word.length(); level++) {
            int idx = word.charAt(level) - 'a'; // Map 'a' -> 0, 'b' -> 1, ..., 'z' -> 25

            // If the branch for this character does not exist, create a new Node
            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }
            curr = curr.children[idx]; // Advance down the tree
        }

        // Mark the final character node as the termination of a complete word
        curr.eow = true;
    }

    // 2. Search in Trie - O(L) where L is the length of the word
    public static boolean search(String word) {
        Node curr = root;

        for (int level = 0; level < word.length(); level++) {
            int idx = word.charAt(level) - 'a';

            // If a required character path is missing, the word does not exist
            if (curr.children[idx] == null) {
                return false;
            }
            curr = curr.children[idx];
        }

        // Word exists only if the path exists AND it is marked as the end of a word
        return curr.eow;
    }

    // 3. Prefix Matching (startsWith) - O(L) where L is the length of prefix
    public static boolean startsWith(String prefix) {
        Node curr = root;

        for (int level = 0; level < prefix.length(); level++) {
            int idx = prefix.charAt(level) - 'a';

            if (curr.children[idx] == null) {
                return false;
            }
            curr = curr.children[idx];
        }

        return true; // Path exists, regardless of eow status
    }

    public static void main(String[] args) {
        // Words demonstrated in tree prefixing: "all", "also", "am"
        String[] words = {"all", "also", "am"};

        for (String word : words) {
            insert(word);
        }

        // Test Exact Word Searches
        System.out.println("Search 'also': " + search("also")); // true
        System.out.println("Search 'al':   " + search("al"));   // false (prefix exists, but eow is false)
        System.out.println("Search 'an':   " + search("an"));   // false

        // Test Prefix Matching
        System.out.println("Prefix 'al':   " + startsWith("al")); // true
        System.out.println("Prefix 'am':   " + startsWith("am")); // true
        System.out.println("Prefix 'ap':   " + startsWith("ap")); // false
    }
}

/*
==================== SUMMARY ====================

Topic: Trie Data Structure (Introduction & Overview)[cite: 20]

Definition & Etymology:
- The word **Trie** comes from the middle syllable of "re**TRIE**val" (often pronounced like "try" or "tree")[cite: 20].
- It is an advanced tree data structure (specifically a $k$-ary tree or prefix tree) used to store and retrieve strings efficiently over an alphabet[cite: 20].

Core Architectural Properties:
1. Root Node:
   - Always empty; it stores no character and represents an empty prefix `""`.
2. $k$-ary Branching Factor:
   - For English lowercase letters ('a' - 'z'), each node contains an array of 26 pointers (`Node[] children = new Node[26]`).
   - The index formula maps each character directly: `index = ch - 'a'`.
3. Common Prefixes are Shared:
   - Words sharing the same initial character sequences share the exact same branch nodes.
   - Example: "all" and "also" branch together under `a -> l` before splitting into `l` and `s -> o`.
4. End of Word (`eow` / `isTerminal`):
   - A boolean flag at each node determining whether the string path from root to this node constitutes a valid complete word.

-------------------------------------------------

Trie Structural Diagram for ["all", "also", "am"][cite: 20]

                     ( Root )
                    /        \
                  ['a']       ...
                 /     \
              ['l']   ['m'] (eow = true: "am")[cite: 20]
             /     \
          ['l']   ['s'][cite: 20]
      (eow=true:    \
        "all")      ['o'] (eow = true: "also")[cite: 20]

-------------------------------------------------

Trie vs. Binary Search Tree (BST) vs. HashMap

-------------------------------------------------------------------------------------------------------------------------
Feature                  | Trie                         | Binary Search Tree (BST)    | HashMap
-------------------------------------------------------------------------------------------------------------------------
Search Time Complexity   | O(L) (L = length of string)  | O(L * log n)                | O(L) average (calculating hash)
Prefix Matching          | O(L) natively supported      | Inefficient / requires scan | O(n) scan through all keys
Memory Efficiency        | High sharing for shared prefixes| Individual node per word   | Independent entries, high overhead
Alphabet Flexibility     | Bound to alphabet size (26)  | Generalized comparison      | Generalized comparison
Common Applications      | Autocomplete, Spell Checkers | General dictionary search   | Key-value caches
-------------------------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Insertion (`insert`)   : O(L) — Follows or creates $L$ nodes along the depth, where $L$ is the string length.
- Search (`search`)      : O(L) — Visits at most $L$ nodes. Independent of the total number of words $n$ stored in the Trie.
- Prefix (`startsWith`)  : O(L) — Traverses the characters of the prefix.
- Space Complexity       : O(A * L * N) worst case (where $A$ is alphabet size 26, $L$ is max word length, and $N$ is total word count).
=================================================
*/
