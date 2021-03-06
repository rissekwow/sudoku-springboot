package wt.sudoku.model.board.main;

import java.util.List;

import wt.sudoku.model.elements.container.BoardMap;
import wt.sudoku.model.elements.container.CellParams;

public class Cell implements Cloneable {

	private int value;
	private CellParams cellParams;
	private BoardMap boardMap;
	private List<Integer> availableValues;

	public Cell(CellParams cellParams, BoardMap boardMap) {
		this.cellParams = cellParams;
		this.boardMap = boardMap;
		value = 0;
	}

	public void addValue(int value) {
		addValueToAllCellTypeContainers(value);
		this.value = value;
	}

	public void deleteValue() {
		removeValueFromAllCellTypeContainers(value);
		this.value = 0;
	}

	public boolean isValueAcceptable(int value) {
		return value != 0 && !isValueExistInRect3X3(value) && !isValueExistInColumn(value) && !isValueExistInRow(value);
	}

	public int getValue() {
		return value;
	}

	public CellParams getCellParams() {
		return cellParams;
	}

	public void setCellParams(CellParams cellParams) {
		this.cellParams = cellParams;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public List<Integer> getAvailableValues() {
		return availableValues;
	}

	public void setAvailableValues(List<Integer> availableValues) {
		this.availableValues = availableValues;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	private void addValueToAllCellTypeContainers(int value) {
		boardMap.getRects().addValueToType(cellParams.getRectType(), value);
		boardMap.getRows().addValueToType(cellParams.getRowType(), value);
		boardMap.getColumns().addValueToType(cellParams.getColumnType(), value);
	}

	private void removeValueFromAllCellTypeContainers(int value) {
		boardMap.getRects().removeElementFromType(cellParams.getRectType(), value);
		boardMap.getRows().removeElementFromType(cellParams.getRowType(), value);
		boardMap.getColumns().removeElementFromType(cellParams.getColumnType(), value);
	}

	private boolean isValueExistInRow(int value) {
		return boardMap.getRows().getRows().get(cellParams.getRowType()).contains(value);
	}

	private boolean isValueExistInColumn(int value) {
		return boardMap.getColumns().getColumns().get(cellParams.getColumnType()).contains(value);
	}

	private boolean isValueExistInRect3X3(int value) {
		return boardMap.getRects().getRects().get(cellParams.getRectType()).contains(value);
	}
	
	

}
