package admin;

public enum Marks {
    Niedostateczny, Dopuszczający, Dostateczny, Dobry, Bardzodobry, Celujący;

    private Marks(){}

    public String value(){
        return name();
    }

    public static Marks formvalue(String v){
        return valueOf(v);
    }
}
