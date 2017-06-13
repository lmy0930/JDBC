package com.learn.orm;



import com.learn.pojo.Passport;
import com.learn.pojo.Person;
import com.learn.utils.ConnectionFactory;
import com.learn.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class One2One {
	
	@SuppressWarnings("resource")
	public void savePerson(Person person){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = ConnectionFactory.getConnection();
			
			String selectSQL = 
					"select t_person_seq.nextval from dual";
			pstmt = conn.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			Long personId = 0L;
			if(rs.next()){
				personId = rs.getLong(1);
			}
			
			selectSQL = 
				"select t_passport_seq.nextval from dual";
			pstmt = conn.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			Long passportId = 0L;
			if(rs.next()){
				passportId = rs.getLong(1);
			}
			
			//��t_person���в����¼
			String insertSQL = 
				"insert into t_person values(?,?,?,?)";
			pstmt = conn.prepareStatement(insertSQL);
			pstmt.setLong(1, personId);
			pstmt.setString(2, person.getName());
			pstmt.setString(3, person.getGender());
			pstmt.setInt(4, person.getAge());
			pstmt.executeUpdate();
			//��t_passport������¼
			insertSQL = 
				"insert into t_passport values(?,?,?)";
			pstmt = conn.prepareStatement(insertSQL);
			pstmt.setLong(1, passportId);
			pstmt.setString(2, 
					person.getPassport().getBh());
			//��������е�ֵ(personIdΪt_person�������ֵ)
			pstmt.setLong(3, personId);
			pstmt.executeUpdate();
			System.out.println("�ɹ�����Person��Ϣ");

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, pstmt, conn);
		}
	}
	
	@SuppressWarnings("resource")
	public void deletePerson(Long id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = ConnectionFactory.getConnection();
			//ɾ��t_passport���еļ�¼
			String sql = 
			  "delete from t_passport where person_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
			
			//ɾ��t_person���еļ�¼
			sql = 
			  "delete from t_person where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
			System.out.println("�ɹ�ɾ��Person��Ϣ");

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(null, pstmt, conn);
		}
	}
	
	@SuppressWarnings("resource")
	public void updatePerson(Person person){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = ConnectionFactory.getConnection();
			//����t_person���е���Ϣ
			String sql = 
				"update t_person set name = ?,age = ? " +
			    "where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getName());
			pstmt.setInt(2, person.getAge());
			pstmt.setLong(3, person.getId());
			pstmt.executeUpdate();
			//����t_passport���е���Ϣ
			sql = 
				"update t_passport set bh = ? " +
			    "where person_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getPassport().getBh());
			pstmt.setLong(2, person.getId());
			pstmt.executeUpdate();
			System.out.println("�ɹ�����Person��Ϣ");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(null, pstmt, conn);
		}
	}
	
	@SuppressWarnings("resource")
	public Person findPerson(Long id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Person person = new Person();
		Passport passport = new Passport();
		
		try{
			conn = ConnectionFactory.getConnection();
			//��ѯt_person���е���Ϣ
			String sql = 
				"select id,name,gender,age " + 
				"from t_person " + 
				"where id = ?";
			pstmt  = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				person.setId(rs.getLong(1));
				person.setName(rs.getString(2));
				person.setGender(rs.getString(3));
				person.setAge(rs.getInt(4));
				//����Person��Passport�����˫�������ϵ
				person.setPassport(passport);
				passport.setPerson(person);
			}
			
			//��ѯt_passport���е���Ϣ
			sql = 
				"select id,bh " + 
				"from t_passport " + 
				"where person_id = ?";
			pstmt  = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				person.getPassport().setId(rs.getLong(1));
				person.getPassport().setBh(rs.getString(2));
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, pstmt, conn);
		}
		return person;
	}
	
	@SuppressWarnings("resource")
	public List<Person> findPersons(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Person> list = new ArrayList<Person>();
		
		try{
			conn = ConnectionFactory.getConnection();
			//��ѯt_person���е���Ϣ
			String sql = 
				"select id,name,gender,age " + 
				"from t_person";
			pstmt  = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Person person = new Person();
				person.setId(rs.getLong(1));
				person.setName(rs.getString(2));
				person.setGender(rs.getString(3));
				person.setAge(rs.getInt(4));
				list.add(person);
			}
			
			//��ѯt_passport���е���Ϣ
			sql = "select id,bh " + 
				  "from t_passport " + 
				  "where person_id = ?";
			pstmt  = conn.prepareStatement(sql);
			for(Person person : list){
				pstmt.setLong(1, person.getId());
				rs = pstmt.executeQuery();
				if(rs.next()){
					Passport passport = new Passport();
					passport.setId(rs.getLong(1));
					passport.setBh(rs.getString(2));
					//����Person��Passport�����˫�������ϵ
					passport.setPerson(person);
					person.setPassport(passport);
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, pstmt, conn);
		}
		return list;
	}
	
}
