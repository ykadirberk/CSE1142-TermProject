//Yasin Enes SISIK, 150119807 - Kadir Berk YAGAR, 150120016

import javafx.scene.Cursor;
import javafx.scene.shape.Rectangle;
	//Definition of the class Balloon
	// This class is the customized from Rectangle
	// and, it has required properties
public class Balloon extends Rectangle{
	
	/* Style sheet for 0 durability balloon*/
	private final String STYLE_NORMAL_0 = "-fx-background-radius: 0em; -fx-fill: rgb(255,255,255); -fx-stroke: rgb(195,195,195); -fx-stroke-width: 3px;";
	
	/* Style sheets for 1 durability balloons*/
	private final String STYLE_NORMAL_1 = "-fx-background-radius: 0em; -fx-fill: rgb(153,217,234); -fx-stroke: rgb(0,159,231); -fx-stroke-width: 3px;";
	private final String STYLE_HOVER_1 = "-fx-background-radius: 0em; -fx-fill: rgb(153,255,234); -fx-stroke: rgb(0,195,231); -fx-stroke-width: 3px;";
	private final String STYLE_CLICK_1 = "-fx-background-radius: 0em; -fx-fill: rgb(153,195,234); -fx-stroke: rgb(0,140,231); -fx-stroke-width: 2px;";
	
	/* Style sheets for 2 durability balloon*/
	private final String STYLE_NORMAL_2 = "-fx-background-radius: 0em; -fx-fill: rgb(200,191,231); -fx-stroke: rgb(161,67,160); -fx-stroke-width: 3px;";
	private final String STYLE_HOVER_2 = "-fx-background-radius: 0em; -fx-fill: rgb(200,235,231); -fx-stroke: rgb(161,105,160); -fx-stroke-width: 3px;";
	private final String STYLE_CLICK_2 = "-fx-background-radius: 0em; -fx-fill: rgb(200,170,231); -fx-stroke: rgb(161,50,160); -fx-stroke-width: 2px;";
	
	/* Constant values to represent the action on style/durability changes*/
	private final int NORMAL_ = 0; 
	private final int HOVER_ = 1;
	private final int CLICK_ = 2;
	
	private int life = 0; //durability
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
		setStyleProperties(life, NORMAL_);
	}
	// Definition of the function setStyleProperties()
	// This function changes the Balloon styles
	void setStyleProperties(int health, int type) {
		switch(health) {
			case 0: //Empty balloons
				super.setStyle(STYLE_NORMAL_0);
				super.setCursor(Cursor.DEFAULT);
				break;
			case 1: //Mirror balloons
				if (type == NORMAL_) {
					super.setStyle(STYLE_NORMAL_1);
				}
				if (type == HOVER_) {
					super.setStyle(STYLE_HOVER_1);
				}
				if (type == CLICK_) {
					super.setStyle(STYLE_CLICK_1);
				}
				super.setCursor(Cursor.HAND);
				break;
			case 2: //Wood balloons
				if (type == NORMAL_) {
					super.setStyle(STYLE_NORMAL_2);
				}
				if (type == HOVER_) {
					super.setStyle(STYLE_HOVER_2);
				}
				if (type == CLICK_) {
					super.setStyle(STYLE_CLICK_2);
				}
				super.setCursor(Cursor.HAND);
				break;
		}
	}
	
	// Definition of the function isClicked()
	// This function executes the click action, which is popping the balloon.
	public void isClicked() {
		if (!isClickable())
			return;
		this.life -= 1;
		LevelCreator.levelLife-=1;
		setStyleProperties(this.life, NORMAL_);
	}
	
	// Definition of the function isClickable()
	// This function checks balloons lives.
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
	
	public void hover() {
		setStyleProperties(this.life, HOVER_);
	}
	
	public void leave() {
		setStyleProperties(this.life, NORMAL_);
	}
	
	public void click() {
		setStyleProperties(this.life, CLICK_);
	}
	
	
}
