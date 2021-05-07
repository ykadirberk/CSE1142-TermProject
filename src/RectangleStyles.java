import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RectangleStyles extends Application{
	public void start(Stage stage) {
		GridPane gPane = new GridPane();
		
		Rectangle rectangle1 = new Rectangle();
		rectangle1.setStyle("-fx-background-radius: 0em; -fx-fill: rgb(127,127,127); -fx-stroke: rgb(47,47,47); -fx-stroke-width: 3px;");
		rectangle1.heightProperty().bind(gPane.heightProperty().divide(4));
		rectangle1.widthProperty().bind(gPane.widthProperty().divide(4));
		
		Rectangle rectangle2 = new Rectangle();
		rectangle2.setStyle("-fx-background-radius: 0em; -fx-fill: rgb(255,255,255); -fx-stroke: rgb(195,195,195); -fx-stroke-width: 3px;");
		rectangle2.heightProperty().bind(gPane.heightProperty().divide(4));
		rectangle2.widthProperty().bind(gPane.widthProperty().divide(4));
		
		Rectangle rectangle3 = new Rectangle();
		rectangle3.setStyle("-fx-background-radius: 0em; -fx-fill: rgb(153,217,234); -fx-stroke: rgb(0,159,231); -fx-stroke-width: 3px;");
		rectangle3.heightProperty().bind(gPane.heightProperty().divide(4));
		rectangle3.widthProperty().bind(gPane.widthProperty().divide(4));
		
		Rectangle rectangle4 = new Rectangle();
		rectangle4.setStyle("-fx-background-radius: 0em; -fx-fill: rgb(200,191,231); -fx-stroke: rgb(161,67,160); -fx-stroke-width: 3px;");
		rectangle4.heightProperty().bind(gPane.heightProperty().divide(4));
		rectangle4.widthProperty().bind(gPane.widthProperty().divide(5));
		
		gPane.add(rectangle1, 1, 0);
		gPane.add(rectangle2, 2, 0);
		gPane.add(rectangle3, 3, 0);
		gPane.add(rectangle4, 4, 0);
		
		Scene scene = new Scene(gPane,200,200);
		stage.setScene(scene);
		stage.show();
		
	}
	public static void main(String[] args) {
			launch(args);

	}

}
