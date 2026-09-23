public class SearchInTrie {

    // Definition of a Trie Node
    static class Node {
        Node[] children = new Node[26]; // 26 possible character branches ('a' through 'z')
        boolean eow = false;            // End of Word marker (terminal flag)

        public Node() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }

    // Static root node representing empty prefix ""
    public static Node root = new Node();

    // Standard insert helper: O(L)
    public static void insert(String word) {
        Node curr = root;
        for (int level = 0; level < word.length(); level++) {
            int idx = word.charAt(level) - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }
            curr = curr.children[idx];
        }
        curr.eow = true;
    }

    // ==========================================================
    // Operation: Search in Trie -> Time Complexity: O(L)
    // where L is the length of the queried key
    // ==========================================================
    public static boolean search(String key) {
        Node curr = root;

        // Traverse character by character through the key
        for (int level = 0; level < key.length(); level++) {
            int idx = key.charAt(level) - 'a';

            // Case 1: Letter path does NOT exist in the Trie
            if (curr.children[idx] == null) {
                return false;
            }

            // Descend to next level node
            curr = curr.children[idx];
        }

        // Case 2: All letters exist -> check if it represents a complete word (eow == true)
        return curr.eow;
    }

    public static void main(String[] args) {
        // Words stored in Trie:
        String words[] = {"the", "a", "there", "their", "any", "thee"};
        for (String word : words) {
            insert(word);
        }

        // Query tests demonstrated on lecture slide:
        // 1. key = "thee" -> Expected: true
        System.out.println("Search 'thee': " + search("thee")); // true

        // 2. key = "thor" -> Expected: false (path breaks at 'o')
        System.out.println("Search 'thor': " + search("thor")); // false

        // Additional edge tests:
        System.out.println("Search 'the':  " + search("the"));  // true
        System.out.println("Search 'th':   " + search("th"));   // false (prefix exists, but eow is false)
        System.out.println("Search 'theer': " + search("theer")); // false (missing 'r' under 'thee')
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 39 — Search in Trie[cite: 20]

Core Search Mechanics:

1. Time Complexity: O(L)[cite: 20]
   - $L$ denotes the total length of the queried search key[cite: 20].
   - It performs exactly $L$ down-tree pointer transitions.
   - Independent of the number of words ($N$) stored in the Trie.

2. The Two Conditions for Returning `true`:
   Condition 1: "All letters exist"
     - Every character in `key` must match a non-null branch child pointer along the path[cite: 20].
     - If at any character `curr.children[idx] == null`, return `false` immediately[cite: 20].
   Condition 2: "eow == true"
     - Upon reaching the node corresponding to the final character of `key`, its `eow` (end of word) flag must be `true`[cite: 20].
     - If the path exists but `eow == false`, the query is merely a prefix of another word, not a standalone valid word[cite: 20].

-------------------------------------------------

Search Execution Visual Tracing[cite: 20]

Trie Representation:
                      ( Root )
                     /        \
                   ['t']      ['a'] (eow=true)
                    |           |
                   ['h']      ['n']
                    |           |
                   ['e'] (eow=true: "the")
                 /  |  \
              ['r']['i']['e'] (eow=true: "thee")[cite: 20]
               |    |
             ['e'] ['r']

Trace 1: key = "thee"[cite: 20]
  - Level 0 ('t'): root.children['t'] != null -> Move to 't'
  - Level 1 ('h'): 't'.children['h']  != null -> Move to 'h'
  - Level 2 ('e'): 'h'.children['e']  != null -> Move to 'e'
  - Level 3 ('e'): 'e'.children['e']  != null -> Move to 'e'
  - End of String reached. Check `curr.eow`:
    -> `curr.eow == true` (Word exists!) -> Returns `true`[cite: 20]

Trace 2: key = "thor"[cite: 20]
  - Level 0 ('t'): root.children['t'] != null -> Move to 't'
  - Level 1 ('h'): 't'.children['h']  != null -> Move to 'h'
  - Level 2 ('o'): 'h'.children['o']  == null -> Branch does not exist![cite: 20]
  - Returns `false` immediately[cite: 20].

Trace 3: key = "theer"[cite: 20]
  - Reaches "thee" successfully.
  - Next character ('r'): 'thee'.children['r'] == null -> Returns `false`[cite: 20].

-------------------------------------------------

Search Verification Matrix

---------------------------------------------------------------------------------------------------------
Search Key   | Path Exists Fully? | Final Node Reached | `curr.eow` Status | Final Result
---------------------------------------------------------------------------------------------------------
"thee"[cite: 20]     | Yes                | Node('e', level 3) | true              | true[cite: 20]
"thor"[cite: 20]     | No (breaks at 'o') | Node('h', level 1) | N/A               | false[cite: 20]
"the"        | Yes                | Node('e', level 2) | true              | true
"th"         | Yes                | Node('h', level 1) | false             | false (Prefix only)
"theer"[cite: 20]    | No (breaks at 'r') | Node('e', level 3) | N/A               | false[cite: 20]
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(L) where L is the length of the search string[cite: 20].
- Space Complexity: O(1) auxiliary space (iterative traversal uses a single reference pointer `curr`).
=================================================
*/
