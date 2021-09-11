package org.mydomain.myproject;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;
import com.jme3.system.JmeContext.Type;
import com.jme3.system.lwjgl.LwjglWindow;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWFramebufferSizeCallbackI;
import org.lwjgl.opengl.GL33;

public class JMonkeyApp extends SimpleApplication implements Runnable {

    // modes include: Type.Display (default), Type.Canvas, Type.Headless (server), Type.OffscreenSurface
    JmeContext.Type contextType;
    
    public static void main(String[] args) {
	JmeContext.Type contextType = Type.Display;
	showArguments(args);
	if (args.length > 0) {
	    contextType = parseContextArg(args[0]);
	}
        JMonkeyApp app = new JMonkeyApp(contextType);
        app.run();
    }
    
    public JMonkeyApp(JmeContext.Type contextType) {
	this.contextType = contextType;
	AppSettings settings = new AppSettings(true);
        settings.setTitle("Default Scene");
	settings.setResizable(true);
        setSettings(settings);
    }

    @Override
    public void run() {
        start(contextType);
    }

    private float getDisplayScale(long window) {
	/* on most systems, the ratio between the sizes of framebuffer and window is 1:1;
	 * on some systems (most notably Mac with Retina screens), the display scale may be different.
	 */
	
	int[] fbWidth = { 0 };
	int[] fbHeight = { 0 };
	int[] scWidth = { 0 };
	int[] scHeight = { 0 };
	
	GLFW.glfwGetFramebufferSize(window, fbWidth, fbHeight);
	GLFW.glfwGetWindowSize(window, scWidth, scHeight);

	float displayScale = fbWidth[0] / (float) scWidth[0];

	return displayScale;
    }
    
    private void setFramebufferSizeCallback() {
	LwjglWindow lwjglContext = (LwjglWindow) context; // context is the Jme context
	long wh = lwjglContext.getWindowHandle();

	float displayScale = getDisplayScale(wh);
	
	/* some screens (e.g. Retina for MacBook Pro) require this */
	GLFW.glfwSetFramebufferSizeCallback(wh, (long window, int width, int height) -> {
		GLFW.glfwSetWindowSize(window, (int) (width / displayScale), (int) (height / displayScale));
		// or should it be:
		//GL33.glViewport(0, 0, (int) (width / displayScale), (int) (height / displayScale));
		}
	    );
	
    }

    private void initScreen() {
	// attempt to fix the resolution on Retina screens (should be neutral for other kinds of screens)
	
	LwjglWindow lwjglContext = (LwjglWindow) context; // context is the Jme context
	long window = lwjglContext.getWindowHandle();
	float displayScale = getDisplayScale(window);
	int[] fbWidth = { 0 };
	int[] fbHeight = { 0 };
	
	GLFW.glfwGetFramebufferSize(window, fbWidth, fbHeight);
	int width = fbWidth[0];
	int height = fbHeight[0];
	
	GLFW.glfwSetWindowSize(window, (int) (width / displayScale), (int) (height / displayScale));
	// or should it be:
	//GL33.glViewport(0, 0, (int) (width / displayScale), (int) (height / displayScale));
    }
    
    @Override
    public void simpleInitApp() {
	initScreen();
	setFramebufferSizeCallback();
	//flyCam.setEnabled(false);
	
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        rootNode.attachChild(geom);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    private static JmeContext.Type parseContextArg(final String contextArg) {
	switch (contextArg.toLowerCase()) {
	case "display": return Type.Display;
	case "canvas": return Type.Canvas;
	case "headless": return Type.Headless;
	case "offscreen": return Type.OffscreenSurface;
	default:
	    System.err.println("Unknown context: " + contextArg + " (passed as first argument). Valid values are: display, canvas, headless and offscreen");
	    System.exit(1);
	}

	return Type.Display; // in principle unreachable, but to avoid a warning from some compilers
    }
    
    private static void showArguments(final String[] args) {
	for (int i = 0 ; i < args.length ; i++) {
	    System.out.println("argument " + i + ": " + args[i]);
	}
    }
}
