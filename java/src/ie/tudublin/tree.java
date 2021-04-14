package ie.tudublin;

import processing.core.PApplet;

public class tree extends MyVisual {

    MyVisual mv;
     
    float theta; 

    public tree(MyVisual mv)
    {
        this.mv = mv; 
    }

    @Override
    public void setup() {
        size(640, 360, P2D);
      }

    void render() {

        mv.background(0);
        mv.frameRate(30);
        for(int i = 0 ; i < mv.getAudioBuffer().size() ; i ++)
        {
            float c = PApplet.map(mv.getSmoothedAmplitude() * 7, 0, 1, 0, 255);
            mv.stroke(c, 255, 255);
            // Let's pick an angle 0 to 90 degrees based on the mouse position
            float a = (c / (float) mv.width) * 90f;
            // Convert it to radians
            theta = PApplet.radians(a);
        }
        // Start the tree from the bottom of the screen
        mv.translate(mv.width/2,mv.height);
        // Draw a line 120 pixels
        mv.line(0,0,0,-100);
        // Move to the end of that linemv.
        mv.translate(0,-100);
        // Start the recursive branching!
        branch(300);
    }

    void branch(float h) {
        // Each branch will be 2/3rds the size of the previous one
        h *= 0.66;
        
        // All recursive functions must have an exit condition!!!!
        // Here, ours is when the length of the branch is 2 pixels or less
        if (h > 2) {
          mv.pushMatrix();    // Save the current state of transformation (i.e. where are we now)
          mv.rotate(theta);   // Rotate by theta
          mv.line(0, 0, 0, -h);  // Draw the branch
          mv.translate(0, -h); // Move to the end of the branch
          branch(h);       // Ok, now call myself to draw two new branches!!
          mv.popMatrix();     // Whenever we get back here, we "pop" in order to restore the previous matrix state
          
          // Repeat the same thing, only branch off to the "left" this time!
          mv.pushMatrix();
          mv.rotate(-theta);
          mv.line(0, 0, 0, -h);
          mv.translate(0, -h);
          branch(h);
          mv.popMatrix();
        }
    }
}
