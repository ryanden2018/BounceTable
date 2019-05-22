

class Disk {
  private double centerX;
  private double centerY;
  private double radius;

  Disk() {
    this.centerX = 0.0;
    this.centerY = 0.0;
    this.radius = 1.0;
  }

  Disk(double centerX,double centerY,double radius) {
    this.centerX = centerX;
    this.centerY = centerY;
    this.radius = Math.abs(radius);
  }

  double getRadius() { return radius; }
  double getCenterX() { return centerX; }
  double getCenterY() { return centerY; }

  void setRadius(double radius) { this.radius = Math.abs(radius); }
  void setCenterX(double centerX) { this.centerX = centerX; }
  void setCenterY(double centerY) { this.centerY = centerY; }

  void displaceX(double changeX) { this.centerX += changeX; }
  void displaceY(double changeY) { this.centerY += changeY; }

  static boolean disksOverlap(Disk disk1, Disk disk2) {
    double dist = Math.sqrt(Math.pow(disk1.centerX-disk2.centerX,2.0) + Math.pow(disk1.centerY-disk2.centerY,2.0));
    return ((disk1.radius+disk2.radius) > dist);
  }

  /* Disk adjustedDisk(Disk disk, double vecX, double vecY)
   * If Disk disk does not overlap this disk, return a copy of Disk disk.
   * Otherwise:
   * Return a disk which meets tangentially to this disk instance and is
   * otherwise a copy of the Disk disk. This is acheived by translating
   * the copy of the Disk disk along the direction vector (-vecX,-vecY).
   */
  Disk adjustedDisk(Disk disk, double vecX, double vecY) {
    Disk newDisk = new Disk(disk.getCenterX(),disk.getCenterY(),disk.getRadius());

    if(!Disk.disksOverlap(this,disk)) {
      return newDisk;
    }

    double d1 = vecX / Math.sqrt(Math.pow(vecX,2)+Math.pow(vecY,2));
    double d2 = vecY / Math.sqrt(Math.pow(vecX,2)+Math.pow(vecY,2));

    // quadratic formula ax^2+bx+c=0 with a=1
    double b = (2.0*d1)*(this.getCenterX()-disk.getCenterX()) +
               (2.0*d2)*(this.getCenterY()-disk.getCenterY());
    
    double c = Math.pow(this.getCenterX()-disk.getCenterX(),2) +
               Math.pow(this.getCenterY()-disk.getCenterY(),2) -
               Math.pow(this.getRadius()+disk.getRadius(),2);
    
    double x = (-b + Math.sqrt(Math.pow(b,2)-4.0*c))/2.0;

    newDisk.displaceX((-1)*x*d1);
    newDisk.displaceY((-1)*x*d2);

    return newDisk;
  }
}
