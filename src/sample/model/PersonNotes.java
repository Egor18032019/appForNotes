package sample.model;

public class PersonNotes {
    private String note;
    private String date;

    public PersonNotes(String note, String date) {
        this.note = note;
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public String getDate() {
        return date;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return " " + date + " => " + note + ". ";

    }
}
