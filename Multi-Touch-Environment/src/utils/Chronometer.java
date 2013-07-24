/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author xhendor
 */
public final class Chronometer{
    private  long begin, end;
    
    public  void start(){
        begin = System.currentTimeMillis();
    }
 
    public  void stop(){
        end = System.currentTimeMillis();
    }
 
    public  long getTime() {
        return end-begin;
    }
 
    public  long getMilliseconds() {
        return end-begin;
    }
 
    public  double getSeconds() {
                end = System.currentTimeMillis();

        return (end - begin) / 1000.0;
    }
 
    public  double getMinutes() {
        return (end - begin) / 60000.0;
    }

 

}
