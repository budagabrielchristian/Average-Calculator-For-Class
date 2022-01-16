<img src = "https://img.shields.io/badge/Ask%20me-anything-1abc9c.svg"></img>
# Average-Calculator-With-Thesis
Find out your semestrial average with thesis at a certain class by using this computer!
I simply had to use 2-3 formulas, most of the code is the button logic really.

This code contains major updates in comparison to the others:
```
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
		for(Integer grade1:grades) {
			average+=grade1;
		}
		average/=grades.size();
		return average;
	}
	public float findAverageWithThesis(float average, float thesis) {
		float averageWithThesis = (3*average+thesis)/4;
		return averageWithThesis;
	}
```
These functions make the code easier to understand and to manipulate.
