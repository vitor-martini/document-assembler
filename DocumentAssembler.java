import java.util.ArrayList;

class Node {
    private String root;
    private Node parent;
    public ArrayList<Node> children;

    public Node(String root) {
        this.root = root;
        children = new ArrayList<>();
    }

    public Node addNode(String value) {
        Node childNode = new Node(value);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public int getLevel() {
        if (this.isRoot())
            return 0;
        else
            return parent.getLevel() + 1;
    }

    @Override
    public String toString() {
        return root != null ? root.toString() : "null";
    }
}

class Document {
    public String nome;
    public Node root;
    public ArrayList<Node> nodes = new ArrayList<>();

    public Document(String nome){
        Node root = new Node("A");

        this.nome = nome;
        this.root = root;
        this.nodes.add(root);
    }

    public void addNode(Node parent, String value){
        Node newNode = parent.addNode(value);
        this.nodes.add(newNode);
    }

    public void preOrderPrint(){
        System.out.println("level: 0 - " + this.root);
        printRecursive(this.root);
    }

    public void printRecursive(Node node){
        for(int i = 0; i < node.children.size(); i++){
            Node children = node.children.get(i);
            System.out.println("level: " + String.valueOf(children.getLevel()) + " - " + children);
            printRecursive(children);
        }
    }

    public Node search(String value){
        if(this.root.toString().equals(value)) return this.root;
        return searchNode(this.root, value);
    }
    public Node searchNode(Node node, String value){
        Node result = null;
        for(int i = 0; i < node.children.size(); i++){
            Node children = node.children.get(i);
            if(children.toString().equals(value)) return children;
            result = searchNode(children, value);
            if(result != null) return result;
        }
        return null;
    }
}

public class DocumentAssembler {
    public static void main(String[] args) {

        Document document1 = new Document("documento 1");
        document1.addNode(document1.search("A"), "B");
        document1.addNode(document1.search("A"), "C");
        document1.addNode(document1.search("B"), "D");
        document1.addNode(document1.search("C"), "E");
        document1.addNode(document1.search("C"), "F");
        document1.addNode(document1.search("D"), "G");        
        document1.addNode(document1.search("E"), "H");
        document1.addNode(document1.search("E"), "I");


        document1.preOrderPrint();

        
            
    }
}