package
exec.SpaceShadows;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioFormat.Encoding;

/**
 * Cette classe permet de jouer un fichier audio,
 * de modifier le pan(la balance), et le gain(le volume)
 * Elle permet egalement de tester si un fichier correspond au format demande
 * @author twins
 *
 */
public class Audio2 {

	private Clip clip = null;
	
	/**
	 * Joue un fichier son
	 * @param f fichier a jouer
	 */
	public void play(File f) throws Exception{
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(f);//recuperation d'un stream de type audo sur le fichier
		AudioFormat audioFormat = audioStream.getFormat();//recuperation du format de son
		Encoding enc = audioFormat.getEncoding();
		//teste du format et conversion afin qu'il puisse etre lu par la carte son
        if (enc == AudioFormat.Encoding.ULAW || enc == AudioFormat.Encoding.ALAW) {
            //on met a jour le format
            convertFormat(audioFormat, audioStream);
        }
        //recuperation du son que l'on va stoquer dans un oblet de type clip
        DataLine.Info info = new DataLine.Info(
        		Clip.class, audioStream.getFormat(),
                ((int) audioStream.getFrameLength() * audioFormat.getFrameSize()));
        //recuperation d'une instance de type Clip
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(audioStream);//ouverture du clip celon le stream audio
        clip.start();//on joue le son

	}
	public void load(File f) throws Exception{
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(f);//recuperation d'un stream de type audo sur le fichier
		AudioFormat audioFormat = audioStream.getFormat();//recuperation du format de son
		Encoding enc = audioFormat.getEncoding();
		//teste du format et conversion afin qu'il puisse etre lu par la carte son
        if (enc == AudioFormat.Encoding.ULAW || enc == AudioFormat.Encoding.ALAW) {
            //on met a jour le format
            convertFormat(audioFormat, audioStream);
        }
        //recuperation du son que l'on va stoquer dans un oblet de type clip
        DataLine.Info info = new DataLine.Info(
        		Clip.class, audioStream.getFormat(),
                ((int) audioStream.getFrameLength() * audioFormat.getFrameSize()));
        //recuperation d'une instance de type Clip
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(audioStream);//ouverture du clip celon le stream audio

	}
	
