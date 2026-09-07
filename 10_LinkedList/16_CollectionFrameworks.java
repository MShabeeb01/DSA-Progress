import java.util.*;

/*
==================== SUMMARY ====================

Topic: Java Collections Framework (JCF) Hierarchy

Core Concept:
- A unified architecture in Java (`java.util` package) providing interfaces, 
  implementations (classes), and algorithms to store and manipulate data.
- Color Key:
  - Yellow Boxes : Interfaces (contracts specifying behaviors)
  - Purple Boxes : Concrete Classes (actual implementations)
  - Solid Line (->)   : Extends (Interface to Interface / Class to Class)
  - Dotted Line (...) : Implements (Class to Interface)

-------------------------------------------------

Framework Hierarchy Overview

1. Collection Interface (Root of standard collections):
   - Collection extends Iterable
   - Main child interfaces:
     a) List (Ordered, allows duplicates, positional access)
        - Implemented by: ArrayList, Vector (extends to Stack), LinkedList
     b) Set (Unordered, unique elements only)
        - Implemented by: HashSet (extends to LinkedHashSet)
        - Sub-interface: SortedSet
     c) Queue (FIFO order, scheduling)
        - Implemented by: PriorityQueue
        - Sub-interface: Deque (Double Ended Queue) -> Implemented by: ArrayDeque, LinkedList

2. Map Interface (Key-Value pairs, independent from Collection interface):
   - Stores pairs of (Key, Value); keys are unique.
   - Core Interface: Map
     - Sub-interfaces: SortedMap -> NavigableMap
     - Abstract class: AbstractMap
     - Concrete classes: HashMap, EnumMap, TreeMap

-------------------------------------------------

LinkedList Dual Identity in JCF:
- `LinkedList` implements BOTH `List` AND `Deque` interfaces.
- It functions as:
  1. A linear list with indexed access methods (`get()`, `add()`).
  2. A double-ended queue / stack (`addFirst()`, `removeLast()`, `peek()`).

-------------------------------------------------

Hierarchy Mapping Table

---------------------------------------------------------------------------------------------------------
Interface   | Type          | Concrete Implementations (Classes)   | Key Property
---------------------------------------------------------------------------------------------------------
List        | Interface     | ArrayList, Vector, Stack, LinkedList | Ordered, allows duplicates, indexed
Set         | Interface     | HashSet, LinkedHashSet, TreeSet      | Unique elements only, no duplicates
Queue/Deque | Interface     | PriorityQueue, ArrayDeque, LinkedList| FIFO / LIFO / Priority-based polling
Map         | Independent   | HashMap, TreeMap, EnumMap            | Key-Value mappings (unique keys)
---------------------------------------------------------------------------------------------------------

Complexity Snapshot (General Collections):
- LinkedList (JCF) Add/Remove Ends : O(1) Time
- LinkedList (JCF) Search / By-Index: O(n) Time
- HashMap / HashSet Operations      : O(1) Average Time
=================================================
*/
