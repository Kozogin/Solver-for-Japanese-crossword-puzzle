package crossword.yapona10.service.impl;

import java.util.ArrayList;
import java.util.List;

public class ResultMatrix {

	private byte[][] result;

	public ResultMatrix(byte[][] result) {
		this.result = result;
	}

	public List<List<Byte>> returnTheList() {

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

	public byte[][] displacementVert(List<List<Integer>> rows, int sizeColumn) {

		byte[] handler = new byte[sizeColumn];
		List<Integer> indents = new ArrayList<>();
		
		for (int row = 0; row < rows.size(); row++) {
			
			createIndents(rows.get(row), sizeColumn);
			
			indents.clear();
			indents = createIndents(rows.get(row), sizeColumn);	
			handler = displacement(rows.get(row), sizeColumn, indents);	
			
			for (int i = 0; i < handler.length; i++) {
				result[row][i] = handler[i];
			}
		}
		return result;
	}

	public byte[][] displacementHorz(List<List<Integer>> columns, int sizeRow) {

		byte[] handler = new byte[sizeRow];		
		
		for (int column = 0; column < columns.size(); column++) {

			//handler = displacement(columns.get(column), sizeRow, finish, 1);			

			for (int i = 0; i < handler.length; i++) {
				result[i][column] = handler[i];
			}
		}
		return result;
	}
	
	
	public List<Integer> createIndents(List<Integer> rowOrColumn, int sizeColumnOrRow) {
		
		List<Integer> indents = new ArrayList<>();
		int summLengthElement = 0;
		int summIndents = 0;
		int indent = 0;
		
		for (int indInnerList = 0; indInnerList < rowOrColumn.size(); indInnerList++) {
			summLengthElement += rowOrColumn.get(indInnerList);
			if(summLengthElement + summIndents <= sizeColumnOrRow) {
				indent = 1;
				
				if(indInnerList == rowOrColumn.size() - 1) {
					indent = sizeColumnOrRow - summLengthElement - summIndents;
				}
				
				summIndents += indent;
				indents.add(indent);
			}			
		}
		
//		System.out.println(rowOrColumn);
//		System.out.println(indents);
		
		return indents;		
	}
	

	public byte[] displacement(List<Integer> rowOrColumn, int sizeColumnOrRow, List<Integer> indents) {
		
		byte[] handler = new byte[sizeColumnOrRow];

		// pass along the line vertical or horizontal push into the matrix from the
		// beginning with a single gap

		int lengthElement = 0;
		int lengthIndent = 0;
		int indMatrix = 0;
		int indexExit = 0;
		for (int indInnerList = 0; indInnerList < rowOrColumn.size(); indInnerList++) {

			lengthElement = rowOrColumn.get(indInnerList);
			lengthIndent = indents.get(indInnerList);
			
			indexExit = indMatrix + lengthElement;

			while (indMatrix < indexExit) {
				handler[indMatrix++] = 1;
			}
			
			indexExit += lengthIndent;
			
			if (indMatrix < sizeColumnOrRow || indexExit < sizeColumnOrRow) {
				while (indMatrix < indexExit) {
					handler[indMatrix++] = 2;
				}
			}
		}		
		return handler;
	}

	public byte[][] getResult() {
		return result;
	}

	public void setResult(byte[][] result) {
		this.result = result;
	}

}
