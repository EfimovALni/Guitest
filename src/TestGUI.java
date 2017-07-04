import javax.swing.*;
import javax.swing.text.PlainDocument;
import javax.swing.text.StringContent;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.*;
import java.util.BitSet;

/**
 * Created by Uzivatel on 6/25/2017.
 */
public class TestGUI extends JFrame{

    private JTextArea history;
    private JTextArea display;
    private JScrollPane jScrollPane;

    private JButton zero;
    private JButton one;
    private JButton two;
    private JButton three;

    private JButton plus;
    private JButton devision;
    private JButton sub;
    private JButton equal;
    private JButton memoryClear;
    private JButton displayClear;
    private JButton backSpace;
    private JButton plusMinus;
    private JButton dot;
    private JButton binary;

    private boolean[] operation = new boolean[4];
    private String operator = "";
    private char operationChar;

    private boolean flagPlus;           // Indication of "flag" or "unflag" button
    private boolean countPlus = true;   // Indication of "pushed" or "unpushed" button (Stupid name =) )

    private boolean flagSub;
    private boolean countSub = true;

    private boolean flagDevision;
    private boolean countDevision = true;

    private boolean flagEqual;

    private boolean flagPlusNinus = true;
    private Double kukuRuku;

    public Double getTempOne() {
        return tempOne;
    }

    public void setTempOne(Double tempOne) {
        this.tempOne = tempOne;
    }

    private Double tempOne = 0.0;

    public Double getTempTwo() {
        return tempTwo;
    }

    public void setTempTwo(Double tempTwo) {
        this.tempTwo = tempTwo;
    }

    private Double tempTwo = 0.0;

    public Double getTempThree() {
        return tempThree;
    }

    public void setTempThree(Double tempThree) {
        this.tempThree = tempThree;
    }

    private Double tempThree = 0.0;

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    private Double result = 0.0;
    private Double tempPlus = 0.0;
    private Double tempSub = 0.0;
    private Double tempDevision = 0.0;

    public static void main(String[] args) {
        new TestGUI();
        Operations op = new Operations(); // This is only for testing another class, his name operations
        System.out.println(op.a + " + " + op.b + " = " + (op.a + op.b)); // This is only for testing another class, hit name Operations
    }

    public TestGUI() {
        super("Test CUI components");
        sendDisplay();
        sendButtons();
        sendUI(this);
    }

