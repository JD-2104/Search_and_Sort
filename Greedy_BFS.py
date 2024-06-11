import heapq

def greedy_best_first_search(graph, start, goal, heuristic):
    # Create a priority queue and add the start node with heuristic cost
    frontier = []
    heapq.heappush(frontier, (heuristic[start], start))
    
    # Set to keep track of visited nodes
    visited = set()
    
    while frontier:
        # Pop the node with the lowest heuristic cost
        current_cost, current_node = heapq.heappop(frontier)
        
        # If the goal is reached, return True
        if current_node == goal:
            return True
        
        # Mark the current node as visited
        visited.add(current_node)
        
        # Iterate through neighbors
        for neighbor, _ in graph[current_node]:
            if neighbor not in visited:
                heapq.heappush(frontier, (heuristic[neighbor], neighbor))
    
    # If the goal is not reached, return False
    return False

# Heuristic function (Manhattan distance for example)
def heuristic(node, goal):
    x1, y1 = node
    x2, y2 = goal
    return abs(x2 - x1) + abs(y2 - y1)

# Example graph (dictionary where keys are nodes and values are lists of tuples of neighbors and their costs)
graph = {
    'A': [('B', 1), ('C', 3)],
    'B': [('D', 1), ('E', 6)],
    'C': [('F', 5)],
    'D': [],
    'E': [('G', 2)],
    'F': [('G', 2)],
    'G': []
}

# Heuristic values for each node (example values)
heuristic_values = {
    'A': 6,
    'B': 4,
    'C': 4,
    'D': 6,
    'E': 2,
    'F': 1,
    'G': 0
}

start_node = 'A'
goal_node = 'G'

if greedy_best_first_search(graph, start_node, goal_node, heuristic_values):
    print("Goal found!")
else:
    print("Goal not found!")


