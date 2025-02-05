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