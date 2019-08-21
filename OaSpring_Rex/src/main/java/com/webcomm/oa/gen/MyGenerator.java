
package com.webcomm.oa.gen;

import java.io.*;
import java.sql.*;
import java.util.Calendar;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * 承辦案件 自定義主鍵
 * 
 * @author user
 *
 */
public class MyGenerator implements IdentifierGenerator {

	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		String prefix = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		String caseNo = null;
		Connection con = session.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT case_seq.NEXTVAL as nextval FROM DUAL");
			rs.next();
			int nextval = rs.getInt("nextval");

			caseNo = prefix + String.format("%05d", nextval);

		} catch (SQLException e) {
			throw new HibernateException("Unable to generate Sequence");
		}
		return caseNo;
	}

	public static void main(String[] args) {
		System.out.println(Calendar.getInstance().get(Calendar.YEAR));
	}
}