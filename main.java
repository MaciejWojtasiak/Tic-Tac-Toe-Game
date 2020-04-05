package tictactoe;
import java.util.Scanner;
public class Main {
    
    public static void fillingArr(char[][] array) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				array[i][j] = ' ';
			}
		}
	}

	public static void printArray(char[][] array) {
		System.out.println("---------");
		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.println("---------");
	}

	public static int countingChars(char inpChar, char array[][]) {
		int counter = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (array[i][j] == inpChar) {
					counter++;
				}
			}
		}
		return counter;
	}

	public static boolean checkImpossible(char array[][]) {
		boolean isImpossible = false;
		if (win('X', array) && win('O', array) || Math.abs(countingChars('X', array) - countingChars('O', array)) > 1) {
			isImpossible = true;
		}
		return isImpossible;
	}

	public static boolean win(char inp, char array[][]) {
		boolean isWin = false;
		int j = 0;
		for (int i = 0; i < 3; i++) {
			if (array[i][j] == inp && array[i][j] == array[i][j + 1] && array[i][j] == array[i][j + 2]) {
				isWin = true;
			}
			if (array[j][i] == inp && array[j][i] == array[j + 1][i] && array[j][i] == array[j + 2][i]) {
				isWin = true;
			}
		}
		if (array[0][0] == inp && array[0][0] == array[1][1] && array[0][0] == array[2][2]
				|| array[0][2] == inp && array[0][2] == array[1][1] && array[0][2] == array[2][0]) {
			isWin = true;
		}

		return isWin;
	}

	
	public static void userMove(char player, char array[][]) {
		Scanner sc = new Scanner(System.in);
		int first, second;
		do {
			System.out.print("Enter the coordinates: ");
			if (sc.hasNextInt()) {
				first = sc.nextInt();
				second = sc.nextInt();

				if (first > 3 || second > 3) {
					System.out.println("Coordinates should be from 1 to 3!");
					sc.nextLine();
					continue;
				}

				if (array[3 - second][first - 1] != ' ') {
					System.out.println("This cell is occupied! Choose another one!");
					continue;
				}
				break;
			} else {
				System.out.println("You should enter numbers!");
				if (!sc.hasNextInt()) {
					sc.nextLine();
					continue;
				}
				sc.nextLine();
			}
		} while (true);

		array[3 - second][first - 1] = player;
	}
    
    
    public static void main(String[] args) {
      int counter = 1;
		char player;
		char[][] arr = new char[3][3];
		fillingArr(arr);
		printArray(arr);

		while (true) {
			if (counter > 2) {
				counter = 1;
			}
			if (counter == 1) {
				player = 'X';
			} else {
				player = 'O';
			}
			userMove(player, arr);

			if (win('X', arr) && !checkImpossible(arr)) {
				printArray(arr);
				System.out.println("X wins");
				break;
			}
			if (win('O', arr) && !checkImpossible(arr)) {
				printArray(arr);
				System.out.println("O wins");
				break;
			}
            if(!win('X', arr) && !win('O', arr) && !checkImpossible(arr) && countingChars(' ', arr) == 0) {
                printArray(arr);
                System.out.println("Draw");
                break;
         }
			printArray(arr);
			counter++;
    }
}
}
