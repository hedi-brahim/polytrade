/*
 * Copyright 2017 Hedi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.polymec.controller;

import com.polymec.domain.ArticleInfo;
import com.polymec.service.ArticleInfoService;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.test.util.ReflectionTestUtils;


/**
 *
 * @author Hedi
 */
public class MainControllerTest {
    private final List<ArticleInfo> articles = new ArrayList<ArticleInfo>();
    
    @Before
    public void initContacts() {
        ArticleInfo art = new ArticleInfo();
        art.setId(1l);
        art.setReference("411007");
        art.setDesignation("Joint de culasse");
        articles.add(art);
    }
    
    @Test
    public void testList() throws Exception {
        ArticleInfoService articleInfoService = mock(ArticleInfoService.class);
        when(articleInfoService.listArticlesExistants()).thenReturn(articles);

        MainController mainController = new MainController();

        ReflectionTestUtils.setField(mainController, "articleInfoService", articleInfoService);

        ExtendedModelMap uiModel = new ExtendedModelMap();
        uiModel.addAttribute("articles", mainController.listArticlesExistants());

        List<ArticleInfo> modelArticles = (List<ArticleInfo>) uiModel.get("articles");

        assertTrue(!modelArticles.isEmpty());
    }
    
}
