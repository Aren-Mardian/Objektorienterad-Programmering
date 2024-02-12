import flyger.Resor;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ResorForm {
    private JPanel mainPanel;
    private JTextArea tA;
    private JButton addaBtn, visaBtn, sokaBtn, tabortBtn, uppdateraBtn, sokaLagstaHogstaBtn, tomaBtn ;
    private JTextField textTyp, textPris, textMaxAntal, textDagar, textBolag, textPartner, textLagsta, textHogsta, textId;
    private JLabel lblBrowse;
    //arraylista data typ Resor
    ArrayList<Resor> resorArrL;
    //variabel av data typ Resor
    private Resor resor;
    //listiterator data typ Resor
    private ListIterator<Resor> iteratorObj;

    String stjarnStreck = "*-";

    private String idResaText, prisResaText, antalResenarerText, antalDagarText, typAvResaText, flygBolagText, samarbetsPartnerText, lagstaText, hogstaText;


    public static void main(String[] args) {
        //auto genererad main metod för gui
        JFrame frame = new JFrame("Sicilien");
        frame.setContentPane(new ResorForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setVisible(true);
    }
    public ResorForm() {
        //skapa en array list och lägg 3 nya resor från början
        resorArrL = new ArrayList<>(List.of(
                new Resor("EU444", 1220.49, 40, 7, "sol", "TUI", "SAS"),
                new Resor("EU443", 1320.99, 45, 4, "golf", "TUI", "Norwegian Air Shuttle"),
                new Resor("EU442", 1420.99, 50, 3, "familj", "TUI", "British Airways")
        ));

        visaAlla();

        //uppdatera lblbrowse
        lblBrowse.setText("antal resor: " + resorArrL.size());


        visaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visaAlla();
            }
        });//end visaBtn


        addaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //hämta ut text från textfälten och i respektiv variabel
                idResaText = textId.getText();
                typAvResaText = textTyp.getText();
                prisResaText = textPris.getText();
                antalResenarerText = textMaxAntal.getText();
                antalDagarText = textDagar.getText();
                flygBolagText = textBolag.getText();
                samarbetsPartnerText = textPartner.getText();


                if (idResaText.isEmpty()|| typAvResaText.isEmpty() ||prisResaText.isEmpty()|| antalResenarerText.isEmpty() ||antalDagarText.isEmpty()||flygBolagText.isEmpty() || samarbetsPartnerText.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Du måste fylla i alla fälterna.");
                    return;
                }

                //Här testar vi kodsatserna för fel medan de körs,
                try {
                    //tilldela iterator objektet arraylistan och anropa listIterator metoden
                    iteratorObj = resorArrL.listIterator();
                    while (iteratorObj.hasNext()) {
                        resor = iteratorObj.next();

                        //hämta ut id från respektiv objekt i arraylistan och jämför med textfältet
                        if (resor.getIdResa().equalsIgnoreCase(textId.getText())) {
                            JOptionPane.showMessageDialog(null, "Detta id: \"" + resor.getIdResa() + "\" finns redan med");
                            //retunera för att inte fortsätta neråt
                            return;
                        }
                    }
                    //skapa ett object resor och lägger till i arraylist
                    resorArrL.add(new Resor(idResaText, Double.parseDouble(prisResaText), Integer.parseInt(antalResenarerText), Integer.parseInt(antalDagarText), typAvResaText, flygBolagText, samarbetsPartnerText));
                    visaAlla();
                    lblBrowse.setText("antal resor: " + resorArrL.size());

                    //här talar vi vad som ska köras om ett fel uppstår i try
                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null, "Vänligen försök igen problemet kan vara att du inte fyllet i rätt!");
                }
            }
        });
        tabortBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //deklarera och initiera boolean
                boolean trip = false;

                iteratorObj = resorArrL.listIterator();
                while (iteratorObj.hasNext()) {
                    resor = iteratorObj.next();

                    if (resor.getIdResa().equalsIgnoreCase(textId.getText())) {
                        //remove metod för att ta bort objekt från arraylistan
                        iteratorObj.remove();
                        visaAlla();
                        lblBrowse.setText("antal resor: " + resorArrL.size());
                        //bekräfta till använder med dialog ruta
                        JOptionPane.showMessageDialog(null, "Resan med id " + textId.getText() + " är bortagen");
                        trip = true;
                        break;
                    }
                    //om trip boolean är falsk gör detta
                } if (!trip)JOptionPane.showMessageDialog(null, "Resan med " + textId.getText() + " finns inte. \n");

            }
        });
        sokaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ta bort metod som rensar textarean
                taBort();

                iteratorObj = resorArrL.listIterator();

                //while loop som körs om iterator objektet har nästa, 3 objekt, 3 gånger
                while (iteratorObj.hasNext()) {
                    resor = iteratorObj.next();

                    if (resor.getTypAvResa().equalsIgnoreCase(textTyp.getText())) {
                        tA.append( resor.toString() + "\n" + stjarnStreck.repeat(30));
                    }
                }
            }
        });
        uppdateraBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taBort();

                iteratorObj = resorArrL.listIterator();
                while (iteratorObj.hasNext()) {
                    resor = iteratorObj.next();
                    // om if satsen är sant, uppdatera antal dagar och priset bara
                    if (resor.getIdResa().equalsIgnoreCase(textId.getText())) {
                        resor.setAntalDagar(Integer.parseInt(textDagar.getText()));
                        resor.setPrisResa(Double.parseDouble(textPris.getText()));
                    }
                }
                visaAlla();
            }
        });
        tomaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taBort();
            }
        });
        //knappen för söka mellan 2 värden
        sokaLagstaHogstaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taBort();
                lagstaText = textLagsta.getText();
                hogstaText = textHogsta.getText();
                iteratorObj = resorArrL.listIterator();

                    while (iteratorObj.hasNext()) {
                        resor = iteratorObj.next();
                        //om detta är sant visa respektiv objekt(hela objektet eftersom .toString())
                        if (resor.getPrisResa() >= Integer.parseInt(lagstaText) && resor.getPrisResa() <= Integer.parseInt(hogstaText)) {
                            tA.append( resor.toString() + "\n" + stjarnStreck.repeat(30));
                        }
                    }
            }
        });
    }
    //visaAlla metoden
    private void visaAlla () {
        iteratorObj = resorArrL.listIterator();
        taBort();
        while (iteratorObj.hasNext()) {
            resor = iteratorObj.next();
            tA.append(resor + "\n\n" + stjarnStreck.repeat(30) + "\n\n");
        }
    }
    //ta bort metoden
    private void taBort(){
        tA.setText("");
    }
}