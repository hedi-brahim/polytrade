package com.polymec.service;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;

import com.polymec.model.Article;
import com.polymec.repository.ArticleRepository;

@Service("jpaArticleService")
public class ArticleServiceImpl implements ArticleService {

    //private static final int PAGE_SIZE = 3;
    //@Inject 
    @Autowired
    private ArticleRepository articleRepository;

    /*
    public Page<Article> getArticles(Integer pageNumber) {
        PageRequest request =
            new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "startTime");
        return articleRepository.findAll(pageRequest);
    }
     */
    //private Log log = LogFactory.getLog(ArticleServiceImpl.class);
    //@Autowired
    //private ArticleRepository articleRepository;
    //@Autowired
    //private PagingAndSortingRepository<Article, String> articleRepository; 
    //= // … get access to a bean
//Page<User> users = repository.findAll(new PageRequest(1, 20));	
    //@PersistenceContext
    //private EntityManager em;
    // @Transactional(readOnly=true) annotation means we want the transaction to be set as read-only. 
    // Setting that attribute for read only methods will result in better performance.
    //@Transactional(readOnly=true)
    @Override
    public List<Article> findAll() {
        return (new ArrayList<Article>(articleRepository.findAll()));
    }

    /*
    @Override
    public List<Article> findAllWithDetail() {
        return Lists.newArrayList(articleRepository.findAllWithDetail());
    }
     */
 /*
    @Transactional(readOnly=true)
    @Override
    public Page<Article> findAll() {
        return articleRepository.findAll(new PageRequest(1, 3));
    }	
     */
    //@Transactional(readOnly=true)
    public List<Article> findByReference(String reference) {
        return articleRepository.findByReference(reference);
    }

    /*
	@Transactional(readOnly=true)
	public List<Article> findByFirstNameAndLastName(String firstName, String lastName) {
		return articleRepository.findByFirstNameAndLastName(firstName, lastName);
	}
     */
    @Override
    //@Transactional(readOnly=true)
    public Article findById(Long id) {
        return articleRepository.findOne(id);
    }

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void delete(Article article) {
        articleRepository.delete(article);
    }

}
