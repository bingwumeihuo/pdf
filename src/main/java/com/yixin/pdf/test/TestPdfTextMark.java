package com.yixin.pdf.test;

import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.yixin.pdf.entity.WaterMark;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * @Auther mawenxin
 * @Date 2020/4/28
 */
public class TestPdfTextMark {
    private final static int interval = -5;

    /**
     * 文字水印
     */
//    public static void waterMark(String inputFile,
//                                 String outputFile,
//                                 String waterMarkName,
//                                 int isTile,
//                                 int angle,
//                                 float tranSparely,
//                                 int x, int y,
//                                 int startPage,
//                                 int endPage ) {
    public void waterMark(WaterMark waterMark) {
        try {
            String uuid = UUID.randomUUID().toString();
            PdfReader reader = new PdfReader(waterMark.getInput_file());
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(
                    waterMark.getOutput_file() + uuid + ".pdf"));
            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            Rectangle pageRect;
            PdfGState gs = new PdfGState();
            gs.setFillOpacity(waterMark.getTran_sparely());
            gs.setStrokeOpacity(0.4f);
            int total = reader.getNumberOfPages();

            JLabel label = new JLabel();
            FontMetrics metrics;
            int textH;
            int textW;
            label.setText(waterMark.getWater_mark_name());
            metrics = label.getFontMetrics(label.getFont());
            textH = metrics.getHeight();
            textW = metrics.stringWidth(label.getText());
            PdfContentByte under;
            /**
             * 判断加水印页码模式
             */
            if (waterMark.getStatus() == 1) {
                waterMark.setStart_page(1);
                waterMark.setEnd_page(1);
            } else if (waterMark.getStatus() == 2) {
                waterMark.setStart_page(1);
                waterMark.setEnd_page(total);
            } else if (waterMark.getStatus() == 3) {
                waterMark.setStart_page(1);
                waterMark.setEnd_page(total);
            } else if (waterMark.getStatus() == 4) {
                waterMark.setStart_page(1);
                waterMark.setEnd_page(total);
            }
            for (int i = waterMark.getStart_page(); i <= waterMark.getEnd_page(); i++) {
                if (waterMark.getStatus() == 4 && i % 2 == 0) {
                    i++;
                } else if (waterMark.getStatus() == 3 && i % 2 != 0) {
                    if (i < total) {
                        i++;
                    }
                }
                pageRect = reader.getPageSizeWithRotation(i);
                under = stamper.getOverContent(i);
                under.saveState();
                under.setGState(gs);
                under.beginText();
                under.setFontAndSize(base, 20);
                // 水印文字成30度角倾斜
                //你可以随心所欲的改你自己想要的角度
                if (waterMark.getIs_tile() == 0) {
                    for (int height = interval + textH; height < pageRect.getHeight();
                         height = height + textH * 5) {
                        for (int width = interval + textW; width < pageRect.getWidth() + textW;
                             width = width + textW * 2) {
                            under.showTextAligned(Element.ALIGN_LEFT
                                    , waterMark.getWater_mark_name(), width - textW,
                                    height - textH, 45);
                        }
                    }
                } else {
                    under.showTextAligned(Element.ALIGN_LEFT
                            , waterMark.getWater_mark_name(), waterMark.getX(), waterMark.getY()
                            , waterMark.getAngle());
                }
                under.endText();
            }

            System.out.println("PDF一共" + total + "页");
            stamper.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString();
//        testPdfTextMark.waterMark("D:/1.pdf", "D:/mark/" + uuid + ".pdf",
//                "CES中信信息",
//                0, 0, 0.333f, 300, 420,1,1);
//        System.out.println("生成水印成功！！！");

    }

}