    private void sendButtons() {

        plus = new JButton("+");
        plus.setBounds(190, 220, 50, 50);
        plus.setFont(new Font("Arial", Font.PLAIN, 25));
        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flagPlus = true;
                operationChar = '+';
                if (flagEqual == false)
//                    history.append("\n" + display.getText().toString() + " history.append");

                if (countPlus == true) {
                    setTempOne(Double.parseDouble(display.getText()));
                    history.append("\n");

                    if (getTempOne().toString().endsWith(".0"))
                        history.append(getTempOne().toString().replace(".0", "") + "\n");

                    display.setText("0");
                    countPlus = false;
                } else {
                    setTempOne(Double.parseDouble(display.getText()));
                    display.setText("0");
                    history.append("\n");
                }

            }
        });
        add(plus);

        sub = new JButton("-");
        sub.setBounds(190, 280, 50,50);
        sub.setFont(new Font("Arial", Font.PLAIN, 30));
        sub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flagSub = true;
                operationChar ='-';

                if (countSub == true) {
                    setTempOne(Double.parseDouble(display.getText()));
                    history.append("\n");
                    if (getTempOne().toString().endsWith(".0"))
                        history.append(getTempOne().toString().replace(".0", "") + "\n");
                    display.setText("0");
                    countSub = false;
                } else {
                    setTempOne(Double.parseDouble(display.getText()));
                    display.setText("0");
                    history.append("\n");
                }
            }
        });
        add(sub);

        devision = new JButton("÷");
        devision.setBounds(190, 340, 50,50);
        devision.setFont(new Font("Arial", Font.PLAIN,  30));
        devision.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flagDevision = true;
                operationChar = '/';

                if (countDevision == true) {
                    setTempOne(Double.parseDouble(display.getText()));
                    history.append("\n");
                    if (getTempOne().toString().endsWith(".0"))
                        history.append(getTempOne().toString().replace(".0", "") + "\n");

                    display.setText("0");
                    countDevision = false;
                } else {
                    setTempOne(Double.parseDouble(display.getText()));
                    display.setText("0");
                    history.append("\n");
                }
            }
        });
        add(devision);

        equal = new JButton("=");
        equal.setBounds(190, 400, 50, 50);
        equal.setFont(new Font("Arial", Font.PLAIN, 25));
        equal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flagEqual = true;
                System.out.println(flagEqual);

                switch (operationChar) {
                    case '+':
                        if (flagPlus == true) {
                            setTempTwo(Double.parseDouble(display.getText()));
                            history.append(operationChar + "  "  + getTempTwo().toString() + "\n");
                            result = getTempOne() + getTempTwo();
                            display.setText(result.toString());
                            history.append( "=  " +  result.toString());
                            tempPlus = result;
                            flagPlus = false;
                            countPlus = false;
                            flagDevision = false;
                        } else {
                            history.append("+  \n" + getTempTwo().toString());
                            result = tempPlus + getTempTwo();
                            display.setText(result.toString());
                            history.append("=  \n" + result.toString());
                            tempPlus = result;
                            flagDevision = false;
                        }

                        break;

                    case '-':
                        if (flagSub == true) {
                            setTempTwo(Double.parseDouble(display.getText()));
                            history.append(operationChar + "  "  + getTempTwo().toString() + "\n");
                            result = getTempOne() - getTempTwo();
                            display.setText(result.toString());
                            history.append("=  " +  result.toString());
                            tempSub = result;
                            flagSub = false;
                            countSub = false;
                        } else {
                            history.append("-  \n" + getTempTwo().toString());
                            result = tempSub - getTempTwo();
                            display.setText(result.toString());
                            history.append("=  \n" + result.toString());
                            tempSub = result;
                        }
                        break;

                    case '/':
                        if (flagDevision == true) {
                            setTempTwo(Double.parseDouble(display.getText()));
                            history.append("÷  "  + getTempTwo().toString() + "\n");
                            result = getTempOne() / getTempTwo();
                            display.setText(result.toString());
//                            display.setText(String.format(result.toString(), "%+8d%n"));
                            history.append("=  " +  result.toString());
                            tempDevision = result;
                            flagDevision = false;
                            countDevision = false;
                        } else {
                            history.append("÷  \n" + getTempTwo().toString());
                            result = tempDevision / getTempTwo();
                            display.setText(result.toString());
                            history.append("=  \n" + result.toString());
                            tempDevision = result;
                        }
                        break;
                }

                if (display.getText().endsWith(".0") || history.getText().endsWith(".0")) {
                    display.setText(display.getText().replace(".0", ""));
                    history.setText(history.getText().replace(".0", ""));
                }
            }

        });
        add(equal);

        displayClear = new JButton("CD");
        displayClear.setBounds(70, 400, 50, 50);
        displayClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.setText("0");
            }
        });
        add(displayClear);

        memoryClear = new JButton("M");
        memoryClear.setBounds(130, 400, 50, 50);

        memoryClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Memory clear: " +
                        "\ntempOne = " + tempOne + ", tempTwo = " + tempTwo + ",\n" +
                        "display: " + display.getText() + ", history:\n" + history.getText() + "\toperator: " + operationChar);
                setTempOne(0.0);
                setTempTwo(0.0);
                display.setText("0");
                history.setText("");
                result = 0.0;
                for (int i = 0; i > 4; i++){
                    operation[i] = false;
                }
                System.out.println("Memory clear: " +
                        "\ntempOne = " + tempOne + ", tempTwo = " + tempTwo + ",\n" +
                        "display: " + display.getText() + ", history:\n" + history.getText() + "\toperator: " + operationChar);
            }
        });
        add(memoryClear);

        plusMinus = new JButton("±");
        plusMinus.setBounds(130,220, 50,50);
        plusMinus.setFont(new Font("Arial", Font.PLAIN, 30));
        plusMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flagPlusNinus = false;

                String tempPlusMinusS = display.getText();
                Double tempPlusMinusD = Double.parseDouble(tempPlusMinusS) * -1;

                if (tempPlusMinusD == 0)
                    return;

                if (tempPlusMinusD.toString().endsWith(".0")) {
                    display.setText(tempPlusMinusD.toString().replace(".0", ""));
                } else {
                    display.setText(tempPlusMinusD.toString());
                }
            }
        });
        add(plusMinus);

        binary = new JButton("bin");
        binary.setBounds(70, 220, 50,50);
        binary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(display.getText());
                history.append(Integer.toString(a, 2) + "\n");
            }
        });
        add(binary);


        dot = new JButton(".");
        dot.setBounds(130, 280, 50, 50);
        dot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (!display.getText().contains(".")) {
                    display.append(".");
                }