	public void load(InputStream f) throws Exception{
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(f);//recuperation d'un stream de type audo sur le fichier
		AudioFormat audioFormat = audioStream.getFormat();//recuperation du format de son
		Encoding enc = audioFormat.getEncoding();
		//teste du format et conversion afin qu'il puisse etre lu par la carte son
        if (enc == AudioFormat.Encoding.ULAW || enc == AudioFormat.Encoding.ALAW) {
            //on met a jour le format
            convertFormat(audioFormat, audioStream);
        }
        //recuperation du son que l'on va stoquer dans un oblet de type clip
        DataLine.Info info = new DataLine.Info(
        		Clip.class, audioStream.getFormat(),
                ((int) audioStream.getFrameLength() * audioFormat.getFrameSize()));
        //recuperation d'une instance de type Clip
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(audioStream);//ouverture du clip celon le stream audio

	}
	public void play() throws Exception{
		clip.start();
	}
	public void playloop() throws Exception{
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	
	
	/**
	 * Cast du format alaw ou ulaw en pcm
	 * @param audioFormat le format source a convertir
	 * @param audioStream le stream a convertir
	 */
	private  void convertFormat(AudioFormat audioFormat, AudioInputStream audioStream ){
    	AudioFormat tmp = new AudioFormat(//conversion du format alaw ou ulaw en pcm
                AudioFormat.Encoding.PCM_SIGNED,
       
                audioFormat.getSampleRate(),
                audioFormat.getSampleSizeInBits() * 2,
                audioFormat.getChannels(),
                audioFormat.getFrameSize() * 2, audioFormat.getFrameRate(), true);
        //on converti le format
        audioStream = AudioSystem.getAudioInputStream(tmp, audioStream);
        //on met a jour le format
        audioFormat =  tmp;
	}
	
	/**
	 * Arrete le fichier
	 */
	public void stop(){
		if (clip != null){
			clip.stop();//on arrete le clip
			clip.close();//on libere les ressources
		}
	}
	public void pause(){
		if (clip != null){
			clip.stop();
		}
	}
	
	/**
	 * Deplace l'offset de lecture
	 * @param seek le nouvel offset
	 */
	public synchronized void setMicrosecondPosition(long seek){
		if(clip != null){
			clip.setMicrosecondPosition(
					seek < 0 ? 0 :
						(seek > getMicrosecondLength() ?
								getMicrosecondLength() : seek));
		}
	}
	
	/**
	 * Recuperation de la longueur max du media
	 * @return retourne la longueur en milliseconde
	 */
	public long getMicrosecondLength(){
		if(clip == null) return 0;
		return clip.getMicrosecondLength();
	}
	
	/**
	 * Recuperation de la position courrante dans le media
	 * @return retourne la position en milliseconde
	 */
	public long getMicrosecondPosition(){
		if(clip == null)return 0;
		return clip.getMicrosecondPosition();
	}
	
	/**
	 * Modification du pan (balance) d'un fichier son
	 * @param value la nouvelle valeur du pan
	 */
	public void setPan(int value) throws Exception{
        if (clip != null) {
            FloatControl panControlClip =  (FloatControl) clip.getControl(FloatControl.Type.PAN);
            panControlClip.setValue(value/100.0f);//on modifie le pan du son
        }
    }
	
	/**
	 * Modification du gain (volume) d'un fichier son
	 * @param value la nouvelle valeur du gain
	 */
	public void setGain(int value) throws Exception {
        if (clip != null){
        	double val = value / 100.0;
            FloatControl gainControlClip = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float)(Math.log(val==0.0?0.0001:val)/Math.log(10.0)*20.0);//on ajuste le volume en DB
            gainControlClip.setValue(dB);
        }
    }
	
	/**
	 * Test si un fichier est au format pcm, alaw ou ulaw
	 * @param f le fichier audio a verifier
	 * @return retourne true si il est valide
	 */
	public boolean isSupportedFormat(File f){
		boolean error = false;
		if(!isPcm(f)){//test si le format du fichier est pcm
			error = true;
			if(!isAlaw(f)){//test si le format du fichier est alaw
				error = true;
				if(!isUlaw(f)){//test si le format du fichier est ulaw
					error = true;
				}else error = false;
			}else error = false;
		}else error = false;
		return error;
	}
	/**
	 * Test si un fichier est au format ulaw ou non
	 * @param f le fichier audio a verifier
	 * @return retourne true si il est valide
	 */
	private boolean isUlaw(File f) {
		try {
			Encoding enc = AudioSystem.getAudioFileFormat(f).getFormat().getEncoding();
			if (enc == AudioFormat.Encoding.ULAW) return true;
			else return false;
		} catch (UnsupportedAudioFileException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Test si un fichier est au format alaw ou non
	 * @param f le fichier audio a verifier
	 * @return retourne true si il est valide
	 */
	private boolean isAlaw(File f) {
		try {
			Encoding enc = AudioSystem.getAudioFileFormat(f).getFormat().getEncoding();
			if (enc == AudioFormat.Encoding.ALAW) return true;
			else return false;
		} catch (UnsupportedAudioFileException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Test si un fichier est au format pcm ou non
	 * @param f le fichier audio a verifier
	 * @return retourne true si il est valide
	 */
	private boolean isPcm(File f) {
		if (f == null)
			return false;
		try {
			Encoding enc = AudioSystem.getAudioFileFormat(f).getFormat().getEncoding();
			if (enc == AudioFormat.Encoding.PCM_SIGNED || enc == AudioFormat.Encoding.PCM_UNSIGNED)
				return true;
			else return false;
		} catch (UnsupportedAudioFileException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}
}
