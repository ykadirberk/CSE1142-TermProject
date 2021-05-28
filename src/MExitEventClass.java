//Yasin Enes SISIK, 150119807 - Kadir Berk YAGAR, 150120016
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
	// Definition of the class MExitEventClass()
	// This class implements leave effect on the balloons
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
		System.out.println("Left:\t" + row + ", " + column);

	}
}