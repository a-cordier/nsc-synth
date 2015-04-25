package com.acordier.nsc.view;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

import com.acordier.mnmd.features.Midinette;
import com.acordier.nsc.controller.NscViewController;
import com.acordier.nsc.core.Nsc;

import controlP5.Canvas;
import controlP5.CheckBox;
import controlP5.ControlP5;
import controlP5.Group;
import controlP5.Knob;
import controlP5.RadioButton;
import controlP5.Slider;
import ddf.minim.AudioOutput;

public class NscControlledView extends PApplet {

	private static final long serialVersionUID = 1L;
	private final int SK_WIDTH = 400, SK_HEIGHT = 300;
	ControlP5 cP5;
	Knob filterKnob, resKnob;
	Knob lfoFreqKnob, lfoAmpKnob;
	RadioButton vco1Wave, vco2Wave;
	Knob gainKnob;
	Knob delayFbKnob, delayTimeKnob;
	Knob bcResKnob, bcRateKnob;
	Slider ampSlider, attackSlider, decaySlider, sustainSlider, releaseSlider;
	Canvas adsrCanvas;
	List<CheckBox> stepSequencer;
	final Nsc nsc = new Nsc(this);
	NscViewController viewController;
	
	@Override
	public void setup() {
		background(255);
		size(SK_WIDTH, SK_HEIGHT);
		cP5 = new ControlP5(this);
		Midinette midinette = new Midinette(nsc);
		stepSequencer = new ArrayList<CheckBox>(midinette.getStepSequence()
				.size());
		midinette.randomize(3);
		midinette.play(true);
		
		viewController = new NscViewController(nsc);
		/* FITLER KNOB VIEW */
		filterKnob = new NscKnob.Builder("Filter").setPosition(5, 5).setRadius(15).build(cP5);
		viewController.bindFilter(filterKnob);
		
		/* FILTER FREQ LFO FREQ KNOB VIEW */
		lfoFreqKnob = new NscKnob.Builder("LFO Freq.").setPosition(5, 50).setRadius(15).build(cP5);
		viewController.bindLfoFrequency(lfoFreqKnob);
		
		
		/* RES KNOB VIEW */
		resKnob  = new NscKnob.Builder("Res.").setPosition(50, 5).setRadius(15).build(cP5);
		viewController.bindResonance(resKnob);
		
		/* DELAY TIME VIEW */
		delayTimeKnob = new NscKnob.Builder("Del. Time").setPosition(100, 5).setRadius(15).build(cP5);
		viewController.bindDelayTime(delayTimeKnob);
		
		/* DELAY FEEDBACK VIEW */
		delayFbKnob = new NscKnob.Builder("Del. FB").setPosition(150, 5).setRadius(15).build(cP5);
		viewController.bindDelayFeedBack(delayFbKnob);
		
		/* DELAY TIME VIEW */
		bcResKnob = new NscKnob.Builder("Bc Res.").setPosition(100, 50).setRadius(15).build(cP5);
		viewController.bindBitCrushResolution(bcResKnob);
		
		/* BIT CRUSH RESOLUTION VIEW */
		bcRateKnob = new NscKnob.Builder("Bc Rate. ").setPosition(150, 50).setRadius(15).build(cP5);
		viewController.bindBitCrushRate(bcRateKnob);
		
		/* GAIN ROTARY KNOB VIEW */
		gainKnob = new NscKnob.Builder("Gain").setPosition(200, 5).setRadius(15).build(cP5);
		viewController.bindGain(gainKnob);
		
		/* ADSR VIEW */
		Group adsrWidget = new NscAdsr.Builder("ADSR").setDimensions(10, 57).setPosition(100,  height-57).build(cP5); 
		viewController.bindAdsr(adsrWidget);
		/* ADSR VIEW DONE */
		
		/* VCO 1 WAVE SELECTOR VIEW */
		vco1Wave = new NscWaveSelector.Builder("VCO_1").setPosition(0, height - 5 * (10 + 1) - 2).setDimensions(10, 10).build(cP5);
		viewController.bindWaveSelector(vco1Wave);

		
		/* VCO 2 WAVE SELECTOR VIEW */
		vco2Wave = new NscWaveSelector.Builder("VCO_2").setPosition(50, height - 5 * (10 + 1) - 2).setDimensions(10, 10).build(cP5);
		viewController.bindWaveSelector(vco2Wave);
	
		/* WAVE VISUALISATION VIEW */
		Canvas canvas = new Canvas() {
			float width = SK_WIDTH-170, height = 57, cX = 170, cY = SK_HEIGHT-height;
			@Override
			public void draw(PApplet arg0) {
				background(255);
				noStroke();
				rect(cX, cY, width, height);
				stroke(ControlP5.getColor().getBackground());
				AudioOutput out = nsc.getAudioOut();
				// draw the waveforms
				for (int i = 0; i < out.bufferSize() - 1; i++) {
					// find the x position of each buffer value
					float x1 = map(i, 0, out.bufferSize(), cX, cX+width);
					float x2 = map(i + 1, 0, out.bufferSize(), cX, cX+width);
					float y1 = map(out.left.get(i), 0, 1, cY+height/2,  cY+height);
					float y2 = map(out.left.get(i+1), 0, 1, cY+height/2,  cY+height);
					line(x1, y1, x2,
							y2);
					line(x1, y1, x2,
							y2);
				}
				
			}
		};
		canvas.setMode(Canvas.PRE);
		cP5.addCanvas(canvas);
	}

	@Override
	public void draw() {		

 	}

}
