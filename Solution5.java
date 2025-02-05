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