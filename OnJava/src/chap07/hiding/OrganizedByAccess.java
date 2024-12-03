package chap07.hiding;

public class OrganizedByAccess {

    public void pub1() { /* Package access */ }
    public void pub2() { /* Package access */ }
    public void pub3() { /* Package access */ }

    private void priv1() { /* Private access */ }
    private void priv2() { /* Private access */ }
    private void priv3() { /* Private access */ }

    private int i;
}
