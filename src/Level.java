
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.text.Text;

public class Level {
	private int current_score = 0;
	private int level_number;
	private int high_score;
	
	private Text level_text;
	private Text highscore_text;
	private Text score_text;
	private Text feedback_text;
	
	
	public Level() {
		level_text = new Text("Level #" + level_number);
		score_text = new Text("Score: " + current_score);
		highscore_text = new Text("HighScore: " + high_score);
		feedback_text = new Text("");
	}
	
	public Level(int current_score, int level_number, int high_score) {
		this.current_score = current_score;
		this.level_number = level_number;
		this.high_score = high_score;
		
		level_text = new Text("Level #" + level_number);
		score_text = new Text("Score: " + current_score);
		highscore_text = new Text("HighScore: " + high_score);
		feedback_text = new Text("");
	}
	
	public void setScore(int score) {
		current_score = score;
		score_text.setText("Score: " + current_score);
		if (score > high_score) {
			changeHighScore(score);
			
		}
	}
	
	public int getScore() {
		return current_score;
	}
	
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
	
	public void changeHighScore(int highScoreX) {
		
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

		
		highscore_text.setText("HighScore: " + highScoreX+"");
		
	}
	
	public void getSunkedHS() {
		
		String highScoreFile = "../CSE1142-TermProject\\src\\highScore.txt";
		File highScorePath = new File(highScoreFile);
		ArrayList<Integer> highScoreHolder = new ArrayList<Integer>();
		
		try(Scanner input = new Scanner(highScorePath)){
			while(input.hasNext()) {
				int x = input.nextInt();
				highScoreHolder.add(x);
			}
			highscore_text.setText("HighScore: " + highScoreHolder.get(getLevel_number()-1)+"");
			this.high_score = highScoreHolder.get(getLevel_number()-1);
		}catch(Exception ex) {
		}
	}
	
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
		setHighscore_text(new Text("HighScore: " + high_score));
		this.high_score = high_score;
	}
	
	public void setLevel_number(int level_number) {
		setLevel_text(new Text("Level #" + level_number));
		this.level_number = level_number;
	}
	
	public int getCurrent_score() {
		return current_score;
	}

	public void setCurrent_score(int current_score) {
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
		getSunkedHS();
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
}
