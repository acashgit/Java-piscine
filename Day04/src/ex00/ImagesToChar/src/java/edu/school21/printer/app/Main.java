package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        char white;
        char black;
        BufferedImage image = null;

        if (args.length < 3 || args[0].length() > 1 || args[1].length() > 1){
            System.err.println("wrong input");
            return;
        }

        white = args[0].charAt(0);
        black = args[1].charAt(0);

        try {
            image = ImageIO.read(new File(args[2]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Logic logic = new Logic(white, black, image);
        logic.printing();
    }
}
