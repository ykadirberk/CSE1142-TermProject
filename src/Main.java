import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		
		Rectangle rect0 = new Rectangle(40, 40);
		rect0.setArcHeight(25);
		rect0.setArcWidth(25);
		rect0.setFill(Color.rgb(255,0,0));
		rect0.setStroke(Color.BLUE);
		rect0.setStrokeWidth(1);
		
		Rectangle rect1 = new Rectangle(40, 40);
		rect1.setArcHeight(40);
		rect1.setArcWidth(40);
		rect1.setFill(Color.rgb(0,255,0));
		rect1.setStroke(Color.BLUE);
		rect1.setStrokeWidth(3);
		
		Rectangle rect2 = new Rectangle(40, 40);
		rect2.setArcHeight(0);
		rect2.setArcWidth(0);
		rect2.setFill(Color.rgb(0,0,255));
		rect2.setStroke(Color.RED);
		rect2.setStrokeWidth(5);
		
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(5,5,5,5));
		pane.setHgap(5.25);
		pane.setVgap(5.25);
		
		pane.add(rect0, 0, 0);
		pane.add(rect1, 2, 2);
		pane.add(rect2, 1, 3);
		
		stage.setTitle("deneme");
		stage.setScene(scene);
		stage.show();
	}

}
