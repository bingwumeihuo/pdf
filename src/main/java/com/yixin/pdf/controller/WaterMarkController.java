package com.yixin.pdf.controller;

import com.yixin.pdf.entity.WaterMark;
import com.yixin.pdf.service.WaterMarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mawenxin
 * @date 2020-05-07 15:41
 */
@Api(value = "/pdf", tags = "添加水印")
@RestController
@CrossOrigin
@RequestMapping("/pdf")
public class WaterMarkController {
    @Autowired
    WaterMarkService waterMarkService;
    @ApiOperation(value = "添加水印", notes = "添加水印")
    @PostMapping
    public String add(@RequestBody WaterMark waterMark){
        waterMarkService.add(waterMark);
        return "添加水印完成";
    }
}
