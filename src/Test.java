public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.solve();
    }

    private void solve() {

        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for(Integer integer : array){
            array[1] = 99;
            System.out.println(integer);
        }
    }

}
