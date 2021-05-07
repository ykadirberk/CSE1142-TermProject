

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BalloonClassTry extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) throws Exception {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(0, 0, 0, 0));
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
		borders.setLeft(left);
		borders.setCenter(mid);
		borders.setRight(right);
		borders.setBottom(pane);

		Scene scene = new Scene(borders);
		Ballonn[][] boxes = new Ballonn[10][10];
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				boxes[i][j] = new Ballonn((int)(Math.random()*3),i,j);
				boxes[i][j].heightProperty().bind(scene.heightProperty().divide(12));
				boxes[i][j].widthProperty().bind(scene.widthProperty().divide(11));

	
				ClickEventClass click = new ClickEventClass(i,j);
				ToggleEventClass toggle = new ToggleEventClass(i,j);
				boxes[i][j].setOnMouseClicked(click);
				boxes[i][j].setOnMouseEntered(toggle);
				
				pane.add(boxes[i][j], j, i);
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
		System.out.println("Click: "  + row + ", " + column);
		Object source = e.getTarget();
		if(source instanceof Ballonn) {
			((Ballonn) source).isClicked();
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

