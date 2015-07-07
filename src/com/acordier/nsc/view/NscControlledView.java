package com.acordier.nsc.view;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import processing.core.PApplet;

import com.acordier.nsc.controller.NscViewController;
import com.acordier.nsc.core.Nsc;
import com.acordier.sequoia.common.Colors;

import controlP5.Canvas;
import controlP5.CheckBox;
import controlP5.ControlP5;
import controlP5.Group;
import controlP5.Knob;
import controlP5.RadioButton;
import controlP5.Slider;
import controlP5.Textfield;
import ddf.minim.AudioOutput;

public class NscControlledView extends PApplet {

	private static final long serialVersionUID = 1L;
	private final int SK_WIDTH = 400, SK_HEIGHT = 300;
	ControlP5 cP5;
	Knob filterKnob, resKnob, filterTypeKnob;
	Knob lfoFreqKnob, lfoAmpKnob;
	RadioButton vco1Wave, vco2Wave;
	Knob vco1Gain, vco2Gain;
	Knob vco1Oct, vco2Oct;
	Knob masterGainKnob;
	Knob delayFbKnob, delayTimeKnob;
	Knob bcResKnob, bcRateKnob;
	NscToggle settingsBtn;
	NscListBox midiInDevices;
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
		
		cP5.setColorBackground(Colors.color(147,177,198));
		cP5.setColorForeground(Colors.color(199,208,213));
		cP5.setColorActive(Colors.color(236, 88, 58)); 

		viewController = new NscViewController(nsc, this);
		/* FITLER KNOB VIEW */
		filterKnob = new NscKnob.Builder("Filter").setPosition(10, 10).setRadius(15).build(cP5);
		viewController.bindFilter(filterKnob);

		/* FILTER FREQ LFO FREQ KNOB VIEW */
		lfoFreqKnob = new NscKnob.Builder("LFO Freq.").setPosition(10, 60).setRadius(15).build(cP5);
		viewController.bindLfoFrequency(lfoFreqKnob);
		
		
		/* RES KNOB VIEW */
		resKnob  = new NscKnob.Builder("Res.").setPosition(60, 10).setRadius(15).build(cP5);
		viewController.bindResonance(resKnob);
		
		/* FILTER TYPE KNOB VIEW */
		filterTypeKnob = new NscKnob.Builder("Filt. type").setPosition(65, 65).setRadius(10).build(cP5);
		viewController.bindLfoFrequency(lfoFreqKnob);
		filterTypeKnob.setRange(0, 2);
		filterTypeKnob.setNumberOfTickMarks(2).snapToTickMarks(true).hideTickMarks();
		viewController.bindFilterType(filterTypeKnob);
		/* DELAY TIME VIEW */
		delayTimeKnob = new NscKnob.Builder("Del. Time").setPosition(110, 10).setRadius(15).build(cP5);
		viewController.bindDelayTime(delayTimeKnob);
		
		/* DELAY FEEDBACK VIEW */
		delayFbKnob = new NscKnob.Builder("Del. FB").setPosition(160, 10).setRadius(15).build(cP5);
		viewController.bindDelayFeedBack(delayFbKnob);
		
		/* DELAY TIME VIEW */
		bcResKnob = new NscKnob.Builder("Bc Res.").setPosition(110, 60).setRadius(15).build(cP5);
		viewController.bindBitCrushResolution(bcResKnob);
		
		/* BIT CRUSH RESOLUTION VIEW */
		bcRateKnob = new NscKnob.Builder("Bc Rate. ").setPosition(160, 60).setRadius(15).build(cP5);
		viewController.bindBitCrushRate(bcRateKnob);
		
		/* GAIN ROTARY KNOB VIEW */
		masterGainKnob = new NscKnob.Builder("Gain").setPosition(210, 10).setRadius(15).build(cP5);
		viewController.bindGain(masterGainKnob);
		
		/* ADSR VIEW */
		Group adsrWidget = new NscAdsr.Builder("ADSR").setDimensions(10, 57).setPosition(120,  height-57).build(cP5); 
		viewController.bindAdsr(adsrWidget);
		/* ADSR VIEW DONE */
		
		/* VCO 1 WAVE SELECTOR VIEW */
		vco1Wave = new NscWaveSelector.Builder("wave_vco_1").setPosition(20, height - 5 * (10 + 1) - 2).setDimensions(10, 10).build(cP5);
		viewController.bindWaveSelector(vco1Wave);
		
		vco1Gain = new NscKnob.Builder("gain_vco_1").setPosition(15, height - 100).setRadius(10).build(cP5).setCaptionLabel("Presence");
		viewController.bindVcoGain(vco1Gain);
		
