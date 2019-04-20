import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

public class marker {
    public static void main(String[] args) {

        String fileName = args[0];
        String input = args[1];
        String output = args[2];
        boolean bool_if = Boolean.parseBoolean(args[3]);
        boolean bool_else = Boolean.parseBoolean(args[4]);
        boolean bool_elseif = Boolean.parseBoolean(args[5]);
        boolean bool_switch = Boolean.parseBoolean(args[6]);
        boolean bool_for = Boolean.parseBoolean(args[7]);
        boolean bool_while = Boolean.parseBoolean(args[8]);
        String feedBack = "";
        String tempOutput = "";
        double maxGrade = Integer.parseInt(args[9]);
        double compilePerc = Integer.parseInt(args[10]);
        double docCommentPerc = Integer.parseInt(args[11]);
        double commentPerc = Integer.parseInt(args[12]);
        double statementPerc = Integer.parseInt(args[13]);

        double grade = 0;

        // Compile Code 
        try {
            Process p = Runtime.getRuntime().exec("javac " + fileName);
            p.waitFor();
            Process p2 = Runtime.getRuntime().exec("java " + fileName.substring(0, fileName.lastIndexOf('.')));
            BufferedWriter s = new BufferedWriter(new OutputStreamWriter(p2.getOutputStream()));
            s.write(input);
            s.close();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(p2.getInputStream()));
            String line=null;
            while((line=br.readLine())!=null) {
                tempOutput += line;

            }

            if (tempOutput.equals(output)) {
                feedBack += "\nO- BUILD SUCCESSFUL & output was right";
                grade += (compilePerc*maxGrade)/100;

            }else {
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(p2.getErrorStream()));
                if (errorReader.ready()) {
                    feedBack += "\nX- There was an error while compiling the code:";
                    while ((line = errorReader.readLine()) != null) {
                        feedBack += line;
                    }
                }else{
                    feedBack += "\nX-BUILD SUCCESSFUL but output was wrong";
                }
            }
            p2.waitFor();
            
        }catch (Exception e) {
            e.printStackTrace();
        }

        // File to string
        StringBuilder fileString = new StringBuilder(); 
        try {
            Scanner myReader = new Scanner(new File(fileName)); 
            while (myReader.hasNextLine()) {
                fileString.append("\n" + myReader.nextLine());
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 
        String regex = null;
        Pattern pattern;
        Matcher matcher;

        // Searching in the code for Documentation Comments
        regex = "(?:\\/\\*\\*)[\\s\\S]*(?:\\*)\\s*(?:@author)[\\s\\S]*(?:\\*)\\s*(?:@version)[\\s\\S]*(?:\\*)\\s*(?:@since)[\\s\\S]*(?:\\*\\/)";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(fileString);
        if (matcher.find()){
            feedBack += "\nO- Your Documentation Comments were good";
            grade += (docCommentPerc*maxGrade)/100;
        }else{
            feedBack += "\nX- You should write your Documentation Comments";
        }

        // Searching in the code for Comments
        regex = "(?:\\/\\/)( |.)*";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(fileString);
        int commentsLines = 0;
        while (matcher.find()){
            commentsLines++;
        }
        if (commentsLines > 5){
            feedBack += "\nO- Your use of comments are very good";
            grade += (commentPerc*maxGrade)/100;
        }else {
            feedBack += "\nX- You should use More Comments";
        }

        double statementCount = 0;
        double statementgrade = 0;
        // Searching in the code 
        if (bool_if){
            statementCount++;
            regex = "(?:if)\\s*(?:\\()(?:.*)(?:\\))\\s*(?:\\{)[\\s\\S]*(?:\\})";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(fileString);
            if (matcher.find()){
                feedBack += "\nO- Using if statement did great job in solving the problem";
                statementgrade++;
            }else{
                feedBack += "\nX- You should use if Statement";
            }
        }

        if (bool_else){
            statementCount++;
            regex = "(?:if)\\s*(?:\\()(?:.*)(?:\\))\\s*(?:\\{)[\\s\\S]*(?:\\})\\s*(?:else)\\s*(?:\\{)[\\s\\S]*(?:\\})";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(fileString);
            if (matcher.find()){
                feedBack += "\nO- Using if-else statement did great job in solving the problem";
                statementgrade++;
            }else{
                feedBack += "\nX- You should use if-else statement";
            }
        }

        if (bool_elseif){
            statementCount++;
            regex = "(?:if)\\s*(?:\\()(?:.*)(?:\\))\\s*(?:\\{)[\\s\\S]*(?:\\})\\s*(?:else if)\\s*(?:\\()(?:.*)(?:\\))\\s*(?:\\{)[\\s\\S]*(?:\\})";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(fileString);
            if (matcher.find()){
                feedBack += "\nO- Using else-if statement did great job in solving the problem";
                statementgrade++;
            }else{
                feedBack += "\nX- You should use else-if statement";
            }     
        }

        if (bool_switch){
            statementCount++;
            regex = "(?:switch)\\s*(?:\\()(?:.*)(?:\\))\\s*(?:\\{)[\\s\\S]*(?:\\})";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(fileString);
            if (matcher.find()){
                feedBack += "\nO- Using switch case did great job in solving the problem";
                statementgrade++;
            }else{
                feedBack += "\nX- You should use switch case";
            }  
        }

        if (bool_for){
            statementCount++;
            regex = "(?:for)\\s*(?:\\()(?:.*)(?:\\;)(?:.*)(?:\\;)(?:.*)(?:\\))\\s*(?:\\{)[\\s\\S]*(?:\\})";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(fileString);
            if (matcher.find()){
                feedBack += "\nO- Using for loop did great job in solving the problem";
                statementgrade++;
            }else{
                feedBack += "\nX- You should use for loop";
            }
        }
        
        if (bool_while){
            statementCount++;
            regex = "(?:while)\\s*(?:\\()(?:.*)(?:\\))\\s*(?:\\{)[\\s\\S]*(?:\\})|(?:do)\\s*(?:\\{)[\\s\\S]*(?:\\})\\s*(?:while)\\s*(?:\\()(?:.*)(?:\\))\\s*(?:\\;)";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(fileString);
            if (matcher.find()){
                feedBack += "\nO- Using while loop did great job in solving the problem";
                statementgrade++;
            }else{
                feedBack += "\nX- You should use while loop";
            }
        }

        grade += (statementgrade/statementCount)*((statementPerc*maxGrade)/100);
        System.out.println("Your grade is: " + grade + "/" + maxGrade);
        System.out.println(feedBack);
    }
}