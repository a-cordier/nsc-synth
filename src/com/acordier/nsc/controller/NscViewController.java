package com.acordier.nsc.controller;

import processing.core.PApplet;

import com.acordier.nsc.core.Nsc;

import controlP5.ControlEvent;
import controlP5.ControlListener;
import controlP5.Group;
import controlP5.Knob;
import controlP5.RadioButton;
import static com.acordier.mnmd.model.NscConstants.FREQ_MAX;
import static com.acordier.mnmd.model.NscConstants.FREQ_MIN;
import static com.acordier.mnmd.model.NscConstants.RES_MAX;
import static com.acordier.mnmd.model.NscConstants.RES_MIN;
import static com.acordier.mnmd.model.NscConstants.AMP_MIN;
import static com.acordier.mnmd.model.NscConstants.AMP_MAX;
import static com.acordier.mnmd.model.NscConstants.ATT_MIN;
import static com.acordier.mnmd.model.NscConstants.ATT_MAX;
import static com.acordier.mnmd.model.NscConstants.DEC_MIN;
import static com.acordier.mnmd.model.NscConstants.DEC_MAX;
import static com.acordier.mnmd.model.NscConstants.SUS_MIN;
import static com.acordier.mnmd.model.NscConstants.SUS_MAX;
import static com.acordier.mnmd.model.NscConstants.REL_MIN;
import static com.acordier.mnmd.model.NscConstants.REL_MAX;
import static com.acordier.mnmd.model.NscConstants.GAIN_MIN;
import static com.acordier.mnmd.model.NscConstants.GAIN_MAX;

public class NscViewController {

	NscCoreController coreController;

	public NscViewController(final Nsc nsc) {
		coreController = new NscCoreController(nsc);
	}

	public void bindFilter(Knob control) {
		control.addListener(new ControlListener() {
			@Override
			public void controlEvent(ControlEvent event) {
				float value = PApplet.map(event.getValue(), 0, 127, FREQ_MIN, FREQ_MAX);
				coreController.setFrequencyValue(value);
				coreController.updateLFO(value);
			}
		});
		control.setValue(PApplet.map(coreController.getFrequencyValue(), FREQ_MIN, FREQ_MAX, 0, 127));
	}

	public void bindResonance(Knob control) {
		control.addListener(new ControlListener() {
			@Override
			public void controlEvent(ControlEvent event) {
				float value = PApplet.map(event.getValue(), 0, 127, RES_MIN, RES_MAX);
				System.out.println("res: " + value);
				coreController.setResonanceValue(value);
			}
		});
		control.setValue(PApplet.map(coreController.getResonanceValue(), RES_MIN, RES_MAX,0, 127));
	}

	public void bindGain(Knob control) {
		control.addListener(new ControlListener() {
			@Override
			public void controlEvent(ControlEvent event) {
				float value = PApplet.map(event.getValue(), 0, 127, GAIN_MIN, GAIN_MAX);
				coreController.setGainValue(value);
			}
		});
		control.setValue(PApplet.map(coreController.getGainValue(), GAIN_MIN, GAIN_MAX, 0, 127));
	}

	public void bindAdsr(Group controls) {
		controls.getController("Amp.").addListener(new ControlListener() {

			@Override
			public void controlEvent(ControlEvent event) {
				float value = PApplet.map(event.getValue(), 0, 127, AMP_MIN, AMP_MAX);
				coreController.setAmpValue(value);
			}
		});
		controls.getController("Amp.").setValue(PApplet.map(coreController.getAmpValue(),AMP_MIN, AMP_MAX, 0,127));
		controls.getController("Att.").addListener(new ControlListener() {

			@Override
			public void controlEvent(ControlEvent event) {
				float value = PApplet.map(event.getValue(), 0,
						127, ATT_MIN, ATT_MAX);
				coreController.setAttackValue(value);

			}
		});
		controls.getController("Att.").setValue(PApplet.map(coreController.getAttackValue(), ATT_MIN, ATT_MAX, 0, 127));


		controls.getController("Dec.").addListener(new ControlListener() {

			@Override
			public void controlEvent(ControlEvent event) {
				float value = PApplet.map(event.getValue(), 0, 127, DEC_MIN,
						DEC_MAX);
				coreController.setDecayValue(value);
			}
		});
		controls.getController("Dec.").setValue(PApplet.map(coreController.getDecayValue(),  DEC_MIN,
				DEC_MAX, 0, 127));

		controls.getController("Sus.").addListener(new ControlListener() {

			@Override
			public void controlEvent(ControlEvent event) {
				float value = PApplet.map(event.getValue(), 0, 127, SUS_MIN,
						SUS_MAX);
				coreController.setSustainValue(value);
			}
		});
		controls.getController("Sus.").setValue(PApplet.map(coreController.getDecayValue(), SUS_MIN,
				SUS_MAX, 0, 127));
		
		controls.getController("Rel.").addListener(new ControlListener() {

			@Override
			public void controlEvent(ControlEvent event) {
				float value = PApplet.map(event.getValue(), 0, 127, REL_MIN,
						REL_MAX);
				coreController.setReleaseValue(value);
			}
		});
		controls.getController("Rel.").setValue(PApplet.map(coreController.getDecayValue(), REL_MIN,
				REL_MAX, 0, 127));
	}
	
	public void bindWaveSelector(RadioButton control){
		if(control.getName().matches("^.*1$")){
			System.out.println(true);
			for(int i = 0; i < control.getItems().size(); i++){
				final int idx = i;
				control.getItem(i).addListener(new ControlListener() {				
					@Override
					public void controlEvent(ControlEvent event) {
						coreController.setWaveFormForVCO1(idx);	
					}
				});		
			}
			control.activate(coreController.getWaveFormIndexForVCO1());
		} else {
			for(int i = 0; i < control.getItems().size(); i++){
				final int idx = i;
				control.getItem(i).addListener(new ControlListener() {				
					@Override
					public void controlEvent(ControlEvent event) {
						coreController.setWaveFormForVCO2(idx);	
					}
				});
				
			}
			control.activate(coreController.getWaveFormIndexForVCO2());
		}
	}
}
