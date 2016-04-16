package com.example.mertcan.mobileprogramminghomework;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivityPhoneContactRecovery extends AppCompatActivity
{
    private ListView contactsList;
    private Cursor cursor;
    private String nameSurname;
    private String phoneNumber;

    //  The list is used for creating one list for one operator in the methods(with new statements).
    private List<Person> allContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_contacts_recovery);

        contactsList = (ListView)findViewById(R.id.contactsList);
    }

    /*
     * Call intentini onCreate içerisinde tek bir operator list için çağırmak diğer radio butonlarına tıklandığında,
     * aynı pozisyona denk gelen numaraların allContacts daki telefon numaralarına göre hepsi için aynı numarayı
     * kullanmaktadır. Bundan dolayı tek bir method oluşturulup içerisine her radio button metodu için operatorContacts
     * değeri gönderiliyor.
     * Edit: 16.04.2016, 14:33*/
    public void callPhoneNumber(final List<Person> operatorContacts)
    {
        contactsList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + "0" + operatorContacts.get(position).getPhoneNumber()));
                startActivity(callIntent);
            }
        });
    }

    public void showTurkcellNumbers(View view)
    {
        // create the ArrayList inside the method not outside the method because it adds the contacts repetitively with respect to counts of turkcell radio button clicks
        allContacts = new ArrayList<Person>();

        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while(cursor.moveToNext())
        {
            // get name, surname and phone number from the contact list pf the virtual device.
            nameSurname = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            // divide the string into substring into 1 to 4th indices and parse it to integer value to compare
            String substringPhoneNumber = phoneNumber.substring(1, 4);
            int phoneNum = Integer.parseInt(substringPhoneNumber);

            // find the matched phone numbers
            if(phoneNum >= 530 && phoneNum < 540)
            {
                allContacts.add(new Person(nameSurname, phoneNumber));
            }

        }

        cursor.close();
        // show the results with a custom layout for turkcell contacts
        SpecialAdapter specialAdapter = new SpecialAdapter(this, allContacts);
        contactsList.setAdapter(specialAdapter);

        callPhoneNumber(allContacts);
    }

    public void showAveaNumbers(View view)
    {
        // create the ArrayList inside the method not outside the method because it adds the contacts repetitively with respect to counts of avea radio button clicks
        allContacts = new ArrayList<Person>();
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while(cursor.moveToNext())
        {
            // get name, surname and phone number from the contact list pf the virtual device.
            nameSurname = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            // divide the string into substring into 1 to 4th indices and parse it to integer value to compare
            String substringPhoneNumber = phoneNumber.substring(1, 4);
            int phoneNum = Integer.parseInt(substringPhoneNumber);

            // find the matched phone numbers
            if(phoneNum >= 501 && phoneNum < 510)
            {
                allContacts.add(new Person(nameSurname, phoneNumber));
            }
        }

        cursor.close();
        // show the results with a custom layout for avea contacts
        SpecialAdapter specialAdapter = new SpecialAdapter(this, allContacts);
        contactsList.setAdapter(specialAdapter);

        callPhoneNumber(allContacts);
    }

    public void showVodafoneNumbers(View view)
    {
        // create the ArrayList inside the method not outside the method because it adds the contacts repetitively with respect to counts of vodafone radio button clicks
        allContacts = new ArrayList<Person>();
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while(cursor.moveToNext())
        {
            // get name, surname and phone number from the contact list pf the virtual device.
            nameSurname = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            // divide the string into substring into 1 to 4th indices and parse it to integer value to compare
            String substringPhoneNumber = phoneNumber.substring(1, 4);
            int phoneNum = Integer.parseInt(substringPhoneNumber);

            // find the matched phone numbers
            if(phoneNum >= 540 && phoneNum < 550)
            {
                allContacts.add(new Person(nameSurname, phoneNumber));
            }
        }

        cursor.close();
        // show the results with a custom layout for vodafone contacts
        SpecialAdapter specialAdapter = new SpecialAdapter(this, allContacts);
        contactsList.setAdapter(specialAdapter);

        callPhoneNumber(allContacts);
    }

    public void showAllNumbers(View view)
    {
        // create the ArrayList inside the method not outside the method because it adds the contacts repetitively with respect to counts of show all radio button clicks
        allContacts = new ArrayList<Person>();
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while(cursor.moveToNext())
        {
            // get name, surname and phone number from the contact list pf the virtual device.
            nameSurname = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            // retrieve all phone numbers and related name and surnames
            allContacts.add(new Person(nameSurname, phoneNumber));
        }

        cursor.close();
        // show the results with a custom layout for all contacts
        SpecialAdapter specialAdapter = new SpecialAdapter(this, allContacts);
        contactsList.setAdapter(specialAdapter);

        callPhoneNumber(allContacts);
    }

    public void backupContacts(View view)
    {
        File backup = new File("backup.txt");
        if(backup.exists() == false)
        {
            try
            {
                backup.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        // The reason to create the list again, if I click again the Back-Up button, it will double the same contacts to be written
        allContacts = new ArrayList<Person>();
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while(cursor.moveToNext())
        {
            nameSurname = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            allContacts.add(new Person(nameSurname, phoneNumber));
        }
        cursor.close();

        try
        {
            PrintStream printStream = new PrintStream(openFileOutput("backup.txt", MODE_PRIVATE));
            for (Person person : allContacts)
            {
                try
                {
                    printStream.write(person.getNameSurname().getBytes());
                    printStream.write("\t".getBytes());
                    printStream.write(person.getPhoneNumber().getBytes());
                    printStream.write("\n".getBytes());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void recoveryContacts(View view)
    {
        allContacts = new ArrayList<Person>();
        try
        {
            Scanner scan = new Scanner(openFileInput("backup.txt"));
            while(scan.hasNextLine())
            {
                String line = scan.nextLine();
                String[] info = line.split("\t");
                allContacts.add(new Person(info[0], info[1]));
            }

            SpecialAdapter specialAdapter = new SpecialAdapter(this, allContacts);
            contactsList.setAdapter(specialAdapter);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(this, "The backup must be done before the recovery!", Toast.LENGTH_LONG).show();
        }

    }
}
