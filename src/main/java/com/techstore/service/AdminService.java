package com.techstore.service;

import com.techstore.dao.AdminDAO;
import com.techstore.daoimpl.AdminDAOImpl;
import com.techstore.model.Admin;

public class AdminService {

    private AdminDAO adminDAO;

    public AdminService() {

        adminDAO = new AdminDAOImpl();

    }

    public boolean addAdmin(Admin admin) {

        return adminDAO.addAdmin(admin);

    }

    public Admin loginAdmin(String email,String password) {

        return adminDAO.loginAdmin(email,password);

    }

}