		vco1Oct = new NscKnob.Builder("oct_vco_1").setPosition(15, height - 140).setRadius(10).build(cP5).setCaptionLabel("Oct.");
		vco1Oct.setRange(0, 4);
		vco1Oct.setNumberOfTickMarks(4).snapToTickMarks(true).hideTickMarks();
		viewController.bindVcoOctave(vco1Oct);
		
		/* VCO 2 WAVE SELECTOR VIEW */
		vco2Wave = new NscWaveSelector.Builder("VCO_2").setPosition(70, height - 5 * (10 + 1) - 2).setDimensions(10, 10).build(cP5);
		viewController.bindWaveSelector(vco2Wave);
	
		vco2Gain = new NscKnob.Builder("gain_vco_2").setPosition(65, height - 100).setRadius(10).build(cP5).setCaptionLabel("Presence");
		viewController.bindVcoGain(vco2Gain);
		
		vco2Oct = new NscKnob.Builder("oct_vco_2").setPosition(65, height - 140).setRadius(10).build(cP5).setCaptionLabel("Oct.");
		vco2Oct.setRange(0, 4);
		vco2Oct.setNumberOfTickMarks(4).snapToTickMarks(true).hideTickMarks();
		viewController.bindVcoOctave(vco2Oct);
		
	//	midiInSelector = new NscDropdown.Builder("Midi in").setDimensions(100, 100).setPosition(width-100, 10).build(cP5);
	//	viewController.bindMidiInputDeviceSelector(midiInSelector);
		
		/* WAVE VISUALISATION VIEW */
		Canvas canvas = new Canvas() {
			float width = SK_WIDTH-180, height = 57, cX = 180, cY = SK_HEIGHT-height;
			@Override
			public void draw(PApplet arg0) {
				background(255);
				noStroke();
				rect(cX, cY, width, height);
				stroke(ControlP5.getColor().getActive());
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
		
		/* settings window */
		settingsBtn = new NscToggle.Builder("settings btn")
				.setPosition(width-122, 20)
				.setImages("setting_default.png", "setting_active.png")
				.build(cP5);
		viewController.bindSettings(settingsBtn);
		
		/* additional layer of pics and|or graphics */
		new NscSkin.Builder("").build(cP5);
	}
	
	@Override
	public void draw(){
		
	}

	public ControlFrame addControlFrame(String theName, int theWidth,
			int theHeight) {
		Frame frame = new Frame(theName);
		final ControlFrame controlFrame = new ControlFrame(this, theWidth, theHeight);
		frame.add(controlFrame);
		controlFrame.init();
		controlFrame.setFrame(frame);
		frame.setTitle(theName);
		frame.setSize(controlFrame.w, controlFrame.h);
		frame.setLocation(this.getMousePosition().x, this.getMousePosition().y);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent we) {
	        	System.out.println("dispose");
	        	we.getWindow().dispose();
	        	settingsBtn.setOff();
	         }
	     }
	);
		return controlFrame;
	}
	
	/* Additional Applet used to open|close a setting dialog */
	public class ControlFrame extends PApplet {

		
		private static final long serialVersionUID = 1L;
		private ControlP5 _cP5;
		private NscControlledView parent;
		private int w, h;
		private NscListBox midiInDevices;
		private Frame parentFrame;
		
		public ControlFrame(NscControlledView parent, int theWidth, int theHeight) {
			this.parent = parent;
			w = theWidth;
			h = theHeight;
		}

		public void setup() {
			size(w, h);
			frameRate(25);
			_cP5 = new ControlP5(this);
			Textfield midiOutDevicesLabel = _cP5.addTextfield("Midi out setting label").setPosition(20, 20);
			midiOutDevicesLabel.setText("Available midi input devices");
			midiOutDevicesLabel.setColor(255);
			midiOutDevicesLabel.setColorBackground(ControlP5.getColor().getBackground());
			
			midiInDevices = new NscListBox.Builder("Midi in").setDimensions(200, 200).setPosition(20, 40)
					.build(_cP5);
			midiInDevices.setColorBackground(Colors.color(199,208,213));
			midiInDevices.setColorForeground(Colors.color(236,88,59));
			parent.viewController.bindMidiInputDeviceSelector(midiInDevices);
			
		}

		public void draw() {
			background(250);
		}
		
		public ControlP5 control() {
			return _cP5;
		}
		
		public Object getPArent(){
			return parent;
		}
		
		public Frame getFrame(){
			return parentFrame;
		}
		
		public void setFrame(Frame parentFrame){
			this.parentFrame = parentFrame;
		}
	}
}
