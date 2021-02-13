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

	public byte[][] impositionVert(List<List<Integer>> rowOrColumn, int sizeColumnOrRow) {

		//byte[] handler = imposition(rowOrColumns, sizeColumnOrRow);
		byte[] handler = new byte[sizeColumnOrRow];

		for (int row = 0; row < rowOrColumn.size(); row++) {

			int lengthElement = 0;
			int indMatrix = 0;
			int indexExit = 0;
			for (int indInnerList = 0; indInnerList < rowOrColumn.get(row).size(); indInnerList++) {

				lengthElement = rowOrColumn.get(row).get(indInnerList);// 2

				indexExit = indMatrix + lengthElement;

				while (indMatrix < indexExit) {
					handler[indMatrix++] = 1;
				}

				if (indMatrix < sizeColumnOrRow) {
					handler[indMatrix++] = 2;
				}

			}

			for (int i = 0; i < handler.length; i++) {
				result[row][i] = handler[i];
			}

		} // row

		return result;
	}

	
				// pass along the line vertical or horizontal push into the matrix from the
				// beginning with a single gap
	

	public byte[][] getResult() {
		return result;
	}

	public void setResult(byte[][] result) {
		this.result = result;
	}

}
