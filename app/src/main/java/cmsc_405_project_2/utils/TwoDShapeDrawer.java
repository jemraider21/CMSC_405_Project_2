package cmsc_405_project_2.utils;

import com.jogamp.opengl.GL2;

public class TwoDShapeDrawer {
    private TwoDShapeDrawer() {
    }

    // Create a method that draws a triangle
    // You can add this to the display method
    public static void triangle(GL2 gl2, double r, double g, double b) {
        gl2.glColor3d(r, g, b);
        gl2.glBegin(GL2.GL_TRIANGLES);
        gl2.glVertex3d(-0.5, -0.5, 0);
        gl2.glVertex3d(0.5, -0.5, 0);
        gl2.glVertex3d(0, 0.5, 0);
        gl2.glEnd();
    }

    // Create a method that draws a square
    // You can add this to the display method
    public static void square(GL2 gl2, double r, double g, double b) {
        gl2.glColor3d(r, g, b);
        gl2.glBegin(GL2.GL_TRIANGLE_FAN);
        gl2.glVertex3d(-0.5, -0.5, 0.5);
        gl2.glVertex3d(0.5, -0.5, 0.5);
        gl2.glVertex3d(0.5, 0.5, 0.5);
        gl2.glVertex3d(-0.5, 0.5, 0.5);
        gl2.glEnd();
    }

    // Create a method that draws a pentagon
    // You can add this to the display method
    public static void pentagon(GL2 gl2, double r, double g, double b) {
        gl2.glColor3d(r, g, b);
        gl2.glBegin(GL2.GL_TRIANGLE_FAN);
        gl2.glVertex3d(0, 0.5, 0.5);
        gl2.glVertex3d(-0.5, 0.25, 0.5);
        gl2.glVertex3d(-0.25, -0.25, 0.5);
        gl2.glVertex3d(0.25, -0.25, 0.5);
        gl2.glVertex3d(0.5, 0.25, 0.5);
        gl2.glEnd();
    }
}
