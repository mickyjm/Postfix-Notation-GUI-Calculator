/**
 * Import Java Stuff
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.EmptyStackException;

/**
 * GUICalculator Class
 * Graphic User Interface PostFix Calculator
 * @author Michael Mangrobang
 *
 **/
public class GUICalculator extends JFrame implements ActionListener {

  /**
   * Serial ID 
   */
  private static final long serialVersionUID = 1L;
  
  JPanel[] row = new JPanel[6];
  JTextArea display = new JTextArea(1, 20);
  JTextArea displayResult = new JTextArea(1, 20);
  JButton[] button = new JButton[19];
  String[] buttonString = { "7", "8", "9", "+",
                            "4", "5", "6", "-",
                            "1", "2", "3", "*",
                            ".", "/", "EMPTY", "POP", 
                            "+/-", "PUSH", "0" };
  int[] dimW = { 600, 90, 200, 180 };
  int[] dimH = { 70, 80 };
  Dimension displayDimension = new Dimension(dimW[0], dimH[0]);
  Dimension regularDimension = new Dimension(dimW[1], dimH[1]);
  Dimension rColumnDimension = new Dimension(dimW[2], dimH[1]);
  Dimension zeroButDimension = new Dimension(dimW[3], dimH[1]);
  boolean[] function = new boolean[4];
  boolean calculated = false;
  boolean emptyDisplay = true;
  int stacksize = 0;
  ArrayStack<Double> stack = new ArrayStack<Double>();
  double[] temporary = { 0, 0 };
  double pushdub, d1, d2, result, dubtemp;
  Font font = new Font("Times new Roman", Font.BOLD, 42);

/**
 * Default Constructor
 */
  public GUICalculator() {
    
    super("PostFixCalculator");
    setDesign();
    setSize(760, 550);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    GridLayout grid = new GridLayout(6, 5);    
    setLayout(grid);

    for (int i = 0; i < 4; i++) {
      
      function[i] = false;
      
    }
    
    FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
    FlowLayout f2 = new FlowLayout(FlowLayout.CENTER, 1, 1);
    
    for (int i = 0; i < 6; i++) {
      row[i] = new JPanel();
    }
    
    row[0].setLayout(f1);
    row[1].setLayout(f1);
    
    for (int i = 2; i < 6; i++) {
      
      row[i].setLayout(f2);
      
    }

    for (int i = 0; i < 19; i++) {
      
      button[i] = new JButton();
      button[i].setText(buttonString[i]);
      button[i].setFont(font);
      button[i].addActionListener(this);
      
    }

    display.setFont(font);
    display.setEditable(false);
    display.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    display.setPreferredSize(displayDimension);
    displayResult.setFont(font);
    displayResult.setEditable(false);
    displayResult.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    displayResult.setPreferredSize(displayDimension);
    
    for (int i = 0; i < 14; i++) {
      
      button[i].setPreferredSize(regularDimension);
    
    }
    
    for (int i = 14; i < 18; i++) {
      
      button[i].setPreferredSize(rColumnDimension);
    
    }
    
    button[18].setPreferredSize(zeroButDimension);

    row[0].add(display);
    row[1].add(displayResult);
    add(row[0]);
    add(row[1]);

    for (int i = 0; i < 4; i++) {
    
      row[2].add(button[i]);
    
    }
    
    row[2].add(button[14]);
    add(row[2]);

    for (int i = 4; i < 8; i++) {
    
      row[3].add(button[i]);
    
    }
    
    row[3].add(button[15]);
    add(row[3]);

    for (int i = 8; i < 12; i++) {
    
      row[4].add(button[i]);
    
    }
    
    row[4].add(button[16]);
    add(row[4]);

    row[5].add(button[18]); 
    
    for (int i = 12; i < 14; i++) {
    
      row[5].add(button[i]);
    }
    
    row[5].add(button[17]);
    add(row[5]);

    setVisible(true);
          
    setResult();
     
  } // end default constructor

  
  /**
   * setResult Method.
   * Sets Result Display
   */
  public void setResult() {
    
    try {
      
      if (stack.empty()) {
        
        displayResult.setText("");
        
      } else {
        
        if (stack.peek() <= 0) {
          
          double temp = stack.peek();
          temp = -temp;
          displayResult.setText(temp + "-");
          
        } else {
          
          displayResult.setText(Double.toString(stack.peek()));
          
        }
        
      }
      
    } catch (NumberFormatException e) {
      
    }
    
  }

  
  /**
   * Sets design
   */
  public final void setDesign() {
    
    try {
      
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
      
    }
    
    catch (Exception e) {

    }

  } // end setDesign() method


  /**
   * Clear() method. Clears screen.
   */
  public void empty() {
    
    try {

      display.setText("");
      
      for (int i = 0; i < 4; i++) {
        
        function[i] = false;
        
      }
      
      for (int i = 0; i < 2; i ++) {
        
        temporary[i] = 0;
        
      }
      
      while (!stack.empty()) {
        
        stack.pop();
        
      }

      setResult();
      
    }
    
    catch (NullPointerException e) {
      
    }
    
  } // end clear() method.


  /**
   * Changes Number to Positive or Negative
   */
  public void setPosNeg() {
    
    try {
      
      String splits = display.getText();
      
      if (splits.contains(".")) {
        
        double dvalue = Double.parseDouble(splits);
      
        if (dvalue != 0) {
        
          dvalue = -dvalue;
          display.setText(Double.toString(dvalue));
        
        }
      
      } else {
        
        long ivalue = Long.parseLong(splits);
        
        if (ivalue != 0) {
          
          ivalue = -ivalue;
          display.setText(Long.toString(ivalue));
          
        }
        
      }
      
    }
    
    catch (NumberFormatException e) {
      
    }
    
  } // end getPosNeg() method


