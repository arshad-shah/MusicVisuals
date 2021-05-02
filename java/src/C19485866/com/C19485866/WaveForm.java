package C19485866.com.C19485866;

import processing.core.*;

// This is an example of a visual that renders the waveform
public class WaveForm {
    MyVisual mv;
    float cy = 0;
    float[] lerpedBuffer;

    public WaveForm(MyVisual mv) {
        this.mv = mv;
        cy = this.mv.height / 2;
    }

    public void render() {
        lerpedBuffer = new float[mv.getAudioBuffer().size()];
        mv.colorMode(PApplet.HSB);
        mv.camera();
        for (int i = 0; i < mv.getAudioBuffer().size(); i++) {
            float buffer = mv.getAudioBuffer().size();
            float c = PApplet.map(i, 0, buffer, 0, 255);
            mv.stroke(c, 255, 255);
            lerpedBuffer[i] = PApplet.lerp(lerpedBuffer[i], mv.getAudioBuffer().get(i), 0.1f);
            mv.line(0, i, lerpedBuffer[i] * cy * 4, i);
            mv.line(mv.width, i, mv.width - (lerpedBuffer[i] * cy * 10), i);
            mv.line(i, 0, i, lerpedBuffer[i] * cy * 4);
            mv.line(i, mv.height, i, mv.height - (lerpedBuffer[i] * cy * 4));
            mv.line(i, cy - lerpedBuffer[i] * cy * 10, cy + lerpedBuffer[i] * cy * 10, i);
        }
    }
}