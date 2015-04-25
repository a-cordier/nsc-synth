package com.acordier.nsc.logging;

import controlP5.ControlEvent;

public class NscLogger {
	
	public static void logEvent(ControlEvent event, float value){
		System.out.println(String.format("%s : %s", event.getController().getName(), value));
	}
}
