package com.any.assessment;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class LRUCache 
{
	
	private final int capacity;
    private final Map<Integer, Integer> cache;
    private final Map<Integer, Long> timestamps;

    @SuppressWarnings("serial")
	public LRUCache(int capacity) 
    {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) 
        {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) 
            {
                return size() > LRUCache.this.capacity;
            }
        };
        this.timestamps = new HashMap<Integer, Long>();
    }

    public int get(int key) 
    {
        Long timestamp = timestamps.get(key);
        if (timestamp == null) 
        {
            return -1;
        }
        cache.put(key, cache.get(key));
        timestamps.put(key, System.currentTimeMillis());
        return cache.get(key);
    }

    public void put(int key, int value) 
    {
        cache.put(key, value);
        timestamps.put(key, System.currentTimeMillis());
    }

	public static void main(String[] args) 
	{
		LRUCache cache = new LRUCache(2);
		cache.put(1, 1);
		cache.put(2, 2);
		
		System.out.println(cache.get(1));
		
		cache.put(3, 3);
		
		System.out.println(cache.get(2));
		
		System.out.println(cache.get(3));
	}
}
