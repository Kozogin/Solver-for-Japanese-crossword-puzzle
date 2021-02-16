package crossword.yapona10.service.impl;

public class SearchCombinations {

	private int[] indent;
	private int numberElement;
	private int summElement;
	private int matrixLength;
	private int sumAllIndent;

	private int actualyIndex;

	public SearchCombinations(int numberElement, int summElement, int matrixLength) {
		this.numberElement = numberElement;
		this.summElement = summElement;
		this.matrixLength = matrixLength;
		this.actualyIndex = 0;
		this.sumAllIndent = matrixLength - (summElement + numberElement - 1);
		this.indent = new int[numberElement];
		this.indent[0] = -1;
	}

	public boolean countCombinations() {

		if (summArray() + 1 > this.sumAllIndent) {

			this.actualyIndex = 0;
			do {
				this.indent[this.actualyIndex] = 0;
				this.actualyIndex++;
			} while (summArray() >= this.sumAllIndent);

			if (this.actualyIndex < this.numberElement) {
				this.indent[this.actualyIndex]++;
			} else {
				return false;
			}
		} else {
			this.actualyIndex = 0;
			this.indent[this.actualyIndex]++;
		}
		return true;
	}

	private int summArray() {
		int summ = 0;
		for (int i = 0; i < this.indent.length; i++) {
			summ += indent[i];
		}
		return summ;
	}

	public int[] getIndent() {
		return indent;
	}

	@Override
	public String toString() {
		return "SearchCombinations [numberElement=" + numberElement + ", summElement=" + summElement + ", matrixLength="
				+ matrixLength + ", sumAllIndent=" + sumAllIndent + ", actualyIndex=" + actualyIndex + "]";
	}

}
