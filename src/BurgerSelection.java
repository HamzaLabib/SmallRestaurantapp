import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BurgerSelection extends JPanel implements ActionListener{

    ArrayList<JButton> buttons = new ArrayList<>();
    ArrayList<Burger> allBurgers = new ArrayList<>();
    static ArrayList<Double> burgerBill = new ArrayList<>();

    private StringListener textListener;

    BurgerSelection(){
        setBorder(BorderFactory.createEtchedBorder());


        BasicBurger bb = new BasicBurger();
        HealthyBurger hb = new HealthyBurger();
        DeluxeBurger db = new DeluxeBurger();

        allBurgers.add(bb);
        allBurgers.add(hb);
        allBurgers.add(db);


        buttons.add(new JButton("Display all Burgers"));
        buttons.add(new JButton("BasicBurger"));
        buttons.add(new JButton("HealthyBurger"));
        buttons.add(new JButton("DeluxeBurger"));

        for (JButton button: buttons) {
            button.setBackground(Color.white);
            button.addActionListener(this);
            add(button);
        }

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.DARK_GRAY);
    }

    public void setStringListener(StringListener textListener){
        this.textListener=textListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        burgerSelected(clicked);
    }


    public void burgerSelected(JButton clicked)
    {
        if (this.textListener!=null){
            switch (clicked.getText()){
                case "Display all Burgers":

                    this.textListener.sendText(allBurgers.get(0).toString() + "\n");
                    this.textListener.sendText(allBurgers.get(1).toString() + "\n");
                    this.textListener.sendText(allBurgers.get(2).toString() + "\n");
                    break;

                case "BasicBurger":
                    this.textListener.sendText(allBurgers.get(0).toString() + "\n");
                    burgerBill.add(3.56);
                    break;

                case "HealthyBurger":
                    this.textListener.sendText(allBurgers.get(1).toString() + "\n");
                    burgerBill.add(5.67);
                    break;

                case "DeluxeBurger":
                    this.textListener.sendText(allBurgers.get(2).toString() + "\n");
                    burgerBill.add(8.12);
                    break;

                default:break;
            }
        }
    }
}
