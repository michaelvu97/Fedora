abstract class Projectile{
     private State state;
     private int x;
     private int y;
     private int xVelocity;
     private int yVelocity;
     private String imgPath;
     private HitBox hitBox;
     public static int playerShotVelocity = 1;
     public static int playerScatterShotXVelocity = 1;
     public static int playerScatterShotYVelocity = 1;
     public static int playerFastShotVelocity = 2;
     
     public Projectile(int x,int y,State state,String imgPath){
          this.x = x;
          this.y = y;
          this.state = state;
          this.imgPath = imgPath;
     }
}

