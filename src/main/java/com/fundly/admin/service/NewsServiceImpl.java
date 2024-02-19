package com.fundly.admin.service;

import com.fundly.admin.model.NewsDao;
import com.persistence.dto.NewsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class NewsServiceImpl implements NewsService{
    @Autowired
    NewsDao newsDao;
    @Override
    public int insertNews(NewsDto dto) throws Exception {
        return newsDao.insert(dto);
    }

    @Override
    public NewsDto selectNews(Integer news_seq) throws Exception {
        newsDao.increaseView(news_seq);
        return newsDao.select(news_seq);
    }

    @Override
    public List<NewsDto> selectAllNews() throws Exception {
        return newsDao.selectAll();
    }

    @Override
    public int deleteNews(Integer news_seq, String reg_id) throws Exception {
        Map map = new HashMap();
        map.put("news_seq",news_seq);
        map.put("reg_id",reg_id);
        return newsDao.delete(map);
    }

    @Override
    public int deleteAll() {
        return newsDao.deleteAll();
    }

    @Override
    public int count() throws Exception {
        return newsDao.count();
    }

    @Override
    public int updateNews(NewsDto dto) throws Exception {
        return newsDao.update(dto);
    }

    @Override
    public List<NewsDto> selectPage(Integer page,Integer pageSize) throws Exception {
        Map map = new HashMap();
        map.put("offset",(page-1)*10);
        map.put("pageSize",pageSize);

        return newsDao.selectPage(map);
    }

    @Override
    public List<NewsDto> searchNews(String news_title) throws Exception {
        news_title = '%'+news_title+'%';
        System.out.println("���񽺿�������Ÿ��Ʋ"+news_title);
        return  newsDao.search(news_title);
    }
}
