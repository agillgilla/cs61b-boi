public class NBody {
	public static double readRadius(String filename) {
		In in = new In(filename);
		in.readInt();
		return in.readDouble();
	}

	public static Planet[] readPlanets(String filename) {
		In in = new In(filename);
		Planet[] planetList = new Planet[5];
		
		int numPlanets = in.readInt();
		in.readDouble();
		for (int i = 0; i < numPlanets; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();
			planetList[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
		}
		
		return planetList;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radiusUniverse = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		StdDraw.setScale(-radiusUniverse, radiusUniverse);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");

		for (Planet planet : planets) {
			planet.draw();
		}

		double time = 0.0;
		while (time < T) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			int i = 0;
			for (Planet planet : planets) {
				xForces[i] = planet.calcNetForceExertedByX(planets);
				yForces[i] = planet.calcNetForceExertedByY(planets);
				i++;
			}

			int n = 0;
			for (Planet planet : planets) {
				planet.update(dt, xForces[n], yForces[n]);
				n++;
			}
			
			StdDraw.picture(0, 0, "images/starfield.jpg");

			for (Planet planet : planets) {
				planet.draw();
			}

			StdDraw.show(10);

			time += dt;
		}
		
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radiusUniverse);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);	
		}

	}
}
