package chapter3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListFiles {

  public static void main(String[] args) {
    try {
      //viewing files in current directory
      Files.list(Paths.get(".")).forEach(System.out::println);
      
      //we only want the subdirectories in the current directory
      Files.list(Paths.get(".")).filter(Files::isDirectory).forEach(System.out::println);
      
      //old way to get specific files
      final String[] files = new File("src/chapter3").list(new java.io.FilenameFilter() {
         public boolean accept(final File dir, final String name) {
           return name.endsWith(".java");
         }
      });
      System.out.println(files);
      
      //new way
      Files.newDirectoryStream(Paths.get("src/chapter3"), 
          path -> path.toString().endsWith(".java"))
          .forEach(System.out::println);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    //get files based on some property, such as being hidden
    final File[] files = new File(".").listFiles(File::isHidden);
  }
  
  //listing subdirectories the old way
  public static void listTheHardWay() {
    List<File> files = new ArrayList<>();
    File[] filesInCurrentDir = new File(".").listFiles();
    for(File file : filesInCurrentDir) {
      File[] filesInSubDir = file.listFiles();
      if(filesInSubDir != null) {
        files.addAll(Arrays.asList(filesInSubDir));
      }else {
        files.add(file);
      }
    }
    System.out.println("Count: " + files.size());
  }
  
  //listing subdirectories the new way
  public static void betterWay() {
    List<File> files = Stream.of(new File(".").listFiles())
        .flatMap(file -> file.listFiles() == null ? Stream.of(file) : Stream.of(file.listFiles()))
        .collect(Collectors.toList());
    System.out.println("Count: " + files.size());
  }
  
  public static void watchFileChange() throws IOException, InterruptedException {
    final Path path = Paths.get(".");
    final WatchService watchService = path.getFileSystem().newWatchService();
    path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
    
    System.out.println("Report any file changed within next 1 minute...");
    
    final WatchKey watchKey = watchService.poll(1, TimeUnit.MINUTES);
    
    if(watchKey != null) {
      watchKey.pollEvents()
      .stream()
      .forEach(event -> System.out.println(event.context()));
    }
  }
}
