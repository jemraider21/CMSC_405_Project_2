package cmsc_405_project_2.utils;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2ES3;

public class TwoDShapeDrawer {
    // Class member variables for shape positions and directions

    public TwoDShapeDrawer() {
        // Empty constructor
    }

    public void drawMovingTriangle(final GL2 gl) {
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        // Drawing the red triangle
        gl.glTranslatef(0.0f, Properties.triangleYPos, 0.0f);
        gl.glColor3f(1.0f, 0.0f, 0.0f); // Set the color to red
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex3f(0.0f, 0.5f, 0.0f);
        gl.glVertex3f(-0.5f, -0.5f, 0.0f);
        gl.glVertex3f(0.5f, -0.5f, 0.0f);
        gl.glEnd();

        // Change the Y position of the triangle for the next frame
        if (Properties.triangleGoingUp) {
            Properties.triangleYPos += Properties.TRIANGLE_SPEED;
            if (Properties.triangleYPos > 1.0f)
                Properties.triangleGoingUp = false;
        } else {
            Properties.triangleYPos -= Properties.TRIANGLE_SPEED;
            if (Properties.triangleYPos < -1.0f)
                Properties.triangleGoingUp = true;
        }
    }

    public void drawMovingSquare(final GL2 gl) {
        // Reset the current matrix
        gl.glLoadIdentity();

        // Drawing the blue square
        gl.glTranslatef(Properties.squareXPos, 0.0f, 0.0f);
        gl.glColor3f(0.0f, 0.0f, 1.0f); // Set the color to blue
        gl.glBegin(GL2ES3.GL_QUADS);
        gl.glVertex3f(-0.25f, 0.25f, 0.0f);
        gl.glVertex3f(-0.25f, -0.25f, 0.0f);
        gl.glVertex3f(0.25f, -0.25f, 0.0f);
        gl.glVertex3f(0.25f, 0.25f, 0.0f);
        gl.glEnd();

        // Change the X position of the square for the next frame
        if (Properties.squareGoingRight) {
            Properties.squareXPos += Properties.SQUARE_SPEED;
            if (Properties.squareXPos > 1.0f)
                Properties.squareGoingRight = false;
        } else {
            Properties.squareXPos -= Properties.SQUARE_SPEED;
            if (Properties.squareXPos < -1.0f)
                Properties.squareGoingRight = true;
        }
    }

    public void drawMovingPentagon(final GL2 gl) {
        // Reset the current matrix
        gl.glLoadIdentity();

        // Drawing the yellow pentagon
        float pentagonSize = 0.2f; // Size of the pentagon
        float pentagonXPos = Properties.pentagonDiagonalMovement; // X position for the pentagon
        float pentagonYPos = Properties.pentagonDiagonalMovement; // Y position for the pentagon

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
        if (Properties.pentagonDiagonalMovement < 1.0f) {
            Properties.pentagonDiagonalMovement += 0.005f; // Increase the diagonal position
        } else {
            Properties.pentagonDiagonalMovement = -1.0f; // Reset to start again from bottom left
        }
    }

    public void drawMovingHexagon(final GL2 gl) {
        // Reset the current matrix
        gl.glLoadIdentity();

        // Drawing the green hexagon
        float hexagonSize = 0.2f; // Size of the hexagon

        gl.glTranslatef(Properties.hexagonDiagonalX, Properties.hexagonDiagonalY, 0.0f);
        gl.glColor3f(0.0f, 1.0f, 0.0f); // Set the color to green
        gl.glBegin(GL2.GL_POLYGON);
        for (int i = 0; i < 6; i++) {
            gl.glVertex2d(
                    hexagonSize * Math.cos(i * 2 * Math.PI / 6),
                    hexagonSize * Math.sin(i * 2 * Math.PI / 6));
        }
        gl.glEnd();

        // Hexagon's position logic...
        if (Properties.hexagonDiagonalX > -1.0f && Properties.hexagonDiagonalY < 1.0f) {
            Properties.hexagonDiagonalX -= 0.005f; // Decrease the X position
            Properties.hexagonDiagonalY += 0.005f; // Increase the Y position
        } else {
            Properties.hexagonDiagonalX = 1.0f; // Reset to start again from bottom right
            Properties.hexagonDiagonalY = -1.0f;
        }
    }

    public void drawMovingOctagon(final GL2 gl) {
        int numSides = 8; // An octagon has 8 sides
        float twoPi = (float) (2.0f * Math.PI);

        gl.glLoadIdentity();
        // Translate to the top right corner
        gl.glTranslatef(0.8f, 0.8f, 0.0f);
        gl.glColor3f(0.5f, 0.0f, 0.5f); // Set color to purple
        gl.glBegin(GL2.GL_POLYGON);
        for (int i = 0; i < numSides; i++) {
            float theta = twoPi * i / numSides;
            float x = Properties.octagonWidth * (float) Math.cos(theta);
            float y = Properties.octagonWidth * (float) Math.sin(theta);
            gl.glVertex2f(x, y);
        }
        gl.glEnd();

        // Animate the octagon width
        if (Properties.octagonExpanding) {
            Properties.octagonWidth += Properties.octagonWidthStep;
            if (Properties.octagonWidth >= Properties.octagonMaxWidth) {
                Properties.octagonExpanding = false;
            }
        } else {
            Properties.octagonWidth -= Properties.octagonWidthStep;
            if (Properties.octagonWidth <= Properties.octagonMinWidth) {
                Properties.octagonExpanding = true;
            }
        }
    }
}
