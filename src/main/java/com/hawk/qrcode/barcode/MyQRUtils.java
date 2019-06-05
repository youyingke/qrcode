package com.hawk.qrcode.barcode;

/**
 * Created by Lenovo on 2019-06-04.
 */

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;


public class MyQRUtils{



    private static final Log logger = LogFactory.getLog(MyQRUtils.class);



    private static final int BLACK = 0xFF000000;

    private static final int WHITE = 0xFFFFFFFF;

    private static final int LogoPart = 4;



    /**

     * 生成二维码前的配置信息

     * @param content 生成二维码图片内容

     * @param width   二维码图片的宽度

     * @param height  二维码图片的高度

     * @return

     */

    public static BitMatrix setBitMatrix(String content,int width,int height){

        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();

        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  //指定纠错等级

        BitMatrix bitMatrix=null;

        try {

            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

        } catch (WriterException e) {

            logger.error("生成二维码错误",e);

        }

        return bitMatrix;

    }



    /**

     * 将LOGO图片放在二维码中间（水印效果）

     * 将生成的图片以流的形式输出到页面展示

     * @param matrix          BitMatrix

     * @param format          图片格式

     * @param outStream       输出流

     * @param logoPath        LOGO地址

     * @param showBottomText  二维码图片底部需要显示的问题

     * @throws IOException

     */

    public static void megerToFile(BitMatrix matrix,String format,OutputStream outStream,String logoPath,String showBottomText) throws IOException {

        BufferedImage image = toBufferedImage(matrix);

        Graphics2D gs = image.createGraphics();



        //1.加入LOGO水印效果

        if(null != logoPath && !"".equals(logoPath)){

            //1.1 载入LOGO图片

            BufferedImage logoImg = ImageIO.read(new File(logoPath));

            //1.2 考虑到LOGO图片贴到二维码中，建议大小不要超过二维码的1/5;

            int width = image.getWidth() / LogoPart;

            int height = image.getHeight() / LogoPart;

            //1.3 LOGO居中显示

            int x = (image.getWidth() - width) / 2;

            int y = (image.getHeight() - height) / 2;

            gs.drawImage(logoImg, x, y, logoImg.getWidth(), logoImg.getHeight(), null);

            logoImg.flush();

        }

        //2.二维码图片底部插入文字

        if(null != showBottomText && !"".equals(showBottomText)){

            //2.1 设置字体样式

            Font font = new Font("微软雅黑", Font.PLAIN, 14);

            gs.setColor(Color.BLACK);

            gs.setFont(font);

            //2.2 字体显示位置

            int x = (image.getWidth() - getWatermarkLength(showBottomText, gs))/2;

            int y = image.getHeight()-2;

            gs.drawString(showBottomText, x, y);

        }

        gs.dispose();

        ImageIO.write(image, format, outStream);

    }



    /**

     * 将LOGO图片放在二维码中间（水印效果）

     * 将生成的图片生成到本地硬盘路径下

     * @param matrix          BitMatrix

     * @param format          图片格式

     * @param imagePath       图片存放路径

     * @param logoPath        LOGO地址

     * @param showBottomText  二维码图片底部需要显示的问题

     * @throws IOException

     */

    public static void megerToFile2(BitMatrix matrix,String format,String imagePath,String logoPath,String showBottomText) throws IOException {

        BufferedImage image = toBufferedImage(matrix);

        Graphics2D gs = image.createGraphics();



        //1.加入LOGO水印效果

        if(null != logoPath && !"".equals(logoPath)){

            BufferedImage logoImg = ImageIO.read(new File(logoPath));

            int width = image.getWidth() / LogoPart;

            int height = image.getHeight() / LogoPart;

            int x = (image.getWidth() - width) / 2;

            int y = (image.getHeight() - height) / 2;

            gs.drawImage(logoImg, x, y, logoImg.getWidth(), logoImg.getHeight(), null);

            logoImg.flush();

        }



        //2.二维码图片底部插入文字

        if(null != showBottomText && !"".equals(showBottomText)){

            //2.1 设置字体样式

            Font font = new Font("微软雅黑", Font.PLAIN, 14);

            gs.setColor(Color.BLACK);

            gs.setFont(font);

            //2.2 字体显示位置

            int x = (image.getWidth() - getWatermarkLength(showBottomText, gs))/2;

            int y = image.getHeight()-2;

            gs.drawString(showBottomText, x, y);

        }

        gs.dispose();

        ImageIO.write(image, format, new File(imagePath));

    }



    /**

     * 获取水印字体的长度

     * @param fontString

     * @param gs

     * @return

     */

    public static int getWatermarkLength(String fontString,Graphics2D gs){

        return gs.getFontMetrics(gs.getFont()).charsWidth(fontString.toCharArray(),0,fontString.length());

    }



    public static BufferedImage toBufferedImage(BitMatrix matrix){

        int width = matrix.getWidth();

        int height = matrix.getHeight();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);



        for(int x=0;x<width;x++){

            for(int y=0;y<height;y++){

                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);

            }

        }

        return image;

    }

}
