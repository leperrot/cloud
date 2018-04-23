package org.iut.cloud;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.iut.cloud.model.Account;
import org.iut.cloud.model.Approval;
import org.iut.cloud.services.CheckAccount;

import com.googlecode.objectify.ObjectifyService;

public class Bootstrapper implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ObjectifyService.init();
		ObjectifyService.register(Account.class);
		ObjectifyService.register(Approval.class);
		ObjectifyService.register(CheckAccount.class);
	}

}
