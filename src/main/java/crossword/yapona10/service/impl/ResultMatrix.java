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
	
	public void imposition(List<List<Integer>> rowOrColumn) {
		
		//byte [] handler = new byte[rowOrColumn.size()];
		//byte [] handler2 = new byte[rowOrColumn.get(0).size()];
		
		byte [] handler = new byte[15];
		
		rowOrColumn = new ArrayList<>();
		List<Integer> row = new ArrayList<>();
		
		row.add(3);
		row.add(5);
		row.add(1);
		
		rowOrColumn.add(row);
		
		int element = 0;
		int indexElement = 0;
		int indexSubElement = 0;
		for (int i = 0; i < handler.length; i++) {
			element = rowOrColumn.get(0).get(indexElement);
			if(element - indexSubElement == 0) {
				element = 0;
				indexElement = 0;
				indexSubElement = 0;
			}
			indexElement++;
			indexSubElement++;
		}
		
		for (int i = 0; i < handler.length; i++) {
			System.out.println(handler[i]);
		}
		
	}
	
	

	public byte[][] getResult() {
		return result;
	}

	public void setResult(byte[][] result) {
		this.result = result;
	}

}
