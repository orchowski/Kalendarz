package beany;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


@ManagedBean
public class FileDownload {

	private StreamedContent file;

	public FileDownload() {

		InputStream stream;
		try {
			stream = new FileInputStream("/src/main/resources/ical.ics");
			file = new DefaultStreamedContent(stream, "/ical", "ical.ics");
		} catch (FileNotFoundException e) {
			info();
		}
		
	}

	public StreamedContent getFile() {
		return file;
	}

	private void info() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Event list is empty"));
	}
}