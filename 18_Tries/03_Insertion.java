public class InsertInTrie {

    // Definition of a Trie Node
    static class Node {
        Node[] children = new Node[26]; // 26 child references ('a' through 'z')
        boolean eow = false;            // End of Word flag

        public Node() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }

    // Static root node representing the empty prefix ""
    public static Node root = new Node();

    // ==========================================================
    // Operation: Insert in Trie -> Time Complexity: O(L)[cite: 20]
    // where L is the length of the string to insert[cite: 20]
    // ==========================================================
    public static void insert(String word) {
        Node curr = root;

        // Iterate character by character through the word[cite: 20]
        for (int level = 0; level < word.length(); level++) {
            int idx = word.charAt(level) - 'a'; // Map 'a' -> 0, ..., 'z' -> 25[cite: 20]

            // If the link does not exist, initialize a new Node[cite: 20]
            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }

            // Descend to child node[cite: 20]
            curr = curr.children[idx];
        }

        // Mark the leaf node as the termination point of this word[cite: 20]
        curr.eow = true;
    }

    public static void main(String[] args) {
        // Words array from the lecture slide[cite: 20]
        String words[] = {"the", "a", "there", "their", "any", "thee"};[cite: 20]

        // Insert each word into the Trie[cite: 20]
        for (String word : words) {
            insert(word);
            System.out.println("Inserted: \"" + word + "\" (Length: " + word.length() + ")");
        }

        System.out.println("\nAll words inserted successfully in O(L) per word.");
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 39 — Insert in Trie[cite: 20]

Core Insertion Mechanics:

1. Time Complexity Bound: O(L)[cite: 20]
   - $L$ denotes the length of the string being inserted[cite: 20].
   - The loop runs exactly $L$ iterations (one step per character)[cite: 20].
   - **Crucial Advantage:** The insertion time is completely independent of the total 
     number of words ($N$) already stored in the Trie.

2. Step-by-Step Insertion Lifecycle:
   - Begin at `curr = root`.
   - For each character at position `level` from $0$ to $L - 1$:
     a. Calculate alphabetical slot: `idx = ch - 'a'`.
     b. Check existence: If `curr.children[idx] == null`, allocate `new Node()`.
     c. Traverse forward: Advance pointer `curr = curr.children[idx]`.
   - Upon loop completion, set `curr.eow = true` to validate the word ending.

-------------------------------------------------

Step-by-Step Trie Growth Visual (Insertion Sequence)[cite: 20]

Words: ["the", "a", "there", "their", "any", "thee"][cite: 20]

1. Insert "the" (L=3):
   (root) -> ['t'] -> ['h'] -> ['e'] (eow=true)

2. Insert "a" (L=1):
   (root)
   ├── ['t'] -> ['h'] -> ['e'] (eow=true)
   └── ['a'] (eow=true)

3. Insert "there" (L=5):
   (root)
   ├── ['t'] -> ['h'] -> ['e'] (eow=true)
   │                       └── ['r'] -> ['e'] (eow=true)
   └── ['a'] (eow=true)

4. Insert "their" (L=5):
   Shares "the" prefix:
   (root)
   ├── ['t'] -> ['h'] -> ['e'] (eow=true)
   │                       ├── ['r'] -> ['e'] (eow=true: "there")
   │                       └── ['i'] -> ['r'] (eow=true: "their")
   └── ['a'] (eow=true)

5. Insert "any" (L=3):
   Shares "a" prefix:
   └── ['a'] (eow=true: "a")
         └── ['n'] -> ['y'] (eow=true: "any")

6. Insert "thee" (L=4):
   Branches directly off 'e':
   └── ['e'] (eow=true: "thee")

-------------------------------------------------

Insertion Execution Trace Table

---------------------------------------------------------------------------------------------------------
Word Inserted | Length ($L$) | Reused Existing Path | Newly Created Nodes | Final Node Set to `eow = true`
---------------------------------------------------------------------------------------------------------
"the"[cite: 20]        | 3            | None                 | 't', 'h', 'e'       | 'e' (Level 3)
"a"[cite: 20]          | 1            | None                 | 'a'                 | 'a' (Level 1)
"there"[cite: 20]      | 5            | "the" (3 nodes)      | 'r', 'e'            | 'e' (Level 5)
"their"[cite: 20]      | 5            | "the" (3 nodes)      | 'i', 'r'            | 'r' (Level 5)
"any"[cite: 20]        | 3            | "a"   (1 node)       | 'n', 'y'            | 'y' (Level 3)
"thee"[cite: 20]       | 4            | "the" (3 nodes)      | 'e'                 | 'e' (Level 4)
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(L) per word, where L is the length of the word[cite: 20].
  - For inserting $N$ words of average length $L$: total time is O(N * L).
- Auxiliary Space: O(L * 26) in the worst case per word (when no letters are shared in the prefix).
=================================================
*/
