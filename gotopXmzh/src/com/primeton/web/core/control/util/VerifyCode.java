// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   VerifyCode.java

package com.primeton.web.core.control.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import com.gotop.util.security.ForUtil;

public class VerifyCode
{

    private int wordHeight;
    private int wordWidth;
    private int imageHeight;
    private String type;
    private int initypos;
    private int charCount;
    private static final Color CHAR_COLOR[];
    private Random r;
    public static String GRAPHIC_JPEG = "JPEG";
    public static String GRAPHIC_PNG = "PNG";

    protected VerifyCode(int length, String type, int height)
    {
        wordHeight = 7;
        wordWidth = 11;
        imageHeight = 21;
        this.type = "number";
        initypos = 5;
        charCount = 0;
        r = new Random();
        charCount = length;
        this.type = type;
        imageHeight = height;
    }

    protected String draw(OutputStream out)
        throws IOException
    {
        String code = null;
        if(type.equals("char"))
            code = drawAlpha(GRAPHIC_PNG, out);
        else
            code = drawNumber(GRAPHIC_PNG, out);
        return code;
    }

    public String drawNumber(String graphicFormat, OutputStream out)
        throws IOException
    {
        String charValue = "";
        charValue = randNumber();
        return draw(charValue, graphicFormat, out);
    }

    public String drawAlpha(String graphicFormat, OutputStream out)
        throws IOException
    {
        String charValue = "";
        charValue = randAlpha();
        return draw(charValue, graphicFormat, out);
    }

    protected String draw(String charValue, String graphicFormat, OutputStream out)
        throws IOException
    {
        int w = (charCount + 2) * wordWidth;
        int h = imageHeight;
        wordHeight = h / 3;
        initypos = wordHeight;
        BufferedImage bi = new BufferedImage(w, h, 5);
        Graphics2D g = bi.createGraphics();
        Color backColor = Color.WHITE;
        g.setBackground(backColor);
        g.fillRect(0, 0, w, h);
        for(int i = 0; i < charCount; i++)
        {
            String c = charValue.substring(i, i + 1);
            g.setFont(new Font(null, randomInt(0, 2) != 1 ? 1 : 3, randomInt(16, 20)));
            Color color = new Color(randomInt(0, 150), randomInt(0, 150), randomInt(0, 150));
            g.setColor(color);
            int xpos = (i + 1) * wordWidth;
            int ypos = randomInt(initypos + wordHeight, initypos + wordHeight + initypos);
            g.drawString(c, xpos, ypos);
        }

        for(int i = 0; i < 100 * charCount; i++)
        {
            Color color = new Color(randomInt(100, 255), randomInt(100, 255), randomInt(100, 255));
            int x = randomInt(0, w);
            int y = randomInt(0, w);
            g.setColor(color);
            g.drawLine(x, y, x, y);
        }

        for(int i = 0; i < charCount * 2; i++)
        {
            Color color = new Color(randomInt(100, 255), randomInt(100, 255), randomInt(100, 255));
            int x = randomInt(0, w);
            int y = randomInt(0, w);
            int x1 = randomInt(0, w);
            int y2 = randomInt(0, w);
            g.setColor(color);
            g.drawLine(x, y, x1, y2);
        }

        g.dispose();
        bi.flush();
        ImageIO.write(bi, graphicFormat, out);
        return charValue;
    }

    protected String randNumber()
    {
        String charValue = "";
        for(int i = 0; i < charCount; i++)
            charValue = (new StringBuilder()).append(charValue).append(String.valueOf(randomInt(0, 10))).toString();

        return charValue;
    }

    private String randAlpha()
    {
        String charValue = "";
        for(int i = 0; i < charCount; i++)
        {
            char c = (char)(randomInt(0, 26) + 97);
            charValue = (new StringBuilder()).append(charValue).append(String.valueOf(c)).toString();
        }

        return charValue;
    }

    protected int randomInt(int from, int to)
    {
        return from + ForUtil.rNextInt(to - from);
    }

    public static byte[] generate(String name, int length, String type, int height, HttpSession session)
        throws IOException
    {
        ByteArrayOutputStream outByteArray = new ByteArrayOutputStream();
        VerifyCode vc = new VerifyCode(length, type, height);
        String code = vc.draw(outByteArray);
        session.setAttribute(name, code);
        return outByteArray.toByteArray();
    }

    public static void main(String args1[])
        throws Exception
    {
    }

    static 
    {
        CHAR_COLOR = (new Color[] {
            Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA
        });
    }
}