//                if (getTempOne().toString().endsWith(".0"))
//                    history.append(getTempOne().toString().replace(".0", "") + "\n");

            }
        });
        add(dot);

        backSpace = new JButton("←");
        backSpace.setBounds(130, 340,50,50);
        backSpace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 1) {
                    display.setText(display.getText().substring(0, display.getText().length()-1));
                    if (display.getText().equals("-"))
                        display.setText("0");
                } else if (display.getText().length() == 1) {
                    display.setText("0");
                }
            }
        });
        add(backSpace);

        zero = new JButton("0");
        zero.setBounds(10, 220, 50,50);
        zero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 9)
                    return;
                if (display.getText().equalsIgnoreCase("0")) {
                    display.setText("0");
                    return;
                }
                display.append("0");
            }
        });
        add(zero);

        one = new JButton("1");
        one.setBounds(10, 280, 50, 50);
        one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 9)
                    return;
                if (display.getText().equalsIgnoreCase("0")) {
                    display.setText("1");
                    return;
                }
                display.append("1");
            }
        });
        add(one);

        two = new JButton("2");
        two.setBounds(10, 340, 50, 50);
        two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flagEqual == true) {
                    display.setText("2");
                    System.out.println("1: " + flagEqual);
                    flagEqual = false;
                } else {
                    if (display.getText().length() > 9)
                        return;
                    if (display.getText().equalsIgnoreCase("0")) {
                        display.setText("2");
                        System.out.println("2: " + flagEqual);
                        return;
                    }
                    display.append("2");
                    System.out.println("3: " + flagEqual);
                }
                kukuRuku = Double.parseDouble(display.getText());
                System.out.println("kukuRuku:\t" + kukuRuku);



                /*if (display.getText().length() > 9)
                    return;
                if (display.getText().equalsIgnoreCase("0")) {
                    display.setText("2");
                    System.out.println("2: " + flagEqual);


                    return;
                }
                display.append("2");
                System.out.println("3: " + flagEqual);*/

            }
        });

        add(two);

        three = new JButton("3");
        three.setBounds(10, 400, 50, 50);
        three.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 9)
                    return;
                if (display.getText().equalsIgnoreCase("0")) {
                    display.setText("3");
                    return;
                }
                display.append("3");
            }
        });
        add(three);
    }

    private void sendDisplay() {
        history = new JTextArea();
        jScrollPane = new JScrollPane(history);
        jScrollPane.setBounds(10,10,200,160);
        history.setFont(new Font("Arial", Font.ITALIC, 15));
//        history.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        history.setBackground(Color.PINK);
        history.setEditable(true);

        add(jScrollPane);

        display = new JTextArea("0");
        display.setBounds(10, 180, 200, 30);
        display.setFont(new Font("Arial", Font.PLAIN, 30));
//        display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        display.setBackground(Color.lightGray);
        display.setEditable(false);

        add(display);

    }

    private void sendUI(TestGUI app){
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(350, 500);
        app.setResizable(false);
        app.setLayout(null);
        app.setLocation(1000,500);
        app.setVisible(true);
    }


}
