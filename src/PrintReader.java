import java.util.Locale;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class PrintReader {
    private String txtFile;
        public static void print(String printWhat) {
            String file = printWhat + ".txt";
            try {
                File pFile = new File("");
                File printFile = new File(pFile.getAbsolutePath()+"/print/"+file.toLowerCase(Locale.ROOT));
                Scanner scanner = new Scanner(printFile);
                boolean paused = false;
                while (scanner.hasNextLine() && !paused){
                    String line = scanner.nextLine();
                    if(line.equals("break")){
                        paused = true;
                        System.out.println("to continue type 'y'");
                        System.out.print(">");
                        Scanner reader = new Scanner(System.in);
                        String answer = reader.nextLine();
                        if (answer.equals("y")){
                            paused = false;
                            line = scanner.nextLine();
                        }
                    }
                    System.out.println(line);
                }
            }catch (FileNotFoundException e){
                System.out.println("Print not found");
            }
        }

}
