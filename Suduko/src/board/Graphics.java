package board;

import java.awt.TextField;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Graphics extends Application {
	
	private OneLetterTextField[][] tf;
	private Button button;
	private Label label;
	
	@Override
	public void start(Stage stage) {
		tf = new OneLetterTextField[9][9];
		
		button = new Button("Yes!");
		label = new Label("0");
		
		VBox root = new VBox();
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(button, label);
		
		Scene scene = new Scene(root, 400, 400);		
		stage.setScene(scene);	
	    stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}


