package beany;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import klasy.Event;
import javax.faces.bean.SessionScoped;
import parserUZ.ParserUz;
import parserUZ.Zajecia;

@ManagedBean
@SessionScoped
public class UzParse implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Event> events;

    public void setEvents(List<Event> events) {
        this.events = events;
    }
    

    public List<Event> getEvents() {
      
        return events;
    }
    private ParserUz parser;
    private String url, msg;

    
    public String returnIndex(){
        return "index";
    }
   

    
    public void setUrl(String url) {
        this.url = url;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

 
  

   

    public void connectAndParse() {
        final FacesContext context = FacesContext.getCurrentInstance();
        setUrl(msg);
        try {
            parser = new ParserUz(url);      
       
        context.addMessage(null, new FacesMessage("Successful connecting to:" + url));
        context.addMessage(null, new FacesMessage("Rozpoczynam parsowanie..."));
        addingArrays();
         } catch (Exception e) {
            context.addMessage(null, new FacesMessage("Connecting error:" + url));
        }
    }
    
    public void addingArrays()
    {
    	for(Zajecia za:parser.getListaZajec()){
    		gafasdfasd
            events.add(new Event("title",new Date(),new Date(),"desc"));}
    }
 

}
