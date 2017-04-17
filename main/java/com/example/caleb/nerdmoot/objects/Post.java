package com.example.caleb.nerdmoot.objects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by caleb on 3/5/2017.
 */

public class Post {

    private String title;
    private String content;
    private String date;
    DateFormat df = new SimpleDateFormat();
    Date d = new Date();

    public Post(){
        date = df.format(d);
        content = "(No Content)";
        title = "(Empty Title)";
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String t){
        title = t;
    }

    public String getDate(){
        return date;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String c){
        content = c;
    }
}
