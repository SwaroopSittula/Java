package amazon.test_02_07_2024;

import java.util.*;

public class FindMinPages {

    public int minPagesToRead(List<Integer> pages, int d) {
        Collections.sort(pages);
        int n = pages.size();

        int pmin = 1, pmid = pages.get(0), pmax = pages.get(n-1); // pages minimum/left, pages mid, pages maximum/right

        long dmax = pages.stream().mapToLong(Integer::longValue).sum(); // pages read = 1 => days required = sum of pages
        long dmid = pages.stream().mapToLong(x -> (long) Math.ceil((double) x/pmid)).sum();

        // pages: p(min) = 1........p(mid).......p(max)
        // days:  d(max)............d(mid).......d(min) = n
        // Given 'd' days
        if( d < n) //Not enough days
            return -1;
        else if (d < dmid) //search within the array b/w p(mid) to p(max)
            return +0;
        else if (d < dmax) //search b/w p(min) to p(mid)
            return -0;
        else // Have enough days to just read one page a day
            return 1;

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        List<Integer> pages = new ArrayList<>();
        for(int i=0; i<n;i++)
            pages.add(scan.nextInt());

        int d = scan.nextInt();

        FindMinPages fmp = new FindMinPages();
        System.out.println(fmp.minPagesToRead(pages, d));
    }
}



