public class Body{

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV,
                double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;

    }

    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        double dis = Math.pow((xxPos - b.xxPos),2) + Math.pow((yyPos - b.yyPos),2);
        return Math.sqrt(dis);
    }

    public double calcForceExertedBy(Body b){
        double G = 6.67*10e-12;
        double force = G*mass*b.mass/Math.pow(calcDistance(b),2);

        return force;
    }

    public double calcForceExertedByX(Body b){
        double Gx;
        if (this.equals(b))   return 0;
        Gx = calcForceExertedBy(b)*(b.xxPos-xxPos)/calcDistance(b);
        return Gx;
    }

    public double calcForceExertedByY(Body b){
        double Gy;
        if (this.equals(b))   return 0;
        Gy = calcForceExertedBy(b)*(b.yyPos-yyPos)/calcDistance(b);
        return Gy;

    }

    public double calcNetForceExertedByX(Body[] bs) {
        double NetGx = 0;
        for(int i=0; i<bs.length;i++) {
            NetGx += calcForceExertedByX(bs[i]);

        }
        return NetGx;
    }
    public double calcNetForceExertedByY(Body[] bs) {
        double NetGy = 0;
        for(int i=0; i<bs.length;i++) {
            NetGy += calcForceExertedByY(bs[i]);
        }
        return NetGy;
    }

    public void update(double dt, double Fx, double Fy){
        double ax = Fx/mass;
        double ay = Fy/mass;
        xxVel += ax*dt;
        yyVel += ay*dt;
        xxPos += xxVel*dt;
        yyPos += yyVel*dt;

    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);

    }


}
