package edu.skku.map.lab6;

import android.content.res.Resources;

public class ThreadEx implements Runnable{
    String Result = "";
    int TestNum = 0;

    @Override
    public void run(){
        for(int i = 0; i < 10; i++){
            if(Thread.currentThread().getName().equals("A")){
                TestNum += 1;
            }
            if(Thread.currentThread().getName().equals("B")){
                TestNum += 1;
            }
            Result += "ThreadName =" + Thread.currentThread().getName() + "TestNum =" + TestNum + "\n";

            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
