package dto;

public class Contents {
	private String conts;
	private String name;
	private String time;
	private String mail;
	private String edit;
	private String filen;

	private int id;

	public Contents(String conts , String name , String time , String mail , String editime , String filen , int id){
		this.conts = conts;
		this.name = name;
		this.time = time;
		this.mail = mail;
		this.edit = editime;
		this.filen = filen;
		this.id = id;
	}

	public Contents(String conts , String name , String time , String mail , String editime ,String filen){
		this.conts = conts;
		this.name = name;
		this.time = time;
		this.mail = mail;
		this.edit = editime;
		this.filen = filen;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getConts() {
		return conts;
	}

	public void setConts(String conts) {
		this.conts = conts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEdit() {
		return edit;
	}

	public void setEdit(String edit) {
		this.edit = edit;
	}

	public String getFilen() {
		return filen;
	}

	public void setFilen(String filen) {
		this.filen = filen;
	}






}
