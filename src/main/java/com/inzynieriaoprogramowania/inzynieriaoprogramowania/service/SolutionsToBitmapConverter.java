package com.inzynieriaoprogramowania.inzynieriaoprogramowania.service;

import com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.calculations.HeatMapSolutions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;

public class SolutionsToBitmapConverter {

    private double[][] values;
    private HeatMapSolutions heatMapSolutions;
    private int height;
    private int width;

    public SolutionsToBitmapConverter(HeatMapSolutions heatMapSolutions) {
        this.heatMapSolutions = heatMapSolutions;
        values = heatMapSolutions.heatArray;
        height = values.length;
        width = values[0].length;
    }

    public int getIntFromColor(int red, int green, int blue){
        red = (red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        green = (green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
        blue = blue & 0x000000FF; //Mask out anything not blue.

        return 0xFF000000 | red | green | blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
    }


    private static double hue2rgb(double p, double q, double h) {
        if (h < 0) {
            h += 1;
        }

        if (h > 1) {
            h -= 1;
        }

        if (6 * h < 1) {
            return p + ((q - p) * 6 * h);
        }

        if (2 * h < 1) {
            return q;
        }

        if (3 * h < 2) {
            return p + ((q - p) * 6 * ((2.0f / 3.0f) - h));
        }

        return p;
    }

    static public int[] hslColor(double h, double s, double l) {
        double q, p, r, g, b;

        if (s == 0) {
            r = g = b = l; // achromatic
        } else {
            q = l < 0.5 ? (l * (1 + s)) : (l + s - l * s);
            p = 2 * l - q;
            r = hue2rgb(p, q, h + 1.0f / 3);
            g = hue2rgb(p, q, h);
            b = hue2rgb(p, q, h - 1.0f / 3);
        }

        int[] colorRGB = new int[3];
        colorRGB[0] = (int)Math.round(r * 255);
        colorRGB[1] = (int)Math.round(g * 255);
        colorRGB[2] = (int)Math.round(b * 255);

        return colorRGB;
    }

    private ArrayList<Integer> doublesToInts(){
        ArrayList<Integer> array = new ArrayList<>();
        for(int i = 0; i < values.length; i++){
            for(int j=0; j < values[0].length; j++){
                /*HSL - Hue = dependent on HeatMapSolutions, Saturation = 100, Light = 60
                * Values of hue has to be between 0 and 60 ()*/
                int[] RGBcolor = hslColor(values[i][j], 1, 0.6);
                int intColor = getIntFromColor(RGBcolor[0], RGBcolor[1], RGBcolor[2]);
                array.add(intColor);
            }
        }
        return array;
    }

    private int[] arraylistToArray(){
        ArrayList<Integer> arrayList = doublesToInts();
        int[] array = new int[arrayList.size()];
        for(int i = 0; i<array.length; i++){
            array[i] = arrayList.get(i);
        }
        return array;
    }

    public BufferedImage createBitmap(){
        BufferedImage bufferedImage = new BufferedImage(this.width,this.height,BufferedImage.TYPE_INT_RGB);
        int[] res = arraylistToArray();
        for(int i = 0; i < res.length; i++){
            System.out.println(res[i]);
        }
        bufferedImage.setRGB(0, 0, width, height, res, 0, width);
        try {
            ImageIO.write(bufferedImage, "jpg", new File("/home/piotr/test.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }
}
