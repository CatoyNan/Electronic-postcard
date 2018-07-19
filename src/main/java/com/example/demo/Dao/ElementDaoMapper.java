package com.example.demo.Dao;

import com.example.demo.Bean.Element;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ElementDaoMapper {

    public void saveElemnetUser(Element element);

    public Element getElemntbyStaynumber(String staynumber);

    public Element getElemntbynumber(String elementNumber);

    public List<Element> findAllElemntUser();

    public void deleteElemntuser(String elmentNumber);

    public void updateElemnt(Element element);

    public int countElemntUser();

    public void updateElementUser(Element element);
}
