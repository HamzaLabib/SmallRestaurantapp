public class BreadCategory {
    private int id;
    private String text;

    public BreadCategory(int id, String text){
        this.id = id;
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
