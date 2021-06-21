
public class MathFunctions {
	
	public static int[] Projection(double x, double y, double z) {

		double fpx = Math.tan(Math.toRadians(45))*z*2;
		
		double rx = x/fpx;
		double ry = y/fpx;
		
		double sx = ScaleNumberLine(x,fpx,Game.Width);
		double sy = ScaleNumberLine(y,fpx,Game.Width);
		
		return new int[] {(int)Math.round(sx+(Game.Width/2)), (int)Math.round(sy+Game.Height/2)};
	}
	
	public static double ScaleNumberLine(double number, double line1, double line2) {
		return (number/line1)*line2;
	}

}
