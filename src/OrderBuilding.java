import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderBuilding extends JPanel implements ActionListener{

    private JLabel nameLabel;
    private JTextField nameField;
    private JButton submitBtn;
    private JList breadList;
    private JComboBox potatoBox;
    private JCheckBox tomato;
    private JCheckBox lettuce;
    private JCheckBox cheese;
    private JCheckBox carrot;
    private JCheckBox pepper;
    private JCheckBox olives;


    private OrderBuildingListener orderBuildingListener;

    private ArrayList<String> allTopping = new ArrayList<>();
    private ArrayList<Topping> allToppings = Fridge.prepareToppings();
    private ArrayList<Double> toppingSelected = new ArrayList<>();
    private ArrayList<Double> burgerSelected = BurgerSelection.burgerBill;
    private ArrayList<JCheckBox> toppings = new ArrayList<>();

    OrderBuilding(){
        Dimension dimension = getPreferredSize();
        dimension.width = 350;
        setPreferredSize(dimension);

        TitledBorder innerBorder = BorderFactory.createTitledBorder("Choose your burger");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        Border fullBorder = BorderFactory.createCompoundBorder(outerBorder,innerBorder);

        innerBorder.setTitleJustification(TitledBorder.CENTER);

        setBorder(fullBorder);


        //Object
        nameLabel = new JLabel("Name: ");

        nameField = new JTextField(10);

        submitBtn = new JButton("Check out");

        submitBtn.addActionListener(this);


        breadList = new JList();
        DefaultListModel breadModel = new DefaultListModel();
        breadModel.addElement(new BreadCategory(0,"Brioche"));
        breadModel.addElement(new BreadCategory(1,"Ciabatta"));
        breadModel.addElement(new BreadCategory(2,"Focaccia"));
        breadList.setModel(breadModel);
        breadList.setSelectedIndex(0);
        breadList.setPreferredSize(new Dimension(100,100));
        breadList.setBorder(BorderFactory.createEtchedBorder());

        potatoBox = new JComboBox();
        DefaultComboBoxModel potatoModel = new DefaultComboBoxModel();
        potatoModel.addElement("");
        potatoModel.addElement("Cheese Potatoes $2.20");
        potatoModel.addElement("Roasted Potatoes $1.50");
        potatoModel.addElement("Fancy Potatoes $3.00");
        potatoBox.setModel(potatoModel);

        tomato = new JCheckBox();
        lettuce = new JCheckBox();
        cheese = new JCheckBox();
        carrot = new JCheckBox();
        pepper = new JCheckBox();
        olives = new JCheckBox();

        toppings.add(tomato);
        toppings.add(lettuce);
        toppings.add(cheese);
        toppings.add(carrot);
        toppings.add(pepper);
        toppings.add(olives);

        componentLayout();
    }


    public void setOrderBuilding(OrderBuildingListener orderBuildingListener) {
        this.orderBuildingListener = orderBuildingListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String firstName = nameField.getText();
        BreadCategory bread = (BreadCategory) breadList.getSelectedValue();
        String potatoes = (String) potatoBox.getSelectedItem();
        toppingSelected();

        double bill = burgerCost() + toppingCost() + potatoesSelected();
        double tax = bill * 0.15;
        double totalBill = bill + tax;

        OrderBuildingEvent fe = new OrderBuildingEvent(e,firstName, bread, potatoes, allTopping, tax, bill, totalBill);

        if(orderBuildingListener != null){
            orderBuildingListener.formEventTrigger(fe);
            nameField.setText("");
            breadList.setSelectedIndex(0);
            potatoBox.setSelectedIndex(0);
            allTopping.clear();
            toppingSelected.clear();
            burgerSelected.clear();
            for (JCheckBox reset: toppings)
                reset.setSelected(false);

        }
    }

    public double potatoesSelected()
    {
        if(potatoBox.getSelectedItem() == "Cheese Potatoes $2.20")
            return 2.20;
        else if(potatoBox.getSelectedItem() == "Fancy Potatoes $3.00")
            return 3.00;
        else if(potatoBox.getSelectedItem() == "")
            return 0.00;
        else
            return 1.50;

    }

    public int toppingSelected()
    {
        int count = 0;
        if (tomato.isSelected()) {
            allTopping.add(allToppings.get(0).toString());
            toppingSelected.add(1.27);
            count++;
        }

        if (lettuce.isSelected()){
            allTopping.add(allToppings.get(1).toString());
            toppingSelected.add(2.37);
            count++;
        }
        if (cheese.isSelected()){
            allTopping.add(allToppings.get(2).toString());
            toppingSelected.add(2.77);
            count++;
        }
        if (carrot.isSelected()){
            allTopping.add(allToppings.get(3).toString());
            toppingSelected.add(2.27);
            count++;
        }
        if (pepper.isSelected()){
            allTopping.add(allToppings.get(4).toString());
            toppingSelected.add(0.57);
            count++;
        }
        if (olives.isSelected()){
            allTopping.add(allToppings.get(5).toString());
            toppingSelected.add(1.77);
            count++;
        }
        return count;
    }

    public double toppingCost()
    {
        double sum = 0;
        for(int i = 0; i < toppingSelected.size(); i++)
        {
            sum += toppingSelected.get(i);
        }
        return sum;
    }

    public double burgerCost()
    {
        double sum = 0;
        for(int i = 0; i < burgerSelected.size(); i++)
        {
            sum += burgerSelected.get(i);
        }
        return sum;
    }

    private void componentLayout() {
        // Layout
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 1;

        //        First Row
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weighty = 0.1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(nameLabel,gc);

        gc.gridx++;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameField,gc);


        //        next Row
        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(new JLabel("Bread: "),gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(breadList,gc);


        //        next Row
        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(new JLabel("Side dishes: "),gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(potatoBox,gc);

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(new JLabel("Topping: "),gc);

        //        next Row
        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(new JLabel("Tomato"),gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(tomato,gc);

        //        next Row
        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(new JLabel("Lettuce"),gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(lettuce,gc);

        //        next Row
        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(new JLabel("Cheese"),gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(cheese,gc);

        //        next Row
        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(new JLabel("Carrot"),gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(carrot,gc);

        //        next Row
        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(new JLabel("Pepper"),gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(pepper,gc);

        //        next Row
        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(new JLabel("Olives"),gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(olives,gc);


        //        Final Row
        gc.gridy++;
        gc.weighty = 2.0;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(submitBtn,gc);
    }
}
