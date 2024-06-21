    public class SortingAlgorithm {
        public void bubbleSort(int[] array, AlgorithmVisualizer visualizer) throws InterruptedException {
            int n = array.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (array[j] > array[j + 1]) {
                        swap(array, j, j + 1);
                        visualizer.updateArray(array);
                    }
                }
            }
        }
        public void selectionSort(int[] array, AlgorithmVisualizer visualizer) throws InterruptedException {
            int n = array.length;
            for (int i = 0; i < n - 1; i++) {
                int minPos = i;
                for (int j = i+1; j < n ; j++) {
                    if (array[minPos] > array[j]) {
                        minPos=j;
                    }
                }
                int temp = array[minPos];
                array[minPos] = array[i];
                array[i] = temp;

                visualizer.updateArray(array);
            }
        }
        public void insertionSort(int[] array, AlgorithmVisualizer visualizer) throws InterruptedException {
            int n = array.length;
            for (int i = 1; i < n ; i++) {
                int curr = array[i];
                int prev = i-1;
                while(prev>=0 && array[prev]>curr){
                    array[prev+1] = array[prev];
                    prev--;
                }
                array[prev+1] = curr;

                visualizer.updateArray(array);
            }
        }
        
        private void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
    
