package com.socnet.dao;

import com.socnet.model.User;

interface UserDAO {

	public void addAccount(User user);

	public void deleteAccount(User user);

}
