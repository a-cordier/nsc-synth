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
		nsc.getLFO().offset.setLastValue(2200+value);
	}
	
	public float getResonanceValue(){
		return nsc.getFilter().resonance.getLastValue();
	}
	
	public void setLfoFrequencyValue(float value){
		nsc.getLFO().frequency.setLastValue(value);
	}
	
	public float getLfoFrequencyValue(){
		return nsc.getLFO().frequency.getLastValue();
	}
	
	public void setLfoAmplitudeValue(float value){
		nsc.getLFO().amplitude.setLastValue(value);
	}
	
	public float getLfoAmplitudeValue(){
		return nsc.getLFO().amplitude.getLastValue();
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
	
	public void setVco1GainValue(float value) {
		nsc.getVco1Gain().setValue(value);
	}
	
	public float getVco1GainValue(){
		return nsc.getVco1Gain().gain.getLastValue();
	}
	
	public void setVco1OctaveValue(int value){
		nsc.setVco1Oct(value);
	}
	
	public int getVco1OctValue() {
		return nsc.getVco2Oct();
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
	
	public void setVco2GainValue(float value) {
		nsc.getVco2Gain().setValue(value);
	}
	
	public float getVco2GainValue(){
		return nsc.getVco2Gain().gain.getLastValue();
	}
	
	public void setVco2OctaveValue(int value){
		nsc.setVco2Oct(value);
	}
	
	public int getVco2OctValue() {
		return nsc.getVco2Oct();
	}
	
	
	public void setDelayFeedBackValue(float value){
		nsc.getDelay().setDelAmp(value);
	}
	
	public float getDelayFeebackValue(){
		return nsc.getDelay().delAmp.getLastValue();
	}
	
	public void setDelayTimeValue(float value){
		nsc.getDelay().setDelTime(value);
	}
	
	public float getDelayTimeValue(){
		return nsc.getDelay().delTime.getLastValue();
	}
	
	public void setBitCrushResolutionValue(float value){
		nsc.getBitCrush().bitRes.setLastValue(value);
	}
	
	
	public float getBitCrushResolutionValue(){
		return nsc.getBitCrush().bitRes.getLastValue();
	}
	
	public void setBitCrushRateValue(float value){
		nsc.getBitCrush().bitRate.setLastValue(value);
	}
	
	
	public float getBitCrushRateValue(){
		return nsc.getBitCrush().bitRate.getLastValue();
	}
	

}
