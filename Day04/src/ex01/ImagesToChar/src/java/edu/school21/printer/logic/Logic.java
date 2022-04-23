package edu.school21.printer.logic;

import java.awt.image.BufferedImage;

public class Logic {
    private char white;
    private char black;
    private BufferedImage image;

    public Logic(char white, char black, BufferedImage image){
        this.black = black;
        this.white = white;
        this.image = image;
    }

    public void printing(){
        for (int i = 0; i < image.getHeight(); i++){
            for (int j = 0; j < image.getWidth(); j++){
                int pixel = image.getRGB(j, i);
                if ((pixel & 0x00FFFFFF) == 0)	{
                    System.out.print(black);
                } else {
                    System.out.print(white);
                }
            }
            System.out.println();
        }
    }
}