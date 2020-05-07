package com.yixin.pdf.test;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @Auther lujunhao
 * @Date 2020/4/28
 */
public class TestPdfImgMark {

    /**
     *
     * @param InPdfFile     要加水印的原pdf文件路径
     * @param outPdfFile    加了水印后要输出的路径
     * @param markImagePath 水印图片路径
     * @param imgWidth      图片横坐标
     * @param imgHeight     图片纵坐标
     * @param isTiled     是否平铺
     * @param tranSparency    透明度
     * @param magnification    放大倍数  100% 输入1为原图大小
     * @param watermarkangle   旋转角度
     *
     */
    public static void addPdfImgMark(String InPdfFile,
                                     String outPdfFile,
                                     String markImagePath,
                                     int imgWidth, int imgHeight,
                                     boolean isTiled,
                                     float tranSparency,
                                     float magnification,
                                     int watermarkangle) {
        try {
            PdfReader reader = new PdfReader(InPdfFile, "PDF".getBytes());
            PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(new File(outPdfFile)));
            PdfContentByte under;
            Rectangle pageRect = null;
            PdfGState gs1 = new PdfGState();
            //透明度设置
            gs1.setFillOpacity(tranSparency);
            //插入图片水印
            Image img = Image.getInstance(markImagePath);
            //旋转 弧度
            img.setRotation(-20);
            // 旋转 角度
            img.setRotationDegrees(watermarkangle);
            //      img.scaleAbsolute(100,100);//自定义大小
            //依照比例缩放
            img.scalePercent((int)(magnification*100));
            int imgWidth1 = (int) img.getWidth();
            int imgHeight1 = (int) img.getHeight();
            System.out.println(imgHeight1);
            System.out.println(imgWidth1);
            img.getHeight();
            // 原pdf文件的总页数
            int pageSize = reader.getNumberOfPages();
            int PDFheight = 0;
            int PDFwidth = 0;
            for (int i = 1; i <= pageSize; i++) {
                if (isTiled) {
                    //获取每一页pdf对象
                    pageRect = reader.getPageSizeWithRotation(i);
                    //页的长度
                    PDFheight = (int) pageRect.getHeight();
                    //页的宽度
                    PDFwidth = (int) pageRect.getWidth();
                    System.out.println("PDFH" + PDFheight);
                    System.out.println("PDFW" + PDFwidth);
                    for (int h = imgHeight1 + 5; h < PDFheight; h = h + imgHeight1) {
                        System.out.println("h" + h);
                        for (int w = imgWidth1 + 5; w < PDFwidth; w = w + imgWidth1 * 5) {
                            System.out.println("w" + w);
                            // 坐标
                            img.setAbsolutePosition(w - imgWidth1, h - imgHeight1);
                            // 水印在之前文本下
                            under = stamp.getUnderContent(i);
                            // 图片水印 透明度
                            under.setGState(gs1);
                            // 图片水印
                            under.addImage(img);
                        }
                    }
                } else {
                    // 坐标
                    img.setAbsolutePosition(imgWidth, imgHeight);
                    // 水印在之前文本下
                    under = stamp.getUnderContent(i);
                    // 图片水印 透明度
                    under.setGState(gs1);
                    // 图片水印
                    under.addImage(img);
                }
            }
            // 关闭
            stamp.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        TestPdfImgMark.addPdfImgMark("E:\\file2\\1502349579058.pdf",
                "E:\\file2" + File.separator + "mark" + File.separator + "mark1111upGRAY.pdf",
                "E:\\file2\\timg.png",
                20, 800,
                false,
                0.4f,
                0.3f,
                45
        );

    }
}
