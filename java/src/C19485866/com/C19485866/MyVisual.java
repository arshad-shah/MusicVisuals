package C19485866.com.C19485866;

public class MyVisual extends Visual {
    cube cu;
    rotatebands rb;
    spiral sp;
    tree tr;

    int which = 0;
    boolean twocubes = false;

    public void settings() {
        size(1024, 800, P3D);

        // Use this to make fullscreen
        // fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        // fullScreen(P3D, SPAN);
    }

    public void setup() {
        startMinim();

        // Call loadAudio to load an audio file to process
        loadAudio("heroplanet.mp3");

        // Call this instead to read audio from the microphone
        // startListening();

        cu = new cube(this);
        rb = new rotatebands(this);
        sp = new spiral(this);
        tr = new tree(this);
    }

    public void keyPressed() {
        if (keyCode >= '0' && keyCode < '5') {
            which = keyCode - '0';
        }
        if (keyCode == ' ') {
            if (getAudioPlayer().isPlaying()) {
                getAudioPlayer().pause();
            } else {
                getAudioPlayer().rewind();
                getAudioPlayer().play();
            }
        }
    }

    public void draw() {
        background(0);
        try {
            // Call this if you want to use FFT data
            calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }
        // Call this is you want to use frequency bands
        calculateFrequencyBands();

        // Call this is you want to get the average amplitude
        calculateAverageAmplitude();
        int highestBand = 0;
        for (int i = 0; i < fft.specSize(); i++) {
            if (fft.getBand(i) > fft.getBand(highestBand)) {
                highestBand = i;
            }
        }

        // float freq = fft.indexToFreq(highestBand);

        // System.out.print("\nFrequency: " + freq);
        switch (which) {
            case 1:
                rb.render();
                break;

            case 2:
                sp.render();
                break;

            case 3:
                tr.render();
                break;

            case 4:
                cu.render();
                break;
            default:
                rb.render();
                break;
        }
    }

}// end of class
