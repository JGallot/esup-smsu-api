/**
 * SMS-U - Copyright (c) 2009-2014 Universite Paris 1 Pantheon-Sorbonne
 */
package org.esupportail.smsuapi.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.esupportail.smsuapi.dao.beans.Account;
import org.esupportail.smsuapi.dao.beans.Application;
import org.esupportail.smsuapi.dao.beans.Blacklist;
import org.esupportail.smsuapi.dao.beans.Sms;
import org.esupportail.smsuapi.dao.beans.Statistic;
import org.esupportail.smsuapi.domain.beans.sms.SmsStatus;

/**
 * The DAO service interface.
 */
public interface DaoService extends Serializable {
	
	//////////////////////////////////////////////////////////////
	// Application methods
	//////////////////////////////////////////////////////////////
	
	
	/**
	 * @param application
	 */
	void updateApplication(final Application application);
	
	//////////////////////////////////////////////////////////////
	// Account methods
	//////////////////////////////////////////////////////////////
	
	/**
	 * @param account
	 */
	void addAccount(final Account account);
	/**
	 * @param account
	 */
	void updateAccount(final Account account);
	
	//////////////////////////////////////////////////////////////
	// SMS methods
	//////////////////////////////////////////////////////////////
	/**
	 * Get the sms by id.
	 */
	Sms getSms(int id);

	/**
	 * Get the sms by broker id
	 */
	Sms getSmsByBrokerId(String id);
	
	/**
	 * Get the sms by message id, application and phone number
	 * @param app 
	 * @param id 
	 * @param phoneNumber 
	 * @return the list of Sms
	 */
	List<Sms> getSms(Application app, int id, String phoneNumber);
	
	/**
	 * Get the sms by status
	 * @param app 
	 * @param id 
	 * @param phoneNumber 
	 * @return the list of Sms
	 */
	List<Sms> getSms(SmsStatus status);

	/**
	 * @param sms
	 */
	void addSms(final Sms sms);
	
	/**
	 * @param sms
	 */
	void updateSms(final Sms sms);
	
	/**
	 * Return the date of the older date of sms stored in DB for specified application and account.
	 * @param application
	 * @param account
	 * @return
	 */
	Date getDateOfOlderSmsByApplicationAndAccount(Application application, Account account);
	
	
	/**
	 * Return the number of sms in table SMS
	 * for an application and an account between two dates.
	 * @param application
	 * @param account
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	int getNbOfSmsByAppAndAccountAndDate(Application application, Account account, 
				 						 Date startDate, Date endDate);
	
	
	/**
	 * Return the number of sms in table SMS with state ERROR, ERROR_PRE_BL or ERROR_POST_BL 
	 * for an application and an account between two dates.
	 * @param application
	 * @param account
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	int getNbOfSmsInErrorByAppAndAccountAndDate(Application application, Account account,
			   			Date startDate, Date endDate);
	
	/**
	 * Delete all the sms in table SMS older than the specified date.
	 * @param date
	 * @return the number of SMS deleted
	 */
	int deleteSmsOlderThan(Date date);
	
	//////////////////////////////////////////////////////////////
	// methods tools for send SMS
	//////////////////////////////////////////////////////////////
	
	/**
	 * @param labelAccount
	 */
	Account getAccByLabel(final String labelAccount);
	
	/**
	 * @param phone
	 */
	int getBlackLListByPhone(final String phone);
	
	//////////////////////////////////////////////////////////////
	// WS SendTrack methods
	//////////////////////////////////////////////////////////////
	/**
	 * @return the number of SMS recipients.
	 */
	int getNbDest(Integer msgId, Application app);
	
	/**
	 * @return the number of sms having one of the wanted states
	 */
	int getNbSmsWithState(Integer msgId, Application app, List<String> list);
	
	/**
	 * @return the number of sent SMS.
	 */
	int getNbSentSMS(Integer msgId, Application app);
	
	/**
	 * @return the number of SMS in progress.
	 */
	int getNbProgressSMS(Integer msgId, Application app);
	
	/**
	 * @return the number of SMS in error.
	 */
	int getNbErrorSMS(Integer msgId, Application app, List<String> list);
	
	/**
	 * @return the number of SMS in error.
	 */
	List<Sms> getListNumErreur(Integer msgId, Application app, List<String> list);
	
	//////////////////////////////////////////////////////////////
	// Application
	//////////////////////////////////////////////////////////////
	
	/**
	 * Get all the application.
	 */
	List<Application> getAllApplications();
	
	/**
	 * @param name
	 * @return the applcation dao
	 */
	Application getApplicationByName(String name);
	
	
	//////////////////////////////////////////////////////////////
	// Black list
	//////////////////////////////////////////////////////////////
	
	/**
	 * Add the object in db.
	 */
	 void addBlacklist(Blacklist blacklist);
	
	/**
	 * Test if a phone number is already in the black list.
	 * @param phoneNumber
	 * @return return true if the phone number is in the bl, false otherwise
	 */
	boolean isPhoneNumberInBlackList(String phoneNumber);
	
	/**
	 * Test if a phone number is already in the black list.
	 * @param phoneNumber
	 * @return return true if the phone number is in the bl, false otherwise
	 */
	List<Blacklist> getListPhoneNumbersInBlackList();
	
	//////////////////////////////////////////////////////////////
	// Statistic
	//////////////////////////////////////////////////////////////
	
	/**
	 * Add new Statistic in db.
	 * @param statistic
	 */
	void addStatistic(Statistic statistic);
	
	/**
	 * Return true if statistic has already been calculated for the specified parameter.
	 * @param application
	 * @param account
	 * @param date
	 * @return
	 */
	boolean isStatisticExistsForApplicationAndAccountAndDate(Application application, Account account, Date date);
	
	/**
	 * Return Statistic.
	 * @param month
	 * @param year
	 * @return
	 */
	Statistic getStatisticByApplicationAndMonthAndYear(Application application, int month, int year);
	
	List<Map<String,?>> getAppsAndCountsToTreat();
}