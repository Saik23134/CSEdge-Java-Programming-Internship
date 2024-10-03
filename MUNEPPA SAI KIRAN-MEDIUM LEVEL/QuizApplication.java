import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApplication {
    private JFrame frame;
    private JPanel panel;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup optionsGroup;
    private JButton nextButton;
    private JTextArea resultArea;
    
    private String[] questions = {
        "What is the capital of India?",
        "What is 2 + 3?",
        "What is the largest ocean on Earth?",
        "What is the capital of China?"
    };
    
    private String[][] optionsList = {
        {"Berlin", "Mumbai", "Delhi", "Kathmandu"},
        {"3", "5", "15", "6"},
        {"Atlantic", "Indian", "Arctic", "Pacific"},
        {"Shanghai", "Beijing", "Delhi", "Kathmandu"}
    };
    
    private int[] answers = {2, 1, 3, 1}; // Correct answers (indexes)
    private int currentQuestionIndex = 0;
    private int score = 0;

    public QuizApplication() {
        frame = new JFrame("Online Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        
        questionLabel = new JLabel();
        panel.add(questionLabel);
        
        optionsGroup = new ButtonGroup();
        options = new JRadioButton[4];
        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton();
            optionsGroup.add(options[i]);
            panel.add(options[i]);
        }
        
        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length) {
                    loadQuestion();
                } else {
                    showResults();
                }
            }
        });
        
        panel.add(nextButton);
        
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        panel.add(resultArea);
        
        frame.add(panel);
        
        loadQuestion();
        
        frame.setVisible(true);
    }
    
    private void loadQuestion() {
        questionLabel.setText(questions[currentQuestionIndex]);
        String[] currentOptions = optionsList[currentQuestionIndex];
        for (int i = 0; i < options.length; i++) {
            options[i].setText(currentOptions[i]);
            options[i].setSelected(false);
        }
    }
    
    private void checkAnswer() {
        int selectedOption = -1;
        for (int i = 0; i < options.length; i++) {
            if (options[i].isSelected()) {
                selectedOption = i;
                break;
            }
        }
        if (selectedOption == answers[currentQuestionIndex]) {
            score++;
        }
    }
    
    private void showResults() {
        resultArea.setText("Quiz Completed!\nYour score: " + score + "/" + questions.length);
        nextButton.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizApplication());
    }
}
