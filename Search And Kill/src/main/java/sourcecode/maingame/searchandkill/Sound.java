package sourcecode.maingame.searchandkill;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound{
    static Clip clip;
    static URL[] soundURL = new URL[10];

    public Sound() {

        soundURL[0] = getClass().getResource("/Sound/Lobby.wav");
        /*Lobby music copyright :  Funk Game Loop by Kevin MacLeod http://incompetech.com
        Creative Commons — Attribution 4.0 International — CC BY 4.0
        Free Download / Stream: https://bit.ly/funk-game-loop
        Music promoted by Audio Library https://youtu.be/a7nnob_ZpZI*/

        soundURL[1] = getClass().getResource("/Sound/Click.wav");

        soundURL[2] = getClass().getResource("/Sound/Error.wav");

        soundURL[3] = getClass().getResource("/Sound/Waiting.wav");
        /*Waiting room music copyright: Noise by Alex-Productions | https://onsound.eu/
        Music promoted by https://www.chosic.com/free-music/all/
        Creative Commons CC BY 3.0
        https://creativecommons.org/licenses/by/3.0/  */

        soundURL[4] = getClass().getResource("/Sound/Running.wav");

        soundURL[5] = getClass().getResource("/Sound/Gunsound.wav");

        if (soundURL[0] == null) {
            throw new IllegalArgumentException("Sound file no 0 does not found ");
        }

        if (soundURL[1] == null) {
            throw new IllegalArgumentException("Sound file no 1 does not found ");
        }

        if (soundURL[2] == null) {
            throw new IllegalArgumentException("Sound file no 2 does not found ");
        }

        if (soundURL[3] == null) {
            throw new IllegalArgumentException("Sound file no 3 does not found ");
        }

        if (soundURL[4] == null) {
            throw new IllegalArgumentException("Sound file no 4 does not found ");
        }

        if (soundURL[5] == null) {
            throw new IllegalArgumentException("Sound file no 5 does not found ");
        }

    }
    public static void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void play(){

        clip.start();
    }
    public static void loop(){

        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }
    public static void stop() {

        clip.stop();
    }


    public static void playMusic(int i) {
        Sound.setFile(i);
        Sound.play();
        Sound.loop();

    }

    public static void stopMusic(){
        Sound.stop();
    }

    public static void playSoundEffect(int i){
        Sound.setFile(i);
        Sound.play();
    }

}

