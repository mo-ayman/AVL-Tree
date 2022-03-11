import FileManaging.FileManager;
import Implementation.TreeAVL;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static TreeAVL tree;
    public static void main(String[] args){
        tree = new TreeAVL();
        System.out.println("AVL Tree Implementation");
        System.out.println("=======================");
        while (true){
            System.out.println("Choose a mode: ");
            System.out.println("- 1...to receive input from keyboard");
            System.out.println("- 2...to receive input from file");
            System.out.println("- Or type anything else...to exit");
            System.out.print("=> ");
            Scanner read = new Scanner(System.in);
            String mode = read.nextLine();
            if(Objects.equals(mode, "1")){
                while (true){
                    System.out.println("- enter the word of the operation + the word");
                    System.out.println("    e.g.: insert boy...delete apple...search car");
                    System.out.println("- type \"Height\" ...to get the height");
                    System.out.println("- type \"exit\" ...to return to mode selection again");
                    System.out.print("=> ");
                    String input = read.nextLine();
                    if(input.equalsIgnoreCase("Height")){
                        try{
                            System.out.println("Height is : " +tree.getRoot().getHeight());
                        }
                        catch (NullPointerException e){
                            System.out.println("Height is : zero....No elements inserted");
                        }
                    }
                    else{
                        Boolean res = readIO(input);
                        if(!res) break;
                    }
                }
            }
            else if(Objects.equals(mode, "2")){
                while (true){
                    System.out.println("- enter the word of the operation + the file path");
                    System.out.println("    e.g.: insert D:/jjk/jlkl...delete */apple/**...search /car/**");
                    System.out.println("- type \"Height\" ...to get the height");
                    System.out.println("- type \"exit\" ...to return to mode selection again");
                    System.out.print("=> ");
                    String input = read.nextLine();
                    Boolean res ;
                    if(input.equalsIgnoreCase("Height")){
                        try{
                            System.out.println("Height is : " +tree.getRoot().getHeight());
                        }
                        catch (NullPointerException e){
                            System.out.println("Height is : zero....No elements inserted");
                        }
                    }
                    else{
                        res = readFile(input);
                        if(!res) break;
                    }
                }
            }
            else break;
        }
    }
    private static Boolean readIO(String input){
        String[] splitted = input.split(" ");
        String word;
        if(splitted.length != 0){
            if(splitted[0].equalsIgnoreCase("insert")){
                word = splitted[1];
                if(word != null)tree.insert(word);
            }
            else if(splitted[0].equalsIgnoreCase("delete")){
                word = splitted[1];
                if(word != null)tree.delete(word);
            }
            else if(splitted[0].equalsIgnoreCase("search")){
                word = splitted[1];
                if(word != null){
                    if(tree.search(word) != null) System.out.println("found");
                    else System.out.println("not found");
                }
            }
            else return !splitted[0].equalsIgnoreCase("exit");
        }
        return true;
    }

    private static Boolean readFile(String input){
        String[] splitted = input.split(" ");
        List<String> words = new ArrayList<>();
        String word ;
        if(splitted.length != 0){
            if(splitted[0].equalsIgnoreCase("insert")){
                word = splitted[1];
                if(word != null) words = FileManager.readFromFile(word);
                for (int i = 0; i < words.size(); i++) tree.insert(words.get(i));
            }
            else if(splitted[0].equalsIgnoreCase("delete")){
                word = splitted[1];
                if(word != null)words = FileManager.readFromFile(word);
                for (int i = 0; i < words.size(); i++) tree.delete(words.get(i));
            }
            else if(splitted[0].equalsIgnoreCase("search")){
                word = splitted[1];
                if(word != null) words = FileManager.readFromFile(word);
                for (int i = 0; i < words.size(); i++){
                    if(tree.search(words.get(i)) != null) System.out.println("found");
                    else System.out.println("not found");
                }
            }
            else return !splitted[0].equalsIgnoreCase("exit");
        }
        return true;
    }
}
