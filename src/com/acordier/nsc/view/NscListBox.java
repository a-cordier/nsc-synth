package com.acordier.nsc.view;

import controlP5.ControlP5;
import controlP5.ListBox;

public class NscListBox extends ListBox {

	private NscListBox(Builder builder) {
		super(builder.cP5, builder.name);
		this.setWidth(builder.w);
		this.setHeight(builder.h * 2);
		this.setPosition(builder.x, builder.y);
		this.setOpen(true);
		this.setItemHeight(10).setBarHeight(15);
		this.setScrollbarVisible(true);
		this.getColor().setForeground(ControlP5.getColor().getActive());
		isBarVisible = false;

	}
	
	public static class Builder {

		private ControlP5 cP5;
		private String name;
		private float x, y;
		private int w, h;


		public Builder(String name) {
			this.name = name;
		}

		public Builder setPosition(float x, float y) {
			this.x = x;
			this.y = y;
			return this;
		}



		public Builder setDimensions(int w, int h) {
			this.w = w;
			this.h = h;
			return this;
		}

		public NscListBox build(ControlP5 cP5) {
			this.cP5 = cP5;
			return new NscListBox(this);
		}
		

	}
}
