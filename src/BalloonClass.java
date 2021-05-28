//Yasin Enes SISIK, 150119807 - Kadir Berk YAGAR, 150120016

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BalloonClass extends Application {
	static Level levelH = new Level();
	int level= 1;
	
	String normal_style = "-fx-background-color: #06112b; -fx-text-fill: white;";
	String hover_style = "-fx-background-color: #081b38; -fx-text-fill: white;";
	String press_style = "-fx-background-color: #050d26; -fx-text-fill: gray;";
	
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
		gPane.setVgap(5);
	
		Button start = new Button("Start");
		start.setMinWidth(150);
		start.setMinHeight(40);
		initStylesHandlers(start);
		start.setOnAction(e -> levels(stage));

		gPane.add(start,  0, 3);

		
		Scene scene = new Scene(gPane,500,500);
		stage.setScene(scene);
		stage.setTitle("Blow'emUp");
		stage.show();
	}
	
	private void initStylesHandlers(Button b) {
		b.setStyle(normal_style);
		b.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				b.setStyle(hover_style);
			}
		});
		b.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				b.setStyle(normal_style);
			}
		});
		
		b.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				b.setStyle(press_style);
			}
		});
	}
	
	// Definition of the function LevelCreator()
	// This function builds levels stages
	// and connects levels with button
	public void levels(Stage stage) {
		LevelCreator levelx = new LevelCreator(level);
		VBox vert_box = new VBox();
		BorderPane border_pane = new BorderPane();
		BorderPane borders = new BorderPane();
		
		borders.setPadding(new Insets(0, 20, 0, 10));
		
		BalloonClass.levelH.setLevel_number(level);
		borders.setLeft(BalloonClass.levelH.getLevelText());
		borders.setCenter(BalloonClass.levelH.getScoreText());
		borders.setRight(BalloonClass.levelH.getHighscore_text());
		borders.setBottom(levelx.constructCenter());
		
		level++;
		
		initStylesHandlers(BalloonClass.levelH.getNextBtn());
		
		border_pane.setLeft(levelH.getFeedbackText());
		border_pane.setRight(BalloonClass.levelH.getNextBtn());
		vert_box.getChildren().addAll(borders, border_pane); 
		
		BalloonClass.levelH.getNextBtn().setOnAction(e-> {
				LevelCreator.levelLife=0;
				levelH.setCurrent_score(0);
				levels(stage);
				levelH.getNextBtn().visibleProperty().setValue(Boolean.FALSE);});
		
		stage.setScene(new Scene(vert_box));
		stage.setTitle("Blow'emUp");
		stage.setResizable(false);
		stage.show();
		
	}
}
