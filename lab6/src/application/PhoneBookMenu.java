package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Set;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import phonebook.PhoneBook;

public class PhoneBookMenu extends MenuBar {
	private PhoneBook phoneBook;
	private NameListView nameListView;
	
	/** Creates the menu for the phone book application.
	 * @param phoneBook the phone book with names and numbers
	 * @param nameListView handles the list view for the names
	 */
	public PhoneBookMenu(PhoneBook phoneBook, NameListView nameListView) {
		this.phoneBook = phoneBook;
		this.nameListView = nameListView;

		final Menu menuPhoneBook = new Menu("PhoneBook");
		final MenuItem menuQuit = new MenuItem("Quit");
		menuQuit.setOnAction(e -> Platform.exit());
		menuPhoneBook.getItems().addAll(menuQuit);
	
		final Menu menuFind = new Menu("Find");
		
		final MenuItem menuShowAll = new MenuItem("Show All");
		menuShowAll.setOnAction(e -> showAll());
		
		final MenuItem menuFindNumbers = new MenuItem("Find Numbers");
		menuFindNumbers.setOnAction(e -> findNumbers());
		
		final MenuItem menuFindNames = new MenuItem("Find Names");
		menuFindNames.setOnAction(e -> findNames());
		
		final MenuItem menuFindPersons = new MenuItem("Find Persons");
		menuFindPersons.setOnAction(e -> findPersons());
		
		menuFind.getItems().addAll(menuShowAll, menuFindNumbers, menuFindNames, menuFindPersons);

	    getMenus().addAll(menuPhoneBook, menuFind);
  //    setUseSystemMenuBar(true);  // if you want operating system rendered menus, uncomment this line
	}

	
	private void findPersons() {
		Optional<String> name = Dialogs.oneInputDialog("Name", "Name of contact", "Write name of contact");
		Set<String> names = phoneBook.names();
		ArrayList<String> foundNames = new ArrayList<>();
		Iterator<String> itr = names.iterator();
		while(itr.hasNext()){
			String s = itr.next();
			if(s.startsWith(name.get())){
				foundNames.add(s);
			}
		}
		nameListView.fillList(foundNames);
	}


	private void findNumbers() {
		Optional<String> name = Dialogs.oneInputDialog("Name", "Name of contact", "Write name of contact");
		if(phoneBook.names().contains(name.get())){
			ArrayList<String> s = new ArrayList<>();
			s.add(name.get());
			nameListView.fillList(s);
			nameListView.clearSelection();
		}else{
			Dialogs.alert("Error", "Not found", "Name not found");
		}
	}
	
	private void findNames() {
		Optional<String> nbr = Dialogs.oneInputDialog("Number", "Number of contact", "Write number of contact");
		if(phoneBook.findNames(nbr.get()).size() != 0){
			nameListView.fillList(phoneBook.findNames(nbr.get()));
			nameListView.clearSelection();
		}else{
			Dialogs.alert("Error", "Not found", "Number not found");
		}
	}


	private void showAll() {
		nameListView.fillList(phoneBook.names());
		nameListView.clearSelection();
	}

}
