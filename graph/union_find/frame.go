package unionfind

type Frame struct {
	Count  int
	Parent []int
}

func InitUnionFind(count int) *Frame {
	parent := make([]int, count)
	for i := 0; i < count; i++ {
		parent[i] = i
	}
	return &Frame{Count: count, Parent: parent}
}

func (f *Frame) Union(p int, q int) {
	pRoot := f.findRoot(p)
	qRoot := f.findRoot(q)

	if pRoot == qRoot {
		return
	}

	f.Parent[pRoot] = qRoot
	f.Count--
}

func (f *Frame) Connect(p int, q int) bool {
	pRoot := f.findRoot(p)
	qRoot := f.findRoot(q)

	return pRoot == qRoot
}

// 这里用迭代写
func (f *Frame) findRoot(x int) int {
	root := x
	for root != f.Parent[root] {
		root = f.Parent[root]
	}

	oldParent := f.Parent[x]
	for root != x {
		f.Parent[x] = root
		x = oldParent
		oldParent = f.Parent[oldParent]
	}

	return f.Parent[x]
}
