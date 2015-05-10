package com.acordier.nsc.controller;

import java.util.List;

import javax.sound.midi.MidiDevice;

import processing.core.PApplet;

import com.acordier.nsc.core.Nsc;
import com.acordier.nsc.logging.NscLogger;

import controlP5.ControlEvent;
import controlP5.ControlListener;
import controlP5.DropdownList;
import controlP5.Group;
import controlP5.Knob;
import controlP5.RadioButton;
import static com.acordier.nsc.model.NscConstants.AMP_MAX;
import static com.acordier.nsc.model.NscConstants.AMP_MIN;
import static com.acordier.nsc.model.NscConstants.ATT_MAX;
import static com.acordier.nsc.model.NscConstants.ATT_MIN;
import static com.acordier.nsc.model.NscConstants.DEC_MAX;
import static com.acordier.nsc.model.NscConstants.DEC_MIN;
import static com.acordier.nsc.model.NscConstants.FREQ_MAX;
import static com.acordier.nsc.model.NscConstants.FREQ_MIN;
import static com.acordier.nsc.model.NscConstants.GAIN_MAX;
import static com.acordier.nsc.model.NscConstants.GAIN_MIN;
import static com.acordier.nsc.model.NscConstants.REL_MAX;
import static com.acordier.nsc.model.NscConstants.REL_MIN;
import static com.acordier.nsc.model.NscConstants.RES_MAX;
import static com.acordier.nsc.model.NscConstants.RES_MIN;
import static com.acordier.nsc.model.NscConstants.SUS_MAX;
import static com.acordier.nsc.model.NscConstants.SUS_MIN;
import static com.acordier.nsc.model.NscConstants.DEL_FB_MIN;
import static com.acordier.nsc.model.NscConstants.DEL_FB_MAX;
import static com.acordier.nsc.model.NscConstants.DEL_TIME_MIN;
import static com.acordier.nsc.model.NscConstants.DEL_TIME_MAX;
import static com.acordier.nsc.model.NscConstants.LFO_FREQ_MIN;
import static com.acordier.nsc.model.NscConstants.LFO_FREQ_MAX;
import static com.acordier.nsc.model.NscConstants.BC_MIN_RES;
import static com.acordier.nsc.model.NscConstants.BC_MAX_RES;
import static com.acordier.nsc.model.NscConstants.BC_MIN_RATE;
import static com.acordier.nsc.model.NscConstants.BC_MAX_RATE;
import static com.acordier.nsc.model.NscConstants.VCO_GAIN_MIN;
import static com.acordier.nsc.model.NscConstants.VCO_GAIN_MAX;
import static com.acordier.nsc.model.NscConstants.VCO_OCT_MIN;
import static com.acordier.nsc.model.NscConstants.VCO_OCT_MAX;

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
				NscLogger.logEvent(event, value);
			}
		});
		control.setValue(PApplet.map(coreController.getFrequencyValue(), FREQ_MIN, FREQ_MAX, 0, 127));
	}

	public void bindLfoFrequency(Knob control) {
		control.addListener(new ControlListener() {
			@Override
			public void controlEvent(ControlEvent event) {
				float value = PApplet.map(event.getValue(), 0, 127, LFO_FREQ_MIN, LFO_FREQ_MAX);
				coreController.setLfoFrequencyValue(value);
				NscLogger.logEvent(event, value);
			}
		});
		control.setValue(PApplet.map(coreController.getLfoFrequencyValue(), LFO_FREQ_MIN, LFO_FREQ_MAX, 0, 127));
	}
	

	public void bindResonance(Knob control) {
		control.addListener(new ControlListener() {
			@Override
			public void controlEvent(ControlEvent event) {
				float value = PApplet.map(event.getValue(), 0, 127, RES_MIN, RES_MAX);
				coreController.setResonanceValue(value);
				NscLogger.logEvent(event, value);

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
				NscLogger.logEvent(event, value);

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
				NscLogger.logEvent(event, value);

			}
		});
		controls.getController("Amp.").setValue(PApplet.map(coreController.getAmpValue(),AMP_MIN, AMP_MAX, 0,127));
		controls.getController("Att.").addListener(new ControlListener() {

			@Override
			public void controlEvent(ControlEvent event) {
				float value = PApplet.map(event.getValue(), 0,
						127, ATT_MIN, ATT_MAX);
				coreController.setAttackValue(value);
				NscLogger.logEvent(event, value);


			}
		});
		controls.getController("Att.").setValue(PApplet.map(coreController.getAttackValue(), ATT_MIN, ATT_MAX, 0, 127));


		controls.getController("Dec.").addListener(new ControlListener() {

			@Override
			public void controlEvent(ControlEvent event) {
				float value = PApplet.map(event.getValue(), 0, 127, DEC_MIN,
						DEC_MAX);
				coreController.setDecayValue(value);
				NscLogger.logEvent(event, value);
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
				NscLogger.logEvent(event, value);
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
				NscLogger.logEvent(event, value);

			}
		});
		controls.getController("Rel.").setValue(PApplet.map(coreController.getDecayValue(), REL_MIN,
				REL_MAX, 0, 127));
	}
	
	public void bindWaveSelector(RadioButton control){
		if(control.getName().matches("^.*1$")){
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

	public void bindVcoGain(Knob control){
		if(control.getName().matches("^.*1$")){
			control.addListener(new ControlListener() {
				@Override
				public void controlEvent(ControlEvent event) {
					float value = PApplet.map(event.getValue(), 0, 127, VCO_GAIN_MIN, VCO_GAIN_MAX);
					coreController.setVco1GainValue(value);
					NscLogger.logEvent(event, value);
				}
			});
			control.setValue(PApplet.map(coreController.getVco1GainValue(), VCO_GAIN_MIN, VCO_GAIN_MAX, 0, 127));
		} else {
			control.addListener(new ControlListener() {
				@Override
				public void controlEvent(ControlEvent event) {
					float value = PApplet.map(event.getValue(), 0, 127, VCO_GAIN_MIN, VCO_GAIN_MAX);
					coreController.setVco2GainValue(value);
					NscLogger.logEvent(event, value);
				}
			});
			control.setValue(PApplet.map(coreController.getVco2GainValue(), VCO_GAIN_MIN, VCO_GAIN_MAX, 0, 127));
		}
	}
	
	public void bindVcoOctave(Knob control){
		if(control.getName().matches("^.*1$")){
			control.addListener(new ControlListener() {
				@Override
				public void controlEvent(ControlEvent event) {
					int value = PApplet.ceil(PApplet.map(event.getValue(), 0, 4, VCO_OCT_MIN, VCO_OCT_MAX));
					coreController.setVco1OctaveValue(value);
					NscLogger.logEvent(event, value);
				}
			});
			control.setValue(PApplet.map(coreController.getVco1OctValue(), VCO_OCT_MIN, VCO_OCT_MAX, 0, 4));
		} else {
			control.addListener(new ControlListener() {
				@Override
				public void controlEvent(ControlEvent event) {
					int value = PApplet.ceil(PApplet.map(event.getValue(), 0, 4, VCO_OCT_MIN, VCO_OCT_MAX));
					coreController.setVco2OctaveValue(value);
					NscLogger.logEvent(event, value);
				}
			});
			control.setValue(PApplet.map(coreController.getVco2OctValue(), VCO_OCT_MIN, VCO_OCT_MAX, 0, 4));
		}
	}
	
	
	public void bindDelayTime(Knob control){
		control.addListener(new ControlListener() {
			@Override
			public void controlEvent(ControlEvent event) {
				float value = PApplet.map(event.getValue(), 0, 127, DEL_TIME_MIN, DEL_TIME_MAX);
				coreController.setDelayTimeValue(value);
				NscLogger.logEvent(event, value);
			}
		});
		control.setValue(PApplet.map(coreController.getDelayTimeValue(), DEL_TIME_MIN, DEL_TIME_MAX, 0, 127));
	}
	
	public void bindDelayFeedBack(Knob control){
		control.addListener(new ControlListener() {
			@Override
			public void controlEvent(ControlEvent event) {
				float value = PApplet.map(event.getValue(), 0, 127, DEL_FB_MIN, DEL_FB_MAX);
				coreController.setDelayFeedBackValue(value);
				NscLogger.logEvent(event, value);
			}
		});
		control.setValue(PApplet.map(coreController.getDelayFeebackValue(), DEL_FB_MIN, DEL_FB_MAX, 0, 127));
	}
	
	public void bindBitCrushResolution(Knob control){
		control.addListener(new ControlListener() {
			@Override
			public void controlEvent(ControlEvent event) {
				float value = PApplet.map(event.getValue(), 0, 127, BC_MIN_RES, BC_MAX_RES);
				coreController.setBitCrushResolutionValue(value);
				NscLogger.logEvent(event, value);
			}
		});
		control.setValue(PApplet.map(coreController.getBitCrushResolutionValue(), BC_MIN_RES, BC_MAX_RES, 0, 127));
	}
	
	public void bindBitCrushRate(Knob control){
		control.addListener(new ControlListener() {
			@Override
			public void controlEvent(ControlEvent event) {
				float value = PApplet.map(event.getValue(), 0, 127, BC_MIN_RATE, BC_MAX_RATE);
				coreController.setBitCrushRateValue(value);;
				NscLogger.logEvent(event, value);
			}
		});
		control.setValue(PApplet.map(coreController.getBitCrushRateValue(), BC_MIN_RATE, BC_MAX_RATE, 0, 127));
	}
	
	public void bindMidiInputDeviceSelector(DropdownList control) {
		List<MidiDevice> devices = coreController.getMidiInputDevices();
		int i = 0;
		for (MidiDevice device : devices) {
			control.addItem(device.getDeviceInfo().getName(), i);
			i++;
		}
		final DropdownList _control = control;
		control.addListener(new ControlListener() {
			@Override
			public void controlEvent(ControlEvent event) {
				int idx = ((int) event.getValue());
				String deviceName = _control.getItem(idx).getName();
				System.out.println(deviceName);
				coreController.setMidiInputDevice(deviceName);
			}
		});
	}

}
