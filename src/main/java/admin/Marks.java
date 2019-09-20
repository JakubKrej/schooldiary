package admin;

public enum Marks {
    ONE, TWO ;

    private Marks(){}

    public String value(){
        return name();
    }

    public static Marks formvalue(String v){
        return valueOf(v);
    }
}
