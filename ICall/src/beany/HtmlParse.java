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
import javax.faces.bean.SessionScoped;
import org.jsoup.nodes.Element;
import parseHTML.Col;
import parseHTML.Parse;
import parseHTML.Table;

@ManagedBean
@SessionScoped
public class HtmlParse implements Serializable {

    private List<Event> events;

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
      
        return events;
    }
    private List<Table> tables;
    private List<Col> cols;
    private Event event;
    private Parse parser;
    private String url, msg;
    private Table tab = new Table("",0);

    
   
   
    private int dateS;
    private int dateE;
    private int desc;
    private int title;

    public int getDateS() {
        return dateS;
    }

    public int getDateE() {
        return dateE;
    }

    public int getDesc() {
        return desc;
    }

     public int getTitle() {
        return title;
    }
    public void setTitle(int title) {
        this.title = title;
    }

    public void setDateS(int dateS) {
        this.dateS = dateS;
    }

    public void setDateE(int dateE) {
        this.dateE = dateE;
    }

    public void setDesc(int desc) {
        this.desc = desc;
    }
    

    public int getCols() {
        return parser.getNumberOfCols(tab.getIndex());
    }
    public String returnIndex(){
        return "index";
    }
    public List<Event> parseEvents(){
         FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage(null, new FacesMessage("Rozpoczynam parsowanie..."));
         int[] desc={this.desc};     
         msg=parser.parseTableToEvents(parser.getTable(tab.getIndex()), title, dateS, dateE, desc);
         context.addMessage(null, new FacesMessage(msg));
         for(Event ev:parser.getEvents())
             events.add(ev);
             
return events;
    }

    
    public void setUrl(String Url) {
        url = Url;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Table> getTables() {

        return tables;
    }

    public Table getTab() {
        return tab;
    }
  

    public void selectTable(int tab) {
       
        FacesContext context = FacesContext.getCurrentInstance();
        Table table = new Table(parser.getTable(tab).toString(),tab);
        if(parser.checkTable(parser.getTable(tab))){
             this.tab = table;
             ColsList();
              context.addMessage(null, new FacesMessage("Poprawna tabela" + this.tab.getIndex()));
        }else
               context.addMessage(null, new FacesMessage("Niepoprawna tabela. (Sprawdź czy na pewno ma tyle samo komórek w każdym wierszu)"));
        
      
    }

    public void connect() {
        FacesContext context = FacesContext.getCurrentInstance();
        setUrl(msg);
        try {
            parser = new Parse(url);
       
        context.addMessage(null, new FacesMessage("Successful connecting to:" + url));
        context.addMessage(null, new FacesMessage(parser.getNumberOfTables() + " tables detected"));
        tables = new ArrayList<Table>();
        cols = new ArrayList<Col>();
        cols.add(new Col(0));
        for (int i = 0; i < parser.getNumberOfTables(); i++) {
            tables.add(new Table(parser.getTable(i).toString(), i));
        }
         } catch (Exception e) {
            context.addMessage(null, new FacesMessage("Connecting error:" + url));
        }
    }
      private void ColsList(){
        for(int i=1;i<parser.getNumberOfCols(tab.getIndex());i++){
            cols.add(new Col(i));
        }
    }

}
