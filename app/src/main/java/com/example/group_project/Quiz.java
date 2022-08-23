package com.example.group_project;

public class Quiz {
    int id;
    String name;
    String qst_1;
    String ans_1;
    String qst_2;
    String ans_2;
    String qst_3;
    String ans_3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQst_1() {
        return qst_1;
    }

    public void setQst_1(String qst_1) {
        this.qst_1 = qst_1;
    }

    public String getAns_1() {
        return ans_1;
    }

    public void setAns_1(String ans_1) {
        this.ans_1 = ans_1;
    }

    public String getQst_2() {
        return qst_2;
    }

    public void setQst_2(String qst_2) {
        this.qst_2 = qst_2;
    }

    public String getAns_2() {
        return ans_2;
    }

    public void setAns_2(String ans_2) {
        this.ans_2 = ans_2;
    }

    public String getQst_3() {
        return qst_3;
    }

    public void setQst_3(String qst_3) {
        this.qst_3 = qst_3;
    }

    public String getAns_3() {
        return ans_3;
    }

    public void setAns_3(String ans_3) {
        this.ans_3 = ans_3;
    }

    public Quiz(int id, String name, String qst_1, String qst_2, String qst_3, String ans_1, String ans_2, String ans_3) {
        this.id = id;
        this.name = name;
        this.qst_1 = qst_1;
        this.ans_1 = ans_1;
        this.qst_2 = qst_2;
        this.ans_2 = ans_2;
        this.qst_3 = qst_3;
        this.ans_3 = ans_3;
    }
    public Quiz() {
    }
}
