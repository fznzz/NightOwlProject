package ugm.fznzz.nightowlproject.dataclass;

public class desiredRoom {

    private String room;
    private String state;


    public desiredRoom(String room, String state) {
        this.room = room;
        this.state = state;
    }

    public desiredRoom()
    {

    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }
}
