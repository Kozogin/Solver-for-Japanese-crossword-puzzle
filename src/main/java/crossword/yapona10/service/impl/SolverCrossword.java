package crossword.yapona10.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolverCrossword {
	
	private String [] vertical;
	private String [] horizontal;
	
	public SolverCrossword(String[] vertical, String[] horizontal) {
		this.vertical = vertical;
		this.horizontal = horizontal;
	}	
	
	public  List<List<Integer>> solver() {
		
		List<List<Integer>> vert = vertHorizPullNumber(vertical);
			System.out.println(vert);
		List<List<Integer>> horz = vertHorizPullNumber(horizontal);	
			System.out.println(horz);
		
		return null;		
	}	
		
	private List<List<Integer>> vertHorizPullNumber(String [] array){
		
		List<List<Integer>> verticalAndHorozontal = new ArrayList<>();
		
		for (int i = 0; i < array.length; i++) {			
			String[] split = array[i].split(",");			
			
			List<Integer> rowColumn = new ArrayList<>();
			for (int j = 0; j < split.length; j++) {
				rowColumn.add(Integer.parseInt(split[j].trim()));				
			}
			verticalAndHorozontal.add(rowColumn);
		}	
		return verticalAndHorozontal;		
	}
	

	public String[] getVertical() {
		return vertical;
	}

	public void setVertical(String[] vertical) {
		this.vertical = vertical;
	}

	public String[] getHorizontal() {
		return horizontal;
	}

	public void setHorizontal(String[] horizontal) {
		this.horizontal = horizontal;
	}

	@Override
	public String toString() {
		return "SolverCrossword [vertical=" + Arrays.toString(vertical) + ", horizontal=" + Arrays.toString(horizontal)
				+ "]";
	}
	
	
	
	

}
