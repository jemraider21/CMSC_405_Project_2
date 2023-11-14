package cmsc_405_project_2.utils;

import com.jogamp.opengl.GL2;

public class ThreeDShapeDrawer {
    private ThreeDShapeDrawer() {
    }

    // Create a method that draws a pyramid using the triangle method
    // You can add this to the display method
    public static void pyramid(GL2 gl2, double r, double g, double b) {
        gl2.glPushMatrix();
        gl2.glTranslated(-3, -3, 0);
        TwoDShapeDrawer.triangle(gl2, r, g, b); // red front face

        gl2.glPushMatrix();
        gl2.glRotated(90, 0, 1, 0);
        TwoDShapeDrawer.triangle(gl2, 0, 1, 0); // green right face
        gl2.glPopMatrix();

        gl2.glPushMatrix();
        gl2.glRotated(-90, 1, 0, 0);
        TwoDShapeDrawer.triangle(gl2, 0, 0, 1); // blue top face
        gl2.glPopMatrix();

        gl2.glPushMatrix();
        gl2.glRotated(180, 0, 1, 0);
        TwoDShapeDrawer.triangle(gl2, 0, 1, 1); // cyan back face
        gl2.glPopMatrix();

        gl2.glPushMatrix();
        gl2.glRotated(-90, 0, 1, 0);
        TwoDShapeDrawer.triangle(gl2, 1, 0, 1); // magenta left face
        gl2.glPopMatrix();

        gl2.glPopMatrix(); // Restore matrix to its state before cube() was called.
    }

    // Create a method that draws a cube using the square method
    // You can add this to the display method

    public static void cube(GL2 gl2, double size) {
        gl2.glPushMatrix();
        gl2.glScaled(size, size, size); // scale unit cube to desired size
        // Move the squares to offset 3,3
        gl2.glTranslated(3, 3, 0);
        TwoDShapeDrawer.square(gl2, 1, 0, 0); // red front face

        gl2.glPushMatrix();
        gl2.glRotated(90, 0, 1, 0);

        TwoDShapeDrawer.square(gl2, 0, 1, 0); // green right face
        gl2.glPopMatrix();

        gl2.glPushMatrix();
        gl2.glRotated(-90, 1, 0, 0);
        TwoDShapeDrawer.square(gl2, 0, 0, 1); // blue top face
        gl2.glPopMatrix();

        gl2.glPushMatrix();
        gl2.glRotated(180, 0, 1, 0);
        TwoDShapeDrawer.square(gl2, 0, 1, 1); // cyan back face
        gl2.glPopMatrix();

        gl2.glPushMatrix();
        gl2.glRotated(-90, 0, 1, 0);
        TwoDShapeDrawer.square(gl2, 1, 0, 1); // magenta left face
        gl2.glPopMatrix();

        gl2.glPushMatrix();
        gl2.glRotated(90, 1, 0, 0);
        TwoDShapeDrawer.square(gl2, 1, 1, 0); // yellow bottom face
        gl2.glPopMatrix();

        gl2.glPopMatrix(); // Restore matrix to its state before cube() was called.
    }

    // Create a method that draws a 3D pentagon using the pentagon method
    // You can add this to the display method
    public static void pentagon3D(GL2 gl2, double r, double g, double b) {
        gl2.glPushMatrix();
        gl2.glTranslated(3, 3, 0);
        TwoDShapeDrawer.pentagon(gl2, r, g, b); // red front face

        gl2.glPushMatrix();
        gl2.glRotated(90, 0, 1, 0);
        TwoDShapeDrawer.pentagon(gl2, 0, 1, 0); // green right face
        gl2.glPopMatrix();

        gl2.glPushMatrix();
        gl2.glRotated(-90, 1, 0, 0);
        TwoDShapeDrawer.pentagon(gl2, 0, 0, 1); // blue top face
        gl2.glPopMatrix();

        gl2.glPushMatrix();
        gl2.glRotated(180, 0, 1, 0);
        TwoDShapeDrawer.pentagon(gl2, 0, 1, 1); // cyan back face
        gl2.glPopMatrix();

        gl2.glPushMatrix();
        gl2.glRotated(-90, 0, 1, 0);
        TwoDShapeDrawer.pentagon(gl2, 1, 0, 1); // magenta left face
        gl2.glPopMatrix();

        gl2.glPopMatrix(); // Restore matrix to its state before cube() was called.
    }
}
