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
		int counter = 0;
		Balloon[][] box = LevelCreator.boxes;
		if ((row - 1) >= 0 && box[row - 1][column].getLife() > 0) {
			box[row - 1][column].isClicked();
			counter++;
		}
		
		if ((row + 1) < 10 && box[row + 1][column].getLife() > 0) {
			box[row + 1][column].isClicked();
			counter++;
		}
		
		if ((column - 1) >= 0 && box[row][column - 1].getLife() > 0) {
			box[row][column - 1].isClicked();
			counter++;
		}
		
		if ((column + 1) < 10 && box[row][column + 1].getLife() > 0) {
			box[row][column + 1].isClicked();
			counter++;
		}
		
		box[row][column].isClicked();
		counter++;
		
		current_score += hitToScore(counter);
		score_text.setText("Score: " + current_score);
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
}
