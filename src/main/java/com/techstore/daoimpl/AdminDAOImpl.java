package com.techstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.techstore.dao.AdminDAO;
import com.techstore.model.Admin;
import com.techstore.util.DBConnection;

public class AdminDAOImpl implements AdminDAO {

    private Connection connection;

    public AdminDAOImpl() {
        connection = DBConnection.getConnection();
    }

    @Override
    public boolean addAdmin(Admin admin) {

        String sql = "INSERT INTO admin(full_name,email,password,phone_no,role,status) VALUES(?,?,?,?,?,?)";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, admin.getFullName());
            ps.setString(2, admin.getEmail());
            ps.setString(3, admin.getPassword());
            ps.setString(4, admin.getPhoneNo());
            ps.setString(5, admin.getRole());
            ps.setString(6, admin.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Admin loginAdmin(String email, String password) {

        String sql = "SELECT * FROM admin WHERE email=? AND password=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Admin admin = new Admin();

                admin.setAdminId(rs.getInt("admin_id"));
                admin.setFullName(rs.getString("full_name"));
                admin.setEmail(rs.getString("email"));
                admin.setRole(rs.getString("role"));
                admin.setStatus(rs.getString("status"));

                return admin;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public Admin getAdminById(int adminId) {

        return null;
    }

    @Override
    public boolean updateAdmin(Admin admin) {

        return false;
    }

    @Override
    public boolean deleteAdmin(int adminId) {

        return false;
    }

}