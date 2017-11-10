/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymec.services;

import com.polymec.domain.Docs;
import java.util.List;

/**
 *
 * @author Hedi
 */
public interface DocsService {

    Docs findById(Long docId);
    List<Docs> listDocs();    
}
