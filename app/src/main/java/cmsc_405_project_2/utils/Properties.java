package cmsc_405_project_2.utils;

public class Properties {
    // Triangle related properties
    public static final float TRIANGLE_SPEED = 0.01f;
    public static float triangleYPos = 0.0f;
    public static boolean triangleGoingUp = true;

    // Square related properties
    public static final float SQUARE_SPEED = 0.01f;
    public static float squareXPos = -1.0f;
    public static boolean squareGoingRight = true;

    // Pentagon related properties
    public static float pentagonDiagonalMovement = -1.0f; // Start from bottom left

    // Hexagon related properties
    public static float hexagonDiagonalX = 1.0f;
    public static float hexagonDiagonalY = -1.0f;

    // Circle related properties
    public static float circleRadius = 0.1f; // Starting radius of the circle
    public static boolean circleGrowing = true; // Direction of growth
    public static final float circleMaxRadius = 0.5f; // Maximum radius
    public static final float circleMinRadius = 0.1f; // Minimum radius
    public static final float circleGrowthStep = 0.005f; // Increment of growth

    // Octagon related properties
    public static float octagonWidth = 0.1f; // Starting width of the octagon
    public static boolean octagonExpanding = true; // Direction of width change
    public static final float octagonMaxWidth = 0.2f; // Maximum width
    public static final float octagonMinWidth = 0.1f; // Minimum width
    public static final float octagonWidthStep = 0.001f; // Increment of width change
}
