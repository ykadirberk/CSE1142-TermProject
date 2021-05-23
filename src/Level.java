
import java.util.ArrayList;

import javafx.scene.text.Text;

public class Level {
	private int current_score = 0;
	private int level_number = 0;
	private int high_score = 0;
	private Text level_text;
	private Text highscore_text;
	private Text score_text;
	
	
	public Level() {
		level_text = new Text("Level #" + level_number);
		score_text = new Text("Score: " + current_score);
		highscore_text = new Text("HighScore: " + high_score);
	}
	
	public Level(int current_score, int level_number, int high_score) {
		this.current_score = current_score;
		this.level_number = level_number;
		this.high_score = high_score;
		
		level_text = new Text("Level #" + level_number);
		score_text = new Text("Score: " + current_score);
		highscore_text = new Text("HighScore: " + high_score);
	}
	
	public void setScore(int score) {
		current_score = score;
		score_text.setText("Score: " + current_score);
		if (score > high_score) {
			high_score = score;
			highscore_text.setText("HighScore: " + high_score);
		}
	}
	
	public int getScore() {
		return current_score;
	}
	
	public void applyHit(int row, int column) {
		Balloon[][] box = LevelCreator.boxes;
		
		ArrayList<Integer[]> list = find5(row, column);
		for (Integer[] i : list) {
			box[ i[0] ][ i[1] ].isClicked();
		}
		
		current_score += hitToScore(list.size());
		score_text.setText("Score: " + current_score);
	}
	
	public void applyHover(int row, int column) {
		Balloon[][] box = LevelCreator.boxes;
		
		ArrayList<Integer[]> list = find5(row, column);
		for (Integer[] i : list) {
			box[ i[0] ][ i[1] ].hover();
		}
	}
	
	public void applyLeave(int row, int column) {
		Balloon[][] box = LevelCreator.boxes;
		
		ArrayList<Integer[]> list = find5(row, column);
		for (Integer[] i : list) {
			box[ i[0] ][ i[1] ].leave();
		}
	}
	
	public void applyClick(int row, int column) {
		Balloon[][] box = LevelCreator.boxes;
		
		ArrayList<Integer[]> list = find5(row, column);
		for (Integer[] i : list) {
			box[ i[0] ][ i[1] ].click();
		}
	}
	
	private int hitToScore(int hit) {
		if (hit <= 3) {
			return 2 * hit - 5;
		} else if (hit >= 4) {
			return 2 * hit - 6;
		}
		return -5;
	}
	
	public Text getLevelText() {
		return level_text;
	}
	public Text getScoreText() {
		return score_text;
	}
	public Text getHighscoreText() {
		return highscore_text;
	}
	
	//find that 5 balloons 
	private ArrayList<Integer[]> find5(int row, int column) {
		ArrayList<Integer[]> balloon5temp = new ArrayList<>();
		Balloon[][] box = LevelCreator.boxes;
		
		if ((row - 1) >= 0 && box[row - 1][column].getLife() > 0) {
			Integer[] pos = new Integer[2];
			pos[0] = row - 1;
			pos[1] = column;
			balloon5temp.add(pos);
		}
		
		if ((row + 1) < 10 && box[row + 1][column].getLife() > 0) {
			Integer[] pos = new Integer[2];
			pos[0] = row + 1;
			pos[1] = column;
			balloon5temp.add(pos);
		}
		
		if ((column - 1) >= 0 && box[row][column - 1].getLife() > 0) {
			Integer[] pos = new Integer[2];
			pos[0] = row;
			pos[1] = column - 1;
			balloon5temp.add(pos);
		}
		
		if ((column + 1) < 10 && box[row][column + 1].getLife() > 0) {
			Integer[] pos = new Integer[2];
			pos[0] = row;
			pos[1] = column + 1;
			balloon5temp.add(pos);
		}
		Integer[] pos = new Integer[2];
		pos[0] = row;
		pos[1] = column;
		balloon5temp.add(pos);
		return balloon5temp;
	}
}
