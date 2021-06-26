import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.EventObject;

public class OrderBuildingEvent extends EventObject {

    private String firstName;

    private BreadCategory bread;
    private String potatoes;
    private ArrayList<String> toppingList = new ArrayList<>();
    private Double bill;
    private Double tax;
    private Double totalBill;


    public OrderBuildingEvent(Object source) {
        super(source);
    }

    public OrderBuildingEvent(
            Object source,
            String firstName,
            BreadCategory bread,
            String potatoes,
            ArrayList<String> toppingList,
            Double tax,
            Double bill,
            Double totalBill

    ) {
        super(source);
        this.firstName = firstName;
        this.bread = bread;
        this.potatoes = potatoes;
        this.toppingList = toppingList;
        this.tax = tax;
        this.bill = bill;
        this.totalBill = totalBill;
    }

    private String printList(ArrayList<String> list){
        String str = "";

        for (int i = 0; i < list.size(); i++) {
            str += toppingList.get(i);

            if (i != list.size() - 1){
                str += ", ";
            }else{
                str += "--------------------------------------------";
            }
        }
        return str;
    }

    @Override
    public String toString() {
        DecimalFormat numberFormat = new DecimalFormat(".00");

        return "First Name: "
                + firstName  + "\nBread: "
                + bread + "\nPotatoes: "
                + potatoes + "\nTopping: "
                + printList(toppingList)
                + "\nTotal Amount: $" + numberFormat.format(bill)
                + " + (Tax: $" + numberFormat.format(tax) + ")"
                + "\nTotal Bill: $" + numberFormat.format(totalBill)
                + "\n\n\n";
    }
}
