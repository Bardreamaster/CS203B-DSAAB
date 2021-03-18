
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class ShortestPathTest {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);

		String fileName = "src\\graph1.txt";
		BufferedReader bf = new BufferedReader(new FileReader(fileName));
		String textLine;
		String str = "";
		while ((textLine = bf.readLine()) != null) {
			str += textLine + " ";
		}
		String[] numbs = str.split(" ");
		int[] number = new int[numbs.length];
		System.out.print("The numbers array is:");
		for (int i = 0; i < numbs.length; i++) {
			number[i] = Integer.parseInt(numbs[i]);
			// System.out.print(number[i]+", ");
		}
		// System.out.println("");
		bf.close();
		System.out.println("Original:"+Arrays.toString(number));
	}
}
