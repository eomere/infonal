/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infonal.managedbeanimpl;

import com.infonal.bean.UserBean;
import java.util.List;

/**
 *
 * @author ömer
 */
public interface ManagedBeanImpl {
    
    List<UserBean> getAllUsers();
    String save();
    String update();
    void delete();
    
}
