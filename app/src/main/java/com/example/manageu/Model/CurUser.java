package com.example.manageu.Model;

public class CurUser {
    private static  CurUser single_instance = null;
    public String nam;
    public String age;
    public String em;
    public String rol;

    private CurUser()
    {
        nam = "Nitin";
        age="22";
        em="nitin@gmail.com";
        rol="Student";
    }

    public static CurUser getInstance(){
        if (single_instance == null)
            single_instance = new CurUser();

        return single_instance;
    }

}
