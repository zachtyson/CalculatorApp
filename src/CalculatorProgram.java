import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorProgram extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        int temp = (int) button.getClientProperty("buttons");
        switch(temp){
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                addOperation(temp+1, firstInputTurn);
                break;
            case 9:
                addOperation("0", firstInputTurn);
                break;
            case 10:
                doOperation(currentOperation);
                break;
            case 11:
                addOperation(".", firstInputTurn);
                break;
            case 12:
                currentOperation = 1;
                firstInputTurn = false;
                break;
            case 13:
                currentOperation = 2;
                firstInputTurn = false;
                break;
            case 14:
                currentOperation = 3;
                firstInputTurn = false;
                break;
            case 15:
                currentOperation = 4;
                firstInputTurn = false;
                break;
            case 16:
                input = "";
                secondInput = "";
                textArea.setText("");
                currentOperation = 0;
                firstInputTurn = true;
                break;
            case 17:
                if(firstInputTurn) {
                    input = "";

                }else {
                    secondInput = "";
                }
                textArea.setText("");
                break;
            case 18:
                try {
                    if (firstInputTurn) {
                        input = input.substring(0, input.length() - 1);
                        textArea.setText(input);

                    } else {
                        secondInput = secondInput.substring(0, secondInput.length() - 1);
                        textArea.setText(secondInput);
                    }
                } catch (IndexOutOfBoundsException k) {
                    textArea.setText("");
                }
                break;
            default: System.out.println("Something wrong");
            break;
        }

    }
    JButton[] buttons = new JButton[19];
    JTextArea textArea = new JTextArea();

    String input = "";
    int currentOperation = 0;
    boolean firstInputTurn = true;
    String secondInput = "";

    double output = 0;

    CalculatorProgram() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Calculator App");
        this.setSize(350,530);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        textArea.setPreferredSize(new Dimension(300,100));
        this.add(textArea);

        buttons[0] = new JButton("1");
        buttons[1] = new JButton("2");
        buttons[2] = new JButton("3");
        buttons[3] = new JButton("4");
        buttons[4] = new JButton("5");
        buttons[5] = new JButton("6");
        buttons[6] = new JButton("7");
        buttons[7] = new JButton("8");
        buttons[8] = new JButton("9");
        buttons[9] = new JButton("0");
        buttons[10] = new JButton("=");
        buttons[11] = new JButton(".");
        buttons[12] = new JButton("+");
        buttons[13] = new JButton("-");
        buttons[14] = new JButton("*");
        buttons[15] = new JButton("/");
        buttons[16] = new JButton("C");
        buttons[17] = new JButton("CE");
        buttons[18] = new JButton("BACK");

        for(int i = 0; i <19; i++) {

            buttons[i].putClientProperty("buttons", i);
            buttons[i].setFont(new Font ("Trebuchet MS", Font.BOLD, 20));
            buttons[i].setPreferredSize(new Dimension(70, 70));
        }

        buttons[16].setPreferredSize(new Dimension(90, 60));
        buttons[17].setPreferredSize(new Dimension(90, 60));
        buttons[18].setPreferredSize(new Dimension(90, 60));


        this.add(buttons[16]);
        this.add(buttons[17]);
        this.add(buttons[18]);

        this.add(buttons[6]);
        this.add(buttons[7]);
        this.add(buttons[8]);
        this.add(buttons[15]);

        this.add(buttons[3]);
        this.add(buttons[4]);
        this.add(buttons[5]);
        this.add(buttons[14]);

        this.add(buttons[0]);
        this.add(buttons[1]);
        this.add(buttons[2]);
        this.add(buttons[13]);

        this.add(buttons[9]);
        this.add(buttons[11]);
        this.add(buttons[12]);
        this.add(buttons[10]);

        for (JButton button : buttons) {
            button.addActionListener(this);
        }


        this.setVisible(true);

    }
    public void addOperation(int input, boolean turn) {
        if(turn) {
        this.input += input;
            textArea.setText(this.input);
        } else {
            this.secondInput += input;
            textArea.setText(secondInput);
        }

    }
    public void addOperation(String input, boolean turn) {
        if(turn) {
            this.input += input;
            textArea.setText(this.input);
            System.out.println("one");
        } else {
            this.secondInput += input;
            textArea.setText(secondInput);
            System.out.println("two");
        }
    }
    public void doOperation(int operation) {
        if(operation == 0) {
            textArea.setText("0");
            return;
        }
        try{
            double inputOne = Double.parseDouble(input);
            double inputTwo = Double.parseDouble(secondInput);
            switch (operation) {
                case 1: output = inputOne+inputTwo;
                break;
                case 2: output = inputOne-inputTwo;
                break;
                case 3: output = inputOne*inputTwo;
                break;
                case 4: output = inputOne/inputTwo;
                break;
            }
            textArea.setText(String.valueOf(output));
            firstInputTurn = false;
            input = Double.toString(output);
            secondInput = "0";
        } catch (NumberFormatException e){
            if(output != 0) {
                textArea.setText(String.valueOf(output));
                return;
            }
            textArea.setText("Please enter a valid number");
            input = "";
            secondInput = "";
            currentOperation = 0;
            firstInputTurn = true;
        } catch (ArithmeticException e) {
            textArea.setText("Cannot divide by Zero");
            input = "";
            secondInput = "";
            currentOperation = 0;
            firstInputTurn = true;
        }
    }
}
