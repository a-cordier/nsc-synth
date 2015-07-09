package com.acordier.nsc.view;

import controlP5.ControlP5;
import controlP5.RadioButton;

public class NscWaveSelector extends RadioButton {
	
	private String[]waves = {"Sine","Tri.", "Saw", "Square", "PWM"};
	
	private NscWaveSelector(Builder builder){
		super(builder.cP5, builder.name);
		this.setSize(builder.width, builder.height);
		//this.setColorForeground(new Color(120).getRGB());
		//this.setColorActive(new Color(255, 50, 50).getRGB());
		this.setColorLabel(0);
		this.setItemsPerRow(1);
		this.setSpacingColumn(50);
		this.activateEvent(true);
		this.setNoneSelectedAllowed(false);
		for(int i = 0; i < waves.length; i++){
			this.addItem(builder.name+waves[i], i);
			this.getItem(i).setCaptionLabel(waves[i]);
		
		}
		this.setPosition(builder.x, builder.y);
	}
	
	public static class Builder{
		private ControlP5 cP5;
		private String name;
		private float x, y;
		private int width, height;
		
		public Builder(String name){
			this.name = name;
		}
		
		public Builder setPosition(float x, float y){
			this.x = x;
			this.y = y;
			return this;
		}
		
		public Builder setDimensions(int width, int height){
			this.width = width;
			this.height = height;
			return this;
		}
		
		
		public RadioButton build(ControlP5 cP5){
			this.cP5 = cP5;
			return new NscWaveSelector(this);
		}
	}
}
