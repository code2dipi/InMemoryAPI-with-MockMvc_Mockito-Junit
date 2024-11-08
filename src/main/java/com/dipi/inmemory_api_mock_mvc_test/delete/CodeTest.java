package com.dipi.inmemory_api_mock_mvc_test.delete;

import java.util.concurrent.*;

interface DownloadAppInterface{
    void downloadImage(String msg);
}
class CodeTest {
     public static void main(String[] args) throws Exception {
         ExecutorService executor = Executors.newSingleThreadExecutor();
         Callable<String> task1=()->{
             Thread.sleep(2000);
             return "Sending Http request";

         } ;
         Callable<String> task2= new Callable<String>() {
             @Override
             public String call() throws Exception {
                 return "doing Http request";
             }
         };
         Callable<String> task3=()-> "Sending http response";

         Callable<Integer> task4=()-> {
             // simulate long running task
           int sum=0;
           for(int i=0;i<10; i++) {
               sum +=i;
               Thread.sleep(100); // delay
           }
           return sum;
         };

         Future<String> result1  = executor.submit(task1);
         Future<String> result2  = executor.submit(task2);
         Future<String> result3  = executor.submit(task3);
         Future<Integer> result4  = executor.submit(task4);



         System.out.println("Main thread is running....");
         System.out.println(result1.get());
         System.out.println(result2.get());
         System.out.println(result4.get());
         System.out.println(result3.get());


         executor.shutdown();



     }

}

