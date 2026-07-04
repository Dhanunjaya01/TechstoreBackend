package com.techstore.service;

import com.techstore.dao.AddressDAO;
import com.techstore.daoimpl.AddressDAOImpl;
import com.techstore.model.Address;

public class AddressService {

    private AddressDAO addressDAO;

    public AddressService() {

        addressDAO = new AddressDAOImpl();

    }

    public int saveAddress(Address address) {

        return addressDAO.saveAddress(address);

    }

}