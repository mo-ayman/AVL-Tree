package Implementation;

public class TreeAVL {
    private Node root;
    private boolean rebalanced;

    public TreeAVL() {
        this.root = null;
        this.rebalanced = true;
    }

    public Node getRoot() {
        return root;
    }

    private Node insert(String newValue, Node node){
        if(node == null) return new Node(newValue);
        int compareResult = node.getWord().compareToIgnoreCase(newValue);
        if(compareResult > 0){
            Node left = insert(newValue, node.getLeftChild());
            node.setLeftChild(left);
        }
        else if(compareResult < 0){
            Node right = insert(newValue, node.getRightChild());
            node.setRightChild(right);
        }
        node.setHeight();
        if(Math.abs(node.getBalance()) > 1) node = node.reBalance(node.getBalance());
        return node;
    }
    public void insert(String newValue){
        this.root = this.insert(newValue, this.root);
    }

    private Node search(String value, Node node) {
        if(node == null) return null;
        if (node.getWord().equals(value)) return node;
        int compareResult = node.getWord().compareToIgnoreCase(value);
        if(compareResult > 0)
            return search(value, node.getLeftChild());
        else if(compareResult < 0)
            return search(value, node.getRightChild());

        return null;
    }

    public Node search(String value) {
        return this.search(value, this.root);
    }

    private void inOrder(Node node){
        if(node.hasLeftChild()) inOrder(node.getLeftChild());
        System.out.println(node.getWord());
        if(node.hasRightChild())  inOrder(node.getRightChild());
    }
    public void inOrder(){
        inOrder(this.root);
    }
    private void preOrder(Node node) {
        if(node == null) return;
        System.out.print(node.getWord());
        preOrder(node.getLeftChild());
        preOrder(node.getRightChild());
    }
    public void preOrder(){
        preOrder(this.root);
    }



    private Node replace(Node node) {
            while (node.getLeftChild() != null) {
                node = node.getLeftChild();
            }
            return node;

    }

    private Node delete(String value, Node node) {
        if (node == null) return null;

        int compareResult = node.getWord().compareToIgnoreCase(value);
        if(compareResult > 0){
            Node left = delete(value, node.getLeftChild());
            node.setLeftChild(left);
        }
        else if(compareResult < 0){
            Node right = delete(value, node.getRightChild());
            node.setRightChild(right);
        } else {
            // node here to be deleted
            if(node.hasRightChild()) {
                Node aux = this.replace(node.getRightChild());
                node.setWord(aux.getWord());
                aux = delete(node.getWord(), node.getRightChild());
                node.setRightChild(aux);
            } else if (node.hasLeftChild()) {
                node = node.getLeftChild();
            } else {
                    return null;
            }

        }
        node.setHeight();
        if(Math.abs(node.getBalance()) > 1) node = node.reBalance(node.getBalance());
        return node;
    }

    public void delete(String value) {
        this.root = this.delete(value, this.root);
    }

}
