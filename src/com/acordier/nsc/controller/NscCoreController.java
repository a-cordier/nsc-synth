package com.acordier.nsc.controller;

import static com.acordier.nsc.model.NscConstants.WAVE_FORMS;

import com.acordier.nsc.core.Nsc;

import ddf.minim.ugens.Waveform;
/**
 * ModelController owns an instance of MidiInstrument and is responsible
 * to execute control request addressed by any individual control controller
 * used by the view.
 * @author acordier
 *
 */
public class NscCoreController {
	
	final Nsc nsc;

	public NscCoreController(final Nsc nsc){
		this.nsc = nsc;
	}
	
	public void setFrequencyValue(float value){
		nsc.getFilter().frequency.setLastValue(value);
	}
	
	
	public float getFrequencyValue(){
		return nsc.getFilter().frequency.getLastValue();
	}
	
	public void setResonanceValue(float value){
		nsc.getFilter().resonance.setLastValue(value);
	}
	
	public void updateLFO(float value){
		nsc.getLFO().offset.setLastValue(1920+value);
	}
	
	public float getResonanceValue(){
		return nsc.getFilter().resonance.getLastValue();
	}
	
	public void setAmpValue(float value){
		nsc.getAdsr().setAmplitude(value);
	}

	
	public float getAmpValue(){
		return nsc.getAdsr().getAmplitude();
	}

	
	public void setAttackValue(float value){
		nsc.getAdsr().setAttack(value);
	}

	
	public float getAttackValue(){
		return nsc.getAdsr().getAttack();
	}
	
	public void setDecayValue(float value){
		nsc.getAdsr().setDecay(value);
	}
	
	public float getDecayValue(){
		return nsc.getAdsr().getDecay();
	}
	
	
	public void setSustainValue(float value){
		nsc.getAdsr().setSustain(value);
	}
	
	public float getSustainValue(){
		return nsc.getAdsr().getSustain();
	}
	
	public void setReleaseValue(float value){
		nsc.getAdsr().setRelease(value);
	}
	
	public float getReleaseValue(){
		return nsc.getAdsr().getRelease();
	}
	
	public void setGainValue(float value){
		nsc.getGain().setValue(value);
	}
	
	public float getGainValue(){
		return nsc.getGain().gain.getLastValue();
	}
	
	public void setWaveFormForVCO1(int i){
		nsc.getVCO1().setWaveform(WAVE_FORMS[i]);
	}

	public int getWaveFormIndexForVCO1(){
		Waveform waveForm = nsc.getVCO1().getWaveform(); 
		for(int i = 0; i < WAVE_FORMS.length; i++){
			if(WAVE_FORMS[i].equals(waveForm)){
				return i;
			}
		}
		return -1;
	}
	
	public void setWaveFormForVCO2(int i){
		nsc.getVCO2().setWaveform(WAVE_FORMS[i]);
	}
	
	public int getWaveFormIndexForVCO2(){
		Waveform waveForm = nsc.getVCO2().getWaveform(); 
		for(int i = 0; i < WAVE_FORMS.length; i++){
			if(WAVE_FORMS[i].equals(waveForm)){
				return i;
			}
		}
		return -1;
	}
}
