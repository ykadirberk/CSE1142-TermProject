

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BalloonClassTry extends Application {
	
	static Level level = new Level();
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) throws Exception {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(0, 10, 0, 10));
		pane.setMinSize(500, 500);
		pane.setHgap(2);
		pane.setVgap(2);
		
		Text left = new Text();
		left.setText("Level #1");
		Text mid = new Text();
		mid.setText("Score: 0");
		Text right = new Text();
		right.setText("HighScore: X");
		
		BorderPane borders = new BorderPane();
		borders.setPadding(new Insets(0, 10, 0, 10));
		borders.setLeft(level.getLevelText());
		borders.setCenter(level.getScoreText());
		borders.setRight(level.getHighscoreText());
		borders.setBottom(pane);

		Scene scene = new Scene(borders);
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				LevelCreator.boxes[i][j] = new Balloon((int)(Math.random()*3),i,j);
				LevelCreator.boxes[i][j].heightProperty().bind(scene.heightProperty().divide(12));
				LevelCreator.boxes[i][j].widthProperty().bind(scene.widthProperty().divide(11));

	
				ClickEventClass click = new ClickEventClass(i,j);
				ToggleEventClass toggle = new ToggleEventClass(i,j);
				LevelCreator.boxes[i][j].setOnMouseClicked(click);
				LevelCreator.boxes[i][j].setOnMouseEntered(toggle);
				
				pane.add(LevelCreator.boxes[i][j], j, i);
			}
		}
		
		stage.setTitle("deneme");
		stage.setScene(scene);
		stage.show();
	}

}

class ClickEventClass implements EventHandler<MouseEvent> {
	public int row;
	public int column;
	public ClickEventClass(int row, int column) {
		this.row = row;
		this.column = column;
		
	}
	
	@Override
	public  void handle(MouseEvent e) {
		Balloon thisBox = LevelCreator.boxes[row][column];
		if(thisBox.getLife() != 0) {
			System.out.println("Click: "  + row + ", " + column);
			BalloonClassTry.level.applyHit(row, column);
		}
	}
	
}

class ToggleEventClass implements EventHandler<MouseEvent> {
	public int row;
	public int column;
	
	public ToggleEventClass(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	@Override
	public void handle(MouseEvent e) {
		System.out.println("Toggle: " + row + ", " + column);

	}
}

