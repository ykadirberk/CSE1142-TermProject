import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BalloonClass extends Application {
	
	static Level level = new Level();
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) throws Exception {
		LevelCreator level1 = new LevelCreator(2); // 1,2,3,4,5
		
		BorderPane borders = new BorderPane();
		borders.setPadding(new Insets(0, 20, 0, 10));
		borders.setLeft(level.getLevelText());
		borders.setCenter(level.getScoreText());
		borders.setRight(level.getHighscoreText());
		borders.setBottom(level1.constructCenter());

		Scene scene = new Scene(borders);
		
		stage.setTitle("deneme");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

}

class HoverEventClass implements EventHandler<MouseEvent> {
	public int row;
	public int column;
	
	public HoverEventClass(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	@Override
	public void handle(MouseEvent e) {
		Balloon thisBox = LevelCreator.boxes[row][column];
		if(thisBox.getLife() != 0) {
			BalloonClass.level.applyHover(row, column);
		}
		
		System.out.println("Toggle: " + row + ", " + column);

	}
}

class MExitEventClass implements EventHandler<MouseEvent> {
	public int row;
	public int column;
	
	public MExitEventClass(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	@Override
	public void handle(MouseEvent e) {
		Balloon thisBox = LevelCreator.boxes[row][column];
		if(thisBox.getLife() != 0) {
			BalloonClass.level.applyLeave(row, column);
		}
		System.out.println("Left: " + row + ", " + column);

	}
}

class PressEventClass implements EventHandler<MouseEvent> {
	public int row;
	public int column;
	
	public PressEventClass(int row, int column) {
		this.row = row;
		this.column = column;
		
	}
	
	@Override
	public void handle(MouseEvent e) {
		Balloon thisBox = LevelCreator.boxes[row][column];
		if(thisBox.getLife() != 0) {
			System.out.println("Click: "  + row + ", " + column);
			BalloonClass.level.applyClick(row, column);
		}
	}
	
}

class ReleaseEventClass implements EventHandler<MouseEvent> {
	public int row;
	public int column;
	
	public ReleaseEventClass(int row, int column) {
		this.row = row;
		this.column = column;
		
	}
	
	@Override
	public void handle(MouseEvent e) {
		Balloon thisBox = LevelCreator.boxes[row][column];
		if(thisBox.getLife() != 0) {
			System.out.println("Release: "  + row + ", " + column);
			BalloonClass.level.applyHit(row, column);
		}
	}
	
}

