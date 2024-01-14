class TicTock{
    String state;
    synchronized void tick(boolean running){
        if(!running){
            state="ticked";
            notify();
            return;
        }
        System.out.print("Tick ");
        state = "ticked";
        notify();

        try{
            while(!state.equals("tocked"))
                wait();
        }
        catch (InterruptedException exc){
            System.out.println("thread Interrupted.");
        }
    }
    synchronized void tock(boolean running){
        if(!running){
            state="tocked";
            notify();
            return;
        }
        System.out.println("tock");

        state = "tocked";
        notify();
        try{
            while(!state.equals("ticked"))
                wait();
        }
        catch (InterruptedException exc){
            System.out.println("Thread interrupted");
        }
    }
}

class MyThread implements Runnable{
    Thread thrd;
    TicTock ttOb;

    MyThread (String name,TicTock tt){
        thrd =new Thread(this,name);
        ttOb=tt;
        thrd.start();
    }
    public void run(){
        if(thrd.getName().compareTo("tick")==0) {
            for (int i = 0; i < 5; i++) ttOb.tick(true);
            ttOb.tick(false);
        }
        else {
            for (int i = 0; i < 5; i++) ttOb.tock(true);
            ttOb.tock(false);

            }

            }
        }
        class ThreadCom{
            public static void main(String[] args) {
                TicTock tt = new TicTock();
                MyThread mt1 = new MyThread("tick",tt);
                MyThread mt2 = new MyThread("tock",tt);

                try{
                    mt1.thrd.join();
                    mt1.thrd.join();
                }
                catch (InterruptedException exc){
                    System.out.println("Main thread interrupted");
                }
            }
        }