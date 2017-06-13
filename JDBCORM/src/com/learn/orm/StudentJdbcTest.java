package com.learn.orm;

import com.learn.pojo.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentJdbcTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StudentJdbc jdbc = new StudentJdbc();
		
		//����ѧ��
/*		Student stu = new Student();
		stu.setName("tony");
		stu.setAddress("beijing");
		stu.setGender("male");
		stu.setAge(35);
		jdbc.saveStudent(stu);*/
		
		//����idɾ��ѧ��
		//jdbc.deleteStudent(3L);
		
		//����ָ��id��ѧ��
		/*Student stu = jdbc.findStudent(23L);
		System.out.println(stu);*/
		
		//����id����ѧ����Ϣ
		/*stu.setName("tony zhang");
		stu.setAddress("tianjing");
		stu.setAge(45);
		jdbc.updateStudent(stu);*/
		
		//�������е�ѧ��
		/*List<Student> list = jdbc.findStudents();
		System.out.println(list);*/
		
		//��̬��ѯ
		List<String> conditions = new ArrayList<String>();
		conditions.add("address = 'shanghai'");
		conditions.add("gender = 'female'");
		List<Student> list = jdbc.findStudents(conditions);
		System.out.println(list);
		
	}

}
