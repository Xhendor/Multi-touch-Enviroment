package common.integration;

import common.integration.persistence.BaseDAO;
import common.integration.persistence.CriteriaUtil;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emmanuelle
 */
public class ServiceLocator {
    private static BaseDAO dao;
    private static CriteriaUtil criteriaUtil;

   

    //Consultar catálogos y clases con interés de CRUD
    public static BaseDAO getInstance() {
        if (dao == null) {
            dao = new BaseDAO();
            return dao;
        } else {
            return dao;
        }
    }

    public static CriteriaUtil getInstanceCriteria(Class tipo) {
        if (criteriaUtil == null) {
            criteriaUtil = new CriteriaUtil(tipo);
            return criteriaUtil;
        } else {
            criteriaUtil.setTipoClass(tipo);
            return criteriaUtil;
        }
    }

    


    

        

}
