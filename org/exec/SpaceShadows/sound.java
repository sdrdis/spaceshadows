package exec.SpaceShadows;


import java.io.*;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.sampled.*;
public class sound {
	
	private AudioFormat format;
	private byte[] samples;
	//private Sequencer sequencer;
	public sound(String filename){
		try{
			AudioInputStream stream = AudioSystem.getAudioInputStream(new File(filename));
			format = stream.getFormat();
			samples = getSamples(stream);
		}
		catch (UnsupportedAudioFileException e){
			e.printStackTrace();
	}
	catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public byte[] getSamples(){
		return samples;
	}
	
	public byte[] getSamples(AudioInputStream stream){
		int length = (int)(stream.getFrameLength() * format.getFrameSize());
		byte[] samples = new byte[length];
		DataInputStream in = new DataInputStream(stream);
		try{
			in.readFully(samples);
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return samples;
	}
	
	public void play(InputStream source, double gain){
		// 100 ms buffer for real time change to the sound stream
		/*
	    if (sequencer instanceof Synthesizer) {
        	GAME_ES.println("H_DEDANS");
            Synthesizer synthesizer = (Synthesizer)sequencer;
            MidiChannel[] channels = synthesizer.getChannels();
        
            // gain is a value between 0 and 1 (loudest)
            for (int i=0; i<channels.length; i++) {
                channels[i].controlChange(7, (int)(gain * 127.0));
            }
       // }
		*/
		
		/*
		FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
	    double gain = .5D;    // number between 0 and 1 (loudest)
	    float dB = (float)(Math.log(gain)/Math.log(10.0)*20.0);
	    gainControl.setValue(dB);
*/
		
		int bufferSize = format.getFrameSize() * Math.round(format.getSampleRate() / 10);
		byte[] buffer = new byte[bufferSize];
		SourceDataLine line;
		try{
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
			line = (SourceDataLine)AudioSystem.getLine(info);
			line.open(format, bufferSize);
		}
		catch (LineUnavailableException e){
			e.printStackTrace();
			return;
		}
		line.start();
		try{
			int numBytesRead = 0;
			while (numBytesRead != -1){
				numBytesRead = source.read(buffer, 0, buffer.length);
				if (numBytesRead != -1)
					line.write(buffer, 0, numBytesRead);
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
		line.drain();
		line.close();
	}
	


}
