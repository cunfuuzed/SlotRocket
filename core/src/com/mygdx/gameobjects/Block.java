package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Rectangle;

public class Block {

	private int rows, columns;
	private float coordX, coordY;
	private Rectangle[][] buttons;
	private float buttonWidth;

	private boolean[][] cells;

	public Block(float coordX, float coordY, float buttonWidth, int rows,
			int columns) {
		this.coordX = coordX;
		this.coordY = coordY;
		this.buttonWidth = buttonWidth;
		this.rows = rows;
		this.columns = columns;
		cells = new boolean[rows][columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				cells[i][j] = true;
			}
		}
		buttons = new Rectangle[rows][columns];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				buttons[i][j] = new Rectangle(coordX + i * buttonWidth, coordY + j
						* buttonWidth, buttonWidth - 2, buttonWidth - 2);
			}
		}

	}

	public float getCoordX() {
		return coordX;
	}

	public float getCoordY() {
		return coordY;
	}

	public boolean[][] getCells() {
		return cells;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public void setCells(boolean[][] cells) {
		this.cells = cells;
	}

	public void resetCells() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				this.cells[i][j] = true;
			}
		}
	}

	public boolean getCellVis(int i, int j) {
		return this.cells[i][j];
	}

	public void setCellVis(int i, int j, boolean tf) {
		this.cells[i][j] = tf;
	}
	
	public Rectangle[][] getButtons() {
		return buttons;
	}

	public void reset(){

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				cells[i][j] = true;
			}
		}


	}


}
