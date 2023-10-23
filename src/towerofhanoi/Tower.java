package towerofhanoi;

public class Tower extends LinkedStack<Disk> {
    private final Position position;

    Tower(Position position) {
        super();
        this.position = position;
    }

    public Position position()
    {
        return position;
    }

    @Override
    public void push(Disk disk) {
         if(disk == null ) throw new IllegalArgumentException();
         if(super.isEmpty()) {
             super.push(disk);
             return ;
         }
         else if(super.topNode.getData().compareTo(disk) >0) {
             super.push(disk);
             return ;
         }
         throw new IllegalStateException();
    }



}