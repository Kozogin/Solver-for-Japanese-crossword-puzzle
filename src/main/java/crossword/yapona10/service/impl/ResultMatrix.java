package crossword.yapona10.service.impl;

import java.util.ArrayList;
import java.util.List;

public class ResultMatrix {

	private byte[][] result;

	private boolean finish;

	public ResultMatrix(byte[][] result) {
		this.result = result;
		this.finish = false;
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

		for (int row = 0; row < 1; row++) {
//		for (int row = 0; row < rows.size(); row++) {					

			
			int visual = 0;
			for (int indexIndentAdd = rows.get(0).size(); indexIndentAdd > 0; indexIndentAdd--) {
				
				System.out.println("indexIndentAdd  -------- " + indexIndentAdd);
				
				int increaseIndent = 0;
				
				this.finish = true;
				while (this.finish) {
					
					indents.clear();
					//indents = createIndents(rows.get(row), sizeColumn, indexIndentAdd - 2, increaseIndent++);
					indents = createIndents(rows.get(row), sizeColumn, indexIndentAdd - 1, increaseIndent++);
					
					handler = displacement(rows.get(row), sizeColumn, indents);

					for (int j = 0; j < handler.length; j++) {
						result[visual][j] = handler[j];
					}
					
					
					visual++;
					if(visual > 12) {
						visual = 12;
					}
				}
				

			}

//			for (int i = 0; i < handler.length; i++) {
//				result[row][i] = handler[i];
//			}
		}
		return result;
	}

	public byte[][] displacementHorz(List<List<Integer>> columns, int sizeRow) {

		byte[] handler = new byte[sizeRow];
		List<Integer> indents = new ArrayList<>();

		for (int column = 0; column < columns.size(); column++) {

			indents.clear();
			indents = createIndents(columns.get(column), sizeRow, 0, 0);
			handler = displacement(columns.get(column), sizeRow, indents);

			for (int i = 0; i < handler.length; i++) {
				result[i][column] = handler[i];
			}
		}
		return result;
	}

	public List<Integer> createIndents(List<Integer> rowOrColumn, int sizeColumnOrRow, 
			int indexIndentAdd, int increaseIndent) {

		List<Integer> indents = new ArrayList<>();
		
		if(indexIndentAdd == 0) {
			indents.add(increaseIndent);
		} else {
			indents.add(0);
		}
		int summLengthElement = 0;
		int summIndents = increaseIndent;
		int indent = 0;

		for (int indInnerList = 0; indInnerList < rowOrColumn.size(); indInnerList++) {
			summLengthElement += rowOrColumn.get(indInnerList);
			
			if (summLengthElement + summIndents <= sizeColumnOrRow) {

				indent = indexIndentAdd == indInnerList + 1 ? 1 + increaseIndent : 1;
				
//				System.out.println("indexIndentAdd--------------" + indexIndentAdd);
//				System.out.println("indInnerList + 1-----" + indInnerList + 1);
//				System.out.println("indent-----+++++" + indent);
				

				if (indInnerList == rowOrColumn.size() - 1) {
					indent = sizeColumnOrRow - summLengthElement - summIndents;
					
//					System.out.println("indent--------------" + indent);
//					System.out.println("sizeColumnOrRow-----" + sizeColumnOrRow);
//					System.out.println("summLengthElement---" + summLengthElement);
//					System.out.println("summIndents---------" + summIndents);
										
				}

				summIndents += indent;
				indents.add(indent);

				if (indents.size() == rowOrColumn.size() + 1 && indents.get(indents.size() - 1) == 0) {
					this.finish = false;
					System.out.println(" 0000000000 " + indents.get(indents.size() - 1) + "   ---  " + finish);
				}
			}
		}

		System.out.println(" " + rowOrColumn);
		System.out.println("                     " + indents);

		return indents;
	}

	public byte[] displacement(List<Integer> rowOrColumn, int sizeColumnOrRow, List<Integer> indents) {

		byte[] handler = new byte[sizeColumnOrRow];

		// pass along the line vertical or horizontal push into the matrix from the
		// beginning with a single gap

		int lengthElement = 0;
		int lengthIndent = 0;
		int indMatrix = 0;
		int indexExit = indents.get(0);
		
		
		if(indents.get(0) != 0){
			while (indMatrix < indexExit) {
				handler[indMatrix++] = 2;
			}
		}
		
		
		for (int indInnerList = 0; indInnerList < rowOrColumn.size(); indInnerList++) {
			
			lengthElement = rowOrColumn.get(indInnerList);
			lengthIndent = indents.get(indInnerList + 1);

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
