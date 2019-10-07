package admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MarksData {

    private final StringProperty mark;


    public MarksData(String mark) {
        this.mark = new SimpleStringProperty(mark);
    }

    public String getMark() {
        return mark.get();
    }

    public StringProperty markProperty() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark.set(mark);
    }


}