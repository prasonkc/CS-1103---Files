public class Test {
    private static int balance;

    static int withdraw(int amount) throws Exception {
        if(balance < amount){
            throw new Exception("Balance cannot be less than withdrawl amount");
        }
        return amount;
    }

    static void main() {
        try{
            withdraw(1000);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
