package com.acordier.nsc.view;

import java.awt.Color;

import controlP5.ControlP5;
import controlP5.Knob;

public class NscKnob extends Knob {
	
	private NscKnob(Builder builder) {
		super(builder.cP5, builder.name);
		this.setPosition(builder.x, builder.y);
		this.setRange(0, 127);
		this.setViewStyle(Knob.ARC);
		this.setConstrained(true);
		this.setRadius(builder.radius);
		this.setDragDirection(Knob.VERTICAL);
		this.setCaptionLabel(builder.name);
		this.setLabelVisible(true);
		this.setColorCaptionLabel(0);
		this.getColor().setActive(new Color(255, 50, 50).getRGB());
		this.getValueLabel().hide();
		// reverse the anti-intuitive default rotation mode ?
; 
	}
	
	public static class Builder {
		
		private String name;
		private float x = 5,  y = 5;
		private int radius;
		private ControlP5 cP5;
		
		public Builder(String name){
			this.name = name;
		}
		
		public Builder setPosition(float x, float y){
			this.x = x;
			this.y = y;
			return this;
		}
		
		public Builder setRadius(int radius){
			this.radius = radius;
			return this;
		}
		
		public Knob build(ControlP5 cP5){
			this.cP5 = cP5;
			return new NscKnob(this);
		}
	}

}
