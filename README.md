# february5_2025
The problems that I solved today

1.You are given two strings s1 and s2 of equal length. A string swap is an operation where you choose two indices in a string (not necessarily different) and swap the characters at these indices. Return true if it is possible to make both strings equal by performing at most one string swap on exactly one of the strings. Otherwise, return false. 

Code:
class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        int i,n=s1.length(),cnt=0;
        int[] freq1=new int[26];
        int[] freq2=new int[26];
        for(i=0;i<n;i++)
        {
            if(s1.charAt(i)!=s2.charAt(i))
                cnt++;
            freq1[s1.charAt(i)-'a']++;
            freq2[s2.charAt(i)-'a']++;
        }
        if(cnt!=0 && cnt>2)
            return false;
        for(i=0;i<26;i++)
        {
            if(freq1[i]!=freq2[i])
                return false;
        }
        return true;
    }
}

2.Given a weighted, undirected, and connected graph with V vertices and E edges, your task is to find the sum of the weights of the edges in the Minimum Spanning Tree (MST) of the graph. The graph is represented by an adjacency list, where each element adj[i] is a vector containing vector of integers. Each vector represents an edge, with the first integer denoting the endpoint of the edge and the second integer denoting the weight of the edge.

Code:

class Disjoint
{
    int[] parent;
    int[] rank;
    int n;
    public Disjoint(int n)
    {
        this.n=n;
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++)
            parent[i]=i;
    }
    public int find(int x)
    {
        if(parent[x]==x)
            return x;
        return parent[x]=find(parent[x]);
    }
    public void union(int u,int v)
    {
        int pu=find(u);
        int pv=find(v);
        if(pu==pv)
            return;
        if(rank[pu]<rank[pv])
            parent[pu]=pv;
        else if(rank[pu]>rank[pv])
            parent[pv]=pu;
        else
        {
            parent[pv]=pu;
            rank[pu]++;
        }
    }
}
class Solution {
    static int spanningTree(int V, int E, List<List<int[]>> adj) {
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->Integer.compare(a[2],b[2]));
        for(int i=0;i<adj.size();i++)
        {
            for(int j=0;j<adj.get(i).size();j++)
                pq.add(new int[]{i,adj.get(i).get(j)[0],adj.get(i).get(j)[1]});
        }
        Disjoint d=new Disjoint(V);
        int sum=0;
        while(!pq.isEmpty())
        {
            int[] x=pq.poll();
            int pu=d.find(x[0]);
            int pv=d.find(x[1]);
            if(pu!=pv)
            {
                sum+=x[2];
                d.union(x[0],x[1]);
            }
            
        }
        return sum;
    }
}

3.Given an array par[] that stores all number from 1 to n (both inclusive and sorted) and k queries.The task is to do the following operations on array elements : 1. UNION x z : Perform union of x and z i.e. parent of z will become the parent of x. 2. FIND x: Find the ultimate parent of x and print it. Note: Initially all are the parent of themselves.The ultimate parent is the topmost node such that par[node]=node.

Code:
class GfG {
    int find(int par[], int x) {
        if(par[x]==x)
            return x;
        return par[x]=find(par,par[x]);
    }

    void unionSet(int par[], int x, int z) {
        int px=find(par,x);
        int pz=find(par,z);
        if(px!=pz)
        {
            par[px]=pz;
        }
    }
}

4.There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi] represents a connection between computers ai and bi. Any computer can reach any other computer directly or indirectly through the network. You are given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected. Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.

Code:
class Disjoint
{
    int[] parent;
    int[] rank;
    public Disjoint(int n)
    {
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++)
            parent[i]=i;
    }
    public int find(int x)
    {
        if(parent[x]==x)
            return x;
        return parent[x]=find(parent[x]);
    }
    public void union(int u,int v)
    {
        int pu=find(u);
        int pv=find(v);
        if(pu==pv)
            return;
        if(rank[pu]<rank[pv])
            parent[pu]=pv;
        else if(rank[pu]>rank[pv])
            parent[pv]=pu;
        else
        {
            parent[pv]=pu;
            rank[pu]++;
        }
    }
}
class Solution {
    public int makeConnected(int n, int[][] connections) {
        Disjoint d=new Disjoint(n);
        int extra=0;
        for(int[] x:connections)
        {
            int pu=d.find(x[0]);
            int pv=d.find(x[1]);
            if(pu==pv)
                extra++;
            else
                d.union(x[0],x[1]);
        }
        int cm=0;
        for(int i=0;i<n;i++)
        {
            if(d.parent[i]==i)
                cm++;
        }
        if(extra>=cm-1)
            return cm-1;
        return -1;
    }
}

5.There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c. A province is a group of directly or indirectly connected cities and no other cities outside of the group. You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise. Return the total number of provinces.

Code:
class Disjoint
{
    int[] parent;
    int[] rank;
    public Disjoint(int n)
    {
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++)
            parent[i]=i;
    }
    public int find(int x)
    {
        if(parent[x]==x)
            return x;
        return parent[x]=find(parent[x]);
    }
    public void union(int u,int v)
    {
        int pu=find(u);
        int pv=find(v);
        if(pu==pv)
            return;
        if(rank[pu]<rank[pv])
            parent[pu]=pv;
        else if(rank[pu]>rank[pv])
            parent[pv]=pu;
        else
        {
            parent[pv]=pu;
            rank[pu]++;
        }
    }
}
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n=isConnected.length;
        Disjoint d=new Disjoint(n);
        int i,j;
        for(i=0;i<n;i++)
        {
            for(j=0;j<n;j++)
            {
                if(isConnected[i][j]==1)    
                    d.union(i,j);
            }
        }
        int cnt=0;
        for(i=0;i<n;i++)
        {
            if(d.parent[i]==i)
                cnt++;
        }
        return cnt;
    }
}

6. Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account. Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name. After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Code:
class Disjoint
{
    int[] parent;
    int[] rank;
    public Disjoint(int n)
    {
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++)
            parent[i]=i;
    }
    public int find(int x)
    {
        if(parent[x]==x)
            return x;
        return parent[x]=find(parent[x]);
    }
    public void union(int u,int v)
    {
        int pu=find(u);
        int pv=find(v);
        if(pu==pv)
            return;
        if(rank[pu]<rank[pv])
            parent[pu]=pv;
        else if(rank[pu]>rank[pv])
            parent[pv]=pu;
        else
        {
            parent[pv]=pu;
            rank[pu]++;
        }
    }
}
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String,Integer> m=new HashMap<>();
        int i,j,n=accounts.size();
        Disjoint d=new Disjoint(n);
        for(i=0;i<n;i++)
        {
            for(j=1;j<accounts.get(i).size();j++)
            {
                String s=accounts.get(i).get(j);
                if(m.containsKey(s))
                    d.union(i,m.get(s));
                else
                    m.put(s,i);
            }
        }
        Map<Integer,List<String>> h=new HashMap<>();
        for(Map.Entry<String,Integer> x:m.entrySet())
        {
            int p=d.find(x.getValue());
            if(!h.containsKey(p))
                h.put(p,new ArrayList<>());
            h.get(p).add(x.getKey());
        }
        List<List<String>> res=new ArrayList<>();
        for(i=0;i<n;i++)
        {
            if(h.containsKey(i))
            {
                List<String> l=h.get(i);
                Collections.sort(l);
                l.add(0,accounts.get(i).get(0));
                res.add(l);
            }
        }
        return res;
    }
}
