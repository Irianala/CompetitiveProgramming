package irianala.codesignal;

import java.util.*;

public class InterviewPractice {
	public static void main(String[] args) {

	}
	
	
	
	public static char firstNotRepeatingCharacter(String s) {
		List<Character> noRepeating = new ArrayList<>();
		Set<Character> charList = new LinkedHashSet<>();
		
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
			if (counter == 1) noRepeating.add(element);
		}
		return noRepeating.size() > 0 ? noRepeating.get(0) : '_';
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
			Set<Character> horizontalChars = new HashSet<>();
			for(int j = 0; j < matrixSize; j++) {
				if (grid[i][j] != '.') {
					int size = horizontalChars.size();
					horizontalChars.add(grid[i][j]);
					hasHorizontalRepeatingValue = hasHorizontalRepeatingValue || (horizontalChars.size() == size);
				}
			}
		}
		
		for(int i = 0; i < matrixSize; i++) {
			Set<Character> verticalChars = new HashSet<>();
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
				
				Set<Character> firstInnerChars = new HashSet<>();
				Set<Character> secondInnerChars = new HashSet<>();
				Set<Character> thirdInnerChars = new HashSet<>();
				
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
		Map<Character, Character> solutions = new HashMap<>();
		for(char[] c:solution) {
			solutions.put(c[0], c[1]);
		}
		
		List<String> decryptWord = new ArrayList<>();
		
		for(String s:crypt) {
			String temp = "";
			for(int i = 0; i < s.length(); i++) {
				temp = temp + solutions.get(s.charAt(i));
			}
			decryptWord.add(temp);
		}
		
		for(String s:decryptWord) {
			if (s.length() >= 2 && s.charAt(0) == '0') {
				return false;
				}
		}

		return Long.parseLong(decryptWord.get(0)) + Long.parseLong(decryptWord.get(1)) == Long.parseLong(decryptWord.get(2));
	}
}
