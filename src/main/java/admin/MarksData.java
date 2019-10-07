package admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MarksData {

<<<<<<< HEAD
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

=======
    public String getMark() {
        return mark.get();
    }

    public StringProperty markProperty() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark.set(mark);
    }

    private final StringProperty mark;


    public MarksData(String mark) {
        this.mark = new SimpleStringProperty(mark);


    }


>>>>>>> 5d3f56647803fb04c63e2f1293d09d07972c6de6

}