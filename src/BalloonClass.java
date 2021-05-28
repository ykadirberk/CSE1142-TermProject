//Yasin Enes SISIK, 150119807
//Kadir Berk YAGAR, 150120016

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BalloonClass extends Application {
	static Level levelH = new Level();
	int level= 1;
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) throws Exception {
		
		buildScene(stage);
		

	}
	
	// Definition of the function buildScene()
	// This function builds menu stage
	public void buildScene(Stage stage) {
		GridPane gPane = new GridPane();
		gPane.setStyle("-fx-background-color: #7986cb");
		gPane.setAlignment(Pos.CENTER);
		Button start = new Button("Start");
		Button aboutUs = new Button("About Us");
		gPane.addColumn(0, start,aboutUs);
		start.setOnAction(e -> levels(stage));
		
		Scene scene = new Scene(gPane,500,500);
		stage.setScene(scene);
		stage.show();
		
	}
	
	// Definition of the function LevelCreator()
	// This function builds levels stages
	// and connects levels with button
	public void levels(Stage stage) {
		LevelCreator levelx = new LevelCreator(level);
		VBox vert_box = new VBox();
		BorderPane borders = new BorderPane();
		
		borders.setPadding(new Insets(0, 20, 0, 10));
		
		BalloonClass.levelH.setLevel_number(level);
		borders.setLeft(BalloonClass.levelH.getLevelText());
		borders.setCenter(BalloonClass.levelH.getScoreText());
		borders.setRight(BalloonClass.levelH.getHighscore_text());
		borders.setBottom(levelx.constructCenter());
		level++;
		Button next = new Button("Next");
		vert_box.getChildren().addAll(borders, levelH.getFeedbackText(),next); 
		next.setOnAction(e-> {
			levelH.setCurrent_score(0);
			levels(stage);
		});
		stage.setScene(new Scene(vert_box));
		stage.setTitle("deneme");
		stage.setResizable(false);
		stage.show();
		
	}
}
