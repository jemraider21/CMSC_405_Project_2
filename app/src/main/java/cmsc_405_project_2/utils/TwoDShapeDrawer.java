package cmsc_405_project_2.utils;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2ES3;

public class TwoDShapeDrawer {
    // Class member variables for shape positions and directions
    float triangleYPos = 0.0f;
    boolean triangleGoingUp = true;
    float squareXPos = -1.0f;
    boolean squareGoingRight = true;
    float pentagonDiagonal = -1.0f;
    float hexagonDiagonalX = 1.0f;
    float hexagonDiagonalY = -1.0f;

    private float diagonalMovement = -1.0f; // Start from bottom left

    private static final float TRIANGLE_SPEED = 0.01f;
    private static final float SQUARE_SPEED = 0.01f;

    public TwoDShapeDrawer() {
        // Empty constructor
    }

    public void drawMovingTriangle(final GL2 gl) {
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        // Drawing the red triangle
        gl.glTranslatef(0.0f, triangleYPos, 0.0f);
        gl.glColor3f(1.0f, 0.0f, 0.0f); // Set the color to red
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex3f(0.0f, 0.5f, 0.0f);
        gl.glVertex3f(-0.5f, -0.5f, 0.0f);
        gl.glVertex3f(0.5f, -0.5f, 0.0f);
        gl.glEnd();

        // Change the Y position of the triangle for the next frame
        if (triangleGoingUp) {
            triangleYPos += TRIANGLE_SPEED;
            if (triangleYPos > 1.0f)
                triangleGoingUp = false;
        } else {
            triangleYPos -= TRIANGLE_SPEED;
            if (triangleYPos < -1.0f)
                triangleGoingUp = true;
        }
    }

    public void drawMovingSquare(final GL2 gl) {
        // Reset the current matrix
        gl.glLoadIdentity();

        // Drawing the blue square
        gl.glTranslatef(squareXPos, 0.0f, 0.0f);
        gl.glColor3f(0.0f, 0.0f, 1.0f); // Set the color to blue
        gl.glBegin(GL2ES3.GL_QUADS);
        gl.glVertex3f(-0.25f, 0.25f, 0.0f);
        gl.glVertex3f(-0.25f, -0.25f, 0.0f);
        gl.glVertex3f(0.25f, -0.25f, 0.0f);
        gl.glVertex3f(0.25f, 0.25f, 0.0f);
        gl.glEnd();

        // Change the X position of the square for the next frame
        if (squareGoingRight) {
            squareXPos += SQUARE_SPEED;
            if (squareXPos > 1.0f)
                squareGoingRight = false;
        } else {
            squareXPos -= SQUARE_SPEED;
            if (squareXPos < -1.0f)
                squareGoingRight = true;
        }
    }

    public void drawMovingPentagon(final GL2 gl) {
        // Reset the current matrix
        gl.glLoadIdentity();

        // Drawing the yellow pentagon
        float pentagonSize = 0.2f; // Size of the pentagon
        float pentagonXPos = diagonalMovement; // X position for the pentagon
        float pentagonYPos = diagonalMovement; // Y position for the pentagon

        gl.glTranslatef(pentagonXPos, pentagonYPos, 0.0f);
        gl.glColor3f(1.0f, 1.0f, 0.0f); // Set the color to yellow
        gl.glBegin(GL2.GL_POLYGON);
        for (int i = 0; i < 5; i++) {
            gl.glVertex2d(
                    pentagonSize * Math.cos(i * 2 * Math.PI / 5),
                    pentagonSize * Math.sin(i * 2 * Math.PI / 5));
        }
        gl.glEnd();

        // Pentagon's position logic...
        if (diagonalMovement < 1.0f) {
            diagonalMovement += 0.005f; // Increase the diagonal position
        } else {
            diagonalMovement = -1.0f; // Reset to start again from bottom left
        }
    }

    public void drawMovingHexagon(final GL2 gl) {
        // Reset the current matrix
        gl.glLoadIdentity();

        // Drawing the green hexagon
        float hexagonSize = 0.2f; // Size of the hexagon

        gl.glTranslatef(hexagonDiagonalX, hexagonDiagonalY, 0.0f);
        gl.glColor3f(0.0f, 1.0f, 0.0f); // Set the color to green
        gl.glBegin(GL2.GL_POLYGON);
        for (int i = 0; i < 6; i++) {
            gl.glVertex2d(
                    hexagonSize * Math.cos(i * 2 * Math.PI / 6),
                    hexagonSize * Math.sin(i * 2 * Math.PI / 6));
        }
        gl.glEnd();

        // Hexagon's position logic...
        if (hexagonDiagonalX > -1.0f && hexagonDiagonalY < 1.0f) {
            hexagonDiagonalX -= 0.005f; // Decrease the X position
            hexagonDiagonalY += 0.005f; // Increase the Y position
        } else {
            hexagonDiagonalX = 1.0f; // Reset to start again from bottom right
            hexagonDiagonalY = -1.0f;
        }
    }

}
