import java.util.ArrayList;
import java.util.Scanner;

class Node {
    private String root;
    private Node parent;
    private ArrayList<Node> children;

    public Node(String root) {
        this.root = root;
        children = new ArrayList<>();
    }

    public int getChildrenSize(){
        return this.children.size();
    }

    public Node getChildren(int i){
        return this.children.get(i);
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
    private String nome;
    private Node root;
    private ArrayList<Node> nodes = new ArrayList<>();

    public Document(String nome){
        Node root = new Node("ROOT");

        this.nome = nome;
        this.root = root;
        this.nodes.add(root);
    }

    public String getNome(){
        return this.nome;
    }

    public void addNode(Node parent, String value){
        Node newNode = parent.addNode(value);
        this.nodes.add(newNode);
    }

    public void preOrderPrint(Boolean printLevel){
        if (printLevel) System.out.println("    -> level: 0 - " + this.root);
        else System.out.println("    -> " + this.root);
        printRecursive(this.root, printLevel);
    }

    public void printRecursive(Node node, Boolean printLevel){
        for(int i = 0; i < node.getChildrenSize(); i++){
            Node children = node.getChildren(i);
            if (printLevel) System.out.println("    -> level: " + String.valueOf(children.getLevel()) + " - " + children);
            else System.out.println("    -> " + children);
            printRecursive(children, printLevel);
        }
    }

    public Node search(String value){
        if(this.root.toString().equals(value)) return this.root;
        return searchNode(this.root, value);
    }
    public Node searchNode(Node node, String value){
        Node result = null;
        for(int i = 0; i < node.getChildrenSize(); i++){
            Node children = node.getChildren(i);
            if(children.toString().equals(value)) return children;
            result = searchNode(children, value);
            if(result != null) return result;
        }
        return null;
    }
}

public class DocumentAssembler {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String value = "", parentValue, childrenValue;
        ArrayList<Document> documents = new ArrayList<>();;

        System.out.println(" Document Assembler");
        while (!value.equals("3")) {
            System.out.println("***********************************");
            System.out.println("-> 1: Novo documento");
            System.out.println("-> 2: Listar documentos");
            System.out.println("-> 3: Sair");
            System.out.println("***********************************");
            System.out.print("-> ");
            value = scanner.next();

            switch(value) {
                case "1": {
                    System.out.print("\n-> Informe o nome do documento: ");
                    value = scanner.next();
                    Document document = new Document(value);
                    documents.add(document);
                    System.out.println("\n-> Informe os nós do documento na forma 'VALOR_PAI VALOR_FILHO'");
                    System.out.println("-> OBS. 1: O primeiro nó já foi criado com o nome 'ROOT'");
                    System.out.println("-> OBS. 2: Informe -1 para sair\n");
                    System.out.print("-> ");
                    value = scanner.next();
                    while(!value.equals("-1")){
                        parentValue = value;
                        childrenValue = scanner.next();
                        try {
                            document.addNode(document.search(parentValue), childrenValue);                            
                        } catch (Exception e) {
                            System.out.println("-> Input inválido! Os nós pais disponíveis são:");
                            document.preOrderPrint(false);
                        }
                        System.out.print("-> ");
                        value = scanner.next();
                    }
                    document.preOrderPrint(true);
                    System.out.println();
                    break;
                }
                case "2": {
                    System.out.println("\n-> Os documentos disponíveis são os seguintes:");
                    for(int i = 0; i < documents.size(); i ++){
                        System.out.println("  -> " + documents.get(i).getNome());
                        documents.get(i).preOrderPrint(true);
                    }
                    System.out.println();
                    break;
                }        
            } 
        };

        // Document document1 = new Document("documento 1");
        // document1.addNode(document1.search("A"), "B");
        // document1.addNode(document1.search("A"), "C");
        // document1.addNode(document1.search("B"), "D");
        // document1.addNode(document1.search("C"), "E");
        // document1.addNode(document1.search("C"), "F");
        // document1.addNode(document1.search("D"), "G");        
        // document1.addNode(document1.search("E"), "H");
        // document1.addNode(document1.search("E"), "I");
        // document1.preOrderPrint();

    }
}