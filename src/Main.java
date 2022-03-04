import Implementation.TreeAVL;

public class Main {
    public static void main(String[] args){

        TreeAVL tree = new TreeAVL();
        tree.insert("e");
        tree.insert("b");
        tree.insert("f");
        tree.insert("d");
        tree.insert("c");
        tree.inOrder();
    }
}
