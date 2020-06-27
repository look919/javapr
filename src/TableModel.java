

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Program <code>MyApplication</code>
 * Klasa <code>TableModel</code> stanowiąca model tabeli
 * @author Tomasz Wirkus
 */
public class TableModel {
	
	/**
	 * Pola modelu tabeli
	 */
	private Integer data[][];
	private int rows;
	private int columns;
	private String filePath = "data\\array.txt";
	
	
	/**
	 * Konstruktor parametryzowany klasy <code>TableModel</code>
	 * @param rows liczba wierzy tabeli
	 * @param columns liczba kolumn tabeli
	 */
	public TableModel(int rows, int columns) {
		this.data = new Integer[rows][columns];
		this.rows = rows;
		this.columns = columns;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				this.data[i][j] = 0;
			}
		}
	}
	

	public int getRowCount() {
		return rows;
	}
	public int getColumnCount() {
		return columns;
	}
	public int getCount() {
		return rows * columns;
	}
	public void fillWithValue(int value) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				data[i][j] = value;
			}
		}
	}

	public void updateValueAt(int row, int column, Integer value) {
		this.data[row][column] = value;
	}
	public int getValue(int row, int column) {
		return data[row][column];
	}
	

	public void clear() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				data[i][j] = 0;
			}
		}
	}
	
	/**
	 * Metoda obliczająca oraz zwracająca sumę elementów tabeli
	 * @return zwraca sum� element�w tabeli
	 */
	public int getSum() {
		int sum = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				sum += data[i][j];
			}
		}
		return sum;
	}
	
	/**
	 * Metoda obliczająca oraz zwracająca średnią arytmetyczną elementów tabeli
	 * @return zwraca średnią arytmetyczną elementów tabeli
	 */
	public float getAverage() {
		return ((float) getSum()) / getCount();
	}
	
	/**
	 * Metoda poszukująca oraz zwracająca najmniejszą wartość znajudjąca się w tabeli
	 * @return zwraca minimalną wartość tabeli
	 */
	public int getMinimum() {
		int minimum = data[0][0];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (data[i][j] < minimum)
					minimum = data[i][j];
			}
		}
		return minimum;
	}
	
	/**
	 * Metoda poszukująca oraz zwracająca największą wartość znajudjąca się w tabeli
	 * @return zwraca maksymalną wartość tabeli
	 */
	public int getMaximum() {
		int maximum = data[0][0];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (data[i][j] > maximum)
					maximum = data[i][j];
				}
		}
		return maximum;
	}
	
	/**
	 * Metoda zapisujaca do pliku obecny stan tabeli
	 */
	public void SaveToFile() {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(filePath));
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					writer.write(Integer.toString(data[i][j]));
					writer.write(" ");
					if (!(j < columns - 1))
						writer.newLine();						
					}
			}
			writer.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metoda wczytujaca z pliku stan tabeli
	 */
	public void ReadFromFile() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			String[] rowsAsString = new String[rows];
			for (int i = 0; i < rows; i++) {
				rowsAsString[i] = reader.readLine();
			}
			reader.close();
			for (int i = 0; i < rows; i++) {
				int k = 0;
				StringBuilder valueAsString = new StringBuilder();
				for (int j = 0; j < rowsAsString[i].length(); j++) {
					if (rowsAsString[i].charAt(j) != ' ') {
						valueAsString.append(rowsAsString[i].charAt(j));
					}
					else {
						data[i][k] = Integer.parseInt(valueAsString.toString());
						valueAsString.setLength(0);
						k++;	
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metoda sprawdzajaca czy tabela jest wypelniona wylacznie wartosciami 0
	 */
	public boolean IsTableFilledOnlyWithZeros() {
		int count = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (data[i][j] != 0)
					count++;
			}
		}
		return count == 0 ? true : false;
	}
}
