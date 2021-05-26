import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

class PressEventClass implements EventHandler<MouseEvent> {
	public int row;
	public int column;
	
	public PressEventClass(int row, int column) {
		this.row = row;
		this.column = column;
		
	}
	
	@Override
	public void handle(MouseEvent e) {
		Balloon thisBox = LevelCreator.boxes[row][column];
		if(thisBox.getLife() != 0) {
			System.out.println("Click:\t"  + row + ", " + column);
			BalloonClass.levelH.applyClick(row, column);
		}
	}
	
}