import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LessonThree {
    public static void main(String[] args) throws IOException {
        byte[] byteArr = readInBiteArray("1.txt");
        ByteArrayInputStream in = new ByteArrayInputStream(byteArr);
        int x;
        while ((x = in.read()) != -1) {
            System.out.print((char) x);
        }
        mergeFiles("2.txt","3.txt","4.txt","5.txt","6.txt");
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер страницы: ");
        int page = sc.nextInt() - 1;
        System.out.println(new String(readList("7.txt", page)));

    }

    public static byte[] readInBiteArray(String file) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (FileInputStream in = new FileInputStream(file)) {
            int x;
            while ((x = in.read()) > -1) {
                out.write(x);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static void mergeFiles(String file1, String file2,String file3, String file4, String file5) throws IOException {
        ArrayList<InputStream> al = new ArrayList<>();
        al.add(new FileInputStream(file1));
        al.add(new FileInputStream(file2));
        al.add(new FileInputStream(file3));
        al.add(new FileInputStream(file4));
        al.add(new FileInputStream(file5));
        try ( BufferedInputStream in = new BufferedInputStream(new SequenceInputStream(Collections.enumeration(al)));) {
           try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("merge.txt"))) {
               int x;
               while ((x = in.read()) != -1) {
                   out.write(x);
               }
           } catch (IOException e) {
            e.printStackTrace();
        }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte [] readList(String file, int page) throws IOException {
        final int PAGE_SIZE = 1800;
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        raf.seek(page * PAGE_SIZE);
        byte[] arr = new byte[1800];
        raf.read(arr);
        raf.close();
        return arr;
    }

}

