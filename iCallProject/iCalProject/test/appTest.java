import static org.junit.Assert.assertTrue;
import java.io.FileInputStream;
import java.io.IOException;
import org.junit.Test;

import iCalProject.App;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;

public class appTest {
	public App app = new App();
	Calendar calendar = new Calendar();

	@Test
	public void testOpenStreams() {
		assertTrue(app.openStreams(calendar).toString().length() > 0);
	}

	@Test
	public void testReadiCalFile() throws IOException, ParserException {
		FileInputStream fin = new FileInputStream(App.ICALFILE);
		CalendarBuilder builder = new CalendarBuilder();
		calendar = builder.build(fin);

		assertTrue(app.readiCalFile(calendar).length() > 0);
	}

}