  /**
   * Push Method. Pushes number into stack.
   */
  public void push() {  
    
    try {

      String splits = display.getText();
      
      if (splits.contains("-1") || splits.contains("-2") ||
          splits.contains("-2") || splits.contains("-3") ||
          splits.contains("-4") || splits.contains("-5") ||
          splits.contains("-6") || splits.contains("-7") ||
          splits.contains("-8") || splits.contains("-9") ||
          splits.contains("-0") || splits.contains("-.")) {
        
        stack.push(Double.parseDouble(splits));
        display.setText("");
        
      } else
      
      if (splits.contains("+") || splits.contains("-") || 
          splits.contains("*") || splits.contains("/")) {
        
        char operand = splits.charAt(0);
        
        switch (operand) {
        
        case '+':
          add();
          break;
          
        case '-':
          subtract();
          break;
          
        case '*':
          multiply();
          break;
        
        case '/':
          divide();
          break;
        
        }
        
      } else {
        
        stack.push(Double.parseDouble(splits));
        
      }
      
      display.setText("");
      setResult();    
    
    }
    
    catch (NumberFormatException e) {
    
    }
    
  } // end push() method.  

  
  /**
   * Add Method.
   */
  public void add() {
    
    try {
    
      d1 = stack.pop();
      d2 = stack.pop();
      result = d2 + d1;
      stack.push(result);
      function[0] = false;
    
    }
    
    catch (NumberFormatException e) {
    
    }
    
  } // end add() method
  
  
  /**
   * Subtract Method.
   */
  public void subtract() {
    
    
    try {

      d1 = stack.pop();
      d2 = stack.pop();
      result = d2 - d1;
      stack.push(result);
      function[1] = false;
      
    }
    
    catch (NumberFormatException e) {
    
    }
    
  } // end subtract() method.
  
  
  /**
   * Multiply Method.
   */
  public void multiply() {
    
    try {

      d1 = stack.pop();
      d2 = stack.pop();
      result = d2 * d1;
      stack.push(result);
      function[2] = false;
      
    }   
    
    catch (NumberFormatException e) {
    
    }
    
  } // end multiply() method.
  
  
  /**
   * Divide Method.
   */
  public void divide() {
    
    try {

      d1 = stack.pop();
      d2 = stack.pop();
      result = d2 / d1;
      stack.push(result);
      function[3] = false;
      
    }
    
    catch (NumberFormatException e) {
    
    }
    
  } // divide method.

  
  /**
   * Calculate Method.
   */
  public void pop() {
    
    try {
      
      if (!stack.empty()) {
        
        stack.pop();
        
      }
      
      setResult();
      
    }
    
    catch (NumberFormatException e) {
      
    }
    
  }
  
  
  public boolean checkOperands() {
    
    String splits = display.getText();
    return splits.contains("+") || splits.contains("-") || 
           splits.contains("*") || splits.contains("/") ||
           splits.contains(".");
    
  }
  
  public boolean checkNum() {
    
    return display.getText().matches("\\d+");
    
  }


  /**
   * actionPerformed Method.
   * Performs calculator button.
   */
  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent ae) {
    
    if (ae.getSource() == button[0]) {

      display.append("7");
      
    }
    
    if (ae.getSource() == button[1]) {
      
      display.append("8");
      
    }
    
    if (ae.getSource() == button[2]) {

      display.append("9");
      
    }
    
    if (ae.getSource() == button[3]) {
      
      function[0] = true;
      
      if (checkOperands() || checkNum()) {
        
      } else {
      
        display.append("+");
      
      }
      
    }
    
    if (ae.getSource() == button[4]) {

      display.append("4");
      
    }
    
    if (ae.getSource() == button[5]) {

      display.append("5");
      
    }
    
    if (ae.getSource() == button[6]) {
    
      display.append("6");
      
    }
      
    if (ae.getSource() == button[7]) {

      function[1] = true;
      
      if (checkOperands() || checkNum()) {
        
      } else {
      
        display.append("-");
      
      }
      
    }
    
    if (ae.getSource() == button[8]) {
      
      display.append("1");
      
    }
    
    if (ae.getSource() == button[9]) {
      
      display.append("2");
      
    }
    
    if (ae.getSource() == button[10]) {
    
      display.append("3");
      
    }
    
    if (ae.getSource() == button[11]) {
      
      function[2] = true;

      if (checkOperands() || checkNum()) {
        
      } else {
      
        display.append("*");
      }
      
    }
    
    if (ae.getSource() == button[12]) {
    
      if (display.getText().contains("+") || display.getText().contains("*") || 
          display.getText().contains("/")|| display.getText().contains(".")) {
        
      } else if (display.getText().isEmpty()) {
      
        display.append("0.");
      
      } else {
        
        display.append(".");
        
      }
      
    }
    
    if (ae.getSource() == button[13]) {
      
      function[3] = true;

      if (checkOperands() || checkNum()) {
        
      } else {
        
        display.append("/");
        
      }
      
    }
    
    if (ae.getSource() == button[14]) {
      
      empty();
      
    }
    
    if (ae.getSource() == button[15]) {
     
      pop();
      
    }
    
    if (ae.getSource() == button[16]) {
      
      setPosNeg();
      
    }
    
    if (ae.getSource() == button[17]) {
     
      push();
      
    }
    
    if (ae.getSource() == button[18]) {
    
      display.append("0");
      
    }
    
  }
  
  /**
   * @param args
   */
  public static void main(String[] args) {

    GUICalculator c = new GUICalculator();

  }


}
