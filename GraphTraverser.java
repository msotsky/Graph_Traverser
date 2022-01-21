import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
/*
Maxime Sotsky
2631 Midterm # 2
Dr. Keliher
*/
public class GraphTraverser {
    public static void main(String[] args) throws FileNotFoundException{

        boolean graphExists = false;
        boolean startExists = false;
        Graph g = null;
        String in = console();
        Scanner sc = new Scanner(System.in);

        while(true){
            //creating graph
            if(in.equals("i")){//create graph with .txt
                System.out.println("Enter full file path: ");
                String input = sc.nextLine();
                File file = new File(input);
                g = new Graph(file);
                graphExists = true;
                in = console();
            }
            //specifying start node
            if(in.equals("s")){
                if(graphExists == false){
                    System.out.println("No graph was made, please return to the menue and create a graph before assigning start node");
                    in = console();
                }
                else{
                    while(true){
                        System.out.println("Enter the label of the node you wish to make the start node: ");
                        String input = sc.nextLine();
                        boolean dn = g.searchList_AssignStart(input);
                        if(dn == true){
                            System.out.println("Start successfully set to: " + input);
                            startExists = true;
                            break;
                        }
                        else
                            System.out.println("Node not found, please try again");
                    }
                    in = console();
                }
            }
            //unreachable nodes starting at startNode
            if(in.equals("u")){
                if(graphExists == false && startExists == false){
                    System.out.println("No graph was made, please return to the menue and create a graph before assigning start node (i)");
                    System.out.println("No start node was assigned, please return to the menue and assign a start node (s)");
                    in = console();
                }
                if(graphExists == false){
                    System.out.println("No graph was made, please return to the menue and create a graph before assigning start node (i)");
                    in = console();
                }
                if(startExists == false){
                    System.out.println("No start node was assigned, please return to the menue and assign a start node (s)");
                    in = console();
                }
                else
                    g.nonReachable(g.searchStart());
        
                in = console();
            }
            //exit
            if(in.equals("q")){
                System.out.println("EXITING . . .");
                System.exit(0);
            }
            //pre-order dfs
            if(in.equals("1")){
                if(graphExists == false && startExists == false){
                    System.out.println("No graph was made, please return to the menue and create a graph before assigning start node (i)");
                    System.out.println("No start node was assigned, please return to the menue and assign a start node (s)");
                    in = console();
                }
                if(graphExists == false){
                    System.out.println("No graph was made, please return to the menue and create a graph before assigning start node (i)");
                    in = console();
                }
                if(startExists == false){
                    System.out.println("No start node was assigned, please return to the menue and assign a start node (s)");
                    in = console();
                }
                else
                    g.preDFS(g.searchStart());
            
                in = console();
            }
            if(in.equals("2")){
                if(graphExists == false && startExists == false){
                    System.out.println("No graph was made, please return to the menue and create a graph before assigning start node (i)");
                    System.out.println("No start node was assigned, please return to the menue and assign a start node (s)");
                    in = console();
                }
                if(graphExists == false){
                    System.out.println("No graph was made, please return to the menue and create a graph before assigning start node (i)");
                    in = console();
                }
                if(startExists == false){
                    System.out.println("No start node was assigned, please return to the menue and assign a start node (s)");
                    in = console();
                }
                else
                    g.postDFS(g.searchStart());
            
                in = console();
            }
            if(in.equals("3")){
                if(graphExists == false && startExists == false){
                    System.out.println("No graph was made, please return to the menue and create a graph before assigning start node (i)");
                    System.out.println("No start node was assigned, please return to the menue and assign a start node (s)");
                    in = console();
                }
                if(graphExists == false){
                    System.out.println("No graph was made, please return to the menue and create a graph before assigning start node (i)");
                    in = console();
                }
                if(startExists == false){
                    System.out.println("No start node was assigned, please return to the menue and assign a start node (s)");
                    in = console();
                }
                else
                    g.lexAndBFS(g.searchStart());
            
                in = console();
            }
        }
    }
    public static String console(){
        System.out.println("Enter one of the characters below to choose the associated option: ");
        System.out.println("i - input a graph");
        System.out.println("s - pick a start node");
        System.out.println("u - list the unreachable nodes");
        System.out.println("q - quit");
        System.out.println("1 - pre-order depth-first traversal");
        System.out.println("2 - post-order depth-first traversal");
        System.out.println("3 - breadth-first traversal");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println("Your choice: " +input);

        if(!input.equals("i") && !input.equals("s") && !input.equals("u") && !input.equals("q") && !input.equals("1") && !input.equals("2") && !input.equals("3")){
            System.out.println("Wrong input");
            console();
        }
            
        //sc.close();
        return input;
    }
}
