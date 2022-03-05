import Implementation.TreeAVL;
import Implementation.Node;
public class Main {
    public static void main(String[] args){

        TreeAVL tree = new TreeAVL();
        tree.insert("9");
        tree.insert("1");
        tree.insert("0");
        tree.insert("1");
        tree.insert("5");
        tree.insert("2");
        tree.insert("3");
       // tree.delete("3");


//        tree.preOrder();
        Node node = tree.search("1");
        if(node == null)
            System.out.println("not found");
//        tree.inOrder();
    }
}
