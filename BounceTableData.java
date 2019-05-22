class BounceTableData {
  Disk puck;
  Disk bigDiskNW;
  Disk bigDiskNE;
  Disk bigDiskSW;
  Disk bigDiskSE;

  double vx; // x velocity component
  double vy; // y velocity component
  double dt; // time step

  BounceTableData(int width, double multiplier, double smallDiskRadius, double dt) {
    double bigDiskRadius = width * multiplier * 0.5;
    puck = new Disk(width*0.5,width*0.5,smallDiskRadius);
    bigDiskNW = new Disk(0,0,bigDiskRadius);
    bigDiskNE = new Disk(width,0,bigDiskRadius);
    bigDiskSW = new Disk(0,width,bigDiskRadius);
    bigDiskSE = new Disk(width,width,bigDiskRadius);

    double theta = 2*Math.PI*Math.random();
    vx = Math.cos(theta);
    vy = Math.sin(theta);
    this.dt = dt;
  }

  void update() {
    puck.displaceX(vx*dt);
    puck.displaceY(vy*dt);

    Disk bigDisk = new Disk();
    if(Disk.disksOverlap(bigDiskNW,puck)) {
      bigDisk = bigDiskNW;
    } else if (Disk.disksOverlap(bigDiskNE,puck)) {
      bigDisk = bigDiskNE;
    } else if (Disk.disksOverlap(bigDiskSW,puck)) {
      bigDisk = bigDiskSW;
    } else if (Disk.disksOverlap(bigDiskSE,puck)) {
      bigDisk = bigDiskSE;
    } else {
      return;
    }
    
    puck = bigDisk.adjustedDisk(puck,vx,vy);

    double nx = bigDisk.getCenterX() - puck.getCenterX();
    double ny = bigDisk.getCenterY() - puck.getCenterY();
    double nabs = Math.sqrt(Math.pow(nx,2)+Math.pow(ny,2));
    nx = nx / nabs;
    ny = ny / nabs;
    double mult = vx*nx + vy*ny;
    vx = vx - 2*nx*mult;
    vy = vy - 2*ny*mult;
    double vabs = Math.sqrt(Math.pow(vx,2)+Math.pow(vy,2)); 
    vx = vx / vabs;
    vy = vy / vabs;
  }
}