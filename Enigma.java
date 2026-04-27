import java.util.Arrays;

public class Enigma{

    private String rotorInit[] = {"#GNUAHOVBIPWCJQXDKRYELSZFMT",
            "#EJOTYCHMRWAFKPUZDINSXBGLQV",
            "#BDFHJLNPRTVXZACEGIKMOQSUWY",
            "#NWDKHGXZVRIFJBLMAOPSCYUTQE",
            "#TGOWHLIFMCSZYRVXQABUPEJKND"};


    private Rotor rotors[];

    public Enigma(int id1, int id2, int id3, String start){

        rotors = new Rotor[3];
        rotors[0] = new Rotor(rotorInit[id1-1], start.charAt(0));
        rotors[1] = new Rotor(rotorInit[id2-1], start.charAt(1));
        rotors[2] = new Rotor(rotorInit[id3-1], start.charAt(2));

    }


    public String decrypt(String message){
        //TODO
        char[] chars = message.toCharArray();
        for(int i = 0; i < message.length(); i++){
            int idx = rotors[2].indexOf(message.charAt(i));
            char c = rotors[1].charAt(idx);
            int idx2 = rotors[2].indexOf(c);
            char c2 = rotors[0].charAt(idx2);
            chars[i] = c2;
            rotate();

        }
        String decrypted = new String(chars);
        return decrypted;




    }



    public String encrypt(String message){
        //TODO
        char[] chars = new char[message.length()];
        for(int i = 0; i < message.length(); i++){
            int idx = rotors[0].indexOf(message.charAt(i));
            char c = rotors[2].charAt(idx);
            int idx2 = rotors[1].indexOf(c);
            char c2 = rotors[2].charAt(idx2);
            chars[i] = c2;
            rotate();

        }
        String encrypted = new String(chars);
        return encrypted;

    }


    private void rotate(){
        if(rotors[0].rotate()){
            if(rotors[1].rotate()){
                rotors[2].rotate();
            }
        }
    }

}