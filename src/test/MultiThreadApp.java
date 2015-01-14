package test;

public class MultiThreadApp {
	BackgroundThread bt = new BackgroundThread();

	public MultiThreadApp() {
		// TODO Auto-generated constructor stub
		bt.start();
	}

	public static void main(String[] args) {
		new MultiThreadApp();
	}

	private class BackgroundThread extends Thread {
		@Override
		public void run() {
			while (true) {
				System.out.println("BackgroundThreadRunning");

			}
		}

	}
}
