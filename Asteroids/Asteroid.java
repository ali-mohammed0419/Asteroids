package Asteroids;

public class Asteroid {
    private double xPos;
    private double yPos;
    private double xVel;
    private double yVel;
    private double radius;
    private boolean isDead;

    // Asteroid asteroid = new Asteroid(1,2,3,4,6)
    // Asteroid asteroid = new Asteroid() - more random

    public Asteroid(double xPos, double yPos, double xVel, double yVel, double radius){
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVel = xVel;
        this.yVel = yVel;
        this.radius = radius;
        this.isDead = false;
    }

    public void calculate(double timeElapsed){
        xPos = xPos + xVel*timeElapsed;
        yPos = yPos + yVel*timeElapsed;
    }

    public void draw(){
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledCircle(xPos, yPos, radius);

        if(xPos > 100 + radius){
            xPos = 0 - radius;
        }
        if(yPos > 100 + radius){
            yPos = 0 - radius;
        }
    }

    public void die(){
        xPos = 0;
        yPos = 0;
        isDead = true;
    }


    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public double getRadius() {
        return radius;
    }

    public boolean getisDead(){
        return isDead;
    }

}