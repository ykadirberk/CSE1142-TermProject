//Yasin Enes SISIK, 150119807 - Kadir Berk YAGAR, 150120016
import java.io.File;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
//Definition of the class LevelCreator
//This class constructs level, digs high scores.
public class LevelCreator {
	
	public static int levelLife = 0;
	private File levelPath;
	public static Balloon[][] boxes = new Balloon[10][10];
	private int level;
	private String path;
	LevelCreator(int Level) {
		

		this.level = Level;
		path = "../CSE1142-TermProject\\src\\levels\\level"+this.level+".txt";
		this.levelPath = new File(path);
		
	}
	// Definition of the function constructCenter()
	// This function constructs balloons grid pane, and return grid pane
	// Assigns balloons to the event classes. It get level from levels directory, and adjusts them to the as balloon
	public GridPane constructCenter() {
		
		GridPane gPane = new GridPane();
		gPane.setPadding(new Insets(1, 1, 1, 1));
		gPane.setMinSize(500, 500);
		gPane.setHgap(2);
		gPane.setVgap(2);

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				
				LevelCreator.boxes[i][j] = new Balloon(i,j);
				LevelCreator.boxes[i][j].heightProperty().bind(gPane.heightProperty().divide(12));
				LevelCreator.boxes[i][j].widthProperty().bind(gPane.widthProperty().divide(11));

				HoverEventClass toggle = new HoverEventClass(i,j);
				PressEventClass press = new PressEventClass(i,j);
				ReleaseEventClass release = new ReleaseEventClass(i,j);
				MExitEventClass exit = new MExitEventClass(i, j);
				
				LevelCreator.boxes[i][j].setOnMouseEntered(toggle);
				LevelCreator.boxes[i][j].setOnMousePressed(press);
				LevelCreator.boxes[i][j].setOnMouseReleased(release);
				LevelCreator.boxes[i][j].setOnMouseExited(exit);
				
				gPane.add(LevelCreator.boxes[i][j], j, i);
			}
		}
		
		try(Scanner input = new Scanner(levelPath)){
			while(input.hasNext()) {
				
				String[] s = input.nextLine().split(",");
				int x = Integer.parseInt(s[1]);
				int y = Integer.parseInt(s[2]);
				LevelCreator.boxes[x][y].setStyleProperties(nameToLifeConv(s[0]), 0);
				LevelCreator.boxes[x][y].setLife(nameToLifeConv(s[0]));
			}
			
			
		}catch(Exception ex) {
			
		}
		levelLife/=2;
		return gPane;
		
	}
	
	// Definition of the function nameToLifeConv()
	// This function converts items to the durability while reading level files
	public int nameToLifeConv(String name) {
		int durability = 0;
		switch(name) {
		case "Empty": 
			break;
		case "Mirror":
			levelLife+=1;
			durability = 1;
			break;
		case "Wood":
			levelLife+=2;
			durability = 2;
			break;
		}
		return durability;
	}

}