public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	
	public Planet(double xP, double yP, double xV,
				  double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	
	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}
	
	public double calcDistance(Planet p) {
		double dx = this.xxPos - p.xxPos;
		double dy = this.yyPos - p.yyPos;
		double r = Math.sqrt(dx * dx + dy * dy);
		return r;
	}
	
	public double calcForceExertedBy(Planet p) {
		double G = 6.67e-11;
		double r = this.calcDistance(p);
		double f = G * this.mass * p.mass / (r * r);
		return f;
	}
	
	public double calcForceExertedByX(Planet p){
		double fx = this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
		return fx;
	}
	
	public double calcForceExertedByY(Planet p){
		double fy = this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
		return fy;
	}
	
	public double calcNetForceExertedByX(Planet[] Planets) {
		double fxNet = 0;
		for (Planet p : Planets ) {
			if (!(this.equals(p))) {
				fxNet += this.calcForceExertedByX(p);
			}
		}
		return fxNet;
	}
	
	public double calcNetForceExertedByY(Planet[] Planets) {
		double fyNet = 0;
		for (Planet p : Planets ) {
			if (!(this.equals(p))) {
				fyNet += this.calcForceExertedByY(p);
			}
		}
		return fyNet;
	}
	
	public void update(double dt, double fX, double fY) {
		double ax = fX / this.mass;
		double ay = fY / this.mass;
		this.xxVel = this.xxVel + ax * dt;
		this.yyVel = this.yyVel + ay * dt;
		this.xxPos = this.xxPos + this.xxVel * dt;
		this.yyPos = this.yyPos + this.yyVel * dt;
	}
	
	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}

















