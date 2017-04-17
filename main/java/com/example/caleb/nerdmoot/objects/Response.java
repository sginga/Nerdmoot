package com.example.caleb.nerdmoot.objects;

/**
 * Created by caleb on 3/5/2017.
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
public class Response {
    private String content;
    private int depth;
    private String date;

    Date d = new Date();
    DateFormat df = new SimpleDateFormat();

    public Response(){
        date = df.format(d);
        content = "(No Content)";
        depth = -1;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String c) {
        content = c;
    }

    public int getDepth(){
        return depth;
    }

    public void setDepth(int d){
        depth = d;
    }

    public String getDate(){
        return date;
    }

}
