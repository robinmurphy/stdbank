package com.sbg.automation.vending.jpa.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;

@Component
public class DbUtil {

    @Autowired
    EntityManager em;

    public Long getSequenceNextVal() {
        Query q = em.createNativeQuery("select nextval('SBG_SALES_SEQ')");
        return ((BigInteger) q.getSingleResult()).longValue();
    }
}
