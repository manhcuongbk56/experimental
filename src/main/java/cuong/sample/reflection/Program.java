package cuong.sample.reflection;

public class Program
{
    static class He {
        private int a;

        public He(int a) {
            this.a = a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getA() {
            return this.a;
        }
    }
    public static void main(String[] args) {
        final He he = new He(1);

        he.setA(3);
        System.out.println(he.getA());
    }
}
