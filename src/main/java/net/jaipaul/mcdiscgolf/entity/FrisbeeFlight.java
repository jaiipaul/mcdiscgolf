package net.jaipaul.mcdiscgolf.entity;

import org.joml.Matrix3x2d;

import net.jaipaul.mcdiscgolf.utils.Mat3d;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class FrisbeeFlight {
    private final double airDensity = 1.225;
    private final double g = 1;

    private double j_xz = 4.23e-3;
    private double j_y = 8.46e-3;
    private double diameter;
    private double mass;
    private double omega;
    private FrisbeeKinematicModel model;
    
    private double forcePerV2;
    private double torquePerV2;
    private Vec3d F_grav;

    private Vec3d angularVelocity = new Vec3d(0, 0, 0);
    private Vec3d prevAngularVelocity = new Vec3d(0, 0, 0);

    private Vec3d acceleration = new Vec3d(0, 0, 0);
    private Vec3d prevAcceleration = new Vec3d(0, 0, 0);

    public FrisbeeFlight(double area, double mass, double spin, boolean forehand, FrisbeeKinematicModel model){
        this.diameter = 2 * Math.sqrt(area / Math.PI);
        this.mass = mass;
        this.model = model;

        this.forcePerV2 = 0.5 * this.airDensity * area;
        this.torquePerV2 = this.forcePerV2 * diameter;
        this.F_grav = new Vec3d(0, -this.mass*this.g, 0);
        this.omega = forehand ? spin : -spin;
        // System.out.println(this.omega);
    }

    private Mat3d TransformGroundFrisbee(Vec3d angles){
        Mat3d rot_X = Mat3d.RotationX(angles.getX());
        Mat3d rot_Y = Mat3d.RotationY(angles.getY());
        Mat3d rot_Z = Mat3d.RotationZ(angles.getZ());
        return rot_X.mul(rot_Y).mul(rot_Z);
    }
    
    private Mat3d TransformFrisbeeSideSlip(float beta){
        return Mat3d.RotationY(beta);
    }
    
    private Mat3d TransformSideSlipWind(float alpha){
        return Mat3d.RotationZ(alpha);
    }
    public void ComputeAccelerations(Vec3d angles, Vec3d velocity){
        // System.out.println("-----------------------------");
        // System.out.println("Transformation");
        Mat3d T_gf = this.TransformGroundFrisbee(angles);
        Mat3d T_fg = T_gf.transpose();
        Vec3d velocity_f = T_gf.mul(velocity); // Velocity in Frisbee coord sys
        double beta = MathHelper.atan2(velocity_f.getZ(), velocity_f.getX());
        
        Mat3d T_fs = TransformFrisbeeSideSlip((float)beta);
        Mat3d T_sf = T_fs.transpose();
        
        Vec3d velocity_s = T_fs.mul(velocity_f);
        double alpha = MathHelper.atan2(velocity_s.getY(), velocity_s.getX());
        
        Mat3d T_sw = TransformSideSlipWind((float)alpha);
        Mat3d T_ws = T_sw.transpose();
        
        Vec3d velocity_w = T_sw.mul(velocity_s);
        Vec3d grav_w = T_sw.mul(T_fs.mul(T_gf.mul(this.F_grav)));
        // System.out.println("Forces in wind coord");
        double x_vel_w2 = Math.pow(velocity_w.getX(), 2);
        double drag = this.forcePerV2*x_vel_w2*this.model.C_drag(alpha);
        // System.out.println(drag);
        double lift = this.forcePerV2*x_vel_w2*this.model.C_lift(alpha);
        // System.out.println(lift);
        double mom = this.torquePerV2*x_vel_w2*this.model.C_mom(alpha);
        // System.out.println(mom);
        
        Vec3d accel_w = grav_w.add(new Vec3d(-drag, lift, 0)).multiply(1/this.mass);
        // System.out.println(accel_w);
        Vec3d rot_s = new Vec3d(-mom/(this.omega*this.mass*(this.j_xz - this.j_y)), 0, 0);

        // Vec3d accel_g = T_fg.mul(T_sf.mul(T_ws.mul(accel_w)));
        // Vec3d rot_g = T_fg.mul(T_sf.mul(rot_s));
        // System.out.println(accel_g);
        // System.out.println(rot_g);
        // this.acceleration = (this.prevAcceleration.add(accel_g)).multiply(0.5);
        // this.angularVelocity = (this.prevAngularVelocity.add(rot_g)).multiply(0.5);
        // System.out.println("Back to ground coord");
        this.acceleration = T_fg.mul(T_sf.mul(T_ws.mul(accel_w)));
        // System.out.println(T_gf.mul(velocity).add(this.acceleration.multiply(0.05f)));
        this.angularVelocity = T_fg.mul(T_sf.mul(rot_s));
        // System.out.println(this.acceleration);
        // System.out.println(this.angularVelocity);
    }

    public Vec3d GetAcceleration(){
        return this.acceleration;
    }

    public Vec3d GetAngularVelocity(){
        return this.angularVelocity;
    }

    public Vec3d UpdatePosition(Vec3d pos, Vec3d vel, float timeDelta){
        return pos.add(vel.multiply(timeDelta)).add(this.acceleration.multiply(0.5*Math.pow(timeDelta, 2)));
    }

    public Vec3d UpdateVelocity(Vec3d vel, float timeDelta){
        return vel.add(this.acceleration.multiply(timeDelta));
    }

    public Vec3d UpdateAngles(Vec3d angles, float timeDelta){
        Vec3d mod_angles = angles.add(this.angularVelocity.multiply(timeDelta));;
        // Vec3d mod_angles = ;
        // System.out.println(mod_angles);
        return ModuloAngles(mod_angles);
    }

    private Vec3d ModuloAngles(Vec3d angles){
        double x = angles.getX() % (2*Math.PI);
        double y = angles.getY() % (2*Math.PI);
        double z = angles.getZ() % (2*Math.PI);
        return new Vec3d(x, y, z);
    }
}
