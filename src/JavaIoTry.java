import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;




public class JavaIoTry {

	public static void main(String[] args) {
		File levelDir = new File(getCurrentPath());
		File level1 = new File(getLevelPath(1));
		System.out.println(levelDir.getAbsolutePath());
		System.out.println(level1.getAbsolutePath());
	}
	
	private static void getContent(File file) {
		try (Scanner input = new Scanner(file)) {

	        while (input.hasNext()) {

	            String s = input.nextLine();
	            System.out.println(s);

	        }
	    } catch (FileNotFoundException ex) {
	        ex.printStackTrace();
	    }
	}
	public static String getCurrentPath() {
		return System.getProperty("user.dir")+"\\levels";
	}
	public static String getLevelPath(int i) {
		return System.getProperty("user.dir")+"\\levels\\level"+i+".txt";
	}

}
