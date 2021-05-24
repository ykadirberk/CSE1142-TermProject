import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BalloonClass extends Application {
	static Level levelH = new Level();

	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) throws Exception {
		
		BorderPane level = constructLevel(2);// 1, 2, 3, 4, 5

			
		stage.setScene(new Scene(level));
		stage.setTitle("deneme");
		stage.setResizable(false);
		stage.show();
	}
	
	public BorderPane constructLevel(int level) {
		
		LevelCreator levelx = new LevelCreator(level);
		BorderPane borders = new BorderPane();
		
		borders.setPadding(new Insets(0, 20, 0, 10));
		
		BalloonClass.levelH.setLevel_number(level);
		borders.setLeft(BalloonClass.levelH.getLevelText());
		borders.setCenter(BalloonClass.levelH.getScoreText());
		borders.setRight(BalloonClass.levelH.getHighscore_text());
		borders.setBottom(levelx.constructCenter());
		return borders;
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
			BalloonClass.levelH.applyHover(row, column);
		}
		
		//System.out.println("Toggle: " + row + ", " + column);

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
			BalloonClass.levelH.applyLeave(row, column);
		}
		//System.out.println("Left: " + row + ", " + column);

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
			//System.out.println("Click: "  + row + ", " + column);
			BalloonClass.levelH.applyClick(row, column);
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
			//System.out.println("Release: "  + row + ", " + column);
			BalloonClass.levelH.applyHit(row, column);
		}
	}
	
}

