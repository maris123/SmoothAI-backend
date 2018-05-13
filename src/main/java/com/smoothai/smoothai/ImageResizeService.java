package com.smoothai.smoothai;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageResizeService {

    public static String downsize(String filePath) {
        ConvertCmd cmd = new ConvertCmd();
        IMOperation op = new IMOperation();
        op.addImage(filePath);
        BufferedImage img;
        try {
            img = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return "Cannot get image from file";
        }
        if (img.getWidth() >=800) {
            int factor = img.getWidth()/800;
            op.resize(img.getWidth() / factor, img.getHeight() / factor);
        }
        op.addImage(filePath+"small");
        try {
            cmd.run(op);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IM4JavaException e) {
            e.printStackTrace();
        }
        return "Resize done successfully";
    }

}
