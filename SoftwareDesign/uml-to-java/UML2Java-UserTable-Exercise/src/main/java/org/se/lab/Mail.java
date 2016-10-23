package org.se.lab;

/**
 * Created by bernhard on 23.10.2016.
 */
public class Mail {

    private String mail;

    public Mail(String mail) {
        this.mail = mail;

    }

    public String getAddress (){
        return mail;
    }



    private User owner;
    public User getOwner()
    {
        return owner;
    }
    public void setOwner(User owner)
    {
        if(owner == null)
            throw new IllegalArgumentException();
        this.owner = owner;
    }




}
