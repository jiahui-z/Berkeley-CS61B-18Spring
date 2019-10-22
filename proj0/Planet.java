public class Planet {
    private static final double gravitationalConstant = 6.67e-11;

    public double xxPos; // current x position
    public double yyPos; // current y position
    public double xxVel; // current velocity in the x direction
    public double yyVel; // current velocity in the y direction
    public double mass;  // mass
    public String imgFileName; // name of the file that depicts the planet

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet planet) {
        this.xxPos = planet.xxPos;
        this.yyPos = planet.yyPos;
        this.xxVel = planet.xxVel;
        this.yyVel = planet.yyVel;
        this.mass = planet.mass;
        this.imgFileName = planet.imgFileName;
    }

    /**
     * Calculating the distance between two bodies.
     * @param planet the given planet
     * @return the distance between the given planet and the instance planet
     */
    public double calcDistance(Planet planet) {
        double sum = Math.pow(this.xxPos - planet.xxPos, 2) + Math.pow(this.yyPos - planet.yyPos, 2);
        return Math.sqrt(sum);
    }

    /**
     * Calculating the force exerted on the instance planet by the given planet.
     * @param planet the given planet
     * @return the force exerted by the given planet on the instance planet
     */
    public double calcForceExertedBy(Planet planet) {
        double distance = this.calcDistance(planet);
        return gravitationalConstant * this.mass * planet.mass / Math.pow(distance, 2);
    }

    /**
     * Calculating the force exerted in the X direction.
     * @param planet the given planet
     * @return the force exerted in the X direction
     */
    public double calcForceExertedByX(Planet planet) {
        double force = this.calcForceExertedBy(planet);
        double distance = this.calcDistance(planet);
        return force * (planet.xxPos - this.xxPos) / distance;
    }

    /**
     * Calculating the force exerted in the Y direction.
     * @param planet the given planet
     * @return the force exerted in the Y direction
     */
    public double calcForceExertedByY(Planet planet) {
        double force = this.calcForceExertedBy(planet);
        double distance = this.calcDistance(planet);
        return force * (planet.yyPos - this.yyPos) / distance;
    }

    /**
     * Calculating the net X force exerted by all bodies in the array upon the current planet.
     * @param bodies the given planet array
     * @return the net force exerted in the X direction
     */
    public double calcNetForceExertedByX(Planet[] bodies) {
        double forceX = 0;
        for (Planet planet : bodies) {
            if (this.equals(planet)) {
                continue;
            }
            forceX += calcForceExertedByX(planet);
        }
        return forceX;
    }

    /**
     * Calculating the net Y force exerted by all bodies in that array upon the current planet.
     * @param bodies the given planet array
     * @return the net force exerted in the Y direction
     */
    public double calcNetForceExertedByY(Planet[] bodies) {
        double forceY = 0;
        for (Planet planet : bodies) {
            if (this.equals(planet)) {
                continue;
            }
            forceY += calcForceExertedByY(planet);
        }
        return forceY;
    }

    /**
     * Updating the planet's velocity and position in a small period of time.
     * @param time
     * @param forceX force exerted in the X direction
     * @param forceY force exerted in the Y direction
     */
    public void update(double time, double forceX, double forceY) {
        double accelerationX = forceX / this.mass;
        double accelerationY = forceY / this.mass;
        this.xxVel += accelerationX * time;
        this.yyVel += accelerationY * time;
        this.xxPos += this.xxVel * time;
        this.yyPos += this.yyVel * time;
    }

    /**
     * Drawing the planet image at its position
     */
    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}