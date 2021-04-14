package ie.tudublin;

import processing.core.PApplet;

public class rotatebands {
    MyVisual mv;

    public rotatebands(MyVisual mv)
    {
        this.mv = mv; 
    }

    float radius = 200;

    float smoothedBoxSize = 0;

    float rot = 0;
    // all render
    public void render()
    {
        mv.calculateAverageAmplitude();
        try
        {
            mv.calculateFFT();
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        mv.calculateFrequencyBands();
        mv.background(0);
        mv.stroke(255);
        mv.strokeWeight(3);
        mv.lights();
        mv.colorMode(PApplet.HSB);
        mv.stroke(PApplet.map(mv.getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        mv.camera(0, -500, 500, 0, 0, 0, 0, 2, 0);
        // mv.translate(0, 0, -250);

        rot += mv.getAmplitude() / 8.0f;

        mv.rotateY(rot);
        float[] bands = mv.getSmoothedBands();
        for(int i = 0 ; i < bands.length ; i ++)
        {
            mv.fill(PApplet.map(i,mv.getBands().length,0,  255, 0), 255, 255);

            float theta = PApplet.map(i, 0, bands.length, 0, PApplet.TWO_PI);

            mv.stroke(PApplet.map(i, 0, bands.length, 0, 255), 255, 255);
            float x = PApplet.sin(theta) * radius;
            float z = PApplet.cos(theta) * radius;
            float h = bands[i];
            mv.pushMatrix();
            mv.translate(x, - h / 2 , z);
            mv.rotateY(theta);
            mv.box(50, h, 50);
            mv.popMatrix();
        }

    }
    float angle = 0;
}
