import java.util.*;

class GreedyBestFirstSearch {
    static class Node {
        String name;
        int heuristic;

        Node(String name, int heuristic) {
            this.name = name;
            this.heuristic = heuristic;
        }
    }

    public static boolean greedyBestFirstSearch(Map<String, List<Node>> graph, String start, String goal, Map<String, Integer> heuristic) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(n -> n.heuristic));
        frontier.add(new Node(start, heuristic.get(start)));

        Set<String> visited = new HashSet<>();

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.name.equals(goal)) {
                return true;
            }

            visited.add(current.name);

            for (Node neighbor : graph.getOrDefault(current.name, Collections.emptyList())) {
                if (!visited.contains(neighbor.name)) {
                    frontier.add(new Node(neighbor.name, heuristic.get(neighbor.name)));
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Map<String, List<Node>> graph = new HashMap<>();
        graph.put("A", Arrays.asList(new Node("B", 1), new Node("C", 3)));
        graph.put("B", Arrays.asList(new Node("D", 1), new Node("E", 6)));
        graph.put("C", Arrays.asList(new Node("F", 5)));
        graph.put("D", new ArrayList<>());
        graph.put("E", Arrays.asList(new Node("G", 2)));
        graph.put("F", Arrays.asList(new Node("G", 2)));
        graph.put("G", new ArrayList<>());

        Map<String, Integer> heuristicValues = new HashMap<>();
        heuristicValues.put("A", 6);
        heuristicValues.put("B", 4);
        heuristicValues.put("C", 4);
        heuristicValues.put("D", 6);
        heuristicValues.put("E", 2);
        heuristicValues.put("F", 1);
        heuristicValues.put("G", 0);

        String startNode = "A";
        String goalNode = "G";

        if (greedyBestFirstSearch(graph, startNode, goalNode, heuristicValues)) {
            System.out.println("Goal found!");
        } else {
            System.out.println("Goal not found!");
        }
    }
}