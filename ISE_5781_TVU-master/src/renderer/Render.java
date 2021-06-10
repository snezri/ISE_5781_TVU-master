package renderer;

import Primitives.Color;
import Primitives.Ray;
import elements.Camera;
import scene.Scene;

import java.util.MissingResourceException;
/**
 * @author Dina Hayoun and Sarah Nezri
 *
 */
public class Render {
    ImageWriter imageWriter;
    Camera camera;
    RayTracerBase _rayTracerBase;
    Scene scene;

    /**
     * @param imageWriter
     * @return the object
     */
    public Render setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    public Render setScene(Scene scene) {
        this.scene = scene;
        return this;
    }

    /**
     * @param camera
     * @return the object
     */
    public Render setCamera(Camera camera) {
        this.camera = camera;
        return this;
    }

    /**
     * @param _rayTracerBase
     * @return the object
     */
    public Render setRayTracerBase(RayTracerBase _rayTracerBase) {
        this._rayTracerBase = _rayTracerBase;
        return this;
    }

    /**
     * The renderImage function doesn't return any value but first it checks if there are no
     * empty value in the attributes otherwise it throws an exception.
     */
    public void renderImage() {
        try {
            if (imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");

            }
            if (camera == null) {
                throw new MissingResourceException("missing resource", Camera.class.getName(), "");
            }
            if (_rayTracerBase == null) {
                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
            }
            //rendering the image
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    Ray ray = camera.constructRayThroughPixel(nX, nY, j, i);
                    Color pixelColor = _rayTracerBase.traceRay(ray);
                    imageWriter.writePixel(j, i, pixelColor);
                }
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
    }

    /**
     * The printGrid function first checks if there is no empty value int the imageWriter attribute
     * otherwise it throws an exception. It then create a grid.
     *
     * @param interval
     * @param color
     */
    public void printGrid(int interval, Color color) {
        try {
            if (imageWriter == null)
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
    }

    /**
     * The writeToImage function first checks if there is no empty value int the imageWriter attribute
     * otherwise it throws an exception. It then calls writeToImage with the imageWriter attribute.
     */
    public void writeToImage() {
        try {
            if (imageWriter == null)
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
        imageWriter.writeToImage();
    }


    @Override
    public String toString() {
        return "Render [imageWriter=" + imageWriter + ", camera=" + camera + ", rayTracerBase="
                + _rayTracerBase + "]";
    }
}