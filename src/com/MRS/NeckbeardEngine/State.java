package com.MRS.NeckbeardEngine;
public enum State {
	RED (1),
	BLUE (2),
	BOTH (3);
  
	private final int state;
	
	private State (int state) {
		this.state = state;
	}
	
	private double state() {
		return this.state;
	}
}
