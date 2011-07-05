package org.domain.vamdcportallevel2.session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asif Akram
 */
public class TAPHTTPAdaptor implements Runnable {

    private String[] queryURIArray = null;
    private UUID uuid = null;
    List<TAPHTTPGet> workerThread = new ArrayList<TAPHTTPGet>();
    private boolean done = false;

    public TAPHTTPAdaptor(String[] queryURIArrayValue) {
        this.queryURIArray = queryURIArrayValue;
        generateUUID();
    }

    private void generateUUID() {
        uuid = UUID.randomUUID();
        /*
        String randomUUIDString = uuid.toString();
        System.out.println("Random UUID String = " + randomUUIDString);
        System.out.println("UUID version       = " + uuid.version());
        System.out.println("UUID variant       = " + uuid.variant());
        */
    }

    public void run() {
        if(uuid == null){
            generateUUID();
        }
        Iterator iterator = null;
        for (int i = 0; i < queryURIArray.length; i++) {
        	TAPHTTPGet tapGet = null;
        	if(queryURIArray.length == 1){
        		// -99 means only one Query is submitted
        		tapGet = new TAPHTTPGet(queryURIArray[i], uuid, -99);
        	} else {
        		tapGet = new TAPHTTPGet(queryURIArray[i], uuid, i);
        	}
            workerThread.add(tapGet);
            Thread t = new Thread(tapGet);
            t.start();
             Logger.getLogger(TAPHTTPAdaptor.class.getName()).log(Level.INFO, "Worker Thread " + i + " created");
        }

        while (!done) {
            iterator = workerThread.iterator();
            while (iterator.hasNext()) {
                TAPHTTPGet tapGetTemp = (TAPHTTPGet) iterator.next();
                if (tapGetTemp.isDone() == true) {
                    workerThread.remove(tapGetTemp);
                    break;
                }
            }
            if (workerThread.isEmpty()) {
                this.done = true;
                try {
					Thread.sleep(500);
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}
                 Logger.getLogger(TAPHTTPAdaptor.class.getName()).log(Level.INFO, "Worker Thread is Empty");
            }
        }
    }
    
    public String UUIDString(){
    	if(this.uuid != null){
    		return this.uuid.toString();
    	}
    	return null;
    }

    public boolean isDone() {
        return done;
    }
}