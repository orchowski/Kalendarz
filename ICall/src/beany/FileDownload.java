package beany;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


/**
 * @author Krav(Przemysław Krawczel)
 * The Class FileDownload.
 */
@ManagedBean
public class FileDownload {

	/** The file. */
	private transient StreamedContent file;

	/**
	 * Instantiates a new file download.
	 * @throws IOException 
	 */
	public FileDownload() throws IOException {
		
		InputStream stream;
		try {
		stream = new FileInputStream("/var/lib/openshift/57337cba0c1e66d8d9000088/wildfly/ics/ical.ics");
		//stream = new FileInputStream("/ical.ics");
			file = new DefaultStreamedContent(stream, "/ical", "ical.ics");
			
			
		} catch (FileNotFoundException e) {
			info();
		}
		
	}

/**
 * Delete file.
 */
public static void deleteFile(){
	final File file1 = new File("/ical.ics");
	file1.delete();
}
	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public StreamedContent getFile() {
		return file;
	}

	/**
	 * Info.
	 */
	private void info() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Event list is empty"));
	}
}