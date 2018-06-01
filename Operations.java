import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Operations {

	private static int max = 100;
	
	public boolean checkSquare(JTextArea matrixArea) throws Exception {
		int rows = 0;
		int cols = 0;
		int[] rsize = new int[max];
		
		String rawText = matrixArea.getText();
		StringTokenizer numberTokens = new StringTokenizer(rawText, "\n");
		while(numberTokens.hasMoreTokens()) {
			StringTokenizer numToken2 = new StringTokenizer(numberTokens.nextToken());
			while(numToken2.hasMoreTokens()) {
				numToken2.nextToken();
				cols++;
			}
			rsize[rows] = cols;
			rows++;
			cols = 0;			
		}
		
		for (int c = 0; c < rows; c++) {
			if (rsize[c] != rows) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkScalar(JTextArea matrixArea) throws Exception {
		String rawText = matrixArea.getText();
		StringTokenizer numberTokens = new StringTokenizer(rawText, " \n");		//checks first for spaces then newlines
		int[] rsize = new int[numberTokens.countTokens()];						//initializes array to size of numberTokens
		System.out.println(rsize.length);										//Console print for debugging
		if(rsize.length == 1) {													//If there is only one token, then there is a scalar
			return true;
		}
		return false;
	}

	public int[][] getMatrix(JTextArea matrixArea) throws Exception{
		int rows = 0;
		int cols = 0;
		int[] rsize = new int[max];
		
		String rawText = matrixArea.getText();
		StringTokenizer numberTokens = new StringTokenizer(rawText, "\n");		//separates tokens by newline (by row)
		while(numberTokens.hasMoreTokens()) {
			StringTokenizer numToken2 = new StringTokenizer(numberTokens.nextToken());
			while(numToken2.hasMoreTokens()) {
				numToken2.nextToken();
				cols++;
			}
			rsize[rows] = cols;
			rows++;
			cols = 0;			
		}
		
		if(checkSquare(matrixArea)) {		
			int n = rows;
			int[][] matrix = new int[n][n];
			String val = "";
			rows = cols = 0;
			
			StringTokenizer st = new StringTokenizer(rawText, "\n");
			while(st.hasMoreTokens()) {
				StringTokenizer st2 = new StringTokenizer(st.nextToken());
				while(st2.hasMoreTokens()) {
					val = st2.nextToken();
					matrix[rows][cols] = Integer.valueOf(val).intValue();
					cols++;
				}
				rows++;
				cols = 0;
			}
			System.out.println("Square matrix: " + Arrays.deepToString(matrix));			//Console print for debugging
			return matrix;
		}
		else {
			return notSquareMatrix(matrixArea);
		}
		
	}
	
	public int[][] notSquareMatrix(JTextArea matrixArea) throws Exception{
		String rawText = matrixArea.getText();		
		StringTokenizer ts = new StringTokenizer(rawText, "\n");
		
		int[][] matrix = new int[ts.countTokens()][];

		int row = 0;
		int col = 0;
		int last = -5;	//these values will work to make sure rows are of equal length
		int curr = -5;
		while (ts.hasMoreTokens()) {
			StringTokenizer st2 = new StringTokenizer(ts.nextToken(), " ");
			last = curr;
			curr = st2.countTokens();
			if(last != -5 && curr!= last)
				throw new Exception("Rows not of equal length");
			matrix[row] = new int[st2.countTokens()];
			while (st2.hasMoreElements()) {
				matrix[row][col++] = Integer.parseInt(st2.nextToken());
			}
			row++;
			col = 0;
		}
		System.out.println("NonSquare: " + Arrays.deepToString(matrix));	//Console print for debugging
		return matrix;
	}
	
	public void displayMatrix(int[][] a, JTextArea matrixText) {
		matrixText.setText("");
	    for(int i = 0; i < a.length; i++){
	        for(int j = 0; j < a[0].length; j++){
	            matrixText.append(String.format("%-5d", a[i][j])); 
	        }
	        matrixText.append("\n");
	    }
	}
	
	public int[][] addMatrix(int[][] a, int[][] b) throws Exception {
		int aMatrixR = a.length;
		int aMatrixC = a[0].length;
		int bMatrixR = b.length;
		int bMatrixC = b[0].length;
		
		System.out.println(aMatrixC);		//Console print for debugging
		System.out.println(aMatrixR);		//Console print for debugging
		System.out.println(bMatrixC);		//Console print for debugging
		System.out.println(bMatrixR);		//Console print for debugging
		
		if(aMatrixR != bMatrixR && aMatrixC != bMatrixC) {
			JOptionPane.showMessageDialog(null, "Matrix A and Matrix B are not same dimension", "Matrix Incompatibility Error", JOptionPane.ERROR_MESSAGE);
			throw new Exception("Matrices incompatible for addition");
		}
		
		int matrix[][] = new int[aMatrixR][aMatrixC];

		for (int i = 0; i < aMatrixR; i++) {
			for (int j = 0; j < aMatrixC; j++) {
				matrix[i][j] = a[i][j] + b[i][j];
			}
		}
		return matrix;
	}
	
	public int[][] subtractMatrix(int[][] a, int[][] b) throws Exception {
		int aMatrixR = a.length;
		int aMatrixC = a[0].length;
		int bMatrixR = b.length;
		int bMatrixC = b[0].length;
		int sumA = aMatrixC + aMatrixR;
		int sumB = bMatrixC + bMatrixR;
		
		System.out.println(aMatrixC);		//Console print for debugging
		System.out.println(aMatrixR);		//Console print for debugging
		System.out.println(bMatrixC);		//Console print for debugging
		System.out.println(bMatrixR);		//Console print for debugging
		
		if(aMatrixR != bMatrixR && aMatrixC != bMatrixC) {
			JOptionPane.showMessageDialog(null, "Matrix A and Matrix B are not same dimension", "Matrix Incompatibility Error", JOptionPane.ERROR_MESSAGE);
			throw new Exception("Matrices incompatible for subtraction");
		}
		
		
		int matrix[][] = new int[aMatrixR][aMatrixC];

		for (int i = 0; i < aMatrixR; i++)
			for (int j = 0; j < aMatrixC; j++) {
				matrix[i][j] = a[i][j] - b[i][j];
			}

		return matrix;
	}
	
	public int[][] multiplyMatrix(int[][] a, int[][] b) throws Exception {
		int aMatrixR = a.length;
		int aMatrixC = a[0].length;
		int bMatrixR = b.length;
		int bMatrixC = b[0].length;
		int sum = 0;
		System.out.println(Arrays.deepToString(a));				//Console print for debugging
		System.out.println(Arrays.deepToString(b));				//Console print for debugging
		System.out.println("" + aMatrixR + " " + aMatrixC);		//Console print for debugging
		System.out.println("" + bMatrixR + " " + bMatrixC);		//Console print for debugging
		
		if(aMatrixC != bMatrixR) {
			JOptionPane.showMessageDialog(null, "Columns in Matrix A are not equal to Rows in Matrix B", "Matrix Incompatibility Error", JOptionPane.ERROR_MESSAGE);
			throw new Exception("Matrices incompatible for multiplication");
			
		}
				
		int[][] matrix = new int[aMatrixR][bMatrixC];
		for(int i = 0; i < aMatrixR; i++) {
			for(int j = 0; j < bMatrixC; j++) {
				sum = 0;
				for(int k = 0; k < aMatrixC; k++) {
					sum = sum + (a[i][k] * b[k][j]);
				}
				matrix[i][j] = sum;
			}
		}
		return matrix;
	}
	
	public int[][] scalarMultiply(JTextArea matrixArea, int[][] b) throws Exception{
		int scalar = Integer.parseInt(matrixArea.getText());
		int matrixTwoR = b.length;
		int matrixTwoC = b[0].length;
		int[][] newMatrix = new int[matrixTwoR][matrixTwoC];
		
		for(int i = 0; i < matrixTwoR; i++) {
			for(int j = 0; j < matrixTwoC; j++) {
				newMatrix[i][j] = scalar*b[i][j];
			}
		}
		return newMatrix;
	}
}
