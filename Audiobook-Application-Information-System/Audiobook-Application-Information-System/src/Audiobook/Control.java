package Audiobook;

import java.util.regex.*;

public class Control{

    public Control(){
    	
    }


    public boolean isStringEmpty(String string) {
    	return string.equals("");
    }

    public boolean isEMailValid(String e_mail){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(e_mail);
        if(matcher.matches())
            return true;
        else
            return false;
    }
    
     public String stringFormat(String bookName, String author, String narratorName, String category, int time) {
    	 Integer i;
    	 if(bookName.charAt(0) == '"')
    		 bookName = bookName.substring(1, bookName.length()-1);
    	 
    	 for(i=bookName.length();i<35;i++) {
    		 bookName += " ";
    	 }
    	 
    	 bookName += author;
    	 for(i=author.length();i<30;i++) {
    		 bookName += " ";
    	 }
    	 
    	 bookName += narratorName;
    	 for(i=narratorName.length();i<24;i++) {
    		 bookName += " ";
    	 }
    	 
    	 if(category.charAt(0) == '"')
    		 category = category.substring(1, category.length()-1);
    	 
    	 bookName += category;
    	 for(i=category.length();i<20;i++) {
    		 bookName += " ";
    	 }
    	 int hour  = time/60;
    	 int min = time - 60*hour;
    	 String hour2 = new String();
    	 if(min != 0) {
    		 hour2 = hour + "." + min;
    	 }else {
    		 hour2 = hour +"";
    	 }
    	 bookName += ""+hour2+"";
    	 return bookName;
    	 
     }
     
     public String firstCharacterUpper(String bookName) {
    	 String[] arrStr = bookName.split(" ");
    	 String name = "";
    	 Integer i;
    	 for(i=0;i<arrStr.length;i++) {
    		 if(arrStr[i].compareTo("ve") != 0 && arrStr[i].compareTo("veya") != 0){
    			 arrStr[i] = arrStr[i].substring(0,1).toUpperCase() + arrStr[i].substring(1).toLowerCase();
    		 }
    		 if(i != arrStr.length-1) {
    			  name += arrStr[i]+" ";
    		 }else {
    			 name += arrStr[i];
    		 }
    		
    	 }
    	
    	 return name;
     }
    
     
     public String getName(String line){
    	 String name;
    	 int i=34;
    	
    	while(line.charAt(i) == ' ') {
    		i--;
    	}
    	 
    	name = line.substring(0,i+1);
    	
		return name;
     }




}
