package com.example.login;

import java.util.ArrayList;

public class JHJ_Test {
    static private JHJ_Test test;

    private String userid;
    private String id="01055556666";
    private String id2="01077778888";
    private ArrayList<String> a = new ArrayList<String>();

    private JHJ_Test() {}

    public static JHJ_Test getTest() {
        if(test == null) {
            test = new JHJ_Test();
        }
        return test;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getId() {
        return id;
    }

    public String getId2() {
        return id2;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public ArrayList<String> getA() {
        return a;
    }

    public void setA(String value) {
        a.add(value);
    }

}
