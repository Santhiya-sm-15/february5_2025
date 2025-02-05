
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