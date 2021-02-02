package com.example.mytodolist;

import java.util.List;

public class MyItem {
    private String title;
    private String description;
    private String created_at;

    public MyItem()
    {
        title="";
        description="";
    }
    public MyItem(String a,String b,String c){
        title=a;
        description=b;
        created_at=c;
    }
    public MyItem(MyItem a)
    {
        title=a.title;
        description=a.description;
    }
    public String getTitle()
    {
        return  this.title;
    }
    public void setTitle(String a)
    {
        this.title=a;
    }
    public String getDescription()
    {
        return this.description;
    }
    public void setDescription(String a)
    {
        this.description=a;
    }
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    public String getCreated_at() {
        return created_at;
    }
    @Override
    public String toString()
    {
        return (this.title+"\n"+this.description);
    }
}
