package C19485866.com.C19485866;

import processing.core.PApplet;

// This is an example of a visual that uses the audio bands
public class cube {
    MyVisual mv;

    public cube(MyVisual mv) {
        this.mv = mv;
    }

    float smoothedBoxSize = 0;

    // all render
    public void render() {
        mv.calculateAverageAmplitude();
        mv.background(0);
        mv.noFill();
        mv.lights();
        mv.colorMode(PApplet.HSB);
        mv.stroke(PApplet.map(mv.getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        mv.strokeWeight(3);
        mv.fill(PApplet.map(mv.getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        mv.camera();
        mv.camera(-200, -200, 100, 0, 0, -1, 0, 1, 0);
        mv.translate(0, 0, -250);

        float boxSize = 100 + (mv.getAmplitude() * 300);
        smoothedBoxSize = PApplet.lerp(smoothedBoxSize, boxSize, 0.1f);

        mv.pushMatrix();
        mv.translate(mv.width / 4, mv.height / 2, 0);
        mv.rotateY(angle);
        mv.sphere(smoothedBoxSize);
        mv.popMatrix();

        mv.pushMatrix();
        mv.translate(mv.width / 8, mv.height / 6, -250);
        mv.rotateY(angle);
        mv.sphere(smoothedBoxSize);
        mv.popMatrix();

        mv.pushMatrix();
        mv.translate(mv.width / 8, mv.height / 6, 450);
        mv.rotateY(angle);
        mv.sphere(smoothedBoxSize);
        mv.popMatrix();

        mv.pushMatrix();
        mv.translate(mv.width / 4, mv.height / 12, 150);
        mv.rotateY(angle);
        mv.sphere(smoothedBoxSize);
        mv.popMatrix();

        mv.pushMatrix();
        mv.translate(mv.width / 20, mv.height / 2, -150);
        mv.rotateY(angle);
        mv.sphere(smoothedBoxSize);
        mv.popMatrix();

        angle += 0.01f;
    }

    float angle = 0;

}