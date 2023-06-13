import java.util.PriorityQueue;
import java.util.Scanner;

public class ToyStore {
    private String[] toyIds;
    private String[] toyNames;
    private int[] toyWeights;
    private PriorityQueue<Toy> toyQueue;

    public ToyStore(String[] ids, String[] names, int[] weights) {
        if (ids.length != names.length || ids.length != weights.length) {
            throw new IllegalArgumentException("The lengths of id, name, and weight arrays must be the same.");
        }

        toyIds = ids;
        toyNames = names;
        toyWeights = weights;

        toyQueue = new PriorityQueue<>(toyIds.length, (t1, t2) -> t2.getWeight() - t1.getWeight());
        for (int i = 0; i < toyIds.length; i++) {
            toyQueue.add(new Toy(toyIds[i], toyNames[i], toyWeights[i]));
        }
    }

    
    public void addNewToy(String id, String name, int weight) {
        toyQueue.add(new Toy(id, name, weight));
    }

    
    public Toy getNextToy() {
        return toyQueue.poll();
    }

    
    private class Toy {
        private String id;
        private String name;
        private int weight;

        public Toy(String id, String name, int weight) {
            this.id = id;
            this.name = name;
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Toy{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        String[] ids = {"1", "2", "3"};
        String[] names = {"Toy1", "Toy2", "Toy3"};
        int[] weights = {3, 1, 2};

        ToyStore toyStore = new ToyStore(ids, names, weights);

        while (running) {
            System.out.println("Меню:");
            System.out.println("1. Добавить новую игрушку");
            System.out.println("2. Получить следующую игрушку");
            System.out.println("0. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("Введите id новой игрушки:");
                    String id = scanner.nextLine();
                    System.out.println("Введите название новой игрушки:");
                    String name = scanner.nextLine();
                    System.out.println("Введите вес новой игрушки:");
                    int weight = scanner.nextInt();
                    scanner.nextLine(); 
                    toyStore.addNewToy(id, name, weight);
                    System.out.println("Новая игрушка успешно добавлена!");
                    break;
                case 2:
                    if (!toyStore.toyQueue.isEmpty()) {
                        ToyStore.Toy nextToy = toyStore.getNextToy();
                        System.out.println("Следующая игрушка: " + nextToy);
                    } else {
                        System.out.println("Магазин игрушек пуст!");
                    }
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Некорректный выбор! Попробуйте еще раз.");
            }

            System.out.println();
        }

        System.out.println("Работа программы завершена.");
        scanner.close();
    }
}
