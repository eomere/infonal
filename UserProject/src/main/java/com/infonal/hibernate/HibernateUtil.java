/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infonal.hibernate;

/**
 *
 * @author ömer
 */


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author onlinetechvision.com
 * @since 3 Oct 2011
 * @version 1.0.0
 *
 */
public class HibernateUtil {

 private static SessionFactory sessionFactory = null;

 public static SessionFactory getSessionFactory() {
  if(sessionFactory == null) {
   sessionFactory = new Configuration().configure().buildSessionFactory();
  }
  return sessionFactory;
 }

 public static void setSessionFactory(SessionFactory sessionFactory) {
  HibernateUtil.sessionFactory = sessionFactory;
 }

}

