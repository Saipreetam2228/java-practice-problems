# 🧠 Problem: Build a Generic LRU Cache (O(1) Time)

Design and implement a generic **Least Recently Used (LRU) Cache** with strict O(1) get/put performance using HashMap + Doubly Linked List.

---

## 🔹 Core Requirements

1. **APIs**
   - `V get(K key)` → returns the value if present; otherwise returns null.
   - `void put(K key, V value)` → inserts or updates the key; if capacity is exceeded, evict the least-recently used entry.
   - `int size()` → current number of entries.
   - `int capacity()` → fixed max entries set at construction.

2. **Complexity**
   - get and put must be **O(1)** average time.

3. **Generics**
   - Class must be usable as:
     - `LRUCache<Integer, String>`
     - `LRUCache<String, Integer>`
     - etc.

4. **Eviction Ordering**
   - “Recently used” means the most recent successful **get** or **put**.

5. **No shortcuts**
   - ❌ Do NOT use `LinkedHashMap` LRU mode.
   - ✅ You MAY use `HashMap`.

---

## 🔹 Design Constraints & Hints

• Typical solution involves:  
  o A `HashMap<K, Node<K,V>>` for **O(1) lookups**.  
  o A **custom doubly-linked list** to maintain recency:  
   head = most recent  
   tail = least recent  

• On **get**:  
  o If key present → move its node to head and return value.

• On **put**:  
  o If key exists → update value + move node to head.  
  o Else → create new node at head.  
  o If size exceeds capacity → remove tail node + remove from map.

---

## 🔹 API & Usage Example

```java
LRUCache<Integer, String> cache = new LRUCache<>(capacity = 2);

cache.put(1, "A");           // cache: [1:A]
cache.put(2, "B");           // cache: [2:B, 1:A]
cache.get(1);                // returns "A", cache: [1:A, 2:B]
cache.put(3, "C");           // evicts key 2, cache: [3:C, 1:A]
cache.get(2);                // returns null
cache.put(4, "D");           // evicts key 1, cache: [4:D, 3:C]
cache.get(1);                // null
cache.get(3);                // "C"
cache.get(4);                // "D"
