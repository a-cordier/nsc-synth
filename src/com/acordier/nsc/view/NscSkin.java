package com.acordier.nsc.view;

import processing.core.PApplet;
import processing.core.PFont;

import com.acordier.sequoia.common.Fonts;

import controlP5.Canvas;
import controlP5.ControlP5;

public class NscSkin extends Canvas {
	
	
	private NscSkin(Builder builder) {
		super();
	}
	
	@Override
	public void draw(PApplet applet) {
		PFont font = Fonts.loadFont("coolvetica.ttf", 40);
		applet.textFont(font);
		applet.fill(236, 88, 58);
		applet.text("nsc Synth", 10, 140);
		applet.textSize(20);
		applet.text("The not so cheap synthetiser", 131, 155);
	}
	
	public static class Builder {

		public Builder(String name) {}
		
		public NscSkin build(ControlP5 cP5) {
			NscSkin skin = new NscSkin(this);
			cP5.addCanvas(skin);
			return skin;
		}
	}
}
