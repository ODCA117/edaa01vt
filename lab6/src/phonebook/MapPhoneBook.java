package phonebook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class MapPhoneBook implements PhoneBook {
	private Map<String, Set<String>> pb;
	

	public MapPhoneBook() {
		pb = new HashMap<String, Set<String>>();
	}
	
	@Override
	public boolean put(String name, String number) {
		if(!pb.containsKey(name)){
			Set<String> n = new HashSet<String>();
			n.add(number);
			pb.put(name, n);
			return true;
		}
		else{
			return pb.get(name).add(number);
			
		}
	}

	@Override
	public boolean remove(String name) {
		return pb.remove(name, pb.get(name));
	}

	@Override
	public boolean removeNumber(String name, String number) {
		if(pb.containsKey(name)){
			return pb.get(name).remove(number);
		}
		else{
			return false;
		}
	}

	@Override
	public Set<String> findNumbers(String name) {
		if(pb.containsKey(name)){
			Set<String> nbr = new HashSet<String>();
			nbr.addAll(pb.get(name));
			return nbr;
		}
		else{
			return new HashSet<String>();
		}
	}

	@Override
	public Set<String> findNames(String number) {
		Set<String> names = new HashSet<String>();
		for(String key : pb.keySet()){
			if(pb.get(key).contains(number)){
				names.add(key);
			}
		}
		
		return names;
	}

	@Override
	public Set<String> names() {
		Set<String> names = new TreeSet<String>(pb.keySet());
		return names;
	}
	
	public boolean isEmpty(){
		return pb.isEmpty();
	}

	@Override
	public int size() {
		return pb.keySet().size();
	}

}
