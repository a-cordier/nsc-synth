package com.acordier.nsc.core;

import processing.core.PApplet;

import com.acordier.mnmd.core.MidiInstrument;
import com.acordier.mnmd.core.MidiReceiver;
import com.acordier.mnmd.features.AdsrX;

import ddf.minim.AudioOutput;
import ddf.minim.Minim;
import ddf.minim.SignalSplitter;
import ddf.minim.ugens.BitCrush;
import ddf.minim.ugens.Bypass;
import ddf.minim.ugens.Damp;
import ddf.minim.ugens.Delay;
import ddf.minim.ugens.Frequency;
import ddf.minim.ugens.Gain;
import ddf.minim.ugens.MoogFilter;
import ddf.minim.ugens.Multiplier;
import ddf.minim.ugens.Oscil;
import ddf.minim.ugens.Summer;
import ddf.minim.ugens.Waves;

/**
 * @author acordier
 *
 */
public class Nsc implements MidiInstrument {

	Minim minim;
	AudioOutput out;
	Oscil vco1;
	Oscil vco2;
	Gain vco1Gain, vco2Gain;
	Oscil lfo;
	MoogFilter filter;
	Summer sum;
	MidiReceiver receiver;
	BitCrush bitCrush;
	PApplet sketch;
	AdsrX adsr;
	Summer adsrSum;
	Damp damp;
	Delay delay;
	Gain globalGain;
	SignalSplitter splitter;
	Summer summer;
	Multiplier multiplier;
	/* transpose values */
	int vco1Oct;
	int vco2Oct;
	

	/** default patching */
	public Nsc(final PApplet sketch) {
		this.sketch = sketch;
		/* Max amp, Attack, Decay, Sustain, Release */;
		vco1 = new Oscil(440.F, 0.5F, Waves.SINE);
		vco2 = new Oscil(440.F, 0.5F, Waves.TRIANGLE);
		vco1Oct = 1;
		vco2Oct = 1;
		adsr = new AdsrX(0.75F, 0.00001F, 0.125F, 0.25F, 0.125F);
		filter = new MoogFilter(2200.F, 0.5F);
		sum = new Summer();
		adsrSum = new Summer();
		minim = new Minim(sketch);
		out = minim.getLineOut();
		lfo = new Oscil(0.F, 2200.F, Waves.TRIANGLE);
		lfo.offset.setLastValue(2200.F); // i don't know what i'm doing here
		lfo.patch(filter.frequency);
		filter.frequency.setLastValue(20000);
		vco1Gain = new Gain();
		vco1.patch(vco1Gain);
		vco1Gain.patch(sum);
		vco2Gain = new Gain();
		vco2.patch(vco2Gain);
		vco2Gain.patch(sum);
		multiplier = new Multiplier(6.F);
		sum.patch(multiplier).patch(adsr);
		adsr.patch(adsrSum);
		adsrSum.patch(filter);
		delay = new Delay(0.005F, 0.05F, true, true);
		bitCrush = new BitCrush(16.F, 2048.F); // 2048 min, 
		bitCrush.bitRate.setLastValue(16384.F);
		globalGain = new Gain();
		globalGain.gain.setLastValue(1.75F);
		Bypass<Delay> delayBypass = new Bypass<Delay>(delay);
		filter.patch(delayBypass);
		filter.type = MoogFilter.Type.LP;
		delayBypass.patch(bitCrush);
		bitCrush.patch(globalGain);
		globalGain.patch(out);
		receiver = new MidiReceiver(this);
		
	}

	@Override
	public void noteOn(float dur) {
		//adsr.patch(adsrSum);
		adsr.noteOn();
	}

	@Override
	public void noteOff() {
		adsr.noteOff();
		//adsr.unpatchAfterRelease(adsrSum);
	}

	@Override
	public void noteOn(int note, int velocity) {
		setFrequency(Frequency.ofMidiNote(note));
		setAmplitude(velocity / 254.F);
		noteOn(0.5F);
	}

	@Override
	public void controlChange(int key, int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public AudioOutput getAudioOut() {
		return out;
	}

	/**
	 * this wrapper method is responsible of setting the global amplitude value
	 * and applying any transformation induced by local settings
	 */
	public void setAmplitude(float amp) {
		vco1.setAmplitude(amp);
		vco2.setAmplitude(amp);
	}

	/**
	 * this wrapper method is responsible of setting the global frequency value
	 * and applying any transformation induced by local settings
	 */
	public void setFrequency(Frequency frequency) {
		vco1.setFrequency(Frequency.ofMidiNote(frequency.asMidiNote() + vco1Oct * 12));
		vco2.setFrequency(Frequency.ofMidiNote(frequency.asMidiNote() + vco2Oct * 12));
	}

	@Override
	public void setAudioOut(AudioOutput out) {
		this.out = out;
	}

	@Override
	public MidiReceiver getReceiver() {
		return receiver;
	}

	/** close resources */
	public void stop() {
		minim.stop();
	}

	public MoogFilter getFilter() {
		return filter;
	}
	
	public Oscil getLFO(){
		return lfo;
	}
	
	public Oscil getVCO1(){
		return vco1;
	}
	
	public Oscil getVCO2(){
		return vco2;
	}

	public AdsrX getAdsr() {
		return adsr;
	}
	
	public Minim getMinim(){
		return minim;
	}

	public Gain getGain() {
		return globalGain;
	}

	public Delay getDelay() {
		return delay;
	}

	public BitCrush getBitCrush() {
		return bitCrush;
	}

	public void setBitCrush(BitCrush bitCrush) {
		this.bitCrush = bitCrush;
	}

	public Gain getVco1Gain() {
		return vco1Gain;
	}

	public void setVco1Gain(Gain vco1Gain) {
		this.vco1Gain = vco1Gain;
	}

	public Gain getVco2Gain() {
		return vco2Gain;
	}

	public void setVco2Gain(Gain vco2Gain) {
		this.vco2Gain = vco2Gain;
	}

	public int getVco1Oct() {
		return vco1Oct;
	}

	public void setVco1Oct(int vco1Oct) {
		this.vco1Oct = vco1Oct;
	}

	public int getVco2Oct() {
		return vco2Oct;
	}

	public void setVco2Oct(int vco2Oct) {
		this.vco2Oct = vco2Oct;
	}
	
	
	
}
