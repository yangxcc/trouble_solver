package tree

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

type Stack struct {
	list []*TreeNode
}

func (s *Stack) Empty() bool {
	return len(s.list) == 0
}

func (s *Stack) Push(item *TreeNode) {
	s.list = append(s.list, item)
}

func (s *Stack) Pop() TreeNode {
	if s.Empty() {
		return TreeNode{}
	}
	lth := len(s.list)
	top := s.list[lth-1]
	s.list = s.list[0 : lth-1]
	return *top
}

// Top == Peek
func (s *Stack) Top() TreeNode {
	if s.Empty() {
		return TreeNode{}
	}
	return *s.list[len(s.list)-1]
}

func (s *Stack) Length() int {
	return len(s.list)
}

// ================= 队列 =================
type Queue struct {
	list []*TreeNode
}

func (q *Queue) Empty() bool {
	return len(q.list) == 0
}

func (q *Queue) Push(item *TreeNode) {
	q.list = append(q.list, item)
}

func (q *Queue) Pop() *TreeNode {
	if q.Empty() {
		return &TreeNode{}
	}
	val := q.list[0]
	q.list = q.list[1:]
	return val
}

func (q *Queue) Top() *TreeNode {
	if q.Empty() {
		return &TreeNode{}
	}

	return q.list[0]
}

func (q *Queue) Length() int {
	return len(q.list)
}
