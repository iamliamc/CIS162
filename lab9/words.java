import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * This lab makes changes to an Array List
 * 
 * @author Brandon Shannon & Liam Considine
 * @version 6/30/15
 */
public class words
{
    private ArrayList<String> ourList;

    public words()
    {
        ourList =  new ArrayList<String>();
    }

    public void addWord(String str)
    {
        ourList.add(str);
    }

    public void display(int option)
    {
        if (option == 1)
        {
            System.out.print(ourList);
        }
        if (option == 2)
        {
            for (String str : ourList) {
                System.out.println(str);
            }
        }

        //System.out.println(ourList.get(option));
    }

    public void addWords(int num){
        int i=0;
        while (i < num){
            String str = JOptionPane.showInputDialog("Enter a word: ");
            ourList.add(str);
            i++;
        }
    }

    public int search(String str){
        int location = 0;
        for(int i = 0; i < ourList.size()-1; i++){ 
            if (ourList.get(i).equalsIgnoreCase(str)){
                location = i;
                break;
            }
            else 
            {
                location = -1;
            }
        }
        return(location);
    }

    public ArrayList <String> partialSearch(String str)
    {  int location = 0;
        ArrayList<String> results = new ArrayList<String>();

        for (int i = 0; i <ourList.size()-1; i++){
            if(ourList.get(i).toLowerCase().contains(str.toLowerCase()))
            {results.add(ourList.get(i));
            }
        }
        return results;
    }

    public void removeEveryOther()
    { for(int i =0; i<ourList.size()-1; i=i+1)
        { ourList.remove(i);
        }

    }
}
