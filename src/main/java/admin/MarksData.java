package admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MarksData {

    private final StringProperty ID;
    private final StringProperty mark1;
    private final StringProperty mark2;

    public MarksData(String id, String mark1, String mark2) {
        this.ID = new SimpleStringProperty(id);
        this.mark1 = new SimpleStringProperty(mark1);
        this.mark2 = new SimpleStringProperty(mark2);

    }

    public String getID() {
        return ID.get();
    }

    public StringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public String getMark1() {
        return mark1.get();
    }

    public StringProperty mark1Property() {
        return mark1;
    }

    public void setMark1(String mark1) {
        this.mark1.set(mark1);
    }

    public String getMark2() {
        return mark2.get();
    }

    public StringProperty mark2Property() {
        return mark2;
    }

    public void setMark2(String mark2) {
        this.mark2.set(mark2);
    }

}
