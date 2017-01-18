package com.polymec.services;

import java.util.List;

import com.polymec.domain.Credit;

public interface CreditService {

    List<Credit> listReglements();

    List<Credit> listAchatsReglements();
}
