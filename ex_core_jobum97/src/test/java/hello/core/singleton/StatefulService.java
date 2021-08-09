package hello.core.singleton;

public class StatefulService {

    //private int price; //공유 필드의 값이 바뀌면서 오류발생

    public int order(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
        //this.price = price; //여기가 문제!
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
