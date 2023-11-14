package cmsc_405_project_2;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;

public class App extends JFrame implements GLEventListener {

    private float yPos = 0.0f;
    private boolean goingUp = true;
    private final float speed = 0.01f;

    public App() {
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas glCanvas = new GLCanvas(capabilities);
        glCanvas.addGLEventListener(this);
        this.setName("3D Moving Triangle");
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

        // Move the "drawing cursor" around
        gl.glTranslatef(0.0f, yPos, 0.0f);

        // Draw the triangle
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex3f(0.0f, 0.5f, 0.0f);
        gl.glVertex3f(-0.5f, -0.5f, 0.0f);
        gl.glVertex3f(0.5f, -0.5f, 0.0f);
        gl.glEnd();

        // Change the Y position for the next frame
        if (goingUp) {
            yPos += speed;
            if (yPos > 1.0f)
                goingUp = false;
        } else {
            yPos -= speed;
            if (yPos < -1.0f)
                goingUp = true;
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
