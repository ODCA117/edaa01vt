package board;

import solver.Sudoku;

import java.awt.TextField;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ChoiceDialog;
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
	private Button buttonColor;
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("SudokuSolver");
		
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
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog");
				alert.setHeaderText("Quit");
				alert.setContentText("Are you sure you want to quit?");
				Optional<ButtonType> result = alert.showAndWait();
				if(result.isPresent() && result.get() == ButtonType.OK)
					Platform.exit();
			}
		});
		
		buttonColor = new Button("Color");
		buttonColor.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				List<String> choices = new ArrayList<>();
				choices.add("yellow");
				choices.add("orange");
				choices.add("lightblue");
				choices.add("pink");
				choices.add("lightgreen");
				choices.add("lightgray");

				ChoiceDialog<String> dialog = new ChoiceDialog<>("yellow", choices);
				dialog.setTitle("Color");
				dialog.setHeaderText("Color");
				dialog.setContentText("Choose the color of textfield-background");

				String color = "yellow";
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
				    color = result.get();
				}
				
				//fixa f�rgen i rutorna
				for(int i = 0; i < tfArray.length; i++){
					for(int j = 0; j < tfArray[i].length; j++){
						if(i < 3 && j < 3){		//uppe v�nster
							tfArray[i][j].setStyle("-fx-control-inner-background:" + color);
						}
						else if(i > 5 && j < 3){		//nere v�nster
							tfArray[i][j].setStyle("-fx-control-inner-background:" + color);
						}
						else if(i > 2 && i < 6 && j > 2 && j < 6){ 		//mitten
							tfArray[i][j].setStyle("-fx-control-inner-background:" + color);
						}
						else if(i > 5 && j > 5){ 		//nere h�ger
							tfArray[i][j].setStyle("-fx-control-inner-background:" + color);
						}
						else if(i < 3 && j > 5){		//uppe h�ger
							tfArray[i][j].setStyle("-fx-control-inner-background:" + color);
						}
					}
				}
				
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
			row[r].setSpacing(20);
			
			row[r].getChildren().addAll(tfArray[r]);
		}
		
		HBox buttons = new HBox();		
		buttons.setAlignment(Pos.BOTTOM_CENTER);
		buttons.setSpacing(50);
		buttons.getChildren().addAll(buttonReset, buttonSolve, buttonQuit, buttonColor);
		
		//Set up/add to root
		root.setPadding(new Insets(10, 50, 10, 50));
		root.setSpacing(20);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(row);
		root.getChildren().add(buttons);
		
		//set scene
		Scene scene = new Scene(root, 500, 500);		
		stage.setScene(scene);	
	    stage.show();
	    
	    
	  //fixa f�rgen i rutorna
	    String color = "yellow";
		for(int i = 0; i < tfArray.length; i++){
			for(int j = 0; j < tfArray[i].length; j++){
				if(i < 3 && j < 3){		//uppe v�nster
					tfArray[i][j].setStyle("-fx-control-inner-background:" + color);
				}
				else if(i > 5 && j < 3){		//nere v�nster
					tfArray[i][j].setStyle("-fx-control-inner-background:" + color);
				}
				else if(i > 2 && i < 6 && j > 2 && j < 6){ 		//mitten
					tfArray[i][j].setStyle("-fx-control-inner-background:" + color);
				}
				else if(i > 5 && j > 5){ 		//nere h�ger
					tfArray[i][j].setStyle("-fx-control-inner-background:" + color);
				}
				else if(i < 3 && j > 5){		//uppe h�ger
					tfArray[i][j].setStyle("-fx-control-inner-background:" + color);
				}
			}
		}
	    
	}
	
	
	public static void main(String[] args) {
		launch(args);
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
			
			//Create a new Sudoku
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
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Error");
				alert.setContentText("No solution was found!");
				alert.show();
				
				for(int i = 0; i < tfArray.length; i++){
					for(int j = 0; j < tfArray[i].length; j++){
						if(sud.getNumber(i, j) == 0)
						tfArray[i][j].setText("");
					}
				}
			}
				
		}
	}
}