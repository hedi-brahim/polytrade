package com.polymec.service;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;

import com.polymec.model.ArticleInfo;
import com.polymec.model.ArticleFrns;
import com.polymec.repository.ArticleFrnsRepository;

@Service("jpaArticleFrnsService")
public class ArticleFrnsServiceImpl implements ArticleFrnsService {

    //private static final int PAGE_SIZE = 3;
    //@Inject 
    @Autowired
    private ArticleFrnsRepository articleFrnsRepository;

    /*
    public Page<Article> getArticles(Integer pageNumber) {
        PageRequest request =
            new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "startTime");
        return articleRepository.findAll(pageRequest);
    }
     */
    //private Log log = LogFactory.getLog(ArticleFrnsServiceImpl.class);
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
    public List<ArticleInfo> findAllValid() {
        return (new ArrayList<ArticleInfo>(articleFrnsRepository.findAllValid()));
    }

    @Override
    public List<ArticleFrns> findByFamille(Long id) {
        return (new ArrayList<ArticleFrns>(articleFrnsRepository.findByFamille(id)));
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
    /*
	public List<ArticleFrns> findByReference(String reference) {
		return articleFrnsRepository.findByReference(reference);
	}
     */

 /*
	@Transactional(readOnly=true)
	public List<Article> findByFirstNameAndLastName(String firstName, String lastName) {
		return articleRepository.findByFirstNameAndLastName(firstName, lastName);
	}
     */
    @Override
    //@Transactional(readOnly=true)
    public ArticleFrns findById(Long id) {
        return articleFrnsRepository.findOne(id);
    }

    @Override
    public ArticleFrns save(ArticleFrns articleFrns) {
        return articleFrnsRepository.save(articleFrns);
    }

    @Override
    public void delete(ArticleFrns articleFrns) {
        articleFrnsRepository.delete(articleFrns);
    }

}
