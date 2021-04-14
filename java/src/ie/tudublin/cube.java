package ie.tudublin;

import processing.core.PApplet;
import processing.core.PConstants;

// This is an example of a visual that uses the audio bands
public class cube
{
    MyVisual mv;

    public cube(MyVisual mv)
    {
        this.mv = mv; 
    }
    float smoothedBoxSize = 0;

    // all render
    public void render()
    {
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
            mv.rotateY(angle);
            mv.fill(PApplet.map(mv.getSmoothedAmplitude(), 0, 1, 0, 255),255,255);
            mv.sphere(smoothedBoxSize);   
            angle += 0.01f;
            mv.popMatrix();

            //stars
            int sides = (1000 / 50);
            for(int i = 1 ; i <= sides ; i ++)
            {
                mv.pushMatrix();
                star(mv.random(1,100), mv.random(1,100), 20, 50, 5);
                mv.translate(mv.random(1,100), mv.random(1,100)); 
                mv.popMatrix();
            }
    }
    float angle = 0;


    void star(float x, float y, float radius1, float radius2, int npoints) {
        float angle = PApplet.TWO_PI / npoints;
        float halfAngle = (float) (angle/2.0);
        mv.beginShape();
        for (float a = 0; a < PApplet.TWO_PI; a += angle) {
          float sx = x + PApplet.cos(a) * radius2;
          float sy = y + PApplet.sin(a) * radius2;
          mv.vertex(sx, sy);
          sx = x + PApplet.cos(a+halfAngle) * radius1;
          sy = y + PApplet.sin(a+halfAngle) * radius1;
          mv.vertex(sx, sy);
        }
        mv.endShape(PApplet.CLOSE);
      }
}
