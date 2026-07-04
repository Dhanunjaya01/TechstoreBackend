package com.techstore.dao;

import com.techstore.model.Admin;

public interface AdminDAO {

    boolean addAdmin(Admin admin);

    Admin loginAdmin(String email, String password);

    Admin getAdminById(int adminId);

    boolean updateAdmin(Admin admin);

    boolean deleteAdmin(int adminId);

}
