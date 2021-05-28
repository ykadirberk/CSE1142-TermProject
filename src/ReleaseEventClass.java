//Yasin Enes SISIK, 150119807 - Kadir Berk YAGAR, 150120016
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

class ReleaseEventClass implements EventHandler<MouseEvent> {
	public int row;
	public int column;
 	// Definition of the class HoverEventClass
	// This class implements hit on the balloons
	public ReleaseEventClass(int row, int column) {
		this.row = row;
		this.column = column;
		
	}
	
	@Override
	public void handle(MouseEvent e) {
		Balloon thisBox = LevelCreator.boxes[row][column];
		if(thisBox.getLife() != 0) {
			System.out.println("Release:"  + row + ", " + column);
			BalloonClass.levelH.applyHit(row, column);
		}
	}
	
}