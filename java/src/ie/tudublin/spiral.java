package ie.tudublin;

import processing.core.PApplet;

public class spiral {

    MyVisual mv;

    public spiral(MyVisual mv)
    {
        this.mv = mv; 
    }
    float smoothedBoxSize = 0;

    // all render
    public void render()
    {
        mv.background(0);
        mv.stroke(255);
        mv.colorMode(PApplet.HSB);
        float average = 0;
        float sum = 0;
        
        float lerpedAverage = 0;

        // Calculate the average of the buffer
        for (int i = 0; i < mv.getAudioBuffer().size(); i ++)
        {
            sum += PApplet.abs(mv.getAudioBuffer().get(i));
        }
        average = sum / mv.getAudioBuffer().size();
        // Move lerpedAverage 10% closer to average every frame
        lerpedAverage = PApplet.lerp(lerpedAverage, average, 0.1f);

        float r = 1f;
        int numPoints = 3;
        float thetaInc = PApplet.TWO_PI / (float) numPoints;
        mv.strokeWeight(2);                
        float lastX = mv.width / 2, lastY = mv.height / 2;
        for(int i = 0 ; i < 10000 ; i ++)
        {
            float c = PApplet.map(i, 0, 300, 0, 255) % 255.0f;
            mv.stroke(c, 255, 255, 100);
            float theta = i * (thetaInc + lerpedAverage * 5);
            float x = mv.width / 2 + PApplet.sin(theta) * r;
            float y = mv.height / 2 - PApplet.cos(theta) * r;
            r += 0.5f + lerpedAverage;
            mv.line(lastX, lastY, x, y);
            lastX = x;
            lastY = y;
        }
    }
    
}
