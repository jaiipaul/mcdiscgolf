package net.jaipaul.mcdiscgolf.utils;

import net.minecraft.util.math.Vec3d;

public class Mat3d
{
	Vec3d[] el = new Vec3d[3];
	
	public Mat3d(Mat3d m)
	{ 
		for(int i= 0; i<3;i++) el[i] = new Vec3d(m.el[i].getX(), m.el[i].getY(), m.el[i].getZ());	
	}
	
	public Mat3d()
	{ 
		for(int i= 0; i<3;i++) el[i] = new Vec3d(0,0,0);	
	}
	public Mat3d(Vec3d a, Vec3d b, Vec3d c)
	{ 
		el[0] = a;
		el[1] = b;
		el[2] = c;
	}
	public Mat3d(double y0,double y1, double y2) //diag
	{
		el[0] = new Vec3d(y0,0,0);
		el[1] = new Vec3d(0,y1,0);
		el[2] = new Vec3d(0,0,y2);
	}
	public Mat3d(double y0,double y1, double y2,double y3,double y4,double y5,double y6,double y7,double y8) //zeilen weise
	{
		el[0] = new Vec3d(y0,y1,y2);
		el[1] = new Vec3d(y3,y4,y5);
		el[2] = new Vec3d(y6,y7,y8);
	}
	
	public Mat3d(double [] y) //zeilen weise
	{
		for(int i= 0; i<3;i++) el[i] = new Vec3d(y[i],y[i+3],y[i+6]);
	}

	public Mat3d(double y) //zeilen weise
	{
		for(int i= 0; i<3;i++) el[i] = new Vec3d(y, y, y);
	}
	
	public Mat3d setRow(int i, double[] y)
	{	
		setRow(i, new Vec3d(y[0],y[1],y[2]));;
		return this;
	}
	public Mat3d setRow(int i, Vec3d y)
	{	
		el[i] = new Vec3d(y.getX(), y.getY(), y.getZ());
		return this;
	}
	public Vec3d getRow(int i){
		return this.el[i];
	}

	public double get(int i, int j){
		
		if (j == 0)	return el[i].x;
		if (j == 1)	return el[i].y;
		if (j == 2)	return el[i].z;
		return 0;
	}
	
	public Vec3d getCol(int j) 
	{
		if (j == 0)	return new Vec3d(el[0].x,el[1].x,el[2].x);
		if (j == 1)	return new Vec3d(el[0].y,el[1].y,el[2].y);
		if (j == 2)	return new Vec3d(el[0].z,el[1].z,el[2].z);
		return null;
	}
	public double[] convert2Array()
	{
		return new double[]{el[0].x, el[0].y, el[0].z, el[1].x, el[1].y, el[1].z, el[2].x, el[2].y, el[2].z};
	}
	
	public Mat3d setIdentity()
	{
		el[0] = new Vec3d(1,0,0);
		el[1] = new Vec3d(0,1,0);
		el[2] = new Vec3d(0,0,1);
		
		return this;
	}
	
	public Vec3d mul(Vec3d y)
	{
		return new Vec3d(el[0].dotProduct(y), el[1].dotProduct(y), el[2].dotProduct(y));
	}
	
	public Mat3d mul(Mat3d m)
	{
		Mat3d ans = new Mat3d();
		for(int i= 0; i<3;i++)
		{
			ans.el[i] = this.mul(m.getCol(i));
		}
		return ans.transpose();
	}
	
	public Mat3d rotX(double ang)
	{
		double c = Math.cos(ang);
		double s = Math.sin(ang);
		return this.mul(new Mat3d( 1, 0, 0,
									0, c, -s,
									0, s, c ) );
	}
	public Mat3d rotY(double ang)
	{
		double c = Math.cos(ang);
		double s = Math.sin(ang);
		return this.mul(new Mat3d( c,  0, s,
									0,  1, 0,
									-s, 0, c ) );
	}
	public Mat3d rotZ(double ang)
	{
		double c = Math.cos(ang);
		double s = Math.sin(ang);
		return this.mul(new Mat3d( c,  -s, 0,
									s,  c,  0,
									0,  0,  1 ) );
	}

	public static Mat3d RotationX(double ang)
	{
		double c = Math.cos(ang);
		double s = Math.sin(ang);
		return new Mat3d(1, 0, 0,
						 0,    c,   -s,
						 0,    s,    c);
	}
	public static Mat3d RotationY(double ang)
	{
		double c = Math.cos(ang);
		double s = Math.sin(ang);
		return new Mat3d( c, 0,    s,
					   0, 1, 0,
						 -s, 0,    c);
	}
	public static Mat3d RotationZ(double ang)
	{
		double c = Math.cos(ang);
		double s = Math.sin(ang);
		return new Mat3d(   c,   -s, 0,
						    s,    c, 0,
					     0, 0, 1);
	}
	
	public Mat3d transpose()
	{
		Mat3d ans = new Mat3d();
		for(int i= 0; i<3;i++) ans.el[i]=this.getCol(i);
		return ans;
	}
	public Mat3d InvertDiag()
	{
		Mat3d tmp = new Mat3d();
		
		tmp.el[0] = new Vec3d (1/el[0].x, 0,0);
		tmp.el[1] = new Vec3d (0, 1/el[1].y ,0);
		tmp.el[2] = new Vec3d (0, 0, 1/el[2].z);
		
		return tmp;
	}

	public Mat3d cross(Vec3d b)
	{
		return new Mat3d(b.crossProduct(getCol(0)), 
					     b.crossProduct(getCol(1)), 
		                 b.crossProduct(getCol(2)));
	}

	public double det()
	{
		return  el[0].x*(el[1].y * el[2].z - el[1].z*el[2].y) -
				el[0].y*(el[1].x * el[2].z - el[1].z * el[2].x) +
				el[0].z*(el[1].x * el[2].y - el[1].y*el[2].x);
	}
	public Mat3d normalize()
	{
		return new Mat3d( getCol(0).normalize(),
						  getCol(1).normalize(),
						  getCol(2).normalize())
				   .transpose();
	}
	public boolean isRotational()
	{
		double tmp = Math.abs(det()-1);
		return true; //(tmp < 1e-14);
	}
}

