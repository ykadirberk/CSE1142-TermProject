import javafx.scene.Cursor;
import javafx.scene.shape.Rectangle;

public class Balloon extends Rectangle{
	private int life = 0;
	private int xPosition;
	private int yPosition;
	
	
	protected Balloon(int xPosition, int yPosition){	
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		super.setStyle("-fx-background-radius: 0em; -fx-fill: rgb(127,127,127); -fx-stroke: rgb(47,47,47); -fx-stroke-width: 3px;");
	}
	protected Balloon(int life, int xPosition, int yPosition) {
		this.life = life;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		setStyleProperties(life);
	}

	private void setStyleProperties(int x) {
		switch(x) {
		case 0:
			super.setStyle("-fx-background-radius: 0em; -fx-fill: rgb(255,255,255); -fx-stroke: rgb(195,195,195); -fx-stroke-width: 3px;");
			super.setCursor(Cursor.DEFAULT);
			break;
		case 1:
			super.setStyle("-fx-background-radius: 0em; -fx-fill: rgb(153,217,234); -fx-stroke: rgb(0,159,231); -fx-stroke-width: 3px;");
			super.setCursor(Cursor.HAND);
			break;
		case 2:
			super.setStyle("-fx-background-radius: 0em; -fx-fill: rgb(200,191,231); -fx-stroke: rgb(161,67,160); -fx-stroke-width: 3px;");
			super.setCursor(Cursor.HAND);
			break;
		}
	}
	public void isClicked() {
		
		if (!isClickable())
			return;
		this.life -= 1;
		setStyleProperties(this.life);
		
	}
	
	public Boolean isClickable() {
		return (this.life>0) ? true : false;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public int getxPosition() {
		return xPosition;
	}
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	public int getyPosition() {
		return yPosition;
	}
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	
	
	
}
