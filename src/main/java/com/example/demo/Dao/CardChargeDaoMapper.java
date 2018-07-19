package com.example.demo.Dao;

import com.example.demo.Bean.CardCharge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CardChargeDaoMapper {

    public CardCharge getcardinfo(String stu_number);

    public List<CardCharge> findAllCardInfo();

    public int countAllCardInfo();

    public void saveCardinfo(CardCharge cardCharge);

    public void chargeFree(CardCharge cardCharge);

    public void deleteCardInfo(String stu_number);
}
