
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
/* Outermost layer is a border pane ,which is called **bPane**, and it consist of three pieces Top,Center,Bottom. 
The top part has a border pane ,which is formed by **getTopBorderPane(BorderPane bPane)**, to hold level,score, 
and best score variables. The Center part consist of a grid pane which is formed by **getCenterGridPane()** . Finally,
 the bottom part is only has a text */



public class GameLayoutDesign extends Application {
	public void start(Stage primaryStage) {
		BorderPane bPane = new BorderPane();// Initial the border Pane
		
		bPane.setTop(getTopBorderPane(bPane));
		bPane.setCenter(getCenterGridPane());
		bPane.setBottom(new Text("--Text--"));
		
		bPane.setAlignment(getCenterGridPane(), Pos.BASELINE_CENTER);//Assign the center to right+left+center
		
		Scene scene = new Scene(bPane);
		
		//Set scene properties
		primaryStage.setTitle("LELEYLELELELEYLELELE");
		primaryStage.setScene(scene);
		primaryStage.setMinWidth(320);
		primaryStage.setMinHeight(400);
		
		
		primaryStage.show();
	}
	
	private BorderPane getTopBorderPane(BorderPane bPane) {
		
		BorderPane bTopPane = new BorderPane();
		
		bTopPane.setPadding(new Insets(5,5,5,5));
		
		bTopPane.setLeft(new Text("Level"));
		bTopPane.setCenter(new Text("Current Score:0"));
		bTopPane.setRight(new Text("High Score: 3"));
		
		return bTopPane;
		
	}
	
	
	private GridPane getCenterGridPane() {
		
		GridPane gPane = new GridPane();
		
		gPane.setPadding(new Insets(1,1,1,1));
		gPane.setStyle("-fx-background-color: gray;");// Set gridpane's background color to gray
		
		gPane.setHgap(2); //horizontal gap in pixels
		gPane.setVgap(2); //vertical gap in pixels
		
		//****************** Create button matrix*************
		for(int i= 0; i<10; i++) {
			for(int j=0; j<10; j++) {
				
				Button btOK = new Button();
				
				//Set Button style
				btOK.setStyle("-fx-background-radius: 0em;");// Set corner radius to 0 for making button a rectangle
				btOK.setStyle( "-fx-background-color: gray; -fx-border-color: black; -fx-border-width: 1px;" );
				
				//Set Button height and width, and bound them to the gPane's height and width properties.
				btOK.prefHeightProperty().bind(gPane.heightProperty().divide(10));
				btOK.prefWidthProperty().bind(gPane.widthProperty().divide(10));
				
				
				gPane.add(btOK,i,j);//Assign button to grid pane with a particular location.
			}
		}
		return gPane;
	}

	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
