public class NBody {
    public static double readRadius(String s){
        In in = new In(s);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String s){
        In in = new In(s);
        int num = in.readInt();
        in.readDouble();
        Body[] bodies = new Body[num];
        for (int i = 0; i<bodies.length; i++){
            bodies[i] =new Body(in.readDouble(),in.readDouble(), in.readDouble(),
                    in.readDouble(),in.readDouble(),in.readString());
        }

        return bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dT = Double.parseDouble(args[1]);
        String FileName = args[2];
        double radius = readRadius(FileName);
        Body[] bodies = readBodies(FileName);


        double Timer = 0.0;
        StdDraw.enableDoubleBuffering();

        while(Timer < T) {
            StdDraw.enableDoubleBuffering();
            Double[] xForce = new Double[bodies.length];
            Double[] yForce = new Double[bodies.length];
            for (int i =0; i<bodies.length;i++) {
                xForce[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForce[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for (int i = 0; i<bodies.length;i++){
                bodies[i].update(dT,xForce[i],yForce[i]);
            }

            StdDraw.setScale(-1*radius,radius);

            /* Clears the drawing window. */

            /* Stamps three copies of advice.png in a triangular pattern. */
            StdDraw.picture(0, 0, "images/starfield.jpg");
            StdDraw.show();


            for (Body k : bodies) {
                k.draw();
                StdDraw.show();
            }
            StdDraw.pause(10);

            Timer += dT;

        }
    }

}
