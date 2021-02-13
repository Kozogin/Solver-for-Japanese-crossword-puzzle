package crossword.yapona10.service.impl;

import java.util.ArrayList;
import java.util.List;

public class ResultMatrix {
	
	private byte [][] result;

	public ResultMatrix(byte[][] result) {		
		this.result = result;
	}
	
	public List<List<Byte>> returnTheList(){
		
		List<List<Byte>> resultShow = new ArrayList<>();		
    	
    	for (int i = 0; i < result.length; i++) {
    		List<Byte> row = new ArrayList<>();
			for (int j = 0; j < result[i].length; j++) {
				row.add(result[i][j]);				
			}			
			resultShow.add(row);			
		}    	
		return resultShow;		
	}
	
	public byte [][] imposition(List<List<Integer>> rowOrColumn, int sizeColumnOrRow) {		
		
		byte [] handler = new byte[sizeColumnOrRow]; 
	
		//byte [] handler = {1, 2, 2, 1, 0, 0};
		for (int row = 0; row < rowOrColumn.size(); row++) {
			
		
		
				//прохід по рядку вертикаль запихаємо в матрицю з початку з одинарним зазором
				int lengthElement = 0;
				int indMatrix = 0;
				int indexExit = 0;
				for (int indInnerList = 0; indInnerList < rowOrColumn.get(row).size(); indInnerList++) {
					
					lengthElement = rowOrColumn.get(row).get(indInnerList);//2
					
					indexExit = indMatrix + lengthElement;
					
					while(indMatrix < indexExit) {
						handler[indMatrix++] = 1;
					}
					
					if(indMatrix < sizeColumnOrRow) {
						handler[indMatrix++] = 2;
					}
					
				}		
				
				
				for (int i = 0; i < handler.length; i++) {
					result[row][i] = handler[i];
				}
				
		} // row
		
		return result;
	}
	
	

	public byte[][] getResult() {
		return result;
	}

	public void setResult(byte[][] result) {
		this.result = result;
	}

}
