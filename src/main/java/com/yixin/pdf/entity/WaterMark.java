package com.yixin.pdf.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Mawenxin
 * @date 2020-05-07 14:56
 */
@Data
@Table(name = "sys_pdf")
@Entity
public class WaterMark implements Serializable {
    @Id
    private int Id;
    private String input_file;
    private String output_file;
    private String water_mark_name;
    private int is_tile;
    private int angle;
    private float tran_sparely;
    private int x;
    private int y;
    /**
     * 1 第一页
     * 2 全部
     * 3 奇数
     * 4 偶数
     *
     */
    private int status;
    private int start_page;
    private int end_page;


}
