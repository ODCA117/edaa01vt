package application;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import phonebook.PhoneBook;


public class NameListView extends BorderPane {
	private ListView<String> listView;
	private ObservableList<String> obsList;
	private PhoneBook phoneBook;
	private Button removeButton;
	private Button addNumberButton;
	private Button removeNumberButton;
	private Label numbersLabel;
	
	/** Creates the list view for the names.Also creates buttons for adding/removing names/numbers.
	 * @param phoneBook the phone book with names and phone numbers
	 */
	public NameListView(PhoneBook phoneBook) {	
		this.phoneBook = phoneBook;
		// Create an observable wrapper for the names.
		obsList = FXCollections.observableArrayList();
		obsList.setAll(phoneBook.names());
		
		// Create a list view to display the names. 
		// The list view is automatically updated when the observable list i updated.
		listView = new ListView<>(obsList);
		listView.setPrefWidth(400);
		listView.setPrefHeight(200);
		setTop(listView);
		
		// A label to display phone numbers
		numbersLabel = new Label();
		numbersLabel.setMinWidth(340);
		
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> add());
		removeButton = new Button("Remove");
		removeButton.setOnAction(e -> remove());
		
		addNumberButton = new Button("Add number");
		addNumberButton.setOnAction(e -> addNumber());
		removeNumberButton = new Button("Remove number");
		removeNumberButton.setOnAction(e -> removeNumber());
		
		
		HBox buttonBox = new HBox();
		buttonBox.setSpacing(5);
		buttonBox.setPadding(new Insets(10, 10, 10, 10));
		buttonBox.getChildren().addAll(numbersLabel, removeButton, removeNumberButton, addButton, addNumberButton);
		setBottom(buttonBox);

		// The method change is called when a row in the list view is selected. 
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				int index = listView.getSelectionModel().getSelectedIndex();
				if (index != -1) {
					addNumberButton.setDisable(false);
					removeNumberButton.setDisable(false);
					removeButton.setDisable(false);
					numbersLabel.setText(newValue + " " + phoneBook.findNumbers(newValue));
				} else {
					numbersLabel.setText("");
				}
			}
		});
		
		clearSelection();	
	}





	/**
	 * Clears all selections in the list view and disable all buttons except the
	 * button for adding a name.
	 */
	public void clearSelection() {
		addNumberButton.setDisable(true);
		removeNumberButton.setDisable(true);
		removeButton.setDisable(true);
		numbersLabel.setText("");
		listView.getSelectionModel().clearSelection();
	}
	
	/**
	 * Selects row index in the list view if index in [0, number rows).
	 * Otherwise nothing happens.
	 * @param index the index of the row to select
	 */
	public void select(int index) {
		listView.getSelectionModel().clearSelection();
		if (index > -1 && index < obsList.size()) {
			listView.getSelectionModel().select(index);
		}
	}

	/**
	 * Selects the row containing name. If no row contains the name nothing
	 * happens.
	 * @param name the name to select
	 */
	public void select(String name) {
		int index = obsList.indexOf(name);
		select(index);
	}
	
	/**
	 * Fills the rows in the list view with the strings in col.
	 * @param col a collection containing strings that will be displayed in the list view
	 */
	public void fillList(Collection<String> col) {
		obsList.setAll(col);
	}
	
	private void add() {
		clearSelection();
		String[] labels = {"Name", "Phone number"};
		Optional<String[]> result = Dialogs.twoInputsDialog("Add phone book entry", 
				"Enter the name and phone number and press Ok.", labels);
		if (result.isPresent()) {
			String[] inputs = result.get();
			if (inputs.length == 2) {
				boolean success = phoneBook.put(inputs[0], inputs[1]);
				if(success) {
					obsList.setAll(phoneBook.names());
					select(inputs[0]);
				} else {
					Dialogs.alert("Add", null, "Failed to add phone number.");
					select(inputs[0]);
				}
			}
		}	
	}
	
	private void addNumber() {
		int index = listView.getSelectionModel().getSelectedIndex();
		if (index != -1) {
			String name = obsList.get(index);
			Optional<String> result = Dialogs.oneInputDialog("Add phone number to " + name, "Enter the number to add", "Number" );
			if (result.isPresent()) {
				String input = result.get();
				boolean success = phoneBook.put(name, input);
				if(success) {
					select(index);
				} else {
					Dialogs.alert("Add", null, "Failed to add phone number.");
				}
			}	
		}	
	}
	
	
	private void remove() {

		if(Dialogs.confirmDialog("Remove", "Reomve Contact", "Do you really want to remove the contact?")){
			int index = listView.getSelectionModel().getSelectedIndex();
			if(index != -1){
				String name = obsList.get(index);
				if(phoneBook.remove(name)){
					obsList.setAll(phoneBook.names());
					Dialogs.alert("removed", name + " removed", name + " was removed from the phonebook");
					clearSelection();
				}
				
				else{
					Dialogs.alert("Error", "Error", name + " was NOT removed from the phonebook");
				}
				
			}
		}
		
	}
	
	private void removeNumber() {

		int index = listView.getSelectionModel().getSelectedIndex();
		if(index != -1){
			
			String name = obsList.get(index);
			Set numbers = phoneBook.findNumbers(name);
			Optional<String> nbr = Dialogs.oneInputDialog("Remove number", "Remove number", "Write in the number you want remove" + numbers.toString());
			
			if(numbers.contains(nbr.get())){
				if(phoneBook.removeNumber(name, nbr.get())){
					Dialogs.alert("Removed", "number removed", "The number: "  + nbr.get() + " was removed from " + name);
					select(index);
				}
				
			}
			else {
				Dialogs.alert("Error", "number NOT removed", "The number: "  + nbr.get() + " does not exist" + numbers.toString());
			}
		}

	
	}
	
}
