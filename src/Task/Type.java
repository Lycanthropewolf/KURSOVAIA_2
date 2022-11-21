package Task;

public enum Type implements java.lang.reflect.Type {
    PERSONAL,
    WORK;

    @Override
    public String getTypeName()  {
        return java.lang.reflect.Type.super.getTypeName();
    }
}
