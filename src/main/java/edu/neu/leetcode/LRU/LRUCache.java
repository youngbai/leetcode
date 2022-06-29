package edu.neu.leetcode.LRU;

import java.util.HashMap;
import java.util.Map;

/*
Thinking:
- cache -> map
- LRU -> order -> double linked list
- dummy head, tail -> easy operation

Time: O(1)
Space: O(n), Map, Double Linked List
 */

class LRUCache {
    private int capacity;
    private Map<Integer, Entry> map;
    private Entry head;
    private Entry tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Entry(0, 0);
        tail = new Entry(0, 0);
        head.next = tail;       // NOTE: don't forget connecting head and tail
        tail.prev = head;
    }
    
    class Entry {
        int key;
        int value;
        Entry prev;
        Entry next;
        Entry (int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            Entry entry = map.get(key);
            popToTail(entry);  
            return entry.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Entry entry = map.get(key);
            entry.value = value;
            popToTail(entry);
        } else {
            Entry newEntry = new Entry(key, value);
            if (map.size() >= capacity) {
                Entry first = removeFirst();   
                map.remove(first.key);
            }
            addToTail(newEntry);
            map.put(key, newEntry);
        }
    }
    
    private void popToTail(Entry entry) {
        if (entry.next == tail)  return;
        Entry prev = entry.prev;
        Entry next = entry.next;
        prev.next = next;
        next.prev = prev;
        addToTail(entry);
    }
    
    private void addToTail(Entry entry) {
        Entry last = tail.prev;
        last.next = entry;
        entry.next = tail;
        tail.prev = entry;
        entry.prev = last;
    }
    
    private Entry removeFirst() {
        Entry first = head.next;
        Entry second = first.next;
        head.next = second;
        second.prev = head;
        return first;
    }
    
    
}