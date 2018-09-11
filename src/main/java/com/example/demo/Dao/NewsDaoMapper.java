package com.example.demo.Dao;

import com.example.demo.Bean.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsDaoMapper {

    public int deleteNews(String news_id);

    public List<News> findAllNewsList();

    public List<News> getRecentNewsListtitle();

    public List<News> getRecentNewsListtitleByType(String news_type);

    int getAllNewsRecordNumber();

    News getRecentNewsListContent(String news_titles);

    int updateNews(News news);

    int SaveNews(News news);
}
