import java.net.*;
import java.io.*;
 
class Server {

    ServerSocket server;  //variable declaration
    Socket socket;
    BufferedReader br; //for reading 
    PrintWriter out;  //for writing



    public Server()  //constructor
    {

        try{
           server=new ServerSocket(7777);//initialising variable server of line 5 
           System.out.println("Server is ready to accept the connection");
           System.out.println("waiting");
           socket=server.accept();

           br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
           
           out=new PrintWriter(socket.getOutputStream());
           
           startReading();
           startWriting();

        } 
           catch (Exception e)
           {
            e.printStackTrace();
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
                System.out.println("client terminated the chat");
                break; //reader end
               }

               System.out.println("client:"+msg);
                   }catch(Exception e)
                   {
                    e.printStackTrace();
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
                        e.printStackTrace();
                    }
                }

            };
            new Thread(r2).start();  //to start thread
    }


    public static void main(String[] args) {
        System.out.println("this is server ");
        new Server();  //object creation i.e calling


    }
}
