package beany;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import klasy.Event;
import parseHTML.Parse;

@ManagedBean

public class HtmlParseBean {
	public Parse parser;
	

	public void setUrl(String url) {
		this.parser = new Parse(url);
	}
}
