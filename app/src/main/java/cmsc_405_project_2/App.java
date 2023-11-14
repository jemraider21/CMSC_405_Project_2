package cmsc_405_project_2;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import cmsc_405_project_2.utils.TwoDShapeDrawer;

import javax.swing.*;

public class App extends JFrame implements GLEventListener {

    transient TwoDShapeDrawer shapeDrawer = new TwoDShapeDrawer();

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

        shapeDrawer.drawMovingTriangle(gl);
        shapeDrawer.drawMovingSquare(gl);
        shapeDrawer.drawMovingPentagon(gl);
        shapeDrawer.drawMovingHexagon(gl);
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
