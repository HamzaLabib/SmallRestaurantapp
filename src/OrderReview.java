import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class OrderReview extends JPanel {

    private JTextArea textArea;

    OrderReview(){
        textArea = new JTextArea();
        textArea.setEditable(false);

        layOut();

        setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    private void layOut()
    {
        Dimension dimension = getPreferredSize();
        dimension.width = 400;
        setPreferredSize(dimension);

        TitledBorder inner = BorderFactory.createTitledBorder("Order Summery");
        Border outer = BorderFactory.createEmptyBorder(5,5,5,5);
        Border full = BorderFactory.createCompoundBorder(outer, inner);
        inner.setTitleJustification(TitledBorder.CENTER);
        setBorder(full);
    }

    public void appendText(String text){
        textArea.append(text);
    }
}
