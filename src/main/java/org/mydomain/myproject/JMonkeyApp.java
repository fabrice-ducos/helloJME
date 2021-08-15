package org.mydomain.myproject;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;
import com.jme3.system.JmeContext.Type;

public class JMonkeyApp extends SimpleApplication implements Runnable {

    // modes include: Type.Display (default), Type.Canvas, Type.Headless (server), Type.OffscreenSurface
    JmeContext.Type context;
    
    public static void main(String[] args) {
	JmeContext.Type context = Type.Display;
	showArguments(args);
	if (args.length > 0) {
	    context = parseContextArg(args[0]);
	}
        JMonkeyApp app = new JMonkeyApp(context);
        app.run();
    }
    
    public JMonkeyApp(JmeContext.Type context) {
	this.context = context;
	AppSettings settings = new AppSettings(true);
        settings.setTitle("Default Scene");
        setSettings(settings);
    }

    @Override
    public void run() {
        start(context);
    }

    @Override
    public void simpleInitApp() {
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
