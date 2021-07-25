package com.atguigu.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.jar.JarEntry;

/**
 * @Author jacklu
 * @Date 07:03:54 2021/06/29
 */
public class Graph {

    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges; //存储图对应的邻接矩阵
    private int numofEdges;//表示边的数目
    private boolean[] isVisited;


    public static void main(String[] args) {
        huisu(30);
        //测试一把图是否创建ok
        int n = 5;
        String[] vertexs = {"A", "B", "C", "D", "E"};
        //创建图
        Graph graph = new Graph(n);
        //循环添加顶点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdges(0, 1, 1);
        graph.insertEdges(0, 2, 1);
        graph.insertEdges(1, 2, 1);
        graph.insertEdges(1, 3, 1);
        graph.insertEdges(1, 4, 1);

        //显示邻接矩阵
        graph.showGraph();

        graph.dfs(graph.isVisited, 0);
    }

    //构造器
    public Graph(int n) {
        //初始化邻接矩阵和vertexList
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        numofEdges = 0;
        isVisited = new boolean[n];
    }

    //插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * @param v1     表示点的下标, 即使用第几个顶点   "A"-"B"  "A"->0   "B"->1
     * @param v2     表示第二个顶点对应的下标
     * @param weight
     */
    public void insertEdges(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numofEdges++;
    }

    /**
     * 返回节点的数目
     *
     * @return
     */
    public int getNumofVertex() {
        return vertexList.size();
    }

    /**
     * 返回边的数目
     *
     * @return
     */
    public int getNumofEdges() {
        return numofEdges;
    }

    /**
     * 返回节点i对应的数据  0->"A"  1->"B"
     *
     * @param i
     * @return
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * 返回v1和v2的对应的权值
     *
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge) + " ");
        }
    }

    /**
     * 得到当前节点的第一个邻接节点
     *
     * @param index 当前节点的下标
     * @return 第一个邻接节点的下标或-1
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据当前节点的下一个邻接节点的下标来获取下下一个邻接节点的下标
     *
     * @param v1 当前节点
     * @param v2 当前节点的下一个节点
     * @return 当前节点的下下一个节点或-1
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 深度优先遍历
     *
     * @param isVisited 是否访问过的标记
     * @param i         第一次访问的节点的下标, 从哪个节点开始访问
     */
    private void dfs(boolean[] isVisited, int i) {
        //首先我们访问该节点, 输出
        System.out.print(getValueByIndex(i) + "->");
        //将该节点设置成已访问
        isVisited[i] = true;
        //查找i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while (w != -1) {//说明有
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //节点w已经被访问过时, 再寻找下一个邻接节点
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs进行重载, 遍历我们所有的节点, 并进行dfs
    public void dfs() {
        //遍历所有的节点, 进行dfs[回溯]
        for (int i = 0; i < getNumofVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    public static void huisu(int i) {
        while (i >= 29) {
            huisu(i - 1);
            System.out.println(i);
            i--;
        }


    }
}
