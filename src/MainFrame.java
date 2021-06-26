import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private OrderReview orderReview;
    private BurgerSelection burgerSelection;
    private OrderBuilding orderBuilding;


    MainFrame(){
        super("Welcome to the restaurant");
        setLayout(new BorderLayout());

        orderReview = new OrderReview();
        burgerSelection = new BurgerSelection();
        orderBuilding = new OrderBuilding();

        // EVENT LISTENERS
        burgerSelection.setStringListener(new StringListener() {
            @Override
            public void sendText(String text) {
                orderReview.appendText(text);
            }
        });


        orderBuilding.setOrderBuilding(new OrderBuildingListener() {
            @Override
            public void formEventTrigger(OrderBuildingEvent e) {
                orderReview.appendText(String.valueOf(e));
            }
        });

        add(orderBuilding, BorderLayout.WEST);
        add(burgerSelection, BorderLayout.NORTH);
        add(orderReview,BorderLayout.CENTER);

        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


}
