package Asteroids;

import java.awt.*;

public class Main {

    /*
            TODO:
            -When checking for collisions between asteroids and bullets, you'll need a for-loop nested in another for-loop.
            -Asteroids
                -Wrap-around logic for when asteroid reaches edge of screen
                -Asteroids get smaller the more you shoot them
                -Start off with 5 asteroids on screen (get stored in index 0-4)
                    -For ex, let's say you shoot the asteroid at index 2.
                        -Shrink the asteroid at index 2. Add another asteroid at index 5. Both asteroids are Medium size.
                        -You shoot the medium asteroid at index 2.
                            -Shrink the asteroid at index 2. Add another asteroid at index 6. Both asteroids are Small.
                                -You shoot the small asteroid at index 6.
                                -Whenever shoot a small asteroid, it disappears.
                                    -Ideas on how to indicate asteroid has disappeared: changing value to null (alternatively, you could flip a boolean flag to have it no longer be visible).
                                        -Let's say you shoot the Large asteroid at index 1.
                                            -This should give you two new asteroids that are medium in size. For the asteroid at index 1, just shrink it. For the other asteroid, add it at index 7.
                    */

    public static void main(String[] args){
        Spaceship spaceship = new Spaceship();
        Asteroid[] asteroids = new Asteroid[100];
        for(int i=0; i<asteroids.length; i++){
            if(i<5){
                asteroids[i] = new Asteroid(Math.random()*100, Math.random()*100, Math.random()*200-100, Math.random()*200-100, 4);
            }
            else{
                asteroids[i] = null;
            }
        }


        StdDraw.setScale(0, 100);
        StdDraw.enableDoubleBuffering(); //Calling this method stops things from being drawn immediately after a draw method is called. This allows you to call many different draw methods without anything being drawn on the screen; when you call StdDraw.show(), everything will be drawn at once.
        double timeElapsed = 0.017; //0.017 seconds-- this is how long each frame of our animation appears.
        while(true){

            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledSquare(50, 50, 50);

            Bullet[] bullets = spaceship.getBullets();

            //Spaceship
            spaceship.calculate(timeElapsed);

            //Bullets
            for(Bullet bullet : bullets) {
                if (bullet != null) {
                    bullet.calculate(timeElapsed);
                    bullet.draw();
                    for (Asteroid asteroid : asteroids) {
                        if (asteroid != null) {
                            if (didCircleCircleCollide(bullet.getxPos(), bullet.getyPos(), bullet.getRadius(), asteroid.getxPos(), asteroid.getyPos(), asteroid.getRadius())) {
                                asteroid.die();
                            }
                        }
                    }
                }
            }




            //Asteroids
            spaceship.draw();
            for(Asteroid asteroid : asteroids) {
                if (asteroid != null) {
                    asteroid.calculate(timeElapsed);
                    if (didCircleCircleCollide(spaceship.getxPos(), spaceship.getyPos(), spaceship.getScaledWidth(), asteroid.getxPos(), asteroid.getyPos(), asteroid.getRadius())) {
                        spaceship.die(spaceship);
                    }


                asteroid.draw();
            }
            }





            StdDraw.show(); //Because we have called StdDraw.enableDoubleBuffering(), everything that you draw up until this point will be loaded into java's memory but not actually drawn. Calling StdDraw.draw() then draws everything at once that is loaded into java's memory.
            StdDraw.pause((int)(timeElapsed*1000)); //You must pass to the pause method the number of milliseconds to pause for; so we multiply by 1000 because our timeElapsed variable is in seconds, not milliseconds.
            StdDraw.clear(); //This clears everything drawn on the screen. You must redraw the image you wish to display for each frame of an animation
        }
    }


    public static boolean didCircleCircleCollide(double x1, double y1, double r1, double x2, double y2, double r2){
        double distance = Math.sqrt( Math.pow((x1-x2), 2)  + Math.pow((y1-y2),2) );
        if(distance >= r1+r2){
            return false;
        }else{
            return true;
        }
    }



}
