package Asteroids;

import java.awt.event.KeyEvent;

public class Spaceship {

    private double degrees;
    private double xRatio;
    private double yRatio;

    private double xPos;
    private double yPos;
    private double xVel;
    private double yVel;
    private double xAccel;
    private double yAccel;
    private double accel;
    private double scaledHeight;
    private double scaledWidth;
    private Bullet[] bullets = new Bullet[100];
    private int counter;

    private String image = "Checkers/Demo.png";


    public Spaceship() {
        degrees = 270;

        xPos = 50;
        yPos = 50;
        xVel = 0;
        yVel = 0;
        accel = 0;
        scaledHeight = 10;
        scaledWidth = 10;
        counter = 0;
    }

    public void calculate(double timeElapsed) {
        xRatio = Math.sin(Math.toRadians(degrees)) * (-1);
        yRatio = Math.cos(Math.toRadians(degrees));

        if (xPos > 100 + scaledWidth) {
            xPos = 0 - scaledWidth;
        }
        if (yPos > 100 + scaledHeight) {
            yPos = 0 - scaledHeight;
        }

        //Rotation Calculations
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
            degrees = degrees + 5;
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
            degrees = degrees - 5;
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
            image = "Asteroids/image.png";
            Bullet bullet = new Bullet(this.xPos, this.yPos, this.xRatio, this.yRatio);
            bullets[counter] = bullet;
            counter++;
            if (counter >= 100) {
                counter = 0;
            }

        }
        if (!StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
            image = "Checkers/Demo.png";
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_R)) {
            xPos = 50;
            yPos = 50;
            xVel = 0;
            yVel = 0;
            xAccel = 0;
            yAccel = 0;
            degrees = 270;
        }


        //Rocket Boost Calculations
        if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
            accel = 20;

        } else {

            accel = 0; //stops rocket boost
            //This gradually slows down the velocity when the up arrow isn't pressed
            xVel = xVel * 0.95;
            yVel = yVel * 0.95;

        }

        //xPos = xPos + vel*timeElapsed + accel*(timeelapsed)^2
        xVel = xVel + (xRatio * accel) * timeElapsed;
        xPos = xPos + xVel * timeElapsed;
        yVel = yVel + (yRatio * accel) * timeElapsed;
        yPos = yPos + yVel * timeElapsed;

        System.out.println(yVel);
        System.out.println("accel: " + accel);

        if (yPos - scaledHeight * 0.5 > 100) {
            yPos = 1;
        } else if (yPos + scaledHeight * 0.5 < 0) {
            yPos = 96;
        }

        if (xPos + scaledWidth * .5 < 0) {
            xPos = 99;
        } else if (xPos - scaledWidth * .5 > 100) {
            xPos = 1;
        }


    }

    public void draw() {
        //StdDraw.setPenColor(StdDraw.YELLOW); testing
        //StdDraw.line(50, 50, 50+xRatio*100, 50+yRatio*10);
        StdDraw.picture(xPos, yPos, image, scaledWidth, scaledHeight, degrees);
    }

    public void resetSpaceship() {
        xPos = 50;
        yPos = 50;
        xVel = 0;
        yVel = 0;
        xAccel = 0;
        yAccel = 0;
        degrees = 270;
    }

    public void setStateDestroyed(Spaceship spaceship) {
        StdDraw.text(50, 50, "Game Over");
        xPos = 50;
        yPos = 50;
    }

    public double getxRatio() {
        return xRatio;
    }

    public double getyRatio() {
        return yRatio;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public double getScaledHeight() {
        return scaledHeight;
    }

    public double getScaledWidth() {
        return scaledWidth;
    }

    public Bullet[] getBullets() {
        return bullets;
    }
}