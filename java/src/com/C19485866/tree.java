package com.C19485866;

import processing.core.PApplet;

public class tree extends MyVisual {

    MyVisual mv;

    float theta;

    public tree(MyVisual mv) {
        this.mv = mv;
    }

    @Override
    public void setup() {
        size(640, 360, P2D);
    }

    void render() {
        mv.camera();
        mv.background(0);
        mv.frameRate(30);
        mv.colorMode(HSB);
        mv.fill(255);
        for (int i = 0; i < mv.getAudioBuffer().size(); i++) {
            float c = PApplet.map(mv.getSmoothedAmplitude() * 10, 0, 1, 0, 255);
            mv.stroke(c, 255, 255);

            float a = (1000 / (float) mv.width) * 30f;

            theta = PApplet.radians(a);
        }

        mv.translate(mv.width / 2, mv.height);

        mv.line(0, 0, 0, -120);

        mv.rect(10, 10, 10, 10);

        mv.line(10, 10, 10, -120);

        mv.line(-10, -10, -10, -120);

        mv.line(20, 20, 20, -120);

        mv.translate(0, -120);

        branch(300);
    }

    void branch(float h) {
        float c = PApplet.map(mv.getSmoothedAmplitude() * 10, 0, 1, 0, 255);
        mv.fill(c, 255, 255);

        h *= 0.67;

        if (h > 20) {
            mv.pushMatrix();
            mv.rotate(theta);
            mv.line(0, 0, 0, -h);
            mv.translate(0, -h);
            branch(h);
            mv.popMatrix();

            mv.pushMatrix();
            mv.rotate(theta);
            mv.line(10, 10, 10, -h);
            mv.translate(10, -h);
            branch(h);
            mv.popMatrix();

            mv.pushMatrix();
            mv.rotate(-theta);
            mv.line(0, 0, 0, -h);
            mv.translate(0, -h);
            branch(h);
            mv.popMatrix();

            mv.pushMatrix();
            mv.rotate(-theta);
            mv.line(-10, -10, -10, -h);
            mv.translate(-10, -h);
            branch(h);
            mv.popMatrix();
        }
    }
}
