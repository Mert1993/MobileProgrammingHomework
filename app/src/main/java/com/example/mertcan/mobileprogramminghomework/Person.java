package com.example.mertcan.mobileprogramminghomework;

/**
 * Created by Mert Can on 13.04.2016.
 * Class Person holds two parameters nameSurname and phoneNumber to create the ArrayList with the type person in the MainActivity Class.
 **/
public class Person
{
    private String nameSurname;
    private String phoneNumber;

    public Person(String nameSurname, String phoneNumber)
    {
        super();
        this.nameSurname = nameSurname;
        this.phoneNumber = phoneNumber;
    }

    // Getter and Setter methods for accesibility of nameSurname and phoneNumber private fields
    public String getNameSurname()
    {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname)
    {
        this.nameSurname = nameSurname;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
}
