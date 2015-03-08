/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infonal.managedbean;

import com.infonal.bean.UserBean;
import com.infonal.hibernate.HibernateUtil;
import com.infonal.managedbeanimpl.ManagedBeanImpl;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author ömer
 */
//Managed Bean Class

public class UserManagedBean implements Serializable, ManagedBeanImpl {

    private int id;
    private String userName;
    private String userSurname;
    private String userPhoneNumber;
    private UserBean selectedUser;

    //Log4j for logging
    static final Logger log=Logger.getLogger(UserManagedBean.class);
    
    //getters and setters
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public UserBean getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(UserBean selectedUser) {
        this.selectedUser = selectedUser;
    }

    //get all saved users
    
    @Override
    public List<UserBean> getAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<UserBean> userList = session.createCriteria(UserBean.class).list();
        return userList;
    }

    //save a new user
    
    @Override
    public String save() {
        String result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        UserBean user = new UserBean();
        user.setName(this.getUserName());
        user.setSurname(this.getUserSurname());
        user.setPhoneNumber(this.getUserPhoneNumber());

        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            addMessage("added successfully!",null);
            result = "success";
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                result = "error";
                addMessage("error! not added",null);
                log.error(e);
            }
        } finally {
            session.close();
        }

        return result;

    }

    //delete selected user
    
    @Override
    public void delete() {
        String result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            UserBean user = getSelectedUser();
            session.delete(user);
            tx.commit();
            addMessage("deleted successfully!",null);
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                addMessage("error! not deleted!",null);
                log.error(e);    
            }
        } finally {
            session.close();
        }
    }

    //update selected user
    
    @Override
    public String update() {

        String result = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        int selectedId = getSelectedUser().getId();

        try {
            tx = session.beginTransaction();
            UserBean user = (UserBean) session.get(UserBean.class, selectedId);

            user.setName(this.getUserName());
            user.setSurname(this.getUserSurname());
            user.setPhoneNumber(this.getUserPhoneNumber());
            session.update(user);
            tx.commit();
            addMessage("edited successfully!",null);
            result = "success";

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                addMessage("error! not edited!",null);
                result = "error";
                log.error(e);
            }
        } finally {
            session.close();
        }
        return result;
    }

    
    //add message for ajax requests
    
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
