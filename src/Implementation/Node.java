package Implementation;

public class Node {
    private String word;
    private Node leftChild;
    private Node rightChild;
    private Integer height;

    public Node(String word) {
        this.word = word;
        this.leftChild = null;
        this.rightChild = null;
        this.height = 0;
    }

    public String getWord() {return word;}
    public void setWord(String word) {this.word = word;}

    public Node getLeftChild() {return leftChild;}
    public void setLeftChild(Node leftChild) {this.leftChild = leftChild;}
    public boolean hasLeftChild(){return this.leftChild != null;}

    public Node getRightChild() {return rightChild;}
    public void setRightChild(Node rightChild) {this.rightChild = rightChild;}
    public boolean hasRightChild(){return this.rightChild != null;}

    public void setHeight(){
        int leftHeight = -1, rightHeight = -1;
        if(this.hasLeftChild()) leftHeight = this.getLeftChild().getHeight();
        if(this.hasRightChild()) rightHeight = this.getRightChild().getHeight();
        this.height = 1 + Math.max(leftHeight, rightHeight);
    }
    public Integer getHeight() {return this.height;}

    private Node rotateRight(){ ///its left child will replace it
        Node left = this.leftChild;
        if(left.hasRightChild()) this.setLeftChild(left.getRightChild());
        else this.setLeftChild(null);
        left.setRightChild(this);
        return left;
    }

    private Node rotateLeft(){ ///its right child will replace it
        Node right = this.getRightChild();
        if(right.hasLeftChild()) this.setRightChild(right.getLeftChild());
        else this.setRightChild(null);
        right.setLeftChild(this);
        return right;
    }

    public int getBalance(){
        int leftHeight = -1, rightHeight = -1;
        if(this.hasLeftChild()) leftHeight = this.getLeftChild().getHeight();
        if(this.hasRightChild()) rightHeight = this.getRightChild().getHeight();
        return leftHeight - rightHeight;
    }

    public Node reBalance(int balance){
        Node afterBalanceNode = null;
        if(balance > 1){
            int leftChildBalance = this.getLeftChild().getBalance();
            if(leftChildBalance < 0) {
                this.setLeftChild(this.getLeftChild().rotateLeft());
                this.getLeftChild().getLeftChild().setHeight();
                this.getLeftChild().setHeight();
            }
            afterBalanceNode = this.rotateRight();
            afterBalanceNode.getRightChild().setHeight();
            afterBalanceNode.setHeight();
        }
        else if(balance < 1){
            int rightChildBalance = this.getRightChild().getBalance();
            if(rightChildBalance > 0) {
                this.setRightChild(this.getRightChild().rotateRight());
                this.getRightChild().getRightChild().setHeight();
                this.getRightChild().setHeight();
            }
            afterBalanceNode = this.rotateLeft();
            afterBalanceNode.getLeftChild().setHeight();
            afterBalanceNode.setHeight();
        }
        return afterBalanceNode;
    }
}
