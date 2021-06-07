package unittests.renderer;

import Primitives.Color;
import org.junit.Test;
import renderer.ImageWriter;

import static org.junit.Assert.*;

public class ImageWriterTest {

    @Test
    public void writeToImage() {
        ImageWriter imageWriter = new ImageWriter("testblue",800,500);
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 500; j++) {
                // 800/16 = 50
                if (i % 50 == 0) {
                    imageWriter.writePixel(i, j, Color.BLACK);
                }
                // 500/10 = 50
                else if (j % 50 == 0) {
                    imageWriter.writePixel(i, j, Color.BLACK);
                } else {
                    imageWriter.writePixel(i, j, Color.GREEN);
                }
            }
        }
        imageWriter.writeToImage();
    }

}