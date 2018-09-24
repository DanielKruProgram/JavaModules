//This is a program that encrypts your message
import java.util.Scanner;
public class Crypto {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter message: ");
        String Message = input.nextLine();
        System.out.println("Enter the shift amount: ");
        int shift = input.nextInt();
        System.out.println("Enter the group spacing: ");
        int groupsize = input.nextInt();
        String encrypted;
        encrypted=encryptString(Message, shift, groupsize);
        System.out.println(encrypted);
        String decrypted;
        decrypted = decryptify(encrypted, shift);
        System.out.println("OMG, Someone is decrypting ur mesage!");
        System.out.println(decrypted);
        System.out.println("Would you like to annihilate the decrypted message?");
        if(input.hasNext("y"))
            for(int i=0;i<26; i++){
                bruteForce(encrypted);
            }
    }
    //=========================================================
    public static String encryptString(String crypticText, int shift, int groupsize){
        crypticText = normalizeText(crypticText);
        crypticText = obify(crypticText);
        crypticText = caesarify(crypticText, shift);
        crypticText = groupify(crypticText, groupsize);

        return crypticText;
    }
    //==========================================================
    public static String normalizeText(String Message){

        Message = Message.replaceAll("\\s+", "");
        Message = Message.replaceAll("\\p{Punct}", "");
        Message = Message.toUpperCase();
        return Message;
    }
    //==========================================================
    public static String obify(String crypticText){
        String obifyText = "";
        for(int i=0; i<crypticText.length(); i++){
            char currchar = crypticText.charAt(i);
            if(currchar=='A'||currchar=='E'||currchar=='I'||currchar=='O'||currchar=='U'||currchar=='Y')
                obifyText += "XZ"+currchar;
            else
                obifyText+=currchar;
        }
        System.out.println(obifyText);
        return obifyText;
    }
    //===========================================================
    public static String caesarify(String Message, int shift){
        String result = shiftAlphabet(shift);
        String alphabet = shiftAlphabet(0);
        String newMessage = "";
        for(int i=0; i < Message.length(); i++){
            char currchar = Message.charAt(i);
            int index = alphabet.indexOf(currchar);
            currchar = result.charAt(index);
            newMessage = newMessage + currchar;
        }
        return newMessage;
    }
    //============================================================
    public static String groupify(String Message, int groupsize){
        String emptygroupstring = "";
        String substring;
        for(int i = 0; i < Message.length()/groupsize; i ++){
            if(i==0)
                substring = Message.substring(0, groupsize)+" ";
            else
                substring = Message.substring(i*groupsize,(i+1)*groupsize)+" ";

            emptygroupstring += substring;
        }
        substring = Message.substring(Message.length()-(Message.length()%groupsize));
        emptygroupstring += substring;
        for(int i=0; i<groupsize-(Message.length()%groupsize); i++){
            emptygroupstring+="x";
        }
        return emptygroupstring;
    }
    //===========================================================
    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }
    //===============================================================
    public static String decryptify(String encrypted, int shift){
        String toDecrypt;
        toDecrypt = encrypted.replaceAll("x", "");
        toDecrypt = toDecrypt.replaceAll(" ","");
        String shiftedAlph = shiftAlphabet(shift);
        String alphabet = shiftAlphabet(0);
        String decrypted="";
        for(int i=0; i<toDecrypt.length(); i++){
            char currchar=toDecrypt.charAt(i);
            int index = shiftedAlph.indexOf(currchar);
            currchar = alphabet.charAt(index);
            decrypted+=currchar;
        }
        decrypted=decrypted.replaceAll("XZ","");
        return decrypted;
    }
    //=================================================================
    public static void bruteForce(String encrypted){
        for(int shift=0;shift<26;shift++){
            String decryptattempt= decryptify(encrypted, shift);
            System.out.println(decryptattempt);

        }
    }
}
