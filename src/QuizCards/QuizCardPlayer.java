package QuizCards;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class QuizCardPlayer {
    private JTextArea display;
    private JTextArea answer;
    private ArrayList <QuizCard> cardList;
    private QuizCard currentCard;
    private int currentCardIndex;
    private JFrame frame;
    private JButton nextButton;
    private boolean isShowAnswer;

    public static void main(String[] args) {
        QuizCardPlayer reader = new QuizCardPlayer();
        reader.go();
    }



    // making and show GUI
    public void go () {

        frame = new JFrame("Quiz card payer");
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);

        display = new JTextArea(10,20);
        display.setFont(bigFont);

        display.setLineWrap(true);
        display.setEditable(false);

        JScrollPane qScroller = new JScrollPane(display);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        nextButton = new JButton("Show Question");
        mainPanel.add(qScroller);
        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load card set");
        loadMenuItem.addActionListener(new OpenMenuListener());
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(640,500);
        frame.setVisible(true);



    }

    // nextCard button. If it's question it shows answer, answer - shows next question
    //set flag answer / question
     public class NextCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (isShowAnswer) {
                display.setText(currentCard.getAnswer());
                nextButton.setText("Next card");
                isShowAnswer = false;
            } else {
                if (currentCardIndex < cardList.size()) {
                    showNextCard ();
                } else {
                    display.setText("Thai was the last card");
                    nextButton.setEnabled(false);
                }
            }

        }
    }
    //brings up the menu for choosing which set of card to open
    public class OpenMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loadFile(fileOpen.getSelectedFile());

        }
    }

    //makes arrayList with cards? reading them from the text file
    private void loadFile (File file) {
        cardList = new ArrayList<QuizCard>();
        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine())!=null) {
                makeCard(line);
            }
            reader.close();


        } catch (Exception ex) {
            System.out.println("Couldn't read the card file");
            ex.printStackTrace();
        }
        showNextCard();

    }

    private void makeCard (String lineToParse) {
        String[]result = lineToParse.split("/");
        QuizCard card = new QuizCard(result[0], result[1]);
        cardList.add(card);
        System.out.println("made a card");

    }

    private void showNextCard() {
        currentCard = cardList.get(currentCardIndex);
        currentCardIndex++;
        display.setText(currentCard.getQuestion());
        nextButton.setText("Show Answer");
        isShowAnswer = true;

    }
}
