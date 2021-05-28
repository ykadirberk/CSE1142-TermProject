//Yasin Enes SISIK, 150119807 - Kadir Berk YAGAR, 150120016


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

 	// Definition of the class HoverEventClass
	// This class implements hover effect on the balloons
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
		
		System.out.println("Toggle:\t" + row + ", " + column);

	}
}
