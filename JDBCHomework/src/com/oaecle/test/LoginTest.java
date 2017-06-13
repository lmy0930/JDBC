package com.oaecle.test;

import java.util.Scanner;

/**
 * Created by Shinelon on 2017/5/6.
 */
public class LoginTest {
    public static void main(String[] args) {
        String username=null;
        String password=null;

        Scanner sc=new Scanner(System.in);
        System.out.println("请输入用户名：");
        username=sc.next();
        System.out.println("请输入密码：");
        password=sc.next();
    }
}
