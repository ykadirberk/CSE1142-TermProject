import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BalloonClass extends Application {
	static Level levelH = new Level();

	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) throws Exception {
		
		VBox level = constructLevel(3);// 1, 2, 3, 4, 5

		stage.setScene(new Scene(level));
		stage.setTitle("deneme");
		stage.setResizable(false);
		stage.show();
	}
	
	public VBox constructLevel(int level) {
		
		LevelCreator levelx = new LevelCreator(level);
		VBox vert_box = new VBox();
		BorderPane borders = new BorderPane();
		
		borders.setPadding(new Insets(0, 20, 0, 10));
		
		BalloonClass.levelH.setLevel_number(level);
		borders.setLeft(BalloonClass.levelH.getLevelText());
		borders.setCenter(BalloonClass.levelH.getScoreText());
		borders.setRight(BalloonClass.levelH.getHighscore_text());
		borders.setBottom(levelx.constructCenter());
		vert_box.getChildren().addAll(borders, levelH.getFeedbackText()); 
		return vert_box;
	}

}






