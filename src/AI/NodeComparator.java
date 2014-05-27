package AI;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node>{
    @Override
    public int compare(Node x, Node y)
    {
        // Assume neither string is null. Real code should
        // probably be more robust
        if (x.getD() < y.getD())
        {
            return -1;
        }
        else if (x.getD() > y.getD())
        {
            return 1;
        }
        return 0;
    }
}
