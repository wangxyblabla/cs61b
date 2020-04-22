public class NBody {
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		in.readInt();
		double Radius = in.readDouble();
		return Radius;
	}
	
	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int num = in.readInt();
		in.readDouble();
		Planet[] Planets = new Planet[num];
		int i = 0;
		while(i < num) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			Planets[i] = new Planet(xP, yP, xV, yV, m, img);
			i += 1;
		}
		return Planets;
	}
	
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String fileName = args[2];
		double Radius = NBody.readRadius(fileName);
		Planet[] Planets = NBody.readPlanets(fileName);
		
		StdDraw.setScale(-Radius, Radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "./images/starfield.jpg");
		
		for(Planet p: Planets) {
			p.draw();
		}
		
		StdDraw.enableDoubleBuffering();
		
		for(double time = 0; time <= T; time += dt) {
			double xForces[] = new double [Planets.length];
			double yForces[] = new double [Planets.length];
			
			int i = 0;
			for (Planet p: Planets) {
				xForces[i] = p.calcNetForceExertedByX(Planets);
				yForces[i] = p.calcNetForceExertedByY(Planets);
				i++;
			}
			
			i = 0;
			for (Planet p: Planets) {
				p.update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, "./images/starfield.jpg");
			for(Planet p: Planets) {
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}
		
		StdOut.printf("%d\n", Planets.length);
		StdOut.printf("%.2e\n", Radius);
		for (int i = 0; i < Planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", 
						  Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel,
						  Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);
		}
	}
	
	
}








