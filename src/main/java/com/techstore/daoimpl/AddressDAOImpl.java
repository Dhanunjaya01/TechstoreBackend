package com.techstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.techstore.dao.AddressDAO;
import com.techstore.model.Address;
import com.techstore.util.DBConnection;

public class AddressDAOImpl implements AddressDAO {

	private static final String INSERT_ADDRESS =
			"INSERT INTO addresses(user_id, full_name, phone_no, house_no, street, landmark, city, state, pincode, country, address_type, is_default) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

    @Override
    public int saveAddress(Address address) {

        int addressId = 0;

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            INSERT_ADDRESS,
                            PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, address.getUserId());
            preparedStatement.setString(2, address.getFullName());
            preparedStatement.setString(3, address.getPhoneNo());
            preparedStatement.setString(4, address.getHouseNo());
            preparedStatement.setString(5, address.getStreet());
            preparedStatement.setString(6, address.getLandmark());
            preparedStatement.setString(7, address.getCity());
            preparedStatement.setString(8, address.getState());
            preparedStatement.setString(9, address.getPincode());
            preparedStatement.setString(10, address.getCountry());
            preparedStatement.setString(11, address.getAddressType());
            preparedStatement.setBoolean(12, address.isDefault());
            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {

                ResultSet rs = preparedStatement.getGeneratedKeys();

                if (rs.next()) {

                    addressId = rs.getInt(1);

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return addressId;

    }

}