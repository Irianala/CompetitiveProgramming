package irianala.codesignal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InterviewPractice {
	public static void main(String[] args) {
		String[] crypt = new String[] {
				"TEN",
				"TWO",
				"ONE"
		};
		char[][] solution = new char[][] {
			{'O', '1'},
			{'T', '0'},
			{'W', '9'},
			{'E', '5'},
			{'N', '4'}
		};
		
		char[][] solun = new char[][] {
			{'A', '1'},
			{'B', '3'},
			{'C', '4'}
		};
		String[] cry = new String[] {
			"AAAAAAAAAAAAAA", 
			 "BBBBBBBBBBBBBB", 
			 "CCCCCCCCCCCCCC"
		};
		
		
		isCryptSolution(cry, solun);
	}
	
	
	
	public static char firstNotRepeatingCharacter(String s) {
		List<Character> noReapiting = new ArrayList<Character>();
		Set<Character> charList = new LinkedHashSet<Character>();
		
		for(int i = 0; i < s.length(); i++) {
			charList.add(s.charAt(i));
		}
		
		for(char element : charList) {
			int counter = 0;
			for(int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == element) {
					counter++;
				}
			}
			if (counter == 1) noReapiting.add(element);
		}
		return noReapiting.size() > 0 ? noReapiting.get(0) : '_';
	}
	
	public static int[][] rotate(int[][] a) {
		int matrixSize = a.length;
		int[][] rotatedMatrix= new int[matrixSize][matrixSize];
		for(int j = 0; j < matrixSize; j++ ) {
			for(int i = 0; i < matrixSize; i++) {
				rotatedMatrix[j][matrixSize - 1 - i] = a[i][j];
			}
		}
		
		return rotatedMatrix;
	}
	
	public static boolean sudoku2(char[][] grid) {
		boolean hasHorizontalRepeatingValue = false;
		boolean hasVerticalRepeatingValue = false;
		boolean hasInnerRepeatingValue = false;
		int matrixSize = grid.length;
		
		for(int i = 0; i < matrixSize; i++) {
			Set<Character> horizontalChars = new HashSet<Character>();
			for(int j = 0; j < matrixSize; j++) {
				if (grid[i][j] != '.') {
					int size = horizontalChars.size();
					horizontalChars.add(grid[i][j]);
					hasHorizontalRepeatingValue = hasHorizontalRepeatingValue || (horizontalChars.size() == size);
				}
			}
		}
		
		for(int i = 0; i < matrixSize; i++) {
			Set<Character> verticalChars = new HashSet<Character>();
			for(int j = 0; j < matrixSize; j++) {
				if (grid[j][i] != '.') {
					int size = verticalChars.size();
					verticalChars.add(grid[j][i]);
					hasVerticalRepeatingValue = hasVerticalRepeatingValue || verticalChars.size() == size;
				}
			}
		}
		
		int index = 0;
		int delimiter = matrixSize / 3;
		
		for(int i = 0; i < matrixSize / 3; i++) {
			for(int j = 0; j < matrixSize / 3; j++) {
				
				Set<Character> firstInnerChars = new HashSet<Character>();
				Set<Character> secondInnerChars = new HashSet<Character>();
				Set<Character> thirdInnerChars = new HashSet<Character>();
				
				for(int k = index; k < delimiter; k++) {
					if (grid[j][k] != '.') {
						int firstSize = firstInnerChars.size();
						firstInnerChars.add(grid[j][k]);
						hasInnerRepeatingValue = hasInnerRepeatingValue || firstInnerChars.size() == firstSize;
					}
					
					if (grid[j + 3][k] != '.') {
						int secondSize = secondInnerChars.size();
						secondInnerChars.add(grid[j + 3][k]);
						hasInnerRepeatingValue = hasInnerRepeatingValue || secondInnerChars.size() == secondSize;
					}
					
					if (grid[j + 6][k] != '.' ) {
						int thirdSize = thirdInnerChars.size();
						thirdInnerChars.add(grid[j + 6][k]);
						hasInnerRepeatingValue = hasInnerRepeatingValue || thirdInnerChars.size() == thirdSize;
					}
				}
			}
			index = delimiter;
			delimiter = delimiter + 3;
		}
		
		return !(hasHorizontalRepeatingValue || hasVerticalRepeatingValue || hasInnerRepeatingValue);
	}

	public static boolean isCryptSolution(String[] crypt, char[][] solution) {
		Map<Character, Character> solutions = new HashMap<Character, Character>();
		Map<Character, Character> reverseSolutions = new HashMap<Character, Character>();
		for(char[] c:solution) {
			solutions.put(c[0], c[1]);
			reverseSolutions.put(c[1], c[0]);
		}
		
		List<String> decryptWord = new ArrayList<String>();
		
		for(String s:crypt) {
			String temp = "";
			for(int i = 0; i < s.length(); i++) {
				temp = temp + String.valueOf(solutions.get(s.charAt(i)));
			}
			decryptWord.add(temp);
		}
		
		for(String s:decryptWord) {
			if (s.length() >= 2 && s.charAt(0) == '0') {
				return false;
				}
		}
		
		if ((Long.parseLong(decryptWord.get(0)) + Long.parseLong(decryptWord.get(1)) == Long.parseLong(decryptWord.get(2)))) {
			return true;
		} else {
			return false;
		}
	}
}
