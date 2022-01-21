import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
/*
Maxime Sotsky
2631 Midterm # 2
Dr. Keliher
*/
public class Graph {
    //input file format (directed graph)
    //First line: 2 integers n (1 <= n <= 1000) , m (0 <= m <= n(n-1))
    //            separated by a space, where n = numNodes and m = numEdges
    //Each of the next n lines contains a node label (string lower/upper with length at most 20)
    //Each of the next m lines specifies an edge as two node labels L1 and L2
    //  separated by a space, meaning there is a directed edge from L1 node with that label to L2 node with that label  
    //All n node labels are distinct, no self loops, and no directed edge specified more than once
    
    //constructor taking in file object and reads in the data from the corresponding disk file.

    private int nNodes = 0;
    private int nEdges = 0;
    private List<Node> nodeList = new ArrayList<>();
    private int nNodes_File = 0;
    private int nEdges_File = 0;
    //private Node startNode;

    public Graph (File file) throws FileNotFoundException{
        Scanner sc = new Scanner(file);
        nNodes_File = sc.nextInt();
        nEdges_File = sc.nextInt();

        for(int i = 0; i <= nNodes_File; i++){  
            String line = sc.nextLine();
            addNode(line);
            
        }
        nodeList.remove(0); //rid empty  index
        for(int i = 0; i < nEdges_File; i++){
            String line = sc.nextLine();
            String[] splitLine = line.split("\\s+");
            addEdge(searchNode(splitLine[0]), searchNode(splitLine[1]));
        }

    }
    public Node addNode(String label){
        Node aNode = new Node(label,false);
        nodeList.add(aNode);
        nNodes++;
        return aNode;
    }
    public void addEdge(Node from, Node to){
        from.neighbors.add(to);
        nEdges++;
    }
    public List<Node> getNodeList(){
        return nodeList;
    }
    public boolean searchList_AssignStart(String target){
        for(Node n : nodeList){
            if(n.getLabel().equals(target)){
                n.setStartNode();
                return true;
            }
                
        }
        return false;
    }
    public Node searchStart(){
        for(Node n : nodeList){
            if(n.start == true){
                return n;
            }
        }
        return null;
    }

    public Node searchNode(String label){
        for(Node n : nodeList){
            if(n.getLabel().equals(label))
                return n;
        }
        return null;
    }

    public void nonReachable(Node aNode){
        List<Node> reach = new ArrayList<>(nodeList);
        reach = dfs(aNode,reach);
        String[] x = new String[reach.size()];
        for(int i = 0; i < reach.size();i++){
            x[i] = reach.get(i).getLabel();
        }
        Arrays.sort(x);
        for(int i = 0; i < x.length;i++){
            System.out.print(" [ " + x[i] + " ] ");
        }
        System.out.println();

        //System.out.println(reach.toString());
        resetVisited();
    }

    public void resetVisited(){
        for(Node n : nodeList){
            n.visited = false;
        }
    }

    //Pre-order: list nodes in the order they are first visited by DFS
    //Post-order: list nodes in the order they are last visisted by DFS

    public void preDFS(Node aNode){
        List<Node> reach = new ArrayList<>(nodeList);
        List<Node> res = new ArrayList<>();
        res = pre_order_dfs(aNode, reach, res);
        System.out.println("Pre-Order: " + res.toString());

        String[] x = new String[res.size()];
        for(int i = 0; i < res.size();i++){
            x[i] = res.get(i).getLabel();
        }
        Arrays.sort(x);
        System.out.print("Lexicographically: ");
        for(int i = 0; i < x.length;i++){
            System.out.print(" [ " + x[i] + " ] ");
        }
        System.out.println();
        resetVisited();

    }

    public List<Node> pre_order_dfs(Node aNode, List<Node> reach, List<Node> res){
        aNode.visited = true;
        if(res.size() == 0)
            res.add(aNode);
        for(Node n : aNode.neighbors){
            if(n.visited == false){
                res.add(n);
                pre_order_dfs(n,reach,res);
            }         
        }
        return res; //print later
    }

    public List<Node> postDFS(Node aNode){
        List<Node> reach = new ArrayList<>(nodeList);
        List<Node> res = new ArrayList<>();
        res = post_order_dfs(aNode, reach, res);
        System.out.println("Post-Order: " + res.toString());

        String[] x = new String[res.size()];
        for(int i = 0; i < res.size();i++){
            x[i] = res.get(i).getLabel();
        }
        Arrays.sort(x);
        System.out.print("Lexicographically: ");
        for(int i = 0; i < x.length;i++){
            System.out.print(" [ " + x[i] + " ] ");
        }
        System.out.println();
        resetVisited();
        return res;

    }
    public List<Node> post_order_dfs(Node aNode, List<Node> reach, List<Node> res){
        aNode.visited = true;

        for(Node n : aNode.neighbors){
            if(n.visited == false){
                post_order_dfs(n,reach,res);
            }
        }
        res.add(aNode);
        return res;

    }

    //for "u" option (redundant)
    public List<Node> dfs(Node aNode, List<Node> reach){
        
        aNode.visited = true;
        reach.remove(aNode);
        for(Node n : aNode.neighbors){
            if(n.visited == false){
                reach.remove(n);
                dfs(n,reach);
            }                
        }
        return reach;
    }

    public void lexAndBFS(Node aNode){
        List<Node> res = new ArrayList<>();
        res = bfs(aNode, res);

        String[] x = new String[res.size()];
        for(int i = 0; i < res.size();i++){
            x[i] = res.get(i).getLabel();
        }
        Arrays.sort(x);
        System.out.print("Lexicographically: ");
        for(int i = 0; i < x.length;i++){
            System.out.print(" [ " + x[i] + " ] ");
        }
        System.out.println();
        resetVisited();
    }


    public List<Node> bfs(Node aNode, List<Node> res){
        aNode.visited = true;
        Queue<Node> q = new ArrayDeque<>();
        q.add(aNode);
        System.out.println("Breadth-First: ");
        while(q.isEmpty() == false){
            Node aNode2 = q.remove();
            System.out.print(" [ " + aNode2.getLabel() + " ] ");
            res.add(aNode2);

            for(Node n : aNode2.neighbors){
                if(n.visited == false){
                    n.visited = true;
                    q.add(n);
                }
            }
            aNode2.visited = true;
        }
        System.out.println();
        return res;
    }





//=============================================================================
    class Node implements Comparable<Node>{

        private String label;
        private boolean start;
        private boolean visited;

        //adj list of node
        List<Node> neighbors = new ArrayList<>();

        public Node(String label, boolean start){
            this.label = label;
            this.start = false;
            this.visited = false;

        }
        public void setStartNode(){
            this.start = true;
        }
        public String getLabel(){
            return this.label;
        }
        public void setLabel(String label){
            this.label = label;
        }
        public String toString(){
            return this.label;
        }
        @Override
        public int compareTo(Node aNode){
            if(this.label.equals(aNode.label))
                return 0;
            else
                return 1;
        }


    }
}
