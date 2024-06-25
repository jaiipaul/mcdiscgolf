package net.jaipaul.mcdiscgolf.entity;


public class FrisbeeKinematicModel {
    private static double PL0 = 0.13;
    private static double PLa = 3.09;
    private static double PD0 = 0.0853;
    private static double PDa = 3.30;
    private static double PM0 = -0.01;
    private static double PMa = 0.057;

    // private static double alpha_0 = 4 * Math.PI / 180;
    private static double alpha_0 = -0.052;
    // private static double alpha_0 = 0;

    // private static double PTxwx = -0.013;
    // private static double PTxwz = -0.0017;
    // private static double PTy0 = -0.082;
    // private static double PTya = 0.43;
    // private static double PTywy = -0.014;
    // private static double PTzwz = -0.000034;

    public double C_lift(double alpha){
        return PL0 + PLa * alpha;
    }

    public double C_drag(double alpha){
        return PD0 + PDa * Math.pow(alpha - alpha_0, 2);
    }

    public double C_mom(double alpha){
        return PM0 + PMa * alpha;
    }


    // public double C_x(double wx, double wz){
    //     return PTxwx * wx + PTxwz * wz;
    // }

    // public double C_y(double alpha, double wy){
    //     return PTy0 + PTywy * wy + PTya * alpha;
    // }

    // public double C_z(double wz){
    //     return PTzwz * wz;
    // }
}
