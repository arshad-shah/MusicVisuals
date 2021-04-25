package com.C19485866;

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
        mv.camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
        mv.translate(0, 0, -250);

        float boxSize = 20 + (mv.getAmplitude() * 200);
        smoothedBoxSize = PApplet.lerp(smoothedBoxSize, boxSize, 0.1f);

        mv.pushMatrix();
        mv.translate(mv.width / 4, mv.height / 2, 0);
        mv.rotateY(angle);
        mv.rotateX(angle);
        mv.box(smoothedBoxSize);
        mv.popMatrix();

        mv.pushMatrix();
        mv.translate(mv.width * 0.75f, mv.height / 2, 0);
        mv.rotateY(angle);
        mv.rotateX(angle);
        mv.box(smoothedBoxSize);
        mv.popMatrix();
    }

    float angle = 0;

}