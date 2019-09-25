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
}
