public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int minus = Math.abs(x - y);
        return minus == 1;
    }
}
