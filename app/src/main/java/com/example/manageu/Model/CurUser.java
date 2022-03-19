package com.example.manageu.Model;

public class CurUser {
    private static  CurUser single_instance = null;
    public String nam;
    public String age;
    public String em;
    public String rol;

    private CurUser()
    {
        nam = "";
        age="";
        em="";
        rol="";
    }

    public static CurUser getInstance(){
        if (single_instance == null)
            single_instance = new CurUser();

        return single_instance;
    }

}
