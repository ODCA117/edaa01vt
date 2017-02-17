package board;

import solver.Sudoku;

import java.awt.TextField;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Graphics extends Application {
	
	private OneLetterTextField tf0;
	private OneLetterTextField tf1;
	private OneLetterTextField tf2;
	private OneLetterTextField tf3;
	private Button buttonSolve;
	private Button buttonQuit;
	
	@Override
	public void start(Stage stage) {
		buttonSolve = new Button("Solve");
		buttonSolve.setOnAction(new ButtonSolveHandler());
		buttonQuit = new Button("Quit");
		buttonQuit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent ecent){
				Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to quit?");
				Optional<ButtonType> result = alert.showAndWait();
				if(result.isPresent() && result.get() == ButtonType.OK)
					Platform.exit();
			}
		});
		
		tf0 = new OneLetterTextField();
		tf1 = new OneLetterTextField();
		tf2 = new OneLetterTextField();
		tf3 = new OneLetterTextField();
		
		//button = new Button("Yes!");
		//label = new Label("0");
		
		VBox root = new VBox();
		HBox row = new HBox();
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.BOTTOM_RIGHT);
		buttons.setSpacing(20);
		buttons.getChildren().addAll(buttonQuit,buttonSolve);
		
		row.setSpacing(5);
		row.getChildren().addAll(tf0,tf1,tf2,tf3);
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(row, buttons);
		
		Scene scene = new Scene(root, 400, 400);		
		stage.setScene(scene);	
	    stage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void getValuesFromField(Sudoku sud){ //Eventuellt boolean
		
	}
	
	private class ButtonSolveHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event){
			
			int first = Integer.parseInt(tf0.getText());
			int second = Integer.parseInt(tf1.getText());
			
			Alert alert = new Alert(AlertType.INFORMATION, "First number: " + first +
					", secound number: " + second);
			alert.show();
//			Create a new Sudoku
//			Get values from Text Fields and add them to Sudoku object
//			sud.Solve
//			If (solution == true){
//				Print the solution
//			}
//			else{
//				Messagebox("No solution found")
//			}
				
		}
	}
	
	
	
}


