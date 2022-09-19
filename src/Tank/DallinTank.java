package Tank;

public class DallinTank extends ITank{

    //Required method
    @Override
    public void runTime() {
        //Your code here:
        this.moveTank();
        this.setNewAngle(this.currentAngle.getValue() + 5);
        System.out.println(this.position[0] + " " + this.position[1] + " " + this.currentAngle.getValue());
    }

    //Your methods here:

}
