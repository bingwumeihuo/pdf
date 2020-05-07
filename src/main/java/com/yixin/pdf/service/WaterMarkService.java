package com.yixin.pdf.service;

import com.yixin.pdf.dao.WaterMarkDAO;
import com.yixin.pdf.entity.WaterMark;
import com.yixin.pdf.test.TestPdfTextMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mawenxin
 * @date 2020-05-07 15:39
 */
@Service
public class WaterMarkService {
    @Autowired
    private WaterMarkDAO waterMarkDAO;
    public void add(WaterMark waterMark){
        waterMarkDAO.save(waterMark);
        TestPdfTextMark testPdfTextMark = new TestPdfTextMark();
        testPdfTextMark.waterMark(waterMark);
    }

}
