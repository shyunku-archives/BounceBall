package ObjectUtil;

import Engine.PhysicsEngine;
import Global.Function;
import Managers.SoundManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Drawable {
    private double posX, posY;
    private double width, height;
    private double velX, velY;
    private double accelX, accelY;
    private boolean isAlive;
    private BufferedImage image = null;
    private boolean isCenteric;

    private boolean isPhysical;
    private boolean isCollidable;
    private double gravityDirection = PhysicsEngine.GENERAL_GRAVITY_DIRECTION;

    private Layer collisionLayer = null;
    private CollideShape collideShape;

    private long lastMovementUpdateTimestamp = 0;

    public Drawable(boolean isCenteric){
        this.isAlive = true;
        this.posX = 0;
        this.posY = 0;
        this.width = 0;
        this.height = 0;
        this.velX = 0;
        this.velY = 0;
        this.accelX = 0;
        this.accelY = 0;
        this.isCenteric = isCenteric;
        this.isPhysical = false;
        this.isCollidable = false;
    }

    public void activatePhysics(boolean fixed){
        this.isPhysical = !fixed;           //중력의 영향을 받음

    }

    public void activateCollidable(CollideShape collideShape){
        this.isCollidable = true;     //충돌의 영향을 받음
        this.collideShape = collideShape;
    }

    public void setImage(int key){
        BufferedImage image = Function.getImage(key);
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public void setCollisionLayer(Layer layer){
        this.collisionLayer = layer;
    }

    public void setPos(double x, double y){
        //iscenteric
        if(isCenteric) {
            this.posX = x;
            this.posY = y;
        }else{
            this.posX = x + width/2;
            this.posY = y + height/2;
        }
    }

    public void setSize(double width, double height){
        this.width = width;
        this.height = height;
    }

    public void move(){
        if(lastMovementUpdateTimestamp == 0){
            lastMovementUpdateTimestamp = System.nanoTime();
            return;
        }
        long elapsed = System.nanoTime() - lastMovementUpdateTimestamp;
        lastMovementUpdateTimestamp = System.nanoTime();
        double t = (double)elapsed/1000000000D;

        double accel = PhysicsEngine.GENERAL_GRAVITY;
        this.accelX = accel * Math.cos(gravityDirection);
        this.accelY = accel * Math.sin(gravityDirection);

        this.velX += t*this.accelX*PhysicsEngine.TIME_OFFSET;
        this.velY += t*this.accelY*PhysicsEngine.TIME_OFFSET;

        double nextX = this.posX + t*this.velX*PhysicsEngine.TIME_OFFSET;
        double nextY = this.posY + t*this.velY*PhysicsEngine.TIME_OFFSET;

        if(collisionLayer != null) {
            for (int i = 0; i < collisionLayer.size(); i++) {
                Drawable d = collisionLayer.get(i);
                NullableVector res = d.collide(this, nextX, nextY);
                if (res.isNull()) continue;
                else {
                    //bounce
                    System.out.println("bounce!");
                    Function.playSound(SoundManager.BOUNCE_SOUND);
                    double velocity = 60;
                    double angle = Math.atan2(velY, velX);
                    double symmetric = 2 * res.vect - angle;

                    this.velX = velocity * Math.cos(symmetric);
                    this.velY = velocity * -Math.sin(symmetric);
//
//                    this.posX += t*this.velX*PhysicsEngine.TIME_OFFSET;
//                    this.posY += t*this.velY*PhysicsEngine.TIME_OFFSET;

                    return;
                }
            }
        }

        this.posX = nextX;
        this.posY = nextY;
    }

    public void draw(Graphics2D g){
        if(this.isPhysical) move();

        Point point = Function.getConvertedPosition(posX, posY);
        if(this.image != null){
            g.drawImage(this.image, null, (int)(point.x - width/2), (int)(point.y - height/2));
        }
    }

    public NullableVector collide(Drawable object, double nX, double nY){
        CollideShape theirShape = object.collideShape;
        NullableVector nVect = new NullableVector();

        if(theirShape == CollideShape.CIRCLE){
            if(collideShape == CollideShape.RECTANGLE){
                //General Case
                double xDiff = Math.abs(posX - nX);
                double yDiff = Math.abs(posY - nY);
                double xUpperLimit = width/2 + object.width/2;
                double yUpperLimit = height/2 + object.height/2;

                //아예 영역 밖
                if(xDiff > xUpperLimit) return nVect;
                if(yDiff > yUpperLimit) return nVect;

                if(xDiff < width/2){
                    if(nY < posY){
                        //윗 방향
                        return new NullableVector(0, 1);
                    }
                    //아래 방향
                    return new NullableVector(0, -1);
                }else if(yDiff < height/2){
                    if(nX < posX){
                        //왼쪽 방향
                        return new NullableVector(-1, 0);
                    }
                    //오른쪽 방향
                    return new NullableVector(1, 0);
                }

                ArrayList<DoublePair> nearestCoordinageBundle = new ArrayList<>();
                nearestCoordinageBundle.add(new DoublePair(posX + width/2, posY + height/2));
                nearestCoordinageBundle.add(new DoublePair(posX - width/2, posY + height/2));
                nearestCoordinageBundle.add(new DoublePair(posX + width/2, posY - height/2));
                nearestCoordinageBundle.add(new DoublePair(posX - width/2, posY - height/2));

                DoublePair core = new DoublePair(nX, nY);
                double minDist = 1000000000000D;
                int minDex = -1;
                for(int i=0;i<4;i++){
                    double dist = core.getDistance(nearestCoordinageBundle.get(i));
                    if(minDist > dist){
                        minDist = dist;
                        minDex = i;
                    }
                }

                DoublePair selected = nearestCoordinageBundle.get(minDex);
                if(core.getDistance(selected) > object.width/2) return nVect;
                return new NullableVector(core.d1 - selected.d1, core.d2 - selected.d2);
            }
        }

        return nVect;
    }

    public boolean getKilled(){
        return !isAlive;
    }

    public Point getPoint(){
        return new Point((int)posX, (int)posY);
    }
}
