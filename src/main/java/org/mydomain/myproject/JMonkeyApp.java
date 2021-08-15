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

    public static void main(String[] args) {
        JMonkeyApp app = new JMonkeyApp();
        app.run();
    }
    
    public JMonkeyApp() {
	AppSettings settings = new AppSettings(true);
        settings.setTitle("Default Scene");
        setSettings(settings);
    }

    @Override
    public void run() {
        // modes include: Type.Display (default), Type.Canvas, Type.Headless (server), Type.OffscreenSurface
	JmeContext.Type mode = Type.Display;
        start(mode);
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
}
