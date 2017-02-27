package application;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import java.util.Optional;

import javax.swing.JFileChooser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import phonebook.MapPhoneBook;
import phonebook.PhoneBook;

public class PhoneBookApplication extends Application{
	private PhoneBook phoneBook;
	private NameListView nameListView;
	private File file;
	
	private static final Long serialVersionUID = 1L;

	/**
	 * The entry point for the Java program.
	 * @param args
	 */
	public static void main(String[] args) {	
		// launch() do the following:
		// - creates an instance of the Main class
		// - calls Main.init()
		// - create and start the javaFX application thread
		// - waits for the javaFX application to finish (close all windows)
		// the javaFX application thread do:
		// - calls Main.start(Stage s)
		// - runs the event handling loop
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		phoneBook = new MapPhoneBook();
		
		// set default locale english 
		Locale.setDefault(Locale.ENGLISH);
		
		readFromFile();
		
		nameListView = new NameListView(phoneBook);
		BorderPane root = new BorderPane();
		root.setTop(new PhoneBookMenu(phoneBook, nameListView));
		root.setCenter(nameListView);		
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("PhoneBook");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		

		
	}

	@Override
	public void stop(){
		// Here you can place any action to be done when the application is closed, i.e. save phone book to file.
		
		boolean op =  Dialogs.confirmDialog("Save", "Save to File", "Do you want to save to File?");
		
		if(op)
			saveToFile();
	}
	
	private void saveToFile(){
		
		JFileChooser fc =  new JFileChooser();
		
		if (file == null){
			Optional<String> f = Dialogs.oneInputDialog("Name", "Name of File", "Choose a name for the file");
			file = new File(f.get() + ".txt");

		}
		
			try{
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
				out.writeObject(phoneBook);
				out.close();
				
			} catch(Exception e){
				e.printStackTrace();
				System.exit(1);
			}
		
	}
	
	private void readFromFile(){
		
		
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(fc);
		
		if(returnVal == JFileChooser.APPROVE_OPTION){
			file = fc.getSelectedFile();
			try{
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				phoneBook = (PhoneBook) in.readObject();
				in.close();
			} catch(Exception e){
				e.printStackTrace();
				System.exit(1);
			}
		}
		
		
		
	}
	
}
