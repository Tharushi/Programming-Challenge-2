package classSlick;

import org.newdawn.slick.Image;


public class Tank {

    private int xCord;
    private int yCord;
    private Image tankImg;
    private int health,coins,points,whetherShot;
    private int direction;
    private float angel;
    boolean rotate;
    String name;

    public Tank(Image img,int dir) {
        this.tankImg = img;
        direction=dir;
        rotate=false;
    }
    
    //set health,coins,points,whetherShot
    public void setHcpw(int hlth,int coins,int points,int shot) {
        this.health = health;
        this.coins=coins;
        this.points=points;
        this.whetherShot=shot;
    }

    public void setxyCord(int xCord, int yCord) {
        this.xCord = xCord;
        this.yCord = yCord;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getHealth() {
        return health;
    }

    public Image getTankImg() {
        return tankImg;
    }

    public int getxCord() {
        return xCord;
    }

    public int getyCord() {
        return yCord;
    }

    public int getDirection() {
        return direction;
    }

    public int getCoins() {
        return coins;
    }

    public float getAngel() {
        return angel;
    }

    public void setAngel(float angel) {
        this.angel = angel;
    }

    public int getPoints() {
        return points;
    }
    public void setCoinHealthPoints(int coin,int hlth,int point){
        this.coins=coin;
        this.health=hlth;
        this.points=point;
    }

    
    
}
class Brick {

    private int xCord;
    private int yCord;
    private Image brickImg;
    private int damage;

    public Brick(Image img) {
        this.brickImg = img;
    }

    public void setxyDamage(int xCord, int yCord, int dmg) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.damage = dmg;
    }

    public Image getBrickImg() {
        return brickImg;
    }

    public int getDamage() {
        return damage;
    }

    public int getxCord() {
        return xCord;
    }

    public int getyCord() {
        return yCord;
    }
}

class Stone {

    private int xCord;
    private int yCord;
    private Image image;

    public Stone(Image img) {
        this.image = img;
    }

    public void setxy(int xCord, int yCord) {
        this.xCord = xCord;
        this.yCord = yCord;
    }

    public Image getImage() {
        return image;
    }

    public int getxCord() {
        return xCord;
    }

    public int getyCord() {
        return yCord;
    }
}
class Water {

    private int xCord;
    private int yCord;
    private Image image;

    public Water(Image img) {
        this.image = img;
    }

    public void setxy(int xCord, int yCord) {
        this.xCord = xCord;
        this.yCord = yCord;
    }

    public Image getImage() {
        return image;
    }

    public int getxCord() {
        return xCord;
    }

    public int getyCord() {
        return yCord;
    }
}




class CoinPile{
    private int appearTime;
    private int lifeTime;
    Image pileImage;
    private int xCord,yCord;

    public CoinPile(int appearTime,Image img) {
        this.appearTime = appearTime;
        this.lifeTime = lifeTime;
        this.pileImage=img;
    }
    
    public boolean setToDissappear(int timerValue){
        if(this.appearTime==this.lifeTime+timerValue){
            return true;
        }
        else{
            return false;
        }
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public int getAppearTime() {
        return appearTime;
    }

    public void setAppearTime(int appearTime) {
        this.appearTime = appearTime;
    }

    
    
    public int getxCord() {
        return xCord;
    }
    
    public int getyCord() {
        return yCord;
    }

    public void setXY(int xCord,int yCord) {
        this.xCord = xCord;
        this.yCord = yCord;
    }

    
    
    public Image getPileImage() {
        return pileImage;
    }
     
}

class Health{
    private int appearTime;
    private int lifeTime;
    Image healthImage;
    private int xCord,yCord;

    public Health(int appearTime,Image img) {
        this.appearTime = appearTime;
        this.lifeTime = lifeTime;
        this.healthImage=img;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }
    
    public void setXYCord(int xCord,int yCord){
        this.xCord=xCord;
        this.yCord=yCord;
    }

    public int getxCord() {
        return xCord;
    }

    public int getyCord() {
        return yCord;
    }

    public int getAppearTime() {
        return appearTime;
    }

    public void setAppearTime(int appearTime) {
        this.appearTime = appearTime;
    }
    
    

    public Image getPileImage() {
        return healthImage;
    }
     
}
