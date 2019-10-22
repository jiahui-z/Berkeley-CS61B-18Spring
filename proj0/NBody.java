public class NBody {
    public static final String backgroundImage = "images/starfield.jpg";

    /**
     * Reading the radius of the universe given the input planets data.
     * @param filename the name of the input file
     * @return the radius of the universe
     */
    public static double readRadius(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    /**
     * Reading the planets of the universe given the input planets data.
     * @param filename the name of the input file
     * @return the planets array of the universe
     */
    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[N];
        for (int i = 0; i < N; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return planets;
    }

    public static void main(String[] args) {
        // collecting all needed input
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int N = planets.length;    

        // setting up drawing parameters
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-3e11, 3e11);
        StdDraw.clear();

        // drawing planets
        double time = 0;
        while (time < T) {
            double[] xForces = new double[N];
            double[] yForces = new double[N];
            for (int i = 0; i < N; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < N; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, backgroundImage);
            for (int i = 0; i < N; i++) {
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }   
        StdOut.printf("%d\n", N);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < N; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", 
                          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, 
                          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}