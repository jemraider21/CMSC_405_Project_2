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
    private final float triangleSpeed = 0.01f;
    private final float squareSpeed = 0.01f;

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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        // Drawing the triangle
        gl.glTranslatef(0.0f, triangleYPos, 0.0f);
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex3f(0.0f, 0.5f, 0.0f);
        gl.glVertex3f(-0.5f, -0.5f, 0.0f);
        gl.glVertex3f(0.5f, -0.5f, 0.0f);
        gl.glEnd();

        // Change the Y position of the triangle for the next frame
        if (triangleGoingUp) {
            triangleYPos += triangleSpeed;
            if (triangleYPos > 1.0f)
                triangleGoingUp = false;
        } else {
            triangleYPos -= triangleSpeed;
            if (triangleYPos < -1.0f)
                triangleGoingUp = true;
        }

        // Reset the current matrix
        gl.glLoadIdentity();

        // Drawing the square
        gl.glTranslatef(squareXPos, 0.0f, 0.0f);
        gl.glBegin(GL2ES3.GL_QUADS);
        gl.glVertex3f(-0.25f, 0.25f, 0.0f);
        gl.glVertex3f(-0.25f, -0.25f, 0.0f);
        gl.glVertex3f(0.25f, -0.25f, 0.0f);
        gl.glVertex3f(0.25f, 0.25f, 0.0f);
        gl.glEnd();

        // Change the X position of the square for the next frame
        if (squareGoingRight) {
            squareXPos += squareSpeed;
            if (squareXPos > 1.0f)
                squareGoingRight = false;
        } else {
            squareXPos -= squareSpeed;
            if (squareXPos < -1.0f)
                squareGoingRight = true;
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
