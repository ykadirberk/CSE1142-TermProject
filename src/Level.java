//Yasin Enes SISIK, 150119807 - Kadir Berk YAGAR, 150120016
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
// Definition of the class level()
// This class applies effects, holds scores and rewrites them
public class Level {
	private int current_score = 0;
	private int level_number;
	private int high_score;
	
	private Text level_text;
	private Text highscore_text;
	private Text score_text;

	private Text feedback_text;
	
	private Button nextBtn;
	
	public Level() {

		level_text = new Text("Level #" + level_number);
		score_text = new Text("Score: " + current_score);
		highscore_text = new Text("HighScore: " + high_score);
		feedback_text = new Text("");
		nextBtn = new Button("NEXT LEVEL >>");
		nextBtn.visibleProperty().setValue(Boolean.FALSE);
		

	}
	
	public Level(int current_score, int level_number, int high_score) {
		this.current_score = current_score;
		this.level_number = level_number;
		this.high_score = high_score;
		
		level_text = new Text("Level #" + level_number);
		score_text = new Text("Score: " + current_score);
		highscore_text = new Text("HighScore: " + high_score);
		feedback_text = new Text("");
		nextBtn = new Button("NEXT LEVEL >>");
		nextBtn.visibleProperty().setValue(Boolean.FALSE);
		
	}
	
	// Definition of the function setScore()
	// This function sets current score and compares it with high score
	public void setScore(int score) {
		current_score = score;
		score_text.setText("Score: " + current_score);
		if(LevelCreator.levelLife<=1 && (level_number<5)) {
			if(!allDead())
				nextBtn.visibleProperty().setValue(Boolean.TRUE);
		}
		if (score > high_score) {
			setEmbededHS(score);
		}
	}
	
	public int getScore() {
		return current_score;
	}
	
	// Definition of the function applyHit()
	// This function applies  hit on the balloons, and print feedback statement
	public void applyHit(int row, int column) {
		Balloon[][] box = LevelCreator.boxes;
		
		String feedbackMess = "    Box: " + column + "-" + row;
		
		ArrayList<Integer[]> list = find5(row, column);
		for (Integer[] i : list) {
			box[ i[0] ][ i[1] ].isClicked();
			if (row != i[0] || column != i[1]) {
				feedbackMess += " - Hit: " + i[1] + "," + i[0]; 
			}
		}
		
		feedbackMess += " (" + hitToScore(list.size()) + " points)";
		feedback_text.setText(feedbackMess);
		System.out.println(feedbackMess);
		current_score += hitToScore(list.size());
		setScore(current_score);
	}
	
	// Definition of the function applyHover()
	// This function applies  hover effect on the balloons..
	public void applyHover(int row, int column) {
		Balloon[][] box = LevelCreator.boxes;
		
		ArrayList<Integer[]> list = find5(row, column);
		for (Integer[] i : list) {
			box[ i[0] ][ i[1] ].hover();
		}
	}
	
	// Definition of the function applyHover()
	// This function applies  leave effect on the balloons.
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

	//Definition of the function find5()
	//This function finds that 5 balloons 
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
	
	//Definition of the function setEmbededHS()
	//This function rewrite the high score to highScore.txt
	public void setEmbededHS(int highScoreX) {
		
		if(allDead())
			return;
		
		String highScoreP = "../CSE1142-TermProject\\src\\highScore.txt";
		File highScorePath = new File(highScoreP);
		int t = 0;
		String s ="";
		
		try(Scanner input = new Scanner(highScorePath)){
			while(input.hasNext()) {
				String x = input.next();
				t+=1;
				if(t==level_number){
					s+= highScoreX+" ";
					continue;
				}
				s += x+" ";
			}
		}catch(Exception ex) {
		}
		
		try(PrintWriter input = new PrintWriter(highScorePath)){
			input.print(s);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		
		setHigh_score(highScoreX);
		
	}
	
	//Definition of the function getEmbededHS()
	//This function digs high scores from highScore.txt
	public void getEmbededHS() {
		
		String highScoreFile = "../CSE1142-TermProject\\src\\highScore.txt";
		File highScorePath = new File(highScoreFile);
		ArrayList<Integer> highScoreHolder = new ArrayList<Integer>();
		
		try(Scanner input = new Scanner(highScorePath)){
			while(input.hasNext()) {
				int x = input.nextInt();
				highScoreHolder.add(x);
			}
			setHigh_score(highScoreHolder.get(level_number-1));
		}catch(Exception ex) {
		}
	}
	
	//Definition of the function allDead()
	//This function checks all balloons lives
	public Boolean allDead() {
		
		Balloon[][] box = LevelCreator.boxes;
		
		for(int i = 0; i< 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(box[i][j].isClickable())
					return true;
			}
		}
		return false;
		
	}
	
	public void setHigh_score(int high_score) {
		highscore_text.setText("HighScore: " + high_score);
		this.high_score = high_score;
	}
	
	public void setLevel_number(int level_number) {
		level_text.setText("Level #" + level_number);
		this.level_number = level_number;
	}
	
	public int getCurrent_score() {
		return current_score;
	}

	public void setCurrent_score(int current_score) {
		score_text.setText("Score: " + current_score);
		this.current_score = current_score;
	}

	public int getLevel_number() {
		return level_number;
	}
	
	public int getHigh_score() {
		return high_score;
	}
	
	public Text getLevel_text() {
		return level_text;
	}

	public void setLevel_text(Text level_text) {
		this.level_text = level_text;
	}

	public Text getHighscore_text() {
		getEmbededHS();
		return highscore_text;
	}

	public void setHighscore_text(Text highscore_text) {
		this.highscore_text = highscore_text;
	}

	public Text getScore_text() {
		return score_text;
	}

	public void setScore_text(Text score_text) {
		this.score_text = score_text;
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

	public Text getFeedbackText() {
		return feedback_text;
	}
	public void setFeedbackText(Text feedback_text) {
		this.feedback_text = feedback_text;
	}
	public Button getNextBtn() {
		return nextBtn;
	}

	public void setNextBtn(Button nextBtn) {
		this.nextBtn = nextBtn;
	}

}
