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