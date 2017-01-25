public class Planet {
	
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;
	final double GRAV_CONSTANT = 6.67 * Math.pow(10, -11);

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet other) {
		return Math.pow((Math.pow((this.xxPos - other.xxPos), 2) + Math.pow((this.yyPos - other.yyPos), 2)), .5);
	}

	public double calcForceExertedBy(Planet other) {
		return (GRAV_CONSTANT * this.mass * other.mass) / (Math.pow(this.calcDistance(other), 2));
	}

	public double calcForceExertedByX(Planet other) {
		return (this.calcForceExertedBy(other) * (other.xxPos - this.xxPos)) / this.calcDistance(other);
	}

	public double calcForceExertedByY(Planet other) {
		return (this.calcForceExertedBy(other) * (other.yyPos - this.yyPos)) / this.calcDistance(other);
	}

	public double calcNetForceExertedByX(Planet[] others) {
		double totalForce = 0;
		for (Planet other : others) {
			if (!this.equals(other)) {
				totalForce += this.calcForceExertedByX(other);
			}
		}
		return totalForce;
	}

	public double calcNetForceExertedByY(Planet[] others) {
		double totalForce = 0;
		for (Planet other : others) {
			if (!this.equals(other)) {
				totalForce += this.calcForceExertedByY(other);
			}
		}
		return totalForce;
	}

	public void update(double dt, double fX, double fY) {
		this.xxVel += dt * (fX / this.mass);
		this.yyVel += dt * (fY / this.mass);
		this.xxPos += dt * xxVel;
		this.yyPos += dt * yyVel;
	}
}