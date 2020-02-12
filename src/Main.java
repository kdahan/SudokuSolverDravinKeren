import java.util.Random;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int populationSize = 50;
        int maxIterations = 5000;
        int iteration = 0;
        Sudoku.setInitialGene(new int[]{1, 0, 0, 4, 0, 0, 0, 0, 0, 3, 2, 0, 0, 0, 0, 0});
        Sudoku currentSudoku = new Sudoku(GeneticOperators.initialize(Sudoku.getInitialGene().clone()));

        while (currentSudoku.getFitnessValue() != 0 && iteration < maxIterations) {
            System.out.println("current: " + currentSudoku + ", iteration: " + iteration);
            Vector<Sudoku> population = new Vector<>();
            for (int i = 0; i < populationSize; i++) population.add(new Sudoku(currentSudoku.getGene().clone()));

            for (int i = 0; i < population.size(); i++) {
                if (random.nextBoolean())
                    population.get(i).mutation();
                else
                    population.get(i).crossover(population.get(random.nextInt(population.size())));
            }
            if (random.nextBoolean())
                currentSudoku = Sudoku.bestSelection(population);
            else
                currentSudoku = Sudoku.rouletteSelection(population);
            iteration++;
        }


        System.out.println("goal: " + currentSudoku + ", iteration: " + iteration);

    }


}