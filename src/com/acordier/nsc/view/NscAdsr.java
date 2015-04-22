package com.acordier.nsc.view;

import controlP5.ControlP5;
import controlP5.Group;
import controlP5.Slider;

public class NscAdsr extends Group{
		
	private int sliderHeight = 57;
	private int sliderWidth = 10;
	private String[]modules= {"Amp.","Att.","Dec.","Sus.","Rel."};
	
	private NscAdsr(Builder builder){
		super(builder.cP5, builder.name);
		this.hideBar();
		for(String module:modules){
			Slider slider = builder.cP5.addSlider(module);
			slider.setMin(0).setMax(127);
			slider.setWidth(sliderWidth).setHeight(sliderHeight);
			slider.setPosition(builder.x, builder.y);
			slider.setGroup(this);
			builder.x += sliderWidth + 1;
		}	
		this.setWidth((sliderWidth+1) * modules.length);
		this.setHeight(sliderHeight);
	}
	
	public static class Builder {

		private String name;
		private float x = 5, y = 5;
		private ControlP5 cP5;

		public Builder(String name) {
			this.name = name;
		}

		public Builder setPosition(float x, float y) {
			this.x = x;
			this.y = y;
			return this;
		}

		public Group build(ControlP5 cP5) {
			this.cP5 = cP5;
			return new NscAdsr(this);
		}
	}
}
