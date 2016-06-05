package beany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import klasy.Event;
import javax.annotation.PostConstruct;
import parseHTML.Parse;
import parseHTML.Table;

@ManagedBean

public class HtmlParse implements Serializable{
    private List<Event> events;
    private List<Table> tables;
    private Event event;
    private Parse parser;
    private String url, msg;

    public void setUrl(String Url) {
        url = Url;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void connect() {
        FacesContext context = FacesContext.getCurrentInstance();
        setUrl(msg);
        try {
            parser = new Parse(url);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage("Connecting error:" + url));
        }
        context.addMessage(null, new FacesMessage("Successful connecting to:" + url));
        context.addMessage(null, new FacesMessage(parser.getNumberOfTables() + " tables detected"));
        tables = new ArrayList<Table>();
         for(int i=0;i<parser.getNumberOfTables();i++)
            tables.add(new Table(parser.getTable(i).toString(),i));
    }

   
    public void init() {
        parser = new Parse();   
        tables = new ArrayList<Table>();
        tables.add(new Table(" ",1));
    }
    
      public List<Table> getTables() {
         
        return tables;
    }




}


