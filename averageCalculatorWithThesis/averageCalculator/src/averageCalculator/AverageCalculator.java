package averageCalculator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public
class AverageCalculator implements ActionListener {
   JFrame frame;
   JButton submitGrade, removeLastGrade, gradesSubmitted, submitThesis, resetEverything;
   JLabel currentGrades, revealAverage;
   JTextField insertGrade, insertThesis;
   LinkedList<Integer> grades = new LinkedList<>();
   Font textFont = new Font("", Font.PLAIN, 20);
   AverageCalculator() {
      submitGrade = new JButton("Submit grade");
      setButtonValues(submitGrade);

      removeLastGrade = new JButton("Remove last grade");
      setButtonValues(removeLastGrade);

      gradesSubmitted = new JButton("Done");
      setButtonValues(gradesSubmitted);

      submitThesis = new JButton("Submit thesis");
      submitThesis.setVisible(false);
      setButtonValues(submitThesis);

      resetEverything = new JButton("Reset everything");
      resetEverything.setVisible(false);
      setButtonValues(resetEverything);

      insertGrade = new JTextField("Type out your grade [1-10]");
      setTextFieldValues(insertGrade);

      insertThesis = new JTextField("Type out your thesis [1-10]");
      insertThesis.setVisible(false);
      setTextFieldValues(insertThesis);

      currentGrades = new JLabel();
      currentGrades.setFont(textFont);

      revealAverage = new JLabel();
      revealAverage.setFont(textFont);

      frame = new JFrame("Average with thesis calculator");
      frame.setSize(420, 420);
      frame.setLayout(new FlowLayout());

      frame.add(insertGrade);
      frame.add(insertThesis);
      frame.add(submitGrade);
      frame.add(removeLastGrade);
      frame.add(gradesSubmitted);
      frame.add(submitThesis);
      frame.add(currentGrades);
      frame.add(revealAverage);
      frame.add(resetEverything);
      frame.setVisible(true);
   }
  public void setButtonValues(JButton button) {
      button.setBackground(Color.white);
      button.setFont(textFont);
      button.addActionListener(this);
      button.setFocusable(false);
   }
  public void setTextFieldValues(JTextField textField) {
      textField.setPreferredSize(new Dimension(300, 100));
      textField.setBackground(Color.white);
      textField.setFont(textFont);
   }
  public float findGradesAverage(LinkedList<Integer> grades) {
      float average = 0;
      for (Integer grade1 : grades) {
         average += grade1;
      }
      average /= grades.size();
      return average;
   }
  public float findAverageWithThesis(float average, float thesis) {
      float averageWithThesis = (3 * average + thesis) / 4;
      return averageWithThesis;
   }
  public void disposeOfFrame() {
      AverageCalculator AVC = new AverageCalculator();
      frame.dispose();
   }
   @Override
   public void actionPerformed(ActionEvent e) {
      String gradeValue = insertGrade.getText();
      if (e.getSource() == submitGrade) {
         try {
            Integer.parseInt(gradeValue);
         } catch (NumberFormatException ae) {
            JOptionPane.showConfirmDialog(frame, "Please input numbers [1-10] only.", "Invalid input", JOptionPane.CLOSED_OPTION);
         }

         Integer grade = Integer.parseInt(gradeValue);
         if (grade >= 1 && grade <= 10) {
            grades.add(grade);
            currentGrades.setText("Inputted: " + grades.toString());
            insertGrade.setText("");
         } else {
            JOptionPane.showConfirmDialog(frame, "Please input numbers [1-10] only.", "Invalid input", JOptionPane.CLOSED_OPTION);
         }
      }

      if (e.getSource() == removeLastGrade) {
         if (!grades.isEmpty()) {
            grades.removeLast();
            currentGrades.setText("Inputted: " + grades.toString());
         } else {
            JOptionPane.showConfirmDialog(frame, "You didn't input any grades.", "Invalid input", JOptionPane.CLOSED_OPTION);
         }
      }

      if (e.getSource() == gradesSubmitted) {
         if (grades.size() > 1) {
            insertThesis.setVisible(true);
            insertGrade.setVisible(false);
            gradesSubmitted.setVisible(false);
            submitGrade.setVisible(false);
            removeLastGrade.setVisible(false);
            submitThesis.setVisible(true);
            int checkForThesis = JOptionPane.showConfirmDialog(
                frame, "Grades average: " + findGradesAverage(grades) + ". Do you have a thesis for this class as well?",
                "Checking for thesis", JOptionPane.YES_NO_OPTION);
            System.out.println(checkForThesis);
            if (checkForThesis == 1 || checkForThesis == -1) {
               disposeOfFrame();
            }
         } else {
            JOptionPane.showConfirmDialog(frame, "Input more grades..", "Invalid input", JOptionPane.CLOSED_OPTION);
         }
      }

      if (e.getSource() == submitThesis) {
         try {
            Integer.parseInt(insertThesis.getText());
         } catch (NumberFormatException z) {
            JOptionPane.showConfirmDialog(frame, "Input thesis grade [1-10]", "Invalid input", JOptionPane.CLOSED_OPTION);
         }
         Integer thesis = Integer.parseInt(insertThesis.getText());
         if (!(thesis >= 1 && thesis <= 10)) {
            JOptionPane.showConfirmDialog(frame, "Input thesis grade [1-10] only.", "Invalid input", JOptionPane.CLOSED_OPTION);
            return;
         }

         if (insertThesis.getText().isBlank()) {
            JOptionPane.showConfirmDialog(frame, "You didn't input a thesis.", "Invalid input", JOptionPane.CLOSED_OPTION);
            return;
         }
         float average = findGradesAverage(grades);
         revealAverage.setText("Average with thesis: " + findAverageWithThesis(average, thesis));
         resetEverything.setVisible(true);
      }

      if (e.getSource() == resetEverything) {
         disposeOfFrame();
      }
   }
}
