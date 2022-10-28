package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.*;
import java.net.URL;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void shouldAnswerWithTrue(){
        String fileName=this.getClass().getClassLoader().getResource("").getFile();
        File testDir=new File(fileName);
        File target=testDir.getParentFile();
        File ROOT=new File(target,"classes/ROOT/WEBROOT.txt");
        try(FileInputStream inputStream=new FileInputStream(ROOT)){
            InputStreamReader inputStreamReader1=new InputStreamReader(inputStream);
            BufferedReader bufferedReader1=new BufferedReader(inputStreamReader1);
            System.out.println(readLine(inputStreamReader1));
            System.out.println(readLine(inputStreamReader1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static String readLine(InputStreamReader inputStreamReader) throws IOException {
        char c;
        int a;
        StringBuffer line=new StringBuffer();
        while ((a=inputStreamReader.read())>=0&(c= (char) a)!='\n'){
            line.append(c);
        }
        return line.toString();
    }
}
