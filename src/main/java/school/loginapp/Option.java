package school.loginapp;

public enum Option {
    Admin, Student;

    private Option(){}

    public String value(){
        return name();
    }

    public static Option formvalue(String v){
        return valueOf(v);
    }
}
