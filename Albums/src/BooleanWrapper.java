public class BooleanWrapper // allows you to change the boolean within a loop
{
    private boolean value;
    public BooleanWrapper(boolean value) { this.value = value; }
    public void set(boolean value) { this.value = value; }
    public boolean get() { return value; }
}
