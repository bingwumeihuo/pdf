package com.yixin.pdf.dao;

import com.yixin.pdf.entity.WaterMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WaterMarkDAO extends JpaRepository<WaterMark,Integer>,
        JpaSpecificationExecutor<WaterMark> {
}