package admin;

public enum Marks {
    niedostateczny , dopuszczający , dostateczny, dobry, bardzodobry, celujący ;

    private Marks(){}

    public String value(){
        return name();
    }

    public static Marks formvalue(String v){
        return valueOf(v);
    }
}
