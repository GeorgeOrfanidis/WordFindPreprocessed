import java.io.*; 
import java.util.*;

public class WordFindP
{
     public static void print2D(char mat[][]) 
    { 
        // Loop through all rows 
        for (int i = 0; i < mat.length; i++) 
        {
            if(i!=0)
                System.out.println();
            for (int j = 0; j < mat[i].length; j++) 
            {  
                System.out.print(mat[i][j] + " ");
            }
        }
    } 
    public static void PrintInfo(Information ReturnedInfoPara,Information SearchingWordPara)
    {
        if(ReturnedInfoPara==null)
        {
            String ReturnedString=SearchingWordPara.GetString(); 
            System.out.print(ReturnedString+" "+"was NOT found"+".");
            System.out.println();
        }
        else
        {
            String ReturnedString=ReturnedInfoPara.GetString(); 
            System.out.print(ReturnedString+" "+"was found"+" ");
            int ReturnedRow=ReturnedInfoPara.GetRow();
            System.out.print("at"+" "+"row:"+ReturnedRow+" ");
            int ReturnedColumn=ReturnedInfoPara.GetColumn();
            System.out.print("and"+" "+"column:"+ReturnedColumn);
            int ReturnedDirectionX=ReturnedInfoPara.GetDX();
            int ReturnedDirectionY=ReturnedInfoPara.GetDY();
            String ReturnedDirection=ReturnedInfoPara.Direction(ReturnedDirectionX,ReturnedDirectionY);
            System.out.print(","+" "+"oriented"+" "+ReturnedDirection+".");
            System.out.println();
        }
    }
    public static void main (String [] args)throws FileNotFoundException 
    {
        File text=new File(args[0]); 
       Scanner scan=new Scanner(text); 
       String firstLine=scan.next();
       int sizeOfMatrix=(firstLine.length()-1)/2;
       char grid[][]=new char[sizeOfMatrix][sizeOfMatrix];
       
       for(int row=0;row<sizeOfMatrix;row++)
       {
           
           String result=scan.next();
           scan.next();
           
           for(int column=0,skipDash=1;column<sizeOfMatrix && skipDash<firstLine.length();column++,skipDash+=2)
           {
             
             
               grid[row][column]=result.charAt(skipDash);
            }       
       }        
       scan.close();
        
        // print2D(grid);//function to print the grid 
        
        int x[] = { -1, -1, -1, 0, 0, 1, 1, 1 }; 
        int y[] = { -1, 0, 1, -1, 1, -1, 0, 1 }; 
        String word="";
        int sizeOfWords=3;
        BST tree=new BST();
        BSTNode node=new BSTNode();
        for(int row=0;row<grid.length;row++)
        {
            for(int column=0;column<grid.length;column++)
            {
               for(int direction=0;direction<8;direction++ )
               { 
                   int rowd=row,columnd=column;
                   word="";
                   while(true)
                  {
                       
                   if (rowd>=grid.length || rowd < 0 || columnd >=grid.length || columnd < 0) 
                        { 
                             
                             break;
                        }
                   char chara=grid[rowd][columnd];
                   word+=chara;
                   if(word.length()>=sizeOfWords)//i can set this to be to >= insted of just >
                   {   
                       Information info= new Information();
                       //object that will be added to the BST with all the information 
                       info.SetRow(row+1); //we add 1 to represent the real position in the matrix, array start at 0, humans start with 1 
                       info.SetColumn(column+1);
                       info.SetDX(x[direction]);
                       info.SetDY(y[direction]);
                       info.SetString(word);
                       node.setValue(info);
                       tree.insert(info);
                   }   
                   rowd += x[direction];
                   columnd += y[direction];
                  }
                }   
            }
        }
        if(args.length==1)
       {
            String ans="yes";
            while(ans.equals("yes")||ans.equals("YES")||ans.equals("Yes"))
            {
                System.out.println("Do you want to search for another Word? Type it");
                Scanner userInput = new Scanner(System.in);
                String variable = userInput.next().toUpperCase();
                
                Information SearchingWord= new Information();
                SearchingWord.SetString(variable);
                Information ReturnedInfo = (Information) tree.find(SearchingWord);
                PrintInfo(ReturnedInfo,SearchingWord);
                System.out.println("Do you want to search for another Word? If yes type yes,else no");
                
                 ans=userInput.next();
                 
            } //while(ans=="yes"||ans=="YES"||ans=="Yes");
           
        }
        else if (args.length==2)
        {
            File text1=new File(args[1]); 
           Scanner scan1=new Scanner(text1); 
           int numStrings=0;
           while( scan1.hasNext())
           {
               scan1.next();
               numStrings++;
           }
           scan1.close();
           File text2=new File(args[1]); 
           Scanner scan2=new Scanner(text2); 
           String words[]=new String[numStrings];
           for( int i=0;i<numStrings;i++)
           {
               String text3=scan2.next();
               words[i]=text3;
            }
    
        
            for( int i=0;i<words.length;i++)
            {
                String s2=words[i];
                Information SearchingWord= new Information();
                SearchingWord.SetString(s2);
                Information ReturnedInfo = (Information) tree.find(SearchingWord);
                PrintInfo(ReturnedInfo,SearchingWord);
            }
            scan2.close();
           
            
        }
         
        
        
        
    }          
}            
               
            
            
        
    
         
                
                
            
            
        
    

