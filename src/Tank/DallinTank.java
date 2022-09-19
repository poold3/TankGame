package Tank;

public class DallinTank extends ITank{

    public final String tankName = "Clifford";
    public final String tankColor = "red";
    private boolean begin = false;

    public DallinTank(double xPosition, double yPosition, Angle startAngle) {
        this.health = 5;
        this.moving = false;
        this.position = new double[]{xPosition, yPosition};
        this.currentAngle = new Angle(startAngle.getValue());
        this.newAngle = new Angle(startAngle.getValue());
    }

    @Override
    public String getTankName() {
        return this.tankName;
    }

    @Override
    public String getTankColor() {
        return this.tankColor;
    }

    @Override
    public void runTime() {

        if (!this.isMoving() && !begin) {
            this.moveTank();
            this.setNewAngle(180);
            begin = true;
        }
        else if (this.currentAngle.getValue() == 180) {
            this.stopTank();
            this.setNewAngle(270);
        }

    }

    //Your methods here:

}
