package cmsc_405_project_2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import cmsc_405_project_2.utils.ThreeDShapeDrawer;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;

/**
 * Use JOGL to draw a simple cube
 * with each face being a different color. Rotations
 * can be applied with the arrow keys, the page up
 * key, and the page down key. The home key will set
 * all rotations to 0. Initial rotations about the
 * x, y, and z axes are 15, -15, and 0.
 *
 * This program is meant as an example of using modeling
 * transforms, with glPushMatrix and glPopMatrix.
 *
 * Note that this program does not use lighting.
 */
public class App extends GLJPanel implements GLEventListener, KeyListener {

    /**
     * A main routine to create and show a window that contains a
     * panel of type App. The program ends when the
     * user closes the window.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("A Simple Unlit Cube -- ARROW KEYS ROTATE");
        App panel = new App();
        window.setContentPane(panel);
        window.pack();
        window.setLocation(50, 50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        panel.requestFocusInWindow();
    }

    /**
     * Constructor for class App.
     */
    public App() {
        super(new GLCapabilities(null)); // Makes a panel with default OpenGL "capabilities".
        setPreferredSize(new Dimension(500, 500));
        addGLEventListener(this); // A listener is essential! The listener is where the OpenGL programming lives.
        addKeyListener(this);
    }

    // -------------------- methods to draw the cube ----------------------

    double rotateX = 15; // rotations of the cube about the axes
    double rotateY = -15;
    double rotateZ = 0;

    
    

    // -------------------- GLEventListener Methods -------------------------

    /**
     * The display method is called when the panel needs to be redrawn.
     * The is where the code goes for drawing the image, using OpenGL commands.
     */
    public void display(GLAutoDrawable drawable) {

        GL2 gl2 = drawable.getGL().getGL2(); // The object that contains all the OpenGL methods.

        gl2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        gl2.glLoadIdentity(); // Set up modelview transform.
        gl2.glRotated(rotateZ, 0, 0, 1);
        gl2.glRotated(rotateY, 0, 1, 0);
        gl2.glRotated(rotateX, 1, 0, 0);

        ThreeDShapeDrawer.cube(gl2, 1);

        ThreeDShapeDrawer.pyramid(gl2, 1, 0, 0);

        // Add an Index Face set
        // Note using Graph paper is the best way to figure these vertices.
        // You can make about any shape you want this way
        double[][] vertexList = { { 1, -0.5, 1 }, { 1, -.5, -1 }, { 1, .5, -1 }, { 1, .5, 1 }, { .75, .75, 0 },
                { -.75, .75, 0 }, { -1, -.5, 1 }, { -1, .5, 1 }, { -1, .5, -1 }, { -1, -.5, -1 } };

        int[][] faceList = { { 0, 1, 2, 3 }, { 3, 2, 4 }, { 7, 3, 4, 5 }, { 2, 8, 5, 4 }, { 5, 8, 7 },
                { 0, 3, 7, 6 }, { 0, 6, 9, 1 }, { 2, 1, 9, 8 }, { 6, 7, 8, 9 } };

        for (int[] ints : faceList) {
            gl2.glColor3f(1, 0, 1); // Set color for face number i.
            gl2.glBegin(GL2.GL_TRIANGLE_FAN);
            for (int j = 0; j < ints.length; j++) {
                int vertexNum = ints[j]; // Index for vertex j of face i.
                double[] vertexCoords = vertexList[vertexNum]; // The vertex itself.
                gl2.glVertex3dv(vertexCoords, 0);
            }
            gl2.glEnd();

            // You can add more shapes here.

        }

    } // end display()

    public void init(GLAutoDrawable drawable) {
        // called when the panel is created
        GL2 gl2 = drawable.getGL().getGL2();
        gl2.glMatrixMode(GL2.GL_PROJECTION);
        // gl2.glOrtho(-1, 1 ,-1, 1, -1, 1);
        // Changing this is your coordinate -x,x,-y,y,-z,z)
        // Larger numbers zooms out.
        // Play with this to make sure you see your shapes.
        gl2.glOrtho(-5, 5, -5, 5, -5, 5);
        gl2.glMatrixMode(GL2.GL_MODELVIEW);
        gl2.glClearColor(0, 0, 0, 1);
        gl2.glEnable(GL2.GL_DEPTH_TEST);
    }

    public void dispose(GLAutoDrawable drawable) {
        // called when the panel is being disposed
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        // called when user resizes the window
    }

    // ---------------- Methods from the KeyListener interface --------------

    public void keyPressed(KeyEvent evt) {
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_LEFT)
            rotateY -= 15;
        else if (key == KeyEvent.VK_RIGHT)
            rotateY += 15;
        else if (key == KeyEvent.VK_DOWN)
            rotateX += 15;
        else if (key == KeyEvent.VK_UP)
            rotateX -= 15;
        else if (key == KeyEvent.VK_PAGE_UP)
            rotateZ += 15;
        else if (key == KeyEvent.VK_PAGE_DOWN)
            rotateZ -= 15;
        else if (key == KeyEvent.VK_HOME)
            rotateX = rotateY = rotateZ = 0;
        repaint();
    }

    public void keyReleased(KeyEvent evt) {
    }

    public void keyTyped(KeyEvent evt) {
    }

}
