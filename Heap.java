import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;

public class Heap<K, V> implements PriorityQueue<K, V>{
    public List<Entry<K, V>> heap;
    public Comparator<K> comparator;
    public int p;
    
    public Heap(Comparator<K> comparator){
        heap = new ArrayList<>();
        heap.add(null);
        this.comparator = comparator;
        p = 0;
    }
    
    public void add(K k, V v) {
        Entry<K, V> entry = new Entry<>(k, v);
        
        heap.add(entry);
        
        bubbleUp(++p);
    }
    
    public Entry<K, V> poll() {
        if (p == 0) {
        	return null;
        }
        
        Entry<K, V> en = heap.get(1);
        
        heap.set(1,  heap.get(p));
        heap.remove(p--);
        
        bubbleDown(1);
        
        return en;
    }
    
    public Entry<K, V> peek() {
    	return heap.get(1);
    }
    
    public List<Entry<K, V>> toArray() {
    	List<Entry<K, V>> array = new ArrayList<>();
    	
    	for (int i = 1; i <= p; i++) {
    		array.add(heap.get(i));
    	}
    	
    	return array;
    }
    
    public boolean isEmpty() {
    	return (p == 0);
    }
    
    public int parent(int index) {
    	return (index-1) / 2;
    }
    
    public int left(int index) {
    	return (index * 2) + 1;
    }
    
    public int right(int index) {
    	return (index * 2) + 2;
    }
    
    public void swap(int index1, int index2) {
    	if (index1 <= p && 
    			index2 < p && 
    			index1 > 0 && 
    			index2 > 0) {
    		
    		Entry<K, V> temp = heap.get(index1);
    		
    		heap.set(index1,  heap.get(index2));
    		
    		heap.set(index2, temp);
    		
    	}
    	
    }
    
    public void bubbleUp(int num) {
    	while (num > 1 && 
    			existsAndGreater(num, num / 2)) {
    		swap(num, num / 2);
    		
    		num = num / 2;
    	}
    }
    
    public void bubbleDown(int num) {
    	while (num <= p / 2) {
    		int dir = 2 * num;
    		if (dir + 1 <= p && existsAndGreater(dir + 1, dir)) {
    			dir++;
    			
    		}
    		
    		if (dir > p || existsAndGreater(num, dir)) {
    			return;
    		}
    		
    		swap(num, dir);
    		num = dir;
    	}
    }
    
    public boolean existsAndGreater(int index1, int index2) {
    	K a = heap.get(index1).getKey();
    	K b = heap.get(index2).getKey();
    	
    	boolean result = comparator.compare(a, b) > 0;
    	return result; 
    }
    
    public int size() {
    	return p;
    }

    public String toString() {
    	String str = "";
    	
    	for (int i = 1; i <= p; i++) {
    		str += heap.get(i).toString();
    	}
    	
    	return str;
    }
    
}