package parserUZ;

import klasy.Event;

public class Zajecia {

	String nauczyciel;
	String godzinaRozpoczaciaZajec;
	String godzinaZakonczeniaZajec;
	String ktoraKlasa;
	String typZajec;
	String grupa;
	String nazwaZajec;
	String typTygodnia;
	String dzien;
	
	public Zajecia(String nauczyciel, String godzinaRozpoczaciaZajec, String godzinaZakonczeniaZajec, String nazwaZajec, 
			String ktoraKlasa, String typZajec, String grupa, String typTygodnia, String dzien) {
		super();
		this.nauczyciel = nauczyciel;
		this.godzinaRozpoczaciaZajec = godzinaRozpoczaciaZajec;
		this.godzinaZakonczeniaZajec = godzinaZakonczeniaZajec;
		this.nazwaZajec=nazwaZajec;
		this.ktoraKlasa = ktoraKlasa;
		this.typZajec = typZajec;
		this.grupa = grupa;
		this.typTygodnia = typTygodnia;
		this.dzien = dzien;
	}
	public final String getNauczyciel() {
		return nauczyciel;
	}
	public final void setNauczyciel(String nauczyciel) {
		this.nauczyciel = nauczyciel;
	}
	public final String getGodzinaRozpoczaciaZajec() {
		return godzinaRozpoczaciaZajec;
	}
	public final void setGodzinaRozpoczaciaZajec(String godzinaRozpoczaciaZajec) {
		this.godzinaRozpoczaciaZajec = godzinaRozpoczaciaZajec;
	}
	public final String getGodzinaZakonczeniaZajec() {
		return godzinaZakonczeniaZajec;
	}
	public final void setGodzinaZakonczeniaZajec(String godzinaZakonczeniaZajec) {
		this.godzinaZakonczeniaZajec = godzinaZakonczeniaZajec;
	}
	public final String getKtoraKlasa() {
		return ktoraKlasa;
	}
	public final void setKtoraKlasa(String ktoraKlasa) {
		this.ktoraKlasa = ktoraKlasa;
	}
	public final String getTypZajec() {
		return typZajec;
	}
	public final void setTypZajec(String typZajec) {
		this.typZajec = typZajec;
	}
	public final String getGrupa() {
		return grupa;
	}
	public final void setGrupa(String grupa) {
		this.grupa = grupa;
	}
	public final String getTypTygodnia() {
		return typTygodnia;
	}
	public final void setTypTygodnia(String typTygodnia) {
		this.typTygodnia = typTygodnia;
	}
	public final String getDzien() {
		return dzien;
	}
	public final void setDzien(String dzien) {
		this.dzien = dzien;
	}
	public Event toEventConvert(){
		Event ev=new Event(nazwaZajec,);
		return ev;
		
	}

	
	
}
