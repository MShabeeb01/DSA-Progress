public class WhatIsATrie {

    // Definition of a Trie Node as structured in the lecture
    static class Node {
        Node[] children = new Node[26]; // Array of 26 pointers for letters 'a' through 'z'[cite: 20]
        boolean endOfWord = false;      // Marks whether a complete word terminates at this node[cite: 20]

        public Node() {
            // Initially, all child pointers point to null[cite: 20]
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }

    // Root node represents the empty string prefix ""[cite: 20]
    public static Node root = new Node();

    // Insertion operation: Builds paths character by character[cite: 20]
    public static void insert(String word) {
        Node curr = root;

        for (int level = 0; level < word.length(); level++) {
            int idx = word.charAt(level) - 'a'; // Convert char to index 0-25[cite: 20]

            // If path does not exist for this character, allocate a new Node[cite: 20]
            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }
            curr = curr.children[idx]; // Move down to child node[cite: 20]
        }

        // Mark the last node as the valid end of word[cite: 20]
        curr.endOfWord = true;
    }

    public static void main(String[] args) {
        // Words array from the lecture slide[cite: 20]:
        String[] words = {"the", "a", "there", "their", "any", "thee"};[cite: 20]

        for (String word : words) {
            insert(word);
        }

        System.out.println("Trie successfully constructed for words:");
        for (String word : words) {
            System.out.println(" - " + word);
        }
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 39 — What is a Trie? (Prefix Tree Architecture)[cite: 20]

Core Definitions & Terminology:
- A Trie (also referred to as a Prefix Tree or Retrieval Tree) is a specialized tree-based data structure used to store collections of strings efficiently[cite: 20].
- Unlike a standard binary tree where each node has at most two children, each Trie node has up to 26 children (one for every lowercase English alphabet character 'a' through 'z')[cite: 20].

Anatomy of a Trie Node (`class Node`)[cite: 20]:
1. `Node[] children = new Node[26];`[cite: 20]
   - Stores references to consecutive letters[cite: 20].
   - Mapping formula: `index = character - 'a'`[cite: 20].
     - 'a' -> 0, 'b' -> 1, ..., 't' -> 19, 'z' -> 25[cite: 20].
2. `boolean endOfWord;` (or `eow`)[cite: 20]:
   - Indicates whether a valid word from the dictionary ends at this character[cite: 20].
   - Crucial because prefixes are shared: in the branch `t -> h -> e -> r -> e`, the node at `e` has `endOfWord = true` to denote that "the" is a standalone word, while continuing downward to form "there"[cite: 20].

-------------------------------------------------

Visual Representation of the Tree from Slide[cite: 20]

Words inserted: ["the", "a", "there", "their", "any", "thee"][cite: 20]

                      ( Root : "" )
                     /             \
                   ['t']           ['a'] (endOfWord = true: "a")[cite: 20]
                    |                |
                   ['h']           ['n']
                    |                |
                   ['e']           ['y'] (endOfWord = true: "any")[cite: 20]
              (endOfWord = true: "the")[cite: 20]
                /   |   \
             ['r'] ['i'] ['e'] (endOfWord = true: "thee")[cite: 20]
              |     |
            ['e'] ['r'] 
       (endOfWord (endOfWord 
        = true:    = true: 
        "there")   "their")[cite: 20]

Prefix-Sharing Breakdown:
- "the", "there", "their", and "thee" share the common root sequence `t -> h -> e`[cite: 20].
- From `e`, the paths split[cite: 20]:
  - Branching to `r -> e` forms "there"[cite: 20].
  - Branching to `i -> r` forms "their"[cite: 20].
  - Branching to `e` forms "thee"[cite: 20].
- "a" and "any" share the root node path `a`[cite: 20].

-------------------------------------------------

Character Index Mapping Trace

---------------------------------------------------------------------------------------------------------
Character | ASCII Value | Mapping (`char - 'a'`)[cite: 20] | Array Slot in `children[]`[cite: 20]
---------------------------------------------------------------------------------------------------------
'a'       | 97          | 97 - 97 = 0                      | children[0][cite: 20]
'e'       | 101         | 101 - 97 = 4                     | children[4]
'h'       | 104         | 104 - 97 = 7                     | children[7]
'i'       | 105         | 105 - 97 = 8                     | children[8]
'n'       | 110         | 110 - 97 = 13                    | children[13]
'r'       | 114         | 114 - 97 = 17                    | children[17]
't'       | 116         | 116 - 97 = 19                    | children[19]
'y'       | 121         | 121 - 97 = 24                    | children[24]
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time to Insert a Word: O(L) where L is word length (independent of the total number of words in the Trie)[cite: 20].
- Space Complexity: O(ALPHABET_SIZE * L * N) in the worst case with no common prefixes; highly space-efficient when prefixes are densely shared[cite: 20].
=================================================
*/
