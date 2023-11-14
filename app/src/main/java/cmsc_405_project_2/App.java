package cmsc_405_project_2;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import javax.swing.*;

public class App extends JFrame implements GLEventListener {

    private float triangleYPos = 0.0f;
    private boolean triangleGoingUp = true;
    private float squareXPos = -1.0f;
    private boolean squareGoingRight = true;
    private static final float TRIANGLE_SPEED = 0.01f;
    private static final float SQUARE_SPEED = 0.01f;
    private float diagonalMovement = -1.0f; // Start from bottom left
    private float hexagonDiagonalX = 1.0f; // Start from bottom right
    private float hexagonDiagonalY = -1.0f;

    public App() {
        // Setup OpenGL Version 2
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas is the widget that's drawn in the JFrame
        GLCanvas glCanvas = new GLCanvas(capabilities);
        glCanvas.addGLEventListener(this);
        this.setName("3D Moving Triangle and Square");
        this.getContentPane().add(glCanvas);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        FPSAnimator animator = new FPSAnimator(glCanvas, 300, true);
        animator.start();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        // Initialize your OpenGL scene here
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        // Cleanup resources here
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

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

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        // Adjust the viewport or projection matrix here
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App();
            }
        });
    }
}
