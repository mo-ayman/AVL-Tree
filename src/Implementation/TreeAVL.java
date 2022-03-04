package Implementation;

public class TreeAVL {
    private Node root;
    private boolean rebalanced;

    public TreeAVL() {
        this.root = null;
        this.rebalanced = true;
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

    private void inOrder(Node node){
        if(node.hasLeftChild()) inOrder(node.getLeftChild());
        System.out.println("content: " + node.getWord());
        System.out.println("height: " + node.getHeight());
        System.out.println("leftChild : " + node.hasLeftChild());
        if(node.hasLeftChild()) System.out.println("leftValue " + node.getLeftChild().getWord());
        System.out.println("rightChild : " + node.hasRightChild());
        if(node.hasRightChild()) System.out.println("RightValue " + node.getRightChild().getWord());
        System.out.println();
        if(node.hasRightChild()) inOrder(node.getRightChild());
    }
    public void inOrder(){
        inOrder(this.root);
    }


}
