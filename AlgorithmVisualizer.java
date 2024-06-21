import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AlgorithmVisualizer extends JFrame {
    private int[] array;
    private final int arraySize = 25;
    private final int minElement = 20;
    private final int maxElement = 400;
    private final int barWidth = 50;
    private final int delay = 150; // Adjust the delay to control the speed of visualization
    private SortingAlgorithm sortingAlgorithm;
    private String selectedAlgorithm = "Insertion Sort"; // Default sorting algorithm

    public AlgorithmVisualizer() {
        setTitle("Algorithm Visualizer ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        initializeArray();
        sortingAlgorithm = new SortingAlgorithm();
        add(new ArrayPanel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        startSorting();

        JComboBox<String> algorithmComboBox = new JComboBox<>(new String[]{"Bubble Sort", "Selection Sort","Insertion Sort"});
        algorithmComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
                // Clear the array and reset the visualization
                initializeArray();
                repaint();
            }
        });
        add(algorithmComboBox);
    }

    private void initializeArray() {
        array = new int[arraySize];
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(maxElement - minElement + 1) + minElement;
        }
    }

    private void startSorting() {
        Thread thread = new Thread(() -> {
            try {
                if (selectedAlgorithm.equals("Bubble Sort")) {
                    sortingAlgorithm.bubbleSort(array, this);
                } else if (selectedAlgorithm.equals("Selection Sort")) {
                    sortingAlgorithm.selectionSort(array, this);
                } else if (selectedAlgorithm.equals("Insertion Sort")) {
                    sortingAlgorithm.selectionSort(array, this);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }


    private class ArrayPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int x = 10;
            for (int value : array) {
                g2d.setColor(Color.BLUE);
                g2d.fillRect(x, getHeight() - value, barWidth, value);
                x += barWidth + 1;
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(arraySize * (barWidth + 1) + 20, maxElement + 20);
        }
    }

    public void updateArray(int[] newArray) {
        array = newArray;
        repaint();
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AlgorithmVisualizer::new);
    }
}



// Step 2: Explanation

// AlgorithmVisualizer is the main class that extends JFrame to create the GUI window. It initializes the array of random elements and starts the sorting process in a separate thread.
// The ArrayPanel class is an inner class that extends JPanel and overrides the paintComponent method to draw the bars representing the elements of the array in the GUI.
// SortingAlgorithm is a separate class responsible for implementing the Bubble Sort algorithm. It takes the array to be sorted and the AlgorithmVisualizer object as parameters. The bubbleSort method sorts the array using the Bubble Sort algorithm, and after each swap, it updates the array in the visualizer to show the current state.
// Step 3: Test the Algorithm Visualizer
// Run the AlgorithmVisualizer class as the main class. A window will pop up, showing the bars representing the array elements. The algorithm will sort the array, and you'll be able to observe the sorting process in real-time as the bars move up and down.

