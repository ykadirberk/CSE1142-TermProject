


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
		LevelCreator level1 = new LevelCreator(2);// 1,2,3,4,5
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
		borders.setBottom(level1.constructCenter());

		Scene scene = new Scene(borders);
		
		
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

