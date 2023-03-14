import java.net.*;
import java.io.*;

public class client {
     
    Socket socket;

    BufferedReader br; //for reading 
    PrintWriter out;  //for writing
    public client()
    {
       try
       {
        System.out.println("sending request to server");
        socket=new Socket("127.0.0.1",7777);
        System.out.println("connection done");
        
        br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out=new PrintWriter(socket.getOutputStream());
        
        startReading();
        startWriting();


       }catch(Exception e){

       }
    }

    public void startReading()
    {
        //thread:to keep on reading and sending
       
        Runnable r1=()->    //thread
        {
           System.out.println("reader started");

           while(true)  //need to read data again & again 
           {
                try{
               String msg =br.readLine(); //meassage from client
               if(msg.equals("exist"))
               {
                System.out.println("server terminated the chat");
                break; //reader end
               }

               System.out.println("server:"+msg);
                   }catch(Exception e)
                   {
                    System.exit(0);//e.printStackTrace();
                   }

        }

        };new Thread(r1).start(); 
    }
    
    public void startWriting()
    {
           //thread: user will take data and will send to client
            Runnable r2=()->
            {   System.out.println("writer started");
                while(true)
                {
                    try
                    {

                    BufferedReader br1 =new BufferedReader(new InputStreamReader(System.in));
                    String content=br1.readLine();
                    out.println(content);
                    out.flush(); //to flush data
                    
                    
                    
                    }catch(Exception e)
                    {
                        System.exit(0);//e.printStackTrace();
                    }
                }

            };
            new Thread(r2).start();  //to start thread
    }
    public static void main(String[] args) {
        System.out.println("this is client");
        new client();
    }
}
