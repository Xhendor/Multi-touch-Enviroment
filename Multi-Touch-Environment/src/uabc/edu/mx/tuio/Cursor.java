package uabc.edu.mx.tuio;



import java.util.UUID;


/*
 * Copyright (C) 2012 rosendorsc
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 */
/**
 *
 * @author rosendorsc
 */

public final class Cursor {
    
	private int lastPointX = 0;
	private int lastPointY = 0;
	private final TuioCursor tcur;
	private UUID uid;
	private boolean showMenu = false;
	private boolean occuped=false;
	
	public Cursor(final TuioCursor tcur) {
		this.tcur = tcur;
		setUid(UUID.randomUUID());
	

	}

	public TuioCursor getTcur() {
		return tcur;
	}

	public int getLastPointX() {
		return lastPointX;
	}

	public void setLastPointX(int lastPointX) {
		this.lastPointX = lastPointX;
	}

	public int getLastPointY() {
		return lastPointY;
	}

	public void setLastPointY(int lastPointY) {
		this.lastPointY = lastPointY;
	}

	public UUID getUid() {
		return uid;
	}

	public void setUid(UUID uid) {
		this.uid = uid;
	}

	public boolean isShowMenu() {
		return showMenu;
	}

	public void setShowMenu(boolean showMenu) {
		this.showMenu = showMenu;
	}

	public boolean isOccuped() {
		return occuped;
	}

	public void setOccuped(boolean occuped) {
		//this.occuped = occuped;
		
		//timelapsed();
		
	}

	
	private synchronized void timelapsed(){
//		if(!showMenu){
		Thread counter = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					
					if (lastPointX == tcur.getScreenX(800)
							&& lastPointY == tcur.getScreenY(600)) {

						reverseCount(30);

					} else {
						
						showMenu = false;

					}
				}

			}

			public synchronized void reverseCount(int seconds) {
				int second = seconds;
				while (second > 0) {

					try {

						Thread.sleep(100L);
					}

					catch (Exception e) {
					}

					second--;

				}

				showMenu = true;

			}
		});

		counter.start();}
}
