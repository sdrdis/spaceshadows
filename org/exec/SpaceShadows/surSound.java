package exec.SpaceShadows;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class surSound extends Thread
{
	String fichier;
	double volume;
	public surSound(String fichier, double volume)
	{
		try
		{
		this.fichier = fichier;
		this.volume = volume;
		this.start();
		}
		catch(Exception e)
		{
			System.out.println("ERR?");
		}
	}
	public void run()
	{
			try
			{
			File file = new File (fichier);
			Audio2 audio = new Audio2();
			audio.play(file);
			audio.setGain((int) Math.round(volume * 100));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

	  

		/*
		
		sound sonscharges = new sound(son);
		InputStream sons = new ByteArrayInputStream(sonscharges.getSamples());
		sonscharges.play(sons, gain);
		*/
	}
	public void quit()
	{
		GAME_ES.println("H_ QUITTE");
	}
}
