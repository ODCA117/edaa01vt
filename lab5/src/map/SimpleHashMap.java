package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	
	private Entry<K,V>[] entry;
	private double loadFactor;
	private int size;
	
	public SimpleHashMap(){
		entry = (Entry<K,V>[]) new Entry[16];
		loadFactor = 0.75;
		size = 0;
	}
	
	public SimpleHashMap(int capacity){
		entry = (Entry<K,V>[]) new Entry[capacity];
		loadFactor = 0.75;
		size = 0;
	}

	private static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K,V> next;
		
		public Entry(K key, V value){
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
		
		public Entry getNext(){
			return next;
		}

		@Override
		public V setValue(V value) {
			this.value = value;
			return value;
		}
		
		@Override
		public String toString(){
			return key.toString() + " = " + value.toString();
		}
		
//		@Override
//		public boolean equals(K key){
//			return this.key == key;
//		}

	}

	@Override
	public V get(Object object) {
		// TODO Auto-generated method stub
		K key;
		try{
			key = (K) object;
		}
		catch (ClassCastException e){
			return null;
		}
		
		int i = index(key);
		if (i == -1)
			return null;
		
		Entry e = find(i, key);
		if(e == null)
			return null;
		
		return (V) e.value;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size == 0);
	}

	@Override
	public V put(K key, V value) {
		//skapa ny entry
		Entry<K,V> newEntry = new Entry(key,value);
		//hashcoda nyckeln
		int index = newEntry.key.hashCode() % (entry.length-1);
		
		if(index < 0)
			index = entry.length - 1 + index;
		
		//placera in nyckeln		
		if(entry[index] == null){
			entry[index] = newEntry;
			size++;
		}
		else if(find(index, key) == null){
			Entry<K,V> temp1 = entry[index];
			Entry<K,V> temp2 = entry[index];
			
			while(temp1 != null){
				temp2 = temp1;
				temp1 = temp1.next;
			}
			temp2.next = newEntry;
			size++;
		}
		
		else{
			Entry<K,V> old = find(index, key);
			
			V oldValue = (V) old.value;
			old.setValue(value);
			return oldValue;
		}
		
//		//fylnadsgrad är överstigen 
//		if( (double)size / ((double)entry.length - 1.0)  > loadFactor)
//			rehash();
		
		return null;
	}

	@Override
	public V remove(Object object) {
		// TODO Auto-generated method stub
		K key;
		try{
			key = (K) object;
		}
		catch (ClassCastException e){
			return null;
		}
		
		int i = index(key);
		if(i == -1)
			return null;
		else if(entry[i].key.equals(key)){
			Entry<K,V> e = entry[i];
			entry[i] = e.next;
			size--;
			return e.getValue();
		}
		Entry<K,V> e = entry[i];
		Entry<K,V> e2 = entry[i];
		
		while (e != null){
			if(e.key.equals(key)){
				e2.next = e.next;
				size--;
				return e.getValue();
			}
			e2 = e;
			e = e.next;
		}
		
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	public String show(){
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < entry.length; i++){
			Entry<K,V> e = entry[i];
			sb.append(i + "\t");
			while(e != null){
				sb.append(e.toString() + ", ");
				e = e.next;
			}
		}
		
		return sb.toString();
	}
	
	//Index för nyckeln om den finns, annars -1
	private int index(K key){
		for(int i = 0; i < entry.length; i++){
			Entry<K,V> e = entry[i];
			while (e != null){
				if(e.getKey().equals(key)){
					return i;
				}
				e = e.next;
				
			}
		}
		return -1;
	}
	
	//returner null om nyckel ej finns
	private Entry<K,V> find(int index, K key){
		Entry<K,V> e = entry[index];
		while(e != null){
			if( e.key.equals(key))
				return e;
			e = e.next;
		}
		
		return null;
	}
	
	private void rehash(){
		Entry<K,V>[] newEntry = (Entry<K,V>[]) new Entry[entry.length * 2];
		
		for(int i = 0; i < entry.length; i++){
			Entry<K,V> e = entry[i];
			
			while(e != null){
				int newHash = e.key.hashCode() % (newEntry.length - 1);
				
				newEntry[newHash] = e;
				e = e.next;
				
			}
		}
		
		entry = newEntry;
	}

}
