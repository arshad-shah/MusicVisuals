package C19485866.com.C19485866;

public class Main {

	public void startUI() {
		String[] a = { "MAIN" };
		processing.core.PApplet.runSketch(a, new MyVisual());

	}

	public static void main(String[] args) {
		Main main = new Main();
		main.startUI();
	}
}