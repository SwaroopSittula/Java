// https://www.hackerrank.com/challenges/java-vistor-pattern/
package DesignPatterns;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.*;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

enum Color {
    RED, GREEN
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis {
    public abstract int getResult();

    public abstract void visitNode(TreeNode node);

    public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
    private int sumInLeaves = 0;

    public int getResult() {
        //implement this
        return sumInLeaves;
    }

    public void visitNode(TreeNode node) {
        //implement this
    }

    public void visitLeaf(TreeLeaf leaf) {
        //implement this
//        System.out.printf("Leaf Node: %d, Color: %s, Depth: %d%n", leaf.getValue(), leaf.getColor(), leaf.getDepth());
        this.sumInLeaves += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    private long productOfRedNodes = 1L;
    private int modulo = 1000000007; // modulo specified in problem

    public int getResult() {
        //implement this
        return (int) productOfRedNodes;
    }

    public void visitNode(TreeNode node) {
        //implement this
        if (node.getColor().equals(Color.RED))
            productOfRedNodes = (productOfRedNodes * node.getValue()) % modulo;
    }

    public void visitLeaf(TreeLeaf leaf) {
        //implement this
        if (leaf.getColor().equals(Color.RED))
            productOfRedNodes = (productOfRedNodes * leaf.getValue()) % modulo;
    }
}

class FancyVisitor extends TreeVis {
    private int sumOfNodesAtEvenDepth = 0; // TreeNode's at even Depth
    private int sumOfGreenLeafs = 0; //TreeLeaf's with Green Color

    public int getResult() {
        //implement this
        return Math.abs(sumOfNodesAtEvenDepth - sumOfGreenLeafs);
    }

    public void visitNode(TreeNode node) {
        //implement this
        if (node.getDepth() % 2 == 0)
            this.sumOfNodesAtEvenDepth += node.getValue();
    }

    public void visitLeaf(TreeLeaf leaf) {
        //implement this
        if (leaf.getColor().equals(Color.GREEN))
            this.sumOfGreenLeafs += leaf.getValue();
    }
}


public class VisitorPattern {

    public static Tree solve() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(); // Number of nodes
        int[] x = IntStream.generate(scan::nextInt).limit(n).toArray(); // Values of n-nodes
        int[] c = IntStream.generate(scan::nextInt).limit(n).toArray(); // Colors of n-nodes
        int[] lc = new int[n]; // Link Count (leaf nodes = 1)
        scan.nextLine();
        Tree[] nodes = new Tree[n];

        // Scan Edges and Identify Leaf and Non-Leaf Nodes
        List<int[]> edges = Stream.generate(scan::nextLine)
                .limit(n - 1)
                .map(line -> Stream.of((line).split(" ")).mapToInt(Integer::parseInt).map(u -> u - 1).peek(u -> lc[u]++).toArray())
                .collect(Collectors.toList());

        // Root Node
        nodes[0] = new TreeNode(x[0], c[0] == 0 ? Color.RED : Color.GREEN, 0);

        // Complete Edges
        while (!edges.isEmpty()) {
            edges = edges.stream().flatMap(e -> {
                        int u = nodes[e[0]] != null ? e[0] : e[1]; // parent
                        int v = nodes[e[0]] != null ? e[1] : e[0]; // child
                        if (nodes[u] != null) {
                            if (lc[v] > 1) // v is Non-Leaf Node
                                nodes[v] = new TreeNode(x[v], c[v] == 0 ? Color.RED : Color.GREEN, nodes[u].getDepth() + 1);
                            else
                                nodes[v] = new TreeLeaf(x[v], c[v] == 0 ? Color.RED : Color.GREEN, nodes[u].getDepth() + 1);
                            ((TreeNode) nodes[u]).addChild(nodes[v]);
                            return null;
                        } else
                            return Stream.of(e);
                    })
                    .filter(Objects::nonNull)
                    .toList();
        }

//        for (Tree node : nodes)
//            System.out.printf("%s: %d, Color: %s, Depth: %d%n", node.getClass().getSimpleName(), node.getValue(), node.getColor(), node.getDepth());

        return nodes[0];
    }


    public static void main(String[] args) {
        Tree root = solve();
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }


    // Simulating Test using Static-block
    static {
        String[] testCases = {
                "5\n4 7 2 5 12\n0 1 0 0 1\n1 2\n1 3\n3 4\n3 5",
                "100\n237 945 785 695 165 919 740 706 67 342 6 282 965 39 441 922 87 555 936 550 860 972 448 577 931 542 733 374 977 335 709 440 850 493 729 478 871 538 189 557 356 737 341 946 875 969 198 315 376 540 84 216 3 833 51 310 992 898 27 52 670 141 629 770 903 355 834 43 156 882 48 317 959 842 776 671 312 931 640 368 525 900 824 485 292 90 665 646 748 73 636 619 992 603 248 946 472 778 601 935\n0 0 0 0 1 0 0 1 1 0 1 1 0 1 0 1 0 1 1 0 0 1 1 1 0 0 1 0 0 0 1 1 0 0 0 0 1 1 1 1 0 0 0 0 1 1 1 1 1 0 0 1 0 1 0 1 1 0 1 1 0 0 0 0 1 0 0 0 0 0 0 1 1 1 0 0 1 1 1 0 0 0 1 0 0 1 0 0 1 0 0 0 0 1 0 0 0 1 1 0\n1 97\n1 4\n1 5\n1 55\n1 72\n1 73\n1 42\n1 75\n1 12\n1 78\n1 48\n1 82\n1 51\n1 83\n1 87\n1 89\n1 90\n1 32\n1 94\n1 70\n2 4\n2 9\n2 10\n2 11\n2 13\n2 15\n2 17\n2 19\n2 20\n2 31\n2 41\n2 44\n2 45\n2 53\n2 61\n2 62\n2 64\n2 66\n2 79\n2 88\n2 92\n2 93\n2 100\n3 33\n3 99\n3 68\n3 5\n3 47\n3 80\n3 57\n3 26\n3 59\n4 67\n4 36\n4 34\n4 43\n4 56\n4 14\n4 81\n4 24\n4 58\n4 91\n5 65\n5 69\n5 7\n5 8\n5 35\n5 84\n5 85\n5 54\n5 23\n5 27\n6 33\n6 98\n6 76\n6 16\n6 22\n6 29\n6 95\n7 49\n7 52\n7 28\n7 74\n8 77\n8 30\n8 71\n8 40\n9 60\n9 37\n9 63\n9 39\n10 96\n11 38\n12 50\n16 46\n18 97\n19 25\n21 98\n23 86"
        };
        for (int t = 0; t < testCases.length; ) {
            System.setIn(new ByteArrayInputStream(testCases[t].getBytes()));
            System.out.println("Test Case: " + t++);
            if (t != testCases.length)
                VisitorPattern.main(null);
            System.setIn(System.in);
        }
    }

    // Simulating Test using static Inner Class
    static class Test {
        public static void main(String[] args) {
            String[] testCases = {
                    "5\n4 7 2 5 12\n0 1 0 0 1\n1 2\n1 3\n3 4\n3 5",
                    "100\n237 945 785 695 165 919 740 706 67 342 6 282 965 39 441 922 87 555 936 550 860 972 448 577 931 542 733 374 977 335 709 440 850 493 729 478 871 538 189 557 356 737 341 946 875 969 198 315 376 540 84 216 3 833 51 310 992 898 27 52 670 141 629 770 903 355 834 43 156 882 48 317 959 842 776 671 312 931 640 368 525 900 824 485 292 90 665 646 748 73 636 619 992 603 248 946 472 778 601 935\n0 0 0 0 1 0 0 1 1 0 1 1 0 1 0 1 0 1 1 0 0 1 1 1 0 0 1 0 0 0 1 1 0 0 0 0 1 1 1 1 0 0 0 0 1 1 1 1 1 0 0 1 0 1 0 1 1 0 1 1 0 0 0 0 1 0 0 0 0 0 0 1 1 1 0 0 1 1 1 0 0 0 1 0 0 1 0 0 1 0 0 0 0 1 0 0 0 1 1 0\n1 97\n1 4\n1 5\n1 55\n1 72\n1 73\n1 42\n1 75\n1 12\n1 78\n1 48\n1 82\n1 51\n1 83\n1 87\n1 89\n1 90\n1 32\n1 94\n1 70\n2 4\n2 9\n2 10\n2 11\n2 13\n2 15\n2 17\n2 19\n2 20\n2 31\n2 41\n2 44\n2 45\n2 53\n2 61\n2 62\n2 64\n2 66\n2 79\n2 88\n2 92\n2 93\n2 100\n3 33\n3 99\n3 68\n3 5\n3 47\n3 80\n3 57\n3 26\n3 59\n4 67\n4 36\n4 34\n4 43\n4 56\n4 14\n4 81\n4 24\n4 58\n4 91\n5 65\n5 69\n5 7\n5 8\n5 35\n5 84\n5 85\n5 54\n5 23\n5 27\n6 33\n6 98\n6 76\n6 16\n6 22\n6 29\n6 95\n7 49\n7 52\n7 28\n7 74\n8 77\n8 30\n8 71\n8 40\n9 60\n9 37\n9 63\n9 39\n10 96\n11 38\n12 50\n16 46\n18 97\n19 25\n21 98\n23 86"
            };
            for (int t = 0; t < testCases.length; ) {
                System.setIn(new ByteArrayInputStream(testCases[t].getBytes()));
                System.out.printf("%nTest Case: %d%n" , t++);
                VisitorPattern.main(null);
                System.setIn(System.in);
            }
        }
    }

}


