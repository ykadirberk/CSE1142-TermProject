import java.io.File;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class LevelCreator {
	private File levelPath;
	public static Balloon[][] boxes = new Balloon[10][10];
	private int level;
	private String path;
	LevelCreator(int Level) {
		this.level = Level;
		String path = "../CSE1142-TermProject\\src\\levels\\level"+this.level+".txt";
		System.out.println(System.getProperty("user.dir"));
		this.levelPath = new File(path);
		System.out.println(this.path);
	}
	
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
		return gPane;
		
	}
	public static int nameToLifeConv(String name) {
		int durability = 0;
		switch(name) {
		case "Empty": 
			break;
		case "Mirror":
			durability = 1;
			break;
		case "Wood":
			durability = 2;
			break;
		}
		return durability;
	}
}