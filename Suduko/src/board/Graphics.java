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
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.Scope;

public class Graphics extends Application {
	
	
	//Deklare Variabels
	private OneLetterTextField[][] tfArray;
	private Button buttonSolve;
	private Button buttonReset;
	private Button buttonQuit;
	
	@Override
	public void start(Stage stage) {
		
		/*set buttons*/
		buttonSolve = new Button("Solve");
		buttonSolve.setOnAction(new ButtonSolveHandler());
		buttonReset = new Button("Clear");
		buttonReset.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				for(int i = 0; i < tfArray.length; i++){
					for(int j = 0; j < tfArray[i].length; j++){
						tfArray[i][j].setText("");
					}
				}
			}
		});
		
		buttonQuit = new Button("Quit");
		buttonQuit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to quit?");
				Optional<ButtonType> result = alert.showAndWait();
				if(result.isPresent() && result.get() == ButtonType.OK)
					Platform.exit();
			}
		});
		
		//set Textfields
		tfArray = new OneLetterTextField[9][9];
		for(int i = 0; i < tfArray.length; i++){
			
			for(int j = 0; j < tfArray[i].length; j++){
				OneLetterTextField t = new OneLetterTextField();
				tfArray[i][j] = t;
			}
		}
		
		//Add VBox and HBox
		VBox root = new VBox();
		
		HBox[] row = new HBox[9];
		
		
		for(int r = 0; r < row.length; r++){
			row[r] = new HBox();
			row[r].setSpacing(5);
			
			row[r].getChildren().addAll(tfArray[r]);
		}
		
		HBox buttons = new HBox();		
		buttons.setAlignment(Pos.BOTTOM_RIGHT);
		buttons.setSpacing(20);
		buttons.getChildren().addAll(buttonQuit, buttonReset,buttonSolve);
		
		//Set up/add to root
		root.setPadding(new Insets(10, 50, 10, 50));
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);
//		for(int r = 0; r < row.length; r++){
//			root.getChildren().add(row[r]);
//		}
		root.getChildren().addAll(row);
		root.getChildren().add(buttons);
		
		//set scene
		Scene scene = new Scene(root, 500, 500);		
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
			
			int[][] numbers = new int[tfArray.length][tfArray[0].length];
			
			for(int i = 0; i < tfArray.length; i++){
				for(int j = 0; j < tfArray[i].length; j++){
					if(tfArray[i][j].getText().isEmpty()){
						tfArray[i][j].setText("0"); 
					}
					numbers[i][j] = Integer.parseInt(tfArray[i][j].getText());
				}
			}
			
			
			
			
			
//			Create a new Sudoku
			Sudoku sud =  new Sudoku();
			sud.setTable(numbers);
			
			boolean solved = sud.solve();
			
			if(solved){
				for(int i = 0; i < tfArray.length; i++){
					for(int j = 0; j < tfArray[i].length; j++){
						tfArray[i][j].setText(Integer.toString(sud.getNumber(i, j))); 
					}
				}
			}
			else{
				Alert alert = new Alert(AlertType.INFORMATION, "Ingen lösning hittades");
				alert.show();
			}
				
			
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


