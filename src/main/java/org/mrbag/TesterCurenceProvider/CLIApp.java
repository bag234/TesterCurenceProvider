package org.mrbag.TesterCurenceProvider;

import java.io.PrintStream;
import java.util.Scanner;

import org.mrbag.TesterCurenceProvider.Utils.LoadCurenncesProvider;

public class CLIApp {

	static void sendHelloMessage(PrintStream out) {
		out.println("Hello in simple app provider test");
	}
	
	static void drawMenu(PrintStream out, Scanner in, LoadCurenncesProvider utils ) {
		try {
			do {
				utils.printListAvvableProvider(out);
			} while (utils.callProviderByIdAvable(in.nextInt(), out));
		}
		finally {
			in.close();
		}
	}
	
	public static void main(String[] args) {
		sendHelloMessage(System.out);
		drawMenu(System.out, new Scanner(System.in), LoadCurenncesProvider.load());
	}

}
