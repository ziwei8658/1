public class Main {
    public static void main(String[] args) {
        Robot robot = new Robot("Robo", 0, 0); // create new robot instance

        System.out.println(robot.getName() + " is at (" + robot.getX() + ", " + robot.getY() + ")");//initial location output
        robot.move(5, 3); //calling method move to change the position of the robot
        System.out.println(robot.getName() + " moved to (" + robot.getX() + ", " + robot.getY() + ")");//final location output
    }
}